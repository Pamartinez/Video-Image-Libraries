package com.samsung.android.sdk.pen.ocr;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.sum.core.types.NumericEnum;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrDataProviderHelper {
    private static final String TAG = "SpenOcrDataProviderHelper";

    private static synchronized void closeContentProviderClient(ContentProviderClient contentProviderClient) {
        synchronized (SpenOcrDataProviderHelper.class) {
            Log.i(TAG, "Close ContentProviderClient");
            if (contentProviderClient == null) {
                Log.e(TAG, "closeContentProviderClient() content provider client is null!");
            } else {
                contentProviderClient.close();
            }
        }
    }

    public static synchronized AssetFileDescriptor getAssetFileDescriptor(Context context, String str, SpenDBType spenDBType) {
        AssetFileDescriptor openAssetFile;
        synchronized (SpenOcrDataProviderHelper.class) {
            Log.d(TAG, "getAssetFileDescriptor");
            ContentProviderClient openContentProviderClient = openContentProviderClient(context);
            AssetFileDescriptor assetFileDescriptor = null;
            if (openContentProviderClient == null) {
                Log.e(TAG, "getAssetFileDescriptor : ContentProviderClient is null");
                return null;
            }
            try {
                if (SpenRecogConfig.getOneUIVersion() < 80500) {
                    Uri parse = Uri.parse("content://com.samsung.android.sdk.ocr.resourcemanager/" + str);
                    String str2 = SpenOcrDataProviderContract.ASSETS_MODEL_DIR_OCRDB;
                    if (spenDBType.equals(SpenDBType.BlockAnalyzer)) {
                        str2 = SpenOcrDataProviderContract.ASSETS_MODEL_DIR_BLOCK_ANALYZER;
                    } else if (spenDBType.equals(SpenDBType.MoireDetector)) {
                        str2 = SpenOcrDataProviderContract.ASSETS_MODEL_DIR_MOIRE_DETECTOR;
                    }
                    Log.i(TAG, parse.toString());
                    openAssetFile = openContentProviderClient.openAssetFile(parse, str2);
                } else {
                    String str3 = SpenOcrDataProviderContract.ASSETS_MODEL_DIR_OCRDB;
                    if (spenDBType.equals(SpenDBType.BlockAnalyzer)) {
                        str3 = SpenOcrDataProviderContract.ASSETS_MODEL_DIR_BLOCK_ANALYZER;
                    } else if (spenDBType.equals(SpenDBType.MoireDetector)) {
                        str3 = SpenOcrDataProviderContract.ASSETS_MODEL_DIR_MOIRE_DETECTOR;
                    }
                    Uri parse2 = Uri.parse("content://com.samsung.android.sdk.ocr.resourcemanager/" + (str3 + NumericEnum.SEP + str));
                    Log.i(TAG, parse2.toString());
                    openAssetFile = openContentProviderClient.openAssetFile(parse2, "");
                }
                assetFileDescriptor = openAssetFile;
                Log.i(TAG, "getAssetFileDescriptor : asset data size: " + assetFileDescriptor.getLength());
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            closeContentProviderClient(openContentProviderClient);
            return assetFileDescriptor;
        }
    }

    private static synchronized byte[] getBytesFrom(AssetFileDescriptor assetFileDescriptor) {
        synchronized (SpenOcrDataProviderHelper.class) {
            if (assetFileDescriptor == null) {
                Log.e(TAG, "getBytesFrom : AssetFileDescriptor is null");
                return null;
            }
            ParcelFileDescriptor parcelFileDescriptor = assetFileDescriptor.getParcelFileDescriptor();
            long startOffset = assetFileDescriptor.getStartOffset();
            long length = assetFileDescriptor.getLength();
            if (length == -1) {
                Log.e(TAG, "getBytesFrom : UNKNOWN_LENGTH");
                return null;
            }
            byte[] bytesFrom = getBytesFrom(parcelFileDescriptor, startOffset, (int) length);
            return bytesFrom;
        }
    }

    public static synchronized String getCachedFilePath(Context context, String str, String str2, SpenDBType spenDBType) {
        String cachedFilePath;
        synchronized (SpenOcrDataProviderHelper.class) {
            cachedFilePath = getCachedFilePath(context, str, str2, spenDBType, getBytesFrom(getAssetFileDescriptor(context, str2, spenDBType)));
        }
        return cachedFilePath;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0041, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized java.util.List<java.lang.String> getSupportedLanguageList(android.content.ContentProviderClient r9) {
        /*
            java.lang.Class<com.samsung.android.sdk.pen.ocr.SpenOcrDataProviderHelper> r1 = com.samsung.android.sdk.pen.ocr.SpenOcrDataProviderHelper.class
            monitor-enter(r1)
            java.lang.String r0 = "SpenOcrDataProviderHelper"
            java.lang.String r2 = "getSupportedLanguageList : start query"
            android.util.Log.i(r0, r2)     // Catch:{ all -> 0x001a }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x001a }
            r2.<init>()     // Catch:{ all -> 0x001a }
            if (r9 != 0) goto L_0x001e
            java.lang.String r9 = "SpenOcrDataProviderHelper"
            java.lang.String r0 = "getSupportedLanguageList : client is null"
            android.util.Log.e(r9, r0)     // Catch:{ all -> 0x001a }
            monitor-exit(r1)
            return r2
        L_0x001a:
            r0 = move-exception
            r9 = r0
            goto L_0x009f
        L_0x001e:
            android.net.Uri r4 = com.samsung.android.sdk.pen.ocr.SpenOcrDataProviderContract.InstalledLanguages.CONTENT_URI     // Catch:{ Exception -> 0x003d }
            r0 = 0
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch:{ Exception -> 0x003d }
            java.lang.String r6 = ""
            java.lang.String[] r7 = new java.lang.String[r0]     // Catch:{ Exception -> 0x003d }
            java.lang.String r8 = ""
            r3 = r9
            android.database.Cursor r9 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x003d }
            if (r9 != 0) goto L_0x0045
            java.lang.String r0 = "SpenOcrDataProviderHelper"
            java.lang.String r3 = "getSupportedLanguageList : cursor is null!"
            android.util.Log.e(r0, r3)     // Catch:{ all -> 0x0042 }
            if (r9 == 0) goto L_0x0040
            r9.close()     // Catch:{ Exception -> 0x003d }
            goto L_0x0040
        L_0x003d:
            r0 = move-exception
            r9 = r0
            goto L_0x006d
        L_0x0040:
            monitor-exit(r1)
            return r2
        L_0x0042:
            r0 = move-exception
            r3 = r0
            goto L_0x0061
        L_0x0045:
            boolean r0 = r9.moveToFirst()     // Catch:{ all -> 0x0042 }
        L_0x0049:
            if (r0 == 0) goto L_0x005d
            java.lang.String r0 = "language"
            int r0 = r9.getColumnIndex(r0)     // Catch:{ all -> 0x0042 }
            java.lang.String r0 = r9.getString(r0)     // Catch:{ all -> 0x0042 }
            r2.add(r0)     // Catch:{ all -> 0x0042 }
            boolean r0 = r9.moveToNext()     // Catch:{ all -> 0x0042 }
            goto L_0x0049
        L_0x005d:
            r9.close()     // Catch:{ Exception -> 0x003d }
            goto L_0x0087
        L_0x0061:
            if (r9 == 0) goto L_0x006c
            r9.close()     // Catch:{ all -> 0x0067 }
            goto L_0x006c
        L_0x0067:
            r0 = move-exception
            r9 = r0
            r3.addSuppressed(r9)     // Catch:{ Exception -> 0x003d }
        L_0x006c:
            throw r3     // Catch:{ Exception -> 0x003d }
        L_0x006d:
            java.lang.String r0 = "SpenOcrDataProviderHelper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x001a }
            r3.<init>()     // Catch:{ all -> 0x001a }
            java.lang.String r4 = "getSupportedLanguageList : query exception : "
            r3.append(r4)     // Catch:{ all -> 0x001a }
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x001a }
            r3.append(r9)     // Catch:{ all -> 0x001a }
            java.lang.String r9 = r3.toString()     // Catch:{ all -> 0x001a }
            android.util.Log.e(r0, r9)     // Catch:{ all -> 0x001a }
        L_0x0087:
            java.lang.String r9 = "SpenOcrDataProviderHelper"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x001a }
            r0.<init>()     // Catch:{ all -> 0x001a }
            java.lang.String r3 = "getSupportedLanguageList : "
            r0.append(r3)     // Catch:{ all -> 0x001a }
            r0.append(r2)     // Catch:{ all -> 0x001a }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x001a }
            android.util.Log.i(r9, r0)     // Catch:{ all -> 0x001a }
            monitor-exit(r1)
            return r2
        L_0x009f:
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.pen.ocr.SpenOcrDataProviderHelper.getSupportedLanguageList(android.content.ContentProviderClient):java.util.List");
    }

    public static synchronized List<String> getSupportedLanguages(Context context) {
        List<String> supportedLanguageList;
        synchronized (SpenOcrDataProviderHelper.class) {
            Log.d(TAG, "getSupportedLanguages");
            ContentProviderClient openContentProviderClient = openContentProviderClient(context);
            supportedLanguageList = getSupportedLanguageList(openContentProviderClient);
            closeContentProviderClient(openContentProviderClient);
        }
        return supportedLanguageList;
    }

    private static synchronized ContentProviderClient openContentProviderClient(Context context) {
        synchronized (SpenOcrDataProviderHelper.class) {
            Log.i(TAG, "Open ContentProviderClient");
            ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
            if (contentResolver == null) {
                Log.e(TAG, "openContentProviderClient() content resolver is null!");
                return null;
            }
            ContentProviderClient acquireContentProviderClient = contentResolver.acquireContentProviderClient(SpenOcrDataProviderContract.AUTHORITY);
            if (acquireContentProviderClient != null) {
                return acquireContentProviderClient;
            }
            Log.e(TAG, "openContentProviderClient() content provider client is null!");
            return null;
        }
    }

    private static synchronized void readAndWriteToBuffer(OutputStream outputStream, InputStream inputStream, byte[] bArr, int i2) {
        synchronized (SpenOcrDataProviderHelper.class) {
            outputStream.write(bArr, 0, inputStream.read(bArr, 0, i2));
        }
    }

    public static synchronized void releaseCachedDBFilePath(Context context, String str) {
        synchronized (SpenOcrDataProviderHelper.class) {
            Log.d(TAG, "releaseCachedDBFilePath dbFilePath : " + str);
            File file = new File(Uri.parse(str).getPath());
            file.delete();
            if (file.exists()) {
                try {
                    file.getCanonicalFile().delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (file.exists()) {
                    context.deleteFile(file.getName());
                }
            }
        }
    }

    private static synchronized boolean writeBytesToFile(File file, byte[] bArr) {
        FileOutputStream fileOutputStream;
        synchronized (SpenOcrDataProviderHelper.class) {
            if (bArr != null) {
                if (bArr.length != 0) {
                    try {
                        fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(bArr);
                        fileOutputStream.close();
                        return true;
                    } catch (IOException e) {
                        Log.e(TAG, "writeBytesToFile : error message = " + e.getMessage());
                        return false;
                    } catch (Throwable th) {
                        th.addSuppressed(th);
                    }
                }
            }
            Log.e(TAG, "writeBytesToFile : input bytes is wrong!");
            return false;
        }
        throw th;
    }

    public static synchronized String getCachedFilePath(Context context, String str, String str2, SpenDBType spenDBType, byte[] bArr) {
        File file;
        String str3;
        synchronized (SpenOcrDataProviderHelper.class) {
            File cacheDir = context.getApplicationContext().getCacheDir();
            if (cacheDir == null) {
                Log.e(TAG, "cache dir is null!");
                return "";
            }
            String str4 = cacheDir.getAbsolutePath() + File.separator + SpenOcrDataProviderContract.ASSETS_MODEL_DIR_OCRDB;
            File file2 = new File(str4);
            if (file2.exists() || file2.mkdirs()) {
                if (!spenDBType.equals(SpenDBType.MoireDetector)) {
                    file = new File(str4, "_" + str2 + SpenOcrDataProviderContract.DBNAME_EXTENSION);
                } else {
                    if (str == null || str.length() <= 0) {
                        str3 = "_moire.tflite";
                    } else {
                        str3 = str.concat(SpenOcrDataProviderContract.TFLITE_EXTENSION);
                    }
                    file = new File(str4, str3);
                }
                if (file.exists()) {
                    if (!file.delete()) {
                        Log.e(TAG, "Fail to delete existing file!");
                        return "";
                    }
                    Log.d(TAG, "Success to delete existing file!");
                }
                if (!writeBytesToFile(file, bArr)) {
                    return "";
                }
                String absolutePath = file.getAbsolutePath();
                return absolutePath;
            }
            Log.e(TAG, "Fail to create moire cache dir!");
            return "";
        }
    }

    private static synchronized byte[] getBytesFrom(ParcelFileDescriptor parcelFileDescriptor, long j2, int i2) {
        BufferedInputStream bufferedInputStream;
        synchronized (SpenOcrDataProviderHelper.class) {
            Log.i(TAG, "getBytesFrom : startOffset = " + j2 + ", bufferSize = " + i2);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bufferedInputStream = new BufferedInputStream(new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor));
                int i7 = (j2 > bufferedInputStream.skip(j2) ? 1 : (j2 == bufferedInputStream.skip(j2) ? 0 : -1));
                if (i7 != 0) {
                    Log.e(TAG, "getBytesFrom : startoffset skip failed!");
                    byte[] bArr = new byte[0];
                    bufferedInputStream.close();
                    return bArr;
                }
                byte[] bArr2 = new byte[SerializeOptions.SORT];
                int i8 = i2 / SerializeOptions.SORT;
                int i10 = i2 % SerializeOptions.SORT;
                for (int i11 = 0; i11 < i8; i11++) {
                    readAndWriteToBuffer(byteArrayOutputStream, bufferedInputStream, bArr2, SerializeOptions.SORT);
                }
                readAndWriteToBuffer(byteArrayOutputStream, bufferedInputStream, bArr2, i10);
                bufferedInputStream.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                return byteArray;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        throw th;
    }
}
