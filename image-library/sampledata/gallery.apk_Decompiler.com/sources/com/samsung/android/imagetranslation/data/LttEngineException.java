package com.samsung.android.imagetranslation.data;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LttEngineException extends Exception {
    private int errorCode;

    public LttEngineException(int i2, String str) {
        super(str);
        this.errorCode = i2;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
