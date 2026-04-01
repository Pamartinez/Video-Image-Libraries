package com.samsung.scsp.framework.core.listeners;

import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ListenersHolder<T> {
    /* access modifiers changed from: private */
    public Map<String, List<String>> headers;
    private final Listeners listeners;
    /* access modifiers changed from: private */
    public T result;

    private ListenersHolder(NetworkStatusListener networkStatusListener, ProgressListener progressListener) {
        Listeners listeners2 = new Listeners();
        this.listeners = listeners2;
        listeners2.networkStatusListener = networkStatusListener;
        listeners2.progressListener = progressListener;
        listeners2.responseListener = new ResponseListener<T>() {
            public void onResponse(T t) {
                Object unused = ListenersHolder.this.result = t;
            }

            public void onResponse(T t, Map<String, List<String>> map) {
                Object unused = ListenersHolder.this.result = t;
                Map unused2 = ListenersHolder.this.headers = map;
            }
        };
    }

    public static <T> ListenersHolder<T> create() {
        return new ListenersHolder<>((NetworkStatusListener) null, (ProgressListener) null);
    }

    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    public Listeners getListeners() {
        return this.listeners;
    }

    public T getResult() {
        return this.result;
    }

    public static <T> ListenersHolder<T> create(NetworkStatusListener networkStatusListener, ProgressListener progressListener) {
        return new ListenersHolder<>(networkStatusListener, progressListener);
    }
}
