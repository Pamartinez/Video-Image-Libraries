package ne;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.j;
import me.i;

/* renamed from: ne.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1196n extends C1195m {
    public static int w0(Iterable iterable, int i2) {
        j.e(iterable, "<this>");
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        return i2;
    }

    public static ArrayList x0(Iterable iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            C1200r.A0((Iterable) it.next(), arrayList);
        }
        return arrayList;
    }

    public static i y0(ArrayList arrayList) {
        int w02 = w0(arrayList, 10);
        ArrayList arrayList2 = new ArrayList(w02);
        ArrayList arrayList3 = new ArrayList(w02);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            i iVar = (i) it.next();
            arrayList2.add(iVar.d);
            arrayList3.add(iVar.e);
        }
        return new i(arrayList2, arrayList3);
    }
}
