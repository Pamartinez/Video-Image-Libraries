package com.samsung.android.gallery.database.dal.local.table;

import A.a;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.utils.BnRConstants;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumBnRHelper {
    private static final AlbumBnRHelper sInstance = new AlbumBnRHelper();
    private final Uri ALBUM_URI;
    private final Boolean USE_MX_ALBUMS;

    public AlbumBnRHelper() {
        Uri uri;
        Boolean valueOf = Boolean.valueOf(PreferenceFeatures.isEnabled(PreferenceFeatures.MxAlbums));
        this.USE_MX_ALBUMS = valueOf;
        if (valueOf.booleanValue()) {
            uri = LocalDatabase.MX_ALBUM_URI;
        } else {
            uri = LocalDatabase.ALBUM_URI;
        }
        this.ALBUM_URI = uri;
    }

    private String getBackupPath() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(FileUtils.EXTERNAL_STORAGE_DIR);
        return C0212a.p(sb2, File.separator, "SdCardBackUp");
    }

    public static AlbumBnRHelper getInstance() {
        return sInstance;
    }

    private long[] getMinMaxOrder(Context context) {
        Cursor query;
        Throwable th;
        try {
            query = context.getContentResolver().query(this.ALBUM_URI, new String[]{"min(album_order), max(album_order)"}, (String) null, (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    long[] jArr = {query.getLong(0), query.getLong(1)};
                    query.close();
                    return jArr;
                }
            }
            if (query == null) {
                return null;
            }
            query.close();
            return null;
        } catch (Exception e) {
            Log.e("AlbumBnRHelper", e.toString());
            return null;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    private String getSdRoPath(String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith("/mnt/media_rw/")) {
            return str;
        }
        return str.replaceFirst("/mnt/media_rw/", "/storage/");
    }

    private String getVolumeName(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (FileUtils.isInExternalDir(str)) {
            return "external_primary";
        }
        return FileUtils.getSdcardVolume(str).toLowerCase();
    }

    private boolean hasOrderInfo(ArrayList<AlbumEntry> arrayList) {
        if (arrayList.isEmpty() || !arrayList.get(0).hasOrderInfo()) {
            return false;
        }
        return true;
    }

    private boolean isNonNormalType(Cursor cursor) {
        if (!this.USE_MX_ALBUMS.booleanValue()) {
            return false;
        }
        int i2 = cursor.getInt(cursor.getColumnIndex("__albumType"));
        if (i2 == AlbumType.AutoUpdated.toInt() || i2 == AlbumType.SystemMerged.toInt()) {
            return true;
        }
        return false;
    }

    private void restoreAlbumPath(AlbumEntry albumEntry, String str, String str2) {
        String replaceFirst = albumEntry.getAlbumPath().replaceFirst(str, str2);
        if (!FileUtils.isEmptyFolder(replaceFirst)) {
            Log.d("AlbumBnRHelper", "restore album path", Logger.getEncodedString(str), Logger.getEncodedString(str2));
            albumEntry.setAlbumPath(replaceFirst);
            albumEntry.setBucketId(FileUtils.getBucketId(replaceFirst));
            if (albumEntry.getCoverPath() != null && !albumEntry.getCoverPath().isEmpty()) {
                albumEntry.setAlbumCoverPath(albumEntry.getCoverPath().replaceFirst(str, str2));
            }
        }
    }

    private void restoreKnoxTargetPath(AlbumEntry albumEntry) {
        String albumPath = albumEntry.getAlbumPath();
        if (albumPath != null && !albumPath.startsWith(FileUtils.EXTERNAL_STORAGE_DIR)) {
            restoreAlbumPath(albumEntry, FileUtils.getStorageName(albumPath), FileUtils.EXTERNAL_STORAGE_DIR);
        }
    }

    private void restoreSdCardTargetPath(Context context, AlbumEntry albumEntry) {
        if (PreferenceFeatures.OneUi41.SUPPORT_PERMANENT_ALBUM_COVER) {
            Log.w("AlbumBnRHelper", "no need getRestoreSdCardTargetPath");
            return;
        }
        String albumPath = albumEntry.getAlbumPath();
        if (FileUtils.isInExternalDir(albumPath)) {
            return;
        }
        if (!FileUtils.hasSdcardVolume() || !FileUtils.isSdcardMounted(context)) {
            String sdcardVolume = FileUtils.getSdcardVolume(albumPath);
            if (!TextUtils.isEmpty(sdcardVolume)) {
                restoreAlbumPath(albumEntry, C0212a.l("/storage/", sdcardVolume), getBackupPath());
            }
        }
    }

    private boolean saveEntry(Context context, AlbumEntry albumEntry, boolean z) {
        if (z || (albumEntry.hasCoverInfo() && FileUtils.exists(albumEntry.getCoverPath()))) {
            Log.v("AlbumBnRHelper", "Entry{" + albumEntry.getTitle() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + albumEntry.isFolder() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + albumEntry.hasCoverInfo() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + albumEntry.hasFolderInfo() + "}");
            try {
                ContentValues contentValues = getContentValues(albumEntry, z);
                return insertOrUpdateAlbum(context.getContentResolver(), contentValues, "__bucketID = " + albumEntry.getBucketId());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            Log.e("AlbumBnRHelper", "entry.order and entry.cover_path is not in device");
            return false;
        }
    }

    private void updateVirtualAlbumOrder(Context context) {
        long[] minMaxOrder;
        long j2;
        int bucketId = FileUtils.getBucketId("Virtual/Recently");
        int bucketId2 = FileUtils.getBucketId("Virtual/Favourites");
        String d = a.d(bucketId, bucketId2, "__bucketID in (", GlobalPostProcInternalPPInterface.SPLIT_REGEX, ")");
        HashMap hashMap = new HashMap();
        Cursor albumCursor = getAlbumCursor(context.getContentResolver(), d);
        if (albumCursor != null) {
            try {
                if (albumCursor.moveToFirst()) {
                    do {
                        hashMap.put(Integer.valueOf(albumCursor.getInt(albumCursor.getColumnIndex("__bucketID"))), Long.valueOf(albumCursor.getLong(albumCursor.getColumnIndex("album_order"))));
                    } while (albumCursor.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (albumCursor != null) {
            albumCursor.close();
        }
        if (!hashMap.isEmpty() && (minMaxOrder = getMinMaxOrder(context)) != null) {
            long longValue = ((Long) hashMap.get(Integer.valueOf(bucketId))).longValue();
            long longValue2 = ((Long) hashMap.get(Integer.valueOf(bucketId2))).longValue();
            long j3 = 0;
            if (!(minMaxOrder[0] == 0 && minMaxOrder[1] == 0 && longValue == 0 && longValue2 == 0) && (!PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums) || longValue == 0 || longValue2 == 0)) {
                long j8 = minMaxOrder[0];
                if (longValue2 > j8) {
                    if (j8 == 0) {
                        j2 = 0;
                    } else {
                        j2 = j8 / 2;
                    }
                    updateAlbumOrder(context, bucketId2, j2);
                } else {
                    j2 = 0;
                }
                if (longValue > minMaxOrder[0]) {
                    if (j2 != 0) {
                        j3 = j2 / 2;
                    }
                    updateAlbumOrder(context, bucketId, j3);
                    return;
                }
                return;
            }
            Log.i("AlbumBnRHelper", "no need to update virtual album order");
            return;
        }
        return;
        throw th;
    }

    public boolean backupAlbumDatabase(Context context, String str, String str2) {
        try {
            ArrayList<AlbumEntry> entries = getEntries(context);
            if (!entries.isEmpty()) {
                return AlbumEntryJsonUtil.saveJsonForAlbumDb(entries, str, str2);
            }
            return false;
        } catch (Exception e) {
            Log.e((CharSequence) "AlbumBnRHelper", "backupAlbumDatabase failed", (Throwable) e);
            return false;
        }
    }

    public void backupRestoredFile(String str) {
        if (FileUtils.exists(str)) {
            String str2 = "/data/sec/gallery/smartswitch" + File.separator + getAlbumFilename(FileUtils.getDateModified(str));
            FileUtils.makeParentIfAbsent(str2);
            FileUtils.copy(str, str2);
        }
    }

    public void convertAlbumPath(Context context, ArrayList<AlbumEntry> arrayList) {
        Iterator<AlbumEntry> it = arrayList.iterator();
        while (it.hasNext()) {
            AlbumEntry next = it.next();
            if (next.getAlbumPath() != null && !next.getAlbumPath().isEmpty()) {
                if (Features.isEnabled(Features.IS_KNOX_MODE)) {
                    restoreKnoxTargetPath(next);
                } else {
                    restoreSdCardTargetPath(context, next);
                }
            }
        }
    }

    public boolean copyAlbumCover(File file, File file2) {
        File[] fileArr;
        if (file == null || file2 == null) {
            Log.w("AlbumBnRHelper", "copyAlbumCover : srcAlbumCoverDir or dstAlbumCoverDir is null.");
            return false;
        }
        try {
            if (!file.exists()) {
                Log.w("AlbumBnRHelper", "copyAlbumCover : srcAlbumCoverDir is not existed.");
                return false;
            }
            if (file.isDirectory()) {
                FileUtils.makeDirectoryIfAbsent(file2);
                File[] listFiles = file.listFiles();
                if (listFiles == null) {
                    return true;
                }
                for (File file3 : listFiles) {
                    if (file3 != null) {
                        fileArr = file3.listFiles();
                    } else {
                        fileArr = null;
                    }
                    if (fileArr != null && fileArr.length > 0) {
                        String str = file2.getAbsolutePath() + File.separator + file3.getName();
                        FileUtils.makeDirectoryIfAbsent(str);
                        for (File file4 : fileArr) {
                            if (file4 != null && file4.exists()) {
                                FileUtils.copy(file4, FileUtils.createNewFileIfAbsent(str + File.separator + file4.getName()));
                            }
                        }
                    }
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.e((CharSequence) "AlbumBnRHelper", "copyAlbumCover failed : ", e.getMessage());
        }
    }

    public boolean deleteAllMxAlbumTable(Context context, SQLiteDatabase sQLiteDatabase) {
        if (context.getContentResolver().delete(LocalDatabase.MX_ALBUM_URI, (String) null, (String[]) null) <= 0) {
            return false;
        }
        new MxAlbumUpdateHelper(sQLiteDatabase).migrationAlbumData();
        return false;
    }

    public Cursor getAlbumCursor(ContentResolver contentResolver, String str) {
        return contentResolver.query(this.ALBUM_URI, this.USE_MX_ALBUMS.booleanValue() ? new String[]{"__Title", "__absPath", "__bucketID", "cover_path", "default_cover_path", "cover_rect", "folder_id", "folder_name", "album_order", "__albumLevel", "__albumType", "essential_album_order"} : new String[]{"__Title", "__absPath", "__bucketID", "cover_path", "default_cover_path", "cover_rect", "folder_id", "folder_name", "album_order"}, str, (String[]) null, (String) null);
    }

    public String getAlbumFilename(long j2) {
        Object obj;
        String str;
        long j3 = j2 % 1000;
        StringBuilder sb2 = new StringBuilder("album_");
        sb2.append(TimeUtil.getFileTimestamp(j2));
        if (j3 < 10) {
            str = "00";
        } else if (j3 < 100) {
            str = "0";
        } else {
            obj = Long.valueOf(j3);
            sb2.append(obj);
            sb2.append(".txt");
            return sb2.toString();
        }
        obj = a.f(str, j3);
        sb2.append(obj);
        sb2.append(".txt");
        return sb2.toString();
    }

    public String getBackupFileName() {
        return "/data/sec/gallery/backup" + File.separator + getAlbumFilename(System.currentTimeMillis());
    }

    public ContentValues getContentValues(AlbumEntry albumEntry, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("__Title", albumEntry.getTitle());
        if (!albumEntry.isFolder()) {
            contentValues.put("__absPath", getSdRoPath(albumEntry.getAlbumPath()));
            contentValues.put("__volumeName", getVolumeName(albumEntry.getAlbumPath()));
            contentValues.put("album_count", 0);
        } else {
            contentValues.putNull("__absPath");
            contentValues.put("album_count", -1);
            contentValues.put("__ishide", -1);
            contentValues.put("__sefFileType", -1);
            contentValues.put("__isDrm", 0);
            contentValues.put("__dateModified", Long.valueOf(System.currentTimeMillis() / 1000));
        }
        contentValues.put("__bucketID", Integer.valueOf(albumEntry.getBucketId()));
        if (albumEntry.hasCoverInfo()) {
            contentValues.put("cover_path", getSdRoPath(albumEntry.getCoverPath()));
        } else {
            contentValues.putNull("cover_path");
        }
        if (albumEntry.hasDefaultCoverInfo()) {
            contentValues.put("default_cover_path", getSdRoPath(albumEntry.getDefaultCoverPath()));
        } else {
            contentValues.putNull("default_cover_path");
        }
        if (albumEntry.hasCoverRect()) {
            contentValues.put("cover_rect", albumEntry.getCoverRect());
        } else {
            contentValues.putNull("cover_rect");
        }
        if (z) {
            contentValues.put("album_order", Long.valueOf(albumEntry.getAlbumOrder()));
            if (albumEntry.hasFolderInfo()) {
                contentValues.put("folder_id", Integer.valueOf(albumEntry.getFolderId()));
                contentValues.put("folder_name", albumEntry.getFolderName());
            } else {
                contentValues.putNull("folder_id");
                contentValues.putNull("folder_name");
            }
        }
        if (this.USE_MX_ALBUMS.booleanValue()) {
            if (albumEntry.getAlbumType() > 0) {
                contentValues.put("__albumType", Integer.valueOf(albumEntry.getAlbumType()));
            } else {
                Integer asInteger = contentValues.getAsInteger("album_count");
                if (asInteger == null || asInteger.intValue() != -1) {
                    contentValues.put("__albumType", Integer.valueOf(AlbumType.None.toInt()));
                } else {
                    contentValues.put("__albumType", Integer.valueOf(AlbumType.Folder.toInt()));
                }
            }
            if (albumEntry.getAlbumLevel() > 0) {
                contentValues.put("__albumLevel", 1);
            } else {
                contentValues.put("__albumLevel", 0);
            }
            if (albumEntry.getAlbumEssOrder() > 0) {
                contentValues.put("essential_album_order", Long.valueOf(albumEntry.getAlbumEssOrder()));
            }
        }
        return contentValues;
    }

    public ArrayList<AlbumEntry> getEntries(Context context) {
        Cursor albumCursor;
        ArrayList<AlbumEntry> arrayList = new ArrayList<>();
        HashSet hashSet = new HashSet();
        try {
            albumCursor = getAlbumCursor(context.getContentResolver(), (String) null);
            if (albumCursor != null) {
                if (albumCursor.moveToFirst()) {
                    do {
                        if (isNonNormalType(albumCursor)) {
                            hashSet.add(Integer.valueOf(albumCursor.getInt(albumCursor.getColumnIndex("__bucketID"))));
                        }
                        arrayList.add(new AlbumEntry(albumCursor));
                    } while (albumCursor.moveToNext());
                }
            }
            if (albumCursor != null) {
                albumCursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        ArrayList arrayList2 = new ArrayList();
        if (hashSet.size() > 0) {
            Iterator<AlbumEntry> it = arrayList.iterator();
            while (it.hasNext()) {
                AlbumEntry next = it.next();
                if (next.getFolderId() != 0 && hashSet.contains(Integer.valueOf(next.getFolderId()))) {
                    next.setFolderInfo(0, (String) null);
                }
                if (hashSet.contains(Integer.valueOf(next.getBucketId()))) {
                    arrayList2.add(next);
                }
            }
            if (arrayList2.size() > 0) {
                arrayList.removeAll(arrayList2);
            }
        }
        return arrayList;
        throw th;
    }

    public boolean hasRestoreFile() {
        return FileUtils.exists(BnRConstants.KEEP_RESTORE_DIR + File.separator + "BACKUP_ALBUM_DB.txt");
    }

    public boolean insertOrUpdateAlbum(ContentResolver contentResolver, ContentValues contentValues, String str) {
        if (contentResolver.update(this.ALBUM_URI, contentValues, str, (String[]) null) == 0 && contentResolver.insert(this.ALBUM_URI, contentValues) == null) {
            return false;
        }
        return true;
    }

    public void keepRestoreFile() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(BnRConstants.KEEP_RESTORE_DIR);
        String p6 = C0212a.p(sb2, File.separator, "BACKUP_ALBUM_DB.txt");
        if (FileUtils.exists(p6)) {
            backupRestoredFile(p6);
            FileUtils.delete(p6);
        }
    }

    public boolean restoreAlbumDatabase(Context context, String str, String str2) {
        try {
            return saveEntries(context, AlbumEntryJsonUtil.parseJsonForAlbumDb(str, str2));
        } catch (Exception e) {
            Log.e((CharSequence) "AlbumBnRHelper", "restoreAlbumDatabase failed", (Throwable) e);
            return false;
        }
    }

    public boolean saveEntries(Context context, ArrayList<AlbumEntry> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        boolean hasOrderInfo = hasOrderInfo(arrayList);
        a.v("saveEntries {order-info=", "}", "AlbumBnRHelper", hasOrderInfo);
        convertAlbumPath(context, arrayList);
        if (hasOrderInfo) {
            saveEntriesForNested(context, arrayList, arrayList2);
        } else {
            arrayList2.addAll(arrayList);
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            if (!saveEntry(context, (AlbumEntry) it.next(), hasOrderInfo)) {
                Log.e("AlbumBnRHelper", "Entry restore failed");
            }
        }
        if (!this.USE_MX_ALBUMS.booleanValue()) {
            return true;
        }
        updateVirtualAlbumOrder(context);
        return true;
    }

    public void saveEntriesForNested(Context context, ArrayList<AlbumEntry> arrayList, ArrayList<AlbumEntry> arrayList2) {
        long j2;
        ArrayList<AlbumEntry> arrayList3 = arrayList2;
        Log.d("AlbumBnRHelper", "save entries for nested");
        ArrayList<AlbumEntry> entries = getEntries(context);
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        Iterator<AlbumEntry> it = arrayList.iterator();
        long j3 = 0;
        while (it.hasNext()) {
            AlbumEntry next = it.next();
            hashSet.add(Integer.valueOf(next.getBucketId()));
            if (next.isFolder()) {
                hashSet2.add(Integer.valueOf(next.getBucketId()));
            }
            j3 = Math.max(j3, next.getAlbumOrder());
        }
        Iterator<AlbumEntry> it2 = arrayList.iterator();
        while (true) {
            j2 = 1000000000000000007L;
            if (!it2.hasNext()) {
                break;
            }
            AlbumEntry next2 = it2.next();
            if (next2.hasFolderInfo() && !hashSet2.contains(Integer.valueOf(next2.getFolderId()))) {
                if (!hashSet3.contains(Integer.valueOf(next2.getFolderId()))) {
                    AlbumEntry albumEntry = new AlbumEntry(next2.getFolderId(), next2.getFolderName(), next2.getAlbumOrder(), 0, (String) null, 0, 0, 0);
                    hashSet3.add(Integer.valueOf(albumEntry.getBucketId()));
                    arrayList3.add(albumEntry);
                }
                next2.setAlbumOrder(1000000000000000007L);
            }
            arrayList3.add(next2);
        }
        if (j3 == 0) {
            j2 = 0;
        }
        Iterator<AlbumEntry> it3 = entries.iterator();
        while (it3.hasNext()) {
            AlbumEntry next3 = it3.next();
            int bucketId = next3.getBucketId();
            if (!hashSet.contains(Integer.valueOf(bucketId)) && !hashSet3.contains(Integer.valueOf(bucketId))) {
                if (j2 != 0 && (BucketUtils.isRecent(bucketId) || BucketUtils.isFavourite(bucketId))) {
                    j2 = 0;
                }
                next3.setAlbumOrder(j2);
                arrayList3.add(next3);
            }
        }
    }

    public int updateAlbumOrder(Context context, int i2, long j2) {
        String i7 = C0086a.i(i2, "__bucketID=");
        ContentValues contentValues = new ContentValues();
        contentValues.put("album_order", Long.valueOf(j2));
        Log.d("AlbumBnRHelper", "updateAlbumOrder = " + i2 + " order = " + j2);
        return context.getContentResolver().update(this.ALBUM_URI, contentValues, i7, (String[]) null);
    }

    public void updateMissingAlbumData(Context context, SQLiteDatabase sQLiteDatabase) {
        new MxAlbumUpdateHelper(sQLiteDatabase).updateMissingAlbumData();
        Log.d("AlbumBnRHelper", "updateMissingAlbumData");
    }
}
