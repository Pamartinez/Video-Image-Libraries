package bd;

import android.graphics.RuntimeShader;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightRenderEffect;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ GuidingLightRenderEffect e;
    public final /* synthetic */ float f;

    public /* synthetic */ k(GuidingLightRenderEffect guidingLightRenderEffect, float f5, int i2) {
        this.d = i2;
        this.e = guidingLightRenderEffect;
        this.f = f5;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                GuidingLightRenderEffect.setViewCornerRadius$lambda$13(this.e, this.f, (RuntimeShader) obj);
                return;
            case 1:
                GuidingLightRenderEffect.setOutlineThickness$lambda$36(this.e, this.f, (RuntimeShader) obj);
                return;
            case 2:
                GuidingLightRenderEffect.setLightIntensity$lambda$20(this.e, this.f, (RuntimeShader) obj);
                return;
            case 3:
                GuidingLightRenderEffect.setReflLightIntensity$lambda$26(this.e, this.f, (RuntimeShader) obj);
                return;
            case 4:
                GuidingLightRenderEffect.setDitherVariation$lambda$28(this.e, this.f, (RuntimeShader) obj);
                return;
            case 5:
                GuidingLightRenderEffect.setViewAlpha$lambda$4(this.e, this.f, (RuntimeShader) obj);
                return;
            case 6:
                GuidingLightRenderEffect.setGlowSharpness$lambda$24(this.e, this.f, (RuntimeShader) obj);
                return;
            case 7:
                GuidingLightRenderEffect.setBoundarySmoothWidth$lambda$37(this.e, this.f, (RuntimeShader) obj);
                return;
            case 8:
                GuidingLightRenderEffect.setOuterSaturation$lambda$9(this.e, this.f, (RuntimeShader) obj);
                return;
            case 9:
                GuidingLightRenderEffect.setReflAlbedo$lambda$27(this.e, this.f, (RuntimeShader) obj);
                return;
            case 10:
                GuidingLightRenderEffect.setGlobalLuminance$lambda$8(this.e, this.f, (RuntimeShader) obj);
                return;
            case 11:
                GuidingLightRenderEffect.setStretch$lambda$5(this.e, this.f, (RuntimeShader) obj);
                return;
            case 12:
                GuidingLightRenderEffect.setProgress$lambda$3(this.e, this.f, (RuntimeShader) obj);
                return;
            case 13:
                GuidingLightRenderEffect.setReflLightRadius$lambda$25(this.e, this.f, (RuntimeShader) obj);
                return;
            case 14:
                GuidingLightRenderEffect.setGlowIntensity$lambda$22(this.e, this.f, (RuntimeShader) obj);
                return;
            case 15:
                GuidingLightRenderEffect.setGlowRadius$lambda$23(this.e, this.f, (RuntimeShader) obj);
                return;
            case 16:
                GuidingLightRenderEffect.setLightRadius$lambda$19(this.e, this.f, (RuntimeShader) obj);
                return;
            case 17:
                GuidingLightRenderEffect.setStretchDirLit$lambda$6(this.e, this.f, (RuntimeShader) obj);
                return;
            default:
                GuidingLightRenderEffect.setSaturation$lambda$7(this.e, this.f, (RuntimeShader) obj);
                return;
        }
    }
}
