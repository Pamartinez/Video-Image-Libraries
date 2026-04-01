package com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Supplier {
    public final /* synthetic */ StoriesCatTransitoryRecapContentViewHolder d;

    public /* synthetic */ c(StoriesCatTransitoryRecapContentViewHolder storiesCatTransitoryRecapContentViewHolder) {
        this.d = storiesCatTransitoryRecapContentViewHolder;
    }

    public final Object get() {
        return Boolean.valueOf(this.d.isTouchable());
    }
}
