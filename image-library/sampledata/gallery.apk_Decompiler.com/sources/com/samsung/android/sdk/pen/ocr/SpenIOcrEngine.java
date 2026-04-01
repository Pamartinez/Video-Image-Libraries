package com.samsung.android.sdk.pen.ocr;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SpenIOcrEngine {
    void close();

    SpenIOcrRecognizer createRecognizer();

    SpenITypeClassifier createTypeClassifier();

    String getCachedDBFilePath(Context context, String str, String str2, SpenDBType spenDBType);

    boolean isSupportedLanguage(SpenOcrLanguage spenOcrLanguage);

    boolean loadLanguageDB(String str);

    void releaseCachedDBFilePath(Context context, String str);
}
