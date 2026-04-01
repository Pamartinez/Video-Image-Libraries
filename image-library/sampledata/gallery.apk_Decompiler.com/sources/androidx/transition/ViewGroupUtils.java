package androidx.transition;

import android.view.ViewGroup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class ViewGroupUtils {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api29Impl {
        public static int getChildDrawingOrder(ViewGroup viewGroup, int i2) {
            return viewGroup.getChildDrawingOrder(i2);
        }

        public static void suppressLayout(ViewGroup viewGroup, boolean z) {
            viewGroup.suppressLayout(z);
        }
    }

    public static void suppressLayout(ViewGroup viewGroup, boolean z) {
        Api29Impl.suppressLayout(viewGroup, z);
    }
}
