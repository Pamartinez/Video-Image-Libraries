package com.samsung.android.gallery.module.trash.helper;

import C3.l;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.ServiceManager;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.HashSet;
import java.util.Iterator;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashRollbackTask {
    private Consumer<Integer> mConsumer;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TrashRollbackJob extends IdleWorkerJob {
        public TrashRollbackJob(int i2, IdleWorkerJob.Type type) {
            super(i2, type);
        }

        private boolean isDeleteServiceRunning(Context context) {
            return ServiceManager.getInstance().hasRunningServiceForTrash(context);
        }

        private boolean isExecutable(Context context) {
            if (!GalleryPreference.getInstanceDebug().loadBoolean("TrashRollbackTask_job_finished", false)) {
                if (!isDeleteServiceRunning(context)) {
                    return true;
                }
                return false;
            } else if (PreferenceCache.QuickDeleteService.getInt() <= 0 || isDeleteServiceRunning(context)) {
                return false;
            } else {
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$execute$0(Context context) {
            new TrashRollbackTask().execute(context);
            GalleryPreference.getInstanceDebug().saveState("TrashRollbackTask_job_finished", true);
            PreferenceCache.QuickDeleteService.clear();
        }

        public void execute(Context context) {
            if (!isExecutable(context)) {
                Log.w(this.TAG, "not executed");
            } else {
                LatchBuilder.executeLatch(10000, new l(context, 8));
            }
        }
    }

    public TrashRollbackTask() {
    }

    private String getIdsForRollback(HashSet<String> hashSet, String str) {
        if (TextUtils.isEmpty(str)) {
            StringBuilder sb2 = new StringBuilder();
            Iterator<String> it = hashSet.iterator();
            while (it.hasNext()) {
                sb2.append(it.next());
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            }
            if (sb2.length() > 0) {
                return sb2.substring(0, sb2.length() - 1);
            }
            return "";
        }
        String[] split = str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        StringBuilder sb3 = new StringBuilder();
        for (String str2 : split) {
            if (!hashSet.contains(str2)) {
                sb3.append(str2);
                sb3.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            }
        }
        if (sb3.length() > 0) {
            return sb3.substring(0, sb3.length() - 1);
        }
        return "";
    }

    private Cursor getTrashedCursor(Context context) {
        String str = "select _id, _data, is_cloud from files where is_trashed = 1 AND (coalesce(sef_file_type, 0) not in (2928,2784,2947)) AND media_type in (1,3) AND volume_name in (" + FileUtils.getMountedVolumeNames() + ")";
        try {
            return context.getContentResolver().query(MediaUri.getInstance().getRawQueryUri(str), (String[]) null, str, (String[]) null, (String) null);
        } catch (Exception e) {
            Log.d("TrashRollbackTask", "getAbnormalRecord:" + e.getMessage());
            return null;
        }
    }

    private boolean isValid(String str) {
        if (str == null || str.startsWith("/data/sec/") || !FileUtils.exists(str)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int updateTrashedRollback(android.content.Context r10, java.util.HashSet<java.lang.String> r11, java.lang.String r12) {
        /*
            r9 = this;
            boolean r0 = r11.isEmpty()
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            android.content.ContentResolver r2 = r10.getContentResolver()
            com.samsung.android.gallery.support.providers.UriInterface r0 = com.samsung.android.gallery.support.providers.MediaUri.getInstance()
            android.net.Uri r3 = r0.getSecTrashUri()
            java.lang.String r0 = "group_concat(sec_media_id)"
            java.lang.String[] r4 = new java.lang.String[]{r0}
            java.lang.String r0 = "sec_media_id in ("
            java.lang.String r8 = ")"
            java.lang.String r5 = i.C0212a.m(r0, r12, r8)
            r6 = 0
            r7 = 0
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7)
            if (r12 == 0) goto L_0x0042
            boolean r0 = r12.moveToFirst()     // Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x0042
            java.lang.String r0 = r12.getString(r1)     // Catch:{ all -> 0x0036 }
            goto L_0x0043
        L_0x0036:
            r0 = move-exception
            r9 = r0
            r12.close()     // Catch:{ all -> 0x003c }
            goto L_0x0041
        L_0x003c:
            r0 = move-exception
            r10 = r0
            r9.addSuppressed(r10)
        L_0x0041:
            throw r9
        L_0x0042:
            r0 = 0
        L_0x0043:
            if (r12 == 0) goto L_0x0048
            r12.close()
        L_0x0048:
            java.lang.String r9 = r9.getIdsForRollback(r11, r0)
            boolean r11 = android.text.TextUtils.isEmpty(r9)
            if (r11 != 0) goto L_0x0095
            android.content.ContentValues r11 = new android.content.ContentValues
            r11.<init>()
            java.lang.String r12 = "is_trashed"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            r11.put(r12, r0)
            android.os.Bundle r12 = new android.os.Bundle
            r12.<init>()
            java.lang.String r0 = "android:query-arg-match-trashed"
            r1 = 1
            r12.putInt(r0, r1)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "_id in ("
            r0.<init>(r1)
            r0.append(r9)
            r0.append(r8)
            java.lang.String r9 = r0.toString()
            java.lang.String r0 = "android:query-arg-sql-selection"
            r12.putString(r0, r9)
            android.content.ContentResolver r9 = r10.getContentResolver()
            android.net.Uri r10 = com.samsung.android.gallery.support.providers.MediaUri.getSecMediaUri()
            int r1 = r9.update(r10, r11, r12)
            java.lang.String r9 = "TrashRollbackTask"
            java.lang.String r10 = "updateTrashedRollback: result = "
            A.a.k(r1, r10, r9)
        L_0x0095:
            com.samsung.android.gallery.database.dal.DebugLogger r9 = com.samsung.android.gallery.database.dal.DebugLogger.getDeleteInstance()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "["
            r10.<init>(r11)
            com.samsung.android.gallery.module.trash.abstraction.TrashLogType r11 = com.samsung.android.gallery.module.trash.abstraction.TrashLogType.TRASH_ABNORMAL_RECOVER
            r10.append(r11)
            java.lang.String r11 = "]["
            r10.append(r11)
            r10.append(r1)
            java.lang.String r11 = "]"
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.insertLog(r10)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.helper.TrashRollbackTask.updateTrashedRollback(android.content.Context, java.util.HashSet, java.lang.String):int");
    }

    public void execute(Context context) {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        Log.d("TrashRollbackTask", "start trash rollback...");
        HashSet hashSet = new HashSet();
        StringBuilder sb2 = new StringBuilder();
        Cursor trashedCursor = getTrashedCursor(context);
        if (trashedCursor != null) {
            try {
                if (trashedCursor.moveToFirst()) {
                    do {
                        long j2 = trashedCursor.getLong(0);
                        String string = trashedCursor.getString(1);
                        if (trashedCursor.getInt(2) != 2 && isValid(string)) {
                            hashSet.add(String.valueOf(j2));
                            sb2.append(j2);
                            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                        }
                    } while (trashedCursor.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (trashedCursor != null) {
            trashedCursor.close();
        }
        if (sb2.length() > 0) {
            str = sb2.substring(0, sb2.length() - 1);
        } else {
            str = "";
        }
        int updateTrashedRollback = updateTrashedRollback(context, hashSet, str);
        Consumer<Integer> consumer = this.mConsumer;
        if (consumer != null) {
            consumer.accept(Integer.valueOf(updateTrashedRollback));
        }
        Log.d("TrashRollbackTask", "elapsed = " + (System.currentTimeMillis() - currentTimeMillis) + " result = " + updateTrashedRollback);
        return;
        throw th;
    }

    public TrashRollbackTask(Consumer<Integer> consumer) {
        this.mConsumer = consumer;
    }
}
