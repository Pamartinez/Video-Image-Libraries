package dd;

import android.graphics.PointF;
import android.graphics.RuntimeShader;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.RadialGradRenderEffect;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ RadialGradRenderEffect d;
    public final /* synthetic */ int e;
    public final /* synthetic */ PointF f;

    public /* synthetic */ e(RadialGradRenderEffect radialGradRenderEffect, int i2, PointF pointF) {
        this.d = radialGradRenderEffect;
        this.e = i2;
        this.f = pointF;
    }

    public final void accept(Object obj) {
        RadialGradRenderEffect.setSpotPosition$lambda$11(this.d, this.e, this.f, (RuntimeShader) obj);
    }
}
