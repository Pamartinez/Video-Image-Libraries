package com.samsung.android.sdk.pen.ocr;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SpenDBConfig {
    private SpenDBType mDbType;
    private String mLanguage;

    public SpenDBConfig(SpenDBType spenDBType, String str) {
        SpenDBType spenDBType2 = SpenDBType.OCR;
        this.mDbType = spenDBType;
        this.mLanguage = str;
    }

    public SpenDBType getDBType() {
        return this.mDbType;
    }

    public final String getDBTypeName() {
        return this.mDbType.name();
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    public void setDBType(SpenDBType spenDBType) {
        this.mDbType = spenDBType;
    }

    public void setLanguage(String str) {
        this.mLanguage = str;
    }
}
