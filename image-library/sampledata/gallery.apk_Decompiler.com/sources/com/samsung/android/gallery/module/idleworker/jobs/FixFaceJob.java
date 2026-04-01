package com.samsung.android.gallery.module.idleworker.jobs;

import A.a;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import i.C0212a;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FixFaceJob extends IdleWorkerJob {
    public static int delete(Context context, String str) {
        int i2;
        long currentTimeMillis = System.currentTimeMillis();
        if (!TextUtils.isEmpty(str)) {
            try {
                Uri parse = Uri.parse("content://secmedia/cmh/faces");
                ContentResolver contentResolver = context.getContentResolver();
                i2 = contentResolver.delete(parse, "_id in (" + str + ")", (String[]) null);
            } catch (Error | Exception e) {
                a.z(e, new StringBuilder("delete failed. e="), "FixFaceJob");
            }
            a.A(new Object[]{Integer.valueOf(i2), Long.valueOf(currentTimeMillis)}, new StringBuilder("delete#id"), "FixFaceJob");
            return i2;
        }
        i2 = 0;
        a.A(new Object[]{Integer.valueOf(i2), Long.valueOf(currentTimeMillis)}, new StringBuilder("delete#id"), "FixFaceJob");
        return i2;
    }

    public static HashMap<String, Object> query(int i2) {
        String str;
        Cursor rawQuery;
        long currentTimeMillis = System.currentTimeMillis();
        HashMap<String, Object> hashMap = new HashMap<>();
        StringBuilder sb2 = new StringBuilder("SELECT group_concat(F._id) as __ids, count(F._id) as __count FROM faces as F INNER JOIN files A ON(A._id = F.sec_media_id) WHERE F.data != IFNULL(A._data, A.cloud_thumb_path) ");
        if (i2 == 0) {
            str = "";
        } else {
            str = C0212a.j(i2, "AND A.bucket_id=", " ");
        }
        String p6 = C0212a.p(sb2, str, "LIMIT 100000");
        String str2 = null;
        int i7 = 0;
        try {
            rawQuery = new SecMpQueryExecutor().rawQuery(p6, "faces#query-id");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    str2 = rawQuery.getString(0);
                    i7 = rawQuery.getInt(1);
                    hashMap.put("id", str2);
                    hashMap.put("count", Integer.valueOf(i7));
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("query#id failed. e="), "FixFaceJob");
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        Log.d("FixFaceJob", "query#id" + Logger.vt(Integer.valueOf(i7), Long.valueOf(currentTimeMillis)) + " " + str2);
        return hashMap;
        throw th;
    }
}
