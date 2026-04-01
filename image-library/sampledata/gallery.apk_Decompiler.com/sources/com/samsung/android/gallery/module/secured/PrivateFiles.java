package com.samsung.android.gallery.module.secured;

import A.a;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PrivateFiles {
    static final PrivateFiles sInstance = new PrivateFiles();
    final HashSet<String> mNonVisibleSet = new HashSet<>(List.of("databases", "nde"));

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FileData {
        String data;
        String displayName;
        long id;
        String originalFileHash;

        public FileData(long j2, String str, String str2, String str3) {
            this.id = j2;
            this.data = str;
            this.displayName = str2;
            this.originalFileHash = str3;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("FileData{");
            sb2.append(this.id);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.data);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.displayName);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            return C0212a.p(sb2, this.originalFileHash, "}");
        }
    }

    public static PrivateFiles getInstance() {
        return sInstance;
    }

    public ArrayList<File> listFiles() {
        ArrayList<File> arrayList = new ArrayList<>();
        String create = SeApiCompat.getPassStorage().create();
        if (create == null || !create.startsWith("/data/sec/gallery/secured")) {
            arrayList.addAll(listFiles(new File("/data/sec/gallery/secured")));
        }
        if (create != null) {
            arrayList.addAll(listFiles(new File(create)));
        }
        return arrayList;
    }

    public HashMap<String, FileData> queryDatabase() {
        Cursor queryData;
        Throwable th;
        HashMap<String, FileData> hashMap = new HashMap<>();
        try {
            queryData = PrivateDatabase.getInstance().queryData();
            if (queryData != null) {
                if (queryData.moveToFirst()) {
                    int columnIndex = queryData.getColumnIndex("__absID");
                    int columnIndex2 = queryData.getColumnIndex("__absPath");
                    int columnIndex3 = queryData.getColumnIndex("__Title");
                    int columnIndex4 = queryData.getColumnIndex("__origin_file_hash");
                    do {
                        long j2 = queryData.getLong(columnIndex);
                        String string = queryData.getString(columnIndex2);
                        hashMap.put(string, new FileData(j2, string, queryData.getString(columnIndex3), queryData.getString(columnIndex4)));
                    } while (queryData.moveToNext());
                }
            }
            if (queryData != null) {
                queryData.close();
            }
            return hashMap;
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("queryDatabase failed. e="), "PrivateFiles");
            return hashMap;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public void restore(ArrayList<File> arrayList, Consumer<Float> consumer) {
        if (arrayList == null || arrayList.isEmpty()) {
            Log.i("PrivateFiles", "restore skip. no files");
            return;
        }
        SeApiCompat.getPassStorage().getRoot();
        HashMap<String, FileData> queryDatabase = queryDatabase();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(StorageInfo.getDefault().dcim);
        String p6 = C0212a.p(sb2, File.separator, "PrivateAlbum");
        File file = new File(p6);
        if (file.exists() || file.mkdirs()) {
            Log.d("PrivateFiles", "restore files=" + arrayList.size() + "");
            int size = arrayList.size();
            int i2 = 0;
            while (true) {
                String str = null;
                if (i2 < size) {
                    File file2 = arrayList.get(i2);
                    FileData fileData = queryDatabase.get(file2.getPath());
                    if (fileData != null && !TextUtils.isEmpty(fileData.displayName)) {
                        str = fileData.displayName;
                    }
                    if (str == null) {
                        str = file2.getName();
                    }
                    StringBuilder s = C0212a.s(p6);
                    String str2 = File.separator;
                    String p8 = C0212a.p(s, str2, str);
                    if (FileUtils.exists(p8)) {
                        StringBuilder t = C0212a.t(p6, str2);
                        t.append(FileUtils.getUniqueFilename(p6, str));
                        p8 = t.toString();
                    }
                    if (!FileUtils.copy(file2.getPath(), p8)) {
                        Log.e((CharSequence) "PrivateFiles", "restore file failed", Integer.valueOf(i2), Logger.getEncodedString(p8));
                    } else if (fileData != null) {
                        Log.d("PrivateFiles", "restore file", Integer.valueOf(i2), Logger.getEncodedString(p8));
                        try {
                            if (PrivateDatabase.getInstance().deleteIfPresent(fileData.data, fileData.originalFileHash)) {
                                PrivateDatabase.getInstance().delete(fileData.id);
                            } else {
                                Log.w("PrivateFiles", "retore file, but failed to delete");
                            }
                        } catch (Exception e) {
                            a.s(e, new StringBuilder("restore file, but failed to delete. e="), "PrivateFiles");
                        }
                    } else if (!file2.delete()) {
                        Log.w("PrivateFiles", "restore file, but failed to delete");
                    }
                    if (consumer != null) {
                        consumer.accept(Float.valueOf(((float) (i2 + 1)) / ((float) size)));
                    }
                    i2++;
                } else {
                    MediaScanner.scanFolder(p6, (MediaScannerListener) null);
                    return;
                }
            }
        } else {
            Log.e((CharSequence) "PrivateFiles", "restore mkdirs failed", file);
        }
    }

    public ArrayList<File> listFiles(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    arrayList.add(file2);
                } else if (file2.isDirectory() && !this.mNonVisibleSet.contains(file2.getName())) {
                    arrayList.addAll(listFiles(file2));
                }
            }
        }
        return arrayList;
    }
}
