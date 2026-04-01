package F2;

import He.F;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/* renamed from: F2.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0015f implements Iterator {
    public final /* synthetic */ int d = 0;
    public final Iterator e;
    public Object f;
    public final /* synthetic */ Object g;

    public C0015f(C0033o oVar) {
        Iterator it;
        this.g = oVar;
        Collection collection = oVar.e;
        this.f = collection;
        if (collection instanceof List) {
            it = ((List) collection).listIterator();
        } else {
            it = collection.iterator();
        }
        this.e = it;
    }

    public void a() {
        C0033o oVar = (C0033o) this.g;
        oVar.p();
        if (oVar.e != ((Collection) this.f)) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean hasNext() {
        switch (this.d) {
            case 0:
                return this.e.hasNext();
            case 1:
                return this.e.hasNext();
            default:
                a();
                return this.e.hasNext();
        }
    }

    public final Object next() {
        switch (this.d) {
            case 0:
                Map.Entry entry = (Map.Entry) this.e.next();
                this.f = (Collection) entry.getValue();
                return ((C0017g) this.g).a(entry);
            case 1:
                Map.Entry entry2 = (Map.Entry) this.e.next();
                this.f = entry2;
                return entry2.getKey();
            default:
                a();
                return this.e.next();
        }
    }

    public final void remove() {
        boolean z;
        boolean z3;
        switch (this.d) {
            case 0:
                if (((Collection) this.f) != null) {
                    z = true;
                } else {
                    z = false;
                }
                F.t(z, "no calls to next() since the last call to remove()");
                this.e.remove();
                ((C0017g) this.g).g.f257i -= ((Collection) this.f).size();
                ((Collection) this.f).clear();
                this.f = null;
                return;
            case 1:
                if (((Map.Entry) this.f) != null) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                F.t(z3, "no calls to next() since the last call to remove()");
                Collection collection = (Collection) ((Map.Entry) this.f).getValue();
                this.e.remove();
                ((C0019h) this.g).e.f257i -= collection.size();
                collection.clear();
                this.f = null;
                return;
            default:
                this.e.remove();
                C0033o oVar = (C0033o) this.g;
                C0009c cVar = oVar.f269h;
                cVar.f257i--;
                oVar.q();
                return;
        }
    }

    public C0015f(C0033o oVar, ListIterator listIterator) {
        this.g = oVar;
        this.f = oVar.e;
        this.e = listIterator;
    }

    public C0015f(C0019h hVar, Iterator it) {
        this.e = it;
        this.g = hVar;
    }

    public C0015f(C0017g gVar) {
        this.g = gVar;
        this.e = gVar.f.entrySet().iterator();
    }
}
