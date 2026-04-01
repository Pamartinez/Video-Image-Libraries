package androidx.core.view;

import android.view.ViewGroup;
import android.view.WindowInsets;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewGroupCompat {
    private static final WindowInsets CONSUMED = WindowInsetsCompat.CONSUMED.toWindowInsets();
    static boolean sCompatInsetsDispatchInstalled = false;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static boolean isTransitionGroup(ViewGroup viewGroup) {
            return viewGroup.isTransitionGroup();
        }
    }

    public static boolean isTransitionGroup(ViewGroup viewGroup) {
        return Api21Impl.isTransitionGroup(viewGroup);
    }
}
