package com.samsung.android.gallery.app.controller.album;

import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.FileOpCmdHelper;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenameAlbumCmd extends BaseCommand {
    private MediaItem getCurrentAlbum(EventContext eventContext) {
        MediaItem[] selectedItems = eventContext.getSelectedItems();
        if (selectedItems == null || selectedItems.length != 1) {
            return null;
        }
        return selectedItems[0];
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem currentAlbum = getCurrentAlbum(eventContext);
        if (currentAlbum != null) {
            if (currentAlbum.isFolder()) {
                new RenameFolderCmd().execute(eventContext, currentAlbum);
            } else if (currentAlbum.isAutoAlbum()) {
                new RenameAutoAlbumCmd().execute(eventContext, currentAlbum);
            } else {
                new FileOpCmd().execute(eventContext, FileOpCmdHelper.CmdType.TYPE_RENAME_ALBUM, currentAlbum);
            }
        }
    }
}
