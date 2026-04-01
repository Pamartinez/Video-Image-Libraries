package Tf;

import Be.a;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g implements Iterator, a {
    public final CharSequence d;
    public int e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f3822h;

    public g(CharSequence charSequence) {
        j.e(charSequence, "string");
        this.d = charSequence;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0041, code lost:
        r1 = r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean hasNext() {
        /*
            r9 = this;
            int r0 = r9.e
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x000a
            if (r0 != r2) goto L_0x0009
            return r2
        L_0x0009:
            return r1
        L_0x000a:
            int r0 = r9.f3822h
            r3 = 2
            if (r0 >= 0) goto L_0x0012
            r9.e = r3
            return r1
        L_0x0012:
            java.lang.CharSequence r0 = r9.d
            int r1 = r0.length()
            int r4 = r9.f
            int r5 = r0.length()
        L_0x001e:
            if (r4 >= r5) goto L_0x0043
            char r6 = r0.charAt(r4)
            r7 = 13
            r8 = 10
            if (r6 == r8) goto L_0x002f
            if (r6 == r7) goto L_0x002f
            int r4 = r4 + 1
            goto L_0x001e
        L_0x002f:
            if (r6 != r7) goto L_0x0040
            int r1 = r4 + 1
            int r5 = r0.length()
            if (r1 >= r5) goto L_0x0040
            char r0 = r0.charAt(r1)
            if (r0 != r8) goto L_0x0040
            goto L_0x0041
        L_0x0040:
            r3 = r2
        L_0x0041:
            r1 = r4
            goto L_0x0044
        L_0x0043:
            r3 = -1
        L_0x0044:
            r9.e = r2
            r9.f3822h = r3
            r9.g = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: Tf.g.hasNext():boolean");
    }

    public final Object next() {
        if (hasNext()) {
            this.e = 0;
            int i2 = this.g;
            int i7 = this.f;
            this.f = this.f3822h + i2;
            return this.d.subSequence(i7, i2).toString();
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
