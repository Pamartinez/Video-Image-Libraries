package androidx.core.view;

import android.content.Context;
import android.view.PointerIcon;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PointerIconCompat {
    private final PointerIcon mPointerIcon;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api24Impl {
        public static PointerIcon getSystemIcon(Context context, int i2) {
            return PointerIcon.getSystemIcon(context, i2);
        }
    }

    private PointerIconCompat(PointerIcon pointerIcon) {
        this.mPointerIcon = pointerIcon;
    }

    public static PointerIconCompat getSystemIcon(Context context, int i2) {
        return new PointerIconCompat(Api24Impl.getSystemIcon(context, i2));
    }

    public Object getPointerIcon() {
        return this.mPointerIcon;
    }
}
