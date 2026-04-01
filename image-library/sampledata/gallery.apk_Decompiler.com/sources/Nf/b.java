package Nf;

import Ge.a;
import Hf.C0759h;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.jvm.internal.j;
import ne.C1192j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends a {
    public Object[] d;
    public int e;

    public final Object get(int i2) {
        return C1192j.p0(i2, this.d);
    }

    public final int i() {
        return this.e;
    }

    public final Iterator iterator() {
        return new a(this);
    }

    public final void p(int i2, C0759h hVar) {
        Object[] objArr = this.d;
        if (objArr.length <= i2) {
            int length = objArr.length;
            do {
                length *= 2;
            } while (length <= i2);
            Object[] copyOf = Arrays.copyOf(this.d, length);
            j.d(copyOf, "copyOf(...)");
            this.d = copyOf;
        }
        Object[] objArr2 = this.d;
        if (objArr2[i2] == null) {
            this.e++;
        }
        objArr2[i2] = hVar;
    }
}
