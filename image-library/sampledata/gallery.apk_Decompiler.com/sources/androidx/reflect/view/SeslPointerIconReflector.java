package androidx.reflect.view;

import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslPointerIconReflector {
    protected static String mClassName = "android.view.PointerIcon";

    public static int getField_SEM_TYPE_STYLUS_DEFAULT() {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "hidden_SEM_TYPE_STYLUS_DEFAULT", (Class<?>[]) new Class[0]);
        Object obj = null;
        if (declaredMethod != null) {
            obj = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 1;
    }

    public static int getField_SEM_TYPE_STYLUS_MORE() {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "hidden_SEM_TYPE_STYLUS_MORE", (Class<?>[]) new Class[0]);
        Object obj = null;
        if (declaredMethod != null) {
            obj = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 20010;
    }

    public static int getField_SEM_TYPE_STYLUS_PEN_SELECT() {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "hidden_SEM_TYPE_STYLUS_PEN_SELECT", (Class<?>[]) new Class[0]);
        Object obj = null;
        if (declaredMethod != null) {
            obj = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 21;
    }

    public static int getField_SEM_TYPE_STYLUS_SCROLL_DOWN() {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "hidden_SEM_TYPE_STYLUS_SCROLL_DOWN", (Class<?>[]) new Class[0]);
        Object obj = null;
        if (declaredMethod != null) {
            obj = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 15;
    }

    public static int getField_SEM_TYPE_STYLUS_SCROLL_LEFT() {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "hidden_SEM_TYPE_STYLUS_SCROLL_LEFT", (Class<?>[]) new Class[0]);
        Object obj = null;
        if (declaredMethod != null) {
            obj = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 17;
    }

    public static int getField_SEM_TYPE_STYLUS_SCROLL_RIGHT() {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "hidden_SEM_TYPE_STYLUS_SCROLL_RIGHT", (Class<?>[]) new Class[0]);
        Object obj = null;
        if (declaredMethod != null) {
            obj = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 13;
    }

    public static int getField_SEM_TYPE_STYLUS_SCROLL_UP() {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "hidden_SEM_TYPE_STYLUS_SCROLL_UP", (Class<?>[]) new Class[0]);
        Object obj = null;
        if (declaredMethod != null) {
            obj = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 11;
    }
}
