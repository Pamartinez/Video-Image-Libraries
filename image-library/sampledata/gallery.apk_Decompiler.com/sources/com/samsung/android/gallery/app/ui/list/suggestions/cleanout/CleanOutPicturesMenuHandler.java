package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.DeleteFromCleanOutCmd;
import com.samsung.android.gallery.app.controller.internals.KeepCleanOutCmd;
import com.samsung.android.gallery.app.controller.internals.MotionPhotoDeleteVideoCmd;
import com.samsung.android.gallery.app.controller.internals.ViewAsCmd;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CleanOutPicturesMenuHandler extends MenuHandler {
    private void handleCancel(EventContext eventContext) {
        ThreadUtil.postOnUiThreadDelayed(new c(eventContext), 100);
    }

    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_cancel:
                handleCancel(eventContext);
                return true;
            case R.id.action_clean_contents:
                new DeleteFromCleanOutCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_delete_motion_photo_clip:
                new MotionPhotoDeleteVideoCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_keep_contents:
            case R.id.action_remove_from_suggestions:
                new KeepCleanOutCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_select:
                eventContext.postAnalyticsLog(AnalyticsEventId.EVENT_SUGGEST_EDIT);
                postEvent(eventContext, EventMessage.obtain(1002));
                return true;
            case R.id.action_view_as:
                new ViewAsCmd().execute(eventContext, new Object[0]);
                return true;
            default:
                return false;
        }
    }
}
