package Re;

import Ne.g;
import Ne.i;
import Ne.p;
import kotlin.jvm.internal.j;
import ne.C1202t;
import ne.z;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;
import vf.C1322b;
import vf.C1327g;
import vf.C1329i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    public static final C1240g f3690a = C1240g.e("message");
    public static final C1240g b = C1240g.e("replaceWith");

    /* renamed from: c  reason: collision with root package name */
    public static final C1240g f3691c = C1240g.e("level");
    public static final C1240g d = C1240g.e("expression");
    public static final C1240g e = C1240g.e("imports");

    public static final j a(i iVar, String str, String str2, String str3) {
        j.e(iVar, "<this>");
        j.e(str, "message");
        j.e(str2, "replaceWith");
        j jVar = new j(iVar, p.f3571o, z.b0(new me.i(d, new C1327g(str2)), new me.i(e, new C1322b(C1202t.d, new g(iVar, 1)))));
        C1236c cVar = p.m;
        me.i iVar2 = new me.i(f3690a, new C1327g(str));
        me.i iVar3 = new me.i(b, new C1327g(jVar));
        C1236c cVar2 = p.n;
        j.e(cVar2, "topLevelFqName");
        C1236c e7 = cVar2.e();
        C1240g f = cVar2.f();
        j.d(f, "shortName(...)");
        return new j(iVar, cVar, z.b0(iVar2, iVar3, new me.i(f3691c, new C1329i(new C1235b(e7, f), C1240g.e(str3)))));
    }
}
