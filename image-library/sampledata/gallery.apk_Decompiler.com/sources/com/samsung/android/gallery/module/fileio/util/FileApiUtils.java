package com.samsung.android.gallery.module.fileio.util;

import A.a;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompatGroup;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.cloud.SCloudHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SefFileUtils;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileApiUtils {
    public static void changeOverwriteDummy(String str) {
        if (new SecureFile(getOverwriteFilter(str)).renameTo(new SecureFile(str))) {
            Log.d("FileApiUtils", "success change to dummy file");
        }
    }

    public static boolean checkValidFileSize(String str, long j2, String str2, long j3) {
        if (j2 == j3 && j3 != 0) {
            return true;
        }
        Log.e("FileApiUtils", "file size check failed");
        StringBuilder sb2 = new StringBuilder("[file size check failed][s : ");
        sb2.append(Logger.getEncodedString(str));
        sb2.append(" : ");
        sb2.append(j2);
        sb2.append("][d : ");
        sb2.append(Logger.getEncodedString(str2));
        sb2.append(" : ");
        String o2 = C0212a.o(sb2, j3, "]");
        SecureFile secureFile = new SecureFile(str2);
        if (secureFile.exists() && secureFile.length() == 0 && secureFile.delete()) {
            o2 = C0212a.A(o2, " dst file is deleted because size is 0");
        }
        DebugLogger.getDeleteInstance().insertLog(o2);
        return false;
    }

    public static boolean checkValidity(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !str2.contains(".")) {
            return false;
        }
        if (!FileUtils.exists(str)) {
            Log.e("FileApiUtils", "source file not exist");
            return false;
        } else if (!FileUtils.exists(str2)) {
            return true;
        } else {
            Log.e("FileApiUtils", "target file already exist");
            return false;
        }
    }

    public static String getDstPath(String str, boolean z) {
        if (z) {
            return getOverwriteFilter(str);
        }
        return str;
    }

    public static String getFileDestPath(FileItemInterface fileItemInterface, String str, String str2) {
        if (fileItemInterface.getStorageType() == StorageType.Cloud) {
            return FileUtils.getNewFilePath(fileItemInterface.getCloudThumbPath());
        }
        return getLogicalDestPath(str, str2);
    }

    public static ArrayList<FileItemInterface> getGroupSubItems(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.getGroupMediaId() == 0) {
            Log.e("FileApiUtils", "can't get group sub item");
            return null;
        }
        ArrayList<FileItemInterface> arrayList = new ArrayList<>();
        Cursor groupCursor = DbCompatGroup.getGroupCursor(mediaItem, mediaItem.getAlbumID());
        if (groupCursor != null) {
            try {
                if (groupCursor.moveToFirst()) {
                    do {
                        arrayList.add(MediaItemBuilder.create(groupCursor));
                    } while (groupCursor.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (groupCursor != null) {
            groupCursor.close();
        }
        return arrayList;
        throw th;
    }

    public static String getLogicalDestPath(String str, String str2) {
        return C0212a.p(C0212a.s(str), File.separator, str2);
    }

    private static String getOverwriteFilter(String str) {
        return str.substring(0, str.lastIndexOf(".")) + "_new" + str.substring(str.lastIndexOf("."));
    }

    public static Map<String, byte[]> getSefDataMap(int i2, int i7) {
        if (GroupType.BURST.value == i2) {
            return SefFileUtils.getBurstShotSefDataMap(i7);
        }
        if (GroupType.SINGLE_TAKEN.value == i2) {
            return SefFileUtils.getSingleTakenSefDataMap(i7);
        }
        return new HashMap();
    }

    public static String getSrcPath(FileItemInterface fileItemInterface) {
        if (fileItemInterface.getStorageType() == StorageType.Cloud) {
            return fileItemInterface.getCloudThumbPath();
        }
        return fileItemInterface.getPath();
    }

    public static String getUpdatePath(FileItemInterface fileItemInterface, String str) {
        if (fileItemInterface.getStorageType() == StorageType.Cloud) {
            return str;
        }
        return null;
    }

    public static void keepFileModifiedTime(String str, String str2) {
        try {
            long dateModified = FileUtils.getDateModified(str);
            if (dateModified > 0) {
                FileUtils.setDateModified(str2, dateModified);
            } else {
                Log.e((CharSequence) "FileApiUtils", "keepFileModifiedTime failed", Long.valueOf(dateModified), Logger.getEncodedString(str));
            }
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("keepFileModifiedTime failed e="), "FileApiUtils");
        }
    }

    public static int makeBurstShotGroupId(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        long hashCode = (long) context.hashCode();
        if (currentTimeMillis > hashCode) {
            currentTimeMillis -= hashCode;
        }
        return new Random(currentTimeMillis).nextInt(Integer.MAX_VALUE) + 1;
    }

    public static String makeBurstShotName(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        String[] split = str2.split("\\.");
        SecureFile secureFile = new SecureFile(str);
        String B = C0212a.B(split[0], ".", split[1]);
        SecureFile secureFile2 = new SecureFile((File) secureFile, B);
        int i2 = 1;
        while (secureFile2.exists()) {
            B = String.format(Locale.getDefault(), "%s_%02d.%s", new Object[]{split[0], Integer.valueOf(i2), split[1]});
            secureFile2 = new SecureFile((File) secureFile, B);
            i2++;
        }
        return B;
    }

    public static String makeCloudBurstShotName(String str, String str2) {
        if (str == null) {
            return null;
        }
        String[] split = str.split("\\.");
        if (str2.isEmpty()) {
            return C0212a.B(split[0], ".", split[1]);
        }
        Locale.getDefault();
        String str3 = split[0];
        String str4 = split[1];
        return str3 + "_" + str2 + "." + str4;
    }

    public static String makeBurstShotName(Context context, FileItemInterface fileItemInterface, String str, FileItemInterface fileItemInterface2) {
        if (fileItemInterface2.getStorageType() != StorageType.Cloud) {
            return makeBurstShotName(FileUtils.getDirectoryFromPath(str, false), FileUtils.getNameFromPath(fileItemInterface2.getPath()));
        }
        return makeCloudBurstShotName(FileUtils.getNameFromPath(fileItemInterface2.getCloudServerPath()), SCloudHelper.getNewFileSetNumberForBurstShot(context, fileItemInterface, str));
    }

    public static boolean checkValidFileSize(File file, File file2) {
        return checkValidFileSize(file.getPath(), file.length(), file2.getPath(), file2.length());
    }
}
