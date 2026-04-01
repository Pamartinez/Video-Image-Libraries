package com.samsung.android.gallery.database.dal.mp.impl;

import A.a;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import com.samsung.android.gallery.database.dal.abstraction.CameraCursor;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import java.util.List;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilesImpl extends BaseImpl {
    public FilesImpl(QueryParams queryParams) {
        super(queryParams);
    }

    @Deprecated
    public Cursor getCameraCursor(String str) {
        Uri parse = Uri.parse(str);
        ContentResolver contentResolver = this.mQueryExecutor.getContentResolver();
        if (contentResolver == null) {
            Log.e(this.TAG, "fail get getCameraCursor");
            return null;
        }
        Cursor query = contentResolver.query(parse, new String[]{"*"}, "_id=" + parse.getLastPathSegment(), (String[]) null, (String) null, (CancellationSignal) null);
        if (query != null) {
            String str2 = this.TAG;
            Log.d(str2, "PPP = " + CursorHelper.dumpCursor(query));
            query.moveToFirst();
            return new CameraCursor(query);
        }
        a.u("fail get getCameraCursor : ", str, this.TAG);
        return null;
    }

    public Cursor getCursorByDataLike(String str) {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterDataLike(str);
        return getCursor(mpFilesTable.buildSelectQuery(), "getCursorByDataLike");
    }

    public Cursor getCursorByPath(String str) {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterFilePathOnly("\"" + str + "\"");
        return getCursor(mpFilesTable.buildSelectQuery(), "getCursorByPath");
    }

    public Cursor getCursorByUri(String str) {
        if (Features.isEnabled(Features.SUPPORT_PPP_V2) && MediaUri.getInstance().isCameraUri(str)) {
            return getCameraCursor(str);
        }
        long idFromUri = ImplHelper.getIdFromUri(str);
        if (idFromUri < 0) {
            return null;
        }
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        if (this.mParams.getOsVersion() < 29 || !MediaUri.getInstance().isSecMediaUri(str)) {
            mpFilesTable.filterMediaID(idFromUri);
            mpFilesTable.addGroupMediaCountProjection(mpFilesTable.getQueryBuilder());
        } else {
            mpFilesTable.filterId(idFromUri);
            mpFilesTable.addGroupMediaCountProjection(mpFilesTable.getQueryBuilder());
        }
        Query buildSelectQuery = mpFilesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "uri : " + str);
    }

    public Cursor getCursorByUris(String[] strArr) {
        String makeMediaIds;
        boolean z = false;
        if (this.mParams.getOsVersion() >= 29 && MediaUri.getInstance().isSecMediaUri(strArr[0])) {
            z = true;
        }
        List<String> makeMediaIdList = ImplHelper.makeMediaIdList(strArr);
        if (makeMediaIdList.isEmpty() || (makeMediaIds = ImplHelper.makeMediaIds(makeMediaIdList)) == null) {
            return null;
        }
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        if (z) {
            mpFilesTable.filterIds(makeMediaIds);
            mpFilesTable.clearOrderBy();
            mpFilesTable.addGroupMediaCountProjection(mpFilesTable.getQueryBuilder());
            mpFilesTable.addOrderByIds("A._id", makeMediaIdList);
        } else {
            mpFilesTable.filterMediaIDs(makeMediaIds);
            mpFilesTable.clearOrderBy();
            mpFilesTable.addGroupMediaCountProjection(mpFilesTable.getQueryBuilder());
            mpFilesTable.addOrderByIds(mpFilesTable.getColumnNameMediaId(), makeMediaIdList);
        }
        return getCursor(mpFilesTable.buildSelectQuery(), "ids : ".concat(makeMediaIds));
    }

    public Cursor getFileCursor() {
        if (this.mParams.getMediaId() != -1 || this.mParams.getFileId() != -1 || this.mParams.getMediaIds() != null || this.mParams.getFileIds() != null) {
            return getFileCursorById();
        }
        if (this.mParams.getRequiredColumns() != null) {
            return getSpecificColumnsCursor();
        }
        if (this.mParams.getUriArray() != null) {
            String[] uriArray = this.mParams.getUriArray();
            if (uriArray.length == 1) {
                return getCursorByUri(uriArray[0]);
            }
            if (uriArray.length > 1) {
                return getCursorByUris(uriArray);
            }
        } else if (this.mParams.getFilePath() != null) {
            return getCursorByPath(this.mParams.getFilePath());
        } else {
            String str = this.mParams.mDataLike;
            if (str != null) {
                return getCursorByDataLike(str);
            }
        }
        return getPicturesCursor();
    }

    public Cursor getFileCursorById() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.addGroupMediaCountProjection(mpFilesTable.getQueryBuilder());
        ImplHelper.filterWithParams(mpFilesTable, this.mParams);
        return getCursor(mpFilesTable.buildSelectQuery(), "getFileCursorById");
    }

    public Cursor getPicturesCursor() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        QueryParams queryParams = this.mParams;
        long j2 = queryParams.mStartTime;
        if (j2 != -1 && queryParams.mEndTime == -1) {
            mpFilesTable.filterByFromTime(String.valueOf(j2), true);
        } else if (j2 != -1) {
            long j3 = queryParams.mEndTime;
            if (j3 != -1) {
                mpFilesTable.filterCreationTime(j2 - 1, j3);
            }
        }
        mpFilesTable.filterGroupMediaBest(true);
        mpFilesTable.filterStorageType();
        if (this.mParams.getLimitSize() > 0) {
            mpFilesTable.limit(this.mParams.getLimitSize());
        }
        if (this.mParams.getOrder() != null) {
            mpFilesTable.getQueryBuilder().clearOrderBy().addOrderBy(this.mParams.getOrder());
        }
        Query buildSelectQuery = mpFilesTable.buildSelectQuery();
        return getCursor(buildSelectQuery, "getPicturesCursor : " + this.mParams);
    }

    public Cursor getSpecificColumnsCursor() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        ImplHelper.filterWithParams(mpFilesTable, this.mParams);
        return getCursor(mpFilesTable.buildSelectQuery(), "getSpecificColumnsCursor");
    }

    public String tag() {
        return "FilesImpl";
    }
}
