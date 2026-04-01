package com.samsung.android.gallery.app.activity.external.launcher;

import A9.b;
import android.os.Bundle;
import c0.C0086a;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MirroringViewLauncher extends QuickViewLauncher {
    public MirroringViewLauncher(Blackboard blackboard, LaunchIntent launchIntent, Runnable runnable) {
        super(blackboard, launchIntent, runnable);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$execute$0(MediaItem[] mediaItemArr, CountDownLatch countDownLatch) {
        try {
            mediaItemArr[0] = this.mItemLoader.loadMirroringItem();
            String str = this.TAG;
            Log.d(str, "loadMirroringItem " + MediaItemUtil.getDebugLog(mediaItemArr[0]));
            MediaItem mediaItem = mediaItemArr[0];
            if (mediaItem == null) {
                Runnable runnable = this.mFailCallback;
                if (runnable != null) {
                    runnable.run();
                }
                return;
            }
            this.mBlackboard.publish("data://viewer_first_data", mediaItem);
            loadThumbnailSync(mediaItemArr[0]);
            countDownLatch.countDown();
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "loadMirroringItem failed", (Throwable) e);
            Runnable runnable2 = this.mFailCallback;
            if (runnable2 != null) {
                runnable2.run();
            }
        } finally {
            countDownLatch.countDown();
        }
    }

    public void execute() {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        Bundle extra = this.mLaunchIntent.getExtra();
        String string = extra.getString("location_key");
        int i2 = extra.getInt("item_index", 0);
        String uri = this.mLaunchIntent.getUriData().toString();
        String str = this.TAG;
        StringBuilder q = C0086a.q("loadMirroringItem {", uri, GlobalPostProcInternalPPInterface.SPLIT_REGEX, string, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        q.append(i2);
        q.append("}");
        Log.d(str, q.toString());
        boolean z3 = true;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        MediaItem[] mediaItemArr = new MediaItem[1];
        SimpleThreadPool.getInstance().execute(new b(this, mediaItemArr, countDownLatch, 13));
        try {
            countDownLatch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            Log.e(this.TAG, "loadMirroringItem time-out");
        }
        String str2 = this.TAG;
        StringBuilder sb2 = new StringBuilder("loadMirroringItem ViewerData{");
        if (this.mMediaData != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (mediaItemArr[0] == null) {
            z3 = false;
        }
        sb2.append(z3);
        sb2.append("} +");
        sb2.append(System.currentTimeMillis() - currentTimeMillis);
        Log.d(str2, sb2.toString());
        if (mediaItemArr[0] != null) {
            new VuLauncher(this.mBlackboard).setIsTemp().disableTimeline().publishData().launch(string, i2, mediaItemArr[0]);
        }
    }
}
