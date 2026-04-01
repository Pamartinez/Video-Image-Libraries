package com.samsung.android.gallery.app.ui.menu.picker;

import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.CreateAlbumCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumPickerMenuHandler extends PickerMenuHandler {
    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_create) {
            return super.onItemSelected(eventContext, menuItem);
        }
        if (LocationKey.isFolder(eventContext.getLocationKey())) {
            MediaItem currentItem = eventContext.getCurrentItem();
            if (currentItem == null) {
                return true;
            }
            new CreateAlbumCmd().execute(eventContext, Integer.valueOf(currentItem.getFolderID()), currentItem.getFolderName(), Boolean.TRUE);
            return true;
        }
        new CreateAlbumCmd().execute(eventContext, 0, null, Boolean.TRUE);
        return true;
    }
}
