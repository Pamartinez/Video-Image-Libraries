package bd;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RuntimeShader;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffectBase;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightRenderEffect;
import com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightRenderEffect;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.RadialGradRenderEffect;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ VibeRenderEffectBase e;
    public final /* synthetic */ Object f;

    public /* synthetic */ m(VibeRenderEffectBase vibeRenderEffectBase, Object obj, int i2) {
        this.d = i2;
        this.e = vibeRenderEffectBase;
        this.f = obj;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                GuidingLightRenderEffect.setLightColor$lambda$21((GuidingLightRenderEffect) this.e, (Color) this.f, (RuntimeShader) obj);
                return;
            case 1:
                GuidingLightRenderEffect.setBorderWidth$lambda$11((GuidingLightRenderEffect) this.e, (Point) this.f, (RuntimeShader) obj);
                return;
            case 2:
                ProcessingLightRenderEffect.setLightPosition$lambda$11((ProcessingLightRenderEffect) this.e, (PointF) this.f, (RuntimeShader) obj);
                return;
            default:
                RadialGradRenderEffect.setLightMap$lambda$4((RadialGradRenderEffect) this.e, (Bitmap) this.f, (RuntimeShader) obj);
                return;
        }
    }
}
