package com.samsung.android.gallery.app.ui.dialog.creature.merge;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements UniqueKey {
    public final /* synthetic */ MediaItem d;

    public /* synthetic */ b(MediaItem mediaItem) {
        this.d = mediaItem;
    }

    public final int getKey() {
        return this.d.getSubCategory().hashCode();
    }
}
