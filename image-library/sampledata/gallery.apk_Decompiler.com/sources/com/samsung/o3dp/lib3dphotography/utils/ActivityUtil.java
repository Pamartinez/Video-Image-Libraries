package com.samsung.o3dp.lib3dphotography.utils;

import android.content.ContentResolver;
import android.net.Uri;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActivityUtil {
    public static void save(ContentResolver contentResolver, Uri uri, byte[] bArr) {
        OutputStream openOutputStream;
        try {
            openOutputStream = contentResolver.openOutputStream(uri);
            if (openOutputStream != null) {
                openOutputStream.write(bArr);
                openOutputStream.flush();
                openOutputStream.close();
                return;
            }
            throw new NullPointerException("output stream from uri is null.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error while saving file to " + uri.getPath(), e);
        } catch (IOException e7) {
            throw new RuntimeException("IO Exception while saving file", e7);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x002a A[SYNTHETIC, Splitter:B:19:0x002a] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0030 A[SYNTHETIC, Splitter:B:22:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void saveBytesToFile(java.lang.String r2, byte[] r3) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r2)
            r2 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0022, all -> 0x001e }
            r1.<init>(r0)     // Catch:{ IOException -> 0x0022, all -> 0x001e }
            r1.write(r3)     // Catch:{ IOException -> 0x001c }
            r1.flush()     // Catch:{ IOException -> 0x001c }
            r1.close()     // Catch:{ IOException -> 0x0015 }
            return
        L_0x0015:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x002d
        L_0x001a:
            r2 = move-exception
            goto L_0x002e
        L_0x001c:
            r2 = move-exception
            goto L_0x0025
        L_0x001e:
            r3 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x002e
        L_0x0022:
            r3 = move-exception
            r1 = r2
            r2 = r3
        L_0x0025:
            r2.printStackTrace()     // Catch:{ all -> 0x001a }
            if (r1 == 0) goto L_0x002d
            r1.close()     // Catch:{ IOException -> 0x0015 }
        L_0x002d:
            return
        L_0x002e:
            if (r1 == 0) goto L_0x0038
            r1.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0038:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.o3dp.lib3dphotography.utils.ActivityUtil.saveBytesToFile(java.lang.String, byte[]):void");
    }

    public static byte[] uriToByteArray(ContentResolver contentResolver, Uri uri) {
        InputStream openInputStream;
        try {
            openInputStream = contentResolver.openInputStream(uri);
            if (openInputStream != null) {
                byte[] bytes = ByteUtil.toBytes(openInputStream);
                openInputStream.close();
                return bytes;
            }
            throw new IOException("Unable to open InputStream for URI: " + uri);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert Uri to ByteArray", e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
