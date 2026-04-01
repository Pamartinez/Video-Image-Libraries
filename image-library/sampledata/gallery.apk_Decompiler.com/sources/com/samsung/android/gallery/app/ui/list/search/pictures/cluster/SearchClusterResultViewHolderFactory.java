package com.samsung.android.gallery.app.ui.list.search.pictures.cluster;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesViewHolderFactory;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchClusterResultViewHolderFactory extends SearchPicturesViewHolderFactory {
    private final boolean mIsKeywordSearch;

    public SearchClusterResultViewHolderFactory(Context context, boolean z) {
        super(context);
        this.mIsKeywordSearch = z;
    }

    public ListViewHolder getDataViewHolder(View view, int i2) {
        if (this.mIsKeywordSearch) {
            return new SearchClusterResultViewHolder(view, i2);
        }
        return super.getDataViewHolder(view, i2);
    }
}
