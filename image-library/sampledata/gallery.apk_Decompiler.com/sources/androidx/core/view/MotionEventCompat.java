package androidx.core.view;

import android.view.MotionEvent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MotionEventCompat {
    public static boolean isFromSource(MotionEvent motionEvent, int i2) {
        if ((motionEvent.getSource() & i2) == i2) {
            return true;
        }
        return false;
    }
}
