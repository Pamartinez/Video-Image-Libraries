package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.Effect;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface GlEffect extends Effect {
    boolean isNoOp(int i2, int i7) {
        return false;
    }

    GlShaderProgram toGlShaderProgram(Context context, boolean z);
}
