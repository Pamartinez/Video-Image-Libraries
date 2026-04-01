package com.samsung.scsp.framework.core.identity;

import android.content.Context;
import android.os.Build;
import com.google.gson.JsonObject;
import com.samsung.scsp.common.ContextFactory;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.SContext;
import com.samsung.scsp.framework.core.SContextHolder;
import com.samsung.scsp.framework.core.api.Response;
import com.samsung.scsp.framework.core.ers.ErsPreferences;
import com.samsung.scsp.framework.core.ers.ScspErs;
import com.samsung.scsp.framework.core.identity.IdentityApiContract;
import com.samsung.scsp.framework.core.network.HeaderSetup;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.HttpRequestImpl;
import com.samsung.scsp.framework.core.network.Network;
import com.samsung.scsp.framework.core.util.DeviceUtil;
import com.samsung.scsp.framework.core.util.StringUtil;
import i.C0212a;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RegistrationApiImpl {
    private static final String BASE_URI = "/identity/v1";
    private static final String DEREGISTER_URI = "/identity/v1/deregister";
    private static final String REGISTER_URI = "/identity/v1/register";
    private static final String TAG = "RegistrationApiImpl";
    private final IdentityHeader identityHeader = new IdentityHeader();
    private final Logger logger = Logger.get(TAG);
    private final SContext scontext;
    private final SContextHolder scontextHolder;

    public RegistrationApiImpl(SContextHolder sContextHolder) {
        this.scontextHolder = sContextHolder;
        this.scontext = sContextHolder.scontext;
    }

    private String getDeviceType() {
        if (DeviceUtil.isWatch(ContextFactory.getApplicationContext())) {
            return "watch";
        }
        if (DeviceUtil.isVst(ContextFactory.getApplicationContext())) {
            return "vst";
        }
        if (DeviceUtil.isTablet()) {
            return "tablet";
        }
        return "phone";
    }

    private String getOsType() {
        if (DeviceUtil.isWatch(ContextFactory.getApplicationContext())) {
            return "wearos";
        }
        return "android";
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$deregister$5(HttpRequest httpRequest, Map map, InputStream inputStream) {
        this.logger.i("Success deregister");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$makePayload$6(E2eeInfoSupplier e2eeInfoSupplier) {
        return E2eeInfoSupplier.TYPES[e2eeInfoSupplier.getType()];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$register$1(JsonObject jsonObject) {
        return "[onStream] : " + jsonObject.toString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$register$2(InputStream inputStream) {
        JsonObject jsonObject = (JsonObject) new Response(inputStream).create(JsonObject.class);
        this.logger.d(new k(jsonObject, 0));
        ScspCorePreferences.get().registrationId.accept(jsonObject.get("registrationId").getAsString());
        if (jsonObject.has("dvcId")) {
            ScspCorePreferences.get().dvcId.accept(jsonObject.get("dvcId").getAsString());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$register$4(Map map, JsonObject jsonObject, HttpRequest httpRequest, Map map2, InputStream inputStream) {
        if (Integer.parseInt((String) ((List) map2.get(Network.HTTP_STATUS)).get(0)) == 200) {
            FaultBarrier.run(new b(4, this, inputStream), true);
            if (map.containsKey("x-sc-sdid")) {
                ScspCorePreferences.get().sakUid.accept((String) map.get("x-sc-sdid"));
            }
            if (!map.containsKey("x-sc-access-token") || !map.containsKey("x-sc-uid")) {
                ScspCorePreferences.get().isDeviceRegistered.accept(Boolean.TRUE);
                this.logger.i("Success to register without account");
            } else {
                ScspCorePreferences.get().isAccountRegistered.accept(Boolean.TRUE);
                ScspCorePreferences.get().hashedUid.accept((String) FaultBarrier.get(new f(1, map), "").obj);
                this.logger.i("Success to register with account, remove cloudToken");
            }
            JsonObject asJsonObject = jsonObject.getAsJsonObject(IdentityApiContract.Parameter.APP);
            ScspCorePreferences.get().appVersion.accept(asJsonObject.get("version").getAsString());
            if (asJsonObject.has(IdentityApiContract.Parameter.PUSHES)) {
                ScspCorePreferences.get().pushInfos.accept(asJsonObject.getAsJsonArray(IdentityApiContract.Parameter.PUSHES).toString());
            }
            if (asJsonObject.has(IdentityApiContract.Parameter.E2EE_TYPE)) {
                ScspCorePreferences.get().e2eeType.accept(asJsonObject.get(IdentityApiContract.Parameter.E2EE_TYPE).getAsString());
            }
            JsonObject asJsonObject2 = jsonObject.getAsJsonObject(IdentityApiContract.Parameter.DEVICE);
            ScspCorePreferences.get().osVersion.accept(asJsonObject2.get(IdentityApiContract.Parameter.OS_VERSION).getAsString());
            ScspCorePreferences.get().deviceAlias.accept(asJsonObject2.get(IdentityApiContract.Parameter.ALIAS).getAsString());
            ScspCorePreferences.get().platformVersion.accept(asJsonObject2.get(IdentityApiContract.Parameter.PLATFORM_VERSION).getAsString());
            if (asJsonObject2.has(IdentityApiContract.Parameter.SIM_MCC)) {
                ScspCorePreferences.get().simMcc.accept(asJsonObject2.get(IdentityApiContract.Parameter.SIM_MCC).getAsString());
            }
            if (asJsonObject2.has(IdentityApiContract.Parameter.SIM_MNC)) {
                ScspCorePreferences.get().simMnc.accept(asJsonObject2.get(IdentityApiContract.Parameter.SIM_MNC).getAsString());
            }
        }
    }

    private JsonObject makePayload() {
        Context applicationContext = ContextFactory.getApplicationContext();
        JsonObject jsonObject = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("version", this.scontext.getAppVersion());
        E2eeInfoSupplier e2eeInfoSupplier = this.scontext.getE2eeInfoSupplier();
        if (e2eeInfoSupplier != null && !StringUtil.isEmpty(e2eeInfoSupplier.getSakUid())) {
            jsonObject2.addProperty(IdentityApiContract.Parameter.E2EE_TYPE, (String) FaultBarrier.get(new e(e2eeInfoSupplier, 1), "non").obj);
        }
        PushInfoSupplier pushInfoSupplier = this.scontext.getPushInfoSupplier();
        AccountInfoSupplier accountInfoSupplier = this.scontext.getAccountInfoSupplier();
        if (pushInfoSupplier != null && pushInfoSupplier.has()) {
            if (accountInfoSupplier == null || !accountInfoSupplier.has()) {
                this.logger.e("Skipping push registration because there is no account information in AccountInfoSupplier");
            } else {
                jsonObject2.add(IdentityApiContract.Parameter.PUSHES, new PushInfoList(pushInfoSupplier.getPushInfo()).toJsonArray());
            }
        }
        jsonObject.add(IdentityApiContract.Parameter.APP, jsonObject2);
        JsonObject jsonObject3 = new JsonObject();
        jsonObject3.addProperty(IdentityApiContract.Parameter.OS_TYPE, getOsType());
        jsonObject3.addProperty(IdentityApiContract.Parameter.OS_VERSION, Integer.toString(Build.VERSION.SDK_INT));
        jsonObject3.addProperty(IdentityApiContract.Parameter.PLATFORM_VERSION, DeviceUtil.getOneUiVersion());
        jsonObject3.addProperty("type", getDeviceType());
        jsonObject3.addProperty("countryCode", DeviceUtil.getIso3CountryCode());
        jsonObject3.addProperty(IdentityApiContract.Parameter.MODEL_NAME, DeviceUtil.getModelName());
        jsonObject3.addProperty(IdentityApiContract.Parameter.ALIAS, DeviceUtil.getDeviceName(applicationContext));
        jsonObject3.addProperty(IdentityApiContract.Parameter.OS_USER_ID, Integer.toString(DeviceUtil.getUserId()));
        String modelCode = DeviceUtil.getModelCode();
        if (!StringUtil.isEmpty(modelCode)) {
            jsonObject3.addProperty(IdentityApiContract.Parameter.MODEL_CODE, modelCode);
        }
        String simMcc = DeviceUtil.getSimMcc(applicationContext);
        if (!StringUtil.isEmpty(simMcc)) {
            jsonObject3.addProperty(IdentityApiContract.Parameter.SIM_MCC, simMcc);
        }
        String simMnc = DeviceUtil.getSimMnc(applicationContext);
        if (!StringUtil.isEmpty(simMnc)) {
            jsonObject3.addProperty(IdentityApiContract.Parameter.SIM_MNC, simMnc);
        }
        String csc = DeviceUtil.getCsc();
        if (!StringUtil.isEmpty(csc)) {
            jsonObject3.addProperty(IdentityApiContract.Parameter.CSC, csc);
        }
        jsonObject.add(IdentityApiContract.Parameter.DEVICE, jsonObject3);
        return jsonObject;
    }

    public void deregister(String str) {
        String str2 = ErsPreferences.get().playUrl.get();
        if (!StringUtil.isEmpty(str2)) {
            String A10 = C0212a.A(str2, DEREGISTER_URI);
            HashMap hashMap = new HashMap();
            hashMap.put(HeaderSetup.Key.AUTHORIZATION, str);
            hashMap.put(HeaderSetup.Key.USER_AGENT, this.scontextHolder.userAgent);
            this.scontextHolder.network.post(new HttpRequestImpl.HttpRequestBuilder((Map<String, String>) hashMap, A10, this.scontextHolder.requestTimeOut).name(TAG).silent().build(), new j(this));
        }
    }

    public void register() {
        String p6 = C0212a.p(new StringBuilder(), ScspErs.getDomain(this.scontextHolder.network).playUrl, REGISTER_URI);
        JsonObject makePayload = makePayload();
        String jsonElement = makePayload.toString();
        this.logger.d(new l(jsonElement, 0));
        Map<String, String> map = this.identityHeader.get(this.scontextHolder);
        this.scontextHolder.network.post(new HttpRequestImpl.HttpRequestBuilder(map, p6, this.scontextHolder.requestTimeOut).name(TAG).payload("application/json;charset=UTF-8", jsonElement).build(), new m(this, (Map) map, makePayload));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$register$0(String str) {
        return str;
    }
}
