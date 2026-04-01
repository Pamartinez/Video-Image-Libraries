package com.samsung.scsp.media.file;

import android.os.ParcelFileDescriptor;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.FileTransferableJob;
import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.network.HttpRequest;
import java.io.FileOutputStream;
import java.io.OutputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadBinaryToSignedUrlJobImpl extends FileTransferableJob {
    public DownloadBinaryToSignedUrlJobImpl(HttpRequest.Method method, String str, String str2) {
        super(method, str, str2);
    }

    public HttpRequest createRequest(ApiContext apiContext, Listeners listeners) {
        HttpRequest build = getHttpRequestBuilder(apiContext, apiContext.parameters.getAsString("url")).clearHeader().responseListener(listeners.responseListener).progressListener(listeners.progressListener).networkStatusListener(listeners.networkStatusListener).build();
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) apiContext.parameters.get("fileDescriptor");
        if (parcelFileDescriptor != null) {
            setFile(build, (OutputStream) new FileOutputStream(parcelFileDescriptor.getFileDescriptor()));
            return build;
        }
        setFile(build, apiContext.parameters.getAsString("filePath"));
        return build;
    }
}
