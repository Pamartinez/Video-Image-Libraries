package com.samsung.android.gallery.module.idleworker.jobs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.samsung.android.gallery.database.dal.local.LocalProviderHelper;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteExpiredTempFileJob extends IdleWorkerJob {
    public DeleteExpiredTempFileJob(int i2, IdleWorkerJob.Type type) {
        super(i2, type);
    }

    public void execute(Context context) {
        Cursor expiredConvertedFileCursor;
        long startOfDaysAgo = new TimeUtil().startOfDaysAgo(1);
        LocalProviderHelper localProviderHelper = new LocalProviderHelper(context.getContentResolver());
        try {
            expiredConvertedFileCursor = localProviderHelper.getExpiredConvertedFileCursor(startOfDaysAgo);
            if (expiredConvertedFileCursor != null) {
                if (expiredConvertedFileCursor.moveToFirst()) {
                    do {
                        localProviderHelper.deleteShareFile(expiredConvertedFileCursor.getString(expiredConvertedFileCursor.getColumnIndex("__absPath")));
                    } while (expiredConvertedFileCursor.moveToNext());
                }
            }
            if (expiredConvertedFileCursor != null) {
                expiredConvertedFileCursor.close();
                return;
            }
            return;
        } catch (SecurityException e) {
            String str = this.TAG;
            Log.w(str, "Expired converted file delete failed. " + e.getMessage());
            return;
        } catch (SQLiteException e7) {
            String str2 = this.TAG;
            Log.w(str2, "Expired converted file delete failed. " + e7.getMessage());
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
