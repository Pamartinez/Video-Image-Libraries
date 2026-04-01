package com.samsung.android.sdk.mobileservice;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
enum SessionErrorCode {
    NO_PROBLEM(0, 100),
    CAUSE_AGENT_NOT_INSTALLED(1, 0),
    CAUSE_AGENT_DISABLED(2, 1),
    CAUSE_AGENT_NOT_AVAILABLE(3, 2),
    CAUSE_AGENT_OLD_VERSION(4, 3),
    CAUSE_SDK_OLD_VERSION(5, 4),
    CAUSE_CONNECT_CANCELED(6, 5),
    CAUSE_CONNECT_TIMEOUT(7, 6),
    CAUSE_AGENT_FORCE_UPDATE_REQUIRED(8, 7),
    CAUSE_UNDEFINED(99, 0);
    
    private int mPriority;
    private int mValue;

    private SessionErrorCode(int i2, int i7) {
        this.mValue = i2;
        this.mPriority = i7;
    }

    public static SessionErrorCode getErrorCode(int i2) {
        for (SessionErrorCode sessionErrorCode : values()) {
            if (sessionErrorCode.getValue() == i2) {
                return sessionErrorCode;
            }
        }
        return null;
    }

    public int comparePriority(SessionErrorCode sessionErrorCode) {
        if (this.mPriority < sessionErrorCode.getPriority()) {
            return -1;
        }
        if (this.mPriority == sessionErrorCode.getPriority()) {
            return 0;
        }
        return 1;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public int getValue() {
        return this.mValue;
    }
}
