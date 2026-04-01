package com.samsung.android.gallery.module.trash.helper;

import A.a;
import C3.l;
import C3.o;
import N2.j;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.module.dataset.K;
import com.samsung.android.gallery.module.trash.abstraction.ITrashProvider;
import com.samsung.android.gallery.module.trash.factory.TrashProviderFactory;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MyFilesApi;
import com.samsung.android.gallery.support.utils.StorageInfo;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import qa.e;
import t4.C0517a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashMpRecoverTask {
    private ITrashProvider mTrashProvider;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TrashMpRecoverJob extends IdleWorkerJob {
        public TrashMpRecoverJob(int i2, IdleWorkerJob.Type type) {
            super(i2, type);
        }

        public void execute(Context context) {
            LatchBuilder.executeLatch(10000, new l(context, 7));
        }
    }

    private String convertTrashPath(String str, String str2) {
        StringBuilder s = C0212a.s(FileUtils.getParent(str));
        s.append(File.separator);
        s.append(FileUtils.getNameFromPath(str2));
        return s.toString();
    }

    private ArrayList<String> getRecoverFileList(Context context) {
        HashSet<String> hashSet;
        ArrayList<String> trashFileList = getTrashFileList(context);
        if (!trashFileList.isEmpty()) {
            if (getTrashDbCount() > 0) {
                hashSet = getTrashDbSet();
            } else {
                hashSet = null;
            }
            if (hashSet != null && !hashSet.isEmpty()) {
                trashFileList.removeIf(new o(hashSet, 1));
            }
        }
        return trashFileList;
    }

    private int getTrashDbCount() {
        int[] trashCount = this.mTrashProvider.getTrashCount(false);
        return trashCount[0] + trashCount[1];
    }

    private HashSet<String> getTrashDbSet() {
        Cursor trashCursor;
        HashSet<String> hashSet = new HashSet<>();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            trashCursor = this.mTrashProvider.getTrashCursor(false);
            if (trashCursor != null) {
                if (trashCursor.moveToFirst()) {
                    int columnIndex = trashCursor.getColumnIndex("__absPath");
                    int columnIndex2 = trashCursor.getColumnIndex("__restoreExtra");
                    do {
                        String string = trashCursor.getString(columnIndex);
                        String str = "";
                        String string2 = trashCursor.getString(columnIndex2);
                        if (!TextUtils.isEmpty(string2)) {
                            str = new JSONObject(string2).optString("__cloudTP");
                        }
                        if (!TextUtils.isEmpty(string)) {
                            hashSet.add(string);
                        }
                        if (!TextUtils.isEmpty(str)) {
                            hashSet.add(convertTrashPath(string, str));
                        }
                    } while (trashCursor.moveToNext());
                }
            }
            if (trashCursor != null) {
                trashCursor.close();
            }
        } catch (JSONException e) {
            Log.e("TrashMpRecoverTask", "getTrashDbSet:" + e.getMessage());
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        a.A(new Object[]{Integer.valueOf(hashSet.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("trash database"), "TrashMpRecoverTask");
        return hashSet;
        throw th;
    }

    private ArrayList<String> getTrashFileList(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList<File> trashDirs = TrashHelperFactory.getExternalHelper(context).getTrashDirs();
        ArrayList<String> arrayList = new ArrayList<>();
        if (Features.isEnabled(Features.SUPPORT_MY_FILES_API)) {
            MyFilesApi.loadListFiles1stDepth(arrayList, (String[]) trashDirs.stream().filter(new e(3)).map(new K(5)).toArray(new C0517a(6)));
            arrayList.removeIf(new e(5));
        } else {
            trashDirs.stream().filter(new e(3)).map(new q8.a(10)).filter(new e(6)).flatMap(new q8.a(11)).filter(new e(7)).map(new K(5)).filter(new e(4)).forEach(new f4.a(arrayList, 17));
        }
        a.A(new Object[]{j.g(new StringBuilder("dirs="), trashDirs), Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("trash files"), "TrashMpRecoverTask");
        return arrayList;
    }

    private File getTrashFinalPath(Context context, String str) {
        String volumeName = FileUtils.getVolumeName(str);
        ArrayList<File> trashDirs = TrashHelperFactory.getExternalHelper(context).getTrashDirs();
        File file = trashDirs.get(0);
        Iterator<File> it = trashDirs.iterator();
        while (it.hasNext()) {
            File next = it.next();
            if (next != null && next.getAbsolutePath().toUpperCase().contains(volumeName.toUpperCase())) {
                return next;
            }
        }
        return file;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$getTrashFileList$0(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getTrashFileList$2(File file) {
        if (file == null || !file.isFile()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getTrashFileList$3(String str) {
        return !str.endsWith(".nomedia");
    }

    private void moveFilesAndScan(Context context, List<String> list, long j2) {
        StorageInfo storageInfo;
        if (list != null && !list.isEmpty()) {
            HashSet hashSet = new HashSet();
            for (String next : list) {
                File trashFinalPath = getTrashFinalPath(context, next);
                String name = new File(next).getName();
                if (FileUtils.isInRemovableStorage(next)) {
                    storageInfo = StorageInfo.getRemovable();
                } else {
                    storageInfo = StorageInfo.getDefault();
                }
                String str = storageInfo.download;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(trashFinalPath.getAbsolutePath());
                String str2 = File.separator;
                j.z(sb2, str2, "uuid", str2);
                sb2.append(j2);
                sb2.append(str);
                sb2.append(str2);
                sb2.append(".!%#@$");
                sb2.append(str2);
                sb2.append(name);
                FileUtils.move(next, sb2.toString());
                hashSet.add(trashFinalPath.getAbsolutePath() + str2 + "uuid" + str2 + j2);
            }
            if (!hashSet.isEmpty()) {
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    context.getContentResolver().call(Uri.parse("content://sectrash"), "scan", (String) it.next(), (Bundle) null);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0070 A[Catch:{ Exception -> 0x003b }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0078 A[Catch:{ Exception -> 0x003b }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007b A[Catch:{ Exception -> 0x003b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void recoverFiles(android.content.Context r11) {
        /*
            r10 = this;
            java.lang.String r0 = "TrashMpRecoverTask"
            java.lang.String r1 = "recover mp trash file"
            java.lang.String r2 = "["
            java.lang.String r3 = "#last"
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x003b }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003b }
            r6.<init>()     // Catch:{ Exception -> 0x003b }
            java.io.File r7 = r11.getFilesDir()     // Catch:{ Exception -> 0x003b }
            r6.append(r7)     // Catch:{ Exception -> 0x003b }
            java.lang.String r7 = java.io.File.separator     // Catch:{ Exception -> 0x003b }
            r6.append(r7)     // Catch:{ Exception -> 0x003b }
            java.lang.String r7 = "trash_mp_recover.txt"
            r6.append(r7)     // Catch:{ Exception -> 0x003b }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x003b }
            com.samsung.android.gallery.support.utils.FileListHolder r7 = new com.samsung.android.gallery.support.utils.FileListHolder     // Catch:{ Exception -> 0x003b }
            r7.<init>(r6)     // Catch:{ Exception -> 0x003b }
            r8 = 200(0xc8, float:2.8E-43)
            java.util.List r7 = r7.trimLastLines(r8)     // Catch:{ Exception -> 0x003b }
            if (r7 == 0) goto L_0x003e
            boolean r9 = r7.isEmpty()     // Catch:{ Exception -> 0x003b }
            if (r9 == 0) goto L_0x0068
            goto L_0x003e
        L_0x003b:
            r10 = move-exception
            goto L_0x00d0
        L_0x003e:
            java.util.ArrayList r7 = r10.getRecoverFileList(r11)     // Catch:{ Exception -> 0x003b }
            boolean r9 = r7.isEmpty()     // Catch:{ Exception -> 0x003b }
            if (r9 == 0) goto L_0x0053
            com.samsung.android.gallery.support.utils.GalleryPreference r10 = com.samsung.android.gallery.support.utils.GalleryPreference.getInstanceDebug()     // Catch:{ Exception -> 0x003b }
            java.lang.String r11 = "TrashMpRecoverTask_job_finished"
            r1 = 1
            r10.saveState((java.lang.String) r11, (boolean) r1)     // Catch:{ Exception -> 0x003b }
            return
        L_0x0053:
            int r9 = r7.size()     // Catch:{ Exception -> 0x003b }
            if (r9 > r8) goto L_0x005a
            goto L_0x0068
        L_0x005a:
            com.samsung.android.gallery.support.utils.FileListHolder r9 = new com.samsung.android.gallery.support.utils.FileListHolder     // Catch:{ Exception -> 0x003b }
            r9.<init>(r6)     // Catch:{ Exception -> 0x003b }
            com.samsung.android.gallery.support.utils.FileListHolder r6 = r9.writeFile(r7)     // Catch:{ Exception -> 0x003b }
            java.util.List r6 = r6.trimLastLines(r8)     // Catch:{ Exception -> 0x003b }
            r7 = r6
        L_0x0068:
            if (r7 == 0) goto L_0x0078
            boolean r6 = r7.isEmpty()     // Catch:{ Exception -> 0x003b }
            if (r6 != 0) goto L_0x0078
            r10.moveFilesAndScan(r11, r7, r4)     // Catch:{ Exception -> 0x003b }
            int r10 = r7.size()     // Catch:{ Exception -> 0x003b }
            goto L_0x0079
        L_0x0078:
            r10 = 0
        L_0x0079:
            if (r10 <= 0) goto L_0x00b0
            com.samsung.android.gallery.support.utils.GalleryPreference r11 = com.samsung.android.gallery.support.utils.GalleryPreference.getInstanceAnalytics()     // Catch:{ Exception -> 0x003b }
            java.lang.Class<com.samsung.android.gallery.module.trash.helper.TrashMpRecoverTask$TrashMpRecoverJob> r6 = com.samsung.android.gallery.module.trash.helper.TrashMpRecoverTask.TrashMpRecoverJob.class
            java.lang.String r6 = r6.getSimpleName()     // Catch:{ Exception -> 0x003b }
            java.lang.String r3 = r6.concat(r3)     // Catch:{ Exception -> 0x003b }
            r6 = 0
            r11.saveState((java.lang.String) r3, (long) r6)     // Catch:{ Exception -> 0x003b }
            com.samsung.android.gallery.database.dal.DebugLogger r11 = com.samsung.android.gallery.database.dal.DebugLogger.getDeleteInstance()     // Catch:{ Exception -> 0x003b }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003b }
            r3.<init>(r2)     // Catch:{ Exception -> 0x003b }
            com.samsung.android.gallery.module.trash.abstraction.TrashLogType r2 = com.samsung.android.gallery.module.trash.abstraction.TrashLogType.TRASH_MP_RECOVER     // Catch:{ Exception -> 0x003b }
            r3.append(r2)     // Catch:{ Exception -> 0x003b }
            java.lang.String r2 = "]["
            r3.append(r2)     // Catch:{ Exception -> 0x003b }
            r3.append(r10)     // Catch:{ Exception -> 0x003b }
            java.lang.String r2 = "]"
            r3.append(r2)     // Catch:{ Exception -> 0x003b }
            java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x003b }
            r11.insertLog(r2)     // Catch:{ Exception -> 0x003b }
        L_0x00b0:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003b }
            r11.<init>(r1)     // Catch:{ Exception -> 0x003b }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ Exception -> 0x003b }
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x003b }
            java.lang.Object[] r10 = new java.lang.Object[]{r10, r1}     // Catch:{ Exception -> 0x003b }
            java.lang.String r10 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r10)     // Catch:{ Exception -> 0x003b }
            r11.append(r10)     // Catch:{ Exception -> 0x003b }
            java.lang.String r10 = r11.toString()     // Catch:{ Exception -> 0x003b }
            com.samsung.android.gallery.support.utils.Log.d(r0, r10)     // Catch:{ Exception -> 0x003b }
            return
        L_0x00d0:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r1 = "recover mp trash file failed. e="
            r11.<init>(r1)
            N2.j.s(r10, r11, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.helper.TrashMpRecoverTask.recoverFiles(android.content.Context):void");
    }

    public void recoverTrashFiles(Context context) {
        if (context != null && !GalleryPreference.getInstanceDebug().loadBoolean("TrashMpRecoverTask_job_finished", false)) {
            this.mTrashProvider = TrashProviderFactory.getInstance(context);
            recoverFiles(context);
        }
    }
}
