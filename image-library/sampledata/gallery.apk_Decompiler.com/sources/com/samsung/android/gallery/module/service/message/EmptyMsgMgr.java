package com.samsung.android.gallery.module.service.message;

import android.content.Context;
import android.content.res.Resources;
import com.samsung.android.gallery.module.R$plurals;
import com.samsung.android.gallery.module.R$string;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class EmptyMsgMgr {
    public static String getCloudToastMessage(Context context, int i2, int i7, int i8, boolean z) {
        if (i7 == i2) {
            Resources resources = context.getResources();
            if (z) {
                return resources.getQuantityString(R$plurals.go_to_samsung_cloud_image_plural, i2, new Object[]{Integer.valueOf(i2)});
            }
            return resources.getQuantityString(R$plurals.go_to_one_drive_image_plural, i2, new Object[]{Integer.valueOf(i2)});
        } else if (i8 == i2) {
            Resources resources2 = context.getResources();
            if (z) {
                return resources2.getQuantityString(R$plurals.go_to_samsung_cloud_video_plural, i2, new Object[]{Integer.valueOf(i2)});
            }
            return resources2.getQuantityString(R$plurals.go_to_one_drive_video_plural, i2, new Object[]{Integer.valueOf(i2)});
        } else {
            Resources resources3 = context.getResources();
            if (z) {
                return resources3.getQuantityString(R$plurals.go_to_samsung_cloud_item_plural, i2, new Object[]{Integer.valueOf(i2)});
            }
            return resources3.getQuantityString(R$plurals.go_to_one_drive_item_plural, i2, new Object[]{Integer.valueOf(i2)});
        }
    }

    public static String getSingleTitle(Context context, boolean z) {
        int i2;
        if (z) {
            i2 = R$string.permanently_delete_this_video;
        } else {
            i2 = R$string.permanently_delete_this_image;
        }
        return context.getString(i2);
    }

    public static String getTitle(Context context, int i2, int i7, int i8) {
        if (i7 == i2) {
            return context.getResources().getQuantityString(R$plurals.permanently_delete_images, i2, new Object[]{Integer.valueOf(i2)});
        }
        if (i8 == i2) {
            return context.getResources().getQuantityString(R$plurals.permanently_delete_videos, i2, new Object[]{Integer.valueOf(i2)});
        }
        return context.getResources().getQuantityString(R$plurals.permanently_delete_items, i2, new Object[]{Integer.valueOf(i2)});
    }
}
