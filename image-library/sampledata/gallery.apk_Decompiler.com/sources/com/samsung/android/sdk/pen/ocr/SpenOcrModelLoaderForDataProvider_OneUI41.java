package com.samsung.android.sdk.pen.ocr;

import android.content.Context;
import android.util.Log;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrModelLoaderForDataProvider_OneUI41 extends SpenOcrModelLoaderBase {
    private static final String TAG = "LoaderForDataProvider_OneUI41";
    private static final List<String> mSupportedLanguages = Arrays.asList(new String[]{SpenOcrLanguage.ENGLISH.toLanguageCode(), SpenOcrLanguage.KOREAN.toLanguageCode()});

    public SpenOcrModelLoaderForDataProvider_OneUI41(Context context, SpenIOcrModelManager spenIOcrModelManager) {
        super(context, spenIOcrModelManager);
    }

    public String getCachedDBFilePath(Context context, String str, String str2, SpenDBType spenDBType) {
        Log.w(TAG, "getCachedDBFilePath : Not supported in One UI 4.1");
        return "";
    }

    public void releaseCachedDBFilePath(Context context, String str) {
        Log.w(TAG, "releaseCachedDBFilePath : Not supported in One UI 4.1");
    }

    public List<String> supportedLanguages() {
        return mSupportedLanguages;
    }
}
