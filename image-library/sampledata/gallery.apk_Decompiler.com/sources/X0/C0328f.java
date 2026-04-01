package x0;

import android.content.Context;
import com.airbnb.lottie.LottieAnimationView;
import java.util.concurrent.Callable;

/* renamed from: x0.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0328f implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LottieAnimationView f2051a;
    public final /* synthetic */ int b;

    public /* synthetic */ C0328f(LottieAnimationView lottieAnimationView, int i2) {
        this.f2051a = lottieAnimationView;
        this.b = i2;
    }

    public final Object call() {
        LottieAnimationView lottieAnimationView = this.f2051a;
        boolean z = lottieAnimationView.m;
        int i2 = this.b;
        if (!z) {
            return n.e(lottieAnimationView.getContext(), i2, (String) null);
        }
        Context context = lottieAnimationView.getContext();
        return n.e(context, i2, n.j(context, i2));
    }
}
