package com.samsung.android.gallery.widget.listview.scroller;

import K4.a;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GoToTopDelegate {
    private static final boolean IS_RTL = Features.isEnabled(Features.IS_RTL);
    private float mBottomPadding;
    private boolean mEnable;
    private final RecyclerView mRecyclerView;
    private String mScreenId;

    public GoToTopDelegate(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setEnable$0(RecyclerView recyclerView) {
        AnalyticsLogger.getInstance().postLog(this.mScreenId, AnalyticsEventId.EVENT_GO_TO_TOP.toString());
        return false;
    }

    public void setEnable(boolean z, boolean z3) {
        this.mEnable = z;
        if (z) {
            if (z3) {
                this.mRecyclerView.seslSetGoToTopEnabled(true, false);
            } else {
                this.mRecyclerView.seslSetGoToTopEnabled(true);
            }
            this.mRecyclerView.seslSetOnGoToTopClickListener(new a(2, this));
            updateGoToTopPosition();
        }
    }

    public void setScreenId(String str) {
        this.mScreenId = str;
    }

    public void setVisibility(boolean z) {
        if (this.mEnable) {
            ViewUtils.setVisibleOrGone(this.mRecyclerView.seslGetGoToTopView(), z);
        }
    }

    public void updateBottomPadding(float f) {
        if (this.mEnable && this.mBottomPadding != f) {
            this.mBottomPadding = f;
        }
    }

    public void updateGoToTopPosition() {
        int i2;
        if (this.mEnable) {
            RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
            if (layoutManager != null) {
                boolean z = IS_RTL;
                int i7 = 0;
                if (z) {
                    i2 = 0;
                } else {
                    i2 = layoutManager.getPaddingLeft() - layoutManager.getPaddingRight();
                }
                if (z) {
                    i7 = layoutManager.getPaddingRight() - layoutManager.getPaddingLeft();
                }
                this.mRecyclerView.seslSetGoToTopPaddingHorizontal(i2, i7);
            }
            if (this.mBottomPadding != 0.0f) {
                RecyclerView recyclerView = this.mRecyclerView;
                recyclerView.seslSetGoToTopBottomPadding(recyclerView.getPaddingBottom() + ((int) this.mBottomPadding));
            }
        }
    }
}
