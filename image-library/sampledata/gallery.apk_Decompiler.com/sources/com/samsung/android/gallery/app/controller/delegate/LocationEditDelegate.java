package com.samsung.android.gallery.app.controller.delegate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.MapUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LocationEditDelegate {
    public static void handleOnLocationEditActivityResult(Blackboard blackboard, int i2, Intent intent) {
        if (i2 != -1) {
            blackboard.post("data://user/EditLocation", (Object) null);
            return;
        }
        blackboard.post("data://user/EditLocation", new Object[]{intent.getStringExtra("address"), Double.valueOf(intent.getDoubleExtra("latitude", MapUtil.INVALID_LOCATION)), Double.valueOf(intent.getDoubleExtra("longitude", MapUtil.INVALID_LOCATION))});
    }

    public static void startLocationEditActivity(Activity activity, Bundle bundle) {
        String string = BundleWrapper.getString(bundle, "address", "");
        double d = BundleWrapper.getDouble(bundle, "latitude");
        activity.startActivityForResult(new Intent().setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.LocationPickerActivity").putExtra("address", string).putExtra("latitude", d).putExtra("longitude", BundleWrapper.getDouble(bundle, "longitude")), 778);
    }
}
