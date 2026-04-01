package com.samsung.android.gallery.widget.search.searchbar;

import android.view.KeyEvent;
import android.view.WindowInsets;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SearchToolbarBehavior {
    boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return false;
    }

    Object getData(SearchToolbar searchToolbar, SearchToolbarDataKey searchToolbarDataKey) {
        return null;
    }

    boolean handleEvent(SearchToolbar searchToolbar, EventMessage eventMessage) {
        return false;
    }

    boolean handleInternalEvent(SearchToolbar searchToolbar, SearchToolbarEvent searchToolbarEvent);

    boolean isViewOnResumed();

    void postAnalyticsLog(AnalyticsEventId analyticsEventId);

    void updateToolbar(SearchToolbar searchToolbar, String str);

    void handleDensityChange() {
    }

    void initMenu(SearchToolbar searchToolbar) {
    }

    void onClickCloseButton(SearchToolbar searchToolbar) {
    }

    void onDestroy(SearchToolbar searchToolbar) {
    }

    void onFirstDraw(SearchToolbar searchToolbar) {
    }

    void onInsetsPrepare(SearchToolbar searchToolbar) {
    }

    void onPreQuery(SearchToolbar searchToolbar) {
    }

    void onResume(SearchToolbar searchToolbar) {
    }

    void onSearchToolbarVisibilityChanged(boolean z) {
    }

    void handleQuery(String str, boolean z) {
    }

    void onApplyInsets(SearchToolbar searchToolbar, WindowInsets windowInsets) {
    }

    void onAttached(SearchToolbar searchToolbar, SearchToolbarOptions searchToolbarOptions) {
    }

    void onFocusChanged(SearchToolbar searchToolbar, boolean z) {
    }

    void onHiddenChange(SearchToolbar searchToolbar, boolean z) {
    }

    void onInsetsProgress(SearchToolbar searchToolbar, WindowInsets windowInsets) {
    }

    void onQueryTextChanged(SearchToolbar searchToolbar, String str) {
    }
}
