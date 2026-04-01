package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Util;
import com.samsung.android.imagetranslation.common.LttEngineErrors;
import com.samsung.android.livetranslation.task.LiveTranslationTask;
import com.samsung.android.sum.solution.filter.UniImgp;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PlaybackException extends Exception {
    private static final String FIELD_BUNDLE_EXTRAS = Util.intToStringMaxRadix(5);
    private static final String FIELD_INT_ERROR_CODE = Util.intToStringMaxRadix(0);
    private static final String FIELD_LONG_TIMESTAMP_MS = Util.intToStringMaxRadix(1);
    private static final String FIELD_STRING_CAUSE_CLASS_NAME = Util.intToStringMaxRadix(3);
    private static final String FIELD_STRING_CAUSE_MESSAGE = Util.intToStringMaxRadix(4);
    private static final String FIELD_STRING_MESSAGE = Util.intToStringMaxRadix(2);
    public final int errorCode;
    public final Bundle extras;
    public final long timestampMs;

    public PlaybackException(String str, Throwable th, int i2, Bundle bundle, long j2) {
        super(str, th);
        this.errorCode = i2;
        this.extras = bundle;
        this.timestampMs = j2;
    }

    public static String getErrorCodeName(int i2) {
        if (i2 == -100) {
            return "ERROR_CODE_DISCONNECTED";
        }
        if (i2 == -6) {
            return "ERROR_CODE_NOT_SUPPORTED";
        }
        if (i2 == -4) {
            return "ERROR_CODE_PERMISSION_DENIED";
        }
        if (i2 == -3) {
            return "ERROR_CODE_BAD_VALUE";
        }
        if (i2 == -2) {
            return "ERROR_CODE_INVALID_STATE";
        }
        if (i2 == 7000) {
            return "ERROR_CODE_VIDEO_FRAME_PROCESSOR_INIT_FAILED";
        }
        if (i2 == 7001) {
            return "ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED";
        }
        switch (i2) {
            case LttEngineErrors.ERROR_INPAINTING_RESOLUTION_NOT_SUPPORTED /*-110*/:
                return "ERROR_CODE_CONTENT_ALREADY_PLAYING";
            case LttEngineErrors.ERROR_INPAINTING_INPUT_IMAGE_NULL /*-109*/:
                return "ERROR_CODE_END_OF_PLAYLIST";
            case LttEngineErrors.ERROR_INPAINTING_GET_MASK_NATIVE_FAILED /*-108*/:
                return "ERROR_CODE_SETUP_REQUIRED";
            case LttEngineErrors.ERROR_INPAINTING_VEX_MANAGER_NULL /*-107*/:
                return "ERROR_CODE_SKIP_LIMIT_REACHED";
            case LttEngineErrors.ERROR_INPAINTING_IMG_DIM_MISMATCH /*-106*/:
                return "ERROR_CODE_NOT_AVAILABLE_IN_REGION";
            case LttEngineErrors.ERROR_INPAINTING_IMG_DECODING_FAILED /*-105*/:
                return "ERROR_CODE_PARENTAL_CONTROL_RESTRICTED";
            case LttEngineErrors.ERROR_INPAINTING_NOT_SUPPORTED /*-104*/:
                return "ERROR_CODE_CONCURRENT_STREAM_LIMIT";
            case LttEngineErrors.ERROR_INPAINTING_PROCESSING_FAILED /*-103*/:
                return "ERROR_CODE_PREMIUM_ACCOUNT_REQUIRED";
            case LttEngineErrors.ERROR_INPAINTING_INITIALIZATION_FAILED /*-102*/:
                return "ERROR_CODE_AUTHENTICATION_EXPIRED";
            default:
                switch (i2) {
                    case 1000:
                        return "ERROR_CODE_UNSPECIFIED";
                    case 1001:
                        return "ERROR_CODE_REMOTE_ERROR";
                    case 1002:
                        return "ERROR_CODE_BEHIND_LIVE_WINDOW";
                    case 1003:
                        return "ERROR_CODE_TIMEOUT";
                    case 1004:
                        return "ERROR_CODE_FAILED_RUNTIME_CHECK";
                    default:
                        switch (i2) {
                            case 2000:
                                return "ERROR_CODE_IO_UNSPECIFIED";
                            case 2001:
                                return "ERROR_CODE_IO_NETWORK_CONNECTION_FAILED";
                            case 2002:
                                return "ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT";
                            case 2003:
                                return "ERROR_CODE_IO_INVALID_HTTP_CONTENT_TYPE";
                            case 2004:
                                return "ERROR_CODE_IO_BAD_HTTP_STATUS";
                            case UniImgp.OPTION_PREFERRED_COLOR_FORMAT /*2005*/:
                                return "ERROR_CODE_IO_FILE_NOT_FOUND";
                            case 2006:
                                return "ERROR_CODE_IO_NO_PERMISSION";
                            case 2007:
                                return "ERROR_CODE_IO_CLEARTEXT_NOT_PERMITTED";
                            case 2008:
                                return "ERROR_CODE_IO_READ_POSITION_OUT_OF_RANGE";
                            default:
                                switch (i2) {
                                    case 3001:
                                        return "ERROR_CODE_PARSING_CONTAINER_MALFORMED";
                                    case 3002:
                                        return "ERROR_CODE_PARSING_MANIFEST_MALFORMED";
                                    case LiveTranslationTask.ERRORTYPE.ERR_INVALID_PARSING_DATA /*3003*/:
                                        return "ERROR_CODE_PARSING_CONTAINER_UNSUPPORTED";
                                    case LiveTranslationTask.ERRORTYPE.ERR_SAME_LANGUAGE /*3004*/:
                                        return "ERROR_CODE_PARSING_MANIFEST_UNSUPPORTED";
                                    default:
                                        switch (i2) {
                                            case 4001:
                                                return "ERROR_CODE_DECODER_INIT_FAILED";
                                            case 4002:
                                                return "ERROR_CODE_DECODER_QUERY_FAILED";
                                            case 4003:
                                                return "ERROR_CODE_DECODING_FAILED";
                                            case 4004:
                                                return "ERROR_CODE_DECODING_FORMAT_EXCEEDS_CAPABILITIES";
                                            case 4005:
                                                return "ERROR_CODE_DECODING_FORMAT_UNSUPPORTED";
                                            case 4006:
                                                return "ERROR_CODE_DECODING_RESOURCES_RECLAIMED";
                                            default:
                                                switch (i2) {
                                                    case 5001:
                                                        return "ERROR_CODE_AUDIO_TRACK_INIT_FAILED";
                                                    case 5002:
                                                        return "ERROR_CODE_AUDIO_TRACK_WRITE_FAILED";
                                                    case 5003:
                                                        return "ERROR_CODE_AUDIO_TRACK_OFFLOAD_WRITE_FAILED";
                                                    case 5004:
                                                        return "ERROR_CODE_AUDIO_TRACK_OFFLOAD_INIT_FAILED";
                                                    default:
                                                        switch (i2) {
                                                            case 6000:
                                                                return "ERROR_CODE_DRM_UNSPECIFIED";
                                                            case 6001:
                                                                return "ERROR_CODE_DRM_SCHEME_UNSUPPORTED";
                                                            case 6002:
                                                                return "ERROR_CODE_DRM_PROVISIONING_FAILED";
                                                            case 6003:
                                                                return "ERROR_CODE_DRM_CONTENT_ERROR";
                                                            case 6004:
                                                                return "ERROR_CODE_DRM_LICENSE_ACQUISITION_FAILED";
                                                            case 6005:
                                                                return "ERROR_CODE_DRM_DISALLOWED_OPERATION";
                                                            case 6006:
                                                                return "ERROR_CODE_DRM_SYSTEM_ERROR";
                                                            case 6007:
                                                                return "ERROR_CODE_DRM_DEVICE_REVOKED";
                                                            case 6008:
                                                                return "ERROR_CODE_DRM_LICENSE_EXPIRED";
                                                            default:
                                                                if (i2 >= 1000000) {
                                                                    return "custom error code";
                                                                }
                                                                return "invalid error code";
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    public final String getErrorCodeName() {
        return getErrorCodeName(this.errorCode);
    }
}
