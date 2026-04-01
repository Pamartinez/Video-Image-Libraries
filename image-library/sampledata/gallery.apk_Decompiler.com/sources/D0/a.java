package D0;

import A0.e;
import A0.f;
import A0.j;
import A0.o;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends Bf.a {
    public final /* synthetic */ int f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(int i2, List list) {
        super(list);
        this.f = i2;
    }

    public final e p0() {
        switch (this.f) {
            case 0:
                return new f(0, (List) this.e);
            case 1:
                return new j(0, (List) this.e);
            case 2:
                return new f(1, (List) this.e);
            case 3:
                return new j(1, (List) this.e);
            case 4:
                return new j(2, (List) this.e);
            case 5:
                return new o((List) this.e);
            default:
                return new f(2, (List) this.e);
        }
    }
}
