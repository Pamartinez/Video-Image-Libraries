package com.samsung.android.sdk.scs.ai.visual;

import com.samsung.android.sdk.scs.base.ResultException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VisualResultErrorException extends ResultException {
    private final VisualErrorCode mErrorCode;

    public VisualResultErrorException(int i2, String str) {
        super(i2, str);
        this.mErrorCode = VisualErrorCode.toErrorCode(i2);
    }

    public VisualErrorCode getErrorCode() {
        return this.mErrorCode;
    }

    public int getServerErrorCode() {
        return getResultCode();
    }

    public String toString() {
        return super.toString() + ", errorCode=" + this.mErrorCode + ", serverErrorCode=" + getServerErrorCode();
    }
}
