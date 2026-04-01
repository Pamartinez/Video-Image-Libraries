package androidx.reflect;

import N2.j;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslBaseReflector {
    public static Object get(Object obj, Field field) {
        if (field == null) {
            Log.e("SeslBaseReflector", "field is null");
            return null;
        }
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            Log.e("SeslBaseReflector", field.getName() + " IllegalAccessException", e);
            return null;
        } catch (IllegalArgumentException e7) {
            Log.e("SeslBaseReflector", field.getName() + " IllegalArgumentException", e7);
            return null;
        }
    }

    public static Class<?> getClass(String str) {
        if (str == null) {
            j.w("className = ", str, "SeslBaseReflector");
            return null;
        }
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            Log.w("SeslBaseReflector", "Fail to get class = ".concat(str));
            return null;
        }
    }

    public static Constructor<?> getConstructor(String str, Class<?>... clsArr) {
        if (str == null) {
            j.w("className = ", str, "SeslBaseReflector");
            return null;
        }
        try {
            return Class.forName(str).getDeclaredConstructor(clsArr);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("SeslBaseReflector", "failed to get reflection - " + e);
            return null;
        }
    }

    public static <T> Field getDeclaredField(Class<T> cls, String str) {
        if (cls == null || str == null) {
            Log.d("SeslBaseReflector", "classT = " + cls + ", fieldName = " + str);
            return null;
        }
        try {
            Field declaredField = cls.getDeclaredField(str);
            if (declaredField != null) {
                declaredField.setAccessible(true);
            }
            return declaredField;
        } catch (NoSuchFieldException unused) {
            Log.w("SeslBaseReflector", "Reflector did not find field = ".concat(str));
            return null;
        }
    }

    public static Method getDeclaredMethod(String str, String str2, Class<?>... clsArr) {
        if (str == null || str2 == null) {
            Log.d("SeslBaseReflector", "className = " + str + ", methodName = " + str2);
            return null;
        }
        Class<?> cls = getClass(str);
        if (cls != null) {
            try {
                Method declaredMethod = cls.getDeclaredMethod(str2, clsArr);
                if (declaredMethod != null) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException unused) {
                Log.w("SeslBaseReflector", "Reflector did not find method = ".concat(str2));
            }
        }
        return null;
    }

    public static Field getField(String str, String str2) {
        if (str == null || str2 == null) {
            Log.d("SeslBaseReflector", "className = " + str + ", fieldName = " + str2);
            return null;
        }
        Class<?> cls = getClass(str);
        if (cls != null) {
            try {
                return cls.getField(str2);
            } catch (NoSuchFieldException unused) {
                Log.w("SeslBaseReflector", "Reflector did not find field = ".concat(str2));
            }
        }
        return null;
    }

    public static Method getMethod(String str, String str2, Class<?>... clsArr) {
        if (str == null || str2 == null) {
            Log.d("SeslBaseReflector", "className = " + str + ", methodName = " + str2);
            return null;
        }
        Class<?> cls = getClass(str);
        if (cls != null) {
            try {
                return cls.getMethod(str2, clsArr);
            } catch (NoSuchMethodException unused) {
                Log.w("SeslBaseReflector", "Reflector did not find method = ".concat(str2));
            }
        }
        return null;
    }

    public static Object invoke(Object obj, Method method, Object... objArr) {
        if (method == null) {
            Log.d("SeslBaseReflector", "method is null");
            return null;
        }
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            Log.e("SeslBaseReflector", method.getName() + " IllegalAccessException", e);
            return null;
        } catch (IllegalArgumentException e7) {
            Log.e("SeslBaseReflector", method.getName() + " IllegalArgumentException", e7);
            return null;
        } catch (InvocationTargetException e8) {
            Log.e("SeslBaseReflector", method.getName() + " InvocationTargetException", e8);
            return null;
        }
    }

    public static void set(Object obj, Field field, Object obj2) {
        if (field == null) {
            Log.e("SeslBaseReflector", "field is null");
            return;
        }
        try {
            field.set(obj, obj2);
        } catch (IllegalAccessException e) {
            Log.e("SeslBaseReflector", field.getName() + " IllegalAccessException", e);
        } catch (IllegalArgumentException e7) {
            Log.e("SeslBaseReflector", field.getName() + " IllegalArgumentException", e7);
        }
    }

    public static <T> Field getField(Class<T> cls, String str) {
        if (cls == null || str == null) {
            Log.d("SeslBaseReflector", "classT = " + cls + ", fieldName = " + str);
            return null;
        }
        try {
            return cls.getField(str);
        } catch (NoSuchFieldException unused) {
            Log.w("SeslBaseReflector", "Reflector did not find field = ".concat(str));
            return null;
        }
    }

    public static <T> Method getMethod(Class<T> cls, String str, Class<?>... clsArr) {
        if (cls == null || str == null) {
            Log.d("SeslBaseReflector", "classT = " + cls + ", methodName = " + str);
            return null;
        }
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException unused) {
            Log.w("SeslBaseReflector", "Reflector did not find method = ".concat(str));
            return null;
        }
    }

    public static <T> Method getDeclaredMethod(Class<T> cls, String str, Class<?>... clsArr) {
        if (cls == null || str == null) {
            Log.d("SeslBaseReflector", "classT = " + cls + ", methodName = " + str);
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            if (declaredMethod != null) {
                declaredMethod.setAccessible(true);
            }
            return declaredMethod;
        } catch (NoSuchMethodException unused) {
            Log.w("SeslBaseReflector", "Reflector did not find method = ".concat(str));
            return null;
        }
    }
}
