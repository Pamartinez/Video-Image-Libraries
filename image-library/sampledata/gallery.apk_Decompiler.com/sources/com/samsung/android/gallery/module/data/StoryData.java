package com.samsung.android.gallery.module.data;

import android.graphics.RectF;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.mp.table.MpAnalyzeInfoTable;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StoryLevel2Cat;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.TimeUtil;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryData {
    private static final StoryData EMPTY = new StoryData();
    private static final String TAG = "StoryData";
    static final Function<String, Object> constructor = new j(10);
    public String bgmInfo = "";
    public int category = -1;
    public int chapterId;
    public String chapterTitle = "";
    public String chunkData = "";
    public boolean chunkDisplayable;
    public int chunkId;
    public int chunkType = -1;
    public long cmhFileId = -1;
    public Object collageOriginalCoverInfo;
    public String collagePath;
    public int collageType;
    public int collageVideoIndex;
    public String coverFaceRectRatio = "";
    public long coverId = -1;
    public String coverRectRatio = "";
    public long creationTime = -1;
    public long displayOrder = -1;
    public String duration;
    public String[] effectFilePaths = new String[2];
    public FileItemInterface effectItem;
    public int effectType;
    public long endTime = -1;
    public long favorite;
    private long fileId = -1;
    public String filter = "";
    public boolean highlightVideo;
    public Uri liveEffectFileUri;
    public float mfcScore;
    public boolean newLabel = true;
    public int notifyStatus = -1;
    public String polygonPoi;
    public String recapPath;
    public int saType;
    public int scoring = -1;
    public long startTime = -1;
    public int storyType = -1;
    public String streetName;
    public String theme = "";
    public boolean timeVisible;
    public String totalSmartCropDeviceRatio;
    public String totalSmartCropRatio;
    public int tripDay;
    public int updatedByUser;
    public boolean userCuration;
    public boolean userEnter;
    public RectF viewOriginTargetCropRectRatio;

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$static$0(String str) {
        return new StoryData();
    }

    public static StoryData of(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null) {
            return EMPTY;
        }
        return ((StoryData) fileItemInterface.tags().computeIfAbsent(TAG, constructor)).setFileId(fileItemInterface.getFileId());
    }

    private void updateAnalyzedInfo(CursorHolder cursorHolder) {
        if (Features.isEnabled(Features.CMH_TO_MP_MIGRATION)) {
            updateAnalyzedInfoV85(cursorHolder);
        } else {
            updateAnalyzedInfoV0(cursorHolder);
        }
    }

    private void updateAnalyzedInfoV0(CursorHolder cursorHolder) {
        this.totalSmartCropRatio = cursorHolder.getString(cursorHolder.indexTotalSmartCropRatio, (String) null);
        this.totalSmartCropDeviceRatio = cursorHolder.getString(cursorHolder.indexTotalSmartCropDeviceRatio, (String) null);
        this.mfcScore = (float) cursorHolder.getFloat(cursorHolder.indexStoryMfcScore, MapUtil.INVALID_LOCATION);
        if (PreferenceFeatures.OneUi7x.STORY_ONE_UI_70 && cursorHolder.getInt(cursorHolder.indexFileAddedStatus, 0) == 0) {
            this.effectType = cursorHolder.getInt(cursorHolder.indexEffectType, 0);
        }
    }

    private void updateAnalyzedInfoV85(CursorHolder cursorHolder) {
        String string = cursorHolder.getString(cursorHolder.indexAnalyzeInfo, (String) null);
        if (!TextUtils.isEmpty(string)) {
            Map<String, Object> unpack = MpAnalyzeInfoTable.unpack(string);
            this.totalSmartCropRatio = (String) unpack.get("__total_smartcrop_ratio");
            this.totalSmartCropDeviceRatio = (String) unpack.get("__total_smartcrop_device_ratio");
            this.mfcScore = ((Float) StringCompat.valueOf((String) unpack.get("__mfc_score"), Float.valueOf(0.0f))).floatValue();
            if (cursorHolder.getInt(cursorHolder.indexFileAddedStatus, 0) == 0) {
                this.effectType = ((Integer) StringCompat.valueOf((String) unpack.get("__effect_type"), 0)).intValue();
            }
        }
    }

    private void updatePolygonPoi(CursorHolder cursorHolder) {
        if (PreferenceFeatures.OneUi8x.SUPPORT_STORY_POLYGON_POI) {
            String string = cursorHolder.getString(cursorHolder.indexPoiCategory, (String) null);
            if (!TextUtils.isEmpty(string)) {
                Stream of2 = Stream.of(string.split("\\u001F"));
                List of3 = List.of("building", "hospital");
                Objects.requireNonNull(of3);
                if (of2.noneMatch(new g(1, of3))) {
                    this.polygonPoi = cursorHolder.getString(cursorHolder.indexPoiName, (String) null);
                }
            }
        }
    }

    private void updateTimeDuration(CursorHolder cursorHolder) {
        long j2;
        long j3;
        long j8;
        this.startTime = cursorHolder.getLong(cursorHolder.indexStoryStartTime, -1);
        Features features = Features.SUPPORT_LOCAL_TIME;
        if (Features.isEnabled(features)) {
            j2 = TimeUtil.toUtcTimeInMillis(this.startTime);
        } else {
            j2 = this.startTime;
        }
        this.startTime = j2;
        this.endTime = cursorHolder.getLong(cursorHolder.indexStoryEndTime, -1);
        if (Features.isEnabled(features)) {
            j3 = TimeUtil.toUtcTimeInMillis(this.endTime);
        } else {
            j3 = this.endTime;
        }
        this.endTime = j3;
        int i2 = cursorHolder.indexLocalTime;
        if (i2 >= 0) {
            j8 = cursorHolder.getLong(i2, 0);
        } else {
            j8 = 0;
        }
        if (j8 <= 0) {
            j8 = cursorHolder.getLong(cursorHolder.indexDateTaken, 0);
        } else if (Features.isEnabled(features)) {
            j8 = TimeUtil.toUtcTimeInMillis(j8);
        }
        if (StoryLevel2Cat.isDailyWithYear(this.saType)) {
            this.duration = TimeUtil.getYearString(j8);
        } else if (StoryLevel2Cat.isDailyWithDate(this.saType)) {
            this.duration = TimeUtil.toLocalDate(j8, "YYMD");
        } else {
            long j10 = this.startTime;
            if (j10 != -1) {
                long j11 = this.endTime;
                if (j11 != -1) {
                    this.duration = TimeUtil.getEventDatePeriod(j10, j11, 50);
                }
            }
        }
    }

    public String getMimeType() {
        if (isCollageType(this.storyType)) {
            return "image/jpeg";
        }
        if (this.storyType == StoryType.AGIF.getValue()) {
            return "image/gif";
        }
        if (this.storyType == StoryType.VIDEO_COLLAGE.getValue()) {
            return "video/*";
        }
        return "";
    }

    public boolean isCollageType(int i2) {
        if (i2 == StoryType.COLLAGE.getValue() || i2 == StoryType.COLLAGE_THEN_AND_NOW.getValue() || i2 == StoryType.REDISCOVER_DAY.getValue()) {
            return true;
        }
        return false;
    }

    public StoryData parse(CursorHolder cursorHolder) {
        boolean z;
        boolean z3;
        this.storyType = cursorHolder.getInt(cursorHolder.indexStoryType, -1);
        boolean z7 = true;
        if (cursorHolder.getInt(cursorHolder.indexStoryHighlightVideo, -1) == 1) {
            z = true;
        } else {
            z = false;
        }
        this.highlightVideo = z;
        this.coverId = cursorHolder.getLong(cursorHolder.indexStoryCover, -1);
        this.notifyStatus = cursorHolder.getInt(cursorHolder.indexStoryNotifyStatus, -1);
        this.coverRectRatio = cursorHolder.getString(cursorHolder.indexStoryCoverRectRatio, "");
        this.category = StoryType.getCategory(this.storyType).ordinal();
        this.creationTime = cursorHolder.getLong(cursorHolder.indexStoryCreationTime, -1);
        this.collagePath = cursorHolder.getString(cursorHolder.indexMemoryCollagePath, (String) null);
        this.collageType = cursorHolder.getInt(cursorHolder.indexMemoryCollageType, 0);
        this.collageVideoIndex = cursorHolder.getInt(cursorHolder.indexMemoryCollageVideoIndex, -1);
        this.streetName = cursorHolder.getString(cursorHolder.indexMemoryStreetName, (String) null);
        this.scoring = cursorHolder.getInt(cursorHolder.indexStoryScoring, -1);
        this.favorite = cursorHolder.getLong(cursorHolder.indexStoryFavorite, 0);
        this.effectFilePaths[0] = cursorHolder.getString(cursorHolder.indexRevitalizedPath, "");
        this.effectFilePaths[1] = cursorHolder.getString(cursorHolder.indexPortraitPath, "");
        if (cursorHolder.getInt(cursorHolder.indexStoryUserCuration, 0) > 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.userCuration = z3;
        if (cursorHolder.getInt(cursorHolder.indexStoryUserEnter, 0) <= 1) {
            z7 = false;
        }
        this.userEnter = z7;
        this.coverFaceRectRatio = cursorHolder.getString(cursorHolder.indexStoryCoverFaceRectRatio, (String) null);
        this.saType = cursorHolder.getInt(cursorHolder.indexStorySaType, 0);
        this.theme = cursorHolder.getString(cursorHolder.indexStoryTheme, "");
        this.filter = cursorHolder.getString(cursorHolder.indexStoryFilter, "");
        this.bgmInfo = cursorHolder.getString(cursorHolder.indexStoryBgmInfo, "");
        this.updatedByUser = cursorHolder.getInt(cursorHolder.indexStoryUpdatedByUser, 0);
        this.displayOrder = cursorHolder.getLong(cursorHolder.indexStoryDisplayOrder, -1);
        this.cmhFileId = cursorHolder.getLong(cursorHolder.indexCmhFileId, -1);
        updateTimeDuration(cursorHolder);
        updateAnalyzedInfo(cursorHolder);
        updatePolygonPoi(cursorHolder);
        this.recapPath = cursorHolder.getString(cursorHolder.indexRecapStoryPath, (String) null);
        return this;
    }

    public StoryData setChapter(int i2, String str) {
        this.chapterId = i2;
        this.chapterTitle = str;
        return this;
    }

    public StoryData setChunk(int i2, String str, int i7) {
        this.chunkId = i2;
        this.chunkData = str;
        this.chunkType = i7;
        return this;
    }

    public StoryData setFileId(long j2) {
        this.fileId = j2;
        return this;
    }

    public StoryData setTheme(String str, String str2, String str3) {
        this.theme = str;
        this.filter = str2;
        this.bgmInfo = str3;
        return this;
    }
}
