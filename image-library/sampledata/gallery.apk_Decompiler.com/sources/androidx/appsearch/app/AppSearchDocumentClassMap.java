package androidx.appsearch.app;

import android.util.Log;
import androidx.collection.ArrayMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AppSearchDocumentClassMap {
    private static volatile Map<String, Class<?>> sCachedAppSearchClasses = new ArrayMap();
    private static final Object sLock = new Object();

    private static Class<?> getAppSearchDocumentClass(String str) {
        Class<?> cls;
        Object obj = sLock;
        synchronized (obj) {
            cls = sCachedAppSearchClasses.get(str);
        }
        if (cls != null) {
            return cls;
        }
        Class<?> cls2 = Class.forName(str);
        synchronized (obj) {
            sCachedAppSearchClasses.put(str, cls2);
        }
        return cls2;
    }

    public static <T> Class<? extends T> getAssignableClassBySchemaName(Map<String, List<String>> map, String str, Class<T> cls) {
        List list = map.get(str);
        if (list == null) {
            return null;
        }
        int i2 = 0;
        while (i2 < list.size()) {
            String str2 = (String) list.get(i2);
            try {
                Class<?> appSearchDocumentClass = getAppSearchDocumentClass(str2);
                if (cls.isAssignableFrom(appSearchDocumentClass)) {
                    return appSearchDocumentClass.asSubclass(cls);
                }
                i2++;
            } catch (ClassNotFoundException unused) {
                Log.w("AppSearchDocumentClassM", "Failed to load document class \"" + str2 + "\". Perhaps the class was proguarded out?");
            }
        }
        return null;
    }
}
