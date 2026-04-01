package rf;

import java.io.Serializable;
import java.util.Collections;

/* renamed from: rf.q  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1267q extends C1252b implements Serializable {
    public static C1266p e(C1252b bVar, C1252b bVar2, int i2, Q q, Class cls) {
        return new C1266p(bVar, Collections.EMPTY_LIST, bVar2, new C1265o(i2, q, true), cls);
    }

    public static C1266p f(C1252b bVar, Object obj, C1252b bVar2, int i2, Q q, Class cls) {
        int i7 = i2;
        C1252b bVar3 = bVar2;
        return new C1266p(bVar, obj, bVar3, new C1265o(i7, q, false), cls);
    }
}
