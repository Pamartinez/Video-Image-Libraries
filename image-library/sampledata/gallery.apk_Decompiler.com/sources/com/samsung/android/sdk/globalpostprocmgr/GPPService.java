package com.samsung.android.sdk.globalpostprocmgr;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.samsung.android.sdk.SsdkInterface;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.SsdkVendorCheck;
import com.samsung.android.sdk.globalpostprocmgr.util.Log;
import i.C0212a;
import java.util.Collections;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GPPService implements SsdkInterface {
    public static final int FEATURE_TASK_STARTRAIL = 2;
    public static final int FEATURE_TASK_VIDEOCLIPPER = 3;
    public static final int FEATURE_TASK_WATERMARK = 1;
    private static final String GPPM_PACKAGE = "com.samsung.android.globalpostprocmgr";
    private static final long MINIMUM_SUPPORTED_GPPM_PP_VERSION = 200000000;
    private static final String TAG = "GPPService";
    private Context mContext;

    public Set<String> getParameterList(int i2) {
        Context context = this.mContext;
        if (context != null) {
            return GPPServiceDBUtil.queryDBForFeatureParamList(context, i2);
        }
        Log.e(TAG, C0212a.j(i2, "getParameterList returning false for ", " as SDK is not initialized"), new Object[0]);
        return Collections.EMPTY_SET;
    }

    public Set<String> getSolutionLibList(int i2) {
        Context context = this.mContext;
        if (context != null) {
            return GPPServiceDBUtil.queryDBForSolutionLibList(context, i2);
        }
        Log.e(TAG, C0212a.j(i2, "getSolutionLibList returning false for ", " as SDK is not initialized"), new Object[0]);
        return Collections.EMPTY_SET;
    }

    public int getVersionCode() {
        return 0;
    }

    public String getVersionName() {
        return "0.1.35";
    }

    public void initialize(Context context) {
        if (context == null) {
            Log.e(TAG, "initialize: Context is null", new Object[0]);
            return;
        }
        Log.d(TAG, "initialized by " + context.getPackageName() + " on " + Build.MODEL + " made by " + Build.MANUFACTURER, new Object[0]);
        if (SsdkVendorCheck.isSamsungDevice()) {
            Log.d(TAG, "initialize : VersionCode " + getVersionCode() + " VersionName : " + getVersionName(), new Object[0]);
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(GPPM_PACKAGE, 0);
                if (packageInfo != null) {
                    Log.d(TAG, "initialize: GPPM version installed on Device is " + packageInfo.versionName, new Object[0]);
                    Log.d(TAG, "initialize: GPPM long version code: " + packageInfo.getLongVersionCode() + ", minimun supported version code 200000000", new Object[0]);
                    if (packageInfo.getLongVersionCode() >= MINIMUM_SUPPORTED_GPPM_PP_VERSION) {
                        this.mContext = context;
                        return;
                    }
                    throw new SsdkUnsupportedException("initialize: Installed GPPM's version code " + packageInfo.getLongVersionCode() + " is less than minimum supported code 200000000", 1);
                }
                throw new SsdkUnsupportedException("Device does not support GPPM", 1);
            } catch (PackageManager.NameNotFoundException unused) {
                throw new SsdkUnsupportedException("Device does not support GPPM", 1);
            }
        } else {
            throw new SsdkUnsupportedException("Device Manufacturer is not supported", 0);
        }
    }

    public boolean isFeatureEnabled(int i2) {
        Context context = this.mContext;
        if (context != null) {
            return GPPServiceDBUtil.queryDBForFeatureSupport(context, i2);
        }
        Log.e(TAG, C0212a.j(i2, "isFeatureEnabled returning false for ", " as SDK is not initialized"), new Object[0]);
        return false;
    }
}
