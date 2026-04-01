package com.samsung.android.gallery.database.dal.mp.impl;

import android.database.Cursor;
import android.database.MergeCursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.query.RawQueryExecutor;
import com.samsung.android.gallery.database.dal.abstraction.table.BaseView;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ImplHelper {
    private static void filterUri(SecFilesTable secFilesTable, String str) {
        long idFromUri = getIdFromUri(str);
        if (idFromUri < 0) {
            throw new IllegalArgumentException(C0212a.l("uri : ", str));
        } else if (MediaUri.getInstance().isSecMediaUri(str)) {
            secFilesTable.filterId(idFromUri);
        } else {
            secFilesTable.filterMediaID(idFromUri);
        }
    }

    public static void filterWithParams(SecFilesTable secFilesTable, QueryParams queryParams) {
        QueryBuilder queryBuilder = secFilesTable.getQueryBuilder();
        long j2 = queryParams.maxFileId;
        int limitSize = queryParams.getLimitSize();
        if (j2 > 0) {
            queryBuilder.andCondition("_id < " + j2);
        }
        if (limitSize > 0) {
            queryBuilder.clearOrderBy();
            queryBuilder.addOrderBy("A._id DESC");
            int limitOffset = queryParams.getLimitOffset();
            if (limitOffset != 0) {
                secFilesTable.limit(limitOffset, limitSize);
            } else {
                queryBuilder.limit(String.valueOf(limitSize));
            }
        }
        long fileId = queryParams.getFileId();
        if (fileId != -1) {
            secFilesTable.clearSelection();
            secFilesTable.filterId(fileId);
            if (queryParams.mTrashOnly) {
                secFilesTable.getQueryBuilder().andCondition("A.is_trashed=1");
            }
        }
        long mediaId = queryParams.getMediaId();
        if (mediaId != -1) {
            secFilesTable.clearSelection();
            secFilesTable.filterMediaID(mediaId);
        }
        String fileIds = queryParams.getFileIds();
        if (fileIds != null) {
            secFilesTable.clearSelection();
            secFilesTable.filterIds(fileIds);
        }
        String mediaIds = queryParams.getMediaIds();
        if (mediaIds != null) {
            secFilesTable.clearSelection();
            secFilesTable.filterMediaIDs(mediaIds);
        }
        if (queryParams.getRequiredColumns() != null) {
            queryBuilder.clearProjection();
            if (queryParams.getRequiredColumns().contains("__storageType")) {
                queryBuilder.addProjection("A.is_cloud", "__storageType");
            }
            queryBuilder.clearOrderBy();
        }
        String[] uriArray = queryParams.getUriArray();
        if (uriArray != null && uriArray.length > 0) {
            for (String filterUri : uriArray) {
                filterUri(secFilesTable, filterUri);
            }
        }
    }

    public static Query getFileIdOnlyQuery(QueryParams queryParams, QueryBuilder queryBuilder) {
        queryBuilder.clearProjection();
        queryBuilder.addProjection("A._id as _id");
        int limitSize = queryParams.getLimitSize();
        if (limitSize > 0) {
            int limitOffset = queryParams.getLimitOffset();
            queryBuilder.limit(limitOffset + GlobalPostProcInternalPPInterface.SPLIT_REGEX + limitSize);
        }
        QueryBuilder addFromSelect = new QueryBuilder().addFromSelect(queryBuilder.build());
        addFromSelect.addProjection("group_concat(_id)");
        return new Query(addFromSelect);
    }

    public static Query getFileIds(QueryParams queryParams, SecFilesTable secFilesTable) {
        QueryBuilder queryBuilder = secFilesTable.getQueryBuilder();
        queryBuilder.clearProjection();
        queryBuilder.addProjection("A._id as _id");
        long j2 = queryParams.maxFileId;
        int limitSize = queryParams.getLimitSize();
        if (j2 > 0) {
            queryBuilder.andCondition("_id < " + j2);
        }
        if (limitSize > 0) {
            queryBuilder.clearOrderBy();
            queryBuilder.addOrderBy("A._id DESC");
            queryBuilder.limit(String.valueOf(limitSize));
        }
        QueryBuilder addFromSelect = new QueryBuilder().addFromSelect(queryBuilder.build());
        addFromSelect.addProjection("group_concat(_id)");
        return new Query(addFromSelect);
    }

    public static final long getIdFromUri(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return UnsafeCast.toLong(str.substring(str.lastIndexOf("/") + 1), -1);
    }

    private static Cursor getRealRatioCursorHelper(SecFilesTable secFilesTable, int i2, RawQueryExecutor rawQueryExecutor) {
        return getRealRatioCursorHelper(secFilesTable.getQueryBuilder(), secFilesTable.getColumnDateTaken(), i2, rawQueryExecutor);
    }

    public static Cursor getRealRatioMergedCursor(SecFilesTable secFilesTable, RawQueryExecutor rawQueryExecutor) {
        ArrayList arrayList = new ArrayList();
        TimeTickLog timeTickLog = new TimeTickLog("getRealRatioCursor");
        Cursor realRatioCursorHelper = getRealRatioCursorHelper(secFilesTable, 0, rawQueryExecutor);
        int i2 = 0;
        while (realRatioCursorHelper != null && realRatioCursorHelper.moveToFirst() && realRatioCursorHelper.getCount() == 1 && realRatioCursorHelper.getInt(realRatioCursorHelper.getColumnIndex("__count")) != 0) {
            arrayList.add(realRatioCursorHelper);
            if (realRatioCursorHelper.getInt(realRatioCursorHelper.getColumnIndex("__count")) < 800000) {
                break;
            }
            i2 += 800000;
            realRatioCursorHelper = getRealRatioCursorHelper(secFilesTable, i2, rawQueryExecutor);
        }
        timeTickLog.tock(500);
        if (arrayList.size() == 1) {
            return (Cursor) arrayList.get(0);
        }
        if (arrayList.size() > 1) {
            return new MergeCursor((Cursor[]) arrayList.toArray(new Cursor[0]));
        }
        return null;
    }

    public static final List<String> makeMediaIdList(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            String substring = str.substring(str.lastIndexOf("/") + 1);
            if (UnsafeCast.toLong(substring, -1) > 0) {
                arrayList.add(substring);
            }
        }
        return arrayList;
    }

    public static final String makeMediaIds(List<String> list) {
        return TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, list);
    }

    private static Cursor getRealRatioCursorHelper(BaseView baseView, int i2, RawQueryExecutor rawQueryExecutor) {
        return getRealRatioCursorHelper(baseView.buildSelectQuery().getQueryBuilder(), baseView.getColumnDateTaken(), i2, rawQueryExecutor);
    }

    private static Cursor getRealRatioCursorHelper(QueryBuilder queryBuilder, String str, int i2, RawQueryExecutor rawQueryExecutor) {
        queryBuilder.clearProjection();
        queryBuilder.addProjection("A._id, A." + str + ", A.width, A.height, A.orientation");
        queryBuilder.limit(i2 + ", 800000");
        QueryBuilder addFromSelect = new QueryBuilder().addFromSelect(queryBuilder.build());
        addFromSelect.addProjection("count(*)", "__count");
        addFromSelect.addProjection("group_concat(case when height is null or height=0 or width is null or width=0 then 1 else substr(case when orientation=90 or orientation=270 then 1.0*height/width else 1.0*width/height end,1,4) end)", "__widthList");
        return rawQueryExecutor.getCursor(new Query(addFromSelect), "getRealRatioDataCursor");
    }

    public static Cursor getRealRatioMergedCursor(BaseView baseView, RawQueryExecutor rawQueryExecutor) {
        ArrayList arrayList = new ArrayList();
        TimeTickLog timeTickLog = new TimeTickLog("getRealRatioCursor");
        Cursor realRatioCursorHelper = getRealRatioCursorHelper(baseView, 0, rawQueryExecutor);
        int i2 = 0;
        while (realRatioCursorHelper != null && realRatioCursorHelper.moveToFirst() && realRatioCursorHelper.getCount() == 1 && realRatioCursorHelper.getInt(realRatioCursorHelper.getColumnIndex("__count")) != 0) {
            arrayList.add(realRatioCursorHelper);
            if (realRatioCursorHelper.getInt(realRatioCursorHelper.getColumnIndex("__count")) < 800000) {
                break;
            }
            i2 += 800000;
            realRatioCursorHelper = getRealRatioCursorHelper(baseView, i2, rawQueryExecutor);
        }
        timeTickLog.tock(500);
        if (arrayList.size() == 1) {
            return (Cursor) arrayList.get(0);
        }
        if (arrayList.size() > 1) {
            return new MergeCursor((Cursor[]) arrayList.toArray(new Cursor[0]));
        }
        return null;
    }
}
