package Me;

import Ze.x;
import java.util.LinkedHashSet;
import kotlin.jvm.internal.j;
import ne.C1195m;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public static final LinkedHashSet f3534a;
    public static final C1235b b;

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (C1236c cVar : C1195m.q0(x.f3963a, x.f3965h, x.f3966i, x.f3964c, x.d, x.f)) {
            j.e(cVar, "topLevelFqName");
            C1236c e = cVar.e();
            C1240g f = cVar.f();
            j.d(f, "shortName(...)");
            linkedHashSet.add(new C1235b(e, f));
        }
        f3534a = linkedHashSet;
        C1236c cVar2 = x.g;
        j.d(cVar2, "REPEATABLE_ANNOTATION");
        C1236c e7 = cVar2.e();
        C1240g f5 = cVar2.f();
        j.d(f5, "shortName(...)");
        b = new C1235b(e7, f5);
    }
}
