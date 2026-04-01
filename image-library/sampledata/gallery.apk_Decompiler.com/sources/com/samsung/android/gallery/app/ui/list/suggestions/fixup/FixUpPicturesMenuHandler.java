package com.samsung.android.gallery.app.ui.list.suggestions.fixup;

import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.RemoveHighlightCmd;
import com.samsung.android.gallery.app.controller.internals.RemovePortraitCmd;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FixUpPicturesMenuHandler extends MenuHandler {
    private void removeSuggestion(EventContext eventContext) {
        if (LocationKey.isHighLightPictures(eventContext.getLocationKey())) {
            new RemoveHighlightCmd().execute(eventContext, eventContext.getSelectedItems());
        } else {
            new RemovePortraitCmd().execute(eventContext, eventContext.getSelectedItems());
        }
    }

    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_remove_from_suggestions) {
            removeSuggestion(eventContext);
            return true;
        } else if (itemId != R.id.action_select) {
            return false;
        } else {
            eventContext.postAnalyticsLog(AnalyticsEventId.EVENT_SUGGEST_EDIT);
            postEvent(eventContext, EventMessage.obtain(1002));
            return true;
        }
    }
}
