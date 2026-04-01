package gg;

import java.util.List;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends f {
    public final List d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(List list, String str, b bVar) {
        super(str, bVar);
        j.e(list, "missingFields");
        this.d = list;
    }
}
