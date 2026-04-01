package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* renamed from: com.samsung.android.gallery.module.dataset.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0592b implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ C0592b(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        ((MediaItem) obj).setAlbumLevel(this.d);
    }
}
