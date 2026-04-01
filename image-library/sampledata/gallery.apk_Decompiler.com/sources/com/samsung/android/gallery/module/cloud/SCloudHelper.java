package com.samsung.android.gallery.module.cloud;

import android.content.Context;
import android.database.Cursor;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import i.C0212a;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SCloudHelper {
    private static final String CLOUD_PATH_PREFIX;
    private static final Boolean IS_JAPAN_BRAND;

    static {
        Features features;
        String str;
        if (SdkConfig.lessThan(SdkConfig.SEM.U)) {
            features = Features.IS_JAPAN_CLOUD_BRAND;
        } else {
            features = Features.IS_JAPAN_DEVICE;
        }
        Boolean valueOf = Boolean.valueOf(Features.isEnabled(features));
        IS_JAPAN_BRAND = valueOf;
        if (valueOf.booleanValue()) {
            str = "Galaxy Cloud";
        } else {
            str = "Samsung Cloud";
        }
        CLOUD_PATH_PREFIX = str;
    }

    public static String getCloudRemotePath(String str, String str2, String str3) {
        if (str == null || str.isEmpty()) {
            Log.e("SCloudHelper", "orgPath, _data2 is null");
        }
        String str4 = "";
        if (str != null) {
            String str5 = CLOUD_PATH_PREFIX;
            if (!str.startsWith(str5)) {
                if (!str.startsWith("/" + str5)) {
                    Log.e("SCloudHelper", "orgPath error, not start with remote path");
                    return str4;
                }
            }
        }
        String replaceFirst = str2.replaceFirst(FileUtils.EXTERNAL_STORAGE_DIR, str4);
        if (!replaceFirst.endsWith("/")) {
            str4 = "/";
        }
        return C0212a.q(new StringBuilder("/"), CLOUD_PATH_PREFIX, replaceFirst, str4, str3);
    }

    public static String getNewFileFullPath(Context context, FileItemInterface fileItemInterface, String str) {
        String directoryFromPath = FileUtils.getDirectoryFromPath(str);
        String nameFromPath = FileUtils.getNameFromPath(str);
        String A10 = C0212a.A(directoryFromPath, nameFromPath);
        int i2 = 1;
        while (true) {
            if (!hasSameCloudItem(context, fileItemInterface, A10) && !FileUtils.exists(A10)) {
                return A10;
            }
            StringBuilder s = C0212a.s(directoryFromPath);
            s.append(FileUtils.addPostfix(nameFromPath, i2));
            A10 = s.toString();
            i2++;
        }
    }

    public static String getNewFileSetNumberForBurstShot(Context context, FileItemInterface fileItemInterface, String str) {
        if (str == null) {
            return null;
        }
        String directoryFromPath = FileUtils.getDirectoryFromPath(str);
        String nameFromPath = FileUtils.getNameFromPath(str);
        String A10 = C0212a.A(directoryFromPath, nameFromPath);
        String[] splitNameAndExtension = FileUtils.splitNameAndExtension(nameFromPath);
        String str2 = "";
        int i2 = 1;
        while (hasSameCloudItem(context, fileItemInterface, A10)) {
            int i7 = i2 + 1;
            String format = String.format(Locale.getDefault(), "%02d", new Object[]{Integer.valueOf(i2)});
            StringBuilder sb2 = new StringBuilder();
            sb2.append(directoryFromPath);
            Locale.getDefault();
            sb2.append(splitNameAndExtension[0] + "_" + format + "." + splitNameAndExtension[1]);
            int i8 = i7;
            str2 = format;
            A10 = sb2.toString();
            i2 = i8;
        }
        return str2;
    }

    public static boolean hasSameCloudItem(Context context, FileItemInterface fileItemInterface, String str) {
        Cursor query;
        Throwable th;
        boolean z;
        try {
            query = context.getContentResolver().query(MediaUri.getInstance().getSecMediaUri(), new String[]{"count(*)"}, "_data2 = '" + getCloudRemotePath(fileItemInterface.getCloudData2(), FileUtils.getDirectoryFromPath(str, false), FileUtils.getNameFromPath(str)) + "'", (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    if (query.getInt(0) > 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    query.close();
                    return z;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        return false;
        throw th;
    }
}
