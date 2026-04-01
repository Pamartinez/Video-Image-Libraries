package V1;

import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import com.google.android.material.behavior.SwipeDismissBehavior;
import z2.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements AccessibilityViewCommand {
    public final /* synthetic */ SwipeDismissBehavior d;

    public b(SwipeDismissBehavior swipeDismissBehavior) {
        this.d = swipeDismissBehavior;
    }

    public final boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
        int i2;
        SwipeDismissBehavior swipeDismissBehavior = this.d;
        boolean z = false;
        if (!swipeDismissBehavior.a(view)) {
            return false;
        }
        if (ViewCompat.getLayoutDirection(view) == 1) {
            z = true;
        }
        int i7 = swipeDismissBehavior.f1402h;
        if ((i7 != 0 || !z) && (i7 != 1 || z)) {
            i2 = view.getWidth();
        } else {
            i2 = -view.getWidth();
        }
        ViewCompat.offsetLeftAndRight(view, i2);
        view.setAlpha(0.0f);
        l lVar = swipeDismissBehavior.e;
        if (lVar != null) {
            lVar.a(view);
        }
        return true;
    }
}
