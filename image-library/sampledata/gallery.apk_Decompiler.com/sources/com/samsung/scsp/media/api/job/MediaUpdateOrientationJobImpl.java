package com.samsung.scsp.media.api.job;

import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.ResponsiveJob;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.util.UrlUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaUpdateOrientationJobImpl extends ResponsiveJob {
    private static final String API_SUFFIX = "/setOrientation";

    public MediaUpdateOrientationJobImpl(HttpRequest.Method method, String str, String str2, Class<?> cls) {
        super(method, str, str2, cls);
    }

    public String getApiUrl(ApiContext apiContext) {
        StringBuilder sb2 = new StringBuilder(super.getApiUrl(apiContext));
        sb2.append(apiContext.parameters.getAsString("photoId"));
        sb2.append("/setOrientation?");
        UrlUtil.addUrlParameter(sb2, "orientation", apiContext.parameters.getAsString("orientation"), true);
        UrlUtil.addUrlParameter(sb2, "clientTimestamp", apiContext.parameters.getAsString("clientTimestamp"), false);
        return sb2.toString();
    }
}
