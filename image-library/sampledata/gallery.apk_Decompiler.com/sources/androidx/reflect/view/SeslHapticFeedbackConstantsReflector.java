package androidx.reflect.view;

import android.view.HapticFeedbackConstants;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslHapticFeedbackConstantsReflector {
    private static final Class<?> mClass = HapticFeedbackConstants.class;

    public static int semGetVibrationIndex(int i2) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_semGetVibrationIndex", (Class<?>[]) new Class[]{Integer.TYPE});
        if (declaredMethod == null) {
            return -1;
        }
        Object invoke = SeslBaseReflector.invoke((Object) null, declaredMethod, Integer.valueOf(i2));
        if (invoke instanceof Integer) {
            return ((Integer) invoke).intValue();
        }
        return -1;
    }
}
