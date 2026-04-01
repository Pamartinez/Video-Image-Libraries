package androidx.core.view;

import android.view.ViewGroup;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MarginLayoutParamsCompat {
    @Deprecated
    public static int getMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.getMarginEnd();
    }

    @Deprecated
    public static int getMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return marginLayoutParams.getMarginStart();
    }

    @Deprecated
    public static void setMarginEnd(ViewGroup.MarginLayoutParams marginLayoutParams, int i2) {
        marginLayoutParams.setMarginEnd(i2);
    }

    @Deprecated
    public static void setMarginStart(ViewGroup.MarginLayoutParams marginLayoutParams, int i2) {
        marginLayoutParams.setMarginStart(i2);
    }
}
