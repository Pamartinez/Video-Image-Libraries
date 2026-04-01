package com.samsung.scsp.framework.core.network;

import com.samsung.scsp.framework.core.SContextHolder;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HttpRequestMultiPartImpl extends AbstractHttpRequest {
    private final List<DefaultBody> bodyPartList;
    private final String boundary;
    private final String charset;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DefaultBody {
        public Object content;
        public String contentType;
        public Map<String, String> headers;
        /* access modifiers changed from: private */
        public PayloadWriterVisitor.PayloadWriter payloadWriter;

        private DefaultBody() {
            this.headers = new HashMap();
            this.payloadWriter = null;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class HttpRequestMultiPartBuilder {
        private static final String DEFAULT_BOUNDARY = "7d1539170136";
        private static final String DEFAULT_CHARSET = "UTF-8";
        /* access modifiers changed from: private */
        public String boundary = DEFAULT_BOUNDARY;
        /* access modifiers changed from: private */
        public String charset = DEFAULT_CHARSET;
        /* access modifiers changed from: private */
        public final Map<String, String> headerMap;
        /* access modifiers changed from: private */
        public HttpRequest.Method method;
        String name;
        /* access modifiers changed from: private */
        public NetworkStatusListener networkStatusListener;
        /* access modifiers changed from: private */
        public ProgressListener progressListener;
        /* access modifiers changed from: private */
        public ResponseListener responseListener;
        /* access modifiers changed from: private */
        public final int timeout;
        /* access modifiers changed from: private */
        public final String url;

        public HttpRequestMultiPartBuilder(SContextHolder sContextHolder, String str) {
            HashMap hashMap = new HashMap();
            this.headerMap = hashMap;
            this.url = str;
            this.timeout = sContextHolder.requestTimeOut;
            this.method = HttpRequest.Method.POST;
            hashMap.putAll(HeaderSetup.commonHeader(sContextHolder));
        }

        public HttpRequestMultiPartBuilder addHeader(String str, String str2) {
            this.headerMap.put(str, str2);
            return this;
        }

        public HttpRequestMultiPartBuilder boundary(String str) {
            this.boundary = str;
            return this;
        }

        public HttpRequest build() {
            return new HttpRequestMultiPartImpl(this);
        }

        public HttpRequestMultiPartBuilder charset(String str) {
            this.charset = str;
            return this;
        }

        public HttpRequestMultiPartBuilder name(String str) {
            this.name = str;
            return this;
        }

        public HttpRequestMultiPartBuilder networkStatusListener(NetworkStatusListener networkStatusListener2) {
            this.networkStatusListener = networkStatusListener2;
            return this;
        }

        public HttpRequestMultiPartBuilder progressListener(ProgressListener progressListener2) {
            this.progressListener = progressListener2;
            return this;
        }

        public HttpRequestMultiPartBuilder responseListener(ResponseListener responseListener2) {
            this.responseListener = responseListener2;
            return this;
        }
    }

    public void addPart(Map<String, String> map, String str, File file) {
        DefaultBody defaultBody = new DefaultBody();
        defaultBody.headers.putAll(map);
        defaultBody.contentType = str;
        defaultBody.content = file;
        PayloadWriterVisitor.PayloadWriter unused = defaultBody.payloadWriter = new FilePayloadWriter();
        this.bodyPartList.add(defaultBody);
    }

    public String getBoundary() {
        return this.boundary;
    }

    public String getCharset() {
        return this.charset;
    }

    public Object getContent(int i2) {
        return this.bodyPartList.get(i2).content;
    }

    public String getContentType(int i2) {
        return this.bodyPartList.get(i2).contentType;
    }

    public long getLength() {
        return 0;
    }

    public int getPartCount() {
        return this.bodyPartList.size();
    }

    public Map<String, String> getPartHeaders(int i2) {
        return this.bodyPartList.get(i2).headers;
    }

    public PayloadWriterVisitor.PayloadWriter getPayloadWriter(int i2) {
        return this.bodyPartList.get(i2).payloadWriter;
    }

    public long getRange() {
        return 0;
    }

    public boolean getSupportChunkedStreaming() {
        return true;
    }

    public boolean isMultipart() {
        return true;
    }

    private HttpRequestMultiPartImpl(HttpRequestMultiPartBuilder httpRequestMultiPartBuilder) {
        this.bodyPartList = new ArrayList();
        this.url = httpRequestMultiPartBuilder.url;
        this.timeout = httpRequestMultiPartBuilder.timeout;
        this.boundary = httpRequestMultiPartBuilder.boundary;
        this.charset = httpRequestMultiPartBuilder.charset;
        Set<String> keySet = httpRequestMultiPartBuilder.headerMap.keySet();
        this.headerKeyList.clear();
        this.headerValueList.clear();
        for (String str : keySet) {
            this.headerKeyList.add(str);
            this.headerValueList.add((String) httpRequestMultiPartBuilder.headerMap.get(str));
        }
        this.responseListener = httpRequestMultiPartBuilder.responseListener;
        this.progressListener = httpRequestMultiPartBuilder.progressListener;
        this.networkStatusListener = httpRequestMultiPartBuilder.networkStatusListener;
        httpRequestMultiPartBuilder.headerMap.clear();
        this.method = httpRequestMultiPartBuilder.method;
        this.name = httpRequestMultiPartBuilder.name;
    }

    public void addPart(Map<String, String> map, String str, FileInputStream fileInputStream) {
        DefaultBody defaultBody = new DefaultBody();
        defaultBody.headers.putAll(map);
        defaultBody.contentType = str;
        defaultBody.content = fileInputStream;
        PayloadWriterVisitor.PayloadWriter unused = defaultBody.payloadWriter = new FileInputStreamPayloadWriter();
        this.bodyPartList.add(defaultBody);
    }

    public void addPart(Map<String, String> map, String str, String str2) {
        DefaultBody defaultBody = new DefaultBody();
        defaultBody.headers.putAll(map);
        defaultBody.contentType = str;
        defaultBody.content = str2;
        PayloadWriterVisitor.PayloadWriter unused = defaultBody.payloadWriter = new StringPayloadWriter();
        this.bodyPartList.add(defaultBody);
    }
}
