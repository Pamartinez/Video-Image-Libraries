package oe;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.j;
import ne.C1189g;

/* renamed from: oe.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1218g extends C1189g {
    public final /* synthetic */ int d;
    public final C1217f e;

    public /* synthetic */ C1218g(C1217f fVar, int i2) {
        this.d = i2;
        this.e = fVar;
    }

    public final boolean add(Object obj) {
        switch (this.d) {
            case 0:
                j.e((Map.Entry) obj, "element");
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final boolean addAll(Collection collection) {
        switch (this.d) {
            case 0:
                j.e(collection, "elements");
                throw new UnsupportedOperationException();
            default:
                j.e(collection, "elements");
                throw new UnsupportedOperationException();
        }
    }

    public final void clear() {
        switch (this.d) {
            case 0:
                this.e.clear();
                return;
            default:
                this.e.clear();
                return;
        }
    }

    public final boolean contains(Object obj) {
        switch (this.d) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                return this.e.f((Map.Entry) obj);
            default:
                return this.e.containsKey(obj);
        }
    }

    public boolean containsAll(Collection collection) {
        switch (this.d) {
            case 0:
                j.e(collection, "elements");
                return this.e.e(collection);
            default:
                return super.containsAll(collection);
        }
    }

    public final boolean isEmpty() {
        switch (this.d) {
            case 0:
                return this.e.isEmpty();
            default:
                return this.e.isEmpty();
        }
    }

    public final Iterator iterator() {
        switch (this.d) {
            case 0:
                C1217f fVar = this.e;
                fVar.getClass();
                return new C1215d(fVar, 0);
            default:
                C1217f fVar2 = this.e;
                fVar2.getClass();
                return new C1215d(fVar2, 1);
        }
    }

    public final int p() {
        switch (this.d) {
            case 0:
                return this.e.l;
            default:
                return this.e.l;
        }
    }

    public final boolean remove(Object obj) {
        switch (this.d) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                C1217f fVar = this.e;
                fVar.getClass();
                fVar.c();
                int j2 = fVar.j(entry.getKey());
                if (j2 < 0) {
                    return false;
                }
                Object[] objArr = fVar.e;
                j.b(objArr);
                if (!j.a(objArr[j2], entry.getValue())) {
                    return false;
                }
                fVar.o(j2);
                return true;
            default:
                C1217f fVar2 = this.e;
                fVar2.c();
                int j3 = fVar2.j(obj);
                if (j3 < 0) {
                    return false;
                }
                fVar2.o(j3);
                return true;
        }
    }

    public final boolean removeAll(Collection collection) {
        switch (this.d) {
            case 0:
                j.e(collection, "elements");
                this.e.c();
                return super.removeAll(collection);
            default:
                j.e(collection, "elements");
                this.e.c();
                return super.removeAll(collection);
        }
    }

    public final boolean retainAll(Collection collection) {
        switch (this.d) {
            case 0:
                j.e(collection, "elements");
                this.e.c();
                return super.retainAll(collection);
            default:
                j.e(collection, "elements");
                this.e.c();
                return super.retainAll(collection);
        }
    }
}
