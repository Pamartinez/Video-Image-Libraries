package com.samsung.android.gallery.app.ui.list.stories.header;

import A.a;
import B2.h;
import V3.b;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b6.C0427b;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.PinViewHolder;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinAnimHandler {
    private StoriesPinAdapter mAdapter;
    private int mDividerWidth;
    private int mItemViewWidth;
    RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        private boolean isOngoingPinAnimation() {
            if (!PinAnimHandler.this.hasPinDirtyPosition() || PinAnimHandler.this.mTargetScrollBy <= 0) {
                return false;
            }
            return true;
        }

        private void runPinShowAnimation() {
            if (isOngoingPinAnimation()) {
                PinAnimHandler.this.triggerPinShowAnimation();
            }
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (i2 == 0) {
                runPinShowAnimation();
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i2, int i7) {
            super.onScrolled(recyclerView, i2, i7);
            if (isOngoingPinAnimation()) {
                PinAnimHandler pinAnimHandler = PinAnimHandler.this;
                pinAnimHandler.mScrollAmount = pinAnimHandler.mScrollAmount + i2;
                if (((float) Math.abs(PinAnimHandler.this.mScrollAmount)) / ((float) PinAnimHandler.this.mTargetScrollBy) > 0.8f) {
                    Log.d("PinAnimation", "runPinShowAnimation=" + PinAnimHandler.this.mScrollAmount + GlobalPostProcInternalPPInterface.SPLIT_REGEX + PinAnimHandler.this.mTargetScrollBy);
                    runPinShowAnimation();
                }
            }
        }
    };
    private boolean mPendingNotifyDataChanged;
    private boolean mPinHidden;
    private RecyclerView mRecyclerView;
    private View mRootView;
    /* access modifiers changed from: private */
    public int mScrollAmount;
    /* access modifiers changed from: private */
    public int mTargetScrollBy;

    public PinAnimHandler(View view, RecyclerView recyclerView) {
        this.mRootView = view;
        this.mRecyclerView = recyclerView;
        this.mAdapter = (StoriesPinAdapter) recyclerView.getAdapter();
        this.mRecyclerView.addOnScrollListener(this.mOnScrollListener);
        initDimen(this.mRecyclerView.getResources());
    }

    private void finalizePinAnimation() {
        this.mAdapter.resetPinAnimation();
        restoreViewProperty();
    }

    private int getMaxVisibleNotiCount() {
        if (ResourceCompat.isLandscape(this.mRootView)) {
            return 4;
        }
        return 2;
    }

    private ValueAnimator getRootScaleYAnimator(int i2, int i7) {
        return ValueAnimator.ofInt(new int[]{i2, i7}).setDuration(200);
    }

    private int getTargetScrollBy() {
        int i2;
        int i7;
        int dividerPosition = this.mAdapter.getDividerPosition();
        int min = Math.min(getMaxVisibleNotiCount() + dividerPosition, this.mAdapter.getItemCount() - 1);
        if (dividerPosition != -1) {
            i2 = min;
        } else {
            i2 = min + 1;
        }
        if (dividerPosition > -1 && this.mRecyclerView.getWidth() > 0) {
            int i8 = min - dividerPosition;
            while (i8 > 1 && (this.mItemViewWidth * i8) + this.mDividerWidth > this.mRecyclerView.getWidth()) {
                i2--;
                i8--;
            }
        }
        int i10 = i2 * this.mItemViewWidth;
        if (min != -1) {
            i7 = this.mDividerWidth;
        } else {
            i7 = 0;
        }
        return Math.max((this.mRecyclerView.getPaddingStart() + (i10 + i7)) - this.mRecyclerView.getWidth(), 0);
    }

    private void initDimen(Resources resources) {
        this.mItemViewWidth = resources.getDimensionPixelOffset(R.dimen.stories_pin_recycler_item_margin_end) + resources.getDimensionPixelOffset(R.dimen.stories_pin_recycler_view_size);
        this.mDividerWidth = resources.getDimensionPixelOffset(R.dimen.stories_pin_recycler_divider_margin_end) + resources.getDimensionPixelOffset(R.dimen.stories_pin_recycler_divider_margin_start);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hide$0(ValueAnimator valueAnimator) {
        this.mRootView.getLayoutParams().height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        View view = this.mRootView;
        view.setLayoutParams(view.getLayoutParams());
        ViewUtils.setAlpha(this.mRootView, 1.0f - valueAnimator.getAnimatedFraction());
        if (valueAnimator.getAnimatedFraction() >= 1.0f) {
            if (isPinHiddenState()) {
                ViewUtils.setVisibility(this.mRootView, 8);
            }
            finalizePinAnimation();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$show$1(AtomicBoolean atomicBoolean, boolean z, ValueAnimator valueAnimator) {
        int i2;
        this.mRootView.getLayoutParams().height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        View view = this.mRootView;
        view.setLayoutParams(view.getLayoutParams());
        if (valueAnimator.getAnimatedFraction() > 0.99f && !atomicBoolean.getAndSet(true)) {
            this.mRecyclerView.animate().setDuration(200).alpha(1.0f).start();
            if (z) {
                preparePinItemAnimation();
                this.mTargetScrollBy = getTargetScrollBy();
                a.w(new StringBuilder("mTargetScrollBy = "), this.mTargetScrollBy, "PinAnimation");
                if (this.mTargetScrollBy > 0) {
                    if (Features.isEnabled(Features.IS_RTL)) {
                        i2 = -this.mTargetScrollBy;
                    } else {
                        i2 = this.mTargetScrollBy;
                    }
                    this.mRecyclerView.smoothScrollBy(i2, 0);
                    return;
                }
                ThreadUtil.postOnUiThreadDelayed(new b(28, this), 200);
            }
        }
    }

    private void measureRootView() {
        View view = this.mRootView;
        view.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    private void preparePinItemAnimation() {
        Iterator<Integer> it = this.mAdapter.getPinDirtyPositions().iterator();
        while (it.hasNext()) {
            PinViewHolder pinViewHolder = (PinViewHolder) this.mRecyclerView.findViewHolderForAdapterPosition(it.next().intValue());
            if (pinViewHolder != null) {
                pinViewHolder.preparePinItemAnim();
            }
        }
    }

    private void prepareShowAnimation() {
        ViewUtils.setVisibility(this.mRootView, 0);
        ViewUtils.setAlpha(this.mRootView, 1.0f);
        ViewUtils.setVisibility(this.mRecyclerView, 0);
        ViewUtils.setAlpha(this.mRecyclerView, 0.0f);
    }

    private void resetScrollPosition() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.mRecyclerView.getLayoutManager();
        if (linearLayoutManager != null) {
            linearLayoutManager.scrollToPosition(0);
        }
    }

    private void restoreViewProperty() {
        this.mRootView.getLayoutParams().height = -2;
        View view = this.mRootView;
        view.setLayoutParams(view.getLayoutParams());
        ViewUtils.setAlpha(this.mRecyclerView, 1.0f);
        ViewUtils.setScale(this.mRootView, 1.0f, 1.0f);
        ViewUtils.setAlpha(this.mRootView, 1.0f);
        this.mScrollAmount = 0;
        this.mTargetScrollBy = 0;
    }

    /* access modifiers changed from: private */
    public void triggerPinShowAnimation() {
        Log.d("PinAnimation", "triggerPinShowAnimation");
        for (int i2 = 0; i2 < this.mRecyclerView.getChildCount(); i2++) {
            RecyclerView recyclerView = this.mRecyclerView;
            PinViewHolder pinViewHolder = (PinViewHolder) recyclerView.findContainingViewHolder(recyclerView.getChildAt(i2));
            if (pinViewHolder != null) {
                pinViewHolder.startPinItemAnim();
            }
        }
        finalizePinAnimation();
    }

    public void handleResolutionChange(int i2) {
        initDimen(this.mRecyclerView.getResources());
        if (isPinHiddenState()) {
            this.mPendingNotifyDataChanged = true;
        }
    }

    public boolean hasPinDirtyPosition() {
        return !this.mAdapter.getPinDirtyPositions().isEmpty();
    }

    public void hide() {
        if (!isPinHiddenState()) {
            this.mPinHidden = true;
            if (this.mAdapter.getItemCount() > 0) {
                Log.d("PinAnimation", "hide = " + this.mRootView.getHeight());
                ValueAnimator rootScaleYAnimator = getRootScaleYAnimator(this.mRootView.getHeight(), 0);
                rootScaleYAnimator.addUpdateListener(new h(7, this));
                rootScaleYAnimator.start();
            }
        }
    }

    public boolean isPinHiddenState() {
        return this.mPinHidden;
    }

    public boolean pinAnimRequired() {
        if (!isPinHiddenState() || !hasPinDirtyPosition()) {
            return false;
        }
        return true;
    }

    public void show() {
        if (isPinHiddenState()) {
            this.mPinHidden = false;
            if (this.mAdapter.getItemCount() > 0) {
                if (this.mPendingNotifyDataChanged && this.mRecyclerView.getChildCount() == 0) {
                    this.mAdapter.notifyDataSetChanged();
                }
                prepareShowAnimation();
                measureRootView();
                ValueAnimator rootScaleYAnimator = getRootScaleYAnimator(0, this.mRootView.getMeasuredHeight());
                AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                boolean hasPinDirtyPosition = hasPinDirtyPosition();
                if (hasPinDirtyPosition) {
                    resetScrollPosition();
                }
                Log.d("PinAnimation", "show", Integer.valueOf(this.mRootView.getMeasuredHeight()), Boolean.valueOf(this.mRecyclerView.isAttachedToWindow()), Boolean.valueOf(hasPinDirtyPosition));
                rootScaleYAnimator.addUpdateListener(new C0427b(this, atomicBoolean, hasPinDirtyPosition));
                rootScaleYAnimator.start();
            }
            this.mPendingNotifyDataChanged = false;
        }
    }
}
