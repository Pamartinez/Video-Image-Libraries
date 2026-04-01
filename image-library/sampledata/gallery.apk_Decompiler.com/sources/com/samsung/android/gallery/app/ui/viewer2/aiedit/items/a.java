package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItemFactory;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2571a;
    public final /* synthetic */ EventContext b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ActionInvoker f2572c;

    public /* synthetic */ a(EventContext eventContext, ActionInvoker actionInvoker, int i2) {
        this.f2571a = i2;
        this.b = eventContext;
        this.f2572c = actionInvoker;
    }

    public final Object apply(Object obj) {
        switch (this.f2571a) {
            case 0:
                return ((AiEditItemFactory.AiEditItemConstructor) obj).apply(this.b, this.f2572c);
            default:
                return ((AiEditItemFactory.AiEditItemConstructor) obj).apply(this.b, this.f2572c);
        }
    }
}
