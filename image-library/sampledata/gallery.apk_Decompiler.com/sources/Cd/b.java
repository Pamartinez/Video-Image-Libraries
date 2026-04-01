package cd;

import android.graphics.RuntimeShader;
import com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightRenderEffect;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ ProcessingLightRenderEffect f;

    public /* synthetic */ b(int i2, ProcessingLightRenderEffect processingLightRenderEffect, int i7) {
        this.d = i7;
        this.e = i2;
        this.f = processingLightRenderEffect;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ProcessingLightRenderEffect.setLightColor$lambda$9(this.e, this.f, (RuntimeShader) obj);
                return;
            default:
                ProcessingLightRenderEffect.setDomainColor$lambda$17(this.e, this.f, (RuntimeShader) obj);
                return;
        }
    }
}
