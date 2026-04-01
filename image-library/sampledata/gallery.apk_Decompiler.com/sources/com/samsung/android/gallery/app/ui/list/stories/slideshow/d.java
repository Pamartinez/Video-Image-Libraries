package com.samsung.android.gallery.app.ui.list.stories.slideshow;

import com.samsung.android.gallery.app.ui.list.stories.slideshow.SlideshowV2Presenter;
import com.samsung.android.gallery.module.dataset.MediaData;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ SlideshowV2Presenter.AnonymousClass1 d;

    public /* synthetic */ d(SlideshowV2Presenter.AnonymousClass1 r1) {
        this.d = r1;
    }

    public final void accept(Object obj) {
        this.d.lambda$onDataChanged$0((MediaData) obj);
    }
}
