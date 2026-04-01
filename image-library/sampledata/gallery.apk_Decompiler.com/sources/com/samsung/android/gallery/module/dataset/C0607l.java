package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataClusterMapList;
import java.util.Comparator;

/* renamed from: com.samsung.android.gallery.module.dataset.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0607l implements Comparator {
    public final /* synthetic */ int d;

    public /* synthetic */ C0607l(int i2) {
        this.d = i2;
    }

    public final int compare(Object obj, Object obj2) {
        MediaItem mediaItem = (MediaItem) obj;
        MediaItem mediaItem2 = (MediaItem) obj2;
        switch (this.d) {
            case 0:
                return MediaDataClusterMapList.MediaDataMapArray.lambda$openInternal$0(mediaItem, mediaItem2);
            default:
                return AlbumDataHelper.lambda$static$0(mediaItem, mediaItem2);
        }
    }
}
