package ad;

import android.view.Choreographer;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffectBase;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Choreographer.FrameCallback {
    public final /* synthetic */ VibeRenderEffectBase d;

    public /* synthetic */ c(VibeRenderEffectBase vibeRenderEffectBase) {
        this.d = vibeRenderEffectBase;
    }

    public final void doFrame(long j2) {
        VibeRenderEffectBase.frameCallback$lambda$1(this.d, j2);
    }
}
