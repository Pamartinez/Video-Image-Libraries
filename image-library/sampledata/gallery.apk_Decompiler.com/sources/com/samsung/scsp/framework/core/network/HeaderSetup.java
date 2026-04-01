package com.samsung.scsp.framework.core.network;

import android.content.Context;
import com.samsung.scsp.common.ContextFactory;
import com.samsung.scsp.framework.core.SContextHolder;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.identity.ScspIdentity;
import com.samsung.scsp.framework.core.util.DeviceUtil;
import com.samsung.scsp.framework.core.util.NetworkUtil;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HeaderSetup {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Key {
        public static final String AUTHORIZATION = "Authorization";
        public static final String MCC = "mcc";
        public static final String MNC = "mnc";
        public static final String MOBILE = "mobile";
        public static final String UNKNOWN = "unknown";
        public static final String USER_AGENT = "User-Agent";
        public static final String WIFI = "wifi";
        public static final String X_SC_APP_ID = "x-sc-app-id";
        public static final String X_SC_DEVICE_CC = "x-sc-device-cc";
        public static final String X_SC_DEVICE_CSC = "x-sc-device-csc";
        public static final String X_SC_DEVICE_MODEL = "x-sc-device-model";
        public static final String X_SC_NETWORK = "x-sc-network";
        public static final String X_SC_NETWORK_MCC = "x-sc-network-mcc";
        public static final String X_SC_NETWORK_MNC = "x-sc-network-mnc";
        public static final String X_SC_OS_TYPE = "x-sc-os-type";
        public static final String X_SC_OS_VERSION = "x-sc-os-version";
    }

    public static Map<String, String> commonHeader(SContextHolder sContextHolder) {
        HashMap hashMap = new HashMap();
        hashMap.put(Key.USER_AGENT, sContextHolder.userAgent);
        String token = ScspIdentity.getToken(sContextHolder.isAccountRequiredFeature);
        if (!token.isEmpty()) {
            hashMap.put(Key.AUTHORIZATION, token);
            return hashMap;
        }
        throw new ScspException(ScspException.Code.RUNTIME_POLICY, "Authorization is invalid.");
    }

    public static Map<String, String> networkHeader() {
        Context applicationContext = ContextFactory.getApplicationContext();
        HashMap hashMap = new HashMap();
        StringBuilder sb2 = new StringBuilder();
        if (NetworkUtil.isWifiOrEthernetConnected(applicationContext)) {
            sb2.append(Key.WIFI);
        } else if (NetworkUtil.isMobileConnected(applicationContext)) {
            sb2.append("mobile,mcc=");
            sb2.append(DeviceUtil.getSimMcc(applicationContext));
            sb2.append(",mnc=");
            sb2.append(DeviceUtil.getSimMnc(applicationContext));
        } else {
            sb2.append("unknown");
        }
        hashMap.put(Key.X_SC_NETWORK, sb2.toString());
        return hashMap;
    }
}
