package androidx.reflect.os;

import android.os.Build;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Field;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslBuildReflector$SeslVersionReflector {
    private static final Class<?> mClass = Build.VERSION.class;

    public static int getField_SEM_PLATFORM_INT() {
        Field declaredField = SeslBaseReflector.getDeclaredField(mClass, "SEM_PLATFORM_INT");
        if (declaredField == null || !(SeslBaseReflector.get((Object) null, declaredField) instanceof Integer)) {
            return -1;
        }
        return ((Integer) SeslBaseReflector.get((Object) null, declaredField)).intValue();
    }
}
