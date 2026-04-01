package androidx.core.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EdgeEffect;
import androidx.reflect.view.SeslViewRuneReflector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class EdgeEffectCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static void onPull(EdgeEffect edgeEffect, float f, float f5) {
            edgeEffect.onPull(f, f5);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api31Impl {
        public static EdgeEffect create(Context context, AttributeSet attributeSet) {
            try {
                return new EdgeEffect(context, attributeSet);
            } catch (Throwable unused) {
                return new EdgeEffect(context);
            }
        }

        public static float getDistance(EdgeEffect edgeEffect) {
            try {
                return edgeEffect.getDistance();
            } catch (Throwable unused) {
                return 0.0f;
            }
        }

        public static float onPullDistance(EdgeEffect edgeEffect, float f, float f5) {
            try {
                return edgeEffect.onPullDistance(f, f5);
            } catch (Throwable unused) {
                edgeEffect.onPull(f, f5);
                return 0.0f;
            }
        }
    }

    public static EdgeEffect create(Context context, AttributeSet attributeSet) {
        if (Build.VERSION.SDK_INT < 31 || !SeslViewRuneReflector.isEdgeEffectStretchType()) {
            return new EdgeEffect(context);
        }
        return Api31Impl.create(context, attributeSet);
    }

    public static float getDistance(EdgeEffect edgeEffect) {
        if (Build.VERSION.SDK_INT < 31 || !SeslViewRuneReflector.isEdgeEffectStretchType()) {
            return 0.0f;
        }
        return Api31Impl.getDistance(edgeEffect);
    }

    public static void onPull(EdgeEffect edgeEffect, float f, float f5) {
        Api21Impl.onPull(edgeEffect, f, f5);
    }

    public static float onPullDistance(EdgeEffect edgeEffect, float f, float f5) {
        if (Build.VERSION.SDK_INT >= 31 && SeslViewRuneReflector.isEdgeEffectStretchType()) {
            return Api31Impl.onPullDistance(edgeEffect, f, f5);
        }
        onPull(edgeEffect, f, f5);
        return f;
    }
}
