package com.samsung.android.gallery.module.authentication;

import A.a;
import com.samsung.android.gallery.module.cloud.sdk.SamsungAccountInfo;
import com.samsung.android.gallery.module.cloud.sdk.SamsungCloudSdk;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class TwoFactorAuthReqInfo {
    protected final String TAG = getClass().getSimpleName();
    private final Map<String, String> mHeaderMap = new HashMap();
    private SamsungAccountInfo mSamsungAccountInfo;

    public abstract void addHeader(Map<String, String> map);

    public final String getAccessToken() {
        return this.mSamsungAccountInfo.accessToken;
    }

    public final String getApiServerUrl() {
        return this.mSamsungAccountInfo.apiServerUrl;
    }

    public final String getAppId() {
        return "22n6hzkam0";
    }

    public Map<String, String> getHeaderMap() {
        if (this.mHeaderMap.isEmpty()) {
            addHeader(this.mHeaderMap);
        }
        return this.mHeaderMap;
    }

    public final String getUserId() {
        return this.mSamsungAccountInfo.userId;
    }

    public boolean refreshAccessToken(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        SamsungAccountInfo samsungAccountInfo = SamsungCloudSdk.getSamsungAccountInfo(AppResources.getAppContext(), z);
        this.mSamsungAccountInfo = samsungAccountInfo;
        if (samsungAccountInfo == null) {
            Log.e(this.TAG, "refreshAccessToken failed. samsung account info is null.");
            return false;
        }
        if (z) {
            this.mHeaderMap.clear();
        }
        a.x(new StringBuilder("refreshAccessToken time = "), currentTimeMillis, this.TAG);
        return true;
    }
}
