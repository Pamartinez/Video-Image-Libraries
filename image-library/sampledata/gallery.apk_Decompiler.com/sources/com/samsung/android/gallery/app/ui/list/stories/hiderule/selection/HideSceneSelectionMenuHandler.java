package com.samsung.android.gallery.app.ui.list.stories.hiderule.selection;

import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideSceneSelectionMenuHandler extends MenuHandler {
    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_save) {
            return false;
        }
        postEvent(eventContext, EventMessage.obtain(1085));
        return true;
    }
}
