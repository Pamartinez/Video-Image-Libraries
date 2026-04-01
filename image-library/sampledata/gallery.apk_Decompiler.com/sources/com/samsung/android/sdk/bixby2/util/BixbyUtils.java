package com.samsung.android.sdk.bixby2.util;

import android.os.Bundle;
import com.samsung.android.sdk.bixby2.LogUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BixbyUtils {
    private static final String TAG = "BixbyUtils";

    public static BixbyContextInfo getBixbyContextInfo(Bundle bundle) {
        LogUtil.d(TAG, "getBixbyContextInfo()");
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle("contextInfo");
        if (bundle2 == null || bundle2.isEmpty()) {
            return new BixbyContextInfo();
        }
        return new BixbyContextInfo(bundle2);
    }
}
