package com.samsung.scsp.framework.core.api;

import com.samsung.scsp.common.Header;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.HttpRequestImpl;
import com.samsung.scsp.framework.core.util.StringUtil;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileTransferableJob extends ResponsiveJob {
    private static final int BUFFER_SIZE = 32768;
    private final Map<HttpRequest, FileVo> map = new HashMap();

    public FileTransferableJob(HttpRequest.Method method, String str, String str2) {
        super(method, str, str2, (Class<?>) null);
    }

    public HttpRequest createRequest(ApiContext apiContext, Listeners listeners, String str, String str2) {
        return createRequest(apiContext, listeners, str, str2, 0);
    }

    public void onStream(HttpRequest httpRequest, Map<String, List<String>> map2, InputStream inputStream) {
        long j2;
        try {
            onResponseHeader(httpRequest, map2);
            List list = map2.get(Header.CONTENT_LENGTH);
            if (list != null) {
                j2 = Long.parseLong((String) list.get(0));
            } else {
                j2 = 0;
            }
            FileVo fileVo = this.map.get(httpRequest);
            if (fileVo != null && fileVo.outputStream != null) {
                byte[] bArr = new byte[BUFFER_SIZE];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileVo.outputStream.write(bArr, 0, read);
                    fileVo.transferred += (long) read;
                    if (httpRequest.getProgressListener() != null && j2 > 0) {
                        httpRequest.getProgressListener().onProgress(fileVo.transferred, j2);
                    }
                }
            }
            OutputStream outputStream = this.map.get(httpRequest).outputStream;
            Objects.requireNonNull(outputStream);
            FaultBarrier.run(new a(2, outputStream));
            this.map.remove(httpRequest);
        } catch (IOException e) {
            throw new ScspException(60000000, "IOException occurred while storing the data received from the server in a file.", e);
        } catch (Throwable th) {
            OutputStream outputStream2 = this.map.get(httpRequest).outputStream;
            Objects.requireNonNull(outputStream2);
            FaultBarrier.run(new a(2, outputStream2));
            this.map.remove(httpRequest);
            throw th;
        }
    }

    public void setFile(HttpRequest httpRequest, String str) {
        setFile(httpRequest, str, false);
    }

    public HttpRequest createRequest(ApiContext apiContext, Listeners listeners, String str, String str2, long j2) {
        HttpRequestImpl.HttpRequestBuilder networkStatusListener = getHttpRequestBuilder(apiContext, str).responseListener(listeners.responseListener).progressListener(listeners.progressListener).networkStatusListener(listeners.networkStatusListener);
        int i2 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        if (i2 > 0) {
            networkStatusListener.addRange(j2 - 1);
        }
        if (!StringUtil.isEmpty(apiContext.payload)) {
            networkStatusListener.payload("application/json;charset=UTF-8", apiContext.payload);
        }
        if (!StringUtil.isEmpty(apiContext.invoker)) {
            networkStatusListener.addHeader(Header.INVOKER, apiContext.invoker);
        }
        HttpRequest build = networkStatusListener.build();
        setFile(build, str2, i2 > 0);
        return build;
    }

    public void setFile(HttpRequest httpRequest, String str, boolean z) {
        try {
            this.map.put(httpRequest, new FileVo(str, z));
        } catch (FileNotFoundException e) {
            throw new ScspException(60000000, "There is no file for storing the data received from the server.", e);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FileVo {
        OutputStream outputStream;
        long transferred = 0;

        public FileVo(String str, boolean z) {
            this.outputStream = new FileOutputStream(str, z);
        }

        public FileVo(OutputStream outputStream2) {
            this.outputStream = outputStream2;
        }
    }

    public void setFile(HttpRequest httpRequest, OutputStream outputStream) {
        try {
            this.map.put(httpRequest, new FileVo(outputStream));
        } catch (Throwable th) {
            throw new ScspException(60000000, "There is no file for storing the data received from the server.", th);
        }
    }

    public void onResponseHeader(HttpRequest httpRequest, Map<String, List<String>> map2) {
    }
}
