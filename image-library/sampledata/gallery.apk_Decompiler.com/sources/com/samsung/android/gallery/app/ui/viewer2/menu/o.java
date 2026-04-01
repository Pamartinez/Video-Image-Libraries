package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements ViewerMenuItem.Validator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2600a;
    public final /* synthetic */ SmartViewMenuItem2 b;

    public /* synthetic */ o(SmartViewMenuItem2 smartViewMenuItem2, int i2) {
        this.f2600a = i2;
        this.b = smartViewMenuItem2;
    }

    public final boolean isValid(MediaItem mediaItem, String str) {
        int i2 = this.f2600a;
        SmartViewMenuItem2 smartViewMenuItem2 = this.b;
        switch (i2) {
            case 0:
                return smartViewMenuItem2.lambda$setMenuAttribute$0(mediaItem, str);
            default:
                return smartViewMenuItem2.lambda$setMenuAttribute$5(mediaItem, str);
        }
    }
}
