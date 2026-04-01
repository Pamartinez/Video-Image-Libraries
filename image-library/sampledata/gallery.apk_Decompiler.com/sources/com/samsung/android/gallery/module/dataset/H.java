package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class H implements Consumer {
    public final /* synthetic */ MediaDataList.MediaDataArray d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ H(MediaDataList.MediaDataArray mediaDataArray, boolean z, MediaItem mediaItem) {
        this.d = mediaDataArray;
        this.e = z;
        this.f = mediaItem;
    }

    public final void accept(Object obj) {
        this.d.lambda$updateList$3(this.e, this.f, (QueryParams) obj);
    }
}
