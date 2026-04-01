package com.samsung.scsp.framework.core.network;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Network {
    public static final int DEFAULT_TIMEOUT = 60000;
    public static final String HTTP_STATUS = "HTTP_STATUS";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ErrorListener {
        void onError(HttpRequest httpRequest, Map<String, List<String>> map, int i2, InputStream inputStream);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface StreamListener {
        void onStream(HttpRequest httpRequest, Map<String, List<String>> map, InputStream inputStream);
    }

    void close();

    void close(int i2);

    void delete(HttpRequest httpRequest, StreamListener streamListener);

    void get(HttpRequest httpRequest, StreamListener streamListener);

    void head(HttpRequest httpRequest, StreamListener streamListener);

    void patch(HttpRequest httpRequest, StreamListener streamListener);

    void post(HttpRequest httpRequest, StreamListener streamListener);

    void put(HttpRequest httpRequest, StreamListener streamListener);
}
