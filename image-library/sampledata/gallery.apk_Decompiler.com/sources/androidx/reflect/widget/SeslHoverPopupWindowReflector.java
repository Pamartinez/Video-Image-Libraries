package androidx.reflect.widget;

import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslHoverPopupWindowReflector {
    private static String mClassName = "com.samsung.android.widget.SemHoverPopupWindow";

    public static int getField_TYPE_NONE() {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "hidden_TYPE_NONE", (Class<?>[]) new Class[0]);
        Object obj = null;
        if (declaredMethod != null) {
            obj = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public static int getField_TYPE_USER_CUSTOM() {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "hidden_TYPE_USER_CUSTOM", (Class<?>[]) new Class[0]);
        Object obj = null;
        if (declaredMethod != null) {
            obj = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 3;
    }

    public static void setGravity(Object obj, int i2) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "hidden_setGravity", (Class<?>[]) new Class[]{Integer.TYPE});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(obj, declaredMethod, Integer.valueOf(i2));
        }
    }

    public static void setHoverDetectTime(Object obj, int i2) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "hidden_setHoverDetectTime", (Class<?>[]) new Class[]{Integer.TYPE});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(obj, declaredMethod, Integer.valueOf(i2));
        }
    }

    public static void setHoveringPoint(Object obj, int i2, int i7) {
        String str = mClassName;
        Class cls = Integer.TYPE;
        Method method = SeslBaseReflector.getMethod(str, "setHoveringPoint", (Class<?>[]) new Class[]{cls, cls});
        if (method != null) {
            SeslBaseReflector.invoke(obj, method, Integer.valueOf(i2), Integer.valueOf(i7));
        }
    }

    public static void setOffset(Object obj, int i2, int i7) {
        String str = mClassName;
        Class cls = Integer.TYPE;
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(str, "hidden_setOffset", (Class<?>[]) new Class[]{cls, cls});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(obj, declaredMethod, Integer.valueOf(i2), Integer.valueOf(i7));
        }
    }

    public static void update(Object obj) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "hidden_update", (Class<?>[]) new Class[0]);
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(obj, declaredMethod, new Object[0]);
        }
    }
}
