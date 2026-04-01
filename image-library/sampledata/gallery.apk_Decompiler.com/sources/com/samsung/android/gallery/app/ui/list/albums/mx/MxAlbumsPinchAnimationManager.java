package com.samsung.android.gallery.app.ui.list.albums.mx;

import Ad.j;
import I4.b;
import I4.c;
import I4.d;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsPinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.animator.WidthAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.PinchItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.pinch.IPinchAdapter;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumsPinchAnimationManager extends AlbumsPinchAnimationManager {
    private ListViewHolder mEmptyReferenceViewHolder;
    private ListViewHolder mHeaderRefHolder;

    public MxAlbumsPinchAnimationManager(PinchLayoutManager pinchLayoutManager) {
        super(pinchLayoutManager);
    }

    private ListViewHolder createHeaderViewHolder() {
        if (!this.mLayoutManager.hasHeader()) {
            return null;
        }
        ListViewHolder listViewHolder = (ListViewHolder) getChildViewHolder(this.mLayoutManager.getChildAt(0));
        if (listViewHolder != null && ViewHolderValue.isHeader(listViewHolder.getItemViewType())) {
            return listViewHolder;
        }
        ListViewHolder createFakeViewHolderInternal = createFakeViewHolderInternal(this.mFakeViewParent, -3);
        this.mLayoutManager.bindHolder(createFakeViewHolderInternal, 0);
        return createFakeViewHolderInternal;
    }

    private int getCheckBoxWidth(ListViewHolder listViewHolder) {
        CheckBox checkbox = listViewHolder.getCheckbox();
        if (checkbox == null) {
            return 0;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) checkbox.getLayoutParams();
        return marginLayoutParams.getMarginEnd() + marginLayoutParams.getMarginStart() + checkbox.getWidth();
    }

    private boolean isEmptyView(int i2) {
        if (-5 == i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Integer lambda$prepareDividerViewHolder$0(IPinchAdapter iPinchAdapter) {
        return Integer.valueOf(iPinchAdapter.getViewHolderMargin(this.mGridInfo.from()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Integer lambda$prepareDividerViewHolder$1(IPinchAdapter iPinchAdapter) {
        return Integer.valueOf(iPinchAdapter.getViewHolderMargin(this.mGridInfo.to()));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$prepareDividerViewHolder$3(View view, int i2) {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$prepareDividerViewHolder$4(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = -1;
        view.setLayoutParams(layoutParams);
    }

    private void removeHeaderRefView() {
        ListViewHolder listViewHolder = this.mHeaderRefHolder;
        if (listViewHolder != null) {
            listViewHolder.recycle();
            if (this.mHeaderRefHolder.getRootView().getParent() == this.mFakeViewParent) {
                Optional.ofNullable(getRecyclerView().getAdapter()).ifPresent(new b(2));
            }
            ViewUtils.removeSelf(this.mHeaderRefHolder.getRootView());
            ViewUtils.setVisibility(this.mHeaderRefHolder.getRootView(), 0);
            this.mHeaderRefHolder = null;
        }
    }

    public void addTranslationAnimator(View view, View view2, ListViewHolder listViewHolder, ListViewHolder listViewHolder2, PinchItem pinchItem) {
        if (view != null && view.getVisibility() != 8 && view2 != null) {
            RectF rect = getRect(view);
            float[] childToXY = getChildToXY(view, listViewHolder2.getRootView(), getBaseToXY(view, view2), pinchItem.getToRect());
            MediaItem mediaItem = listViewHolder.getMediaItem();
            if (mediaItem != null && mediaItem.isSharing() && ViewUtils.isVisible(listViewHolder2.getCheckbox())) {
                AlbumsBasePinchAnimationManager.TransitionType transitionType = this.mTransitionType;
                if (transitionType == AlbumsBasePinchAnimationManager.TransitionType.GRID_TO_LIST) {
                    int checkBoxWidth = getCheckBoxWidth(listViewHolder2);
                    float f = childToXY[0];
                    if (!this.mIsRtl) {
                        checkBoxWidth = -checkBoxWidth;
                    }
                    childToXY[0] = f + ((float) checkBoxWidth);
                } else if (transitionType == AlbumsBasePinchAnimationManager.TransitionType.LIST_TO_GRID && ViewUtils.isVisible(listViewHolder.getCheckbox())) {
                    int checkBoxWidth2 = getCheckBoxWidth(listViewHolder);
                    float f5 = childToXY[0];
                    if (this.mIsRtl) {
                        checkBoxWidth2 = -checkBoxWidth2;
                    }
                    childToXY[0] = f5 + ((float) checkBoxWidth2);
                }
            }
            float f8 = childToXY[0];
            float f10 = childToXY[1];
            TranslationAnimator translationAnimator = new TranslationAnimator(view, rect, new RectF(f8, f10, f8, f10));
            translationAnimator.setAnimationListener(new c(this, 0));
            addAnimation((PropertyAnimator) translationAnimator);
        }
    }

    public void clearAnimationInfo() {
        super.clearAnimationInfo();
        ListViewHolder listViewHolder = this.mHeaderRefHolder;
        if (listViewHolder != null && listViewHolder.getRootView().getParent() == this.mFakeViewParent) {
            ViewUtils.setVisibility(this.mHeaderRefHolder.getRootView(), 4);
        }
        recyclerViewHolder(this.mEmptyReferenceViewHolder);
        this.mEmptyReferenceViewHolder = null;
    }

    public void createReferenceView() {
        this.mHeaderRefHolder = createHeaderViewHolder();
        this.mEmptyReferenceViewHolder = createFakeViewHolderInternal(this.mFakeViewParent, -5);
        super.createReferenceView();
    }

    public int getLastVisibleItemPosition() {
        int lastVisibleItemPosition = super.getLastVisibleItemPosition();
        do {
            lastVisibleItemPosition++;
            if (lastVisibleItemPosition >= this.mLayoutManager.getItemCount()) {
                return this.mLayoutManager.getItemCount() - 1;
            }
        } while (ViewUtils.isAttachedToWindow(this.mLayoutManager.findViewByPosition(lastVisibleItemPosition)));
        return lastVisibleItemPosition - 1;
    }

    public ListViewHolder getRefViewHolder(int i2, boolean z) {
        int hintItemViewType = this.mLayoutManager.getHintItemViewType(i2, this.mGridInfo.from());
        if (ViewHolderValue.isHeader(hintItemViewType)) {
            return this.mHeaderRefHolder;
        }
        if (isEmptyView(hintItemViewType)) {
            return this.mEmptyReferenceViewHolder;
        }
        return super.getRefViewHolder(i2, z);
    }

    public float getXPosition(int i2, float f, int i7, int i8, int i10, int i11) {
        int i12;
        if (!ViewHolderValue.isHeader(i11)) {
            return super.getXPosition(i2, f, i7, i8, i10, i11);
        }
        if (this.mIsRtl) {
            i12 = 0;
        } else {
            i12 = this.mLayoutManager.getExtraStartPadding(i10);
        }
        return (float) i12;
    }

    public void onAnimationCompleted(boolean z, boolean z3) {
        removeHeaderRefView();
        super.onAnimationCompleted(z, z3);
    }

    public void onLayout() {
        if (this.mFinishingAnimation) {
            setItemViewMargin();
        }
        super.onLayout();
    }

    public void prepareCheckBoxAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        ViewUtils.setVisibleOrGone(listViewHolder.getCheckbox(), listViewHolder.isCheckBoxEnabled());
        super.prepareCheckBoxAnimation(listViewHolder, pinchItem);
    }

    public void prepareDividerViewHolder(PinchItem pinchItem, ListViewHolder listViewHolder) {
        int hintWidthSpace = this.mLayoutManager.getHintWidthSpace(this.mGridInfo.from());
        int hintWidthSpace2 = this.mLayoutManager.getHintWidthSpace(this.mGridInfo.to());
        View dividerView = listViewHolder.getDividerView();
        if (dividerView != null && hintWidthSpace != hintWidthSpace2) {
            int intValue = ((Integer) Optional.ofNullable(getAdapter()).map(new d(this, 0)).orElse(0)).intValue();
            int intValue2 = ((Integer) Optional.ofNullable(getAdapter()).map(new d(this, 1)).orElse(0)).intValue();
            addAnimation(new TranslationAnimator(dividerView, new RectF(dividerView.getX(), dividerView.getY(), 0.0f, 0.0f), new RectF((((float) (hintWidthSpace2 - hintWidthSpace)) / 2.0f) + dividerView.getX(), dividerView.getY(), 0.0f, 0.0f)).setAnimationListener(new c(this, 2)));
            addAnimation(new WidthAnimator(dividerView, hintWidthSpace - intValue, hintWidthSpace2 - intValue2, new j(24)).setAnimationListener(new j(25)));
        }
    }

    public void prepareOtherViewHolder(PinchItem pinchItem, ListViewHolder listViewHolder) {
        int hintWidthSpace = this.mLayoutManager.getHintWidthSpace(this.mGridInfo.from());
        int hintWidthSpace2 = this.mLayoutManager.getHintWidthSpace(this.mGridInfo.to());
        View dividerView = listViewHolder.getDividerView();
        if (dividerView != null && hintWidthSpace != hintWidthSpace2) {
            addAnimation(new TranslationAnimator(dividerView, new RectF(dividerView.getX(), dividerView.getY(), 0.0f, 0.0f), new RectF((((float) (hintWidthSpace2 - hintWidthSpace)) / 2.0f) + dividerView.getX(), dividerView.getY(), 0.0f, 0.0f)).setAnimationListener(new c(this, 1)));
        }
    }

    public void setItemViewMargin() {
        super.setItemViewMargin();
        Optional.ofNullable(getAdapter()).ifPresent(new b(1));
    }
}
