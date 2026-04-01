package x0;

import com.airbnb.lottie.LottieAnimationView;
import java.lang.ref.WeakReference;

/* renamed from: x0.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0331i implements z {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2055a;
    public final WeakReference b;

    public C0331i(LottieAnimationView lottieAnimationView, int i2) {
        this.f2055a = i2;
        switch (i2) {
            case 1:
                this.b = new WeakReference(lottieAnimationView);
                return;
            default:
                this.b = new WeakReference(lottieAnimationView);
                return;
        }
    }

    public final void onResult(Object obj) {
        switch (this.f2055a) {
            case 0:
                Throwable th = (Throwable) obj;
                LottieAnimationView lottieAnimationView = (LottieAnimationView) this.b.get();
                if (lottieAnimationView != null) {
                    int i2 = lottieAnimationView.g;
                    if (i2 != 0) {
                        lottieAnimationView.setImageResource(i2);
                    }
                    z zVar = lottieAnimationView.f;
                    if (zVar == null) {
                        zVar = LottieAnimationView.q;
                    }
                    zVar.onResult(th);
                    return;
                }
                return;
            default:
                C0332j jVar = (C0332j) obj;
                LottieAnimationView lottieAnimationView2 = (LottieAnimationView) this.b.get();
                if (lottieAnimationView2 != null) {
                    lottieAnimationView2.setComposition(jVar);
                    return;
                }
                return;
        }
    }
}
