package com.samsung.android.gallery.app.ui.list.stories;

import com.samsung.android.gallery.app.ui.list.stories.abstraction.IPinView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ StoriesLayoutManager d;
    public final /* synthetic */ int e;

    public /* synthetic */ b(StoriesLayoutManager storiesLayoutManager, int i2) {
        this.d = storiesLayoutManager;
        this.e = i2;
    }

    public final void accept(Object obj) {
        this.d.lambda$updateViewSize$0(this.e, (IPinView) obj);
    }
}
