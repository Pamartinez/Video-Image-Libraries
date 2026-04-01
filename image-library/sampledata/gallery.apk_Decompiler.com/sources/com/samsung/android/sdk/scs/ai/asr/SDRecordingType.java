package com.samsung.android.sdk.scs.ai.asr;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum SDRecordingType {
    TYPE_NORMAL(0),
    TYPE_CALL(1);
    
    private final int type;

    private SDRecordingType(int i2) {
        this.type = i2;
    }

    public int getTypeInt() {
        return this.type;
    }
}
