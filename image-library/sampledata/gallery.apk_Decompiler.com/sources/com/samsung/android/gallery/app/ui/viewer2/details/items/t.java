package com.samsung.android.gallery.app.ui.viewer2.details.items;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItemFactory;
import com.samsung.android.gallery.widget.details.DetailsView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t implements DetailsItemFactory.DetailsListConstructor {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2589a;

    public /* synthetic */ t(int i2) {
        this.f2589a = i2;
    }

    public final DetailsItem apply(DetailsView detailsView, EventContext eventContext) {
        switch (this.f2589a) {
            case 0:
                return new DetailsItemBasic(detailsView, eventContext);
            case 1:
                return new DetailsItemLocationText(detailsView, eventContext);
            case 2:
                return new DetailsItemLocation(detailsView, eventContext);
            case 3:
                return new DetailsItemCreatures(detailsView, eventContext);
            case 4:
                return new DetailsItemStory(detailsView, eventContext);
            case 5:
                return new DetailsItemClippedImage(detailsView, eventContext);
            case 6:
                return new DetailsItemSharedProfile(detailsView, eventContext);
            case 7:
                return new DetailsItemTag(detailsView, eventContext);
            case 8:
                return new DetailsItemRelated(detailsView, eventContext);
            case 9:
                return new DetailsItemDynamicVideo(detailsView, eventContext);
            case 10:
                return new DetailsItemSuperSlow(detailsView, eventContext);
            case 11:
                return new DetailsItem3rdParty(detailsView, eventContext);
            case 12:
                return new DetailsItemC2pa(detailsView, eventContext);
            case 13:
                return new DetailsItemGeneratedImage(detailsView, eventContext);
            case 14:
                return new DetailsItemDebugExif(detailsView, eventContext);
            case 15:
                return new DetailsItemCameraInfo(detailsView, eventContext);
            default:
                return new DetailsItemCaptureUrl(detailsView, eventContext);
        }
    }
}
