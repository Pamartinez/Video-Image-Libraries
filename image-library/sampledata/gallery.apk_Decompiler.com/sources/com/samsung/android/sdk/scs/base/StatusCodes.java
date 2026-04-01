package com.samsung.android.sdk.scs.base;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StatusCodes {
    public static final int EMERGENCY_MODE = 8;
    public static final int ERROR = 2000;
    public static final int FEATURE_UNAVAILABLE = 5;
    public static final int INPUT_INVALID = 700;
    public static final int INPUT_MISSING = 300;
    public static final int REMOTE_EXCEPTION = 500;
    public static final int SERVICE_DISABLED = 2;
    public static final int SERVICE_MISSING = 1;
    public static final int SERVICE_MISSING_PERMISSION = 6;
    public static final int SERVICE_UPDATING = 4;
    public static final int SERVICE_VERSION_UPDATE_REQUIRED = 3;
    public static final int SUCCESS = 0;
    public static final int TIMEOUT = 7;
    public static final int UNDEFINED = -1000;

    @Retention(RetentionPolicy.CLASS)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface checkFeatureResult {
    }
}
