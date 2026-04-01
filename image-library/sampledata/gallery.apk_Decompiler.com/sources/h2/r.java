package h2;

import android.view.View;
import androidx.core.view.ViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r implements View.OnAttachStateChangeListener {
    public final void onViewAttachedToWindow(View view) {
        view.removeOnAttachStateChangeListener(this);
        ViewCompat.requestApplyInsets(view);
    }

    public final void onViewDetachedFromWindow(View view) {
    }
}
