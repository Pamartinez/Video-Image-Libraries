package com.samsung.scsp.framework.core.listeners;

import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ResponseListener<T> {
    void onResponse(T t);

    void onResponse(T t, Map<String, List<String>> map) {
        onResponse(t);
    }
}
