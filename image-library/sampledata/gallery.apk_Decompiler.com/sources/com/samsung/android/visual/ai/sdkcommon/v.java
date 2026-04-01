package com.samsung.android.visual.ai.sdkcommon;

import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum v {
    MODEL_NOT_FOUND(-2),
    NOT_SPECIFIED(2),
    RESOURCE_BUSY(16),
    REQUEST_CANCELED(17),
    TIME_OUT(101),
    NETWORK_ERROR(102),
    INPUT_DATA_ERROR(103),
    EXCEED_RATE_LIMIT(250),
    MISSING_MANDATORY_FIELD(StatusCodes.INPUT_MISSING),
    SERVICE_EXCEPTION(500),
    CLIENT_ERROR(1000),
    AUTH_ERROR(2000),
    AUTH_SA_ERROR(2200),
    SERVER_QUOTA_ERROR(TextToSpeechConst.MAX_SPEECH_INPUT),
    SAFETY_FILTER_ERROR(5000),
    SERVER_ERROR(9000);
    
    private final int code;

    /* access modifiers changed from: public */
    v(int i2) {
        this.code = i2;
    }

    public final int a() {
        return this.code;
    }
}
