package com.samsung.scsp.framework.core.network;

import com.samsung.scsp.framework.core.api.SCHashMap;
import com.samsung.scsp.framework.core.listeners.NetworkStatusListener;
import com.samsung.scsp.framework.core.listeners.ProgressListener;
import com.samsung.scsp.framework.core.listeners.ResponseListener;
import com.samsung.scsp.framework.core.network.visitor.PayloadWriterVisitor;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface HttpRequest {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Method {
        POST,
        PUT,
        GET,
        DELETE,
        PATCH,
        HEAD
    }

    String getBoundary();

    String getCharset();

    Object getContent(int i2);

    String getContentType(int i2);

    int getHeaderCount();

    String getHeaderKey(int i2);

    String getHeaderValue(int i2);

    long getLength();

    Method getMethod();

    String getName();

    NetworkStatusListener getNetworkStatusListener();

    SCHashMap getParameters();

    int getPartCount();

    Map<String, String> getPartHeaders(int i2);

    PayloadWriterVisitor.PayloadWriter getPayloadWriter(int i2);

    ProgressListener getProgressListener();

    long getRange();

    ResponseListener getResponseListener();

    boolean getSupportChunkedStreaming();

    int getTimeOut();

    String getUrl();

    boolean isMultipart();

    boolean isSilent();

    void setUrl(String str);

    void begin() {
    }

    void end() {
    }

    void error(int i2) {
    }

    HttpRequest journal(String str, String str2) {
        return this;
    }
}
