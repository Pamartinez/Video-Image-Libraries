package V1;

import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.behavior.SwipeDismissBehavior;
import ge.C1019e0;
import ge.C1031i0;
import z2.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c implements Runnable {
    public final /* synthetic */ int d;
    public final boolean e;
    public final Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ c(Object obj, Object obj2, boolean z, int i2) {
        this.d = i2;
        this.g = obj;
        this.f = obj2;
        this.e = z;
    }

    public final void run() {
        l lVar;
        switch (this.d) {
            case 0:
                View view = (View) this.f;
                SwipeDismissBehavior swipeDismissBehavior = (SwipeDismissBehavior) this.g;
                ViewDragHelper viewDragHelper = swipeDismissBehavior.d;
                if (viewDragHelper != null && viewDragHelper.continueSettling(true)) {
                    ViewCompat.postOnAnimation(view, this);
                    return;
                } else if (this.e && (lVar = swipeDismissBehavior.e) != null) {
                    lVar.a(view);
                    return;
                } else {
                    return;
                }
            default:
                ((C1031i0) this.g).t.A0((C1019e0) this.f, this.e);
                return;
        }
    }
}
