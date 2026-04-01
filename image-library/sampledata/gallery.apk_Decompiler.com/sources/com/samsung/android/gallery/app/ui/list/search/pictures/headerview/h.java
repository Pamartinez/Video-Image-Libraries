package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import android.graphics.drawable.Drawable;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureSlideshow;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureSlideshow2;
import com.samsung.android.gallery.widget.utils.ViewMatrixUtils;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ h(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((SearchCreatureSlideshow.SlidePage) obj).mView.setImageDrawable((Drawable) null);
                return;
            case 1:
                ViewMatrixUtils.setViewMatrix(((SearchCreatureSlideshow.SlidePage) obj).mView, ((SearchCreatureSlideshow.SlidePage) obj).mItem, true);
                return;
            case 2:
                ((SearchCreatureSlideshow2.SlidePage) obj).mView.setImageDrawable((Drawable) null);
                return;
            default:
                ViewMatrixUtils.setViewMatrix(((SearchCreatureSlideshow2.SlidePage) obj).mView, ((SearchCreatureSlideshow2.SlidePage) obj).mItem, true);
                return;
        }
    }
}
