package androidx.fragment.app;

import androidx.collection.SimpleArrayMap;
import androidx.fragment.app.Fragment;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FragmentFactory {
    private static final SimpleArrayMap<ClassLoader, SimpleArrayMap<String, Class<?>>> sClassCacheMap = new SimpleArrayMap<>();

    public static boolean isFragmentClass(ClassLoader classLoader, String str) {
        try {
            return Fragment.class.isAssignableFrom(loadClass(classLoader, str));
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private static Class<?> loadClass(ClassLoader classLoader, String str) {
        SimpleArrayMap<ClassLoader, SimpleArrayMap<String, Class<?>>> simpleArrayMap = sClassCacheMap;
        SimpleArrayMap simpleArrayMap2 = simpleArrayMap.get(classLoader);
        if (simpleArrayMap2 == null) {
            simpleArrayMap2 = new SimpleArrayMap();
            simpleArrayMap.put(classLoader, simpleArrayMap2);
        }
        Class<?> cls = (Class) simpleArrayMap2.get(str);
        if (cls != null) {
            return cls;
        }
        Class<?> cls2 = Class.forName(str, false, classLoader);
        simpleArrayMap2.put(str, cls2);
        return cls2;
    }

    public static Class<? extends Fragment> loadFragmentClass(ClassLoader classLoader, String str) {
        try {
            return loadClass(classLoader, str);
        } catch (ClassNotFoundException e) {
            throw new Fragment.InstantiationException(C0212a.m("Unable to instantiate fragment ", str, ": make sure class name exists"), e);
        } catch (ClassCastException e7) {
            throw new Fragment.InstantiationException(C0212a.m("Unable to instantiate fragment ", str, ": make sure class is a valid subclass of Fragment"), e7);
        }
    }

    public abstract Fragment instantiate(ClassLoader classLoader, String str);
}
