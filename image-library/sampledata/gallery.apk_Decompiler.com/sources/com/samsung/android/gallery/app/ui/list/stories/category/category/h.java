package com.samsung.android.gallery.app.ui.list.stories.category.category;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Supplier {
    public final /* synthetic */ StoriesCatTransitoryViewHolder d;

    public /* synthetic */ h(StoriesCatTransitoryViewHolder storiesCatTransitoryViewHolder) {
        this.d = storiesCatTransitoryViewHolder;
    }

    public final Object get() {
        return Boolean.valueOf(this.d.isTouchable());
    }
}
