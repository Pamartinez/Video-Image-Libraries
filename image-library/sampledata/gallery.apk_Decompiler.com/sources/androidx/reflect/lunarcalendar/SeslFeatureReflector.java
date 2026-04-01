package androidx.reflect.lunarcalendar;

import androidx.reflect.SeslBaseReflector;
import androidx.reflect.SeslPathClassReflector;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslFeatureReflector {
    public static Object getSolarLunarConverter(PathClassLoader pathClassLoader) {
        Method method = SeslPathClassReflector.getMethod(pathClassLoader, "com.android.calendar.Feature", "getSolarLunarConverter", new Class[0]);
        if (method != null) {
            return SeslBaseReflector.invoke((Object) null, method, new Object[0]);
        }
        return null;
    }
}
