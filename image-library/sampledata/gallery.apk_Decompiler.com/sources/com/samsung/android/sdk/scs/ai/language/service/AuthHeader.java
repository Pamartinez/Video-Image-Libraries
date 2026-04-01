package com.samsung.android.sdk.scs.ai.language.service;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AuthHeader {
    private static final String TAG = "AuthHeader";
    private final AppInfo appInfo;

    public AuthHeader(AppInfo appInfo2) {
        this.appInfo = appInfo2;
    }

    private String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int i2 = applicationInfo.labelRes;
        if (i2 == 0) {
            return applicationInfo.nonLocalizedLabel.toString();
        }
        return context.getString(i2);
    }

    public Map<String, String> generateHeaderMap(Context context) {
        HashMap hashMap = new HashMap();
        AppInfo appInfo2 = this.appInfo;
        if (appInfo2 != null) {
            hashMap.put("api-key", appInfo2.getApiKey());
            hashMap.put("package-signing-key", this.appInfo.getSigningKey());
            hashMap.put("ssp-app-id", this.appInfo.getAppId());
            hashMap.put("ssp-access-token", this.appInfo.getAccessToken());
            hashMap.put("ssp-user-id", this.appInfo.getUserId());
            hashMap.put("ssp-server-url", this.appInfo.getServerUrl());
            hashMap.put("ssp-account-type", this.appInfo.getAccountType());
            hashMap.put("request-type", this.appInfo.getRequestType().name());
            hashMap.put("streaming-mode", Boolean.toString(this.appInfo.isStreamingMode()));
        }
        hashMap.put("package-name", context.getPackageName());
        Log.i(TAG, "SCS SDK VERSION: 4.0.53");
        return hashMap;
    }
}
