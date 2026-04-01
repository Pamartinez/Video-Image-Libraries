package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/* renamed from: com.samsung.android.gallery.module.dataset.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0606k implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2982a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0606k(int i2, Object obj) {
        this.f2982a = i2;
        this.b = obj;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2982a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return MediaDataAlbum.lambda$reuseCachedData$6((HashMap) obj2, (MediaItem) obj);
            case 1:
                return ((MediaDataRemaster) obj2).lambda$loadAndCompare$0((MediaItem) obj);
            case 2:
                return ((MediaDataSearch) obj2).lambda$removeIfNoData$6((String) obj);
            case 3:
                return ((Boolean) ((ConcurrentHashMap) obj2).get((MediaItem) obj)).booleanValue();
            default:
                return ((HashSet) obj2).contains(Integer.valueOf(((MediaItem) obj).getAlbumID()));
        }
    }
}
