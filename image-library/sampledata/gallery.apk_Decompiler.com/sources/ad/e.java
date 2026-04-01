package ad;

import android.view.View;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffectBase;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ VibeRenderEffectBase e;

    public /* synthetic */ e(VibeRenderEffectBase vibeRenderEffectBase, int i2) {
        this.d = i2;
        this.e = vibeRenderEffectBase;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        VibeRenderEffectBase vibeRenderEffectBase = this.e;
        View view = (View) obj;
        switch (i2) {
            case 0:
                VibeRenderEffectBase.destroy$lambda$6(vibeRenderEffectBase, view);
                return;
            default:
                VibeRenderEffectBase.update$lambda$9(vibeRenderEffectBase, view);
                return;
        }
    }
}
