package com.samsung.android.gallery.widget.progressbar;

import Qb.e;
import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.SeslProgressBar;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchProgressCircle extends SeslProgressBar {
    private Runnable mShowProgressRunnable;

    public SearchProgressCircle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateVisibility$0() {
        setVisibility(0);
    }

    public void updateVisibility(boolean z) {
        if (z) {
            if (this.mShowProgressRunnable == null) {
                this.mShowProgressRunnable = new e(6, this);
            }
            ThreadUtil.postOnUiThreadDelayed(this.mShowProgressRunnable, 1000);
            return;
        }
        Runnable runnable = this.mShowProgressRunnable;
        if (runnable != null) {
            ThreadUtil.removeCallbackOnUiThread(runnable);
        }
        setVisibility(8);
    }
}
