package X0;

import B1.a;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends a implements Serializable {

    /* renamed from: j  reason: collision with root package name */
    public final b f893j;
    public final /* synthetic */ int k;

    public d(int i2) {
        this.k = i2;
        this.f893j = b.k;
        new HashMap(8);
        new ConcurrentHashMap(Math.min(64, 500), 0.8f, 4);
    }

    public final d b0(b bVar) {
        switch (this.k) {
            case 0:
                return new d(this, bVar, 0);
            default:
                return new d(this, bVar, 1);
        }
    }

    public d(d dVar, b bVar, int i2) {
        this.k = i2;
        this.f893j = bVar;
    }
}
