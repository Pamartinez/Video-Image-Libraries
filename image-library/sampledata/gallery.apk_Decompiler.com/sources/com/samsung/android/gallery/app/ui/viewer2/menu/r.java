package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuFactory;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements ViewerMenuFactory.ViewerMenuItemConstructor {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2603a;

    public /* synthetic */ r(int i2) {
        this.f2603a = i2;
    }

    public final Object apply(Object obj, Object obj2) {
        switch (this.f2603a) {
            case 0:
                return new PasteEffectMenu((EventContext) obj, (ActionInvoker) obj2);
            case 1:
                return new ConvertHDR10PlusToSDRMenu((EventContext) obj, (ActionInvoker) obj2);
            case 2:
                return new ConvertHDRToSDRMenu((EventContext) obj, (ActionInvoker) obj2);
            case 3:
                return new ConvertAPVToHEVCMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 4:
                return new ConvertHEIFToJPEGMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 5:
                return new RevertToOriginalMenuItem((EventContext) obj, (ActionInvoker) obj2);
            case 6:
                return new RemoveLiveEffectMenuItem((EventContext) obj, (ActionInvoker) obj2);
            default:
                return new CreateVideoGifMenuItem((EventContext) obj, (ActionInvoker) obj2);
        }
    }
}
