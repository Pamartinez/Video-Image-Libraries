package com.samsung.android.sdk.mobileservice.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.samsung.android.sdk.mobileservice.SeMobileService;
import com.samsung.android.sdk.mobileservice.util.SdkLog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CommonUtils {
    public static final int MAX_IMAGE_SIZE = 819200;
    public static final String MOBILE_SERVICE_PACKAGE_NAME = "com.samsung.android.mobileservice";
    public static final String SAMSUNG_ACCOUNT_PACKAGE_NAME = "com.osp.app.signin";
    private static final String TAG = "CommonUtils";
    private static Boolean sIsStandAloneSamsungAccountSupported;

    public static String getMetaData(Context context, String str, String str2) {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
        } catch (PackageManager.NameNotFoundException e) {
            SdkLog.s(e);
            applicationInfo = null;
        }
        if (applicationInfo != null) {
            bundle = applicationInfo.metaData;
        } else {
            bundle = null;
        }
        if (bundle != null) {
            return bundle.getString(str2);
        }
        return null;
    }

    private static boolean getSupportStandAloneFromMetaData(Context context) {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(SAMSUNG_ACCOUNT_PACKAGE_NAME, 128);
        } catch (PackageManager.NameNotFoundException e) {
            SdkLog.s(e);
            applicationInfo = null;
        }
        boolean z = false;
        if (!(applicationInfo == null || (bundle = applicationInfo.metaData) == null)) {
            z = bundle.getBoolean("SupportStandAlone", false);
        }
        SdkLog.d(TAG, "getSupportStandAloneFromMetaData : " + z);
        sIsStandAloneSamsungAccountSupported = Boolean.valueOf(z);
        return z;
    }

    public static boolean isAgentNoMoreSupportedVersion(int i2, Context context) {
        if (SeMobileService.getAgentVersion(context) >= i2) {
            return true;
        }
        return false;
    }

    public static boolean isAgentSupportMinVersion(int i2, Context context) {
        if (SeMobileService.getAgentVersion(context) >= i2) {
            return true;
        }
        return false;
    }

    public static boolean isAgentSupportMinVersionBetween(int i2, int i7, Context context) {
        if (SeMobileService.getAgentVersion(context) < i2 || SeMobileService.getAgentVersion(context) >= i7) {
            return false;
        }
        return true;
    }

    public static boolean isSaAgentSupportMinVersion(int i2, Context context) {
        if (!isStandAloneSamsungAccountSupported(context) || SeMobileService.getSaAgentVersion(context) < i2) {
            return false;
        }
        return true;
    }

    public static boolean isStandAloneSamsungAccountSupported(Context context) {
        Boolean bool = sIsStandAloneSamsungAccountSupported;
        if (bool != null) {
            return bool.equals(Boolean.TRUE);
        }
        return getSupportStandAloneFromMetaData(context);
    }
}
