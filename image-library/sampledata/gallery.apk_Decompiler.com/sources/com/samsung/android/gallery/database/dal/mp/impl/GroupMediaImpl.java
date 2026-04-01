package com.samsung.android.gallery.database.dal.mp.impl;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupMediaImpl extends BaseImpl {
    public GroupMediaImpl(QueryParams queryParams) {
        super(queryParams);
    }

    private void checkParamsGroupType(GroupType groupType) {
        if (!this.mParams.hasGroupType(groupType)) {
            this.mParams.addGroupType(groupType);
            String str = this.TAG;
            Log.w(str, "missed group type : " + groupType);
        }
    }

    public Cursor getBurstShotCursor() {
        GroupType groupType = GroupType.BURST;
        checkParamsGroupType(groupType);
        QueryParams.GroupMediaFilter groupMediaFilter = this.mParams.getGroupMediaFilter();
        int i2 = groupMediaFilter.albumId;
        long j2 = groupMediaFilter.groupId;
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterImage();
        mpFilesTable.filterAlbumID(i2);
        mpFilesTable.filterGroupMediaId(groupType.value, j2);
        mpFilesTable.filterFileStatus();
        if (groupMediaFilter.bestOnly) {
            mpFilesTable.filterGroupMediaBest(true);
        } else if (groupMediaFilter.hasBest) {
            mpFilesTable.filterGroupMediaHasBest();
        } else {
            mpFilesTable.addGroupMediaCountProjection();
        }
        mpFilesTable.clearOrderBy();
        mpFilesTable.orderByName();
        Query buildSelectQuery = mpFilesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "groupId : " + j2);
    }

    public Cursor getGroupShotCursor() {
        String dbKey = this.mParams.getDbKey();
        if (DbKey.FILES_SIMILAR.equals(dbKey)) {
            return getSimilarShotCursor();
        }
        if (DbKey.FILES_BURSTSHOT.equals(dbKey)) {
            return getBurstShotCursor();
        }
        if (DbKey.FILES_SINGLETAKE.equals(dbKey)) {
            return getSingleTakenShotCursor();
        }
        return null;
    }

    public Cursor getSimilarShotCountCursor() {
        checkParamsGroupType(GroupType.SIMILAR);
        QueryParams.GroupMediaFilter groupMediaFilter = this.mParams.getGroupMediaFilter();
        int i2 = groupMediaFilter.albumId;
        long j2 = groupMediaFilter.groupId;
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addProjection("count(*)");
        queryBuilder.addTable("group_contents");
        queryBuilder.addLeftOuterJoin("files", "files._id=group_contents.sec_media_id");
        queryBuilder.andCondition("files.bucket_id=" + i2);
        queryBuilder.andCondition("group_contents.group_id =" + j2);
        Query query = new Query(queryBuilder);
        return getCursor(query, "getSimilarShotCountCursor groupId : " + j2 + ", bucket=" + i2);
    }

    public Cursor getSimilarShotCursor() {
        GroupType groupType = GroupType.SIMILAR;
        checkParamsGroupType(groupType);
        QueryParams.GroupMediaFilter groupMediaFilter = this.mParams.getGroupMediaFilter();
        int i2 = groupMediaFilter.albumId;
        long j2 = groupMediaFilter.groupId;
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterImage();
        mpFilesTable.filterAlbumID(i2);
        mpFilesTable.filterGroupMediaId(groupType.value, j2);
        mpFilesTable.filterFileStatus();
        mpFilesTable.addGroupMediaCountProjection();
        Query buildSelectQuery = mpFilesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "groupId : " + j2);
    }

    public Cursor getSimilarShotForAllCursor() {
        GroupType groupType = GroupType.SIMILAR;
        checkParamsGroupType(groupType);
        QueryParams.GroupMediaFilter groupMediaFilter = this.mParams.getGroupMediaFilter();
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterImage();
        mpFilesTable.filterGroupMediaIds(groupType.value, groupMediaFilter.groupIds);
        mpFilesTable.filterFileStatus();
        mpFilesTable.addGroupMediaCountProjection();
        return getCursor(mpFilesTable.buildSelectQuery(), "getSimilarShotsCursor");
    }

    public Cursor getSingleTakenShotCursor() {
        GroupType groupType = GroupType.SINGLE_TAKEN;
        checkParamsGroupType(groupType);
        QueryParams.GroupMediaFilter groupMediaFilter = this.mParams.getGroupMediaFilter();
        int i2 = groupMediaFilter.albumId;
        long j2 = groupMediaFilter.groupId;
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterAlbumID(i2);
        mpFilesTable.filterGroupMediaId(groupType.value, j2);
        mpFilesTable.filterFileStatus();
        mpFilesTable.addProjectionSefFileTypes();
        mpFilesTable.addGroupMediaCountProjection();
        mpFilesTable.clearOrderBy();
        mpFilesTable.orderByName();
        Query buildSelectQuery = mpFilesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "groupId : " + j2);
    }

    public Cursor getSingleTakenShotForAllCursor() {
        GroupType groupType = GroupType.SINGLE_TAKEN;
        checkParamsGroupType(groupType);
        QueryParams.GroupMediaFilter groupMediaFilter = this.mParams.getGroupMediaFilter();
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterGroupMediaIds(groupType.value, groupMediaFilter.groupIds);
        mpFilesTable.filterFileStatus();
        mpFilesTable.addProjectionSefFileTypes();
        mpFilesTable.addGroupMediaCountProjection();
        mpFilesTable.clearOrderBy();
        mpFilesTable.orderByName();
        return getCursor(mpFilesTable.buildSelectQuery(), "getSingleTakenShotsCursor");
    }

    public String tag() {
        return "GroupMediaImpl";
    }
}
