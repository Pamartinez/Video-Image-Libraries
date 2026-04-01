package ne;

import Be.b;
import java.util.AbstractList;
import java.util.List;

/* renamed from: ne.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1188f extends AbstractList implements List, b {
    public abstract int p();

    public abstract Object q(int i2);

    public final /* bridge */ Object remove(int i2) {
        return q(i2);
    }

    public final /* bridge */ int size() {
        return p();
    }
}
