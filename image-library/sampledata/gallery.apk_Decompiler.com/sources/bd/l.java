package bd;

import android.graphics.Bitmap;
import android.graphics.RuntimeShader;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightRenderEffect;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Bitmap e;
    public final /* synthetic */ GuidingLightRenderEffect f;

    public /* synthetic */ l(Bitmap bitmap, GuidingLightRenderEffect guidingLightRenderEffect, int i2) {
        this.d = i2;
        this.e = bitmap;
        this.f = guidingLightRenderEffect;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                GuidingLightRenderEffect.setLightmap$lambda$15(this.e, this.f, (RuntimeShader) obj);
                return;
            default:
                GuidingLightRenderEffect.setLightmapGlow$lambda$17(this.e, this.f, (RuntimeShader) obj);
                return;
        }
    }
}
