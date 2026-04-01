package androidx.transition;

import android.graphics.Matrix;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ViewUtilsApi29 extends ViewUtilsApi23 {
    public float getTransitionAlpha(View view) {
        return view.getTransitionAlpha();
    }

    public void setLeftTopRightBottom(View view, int i2, int i7, int i8, int i10) {
        view.setLeftTopRightBottom(i2, i7, i8, i10);
    }

    public void setTransitionAlpha(View view, float f) {
        view.setTransitionAlpha(f);
    }

    public void setTransitionVisibility(View view, int i2) {
        view.setTransitionVisibility(i2);
    }

    public void transformMatrixToGlobal(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    public void transformMatrixToLocal(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
