package com.samsung.android.sdk.scs.base;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ResultException extends RuntimeException {
    public final int mResultCode;

    public ResultException(int i2) {
        this.mResultCode = i2;
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public ResultException(int i2, String str) {
        super(str);
        this.mResultCode = i2;
    }
}
