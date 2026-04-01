package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.cache.AbsCacheMgr$EvictListener;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;

/* renamed from: com.samsung.android.gallery.module.dataset.t  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0614t implements AbsCacheMgr$EvictListener, MediaData.OnDataReadListener {
    public final /* synthetic */ Object d;

    public /* synthetic */ C0614t(Object obj) {
        this.d = obj;
    }

    public void OnEvicted(Object obj, Object obj2) {
        ((MediaDataCursor) this.d).onCacheEvicted((Integer) obj, (MediaItem) obj2);
    }

    public void onDataReadCompleted(MediaItem mediaItem) {
        MediaDataTimeline2.lambda$readOnDemand$2((MediaItem[]) this.d, mediaItem);
    }
}
