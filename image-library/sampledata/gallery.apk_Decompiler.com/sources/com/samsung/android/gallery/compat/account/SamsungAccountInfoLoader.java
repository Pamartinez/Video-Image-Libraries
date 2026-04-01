package com.samsung.android.gallery.compat.account;

import android.content.Context;
import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SamsungAccountInfoLoader extends SamsungAccountService {
    public Bundle buildReqBundle() {
        Bundle bundle = new Bundle();
        bundle.putStringArray("additional", new String[]{"cc"});
        return bundle;
    }

    public String loadCountryCode(Context context) {
        String str;
        Bundle loadBundle = loadBundle(context);
        if (loadBundle != null) {
            str = loadBundle.getString("cc");
        } else {
            str = null;
        }
        if (str != null) {
            return str;
        }
        return "NONE";
    }
}
