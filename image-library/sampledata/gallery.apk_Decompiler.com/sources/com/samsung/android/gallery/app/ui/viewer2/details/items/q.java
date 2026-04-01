package com.samsung.android.gallery.app.ui.viewer2.details.items;

import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements VideoCompleteCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2587a;
    public final /* synthetic */ DetailsListItem b;

    public /* synthetic */ q(DetailsListItem detailsListItem, int i2) {
        this.f2587a = i2;
        this.b = detailsListItem;
    }

    public final void onCompleted(MediaItem mediaItem) {
        int i2 = this.f2587a;
        DetailsListItem detailsListItem = this.b;
        switch (i2) {
            case 0:
                ((DetailsItemDynamicVideo) detailsListItem).onCompletedVideo(mediaItem);
                return;
            default:
                ((DetailsItemSuperSlow) detailsListItem).onCompletedVideo(mediaItem);
                return;
        }
    }
}
