package com.samsung.android.gallery.module.album;

import A.a;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.local.LocalAlbumsApi;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import i.C0212a;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AlbumDelegate {
    private static final Uri EXTERNAL_URI = MediaStore.Files.getContentUri("external");

    public static void clearAlbumCoverSyncDB(ContentValues contentValues, boolean z) {
        if (z) {
            String albumCoverSyncData = getAlbumCoverSyncData(true, (String) null);
            if (albumCoverSyncData != null) {
                contentValues.put("__coverSyncData", albumCoverSyncData);
                contentValues.put("__coverSyncDirty", 1);
                return;
            }
            return;
        }
        contentValues.putNull("__coverSyncRecordId");
        contentValues.putNull("__coverSyncData");
        contentValues.putNull("__coverSyncDirty");
    }

    private static Bitmap createCoverBitmap(MediaItem mediaItem) {
        if (mediaItem == null) {
            return null;
        }
        return BitmapUtils.getDecodedBitmap(mediaItem.getPath(), BitmapOptionsFactory.of(mediaItem).adjustInSampleSize(992));
    }

    public static String getAlbumCoverSavedPath(Context context) {
        File externalFilesDir = context.getExternalFilesDir(".album");
        if (externalFilesDir == null) {
            return "";
        }
        return externalFilesDir.getAbsolutePath();
    }

    private static String getAlbumCoverSyncData(boolean z, String str) {
        String str2;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("data_sync_timestamp", System.currentTimeMillis());
            if (z) {
                str2 = "1";
            } else {
                str2 = "0";
            }
            jSONObject.put("data_sync_deleted", str2);
            jSONObject.put("data_sync_filehash", str);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static MediaItem getCoverItem(String str) {
        Cursor query;
        try {
            query = DbCompat.query(DbKey.ALL_PICTURES, new b(str));
            if (query != null) {
                if (query.moveToFirst()) {
                    MediaItem create = MediaItemBuilder.create(query);
                    query.close();
                    return create;
                }
            }
            if (query == null) {
                return null;
            }
            query.close();
            return null;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getCoverItem failed. e="), "AlbumDelegate");
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static String getReplacedCoverPath(String str, String str2) {
        if (PreferenceFeatures.OneUi41.SUPPORT_PERMANENT_ALBUM_COVER) {
            return str;
        }
        return getReplacedPath(str, str2);
    }

    private static String getReplacedPath(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder s = C0212a.s(str2);
        s.append(str.substring(str.lastIndexOf(File.separator)));
        return s.toString();
    }

    private static String getTargetFilename(String str, boolean z) {
        String nameFromPath = FileUtils.getNameFromPath(str);
        if (TextUtils.isEmpty(nameFromPath)) {
            nameFromPath = "cover.jpg";
        }
        if (!z || nameFromPath.toLowerCase().endsWith(".jpg")) {
            return nameFromPath;
        }
        return FileUtils.replaceExtension(nameFromPath, "jpg");
    }

    private static String getTargetSavePath(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        StringBuilder s = C0212a.s(str);
        s.append(File.separator);
        s.append(getTargetFilename(str2, z));
        return s.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0039 A[Catch:{ all -> 0x0027, all -> 0x002d, Exception -> 0x0033 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean hasAlbumSyncStatus(android.content.Context r8) {
        /*
            java.lang.String r0 = "__bucketID"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            java.lang.String r4 = "album_sync_status = 1"
            java.lang.String r6 = "__bucketID limit 1"
            r7 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch:{ Exception -> 0x0033 }
            com.samsung.android.gallery.database.dal.local.LocalAlbumsApi r8 = new com.samsung.android.gallery.database.dal.local.LocalAlbumsApi     // Catch:{ Exception -> 0x0033 }
            r8.<init>()     // Catch:{ Exception -> 0x0033 }
            android.net.Uri r2 = r8.getTableUri()     // Catch:{ Exception -> 0x0033 }
            r5 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0033 }
            if (r8 == 0) goto L_0x0036
            boolean r0 = r8.moveToFirst()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x0036
            r0 = 1
            goto L_0x0037
        L_0x0027:
            r0 = move-exception
            r1 = r0
            r8.close()     // Catch:{ all -> 0x002d }
            goto L_0x0032
        L_0x002d:
            r0 = move-exception
            r8 = r0
            r1.addSuppressed(r8)     // Catch:{ Exception -> 0x0033 }
        L_0x0032:
            throw r1     // Catch:{ Exception -> 0x0033 }
        L_0x0033:
            r0 = move-exception
            r8 = r0
            goto L_0x003d
        L_0x0036:
            r0 = r7
        L_0x0037:
            if (r8 == 0) goto L_0x003c
            r8.close()     // Catch:{ Exception -> 0x0033 }
        L_0x003c:
            return r0
        L_0x003d:
            r8.printStackTrace()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.album.AlbumDelegate.hasAlbumSyncStatus(android.content.Context):boolean");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$saveCoverFile$0(String str, Context context, int i2, CountDownLatch countDownLatch, String[] strArr, ThreadPool.JobContext jobContext) {
        boolean z;
        boolean z3;
        String str2;
        String str3;
        long currentTimeMillis = System.currentTimeMillis();
        MediaItem coverItem = getCoverItem(str);
        Bitmap createCoverBitmap = createCoverBitmap(coverItem);
        String str4 = getAlbumCoverSavedPath(context) + File.separator + i2;
        if (createCoverBitmap != null) {
            z = true;
        } else {
            z = false;
        }
        String targetSavePath = getTargetSavePath(str4, str, z);
        if (TextUtils.isEmpty(targetSavePath)) {
            countDownLatch.countDown();
            return null;
        }
        if (FileUtils.makeDirectoryIfAbsent(str4)) {
            FileUtils.deleteFilesInDir(new SecureFile(str4));
        }
        if (createCoverBitmap == null) {
            if (!FileUtils.copy(str, targetSavePath)) {
                targetSavePath = null;
            }
            strArr[0] = targetSavePath;
        } else {
            if (BitmapUtils.hasTransparency(createCoverBitmap)) {
                targetSavePath = FileUtils.replaceExtension(targetSavePath, "png");
                z3 = BitmapUtils.saveAsPng(createCoverBitmap, targetSavePath, 100);
            } else {
                z3 = BitmapUtils.saveAsJpeg(createCoverBitmap, targetSavePath, 98);
            }
            if (z3 && coverItem.isImage() && coverItem.getOrientation() > 0) {
                ExifUtils.changeOrientation(targetSavePath, coverItem.getOrientation());
            }
            a.A(new Object[]{Boolean.valueOf(z3), Integer.valueOf(i2), Long.valueOf(FileUtils.length(targetSavePath)), createCoverBitmap, Long.valueOf(currentTimeMillis)}, new StringBuilder("saveCoverFile"), "AlbumDelegate");
            if (!z3) {
                targetSavePath = null;
            }
            strArr[0] = targetSavePath;
            strArr[1] = String.valueOf(MediaType.Image.toInt());
            if (z3) {
                str2 = String.valueOf(createCoverBitmap.getWidth());
            } else {
                str2 = null;
            }
            strArr[2] = str2;
            if (z3) {
                str3 = String.valueOf(createCoverBitmap.getHeight());
            } else {
                str3 = null;
            }
            strArr[3] = str3;
        }
        countDownLatch.countDown();
        return null;
    }

    public static boolean replaceAlbumCover(int i2, String str) {
        Cursor folderCursor;
        boolean z;
        try {
            folderCursor = new LocalAlbumsApi().getFolderCursor(i2);
            if (folderCursor != null) {
                if (folderCursor.getCount() > 0 && folderCursor.moveToFirst()) {
                    String string = folderCursor.getString(folderCursor.getColumnIndex("default_cover_path"));
                    String string2 = folderCursor.getString(folderCursor.getColumnIndex("cover_path"));
                    int bucketId = FileUtils.getBucketId(str);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("__bucketID", Integer.valueOf(bucketId));
                    contentValues.put("__Title", FileUtils.getNameFromPath(str));
                    contentValues.put("__absPath", str);
                    String replacedPath = getReplacedPath(string, str);
                    String replacedCoverPath = getReplacedCoverPath(string2, str);
                    if (replacedPath != null) {
                        contentValues.put("default_cover_path", replacedPath);
                    } else {
                        contentValues.putNull("default_cover_path");
                    }
                    if (replacedCoverPath != null) {
                        contentValues.put("cover_path", replacedCoverPath);
                    } else {
                        contentValues.putNull("cover_path");
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Integer.valueOf(i2));
                    int updateData = new LocalAlbumsApi().updateData(arrayList, contentValues);
                    Log.d("AlbumDelegate", "replaceAlbumCover finish. update result = " + updateData);
                    if (updateData > 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    folderCursor.close();
                    return z;
                }
            }
            if (folderCursor != null) {
                folderCursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return true;
        throw th;
    }

    public static String replaceCoverRect(String str, String str2, String str3) {
        if (str == null || str2 == null) {
            Log.e("AlbumDelegate", "replaceCoverRectW : width or height is null");
            return str3;
        }
        if (str3 != null) {
            try {
                if (!str3.isEmpty()) {
                    String[] split = str3.split(";");
                    if (split.length > 6) {
                        split[5] = str;
                        split[6] = str2;
                        return String.join(";", split);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str3;
    }

    public static String[] saveCoverFile(Context context, int i2, String str) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String[] strArr = new String[4];
        ThreadPool.getInstance().submit(new a(str, context, i2, countDownLatch, strArr));
        try {
            countDownLatch.await(1, TimeUnit.SECONDS);
            return strArr;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return strArr;
        }
    }

    private static void setDummyExif(String str) {
        try {
            ExifInterface exif = ExifUtils.getExif(str, true);
            if (exif != null) {
                exif.setAttribute("DateTime", "1970:01:01 00:00:01");
                exif.setAttribute("DateTimeOriginal", "1970:01:01 00:00:01");
                exif.saveAttributes();
            }
        } catch (IOException e) {
            Log.e("AlbumDelegate", e.toString());
        }
    }

    public static void updateAlbumCoverSyncDB(ContentValues contentValues) {
        String albumCoverSyncData = getAlbumCoverSyncData(false, "filehash");
        if (albumCoverSyncData != null) {
            contentValues.put("__coverSyncData", albumCoverSyncData);
            contentValues.put("__coverSyncDirty", 1);
        }
    }

    public static boolean updateLegacyWelcomeImages(Context context) {
        Cursor query;
        Throwable th;
        try {
            query = context.getContentResolver().query(EXTERNAL_URI, new String[]{"_data"}, "datetaken!=1000 and _display_name = ?", new String[]{"!$&Welcome@#Image.jpg"}, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    do {
                        setDummyExif(query.getString(0));
                    } while (query.moveToNext());
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(IParameterKey.DATE_TAKEN, 1000);
                    context.getContentResolver().update(EXTERNAL_URI, contentValues, "_display_name = ?", new String[]{"!$&Welcome@#Image.jpg"});
                    query.close();
                    return true;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (IllegalArgumentException e) {
            IllegalArgumentException illegalArgumentException = e;
            Log.e("AlbumDelegate", "error: " + illegalArgumentException);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        return false;
        throw th;
    }
}
