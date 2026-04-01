package ne;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kotlin.jvm.internal.j;

/* renamed from: ne.q  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1199q extends C1198p {
    public static void z0(List list, Comparator comparator) {
        j.e(list, "<this>");
        if (list.size() > 1) {
            Collections.sort(list, comparator);
        }
    }
}
