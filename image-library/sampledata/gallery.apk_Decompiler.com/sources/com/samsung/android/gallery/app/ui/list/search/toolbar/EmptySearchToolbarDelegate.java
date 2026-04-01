package com.samsung.android.gallery.app.ui.list.search.toolbar;

import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EmptySearchToolbarDelegate implements SearchToolbarDelegate {
    public int getDimAreaHeight() {
        return 0;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        return false;
    }

    public boolean handleInternalEvent(SearchToolbarEvent searchToolbarEvent) {
        return false;
    }

    public boolean hasFocus() {
        return false;
    }

    public boolean isEmpty() {
        return true;
    }

    public boolean isVisible() {
        return false;
    }

    public void clearFocus() {
    }

    public void handleDensityChange() {
    }

    public void onDestroy() {
    }

    public void onResume() {
    }
}
