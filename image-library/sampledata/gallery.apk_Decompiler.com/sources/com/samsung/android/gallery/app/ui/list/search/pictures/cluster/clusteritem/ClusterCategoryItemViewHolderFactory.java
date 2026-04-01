package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemViewHolderFactory;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusterCategoryItemViewHolderFactory extends CategoryItemViewHolderFactory {
    protected final LayoutInflater mLayoutInflater;

    public ClusterCategoryItemViewHolderFactory(Context context) {
        this(LayoutInflater.from(context));
    }

    private ListViewHolder createCarouselCategoryViewHolder(ViewGroup viewGroup, int i2) {
        return new CategoryClusterResultViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_item_category_cluster_layout, viewGroup, false), i2);
    }

    private ListViewHolder createIconTitleDividerViewHolder(ViewGroup viewGroup, int i2) {
        return new IconTitleDividerViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_category_item_icon_title_divider, viewGroup, false), i2);
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == 9) {
            return createCarouselCategoryViewHolder(viewGroup, i2);
        }
        return createIconTitleDividerViewHolder(viewGroup, i2);
    }

    public ClusterCategoryItemViewHolderFactory(LayoutInflater layoutInflater) {
        super(layoutInflater);
        this.mLayoutInflater = layoutInflater;
    }
}
