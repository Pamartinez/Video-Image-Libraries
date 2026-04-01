package E2;

import G0.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o {

    /* renamed from: a  reason: collision with root package name */
    public final b f173a;
    public final c b;

    public o(c cVar, b bVar) {
        this.b = cVar;
        this.f173a = bVar;
    }

    public static o a(char c5) {
        return new o(new c(3, (Object) new c(c5)), e.e);
    }

    public final List b(CharSequence charSequence) {
        charSequence.getClass();
        n nVar = new n(this.b, this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (nVar.hasNext()) {
            arrayList.add((String) nVar.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
