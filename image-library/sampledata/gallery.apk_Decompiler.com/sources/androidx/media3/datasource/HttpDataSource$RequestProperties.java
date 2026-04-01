package androidx.media3.datasource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class HttpDataSource$RequestProperties {
    private final Map<String, String> requestProperties = new HashMap();
    private Map<String, String> requestPropertiesSnapshot;

    public synchronized Map<String, String> getSnapshot() {
        try {
            if (this.requestPropertiesSnapshot == null) {
                this.requestPropertiesSnapshot = Collections.unmodifiableMap(new HashMap(this.requestProperties));
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.requestPropertiesSnapshot;
    }
}
