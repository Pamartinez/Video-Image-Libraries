package com.samsung.android.gallery.app.ui.map;

import Ba.a;
import Fa.C0558l;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.map.picker.PermissionRationaleDialogMap;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PermissionHelper;
import com.samsung.android.gallery.support.utils.RuntimePermissionUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LocationPermissionUtil {
    public static boolean hasLocationPermission(Activity activity, boolean z) {
        return hasLocationPermission(activity, z, (ActivityResultLauncher<String[]>) null);
    }

    public static boolean isDeviceLocationStatusAvailable(Context context, int i2) {
        if (isDeviceLocationStatusAvailable(context)) {
            return true;
        }
        showLocationSettingDialog(context, i2);
        return false;
    }

    private static boolean shouldShowRequestPermission(Activity activity, String[] strArr) {
        for (String str : strArr) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, str)) {
                Log.d("LocationPermissionUtil", "shouldShowRequestPermissionRationale " + str + "=true");
                return true;
            }
        }
        return false;
    }

    private static void showLocationSettingDialog(Context context, int i2) {
        new AlertDialog.Builder(context).setTitle((CharSequence) context.getResources().getString(R.string.moreinfo_location_editor_gps_popup_title)).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new a(4)).setPositiveButton((int) R.string.settings, (DialogInterface.OnClickListener) new C0558l(7, context)).setMessage(i2).create().show();
    }

    public static void showPermissionRationaleDialog(Activity activity) {
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            PermissionHelper.showSnackBar(activity);
            return;
        }
        String[] strArr = RuntimePermissionUtil.LOCATION_PERMISSION_GROUP;
        Bundle bundle = new Bundle();
        bundle.putInt("function", R.string.permission_function_current_location);
        bundle.putString("size", "" + strArr.length);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            bundle.putString(C0086a.i(i2, "request"), strArr[i2]);
        }
        PermissionRationaleDialogMap permissionRationaleDialogMap = new PermissionRationaleDialogMap();
        permissionRationaleDialogMap.setArguments(bundle);
        try {
            permissionRationaleDialogMap.show(((AppCompatActivity) activity).getSupportFragmentManager(), "PermissionRationaleDialogMap");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static void startLocationSettingActivity(Context context) {
        try {
            Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
            intent.setFlags(268435456);
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e((CharSequence) "LocationPermissionUtil", "failed to start location setting", e.getMessage());
        }
    }

    public static boolean hasLocationPermission(Activity activity, boolean z, ActivityResultLauncher<String[]> activityResultLauncher) {
        String[] strArr = RuntimePermissionUtil.LOCATION_PERMISSION_GROUP;
        if (RuntimePermissionUtil.hasPermissionAtLeast(activity, strArr)) {
            return true;
        }
        if (!z) {
            return false;
        }
        try {
            if (shouldShowRequestPermission(activity, strArr)) {
                showPermissionRationaleDialog(activity);
                return false;
            } else if (activityResultLauncher != null) {
                activityResultLauncher.launch(strArr);
                return false;
            } else {
                ActivityCompat.requestPermissions(activity, strArr, 102);
                return false;
            }
        } catch (Exception e) {
            Log.e((CharSequence) "LocationPermissionUtil", "failed to request permissions", e.getMessage());
            return false;
        }
    }

    public static boolean isDeviceLocationStatusAvailable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        return locationManager != null && locationManager.isLocationEnabled();
    }
}
