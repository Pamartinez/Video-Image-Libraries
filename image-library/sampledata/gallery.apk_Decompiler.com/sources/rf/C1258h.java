package rf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: rf.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1258h {
    public static final /* synthetic */ int b = 0;

    /* renamed from: a  reason: collision with root package name */
    public final Map f5063a;

    static {
        new C1258h(0);
    }

    public C1258h() {
        this.f5063a = new HashMap();
    }

    public final void a(C1266p pVar) {
        this.f5063a.put(new C1257g(pVar.d.d, pVar.f5068a), pVar);
    }

    public C1258h(int i2) {
        this.f5063a = Collections.EMPTY_MAP;
    }
}
