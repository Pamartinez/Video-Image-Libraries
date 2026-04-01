package B2;

import B1.b;
import P1.e;
import Xd.a;
import Y1.f;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import ee.G;
import ee.a0;
import ge.A0;
import ge.A1;
import ge.B0;
import ge.C1002C;
import ge.C1003D;
import ge.C1016d0;
import ge.C1031i0;
import ge.C1057t0;
import ge.C1059u0;
import ge.E0;
import ge.F0;
import ge.K;
import ge.P0;
import ge.T0;
import ge.V0;
import ge.t1;
import ge.v1;
import java.util.LinkedHashSet;
import t1.C0276a;
import u1.C0285c;
import v1.i;
import v1.k;
import v1.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class A implements Runnable {
    public final /* synthetic */ int d;
    public final Object e;

    public /* synthetic */ A(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                CheckableImageButton checkableImageButton = ((TextInputLayout) obj).f.f58j;
                checkableImageButton.performClick();
                checkableImageButton.jumpDrawablesToCurrentState();
                return;
            case 1:
                a aVar = (a) obj;
                aVar.run();
                aVar.onFinish();
                return;
            case 2:
                f fVar = (f) obj;
                fVar.b = false;
                ViewDragHelper viewDragHelper = ((BottomSheetBehavior) fVar.e).f1416P;
                if (viewDragHelper == null || !viewDragHelper.continueSettling(true)) {
                    BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) fVar.e;
                    if (bottomSheetBehavior.f1415O == 2) {
                        bottomSheetBehavior.setStateInternal(fVar.f951c);
                        return;
                    }
                    return;
                }
                fVar.b(fVar.f951c);
                return;
            case 3:
                ((C1003D) obj).f4388i.d();
                return;
            case 4:
                ((C1002C) obj).f4386c.h();
                return;
            case 5:
                ((K) obj).d.b0();
                return;
            case 6:
                C1031i0 i0Var = (C1031i0) ((e) obj).f;
                P0 p02 = i0Var.r;
                i0Var.q = null;
                i0Var.r = null;
                p02.d(a0.f4289o.g("InternalSubchannel closed transport due to address change"));
                return;
            case 7:
                ((C1059u0) obj).b.k();
                return;
            case 8:
                F0 f02 = (F0) ((D0.e) obj).f;
                f02.m.d();
                if (f02.w) {
                    f02.v.g();
                    return;
                }
                return;
            case 9:
                A0 a0 = (A0) obj;
                B0 b0 = a0.r;
                B0 b02 = a0.r;
                LinkedHashSet linkedHashSet = b0.d.B;
                if (linkedHashSet != null) {
                    linkedHashSet.remove(a0);
                    if (b02.d.B.isEmpty()) {
                        F0 f03 = b02.d;
                        f03.Z.A0(f03.f4405C, false);
                        F0 f04 = b02.d;
                        f04.B = null;
                        if (f04.f4407G.get()) {
                            b02.d.f4406F.P(F0.f4400f0);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            case 10:
                C1031i0 i0Var2 = ((E0) obj).f;
                i0Var2.k.execute(new C1016d0(i0Var2, F0.f4401g0, 0));
                return;
            case 11:
                V0 v02 = (V0) obj;
                v02.f4484i = null;
                if (v02.f.b()) {
                    v02.o();
                    return;
                }
                return;
            case 12:
                ((E0) ((T0) obj).e).a();
                return;
            case 13:
                C1057t0 t0Var = (C1057t0) obj;
                if (!t0Var.f4552C) {
                    t0Var.f4560x.b0();
                    return;
                }
                return;
            case 14:
                t1 t1Var = (t1) obj;
                v1 v1Var = t1Var.e;
                G g = C1057t0.f4547H;
                ((C1057t0) t1Var.f.f).l(v1Var);
                return;
            case 15:
                ((A1) obj).g();
                return;
            case 16:
                ((k) obj).g();
                return;
            case 17:
                C0285c cVar = ((k) ((b) obj).e).b;
                cVar.a(cVar.getClass().getName().concat(" disconnecting because it was signed out."));
                return;
            case 18:
                ((r) obj).f1978h.b(new C0276a(4));
                return;
            default:
                throw null;
        }
    }

    public A(i iVar, Q2.a aVar) {
        this.d = 19;
        this.e = aVar;
    }
}
