package androidx.reflect.widget;

import android.widget.TextView;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslTextViewReflector {
    private static final Class<?> mClass = TextView.class;

    public static int getField_SEM_AUTOFILL_ID() {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_SEM_AUTOFILL_ID", (Class<?>[]) new Class[0]);
        Object obj = null;
        if (declaredMethod != null) {
            obj = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public static boolean getField_mSingleLine(TextView textView) {
        Field declaredField = SeslBaseReflector.getDeclaredField(mClass, "mSingleLine");
        if (declaredField == null) {
            return false;
        }
        Object obj = SeslBaseReflector.get(textView, declaredField);
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public static boolean semIsTextSelectionProgressing() {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_semIsTextSelectionProgressing", (Class<?>[]) new Class[0]);
        if (declaredMethod != null) {
            Object invoke = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
        }
        return false;
    }

    public static boolean semIsTextViewHovered() {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_semIsTextViewHovered", (Class<?>[]) new Class[0]);
        if (declaredMethod != null) {
            Object invoke = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
        }
        return false;
    }

    public static void semSetActionModeMenuItemEnabled(TextView textView, int i2, boolean z) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_semSetActionModeMenuItemEnabled", (Class<?>[]) new Class[]{Integer.TYPE, Boolean.TYPE});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(textView, declaredMethod, Integer.valueOf(i2), Boolean.valueOf(z));
        }
    }

    public static void semSetButtonShapeEnabled(TextView textView, boolean z) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_semSetButtonShapeEnabled", (Class<?>[]) new Class[]{Boolean.TYPE});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(textView, declaredMethod, Boolean.valueOf(z));
        }
    }

    public static void setLocalePreferredLineHeightForMinimumUsed(TextView textView, boolean z) {
        Method method = SeslBaseReflector.getMethod(mClass, "setLocalePreferredLineHeightForMinimumUsed", (Class<?>[]) new Class[]{Boolean.TYPE});
        if (method != null) {
            SeslBaseReflector.invoke(textView, method, Boolean.valueOf(z));
        }
    }

    public static void semSetButtonShapeEnabled(TextView textView, boolean z, int i2) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_semSetButtonShapeEnabled", (Class<?>[]) new Class[]{Boolean.TYPE, Integer.TYPE});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(textView, declaredMethod, Boolean.valueOf(z), Integer.valueOf(i2));
        }
    }
}
