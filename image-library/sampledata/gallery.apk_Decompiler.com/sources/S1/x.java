package S1;

import android.view.View;
import androidx.core.view.ViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x {

    /* renamed from: a  reason: collision with root package name */
    public final View f792a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public int f793c;
    public int d;

    public x(View view) {
        this.f792a = view;
    }

    public final void a() {
        int i2 = this.d;
        View view = this.f792a;
        ViewCompat.offsetTopAndBottom(view, i2 - (view.getTop() - this.b));
        ViewCompat.offsetLeftAndRight(view, 0 - (view.getLeft() - this.f793c));
    }

    public final boolean b(int i2) {
        if (this.d == i2) {
            return false;
        }
        this.d = i2;
        a();
        return true;
    }
}
