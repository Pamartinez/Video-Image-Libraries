package com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor;

import a6.g;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoEditorReceiveHandler {
    private final String TAG = getClass().getSimpleName();
    private final EditorInfo mInfo;
    private String mVideoEditingAppAction;
    private Bundle mVideoEditingAppExtras;
    private BroadcastReceiver mVideoEditingReceiver;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnVideoEditListener {
    }

    public VideoEditorReceiveHandler(EditorInfo editorInfo) {
        this.mInfo = editorInfo;
    }

    private IntentFilter createIntentFilterForVideoEditingApps() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(getVideoEditorSavedActionName());
        if (Features.isEnabled(Features.SUPPORT_APP_TRANSITION)) {
            intentFilter.addAction(getVideoEditorCanceledActionName());
            intentFilter.addAction("com.samsung.app.slowmotion.action.saved");
            intentFilter.addAction("com.samsung.app.slowmotion.action.canceled");
            intentFilter.addAction("com.samsung.app.newtrim.action.saved");
            intentFilter.addAction("com.samsung.app.newtrim.action.canceled");
        }
        return intentFilter;
    }

    private String getVideoEditorAppName() {
        if (getVideoEditorSavedActionName().equals(this.mVideoEditingAppAction) || getVideoEditorCanceledActionName().equals(this.mVideoEditingAppAction)) {
            return "com.sec.android.app.vepreload";
        }
        if ("com.samsung.app.slowmotion.action.saved".equals(this.mVideoEditingAppAction) || "com.samsung.app.slowmotion.action.canceled".equals(this.mVideoEditingAppAction)) {
            return "com.samsung.app.slowmotion";
        }
        if ("com.samsung.app.newtrim.action.saved".equals(this.mVideoEditingAppAction) || "com.samsung.app.newtrim.action.canceled".equals(this.mVideoEditingAppAction)) {
            return "com.samsung.app.newtrim";
        }
        return null;
    }

    private String getVideoEditorCanceledActionName() {
        if (SdkConfig.atLeast(SdkConfig.GED.S)) {
            return "com.sec.android.app.vepreload.singleedit.action.canceled";
        }
        return "com.sec.android.app.vepreload.action.canceled";
    }

    private String getVideoEditorSavedActionName() {
        if (SdkConfig.atLeast(SdkConfig.GED.S)) {
            return "com.sec.android.app.vepreload.singleedit.action.saved";
        }
        return "com.sec.android.app.vepreload.action.saved";
    }

    private void registerVideoEditingAppReceiver(Context context, final OnVideoEditListener onVideoEditListener) {
        if (!SdkConfig.atLeast(SdkConfig.SEM.U)) {
            AnonymousClass1 r0 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    VideoEditorReceiveHandler.this.parseVideoEditingAppIntent(intent);
                    ((VideoEditorTransitionHandler) ((g) onVideoEditListener).e).handleItemEditedEvent();
                }
            };
            this.mVideoEditingReceiver = r0;
            AndroidCompat.registerReceiver(context, r0, createIntentFilterForVideoEditingApps());
        }
    }

    public void clear(Context context) {
        unregisterVideoEditingAppReceiver(context);
        this.mVideoEditingAppExtras = null;
        this.mVideoEditingAppAction = null;
    }

    public Bundle getAppExtras() {
        return this.mVideoEditingAppExtras;
    }

    public boolean isVideoEditingCanceledAction() {
        if (!Features.isEnabled(Features.SUPPORT_APP_TRANSITION)) {
            return false;
        }
        if (getVideoEditorCanceledActionName().equals(this.mVideoEditingAppAction) || "com.samsung.app.slowmotion.action.canceled".equals(this.mVideoEditingAppAction) || "com.samsung.app.newtrim.action.canceled".equals(this.mVideoEditingAppAction)) {
            return true;
        }
        return false;
    }

    public boolean isVideoEditingSavedAction() {
        if (!Features.isEnabled(Features.SUPPORT_APP_TRANSITION)) {
            return getVideoEditorSavedActionName().equals(this.mVideoEditingAppAction);
        }
        if (getVideoEditorSavedActionName().equals(this.mVideoEditingAppAction) || "com.samsung.app.slowmotion.action.saved".equals(this.mVideoEditingAppAction) || "com.samsung.app.newtrim.action.saved".equals(this.mVideoEditingAppAction)) {
            return true;
        }
        return false;
    }

    public void parseVideoEditingAppIntent(Intent intent) {
        if (intent != null) {
            this.mVideoEditingAppAction = intent.getAction();
            Bundle extras = intent.getExtras();
            if (extras != null) {
                extras.putString("launch_from", getVideoEditorAppName());
                this.mInfo.mUri = (Uri) extras.get("saved_uri");
                this.mVideoEditingAppExtras = extras;
                String str = this.TAG;
                Log.d(str, "parseVideoEditingAppIntent " + Logger.toString(extras));
                return;
            }
            Log.w(this.TAG, "parseVideoEditingAppIntent, extras is null");
            return;
        }
        Log.w(this.TAG, "parseVideoEditingAppIntent intent is null");
    }

    public void registerReceiver(Context context, OnVideoEditListener onVideoEditListener) {
        registerVideoEditingAppReceiver(context, onVideoEditListener);
    }

    public synchronized void unregisterVideoEditingAppReceiver(Context context) {
        BroadcastReceiver broadcastReceiver = this.mVideoEditingReceiver;
        if (broadcastReceiver != null) {
            AndroidCompat.unregisterReceiver(context, broadcastReceiver);
            this.mVideoEditingReceiver = null;
        }
    }
}
