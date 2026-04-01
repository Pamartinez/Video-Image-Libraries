package com.samsung.android.sdk.scs.ai.translation;

import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.android.sum.core.message.Message;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum NeuralTranslationErrorCode {
    UNKNOWN(-1),
    NOT_AVAILABLE_DIRECTION_ERROR(100),
    NONE(200),
    INTERRUPTED(StatusCodes.INPUT_MISSING),
    COMPUTATION_ERROR(400),
    RESOURCE_ACCESS_ERROR(500),
    ILLEGAL_RESOURCE_ERROR(501),
    UNAUTHORIZED_RESOURCE_ERROR(Message.WARN_CANCELED),
    SPEECH_LLM_ERROR_NONE(1000),
    SPEECH_LLM_ERROR_INVALID_PARAMETER(1001),
    SPEECH_LLM_ERROR_FILE_NOT_FOUND(1002),
    SPEECH_LLM_ERROR_CONFIG_ERROR(1003),
    SPEECH_LLM_ERROR_INVALID_STATE(1004),
    SPEECH_LLM_ERROR_INVALID_TYPE(1005),
    SPEECH_LLM_ERROR_INVALID_FILE(ErrorCodeConvertor.TEMP_AGENT_INVALID_PARAMETER),
    SPEECH_LLM_ERROR_UNSUPPORTED(ErrorCodeConvertor.TEMP_AGENT_INTERNAL_ERROR),
    SPEECH_LLM_ERROR_OVERFLOW(1008),
    SPEECH_LLM_ERROR_NOT_INITIALIZED(ErrorCodeConvertor.TEMP_AGENT_FILE_IO_ERROR),
    SPEECH_LLM_ERROR_ALLOCATE_FAIL(ErrorCodeConvertor.TEMP_AGENT_SERVICE_DISABLED),
    SPEECH_LLM_ERROR_INPUT_SOURCE(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN),
    SPEECH_LLM_ERROR_INSUFFICIENT_PERMISSIONS(ErrorCodeConvertor.TEMP_AGENT_NOT_ACTIVATED),
    SPEECH_LLM_ERROR_RESULT_STREAM(ErrorCodeConvertor.TEMP_AGENT_NOT_USER_OWNER),
    SPEECH_LLM_ERROR_TIMEOUT(ErrorCodeConvertor.TEMP_AGENT_VOLLEY_CANCELED);
    
    private final int codeValue;

    private NeuralTranslationErrorCode(int i2) {
        this.codeValue = i2;
    }

    public static NeuralTranslationErrorCode from(int i2) {
        for (NeuralTranslationErrorCode neuralTranslationErrorCode : values()) {
            if (neuralTranslationErrorCode.code() == i2) {
                return neuralTranslationErrorCode;
            }
        }
        return UNKNOWN;
    }

    public int code() {
        return this.codeValue;
    }
}
