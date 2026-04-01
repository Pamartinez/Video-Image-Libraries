package md;

import android.graphics.Bitmap;
import com.samsung.android.vexfwk.sdk.bokeheffect.VexFwkPortraitBokehEffect;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ VexFwkPortraitBokehEffect e;
    public final /* synthetic */ Bitmap f;
    public final /* synthetic */ VexFwkPortraitBokehEffect.BokehEffect g;

    public /* synthetic */ a(VexFwkPortraitBokehEffect vexFwkPortraitBokehEffect, Bitmap bitmap, VexFwkPortraitBokehEffect.BokehEffect bokehEffect, int i2) {
        this.d = i2;
        this.e = vexFwkPortraitBokehEffect;
        this.f = bitmap;
        this.g = bokehEffect;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return VexFwkPortraitBokehEffect.applyEffect$lambda$2(this.e, this.f, this.g);
            default:
                return VexFwkPortraitBokehEffect.applyEffectWithSegMapOutput$lambda$5(this.e, this.f, this.g);
        }
    }
}
