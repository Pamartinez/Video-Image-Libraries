package com.samsung.android.gallery.app.ui.menu.list;

import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.sharing.ChooseSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.story.SaveRecapVideoCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryRecapMenuHandler extends StoryPicturesMenuHandler {
    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_add_to_shared_album) {
            new ChooseSharedAlbumCmd().execute(eventContext, new MediaItem[]{eventContext.getHeaderItem()});
            return true;
        } else if (itemId == R.id.action_save_story) {
            new SaveRecapVideoCmd().execute(eventContext, eventContext.getHeaderItem(), Boolean.TRUE);
            return true;
        } else if (itemId != R.id.action_share_menu) {
            return super.onItemSelected(eventContext, menuItem);
        } else {
            eventContext.prepareExtendedShare();
            new ShareViaCmd().execute(eventContext, new MediaItem[]{eventContext.getHeaderItem()}, null);
            eventContext.postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_SHARE);
            return true;
        }
    }
}
