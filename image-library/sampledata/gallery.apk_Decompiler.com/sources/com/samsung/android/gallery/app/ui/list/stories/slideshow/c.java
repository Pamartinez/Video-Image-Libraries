package com.samsung.android.gallery.app.ui.list.stories.slideshow;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.ToLongFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ToLongFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2563a;

    public /* synthetic */ c(int i2) {
        this.f2563a = i2;
    }

    public final long applyAsLong(Object obj) {
        MediaItem mediaItem = (MediaItem) obj;
        switch (this.f2563a) {
            case 0:
                return ((MediaItem) mediaItem).getDateTaken();
            default:
                return ((MediaItem) mediaItem).getFileId();
        }
    }
}
