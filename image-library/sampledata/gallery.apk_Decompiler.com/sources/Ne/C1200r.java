package ne;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.j;

/* renamed from: ne.r  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1200r extends C1199q {
    public static void A0(Iterable iterable, Collection collection) {
        j.e(collection, "<this>");
        j.e(iterable, "elements");
        if (iterable instanceof Collection) {
            collection.addAll((Collection) iterable);
            return;
        }
        for (Object add : iterable) {
            collection.add(add);
        }
    }

    public static void B0(AbstractList abstractList, Object[] objArr) {
        j.e(objArr, "elements");
        abstractList.addAll(C1192j.a0(objArr));
    }

    public static Object C0(List list) {
        j.e(list, "<this>");
        if (!list.isEmpty()) {
            return list.remove(C1195m.p0(list));
        }
        throw new NoSuchElementException("List is empty.");
    }
}
