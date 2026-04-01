package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DebugViewEffect implements GlEffect {
    private final DebugViewProvider debugViewProvider;
    private final ColorInfo outputColorInfo;

    public DebugViewEffect(DebugViewProvider debugViewProvider2, ColorInfo colorInfo) {
        this.debugViewProvider = debugViewProvider2;
        this.outputColorInfo = colorInfo;
    }

    public GlShaderProgram toGlShaderProgram(Context context, boolean z) {
        return new DebugViewShaderProgram(context, this.debugViewProvider, this.outputColorInfo);
    }
}
