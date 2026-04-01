package com.samsung.android.gallery.database.dal.abstraction.query;

import N2.j;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import com.samsung.android.gallery.support.helper.CursorHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RawQueryExecutor extends QueryExecutor {
    public static String getSchemaQuery(String str) {
        return C0212a.m("select * from sqlite_master where tbl_name='", str, "' and type in ('table','index','view') and sql is not null order by type desc");
    }

    public void dumpTableToFile(String str, String str2) {
        Cursor rawQuery;
        Cursor rawQuery2;
        String schemaQuery = getSchemaQuery(str);
        try {
            rawQuery = rawQuery(schemaQuery, "schema_" + str);
            rawQuery2 = rawQuery("select * from " + str, str);
            CursorHelper.dumpCursorToFile(rawQuery, rawQuery2, str, str2, "");
            if (rawQuery2 != null) {
                rawQuery2.close();
            }
            if (rawQuery != null) {
                rawQuery.close();
                return;
            }
            return;
            throw th;
            throw th;
        } catch (Error | Exception e) {
            String str3 = this.TAG;
            StringBuilder k = j.k("dumpTableToFile failed {", str, "} e=");
            k.append(e.getMessage());
            Log.e(str3, k.toString());
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
    }

    public Cursor getCursor(Query query, String str) {
        ThreadUtil.assertBgThread("AbsDbFactory getCursor should run on background thread");
        try {
            return rawQuery(query, str);
        } catch (SQLiteException e) {
            Log.e(this.TAG, e.toString());
            throw e;
        }
    }

    public final Uri getRawQueryUri(Query query) {
        return getRawQueryUri(query.buildSql());
    }

    public abstract Uri getRawQueryUri(String str);

    public String logQuery(String str) {
        Cursor rawQuery;
        if (getContext() == null) {
            Log.e(this.TAG, "fail log. null");
            return "";
        }
        try {
            rawQuery = rawQuery(str, "dumpQuery");
            String dumpCursor = CursorHelper.dumpCursor(rawQuery);
            if (rawQuery != null) {
                rawQuery.close();
            }
            return dumpCursor;
        } catch (Exception unused) {
            return "";
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public abstract Cursor rawQuery(Query query, String str);

    public abstract Cursor rawQuery(String str, String str2);
}
