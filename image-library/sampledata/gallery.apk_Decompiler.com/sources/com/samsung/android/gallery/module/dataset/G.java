package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class G implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaItem e;

    public /* synthetic */ G(MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = mediaItem;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        MediaItem mediaItem = this.e;
        QueryParams queryParams = (QueryParams) obj;
        switch (i2) {
            case 0:
                queryParams.setGroupTypes(GroupType.SINGLE_TAKEN).setGroupMediaFilter(mediaItem.getAlbumID(), mediaItem.getGroupMediaId());
                return;
            case 1:
                queryParams.setGroupMediaFilter(mediaItem.getAlbumID(), mediaItem.getGroupMediaId());
                return;
            default:
                queryParams.setGroupTypes(GroupType.BURST).setGroupMediaFilter(mediaItem.getBucketID(), mediaItem.getGroupMediaId());
                return;
        }
    }
}
