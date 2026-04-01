package com.samsung.android.gallery.app.ui.viewer2.crop.handler;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CropHelper {
    public static void fixWrongOrientation(MediaItem mediaItem, Bitmap bitmap) {
        boolean z;
        if (bitmap != null && mediaItem != null && mediaItem.isVideo()) {
            boolean z3 = false;
            if (bitmap.getWidth() >= bitmap.getHeight()) {
                z = true;
            } else {
                z = false;
            }
            if (mediaItem.getWidth() >= mediaItem.getHeight()) {
                z3 = true;
            }
            if (z != z3) {
                Log.e("CropHelper", "fixWrongOrientation {" + bitmap.getWidth() + "x" + bitmap.getHeight() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.getWidth() + "x" + mediaItem.getHeight() + "}");
                mediaItem.setSize(bitmap.getWidth(), bitmap.getHeight());
            }
        }
    }

    public static String[] getCustomNavigationMenu(Context context, Bundle bundle) {
        boolean z;
        if (bundle == null) {
            return null;
        }
        String[] strArr = new String[2];
        boolean z3 = bundle.getBoolean("crop_custom_bottom_menu_title", false);
        int i2 = R.string.done;
        if (z3) {
            strArr[0] = bundle.getString("crop_menu_done", context.getString(R.string.done));
            strArr[1] = bundle.getString("crop_menu_cancel", context.getString(R.string.cancel));
            if (bundle.getBoolean("crop_custom_bottom_menu_remove_cancel", false)) {
                strArr[1] = null;
            }
            return strArr;
        }
        if (bundle.getBoolean("FromStoryCover", false) || bundle.getBoolean("FromSharedAlbumCover", false) || bundle.getBoolean("FromAlbumCover", false)) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            i2 = R.string.change_cover_apply;
        }
        strArr[0] = context.getString(i2);
        strArr[1] = context.getString(R.string.cancel);
        return strArr;
    }

    public static int getErrorResult(Bitmap bitmap, boolean z) {
        if (bitmap == null) {
            if (z) {
                return R.string.fail_to_set_profile_picture;
            }
            return R.string.fail_to_load;
        } else if (Math.min(bitmap.getWidth(), bitmap.getHeight()) <= 16) {
            return R.string.image_too_small;
        } else {
            return 0;
        }
    }

    public static MediaItem mutateMediaItem(MediaItem mediaItem) {
        if (mediaItem.isVideo()) {
            mediaItem = mediaItem.clone();
            if (MediaHelper.isVertical(mediaItem.getOrientation())) {
                mediaItem.setSize(mediaItem.getHeight(), mediaItem.getWidth());
            }
            mediaItem.setOrientation(0);
        }
        return mediaItem;
    }
}
