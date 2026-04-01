package com.samsung.android.sdk.scs.ai.language;

import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.android.sum.core.message.Message;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ErrorClassifier {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ErrorCode {
        DEVICE_ERROR(100),
        DEVICE_NETWORK_ERROR(101),
        DEVICE_INIT_ERROR(102),
        DEVICE_WATCH_CONNECTION_ERROR(MOCRLang.KHMER),
        DEVICE_WATCH_DATA_LAYER_ERROR(181),
        DEVICE_WATCH_ON_DEVICE_PACKAGE_ERROR(182),
        DEVICE_WATCH_INTERNAL_ERROR(183),
        DEVICE_WATCH_NOT_SUPPORT_TASK_ERROR(184),
        DEVICE_UNKNOWN_ERROR(199),
        DEVICE_EXCEED_RATE_LIMIT(250),
        CLIENT_ERROR(200),
        CLIENT_ERROR_INVALID_REQUEST_TYPE(201),
        CLIENT_ERROR_MANDATORY_FIELD_MISSING(MOCRLang.ARMENIAN),
        CLIENT_ERROR_INPUT_TOO_LONG(211),
        CLIENT_ERROR_UNSUPPORTED_DEVICE(MOCRLang.LAO),
        CLIENT_ERROR_BUSY(221),
        CLIENT_ERROR_CANCEL(222),
        CLIENT_ERROR_UNSUPPORTED_LANGUAGE(230),
        CLIENT_ERROR_KERNEL_UPDATE_REQUIRED(240),
        CLIENT_ERROR_MODEL_UPDATE_REQUIRED(241),
        AUTH_ERROR(StatusCodes.INPUT_MISSING),
        AUTH_SA_ERROR(301),
        SAFETY_FILTER_ERROR(400),
        SAFETY_FILTER_UNSUPPORTED_LANGUAGE_ERROR(401),
        SAFETY_FILTER_RECITATION_ERROR(402),
        SAFETY_FILTER_TOXIC_ERROR(403),
        SERVER_ERROR(500),
        SERVER_QUOTA_ERROR(501),
        SERVER_INTERNAL_ERROR(Message.WARN_CANCELED),
        SERVER_UNVAILABLE(503);
        
        private final int mError;

        private ErrorCode(int i2) {
            this.mError = i2;
        }
    }

    public static ErrorCode getErrorCode(int i2) {
        int i7 = i2 / 1000;
        if (i2 >= 1 && i2 <= 16) {
            return ErrorCode.DEVICE_NETWORK_ERROR;
        }
        if (i2 == 102) {
            return ErrorCode.DEVICE_INIT_ERROR;
        }
        if (i2 == 180) {
            return ErrorCode.DEVICE_WATCH_CONNECTION_ERROR;
        }
        if (i2 == 181) {
            return ErrorCode.DEVICE_WATCH_DATA_LAYER_ERROR;
        }
        if (i2 == 182) {
            return ErrorCode.DEVICE_WATCH_ON_DEVICE_PACKAGE_ERROR;
        }
        if (i2 == 183) {
            return ErrorCode.DEVICE_WATCH_INTERNAL_ERROR;
        }
        if (i2 == 184) {
            return ErrorCode.DEVICE_WATCH_NOT_SUPPORT_TASK_ERROR;
        }
        if (i2 == 250) {
            return ErrorCode.DEVICE_EXCEED_RATE_LIMIT;
        }
        if (i7 != 1) {
            if (i7 != 2) {
                if (i7 == 4) {
                    return ErrorCode.SERVER_QUOTA_ERROR;
                }
                if (i7 == 5) {
                    if (i2 != 5120) {
                        if (i2 != 5152) {
                            if (i2 == 5210) {
                                return ErrorCode.SAFETY_FILTER_RECITATION_ERROR;
                            }
                            if (i2 != 5220) {
                                if (i2 != 5252) {
                                    return ErrorCode.SAFETY_FILTER_ERROR;
                                }
                            }
                        }
                        return ErrorCode.SAFETY_FILTER_TOXIC_ERROR;
                    }
                    return ErrorCode.SAFETY_FILTER_UNSUPPORTED_LANGUAGE_ERROR;
                } else if (i7 != 8 && i7 != 9) {
                    return ErrorCode.DEVICE_UNKNOWN_ERROR;
                } else {
                    if (i2 == 9000) {
                        return ErrorCode.SERVER_INTERNAL_ERROR;
                    }
                    if (i2 != 9001) {
                        return ErrorCode.SERVER_ERROR;
                    }
                    return ErrorCode.SERVER_UNVAILABLE;
                }
            } else if (i2 == 2200 || i2 == 2201) {
                return ErrorCode.AUTH_SA_ERROR;
            } else {
                return ErrorCode.AUTH_ERROR;
            }
        } else if (i2 == 1010) {
            return ErrorCode.CLIENT_ERROR_INVALID_REQUEST_TYPE;
        } else {
            if (i2 == 1024) {
                return ErrorCode.CLIENT_ERROR_MANDATORY_FIELD_MISSING;
            }
            if (i2 == 1026) {
                return ErrorCode.CLIENT_ERROR_INPUT_TOO_LONG;
            }
            if (i2 == 1900) {
                return ErrorCode.CLIENT_ERROR_UNSUPPORTED_LANGUAGE;
            }
            if (i2 == 1107) {
                return ErrorCode.CLIENT_ERROR_KERNEL_UPDATE_REQUIRED;
            }
            if (i2 == 1108) {
                return ErrorCode.CLIENT_ERROR_MODEL_UPDATE_REQUIRED;
            }
            switch (i2) {
                case 1097:
                    return ErrorCode.CLIENT_ERROR_UNSUPPORTED_DEVICE;
                case 1098:
                    return ErrorCode.CLIENT_ERROR_BUSY;
                case 1099:
                    return ErrorCode.CLIENT_ERROR_CANCEL;
                default:
                    return ErrorCode.CLIENT_ERROR;
            }
        }
    }
}
