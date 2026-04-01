package androidx.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class ViewUtils {
    static final Property<View, Rect> CLIP_BOUNDS = new Property<View, Rect>(Rect.class, "clipBounds") {
        public Rect get(View view) {
            return view.getClipBounds();
        }

        public void set(View view, Rect rect) {
            view.setClipBounds(rect);
        }
    };
    private static final ViewUtilsApi19 IMPL = new ViewUtilsApi29();
    static final Property<View, Float> TRANSITION_ALPHA = new Property<View, Float>(Float.class, "translationAlpha") {
        public Float get(View view) {
            return Float.valueOf(ViewUtils.getTransitionAlpha(view));
        }

        public void set(View view, Float f) {
            ViewUtils.setTransitionAlpha(view, f.floatValue());
        }
    };

    public static void clearNonTransitionAlpha(View view) {
        IMPL.clearNonTransitionAlpha(view);
    }

    public static float getTransitionAlpha(View view) {
        return IMPL.getTransitionAlpha(view);
    }

    public static void saveNonTransitionAlpha(View view) {
        IMPL.saveNonTransitionAlpha(view);
    }

    public static void setLeftTopRightBottom(View view, int i2, int i7, int i8, int i10) {
        IMPL.setLeftTopRightBottom(view, i2, i7, i8, i10);
    }

    public static void setTransitionAlpha(View view, float f) {
        IMPL.setTransitionAlpha(view, f);
    }

    public static void setTransitionVisibility(View view, int i2) {
        IMPL.setTransitionVisibility(view, i2);
    }

    public static void transformMatrixToGlobal(View view, Matrix matrix) {
        IMPL.transformMatrixToGlobal(view, matrix);
    }

    public static void transformMatrixToLocal(View view, Matrix matrix) {
        IMPL.transformMatrixToLocal(view, matrix);
    }
}
