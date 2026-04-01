package com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto;

import com.google.protobuf.Internal$EnumLite;
import com.google.protobuf.Internal$EnumLiteMap;
import com.google.protobuf.Internal$EnumVerifier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum RobinIntegrationCapability implements Internal$EnumLite {
    UNDEFINED_CAPABILITY(0),
    TEXT_QUERY_CAPABILITY(3),
    TEXT_QUERY_WITH_ATTACHMENTS_CAPABILITY(5),
    UNRECOGNIZED(-1);
    
    public static final int TEXT_QUERY_CAPABILITY_VALUE = 3;
    public static final int TEXT_QUERY_WITH_ATTACHMENTS_CAPABILITY_VALUE = 5;
    public static final int UNDEFINED_CAPABILITY_VALUE = 0;
    private static final Internal$EnumLiteMap internalValueMap = null;
    private final int value;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class RobinIntegrationCapabilityVerifier implements Internal$EnumVerifier {
        static final Internal$EnumVerifier INSTANCE = null;

        static {
            INSTANCE = new RobinIntegrationCapabilityVerifier();
        }

        private RobinIntegrationCapabilityVerifier() {
        }

        public boolean isInRange(int i2) {
            if (RobinIntegrationCapability.forNumber(i2) != null) {
                return true;
            }
            return false;
        }
    }

    static {
        internalValueMap = new Internal$EnumLiteMap() {
            public RobinIntegrationCapability findValueByNumber(int i2) {
                return RobinIntegrationCapability.forNumber(i2);
            }
        };
    }

    private RobinIntegrationCapability(int i2) {
        this.value = i2;
    }

    public static RobinIntegrationCapability forNumber(int i2) {
        if (i2 == 0) {
            return UNDEFINED_CAPABILITY;
        }
        if (i2 == 3) {
            return TEXT_QUERY_CAPABILITY;
        }
        if (i2 != 5) {
            return null;
        }
        return TEXT_QUERY_WITH_ATTACHMENTS_CAPABILITY;
    }

    public static Internal$EnumLiteMap internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal$EnumVerifier internalGetVerifier() {
        return RobinIntegrationCapabilityVerifier.INSTANCE;
    }

    public final int getNumber() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    @Deprecated
    public static RobinIntegrationCapability valueOf(int i2) {
        return forNumber(i2);
    }
}
