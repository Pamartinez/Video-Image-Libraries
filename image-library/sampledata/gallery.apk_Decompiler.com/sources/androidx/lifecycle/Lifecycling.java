package androidx.lifecycle;

import Tf.v;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import o1.C0246a;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\u000b\u001a\u00020\t2\u000e\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b2\u0006\u0010\u0004\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u000b\u0010\fJ%\u0010\u000f\u001a\f\u0012\u0006\b\u0001\u0012\u00020\t\u0018\u00010\b2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0012\u001a\u00020\u00112\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\rH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0014\u001a\u00020\u00112\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\rH\u0002¢\u0006\u0004\b\u0014\u0010\u0013J\u001d\u0010\u0016\u001a\u00020\u00152\f\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\rH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0007¢\u0006\u0004\b\u001a\u0010\u001bR$\u0010\u001d\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u00110\u001c8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR2\u0010 \u001a \u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\t0\b0\u001f0\u001c8\u0002X\u0004¢\u0006\u0006\n\u0004\b \u0010\u001e¨\u0006!"}, d2 = {"Landroidx/lifecycle/Lifecycling;", "", "<init>", "()V", "object", "Landroidx/lifecycle/LifecycleEventObserver;", "lifecycleEventObserver", "(Ljava/lang/Object;)Landroidx/lifecycle/LifecycleEventObserver;", "Ljava/lang/reflect/Constructor;", "Landroidx/lifecycle/GeneratedAdapter;", "constructor", "createGeneratedAdapter", "(Ljava/lang/reflect/Constructor;Ljava/lang/Object;)Landroidx/lifecycle/GeneratedAdapter;", "Ljava/lang/Class;", "klass", "generatedConstructor", "(Ljava/lang/Class;)Ljava/lang/reflect/Constructor;", "", "getObserverConstructorType", "(Ljava/lang/Class;)I", "resolveObserverCallbackType", "", "isLifecycleParent", "(Ljava/lang/Class;)Z", "", "className", "getAdapterName", "(Ljava/lang/String;)Ljava/lang/String;", "", "callbackCache", "Ljava/util/Map;", "", "classToAdapters", "lifecycle-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Lifecycling {
    public static final Lifecycling INSTANCE = new Lifecycling();
    private static final Map<Class<?>, Integer> callbackCache = new HashMap();
    private static final Map<Class<?>, List<Constructor<? extends GeneratedAdapter>>> classToAdapters = new HashMap();

    private Lifecycling() {
    }

    private final GeneratedAdapter createGeneratedAdapter(Constructor<? extends GeneratedAdapter> constructor, Object obj) {
        try {
            j.d(constructor.newInstance(new Object[]{obj}), "{\n            constructo…tance(`object`)\n        }");
            throw new ClassCastException();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e7) {
            throw new RuntimeException(e7);
        } catch (InvocationTargetException e8) {
            throw new RuntimeException(e8);
        }
    }

    private final Constructor<? extends GeneratedAdapter> generatedConstructor(Class<?> cls) {
        String str;
        try {
            Package packageR = cls.getPackage();
            String canonicalName = cls.getCanonicalName();
            if (packageR != null) {
                str = packageR.getName();
            } else {
                str = "";
            }
            j.d(str, "fullPackage");
            if (str.length() != 0) {
                j.d(canonicalName, "name");
                canonicalName = canonicalName.substring(str.length() + 1);
                j.d(canonicalName, "this as java.lang.String).substring(startIndex)");
            }
            j.d(canonicalName, "if (fullPackage.isEmpty(…g(fullPackage.length + 1)");
            String adapterName = getAdapterName(canonicalName);
            if (str.length() != 0) {
                adapterName = str + '.' + adapterName;
            }
            Constructor<?> declaredConstructor = Class.forName(adapterName).getDeclaredConstructor(new Class[]{cls});
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String getAdapterName(String str) {
        j.e(str, "className");
        return v.s0(str, ".", "_").concat("_LifecycleAdapter");
    }

    private final int getObserverConstructorType(Class<?> cls) {
        Map<Class<?>, Integer> map = callbackCache;
        Integer num = map.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int resolveObserverCallbackType = resolveObserverCallbackType(cls);
        map.put(cls, Integer.valueOf(resolveObserverCallbackType));
        return resolveObserverCallbackType;
    }

    private final boolean isLifecycleParent(Class<?> cls) {
        if (cls == null || !LifecycleObserver.class.isAssignableFrom(cls)) {
            return false;
        }
        return true;
    }

    public static final LifecycleEventObserver lifecycleEventObserver(Object obj) {
        j.e(obj, "object");
        boolean z = obj instanceof LifecycleEventObserver;
        boolean z3 = obj instanceof DefaultLifecycleObserver;
        if (z && z3) {
            return new DefaultLifecycleObserverAdapter((DefaultLifecycleObserver) obj, (LifecycleEventObserver) obj);
        }
        if (z3) {
            return new DefaultLifecycleObserverAdapter((DefaultLifecycleObserver) obj, (LifecycleEventObserver) null);
        }
        if (z) {
            return (LifecycleEventObserver) obj;
        }
        Class<?> cls = obj.getClass();
        Lifecycling lifecycling = INSTANCE;
        if (lifecycling.getObserverConstructorType(cls) != 2) {
            return new ReflectiveGenericLifecycleObserver(obj);
        }
        List<Constructor<? extends GeneratedAdapter>> list = classToAdapters.get(cls);
        j.b(list);
        List list2 = list;
        if (list2.size() == 1) {
            lifecycling.createGeneratedAdapter((Constructor) list2.get(0), obj);
            return new SingleGeneratedAdapterObserver((GeneratedAdapter) null);
        }
        int size = list2.size();
        GeneratedAdapter[] generatedAdapterArr = new GeneratedAdapter[size];
        for (int i2 = 0; i2 < size; i2++) {
            INSTANCE.createGeneratedAdapter((Constructor) list2.get(i2), obj);
            generatedAdapterArr[i2] = null;
        }
        return new CompositeGeneratedAdaptersObserver(generatedAdapterArr);
    }

    private final int resolveObserverCallbackType(Class<?> cls) {
        ArrayList arrayList;
        if (cls.getCanonicalName() == null) {
            return 1;
        }
        Constructor<? extends GeneratedAdapter> generatedConstructor = generatedConstructor(cls);
        if (generatedConstructor != null) {
            classToAdapters.put(cls, C0246a.e0(generatedConstructor));
            return 2;
        } else if (ClassesInfoCache.sInstance.hasLifecycleMethods(cls)) {
            return 1;
        } else {
            Class<? super Object> superclass = cls.getSuperclass();
            if (isLifecycleParent(superclass)) {
                j.d(superclass, "superclass");
                if (getObserverConstructorType(superclass) == 1) {
                    return 1;
                }
                List<Constructor<? extends GeneratedAdapter>> list = classToAdapters.get(superclass);
                j.b(list);
                arrayList = new ArrayList(list);
            } else {
                arrayList = null;
            }
            Class[] interfaces = cls.getInterfaces();
            j.d(interfaces, "klass.interfaces");
            for (Class cls2 : interfaces) {
                if (isLifecycleParent(cls2)) {
                    j.d(cls2, "intrface");
                    if (getObserverConstructorType(cls2) == 1) {
                        return 1;
                    }
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    List<Constructor<? extends GeneratedAdapter>> list2 = classToAdapters.get(cls2);
                    j.b(list2);
                    arrayList.addAll(list2);
                }
            }
            if (arrayList == null) {
                return 1;
            }
            classToAdapters.put(cls, arrayList);
            return 2;
        }
    }
}
