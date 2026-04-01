package com.samsung.scsp.framework.core.network;

import A.a;
import N2.j;
import com.samsung.scsp.common.ContentType;
import com.samsung.scsp.common.Header;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.SContextHolder;
import com.samsung.scsp.framework.core.api.SCHashMap;
import com.samsung.scsp.framework.core.listeners.NetworkStatusListener;
import com.samsung.scsp.framework.core.listeners.ProgressListener;
import com.samsung.scsp.framework.core.listeners.ResponseListener;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.visitor.FileInputStreamPayloadWriter;
import com.samsung.scsp.framework.core.network.visitor.FilePayloadWriter;
import com.samsung.scsp.framework.core.network.visitor.PayloadWriterVisitor;
import com.samsung.scsp.framework.core.network.visitor.StringPayloadWriter;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class HttpRequestImpl extends AbstractHttpRequest {
    private final Object content;
    private final String contentType;
    private long length;
    private PayloadWriterVisitor.PayloadWriter payloadWriter;
    private long range;

    public Object getContent(int i2) {
        return this.content;
    }

    public String getContentType(int i2) {
        return this.contentType;
    }

    public long getLength() {
        return this.length;
    }

    public int getPartCount() {
        return 1;
    }

    public PayloadWriterVisitor.PayloadWriter getPayloadWriter(int i2) {
        return this.payloadWriter;
    }

    public long getRange() {
        return this.range;
    }

    public boolean getSupportChunkedStreaming() {
        return this.supportChunkedStreaming;
    }

    public boolean isMultipart() {
        return false;
    }

    private HttpRequestImpl(HttpRequestBuilder httpRequestBuilder) {
        this.range = 0;
        this.length = 0;
        this.payloadWriter = null;
        this.url = httpRequestBuilder.url;
        this.timeout = httpRequestBuilder.timeout;
        this.contentType = httpRequestBuilder.contentType;
        this.content = httpRequestBuilder.content;
        this.payloadWriter = httpRequestBuilder.payloadWriter;
        Set<String> keySet = httpRequestBuilder.headerMap.keySet();
        this.headerKeyList.clear();
        this.headerValueList.clear();
        for (String str : keySet) {
            this.headerKeyList.add(str);
            this.headerValueList.add((String) httpRequestBuilder.headerMap.get(str));
        }
        this.method = httpRequestBuilder.method;
        this.range = httpRequestBuilder.range;
        this.length = httpRequestBuilder.length;
        this.responseListener = httpRequestBuilder.responseListener;
        this.progressListener = httpRequestBuilder.progressListener;
        this.networkStatusListener = httpRequestBuilder.networkStatusListener;
        this.name = httpRequestBuilder.name;
        this.supportChunkedStreaming = httpRequestBuilder.supportChunkedStreaming;
        this.silent = httpRequestBuilder.silent;
        this.parameters = httpRequestBuilder.parameters;
        httpRequestBuilder.headerMap.clear();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class HttpRequestBuilder {
        /* access modifiers changed from: private */
        public Object content;
        /* access modifiers changed from: private */
        public String contentType;
        /* access modifiers changed from: private */
        public final Map<String, String> headerMap;
        /* access modifiers changed from: private */
        public long length = 0;
        /* access modifiers changed from: private */
        public HttpRequest.Method method;
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public NetworkStatusListener networkStatusListener;
        /* access modifiers changed from: private */
        public SCHashMap parameters;
        /* access modifiers changed from: private */
        public PayloadWriterVisitor.PayloadWriter payloadWriter = null;
        /* access modifiers changed from: private */
        public ProgressListener progressListener;
        /* access modifiers changed from: private */
        public long range = 0;
        /* access modifiers changed from: private */
        public ResponseListener responseListener;
        /* access modifiers changed from: private */
        public boolean silent = false;
        /* access modifiers changed from: private */
        public boolean supportChunkedStreaming = true;
        /* access modifiers changed from: private */
        public int timeout;
        /* access modifiers changed from: private */
        public final String url;

        public HttpRequestBuilder(SContextHolder sContextHolder, HttpRequest.Method method2, String str) {
            HashMap hashMap = new HashMap();
            this.headerMap = hashMap;
            this.url = str;
            this.timeout = sContextHolder.requestTimeOut;
            this.method = method2;
            hashMap.putAll(HeaderSetup.commonHeader(sContextHolder));
            FaultBarrier.run(new a(2, this, str));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0(String str) {
            if (new URL(str).getHost().contains("samsungcloud")) {
                this.headerMap.putAll(HeaderSetup.networkHeader());
            }
        }

        public HttpRequestBuilder addHeader(String str, String str2) {
            this.headerMap.put(str, str2);
            return this;
        }

        public HttpRequestBuilder addHeaderMap(Map<String, String> map) {
            this.headerMap.putAll(map);
            return this;
        }

        public HttpRequestBuilder addLength(long j2) {
            addHeader(Header.CONTENT_LENGTH, String.valueOf(j2));
            this.length = j2;
            return this;
        }

        public HttpRequestBuilder addRange(long j2) {
            addHeader(Header.RANGE, "bytes=0-");
            if (j2 > 0) {
                j2++;
                addHeader(Header.RANGE, a.e(j2, "bytes=", "-"));
            }
            this.range = j2;
            return this;
        }

        public HttpRequest build() {
            return new HttpRequestImpl(this);
        }

        public HttpRequestBuilder clearHeader() {
            this.headerMap.clear();
            return this;
        }

        public HttpRequestBuilder name(String str) {
            this.name = str;
            return this;
        }

        public HttpRequestBuilder networkStatusListener(NetworkStatusListener networkStatusListener2) {
            this.networkStatusListener = networkStatusListener2;
            return this;
        }

        public HttpRequestBuilder parameters(SCHashMap sCHashMap) {
            this.parameters = sCHashMap;
            return this;
        }

        public HttpRequestBuilder payload(String str, String str2) {
            addHeader(ContentType.KEY, str);
            this.contentType = str;
            this.content = str2;
            this.payloadWriter = new StringPayloadWriter();
            return this;
        }

        public HttpRequestBuilder progressListener(ProgressListener progressListener2) {
            this.progressListener = progressListener2;
            return this;
        }

        public HttpRequestBuilder removeHeader(String str) {
            this.headerMap.remove(str);
            return this;
        }

        public HttpRequestBuilder requestTimeOut(int i2) {
            this.timeout = i2;
            return this;
        }

        public HttpRequestBuilder responseListener(ResponseListener responseListener2) {
            this.responseListener = responseListener2;
            return this;
        }

        public HttpRequestBuilder silent() {
            this.silent = true;
            return this;
        }

        public HttpRequestBuilder supportChunkedStreaming(boolean z) {
            this.supportChunkedStreaming = z;
            return this;
        }

        public HttpRequestBuilder payload(String str, File file) {
            addHeader(ContentType.KEY, str);
            this.contentType = str;
            this.content = file;
            this.payloadWriter = new FilePayloadWriter();
            return this;
        }

        public HttpRequestBuilder payload(String str, FileInputStream fileInputStream) {
            addHeader(ContentType.KEY, str);
            this.contentType = str;
            this.content = fileInputStream;
            this.payloadWriter = new FileInputStreamPayloadWriter();
            return this;
        }

        public HttpRequestBuilder addRange(long j2, long j3, long j8) {
            StringBuilder j10 = j.j(j2, "bytes ", "-");
            j10.append(j3);
            j10.append("/");
            j10.append(j8);
            addHeader(Header.CONTENT_RANGE, j10.toString());
            this.range = j2;
            return this;
        }

        public HttpRequestBuilder(Map<String, String> map, String str, int i2) {
            HashMap hashMap = new HashMap();
            this.headerMap = hashMap;
            this.url = str;
            this.timeout = i2;
            hashMap.putAll(map);
        }
    }
}
