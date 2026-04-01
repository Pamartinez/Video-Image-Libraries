package com.samsung.android.gallery.app.ui.viewer2.details.items;

import androidx.recyclerview.widget.GridLayoutManager;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ DetailsListItem e;

    public /* synthetic */ r(DetailsListItem detailsListItem, int i2) {
        this.d = i2;
        this.e = detailsListItem;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        DetailsListItem detailsListItem = this.e;
        switch (i2) {
            case 0:
                ((DetailsItemDynamicVideo) detailsListItem).lambda$onItemClick$1((DynamicViewPlayInfo) obj);
                return;
            default:
                ((DetailsItemStory) detailsListItem).lambda$updateLayout$0((GridLayoutManager) obj);
                return;
        }
    }
}
