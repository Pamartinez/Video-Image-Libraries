package cd;

import android.graphics.RuntimeShader;
import com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightRenderEffect;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ProcessingLightRenderEffect e;
    public final /* synthetic */ float f;

    public /* synthetic */ a(ProcessingLightRenderEffect processingLightRenderEffect, float f5, int i2) {
        this.d = i2;
        this.e = processingLightRenderEffect;
        this.f = f5;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ProcessingLightRenderEffect.setDomainStrength$lambda$19(this.e, this.f, (RuntimeShader) obj);
                return;
            case 1:
                ProcessingLightRenderEffect.setLightScale$lambda$13(this.e, this.f, (RuntimeShader) obj);
                return;
            case 2:
                ProcessingLightRenderEffect.setLightSaturation$lambda$16(this.e, this.f, (RuntimeShader) obj);
                return;
            case 3:
                ProcessingLightRenderEffect.setStretch$lambda$14(this.e, this.f, (RuntimeShader) obj);
                return;
            case 4:
                ProcessingLightRenderEffect.setDomainDeltaRatio$lambda$18(this.e, this.f, (RuntimeShader) obj);
                return;
            case 5:
                ProcessingLightRenderEffect.setLightIntensity$lambda$15(this.e, this.f, (RuntimeShader) obj);
                return;
            default:
                ProcessingLightRenderEffect.setLightRotation$lambda$10(this.e, this.f, (RuntimeShader) obj);
                return;
        }
    }
}
