package com.samsung.android.gallery.module.details;

import com.samsung.android.gallery.module.c2pa.C2paInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.map.manager.AddressCompat;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d = 1;
    public final /* synthetic */ MediaItem e;
    public final /* synthetic */ DetailsDataLoadCallback f;

    public /* synthetic */ a(MediaItem mediaItem, DetailsDataLoadCallback detailsDataLoadCallback) {
        this.e = mediaItem;
        this.f = detailsDataLoadCallback;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                DetailsC2paLoader.lambda$loadC2pa$0(this.f, this.e, (C2paInfo) obj);
                return;
            default:
                DetailsMapDataLoader.lambda$loadAddress$0(this.e, this.f, (AddressCompat) obj);
                return;
        }
    }

    public /* synthetic */ a(DetailsDataLoadCallback detailsDataLoadCallback, MediaItem mediaItem) {
        this.f = detailsDataLoadCallback;
        this.e = mediaItem;
    }
}
