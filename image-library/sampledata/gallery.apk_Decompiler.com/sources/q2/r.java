package q2;

import android.os.Handler;
import com.google.android.material.appbar.AppBarLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ u f1888a;

    public r(u uVar) {
        this.f1888a = uVar;
    }

    public final void a(int i2) {
        u uVar = this.f1888a;
        Handler handler = uVar.f1903x;
        c cVar = uVar.y;
        handler.removeCallbacks(cVar);
        handler.postDelayed(cVar, 300);
        uVar.i();
        AppBarLayout appBarLayout$material_release = uVar.getAppBarLayout$material_release();
        if (appBarLayout$material_release == null) {
            uVar.d(i2);
        } else if (appBarLayout$material_release.seslIsHided()) {
            uVar.d(i2);
        }
        if (uVar.getVisibleState() == v.STATE_HIDE || uVar.getVisibleState() == v.STATE_ANIMATING_TO_HIDE) {
            Handler handler2 = uVar.z;
            c cVar2 = uVar.f1891A;
            handler2.removeCallbacks(cVar2);
            handler2.postDelayed(cVar2, 300);
        }
    }
}
