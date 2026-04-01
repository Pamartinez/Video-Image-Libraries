package com.samsung.scsp.media.api.job;

import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.Response;
import com.samsung.scsp.framework.core.api.ResponsiveJob;
import com.samsung.scsp.framework.core.api.SCHashMap;
import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import i.C0212a;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneDriveUploadUrlCheckRangeJobImpl extends ResponsiveJob {
    private Logger logger = Logger.get("OneDriveUploadUrlCheckRangeJobImpl");

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OneDriveUploadInfo {
        public String expirationDateTime;
        public String[] nextExpectedRanges;

        private OneDriveUploadInfo() {
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder(C0212a.p(new StringBuilder("expirationDateTime : "), this.expirationDateTime, " / "));
            for (String append : this.nextExpectedRanges) {
                sb2.append("ranges : ");
                sb2.append(append);
                sb2.append(" / ");
            }
            return sb2.toString();
        }
    }

    public OneDriveUploadUrlCheckRangeJobImpl(HttpRequest.Method method, String str, String str2, Class<?> cls) {
        super(method, str, str2, cls);
    }

    public HttpRequest createRequest(ApiContext apiContext, Listeners listeners) {
        return getHttpRequestBuilder(apiContext, apiContext.parameters.getAsString("url")).responseListener(listeners.responseListener).build();
    }

    public void onStream(HttpRequest httpRequest, Map<String, List<String>> map, InputStream inputStream) {
        String str;
        Response response = new Response(inputStream);
        OneDriveUploadInfo oneDriveUploadInfo = (OneDriveUploadInfo) response.create(OneDriveUploadInfo.class);
        SCHashMap sCHashMap = new SCHashMap();
        sCHashMap.put(MediaApiContract.Parameter.EXPIRATION_DATE_TIME, oneDriveUploadInfo.expirationDateTime);
        String[] strArr = oneDriveUploadInfo.nextExpectedRanges;
        if (strArr == null || strArr.length < 1) {
            str = "";
        } else {
            str = strArr[0];
        }
        Logger logger2 = this.logger;
        logger2.i("[onStream] : " + response);
        Logger logger3 = this.logger;
        logger3.i("OneDriveUploadInfo: " + oneDriveUploadInfo);
        sCHashMap.put(MediaApiContract.Parameter.NEXT_EXPECTED_RANGE, str);
        httpRequest.getResponseListener().onResponse(sCHashMap);
    }
}
