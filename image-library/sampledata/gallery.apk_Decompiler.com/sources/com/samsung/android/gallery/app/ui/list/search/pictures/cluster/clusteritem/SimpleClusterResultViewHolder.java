package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleClusterResultViewHolder extends ClusterResultViewHolder {
    public SimpleClusterResultViewHolder(EventContext eventContext, View view, int i2) {
        super(eventContext, view, i2);
    }

    public void bindView(View view) {
        super.bindView(view);
        if (getViewType() == ClusterResultType.PEOPLE.ordinal()) {
            setThumbnailShape(1, (float) getContext().getResources().getDimensionPixelOffset(R.dimen.search_cluster_results_people_type_item_radius));
        }
    }

    public String getDurationText(MediaItem mediaItem) {
        if (mediaItem != null) {
            return Integer.toString(mediaItem.getCount());
        }
        return "";
    }
}
