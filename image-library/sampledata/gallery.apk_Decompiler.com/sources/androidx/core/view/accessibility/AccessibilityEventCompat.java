package androidx.core.view.accessibility;

import android.view.accessibility.AccessibilityEvent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AccessibilityEventCompat {
    @Deprecated
    public static int getContentChangeTypes(AccessibilityEvent accessibilityEvent) {
        return accessibilityEvent.getContentChangeTypes();
    }

    @Deprecated
    public static void setContentChangeTypes(AccessibilityEvent accessibilityEvent, int i2) {
        accessibilityEvent.setContentChangeTypes(i2);
    }
}
