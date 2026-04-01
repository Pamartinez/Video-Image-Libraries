package androidx.reflect.media;

import android.content.Context;
import android.util.Log;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslSemSoundAssistantManagerReflector {
    private static String mClassName = "com.samsung.android.media.SemSoundAssistantManager";

    private static Object getInstance(Context context) {
        Constructor<?> constructor = SeslBaseReflector.getConstructor(mClassName, Context.class);
        if (constructor == null) {
            return null;
        }
        try {
            return constructor.newInstance(new Object[]{context});
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            Log.e("SeslSemSoundAssistantManagerReflector", "Failed to instantiate class");
            return null;
        }
    }

    public static void setFastAudioOpenMode(Context context, boolean z) {
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(mClassName, "setFastAudioOpenMode", (Class<?>[]) new Class[]{Boolean.TYPE});
        Object instance = getInstance(context);
        if (declaredMethod != null && instance != null) {
            SeslBaseReflector.invoke(instance, declaredMethod, Boolean.valueOf(z));
        }
    }
}
