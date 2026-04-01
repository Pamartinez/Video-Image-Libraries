package com.samsung.android.gallery.module.dataset.tables;

import A.a;
import A8.C0545b;
import Ba.h;
import U9.b;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.chapter.DeployCluster;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;
import com.samsung.android.gallery.support.utils.ExifCompat;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;
import i.C0212a;
import java.io.Closeable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChapterIndexer implements Closeable {
    private ArrayList<Integer> mChapterIdxList = new ArrayList<>();
    private HashMap<Integer, ClusterItem> mChapterItemMap = new HashMap<>();
    protected int mCount;
    private int mDataCount;
    private int[] mItemHeight;
    HashMap<LayoutInfo, MediaItem> mItemMap = new HashMap<>();
    private int[] mItemWidth;
    private LayoutInfo[] mLayoutInfo;
    private int mMaxScroll;
    private int[] mScrollIndex;
    private int mWidth;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ChapterItem extends AbsClusterItem {
        private final MediaItem mBaseItem;
        private int mChildCount;
        private Long mEndDateTimeMs;
        private Long mStartDateTimeMs;

        public ChapterItem(MediaItem mediaItem) {
            this.mBaseItem = mediaItem;
        }

        /* access modifiers changed from: private */
        public void increaseChildCount() {
            this.mChildCount++;
        }

        public int getChapterId() {
            return MediaItemStory.getStoryChapterId(this.mBaseItem);
        }

        public int getCount() {
            return this.mChildCount;
        }

        public String getDate() {
            return TimeUtil.getEventDatePeriod(this.mStartDateTimeMs.longValue(), this.mEndDateTimeMs.longValue(), 50);
        }

        public long getDateTaken() {
            return this.mStartDateTimeMs.longValue();
        }

        public String getTitle() {
            return MediaItemStory.getStoryChapterTitle(this.mBaseItem);
        }

        public String toString() {
            String str;
            StringBuilder sb2 = new StringBuilder("ChapterItem{d=");
            sb2.append(getDate());
            sb2.append(", dt=");
            sb2.append(getDateTaken());
            sb2.append(", c=");
            sb2.append(getCount());
            sb2.append(", item=");
            sb2.append(MediaItemUtil.getSimpleLog(this.mBaseItem));
            sb2.append(", dr=");
            MediaItem mediaItem = this.mBaseItem;
            if (mediaItem != null) {
                str = mediaItem.getDateRaw();
            } else {
                str = "null";
            }
            return C0212a.p(sb2, str, "}");
        }

        public void updateDate(long j2) {
            Long l = this.mStartDateTimeMs;
            if (l == null) {
                Long valueOf = Long.valueOf(j2);
                this.mEndDateTimeMs = valueOf;
                this.mStartDateTimeMs = valueOf;
                return;
            }
            if (j2 < l.longValue()) {
                this.mStartDateTimeMs = Long.valueOf(j2);
            }
            if (j2 > this.mEndDateTimeMs.longValue()) {
                this.mEndDateTimeMs = Long.valueOf(j2);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayoutInfoCreator {
        /* access modifiers changed from: private */
        public static LayoutInfo create(MediaItem mediaItem, int i2) {
            int i7;
            LayoutInfo layoutInfo = new LayoutInfo();
            boolean z = true;
            int max = Math.max(mediaItem.getWidth(), 1);
            int max2 = Math.max(mediaItem.getHeight(), 1);
            if (rotated(mediaItem)) {
                i7 = max2;
            } else {
                i7 = max;
            }
            layoutInfo.width = i7;
            if (!rotated(mediaItem)) {
                max = max2;
            }
            layoutInfo.height = max;
            layoutInfo.isVideo = mediaItem.isVideo();
            layoutInfo.position = i2;
            layoutInfo.mod = (int) (mediaItem.getFileId() % 10);
            if (layoutInfo.width < layoutInfo.height) {
                z = false;
            }
            layoutInfo.horizontal = z;
            layoutInfo.chapterId = MediaItemStory.getStoryChapterId(mediaItem);
            layoutInfo.chunkId = MediaItemStory.getStoryChunkId(mediaItem);
            layoutInfo.chunkType = MediaItemStory.getStoryChunkType(mediaItem);
            layoutInfo.hasExtraInfo = hasExtraInfo(mediaItem);
            return layoutInfo;
        }

        private static boolean hasExtraInfo(MediaItem mediaItem) {
            if (!TextUtils.isEmpty(MediaItemStory.getStoryStreetName(mediaItem)) || TimeZone.displayable(mediaItem)) {
                return true;
            }
            return false;
        }

        private static boolean rotated(MediaItem mediaItem) {
            if (mediaItem.getOrientation() == 90 || mediaItem.getOrientation() == 270) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface LayoutLookup {
        float getVerticalGapRatio();

        float getWidthRatio(LayoutInfo layoutInfo);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TimeZone {
        static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

        public static boolean displayable(MediaItem mediaItem) {
            try {
                String dateTimeInFile = ExifCompat.getDateTimeInFile(FileUtils.getNameFromPath(mediaItem.getPath()));
                if (TextUtils.isEmpty(dateTimeInFile)) {
                    return false;
                }
                return validTimeDiff(getDateTime(dateTimeInFile).getTime(), mediaItem.getDateTaken());
            } catch (Exception e) {
                a.s(e, new StringBuilder("isValidTimeZone failed e="), "TimeZone");
                return false;
            }
        }

        public static Date getDateTime(String str) {
            return FORMATTER.parse(str);
        }

        private static boolean validTimeDiff(long j2, long j3) {
            if (Math.abs(j2 - j3) < 600000) {
                return true;
            }
            return false;
        }
    }

    public ChapterIndexer(DataTable dataTable) {
        makeCluster(dataTable);
    }

    private void calculateRatio(int i2, LayoutLookup layoutLookup) {
        LayoutInfo[] layoutInfoArr = this.mLayoutInfo;
        if (layoutInfoArr != null && layoutInfoArr.length != 0) {
            this.mMaxScroll = 0;
            this.mWidth = i2;
            int size = this.mChapterIdxList.size() + this.mDataCount;
            this.mItemWidth = new int[size];
            this.mItemHeight = new int[size];
            float f = (float) i2;
            int verticalGapRatio = (int) (layoutLookup.getVerticalGapRatio() * f);
            int i7 = i2;
            int i8 = 0;
            for (int i10 = 0; i10 < size; i10++) {
                if (this.mChapterIdxList.contains(Integer.valueOf(i10))) {
                    i8++;
                } else {
                    LayoutInfo layoutInfo = this.mLayoutInfo[i10 - i8];
                    this.mItemWidth[i10] = (int) (layoutLookup.getWidthRatio(layoutInfo) * f);
                    int i11 = i2 + verticalGapRatio;
                    this.mItemHeight[i10] = i11;
                    if (layoutInfo.groupType == 1) {
                        this.mMaxScroll += i11;
                    } else if (layoutInfo.align.end()) {
                        this.mItemWidth[i10] = i7;
                        this.mMaxScroll += this.mItemHeight[i10];
                    } else {
                        i7 -= this.mItemWidth[i10];
                    }
                    i7 = i2;
                }
            }
        }
    }

    private void createChapterItem() {
        int i2 = 0;
        ChapterItem chapterItem = null;
        for (int i7 = 0; i7 < this.mDataCount; i7++) {
            LayoutInfo layoutInfo = this.mLayoutInfo[i7];
            MediaItem mediaItem = this.mItemMap.get(layoutInfo);
            int i8 = layoutInfo.chapterId;
            if (i8 > 0) {
                if (chapterItem == null || chapterItem.getChapterId() != i8) {
                    chapterItem = new ChapterItem(mediaItem);
                    int i10 = i7 + i2;
                    this.mChapterIdxList.add(Integer.valueOf(i10));
                    this.mChapterItemMap.put(Integer.valueOf(i10), chapterItem);
                    i2++;
                }
                chapterItem.increaseChildCount();
                chapterItem.updateDate(mediaItem.getDateTaken());
            } else {
                chapterItem = null;
            }
        }
        this.mScrollIndex = this.mChapterIdxList.stream().mapToInt(new C0545b(17)).toArray();
        this.mCount = this.mChapterItemMap.size();
        Log.d("ChapterIndexer", "chapter count=" + this.mCount + ", list=" + this.mChapterIdxList.size());
    }

    private void createLayoutInfo(DataTable dataTable) {
        for (int i2 = 0; i2 < this.mDataCount; i2++) {
            MediaItem currentItem = getCurrentItem(dataTable, i2);
            LayoutInfo a7 = LayoutInfoCreator.create(currentItem, i2);
            this.mItemMap.put(a7, currentItem);
            this.mLayoutInfo[i2] = a7;
        }
    }

    private MediaItem getCurrentItem(DefaultTable defaultTable, int i2) {
        MediaItem mediaItem = defaultTable.get(i2);
        if (mediaItem == null) {
            return defaultTable.loadAndGet(i2);
        }
        return mediaItem;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateExtraDisplayable$0(LayoutInfo layoutInfo) {
        MediaItemStory.setStoryTimeVisible(this.mItemMap.get(layoutInfo), layoutInfo.hasExtraInfo);
    }

    private void makeCluster(DataTable dataTable) {
        this.mDataCount = dataTable.getRealCount();
        a.w(new StringBuilder("makeCluster="), this.mDataCount, "ChapterIndexer");
        int i2 = this.mDataCount;
        if (i2 > 0) {
            this.mLayoutInfo = new LayoutInfo[i2];
            createLayoutInfo(dataTable);
            createChapterItem();
            new DeployCluster(new h(22, this)).withCluster().deploy(this.mLayoutInfo);
            updateExtraDisplayable();
            debugLayoutInfo(this.mLayoutInfo);
        }
    }

    /* access modifiers changed from: private */
    public void onClusterMessage(int i2, LayoutInfo layoutInfo) {
        if (i2 == 0) {
            MediaItemStory.setStoryChunkDisplayable(this.mItemMap.get(layoutInfo), true);
        }
    }

    private void updateExtraDisplayable() {
        Arrays.stream(this.mLayoutInfo).forEach(new b(18, this));
    }

    public void close() {
        this.mChapterIdxList = null;
        this.mChapterItemMap = null;
        this.mItemWidth = null;
        this.mItemHeight = null;
        this.mLayoutInfo = null;
        this.mScrollIndex = null;
    }

    public ArrayList<Integer> getChapterIdxList() {
        return this.mChapterIdxList;
    }

    public HashMap<Integer, ClusterItem> getChapterItemMap() {
        return this.mChapterItemMap;
    }

    public int getCount() {
        return this.mCount;
    }

    public int getItemHeight(int i2) {
        int[] iArr = this.mItemHeight;
        if (iArr == null || i2 >= iArr.length) {
            return Math.max(this.mWidth, 1);
        }
        return iArr[i2];
    }

    public int getItemWidth(int i2) {
        int[] iArr = this.mItemWidth;
        if (iArr == null || i2 >= iArr.length) {
            return Math.max(this.mWidth, 1);
        }
        return iArr[i2];
    }

    public LayoutInfo[] getLayoutInfo() {
        return this.mLayoutInfo;
    }

    public int getMaxScroll() {
        return this.mMaxScroll;
    }

    public int getMaxWidth() {
        return this.mWidth;
    }

    public int[] getScrollIndex() {
        return this.mScrollIndex;
    }

    public void init(int i2, LayoutLookup layoutLookup) {
        calculateRatio(i2, layoutLookup);
    }

    private void debugLayoutInfo(LayoutInfo[] layoutInfoArr) {
    }
}
