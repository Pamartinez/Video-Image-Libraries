package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import com.samsung.android.gallery.app.ui.list.search.pdc.PdcSearchDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchRelationshipHeaderView extends SearchHeaderView {
    private final PdcSearchDelegate mPdcSearchDelegate;

    public SearchRelationshipHeaderView(ISearchPicturesView iSearchPicturesView, boolean z) {
        super(iSearchPicturesView.getContext(), iSearchPicturesView.getHeaderTargetView(), z);
        PdcSearchDelegate pdcSearchDelegate = new PdcSearchDelegate(iSearchPicturesView);
        this.mPdcSearchDelegate = pdcSearchDelegate;
        pdcSearchDelegate.bindView(getView());
    }

    public int getLayoutId() {
        return R.layout.search_relationship_preview_header;
    }

    public PdcSearchDelegate getPdcDelegate() {
        return this.mPdcSearchDelegate;
    }

    public void handleResolutionChanged() {
        super.handleResolutionChanged();
        this.mPdcSearchDelegate.handleResolutionChanged();
    }

    public void initHeaderItem() {
        this.mPdcSearchDelegate.onRecycled();
    }

    public void recycle() {
        this.mPdcSearchDelegate.onRecycled();
    }

    public boolean setHeaderItem(MediaItem mediaItem) {
        return false;
    }

    public void showCountHeaderOnly(boolean z) {
        super.showCountHeaderOnly(z);
        if (z) {
            this.mPdcSearchDelegate.onRecycled();
        }
    }

    public void setEnabled(boolean z) {
    }
}
