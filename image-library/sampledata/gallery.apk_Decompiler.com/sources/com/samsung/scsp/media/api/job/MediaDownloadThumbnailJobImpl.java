package com.samsung.scsp.media.api.job;

import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.FileTransferableJob;
import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.util.UrlUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDownloadThumbnailJobImpl extends FileTransferableJob {
    private static final String API_SUFFIX = "/thumbnail";

    public MediaDownloadThumbnailJobImpl(HttpRequest.Method method, String str, String str2) {
        super(method, str, str2);
    }

    public HttpRequest createRequest(ApiContext apiContext, Listeners listeners) {
        return createRequest(apiContext, listeners, getApiUrl(apiContext), apiContext.parameters.getAsString("filePath"));
    }

    public String getApiUrl(ApiContext apiContext) {
        StringBuilder sb2 = new StringBuilder(super.getApiUrl(apiContext));
        sb2.append(apiContext.parameters.getAsString("photoId"));
        sb2.append("/thumbnail?");
        UrlUtil.addUrlParameter(sb2, "size", apiContext.parameters.getAsString("size"), true);
        return sb2.toString();
    }
}
