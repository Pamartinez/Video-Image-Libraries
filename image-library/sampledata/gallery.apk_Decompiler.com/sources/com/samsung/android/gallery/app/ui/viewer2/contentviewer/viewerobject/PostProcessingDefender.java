package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import i.C0212a;
import t8.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PostProcessingDefender extends ViewerObject {
    private boolean mActivate = false;
    private final Runnable sReload = new e(16, this);

    private void defend(MediaItem mediaItem) {
        long j2;
        int i2;
        if (mediaItem.isPostProcessing()) {
            long currentTimeMillis = System.currentTimeMillis() - mediaItem.getDateTaken();
            if (currentTimeMillis > 600000) {
                StringCompat stringCompat = this.TAG;
                Log.d(stringCompat, "pppd skip old ppp : " + mediaItem.getFileId(), Long.valueOf(currentTimeMillis));
                return;
            }
            int pppRetryCounter = this.mModel.getContainerModel().getPppRetryCounter();
            if (pppRetryCounter > 0) {
                StringCompat stringCompat2 = this.TAG;
                Log.d(stringCompat2, "pppd activate : " + mediaItem.getFileId(), Integer.valueOf(pppRetryCounter));
                this.mActivate = true;
                if (Math.round(((float) (mediaItem.getHeightInDB() * mediaItem.getWidthInDB())) / 1000000.0f) > 49) {
                    i2 = 10000;
                } else {
                    i2 = 3000;
                }
                ThreadUtil.postOnUiThreadDelayed(this.sReload, (long) i2);
                return;
            }
            StringCompat stringCompat3 = this.TAG;
            Log.e((CharSequence) stringCompat3, "pppd defending failed : " + mediaItem.getFileId(), Integer.valueOf(pppRetryCounter));
        } else if (this.mActivate) {
            Long l = (Long) Blackboard.getApplicationInstance().read("debug://PppCompletedStart");
            StringCompat stringCompat4 = this.TAG;
            StringBuilder sb2 = new StringBuilder("pppCompleted done : ");
            Long valueOf = Long.valueOf(mediaItem.getFileId());
            if (l != null) {
                j2 = l.longValue();
            } else {
                j2 = 0;
            }
            C0212a.z(new Object[]{valueOf, Long.valueOf(j2)}, sb2, stringCompat4);
            this.mActivate = false;
            ThreadUtil.removeCallbackOnUiThread(this.sReload);
        }
    }

    /* access modifiers changed from: private */
    public void reload() {
        Log.d(this.TAG, "pppd run");
        this.mModel.getContainerModel().decreasePppRetryCounter();
        BlackboardUtils.requestMediaDataReQuery(getBlackboard());
        ThreadUtil.removeCallbackOnUiThread(this.sReload);
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        defend(mediaItem);
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        defend(mediaItem);
    }
}
