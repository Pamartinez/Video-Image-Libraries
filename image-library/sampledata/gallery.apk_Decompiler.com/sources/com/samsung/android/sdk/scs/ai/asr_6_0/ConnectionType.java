package com.samsung.android.sdk.scs.ai.asr_6_0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ConnectionType {
    LOCAL(1),
    NETWORK(2);
    
    private final int type;

    private ConnectionType(int i2) {
        this.type = i2;
    }

    public int getTypeInt() {
        return this.type;
    }
}
