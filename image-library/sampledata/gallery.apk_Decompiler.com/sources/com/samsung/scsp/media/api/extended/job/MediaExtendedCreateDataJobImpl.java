package com.samsung.scsp.media.api.extended.job;

import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.ResponsiveJob;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.util.UrlUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaExtendedCreateDataJobImpl extends ResponsiveJob {
    public MediaExtendedCreateDataJobImpl(HttpRequest.Method method, String str, String str2, Class<?> cls) {
        super(method, str, str2, cls);
    }

    public String getApiUrl(ApiContext apiContext) {
        StringBuilder sb2 = new StringBuilder(super.getApiUrl(apiContext));
        UrlUtil.addUrlParameter(sb2, "photoId", apiContext.parameters.getAsString("photoId"), true);
        return sb2.toString();
    }
}
