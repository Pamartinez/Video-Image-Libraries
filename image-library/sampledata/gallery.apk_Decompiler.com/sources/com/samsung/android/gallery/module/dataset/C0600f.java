package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.album.AlbumInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

/* renamed from: com.samsung.android.gallery.module.dataset.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0600f implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ HashMap e;

    public /* synthetic */ C0600f(int i2, HashMap hashMap) {
        this.d = i2;
        this.e = hashMap;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        HashMap hashMap = this.e;
        MediaItem mediaItem = (MediaItem) obj;
        switch (i2) {
            case 0:
                hashMap.put(mediaItem.getThumbCacheKey(), mediaItem);
                return;
            case 1:
                MediaDataAlbum.lambda$reuseCachedData$5(hashMap, mediaItem);
                return;
            case 2:
                MediaDataAlbum.lambda$reuseCachedData$8(hashMap, mediaItem);
                return;
            default:
                ((ArrayList) hashMap.computeIfAbsent(Integer.valueOf(AlbumInfo.getDuplicateNameFolderId(mediaItem.getTitle())), new K(12))).add(mediaItem);
                return;
        }
    }
}
