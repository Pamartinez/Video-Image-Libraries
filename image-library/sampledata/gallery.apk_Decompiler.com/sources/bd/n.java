package bd;

import android.graphics.PointF;
import android.graphics.RuntimeShader;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightRenderEffect;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ GuidingLightRenderEffect e;
    public final /* synthetic */ PointF f;

    public /* synthetic */ n(GuidingLightRenderEffect guidingLightRenderEffect, PointF pointF, int i2) {
        this.d = i2;
        this.e = guidingLightRenderEffect;
        this.f = pointF;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                GuidingLightRenderEffect.setLightPos$lambda$18(this.e, this.f, (RuntimeShader) obj);
                return;
            default:
                GuidingLightRenderEffect.setViewCenter$lambda$10(this.e, this.f, (RuntimeShader) obj);
                return;
        }
    }
}
