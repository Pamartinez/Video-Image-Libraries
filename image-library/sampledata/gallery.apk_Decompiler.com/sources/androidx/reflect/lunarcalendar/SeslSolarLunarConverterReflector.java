package androidx.reflect.lunarcalendar;

import androidx.reflect.SeslBaseReflector;
import androidx.reflect.SeslPathClassReflector;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslSolarLunarConverterReflector {
    public static void convertLunarToSolar(PathClassLoader pathClassLoader, Object obj, int i2, int i7, int i8, boolean z) {
        Class cls = Integer.TYPE;
        Method method = SeslPathClassReflector.getMethod(pathClassLoader, "com.samsung.android.calendar.secfeature.lunarcalendar.SolarLunarConverter", "convertLunarToSolar", cls, cls, cls, Boolean.TYPE);
        if (method != null) {
            SeslBaseReflector.invoke(obj, method, Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(i8), Boolean.valueOf(z));
        }
    }

    public static void convertSolarToLunar(PathClassLoader pathClassLoader, Object obj, int i2, int i7, int i8) {
        Class cls = Integer.TYPE;
        Method method = SeslPathClassReflector.getMethod(pathClassLoader, "com.samsung.android.calendar.secfeature.lunarcalendar.SolarLunarConverter", "convertSolarToLunar", cls, cls, cls);
        if (method != null) {
            SeslBaseReflector.invoke(obj, method, Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(i8));
        }
    }

    public static int getDay(PathClassLoader pathClassLoader, Object obj) {
        Method method = SeslPathClassReflector.getMethod(pathClassLoader, "com.samsung.android.calendar.secfeature.lunarcalendar.SolarLunarConverter", "getDay", new Class[0]);
        if (method == null) {
            return 19;
        }
        Object invoke = SeslBaseReflector.invoke(obj, method, new Object[0]);
        if (invoke instanceof Integer) {
            return ((Integer) invoke).intValue();
        }
        return 19;
    }

    public static int getDayLengthOf(PathClassLoader pathClassLoader, Object obj, int i2, int i7, boolean z) {
        Class cls = Integer.TYPE;
        Method method = SeslPathClassReflector.getMethod(pathClassLoader, "com.samsung.android.calendar.secfeature.lunarcalendar.SolarLunarConverter", "getDayLengthOf", cls, cls, Boolean.TYPE);
        if (method == null) {
            return 30;
        }
        Object invoke = SeslBaseReflector.invoke(obj, method, Integer.valueOf(i2), Integer.valueOf(i7), Boolean.valueOf(z));
        if (invoke instanceof Integer) {
            return ((Integer) invoke).intValue();
        }
        return 30;
    }

    public static int getMonth(PathClassLoader pathClassLoader, Object obj) {
        Method method = SeslPathClassReflector.getMethod(pathClassLoader, "com.samsung.android.calendar.secfeature.lunarcalendar.SolarLunarConverter", "getMonth", new Class[0]);
        if (method == null) {
            return 10;
        }
        Object invoke = SeslBaseReflector.invoke(obj, method, new Object[0]);
        if (invoke instanceof Integer) {
            return ((Integer) invoke).intValue();
        }
        return 10;
    }

    public static int getWeekday(PathClassLoader pathClassLoader, Object obj, int i2, int i7, int i8) {
        Class cls = Integer.TYPE;
        Method method = SeslPathClassReflector.getMethod(pathClassLoader, "com.samsung.android.calendar.secfeature.lunarcalendar.SolarLunarConverter", "getWeekday", cls, cls, cls);
        if (method == null) {
            return 0;
        }
        Object invoke = SeslBaseReflector.invoke(obj, method, Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(i8));
        if (invoke instanceof Integer) {
            return ((Integer) invoke).intValue();
        }
        return 0;
    }

    public static int getYear(PathClassLoader pathClassLoader, Object obj) {
        Method method = SeslPathClassReflector.getMethod(pathClassLoader, "com.samsung.android.calendar.secfeature.lunarcalendar.SolarLunarConverter", "getYear", new Class[0]);
        if (method == null) {
            return 2019;
        }
        Object invoke = SeslBaseReflector.invoke(obj, method, new Object[0]);
        if (invoke instanceof Integer) {
            return ((Integer) invoke).intValue();
        }
        return 2019;
    }

    public static boolean isLeapMonth(PathClassLoader pathClassLoader, Object obj) {
        Method method = SeslPathClassReflector.getMethod(pathClassLoader, "com.samsung.android.calendar.secfeature.lunarcalendar.SolarLunarConverter", "isLeapMonth", new Class[0]);
        if (method != null) {
            Object invoke = SeslBaseReflector.invoke(obj, method, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
        }
        return false;
    }
}
