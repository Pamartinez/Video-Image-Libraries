package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureSlideshow;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureSlideshow2;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements ThumbnailLoadedListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ i(Object obj, Object obj2, int i2, int i7) {
        this.d = i7;
        this.f = obj;
        this.g = obj2;
        this.e = i2;
    }

    public final void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        switch (this.d) {
            case 0:
                SearchCreatureSlideshow searchCreatureSlideshow = (SearchCreatureSlideshow) this.f;
                SearchCreatureSlideshow.SlidePage slidePage = (SearchCreatureSlideshow.SlidePage) this.g;
                searchCreatureSlideshow.lambda$slideshowNext$5(slidePage, this.e, bitmap, uniqueKey, thumbKind);
                return;
            default:
                ((SearchCreatureSlideshow2) this.f).lambda$slideshowNext$5((SearchCreatureSlideshow2.SlidePage) this.g, this.e, bitmap, uniqueKey, thumbKind);
                return;
        }
    }
}
