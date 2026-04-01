package com.samsung.android.sdk.scs.ai.visual;

import B8.b;
import com.samsung.android.visual.ai.sdkcommon.v;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum VisualErrorCode {
    MODEL_NOT_FOUND(v.MODEL_NOT_FOUND.a()),
    NOT_SPECIFIED(v.NOT_SPECIFIED.a()),
    RESOURCE_BUSY(v.RESOURCE_BUSY.a()),
    REQUEST_CANCELED(v.REQUEST_CANCELED.a()),
    TIME_OUT(v.TIME_OUT.a()),
    NETWORK_ERROR(v.NETWORK_ERROR.a()),
    INPUT_DATA_ERROR(v.INPUT_DATA_ERROR.a()),
    MISSING_MANDATORY_FIELD(v.MISSING_MANDATORY_FIELD.a()),
    EXCEED_RATE_LIMIT(v.EXCEED_RATE_LIMIT.a()),
    SERVICE_EXCEPTION(v.SERVICE_EXCEPTION.a()),
    CLIENT_ERROR(v.CLIENT_ERROR.a()),
    AUTH_ERROR(v.AUTH_ERROR.a()),
    AUTH_SA_ERROR(v.AUTH_SA_ERROR.a()),
    SERVER_QUOTA_ERROR(v.SERVER_QUOTA_ERROR.a()),
    SAFETY_FILTER_ERROR(v.SAFETY_FILTER_ERROR.a()),
    SERVER_ERROR(v.SERVER_ERROR.a());
    
    private final int code;

    private VisualErrorCode(int i2) {
        this.code = i2;
    }

    public static VisualErrorCode toCoreErrorCode(int i2) {
        return (VisualErrorCode) Arrays.stream(values()).filter(new b(i2, 15)).findFirst().orElse(SERVER_ERROR);
    }

    public static VisualErrorCode toErrorCode(int i2) {
        int i7 = i2 / 1000;
        if (i7 == 0) {
            return toCoreErrorCode(i2);
        }
        if (i7 == 1) {
            return CLIENT_ERROR;
        }
        if (i7 != 2) {
            if (i7 == 4) {
                return SERVER_QUOTA_ERROR;
            }
            if (i7 == 5) {
                return SAFETY_FILTER_ERROR;
            }
            if (i7 == 8 || i7 == 9) {
                return SERVER_ERROR;
            }
            return SERVER_ERROR;
        } else if (i2 == 2200 || i2 == 2201) {
            return AUTH_SA_ERROR;
        } else {
            return AUTH_ERROR;
        }
    }

    public int getCode() {
        return this.code;
    }
}
