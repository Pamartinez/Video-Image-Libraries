package com.samsung.scsp.framework.core.identity;

import com.google.gson.JsonObject;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.SContext;
import com.samsung.scsp.framework.core.SContextHolder;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.api.Response;
import com.samsung.scsp.framework.core.ers.ScspErs;
import com.samsung.scsp.framework.core.identity.IdentityApiContract;
import com.samsung.scsp.framework.core.network.HeaderSetup;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.HttpRequestImpl;
import com.samsung.scsp.framework.core.network.Network;
import com.samsung.scsp.framework.core.util.StringUtil;
import i.C0212a;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class UpdateApiImpl {
    private static final String IDENTITY_V_1_UPDATE = "/identity/v1/update";
    private final String TAG = "UpdateApiImpl";
    private final Logger logger = Logger.get("UpdateApiImpl");
    private final SContext scontext;
    private final SContextHolder scontextHolder;
    private final FaultBarrier.ThrowableFunction<Boolean, String> token;

    public UpdateApiImpl(SContextHolder sContextHolder, FaultBarrier.ThrowableFunction<Boolean, String> throwableFunction) {
        this.scontextHolder = sContextHolder;
        this.scontext = sContextHolder.scontext;
        this.token = throwableFunction;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$update$0(JsonObject jsonObject) {
        return "[onStream] : " + jsonObject;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$update$1(HttpRequest httpRequest, Map map, InputStream inputStream) {
        if (Integer.parseInt((String) ((List) map.get(Network.HTTP_STATUS)).get(0)) != 204) {
            JsonObject jsonObject = (JsonObject) new Response(inputStream).create(JsonObject.class);
            this.logger.d(new k(jsonObject, 2));
            throw new ScspException(jsonObject.get("rcode").getAsInt(), jsonObject.get(Response.RMSG).getAsString());
        }
    }

    private JsonObject makePayload(DeviceInfo deviceInfo) {
        JsonObject jsonObject = new JsonObject();
        if (!StringUtil.isEmpty(deviceInfo.getAlias())) {
            jsonObject.addProperty(IdentityApiContract.Parameter.ALIAS, deviceInfo.getAlias());
        }
        if (!StringUtil.isEmpty(deviceInfo.getOsVersion())) {
            jsonObject.addProperty(IdentityApiContract.Parameter.OS_VERSION, deviceInfo.getOsVersion());
        }
        if (!StringUtil.isEmpty(deviceInfo.getSimMcc())) {
            jsonObject.addProperty(IdentityApiContract.Parameter.SIM_MCC, deviceInfo.getSimMcc());
        }
        if (!StringUtil.isEmpty(deviceInfo.getSimMnc())) {
            jsonObject.addProperty(IdentityApiContract.Parameter.SIM_MNC, deviceInfo.getSimMnc());
        }
        if (!StringUtil.isEmpty(deviceInfo.getPlatformVersion())) {
            jsonObject.addProperty(IdentityApiContract.Parameter.PLATFORM_VERSION, deviceInfo.getPlatformVersion());
        }
        return jsonObject;
    }

    public boolean update(String str) {
        return update(str, (PushInfoList) null, (DeviceInfo) null);
    }

    public boolean update(PushInfoList pushInfoList) {
        return update((String) null, pushInfoList, (DeviceInfo) null);
    }

    public boolean update(DeviceInfo deviceInfo) {
        return update((String) null, (PushInfoList) null, deviceInfo);
    }

    private boolean update(String str, PushInfoList pushInfoList, DeviceInfo deviceInfo) {
        String p6 = C0212a.p(new StringBuilder(), ScspErs.getDomain(this.scontextHolder.network).playUrl, IDENTITY_V_1_UPDATE);
        JsonObject jsonObject = new JsonObject();
        if (!(str == null && pushInfoList == null)) {
            JsonObject jsonObject2 = new JsonObject();
            if (str != null) {
                jsonObject2.addProperty(IdentityApiContract.Parameter.E2EE_TYPE, str);
            }
            if (pushInfoList != null) {
                jsonObject2.add(IdentityApiContract.Parameter.PUSHES, pushInfoList.toJsonArray());
            }
            jsonObject.add(IdentityApiContract.Parameter.APP_PROPERTIES, jsonObject2);
        }
        if (deviceInfo != null && !deviceInfo.isEmpty()) {
            jsonObject.add(IdentityApiContract.Parameter.DEVICE_PROPERTIES, makePayload(deviceInfo));
        }
        HashMap hashMap = new HashMap();
        hashMap.put(HeaderSetup.Key.USER_AGENT, this.scontextHolder.userAgent);
        try {
            hashMap.put(HeaderSetup.Key.AUTHORIZATION, this.token.apply(Boolean.valueOf(this.scontextHolder.isAccountRequiredFeature)));
            this.scontextHolder.network.post(new HttpRequestImpl.HttpRequestBuilder((Map<String, String>) hashMap, p6, this.scontextHolder.requestTimeOut).name(this.TAG).payload("application/json;charset=UTF-8", jsonObject.toString()).build(), new f(3, this));
            return true;
        } catch (Throwable unused) {
            throw new ScspException(ScspException.Code.RUNTIME_POLICY, "Authorization is invalid.");
        }
    }
}
