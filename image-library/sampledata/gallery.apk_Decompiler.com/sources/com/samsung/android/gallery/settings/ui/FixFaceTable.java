package com.samsung.android.gallery.settings.ui;

import A.a;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.module.idleworker.jobs.FixFaceJob;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FixFaceTable {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class WrongFaceData {
        int bucketId;
        String bucketPath;
        int count;

        public WrongFaceData(int i2, String str, int i7) {
            this.bucketId = i2;
            this.bucketPath = str;
            this.count = i7;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("FD{");
            sb2.append(this.bucketId);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.count);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            return C0212a.p(sb2, this.bucketPath, "}");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$fix$1(Context context) {
        ThreadUtil.postOnUiThread(new C0647b(this, context, queryBuckets(), 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDeleteConfirmDlg$6(Context context, WrongFaceData wrongFaceData) {
        int delete = delete(context, wrongFaceData.bucketId);
        Utils.showToast(context, delete + " items were removed from faces table");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDeleteConfirmDlg$7(WrongFaceData wrongFaceData, Context context, DialogInterface dialogInterface, int i2) {
        Log.d("FixFaceTable", "ok#delete " + wrongFaceData);
        SimpleThreadPool.getInstance().execute(new C0647b(this, context, wrongFaceData, 1));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$showSelectBucketDlg$2(WrongFaceData wrongFaceData) {
        return "[" + wrongFaceData.count + "] " + wrongFaceData.bucketPath;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$showSelectBucketDlg$3(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$showSelectBucketDlg$4(AtomicInteger atomicInteger, DialogInterface dialogInterface, int i2) {
        Log.d("FixFaceTable", "selected", Integer.valueOf(i2));
        atomicInteger.set(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showSelectBucketDlg$5(ArrayList arrayList, AtomicInteger atomicInteger, Context context, DialogInterface dialogInterface, int i2) {
        WrongFaceData wrongFaceData = (WrongFaceData) arrayList.get(atomicInteger.get());
        Log.d("FixFaceTable", "ok#select#" + atomicInteger + " " + wrongFaceData);
        showDeleteConfirmDlg(context, wrongFaceData);
    }

    public int delete(Context context, int i2) {
        TimeTickLog timeTickLog = new TimeTickLog();
        HashMap<String, Object> query = FixFaceJob.query(i2);
        timeTickLog.tick();
        int delete = FixFaceJob.delete(context, (String) query.getOrDefault("id", ""));
        timeTickLog.tick();
        a.A(new Object[]{Integer.valueOf(i2), (Integer) query.getOrDefault("count", 0), Integer.valueOf(delete), timeTickLog}, new StringBuilder(OpenSessionApi.ITEM_LIMIT_DELETE), "FixFaceTable");
        return delete;
    }

    public boolean fix(Context context) {
        SimpleThreadPool.getInstance().execute(new C0653h(0, this, context));
        return true;
    }

    public ArrayList<WrongFaceData> queryBuckets() {
        Cursor rawQuery;
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList<WrongFaceData> arrayList = new ArrayList<>();
        try {
            rawQuery = new SecMpQueryExecutor().rawQuery("SELECT A.bucket_id, A.relative_path, count(F._id) as count FROM faces as F INNER JOIN files A ON(A._id = F.sec_media_id) WHERE F.data != IFNULL(A._data, A.cloud_thumb_path) GROUP BY A.bucket_id", "faces#query-bucket");
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    do {
                        int i2 = rawQuery.getInt(0);
                        arrayList.add(new WrongFaceData(i2, "/" + rawQuery.getString(1), rawQuery.getInt(2)));
                    } while (rawQuery.moveToNext());
                }
            }
            Log.d("FixFaceTable", "queryBuckets" + Logger.vt(Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis)) + "");
            if (rawQuery == null) {
                return arrayList;
            }
            rawQuery.close();
            return arrayList;
        } catch (Exception e) {
            a.s(e, new StringBuilder("queryBuckets failed. e="), "FixFaceTable");
            return arrayList;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void showDeleteConfirmDlg(Context context, WrongFaceData wrongFaceData) {
        new AlertDialog.Builder(context).setTitle((CharSequence) wrongFaceData.bucketPath).setMessage((CharSequence) C0086a.l(new StringBuilder("Delete "), wrongFaceData.count, " items from database?")).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) new C0652g(context, wrongFaceData, this)).create().show();
    }

    /* renamed from: showSelectBucketDlg */
    public void lambda$fix$0(Context context, ArrayList<WrongFaceData> arrayList) {
        if (arrayList.isEmpty()) {
            Toast.makeText(context, "Nothing to fix", 0).show();
            return;
        }
        AtomicInteger atomicInteger = new AtomicInteger(0);
        new AlertDialog.Builder(context).setTitle((CharSequence) "Select album to fix").setSingleChoiceItems((CharSequence[]) (String[]) arrayList.stream().map(new C0648c(0)).toArray(new C0649d(0)), 0, (DialogInterface.OnClickListener) new C0650e(atomicInteger, 0)).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) new C0651f(0, atomicInteger, this, arrayList, context)).create().show();
    }
}
