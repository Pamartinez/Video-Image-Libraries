package androidx.reflect.content.res;

import android.content.res.Configuration;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslConfigurationReflector {
    private static final Class<?> mClass = Configuration.class;

    private static int getField_SEM_DESKTOP_MODE_ENABLED() {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_SEM_DESKTOP_MODE_ENABLED", (Class<?>[]) new Class[0]);
        Object obj = null;
        if (declaredMethod != null) {
            obj = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    private static int getField_semDesktopModeEnabled(Configuration configuration) {
        Object obj;
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_semDesktopModeEnabled", (Class<?>[]) new Class[0]);
        if (declaredMethod != null) {
            obj = SeslBaseReflector.invoke(configuration, declaredMethod, new Object[0]);
        } else {
            obj = null;
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return -1;
    }

    public static boolean isDexEnabled(Configuration configuration) {
        if (getField_semDesktopModeEnabled(configuration) == getField_SEM_DESKTOP_MODE_ENABLED()) {
            return true;
        }
        return false;
    }
}
