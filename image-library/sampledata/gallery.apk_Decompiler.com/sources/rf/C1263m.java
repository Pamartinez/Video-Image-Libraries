package rf;

import java.util.Map;

/* renamed from: rf.m  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1263m extends C1262l implements y {
    public C1260j e = C1260j.f5065c;
    public boolean f;

    public final void d(C1264n nVar) {
        D d;
        if (!this.f) {
            this.e = this.e.clone();
            this.f = true;
        }
        C1260j jVar = this.e;
        C1260j jVar2 = nVar.d;
        jVar.getClass();
        int i2 = 0;
        while (true) {
            d = jVar2.f5066a;
            if (i2 >= d.e.size()) {
                break;
            }
            jVar.g((Map.Entry) d.e.get(i2));
            i2++;
        }
        for (Map.Entry g : d.c()) {
            jVar.g(g);
        }
    }
}
