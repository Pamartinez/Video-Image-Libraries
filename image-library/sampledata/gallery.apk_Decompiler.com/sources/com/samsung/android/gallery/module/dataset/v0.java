package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.ToIntFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class v0 implements ToIntFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2999a;
    public final /* synthetic */ MediaDataCursor b;

    public /* synthetic */ v0(MediaDataCursor mediaDataCursor, int i2) {
        this.f2999a = i2;
        this.b = mediaDataCursor;
    }

    public final int applyAsInt(Object obj) {
        int i2 = this.f2999a;
        MediaDataCursor mediaDataCursor = this.b;
        switch (i2) {
            case 0:
                return ((MediaDataSuggestions) mediaDataCursor).lambda$preload$3((MediaItem) obj);
            default:
                return ((MediaDataSearchStories) mediaDataCursor).childOrder((String) obj);
        }
    }
}
