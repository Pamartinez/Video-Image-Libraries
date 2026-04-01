package com.samsung.android.gallery.app.controller.externals;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartBrowserByPathCmd extends StartBrowserCmd {
    public String getAnalyticsDetail() {
        return null;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_GO_TO_URL_BY_PATH.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        startBrowser(getContext(), DetailsData.of(objArr[0]).capturedPath, (String) null);
    }
}
