package F2;

import java.util.List;
import java.util.ListIterator;

/* renamed from: F2.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0031n extends C0015f implements ListIterator {

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ C0033o f268h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0031n(C0033o oVar) {
        super(oVar);
        this.f268h = oVar;
    }

    public final void add(Object obj) {
        C0033o oVar = this.f268h;
        boolean isEmpty = oVar.isEmpty();
        b().add(obj);
        oVar.f270i.f257i++;
        if (isEmpty) {
            oVar.i();
        }
    }

    public final ListIterator b() {
        a();
        return (ListIterator) this.e;
    }

    public final boolean hasPrevious() {
        return b().hasPrevious();
    }

    public final int nextIndex() {
        return b().nextIndex();
    }

    public final Object previous() {
        return b().previous();
    }

    public final int previousIndex() {
        return b().previousIndex();
    }

    public final void set(Object obj) {
        b().set(obj);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0031n(C0033o oVar, int i2) {
        super(oVar, ((List) oVar.e).listIterator(i2));
        this.f268h = oVar;
    }
}
