package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsClusterResult extends ClusterResult {
    public AlbumsClusterResult(View view, EventContext eventContext) {
        super(view, eventContext);
    }

    public String getCategoryLocationKey() {
        return new UriBuilder("location://search/fileList/ClusterCategoryAlbum").appendArg("name", this.mPresenter.getClusterKeyNames()).build();
    }

    public String getClusterItemLocationKey(ListViewHolder listViewHolder, MediaItem mediaItem) {
        return getTargetAlbum(mediaItem);
    }

    public String getLocationKey() {
        return new UriBuilder("location://search/fileList/KeywordAlbums").appendArg("name", this.mPresenter.getClusterKeyNames()).build();
    }

    public ClusterResultType getType() {
        return ClusterResultType.ALBUMS;
    }

    public int getViewStubId() {
        return R.id.albums_cluster_view_stub;
    }

    public void onClusterItemClicked(ListViewHolder listViewHolder, MediaItem mediaItem, int i2) {
        this.mPresenter.getBlackboard().publish("data://albums/current", mediaItem);
        super.onClusterItemClicked(listViewHolder, mediaItem, i2);
    }
}
