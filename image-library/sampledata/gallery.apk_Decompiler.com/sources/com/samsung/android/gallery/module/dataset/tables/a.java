package com.samsung.android.gallery.module.dataset.tables;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {
    public final Object apply(Object obj) {
        return Long.valueOf(((MediaItem) ((MediaItem) obj)).getFileId());
    }
}
