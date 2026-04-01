package ne;

import B1.a;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.j;

/* renamed from: ne.C  reason: case insensitive filesystem */
public abstract class C1182C extends a {
    public static LinkedHashSet b0(Set set, Iterable iterable) {
        Integer num;
        int i2;
        j.e(set, "<this>");
        j.e(iterable, "elements");
        if (iterable instanceof Collection) {
            num = Integer.valueOf(((Collection) iterable).size());
        } else {
            num = null;
        }
        if (num != null) {
            i2 = set.size() + num.intValue();
        } else {
            i2 = set.size() * 2;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(z.Z(i2));
        linkedHashSet.addAll(set);
        C1200r.A0(iterable, linkedHashSet);
        return linkedHashSet;
    }

    public static LinkedHashSet c0(Set set, Object obj) {
        j.e(set, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet(z.Z(set.size() + 1));
        linkedHashSet.addAll(set);
        linkedHashSet.add(obj);
        return linkedHashSet;
    }
}
