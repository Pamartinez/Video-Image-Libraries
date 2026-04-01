package Ke;

import B1.b;
import D1.f;
import Ff.k;
import Ff.m;
import Ff.u;
import Qe.C0822l;
import Qe.C0827q;
import Qe.H;
import Qe.O;
import Tf.n;
import Ze.w;
import a.C0068a;
import java.util.regex.Pattern;
import jf.C1109i;
import kotlin.jvm.internal.j;
import lf.C1157j;
import lf.G;
import nf.C1209f;
import of.e;
import pf.d;
import pf.i;
import qf.C1240g;
import qf.C1241h;
import rf.C1266p;

/* renamed from: Ke.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0796n extends C0068a {
    public final O d;
    public final G e;
    public final e f;
    public final C1209f g;

    /* renamed from: h  reason: collision with root package name */
    public final b f3503h;

    /* renamed from: i  reason: collision with root package name */
    public final String f3504i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0796n(O o2, G g3, e eVar, C1209f fVar, b bVar) {
        super(4);
        String str;
        String str2;
        String str3;
        j.e(g3, "proto");
        j.e(fVar, "nameResolver");
        j.e(bVar, "typeTable");
        this.d = o2;
        this.e = g3;
        this.f = eVar;
        this.g = fVar;
        this.f3503h = bVar;
        if ((eVar.e & 4) == 4) {
            str = fVar.getString(eVar.f4994h.f).concat(fVar.getString(eVar.f4994h.g));
        } else {
            d b = i.b(g3, fVar, bVar, true);
            if (b != null) {
                String str4 = b.d;
                String str5 = b.e;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(w.a(str4));
                C0822l g10 = o2.g();
                j.d(g10, "getContainingDeclaration(...)");
                if (!j.a(o2.getVisibility(), C0827q.d) || !(g10 instanceof k)) {
                    if (j.a(o2.getVisibility(), C0827q.f3675a) && (g10 instanceof H)) {
                        m mVar = ((u) o2).f3403I;
                        if (mVar instanceof C1109i) {
                            C1109i iVar = (C1109i) mVar;
                            if (iVar.e != null) {
                                StringBuilder sb3 = new StringBuilder("$");
                                String d2 = iVar.d.d();
                                j.d(d2, "getInternalName(...)");
                                sb3.append(C1240g.e(n.O0(d2, '/')).b());
                                str2 = sb3.toString();
                            }
                        }
                    }
                    str2 = "";
                } else {
                    C1157j jVar = ((k) g10).f3386h;
                    C1266p pVar = of.k.f5011i;
                    j.d(pVar, "classModuleName");
                    Integer num = (Integer) f.q(jVar, pVar);
                    if (num != null) {
                        str3 = fVar.getString(num.intValue());
                    } else {
                        str3 = "main";
                    }
                    String replaceAll = ((Pattern) C1241h.f5040a.e).matcher(str3).replaceAll("_");
                    j.d(replaceAll, "replaceAll(...)");
                    str2 = "$".concat(replaceAll);
                }
                str = N2.j.f(sb2, str2, "()", str5);
            } else {
                throw new v0("No field signature for property: " + o2, 0);
            }
        }
        this.f3504i = str;
    }

    public final String h() {
        return this.f3504i;
    }
}
