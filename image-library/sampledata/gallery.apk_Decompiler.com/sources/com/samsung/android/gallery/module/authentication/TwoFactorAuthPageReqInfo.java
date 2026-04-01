package com.samsung.android.gallery.module.authentication;

import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.RandomNumber;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TwoFactorAuthPageReqInfo extends TwoFactorAuthReqInfo {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        static final TwoFactorAuthPageReqInfo sInstance = new TwoFactorAuthPageReqInfo();
    }

    public static TwoFactorAuthPageReqInfo getInstance() {
        return LazyHolder.sInstance;
    }

    public void addHeader(Map<String, String> map) {
        map.put("x-osp-code", getAccessToken());
    }

    public /* bridge */ /* synthetic */ Map getHeaderMap() {
        return super.getHeaderMap();
    }

    public String getOpaqueValue() {
        StringBuilder sb2 = new StringBuilder();
        for (int i2 = 0; i2 < 16; i2++) {
            sb2.append(RandomNumber.nextInt(10));
        }
        return sb2.toString();
    }

    public String getSaApiUrl() {
        try {
            String substring = getApiServerUrl().substring(0, 2);
            int hashCode = substring.hashCode();
            if (hashCode != 3248) {
                if (hashCode == 3742) {
                    if (substring.equals("us")) {
                        return substring.concat(".account.samsung.com");
                    }
                }
            } else if (substring.equals("eu")) {
                return "account.samsung.com";
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public String getUrl() {
        String appendArgs = ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs("https://" + getSaApiUrl() + "/accounts/dfltMobileHybrid/set2FactorAuthGate", "clientId", "22n6hzkam0"), "state", getOpaqueValue());
        String str = this.TAG;
        Log.d(str, "getUrl url=" + Logger.getEncodedString(appendArgs));
        return appendArgs;
    }

    public /* bridge */ /* synthetic */ boolean refreshAccessToken(boolean z) {
        return super.refreshAccessToken(z);
    }
}
