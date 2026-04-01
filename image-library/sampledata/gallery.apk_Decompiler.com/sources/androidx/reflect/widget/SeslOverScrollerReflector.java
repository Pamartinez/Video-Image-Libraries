package androidx.reflect.widget;

import android.widget.OverScroller;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslOverScrollerReflector {
    private static final Class<?> mClass = OverScroller.class;

    public static void fling(OverScroller overScroller, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14, boolean z, float f) {
        Class<?> cls = mClass;
        Class cls2 = Boolean.TYPE;
        Class cls3 = Float.TYPE;
        Class cls4 = Integer.TYPE;
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(cls, "hidden_fling", (Class<?>[]) new Class[]{cls4, cls4, cls2, cls3});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(overScroller, declaredMethod, Integer.valueOf(i8), Integer.valueOf(i10), Boolean.valueOf(z), Float.valueOf(f));
            return;
        }
        int i15 = i13;
        int i16 = i14;
        int i17 = i11;
        int i18 = i12;
        int i19 = i10;
        int i20 = i7;
        OverScroller overScroller2 = overScroller;
        overScroller2.fling(i2, i20, i8, i19, i17, i18, i15, i16);
    }
}
