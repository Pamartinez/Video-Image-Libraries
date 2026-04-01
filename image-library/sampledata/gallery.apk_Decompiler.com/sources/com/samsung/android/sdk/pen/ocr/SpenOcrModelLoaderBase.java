package com.samsung.android.sdk.pen.ocr;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.util.Log;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SpenOcrModelLoaderBase implements SpenIOcrModelLoader {
    private static final String TAG = "LoaderBase";
    protected final Context mContext;
    protected SpenIOcrModelManager mModelManager;

    public SpenOcrModelLoaderBase(Context context, SpenIOcrModelManager spenIOcrModelManager) {
        this.mContext = context;
        this.mModelManager = spenIOcrModelManager;
    }

    private Object[] getFileDescriptor(AssetFileDescriptor assetFileDescriptor, String str, SpenDBType spenDBType) {
        return getFileDescriptorTuple(assetFileDescriptor);
    }

    private synchronized boolean loadLanguageModel(String str) {
        List<String> supportedLanguages = supportedLanguages();
        if (supportedLanguages != null) {
            if (supportedLanguages.size() != 0) {
                if (SpenOcrLanguage.AUTO.toLanguageCode().equals(str)) {
                    for (String next : supportedLanguages) {
                        if (!loadOcrModel(next, SpenDBType.OCR)) {
                            Log.e(TAG, "loadLanguageModel() fail to loadOcrModel(" + next + ")");
                            return false;
                        }
                    }
                    Log.i(TAG, "loadLanguageModel() all languages are loaded!");
                    return true;
                } else if (!supportedLanguages.contains(str)) {
                    Log.e(TAG, "loadLanguageModel() not supported language : " + str);
                    return false;
                } else {
                    return loadOcrModel(str, SpenDBType.OCR);
                }
            }
        }
        Log.e(TAG, "loadLanguageModel() there is no supported languages");
        return false;
    }

    public Object[] getFileDescriptorTuple(AssetFileDescriptor assetFileDescriptor) {
        return new Object[]{assetFileDescriptor.getFileDescriptor(), Long.valueOf(assetFileDescriptor.getStartOffset()), Long.valueOf(assetFileDescriptor.getLength())};
    }

    public List<String> getSupportedLanguages() {
        return supportedLanguages();
    }

    public boolean loadCommonDB() {
        return loadOcrModel(SpenOcrDataProviderContract.ASSETS_MODEL_BLOCK_ANALYZER, SpenDBType.BlockAnalyzer);
    }

    public synchronized boolean loadDBby(String str, String str2, SpenDBType spenDBType) {
        if (this.mModelManager.loadDB(this.mContext, str, new SpenDBConfig(spenDBType, str2)) >= 0) {
            return true;
        }
        Log.e(TAG, "loadDBby(String filePath, ...) Fail to load");
        return false;
    }

    public boolean loadLanguageDB(String str) {
        return loadLanguageModel(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean loadOcrModel(java.lang.String r5, com.samsung.android.sdk.pen.ocr.SpenDBType r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            android.content.Context r1 = r4.mContext     // Catch:{ IOException -> 0x0019 }
            android.content.res.AssetFileDescriptor r1 = com.samsung.android.sdk.pen.ocr.SpenOcrDataProviderHelper.getAssetFileDescriptor(r1, r5, r6)     // Catch:{ IOException -> 0x0019 }
            if (r1 != 0) goto L_0x001f
            java.lang.String r5 = "LoaderBase"
            java.lang.String r6 = "AssetFileDescriptor is null!"
            android.util.Log.e(r5, r6)     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x001b
            r1.close()     // Catch:{ IOException -> 0x0019 }
            goto L_0x001b
        L_0x0017:
            r5 = move-exception
            goto L_0x004c
        L_0x0019:
            r5 = move-exception
            goto L_0x0041
        L_0x001b:
            monitor-exit(r4)
            return r0
        L_0x001d:
            r5 = move-exception
            goto L_0x0036
        L_0x001f:
            java.lang.Object[] r2 = r4.getFileDescriptor(r1, r5, r6)     // Catch:{ all -> 0x001d }
            java.lang.String r3 = "ba"
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x001d }
            if (r3 == 0) goto L_0x002d
            java.lang.String r5 = "common"
        L_0x002d:
            boolean r5 = r4.loadDBby((java.lang.Object[]) r2, (java.lang.String) r5, (com.samsung.android.sdk.pen.ocr.SpenDBType) r6)     // Catch:{ all -> 0x001d }
            r1.close()     // Catch:{ IOException -> 0x0019 }
            monitor-exit(r4)
            return r5
        L_0x0036:
            if (r1 == 0) goto L_0x0040
            r1.close()     // Catch:{ all -> 0x003c }
            goto L_0x0040
        L_0x003c:
            r6 = move-exception
            r5.addSuppressed(r6)     // Catch:{ IOException -> 0x0019 }
        L_0x0040:
            throw r5     // Catch:{ IOException -> 0x0019 }
        L_0x0041:
            java.lang.String r6 = "LoaderBase"
            java.lang.String r5 = r5.getLocalizedMessage()     // Catch:{ all -> 0x0017 }
            android.util.Log.e(r6, r5)     // Catch:{ all -> 0x0017 }
            monitor-exit(r4)
            return r0
        L_0x004c:
            monitor-exit(r4)     // Catch:{ all -> 0x0017 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.pen.ocr.SpenOcrModelLoaderBase.loadOcrModel(java.lang.String, com.samsung.android.sdk.pen.ocr.SpenDBType):boolean");
    }

    public abstract List<String> supportedLanguages();

    private synchronized boolean loadDBby(Object[] objArr, String str, SpenDBType spenDBType) {
        if (this.mModelManager.loadDB(this.mContext, objArr, new SpenDBConfig(spenDBType, str)) < 0) {
            Log.e(TAG, "loadDBby(Object[] fileDescriptor, ...) Fail to load");
            return false;
        }
        Log.e(TAG, "loadDBby(Object[] fileDescriptor, ...) success to load : " + str);
        return true;
    }

    public void close() {
    }
}
