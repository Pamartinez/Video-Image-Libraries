package com.samsung.android.gallery.app.ui.list.search.keyword.stories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchResultStoriesViewHolderFactory {
    private LayoutCache mCacheLoader = LayoutCache.getInstance();
    private final int mLayoutId = R.layout.recycler_item_search_result_stories_layout;
    private final LayoutInflater mLayoutInflater;

    public SearchResultStoriesViewHolderFactory(Context context) {
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public ListViewHolder createViewHolder(ViewGroup viewGroup, int i2) {
        LayoutCache layoutCache = this.mCacheLoader;
        View view = null;
        if (layoutCache != null) {
            View cachedItemView = layoutCache.getCachedItemView(this.mLayoutId);
            if (cachedItemView == null) {
                this.mCacheLoader = null;
            }
            view = cachedItemView;
        }
        if (view == null) {
            view = this.mLayoutInflater.inflate(this.mLayoutId, viewGroup, false);
        }
        return new SearchResultStoriesViewHolder(view, i2);
    }
}
