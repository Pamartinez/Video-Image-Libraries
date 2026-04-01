package androidx.reflect.view.inputmethod;

import android.view.inputmethod.InputMethodManager;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslInputMethodManagerReflector {
    private static final Class<?> mClass = InputMethodManager.class;

    public static int isAccessoryKeyboardState(InputMethodManager inputMethodManager) {
        Method method = SeslBaseReflector.getMethod(mClass, "isAccessoryKeyboardState", (Class<?>[]) new Class[0]);
        if (method != null) {
            Object invoke = SeslBaseReflector.invoke(inputMethodManager, method, new Object[0]);
            if (invoke instanceof Integer) {
                return ((Integer) invoke).intValue();
            }
        }
        return 0;
    }

    public static boolean isInputMethodShown(InputMethodManager inputMethodManager) {
        Method method = SeslBaseReflector.getMethod(mClass, "semIsInputMethodShown", (Class<?>[]) new Class[0]);
        if (method != null) {
            Object invoke = SeslBaseReflector.invoke(inputMethodManager, method, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
        }
        return false;
    }
}
