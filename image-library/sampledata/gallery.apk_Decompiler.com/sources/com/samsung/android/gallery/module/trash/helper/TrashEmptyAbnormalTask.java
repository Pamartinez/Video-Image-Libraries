package com.samsung.android.gallery.module.trash.helper;

import A.a;
import C3.l;
import N2.j;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.module.trash.abstraction.ITrashProvider;
import com.samsung.android.gallery.module.trash.factory.TrashProviderFactory;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashEmptyAbnormalTask extends AsyncTask<Context, Void, Void> {
    private ITrashProvider mTrashProvider;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TrashEmptyAbnormalJob extends IdleWorkerJob {
        public TrashEmptyAbnormalJob(int i2, IdleWorkerJob.Type type) {
            super(i2, type);
        }

        public void execute(Context context) {
            LatchBuilder.executeLatch(10000, new l(context, 5));
        }
    }

    private String convertTrashPath(String str, String str2) {
        StringBuilder s = C0212a.s(FileUtils.getParent(str));
        s.append(File.separator);
        s.append(FileUtils.getNameFromPath(str2));
        return s.toString();
    }

    private void deleteFiles(List<String> list) {
        if (list != null && !list.isEmpty()) {
            for (String delete : list) {
                FileUtils.delete(delete);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x006d A[Catch:{ Exception -> 0x0039 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0075 A[Catch:{ Exception -> 0x0039 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0078 A[Catch:{ Exception -> 0x0039 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void emptyAbnormal(android.content.Context r10, boolean r11) {
        /*
            r9 = this;
            java.lang.String r0 = "TrashEmptyAbnormalTask"
            java.lang.String r1 = "empty abnormal"
            java.lang.String r2 = "["
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0039 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0039 }
            r5.<init>()     // Catch:{ Exception -> 0x0039 }
            java.io.File r6 = r10.getFilesDir()     // Catch:{ Exception -> 0x0039 }
            r5.append(r6)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r6 = java.io.File.separator     // Catch:{ Exception -> 0x0039 }
            r5.append(r6)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r6 = "trash_abnormal.txt"
            r5.append(r6)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.gallery.support.utils.FileListHolder r6 = new com.samsung.android.gallery.support.utils.FileListHolder     // Catch:{ Exception -> 0x0039 }
            r6.<init>(r5)     // Catch:{ Exception -> 0x0039 }
            r7 = 500(0x1f4, float:7.0E-43)
            java.util.List r6 = r6.trimLastLines(r7)     // Catch:{ Exception -> 0x0039 }
            if (r6 == 0) goto L_0x003c
            boolean r8 = r6.isEmpty()     // Catch:{ Exception -> 0x0039 }
            if (r8 == 0) goto L_0x0065
            goto L_0x003c
        L_0x0039:
            r9 = move-exception
            goto L_0x00ba
        L_0x003c:
            if (r11 == 0) goto L_0x0045
            boolean r11 = r9.isCheckingAvailable(r3)     // Catch:{ Exception -> 0x0039 }
            if (r11 != 0) goto L_0x0045
            return
        L_0x0045:
            java.util.ArrayList r10 = r9.getAbnormalFileList(r10)     // Catch:{ Exception -> 0x0039 }
            boolean r11 = r10.isEmpty()     // Catch:{ Exception -> 0x0039 }
            if (r11 != 0) goto L_0x0065
            int r11 = r10.size()     // Catch:{ Exception -> 0x0039 }
            if (r11 > r7) goto L_0x0057
        L_0x0055:
            r6 = r10
            goto L_0x0065
        L_0x0057:
            com.samsung.android.gallery.support.utils.FileListHolder r11 = new com.samsung.android.gallery.support.utils.FileListHolder     // Catch:{ Exception -> 0x0039 }
            r11.<init>(r5)     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.gallery.support.utils.FileListHolder r10 = r11.writeFile(r10)     // Catch:{ Exception -> 0x0039 }
            java.util.List r10 = r10.trimLastLines(r7)     // Catch:{ Exception -> 0x0039 }
            goto L_0x0055
        L_0x0065:
            if (r6 == 0) goto L_0x0075
            boolean r10 = r6.isEmpty()     // Catch:{ Exception -> 0x0039 }
            if (r10 != 0) goto L_0x0075
            r9.deleteFiles(r6)     // Catch:{ Exception -> 0x0039 }
            int r9 = r6.size()     // Catch:{ Exception -> 0x0039 }
            goto L_0x0076
        L_0x0075:
            r9 = 0
        L_0x0076:
            if (r9 <= 0) goto L_0x009a
            com.samsung.android.gallery.database.dal.DebugLogger r10 = com.samsung.android.gallery.database.dal.DebugLogger.getDeleteInstance()     // Catch:{ Exception -> 0x0039 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0039 }
            r11.<init>(r2)     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.gallery.module.trash.abstraction.TrashLogType r2 = com.samsung.android.gallery.module.trash.abstraction.TrashLogType.EMPTY_ABNORMAL     // Catch:{ Exception -> 0x0039 }
            r11.append(r2)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r2 = "]["
            r11.append(r2)     // Catch:{ Exception -> 0x0039 }
            r11.append(r9)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r2 = "]"
            r11.append(r2)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x0039 }
            r10.insertLog(r11)     // Catch:{ Exception -> 0x0039 }
        L_0x009a:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0039 }
            r10.<init>(r1)     // Catch:{ Exception -> 0x0039 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x0039 }
            java.lang.Long r11 = java.lang.Long.valueOf(r3)     // Catch:{ Exception -> 0x0039 }
            java.lang.Object[] r9 = new java.lang.Object[]{r9, r11}     // Catch:{ Exception -> 0x0039 }
            java.lang.String r9 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r9)     // Catch:{ Exception -> 0x0039 }
            r10.append(r9)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r9 = r10.toString()     // Catch:{ Exception -> 0x0039 }
            com.samsung.android.gallery.support.utils.Log.d(r0, r9)     // Catch:{ Exception -> 0x0039 }
            return
        L_0x00ba:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "empty abnormal failed. e="
            r10.<init>(r11)
            N2.j.s(r9, r10, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.helper.TrashEmptyAbnormalTask.emptyAbnormal(android.content.Context, boolean):void");
    }

    private ArrayList<String> getAbnormalFileList(Context context) {
        HashSet<String> hashSet;
        ArrayList<String> arrayList = new ArrayList<>();
        HashSet<String> trashFileSet = getTrashFileSet(context);
        if (!trashFileSet.isEmpty()) {
            if (getTrashDbCount() > 0) {
                hashSet = getTrashDbSet();
            } else {
                hashSet = null;
            }
            if (hashSet == null || hashSet.isEmpty()) {
                arrayList.addAll(trashFileSet);
                return arrayList;
            } else if (trashFileSet.size() != hashSet.size()) {
                Iterator<String> it = trashFileSet.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (!hashSet.contains(next)) {
                        arrayList.add(next);
                    }
                }
            }
        }
        return arrayList;
    }

    private int getTrashDbCount() {
        int[] trashCount = this.mTrashProvider.getTrashCount(false);
        return trashCount[0] + trashCount[1];
    }

    private HashSet<String> getTrashDbSet() {
        HashSet<String> hashSet = new HashSet<>();
        long currentTimeMillis = System.currentTimeMillis();
        Cursor trashCursor = this.mTrashProvider.getTrashCursor(false);
        if (trashCursor != null) {
            try {
                if (trashCursor.moveToFirst()) {
                    int columnIndex = trashCursor.getColumnIndex("__absPath");
                    int columnIndex2 = trashCursor.getColumnIndex("__cloudTP");
                    do {
                        String string = trashCursor.getString(columnIndex);
                        String string2 = trashCursor.getString(columnIndex2);
                        if (!TextUtils.isEmpty(string)) {
                            hashSet.add(string);
                        }
                        if (!TextUtils.isEmpty(string2)) {
                            hashSet.add(convertTrashPath(string, string2));
                        }
                    } while (trashCursor.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (trashCursor != null) {
            trashCursor.close();
        }
        a.A(new Object[]{Integer.valueOf(hashSet.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("trash database"), "TrashEmptyAbnormalTask");
        return hashSet;
        throw th;
    }

    private HashSet<String> getTrashFileSet(Context context) {
        HashSet<String> hashSet = new HashSet<>();
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList<File> trashDirs = TrashHelperFactory.getExternalHelper(context).getTrashDirs();
        Iterator<File> it = trashDirs.iterator();
        while (it.hasNext()) {
            File next = it.next();
            ArrayList arrayList = new ArrayList();
            if (next != null) {
                File[] listFiles = next.listFiles();
                if (listFiles != null) {
                    for (File absolutePath : listFiles) {
                        arrayList.add(absolutePath.getAbsolutePath());
                    }
                }
                int size = arrayList.size();
                if (size > 0) {
                    for (int i2 = 0; i2 < size; i2++) {
                        hashSet.add((String) arrayList.get(i2));
                    }
                }
            }
        }
        a.A(new Object[]{j.g(new StringBuilder("dirs="), trashDirs), Integer.valueOf(hashSet.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("trash files"), "TrashEmptyAbnormalTask");
        return hashSet;
    }

    public void emptyAbnormalInIdleWorker(Context context) {
        this.mTrashProvider = TrashProviderFactory.getInstance(context);
        emptyAbnormal(context, true);
    }

    public boolean isCheckingAvailable(long j2) {
        long loadLong = GalleryPreference.getInstance().loadLong("TrashEmptyAbnormalTask", 0);
        if (j2 - loadLong > MediaApiContract.DAY_IN_MILLI || j2 < loadLong) {
            GalleryPreference.getInstance().saveState("TrashEmptyAbnormalTask", j2);
            return true;
        }
        Log.d("TrashEmptyAbnormalTask", "empty abnormal skip", TimeUtil.getIsoLocalDateTime(loadLong));
        return false;
    }

    public Void doInBackground(Context... contextArr) {
        Context context = contextArr[0];
        if (context == null) {
            return null;
        }
        this.mTrashProvider = TrashProviderFactory.getInstance(context);
        emptyAbnormal(context, false);
        return null;
    }
}
