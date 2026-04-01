package com.samsung.android.sdk.spage.card;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.samsung.android.sdk.SsdkInterface;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.SsdkVendorCheck;
import com.samsung.android.sivs.ai.sdkcommon.asr.SpeechRecognitionConst;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpageCardSdk implements SsdkInterface {
    private static final String BIXBY_HOME_PACKAGE = "com.samsung.android.app.spage";
    public static final int FEATURE_TEMPLATE = 1;
    private static final int MIN_BIXBY_HOME_VERSION_CODE_SUPPORT_SDK = 210200007;
    private static final int VERSION_CODE = 130100000;
    private static final String VERSION_NAME = "1.3.01.0";
    private PackageInfo mInfo = null;

    public int getApiPatchVersionOfSpage(Context context) {
        if (MIN_BIXBY_HOME_VERSION_CODE_SUPPORT_SDK > this.mInfo.versionCode) {
            return 0;
        }
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(BIXBY_HOME_PACKAGE, 128).metaData;
            if (bundle != null) {
                return bundle.getInt("api_patch");
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return 0;
    }

    public int getApiVersionOfSpage(Context context) {
        int i2 = 0;
        if (MIN_BIXBY_HOME_VERSION_CODE_SUPPORT_SDK > this.mInfo.versionCode) {
            return 0;
        }
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(BIXBY_HOME_PACKAGE, 128).metaData;
            if (bundle != null) {
                i2 = bundle.getInt(SpeechRecognitionConst.Key.INFO_API_LEVEL);
            }
            if (i2 == 0) {
                return 1;
            }
            return i2;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public int getVersionCode() {
        return 130100000;
    }

    public String getVersionName() {
        return "1.3.01.0";
    }

    public void initialize(Context context) {
        if (!SsdkVendorCheck.isSamsungDevice()) {
            throw new SsdkUnsupportedException("This is not samsung product", 0);
        } else if (context != null) {
            try {
                this.mInfo = context.getPackageManager().getPackageInfo(BIXBY_HOME_PACKAGE, 1);
            } catch (PackageManager.NameNotFoundException unused) {
                throw new SsdkUnsupportedException("This device is not supported Bixby Home.", 1);
            }
        } else {
            throw new NullPointerException("You should set context.");
        }
    }

    public boolean isFeatureEnabled(int i2) {
        if (i2 == 1) {
            try {
                if (MIN_BIXBY_HOME_VERSION_CODE_SUPPORT_SDK <= this.mInfo.versionCode) {
                    return true;
                }
                return false;
            } catch (NullPointerException unused) {
            }
        } else {
            throw new IllegalArgumentException("This type is not support");
        }
    }
}
