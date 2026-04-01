package com.samsung.android.gallery.database.dal.cmh.impl;

import android.content.ContentResolver;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.query.RawQueryExecutor;
import com.samsung.android.gallery.database.dal.cmh.executor.CmhQueryExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseImpl {
    protected final boolean IS_GTE_Q;
    protected final boolean IS_GTE_T;
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
            this.mQueryExecutor = new CmhQueryExecutor();
        }
        int osVersion = this.mParams.getOsVersion();
        boolean z3 = false;
        if (osVersion >= 33) {
            z = true;
        } else {
            z = false;
        }
        this.IS_GTE_T = z;
        z3 = osVersion >= 29 ? true : z3;
        this.IS_GTE_Q = z3;
        this.IS_LT_Q = !z3;
    }

    public ContentResolver getContentResolver() {
        return this.mQueryExecutor.getContentResolver();
    }

    public Cursor getCursor(Query query, String str) {
        return this.mQueryExecutor.getCursor(query, str);
    }

    public abstract String tag();
}
