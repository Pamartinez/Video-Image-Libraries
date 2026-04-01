package com.samsung.android.gallery.app.ui.list.stories.category;

import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ InternalEvent d;
    public final /* synthetic */ Object[] e;

    public /* synthetic */ e(InternalEvent internalEvent, Object[] objArr) {
        this.d = internalEvent;
        this.e = objArr;
    }

    public final void accept(Object obj) {
        ((StoriesCategory2Header) obj).onHandleInternalEvent(this.d, this.e);
    }
}
