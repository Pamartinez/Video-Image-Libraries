package androidx.reflect.view;

import android.view.View;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslDecorViewReflector {
    public static void semSetForceHideRoundedCorner(View view, boolean z) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(view.getClass(), "hidden_semSetForceHideRoundedCorner", (Class<?>[]) new Class[]{Boolean.TYPE});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(view, declaredMethod, Boolean.valueOf(z));
        }
    }
}
