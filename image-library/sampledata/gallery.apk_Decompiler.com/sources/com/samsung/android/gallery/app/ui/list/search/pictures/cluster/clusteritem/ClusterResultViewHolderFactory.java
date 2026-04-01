package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultViewHolder;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusterResultViewHolderFactory {
    private LayoutCache mCacheLoader = LayoutCache.getInstance();
    private final LayoutInflater mLayoutInflater;

    public ClusterResultViewHolderFactory(Context context) {
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    private View getView(ViewGroup viewGroup, int i2) {
        LayoutCache layoutCache = this.mCacheLoader;
        View view = null;
        if (layoutCache != null) {
            View cachedItemView = layoutCache.getCachedItemView(i2);
            if (cachedItemView == null) {
                this.mCacheLoader = null;
            }
            view = cachedItemView;
        }
        if (view == null) {
            return this.mLayoutInflater.inflate(i2, viewGroup, false);
        }
        return view;
    }

    public ListViewHolder createViewHolder(EventContext eventContext, ViewGroup viewGroup, int i2) {
        if (i2 == ClusterResultType.OCRS.ordinal()) {
            return new OCRsClusterResultViewHolder(eventContext, getView(viewGroup, R.layout.recycler_item_search_cluster_result_ocrs_layout), i2);
        }
        if (i2 == ClusterResultType.TOP_RESULT.ordinal()) {
            return new SearchClusterResultViewHolder(getView(viewGroup, R.layout.recycler_item_pictures_previewable_image_layout), i2);
        }
        if (i2 == ClusterResultType.CAROUSELS.ordinal()) {
            return new CarouselClusterResult2ViewHolder(eventContext, getView(viewGroup, R.layout.recycler_item_search_cluster_result_carousel), i2);
        }
        return new SimpleClusterResultViewHolder(eventContext, getView(viewGroup, R.layout.recycler_item_search_cluster_result_simple_layout), i2);
    }
}
