package com.samsung.android.gallery.app.ui.list.albums.choice;

import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        GalleryToolbar galleryToolbar = (GalleryToolbar) obj;
        switch (this.d) {
            case 0:
                galleryToolbar.setFloatingMode(false);
                return;
            default:
                galleryToolbar.setFloatingMode(true);
                return;
        }
    }
}
