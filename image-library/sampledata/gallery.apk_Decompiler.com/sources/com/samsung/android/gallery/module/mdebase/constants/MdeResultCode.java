package com.samsung.android.gallery.module.mdebase.constants;

import c0.C0086a;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.social.social.BundleKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MdeResultCode {
    public static boolean isGdprError(int i2) {
        if (i2 == 112) {
            return true;
        }
        return false;
    }

    public static boolean isPermissionDenied(int i2, String str) {
        if (i2 != 102 || !"ERROR_DEVICE_PERMISSIONS_DENIED".equals(str)) {
            return false;
        }
        return true;
    }

    public static boolean isQuotaExceeded(int i2) {
        if (i2 == 109) {
            return true;
        }
        return false;
    }

    public static boolean isSharedAlbumAlreadyExist(int i2) {
        if (i2 == 126) {
            return true;
        }
        return false;
    }

    public static boolean isSuccess(int i2) {
        if (i2 == 1) {
            return true;
        }
        return false;
    }

    public static boolean isTaskCanceled(String str) {
        return "ERROR_SEMS_TASK_CANCELED".equalsIgnoreCase(str);
    }

    public static String toErrorReason(int i2) {
        if (i2 == 102) {
            return "INVALID_ACCESS";
        }
        if (i2 == 109) {
            return "QUOTA";
        }
        if (i2 != 112) {
            return "UNKNOWN";
        }
        return BundleKey.GDPR;
    }

    public static String toString(CommonResultStatus commonResultStatus) {
        String str;
        if (commonResultStatus == null) {
            return "MdeResult{null}";
        }
        int code = commonResultStatus.getCode();
        StringBuilder sb2 = new StringBuilder("MdeResult{");
        if (code == 1) {
            str = "ok";
        } else {
            str = (code + 44) + toErrorReason(code) + ',' + commonResultStatus.getMessage();
        }
        return C0086a.m(sb2, str, '}');
    }
}
