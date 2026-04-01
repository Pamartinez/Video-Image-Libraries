package com.samsung.android.gallery.app.activity.external.launcher;

import E3.g;
import android.graphics.Bitmap;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.viewer.DownloadHttpForViewerCmd;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HttpItemViewLauncher extends QuickViewLauncher {
    EventContext mEventContext;
    Runnable mFailCallbackNoMessage;

    public HttpItemViewLauncher(Blackboard blackboard, LaunchIntent launchIntent, Runnable runnable, Runnable runnable2, EventContext eventContext) {
        super(blackboard, launchIntent, runnable);
        this.mFailCallbackNoMessage = runnable2;
        this.mEventContext = eventContext;
    }

    /* access modifiers changed from: private */
    public void onHttpDownloadCompleted(Object[] objArr) {
        boolean z;
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("onHttpDownloadCompleted : item=");
        if (objArr != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        Log.d(str, sb2.toString());
        if (objArr != null) {
            MediaItem mediaItem = objArr[0];
            Bitmap bitmap = objArr[1];
            this.mBlackboard.publish("data://viewer_first_data", mediaItem);
            this.mBlackboard.publish("data://viewer_first_bitmap", objArr);
            new VuLauncher(this.mBlackboard).publishData().launch("location://quickView", 0, mediaItem);
            if (bitmap == null) {
                Log.e(this.TAG, "onHttpDownloadCompleted : null bitmap " + mediaItem);
                return;
            }
            return;
        }
        Runnable runnable = this.mFailCallbackNoMessage;
        if (runnable != null) {
            runnable.run();
        }
    }

    private boolean requestQuickViewHttpItem(Uri uri, String str) {
        String str2 = this.TAG;
        Log.d(str2, "requestQuickViewHttpItem {" + uri + GlobalPostProcInternalPPInterface.SPLIT_REGEX + str + "}");
        if (str == null) {
            str = "image/*";
        }
        new DownloadHttpForViewerCmd().execute(this.mEventContext, UriItemLoader.createHttpMediaItem(uri, str), new g(this));
        return true;
    }

    public void execute() {
        Uri uri;
        Runnable runnable;
        if (isRestartedFromFlipCover()) {
            uri = getUriFromFlipCoverSavedData();
        } else {
            uri = this.mLaunchIntent.getUriData();
        }
        if (!requestQuickViewHttpItem(uri, this.mLaunchIntent.getType()) && (runnable = this.mFailCallback) != null) {
            runnable.run();
        }
    }
}
