package com.samsung.o3dp.lib3dphotography.utils;

import android.content.Context;
import i.C0212a;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FileUtil {
    private static final String TAG = "FileUtil";

    public static long getLastModified(String str) {
        return new File(str).lastModified();
    }

    public static String getStringFromAsset(Context context, String str) {
        InputStream open;
        try {
            open = context.getAssets().open(str);
            int available = open.available();
            byte[] bArr = new byte[available];
            int read = open.read(bArr);
            if (read != -1) {
                if (read == available) {
                    String str2 = new String(bArr, StandardCharsets.UTF_8);
                    open.close();
                    return str2;
                }
            }
            LogUtil.e(TAG, "Failed to read all bytes from asset " + str);
            open.close();
            return "";
        } catch (IOException e) {
            LogUtil.e(TAG, "Failed to getStringFromAsset ", e);
            return "";
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static byte[] readFileToByteArray(File file) {
        return readFileToByteArray(file.getAbsolutePath());
    }

    public static void restoreLastModified(String str, long j2) {
        if (!new File(str).setLastModified(j2)) {
            throw new IOException(C0212a.l("Failed to restore lastModified timestamp for ", str));
        }
    }

    public static byte[] readFileToByteArray(String str) {
        File file = new File(str);
        if (file.exists()) {
            int length = (int) file.length();
            byte[] bArr = new byte[length];
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                if (fileInputStream.read(bArr) == length) {
                    fileInputStream.close();
                    return bArr;
                }
                throw new IOException("Could not completely read the file");
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            throw new IOException("File not found");
        }
        throw th;
    }
}
