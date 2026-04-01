package com.samsung.android.gallery.app.ui.list.search.toolbar;

import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarBehavior;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseSearchToolbarBehavior implements SearchToolbarBehavior {
    private final IMvpBaseView mView;

    public BaseSearchToolbarBehavior(IMvpBaseView iMvpBaseView) {
        this.mView = iMvpBaseView;
    }

    public boolean isViewOnResumed() {
        return this.mView.isViewResumed();
    }

    public final void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
        this.mView.postAnalyticsLog(analyticsEventId);
    }
}
