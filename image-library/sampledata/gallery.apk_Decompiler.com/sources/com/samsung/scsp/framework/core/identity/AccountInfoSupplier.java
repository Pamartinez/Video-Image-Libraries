package com.samsung.scsp.framework.core.identity;

import com.samsung.scsp.framework.core.util.StringUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface AccountInfoSupplier {
    String getAccessToken();

    String getUserId();

    boolean has() {
        if (StringUtil.isEmpty(getAccessToken()) || StringUtil.isEmpty(getUserId())) {
            return false;
        }
        return true;
    }

    void onUnauthorized();
}
