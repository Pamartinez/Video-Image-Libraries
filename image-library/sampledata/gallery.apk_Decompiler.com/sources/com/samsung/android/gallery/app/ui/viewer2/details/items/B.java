package com.samsung.android.gallery.app.ui.viewer2.details.items;

import com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItemTag;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class B implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DetailsItemTag.TagAdapter f2580a;
    public final /* synthetic */ MediaItem b;

    public /* synthetic */ B(DetailsItemTag.TagAdapter tagAdapter, MediaItem mediaItem) {
        this.f2580a = tagAdapter;
        this.b = mediaItem;
    }

    public final boolean test(Object obj) {
        return this.f2580a.lambda$contains$0(this.b, (MediaItem) obj);
    }
}
