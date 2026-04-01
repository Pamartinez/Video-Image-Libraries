package dd;

import android.graphics.RuntimeShader;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.RadialGradRenderEffect;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ RadialGradRenderEffect e;
    public final /* synthetic */ int f;

    public /* synthetic */ c(int i2, RadialGradRenderEffect radialGradRenderEffect) {
        this.f = i2;
        this.e = radialGradRenderEffect;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                RadialGradRenderEffect.setBackgroundColor$lambda$6(this.f, this.e, (RuntimeShader) obj);
                return;
            default:
                RadialGradRenderEffect.setSpotCount$lambda$7(this.e, this.f, (RuntimeShader) obj);
                return;
        }
    }

    public /* synthetic */ c(RadialGradRenderEffect radialGradRenderEffect, int i2) {
        this.e = radialGradRenderEffect;
        this.f = i2;
    }
}
