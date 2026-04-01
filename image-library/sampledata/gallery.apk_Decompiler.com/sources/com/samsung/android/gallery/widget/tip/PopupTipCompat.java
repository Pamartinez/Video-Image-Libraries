package com.samsung.android.gallery.widget.tip;

import android.view.ViewGroup;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.tip.PopupTipBuilder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.function.IntSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PopupTipCompat extends PopupTipBuilder {
    public PopupTipCompat(ViewGroup viewGroup, int i2, int i7) {
        super(viewGroup, i2, i7);
    }

    public PopupTipCompat build() {
        super.build();
        return this;
    }

    public void dismiss() {
        super.dismiss();
    }

    public void refresh() {
        Log.d(this.TAG, "refresh popup tip");
        ViewUtils.removeSelf(this.mRootView);
        build();
        show();
    }

    public PopupTipCompat setDismissAnimationEnable(boolean z) {
        super.setDismissAnimationEnable(z);
        return this;
    }

    public PopupTipCompat setDismissListener(PopupTipBuilder.OnDismissListener onDismissListener) {
        super.setDismissListener(onDismissListener);
        return this;
    }

    public PopupTipCompat setGravity(IntSupplier intSupplier) {
        super.setGravity(intSupplier);
        return this;
    }

    public PopupTipCompat setIgnoreRootViewTouch(boolean z) {
        super.setIgnoreRootViewTouch(z);
        return this;
    }

    public PopupTipCompat setMargin(IntSupplier intSupplier, IntSupplier intSupplier2, IntSupplier intSupplier3, IntSupplier intSupplier4) {
        super.setMargin(intSupplier, intSupplier2, intSupplier3, intSupplier4);
        return this;
    }

    public PopupTipCompat setViewIdListDismissWhenClick(int[] iArr) {
        super.setViewIdListDismissWhenClick(iArr);
        return this;
    }

    public PopupTipCompat show() {
        super.show();
        return this;
    }
}
