package androidx.reflect.provider;

import android.os.Build;
import android.provider.Settings;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslSettingsReflector$SeslSystemReflector {
    private static final Class<?> mClass = Settings.System.class;

    public static String getField_SEM_ACCESSIBILITY_REDUCE_TRANSPARENCY() {
        Method declaredMethod;
        Object obj = null;
        if (Build.VERSION.SDK_INT >= 31 && (declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_SEM_ACCESSIBILITY_REDUCE_TRANSPARENCY", (Class<?>[]) new Class[0])) != null) {
            obj = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        return "not_supported";
    }

    public static String getField_SEM_PEN_HOVERING() {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_SEM_PEN_HOVERING", (Class<?>[]) new Class[0]);
        Object obj = null;
        if (declaredMethod != null) {
            obj = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        return "pen_hovering";
    }
}
