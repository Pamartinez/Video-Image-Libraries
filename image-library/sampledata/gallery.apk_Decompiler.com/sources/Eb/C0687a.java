package eb;

import android.app.Activity;
import com.samsung.android.gallery.widget.animations.QuickViewTransitionAnimation;

/* renamed from: eb.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0687a implements Activity.SemTranslucentConversionListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ QuickViewTransitionAnimation f3263a;

    public /* synthetic */ C0687a(QuickViewTransitionAnimation quickViewTransitionAnimation) {
        this.f3263a = quickViewTransitionAnimation;
    }

    public final void onTranslucentConversionCompleted(boolean z) {
        this.f3263a.onConverted(z);
    }
}
