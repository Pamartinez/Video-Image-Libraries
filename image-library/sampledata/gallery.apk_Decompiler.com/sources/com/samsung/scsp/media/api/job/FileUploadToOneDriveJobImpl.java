package com.samsung.scsp.media.api.job;

import com.samsung.scsp.common.ContentType;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.SimpleJob;
import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.media.file.FileApiContract;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileUploadToOneDriveJobImpl extends SimpleJob {
    public FileUploadToOneDriveJobImpl(HttpRequest.Method method, String str, String str2) {
        super(method, str, str2);
    }

    public HttpRequest createRequest(ApiContext apiContext, Listeners listeners) {
        Long l = 0L;
        Long asLong = apiContext.parameters.getAsLong("size");
        Long asLong2 = apiContext.parameters.getAsLong(FileApiContract.Parameter.LENGTH);
        if (apiContext.parameters.containsKey(FileApiContract.Parameter.RANGE_START)) {
            l = apiContext.parameters.getAsLong(FileApiContract.Parameter.RANGE_START);
        }
        return getHttpRequestBuilder(apiContext, apiContext.parameters.getAsString("url")).clearHeader().payload(ContentType.OCTET_STREAM, new File(apiContext.parameters.getAsString(FileApiContract.Parameter.PATH_TO_UPLOAD))).supportChunkedStreaming(false).addRange(l.longValue(), (asLong2.longValue() + l.longValue()) - 1, asLong.longValue()).addLength(asLong2.longValue()).responseListener(listeners.responseListener).networkStatusListener(listeners.networkStatusListener).build();
    }
}
