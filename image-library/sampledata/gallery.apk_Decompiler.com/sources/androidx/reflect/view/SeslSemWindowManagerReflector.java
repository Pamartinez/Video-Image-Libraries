package androidx.reflect.view;

import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslSemWindowManagerReflector {
    private static String mClassName = "com.samsung.android.view.SemWindowManager";

    private static Object getInstance() {
        Object invoke;
        Method method = SeslBaseReflector.getMethod(mClassName, "getInstance", (Class<?>[]) new Class[0]);
        if (method == null || (invoke = SeslBaseReflector.invoke((Object) null, method, new Object[0])) == null || !invoke.getClass().getName().equals(mClassName)) {
            return null;
        }
        return invoke;
    }

    public static boolean isTableMode() {
        Method method = SeslBaseReflector.getMethod(mClassName, "isTableMode", (Class<?>[]) new Class[0]);
        if (method != null) {
            Object invoke = SeslBaseReflector.invoke(getInstance(), method, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
        }
        return false;
    }
}
