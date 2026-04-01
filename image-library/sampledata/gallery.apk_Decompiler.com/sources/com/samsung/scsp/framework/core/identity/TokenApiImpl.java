package com.samsung.scsp.framework.core.identity;

import com.google.gson.JsonObject;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.SContext;
import com.samsung.scsp.framework.core.SContextHolder;
import com.samsung.scsp.framework.core.api.Response;
import com.samsung.scsp.framework.core.ers.ScspErs;
import com.samsung.scsp.framework.core.identity.IdentityApiContract;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.HttpRequestImpl;
import i.C0212a;
import java.io.InputStream;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TokenApiImpl {
    private static final String TAG = "TokenApiImpl";
    private static final String TOKEN_URI = "/identity/v1/tokens";
    private final IdentityHeader identityHeader = new IdentityHeader();
    private final Logger logger = Logger.get(TAG);
    private final SContext scontext;
    private final SContextHolder scontextHolder;

    public TokenApiImpl(SContextHolder sContextHolder) {
        this.scontextHolder = sContextHolder;
        this.scontext = sContextHolder.scontext;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$issue$0(JsonObject jsonObject) {
        return "[onStream] : " + jsonObject.toString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$issue$1(String[] strArr, Map map, HttpRequest httpRequest, Map map2, InputStream inputStream) {
        JsonObject jsonObject = (JsonObject) new Response(inputStream).create(JsonObject.class);
        this.logger.d(new k(jsonObject, 1));
        String asString = jsonObject.get(IdentityApiContract.Parameter.TOKEN_TYPE).getAsString();
        String asString2 = jsonObject.get("accessToken").getAsString();
        long asLong = jsonObject.get(IdentityApiContract.Parameter.EXPIRES_AT).getAsLong();
        strArr[0] = C0212a.B(asString, " ", asString2);
        if (!map.containsKey("x-sc-access-token") || !map.containsKey("x-sc-uid")) {
            ScspCorePreferences.get().deviceToken.accept(strArr[0]);
            ScspCorePreferences.get().deviceTokenExpiredOn.accept(Long.valueOf(asLong * 1000));
            this.logger.i("Success to issue token without account");
            return;
        }
        ScspCorePreferences.get().cloudToken.accept(strArr[0]);
        ScspCorePreferences.get().cloudTokenExpiredOn.accept(Long.valueOf(asLong * 1000));
        this.logger.i("Success to issue token with account");
    }

    public String issue() {
        String p6 = C0212a.p(new StringBuilder(), ScspErs.getDomain(this.scontextHolder.network).playUrl, TOKEN_URI);
        Map<String, String> map = this.identityHeader.get(this.scontextHolder);
        String[] strArr = new String[1];
        this.scontextHolder.network.post(new HttpRequestImpl.HttpRequestBuilder(map, p6, this.scontextHolder.requestTimeOut).name(TAG).addHeader("x-sc-app-version", this.scontext.getAppVersion()).build(), new m(this, strArr, (Map) map));
        return strArr[0];
    }
}
