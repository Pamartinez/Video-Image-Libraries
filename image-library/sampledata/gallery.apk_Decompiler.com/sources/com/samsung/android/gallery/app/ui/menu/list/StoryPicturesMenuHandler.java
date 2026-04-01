package com.samsung.android.gallery.app.ui.menu.list;

import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.internals.DeleteOriginalCmd;
import com.samsung.android.gallery.app.controller.sharing.ChooseSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.story.AddContentsToStoryCmd;
import com.samsung.android.gallery.app.controller.story.ChangeStoryCoverCmd;
import com.samsung.android.gallery.app.controller.story.DeleteStoryCmd;
import com.samsung.android.gallery.app.controller.story.RemoveFromStoryCmd;
import com.samsung.android.gallery.app.controller.story.RenameStoryCmd;
import com.samsung.android.gallery.app.controller.story.StartHighlightPlayerCmd;
import com.samsung.android.gallery.app.controller.story.StoryPicturesPinCmd;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryPicturesMenuHandler extends MenuHandler {
    private MediaItem getStoryMediaItem(EventContext eventContext) {
        return eventContext.getHeaderItem();
    }

    private void startAddToContent(EventContext eventContext) {
        new AddContentsToStoryCmd().execute(eventContext, new Object[0]);
    }

    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_add_content_to_story:
                startAddToContent(eventContext);
                return true;
            case R.id.action_add_to_shared_album:
                new ChooseSharedAlbumCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_change_cover:
            case R.id.action_change_story_cover_image:
                new ChangeStoryCoverCmd().execute(eventContext, eventContext.getHeaderItem(), Boolean.TRUE);
                return true;
            case R.id.action_create_highlight_reel_story:
                new StartHighlightPlayerCmd().execute(eventContext, Integer.valueOf(MediaItemStory.getStoryId(eventContext.getHeaderItem())));
                return true;
            case R.id.action_delete:
                new DeleteOriginalCmd().execute(eventContext, eventContext.getSelectedItems());
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_delete_story:
                new DeleteStoryCmd().execute(eventContext, new MediaItem[]{eventContext.getHeaderItem()});
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_pintotop:
                new StoryPicturesPinCmd().execute(eventContext, new MediaItem[]{eventContext.getHeaderItem()}, Boolean.TRUE, null);
                return true;
            case R.id.action_remove_from_story:
                new RemoveFromStoryCmd().execute(eventContext, getStoryMediaItem(eventContext));
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_rename:
                new RenameStoryCmd().execute(eventContext, eventContext.getHeaderItem());
                return true;
            case R.id.action_rename_story_album:
                new RenameStoryCmd().execute(eventContext, getStoryMediaItem(eventContext));
                return true;
            case R.id.action_select:
                postEvent(eventContext, EventMessage.obtain(1002));
                return true;
            case R.id.action_share:
                eventContext.prepareExtendedShare();
                new ShareViaCmd().execute(eventContext, eventContext.getSelectedItems(), null);
                return true;
            case R.id.action_unpin:
                new StoryPicturesPinCmd().execute(eventContext, new MediaItem[]{eventContext.getHeaderItem()}, Boolean.FALSE, null);
                return true;
            default:
                return false;
        }
    }
}
