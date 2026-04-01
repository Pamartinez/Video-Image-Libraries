package com.samsung.android.gallery.database.dal.mp.impl;

import A.a;
import android.database.Cursor;
import android.database.MatrixCursor;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpLocationView;
import com.samsung.android.gallery.database.dal.util.QueryUtils;
import com.samsung.android.gallery.database.dbtype.DateType;
import com.samsung.android.gallery.support.type.ActivityType;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActivitiesImpl extends CategoriesImpl {
    public ActivitiesImpl(QueryParams queryParams) {
        super(queryParams);
    }

    private SecFilesTable getGeneratedFilesTable() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterGenerated();
        mpFilesTable.clearOrderBy();
        mpFilesTable.orderByModifiedTime();
        return mpFilesTable;
    }

    private SecFilesTable getRecentlyEditedFilesTable() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterRecentlyEdited();
        mpFilesTable.clearOrderBy();
        mpFilesTable.orderByModifiedTime();
        return mpFilesTable;
    }

    public Cursor getActivityCursor() {
        this.mParams.useDefaultOrder = false;
        long currentTimeMillis = System.currentTimeMillis();
        List<String> listOf = ActivityType.listOf();
        int size = listOf.size();
        int[] iArr = new int[size];
        Arrays.fill(iArr, 1);
        a.x(new StringBuilder("activityQuery "), currentTimeMillis, "ActivitiesImpl");
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"__Title", "__count", "__mainCategory", "__subCategory", "__absPath"}, size);
        for (int i2 = 0; i2 < size; i2++) {
            if (iArr[i2] > 0) {
                String str = listOf.get(i2);
                matrixCursor.addRow(new Object[]{str, Integer.valueOf(iArr[i2]), "Activity", str, "Activity"});
            }
        }
        return matrixCursor;
    }

    public Cursor getGeneratedFileCursor() {
        SecFilesTable generatedFilesTable = getGeneratedFilesTable();
        String subCategory = this.mParams.getSubCategory();
        if (this.mParams.mIsForOnDemandQuery) {
            generatedFilesTable.resetProjectionForID();
        }
        if (this.mParams.mUseFileIdsConcat) {
            Query buildSelectQuery = generatedFilesTable.buildSelectQuery();
            return getFileIdsConcatCursor(buildSelectQuery, "generatedFile : " + subCategory);
        }
        Query buildSelectQuery2 = generatedFilesTable.buildSelectQuery();
        return getCursor(buildSelectQuery2, "generatedFile : " + subCategory);
    }

    public Cursor getGeneratedFileDateCursor() {
        SecFilesTable generatedFilesTable = getGeneratedFilesTable();
        generatedFilesTable.modifyForTimelineDateData(DateType.DAY, getDateTakenColumnName());
        Query updateQueryForMultipleLocations = QueryUtils.updateQueryForMultipleLocations(generatedFilesTable.buildSelectQuery(), new MpLocationView(this.mParams));
        return getCursor(updateQueryForMultipleLocations, "generated date : " + this.mParams.getSubCategory());
    }

    public Cursor getGeneratedFileRealRatioCursor() {
        return ImplHelper.getRealRatioMergedCursor(getGeneratedFilesTable(), this.mQueryExecutor);
    }

    public Cursor getRecentlyEditedFileCursor() {
        SecFilesTable recentlyEditedFilesTable = getRecentlyEditedFilesTable();
        String subCategory = this.mParams.getSubCategory();
        if (this.mParams.mIsForOnDemandQuery) {
            recentlyEditedFilesTable.resetProjectionForID();
        }
        if (this.mParams.mUseFileIdsConcat) {
            Query buildSelectQuery = recentlyEditedFilesTable.buildSelectQuery();
            return getFileIdsConcatCursor(buildSelectQuery, "generatedFile : " + subCategory);
        }
        Query buildSelectQuery2 = recentlyEditedFilesTable.buildSelectQuery();
        return getCursor(buildSelectQuery2, "generatedFile : " + subCategory);
    }

    public Cursor getRecentlyEditedFileDateCursor() {
        SecFilesTable recentlyEditedFilesTable = getRecentlyEditedFilesTable();
        recentlyEditedFilesTable.modifyForTimelineDateData(DateType.DAY, getDateTakenColumnName());
        Query updateQueryForMultipleLocations = QueryUtils.updateQueryForMultipleLocations(recentlyEditedFilesTable.buildSelectQuery(), new MpLocationView(this.mParams));
        return getCursor(updateQueryForMultipleLocations, "generated date : " + this.mParams.getSubCategory());
    }

    public Cursor getRecentlyEditedFileRealRatioCursor() {
        return ImplHelper.getRealRatioMergedCursor(getRecentlyEditedFilesTable(), this.mQueryExecutor);
    }
}
