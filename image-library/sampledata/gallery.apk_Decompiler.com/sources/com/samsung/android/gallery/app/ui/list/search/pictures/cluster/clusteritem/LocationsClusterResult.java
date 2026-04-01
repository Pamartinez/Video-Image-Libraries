package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocationsClusterResult extends ClusterResult {
    public LocationsClusterResult(View view, EventContext eventContext) {
        super(view, eventContext);
    }

    public String getCategoryLocationKey() {
        return new UriBuilder("location://search/fileList/ClusterCategoryLocation").appendArg("name", this.mPresenter.getClusterKeyNames()).build();
    }

    public String getClusterItemLocationKey(ListViewHolder listViewHolder, MediaItem mediaItem) {
        return getTargetLocation(mediaItem);
    }

    public String getLocationKey() {
        return new UriBuilder("location://search/fileList/KeywordLocations").appendArg("name", this.mPresenter.getClusterKeyNames()).build();
    }

    public ClusterResultType getType() {
        return ClusterResultType.LOCATIONS;
    }

    public int getViewStubId() {
        return R.id.locations_cluster_view_stub;
    }
}
