package com.samsung.android.sdk.pen.ocr;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SpenIOcrModelManager {
    void close();

    int findDB(SpenDBConfig spenDBConfig);

    String getDBVersion(int i2);

    int loadDB(Context context, String str, SpenDBConfig spenDBConfig);

    int loadDB(Context context, Object[] objArr, SpenDBConfig spenDBConfig);

    void setMaxDBNumber(int i2);

    void setModelLoader(SpenIOcrModelLoaderNative spenIOcrModelLoaderNative);

    void unloadAllDB();

    void unloadDB(int i2);
}
