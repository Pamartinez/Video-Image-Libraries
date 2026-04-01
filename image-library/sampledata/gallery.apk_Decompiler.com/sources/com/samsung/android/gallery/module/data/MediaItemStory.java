package com.samsung.android.gallery.module.data;

import android.graphics.RectF;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.abstraction.MediaItemBase;
import com.samsung.android.gallery.module.abstraction.StoryCategory;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaItemStory extends MediaItemBase {
    public static long getDisplayOrder(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).displayOrder;
    }

    public static String[] getEffectFilePaths(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).effectFilePaths;
    }

    public static FileItemInterface getEffectItem(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).effectItem;
    }

    public static int getEffectType(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).effectType;
    }

    public static Uri getLiveEffectFileUri(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).liveEffectFileUri;
    }

    public static float getMfcScore(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).mfcScore;
    }

    public static String getPolygonPoi(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).polygonPoi;
    }

    public static String getRecapPath(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).recapPath;
    }

    public static String getStoryBgmInfo(FileItemInterface fileItemInterface) {
        return (String) MediaItemBase.getOrElse(StoryData.of(fileItemInterface).bgmInfo, "");
    }

    public static String getStoryCategoryKey(FileItemInterface fileItemInterface) {
        return StoryCategory.getKey(getStoryType(fileItemInterface), getStorySaType(fileItemInterface));
    }

    public static int getStoryCategoryType(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).category;
    }

    public static int getStoryChapterId(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).chapterId;
    }

    public static String getStoryChapterTitle(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).chapterTitle;
    }

    public static String getStoryChunkData(FileItemInterface fileItemInterface) {
        return (String) MediaItemBase.getOrElse(StoryData.of(fileItemInterface).chunkData, null);
    }

    public static boolean getStoryChunkDisplayable(FileItemInterface fileItemInterface) {
        StoryData of2 = StoryData.of(fileItemInterface);
        if (TextUtils.isEmpty(of2.chunkData) || !of2.chunkDisplayable) {
            return false;
        }
        return true;
    }

    public static int getStoryChunkId(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).chunkId;
    }

    public static int getStoryChunkType(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).chunkType;
    }

    public static String getStoryCollagePath(FileItemInterface fileItemInterface) {
        return (String) MediaItemBase.getOrElse(StoryData.of(fileItemInterface).collagePath, null);
    }

    public static int getStoryCollageType(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).collageType;
    }

    public static int getStoryCollageVideoIndex(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).collageVideoIndex;
    }

    public static String getStoryCoverFaceRectRatio(FileItemInterface fileItemInterface) {
        return (String) MediaItemBase.getOrElse(StoryData.of(fileItemInterface).coverFaceRectRatio, "");
    }

    public static long getStoryCoverId(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).coverId;
    }

    public static String getStoryCoverRectRatio(FileItemInterface fileItemInterface) {
        return (String) MediaItemBase.getOrElse(StoryData.of(fileItemInterface).coverRectRatio, "");
    }

    public static long getStoryCreationTime(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).creationTime;
    }

    public static long getStoryEndTime(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).endTime;
    }

    public static long getStoryFavorite(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).favorite;
    }

    public static String getStoryFilter(FileItemInterface fileItemInterface) {
        return (String) MediaItemBase.getOrElse(StoryData.of(fileItemInterface).filter, "");
    }

    public static int getStoryId(FileItemInterface fileItemInterface) {
        if (fileItemInterface != null) {
            return fileItemInterface.getAlbumID();
        }
        return -1;
    }

    public static String getStoryLevel2Detail(FileItemInterface fileItemInterface) {
        return String.valueOf(getStorySaType(fileItemInterface));
    }

    public static int getStoryNotifyStatus(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).notifyStatus;
    }

    public static Object getStoryOriginCoverData(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).collageOriginalCoverInfo;
    }

    public static int getStorySaType(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).saType;
    }

    public static int getStoryScoring(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).scoring;
    }

    public static long getStoryStartTime(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).startTime;
    }

    public static String getStoryStreetName(FileItemInterface fileItemInterface) {
        return (String) MediaItemBase.getOrElse(StoryData.of(fileItemInterface).streetName, null);
    }

    public static String getStoryTheme(FileItemInterface fileItemInterface) {
        return (String) MediaItemBase.getOrElse(StoryData.of(fileItemInterface).theme, "");
    }

    public static String getStoryTimeDuration(FileItemInterface fileItemInterface) {
        return (String) MediaItemBase.getOrElse(StoryData.of(fileItemInterface).duration, "");
    }

    public static long[] getStoryTimeDurationArray(FileItemInterface fileItemInterface) {
        StoryData of2 = StoryData.of(fileItemInterface);
        return new long[]{Math.max(0, of2.startTime), Math.max(0, of2.endTime)};
    }

    public static boolean getStoryTimeVisible(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).timeVisible;
    }

    public static int getStoryTripDay(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).tripDay;
    }

    public static int getStoryType(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).storyType;
    }

    public static int getStoryUpdatedByUser(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).updatedByUser;
    }

    public static String getTotalSmartCropDeviceRatio(FileItemInterface fileItemInterface) {
        return (String) MediaItemBase.getOrElse(StoryData.of(fileItemInterface).totalSmartCropDeviceRatio, "");
    }

    public static String getTotalSmartCropRatio(FileItemInterface fileItemInterface) {
        return (String) MediaItemBase.getOrElse(StoryData.of(fileItemInterface).totalSmartCropRatio, "");
    }

    public static RectF getViewOriginTargetCropRectRatio(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).viewOriginTargetCropRectRatio;
    }

    public static boolean hasCoverDesignData(FileItemInterface fileItemInterface) {
        return hasEffectType(fileItemInterface, 16);
    }

    private static boolean hasEffectType(FileItemInterface fileItemInterface, int i2) {
        if ((getEffectType(fileItemInterface) & i2) != 0) {
            return true;
        }
        return false;
    }

    public static boolean hasLiveEffectData(FileItemInterface fileItemInterface) {
        if (!hasEffectType(fileItemInterface, 8) || getStoryType(fileItemInterface) < StoryType.RECENT_HIGHLIGHTS_WEEKLY.getValue() - 30) {
            return false;
        }
        return true;
    }

    public static boolean hasNewStoryLabel(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).newLabel;
    }

    public static boolean hasStoryHighlightVideo(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).highlightVideo;
    }

    public static boolean isAiEditEffect(FileItemInterface fileItemInterface) {
        if (getEffectType(fileItemInterface) == 4 || getEffectType(fileItemInterface) == 32) {
            return true;
        }
        return false;
    }

    public static boolean isCollageStory(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || TextUtils.isEmpty(getStoryCollagePath(fileItemInterface))) {
            return false;
        }
        return true;
    }

    public static boolean isLiveEffectItem(FileItemInterface fileItemInterface) {
        if (getLiveEffectFileUri(fileItemInterface) == null || !fileItemInterface.isVideo()) {
            return false;
        }
        return true;
    }

    public static boolean isRecap(FileItemInterface fileItemInterface) {
        if (!PreferenceFeatures.OneUi8x.SUPPORT_RECAP) {
            return false;
        }
        if (getStoryType(fileItemInterface) == StoryType.RECAP.getValue() || getStoryType(fileItemInterface) == StoryType.RECAP_BOOKMARKED.getValue()) {
            return true;
        }
        return false;
    }

    public static boolean isTransitory(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || !StoryType.isTransitoryType(getStoryType(fileItemInterface))) {
            return false;
        }
        return true;
    }

    public static boolean isUserCuration(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).userCuration;
    }

    public static boolean isUserEnter(FileItemInterface fileItemInterface) {
        return StoryData.of(fileItemInterface).userEnter;
    }

    public static FileItemInterface replaceIfRequired(FileItemInterface fileItemInterface) {
        try {
            if (PreferenceFeatures.OneUi8x.SUPPORT_RECAP && isRecap(fileItemInterface)) {
                return EffectItemBuilder.buildRecapItem(fileItemInterface);
            }
            if (!PreferenceFeatures.OneUi7x.SUPPORT_STORY_LIVE_EFFECT_TYPE || !hasLiveEffectData(fileItemInterface)) {
                return fileItemInterface;
            }
            return EffectItemBuilder.buildLiveEffectItem(fileItemInterface);
        } catch (Exception e) {
            Log.d("MediaITemStory", "fail to load recapItem e=" + e.getMessage());
            return fileItemInterface;
        }
    }

    public static void setEffectItem(FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2) {
        StoryData.of(fileItemInterface).effectItem = fileItemInterface2;
    }

    public static void setEffectType(FileItemInterface fileItemInterface, int i2) {
        StoryData.of(fileItemInterface).effectType = i2;
    }

    public static void setLiveEffectFileUri(FileItemInterface fileItemInterface, Uri uri) {
        StoryData.of(fileItemInterface).liveEffectFileUri = uri;
    }

    public static void setNewStoryLabel(FileItemInterface fileItemInterface, boolean z) {
        StoryData.of(fileItemInterface).newLabel = z;
    }

    public static void setStoryChunkDisplayable(FileItemInterface fileItemInterface, boolean z) {
        StoryData.of(fileItemInterface).chunkDisplayable = z;
    }

    public static void setStoryCollagePath(FileItemInterface fileItemInterface, String str) {
        StoryData.of(fileItemInterface).collagePath = str;
    }

    public static void setStoryFavorite(FileItemInterface fileItemInterface, long j2) {
        StoryData.of(fileItemInterface).favorite = j2;
    }

    public static void setStoryNotifyStatus(FileItemInterface fileItemInterface, int i2) {
        StoryData.of(fileItemInterface).notifyStatus = i2;
    }

    public static void setStoryOriginCoverData(FileItemInterface fileItemInterface, Object obj) {
        StoryData.of(fileItemInterface).collageOriginalCoverInfo = obj;
    }

    public static void setStoryScoring(FileItemInterface fileItemInterface, int i2) {
        StoryData.of(fileItemInterface).scoring = i2;
    }

    public static void setStoryStartTime(FileItemInterface fileItemInterface, long j2) {
        StoryData.of(fileItemInterface).startTime = j2;
    }

    public static void setStoryTimeDuration(FileItemInterface fileItemInterface, String str) {
        StoryData.of(fileItemInterface).duration = str;
    }

    public static void setStoryTimeVisible(FileItemInterface fileItemInterface, boolean z) {
        StoryData.of(fileItemInterface).timeVisible = z;
    }

    public static void setStoryTripDay(FileItemInterface fileItemInterface, int i2) {
        StoryData.of(fileItemInterface).tripDay = i2;
    }

    public static void setStoryType(FileItemInterface fileItemInterface, int i2) {
        StoryData.of(fileItemInterface).storyType = i2;
    }

    public static void setTotalSmartCropDeviceRatio(FileItemInterface fileItemInterface, String str) {
        StoryData.of(fileItemInterface).totalSmartCropDeviceRatio = str;
    }

    public static void setTotalSmartCropRatio(FileItemInterface fileItemInterface, String str) {
        StoryData.of(fileItemInterface).totalSmartCropRatio = str;
    }

    public static void setUserCuration(FileItemInterface fileItemInterface, boolean z) {
        StoryData.of(fileItemInterface).userCuration = z;
    }

    public static void setViewOriginTargetCropRectRatio(FileItemInterface fileItemInterface, RectF rectF) {
        StoryData.of(fileItemInterface).viewOriginTargetCropRectRatio = rectF;
    }
}
