package com.samsung.android.sdk.mobileservice;

import android.content.Context;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;
import com.samsung.android.sdk.mobileservice.util.SdkLog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeMobileServiceWatchSessionFactory extends SeMobileServiceSessionFactory {
    private static final String TAG = "SeMobileServiceWatchSessionFactory";

    public SeMobileServiceWatchSessionFactory(Context context, SeMobileServiceSession.ConnectionResultCallback connectionResultCallback) {
        super(context, connectionResultCallback);
        this.mServiceList.remove("AuthService");
    }

    public SeMobileServiceSession build() {
        Context context = this.mCtx;
        if (context == null) {
            SdkLog.d(TAG, "context is null");
            return null;
        }
        SeMobileServiceWatchSessionImpl seMobileServiceWatchSessionImpl = new SeMobileServiceWatchSessionImpl(context, this.mServiceList, this.mAppId, this.mConnectionResultCallback);
        SdkLog.d(TAG, "build " + this.mServiceList.toString());
        return seMobileServiceWatchSessionImpl;
    }

    public SeMobileServiceWatchSessionFactory addService(String str) {
        if ("SocialService".equals(str)) {
            this.mServiceList.add(str);
            return this;
        }
        SdkLog.i(TAG, "support only SocialApi - not support " + str);
        return this;
    }
}
