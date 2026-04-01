package com.samsung.android.gallery.widget.listview.pinch.v3;

import android.graphics.RectF;
import android.util.SparseArray;
import android.view.View;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PinchItem {
    private boolean mIsFakeView = true;
    protected ListViewHolder mListViewHolder;
    protected final SparseArray<PinchInfo> mPinchInfoOnGrid = new SparseArray<>();
    private Integer mViewType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PinchInfo {
        /* access modifiers changed from: private */
        public Integer dataPosition;
        /* access modifiers changed from: private */
        public int gridSize;
        /* access modifiers changed from: private */
        public boolean isShowing = false;
        /* access modifiers changed from: private */
        public RectF rectF;
        /* access modifiers changed from: private */
        public Integer viewPosition;

        public String toString() {
            return "PinchInfo {G=" + this.gridSize + ",V=" + this.viewPosition + ",D=" + this.dataPosition + ", B=" + this.isShowing + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.rectF;
        }
    }

    private void resetTranslate(View view) {
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }

    public ArrayList<PropertyAnimator> getAnimators(PinchLayoutManager pinchLayoutManager, GridInfo gridInfo, float f, int i2, float f5, boolean z) {
        return new ArrayList<>();
    }

    public abstract ArrayList<PropertyAnimator> getAnimators(GridInfo gridInfo, PinchLayoutManager pinchLayoutManager, int i2, boolean z, Runnable runnable);

    public RectF getAnyValidRectF() {
        for (int i2 = 0; i2 < this.mPinchInfoOnGrid.size(); i2++) {
            RectF b = this.mPinchInfoOnGrid.valueAt(i2).rectF;
            if (b != null) {
                return b;
            }
        }
        return null;
    }

    public Integer getBaseGrid() {
        return null;
    }

    public int getHeightDelta(PinchLayoutManager pinchLayoutManager, GridInfo gridInfo) {
        return 0;
    }

    public MediaItem getMediaItem(int i2, PinchLayoutManager pinchLayoutManager) {
        int i7;
        if (isShowing(i2)) {
            i7 = getViewPosition(i2);
        } else {
            i7 = -1;
        }
        if (i7 < 0) {
            return null;
        }
        return pinchLayoutManager.getMediaItem(i7, i2);
    }

    public PinchInfo getPinchInfo(int i2) {
        PinchInfo pinchInfo = this.mPinchInfoOnGrid.get(i2);
        if (pinchInfo != null) {
            return pinchInfo;
        }
        PinchInfo pinchInfo2 = new PinchInfo();
        pinchInfo2.gridSize = i2;
        this.mPinchInfoOnGrid.put(i2, pinchInfo2);
        return pinchInfo2;
    }

    public RectF getRect(int i2) {
        PinchInfo pinchInfo = getPinchInfo(i2);
        if (RectUtils.isValidRect(pinchInfo.rectF)) {
            return pinchInfo.rectF;
        }
        return null;
    }

    public View getScalableView() {
        if (this.mListViewHolder.getScalableView() != null) {
            return this.mListViewHolder.getScalableView();
        }
        return this.mListViewHolder.getRootView();
    }

    public TranslationAnimator getTranslateAnimator(GridInfo gridInfo, float f, int i2, boolean z) {
        RectF rect = getRect(z ? gridInfo.to() : gridInfo.from());
        if (rect == null) {
            return null;
        }
        RectF rectF = new RectF(rect);
        if (z) {
            rect.offset(-f, 0.0f);
            rectF.offset(0.0f, (float) i2);
        } else {
            rectF.offset(f, (float) i2);
        }
        return new TranslationAnimator(this.mListViewHolder.getRootView(), rect, rectF);
    }

    public ListViewHolder getViewHolder() {
        return this.mListViewHolder;
    }

    public int getViewPosition(int i2) {
        PinchInfo pinchInfo = getPinchInfo(i2);
        if (pinchInfo.viewPosition != null) {
            return pinchInfo.viewPosition.intValue();
        }
        return -1;
    }

    public Integer getViewType() {
        return this.mViewType;
    }

    public boolean hasValidRect(GridInfo gridInfo) {
        if (getRect(gridInfo.from()) == null || getRect(gridInfo.to()) == null) {
            return false;
        }
        return true;
    }

    public abstract boolean isData();

    public boolean isDivider() {
        return false;
    }

    public boolean isFakeView() {
        return this.mIsFakeView;
    }

    public boolean isHeader() {
        return false;
    }

    public boolean isRelated(GridInfo gridInfo) {
        if (isShowing(gridInfo.from()) || isShowing(gridInfo.to())) {
            return true;
        }
        return false;
    }

    public boolean isSameGroup(int i2, int i7, SparseArray<Integer> sparseArray) {
        Integer num = sparseArray.get(i7);
        if (num == null) {
            return false;
        }
        for (int i8 = 0; i8 < this.mPinchInfoOnGrid.size(); i8++) {
            if (num.equals(sparseArray.get(this.mPinchInfoOnGrid.keyAt(i8))) && getViewPosition(this.mPinchInfoOnGrid.keyAt(i8)) == i2) {
                return true;
            }
        }
        return false;
    }

    public boolean isShowing(int i2) {
        return getPinchInfo(i2).isShowing;
    }

    public void onAnimationCompleted() {
        ListViewHolder listViewHolder = this.mListViewHolder;
        if (listViewHolder != null) {
            resetTranslate(listViewHolder.getRootView());
            ViewUtils.setVisibleOrGone(this.mListViewHolder.getDecoView(11), true);
        }
    }

    public void onAnimationStarted() {
        ListViewHolder listViewHolder = this.mListViewHolder;
        if (listViewHolder != null) {
            ViewUtils.setVisibleOrGone(listViewHolder.getDecoView(11), false);
        }
    }

    public void resetAlpha(View view) {
        view.setAlpha(1.0f);
    }

    public void resetScale(View view) {
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setPivotX(((float) view.getWidth()) / 2.0f);
        view.setPivotY(((float) view.getHeight()) / 2.0f);
    }

    public void setDataPosition(int i2, int i7) {
        getPinchInfo(i2).dataPosition = Integer.valueOf(i7);
    }

    public void setFakeView(boolean z) {
        this.mIsFakeView = z;
    }

    public void setRect(int i2, RectF rectF) {
        getPinchInfo(i2).rectF = rectF;
    }

    public void setShowing(int i2, boolean z) {
        getPinchInfo(i2).isShowing = z;
    }

    public void setViewHolder(ListViewHolder listViewHolder) {
        this.mListViewHolder = listViewHolder;
    }

    public void setViewPosition(int i2, int i7) {
        getPinchInfo(i2).viewPosition = Integer.valueOf(i7);
    }

    public void setViewType(int i2) {
        this.mViewType = Integer.valueOf(i2);
    }

    public void setVisibility(boolean z) {
        ListViewHolder listViewHolder = this.mListViewHolder;
        if (listViewHolder != null) {
            ViewUtils.setVisibleOrGone(listViewHolder.getRootView(), z);
        }
    }

    public TranslationAnimator getTranslateAnimator(GridInfo gridInfo, int i2) {
        RectF rect = getRect(gridInfo.from());
        RectF rect2 = getRect(gridInfo.to());
        if (rect == null && rect2 == null) {
            return null;
        }
        if (rect == null) {
            rect = rect2;
        }
        if (rect2 == null) {
            rect2 = rect;
        }
        rect2.offset(0.0f, (float) i2);
        if (this.mListViewHolder != null) {
            return new TranslationAnimator(this.mListViewHolder.getRootView(), rect, rect2);
        }
        return null;
    }

    public void setCrossOver(boolean z) {
    }
}
