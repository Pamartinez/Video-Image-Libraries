package androidx.reflect.os;

import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslSystemPropertiesReflector {
    private static String mClassName = "android.os.SemSystemProperties";

    public static String getSalesCode() {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "getSalesCode", (Class<?>[]) new Class[0]);
        if (declaredMethod != null) {
            Object invoke = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
            if (invoke instanceof String) {
                return (String) invoke;
            }
        }
        return null;
    }

    public static String getStringProperties(String str) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "get", (Class<?>[]) new Class[]{String.class});
        if (declaredMethod != null) {
            Object invoke = SeslBaseReflector.invoke((Object) null, declaredMethod, str);
            if (invoke instanceof String) {
                return (String) invoke;
            }
        }
        return null;
    }
}
