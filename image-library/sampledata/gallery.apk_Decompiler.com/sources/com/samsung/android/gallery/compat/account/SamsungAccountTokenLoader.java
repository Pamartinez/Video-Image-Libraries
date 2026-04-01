package com.samsung.android.gallery.compat.account;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SamsungAccountTokenLoader extends SamsungAccountService {
    private String mExpiredToken;
    private int mRetryCount;

    public Bundle buildReqBundle() {
        Bundle buildReqBundle = super.buildReqBundle();
        String str = this.mExpiredToken;
        if (str != null) {
            buildReqBundle.putString("expired_access_token", str);
        }
        return buildReqBundle;
    }

    public long getTimeoutValue() {
        return 50000;
    }

    public Bundle loadAccessToken(Context context, String str) {
        this.mExpiredToken = str;
        return loadBundle(context);
    }

    public void onReceiveAccessToken(int i2, boolean z, Bundle bundle) {
        if (bundle != null && !z) {
            int i7 = this.mRetryCount + 1;
            this.mRetryCount = i7;
            if (i7 < 3 && requestAccessToken()) {
                String str = this.TAG;
                StringBuilder o2 = C0086a.o(i2, "onReceiveAccessToken#", "(");
                o2.append(bundle.getString("error_code"));
                o2.append(") failed retry=");
                o2.append(this.mRetryCount);
                Log.d(str, o2.toString());
                return;
            }
        }
        super.onReceiveAccessToken(i2, z, bundle);
    }
}
