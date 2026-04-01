package com.samsung.android.gallery.app.ui.viewer2.details.items;

import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements BooleanSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2586a;
    public final /* synthetic */ DetailsListItem b;

    public /* synthetic */ p(DetailsListItem detailsListItem, int i2) {
        this.f2586a = i2;
        this.b = detailsListItem;
    }

    public final boolean getAsBoolean() {
        DetailsListItem detailsListItem;
        int i2 = this.f2586a;
        DetailsListItem detailsListItem2 = this.b;
        switch (i2) {
            case 0:
                detailsListItem = (DetailsItemDynamicVideo) detailsListItem2;
                break;
            default:
                detailsListItem = (DetailsItemSuperSlow) detailsListItem2;
                break;
        }
        return detailsListItem.isResumed();
    }
}
