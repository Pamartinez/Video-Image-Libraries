package dd;

import android.graphics.RuntimeShader;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.RadialGradRenderEffect;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ RadialGradRenderEffect d;
    public final /* synthetic */ int e;
    public final /* synthetic */ float f;

    public /* synthetic */ f(RadialGradRenderEffect radialGradRenderEffect, int i2, float f5) {
        this.d = radialGradRenderEffect;
        this.e = i2;
        this.f = f5;
    }

    public final void accept(Object obj) {
        RadialGradRenderEffect.setSpotScale$lambda$12(this.d, this.e, this.f, (RuntimeShader) obj);
    }
}
