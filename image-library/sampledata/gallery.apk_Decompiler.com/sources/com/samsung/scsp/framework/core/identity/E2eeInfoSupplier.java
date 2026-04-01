package com.samsung.scsp.framework.core.identity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface E2eeInfoSupplier {
    public static final int DEFAULT_INTEGRITY_VALUE = Integer.MAX_VALUE;
    public static final String[] TYPES = {"non", "kmx", "sks"};

    int getDeviceIntegrity() {
        return Integer.MAX_VALUE;
    }

    String getSakUid();

    int getType();
}
