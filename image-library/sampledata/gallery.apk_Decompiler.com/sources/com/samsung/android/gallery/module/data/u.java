package com.samsung.android.gallery.module.data;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class u implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ HashMap e;
    public final /* synthetic */ MediaItem[] f;

    public /* synthetic */ u(HashMap hashMap, MediaItem[] mediaItemArr, int i2) {
        this.d = i2;
        this.e = hashMap;
        this.f = mediaItemArr;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                Optional.ofNullable((List) this.e.get("s" + ((MediaItem) obj).getFileId())).ifPresent(new q(this.f, (MediaItem) obj, 0));
                return;
            default:
                Optional.ofNullable((List) this.e.get("g" + ((MediaItem) obj).getMediaId())).ifPresent(new q(this.f, (MediaItem) obj, 2));
                return;
        }
    }
}
