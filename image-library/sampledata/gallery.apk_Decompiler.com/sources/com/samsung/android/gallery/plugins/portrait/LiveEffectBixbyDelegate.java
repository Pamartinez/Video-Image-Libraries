package com.samsung.android.gallery.plugins.portrait;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.module.bixby.BixbyAppStateUtil;
import com.samsung.android.gallery.module.bixby.BixbyKey;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.share.ShareComponent;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.sdk.bixby2.Sbixby;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LiveEffectBixbyDelegate {
    private static final HashMap<String, Object> OPTIONS = new HashMap<String, Object>() {
        {
            put("KEY_SHARE", Boolean.TRUE);
            put("KEY_SCREEN", Integer.valueOf(BixbyKey.ScreenType.VIEWER.toInt()));
        }
    };
    private final SubscriberListener mBixbyRemoveListener = new c(this, 1);
    private final SubscriberListener mBixbyRequestListener = new c(this, 0);
    /* access modifiers changed from: private */
    public final Blackboard mBlackboard;
    private final ILiveEffect mILiveEffect;
    /* access modifiers changed from: private */
    public final MediaItem mMediaItem;
    private Runnable mPendedBixbyAction = null;
    private ShareComponent mShareComponent;

    public LiveEffectBixbyDelegate(ILiveEffect iLiveEffect, Blackboard blackboard, MediaItem mediaItem) {
        this.mILiveEffect = iLiveEffect;
        this.mBlackboard = blackboard;
        this.mMediaItem = mediaItem;
        subscribe();
        updateStateCallback();
    }

    /* access modifiers changed from: private */
    public HashMap<String, Object> getInformationList(MediaItem mediaItem) {
        HashMap<String, Object> hashMap = new HashMap<>();
        for (Map.Entry<String, Object> putInformation : BixbyAppStateUtil.OPTIONS.entrySet()) {
            putInformation(hashMap, putInformation);
        }
        putItemInformation(hashMap, mediaItem);
        return hashMap;
    }

    private void handleBixbyAction(Object obj) {
        Blackboard.postGlobal("command://remove_bixby_action", (Object) null);
        Uri uri = (Uri) obj;
        Log.bx("LiveEffectBixbyDelegate", "handle command [" + Logger.getEncodedString(uri.toString()) + "]");
        String lastPathSegment = uri.getLastPathSegment();
        if (!TextUtils.isEmpty(lastPathSegment) && lastPathSegment.equals("DETAIL_VIEW_SHARE")) {
            String queryParameter = uri.getQueryParameter("KEY_TARGET_COMPONENT_PACKAGE");
            String queryParameter2 = uri.getQueryParameter("KEY_TARGET_COMPONENT_ACTIVITY");
            Log.bx("LiveEffectBixbyDelegate", "handle share [" + Logger.getEncodedString(queryParameter) + "][" + Logger.getEncodedString(queryParameter2) + "]");
            this.mShareComponent = ShareComponent.builder().setPackageName(queryParameter).setClassName(queryParameter2).setFromBixby();
            this.mILiveEffect.onMenuClicked(R$id.action_share);
        }
    }

    private boolean isExecutable(Activity activity, String str) {
        String appState;
        if (activity == null || str == null || (appState = Sbixby.getStateHandler().getAppState(activity.getApplicationContext(), (Bundle) null)) == null || !appState.contains(str)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBixbyAction$0(Object obj) {
        this.mPendedBixbyAction = null;
        handleBixbyAction(obj);
    }

    /* access modifiers changed from: private */
    public void onBixbyAction(Object obj, Bundle bundle) {
        Activity activity = this.mILiveEffect.getActivity();
        if (!isExecutable(activity, this.mBlackboard.getName())) {
            Log.bx("LiveEffectBixbyDelegate", "onBixbyAction skipped, not executable");
        } else if ((DeviceInfo.isDexMode(activity) || SystemUi.isInMultiWindowMode(activity)) && activity.hasWindowFocus()) {
            Log.bx("LiveEffectBixbyDelegate", "onBixbyAction direct call");
            handleBixbyAction(obj);
        } else {
            Log.bx("LiveEffectBixbyDelegate", "onBixbyAction pended");
            this.mPendedBixbyAction = new i(2, this, obj);
        }
    }

    /* access modifiers changed from: private */
    public void onRemoveBixbyAction(Object obj, Bundle bundle) {
        Log.bx("LiveEffectBixbyDelegate", "onRemoveBixbyAction " + this.mPendedBixbyAction);
        this.mPendedBixbyAction = null;
    }

    private void putInformation(HashMap<String, Object> hashMap, Map.Entry<String, Object> entry) {
        String key = entry.getKey();
        HashMap<String, Object> hashMap2 = OPTIONS;
        if (hashMap2.containsKey(key)) {
            hashMap.put(key, hashMap2.get(key));
        } else {
            hashMap.put(key, entry.getValue());
        }
    }

    private void putItemInformation(HashMap<String, Object> hashMap, MediaItem mediaItem) {
        hashMap.put("KEY_URI", BixbyAppStateUtil.getNonNullValue(ContentUri.getUri(mediaItem)));
        hashMap.put("mime_type", BixbyAppStateUtil.getNonNullValue(mediaItem.getMimeType()));
        hashMap.put("KEY_CONTENT_ATTR", BixbyAppStateUtil.getContentType(mediaItem));
        hashMap.put("KEY_CONTENT_TYPE", 1);
        hashMap.put("KEY_CONTENT_ID", Long.valueOf(mediaItem.getFileId()));
        hashMap.put("KEY_PATH", BixbyAppStateUtil.getNonNullValue(mediaItem.getPath()));
        hashMap.put("KEY_SIZE", Long.valueOf(mediaItem.getFileSize()));
        hashMap.put("KEY_RESOLUTION", mediaItem.getWidth() + "x" + mediaItem.getHeight());
        hashMap.put("KEY_ORIENTATION", Integer.valueOf(mediaItem.getOrientation()));
        hashMap.put("KEY_DURATION", Integer.valueOf(mediaItem.getFileDuration()));
    }

    private void subscribe() {
        try {
            this.mBlackboard.subscribeOnUi("command://bixby_action", this.mBixbyRequestListener);
            this.mBlackboard.subscribeOnUi("command://remove_bixby_action", this.mBixbyRemoveListener);
        } catch (Exception unused) {
            Log.bxe("LiveEffectBixbyDelegate", "subscribe bixby action failed");
        }
    }

    private void unsubscribe() {
        try {
            this.mBlackboard.unsubscribe("command://bixby_action", this.mBixbyRequestListener);
            this.mBlackboard.unsubscribe("command://remove_bixby_action", this.mBixbyRemoveListener);
            this.mBlackboard.reset(toString());
        } catch (Exception unused) {
            Log.bxe("LiveEffectBixbyDelegate", "unsubscribe bixby action failed");
        }
    }

    public void destroy() {
        unsubscribe();
    }

    public ShareComponent getShareComponent() {
        ShareComponent shareComponent = this.mShareComponent;
        this.mShareComponent = null;
        return shareComponent;
    }

    public void handlePendedBixbyAction() {
        Log.bx("LiveEffectBixbyDelegate", "handlePendedBixbyAction " + this.mPendedBixbyAction);
        Runnable runnable = this.mPendedBixbyAction;
        if (runnable != null) {
            runnable.run();
        }
    }

    public void updateStateCallback() {
        Sbixby.getStateHandler().updateStateChange(new StateHandler.Callback() {
            public List<String> getUsedPermissionsWhenAppStateRequested() {
                return BixbyAppStateUtil.getUsedPermissions();
            }

            public String onAppStateRequested() {
                if (LiveEffectBixbyDelegate.this.mMediaItem == null) {
                    return BixbyAppStateUtil.generateEmpty();
                }
                String name = LiveEffectBixbyDelegate.this.mBlackboard.getName();
                LiveEffectBixbyDelegate liveEffectBixbyDelegate = LiveEffectBixbyDelegate.this;
                return BixbyAppStateUtil.generate(name, liveEffectBixbyDelegate.getInformationList(liveEffectBixbyDelegate.mMediaItem));
            }
        });
    }
}
