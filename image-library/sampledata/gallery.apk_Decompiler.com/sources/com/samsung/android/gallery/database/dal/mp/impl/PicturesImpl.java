package com.samsung.android.gallery.database.dal.mp.impl;

import N2.j;
import android.database.Cursor;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpLocationView;
import com.samsung.android.gallery.database.dal.util.QueryUtils;
import com.samsung.android.gallery.database.dbtype.DateType;
import com.samsung.android.gallery.database.lockedalbum.LockedAlbum;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PicturesImpl extends BaseImpl {
    public PicturesImpl(QueryParams queryParams) {
        super(queryParams);
    }

    private void addGroupConcat(StringJoiner stringJoiner, String str) {
        stringJoiner.add("group_concat(ifnull(" + str + ",0),'|') as " + str);
    }

    private Cursor getSpanCursorHelper(SecFilesTable secFilesTable, boolean z) {
        QueryBuilder queryBuilder = secFilesTable.getQueryBuilder();
        int i2 = StorageInfo.getDefault().buckets().screenShot;
        queryBuilder.clearProjection();
        queryBuilder.addProjection("A._id as __id, A.bucket_id, A.media_type, A.group_id");
        QueryBuilder addFromSelect = new QueryBuilder().addFromSelect(queryBuilder.build());
        if (z) {
            addFromSelect.addProjection(getDateTakenColumnName() + ">>31", "clusterIndex");
        }
        addFromSelect.addProjection("count(__id)", "__count");
        addFromSelect.addProjection("group_concat(case when bucket_id=" + i2 + " then 0 else (case when media_type=3 then 1 else (case when __id in (select sec_media_id from group_contents where group_type=2) then 1 else 0 end) end) end)", "__spanList");
        if (z) {
            addFromSelect.groupBy("clusterIndex");
            addFromSelect.addOrderBy("clusterIndex desc");
        }
        return getCursor(new Query(addFromSelect), "getSpanDataCursor");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable createTimelineFilesTable(boolean r9) {
        /*
            r8 = this;
            com.samsung.android.gallery.database.dal.mp.table.MpFilesTable r0 = new com.samsung.android.gallery.database.dal.mp.table.MpFilesTable
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r8.mParams
            r0.<init>(r1)
            r1 = 1
            r0.filterGroupMediaBest(r1)
            r0.filterStorageType()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r8.mParams
            long r2 = r1.mStartTime
            r4 = -1
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x0022
            long r6 = r1.mEndTime
            int r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0022
            r0.filterCreationTime(r2, r6)
            goto L_0x0025
        L_0x0022:
            r8.setDateTakenFrom(r0)
        L_0x0025:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r8.mParams
            java.lang.String r1 = r1.getFileIds()
            if (r1 == 0) goto L_0x0036
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r8.mParams
            java.lang.String r1 = r1.getFileIds()
            r0.filterIds(r1)
        L_0x0036:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams$TargetDbTypes r1 = com.samsung.android.gallery.database.dal.abstraction.query.QueryParams.TargetDbTypes.SEC
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r2 = r8.mParams
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams$TargetDbTypes r2 = r2.mTargetDb
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0053
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = r8.mParams
            int r1 = r1.getOsVersion()
            r2 = 30
            if (r1 < r2) goto L_0x0053
            if (r9 == 0) goto L_0x0053
            java.lang.String r9 = "datetime_id_idx"
            r0.setIndex(r9)
        L_0x0053:
            boolean r9 = com.samsung.android.gallery.support.utils.PocFeatures.QUICK_SEARCH
            if (r9 == 0) goto L_0x006b
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r8 = r8.mParams
            java.lang.String r8 = r8.getMediaIdsFilter()
            java.util.Optional r8 = java.util.Optional.ofNullable(r8)
            o4.a r9 = new o4.a
            r1 = 13
            r9.<init>(r1, r0)
            r8.ifPresent(r9)
        L_0x006b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.database.dal.mp.impl.PicturesImpl.createTimelineFilesTable(boolean):com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable");
    }

    public Cursor findHiddenFiles() {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addTable("files");
        queryBuilder.addProjection("_id, _data");
        if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            queryBuilder.andCondition("media_type in (1,3) and is_cloud != 2 and width is null");
        } else {
            queryBuilder.andCondition("media_type in (1,3) and is_cloud != 2 and (" + getDateTakenColumnName() + " is null or _size is null)");
        }
        if (this.mParams.minFileId > 0) {
            queryBuilder.andCondition("_id > " + this.mParams.minFileId);
        }
        return getCursor(new Query(queryBuilder), "findHiddenFiles");
    }

    public Cursor findWrongDateTime() {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addTable("files");
        queryBuilder.addProjection("_id, _data");
        queryBuilder.andCondition("media_type in (1,3) and " + getDateTakenColumnName() + "<=-62167219200000");
        if (this.mParams.minFileId > 0) {
            queryBuilder.andCondition("_id > " + this.mParams.minFileId);
        }
        return getCursor(new Query(queryBuilder), "findWrongDateTime");
    }

    public Cursor getRealRatioDataCursor() {
        return ImplHelper.getRealRatioMergedCursor(createTimelineFilesTable(true), this.mQueryExecutor);
    }

    public Cursor getSpanDataCursor() {
        return getSpanCursorHelper(createTimelineFilesTable(true), false);
    }

    public Cursor getTimelineDateCursor(DateType dateType) {
        SecFilesTable createTimelineFilesTable = createTimelineFilesTable(false);
        createTimelineFilesTable.modifyForTimelineDateData(dateType, getDateTakenColumnName());
        return getCursor(QueryUtils.updateQueryForMultipleLocations(createTimelineFilesTable.buildSelectQuery(), new MpLocationView(this.mParams)), "getTimelineDateData");
    }

    public int getTimelineFileCount() {
        SecFilesTable createTimelineFilesTable = createTimelineFilesTable(false);
        createTimelineFilesTable.resetProjectionForCursorCount(false);
        Cursor cursor = getCursor(createTimelineFilesTable.buildSelectQuery(), "getTimelineFileCount");
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int i2 = cursor.getInt(0);
                    cursor.close();
                    return i2;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return 0;
        throw th;
    }

    public Cursor getTimelineFileCursor() {
        return getTimelineFileCursor(this.mParams.getLimitOffset(), this.mParams.getLimitSize());
    }

    public Cursor getTimelineFileIds() {
        Query fileIds = ImplHelper.getFileIds(this.mParams, createTimelineFilesTable(false));
        return getCursor(fileIds, "getTimelineFileIds : " + this.mParams.maxFileId + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mParams.getLimitSize());
    }

    public Cursor getTimelineFileIdsOrdered() {
        Query fileIdOnlyQuery = ImplHelper.getFileIdOnlyQuery(this.mParams, createTimelineFilesTable(true).getQueryBuilder());
        return getCursor(fileIdOnlyQuery, "getTimelineFileIdsOrdered " + this.mParams.getLimitOffset() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mParams.getLimitSize());
    }

    public Cursor getTimelineYear() {
        SecFilesTable createTimelineFilesTable = createTimelineFilesTable(false);
        createTimelineFilesTable.modifyQueryForYear();
        createTimelineFilesTable.getQueryBuilder().groupBy("__hour");
        if (PocFeatures.SUPPORT_LOCKED_ALBUM) {
            createTimelineFilesTable.filterBucketIds(LockedAlbum.getInstance().getBucketList(), false);
        }
        StringJoiner stringJoiner = new StringJoiner(ArcCommonLog.TAG_COMMA);
        stringJoiner.add("__year");
        stringJoiner.add("count(*) as __count");
        addGroupConcat(stringJoiner, "__absID");
        addGroupConcat(stringJoiner, "__absPath");
        addGroupConcat(stringJoiner, "__width");
        addGroupConcat(stringJoiner, "__height");
        addGroupConcat(stringJoiner, "__orientation");
        addGroupConcat(stringJoiner, "__dateModified");
        addGroupConcat(stringJoiner, "__dateTaken");
        addGroupConcat(stringJoiner, "__mediaType");
        addGroupConcat(stringJoiner, "__size");
        addGroupConcat(stringJoiner, "__mimeType");
        addGroupConcat(stringJoiner, "__storageType");
        return this.mQueryExecutor.rawQuery("select " + stringJoiner + " from (" + createTimelineFilesTable.buildSelectQuery() + ") group by __year order by __year desc", "getTimelineYear");
    }

    public String tag() {
        return "PicturesImpl";
    }

    public Cursor getTimelineFileCursor(int i2, int i7) {
        SecFilesTable createTimelineFilesTable = createTimelineFilesTable(true);
        createTimelineFilesTable.addDataStamp();
        if (createTimelineFilesTable.getDefaultIndex() != null) {
            createTimelineFilesTable.setIndex(createTimelineFilesTable.getDefaultIndex());
        }
        if (i7 > 0) {
            createTimelineFilesTable.addProjectionDay();
            createTimelineFilesTable.limit(i2, i7);
        }
        return getCursor(createTimelineFilesTable.buildSelectQuery(), j.b(i2, i7, "getTimelineFileData limit ", GlobalPostProcInternalPPInterface.SPLIT_REGEX));
    }
}
