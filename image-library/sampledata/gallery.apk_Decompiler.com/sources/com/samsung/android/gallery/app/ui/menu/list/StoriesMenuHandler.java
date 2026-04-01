package com.samsung.android.gallery.app.ui.menu.list;

import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.story.ChangeStoryCoverCmd;
import com.samsung.android.gallery.app.controller.story.CreateStoryWithPickCmd;
import com.samsung.android.gallery.app.controller.story.DeleteStoryCmd;
import com.samsung.android.gallery.app.controller.story.LaunchOnDemandStoryCmd;
import com.samsung.android.gallery.app.controller.story.RenameStoryCmd;
import com.samsung.android.gallery.app.controller.story.StoriesPinCmd;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoriesMenuHandler extends MenuHandler {
    private MediaItem getItem(EventContext eventContext) {
        if (PreferenceFeatures.OneUi40.SUPPORT_MEMORY_COVER_ACTION_ON_SELECTION_MODE) {
            return getSelectedItem(eventContext);
        }
        return eventContext.getCurrentItem();
    }

    private MediaItem getSelectedItem(EventContext eventContext) {
        if (eventContext.getSelectedItems().length > 0) {
            return eventContext.getSelectedItems()[0];
        }
        return null;
    }

    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_change_cover:
                new ChangeStoryCoverCmd().execute(eventContext, getSelectedItem(eventContext), Boolean.TRUE);
                return true;
            case R.id.action_create_story_album:
                new CreateStoryWithPickCmd().execute(eventContext, new Object[0]);
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_delete_story:
                eventContext.getBlackboard().publish("data://stories/current", eventContext.getCurrentItem());
                new DeleteStoryCmd().execute(eventContext, new MediaItem[]{eventContext.getCurrentItem()});
                return true;
            case R.id.action_delete_story_album_in_list:
                new DeleteStoryCmd().execute(eventContext, eventContext.getSelectedItems());
                return true;
            case R.id.action_favorite_view:
                moveTo(eventContext, "location://stories/favorite");
                return true;
            case R.id.action_hide_content:
                moveTo(eventContext, "location://stories/hideRule");
                return true;
            case R.id.action_on_demand:
                new LaunchOnDemandStoryCmd().execute(eventContext, null, 1143);
                return true;
            case R.id.action_pin:
                new StoriesPinCmd().execute(eventContext, eventContext.getSelectedItems(), Boolean.TRUE, null);
                return true;
            case R.id.action_rename:
            case R.id.action_rename_story_album_in_list:
                new RenameStoryCmd().execute(eventContext, getItem(eventContext));
                return true;
            case R.id.action_save_cover:
                postEvent(eventContext, EventMessage.obtain(1080, getItem(eventContext)));
                return true;
            case R.id.action_share_cover:
                postEvent(eventContext, EventMessage.obtain(1081, getItem(eventContext)));
                return true;
            case R.id.action_share_story_in_list:
                return true;
            default:
                return false;
        }
    }
}
