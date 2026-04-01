package androidx.reflect.content.res;

import android.content.res.Resources;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslResourcesReflector {
    private static final Class<?> mClass = Resources.class;

    public static Object getCompatibilityInfo(Resources resources) {
        Object invoke;
        Method method = SeslBaseReflector.getMethod(mClass, "getCompatibilityInfo", (Class<?>[]) new Class[0]);
        if (method == null || (invoke = SeslBaseReflector.invoke(resources, method, new Object[0])) == null || !invoke.getClass().getName().equals("android.content.res.CompatibilityInfo")) {
            return null;
        }
        return invoke;
    }
}
