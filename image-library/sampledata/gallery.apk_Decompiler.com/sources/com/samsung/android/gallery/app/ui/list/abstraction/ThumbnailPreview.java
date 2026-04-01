package com.samsung.android.gallery.app.ui.list.abstraction;

import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThumbnailPreview<V extends IBaseListView> {
    protected final V mView;

    public ThumbnailPreview(V v) {
        this.mView = v;
    }

    public int getLayoutPosition(PreviewViewHolder previewViewHolder) {
        return previewViewHolder.getLayoutPosition();
    }

    public int getViewHolderPosition(PreviewViewHolder previewViewHolder) {
        return (previewViewHolder.getImage().getHeight() / 2) + previewViewHolder.itemView.getTop();
    }

    public boolean isViewAnimating() {
        GalleryListView listView = this.mView.getListView();
        if (listView == null || !listView.isSimilarModeAnimating()) {
            return false;
        }
        return true;
    }

    public boolean limitDuration() {
        return false;
    }

    public List<PreviewViewHolder> listOf() {
        ArrayList arrayList = new ArrayList();
        GalleryListView listView = this.mView.getListView();
        if (!(listView == null || listView.getLayoutManager() == null)) {
            int findLastVisibleItemPositionCompat = listView.findLastVisibleItemPositionCompat();
            for (int findFirstVisibleItemPositionCompat = listView.findFirstVisibleItemPositionCompat(); findFirstVisibleItemPositionCompat <= findLastVisibleItemPositionCompat; findFirstVisibleItemPositionCompat++) {
                RecyclerView.ViewHolder findViewHolderForLayoutPosition = listView.findViewHolderForLayoutPosition(findFirstVisibleItemPositionCompat);
                if (findViewHolderForLayoutPosition instanceof PreviewViewHolder) {
                    arrayList.add((PreviewViewHolder) findViewHolderForLayoutPosition);
                }
            }
        }
        return arrayList;
    }

    public int lowerLimit() {
        GalleryListView listView = this.mView.getListView();
        int i2 = 0;
        if (listView == null) {
            return 0;
        }
        GalleryAppBarLayout appbarLayout = this.mView.getAppbarLayout();
        int bottom = listView.getBottom();
        if (appbarLayout != null) {
            i2 = appbarLayout.getVisibleHeight();
        }
        return bottom - i2;
    }

    public int upperLimit() {
        GalleryListView listView = this.mView.getListView();
        if (listView != null) {
            return listView.getTop();
        }
        return 0;
    }
}
