package com.samsung.android.gallery.database.dal.mp.impl;

import android.content.ContentResolver;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.query.RawQueryExecutor;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseImpl {
    protected final boolean IS_GTE_Q;
    protected final boolean IS_GTE_R;
    protected final boolean IS_LT_Q;
    protected final String TAG = tag();
    protected QueryParams mParams;
    protected RawQueryExecutor mQueryExecutor;

    public BaseImpl(QueryParams queryParams) {
        boolean z;
        this.mParams = queryParams;
        RawQueryExecutor rawQueryExecutor = queryParams.inQueryExecutor;
        this.mQueryExecutor = rawQueryExecutor;
        if (rawQueryExecutor == null) {
            this.mQueryExecutor = new SecMpQueryExecutor();
        }
        if (queryParams.mPrintQuery) {
            this.mQueryExecutor.enableLogcatOnce();
        }
        int osVersion = queryParams.getOsVersion();
        boolean z3 = false;
        if (osVersion >= 30) {
            z = true;
        } else {
            z = false;
        }
        this.IS_GTE_R = z;
        z3 = osVersion >= 29 ? true : z3;
        this.IS_GTE_Q = z3;
        this.IS_LT_Q = !z3;
    }

    public ContentResolver getContentResolver() {
        return this.mQueryExecutor.getContentResolver();
    }

    public Cursor getCursor(Query query, String str) {
        if (this.mParams.mPrintQuery) {
            this.mQueryExecutor.enableLogcatOnce();
        }
        return this.mQueryExecutor.getCursor(query, str);
    }

    public String getDateTakenColumnName() {
        return new MpFilesTable(this.mParams).getColumnDateTaken();
    }

    public void setDateTakenFrom(SecFilesTable secFilesTable) {
        if (this.mParams.getFromNow() != -1) {
            secFilesTable.replaceDateTakenFrom(this.mParams.getFromNow());
        }
    }

    public String tag() {
        return "MpBaseImpl";
    }
}
