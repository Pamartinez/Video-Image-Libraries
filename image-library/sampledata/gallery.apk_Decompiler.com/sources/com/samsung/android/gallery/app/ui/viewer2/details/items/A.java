package com.samsung.android.gallery.app.ui.viewer2.details.items;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class A implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ DetailsListItem e;

    public /* synthetic */ A(DetailsListItem detailsListItem, int i2) {
        this.d = i2;
        this.e = detailsListItem;
    }

    public final void run() {
        int i2 = this.d;
        DetailsListItem detailsListItem = this.e;
        switch (i2) {
            case 0:
                ((DetailsItemRelated) detailsListItem).unSubscribeReturnTransition();
                return;
            default:
                ((DetailsItemTag) detailsListItem).updateTitleMaxWidth();
                return;
        }
    }
}
