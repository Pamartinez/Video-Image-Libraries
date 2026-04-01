package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.menu;

import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.sharing.ChooseSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.story.AddContentsToStoryCmd;
import com.samsung.android.gallery.app.controller.story.RemoveFromStoryDialogCmd;
import com.samsung.android.gallery.app.ui.menu.list.ListMenuHandler;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightListV2MenuHandler extends ListMenuHandler {
    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_add_content_to_story:
                new AddContentsToStoryCmd().execute(eventContext, new Object[0]);
                break;
            case R.id.action_add_items_to_shared_album_in_list:
                new ChooseSharedAlbumCmd().execute(eventContext, eventContext.getAllItems());
                break;
            case R.id.action_add_items_to_shared_album_on_selection:
                postEvent(eventContext, EventMessage.obtain(1129));
                break;
            case R.id.action_done:
                postEvent(eventContext, EventMessage.obtain(1126));
                break;
            case R.id.action_edit:
                postEvent(eventContext, EventMessage.obtain(1002));
                break;
            case R.id.action_enter_remove_from_story:
                postEvent(eventContext, EventMessage.obtain(1125));
                break;
            case R.id.action_enter_show_hide_contents:
                postEvent(eventContext, EventMessage.obtain(1124));
                break;
            case R.id.action_remove_from_story_in_list:
                new RemoveFromStoryDialogCmd().execute(eventContext, eventContext.getHeaderItem(), null, Boolean.TRUE);
                break;
            case R.id.action_share_items:
                eventContext.prepareExtendedShare();
                new ShareViaCmd().execute(eventContext, eventContext.getSelectedItems(), null);
                break;
        }
        return super.onItemSelected(eventContext, menuItem);
    }
}
