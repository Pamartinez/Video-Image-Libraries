package androidx.reflect.view;

import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslSemBlurInfoReflector {
    public static void semBuildSetBlurInfo(Object obj, View view) {
        Method method;
        if (Build.VERSION.SDK_INT >= 31) {
            method = SeslBaseReflector.getDeclaredMethod("android.view.SemBlurInfo$Builder", "hidden_build", (Class<?>[]) new Class[0]);
        } else {
            method = null;
        }
        if (method != null) {
            method.setAccessible(true);
            SeslViewReflector.semSetBlurInfo(view, SeslBaseReflector.invoke(obj, method, new Object[0]));
        }
    }

    public static Object semCreateBlurBuilder(int i2) {
        Constructor<?> constructor = SeslBaseReflector.getConstructor("android.view.SemBlurInfo$Builder", Integer.TYPE);
        if (Build.VERSION.SDK_INT < 31 || constructor == null) {
            return null;
        }
        try {
            return constructor.newInstance(new Object[]{Integer.valueOf(i2)});
        } catch (IllegalAccessException e) {
            Log.e("SeslSemBlurInfoRftr", "semCreateBlurBuilder IllegalAccessException", e);
            return null;
        } catch (InvocationTargetException e7) {
            Log.e("SeslSemBlurInfoRftr", "semCreateBlurBuilder InvocationTargetException", e7);
            return null;
        } catch (InstantiationException e8) {
            Log.e("SeslSemBlurInfoRftr", "semCreateBlurBuilder InstantiationException", e8);
            return null;
        }
    }

    public static Object semSetBuilderBlurBackgroundColor(Object obj, int i2) {
        Method method;
        if (Build.VERSION.SDK_INT >= 31) {
            method = SeslBaseReflector.getDeclaredMethod("android.view.SemBlurInfo$Builder", "hidden_setBackgroundColor", (Class<?>[]) new Class[]{Integer.TYPE});
        } else {
            method = null;
        }
        if (method != null) {
            method.setAccessible(true);
            SeslBaseReflector.invoke(obj, method, Integer.valueOf(i2));
        }
        return obj;
    }

    public static Object semSetBuilderBlurBackgroundCornerRadius(Object obj, float f) {
        Method method;
        if (Build.VERSION.SDK_INT >= 31) {
            method = SeslBaseReflector.getDeclaredMethod("android.view.SemBlurInfo$Builder", "hidden_setBackgroundCornerRadius", (Class<?>[]) new Class[]{Float.TYPE});
        } else {
            method = null;
        }
        if (method != null) {
            method.setAccessible(true);
            SeslBaseReflector.invoke(obj, method, Float.valueOf(f));
        }
        return obj;
    }

    public static Object semSetBuilderBlurRadius(Object obj, int i2) {
        Method method;
        if (Build.VERSION.SDK_INT >= 35) {
            method = SeslBaseReflector.getDeclaredMethod("android.view.SemBlurInfo$Builder", "hidden_setRadius", (Class<?>[]) new Class[]{Integer.TYPE});
        } else {
            method = null;
        }
        if (method != null) {
            method.setAccessible(true);
            SeslBaseReflector.invoke(obj, method, Integer.valueOf(i2));
        }
        return obj;
    }

    public static Object semSetColorCurve(Object obj, float f, float f5, float f8, float f10, float f11, float f12) {
        Method method;
        if (Build.VERSION.SDK_INT >= 35) {
            Class cls = Float.TYPE;
            method = SeslBaseReflector.getDeclaredMethod("android.view.SemBlurInfo$Builder", "setColorCurve", (Class<?>[]) new Class[]{cls, cls, cls, cls, cls, cls});
        } else {
            method = null;
        }
        if (method != null) {
            method.setAccessible(true);
            SeslBaseReflector.invoke(obj, method, Float.valueOf(f), Float.valueOf(f5), Float.valueOf(f8), Float.valueOf(f10), Float.valueOf(f11), Float.valueOf(f12));
        }
        return obj;
    }
}
