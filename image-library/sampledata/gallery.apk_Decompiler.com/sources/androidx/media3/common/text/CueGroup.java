package androidx.media3.common.text;

import Ad.j;
import F2.C0039u;
import F2.C0040v;
import F2.G;
import F2.U;
import F2.v0;
import F2.w0;
import F2.y0;
import androidx.media3.common.util.Util;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CueGroup {
    private static final w0 CUES_PRIORITY_COMPARATOR = new C0039u(new j(11), v0.e);
    public static final CueGroup EMPTY_TIME_ZERO = new CueGroup(y0.f278h, 0);
    private static final String FIELD_CUES = Util.intToStringMaxRadix(0);
    private static final String FIELD_PRESENTATION_TIME_US = Util.intToStringMaxRadix(1);
    public final U cues;
    public final long presentationTimeUs;

    static {
        G g = U.e;
    }

    public CueGroup(List<Cue> list, long j2) {
        Collection collection;
        w0 w0Var = CUES_PRIORITY_COMPARATOR;
        List list2 = list;
        w0Var.getClass();
        if (list2 instanceof Collection) {
            collection = list2;
        } else {
            collection = C0040v.m(list2.iterator());
        }
        Object[] array = collection.toArray();
        C0040v.a(array.length, array);
        Arrays.sort(array, w0Var);
        this.cues = U.w(array.length, array);
        this.presentationTimeUs = j2;
    }
}
