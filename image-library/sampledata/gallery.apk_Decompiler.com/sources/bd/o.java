package bd;

import android.graphics.PointF;
import android.graphics.RuntimeShader;
import android.graphics.Shader;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightRenderEffect;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Consumer {
    public final /* synthetic */ GuidingLightRenderEffect d;
    public final /* synthetic */ Shader e;
    public final /* synthetic */ PointF f;

    public /* synthetic */ o(GuidingLightRenderEffect guidingLightRenderEffect, Shader shader, PointF pointF) {
        this.d = guidingLightRenderEffect;
        this.e = shader;
        this.f = pointF;
    }

    public final void accept(Object obj) {
        GuidingLightRenderEffect.setTintShader$lambda$33(this.d, this.e, this.f, (RuntimeShader) obj);
    }
}
