package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureSlideshow;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureSlideshow2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Bitmap e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2546h;

    public /* synthetic */ j(Object obj, Object obj2, Bitmap bitmap, int i2, int i7) {
        this.d = i7;
        this.g = obj;
        this.f2546h = obj2;
        this.e = bitmap;
        this.f = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((SearchCreatureSlideshow) this.g).lambda$slideshowNext$4((SearchCreatureSlideshow.SlidePage) this.f2546h, this.e, this.f);
                return;
            default:
                ((SearchCreatureSlideshow2) this.g).lambda$slideshowNext$4((SearchCreatureSlideshow2.SlidePage) this.f2546h, this.e, this.f);
                return;
        }
    }
}
