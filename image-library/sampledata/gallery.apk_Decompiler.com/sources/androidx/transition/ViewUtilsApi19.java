package androidx.transition;

import android.graphics.Matrix;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class ViewUtilsApi19 {
    public abstract float getTransitionAlpha(View view);

    public abstract void setLeftTopRightBottom(View view, int i2, int i7, int i8, int i10);

    public abstract void setTransitionAlpha(View view, float f);

    public abstract void setTransitionVisibility(View view, int i2);

    public abstract void transformMatrixToGlobal(View view, Matrix matrix);

    public abstract void transformMatrixToLocal(View view, Matrix matrix);

    public void clearNonTransitionAlpha(View view) {
    }

    public void saveNonTransitionAlpha(View view) {
    }
}
