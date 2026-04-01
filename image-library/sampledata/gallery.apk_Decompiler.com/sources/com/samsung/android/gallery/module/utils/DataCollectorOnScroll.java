package com.samsung.android.gallery.module.utils;

import android.animation.Animator;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sum.core.Def;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DataCollectorOnScroll extends RecyclerView.OnScrollListener implements Animator.AnimatorListener {
    Runnable dataCollectWatchDog = new Runnable() {
        public void run() {
            RecyclerView recyclerView;
            DataCollectorOnScroll dataCollectorOnScroll = DataCollectorOnScroll.this;
            if (dataCollectorOnScroll.mLast && (recyclerView = dataCollectorOnScroll.mRecyclerView) != null) {
                if (recyclerView.getScrollState() == 0) {
                    Log.w("DataCollectorOnScroll", "dataCollect enabled unexpected");
                    BlackboardUtils.collectExternalDataChangedEvent(DataCollectorOnScroll.this.mBlackboard, false);
                    return;
                }
                ThreadUtil.postOnUiThreadDelayed(this, Def.SURFACE_CHANNEL_TIMEOUT_MILLIS);
            }
        }
    };
    Blackboard mBlackboard;
    boolean mLast = false;
    RecyclerView mRecyclerView;

    public DataCollectorOnScroll(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    public void attach(RecyclerView recyclerView) {
        if (this.mRecyclerView == null) {
            this.mRecyclerView = recyclerView;
            recyclerView.addOnScrollListener(this);
            return;
        }
        throw new IllegalStateException("already attached");
    }

    public void destroy() {
        if (this.mLast) {
            Log.w("DataCollectorOnScroll", "dataCollect enabled onDestroy");
            BlackboardUtils.collectExternalDataChangedEvent(this.mBlackboard, false);
        }
        ThreadUtil.removeCallbackOnBgThread(this.dataCollectWatchDog);
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.removeOnScrollListener(this);
            this.mRecyclerView = null;
        }
        this.mBlackboard = null;
    }

    public void enableDataCollect(boolean z) {
        if (this.mLast != z) {
            BlackboardUtils.collectExternalDataChangedEvent(this.mBlackboard, z);
            if (z) {
                ThreadUtil.postOnUiThreadDelayed(this.dataCollectWatchDog, Def.SURFACE_CHANNEL_TIMEOUT_MILLIS);
            } else {
                ThreadUtil.removeCallbackOnBgThread(this.dataCollectWatchDog);
            }
            this.mLast = z;
        }
    }

    public void onAnimationCancel(Animator animator) {
        enableDataCollect(false);
    }

    public void onAnimationEnd(Animator animator) {
        enableDataCollect(false);
    }

    public void onAnimationStart(Animator animator) {
        enableDataCollect(true);
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
        boolean z;
        super.onScrollStateChanged(recyclerView, i2);
        if (i2 != 0) {
            z = true;
        } else {
            z = false;
        }
        enableDataCollect(z);
    }

    public void onAnimationRepeat(Animator animator) {
    }
}
