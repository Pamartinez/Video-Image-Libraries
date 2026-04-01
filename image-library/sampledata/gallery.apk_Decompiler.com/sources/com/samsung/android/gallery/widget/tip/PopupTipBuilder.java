package com.samsung.android.gallery.widget.tip;

import B2.i;
import C3.n;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sum.core.descriptor.b;
import dc.C0686a;
import java.util.Optional;
import java.util.function.IntSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PopupTipBuilder {
    protected final String TAG = getClass().getSimpleName();
    private IntSupplier mBottomMargin;
    private View mCloseButton;
    private final Context mContext;
    private boolean mDismissAnimationEnabled;
    private OnDismissListener mDismissListener;
    private IntSupplier mGravity;
    private boolean mHideRootViewOnTouch;
    private boolean mIgnoreRootViewTouch;
    private final int mLayoutId;
    private IntSupplier mLeftMargin;
    private View mMainView;
    private final ViewGroup mParentView;
    private IntSupplier mRightMargin;
    protected RelativeLayout mRootView;
    private IntSupplier mTopMargin;
    private int[] mViewIdListDismissWhenClick;
    private final int mViewRes;

    public PopupTipBuilder(ViewGroup viewGroup, int i2, int i7) {
        this.mContext = viewGroup.getContext();
        this.mParentView = viewGroup;
        this.mLayoutId = i2;
        this.mViewRes = i7;
    }

    private void bindView(RelativeLayout relativeLayout) {
        this.mRootView = relativeLayout;
        this.mMainView = relativeLayout.findViewById(this.mViewRes);
        this.mCloseButton = relativeLayout.findViewById(R$id.tip_close_button);
    }

    private void checkIgnoreRootViewTouch() {
        if (this.mIgnoreRootViewTouch) {
            Optional.ofNullable(this.mRootView).ifPresent(new b(15, this));
            View view = this.mMainView;
            if (view != null) {
                view.setOnClickListener(new n(2));
            }
        }
    }

    private void checkViewsDismissWhenClick() {
        ViewUtils.setOnClickListener(this.mCloseButton, new dc.b(this, 0));
        int[] iArr = this.mViewIdListDismissWhenClick;
        if (iArr != null && this.mRootView != null) {
            for (int findViewById : iArr) {
                View findViewById2 = this.mRootView.findViewById(findViewById);
                if (!this.mIgnoreRootViewTouch || findViewById2 != this.mRootView) {
                    ViewUtils.setOnClickListener(findViewById2, new dc.b(this, 1));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void dismissInternal() {
        ViewUtils.post(this.mRootView, new C0686a(this, 1));
        this.mLeftMargin = null;
        this.mTopMargin = null;
        this.mRightMargin = null;
        this.mBottomMargin = null;
        this.mGravity = null;
    }

    private void hide() {
        OnDismissListener onDismissListener = this.mDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onHide();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$checkIgnoreRootViewTouch$0(View view, MotionEvent motionEvent) {
        if (this.mHideRootViewOnTouch) {
            hide();
            return false;
        }
        dismiss();
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkIgnoreRootViewTouch$1(RelativeLayout relativeLayout) {
        relativeLayout.setOnTouchListener(new i(19, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkViewsDismissWhenClick$3(View view) {
        dismiss();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkViewsDismissWhenClick$4(View view) {
        dismiss();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$dismissInternal$5() {
        ViewUtils.removeSelf(this.mRootView);
        OnDismissListener onDismissListener = this.mDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
            this.mDismissListener = null;
        }
    }

    private void updateGravity() {
        IntSupplier intSupplier = this.mGravity;
        if (intSupplier != null) {
            this.mRootView.setGravity(intSupplier.getAsInt());
        }
    }

    public PopupTipBuilder build() {
        bindView((RelativeLayout) LayoutInflater.from(this.mContext).inflate(this.mLayoutId, (ViewGroup) null));
        updateMargin();
        updateGravity();
        checkIgnoreRootViewTouch();
        checkViewsDismissWhenClick();
        return this;
    }

    public void dismiss() {
        Log.d(this.TAG, "dismiss popup tip");
        if (this.mDismissAnimationEnabled) {
            this.mRootView.animate().setDuration(250).alpha(0.0f).withEndAction(new C0686a(this, 0)).start();
        } else {
            dismissInternal();
        }
    }

    public View getView() {
        return this.mMainView;
    }

    public PopupTipBuilder setDismissAnimationEnable(boolean z) {
        this.mDismissAnimationEnabled = z;
        return this;
    }

    public PopupTipBuilder setDismissListener(OnDismissListener onDismissListener) {
        this.mDismissListener = onDismissListener;
        return this;
    }

    public PopupTipBuilder setGravity(IntSupplier intSupplier) {
        this.mGravity = intSupplier;
        return this;
    }

    public PopupTipBuilder setHideRootViewOnTouch(boolean z) {
        this.mHideRootViewOnTouch = z;
        return this;
    }

    public PopupTipBuilder setIgnoreRootViewTouch(boolean z) {
        this.mIgnoreRootViewTouch = z;
        return this;
    }

    public PopupTipBuilder setMargin(IntSupplier intSupplier, IntSupplier intSupplier2, IntSupplier intSupplier3, IntSupplier intSupplier4) {
        this.mLeftMargin = intSupplier;
        this.mTopMargin = intSupplier2;
        this.mRightMargin = intSupplier3;
        this.mBottomMargin = intSupplier4;
        return this;
    }

    public PopupTipBuilder setViewIdListDismissWhenClick(int[] iArr) {
        this.mViewIdListDismissWhenClick = iArr;
        return this;
    }

    public PopupTipBuilder show() {
        Log.d(this.TAG, "show popup tip");
        ViewUtils.addView(this.mParentView, this.mRootView);
        ViewUtils.setMatchParentView(this.mRootView);
        return this;
    }

    public void updateMargin() {
        IntSupplier intSupplier = this.mLeftMargin;
        if (intSupplier != null) {
            ViewMarginUtils.setLeftMargin(this.mMainView, intSupplier.getAsInt());
        }
        IntSupplier intSupplier2 = this.mTopMargin;
        if (intSupplier2 != null) {
            ViewMarginUtils.setTopMargin(this.mMainView, intSupplier2.getAsInt());
        }
        IntSupplier intSupplier3 = this.mRightMargin;
        if (intSupplier3 != null) {
            ViewMarginUtils.setRightMargin(this.mMainView, intSupplier3.getAsInt());
        }
        IntSupplier intSupplier4 = this.mBottomMargin;
        if (intSupplier4 != null) {
            ViewMarginUtils.setBottomMargin(this.mMainView, intSupplier4.getAsInt());
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDismissListener {
        void onDismiss();

        void onHide() {
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$checkIgnoreRootViewTouch$2(View view) {
    }
}
