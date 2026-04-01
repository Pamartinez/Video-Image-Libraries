package androidx.core.view.accessibility;

import android.view.View;
import android.view.accessibility.AccessibilityRecord;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AccessibilityRecordCompat {
    @Deprecated
    public static void setMaxScrollX(AccessibilityRecord accessibilityRecord, int i2) {
        accessibilityRecord.setMaxScrollX(i2);
    }

    @Deprecated
    public static void setMaxScrollY(AccessibilityRecord accessibilityRecord, int i2) {
        accessibilityRecord.setMaxScrollY(i2);
    }

    @Deprecated
    public static void setSource(AccessibilityRecord accessibilityRecord, View view, int i2) {
        accessibilityRecord.setSource(view, i2);
    }
}
