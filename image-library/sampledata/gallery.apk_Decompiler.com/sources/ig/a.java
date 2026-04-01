package ig;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1202t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final String f4601a;
    public List b = C1202t.d;

    /* renamed from: c  reason: collision with root package name */
    public final ArrayList f4602c = new ArrayList();
    public final HashSet d = new HashSet();
    public final ArrayList e = new ArrayList();
    public final ArrayList f = new ArrayList();
    public final ArrayList g = new ArrayList();

    public a(String str) {
        j.e(str, "serialName");
        this.f4601a = str;
    }

    public static void a(a aVar, String str, f fVar) {
        aVar.getClass();
        j.e(str, "elementName");
        j.e(fVar, "descriptor");
        if (aVar.d.add(str)) {
            aVar.f4602c.add(str);
            aVar.e.add(fVar);
            aVar.f.add(C1202t.d);
            aVar.g.add(false);
            return;
        }
        StringBuilder k = N2.j.k("Element with name '", str, "' is already registered in ");
        k.append(aVar.f4601a);
        throw new IllegalArgumentException(k.toString().toString());
    }
}
