package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements ViewerMenuItem.Validator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2604a;

    public /* synthetic */ s(int i2) {
        this.f2604a = i2;
    }

    public final boolean isValid(MediaItem mediaItem, String str) {
        switch (this.f2604a) {
            case 0:
                return ViewerMenuItem.lambda$static$8(mediaItem, str);
            case 1:
                return ViewerMenuItem.lambda$static$9(mediaItem, str);
            case 2:
                return ViewerMenuItem.lambda$static$10(mediaItem, str);
            case 3:
                return ViewerMenuItem.lambda$static$11(mediaItem, str);
            case 4:
                return ViewerMenuItem.lambda$static$12(mediaItem, str);
            case 5:
                return ViewerMenuItem.lambda$static$13(mediaItem, str);
            case 6:
                return ViewerMenuItem.lambda$static$14(mediaItem, str);
            case 7:
                return ViewerMenuItem.lambda$static$15(mediaItem, str);
            case 8:
                return ViewerMenuItem.lambda$static$16(mediaItem, str);
            default:
                return ViewerMenuItem.lambda$static$17(mediaItem, str);
        }
    }
}
