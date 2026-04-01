package ne;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.j;
import me.i;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class z extends k {
    public static Object Y(Object obj, Map map) {
        j.e(map, "<this>");
        Object obj2 = map.get(obj);
        if (obj2 != null || map.containsKey(obj)) {
            return obj2;
        }
        throw new NoSuchElementException("Key " + obj + " is missing in the map.");
    }

    public static int Z(int i2) {
        if (i2 < 0) {
            return i2;
        }
        if (i2 < 3) {
            return i2 + 1;
        }
        if (i2 < 1073741824) {
            return (int) ((((float) i2) / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static Map a0(i iVar) {
        j.e(iVar, "pair");
        Map singletonMap = Collections.singletonMap(iVar.d, iVar.e);
        j.d(singletonMap, "singletonMap(...)");
        return singletonMap;
    }

    public static Map b0(i... iVarArr) {
        if (iVarArr.length <= 0) {
            return C1203u.d;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(Z(iVarArr.length));
        d0(linkedHashMap, iVarArr);
        return linkedHashMap;
    }

    public static LinkedHashMap c0(Map map, Map map2) {
        j.e(map, "<this>");
        j.e(map2, "map");
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.putAll(map2);
        return linkedHashMap;
    }

    public static final void d0(HashMap hashMap, i[] iVarArr) {
        for (i iVar : iVarArr) {
            hashMap.put(iVar.d, iVar.e);
        }
    }

    public static Map e0(Iterable iterable) {
        Object next;
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size != 0) {
                if (size != 1) {
                    LinkedHashMap linkedHashMap = new LinkedHashMap(Z(collection.size()));
                    g0(iterable, linkedHashMap);
                    return linkedHashMap;
                }
                if (iterable instanceof List) {
                    next = ((List) iterable).get(0);
                } else {
                    next = collection.iterator().next();
                }
                return a0((i) next);
            }
        } else {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            g0(iterable, linkedHashMap2);
            int size2 = linkedHashMap2.size();
            if (size2 != 0) {
                if (size2 != 1) {
                    return linkedHashMap2;
                }
                return h0(linkedHashMap2);
            }
        }
        return C1203u.d;
    }

    public static Map f0(Map map) {
        j.e(map, "<this>");
        int size = map.size();
        if (size == 0) {
            return C1203u.d;
        }
        if (size != 1) {
            return new LinkedHashMap(map);
        }
        return h0(map);
    }

    public static final void g0(Iterable iterable, LinkedHashMap linkedHashMap) {
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            i iVar = (i) it.next();
            linkedHashMap.put(iVar.d, iVar.e);
        }
    }

    public static final Map h0(Map map) {
        j.e(map, "<this>");
        Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
        Map singletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
        j.d(singletonMap, "with(...)");
        return singletonMap;
    }
}
