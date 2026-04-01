package androidx.media3.effect;

import android.graphics.Matrix;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MatrixTransformation extends GlMatrixTransformation {
    float[] getGlMatrixArray(long j2) {
        return MatrixUtils.getGlMatrixArray(getMatrix(j2));
    }

    Matrix getMatrix(long j2);
}
