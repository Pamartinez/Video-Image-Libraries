package androidx.reflect.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslStateListDrawableReflector {
    private static final Class<?> mClass = StateListDrawable.class;

    public static int getStateCount(StateListDrawable stateListDrawable) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_getStateCount", (Class<?>[]) new Class[0]);
        if (declaredMethod != null) {
            Object invoke = SeslBaseReflector.invoke(stateListDrawable, declaredMethod, new Object[0]);
            if (invoke instanceof Integer) {
                return ((Integer) invoke).intValue();
            }
        }
        return 0;
    }

    public static Drawable getStateDrawable(StateListDrawable stateListDrawable, int i2) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_getStateDrawable", (Class<?>[]) new Class[]{Integer.TYPE});
        if (declaredMethod == null) {
            return null;
        }
        Object invoke = SeslBaseReflector.invoke(stateListDrawable, declaredMethod, Integer.valueOf(i2));
        if (invoke instanceof Drawable) {
            return (Drawable) invoke;
        }
        return null;
    }

    public static int[] getStateSet(StateListDrawable stateListDrawable, int i2) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_getStateSet", (Class<?>[]) new Class[]{Integer.TYPE});
        if (declaredMethod != null) {
            Object invoke = SeslBaseReflector.invoke(stateListDrawable, declaredMethod, Integer.valueOf(i2));
            if (invoke instanceof int[]) {
                return (int[]) invoke;
            }
        }
        return new int[0];
    }
}
