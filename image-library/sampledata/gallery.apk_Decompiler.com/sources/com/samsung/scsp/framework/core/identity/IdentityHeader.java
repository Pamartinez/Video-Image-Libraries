package com.samsung.scsp.framework.core.identity;

import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.SContextHolder;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.network.HeaderSetup;
import com.samsung.scsp.framework.core.util.StringUtil;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class IdentityHeader {
    private static final String USER_AGENT = "User-Agent";
    static final String X_SC_ACCESS_TOKEN = "x-sc-access-token";
    private static final String X_SC_APP_ID = "x-sc-app-id";
    static final String X_SC_APP_VERSION = "x-sc-app-version";
    private static final String X_SC_CDID = "x-sc-cdid";
    private static final String X_SC_PDID = "x-sc-pdid";
    static final String X_SC_SDID = "x-sc-sdid";
    static final String X_SC_UID = "x-sc-uid";
    private final DeviceId deviceId = new DeviceId();

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$get$0(SContextHolder sContextHolder) {
        return this.deviceId.getPhysicalDeviceId(sContextHolder.scontext.getDeviceIdSupplier());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$get$1(E2eeInfoSupplier e2eeInfoSupplier, Map map) {
        if (!StringUtil.isEmpty(e2eeInfoSupplier.getSakUid())) {
            map.put(X_SC_SDID, e2eeInfoSupplier.getSakUid());
        }
    }

    public Map<String, String> get(SContextHolder sContextHolder) {
        HashMap hashMap = new HashMap();
        AccountInfoSupplier accountInfoSupplier = sContextHolder.scontext.getAccountInfoSupplier();
        hashMap.put("User-Agent", sContextHolder.userAgent);
        hashMap.put("x-sc-app-id", sContextHolder.scontext.getAppId());
        String clientDeviceId = this.deviceId.getClientDeviceId();
        if (!StringUtil.isEmpty(clientDeviceId)) {
            hashMap.put(X_SC_CDID, clientDeviceId);
            String str = (String) FaultBarrier.get(new b(0, this, sContextHolder), null, true).obj;
            if (!StringUtil.isEmpty(str)) {
                hashMap.put(X_SC_PDID, str);
            }
            if (accountInfoSupplier.has()) {
                hashMap.put(X_SC_ACCESS_TOKEN, accountInfoSupplier.getAccessToken());
                hashMap.put(X_SC_UID, accountInfoSupplier.getUserId());
            }
            E2eeInfoSupplier e2eeInfoSupplier = sContextHolder.scontext.getE2eeInfoSupplier();
            if (e2eeInfoSupplier != null) {
                FaultBarrier.run(new b(1, e2eeInfoSupplier, hashMap));
            }
            hashMap.putAll(HeaderSetup.networkHeader());
            return hashMap;
        }
        throw new ScspException(ScspException.Code.RUNTIME_POLICY, "There is no cdid.");
    }
}
