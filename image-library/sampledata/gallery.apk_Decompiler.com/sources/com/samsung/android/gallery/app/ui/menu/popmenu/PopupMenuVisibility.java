package com.samsung.android.gallery.app.ui.menu.popmenu;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PopupMenuVisibility {
    private static boolean isAccountHasDeletePermission(MediaItem mediaItem) {
        return isOwnedByMe(mediaItem);
    }

    public static boolean isActiveAlbumRename(MediaItem mediaItem) {
        if (BucketUtils.isSystem(mediaItem.getAlbumID()) || BucketUtils.isVirtualAlbum(mediaItem.getAlbumID())) {
            return false;
        }
        return true;
    }

    public static boolean isActiveSharedAlbumChangeCover(MediaItem mediaItem) {
        return isOwnedByMe(mediaItem);
    }

    public static boolean isActiveSharedAlbumDelete(MediaItem mediaItem) {
        return isAccountHasDeletePermission(mediaItem);
    }

    public static boolean isActiveSharedAlbumLeave(MediaItem mediaItem) {
        if (isOwnedByMe(mediaItem) || isFamilyAlbum(mediaItem)) {
            return false;
        }
        return true;
    }

    public static boolean isActiveSharedAlbumRename(MediaItem mediaItem) {
        if (isOwnedByMe(mediaItem) || isFamilyAlbum(mediaItem)) {
            return true;
        }
        return false;
    }

    private static boolean isFamilyAlbum(MediaItem mediaItem) {
        if (mediaItem == null || !MdeGroupHelper.isSAFamilyGroup(MediaItemMde.getGroupId(mediaItem))) {
            return false;
        }
        return true;
    }

    private static boolean isOwnedByMe(MediaItem mediaItem) {
        if (mediaItem == null || !MediaItemMde.isOwnedByMe(mediaItem)) {
            return false;
        }
        return true;
    }

    public static boolean isShortcutAlbum(String str) {
        return ArgumentsUtil.getArgValue(str, "shortcut_album", false);
    }
}
