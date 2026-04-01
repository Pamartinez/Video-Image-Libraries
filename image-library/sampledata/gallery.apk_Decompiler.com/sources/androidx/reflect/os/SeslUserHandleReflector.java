package androidx.reflect.os;

import android.os.UserHandle;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslUserHandleReflector {
    private static final Class<?> mClass = UserHandle.class;

    public static int myUserId() {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_myUserId", (Class<?>[]) new Class[0]);
        if (declaredMethod != null) {
            Object invoke = SeslBaseReflector.invoke((Object) null, declaredMethod, new Object[0]);
            if (invoke instanceof Integer) {
                return ((Integer) invoke).intValue();
            }
        }
        return 0;
    }
}
