package com.samsung.android.gallery.app.ui.list.search;

import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CategoryCardListAdapterV2 f2540a;
    public final /* synthetic */ ListViewHolder b;

    public /* synthetic */ d(CategoryCardListAdapterV2 categoryCardListAdapterV2, ListViewHolder listViewHolder) {
        this.f2540a = categoryCardListAdapterV2;
        this.b = listViewHolder;
    }

    public final boolean getAsBoolean() {
        return this.f2540a.lambda$bindViewHolderInternal$0(this.b);
    }
}
