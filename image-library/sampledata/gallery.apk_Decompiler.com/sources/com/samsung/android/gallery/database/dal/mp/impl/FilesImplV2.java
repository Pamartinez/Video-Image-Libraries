package com.samsung.android.gallery.database.dal.mp.impl;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.query.RawQueryExecutor;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dal.abstraction.table.SecLocationView;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTableV2;
import com.samsung.android.gallery.database.dal.mp.table.MpLocationView;
import com.samsung.android.gallery.database.dal.util.QueryUtils;
import com.samsung.android.gallery.database.dbtype.DateType;
import com.samsung.android.gallery.database.dbtype.SortByType;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilesImplV2 extends BaseImpl {
    public FilesImplV2(QueryParams queryParams) {
        super(queryParams);
    }

    private Query createIdConcatQuery(QueryBuilder queryBuilder) {
        queryBuilder.clearProjection();
        queryBuilder.addProjection("A._id as _id");
        QueryBuilder addFromSelect = new QueryBuilder().addFromSelect(queryBuilder.build());
        addFromSelect.addProjection("group_concat(_id)");
        return new Query(addFromSelect);
    }

    public Cursor getCursor() {
        return getCursor(new MpFilesTableV2(this.mParams).buildSelectQuery(), "getAllPicCursor");
    }

    public Cursor getDataStamp() {
        RawQueryExecutor rawQueryExecutor = this.mQueryExecutor;
        String dataStampQuery = new MpFilesTable(this.mParams).getDataStampQuery();
        return rawQueryExecutor.rawQuery(dataStampQuery, "getDataStamp : " + this.mParams);
    }

    public Cursor getDayCursor() {
        MpFilesTableV2 mpFilesTableV2 = new MpFilesTableV2(this.mParams);
        mpFilesTableV2.modifyForTimelineDateData(DateType.DAY, getDateTakenColumnName(), SortByType.getSortBy(this.mParams.getSortBy()));
        mpFilesTableV2.getQueryBuilder().addProjection("A.date_modified", "__dateModified");
        Query buildSelectQuery = mpFilesTableV2.buildSelectQuery();
        String replace = mpFilesTableV2.getAlbumOrderByQuery(this.mParams.getSortBy()).replace("A.date_modified", "__dateModified");
        Query updateQueryForMultipleLocations = QueryUtils.updateQueryForMultipleLocations(buildSelectQuery, replace.replace("A." + getDateTakenColumnName(), "__dateTaken").replace(", A._ID DESC", "").replace(", A._ID ASC", ""), (SecLocationView) new MpLocationView(this.mParams));
        return getCursor(updateQueryForMultipleLocations, this.TAG + "#getDayCursor");
    }

    public Cursor getIdCursor() {
        return getCursor(createIdConcatQuery(new MpFilesTableV2(this.mParams).getQueryBuilder()), "getAllPicIdList");
    }

    public Cursor getNoGroupCursor() {
        MpFilesTableV2 mpFilesTableV2 = new MpFilesTableV2(this.mParams);
        mpFilesTableV2.removeBurstShotProjections();
        return getCursor(mpFilesTableV2.buildSelectQuery(), "getAllPicNoGroupCursor");
    }

    public Cursor getRealRatioCursor() {
        return ImplHelper.getRealRatioMergedCursor((SecFilesTable) new MpFilesTableV2(this.mParams), this.mQueryExecutor);
    }

    public String tag() {
        return "FilesImplV2";
    }
}
