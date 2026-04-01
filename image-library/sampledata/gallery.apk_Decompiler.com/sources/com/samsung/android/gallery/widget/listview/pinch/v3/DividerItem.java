package com.samsung.android.gallery.widget.listview.pinch.v3;

import Ad.j;
import Fa.C0571z;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.HeightAnimator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DividerItem extends PinchItem {
    private boolean mIsCrossOver;

    private PropertyAnimator getDividerAlphaAnimator(GridInfo gridInfo) {
        if (this.mIsCrossOver) {
            return new AlphaAnimator(this.mListViewHolder.getRootView(), 1.0f, 0.0f).setDurationRelative(0.15f).playback(true);
        }
        if (hasValidRect(gridInfo)) {
            resetAlpha(this.mListViewHolder.getRootView());
            return null;
        } else if (getRect(gridInfo.from()) == null) {
            return new AlphaAnimator(this.mListViewHolder.getRootView(), 0.0f, 1.0f).setStartRelative(0.85f);
        } else {
            return new AlphaAnimator(this.mListViewHolder.getRootView(), 1.0f, 0.0f).setDurationRelative(0.15f);
        }
    }

    private PropertyAnimator getHeightAnimator(GridInfo gridInfo) {
        float height;
        float height2;
        View dividerView = this.mListViewHolder.getDividerView();
        if (dividerView == null) {
            return null;
        }
        RectF rect = getRect(gridInfo.from());
        RectF rect2 = getRect(gridInfo.to());
        if (rect == null && rect2 == null) {
            return null;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) dividerView.getLayoutParams();
        int i2 = marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
        if (rect == null) {
            height = rect2.height();
        } else {
            height = rect.height();
        }
        int i7 = ((int) height) - i2;
        if (rect2 == null) {
            height2 = rect.height();
        } else {
            height2 = rect2.height();
        }
        return new HeightAnimator(dividerView, i7, ((int) height2) - i2).setAnimationListener(new j(28));
    }

    private void handleWrongItem(Runnable runnable) {
        Log.w("DividerItem", "Doesn't have ListViewHolder");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getHeightAnimator$0(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = -1;
        view.setLayoutParams(layoutParams);
    }

    public ArrayList<PropertyAnimator> getAnimators(GridInfo gridInfo, PinchLayoutManager pinchLayoutManager, int i2, boolean z, Runnable runnable) {
        float f;
        MediaItem mediaItem;
        ArrayList<PropertyAnimator> arrayList = new ArrayList<>();
        if (this.mListViewHolder == null) {
            handleWrongItem(runnable);
            return arrayList;
        }
        TranslationAnimator translateAnimator = getTranslateAnimator(gridInfo, i2);
        if (this.mIsCrossOver) {
            f = 0.15f;
        } else {
            f = 0.0f;
        }
        arrayList.add(translateAnimator.offset(f));
        arrayList.add(getHeightAnimator(gridInfo));
        arrayList.add(getDividerAlphaAnimator(gridInfo));
        if (this.mListViewHolder.getMediaItem() == null && (mediaItem = getMediaItem(gridInfo.to(), pinchLayoutManager)) != null) {
            this.mListViewHolder.bind(mediaItem);
        }
        arrayList.removeIf(new C0571z(21));
        return arrayList;
    }

    public boolean isData() {
        return false;
    }

    public boolean isDivider() {
        return true;
    }

    public void onAnimationCompleted() {
        if (this.mListViewHolder != null) {
            super.onAnimationCompleted();
            resetAlpha(this.mListViewHolder.getRootView());
        }
    }

    public void setCrossOver(boolean z) {
        this.mIsCrossOver = z;
    }
}
