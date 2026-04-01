package com.samsung.android.gallery.app.controller;

import android.util.Pair;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.abstraction.LaunchModeType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.logger.Analytics;
import com.samsung.android.gallery.support.analytics.AnalyticsDetailKey;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VuAnalytics {
    public static String getAnalyticsScreenId(Blackboard blackboard, String str, MediaItem mediaItem, boolean z, boolean z3, boolean z7, boolean z9) {
        Object read = blackboard.read("data://launch_mode_type");
        if (read != null && read != LaunchModeType.ACTION_NORMAL) {
            return AnalyticsScreenId.SCREEN_DETAIL_VIEW_PICK.toString();
        }
        if (mediaItem == null) {
            return null;
        }
        if (z) {
            return AnalyticsScreenId.SCREEN_DETAILS_NORMAL.toString();
        }
        if (LocationKey.isRemasterPictures(str)) {
            return AnalyticsScreenId.SCREEN_REMASTER_VIEWER.toString();
        }
        if (LocationKey.isRemasterSingle(str)) {
            long revitalizedResultType = MediaItemSuggest.getRevitalizedResultType(mediaItem);
            if (revitalizedResultType == 9) {
                return AnalyticsScreenId.SCREEN_DETAIL_VIEW_REMOVE_MOIRE.toString();
            }
            if (revitalizedResultType == 17) {
                return AnalyticsScreenId.SCREEN_DETAIL_VIEW_REMOVE_LENS_FLARE.toString();
            }
            if (revitalizedResultType == 15) {
                return AnalyticsScreenId.SCREEN_DETAIL_VIEW_FIX_LENS_DISTORTION.toString();
            }
            if (revitalizedResultType == 19) {
                return AnalyticsScreenId.SCREEN_DETAIL_VIEW_COLORIZE.toString();
            }
            return AnalyticsScreenId.SCREEN_ON_DEMAND_REMASTER_VIEWER.toString();
        } else if (LocationKey.isAllDayTimeLapse(str)) {
            return AnalyticsScreenId.SCREEN_DETAIL_VIEW_TIME_LAPSE.toString();
        } else {
            if (!z3) {
                return AnalyticsScreenId.SCREEN_DETAIL_VIEW_IMMERSED.toString();
            }
            if (z7) {
                if (z9) {
                    if (mediaItem.isVideo()) {
                        return AnalyticsScreenId.SCREEN_COVER_QUICK_VIEW_VIDEO.toString();
                    }
                    return AnalyticsScreenId.SCREEN_COVER_QUICK_VIEW_PICTURE.toString();
                } else if (mediaItem.isVideo()) {
                    return AnalyticsScreenId.SCREEN_QUICK_VIEW_VIDEO.toString();
                } else {
                    return AnalyticsScreenId.SCREEN_QUICK_VIEW_PICTURE.toString();
                }
            } else if (mediaItem.isSharing()) {
                if (mediaItem.isVideo()) {
                    return AnalyticsScreenId.SCREEN_SHARED_DETAIL_VIEW_VIDEO.toString();
                }
                return AnalyticsScreenId.SCREEN_SHARED_DETAIL_VIEW_PICTURE.toString();
            } else if (Analytics.isStoryNotifications(str)) {
                if (MediaItemStory.getStoryType(mediaItem) == StoryType.AGIF.getValue()) {
                    return AnalyticsScreenId.SCREEN_GALLERY_ANIMATE_PREVIEW.toString();
                }
                return AnalyticsScreenId.SCREEN_GALLERY_COLLAGE_PREVIEW.toString();
            } else if (LocationKey.isHighLightPictures(str)) {
                return AnalyticsScreenId.SCREEN_HIGHLIGHT_VIEWER.toString();
            } else {
                if (LocationKey.isDynamicViewList(str)) {
                    return AnalyticsScreenId.SCREEN_DYNAMIC_VIEWER.toString();
                }
                if (LocationKey.isCleanOutMotionPhoto(str)) {
                    return AnalyticsScreenId.SCREEN_MOTION_PHOTO_VIEWER.toString();
                }
                if (LocationKey.isCleanOutDuplicatedPictures(str)) {
                    if (mediaItem.isVideo()) {
                        return AnalyticsScreenId.SCREEN_CLEAN_OUT_DUPLICATED_VIDEO_VIEWER.toString();
                    }
                    return AnalyticsScreenId.SCREEN_CLEAN_OUT_DUPLICATED_IMAGE_VIEWER.toString();
                } else if (LocationKey.isSecondDepthGroupPanelView(str)) {
                    if (LocationKey.isAiEditGroupPanelViewer(str)) {
                        return AnalyticsScreenId.SCREEN_DETAILS_NORMAL.toString();
                    }
                    return AnalyticsScreenId.SCREEN_DETAIL_VIEW_SINGLE_TAKE_EXPANDED.toString();
                } else if (LocationKey.isColorCorrectView(str)) {
                    return AnalyticsScreenId.SCREEN_DETAIL_VIEW_COLOR_GRADING.toString();
                } else {
                    if (LocationKey.isLongExposure(str)) {
                        return AnalyticsScreenId.SCREEN_DETAIL_VIEW_LONG_EXPOSURE.toString();
                    }
                    if (mediaItem.isSingleTakenShot()) {
                        return AnalyticsScreenId.SCREEN_DETAIL_VIEW_SINGLE_TAKE.toString();
                    }
                    if (mediaItem.isVideo()) {
                        return AnalyticsScreenId.SCREEN_DETAIL_VIEW_VIDEO.toString();
                    }
                    if (mediaItem.isMotionPhoto()) {
                        return AnalyticsScreenId.SCREEN_DETAIL_VIEW_MOTION_PHOTO.toString();
                    }
                    return AnalyticsScreenId.SCREEN_DETAIL_VIEW_PICTURE.toString();
                }
            }
        }
    }

    public static Pair<String, String>[] getViewerCustomDimensions(MediaItem mediaItem, String str) {
        return new Pair[]{new Pair(AnalyticsDetailKey.DEFAULT.toString(), str), new Pair(AnalyticsDetailKey.MEDIA_TYPE.toString(), String.valueOf(mediaItem.getMediaType().toInt())), new Pair(AnalyticsDetailKey.SEF_TYPE.toString(), String.valueOf(mediaItem.getSefFileType())), new Pair(AnalyticsDetailKey.SEF_SUB_TYPE.toString(), String.valueOf(mediaItem.getSefFileSubType())), new Pair(AnalyticsDetailKey.SEF_TYPES.toString(), mediaItem.getSefFileTypes()), new Pair(AnalyticsDetailKey.RECORDING_MODE.toString(), String.valueOf(mediaItem.getRecordingMode()))};
    }
}
