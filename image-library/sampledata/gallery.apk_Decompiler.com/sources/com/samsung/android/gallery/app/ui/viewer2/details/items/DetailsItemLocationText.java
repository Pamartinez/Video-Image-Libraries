package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.view.View;
import android.view.ViewStub;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.viewer.MoveToMapViewCmd;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItemLocationText extends DetailsItem {
    public DetailsItemLocationText(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewInflate$0(View view) {
        MoveToMapViewCmd moveToMapViewCmd = new MoveToMapViewCmd();
        EventContext eventContext = this.mEventContext;
        moveToMapViewCmd.execute(eventContext, eventContext.getCurrentItem());
    }

    /* access modifiers changed from: private */
    public void updateAddressView(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        setTextAndVisibility((int) R.id.moreinfo_location_text, (CharSequence) DetailsData.of(mediaItem).getAddress());
    }

    /* access modifiers changed from: private */
    public void updatePoiView(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        setTextAndVisibility((int) R.id.moreinfo_location_poi_text, (CharSequence) DetailsData.of(mediaItem).getPoi());
    }

    public int getLayoutId() {
        return R.id.moreinfo_location_text_layout;
    }

    public void onViewInflate(ViewStub viewStub, View view) {
        super.onViewInflate(viewStub, view);
        if (this.mDetailsView.getLocationDim()) {
            view.setAlpha(0.6f);
        } else {
            view.setOnClickListener(new g(4, this));
        }
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.ADDRESS, new y(this, 0));
        this.mViewUpdaterMap.put(DetailsUpdateKey.POI, new y(this, 1));
        this.mViewUpdaterMap.put(DetailsUpdateKey.NO_LOCATION, new x(1));
    }

    public boolean supportItem(MediaItem mediaItem) {
        return MapUtil.isValidLocation(mediaItem.getLatitude(), mediaItem.getLongitude());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$registerViewUpdater$1(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
    }
}
