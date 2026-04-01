package com.samsung.android.gallery.app.activity;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ GalleryCompat d;

    public /* synthetic */ f(GalleryCompat galleryCompat) {
        this.d = galleryCompat;
    }

    public final void accept(Object obj) {
        this.d.onAnrBg((StackTraceElement[]) obj);
    }
}
