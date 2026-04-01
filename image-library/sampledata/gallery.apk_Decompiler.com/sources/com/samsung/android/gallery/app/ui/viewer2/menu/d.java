package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements ViewerMenuItem.Validator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2593a;
    public final /* synthetic */ ForceRotateMenuItem b;

    public /* synthetic */ d(ForceRotateMenuItem forceRotateMenuItem, int i2) {
        this.f2593a = i2;
        this.b = forceRotateMenuItem;
    }

    public final boolean isValid(MediaItem mediaItem, String str) {
        int i2 = this.f2593a;
        ForceRotateMenuItem forceRotateMenuItem = this.b;
        switch (i2) {
            case 0:
                return forceRotateMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 1:
                return forceRotateMenuItem.lambda$setMenuAttribute$2(mediaItem, str);
            case 2:
                return forceRotateMenuItem.lambda$setMenuAttribute$3(mediaItem, str);
            case 3:
                return forceRotateMenuItem.lambda$setMenuAttribute$4(mediaItem, str);
            case 4:
                return forceRotateMenuItem.lambda$setMenuAttribute$5(mediaItem, str);
            default:
                return forceRotateMenuItem.lambda$setMenuAttribute$6(mediaItem, str);
        }
    }
}
