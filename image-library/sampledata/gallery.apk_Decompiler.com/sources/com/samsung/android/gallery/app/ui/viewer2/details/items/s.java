package com.samsung.android.gallery.app.ui.viewer2.details.items;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItemFactory;
import com.samsung.android.gallery.widget.details.DetailsView;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DetailsView f2588a;
    public final /* synthetic */ EventContext b;

    public /* synthetic */ s(DetailsView detailsView, EventContext eventContext) {
        this.f2588a = detailsView;
        this.b = eventContext;
    }

    public final Object apply(Object obj) {
        return ((DetailsItemFactory.DetailsListConstructor) obj).apply(this.f2588a, this.b);
    }
}
