package androidx.core.view.animation;

import android.graphics.Path;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PathInterpolatorCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static Interpolator createPathInterpolator(Path path) {
            return new PathInterpolator(path);
        }

        public static Interpolator createPathInterpolator(float f, float f5, float f8, float f10) {
            return new PathInterpolator(f, f5, f8, f10);
        }
    }

    public static Interpolator create(Path path) {
        return Api21Impl.createPathInterpolator(path);
    }

    public static Interpolator create(float f, float f5, float f8, float f10) {
        return Api21Impl.createPathInterpolator(f, f5, f8, f10);
    }
}
