package dd;

import android.animation.ValueAnimator;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.WiggleAnimationConfig;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ WiggleAnimationConfig d;
    public final /* synthetic */ Long[] e;
    public final /* synthetic */ int f;
    public final /* synthetic */ HashMap g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ ValueAnimator f1726h;

    public /* synthetic */ g(WiggleAnimationConfig wiggleAnimationConfig, Long[] lArr, int i2, HashMap hashMap, ValueAnimator valueAnimator) {
        this.d = wiggleAnimationConfig;
        this.e = lArr;
        this.f = i2;
        this.g = hashMap;
        this.f1726h = valueAnimator;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        WiggleAnimationConfig.build$lambda$13$lambda$12(this.d, this.e, this.f, this.g, this.f1726h, valueAnimator);
    }
}
