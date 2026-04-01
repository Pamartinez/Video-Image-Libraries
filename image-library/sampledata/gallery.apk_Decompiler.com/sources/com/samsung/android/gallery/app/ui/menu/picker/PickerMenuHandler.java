package com.samsung.android.gallery.app.ui.menu.picker;

import android.os.Bundle;
import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.CreateAlbumCmd;
import com.samsung.android.gallery.app.controller.delegate.PickerDelegate;
import com.samsung.android.gallery.app.controller.internals.ViewAsCmd;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.abstraction.CoverPickType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PickerMenuHandler extends MenuHandler {
    private void handleDone(EventContext eventContext) {
        MediaItem[] selectedItems = eventContext.getSelectedItems();
        if (selectedItems != null && selectedItems.length > 0) {
            eventContext.postAnalyticsLog(AnalyticsEventId.EVENT_DONE, (long) selectedItems.length);
            eventContext.getBlackboard().post("command://MultiplePickerItemsSelection", selectedItems);
        }
    }

    public Bundle getPickerBundle() {
        return ArgumentsUtil.getArgs(new UriBuilder("data://user/pick/SingleItem").appendArg("crop", true).appendArg("is-get-rect-result", true).appendArg("FromAlbumCover", true).appendArg("KEY_COVER_PICKER_TYPE", CoverPickType.NONE.toInt()).build());
    }

    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_all_album:
                PickerDelegate.startPicker(eventContext.getActivity(), getPickerBundle());
                return true;
            case R.id.action_create_albums:
                new CreateAlbumCmd().execute(eventContext, 0, null, Boolean.TRUE);
                return true;
            case R.id.action_done:
                handleDone(eventContext);
                return true;
            case R.id.action_search:
                moveTo(eventContext, "location://search/fileList/Recommendation");
                return true;
            case R.id.action_view_as:
                new ViewAsCmd().execute(eventContext, new Object[0]);
                return true;
            default:
                return false;
        }
    }
}
