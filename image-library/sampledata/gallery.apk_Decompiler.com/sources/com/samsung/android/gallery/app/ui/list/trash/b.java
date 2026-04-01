package com.samsung.android.gallery.app.ui.list.trash;

import android.content.Context;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ TrashPresenter d;
    public final /* synthetic */ Context e;

    public /* synthetic */ b(TrashPresenter trashPresenter, Context context) {
        this.d = trashPresenter;
        this.e = context;
    }

    public final void accept(Object obj) {
        this.d.lambda$updateToolbar$3(this.e, (GalleryAppBarLayout) obj);
    }
}
