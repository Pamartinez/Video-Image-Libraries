package com.samsung.android.sdk.pen.ocr;

import N2.j;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import i.C0212a;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrModelLoaderForLocalAssetFile extends SpenOcrModelLoaderBase {
    private static final String ASSETS_MODEL_BLOCK_ANALYZER_DIRECTORY = "models/blockanalyzer";
    private static final String ASSETS_MODEL_BLOCK_ANALYZER_FILE_NAME = "ba";
    private static final String ASSETS_MODEL_STANDARD_DIRECTORY = "models";
    private static final String FILES_MODEL_STANDARD_DIRECTORY = "ocr";
    private static final String TAG = "LoaderForLocalAssetFile";
    private static final List<String> mSupportedLanguages = Arrays.asList(new String[]{SpenOcrLanguage.ENGLISH.toLanguageCode(), SpenOcrLanguage.KOREAN.toLanguageCode(), SpenOcrLanguage.GERMAN.toLanguageCode(), SpenOcrLanguage.SPANISH.toLanguageCode(), SpenOcrLanguage.FRENCH.toLanguageCode(), SpenOcrLanguage.ITALIAN.toLanguageCode(), SpenOcrLanguage.PORTUGUESE.toLanguageCode(), SpenOcrLanguage.CHINESE.toLanguageCode()});
    private String blockAnalyzerDBPath = null;

    public SpenOcrModelLoaderForLocalAssetFile(Context context, SpenIOcrModelManager spenIOcrModelManager) {
        super(context, spenIOcrModelManager);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:5|6|(2:8|9)|10|11|46) */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return true;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0019 */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0042 A[SYNTHETIC, Splitter:B:29:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0047 A[SYNTHETIC, Splitter:B:33:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x004f A[SYNTHETIC, Splitter:B:38:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0054 A[SYNTHETIC, Splitter:B:42:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean copyFile(android.content.res.AssetManager r4, java.lang.String r5, java.lang.String r6) {
        /*
            r3 = this;
            java.lang.String r0 = "SPenMathResourceExtractor::copyFile() Failed to copy asset file: "
            r1 = 0
            java.io.InputStream r4 = r4.open(r5)     // Catch:{ IOException -> 0x002d, all -> 0x002a }
            java.io.File r2 = new java.io.File     // Catch:{ IOException -> 0x0027, all -> 0x0024 }
            r2.<init>(r6)     // Catch:{ IOException -> 0x0027, all -> 0x0024 }
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0027, all -> 0x0024 }
            r6.<init>(r2)     // Catch:{ IOException -> 0x0027, all -> 0x0024 }
            r3.copyFile(r4, r6)     // Catch:{ IOException -> 0x0021, all -> 0x001e }
            if (r4 == 0) goto L_0x0019
            r4.close()     // Catch:{ IOException -> 0x0019 }
        L_0x0019:
            r6.close()     // Catch:{ IOException -> 0x001c }
        L_0x001c:
            r3 = 1
            return r3
        L_0x001e:
            r3 = move-exception
        L_0x001f:
            r1 = r4
            goto L_0x004d
        L_0x0021:
            r3 = move-exception
        L_0x0022:
            r1 = r4
            goto L_0x002f
        L_0x0024:
            r3 = move-exception
            r6 = r1
            goto L_0x001f
        L_0x0027:
            r3 = move-exception
            r6 = r1
            goto L_0x0022
        L_0x002a:
            r3 = move-exception
            r6 = r1
            goto L_0x004d
        L_0x002d:
            r3 = move-exception
            r6 = r1
        L_0x002f:
            java.lang.String r4 = "LoaderForLocalAssetFile"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x004c }
            r2.<init>(r0)     // Catch:{ all -> 0x004c }
            r2.append(r5)     // Catch:{ all -> 0x004c }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x004c }
            android.util.Log.e(r4, r5, r3)     // Catch:{ all -> 0x004c }
            if (r1 == 0) goto L_0x0045
            r1.close()     // Catch:{ IOException -> 0x0045 }
        L_0x0045:
            if (r6 == 0) goto L_0x004a
            r6.close()     // Catch:{ IOException -> 0x004a }
        L_0x004a:
            r3 = 0
            return r3
        L_0x004c:
            r3 = move-exception
        L_0x004d:
            if (r1 == 0) goto L_0x0052
            r1.close()     // Catch:{ IOException -> 0x0052 }
        L_0x0052:
            if (r6 == 0) goto L_0x0057
            r6.close()     // Catch:{ IOException -> 0x0057 }
        L_0x0057:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.pen.ocr.SpenOcrModelLoaderForLocalAssetFile.copyFile(android.content.res.AssetManager, java.lang.String, java.lang.String):boolean");
    }

    private String createBlockAnalyzerDbPath() {
        String[] strArr;
        String str = this.blockAnalyzerDBPath;
        if (str != null) {
            return str;
        }
        AssetManager assets = this.mContext.getAssets();
        try {
            strArr = assets.list(ASSETS_MODEL_BLOCK_ANALYZER_DIRECTORY);
        } catch (IOException e) {
            Log.e(TAG, "SpenOcrEngine::createBlockAnalyzerDbPath() Failed to get asset file list.", e);
            strArr = null;
        }
        if (strArr == null) {
            Log.e(TAG, "SpenOcrEngine::createBlockAnalyzerDbPath() files == null");
            return null;
        }
        String modelDir = getModelDir();
        this.blockAnalyzerDBPath = modelDir;
        if (modelDir == null) {
            Log.e(TAG, "SpenOcrEngine::createBlockAnalyzerDbPath() dstDir == null");
            return null;
        }
        int length = strArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            String str2 = strArr[i2];
            if (str2.contains("ba")) {
                Log.d(TAG, "SpenOcrEngine::createBlockAnalyzerDbPath() file : ".concat(str2));
                this.blockAnalyzerDBPath = j.f(new StringBuilder(), this.blockAnalyzerDBPath, "/", str2);
                if (copyFile(assets, "models/blockanalyzer/".concat(str2), this.blockAnalyzerDBPath)) {
                    return this.blockAnalyzerDBPath;
                }
            } else {
                i2++;
            }
        }
        return null;
    }

    private String getAssetDBFilePath(AssetManager assetManager, String str, SpenDBType spenDBType) {
        if (assetManager == null) {
            Log.e(TAG, "getAssetDBFilePath : AssetManager is null!");
            return "";
        } else if (spenDBType.equals(SpenDBType.MoireDetector)) {
            return "moire.tflite";
        } else {
            if (spenDBType.equals(SpenDBType.BlockAnalyzer)) {
                StringBuilder sb2 = new StringBuilder(ASSETS_MODEL_STANDARD_DIRECTORY);
                String str2 = File.separator;
                return C0212a.q(sb2, str2, SpenOcrDataProviderContract.ASSETS_MODEL_DIR_BLOCK_ANALYZER, str2, "ba.dat");
            }
            try {
                String[] list = assetManager.list(ASSETS_MODEL_STANDARD_DIRECTORY + File.separator + str);
                if (list != null) {
                    for (String str3 : list) {
                        if (str3.contains(str)) {
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(ASSETS_MODEL_STANDARD_DIRECTORY);
                            String str4 = File.separator;
                            sb3.append(str4);
                            sb3.append(str);
                            sb3.append(str4);
                            sb3.append(str3);
                            return sb3.toString();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
    }

    private String getModelDir() {
        File filesDir = this.mContext.getFilesDir();
        File file = new File(filesDir, FILES_MODEL_STANDARD_DIRECTORY);
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (Exception e) {
                Log.e(TAG, "SpenOcrEngine::getModelDir() mkdir failed.", e);
                return null;
            }
        }
        return filesDir + "/ocr";
    }

    public String getCachedDBFilePath(Context context, String str, String str2, SpenDBType spenDBType) {
        InputStream open;
        AssetManager assets = context.getAssets();
        String assetDBFilePath = getAssetDBFilePath(assets, str2, spenDBType);
        Log.i(TAG, "getCachedDBFilePath : path = " + assetDBFilePath);
        try {
            open = assets.open(assetDBFilePath);
            byte[] bArr = new byte[open.available()];
            int read = open.read(bArr);
            open.close();
            if (read > 0) {
                String cachedFilePath = SpenOcrDataProviderHelper.getCachedFilePath(context, str, str2, spenDBType, bArr);
                open.close();
                return cachedFilePath;
            }
            Log.e(TAG, "There is nothing to read from asset DB File path");
            open.close();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public boolean loadCommonDB() {
        String createBlockAnalyzerDbPath = createBlockAnalyzerDbPath();
        if (createBlockAnalyzerDbPath != null) {
            return loadDBby(createBlockAnalyzerDbPath, "common", SpenDBType.BlockAnalyzer);
        }
        Log.e(TAG, "loadCommonDB() Failed to get db Path");
        return false;
    }

    public boolean loadOcrModel(String str, SpenDBType spenDBType) {
        String[] strArr;
        boolean z;
        String l = C0212a.l("models/", str);
        AssetManager assets = this.mContext.getAssets();
        try {
            strArr = assets.list(l);
        } catch (IOException e) {
            Log.e(TAG, "loadLanguageDB Failed to get asset file list.", e);
            strArr = null;
        }
        if (strArr == null) {
            Log.e(TAG, "loadLanguageDB files == null");
            return false;
        }
        String modelDir = getModelDir();
        if (modelDir == null) {
            Log.e(TAG, "loadLanguageDB dstDir == null");
            return false;
        }
        int length = strArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                z = false;
                break;
            }
            String str2 = strArr[i2];
            if (str2.contains(str)) {
                Log.d(TAG, "loadLanguageDB file : ".concat(str2));
                String str3 = modelDir + "/" + str2;
                z = copyFile(assets, C0212a.B(l, "/", str2), str3);
                modelDir = str3;
                break;
            }
            i2++;
        }
        if (z) {
            return loadDBby(modelDir, str, spenDBType);
        }
        Log.e(TAG, "loadLanguageModel() Fail to prepare language File DB: " + str);
        return false;
    }

    public void releaseCachedDBFilePath(Context context, String str) {
        SpenOcrDataProviderHelper.releaseCachedDBFilePath(context, str);
    }

    public List<String> supportedLanguages() {
        return mSupportedLanguages;
    }

    private void copyFile(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }
}
