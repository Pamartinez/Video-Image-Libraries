package bd;

import android.graphics.RuntimeShader;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffectBase;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightRenderEffect;
import com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightRenderEffect;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ VibeRenderEffectBase e;

    public /* synthetic */ j(VibeRenderEffectBase vibeRenderEffectBase, int i2) {
        this.d = i2;
        this.e = vibeRenderEffectBase;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        VibeRenderEffectBase vibeRenderEffectBase = this.e;
        switch (i2) {
            case 0:
                GuidingLightRenderEffect._init_$lambda$1((GuidingLightRenderEffect) vibeRenderEffectBase, (RuntimeShader) obj);
                return;
            default:
                ProcessingLightRenderEffect.setLightMap$lambda$7((ProcessingLightRenderEffect) vibeRenderEffectBase, (RuntimeShader) obj);
                return;
        }
    }
}
