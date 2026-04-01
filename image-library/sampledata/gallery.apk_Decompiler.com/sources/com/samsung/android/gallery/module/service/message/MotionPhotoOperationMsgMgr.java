package com.samsung.android.gallery.module.service.message;

import com.samsung.android.gallery.module.R$plurals;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.utils.AppResources;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MotionPhotoOperationMsgMgr {
    public static String getChannelName(int i2, int i7) {
        return getNotificationMessage(i2, i7);
    }

    private static String getFailedDescription(int i2, int i7) {
        int i8;
        int i10;
        if (i7 == 0) {
            if (i2 == 1) {
                i10 = R$string.motion_photo_clip_delete_failed;
            } else {
                i10 = R$string.motion_photos_clips_delete_failed;
            }
            return AppResources.getString(i10);
        } else if (i7 != 1) {
            return "failed";
        } else {
            if (i2 == 1) {
                i8 = R$string.motion_photo_clip_export_failed;
            } else {
                i8 = R$string.motion_photos_clips_export_failed;
            }
            return AppResources.getString(i8);
        }
    }

    public static String getNotificationMessage(int i2, int i7) {
        int i8;
        int i10;
        if (i7 == 0) {
            if (i2 == 1) {
                i10 = R$string.delete_video_clip;
            } else {
                i10 = R$string.delete_video_clips_option;
            }
            return AppResources.getString(i10);
        } else if (i7 != 1) {
            return "operate";
        } else {
            if (i2 == 1) {
                i8 = R$string.export_video_clip_option;
            } else {
                i8 = R$string.export_video_clips_option;
            }
            return AppResources.getString(i8);
        }
    }

    public static String getResultDescription(int i2, int i7, int i8, boolean z) {
        if (z) {
            return AppResources.getString(R$string.save_canceled);
        }
        if (i8 == 0) {
            return getFailedDescription(i7, i2);
        }
        return getSuccessDescription(i8, i2);
    }

    private static String getSuccessDescription(int i2, int i7) {
        int i8;
        if (i7 == 0) {
            if (i2 == 1) {
                i8 = R$string.deleting_video_from_motion_photo;
            } else {
                i8 = R$string.deleting_video_from_motion_photos;
            }
            return AppResources.getString(i8);
        } else if (i7 == 1) {
            return AppResources.getQuantityString(R$plurals.motion_photo_clips_export_video_n_saved, i2, Integer.valueOf(i2));
        } else {
            return "success";
        }
    }
}
