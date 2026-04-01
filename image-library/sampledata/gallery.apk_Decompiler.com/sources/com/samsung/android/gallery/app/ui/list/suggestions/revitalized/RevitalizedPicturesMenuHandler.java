package com.samsung.android.gallery.app.ui.list.suggestions.revitalized;

import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.RemoveRemasteredImageCmd;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RevitalizedPicturesMenuHandler extends MenuHandler {
    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_change_view) {
            postEvent(eventContext, EventMessage.obtain(1111));
            return true;
        } else if (itemId == R.id.action_remove_from_suggestions) {
            new RemoveRemasteredImageCmd().execute(eventContext, eventContext.getSelectedItems());
            return true;
        } else if (itemId != R.id.action_select) {
            return false;
        } else {
            postEvent(eventContext, EventMessage.obtain(1002));
            return true;
        }
    }
}
