package af;

import B0.a;
import Ne.p;
import We.C0892d;
import We.C0893e;
import Ze.x;
import a.C0068a;
import df.C0943f;
import gf.C1071b;
import kotlin.jvm.internal.j;
import me.i;
import ne.z;
import og.k;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;

/* renamed from: af.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0905c {

    /* renamed from: a  reason: collision with root package name */
    public static final C1240g f3992a = C1240g.e("message");
    public static final C1240g b = C1240g.e("allowedTargets");

    /* renamed from: c  reason: collision with root package name */
    public static final C1240g f3993c = C1240g.e("value");
    public static final Object d = z.b0(new i(p.t, x.f3964c), new i(p.w, x.d), new i(p.f3573x, x.f));

    /* JADX WARNING: type inference failed for: r0v5, types: [java.util.Map, java.lang.Object] */
    public static bf.i a(C1236c cVar, C1071b bVar, a aVar) {
        C0893e a7;
        j.e(cVar, "kotlinName");
        j.e(bVar, "annotationOwner");
        j.e(aVar, "c");
        if (cVar.equals(p.m)) {
            C1236c cVar2 = x.e;
            j.d(cVar2, "DEPRECATED_ANNOTATION");
            C0893e a10 = bVar.a(cVar2);
            if (a10 != null) {
                return new C0909g(a10, aVar);
            }
        }
        C1236c cVar3 = (C1236c) d.get(cVar);
        if (cVar3 == null || (a7 = bVar.a(cVar3)) == null) {
            return null;
        }
        return b(aVar, a7, false);
    }

    public static bf.i b(a aVar, C0893e eVar, boolean z) {
        j.e(eVar, "annotation");
        j.e(aVar, "c");
        C1235b a7 = C0892d.a(C0068a.A(C0068a.w(eVar.f3887a)));
        C1236c cVar = x.f3964c;
        j.d(cVar, "TARGET_ANNOTATION");
        if (a7.equals(k.U(cVar))) {
            return new C0912j(eVar, aVar);
        }
        C1236c cVar2 = x.d;
        j.d(cVar2, "RETENTION_ANNOTATION");
        if (a7.equals(k.U(cVar2))) {
            return new C0911i(eVar, aVar);
        }
        C1236c cVar3 = x.f;
        j.d(cVar3, "DOCUMENTED_ANNOTATION");
        if (a7.equals(k.U(cVar3))) {
            return new C0904b(aVar, eVar, p.f3573x);
        }
        C1236c cVar4 = x.e;
        j.d(cVar4, "DEPRECATED_ANNOTATION");
        if (a7.equals(k.U(cVar4))) {
            return null;
        }
        return new C0943f(aVar, eVar, z);
    }
}
