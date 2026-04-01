package androidx.picker.util;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslSleepTimePickerUtil {
    public static float convertToTime(float f) {
        return (((((((float) ((int) (f / 2.5f))) * 2.5f) - 270.0f) + 360.0f) % 360.0f) * 1440.0f) / 360.0f;
    }

    public static boolean needBedTimePickerAdjustment(float f) {
        if (f < 420.0f) {
            return true;
        }
        return false;
    }

    public static void performHapticFeedback(View view, int i2) {
        view.performHapticFeedback(i2 + 50024);
    }
}
