package com.samsung.android.gallery.support.utils;

import A.a;
import Sd.x;
import com.samsung.android.gallery.support.R$plurals;
import com.samsung.android.gallery.support.R$string;
import com.samsung.android.gallery.support.config.SdkConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StringResources {
    public static int getBurstShotStringId(int i2) {
        if (!SdkConfig.atLeast(SdkConfig.SEM.U)) {
            return i2;
        }
        if (i2 == R$string.burst_shot) {
            return R$string.burst_shot_m_trans;
        }
        if (i2 == R$string.delete_one_burst_shot) {
            return R$string.delete_one_burst_shot_m_trans;
        }
        if (i2 == R$string.delete_n_burst_shots) {
            return R$string.delete_n_burst_shots_m_trans;
        }
        if (i2 == R$plurals.move_burst_shot_to_the_trash_plural) {
            return R$plurals.move_burst_shot_to_the_trash_plural_m_trans;
        }
        if (i2 == R$plurals.move_image_in_burst_shot_to_the_trash_plural) {
            return R$plurals.move_image_in_burst_shot_to_the_trash_plural_m_trans;
        }
        if (i2 == R$string.also_move_the_rest_of_the_burst_shot) {
            return R$string.also_move_the_rest_of_the_burst_shot_m_trans;
        }
        if (i2 == R$string.also_delete_the_rest_of_the_burst_shot) {
            return R$string.also_delete_the_rest_of_the_burst_shot_m_trans;
        }
        if (i2 == R$string.camera_capture_mode_burst_shot) {
            return R$string.camera_capture_mode_burst_shot_m_trans;
        }
        Log.e("StringResources", "There is no matching ID");
        return i2;
    }

    public static String getCloudBrand() {
        int i2;
        if (!Features.isEnabled(Features.SUPPORT_NEW_SAMSUNG_CLOUD)) {
            return AppResources.getString(R$string.one_drive);
        }
        GalleryPreference instanceCache = GalleryPreference.getInstanceCache();
        x xVar = x.LegacyServiceStatusRequired;
        int loadInt = instanceCache.loadInt("cloud_service_available", xVar.ordinal());
        if (loadInt == xVar.ordinal() || loadInt == x.MigrationAvailable.ordinal() || loadInt == x.OneDriveAvailable.ordinal() || loadInt == x.OneDriveLinkRequired.ordinal()) {
            i2 = R$string.one_drive;
        } else {
            i2 = R$string.samsung_cloud;
        }
        return AppResources.getString(i2);
    }

    public static String getCountString(int i2, int i7) {
        if (i2 <= 0 && i7 <= 0) {
            return null;
        }
        return (getImageCountString(i2) + " " + getVideoCountString(i7)).trim();
    }

    public static String getImageCountString(int i2) {
        if (i2 == 0) {
            return "";
        }
        if (i2 <= 1) {
            return AppResources.getAppContext().getString(R$string.search_image);
        }
        try {
            return AppResources.getAppContext().getString(R$string.search_images, new Object[]{Integer.valueOf(i2)});
        } catch (Exception e) {
            a.s(e, new StringBuilder("getImageCountString failed e="), "StringResources");
            return "";
        }
    }

    public static String getVideoCountString(int i2) {
        if (i2 == 0) {
            return "";
        }
        if (i2 <= 1) {
            return AppResources.getAppContext().getString(R$string.search_video);
        }
        try {
            return AppResources.getAppContext().getString(R$string.search_videos, new Object[]{Integer.valueOf(i2)});
        } catch (Exception e) {
            a.s(e, new StringBuilder("getVideoCountString failed e="), "StringResources");
            return "";
        }
    }

    public static String getVideoEditorAppName() {
        if (Features.isEnabled(Features.SUPPORT_MULTI_VIDEO_EDIT)) {
            return AppResources.getString(R$string.video_studio_app_name);
        }
        if (SdkConfig.atLeast(SdkConfig.SEM.S)) {
            return AppResources.getString(R$string.video_editor);
        }
        return AppResources.getString(R$string.story_video_editor);
    }

    public static String getVideoTrimmerName() {
        if (SdkConfig.atLeast(SdkConfig.GED.S)) {
            return AppResources.getString(R$string.video_editor_lite);
        }
        return AppResources.getString(R$string.video_trimmer);
    }
}
