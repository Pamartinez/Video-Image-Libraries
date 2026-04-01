package androidx.reflect.media;

import android.media.AudioManager;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Field;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslAudioManagerReflector {
    private static final Class<?> mClass = AudioManager.class;

    public static int getField_SOUND_TIME_PICKER_SCROLL() {
        Field field = SeslBaseReflector.getField(mClass, "SOUND_TIME_PICKER_SCROLL");
        if (field == null) {
            return 0;
        }
        Object obj = SeslBaseReflector.get((Object) null, field);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public static int getField_SOUND_TIME_PICKER_SCROLL_FAST() {
        Field field = SeslBaseReflector.getField(mClass, "SOUND_TIME_PICKER_FAST");
        if (field == null) {
            return 0;
        }
        Object obj = SeslBaseReflector.get((Object) null, field);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public static int getField_SOUND_TIME_PICKER_SCROLL_SLOW() {
        Field field = SeslBaseReflector.getField(mClass, "SOUND_TIME_PICKER_SLOW");
        if (field == null) {
            return 0;
        }
        Object obj = SeslBaseReflector.get((Object) null, field);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }
}
