package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements ViewerMenuItem.Validator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2595a;
    public final /* synthetic */ GroupPanelSingleTakeShareMenuItem b;

    public /* synthetic */ f(GroupPanelSingleTakeShareMenuItem groupPanelSingleTakeShareMenuItem, int i2) {
        this.f2595a = i2;
        this.b = groupPanelSingleTakeShareMenuItem;
    }

    public final boolean isValid(MediaItem mediaItem, String str) {
        int i2 = this.f2595a;
        GroupPanelSingleTakeShareMenuItem groupPanelSingleTakeShareMenuItem = this.b;
        switch (i2) {
            case 0:
                return groupPanelSingleTakeShareMenuItem.lambda$setMenuAttribute$1(mediaItem, str);
            default:
                return groupPanelSingleTakeShareMenuItem.lambda$setMenuAttribute$2(mediaItem, str);
        }
    }
}
