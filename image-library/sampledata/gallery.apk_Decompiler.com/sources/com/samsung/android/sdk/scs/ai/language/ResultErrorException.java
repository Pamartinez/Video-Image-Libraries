package com.samsung.android.sdk.scs.ai.language;

import com.samsung.android.sdk.scs.ai.language.ErrorClassifier;
import com.samsung.android.sdk.scs.base.ResultException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ResultErrorException extends ResultException {
    public final int mErrorCode;

    public ResultErrorException(int i2) {
        super(i2);
        this.mErrorCode = ErrorClassifier.ErrorCode.DEVICE_UNKNOWN_ERROR.ordinal();
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public ErrorClassifier.ErrorCode getErrorCodeClassified() {
        return ErrorClassifier.getErrorCode(this.mErrorCode);
    }

    public ResultErrorException(int i2, String str) {
        super(i2, str);
        this.mErrorCode = ErrorClassifier.ErrorCode.DEVICE_UNKNOWN_ERROR.ordinal();
    }

    public ResultErrorException(int i2, int i7, String str) {
        super(i7, str);
        this.mErrorCode = i7;
    }
}
