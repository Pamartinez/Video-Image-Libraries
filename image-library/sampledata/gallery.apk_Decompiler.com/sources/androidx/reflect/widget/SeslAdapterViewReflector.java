package androidx.reflect.widget;

import android.widget.AdapterView;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslAdapterViewReflector {
    private static final Class<?> mClass = AdapterView.class;

    public static int getField_mSelectedPosition(AdapterView adapterView) {
        Field declaredField = SeslBaseReflector.getDeclaredField(mClass, "mSelectedPosition");
        if (declaredField == null) {
            return -1;
        }
        Object obj = SeslBaseReflector.get(adapterView, declaredField);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return -1;
    }

    public static void semSetBottomColor(AdapterView adapterView, int i2) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "hidden_semSetBottomColor", (Class<?>[]) new Class[]{Integer.TYPE});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(adapterView, declaredMethod, Integer.valueOf(i2));
        }
    }
}
