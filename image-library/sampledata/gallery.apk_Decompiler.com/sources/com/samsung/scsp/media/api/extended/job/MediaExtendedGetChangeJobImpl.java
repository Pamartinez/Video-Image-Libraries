package com.samsung.scsp.media.api.extended.job;

import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.ResponsiveJob;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.util.UrlUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaExtendedGetChangeJobImpl extends ResponsiveJob {
    public MediaExtendedGetChangeJobImpl(HttpRequest.Method method, String str, String str2, Class<?> cls) {
        super(method, str, str2, cls);
    }

    public String getApiUrl(ApiContext apiContext) {
        StringBuilder sb2 = new StringBuilder(super.getApiUrl(apiContext));
        if (apiContext.parameters.containsKey("pageToken")) {
            UrlUtil.addUrlParameter(sb2, "pageToken", apiContext.parameters.getAsString("pageToken"), false);
        }
        UrlUtil.addUrlParameter(sb2, "modifiedAfter", apiContext.parameters.getAsString("modifiedAfter"), false);
        if (apiContext.parameters.containsKey("count")) {
            UrlUtil.addUrlParameter(sb2, "count", apiContext.parameters.getAsString("count"), false);
        }
        UrlUtil.addUrlParameter(sb2, "includeDetail", apiContext.parameters.getAsString("includeDetail"), false);
        return sb2.toString();
    }
}
