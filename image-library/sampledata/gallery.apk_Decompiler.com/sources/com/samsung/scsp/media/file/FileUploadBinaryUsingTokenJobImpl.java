package com.samsung.scsp.media.file;

import com.google.gson.JsonObject;
import com.samsung.scsp.common.ContentType;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.Response;
import com.samsung.scsp.framework.core.api.ResponsiveJob;
import com.samsung.scsp.framework.core.api.SCHashMap;
import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.media.file.FileApiContract;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileUploadBinaryUsingTokenJobImpl extends ResponsiveJob {
    public FileUploadBinaryUsingTokenJobImpl(HttpRequest.Method method, String str, String str2, Class<?> cls) {
        super(method, str, str2, cls);
    }

    public HttpRequest createRequest(ApiContext apiContext, Listeners listeners) {
        Long l = 0L;
        if (apiContext.parameters.containsKey(FileApiContract.Parameter.RANGE_START)) {
            l = apiContext.parameters.getAsLong(FileApiContract.Parameter.RANGE_START);
        }
        File file = new File(apiContext.parameters.getAsString(FileApiContract.Parameter.PATH_TO_UPLOAD));
        return getHttpRequestBuilder(apiContext, apiContext.parameters.getAsString("url")).clearHeader().payload(ContentType.OCTET_STREAM, file).addLength(file.length()).addRange(l.longValue()).responseListener(listeners.responseListener).progressListener(listeners.progressListener).networkStatusListener(listeners.networkStatusListener).build();
    }

    public void onStream(HttpRequest httpRequest, Map<String, List<String>> map, InputStream inputStream) {
        JsonObject json = new Response(inputStream).toJson();
        SCHashMap sCHashMap = new SCHashMap();
        if (json.has("content_range")) {
            sCHashMap.put("content_range", json.get("content_range").getAsString());
        }
        if (json.has("url")) {
            sCHashMap.put("url", json.get("url").getAsString());
        }
        if (json.has("hash")) {
            sCHashMap.put("hash", json.get("hash").getAsString());
        }
        httpRequest.getResponseListener().onResponse(sCHashMap);
    }
}
