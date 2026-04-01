package F2;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class K0 extends AbstractSet {
    public boolean removeAll(Collection<Object> collection) {
        collection.getClass();
        if (collection instanceof u0) {
            collection = ((u0) collection).elementSet();
        }
        boolean z = false;
        if (!(collection instanceof Set) || collection.size() <= size()) {
            for (Object remove : collection) {
                z |= remove(remove);
            }
            return z;
        }
        Iterator it = iterator();
        while (it.hasNext()) {
            if (collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public boolean retainAll(Collection collection) {
        collection.getClass();
        return super.retainAll(collection);
    }
}
