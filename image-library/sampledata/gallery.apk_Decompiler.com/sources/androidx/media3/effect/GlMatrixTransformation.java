package androidx.media3.effect;

import F2.U;
import F2.y0;
import android.content.Context;
import androidx.media3.common.util.Size;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface GlMatrixTransformation extends GlEffect {
    Size configure(int i2, int i7) {
        return new Size(i2, i7);
    }

    float[] getGlMatrixArray(long j2);

    int getGlTextureMinFilter() {
        return 9729;
    }

    BaseGlShaderProgram toGlShaderProgram(Context context, boolean z) {
        return DefaultShaderProgram.create(context, U.B(this), y0.f278h, z);
    }
}
