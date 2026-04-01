package af;

import Ne.p;
import Re.m;
import Re.o;
import We.t;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1196n;
import ne.C1200r;
import ne.v;
import ne.z;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;
import vf.C1322b;
import vf.C1329i;

/* renamed from: af.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0907e {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f3994a = z.b0(new i("PACKAGE", EnumSet.noneOf(o.class)), new i("TYPE", EnumSet.of(o.CLASS, o.FILE)), new i("ANNOTATION_TYPE", EnumSet.of(o.ANNOTATION_CLASS)), new i("TYPE_PARAMETER", EnumSet.of(o.TYPE_PARAMETER)), new i("FIELD", EnumSet.of(o.FIELD)), new i("LOCAL_VARIABLE", EnumSet.of(o.LOCAL_VARIABLE)), new i("PARAMETER", EnumSet.of(o.VALUE_PARAMETER)), new i("CONSTRUCTOR", EnumSet.of(o.CONSTRUCTOR)), new i("METHOD", EnumSet.of(o.FUNCTION, o.PROPERTY_GETTER, o.PROPERTY_SETTER)), new i("TYPE_USE", EnumSet.of(o.TYPE)));
    public static final Object b = z.b0(new i("RUNTIME", m.RUNTIME), new i("CLASS", m.BINARY), new i("SOURCE", m.SOURCE));

    /* JADX WARNING: type inference failed for: r2v1, types: [java.util.Map, java.lang.Object] */
    public static C1322b a(List list) {
        ArrayList arrayList = new ArrayList();
        for (Object next : list) {
            if (next instanceof t) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Iterable iterable = (EnumSet) f3994a.get(C1240g.e(((t) it.next()).b.name()).b());
            if (iterable == null) {
                iterable = v.d;
            }
            C1200r.A0(iterable, arrayList2);
        }
        ArrayList arrayList3 = new ArrayList(C1196n.w0(arrayList2, 10));
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            C1236c cVar = p.u;
            j.e(cVar, "topLevelFqName");
            C1236c e = cVar.e();
            C1240g f = cVar.f();
            j.d(f, "shortName(...)");
            arrayList3.add(new C1329i(new C1235b(e, f), C1240g.e(((o) it2.next()).name())));
        }
        return new C1322b(arrayList3, C0906d.d);
    }
}
