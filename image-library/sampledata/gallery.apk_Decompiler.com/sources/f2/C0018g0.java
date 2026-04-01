package F2;

import He.F;
import java.util.Collections;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;

/* renamed from: F2.g0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0018g0 extends C0010c0 implements NavigableSet, M0 {
    public final transient Comparator g;

    /* renamed from: h  reason: collision with root package name */
    public transient C0018g0 f263h;

    public C0018g0(Comparator comparator) {
        this.g = comparator;
    }

    public static F0 D(Comparator comparator) {
        if (v0.e.equals(comparator)) {
            return F0.f245j;
        }
        return new F0(y0.f278h, comparator);
    }

    /* renamed from: B */
    public final C0018g0 descendingSet() {
        C0018g0 g0Var = this.f263h;
        if (g0Var == null) {
            F0 f02 = (F0) this;
            Comparator reverseOrder = Collections.reverseOrder(f02.g);
            if (f02.isEmpty()) {
                g0Var = D(reverseOrder);
            } else {
                g0Var = new F0(f02.f246i.F(), reverseOrder);
            }
            this.f263h = g0Var;
            g0Var.f263h = this;
        }
        return g0Var;
    }

    /* renamed from: E */
    public final F0 subSet(Object obj, boolean z, Object obj2, boolean z3) {
        boolean z7;
        obj.getClass();
        obj2.getClass();
        if (this.g.compare(obj, obj2) <= 0) {
            z7 = true;
        } else {
            z7 = false;
        }
        F.j(z7);
        F0 f02 = (F0) this;
        F0 F4 = f02.F(f02.H(obj, z), f02.f246i.size());
        return F4.F(0, F4.G(obj2, z3));
    }

    public final Comparator comparator() {
        return this.g;
    }

    public final NavigableSet headSet(Object obj, boolean z) {
        obj.getClass();
        F0 f02 = (F0) this;
        return f02.F(0, f02.G(obj, z));
    }

    public final Object pollFirst() {
        throw new UnsupportedOperationException();
    }

    public final Object pollLast() {
        throw new UnsupportedOperationException();
    }

    public final NavigableSet tailSet(Object obj, boolean z) {
        obj.getClass();
        F0 f02 = (F0) this;
        return f02.F(f02.H(obj, z), f02.f246i.size());
    }

    public final SortedSet subSet(Object obj, Object obj2) {
        return subSet(obj, true, obj2, false);
    }

    public final SortedSet headSet(Object obj) {
        obj.getClass();
        F0 f02 = (F0) this;
        return f02.F(0, f02.G(obj, false));
    }

    public final SortedSet tailSet(Object obj) {
        obj.getClass();
        F0 f02 = (F0) this;
        return f02.F(f02.H(obj, true), f02.f246i.size());
    }
}
