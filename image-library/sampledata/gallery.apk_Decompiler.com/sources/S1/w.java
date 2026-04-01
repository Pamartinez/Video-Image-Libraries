package S1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class w extends CoordinatorLayout.Behavior {
    public x d;
    public int e = 0;

    public w() {
    }

    public final int a() {
        x xVar = this.d;
        if (xVar != null) {
            return xVar.d;
        }
        return 0;
    }

    public void b(CoordinatorLayout coordinatorLayout, View view, int i2) {
        coordinatorLayout.onLayoutChild(view, i2);
    }

    public final boolean c(int i2) {
        x xVar = this.d;
        if (xVar != null) {
            return xVar.b(i2);
        }
        this.e = i2;
        return false;
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
        b(coordinatorLayout, view, i2);
        if (this.d == null) {
            this.d = new x(view);
        }
        x xVar = this.d;
        View view2 = xVar.f792a;
        xVar.b = view2.getTop();
        xVar.f793c = view2.getLeft();
        this.d.a();
        int i7 = this.e;
        if (i7 == 0) {
            return true;
        }
        this.d.b(i7);
        this.e = 0;
        return true;
    }

    public w(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
