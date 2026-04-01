package com.samsung.scsp.media.api.job;

import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.ResponsiveJob;
import com.samsung.scsp.framework.core.network.HttpRequest;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDownloadHDScaledVideoJobImpl extends ResponsiveJob {
    public MediaDownloadHDScaledVideoJobImpl(HttpRequest.Method method, String str, String str2, Class<?> cls) {
        super(method, str, str2, cls);
    }

    public String getApiUrl(ApiContext apiContext) {
        StringBuilder sb2 = new StringBuilder(super.getApiUrl(apiContext));
        sb2.append(apiContext.parameters.getAsString("photoId"));
        sb2.append("/hd?redirect=false");
        if (apiContext.parameters.containsKey("caller_package")) {
            sb2.append("&caller_package=");
            sb2.append(apiContext.parameters.getAsString("caller_package"));
        }
        return sb2.toString();
    }
}
