package com.samsung.android.gallery.app.ui.menu.list;

import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.story.ChangeStoryCoverCmd;
import com.samsung.android.gallery.app.controller.story.DownloadAllBgmCmd;
import com.samsung.android.gallery.app.controller.story.RecapCmd;
import com.samsung.android.gallery.app.controller.story.RemoveFromStoryCmd;
import com.samsung.android.gallery.app.controller.story.SaveManageContentsCmd;
import com.samsung.android.gallery.app.controller.story.UpdateStoryFavoriteCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.ExportType;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightMenuHandler extends StoryPicturesMenuHandler {
    private MediaItem getCurrentItem(EventContext eventContext) {
        return eventContext.getCurrentItem();
    }

    private MediaItem getHeaderItem(EventContext eventContext) {
        return eventContext.getHeaderItem();
    }

    private void postSharingEventLog(EventContext eventContext) {
        eventContext.postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_SHARE_CONTENTS, String.valueOf(MediaItemStory.getStorySaType(eventContext.getHeaderItem())));
    }

    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_add_to_favorite:
            case R.id.action_remove_from_favorite:
                new UpdateStoryFavoriteCmd().execute(eventContext, new MediaItem[]{getHeaderItem(eventContext)}, 0);
                return true;
            case R.id.action_add_to_shared_album:
                if (PreferenceFeatures.OneUi6x.SUPPORT_SHARE_STORY) {
                    postEvent(eventContext, EventMessage.obtain(1129));
                    return true;
                }
                postEvent(eventContext, EventMessage.obtain(1091, ExportType.ADD_TO_SHARED_ALBUM));
                return true;
            case R.id.action_change_cover:
                new ChangeStoryCoverCmd().execute(eventContext, eventContext.getHeaderItem(), Boolean.TRUE, Boolean.valueOf(Features.isEnabled(Features.SUPPORT_STORY_REORDER)));
                return true;
            case R.id.action_create_highlight_reel_story:
                postEvent(eventContext, EventMessage.obtain(1094));
                return true;
            case R.id.action_download_all:
                new DownloadAllBgmCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_export_options:
                postEvent(eventContext, EventMessage.obtain(1122));
                return true;
            case R.id.action_hide_from_story:
                new SaveManageContentsCmd().execute(eventContext, null, new MediaItem[]{getCurrentItem(eventContext)}, null);
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_remove_from_story:
                new RemoveFromStoryCmd().execute(eventContext, getHeaderItem(eventContext));
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_save_story:
                postEvent(eventContext, EventMessage.obtain(1091, ExportType.SAVE));
                return true;
            case R.id.action_share_items:
            case R.id.action_sub_share_items:
                postEvent(eventContext, EventMessage.obtain(1090));
                int itemId = menuItem.getItemId();
                int i2 = R.id.action_share_items;
                if (itemId != R.id.action_share_items) {
                    i2 = R.id.action_share_menu;
                }
                publishPopoverToolbarInfo(eventContext, i2);
                postSharingEventLog(eventContext);
                return true;
            case R.id.action_share_menu:
                eventContext.postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_SHARE);
                return super.onItemSelected(eventContext, menuItem);
            case R.id.action_share_story:
            case R.id.action_sub_share_story_video:
                postEvent(eventContext, EventMessage.obtain(1091, ExportType.SHARE));
                return true;
            case R.id.action_sound_picker:
                postEvent(eventContext, EventMessage.obtain(1120));
                return true;
            case R.id.action_story_recap:
                new RecapCmd().addParameter("slide_show_recap", "1").execute(eventContext, eventContext.getAllItems());
                return true;
            case R.id.action_view_original_picture:
                postEvent(eventContext, EventMessage.obtain(1128));
                return true;
            default:
                return super.onItemSelected(eventContext, menuItem);
        }
    }
}
