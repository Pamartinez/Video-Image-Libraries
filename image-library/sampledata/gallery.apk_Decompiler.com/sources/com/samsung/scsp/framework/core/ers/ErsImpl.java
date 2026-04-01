package com.samsung.scsp.framework.core.ers;

import android.content.Context;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.error.Result;
import com.samsung.scsp.framework.core.api.Response;
import com.samsung.scsp.framework.core.network.HeaderSetup;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.HttpRequestImpl;
import com.samsung.scsp.framework.core.network.Network;
import com.samsung.scsp.framework.core.util.DeviceUtil;
import com.samsung.scsp.framework.core.util.StringUtil;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ErsImpl {
    private static final Object LOCK = new Object();
    private static final String PRIMARY_SERVER = "https://ers.samsungcloud.com/ers/v1/endpoints";
    private static final String SECONDARY_SERVER = "https://ers.samsungcloudplatform.com/ers/v1/endpoints";
    private static final int TIMEOUT = 60000;
    private final String TAG = "ScspErs";
    private final Logger logger = Logger.get("ScspErs");

    private void execute(Context context, Network network, String str, String str2) {
        network.get(new HttpRequestImpl.HttpRequestBuilder(getHeaders(context, str), str2, 60000).name("ScspErs").build(), new b(this));
    }

    private Map<String, String> getHeaders(Context context, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(HeaderSetup.Key.X_SC_APP_ID, str);
        hashMap.put(HeaderSetup.Key.X_SC_DEVICE_MODEL, DeviceUtil.getModelName());
        hashMap.put(HeaderSetup.Key.X_SC_OS_TYPE, "android");
        hashMap.put(HeaderSetup.Key.X_SC_OS_VERSION, DeviceUtil.getSDKVersion() + "");
        hashMap.put(HeaderSetup.Key.X_SC_NETWORK_MNC, DeviceUtil.getNetworkMnc(context));
        hashMap.put(HeaderSetup.Key.X_SC_NETWORK_MCC, DeviceUtil.getNetworkMcc(context));
        hashMap.put(HeaderSetup.Key.X_SC_DEVICE_CSC, DeviceUtil.getCsc());
        hashMap.put(HeaderSetup.Key.X_SC_DEVICE_CC, DeviceUtil.getIso3CountryCode());
        hashMap.putAll(HeaderSetup.networkHeader());
        return hashMap;
    }

    private long getMaxAge(Map<String, List<String>> map) {
        return ((Long) FaultBarrier.get(new b(map.get("Cache-Control")), 86400L, true).obj).longValue() * 1000;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$execute$3(DomainVo domainVo) {
        return "[onStream] : defaultUrl : " + domainVo.defaultUrl + ", play : " + domainVo.playUrl;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$execute$4(HttpRequest httpRequest, Map map, InputStream inputStream) {
        DomainVo domainVo = (DomainVo) new Response(inputStream).create(DomainVo.class);
        this.logger.d(new a(domainVo));
        if (!StringUtil.isEmpty(domainVo.defaultUrl) && !StringUtil.isEmpty(domainVo.playUrl)) {
            ErsPreferences ersPreferences = ErsPreferences.get();
            long maxAge = getMaxAge(map);
            ersPreferences.defaultUrl.accept(domainVo.defaultUrl);
            ersPreferences.playUrl.accept(domainVo.playUrl);
            ersPreferences.expiredTime.accept(Long.valueOf(System.currentTimeMillis() + maxAge));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getDomain$0(Context context, Network network, String str) {
        execute(context, network, str, PRIMARY_SERVER);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getDomain$1(Context context, Network network, String str) {
        execute(context, network, str, SECONDARY_SERVER);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getDomain$2(Context context, Network network, String str) {
        synchronized (LOCK) {
            try {
                ErsPreferences ersPreferences = ErsPreferences.get();
                if (ersPreferences.expiredTime.get().longValue() < System.currentTimeMillis()) {
                    Result run = FaultBarrier.run(new c(this, context, network, str, 0));
                    if (!run.success) {
                        this.logger.e("Cannot get ers url from server, So use backup server url");
                        run = FaultBarrier.run(new c(this, context, network, str, 1));
                    }
                    if (!run.success) {
                        ersPreferences.expiredTime.accept(Long.valueOf(System.currentTimeMillis() + 7200000));
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void getDomain(Context context, Network network, String str) {
        Thread thread = new Thread(new d(this, context, network, str));
        thread.start();
        FaultBarrier.run(new b(thread));
    }
}
