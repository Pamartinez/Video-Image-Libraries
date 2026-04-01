package com.samsung.android.sdk.pen.ocr;

import android.content.Context;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SpenIOcrModelLoader {
    void close();

    String getCachedDBFilePath(Context context, String str, String str2, SpenDBType spenDBType);

    List<String> getSupportedLanguages();

    boolean loadCommonDB();

    boolean loadLanguageDB(String str);

    void releaseCachedDBFilePath(Context context, String str);
}
