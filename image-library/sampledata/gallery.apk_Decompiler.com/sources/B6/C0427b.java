package b6;

import android.animation.ValueAnimator;
import com.samsung.android.gallery.app.ui.list.stories.header.PinAnimHandler;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: b6.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0427b implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ PinAnimHandler d;
    public final /* synthetic */ AtomicBoolean e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ C0427b(PinAnimHandler pinAnimHandler, AtomicBoolean atomicBoolean, boolean z) {
        this.d = pinAnimHandler;
        this.e = atomicBoolean;
        this.f = z;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.d.lambda$show$1(this.e, this.f, valueAnimator);
    }
}
