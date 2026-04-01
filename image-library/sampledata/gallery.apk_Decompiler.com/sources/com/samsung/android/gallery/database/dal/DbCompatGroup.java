package com.samsung.android.gallery.database.dal;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.GroupType;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DbCompatGroup {
    public static Cursor getGroupCursor(FileItemInterface fileItemInterface) {
        return getGroupCursor(fileItemInterface, fileItemInterface.getBucketID());
    }

    public static QueryParams getGroupShotQueryParams(FileItemInterface fileItemInterface) {
        GroupType groupType;
        String str = null;
        if (fileItemInterface.isBurstShot()) {
            groupType = GroupType.BURST;
        } else if (fileItemInterface.isSimilarShot()) {
            groupType = GroupType.SIMILAR;
        } else if (fileItemInterface.isSingleTakenShot()) {
            groupType = GroupType.SINGLE_TAKEN;
        } else {
            groupType = null;
        }
        if (groupType == GroupType.BURST) {
            str = DbKey.FILES_BURSTSHOT;
        } else if (groupType == GroupType.SIMILAR) {
            str = DbKey.FILES_SIMILAR;
        } else if (groupType == GroupType.SINGLE_TAKEN) {
            str = DbKey.FILES_SINGLETAKE;
        }
        return new QueryParams(str).setGroupMediaFilter(fileItemInterface.getAlbumID(), fileItemInterface.getGroupMediaId()).setGroupTypes(groupType);
    }

    public static Cursor getGroupCursor(GroupType groupType, int i2, long j2) {
        String str;
        if (groupType == GroupType.BURST) {
            str = DbKey.FILES_BURSTSHOT;
        } else {
            str = groupType == GroupType.SIMILAR ? DbKey.FILES_SIMILAR : groupType == GroupType.SINGLE_TAKEN ? DbKey.FILES_SINGLETAKE : null;
        }
        if (str == null) {
            return null;
        }
        return DbCompat.query(new QueryParams(str).setGroupMediaFilter(i2, j2).setGroupTypes(groupType));
    }

    public static Cursor getGroupCursor(GroupType groupType, int i2, long j2, boolean z) {
        String str;
        if (groupType == GroupType.BURST) {
            str = DbKey.FILES_BURSTSHOT;
        } else {
            str = groupType == GroupType.SIMILAR ? DbKey.FILES_SIMILAR : groupType == GroupType.SINGLE_TAKEN ? DbKey.FILES_SINGLETAKE : null;
        }
        QueryParams groupTypes = new QueryParams(str).setGroupMediaFilter(i2, j2).setGroupTypes(groupType);
        if (z) {
            groupTypes.onlyTrashed();
        }
        if (str == null) {
            return null;
        }
        return DbCompat.query(groupTypes);
    }

    public static Cursor getGroupCursor(FileItemInterface fileItemInterface, int i2) {
        GroupType groupType;
        if (fileItemInterface.isBurstShot()) {
            groupType = GroupType.BURST;
        } else {
            groupType = fileItemInterface.isSimilarShot() ? GroupType.SIMILAR : fileItemInterface.isSingleTakenShot() ? GroupType.SINGLE_TAKEN : null;
        }
        return getGroupCursor(groupType, i2, fileItemInterface.getGroupMediaId());
    }
}
