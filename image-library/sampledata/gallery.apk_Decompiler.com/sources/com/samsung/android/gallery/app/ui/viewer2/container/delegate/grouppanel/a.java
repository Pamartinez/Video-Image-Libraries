package com.samsung.android.gallery.app.ui.viewer2.container.delegate.grouppanel;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.grouppanel.GroupItemPanelDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaItem f2576a;

    public /* synthetic */ a(MediaItem mediaItem) {
        this.f2576a = mediaItem;
    }

    public final boolean test(Object obj) {
        return GroupItemPanelDelegate.AnonymousClass1.lambda$onSelectAll$0(this.f2576a, (MediaItem) obj);
    }
}
