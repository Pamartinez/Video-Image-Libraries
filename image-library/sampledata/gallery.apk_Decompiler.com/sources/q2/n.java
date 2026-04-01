package q2;

import android.view.ViewTreeObserver;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ o d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ u f;

    public n(o oVar, boolean z, u uVar) {
        this.d = oVar;
        this.e = z;
        this.f = uVar;
    }

    public final boolean onPreDraw() {
        boolean z;
        o oVar = this.d;
        if (!this.e || oVar.f1885i) {
            z = false;
        } else {
            z = true;
        }
        oVar.f1885i = false;
        oVar.d(C0267a.START_FIRST, z);
        oVar.d(C0267a.START_SECOND, z);
        oVar.d(C0267a.END_FIRST, z);
        this.f.getViewTreeObserver().removeOnPreDrawListener(this);
        return true;
    }
}
