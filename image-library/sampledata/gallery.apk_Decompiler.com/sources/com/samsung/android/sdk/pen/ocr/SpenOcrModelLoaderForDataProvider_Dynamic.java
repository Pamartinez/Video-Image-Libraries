package com.samsung.android.sdk.pen.ocr;

import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrModelLoaderForDataProvider_Dynamic extends SpenOcrModelLoaderBase implements SpenIOcrModelLoaderNative {
    private static final int MAX_DB_NUM = 5;
    private static final String TAG = "LoaderForDataProviderDynamic";
    private long mNativeHandle;
    private final List<String> mSupportedLanguages;

    public SpenOcrModelLoaderForDataProvider_Dynamic(Context context, SpenIOcrModelManager spenIOcrModelManager) {
        super(context, spenIOcrModelManager);
        ArrayList arrayList = new ArrayList();
        this.mSupportedLanguages = arrayList;
        this.mNativeHandle = 0;
        this.mNativeHandle = Native_createModelLoader(this);
        spenIOcrModelManager.setModelLoader(this);
        spenIOcrModelManager.setMaxDBNumber(5);
        List<String> supportedLanguages = supportedLanguages();
        if (supportedLanguages == null || supportedLanguages.size() == 0) {
            Log.e(TAG, "loadLanguageModel() there is no supported languages");
            return;
        }
        arrayList.addAll(supportedLanguages);
        Log.i(TAG, "mSupportedLanguages : " + arrayList);
    }

    public native long Native_createModelLoader(SpenIOcrModelLoaderNative spenIOcrModelLoaderNative);

    public native void Native_finalize(long j2);

    public void close() {
        if (this.mNativeHandle != 0) {
            this.mModelManager.setModelLoader((SpenIOcrModelLoaderNative) null);
            Native_finalize(this.mNativeHandle);
        }
        this.mNativeHandle = 0;
    }

    public void finalize() {
        super.finalize();
        close();
    }

    public String getCachedDBFilePath(Context context, String str, String str2, SpenDBType spenDBType) {
        return SpenOcrDataProviderHelper.getCachedFilePath(context, str, str2, spenDBType);
    }

    public boolean loadLanguageDB(String str) {
        if (!SpenOcrLanguage.AUTO.toLanguageCode().equals(str)) {
            return super.loadLanguageDB(str);
        }
        return true;
    }

    public long nativeHandle() {
        return this.mNativeHandle;
    }

    public void onGetSupportedModelList(List<String> list) {
        list.addAll(this.mSupportedLanguages);
        Log.i(TAG, "onGetSupportedModelList: added language list = " + list);
    }

    public boolean onLoadRequest(SpenDBConfig spenDBConfig) {
        Log.i(TAG, "onLoadRequest: db type = " + spenDBConfig.getDBTypeName() + ", language = " + spenDBConfig.getLanguage());
        return loadOcrModel(spenDBConfig.getLanguage(), spenDBConfig.getDBType());
    }

    public void releaseCachedDBFilePath(Context context, String str) {
        SpenOcrDataProviderHelper.releaseCachedDBFilePath(context, str);
    }

    public List<String> supportedLanguages() {
        return SpenOcrDataProviderHelper.getSupportedLanguages(this.mContext);
    }
}
