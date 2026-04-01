package Ib;

import android.view.View;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ float d;

    public /* synthetic */ a(float f) {
        this.d = f;
    }

    public final void onAnimationEnd(View view) {
        view.setAlpha(this.d);
    }
}
