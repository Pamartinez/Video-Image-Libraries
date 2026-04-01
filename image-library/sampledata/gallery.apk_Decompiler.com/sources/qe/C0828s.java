package Qe;

import Ae.b;
import Te.B;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1194l;
import qf.C1235b;

/* renamed from: Qe.s  reason: case insensitive filesystem */
public final class C0828s implements b {
    public static final C0828s e = new C0828s(0);
    public static final C0828s f = new C0828s(1);
    public static final C0828s g = new C0828s(2);

    /* renamed from: h  reason: collision with root package name */
    public static final C0828s f3682h = new C0828s(3);
    public final /* synthetic */ int d;

    public /* synthetic */ C0828s(int i2) {
        this.d = i2;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                j.e((C1235b) obj, "it");
                return 0;
            case 1:
                H h5 = (H) obj;
                j.e(h5, "it");
                return ((B) h5).f3743i;
            case 2:
                C0822l lVar = (C0822l) obj;
                j.e(lVar, "it");
                return Boolean.valueOf(!(lVar instanceof C0821k));
            default:
                C0822l lVar2 = (C0822l) obj;
                j.e(lVar2, "it");
                List typeParameters = ((C0812b) lVar2).getTypeParameters();
                j.d(typeParameters, "getTypeParameters(...)");
                return C1194l.F0(typeParameters);
        }
    }
}
