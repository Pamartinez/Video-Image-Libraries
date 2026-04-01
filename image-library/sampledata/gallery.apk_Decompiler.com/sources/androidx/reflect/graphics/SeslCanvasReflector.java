package androidx.reflect.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslCanvasReflector {
    private static final Class<?> mClass = Canvas.class;

    public static void restoreUnclippedLayer(Canvas canvas, int i2, Paint paint) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClass, "restoreUnclippedLayer", (Class<?>[]) new Class[]{Integer.TYPE, Paint.class});
        if (declaredMethod != null) {
            SeslBaseReflector.invoke(canvas, declaredMethod, Integer.valueOf(i2), paint);
        }
    }

    public static int saveUnclippedLayer(Canvas canvas, int i2, int i7, int i8, int i10) {
        Class<?> cls = mClass;
        Class cls2 = Integer.TYPE;
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(cls, "saveUnclippedLayer", (Class<?>[]) new Class[]{cls2, cls2, cls2, cls2});
        if (declaredMethod == null) {
            return -1;
        }
        Object invoke = SeslBaseReflector.invoke(canvas, declaredMethod, Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(i8), Integer.valueOf(i10));
        if (invoke instanceof Integer) {
            return ((Integer) invoke).intValue();
        }
        return -1;
    }
}
