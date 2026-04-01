package androidx.reflect.widget;

import android.os.Build;
import android.widget.HorizontalScrollView;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslHorizontalScrollViewReflector {
    private static final Class<?> mClass = HorizontalScrollView.class;

    public static void setTouchSlop(HorizontalScrollView horizontalScrollView, int i2) {
        Method declaredMethod;
        if (Build.VERSION.SDK_INT >= 31 && (declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_setTouchSlop", (Class<?>[]) new Class[]{Integer.TYPE})) != null) {
            SeslBaseReflector.invoke(horizontalScrollView, declaredMethod, Integer.valueOf(i2));
        }
    }
}
