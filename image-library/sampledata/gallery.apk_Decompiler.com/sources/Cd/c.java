package cd;

import android.graphics.Bitmap;
import android.graphics.RuntimeShader;
import com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightRenderEffect;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ProcessingLightRenderEffect e;
    public final /* synthetic */ Bitmap f;

    public /* synthetic */ c(ProcessingLightRenderEffect processingLightRenderEffect, Bitmap bitmap, int i2) {
        this.d = i2;
        this.e = processingLightRenderEffect;
        this.f = bitmap;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ProcessingLightRenderEffect.setLightMap$lambda$8(this.e, this.f, (RuntimeShader) obj);
                return;
            default:
                ProcessingLightRenderEffect.setTintTexture$lambda$4(this.e, this.f, (RuntimeShader) obj);
                return;
        }
    }
}
