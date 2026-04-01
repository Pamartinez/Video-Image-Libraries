package androidx.reflect.feature;

import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslFloatingFeatureReflector {
    private static String mClassName = "com.samsung.sesl.feature.SemFloatingFeature";

    public static String getString(String str, String str2) {
        Class<String> cls = String.class;
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "hidden_getString", (Class<?>[]) new Class[]{cls, cls});
        Object obj = null;
        if (declaredMethod != null) {
            obj = SeslBaseReflector.invoke((Object) null, declaredMethod, str, str2);
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        return str2;
    }
}
