package dd;

import android.animation.ValueAnimator;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.RadialGradControl;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.RadialGradRenderEffect;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.SpotConfig;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkVideoObjectRemover;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Ae.b {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f1725h;

    public /* synthetic */ b(Object obj, Object obj2, Object obj3, int i2, int i7) {
        this.d = i7;
        this.f = obj;
        this.g = obj2;
        this.f1725h = obj3;
        this.e = i2;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                return RadialGradControl.buildAnimations$lambda$10$lambda$9$buildWiggle$lambda$8((RadialGradControl) this.f, (SpotConfig) this.g, (RadialGradRenderEffect) this.f1725h, this.e, (ValueAnimator) obj);
            default:
                return VexFwkVideoObjectRemover.removeObject$lambda$9$lambda$3((VexFwkVideoObjectRemover.ObjectMask) this.f, (String) this.g, (String) this.f1725h, this.e, (VexFwkMetadataNative) obj);
        }
    }
}
