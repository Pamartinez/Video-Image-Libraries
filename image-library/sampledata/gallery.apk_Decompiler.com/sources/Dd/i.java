package dd;

import android.animation.ValueAnimator;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.WiggleAnimationConfig;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ HashMap e;
    public final /* synthetic */ ValueAnimator f;

    public /* synthetic */ i(HashMap hashMap, ValueAnimator valueAnimator, int i2) {
        this.d = i2;
        this.e = hashMap;
        this.f = valueAnimator;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                WiggleAnimationConfig.build$lambda$13$lambda$12$lambda$9$lambda$8(this.e, this.f);
                return;
            default:
                WiggleAnimationConfig.build$lambda$13$lambda$12$lambda$11$lambda$10(this.e, this.f);
                return;
        }
    }
}
