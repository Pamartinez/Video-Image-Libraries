package com.samsung.android.gallery.app.controller.viewer;

import U5.c;
import V3.b;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.clipboard.ClipDataCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SafeClipboard;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CopyToClipboardCmd extends BaseCommand {
    private MediaItem[] mItems;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ClipboardService {
        private final ServiceConnection mConnection = new ServiceConnection() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onServiceConnected$0(Messenger messenger) {
                ClipboardService.this.sendClipboardBundle(messenger);
                ClipboardService.this.unbindService();
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                SimpleThreadPool.getInstance().execute(new a(this, new Messenger(iBinder)));
            }

            public void onServiceDisconnected(ComponentName componentName) {
            }
        };
        private final String mMimeType;
        private final String mPath;

        public ClipboardService(String str, String str2) {
            this.mMimeType = str;
            this.mPath = str2;
        }

        public void sendClipboardBundle(Messenger messenger) {
            try {
                Message obtain = Message.obtain((Handler) null, 4096);
                obtain.setData(SeApiCompat.createClipboardIntent(this.mMimeType, this.mPath).getExtras());
                messenger.send(obtain);
                Log.d("ClipboardService", "sendClipboardBundle ");
            } catch (Exception e) {
                Log.e((CharSequence) "ClipboardService", "sendClipboardBundle failed", (Throwable) e);
            }
        }

        public void start() {
            try {
                Intent intent = new Intent("com.sec.android.clipboard.REQUEST_REMOTE_CONTROL");
                intent.setPackage("com.samsung.clipboardsaveservice");
                AppResources.getAppContext().bindService(intent, this.mConnection, 1);
            } catch (Exception e) {
                Log.e((CharSequence) "ClipboardService", "start > bind failed.", (Throwable) e);
            }
        }

        public void unbindService() {
            try {
                AppResources.getAppContext().unbindService(this.mConnection);
            } catch (Exception e) {
                Log.e((CharSequence) "ClipboardService", "unbindService failed", (Throwable) e);
            }
        }
    }

    /* access modifiers changed from: private */
    public void copyToClipboard() {
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            execClipboardManager();
        } else if (!SdkConfig.atLeast(SdkConfig.GED.S) || !execClipboardManager()) {
            execLegacyClipboardService(this.mItems[0]);
        }
    }

    private boolean execClipboardManager() {
        SafeClipboard safeClipboard = new SafeClipboard(getContext());
        if (safeClipboard.hasService()) {
            ClipData clipData = getClipData(this.mItems);
            if (clipData == null) {
                Log.e(this.TAG, "all selected items are invalid");
                Utils.showToast(getContext(), (int) R.string.unsupported_file);
                return true;
            }
            safeClipboard.setPrimaryClip(clipData);
            getBlackboard().postEvent(EventMessage.obtain(1003));
            if (Build.VERSION.SDK_INT <= 32) {
                Utils.showToast(getContext(), (int) R.string.copied_to_clipboard);
            }
            if (PreferenceFeatures.isEnabled(PreferenceFeatures.PasteClipboardViewer)) {
                getBlackboard().postEvent(EventMessage.obtain(3203));
                consumeExecuteListener((Object) null);
            }
            return true;
        }
        Log.e(this.TAG, "no clipboard service");
        return false;
    }

    private void execLegacyClipboardService(MediaItem mediaItem) {
        String path = mediaItem.getPath();
        if (TextUtils.isEmpty(path)) {
            Log.e(this.TAG, "copy to clipboard failed due to null path");
        } else if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            SimpleThreadPool.getInstance().execute(new c(9, mediaItem, path));
        } else {
            getContext().sendBroadcast(SeApiCompat.createClipboardIntent(mediaItem.getMimeType(), path));
        }
    }

    private ClipData getClipData(MediaItem[] mediaItemArr) {
        return ClipDataCompat.of(mediaItemArr, "");
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_COPY_TO_CLIPBOARD.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr = objArr[0];
        this.mItems = mediaItemArr;
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.e(this.TAG, "invalid items");
        } else {
            SimpleThreadPool.getInstance().execute(new b(4, this));
        }
    }
}
