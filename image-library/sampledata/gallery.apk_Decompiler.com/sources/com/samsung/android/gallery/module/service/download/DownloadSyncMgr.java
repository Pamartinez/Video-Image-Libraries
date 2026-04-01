package com.samsung.android.gallery.module.service.download;

import A.a;
import M8.c;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadSyncMgr {
    protected final List<Long> mSynchronizedList = Collections.synchronizedList(new LinkedList());

    private boolean checkDownloaded(long j2) {
        Cursor query;
        try {
            query = DbCompat.query(DbKey.ALL_PICTURES, new c(j2, 5));
            if (query != null) {
                if (query.moveToFirst()) {
                    boolean isNotCloudOnly = isNotCloudOnly(query);
                    query.close();
                    return isNotCloudOnly;
                }
            }
            if (query == null) {
                return false;
            }
            query.close();
            return false;
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to check whether downloaded e="), "DownloadSyncMgr");
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private boolean checkDownloading(long j2) {
        try {
            return this.mSynchronizedList.contains(Long.valueOf(j2));
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to check whether downloading e="), "DownloadSyncMgr");
            return false;
        }
    }

    private boolean isNotCloudOnly(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("__storageType");
        if (columnIndex < 0 || cursor.getInt(columnIndex) != 2) {
            return true;
        }
        return false;
    }

    public void add(long j2) {
        try {
            this.mSynchronizedList.add(Long.valueOf(j2));
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to add e="), "DownloadSyncMgr");
        }
    }

    public void remove(long j2) {
        try {
            this.mSynchronizedList.remove(Long.valueOf(j2));
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to remove e="), "DownloadSyncMgr");
        }
    }

    public synchronized boolean valid(long j2) {
        boolean z;
        if (checkDownloading(j2) || checkDownloaded(j2)) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }
}
