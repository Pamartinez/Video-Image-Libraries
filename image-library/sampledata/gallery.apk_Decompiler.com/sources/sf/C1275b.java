package sf;

import Qe.C0816f;
import Qe.C0819i;
import Qe.C0822l;
import Qe.H;
import Qe.V;
import Te.B;
import a.C0068a;
import java.util.ArrayList;
import kotlin.jvm.internal.j;
import ne.C1181B;
import qf.C1238e;
import qf.C1240g;
import tf.C1301e;

/* renamed from: sf.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1275b implements C1276c {
    public static final C1275b b = new C1275b(0);

    /* renamed from: c  reason: collision with root package name */
    public static final C1275b f5072c = new C1275b(1);
    public static final C1275b d = new C1275b(2);

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f5073a;

    public /* synthetic */ C1275b(int i2) {
        this.f5073a = i2;
    }

    public static String b(C0819i iVar) {
        String str;
        C1240g name = iVar.getName();
        j.d(name, "getName(...)");
        String S = C0068a.S(name);
        if (!(iVar instanceof V)) {
            C0822l g = iVar.g();
            j.d(g, "getContainingDeclaration(...)");
            if (g instanceof C0816f) {
                str = b((C0819i) g);
            } else if (g instanceof H) {
                C1238e i2 = ((B) ((H) g)).f3743i.i();
                j.d(i2, "toUnsafe(...)");
                str = C0068a.T(i2.e());
            } else {
                str = null;
            }
            if (str != null && !str.equals("")) {
                return str + '.' + S;
            }
        }
        return S;
    }

    public final String a(C0819i iVar, C1283j jVar) {
        boolean z;
        switch (this.f5073a) {
            case 0:
                if (iVar instanceof V) {
                    C1240g name = ((V) iVar).getName();
                    j.d(name, "getName(...)");
                    return jVar.O(name, false);
                }
                C1238e g = C1301e.g(iVar);
                j.d(g, "getFqName(...)");
                return jVar.o(C0068a.T(g.e()));
            case 1:
                if (iVar instanceof V) {
                    C1240g name2 = ((V) iVar).getName();
                    j.d(name2, "getName(...)");
                    return jVar.O(name2, false);
                }
                ArrayList arrayList = new ArrayList();
                C0822l lVar = iVar;
                do {
                    arrayList.add(lVar.getName());
                    C0822l g3 = lVar.g();
                    z = g3 instanceof C0816f;
                    lVar = g3;
                } while (z);
                return C0068a.T(new C1181B(arrayList));
            default:
                return b(iVar);
        }
    }
}
