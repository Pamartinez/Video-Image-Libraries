package com.samsung.android.sdk.pen.ocr;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrDataProviderContract {
    public static final String ASSETS_MODEL_BLOCK_ANALYZER = "ba";
    public static final String ASSETS_MODEL_DIR_BLOCK_ANALYZER = "blockanalyzer";
    public static final String ASSETS_MODEL_DIR_MOIRE_DETECTOR = "moiredetector";
    public static final String ASSETS_MODEL_DIR_OCRDB = "ocrdb";
    public static final String ASSETS_MODEL_MOIRE_DETECTOR = "moire";
    public static final String AUTHORITY = "com.samsung.android.sdk.ocr.resourcemanager";
    public static final String COMMON_LANGUAGE = "language";
    public static final String COMMON_PRELOADED = "preloaded";
    public static final String COMMON_VERSION = "version";
    public static final String DBNAME_EXTENSION = ".dat";
    public static final String TFLITE_EXTENSION = ".tflite";
    public static final String URI_PATH_INSTALLED_LANGUAGES = "installed_languages";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class InstalledLanguages {
        public static final Uri CONTENT_URI = Uri.parse("content://com.samsung.android.sdk.ocr.resourcemanager/installed_languages");
        public static final String LANGUAGE = "language";
        public static final String VERSION = "version";
    }
}
