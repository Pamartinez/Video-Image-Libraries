package com.samsung.android.gallery.app.ui.list.stories.legacy;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesBaseView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StoriesBaseFragment;
import com.samsung.android.gallery.app.ui.list.stories.legacy.StoriesLegacyPresenter;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesLegacyFragment<V extends IStoriesBaseView, P extends StoriesLegacyPresenter> extends StoriesBaseFragment<V, P> implements IStoriesBaseView {
    private RecyclerView.ItemDecoration mListViewDecoration;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class GridItemDecoration extends RecyclerView.ItemDecoration {
        private int mGap;
        private int mSpanCount;

        public GridItemDecoration(int i2, int i7) {
            this.mSpanCount = i2;
            this.mGap = i7 / 2;
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            if (!StoriesLegacyFragment.this.isInMultiWindowMode()) {
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                int i2 = this.mSpanCount;
                int i7 = childAdapterPosition % i2;
                int i8 = this.mGap;
                rect.left = i7 * i8;
                rect.right = ((i2 - i7) - 1) * i8;
                if (Features.isEnabled(Features.IS_RTL)) {
                    int i10 = rect.left;
                    rect.left = rect.right;
                    rect.right = i10;
                }
            }
        }
    }

    private synchronized RecyclerView.ItemDecoration getListViewDecoration() {
        try {
            if (this.mListViewDecoration == null) {
                this.mListViewDecoration = new GridItemDecoration(2, getResources().getDimensionPixelSize(R.dimen.stories_view_item_gap));
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.mListViewDecoration;
    }

    private void setListViewDecoration(GalleryListView galleryListView, int i2) {
        if (i2 == 2) {
            galleryListView.addItemDecoration(getListViewDecoration());
        } else {
            galleryListView.removeItemDecoration(getListViewDecoration());
        }
    }

    public RecyclerView.LayoutManager createLayoutManager() {
        return new GalleryGridLayoutManager(getContext(), getMaxColumnSize());
    }

    public int getLayoutId() {
        return R.layout.fragment_stories_layout_legacy;
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        setListViewDecoration(getListView(), i2);
    }

    public int[] loadPinchColumnResource() {
        if (!isDexMode()) {
            return getResources().getIntArray(R.array.stories_legacy_column_count);
        }
        if (isPortrait()) {
            return new int[]{1};
        }
        return new int[]{2};
    }

    public StoriesLegacyViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        setListViewDecoration(galleryListView, getResources().getConfiguration().orientation);
        return new StoriesLegacyViewAdapter(this, getLocationKey());
    }

    public StoriesLegacyPresenter createPresenter(IStoriesBaseView iStoriesBaseView) {
        return new StoriesLegacyPresenter(this.mBlackboard, iStoriesBaseView);
    }
}
