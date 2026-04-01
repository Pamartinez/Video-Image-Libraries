package com.samsung.android.gallery.module.story;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.R$dimen;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.samsung.android.sdk.mobileservice.common.result.CommonStatusCodes;
import i.C0212a;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoryHelper {
    public static String createSaveFilePath(String str) {
        return createSaveFilePath(str, false);
    }

    public static int getCollageColumns(int i2) {
        if (i2 == 1 || i2 == 2) {
            return 2;
        }
        if (i2 == 3) {
            return 3;
        }
        return 0;
    }

    public static int[] getPreferCoverCropRatio(FileItemInterface fileItemInterface) {
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            return new int[]{CommonStatusCodes.DEVICE_OUT_OF_STORAGE, 366};
        }
        if (PreferenceFeatures.OneUi30.MEMORIES) {
            return new int[]{360, 360};
        }
        return new int[]{360, 254};
    }

    public static int getSpacingByRatio(Context context) {
        return -((int) (((1.0f - getWidthRatio(context)) * (context.getResources().getDisplayMetrics().density * ((float) context.getResources().getConfiguration().screenWidthDp))) / 2.0f));
    }

    public static float getWidthRatio(Context context) {
        int i2;
        Resources resources = context.getResources();
        TypedValue typedValue = new TypedValue();
        if (DeviceInfo.isDexMode(context)) {
            i2 = R$dimen.stories_width_ratio_dex;
        } else if (resources.getConfiguration().orientation == 2) {
            i2 = R$dimen.stories_width_ratio_land;
        } else {
            i2 = R$dimen.stories_width_ratio_port;
        }
        resources.getValue(i2, typedValue, true);
        return typedValue.getFloat();
    }

    public static boolean isAgif(int i2) {
        if (i2 == StoryType.AGIF.getValue()) {
            return true;
        }
        return false;
    }

    public static boolean isCollage(int i2) {
        if (i2 == StoryType.COLLAGE.getValue() || i2 == StoryType.VIDEO_COLLAGE.getValue() || i2 == StoryType.REDISCOVER_DAY.getValue() || i2 == StoryType.COLLAGE_THEN_AND_NOW.getValue()) {
            return true;
        }
        return false;
    }

    public static boolean isGeneralSlideshowVideoFormat(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || !fileItemInterface.isVideo() || fileItemInterface.isCloudOnly() || fileItemInterface.isBroken()) {
            return false;
        }
        return true;
    }

    public static boolean isNewStory(int i2) {
        if (i2 != 1) {
            return true;
        }
        return false;
    }

    private static boolean isRemoveAll(int i2, int i7) {
        if (i2 <= i7) {
            return true;
        }
        return false;
    }

    public static boolean isSlideshowFormat(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null) {
            return false;
        }
        if ((fileItemInterface.isVideo() || MediaItemStory.isLiveEffectItem(fileItemInterface)) && !fileItemInterface.isCloudOnly() && !fileItemInterface.isBroken() && supportIfHlg(fileItemInterface) && supportedResolution(fileItemInterface)) {
            return true;
        }
        return false;
    }

    private static boolean isTimeBoundary(FileItemInterface fileItemInterface, long[] jArr) {
        long j2;
        if (fileItemInterface == null) {
            return false;
        }
        if (fileItemInterface.getDateLocal() > 0) {
            j2 = fileItemInterface.getDateLocal();
        } else {
            j2 = fileItemInterface.getDateTaken();
        }
        if (j2 == jArr[0] || j2 == jArr[1]) {
            return true;
        }
        return false;
    }

    public static boolean isValidObject(Object[] objArr, int i2) {
        if (objArr == null || objArr.length < i2) {
            return false;
        }
        for (Object obj : objArr) {
            if (obj == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean isVideoItem(FileItemInterface fileItemInterface) {
        if (!isSlideshowFormat(fileItemInterface) || fileItemInterface.getFileDuration() <= 0) {
            return false;
        }
        return true;
    }

    public static void removeFromStory(Context context, FileItemInterface fileItemInterface, FileItemInterface[] fileItemInterfaceArr) {
        FileItemInterface fileItemInterface2;
        FileItemInterface[] fileItemInterfaceArr2 = fileItemInterfaceArr;
        if (fileItemInterface == null) {
            try {
                Log.w("StoryHelper", "removeFromStory, no header");
                fileItemInterface2 = fileItemInterfaceArr2[0];
            } catch (Exception e) {
                Log.e("StoryHelper", "removeFromStory failed e=" + e.getMessage());
                e.printStackTrace();
                return;
            }
        } else {
            fileItemInterface2 = fileItemInterface;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long storyCoverId = MediaItemStory.getStoryCoverId(fileItemInterface2);
        int count = fileItemInterface2.getCount();
        long[] storyTimeDurationArray = MediaItemStory.getStoryTimeDurationArray(fileItemInterface2);
        ArrayList arrayList = new ArrayList(Arrays.asList(fileItemInterfaceArr2));
        boolean removeItemsFromStory = DbCompat.storyApi().removeItemsFromStory(arrayList);
        int albumID = ((FileItemInterface) arrayList.get(0)).getAlbumID();
        if (removeItemsFromStory) {
            updateStoryTime(albumID, count, arrayList, storyTimeDurationArray);
            if (storyCoverId <= 0) {
                storyCoverId = DbCompat.storyApi().getStoryCoverCmhFileId(albumID);
            }
            if (storyCoverId > 0) {
                updateStoryCover(fileItemInterfaceArr2, albumID, storyCoverId);
            }
            StoryUpdateNotifier.getInstance().notifyStory(context, true);
            Blackboard.publishGlobal("data://user/storyUpdated", (Object) null);
        }
        Log.d("StoryHelper", "removeFromStory {" + storyCoverId + GlobalPostProcInternalPPInterface.SPLIT_REGEX + fileItemInterface2.getAlbumID() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + albumID + GlobalPostProcInternalPPInterface.SPLIT_REGEX + count + GlobalPostProcInternalPPInterface.SPLIT_REGEX + arrayList.size() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + removeItemsFromStory + "} +" + (System.currentTimeMillis() - currentTimeMillis));
    }

    public static void saveStoryViewedId(int i2) {
        if (PreferenceFeatures.PERFORMANCE.STORIES_CURSOR_CACHE && PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            PreferenceCache.StoryViewedIds.append(String.valueOf(i2), GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        }
    }

    private static boolean supportIfHlg(FileItemInterface fileItemInterface) {
        if (!fileItemInterface.isHlgVideo()) {
            return true;
        }
        if (!SdkConfig.atLeast(SdkConfig.GED.V) || !fileItemInterface.isHdrVideo()) {
            return false;
        }
        return true;
    }

    private static boolean supportedResolution(FileItemInterface fileItemInterface) {
        int height = fileItemInterface.getHeight() * fileItemInterface.getWidth();
        if (height <= 0 || height > 16777216) {
            return false;
        }
        return true;
    }

    private static void updateStoryCover(FileItemInterface[] fileItemInterfaceArr, int i2, long j2) {
        if (!SdkConfig.atLeast(SdkConfig.GED.R) && SdkConfig.atLeast(SdkConfig.GED.P)) {
            j2 = DbCompat.storyApi().getFileId(j2);
        }
        int length = fileItemInterfaceArr.length;
        int i7 = 0;
        while (i7 < length) {
            FileItemInterface fileItemInterface = fileItemInterfaceArr[i7];
            if (fileItemInterface == null || fileItemInterface.getFileId() != j2) {
                i7++;
            } else {
                DbCompat.storyApi().resetStoryCover(i2);
                return;
            }
        }
    }

    private static void updateStoryTime(int i2, int i7, ArrayList<FileItemInterface> arrayList, long[] jArr) {
        if (i2 != -1 && !isRemoveAll(i7, arrayList.size())) {
            Iterator<FileItemInterface> it = arrayList.iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (isTimeBoundary(it.next(), jArr)) {
                    z = true;
                }
            }
            if (z) {
                DbCompat.storyApi().updateStoryTime(i2);
            }
        }
    }

    public static void updateStoryViewed(Context context, FileItemInterface fileItemInterface, boolean z) {
        if (fileItemInterface != null) {
            int albumID = fileItemInterface.getAlbumID();
            saveStoryViewedId(albumID);
            DbCompat.storyBadgeApi().updateNotifyStatusViewed(albumID);
            if (z) {
                StoryUpdateNotifier.getInstance().notifyStory(context, true);
            }
        }
    }

    public static String createSaveFilePath(String str, boolean z) {
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(System.currentTimeMillis()));
        StringBuilder s = C0212a.s(str);
        s.append(File.separator);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(format);
        sb2.append(".");
        sb2.append(z ? "jpg" : IFormat.FORMAT_MP4);
        s.append(FileUtils.getUniqueFilename(str, sb2.toString()));
        return s.toString();
    }
}
