package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements ViewerMenuItem.Validator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2599a;
    public final /* synthetic */ SingleTakeGroupShotDeleteMenuItem b;

    public /* synthetic */ n(SingleTakeGroupShotDeleteMenuItem singleTakeGroupShotDeleteMenuItem, int i2) {
        this.f2599a = i2;
        this.b = singleTakeGroupShotDeleteMenuItem;
    }

    public final boolean isValid(MediaItem mediaItem, String str) {
        int i2 = this.f2599a;
        SingleTakeGroupShotDeleteMenuItem singleTakeGroupShotDeleteMenuItem = this.b;
        switch (i2) {
            case 0:
                return singleTakeGroupShotDeleteMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            default:
                return singleTakeGroupShotDeleteMenuItem.lambda$setMenuAttribute$1(mediaItem, str);
        }
    }
}
