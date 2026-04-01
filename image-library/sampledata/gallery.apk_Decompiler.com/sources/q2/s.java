package q2;

import B2.p;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s implements View.OnAttachStateChangeListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ViewGroup e;

    public /* synthetic */ s(ViewGroup viewGroup, int i2) {
        this.d = i2;
        this.e = viewGroup;
    }

    public final void onViewAttachedToWindow(View view) {
        switch (this.d) {
            case 0:
            case 1:
                j.e(view, "v");
                return;
            default:
                p pVar = (p) this.e;
                AccessibilityManager accessibilityManager = pVar.w;
                if (pVar.f61x != null && accessibilityManager != null && ViewCompat.isAttachedToWindow(pVar)) {
                    AccessibilityManagerCompat.addTouchExplorationStateChangeListener(accessibilityManager, pVar.f61x);
                    return;
                }
                return;
        }
    }

    public final void onViewDetachedFromWindow(View view) {
        AccessibilityManager accessibilityManager;
        switch (this.d) {
            case 0:
                j.e(view, "view");
                u uVar = (u) this.e;
                if (j.a(uVar.getNestedScrollView(), view)) {
                    uVar.e();
                    return;
                }
                return;
            case 1:
                j.e(view, "view");
                u uVar2 = (u) this.e;
                if (j.a(uVar2.getRecyclerView(), view)) {
                    uVar2.e();
                    return;
                }
                return;
            default:
                p pVar = (p) this.e;
                AccessibilityManagerCompat.TouchExplorationStateChangeListener touchExplorationStateChangeListener = pVar.f61x;
                if (touchExplorationStateChangeListener != null && (accessibilityManager = pVar.w) != null) {
                    AccessibilityManagerCompat.removeTouchExplorationStateChangeListener(accessibilityManager, touchExplorationStateChangeListener);
                    return;
                }
                return;
        }
    }
}
