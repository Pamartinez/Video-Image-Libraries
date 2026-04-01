package androidx.core.view;

import android.graphics.Rect;
import android.view.Gravity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GravityCompat {
    public static void apply(int i2, int i7, int i8, Rect rect, Rect rect2, int i10) {
        Gravity.apply(i2, i7, i8, rect, rect2, i10);
    }

    public static int getAbsoluteGravity(int i2, int i7) {
        return Gravity.getAbsoluteGravity(i2, i7);
    }
}
