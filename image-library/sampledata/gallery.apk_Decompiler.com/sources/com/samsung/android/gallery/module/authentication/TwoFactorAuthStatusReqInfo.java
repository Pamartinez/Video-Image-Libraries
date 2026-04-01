package com.samsung.android.gallery.module.authentication;

import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.scsp.framework.core.network.HeaderSetup;
import i.C0212a;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TwoFactorAuthStatusReqInfo extends TwoFactorAuthReqInfo {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        static final TwoFactorAuthStatusReqInfo sInstance = new TwoFactorAuthStatusReqInfo();
    }

    public static TwoFactorAuthStatusReqInfo getInstance() {
        return LazyHolder.sInstance;
    }

    public void addHeader(Map<String, String> map) {
        map.put(HeaderSetup.Key.AUTHORIZATION, "Bearer " + getAccessToken());
        map.put("x-osp-appId", getAppId());
        map.put("x-osp-userId", getUserId());
    }

    public /* bridge */ /* synthetic */ Map getHeaderMap() {
        return super.getHeaderMap();
    }

    public String getUrl() {
        String format = String.format(C0212a.p(new StringBuilder("https://"), getApiServerUrl(), "/v2/profile/user/user/%s/2factor/authentication/configuration/list"), new Object[]{getUserId()});
        String str = this.TAG;
        Log.d(str, "getUrl url=" + Logger.getEncodedString(format));
        return format;
    }

    public /* bridge */ /* synthetic */ boolean refreshAccessToken(boolean z) {
        return super.refreshAccessToken(z);
    }
}
