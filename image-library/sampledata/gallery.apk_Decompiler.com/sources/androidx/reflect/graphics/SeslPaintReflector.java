package androidx.reflect.graphics;

import android.graphics.Paint;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslPaintReflector {
    private static final Class<?> mClass = Paint.class;

    public static float getHCTStrokeWidth(Paint paint) {
        Method method = SeslBaseReflector.getMethod(mClass, "getHCTStrokeWidth", (Class<?>[]) new Class[0]);
        if (method == null) {
            return 0.0f;
        }
        Object invoke = SeslBaseReflector.invoke(paint, method, new Object[0]);
        if (invoke instanceof Float) {
            return ((Float) invoke).floatValue();
        }
        return 0.0f;
    }
}
