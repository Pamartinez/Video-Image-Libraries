package com.samsung.android.gallery.widget.photoview;

import Nb.d;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.RelativeLayout;
import c0.C0086a;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.remaster.AbstractRemasterLayout;
import com.samsung.android.gallery.widget.remaster.RemasterLayoutFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterPhotoPreView extends PhotoPreView {
    private final int mMinSize;

    public RemasterPhotoPreView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMinSize = context.getResources().getDimensionPixelSize(R$dimen.remaster_viewer_min_size);
    }

    private int getMinHeight() {
        if (isWindowMode()) {
            return Math.min(getMaxViewSize().getHeight(), this.mMinSize);
        }
        return this.mMinSize;
    }

    private int getMinWidth() {
        return this.mMinSize;
    }

    private int getRemasterPhotoViewHeight(int i2, int i7, boolean z) {
        boolean z3;
        AbstractRemasterLayout create = new RemasterLayoutFactory().create((Activity) getContext(), (ViewGroup) getRootView());
        create.updateAttrs();
        if (!z || isWindowMode()) {
            z3 = false;
        } else {
            z3 = true;
        }
        return create.getPhotoViewHeight(i2, i7, z3);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$applyCenterCrop$0(int i2, float f) {
        if (!isWindowMode() || getMinHeight() != super.getViewHeight(i2, f)) {
            return false;
        }
        return true;
    }

    private void setLayoutParams(RelativeLayout.LayoutParams layoutParams) {
        int i2;
        int i7;
        WindowInsets rootWindowInsets = getRootWindowInsets();
        if (rootWindowInsets == null) {
            Log.e(this.TAG, "failed to set layout params");
            return;
        }
        layoutParams.removeRule(15);
        layoutParams.addRule(12);
        layoutParams.addRule(20);
        int D = C0086a.D(getMaxViewSize().getHeight(), layoutParams.height, 2, this.mMargin.bottomMargin);
        if (Features.isEnabled(Features.IS_RTL)) {
            int width = getParentViewSize().getWidth() - layoutParams.width;
            if (isWindowMode()) {
                i7 = 0;
            } else {
                i7 = rootWindowInsets.getStableInsetRight() + (-rootWindowInsets.getStableInsetLeft());
            }
            layoutParams.setMargins(0, 0, (width + i7) / 2, D);
            return;
        }
        int width2 = getParentViewSize().getWidth() - layoutParams.width;
        if (isWindowMode()) {
            i2 = 0;
        } else {
            i2 = rootWindowInsets.getStableInsetLeft() - rootWindowInsets.getStableInsetRight();
        }
        layoutParams.setMargins((width2 + i2) / 2, 0, 0, D);
    }

    public void applyCenterCrop(int i2, int i7, float f) {
        setCenterCrop(new d(this, i7, f));
    }

    public Size getMaxViewSize() {
        int i2;
        View findViewById = getRootView().findViewById(R$id.content);
        if (findViewById == null) {
            return new Size(getParentViewSize().getWidth(), getParentViewSize().getHeight());
        }
        if (isWindowMode() || !ResourceCompat.isLandscape(getContext())) {
            i2 = findViewById.getWidth();
        } else {
            i2 = DeviceInfo.getDisplayWidth(findViewById.getContext());
        }
        return new Size(getParentViewSize().getWidth(), getRemasterPhotoViewHeight(i2, getParentViewSize().getHeight(), ResourceCompat.isLandscape(getContext())));
    }

    public float getScale(int i2, int i7) {
        return getDisplayMinScale(i2, i7);
    }

    public int getViewHeight(int i2, float f) {
        return Math.max(getMinHeight(), super.getViewHeight(i2, f));
    }

    public int getViewWidth(int i2, float f) {
        return Math.max(getMinWidth(), super.getViewWidth(i2, f));
    }

    public void setLayoutRuleAndMargins(RelativeLayout.LayoutParams layoutParams) {
        setLayoutParams(layoutParams);
    }
}
