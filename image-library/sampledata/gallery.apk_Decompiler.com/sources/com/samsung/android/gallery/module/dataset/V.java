package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.ToLongFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class V implements ToLongFunction {
    public final long applyAsLong(Object obj) {
        return ((MediaItem) obj).getAlbumOrder();
    }
}
