package rf;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/* renamed from: rf.A  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1250A implements Iterator {
    public final Stack d = new Stack();
    public x e;

    public C1250A(C1255e eVar) {
        while (eVar instanceof C) {
            C c5 = (C) eVar;
            this.d.push(c5);
            eVar = c5.f;
        }
        this.e = (x) eVar;
    }

    /* renamed from: a */
    public final x next() {
        x xVar;
        x xVar2 = this.e;
        if (xVar2 != null) {
            while (true) {
                Stack stack = this.d;
                if (!stack.isEmpty()) {
                    Object obj = ((C) stack.pop()).g;
                    while (obj instanceof C) {
                        C c5 = (C) obj;
                        stack.push(c5);
                        obj = c5.f;
                    }
                    xVar = (x) obj;
                    if (xVar.e.length != 0) {
                        break;
                    }
                } else {
                    xVar = null;
                    break;
                }
            }
            this.e = xVar;
            return xVar2;
        }
        throw new NoSuchElementException();
    }

    public final boolean hasNext() {
        if (this.e != null) {
            return true;
        }
        return false;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
