package com.samsung.android.gallery.app.ui.viewer2.details.items;

import com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItemDebugExif;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaItem f2582a;

    public /* synthetic */ j(MediaItem mediaItem) {
        this.f2582a = mediaItem;
    }

    public final Object apply(Object obj) {
        return DetailsItemDebugExif.DebugExifAdapter.lambda$new$0(this.f2582a, (String) obj);
    }
}
