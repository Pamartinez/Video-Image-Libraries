package W0;

import V0.l;
import java.io.Serializable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class i implements Serializable {
    public final int d;
    public final a e;

    public i(a aVar, int i2) {
        this.e = aVar;
        this.d = i2;
    }

    public static int a(Class cls) {
        int i2 = 0;
        for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
            e eVar = (e) enumR;
            if (eVar.a()) {
                i2 |= eVar.b();
            }
        }
        return i2;
    }

    public i(j jVar, int i2) {
        this.e = jVar.e;
        this.d = i2;
    }

    public i(j jVar, a aVar) {
        this.e = aVar;
        this.d = jVar.d;
    }

    public i(l lVar) {
        this.e = lVar.e;
        this.d = lVar.d;
    }
}
