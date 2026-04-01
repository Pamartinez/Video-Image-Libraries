package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import com.samsung.android.gallery.widget.search.pictures.OnHeaderClickListener;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ b(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((SearchCreatureSlideshow) obj).setOnClickListener((OnHeaderClickListener) obj2);
                return;
            case 1:
                ((SearchCreatureSlideshow) obj2).lambda$onSlideshowClicked$3((OnHeaderClickListener) obj);
                return;
            default:
                ((SearchCreatureSlideshow2) obj2).lambda$onSlideshowClicked$3((OnHeaderClickListener) obj);
                return;
        }
    }
}
