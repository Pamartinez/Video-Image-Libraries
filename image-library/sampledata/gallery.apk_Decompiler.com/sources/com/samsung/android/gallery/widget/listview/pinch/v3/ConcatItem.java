package com.samsung.android.gallery.widget.listview.pinch.v3;

import Fa.C0571z;
import android.view.View;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConcatItem extends PinchItem {
    private AlphaAnimator getAlphaAnimator(boolean z) {
        float f;
        View scalableView = getScalableView();
        float f5 = 1.0f;
        if (z) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        if (!z) {
            f5 = 0.0f;
        }
        return new AlphaAnimator(scalableView, f, f5);
    }

    private ScaleAnimator getScaleAnimator(float f, boolean z) {
        float f5;
        if (z) {
            f5 = 1.0f / f;
        } else {
            f5 = 1.0f;
        }
        if (z) {
            f = 1.0f;
        }
        return new ScaleAnimator(getScalableView(), f5, f);
    }

    public ArrayList<PropertyAnimator> getAnimators(GridInfo gridInfo, PinchLayoutManager pinchLayoutManager, int i2, boolean z, Runnable runnable) {
        ArrayList<PropertyAnimator> arrayList = new ArrayList<>();
        arrayList.add(getTranslateAnimator(gridInfo, i2));
        arrayList.removeIf(new C0571z(21));
        return arrayList;
    }

    public boolean isData() {
        return true;
    }

    public void onAnimationCompleted() {
        super.onAnimationCompleted();
        if (this.mListViewHolder != null) {
            resetScale(getScalableView());
            resetAlpha(getScalableView());
        }
    }

    public ArrayList<PropertyAnimator> getAnimators(PinchLayoutManager pinchLayoutManager, GridInfo gridInfo, float f, int i2, float f5, boolean z) {
        ArrayList<PropertyAnimator> arrayList = new ArrayList<>();
        arrayList.add(getTranslateAnimator(gridInfo, f, i2, z));
        arrayList.add(getScaleAnimator(f5, z));
        arrayList.add(getAlphaAnimator(z));
        arrayList.removeIf(new C0571z(21));
        return arrayList;
    }
}
