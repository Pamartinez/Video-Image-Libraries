package Sf;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements k {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicReference f3728a;

    public a(k kVar) {
        this.f3728a = new AtomicReference(kVar);
    }

    public final Iterator iterator() {
        k kVar = (k) this.f3728a.getAndSet((Object) null);
        if (kVar != null) {
            return kVar.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}
