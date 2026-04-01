package androidx.appsearch.app;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DocumentClassMappingContext {
    public static final DocumentClassMappingContext EMPTY = new DocumentClassMappingContext((Map<String, List<String>>) null, (Map<String, List<String>>) null);
    private final Map<String, List<String>> mDocumentClassMap;
    private final Map<String, List<String>> mParentTypeMap;

    public DocumentClassMappingContext(Map<String, List<String>> map, Map<String, List<String>> map2) {
        Map<String, List<String>> map3;
        Map<String, List<String>> map4;
        if (map != null) {
            map3 = Collections.unmodifiableMap(map);
        } else {
            map3 = Collections.EMPTY_MAP;
        }
        this.mDocumentClassMap = map3;
        if (map2 != null) {
            map4 = Collections.unmodifiableMap(map2);
        } else {
            map4 = Collections.EMPTY_MAP;
        }
        this.mParentTypeMap = map4;
    }

    public Map<String, List<String>> getDocumentClassMap() {
        return this.mDocumentClassMap;
    }

    public Map<String, List<String>> getParentTypeMap() {
        return this.mParentTypeMap;
    }
}
