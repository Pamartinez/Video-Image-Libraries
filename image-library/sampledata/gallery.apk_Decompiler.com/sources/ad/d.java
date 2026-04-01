package ad;

import android.graphics.RuntimeShader;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffectBase;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.RadialGradRenderEffect;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ VibeRenderEffectBase g;

    public /* synthetic */ d(VibeRenderEffectBase vibeRenderEffectBase, int i2, int i7) {
        this.g = vibeRenderEffectBase;
        this.e = i2;
        this.f = i7;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                VibeRenderEffectBase.setSize$lambda$10(this.g, this.e, this.f, (RuntimeShader) obj);
                return;
            default:
                RadialGradRenderEffect.setSpotColor$lambda$10(this.e, (RadialGradRenderEffect) this.g, this.f, (RuntimeShader) obj);
                return;
        }
    }

    public /* synthetic */ d(RadialGradRenderEffect radialGradRenderEffect, int i2, int i7) {
        this.e = i2;
        this.g = radialGradRenderEffect;
        this.f = i7;
    }
}
