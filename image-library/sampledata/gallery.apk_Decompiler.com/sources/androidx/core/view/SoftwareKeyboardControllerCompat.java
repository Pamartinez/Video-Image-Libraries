package androidx.core.view;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SoftwareKeyboardControllerCompat {
    private final Impl mImpl;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Impl {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Impl20 extends Impl {
        private final View mView;

        public Impl20(View view) {
            this.mView = view;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Impl30 extends Impl20 {
        private View mView;

        public Impl30(View view) {
            super(view);
            this.mView = view;
        }
    }

    public SoftwareKeyboardControllerCompat(View view) {
        this.mImpl = new Impl30(view);
    }
}
