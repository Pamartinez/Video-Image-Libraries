package com.samsung.android.gallery.module.trash.helper;

import A.a;
import C3.l;
import android.content.Context;
import android.os.AsyncTask;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.module.trash.abstraction.ITrashProvider;
import com.samsung.android.gallery.module.trash.abstraction.TrashEmptyItem;
import com.samsung.android.gallery.module.trash.expired.TrashExpiredDefault;
import com.samsung.android.gallery.module.trash.factory.TrashProviderFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.TimeUtil;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashEmptyExpiredTask extends AsyncTask<Void, Void, Void> {
    private final WeakReference<Context> mContext;
    private int mEmptyCount = 0;
    private boolean mFileDeleted = false;
    private int mFileDeletedCount = 0;
    private final boolean mOnIdleWorker;
    private boolean mRecordDeleted = false;
    private final ITrashProvider mTrashProvider;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TrashEmptyExpiredJob extends IdleWorkerJob {
        public TrashEmptyExpiredJob(int i2, IdleWorkerJob.Type type) {
            super(i2, type);
        }

        public void execute(Context context) {
            LatchBuilder.executeLatch(10000, new l(context, 6));
        }
    }

    public TrashEmptyExpiredTask(Context context, boolean z) {
        this.mContext = new WeakReference<>(context);
        this.mOnIdleWorker = z;
        this.mTrashProvider = TrashProviderFactory.getInstance(context);
    }

    private void emptyExpiredFile(TrashEmptyItem trashEmptyItem) {
        String path = trashEmptyItem.getPath();
        if (deleteFile(path)) {
            this.mFileDeletedCount++;
            this.mFileDeleted = true;
            return;
        }
        Log.w("TrashEmptyExpiredTask", "delete file failed [" + Logger.getEncodedString(path) + "]");
    }

    private void emptyExpiredRecord(String str) {
        int deleteTrash = this.mTrashProvider.deleteTrash(str, (String[]) null);
        this.mEmptyCount = deleteTrash;
        if (deleteTrash > 0) {
            this.mRecordDeleted = true;
            return;
        }
        Log.w("TrashEmptyExpiredTask", "delete trash failed [" + this.mFileDeletedCount + "]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x006c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean emptyOperation(com.samsung.android.gallery.module.trash.expired.TrashExpiredType r9) {
        /*
            r8 = this;
            java.lang.String r0 = "TrashEmptyExpiredTask"
            long r1 = r9.getExpiredTime()
            java.lang.String r1 = r9.getExpiredSimpleDate(r1)
            r2 = 0
            com.samsung.android.gallery.module.trash.abstraction.ITrashProvider r3 = r8.mTrashProvider     // Catch:{ Exception -> 0x0055 }
            java.lang.String r4 = r9.getTrashSelection()     // Catch:{ Exception -> 0x0055 }
            android.database.Cursor r3 = r3.getExpiredTrashCursor(r4)     // Catch:{ Exception -> 0x0055 }
            if (r3 == 0) goto L_0x004f
            boolean r4 = r3.moveToFirst()     // Catch:{ all -> 0x0042 }
            if (r4 == 0) goto L_0x004f
            int r4 = r3.getCount()     // Catch:{ all -> 0x0042 }
            boolean r5 = r8.mOnIdleWorker     // Catch:{ all -> 0x0040 }
            if (r5 != 0) goto L_0x0030
            r5 = 200000(0x30d40, float:2.8026E-40)
            if (r4 <= r5) goto L_0x0030
            r3.close()     // Catch:{ Exception -> 0x002e }
            return r2
        L_0x002e:
            r2 = move-exception
            goto L_0x0058
        L_0x0030:
            com.samsung.android.gallery.module.trash.abstraction.TrashEmptyItem r2 = new com.samsung.android.gallery.module.trash.abstraction.TrashEmptyItem     // Catch:{ all -> 0x0040 }
            r2.<init>((android.database.Cursor) r3)     // Catch:{ all -> 0x0040 }
            r8.emptyExpiredFile(r2)     // Catch:{ all -> 0x0040 }
            boolean r2 = r3.moveToNext()     // Catch:{ all -> 0x0040 }
            if (r2 != 0) goto L_0x0030
            r2 = r4
            goto L_0x004f
        L_0x0040:
            r2 = move-exception
            goto L_0x0046
        L_0x0042:
            r4 = move-exception
            r7 = r4
            r4 = r2
            r2 = r7
        L_0x0046:
            r3.close()     // Catch:{ all -> 0x004a }
            goto L_0x004e
        L_0x004a:
            r3 = move-exception
            r2.addSuppressed(r3)     // Catch:{ Exception -> 0x002e }
        L_0x004e:
            throw r2     // Catch:{ Exception -> 0x002e }
        L_0x004f:
            if (r3 == 0) goto L_0x0064
            r3.close()     // Catch:{ Exception -> 0x0055 }
            goto L_0x0064
        L_0x0055:
            r3 = move-exception
            r4 = r2
            r2 = r3
        L_0x0058:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "unable to get expired trash cursor e="
            r3.<init>(r5)
            A.a.s(r2, r3, r0)
            r2 = r4
        L_0x0064:
            int r3 = r8.mFileDeletedCount
            java.lang.String r4 = "]"
            java.lang.String r5 = "]["
            if (r3 <= 0) goto L_0x00ab
            java.lang.String r9 = r9.getTrashSelection()
            r8.emptyExpiredRecord(r9)
            com.samsung.android.gallery.database.dal.DebugLogger r9 = com.samsung.android.gallery.database.dal.DebugLogger.getDeleteInstance()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r6 = "["
            r3.<init>(r6)
            com.samsung.android.gallery.module.trash.abstraction.TrashLogType r6 = com.samsung.android.gallery.module.trash.abstraction.TrashLogType.EMPTY_EXPIRED
            r3.append(r6)
            r3.append(r5)
            int r6 = r8.mFileDeletedCount
            r3.append(r6)
            r3.append(r5)
            int r6 = r8.mEmptyCount
            r3.append(r6)
            r3.append(r5)
            r3.append(r1)
            r3.append(r5)
            boolean r6 = r8.mOnIdleWorker
            r3.append(r6)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r9.insertLog(r3)
        L_0x00ab:
            java.lang.String r9 = "empty expired ["
            java.lang.StringBuilder r9 = c0.C0086a.o(r2, r9, r5)
            int r2 = r8.mEmptyCount
            r9.append(r2)
            r9.append(r5)
            int r2 = r8.mFileDeletedCount
            r9.append(r2)
            r9.append(r5)
            r9.append(r1)
            r9.append(r5)
            boolean r1 = r8.mOnIdleWorker
            r9.append(r1)
            r9.append(r4)
            java.lang.String r9 = r9.toString()
            com.samsung.android.gallery.support.utils.Log.i(r0, r9)
            boolean r8 = r8.mFileDeleted
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.helper.TrashEmptyExpiredTask.emptyOperation(com.samsung.android.gallery.module.trash.expired.TrashExpiredType):boolean");
    }

    public boolean deleteFile(String str) {
        try {
            return FileUtils.delete(str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Void doInBackground(Void... voidArr) {
        if (this.mContext.get() != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (emptyOperation(new TrashExpiredDefault())) {
                a.A(new Object[]{TimeUtil.getIsoLocalDateTime(currentTimeMillis), Long.valueOf(currentTimeMillis)}, new StringBuilder("completed"), "TrashEmptyExpiredTask");
                if (!this.mOnIdleWorker) {
                    Blackboard.postBroadcastEventGlobal(EventMessage.obtain(1029, 1, 0, (Object) null));
                }
            } else if (!this.mFileDeleted) {
                Log.d("TrashEmptyExpiredTask", "skip empty expired. no file to empty expired.");
            } else {
                Log.e("TrashEmptyExpiredTask", "skip empty expired. It is not on IdleWorker and need to delete large data");
            }
            PreferenceCache.TrashEmptyTime.setLong(currentTimeMillis);
        }
        return null;
    }
}
