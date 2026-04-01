package com.samsung.android.gallery.app.ui.list.picker.albums;

import android.content.res.Resources;
import android.widget.CheckBox;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsPinchAnimationManager;
import com.samsung.android.gallery.widget.animator.TintAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumFolderPickerPinchAnimationManager extends AlbumsPinchAnimationManager {
    private ListViewHolder mHeaderRefHolder;
    private final IAlbumFolderPicker mView;

    public AlbumFolderPickerPinchAnimationManager(PinchLayoutManager pinchLayoutManager, IAlbumFolderPicker iAlbumFolderPicker) {
        super(pinchLayoutManager);
        this.mView = iAlbumFolderPicker;
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

    private void removeHeaderRefView() {
        ListViewHolder listViewHolder = this.mHeaderRefHolder;
        if (listViewHolder != null) {
            listViewHolder.recycle();
            ViewUtils.removeSelf(this.mHeaderRefHolder.getRootView());
            ViewUtils.setVisibility(this.mHeaderRefHolder.getRootView(), 0);
            this.mHeaderRefHolder = null;
        }
    }

    public void clearAnimationInfo() {
        super.clearAnimationInfo();
        ListViewHolder listViewHolder = this.mHeaderRefHolder;
        if (listViewHolder != null && listViewHolder.getRootView().getParent() == this.mFakeViewParent) {
            ViewUtils.setVisibility(this.mHeaderRefHolder.getRootView(), 4);
        }
    }

    public TintAnimator createCheckboxTintAnimator(CheckBox checkBox, boolean z) {
        int[] iArr;
        if (!this.mView.isFlipWidgetWithCoverScreen()) {
            return super.createCheckboxTintAnimator(checkBox, z);
        }
        int color = checkBox.getResources().getColor(R.color.white_color, (Resources.Theme) null);
        int[] iArr2 = {color, color};
        int[] iArr3 = {color, color};
        if (z) {
            iArr = iArr2;
        } else {
            iArr = iArr3;
        }
        if (z) {
            iArr2 = iArr3;
        }
        return new TintAnimator(checkBox, iArr, iArr2);
    }

    public void createReferenceView() {
        this.mHeaderRefHolder = createHeaderViewHolder();
        super.createReferenceView();
    }

    public ListViewHolder getRefViewHolder(int i2, boolean z) {
        if (ViewHolderValue.isHeader(this.mLayoutManager.getHintItemViewType(i2, this.mGridInfo.from()))) {
            return this.mHeaderRefHolder;
        }
        return super.getRefViewHolder(i2, z);
    }

    public void onAnimationCompleted(boolean z, boolean z3) {
        removeHeaderRefView();
        super.onAnimationCompleted(z, z3);
    }
}
