package com.samsung.android.gallery.module.utils;

import C3.C0392b;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.net.Uri;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileOpUtils {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum EXCEPTION_CAUSE {
        UNKNOWN,
        FAIL_SAME_NAME,
        FAIL_DEST_EXIST,
        FAIL_SRC_NOT_FOUND,
        FAIL_INVALID_CHAR
    }

    public static String copy(String str, String str2, boolean z) {
        String str3;
        if (!FileUtils.equals(str, str2)) {
            SecureFile secureFile = new SecureFile(str2);
            if (!secureFile.exists()) {
                str3 = secureFile.getAbsolutePath();
            } else if (z) {
                str3 = FileUtils.getNewFilePath(secureFile.getAbsolutePath());
            } else {
                throw new IOException("A target already exist : ", new Throwable(EXCEPTION_CAUSE.FAIL_DEST_EXIST.name()));
            }
            File parentFile = secureFile.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                FileUtils.makeDirectoryIfAbsent(parentFile);
            }
            FileUtils.copy(str, str3);
            return str3;
        }
        throw new IOException(C0212a.l("File name is same : ", str), new Throwable(EXCEPTION_CAUSE.FAIL_SAME_NAME.name()));
    }

    public static String move(String str, String str2, boolean z) {
        String str3;
        if (!FileUtils.equals(str, str2)) {
            SecureFile secureFile = new SecureFile(str2);
            if (!secureFile.exists()) {
                str3 = secureFile.getAbsolutePath();
            } else if (z) {
                str3 = FileUtils.getNewFilePath(secureFile.getAbsolutePath());
            } else {
                throw new IOException("A target already exist : ", new Throwable(EXCEPTION_CAUSE.FAIL_DEST_EXIST.name()));
            }
            if (FileUtils.move(str, str3)) {
                return str3;
            }
            String nameFromPath = FileUtils.getNameFromPath(str);
            String nameFromPath2 = FileUtils.getNameFromPath(str3);
            List asList = Arrays.asList(new String[]{"\\", "\"", "?", NumericEnum.SEP, "*", "<", ">", "|"});
            Stream stream = asList.stream();
            Objects.requireNonNull(nameFromPath);
            if (!stream.anyMatch(new C0392b(nameFromPath, 13))) {
                Stream stream2 = asList.stream();
                Objects.requireNonNull(nameFromPath2);
                if (!stream2.anyMatch(new C0392b(nameFromPath2, 13))) {
                    throw new IOException("File.renameTo() returns false", new Throwable(EXCEPTION_CAUSE.UNKNOWN.name()));
                }
            }
            throw new IOException(C0212a.l("File.renameTo() returns false (invalid char) : ", Logger.getEncodedString("src : " + nameFromPath + ", result : " + nameFromPath2)), new Throwable(EXCEPTION_CAUSE.FAIL_INVALID_CHAR.name()));
        }
        throw new IOException(C0212a.l("File name is same : ", str), new Throwable(EXCEPTION_CAUSE.FAIL_SAME_NAME.name()));
    }

    public static void rename(String str, String str2) {
        SecureFile secureFile = new SecureFile(str);
        SecureFile secureFile2 = new SecureFile(str2);
        if (FileUtils.equals((File) secureFile, (File) secureFile2)) {
            throw new IOException(C0212a.l("File name is same : ", str), new Throwable(EXCEPTION_CAUSE.FAIL_SAME_NAME.name()));
        } else if (secureFile2.exists()) {
            throw new IOException("A target already exist : ", new Throwable(EXCEPTION_CAUSE.FAIL_DEST_EXIST.name()));
        } else if (!secureFile.renameTo(secureFile2)) {
            throw new IOException("File.renameTo() returns false", new Throwable(EXCEPTION_CAUSE.UNKNOWN.name()));
        }
    }

    public static void updateDbRename(Context context, Uri uri, String str) {
        ContentValues c5 = C0086a.c("_data", str);
        String nameFromPath = FileUtils.getNameFromPath(str);
        c5.put("_display_name", nameFromPath);
        c5.put("title", FileUtils.getBaseName(nameFromPath));
        try {
            if (context.getContentResolver().update(uri, c5, (String) null, (String[]) null) <= 0) {
                Log.e("FileOpUtils", "updateDbRename failed");
            }
        } catch (SQLiteConstraintException | SecurityException e) {
            Log.e((CharSequence) "FileOpUtils", "updateDbRename failed {" + uri + ',' + nameFromPath + ',' + str + '}', e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0052 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean updateMimeType(android.content.Context r5, android.net.Uri r6, java.lang.String r7, java.lang.String r8) {
        /*
            java.lang.String r0 = "FileOpUtils"
            java.lang.String r1 = "_display_name"
            android.content.ContentValues r1 = c0.C0086a.c(r1, r7)
            com.samsung.android.gallery.database.dbtype.MimeType r2 = com.samsung.android.gallery.database.dbtype.MimeType.getMimeType((java.lang.String) r7)
            java.lang.String r2 = r2.mimeType
            java.lang.String r3 = "mime_type"
            r1.put(r3, r2)
            r2 = 0
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch:{ Exception -> 0x0028 }
            r3 = 0
            int r5 = r5.update(r6, r1, r3, r3)     // Catch:{ Exception -> 0x0028 }
            if (r5 > 0) goto L_0x0050
            java.lang.String r1 = "updateMimeType: fail"
            com.samsung.android.gallery.support.utils.Log.e(r0, r1)     // Catch:{ Exception -> 0x0026 }
            goto L_0x0050
        L_0x0026:
            r1 = move-exception
            goto L_0x002a
        L_0x0028:
            r1 = move-exception
            r5 = r2
        L_0x002a:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "updateMimeType: fail {"
            r3.<init>(r4)
            r3.append(r6)
            java.lang.String r6 = ", "
            r3.append(r6)
            r3.append(r7)
            r3.append(r6)
            r3.append(r8)
            java.lang.String r6 = "}"
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r0, (java.lang.String) r6, (java.lang.Throwable) r1)
        L_0x0050:
            if (r5 <= 0) goto L_0x0053
            r2 = 1
        L_0x0053:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.utils.FileOpUtils.updateMimeType(android.content.Context, android.net.Uri, java.lang.String, java.lang.String):boolean");
    }
}
