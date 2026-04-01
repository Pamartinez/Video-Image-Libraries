package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItemFactory;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements AiEditItemFactory.AiEditItemConstructor {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2573a;

    public /* synthetic */ b(int i2) {
        this.f2573a = i2;
    }

    public final AiEditItem apply(EventContext eventContext, ActionInvoker actionInvoker) {
        switch (this.f2573a) {
            case 0:
                return new PortraitEffectAiEdit(eventContext, actionInvoker);
            case 1:
                return new LongExposureAiEdit(eventContext, actionInvoker);
            case 2:
                return new BackgroundEffectAiEdit(eventContext, actionInvoker);
            case 3:
                return new RemasterAiEditComposite(eventContext, actionInvoker);
            case 4:
                return new HighlightAiEdit(eventContext, actionInvoker);
            case 5:
                return new SuperSlowAiEdit(eventContext, actionInvoker);
            case 6:
                return new ColorCorrectAiEdit(eventContext, actionInvoker);
            default:
                return new ObjectEraserAiEdit(eventContext, actionInvoker);
        }
    }
}
