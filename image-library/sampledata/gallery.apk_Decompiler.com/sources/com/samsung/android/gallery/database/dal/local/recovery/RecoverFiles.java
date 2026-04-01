package com.samsung.android.gallery.database.dal.local.recovery;

import A.a;
import C3.i;
import android.content.ContentValues;
import android.database.Cursor;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import g6.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecoverFiles {
    private static long sLastUpdated;

    public static void execute() {
        if (Features.isEnabled(Features.SUPPORT_RECOVER_COLLECT)) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - sLastUpdated > 3600000) {
                sLastUpdated = currentTimeMillis;
                SimpleThreadPool.getInstance().execute(new i(24));
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$execute$0() {
        try {
            new RecoverFiles().update();
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("RecoveryFiles failed. e="), "RecoverFiles");
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$update$1(HashMap hashMap, String str) {
        return (Integer) hashMap.get(str);
    }

    private void update() {
        Cursor query;
        Throwable th;
        RecoverCollector instance = RecoverCollector.getInstance();
        try {
            query = AppResources.getAppContext().getContentResolver().query(LocalDatabase.RECOVERY_URI, new String[]{"_id", "__absPath", "__recoverType"}, "__recoverDirty = 1", (String[]) null, (String) null);
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList();
            if (query != null && query.moveToFirst()) {
                do {
                    arrayList.add(String.valueOf(query.getLong(0)));
                    hashMap.put(query.getString(1), Integer.valueOf(query.getInt(2)));
                } while (query.moveToNext());
            }
            if (!hashMap.isEmpty()) {
                ((Map) hashMap.keySet().stream().collect(Collectors.groupingBy(new b(27, hashMap)))).forEach(new f(6, instance));
                Log.d("RecoverFiles", "update: size = " + hashMap.size());
                updateDirty(arrayList);
            }
            if (query != null) {
                query.close();
                return;
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    private void updateDirty(ArrayList<String> arrayList) {
        if (!arrayList.isEmpty()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("__recoverDirty", 0);
            int update = AppResources.getAppContext().getContentResolver().update(LocalDatabase.RECOVERY_URI, contentValues, getWhere(arrayList.size()), (String[]) arrayList.toArray(new String[0]));
            Log.d("RecoverFiles", "updateDirty size[" + update + "]");
        }
    }

    public String getWhere(int i2) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        for (int i7 = 0; i7 < i2; i7++) {
            stringJoiner.add("?");
        }
        return "_id IN (" + stringJoiner.toString() + ")";
    }
}
