package com.samsung.android.gallery.widget.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.dynamicview.a;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.nondestruction.NonDestructionManager;
import com.samsung.android.gallery.module.thumbnail.IconResources;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.library.sef.SingleTakeType;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import com.samsung.android.gallery.support.shotmode.ShotModeList;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$drawable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ResourceUtil {
    public static final int[] BG_GRADIENT = {AppResources.getColor(R$color.stories_category_on_demand_bg_gradient_point_1_color), AppResources.getColor(R$color.stories_category_on_demand_bg_gradient_point_2_color), AppResources.getColor(R$color.stories_category_on_demand_bg_gradient_point_3_color), AppResources.getColor(R$color.stories_category_on_demand_bg_gradient_point_4_color)};
    public static final int[] BG_ON_DEMAND_TOP_GRADIENT = {AppResources.getColor(R$color.stories_category_on_demand_top_bg_gradient_point_1_color), AppResources.getColor(R$color.stories_category_on_demand_top_bg_gradient_point_2_color), AppResources.getColor(R$color.stories_category_on_demand_top_bg_gradient_point_3_color), 0};
    private static final int DEFAULT_BROKEN_BG_COLOR = R$color.cloud_only_image_bg;
    private static final int DEFAULT_BROKEN_DRAWABLE = R$drawable.gallery_ic_timeview_error;
    public static final Typeface SEC_400;
    public static final Typeface SEC_600;
    private static final Typeface SEC_FONT_BASE;

    static {
        Typeface create = Typeface.create("sec", 0);
        SEC_FONT_BASE = create;
        SEC_400 = Typeface.create(create, 400, false);
        SEC_600 = Typeface.create(create, 600, false);
    }

    public static Drawable getAlbumStorageIcon(Context context, MediaItem mediaItem) {
        if (!(context == null || mediaItem == null)) {
            int albumID = mediaItem.getAlbumID();
            if (mediaItem.isMergedAlbum()) {
                if (mediaItem.getItemCount() == 1) {
                    return getAlbumStorageIcon(context, mediaItem.getFirst());
                }
                if (mediaItem.getChildList().stream().allMatch(new a(29))) {
                    return IconResources.getIconDrawable(context, R$drawable.gallery_ic_album_thumbnail_sdcard);
                }
                if (PocFeatures.isEnabled(PocFeatures.ShowNameMergedAlbumIcon)) {
                    return IconResources.getIconDrawable(context, R$drawable.album_name_merged_icon);
                }
                return null;
            } else if (mediaItem.isVirtualAlbum()) {
                return null;
            } else {
                if (AlbumType.isAutoAlbum(mediaItem.getAlbumType())) {
                    return IconResources.getIconDrawable(context, R$drawable.gallery_ic_album_thumbnail_auto_updating);
                }
                if (BucketUtils.isCamera(albumID)) {
                    return IconResources.getIconDrawable(context, R$drawable.gallery_ic_album_thumbnail_camera);
                }
                if (BucketUtils.isSdCamera(albumID)) {
                    return IconResources.getIconDrawable(context, R$drawable.gallery_ic_album_thumbnail_camera_sdcard);
                }
                if (FileUtils.isInRemovableStorage(mediaItem.getDataPath())) {
                    return IconResources.getIconDrawable(context, R$drawable.gallery_ic_album_thumbnail_sdcard);
                }
                if (mediaItem.isSharing() && MdeGroupHelper.isSAFamilyGroup(MediaItemMde.getGroupId(mediaItem))) {
                    return IconResources.getIconDrawable(context, R$drawable.gallery_ic_album_thumbnail_family_shared_album);
                }
            }
        }
        return null;
    }

    public static int getBrokenBgColor(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isSharing()) {
            return DEFAULT_BROKEN_BG_COLOR;
        }
        return R$color.album_no_pic_background_color;
    }

    public static Drawable getBrokenDrawable(Context context, Bitmap bitmap) {
        return getBrokenDrawableWithExtraKey(context, bitmap, (String) null);
    }

    public static Drawable getBrokenDrawableWithExtraKey(Context context, Bitmap bitmap, String str) {
        String str2;
        if (str == null) {
            str2 = bitmap.toString();
        } else {
            str2 = bitmap.toString() + "/" + str;
        }
        return IconResources.getIconDrawable(context, bitmap, str2);
    }

    public static int getIconResourceId(MediaItem mediaItem) {
        ShotMode byRecMode;
        int i2;
        ShotMode byType = ShotModeList.getInstance().getByType(mediaItem.getGroupMode());
        if (byType != null) {
            return byType.iconRes;
        }
        ShotMode byType2 = ShotModeList.getInstance().getByType(mediaItem.getShotMode());
        if (byType2 != null && (i2 = byType2.iconRes) != -1) {
            return i2;
        }
        if (!mediaItem.isVideo()) {
            return 0;
        }
        if (!PreferenceFeatures.OneUi40.SUPPORT_NONDESTRUCTIVE_SLOW_MO || !MediaItemUtil.isNoneDestructionSlowRecordingMode(mediaItem.getRecordingMode()) || !NonDestructionManager.getInstance().hasData(mediaItem.getOriginalFileHash()) || (byRecMode = ShotModeList.getInstance().getByRecMode(mediaItem.getRecordingMode())) == null) {
            return R$drawable.gallery_ic_thumbnail_video;
        }
        return byRecMode.iconRes;
    }

    public static int getIconResourceIdForSingleTake(MediaItem mediaItem) {
        ShotMode byType = ShotModeList.getInstance().getByType(mediaItem.getShotMode());
        if (byType != null) {
            return byType.iconRes;
        }
        if (!mediaItem.isVideo()) {
            return 0;
        }
        String sefFileTypes = mediaItem.getSefFileTypes();
        if (!TextUtils.isEmpty(sefFileTypes)) {
            if (sefFileTypes.contains(String.valueOf(SingleTakeType.VideoFastForward.value))) {
                return R$drawable.gallery_ic_st_forward;
            }
            if (sefFileTypes.contains(String.valueOf(SingleTakeType.VideoBoomerang.value))) {
                return R$drawable.gallery_ic_st_loop;
            }
            if (sefFileTypes.contains(String.valueOf(SingleTakeType.VideoReverse.value))) {
                return R$drawable.gallery_ic_st_backward;
            }
            if (sefFileTypes.contains(String.valueOf(SingleTakeType.VideoHighlight.value))) {
                return R$drawable.gallery_ic_st_hightlight_video;
            }
            if (sefFileTypes.contains(String.valueOf(SingleTakeType.VideoSlowMo.value))) {
                return R$drawable.gallery_ic_st_dynamic_video;
            }
            if (sefFileTypes.contains(String.valueOf(SingleTakeType.Video24TimeLaps.value))) {
                return R$drawable.gallery_ic_st_timelapse;
            }
        }
        return R$drawable.gallery_ic_st_play;
    }

    public static Bitmap getRemoteBrokenBitmap(MediaItem mediaItem) {
        return IconResources.createBrokenIconBitmapLegacy(AppResources.getAppContext(), getBrokenDrawable(mediaItem), getBrokenBgColor(mediaItem), ThumbKind.MEDIUM_KIND.size());
    }

    public static int getStorageResourceId(MediaItem mediaItem) {
        if (Features.isEnabled(Features.IS_UPSM)) {
            return -1;
        }
        if (mediaItem.isCloudOnly()) {
            return R$drawable.gallery_ic_thumbnail_cloud_only;
        }
        if (mediaItem.getStorageType() == StorageType.LocalCloud) {
            return R$drawable.gallery_ic_thumbnail_cloud_and_device_item;
        }
        return -1;
    }

    public static int getBrokenDrawable(MediaItem mediaItem) {
        if (mediaItem != null) {
            if (mediaItem.isCloudOnly()) {
                return R$drawable.gallery_ic_timeview_cloud_only;
            }
            if (mediaItem.isSharing()) {
                return R$drawable.gallery_ic_album_no_pic;
            }
            if (mediaItem.isDrm() || TrashData.of(mediaItem).drm) {
                if (mediaItem.isVideo()) {
                    return R$drawable.gallery_ic_timeview_drm_video;
                }
                return R$drawable.gallery_ic_timeview_drm_image;
            }
        }
        return DEFAULT_BROKEN_DRAWABLE;
    }
}
