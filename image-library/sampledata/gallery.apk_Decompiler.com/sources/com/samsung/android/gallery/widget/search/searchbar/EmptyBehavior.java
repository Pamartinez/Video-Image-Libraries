package com.samsung.android.gallery.widget.search.searchbar;

import com.samsung.android.gallery.support.analytics.AnalyticsEventId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class EmptyBehavior implements SearchToolbarBehavior {
    public boolean handleInternalEvent(SearchToolbar searchToolbar, SearchToolbarEvent searchToolbarEvent) {
        return false;
    }

    public boolean isViewOnResumed() {
        return false;
    }

    public void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
    }

    public void updateToolbar(SearchToolbar searchToolbar, String str) {
    }
}
