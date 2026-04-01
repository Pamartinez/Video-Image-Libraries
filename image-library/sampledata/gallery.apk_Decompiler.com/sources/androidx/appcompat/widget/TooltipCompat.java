package androidx.appcompat.widget;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TooltipCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api26Impl {
        public static void setTooltipText(View view, CharSequence charSequence) {
            view.setTooltipText(charSequence);
        }
    }

    public static void seslSetNextTooltipForceActionBarPosX(boolean z) {
        TooltipCompatHandler.seslSetTooltipForceActionBarPosX(z);
    }

    public static void seslSetNextTooltipForceBelow(boolean z) {
        TooltipCompatHandler.seslSetTooltipForceBelow(z);
    }

    public static void seslSetTooltipNull(boolean z) {
        TooltipCompatHandler.seslSetTooltipNull(z);
    }

    public static void seslSetTooltipText(View view, CharSequence charSequence, boolean z) {
        if (!z) {
            Api26Impl.setTooltipText(view, charSequence);
        } else {
            TooltipCompatHandler.setTooltipText(view, charSequence);
        }
    }

    @Deprecated(since = "Use seslSetTooltipNull instead")
    public static void setTooltipNull(boolean z) {
        seslSetTooltipNull(z);
    }

    public static void setTooltipText(View view, CharSequence charSequence) {
        setTooltipText(view, charSequence, false);
    }

    @Deprecated(since = "Use seslSetTooltipText instead")
    public static void setTooltipText(View view, CharSequence charSequence, boolean z) {
        seslSetTooltipText(view, charSequence, z);
    }
}
