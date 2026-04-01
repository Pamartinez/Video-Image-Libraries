package com.samsung.android.gallery.app.ui.dialog;

import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenameSharedFamilyAlbumDialog extends RenameSharedAlbumDialog {
    public String getDataRequestKey() {
        return "data://user/dialog/SharedFamilyAlbumRename";
    }

    public String getTitle() {
        return getResources().getString(R.string.rename_shared_family_album);
    }
}
