package com.samsung.android.gallery.module.grid;

import A.a;
import android.content.Context;
import android.content.res.Resources;
import c0.C0086a;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i4.C0468a;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GridHelper {
    public static final boolean NON_REAL_RATIO;
    public static final int STORY_PICTURES_DEFAULT_DEPTH;
    private static final ConcurrentHashMap<String, GridHelper> sMap = new ConcurrentHashMap<>();
    protected final String TAG = getClass().getSimpleName();
    final String mLocationKey;
    final int[] mMonthIndexes = buildMonth();
    final int[] mSpanInfo;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        CustomImpl impl;

        public Builder(String str) {
            this.impl = new CustomImpl(str, new int[]{1});
        }

        public GridHelper build() {
            return this.impl;
        }

        public Builder setDepthDefault(int i2) {
            this.impl.setDepthDefault(i2);
            return this;
        }

        public Builder setResources(int i2) {
            this.impl.setResources(i2, i2);
            return this;
        }

        public Builder setSpanInfo(int[] iArr) {
            this.impl = new CustomImpl(this.impl.mLocationKey, iArr).cloneOf(this.impl);
            return this;
        }

        public Builder setSpans(int[] iArr) {
            this.impl.setSpans(iArr, iArr);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ModelSegment {
        static final boolean SEP_LITE = Features.isEnabled(Features.IS_SEP_LITE);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TimelineHolder {
        static volatile GridHelper instance = new Timeline70Impl();
    }

    static {
        Features features = Features.SUPPORT_REAL_RATIO;
        STORY_PICTURES_DEFAULT_DEPTH = Features.isEnabled(features) ? 1 : 0;
        NON_REAL_RATIO = !Features.isEnabled(features);
    }

    public GridHelper(String str) {
        this.mLocationKey = str;
        this.mSpanInfo = buildSpanInfo();
    }

    /* access modifiers changed from: private */
    public static GridHelper createGridHelper(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1734318230:
                if (str.equals("location://albums/choice/root")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1688309799:
                if (str.equals("location://cleanOut/burstSimilar/fileList")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1648894317:
                if (str.equals("location://trash")) {
                    c5 = 2;
                    break;
                }
                break;
            case -1393988146:
                if (str.equals("location://albums/pane")) {
                    c5 = 3;
                    break;
                }
                break;
            case -1374681858:
                if (str.equals("location://folder/root")) {
                    c5 = 4;
                    break;
                }
                break;
            case -1327340640:
                if (str.equals("location://albums/fileList")) {
                    c5 = 5;
                    break;
                }
                break;
            case -1061090085:
                if (str.equals("location://virtual/album/view/fileList")) {
                    c5 = 6;
                    break;
                }
                break;
            case -964411829:
                if (str.equals("location://cleanOut/duplicated/fileList")) {
                    c5 = 7;
                    break;
                }
                break;
            case -742347276:
                if (str.equals("location://family/shared/trash")) {
                    c5 = 8;
                    break;
                }
                break;
            case -440239236:
                if (str.equals("location://sharing/albums")) {
                    c5 = 9;
                    break;
                }
                break;
            case -332973319:
                if (str.equals("location://albums/fileList/mxVirtual/recent")) {
                    c5 = 10;
                    break;
                }
                break;
            case -300413254:
                if (str.equals("location://albums/fileList/mxVirtual/favorite")) {
                    c5 = 11;
                    break;
                }
                break;
            case -212479357:
                if (str.equals("location://story/albums")) {
                    c5 = 12;
                    break;
                }
                break;
            case -125579287:
                if (str.equals("location://albums")) {
                    c5 = 13;
                    break;
                }
                break;
            case -90135137:
                if (str.equals("location://cleanOut/fileList")) {
                    c5 = 14;
                    break;
                }
                break;
            case 263612166:
                if (str.equals("location://timeline")) {
                    c5 = 15;
                    break;
                }
                break;
            case 321300043:
                if (str.equals("location://albums/manage")) {
                    c5 = 16;
                    break;
                }
                break;
            case 505417069:
                if (str.equals("location://sharing/albums/fileList")) {
                    c5 = 17;
                    break;
                }
                break;
            case 972383991:
                if (str.equals("location://search/CreatureCoverChoice")) {
                    c5 = 18;
                    break;
                }
                break;
            case 1177645495:
                if (str.equals("location://virtual/album/video/fileList")) {
                    c5 = 19;
                    break;
                }
                break;
            case 1231072838:
                if (str.equals("location://story/albums/fileList")) {
                    c5 = 20;
                    break;
                }
                break;
            case 1297591864:
                if (str.equals("location://virtual/album/recently/fileList")) {
                    c5 = 21;
                    break;
                }
                break;
            case 1344752317:
                if (str.equals("location://folder/choice")) {
                    c5 = 22;
                    break;
                }
                break;
            case 1613952905:
                if (str.equals("location://story/albums/storyHighlightList")) {
                    c5 = 23;
                    break;
                }
                break;
            case 1854729011:
                if (str.equals("location://virtual/album/repair/fileList")) {
                    c5 = 24;
                    break;
                }
                break;
            case 1894681211:
                if (str.equals("location://albums/all")) {
                    c5 = 25;
                    break;
                }
                break;
            case 1944014916:
                if (str.equals("location://virtual/album/favorite/fileList")) {
                    c5 = 26;
                    break;
                }
                break;
            case 2118123714:
                if (str.equals("location://cleanOut/motionPhotoClip/fileList")) {
                    c5 = 27;
                    break;
                }
                break;
            case 2140179036:
                if (str.equals("location://search/fileList")) {
                    c5 = 28;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 4:
            case 13:
            case 16:
            case 22:
            case 25:
                return new AlbumsImpl();
            case 1:
            case 7:
                return new CleanOutDuplicateImpl();
            case 2:
            case 8:
                return new TrashImpl70(str);
            case 3:
                return new AlbumsPaneImpl();
            case 5:
            case 6:
            case 10:
            case 11:
            case 19:
            case 21:
            case 24:
            case 26:
                return new AlbumPicturesImpl70(str);
            case 9:
                return new SharingsImpl();
            case 12:
                return new StoriesImpl();
            case 14:
            case 27:
                return new CleanOutImpl(str);
            case 15:
                return TimelineHolder.instance;
            case 17:
                return new SharingPicturesImpl70();
            case 18:
                return new CreatureCoverChoiceImpl();
            case 20:
                return new StoryPicturesImpl();
            case 23:
                return new StoryHighlightListImpl();
            case 28:
                return new SearchPicturesImpl70();
            default:
                return new PicturesImpl70(str);
        }
    }

    public static GridHelper getInstance(String str) {
        return sMap.computeIfAbsent(getKey(str), new C0468a(27));
    }

    private static String getKey(String str) {
        if (LocationKey.isStoryPictures(str) || LocationKey.isStoryHighlight(str)) {
            if (!LocationKey.isStoryHighlight(str) || ArgumentsUtil.getArgValue(str, "cover_pick", false)) {
                return "location://story/albums/fileList";
            }
            return "location://story/albums/storyHighlightList";
        } else if (isStoriesKey(str)) {
            return "location://story/albums";
        } else {
            if (LocationKey.isSearchPictures(str)) {
                return "location://search/fileList";
            }
            if (LocationKey.isFolder(str)) {
                return "location://folder/root";
            }
            return ArgumentsUtil.removeArgs(str);
        }
    }

    public static GridHelper getTimeline() {
        return TimelineHolder.instance;
    }

    private boolean isDexMode(Context context) {
        return DeviceInfo.isDexMode(context);
    }

    private static boolean isStoriesKey(String str) {
        if (LocationKey.isStoriesCategory(str) || LocationKey.isStoriesFavorite(str) || LocationKey.isSearchCategoryStories(str)) {
            return true;
        }
        return false;
    }

    public static boolean isTimelineMonth(int i2) {
        return getTimeline().isMonth(i2);
    }

    public static boolean isTimelineYear() {
        return PreferenceFeatures.OneUi30.YEAR_CLUSTERING && isTimelineYear(getTimeline().getGridDepth());
    }

    @Deprecated
    public int[] buildMonth() {
        return new int[]{0};
    }

    public int[] buildSpanInfo() {
        return new int[]{2, 1, 1, 17};
    }

    public int getColumnSize(Context context) {
        return getColumnSize(context, getGridDepth());
    }

    public abstract int getDefaultDepth();

    public int[] getGridArray(Context context) {
        int i2;
        if (context != null) {
            Resources resources = context.getResources();
            if (isDexMode(context)) {
                i2 = getGridArrayResourceOnDex();
            } else {
                i2 = getGridArrayResource();
            }
            return resources.getIntArray(i2);
        }
        return new int[]{1};
    }

    public abstract int getGridArrayResource();

    public int getGridArrayResourceOnDex() {
        return getGridArrayResource();
    }

    public int getGridDepth() {
        return GalleryPreference.getInstance().loadInt(getPreferenceName(), getDefaultDepth());
    }

    public int getGridDepthFromLegacy(Context context, GridHelper gridHelper) {
        int preferenceGridCount = gridHelper.getPreferenceGridCount(context);
        if (preferenceGridCount > 0) {
            int[] intArray = context.getResources().getIntArray(getGridArrayResource());
            String str = this.TAG;
            StringBuilder o2 = C0086a.o(preferenceGridCount, "GridSpans#recover span count ", " from ");
            o2.append(StringCompat.toString(intArray));
            Log.w(str, o2.toString());
            for (int i2 = 0; i2 < intArray.length; i2++) {
                if (intArray[i2] == preferenceGridCount) {
                    return i2;
                }
            }
        }
        return getDefaultDepth();
    }

    public String getNotifyKey() {
        return null;
    }

    public final int getPreferenceGridCount(Context context) {
        int loadInt = GalleryPreference.getInstance().loadInt(getPreferenceName(), -1);
        if (loadInt < 0) {
            return -1;
        }
        int[] intArray = context.getResources().getIntArray(getGridArrayResource());
        if (loadInt < intArray.length) {
            return intArray[loadInt];
        }
        return -1;
    }

    public abstract PreferenceName getPreferenceName();

    public int[] getSpanInfo() {
        return this.mSpanInfo;
    }

    public int getSplitArrayResource() {
        return getGridArrayResource();
    }

    public int getSplitArrayResourceOnDex() {
        return getSplitArrayResource();
    }

    public int[] getSplitGridArray(Context context) {
        int i2;
        Resources resources = context.getResources();
        if (isDexMode(context)) {
            i2 = getSplitArrayResourceOnDex();
        } else {
            i2 = getSplitArrayResource();
        }
        return resources.getIntArray(i2);
    }

    public boolean isMonth(int i2) {
        if (i2 == 0) {
            return true;
        }
        return false;
    }

    public boolean isMonthForViewing() {
        return false;
    }

    public boolean isSelectable(int i2) {
        return true;
    }

    public boolean isYear(int i2) {
        return false;
    }

    public void saveGridDepth(int i2) {
        GalleryPreference.getInstance().saveState(getPreferenceName(), i2);
    }

    public final String tag() {
        return this.TAG;
    }

    public String toString() {
        return "GridHelper{" + this.mLocationKey + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getPreferenceName() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getGridDepth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getDefaultDepth() + "}";
    }

    public static boolean isTimelineYear(int i2) {
        return PreferenceFeatures.OneUi30.YEAR_CLUSTERING && getTimeline().isYear(i2);
    }

    public int getColumnSize(Context context, int i2) {
        int i7;
        try {
            int[] gridArray = getGridArray(context);
            if (i2 >= 0) {
                if (i2 < gridArray.length) {
                    i7 = i2;
                    String str = this.TAG;
                    Log.d(str, "GridHelper{" + this.mLocationKey + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i7 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + gridArray[i7] + "}");
                    return gridArray[i7];
                }
            }
            i7 = gridArray.length - 1;
            String str2 = this.TAG;
            Log.d(str2, "GridHelper{" + this.mLocationKey + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i7 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + gridArray[i7] + "}");
            return gridArray[i7];
        } catch (Exception e) {
            a.s(e, new StringBuilder("getColumnSize failed e="), this.TAG);
            return 1;
        }
    }

    public GridHelper(String str, int[] iArr) {
        this.mLocationKey = str;
        this.mSpanInfo = iArr;
    }

    public void saveGridCount(int i2) {
    }
}
