package com.samsung.android.gallery.app.ui.list.search.category.document;

import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryFragment;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchDocumentFragment extends CategoryFragment {
    public void bindView(View view) {
        super.bindView(view);
        ViewMarginUtils.setHorizontalPadding(getListView(), 0);
    }

    public RecyclerView.LayoutManager createLayoutManager() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), getMaxColumnSize());
        gridLayoutManager.setSpanSizeLookup(createSpanSizeLookUp(gridLayoutManager));
        return gridLayoutManager;
    }

    public GridLayoutManager.SpanSizeLookup createSpanSizeLookUp(final GridLayoutManager gridLayoutManager) {
        return new GridLayoutManager.SpanSizeLookup() {
            public int getSpanIndex(int i2, int i7) {
                SearchDocumentItemAdapter adapter = SearchDocumentFragment.this.getAdapter();
                if (adapter != null) {
                    return adapter.getHintStartSpan(i2, i7);
                }
                Log.w(SearchDocumentFragment.this.TAG, "span index 0 (null adapter)");
                return 0;
            }

            public int getSpanSize(int i2) {
                SearchDocumentItemAdapter adapter = SearchDocumentFragment.this.getAdapter();
                if (adapter == null || !adapter.isDivider(i2)) {
                    return 1;
                }
                return gridLayoutManager.getSpanCount();
            }
        };
    }

    public SearchDocumentItemAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new SearchDocumentItemAdapter(this, getLocationKey(), galleryListView, this.mPropertyHelper, true);
    }

    public SearchDocumentItemAdapter getAdapter() {
        return (SearchDocumentItemAdapter) super.getAdapter();
    }
}
