package com.samsung.android.gallery.support.library.utils;

import N2.j;
import android.util.Log;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Reflector {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ObjectType {
        private static final Map<Class<?>, Class<?>> map;

        static {
            HashMap hashMap = new HashMap();
            map = hashMap;
            hashMap.put(Integer.class, Integer.TYPE);
            hashMap.put(Byte.class, Byte.TYPE);
            hashMap.put(Character.class, Character.TYPE);
            hashMap.put(Boolean.class, Boolean.TYPE);
            hashMap.put(Double.class, Double.TYPE);
            hashMap.put(Float.class, Float.TYPE);
            hashMap.put(Long.class, Long.TYPE);
            hashMap.put(Short.class, Short.TYPE);
            hashMap.put(Void.class, Void.TYPE);
        }

        public static Class<?> toPrimitive(Class<?> cls) {
            if (cls.isPrimitive()) {
                return cls;
            }
            return map.getOrDefault(cls, cls);
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.Class<?>[]} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.lang.Class<?>[] toPrimitiveTypes(java.lang.Object... r3) {
            /*
                int r0 = r3.length
                java.lang.Class[] r0 = new java.lang.Class[r0]
                r1 = 0
            L_0x0004:
                int r2 = r3.length
                if (r1 >= r2) goto L_0x001b
                r2 = r3[r1]
                if (r2 != 0) goto L_0x000e
                java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
                goto L_0x0016
            L_0x000e:
                java.lang.Class r2 = r2.getClass()
                java.lang.Class r2 = toPrimitive(r2)
            L_0x0016:
                r0[r1] = r2
                int r1 = r1 + 1
                goto L_0x0004
            L_0x001b:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.library.utils.Reflector.ObjectType.toPrimitiveTypes(java.lang.Object[]):java.lang.Class[]");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UniqueMethodHolder {
        static final ConcurrentHashMap<String, AtomicReference<Method>> MAP = new ConcurrentHashMap<>();

        public static Method computeMethod(Class<?> cls, String str) {
            ConcurrentHashMap<String, AtomicReference<Method>> concurrentHashMap = MAP;
            return (Method) concurrentHashMap.computeIfAbsent(cls + NumericEnum.SEP + str, new a(cls, str)).get();
        }

        public static Method getMethod(Class<?> cls, String str) {
            if (cls == null || str == null) {
                return null;
            }
            try {
                for (Method method : cls.getMethods()) {
                    if (str.equals(method.getName())) {
                        return method;
                    }
                }
                Log.w("Reflector", "getMethod {" + cls + NumericEnum.SEP + str + "} not found");
                return null;
            } catch (Exception e) {
                StringBuilder sb2 = new StringBuilder("getMethod {");
                sb2.append(cls);
                sb2.append(NumericEnum.SEP);
                sb2.append(str);
                sb2.append("} failed. e=");
                j.C(e, sb2, "Reflector");
                return null;
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ AtomicReference lambda$computeMethod$0(Class cls, String str, String str2) {
            return new AtomicReference(getMethod(cls, str));
        }
    }

    public static Class<?> getClass(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            Log.e("Reflector", "getClass failed. e=" + e.getMessage());
            return null;
        }
    }

    public static Object getField(Class<?> cls, Object obj, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (IllegalAccessException | NoSuchFieldException | NullPointerException e) {
            Log.w("Reflector", e.toString());
            return null;
        }
    }

    public static <T> Method getMethod(Class<T> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (Error | Exception e) {
            StringBuilder t = C0212a.t(str, " failed. e=");
            t.append(e.getMessage());
            Log.w("Reflector", t.toString());
            return null;
        }
    }

    public static Object invoke(Class<?> cls, Object obj, String str, Object... objArr) {
        try {
            return UniqueMethodHolder.computeMethod(cls, str).invoke(obj, objArr);
        } catch (IllegalAccessException | NullPointerException | InvocationTargetException e) {
            Log.w("Reflector", "invoke#" + str + " failed", e);
            return null;
        }
    }

    public static void setField(Class<?> cls, Object obj, String str, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (IllegalAccessException | NoSuchFieldException | NullPointerException e) {
            Log.w("Reflector", e.toString());
        }
    }

    public static Class<?> getClass(String str, ClassLoader classLoader) {
        if (classLoader != null) {
            try {
                return classLoader.loadClass(str);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Object invoke(Class<?> cls, Object obj, String str) {
        try {
            return UniqueMethodHolder.computeMethod(cls, str).invoke(obj, (Object[]) null);
        } catch (IllegalAccessException | NullPointerException | InvocationTargetException e) {
            Log.w("Reflector", "invoke#" + str + " failed", e);
            return null;
        }
    }

    public static Object invoke(Class<?> cls, String str, Object... objArr) {
        try {
            return cls.getDeclaredMethod(str, ObjectType.toPrimitiveTypes(objArr)).invoke((Object) null, objArr);
        } catch (Exception e) {
            j.C(e, j.k("invoke#", str, " failed. e="), "Reflector");
            return null;
        }
    }

    public static Object invoke(Method method, Object obj, Object... objArr) {
        if (method == null) {
            return null;
        }
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.w("Reflector", "invoke#" + method + " failed", e);
            return null;
        }
    }
}
