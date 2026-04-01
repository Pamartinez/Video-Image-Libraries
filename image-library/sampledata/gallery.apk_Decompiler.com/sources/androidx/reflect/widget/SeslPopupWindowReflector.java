package androidx.reflect.widget;

import android.widget.PopupWindow;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslPopupWindowReflector {
    private static final Class<?> mClass = PopupWindow.class;

    public static void setAllowScrollingAnchorParent(PopupWindow popupWindow, boolean z) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "setAllowScrollingAnchorParent", (Class<?>[]) new Class[]{Boolean.TYPE});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(popupWindow, declaredMethod, Boolean.valueOf(z));
        }
    }
}
