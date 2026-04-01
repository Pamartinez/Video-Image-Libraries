package com.samsung.android.gallery.widget.videoview.controller;

import A4.C0369d;
import B2.i;
import Fb.h;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoSpeedControlHandler;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.tip.PopupTipBuilder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import e6.C0453a;
import java.util.Optional;
import java.util.function.IntSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoSpeedControllerCompat extends PopupTipBuilder {
    private ChangeSpeedListener mChangeSpeedListener;
    private ViewGroup mFastLayout;
    private ViewGroup mNormalLayout;
    private ViewGroup mSlowLayout;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ChangeSpeedListener {
    }

    public VideoSpeedControllerCompat(ViewGroup viewGroup, int i2, int i7) {
        super(viewGroup, i2, i7);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateSelected$0(int i2, ViewGroup viewGroup) {
        boolean z;
        if (i2 == 0) {
            z = true;
        } else {
            z = false;
        }
        viewGroup.setSelected(z);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateSelected$1(int i2, ViewGroup viewGroup) {
        boolean z = true;
        if (i2 != 1) {
            z = false;
        }
        viewGroup.setSelected(z);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateSelected$2(int i2, ViewGroup viewGroup) {
        boolean z;
        if (i2 == 2) {
            z = true;
        } else {
            z = false;
        }
        viewGroup.setSelected(z);
    }

    /* access modifiers changed from: private */
    public boolean onTouchedItem(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            int i2 = 0;
            if (action != 1 && action != 3) {
                return false;
            }
            if (view != this.mSlowLayout) {
                if (view == this.mFastLayout) {
                    i2 = 2;
                } else {
                    i2 = 1;
                }
            }
            updateSelected(i2);
            ChangeSpeedListener changeSpeedListener = this.mChangeSpeedListener;
            if (changeSpeedListener != null) {
                ((VideoSpeedControlHandler) ((h) changeSpeedListener).e).lambda$new$3(i2);
            }
        }
        return true;
    }

    public VideoSpeedControllerCompat attach() {
        super.show();
        return this;
    }

    public VideoSpeedControllerCompat build() {
        super.build();
        return this;
    }

    public void dismiss() {
        super.dismiss();
    }

    public boolean isShowing() {
        return ViewUtils.isVisible(this.mRootView);
    }

    public void refresh() {
        Log.d(this.TAG, "refresh speed controller");
        ViewUtils.removeSelf(this.mRootView);
        build();
        attach();
    }

    public void resetControllerView() {
        Optional.ofNullable(this.mSlowLayout).ifPresent(new C0453a(21));
        Optional.ofNullable(this.mNormalLayout).ifPresent(new C0453a(22));
        Optional.ofNullable(this.mFastLayout).ifPresent(new C0453a(23));
    }

    public VideoSpeedControllerCompat setControlActionListener(ChangeSpeedListener changeSpeedListener) {
        this.mChangeSpeedListener = changeSpeedListener;
        return this;
    }

    public VideoSpeedControllerCompat setDismissListener(PopupTipBuilder.OnDismissListener onDismissListener) {
        super.setDismissListener(onDismissListener);
        return this;
    }

    public VideoSpeedControllerCompat setHideRootViewOnTouch(boolean z) {
        super.setHideRootViewOnTouch(z);
        return this;
    }

    public VideoSpeedControllerCompat setIgnoreRootViewTouch(boolean z) {
        super.setIgnoreRootViewTouch(z);
        return this;
    }

    public VideoSpeedControllerCompat setMargin(IntSupplier intSupplier, IntSupplier intSupplier2, IntSupplier intSupplier3, IntSupplier intSupplier4) {
        super.setMargin(intSupplier, intSupplier2, intSupplier3, intSupplier4);
        return this;
    }

    public void setSpeedControllerBinding() {
        View view = getView();
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.findViewById(R$id.slow_layout);
            this.mSlowLayout = viewGroup;
            viewGroup.setOnTouchListener(new i(20, this));
            ViewGroup viewGroup2 = (ViewGroup) view.findViewById(R$id.fast_layout);
            this.mFastLayout = viewGroup2;
            viewGroup2.setOnTouchListener(new i(20, this));
            ViewGroup viewGroup3 = (ViewGroup) view.findViewById(R$id.normal_layout);
            this.mNormalLayout = viewGroup3;
            viewGroup3.setOnTouchListener(new i(20, this));
            this.mNormalLayout.setSelected(true);
        }
    }

    public VideoSpeedControllerCompat setViewIdListDismissWhenClick(int[] iArr) {
        super.setViewIdListDismissWhenClick(iArr);
        return this;
    }

    public void show(boolean z) {
        if (z) {
            updateMargin();
            SimpleAnimator.setAlphaVisible(this.mRootView, 200);
            return;
        }
        SimpleAnimator.setAlphaOutVisible(this.mRootView, 200);
    }

    public void updateSelected(int i2) {
        Optional.ofNullable(this.mSlowLayout).ifPresent(new C0369d(i2, 21));
        Optional.ofNullable(this.mNormalLayout).ifPresent(new C0369d(i2, 22));
        Optional.ofNullable(this.mFastLayout).ifPresent(new C0369d(i2, 23));
    }
}
