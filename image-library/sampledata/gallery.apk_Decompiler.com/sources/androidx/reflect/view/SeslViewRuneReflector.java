package androidx.reflect.view;

import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslViewRuneReflector {
    private static String mClassName = "com.samsung.android.rune.ViewRune";

    public static boolean isEdgeEffectStretchType() {
        Object obj;
        Method method = SeslBaseReflector.getMethod(mClassName, "hidden_isEdgeEffectStretchType", (Class<?>[]) new Class[0]);
        if (method != null) {
            obj = SeslBaseReflector.invoke(mClassName, method, new Object[0]);
        } else {
            obj = null;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public static boolean supportFoldableDualDisplay() {
        Object obj;
        Method method = SeslBaseReflector.getMethod(mClassName, "hidden_supportFoldableDualDisplay", (Class<?>[]) new Class[0]);
        if (method != null) {
            obj = SeslBaseReflector.invoke(mClassName, method, new Object[0]);
        } else {
            obj = null;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public static boolean supportFoldableNoSubDisplay() {
        Object obj;
        Method method = SeslBaseReflector.getMethod(mClassName, "hidden_supportFoldableNoSubDisplay", (Class<?>[]) new Class[0]);
        if (method != null) {
            obj = SeslBaseReflector.invoke(mClassName, method, new Object[0]);
        } else {
            obj = null;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }
}
