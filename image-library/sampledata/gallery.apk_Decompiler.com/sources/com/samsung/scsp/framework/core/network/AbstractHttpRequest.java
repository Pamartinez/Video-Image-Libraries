package com.samsung.scsp.framework.core.network;

import com.samsung.scsp.common.Journal;
import com.samsung.scsp.common.JournalFactory;
import com.samsung.scsp.framework.core.api.SCHashMap;
import com.samsung.scsp.framework.core.listeners.NetworkStatusListener;
import com.samsung.scsp.framework.core.listeners.ProgressListener;
import com.samsung.scsp.framework.core.listeners.ResponseListener;
import com.samsung.scsp.framework.core.network.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbstractHttpRequest implements HttpRequest {
    private static final String BOUNDARY = "NO_BOUNDARY";
    private static final String CHARSET = "UTF-8";
    private String from;
    protected final List<String> headerKeyList = new ArrayList();
    protected final List<String> headerValueList = new ArrayList();
    private Journal journal;
    protected HttpRequest.Method method;
    protected String name;
    protected NetworkStatusListener networkStatusListener;
    protected SCHashMap parameters;
    protected ProgressListener progressListener;
    protected ResponseListener responseListener;
    protected boolean silent;
    protected boolean supportChunkedStreaming;
    protected int timeout;
    protected String url;

    public void begin() {
        Journal journal2 = this.journal;
        if (journal2 != null) {
            journal2.begin(this.from, this.name);
        }
    }

    public void end() {
        Journal journal2 = this.journal;
        if (journal2 != null) {
            journal2.end(this.from, this.name);
        }
    }

    public void error(int i2) {
        Journal journal2 = this.journal;
        if (journal2 != null) {
            journal2.error(this.from, this.name, i2);
        }
    }

    public String getBoundary() {
        return BOUNDARY;
    }

    public String getCharset() {
        return CHARSET;
    }

    public int getHeaderCount() {
        return this.headerKeyList.size();
    }

    public String getHeaderKey(int i2) {
        return this.headerKeyList.get(i2);
    }

    public String getHeaderValue(int i2) {
        return this.headerValueList.get(i2);
    }

    public HttpRequest.Method getMethod() {
        return this.method;
    }

    public String getName() {
        return this.name;
    }

    public NetworkStatusListener getNetworkStatusListener() {
        return this.networkStatusListener;
    }

    public SCHashMap getParameters() {
        return this.parameters;
    }

    public Map<String, String> getPartHeaders(int i2) {
        return null;
    }

    public ProgressListener getProgressListener() {
        return this.progressListener;
    }

    public ResponseListener getResponseListener() {
        return this.responseListener;
    }

    public int getTimeOut() {
        return this.timeout;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isSilent() {
        return this.silent;
    }

    public HttpRequest journal(String str, String str2) {
        this.journal = JournalFactory.get(str);
        this.from = str2;
        return this;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
