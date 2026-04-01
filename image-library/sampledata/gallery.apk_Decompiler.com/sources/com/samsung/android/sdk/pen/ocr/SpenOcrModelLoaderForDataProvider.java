package com.samsung.android.sdk.pen.ocr;

import android.content.Context;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrModelLoaderForDataProvider extends SpenOcrModelLoaderBase {
    private static final String TAG = "LoaderForDataProvider";

    public SpenOcrModelLoaderForDataProvider(Context context, SpenIOcrModelManager spenIOcrModelManager) {
        super(context, spenIOcrModelManager);
    }

    public String getCachedDBFilePath(Context context, String str, String str2, SpenDBType spenDBType) {
        return SpenOcrDataProviderHelper.getCachedFilePath(context, str, str2, spenDBType);
    }

    public void releaseCachedDBFilePath(Context context, String str) {
        SpenOcrDataProviderHelper.releaseCachedDBFilePath(context, str);
    }

    public List<String> supportedLanguages() {
        return SpenOcrDataProviderHelper.getSupportedLanguages(this.mContext);
    }
}
