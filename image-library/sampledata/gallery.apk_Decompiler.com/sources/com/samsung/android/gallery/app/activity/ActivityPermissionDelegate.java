package com.samsung.android.gallery.app.activity;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.dialog.permission.PermissionRationaleDialog;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PermissionHelper;
import com.samsung.android.gallery.support.utils.RuntimePermissionUtil;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActivityPermissionDelegate {
    private static final CharSequence TAG = "PermissionDelegate";
    private final GalleryActivity mActivity;
    private boolean mWaitingPermission;

    public ActivityPermissionDelegate(GalleryActivity galleryActivity) {
        this.mActivity = galleryActivity;
    }

    private boolean shouldShowRequestPermission(Activity activity, String[] strArr) {
        if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
            return false;
        }
        for (String str : strArr) {
            if (activity.shouldShowRequestPermissionRationale(str)) {
                Log.d(TAG, "shouldShowRequestPermissionRationale " + str + "=true");
                return true;
            }
        }
        return false;
    }

    private void showPermissionRequestDialog(Activity activity, String[] strArr) {
        activity.requestPermissions(strArr, 106);
    }

    private void showRationaleDialog(Activity activity, String[] strArr) {
        Bundle bundle = new Bundle();
        bundle.putString("size", "" + strArr.length);
        bundle.putString("isFinishActivity", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            bundle.putString(C0086a.i(i2, "request"), strArr[i2]);
        }
        PermissionRationaleDialog permissionRationaleDialog = new PermissionRationaleDialog();
        permissionRationaleDialog.setArguments(bundle);
        try {
            permissionRationaleDialog.show(((AppCompatActivity) activity).getSupportFragmentManager(), "PermissionRationaleDialog");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void acquireLaunchPermission(Activity activity) {
        String[] strArr = RuntimePermissionUtil.LAUNCH_PERMISSION_GROUP;
        if (RuntimePermissionUtil.hasLaunchPermission(activity)) {
            return;
        }
        if (shouldShowRequestPermission(activity, strArr)) {
            showPermissionRequestDialog(activity, strArr);
        } else if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            showRationaleDialog(activity, strArr);
        } else {
            PermissionHelper.showSnackBar(activity);
            activity.finish();
        }
    }

    public void acquireMandatoryPermission() {
        if (!isPermissionCheckRequired()) {
            return;
        }
        if (RuntimePermissionUtil.hasLaunchPermission(this.mActivity)) {
            afterAcquirePermissions();
        } else {
            acquireLaunchPermission(this.mActivity);
        }
    }

    public void afterAcquirePermissions() {
        setPermissionCheckRequired(false);
        this.mActivity.onCreateInternal();
    }

    public void inspectMandatoryPermission() {
        if (RuntimePermissionUtil.hasLaunchPermission(this.mActivity)) {
            afterAcquirePermissions();
        } else {
            setPermissionCheckRequired(true);
        }
    }

    public boolean isPermissionCheckRequired() {
        return this.mWaitingPermission;
    }

    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (strArr.length == 0 || iArr.length == 0) {
            Log.e(TAG, "onRequestPermissionsResult length is zero");
            return;
        }
        this.mActivity.getBlackboard().post("lifecycle://on_request_permission_result", new Object[]{Integer.valueOf(i2), strArr, iArr});
        if (i2 == 106) {
            for (int i7 = 0; i7 < strArr.length; i7++) {
                if (iArr[i7] == -1) {
                    this.mActivity.finish();
                    return;
                }
            }
            afterAcquirePermissions();
        }
    }

    public void setPermissionCheckRequired(boolean z) {
        this.mWaitingPermission = z;
    }
}
