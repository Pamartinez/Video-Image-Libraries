package androidx.reflect.view.accessibility;

import android.view.accessibility.AccessibilityManager;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslAccessibilityManagerReflector {
    private static String mClassName = "android.view.accessibility.AccessibilityManager";

    public static boolean isScreenReaderEnabled(AccessibilityManager accessibilityManager, boolean z) {
        Object invoke;
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "semIsScreenReaderEnabled", (Class<?>[]) new Class[0]);
        if (declaredMethod == null || (invoke = SeslBaseReflector.invoke(accessibilityManager, declaredMethod, new Object[0])) == null) {
            return z;
        }
        return ((Boolean) invoke).booleanValue();
    }
}
