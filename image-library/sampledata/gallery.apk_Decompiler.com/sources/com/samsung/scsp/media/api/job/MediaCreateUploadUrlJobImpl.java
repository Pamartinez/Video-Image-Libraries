package com.samsung.scsp.media.api.job;

import J5.c;
import com.google.gson.JsonObject;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.SContext;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.Response;
import com.samsung.scsp.framework.core.api.ResponsiveJob;
import com.samsung.scsp.framework.core.api.SCHashMap;
import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.Network;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import com.samsung.scsp.media.file.FileApiContract;
import com.samsung.scsp.media.nde.NDEApiHelper;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaCreateUploadUrlJobImpl extends ResponsiveJob {
    private static final String TAG = "MediaCreateUploadUrlJobImpl";

    public MediaCreateUploadUrlJobImpl(HttpRequest.Method method, String str, String str2, Class<?> cls) {
        super(method, str, str2, cls);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$onStream$0(Response response) {
        return "[onStream] : " + response;
    }

    public HttpRequest createRequest(ApiContext apiContext, Listeners listeners) {
        StringBuilder sb2 = new StringBuilder(getApiUrl(apiContext));
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("hash", apiContext.parameters.getAsString("hash"));
        jsonObject.addProperty(MediaApiContract.Parameter.VALIDATION_KEY, apiContext.parameters.getAsString(FileApiContract.Parameter.VALIDATION_KEY));
        jsonObject.addProperty("size", (Number) apiContext.parameters.getAsLong("size"));
        jsonObject.addProperty(FileApiContract.Parameter.MIME_TYPE, apiContext.parameters.getAsString(FileApiContract.Parameter.CONTENT_TYPE));
        jsonObject.addProperty(FileApiContract.Parameter.PATH, apiContext.parameters.getAsString(FileApiContract.Parameter.PATH_TO_UPLOAD));
        new NDEApiHelper(SContext.getInstance()).handleCreateUploadUrlRequest(apiContext, jsonObject);
        return getHttpRequestBuilder(apiContext, sb2.toString()).payload("application/json;charset=UTF-8", jsonObject.toString()).responseListener(listeners.responseListener).networkStatusListener(listeners.networkStatusListener).parameters(apiContext.parameters).build();
    }

    public void onStream(HttpRequest httpRequest, Map<String, List<String>> map, InputStream inputStream) {
        Response response = new Response(inputStream);
        Logger.get(TAG).d(new c(24, response));
        SCHashMap sCHashMap = new SCHashMap();
        if (((String) map.get(Network.HTTP_STATUS).get(0)).equals("204")) {
            sCHashMap.put("rcode", 204);
        } else {
            JsonObject json = response.toJson();
            if (json.has("url")) {
                sCHashMap.put("url", json.get("url").getAsString());
            }
            if (json.has("location")) {
                sCHashMap.put("location", json.get("location").getAsString());
            }
            new NDEApiHelper(SContext.getInstance()).handleCreateUploadUrlResponse(httpRequest, json, sCHashMap);
        }
        httpRequest.getResponseListener().onResponse(sCHashMap);
    }
}
