package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements ViewerMenuItem.Validator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2594a;
    public final /* synthetic */ GroupPanelSingleTakeSaveMenuItem b;

    public /* synthetic */ e(GroupPanelSingleTakeSaveMenuItem groupPanelSingleTakeSaveMenuItem, int i2) {
        this.f2594a = i2;
        this.b = groupPanelSingleTakeSaveMenuItem;
    }

    public final boolean isValid(MediaItem mediaItem, String str) {
        int i2 = this.f2594a;
        GroupPanelSingleTakeSaveMenuItem groupPanelSingleTakeSaveMenuItem = this.b;
        switch (i2) {
            case 0:
                return groupPanelSingleTakeSaveMenuItem.lambda$setMenuAttribute$0(mediaItem, str);
            case 1:
                return groupPanelSingleTakeSaveMenuItem.lambda$setMenuAttribute$1(mediaItem, str);
            default:
                return groupPanelSingleTakeSaveMenuItem.lambda$setMenuAttribute$2(mediaItem, str);
        }
    }
}
