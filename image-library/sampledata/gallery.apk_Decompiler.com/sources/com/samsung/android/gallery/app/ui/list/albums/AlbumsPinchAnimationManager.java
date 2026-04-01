package com.samsung.android.gallery.app.ui.list.albums;

import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseBlurPinchAnimationManager;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsPinchAnimationManager extends AlbumsBaseBlurPinchAnimationManager {
    private ListViewHolder mFooterRefHolder;
    private final boolean mHasFooter;

    public AlbumsPinchAnimationManager(PinchLayoutManager pinchLayoutManager) {
        super(pinchLayoutManager);
        this.mHasFooter = pinchLayoutManager.hasFooter();
    }

    private ListViewHolder createFooterViewHolder() {
        if (!hasFooter()) {
            return null;
        }
        PinchLayoutManager pinchLayoutManager = this.mLayoutManager;
        ListViewHolder listViewHolder = (ListViewHolder) getChildViewHolder(pinchLayoutManager.getChildAt(pinchLayoutManager.getChildCount() - 1));
        if (listViewHolder != null && ViewHolderValue.isFooter(listViewHolder.getItemViewType())) {
            return listViewHolder;
        }
        ListViewHolder createFakeViewHolderInternal = createFakeViewHolderInternal(this.mFakeViewParent, -4);
        PinchLayoutManager pinchLayoutManager2 = this.mLayoutManager;
        pinchLayoutManager2.bindHolder(createFakeViewHolderInternal, pinchLayoutManager2.getItemCount() - 1);
        return createFakeViewHolderInternal;
    }

    public void clearAnimationInfo() {
        super.clearAnimationInfo();
        ListViewHolder listViewHolder = this.mFooterRefHolder;
        if (listViewHolder != null && listViewHolder.getRootView().getParent() == this.mFakeViewParent) {
            ViewUtils.setVisibility(this.mFooterRefHolder.getRootView(), 4);
        }
    }

    public void createReferenceView() {
        super.createReferenceView();
        if (hasFooter()) {
            this.mFooterRefHolder = createFooterViewHolder();
        }
    }

    public ListViewHolder getRefViewHolder(int i2, boolean z) {
        if (!hasFooter() || !ViewHolderValue.isFooter(this.mLayoutManager.getHintItemViewType(i2, this.mGridInfo.from()))) {
            return super.getRefViewHolder(i2, z);
        }
        return this.mFooterRefHolder;
    }

    public float getXPosition(int i2, float f, int i7, int i8, int i10, int i11) {
        if (!hasFooter() || !ViewHolderValue.isFooter(i11)) {
            return super.getXPosition(i2, f, i7, i8, i10, i11);
        }
        return (float) this.mLayoutManager.getExtraStartPadding(i10);
    }

    public boolean hasFooter() {
        return this.mHasFooter;
    }

    public void onAnimationCompleted(boolean z, boolean z3) {
        ListViewHolder listViewHolder = this.mFooterRefHolder;
        if (listViewHolder != null) {
            listViewHolder.recycle();
            ViewUtils.removeSelf(this.mFooterRefHolder.getRootView());
            ViewUtils.setVisibility(this.mFooterRefHolder.getRootView(), 0);
            putRecycledViewPool(this.mFooterRefHolder);
            this.mFooterRefHolder = null;
        }
        super.onAnimationCompleted(z, z3);
    }
}
