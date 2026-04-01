package com.samsung.android.gallery.module.dataset.tables;

import A.a;
import android.database.Cursor;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.tables.DefaultTable;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DataTable extends DefaultTable<DataRecord> {
    static volatile boolean SKIP_VIDEO_TIMESTAMP_UPDATE;
    private boolean isQueryOnDemand;
    private CursorReader mCursorReader;
    String mDataStamp;
    private boolean mDisableDayMerge;
    private boolean mHasPostProcessing;
    HashMap<Long, Integer> mIndexFileId;
    HashMap<Long, Integer> mIndexMediaId;
    private final ClusterIndexer[] mIndexer = new ClusterIndexer[3];
    private boolean mPartialLoaded;

    static {
        boolean z;
        if (SdkConfig.atLeast(SdkConfig.GED.R) || !Features.isEnabled(Features.USE_SEC_MP)) {
            z = false;
        } else {
            z = true;
        }
        SKIP_VIDEO_TIMESTAMP_UPDATE = z;
    }

    public DataTable(Cursor cursor) {
        super(cursor);
    }

    private boolean isItemPostProcessingType(MediaItem mediaItem) {
        return mediaItem.isPostProcessing();
    }

    private boolean isUsed(int i2) {
        DataRecord dataRecord;
        if (i2 < this.mDataList.size() && (dataRecord = (DataRecord) this.mDataList.get(i2)) != null && dataRecord.isUsed()) {
            return true;
        }
        return false;
    }

    private void updateIndex(int i2, long j2, long j3, long j8) {
        this.mIndexMediaId.put(Long.valueOf(j2), Integer.valueOf(i2));
        this.mIndexFileId.put(Long.valueOf(j3), Integer.valueOf(i2));
    }

    public void close() {
        super.close();
        for (ClusterIndexer clusterIndexer : this.mIndexer) {
            if (clusterIndexer != null) {
                clusterIndexer.mTimelineItemMap.clear();
            }
        }
        ClusterIndexer[] clusterIndexerArr = this.mIndexer;
        clusterIndexerArr[0] = null;
        clusterIndexerArr[1] = null;
        clusterIndexerArr[2] = null;
    }

    public ClusterIndexer createClusterIndexer(int i2) {
        ClusterIndexer clusterIndexer = new ClusterIndexer(!this.mDisableDayMerge);
        clusterIndexer.calculate(this, i2, getLoadedCount());
        this.mIndexer[i2] = clusterIndexer;
        return clusterIndexer;
    }

    public CursorReader createCursorReader(Cursor cursor) {
        return new CursorReader(cursor);
    }

    public MediaItem createMediaItem(Cursor cursor, int i2) {
        return this.mCursorReader.createMediaItem(i2);
    }

    public boolean equals(Object obj) {
        String str;
        Cursor rawQuery;
        if (!(obj instanceof DataTable)) {
            Log.e(this.TAG, "equals failed. wrong object");
            return false;
        }
        DataTable dataTable = (DataTable) obj;
        if (TextUtils.isEmpty(this.mDataStamp) || TextUtils.isEmpty(dataTable.mDataStamp)) {
            Log.d(this.TAG, "equals failed. table compare not supported");
            return false;
        }
        if (this.mRealCount == dataTable.mRealCount) {
            if (this.mDataStamp.equals(dataTable.mDataStamp)) {
                return true;
            }
            if (SKIP_VIDEO_TIMESTAMP_UPDATE) {
                try {
                    String[] split = this.mDataStamp.split("_");
                    String[] split2 = dataTable.mDataStamp.split("_");
                    String str2 = split[2];
                    if (split[0].equals(split2[0]) && split[1].equals(split2[1])) {
                        if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                            str = "generation_modified";
                        } else {
                            str = "date_touched";
                        }
                        SecMpQueryExecutor secMpQueryExecutor = new SecMpQueryExecutor();
                        rawQuery = secMpQueryExecutor.rawQuery("select _id from files where media_type=1 and " + str + ">" + str2 + " limit 1", "");
                        if (rawQuery != null) {
                            if (rawQuery.getCount() != 0) {
                                rawQuery.close();
                            }
                        }
                        dataTable.setVideoTouched(true);
                        Log.d(this.TAG, "equals except video update");
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        return true;
                    }
                } catch (Exception e) {
                    a.s(e, new StringBuilder("equals failed to check video update e="), this.TAG);
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
        }
        String str3 = this.TAG;
        Log.d(str3, "not equals " + dataTable + ArcCommonLog.TAG_COMMA + toString());
        return false;
        throw th;
    }

    public void fillDataRecords(DataTable dataTable) {
        MediaItem mediaItem;
        if (dataTable == null) {
            Log.d(this.TAG, "fillDataRecords : datatable is null.");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        boolean isPartialLoaded = dataTable.isPartialLoaded();
        int i2 = 0;
        int i7 = 0;
        int i8 = 0;
        while (true) {
            int i10 = this.mRealCount;
            if (i2 < i10) {
                if (dataTable.isUsed(i2)) {
                    if (isPartialLoaded) {
                        mediaItem = dataTable.get(i2);
                    } else {
                        mediaItem = null;
                    }
                    if (mediaItem != null) {
                        setToDataList(i2, mediaItem);
                        onDataLoaded(i2, mediaItem);
                    } else if (!this.isQueryOnDemand) {
                        load(i2);
                    }
                    i8++;
                } else {
                    i7++;
                }
                i2++;
            } else {
                this.mCount = i10;
                a.x(a.h(i7, i8, "fillDataRecords {P=", ",H=0,M=", "} +"), currentTimeMillis, this.TAG);
                return;
            }
        }
    }

    public int findPosition(long j2) {
        Integer num = this.mIndexMediaId.get(Long.valueOf(j2));
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public int findPositionByFileId(long j2) {
        Integer num = this.mIndexFileId.get(Long.valueOf(j2));
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public ChapterIndexer getChapterIndexer() {
        return new ChapterIndexer(this);
    }

    public CleanOutDuplicateClusterIndexer getCleanOutDuplicateClusterIndexer() {
        CleanOutDuplicateClusterIndexer cleanOutDuplicateClusterIndexer = new CleanOutDuplicateClusterIndexer();
        cleanOutDuplicateClusterIndexer.calculate(this);
        return cleanOutDuplicateClusterIndexer;
    }

    public ClusterIndexer getClusterIndexer(int i2) {
        return this.mIndexer[i2];
    }

    public final CursorReader getCursorReader() {
        return this.mCursorReader;
    }

    public String getDataStamp() {
        return this.mDataStamp;
    }

    public int getFakeLoadingCount() {
        return 120;
    }

    public MediaItem getTemp(int i2) {
        try {
            DataRecord dataRecord = (DataRecord) this.mDataList.get(i2);
            if (dataRecord == null) {
                return null;
            }
            return dataRecord.mMediaItem;
        } catch (Exception unused) {
            return null;
        }
    }

    public boolean hasPostProcessing() {
        return this.mHasPostProcessing;
    }

    public boolean isDuplicated(Cursor cursor) {
        if (this.mCursor == cursor) {
            return true;
        }
        return false;
    }

    public boolean isPartialLoaded() {
        return this.mPartialLoaded;
    }

    public MediaItem loadAndGetPrimitive(int i2) {
        return this.mCursorReader.loadAndGetPrimitive(i2);
    }

    public void onConstruct(Cursor cursor) {
        super.onConstruct(cursor);
        this.mIndexMediaId = new HashMap<>(this.mRealCount);
        this.mIndexFileId = new HashMap<>(this.mRealCount);
        this.mCursorReader = createCursorReader(cursor);
        int columnIndex = cursor.getColumnIndex("__dataStamp");
        if (columnIndex != -1 && cursor.getPosition() < 0 && cursor.moveToFirst()) {
            this.mDataStamp = cursor.getString(columnIndex);
        }
    }

    public void onDataLoaded(int i2, MediaItem mediaItem) {
        long mediaId = mediaItem.getMediaId();
        long fileId = mediaItem.getFileId();
        long dateModified = mediaItem.getDateModified();
        if (isItemPostProcessingType(mediaItem)) {
            this.mHasPostProcessing = true;
        }
        updateIndex(i2, mediaId, fileId, dateModified);
    }

    public List<Long> readAllFileId() {
        return this.mCursorReader.readAllFileId();
    }

    public void remove(int i2) {
        DataRecord dataRecord = (DataRecord) this.mDataList.remove(i2);
        if (dataRecord != null) {
            this.mIndexFileId.remove(Long.valueOf(dataRecord.mMediaItem.getFileId()));
            this.mIndexMediaId.remove(Long.valueOf(dataRecord.mMediaItem.getMediaId()));
        }
    }

    public void set(int i2, MediaItem mediaItem) {
        setToDataList(i2, mediaItem);
        onDataLoaded(i2, mediaItem);
    }

    public DataTable setDisableDayMerge() {
        this.mDisableDayMerge = true;
        return this;
    }

    public void setPartialLoaded(boolean z) {
        this.mPartialLoaded = z;
    }

    public DataTable setQueryOnDemand() {
        this.isQueryOnDemand = true;
        return this;
    }

    public String tag() {
        return "DataTable";
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("DataTable{");
        sb2.append(this.mDataStamp);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mRealCount);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mPartialLoaded);
        Cursor cursor = this.mCursor;
        if (cursor == null || cursor.isClosed()) {
            str = ",C";
        } else {
            str = "";
        }
        return C0212a.p(sb2, str, "}");
    }

    public DataRecord createRecordInstance(MediaItem mediaItem) {
        return new DataRecord(mediaItem);
    }

    public DataTable(Cursor cursor, DefaultTable.OnLoadDoneListener onLoadDoneListener, int i2) {
        super(cursor, onLoadDoneListener, i2);
    }
}
