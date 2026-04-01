package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartBrowserCmd;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsDataRefiner;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItemCaptureUrl extends DetailsItem implements DetailsViewUpdater {
    public DetailsItemCaptureUrl(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewInflate$0(View view) {
        StartBrowserCmd startBrowserCmd = new StartBrowserCmd();
        EventContext eventContext = this.mEventContext;
        startBrowserCmd.execute(eventContext, eventContext.getCurrentItem());
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_SELECT_CAPTURED_FROM);
    }

    public int getLayoutId() {
        return R.id.moreinfo_captured_url;
    }

    public void onViewInflate(ViewStub viewStub, View view) {
        getView(view, (int) R.id.moreinfo_url_detail).setOnClickListener(new g(0, this));
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.DEFAULT, this);
        this.mViewUpdaterMap.put(DetailsUpdateKey.CAPTURED_URL, this);
    }

    public boolean supportItem(MediaItem mediaItem) {
        if (mediaItem.isSharing() || !mediaItem.isImage()) {
            return false;
        }
        DetailsData of2 = DetailsData.of(mediaItem);
        if (TextUtils.isEmpty(of2.capturedUrl) || !StartBrowserCmd.supportAppLink(of2.capturedApp, of2.capturedUrl)) {
            return false;
        }
        return true;
    }

    public void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        setTextAndVisibility((int) R.id.moreinfo_url_detail, (CharSequence) DetailsDataRefiner.getCapturedUrlText(mediaItem));
    }
}
