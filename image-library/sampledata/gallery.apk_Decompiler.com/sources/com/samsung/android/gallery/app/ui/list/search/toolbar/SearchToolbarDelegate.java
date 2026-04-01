package com.samsung.android.gallery.app.ui.list.search.toolbar;

import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarDataKey;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SearchToolbarDelegate {
    void clearFocus();

    Object getData(SearchToolbarDataKey searchToolbarDataKey) {
        return null;
    }

    int getDimAreaHeight();

    void handleDensityChange();

    boolean handleEvent(EventMessage eventMessage);

    boolean handleInternalEvent(SearchToolbarEvent searchToolbarEvent);

    boolean hasFocus();

    boolean isEmpty() {
        return false;
    }

    boolean isVisible();

    void onDestroy();

    void onResume();

    void initMoreOptions(SearchToolbar.OptionMenuListener optionMenuListener) {
    }

    void query(String str, boolean z) {
    }
}
