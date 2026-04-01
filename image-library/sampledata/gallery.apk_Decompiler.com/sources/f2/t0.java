package F2;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.SortedMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t0 extends C0009c {

    /* renamed from: j  reason: collision with root package name */
    public transient s0 f272j;

    public final Map e() {
        Map map = this.f256h;
        if (map instanceof NavigableMap) {
            return new C0021i(this, (NavigableMap) map);
        }
        if (map instanceof SortedMap) {
            return new C0027l(this, (SortedMap) map);
        }
        return new C0017g(this, map);
    }

    public final Collection f() {
        return (List) this.f272j.get();
    }

    public final Set g() {
        Map map = this.f256h;
        if (map instanceof NavigableMap) {
            return new C0023j(this, (NavigableMap) map);
        }
        if (map instanceof SortedMap) {
            return new C0029m(this, (SortedMap) map);
        }
        return new C0019h(this, map);
    }
}
