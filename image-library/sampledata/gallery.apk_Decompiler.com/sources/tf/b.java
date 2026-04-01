package Tf;

import Be.a;
import Ge.c;
import Ge.e;
import Sf.j;
import java.util.Iterator;
import java.util.NoSuchElementException;
import me.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements Iterator, a {
    public int d = -1;
    public int e;
    public int f;
    public e g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ j f3816h;

    public b(j jVar) {
        this.f3816h = jVar;
        int l = B1.a.l(0, 0, ((CharSequence) jVar.b).length());
        this.e = l;
        this.f = l;
    }

    /* JADX WARNING: type inference failed for: r0v8, types: [Ge.c, Ge.e] */
    /* JADX WARNING: type inference failed for: r0v9, types: [Ge.c, Ge.e] */
    public final void a() {
        j jVar = this.f3816h;
        CharSequence charSequence = (CharSequence) jVar.b;
        int i2 = this.f;
        int i7 = 0;
        if (i2 < 0) {
            this.d = 0;
            this.g = null;
            return;
        }
        if (i2 > charSequence.length()) {
            this.g = new c(this.e, n.x0(charSequence), 1);
            this.f = -1;
        } else {
            i iVar = (i) ((Ae.c) jVar.f3737c).invoke(charSequence, Integer.valueOf(this.f));
            if (iVar == null) {
                this.g = new c(this.e, n.x0(charSequence), 1);
                this.f = -1;
            } else {
                int intValue = ((Number) iVar.d).intValue();
                int intValue2 = ((Number) iVar.e).intValue();
                this.g = B1.a.Z(this.e, intValue);
                int i8 = intValue + intValue2;
                this.e = i8;
                if (intValue2 == 0) {
                    i7 = 1;
                }
                this.f = i8 + i7;
            }
        }
        this.d = 1;
    }

    public final boolean hasNext() {
        if (this.d == -1) {
            a();
        }
        if (this.d == 1) {
            return true;
        }
        return false;
    }

    public final Object next() {
        if (this.d == -1) {
            a();
        }
        if (this.d != 0) {
            e eVar = this.g;
            kotlin.jvm.internal.j.c(eVar, "null cannot be cast to non-null type kotlin.ranges.IntRange");
            this.g = null;
            this.d = -1;
            return eVar;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
