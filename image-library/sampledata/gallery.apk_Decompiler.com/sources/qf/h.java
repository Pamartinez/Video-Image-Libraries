package Qf;

import Nf.o;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.y;
import ne.C1189g;
import ne.C1192j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends C1189g {
    public static final /* synthetic */ int f = 0;
    public Object d;
    public int e;

    public h(int i2) {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.util.LinkedHashSet} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean add(java.lang.Object r5) {
        /*
            r4 = this;
            int r0 = r4.e
            r1 = 1
            if (r0 != 0) goto L_0x0008
            r4.d = r5
            goto L_0x0073
        L_0x0008:
            if (r0 != r1) goto L_0x001c
            java.lang.Object r0 = r4.d
            boolean r0 = kotlin.jvm.internal.j.a(r0, r5)
            if (r0 == 0) goto L_0x0013
            goto L_0x0071
        L_0x0013:
            java.lang.Object r0 = r4.d
            java.lang.Object[] r5 = new java.lang.Object[]{r0, r5}
            r4.d = r5
            goto L_0x0073
        L_0x001c:
            r2 = 5
            if (r0 >= r2) goto L_0x0060
            java.lang.Object r0 = r4.d
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.Array<T of org.jetbrains.kotlin.utils.SmartSet>"
            kotlin.jvm.internal.j.c(r0, r2)
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            boolean r2 = ne.C1192j.d0(r0, r5)
            if (r2 == 0) goto L_0x002f
            goto L_0x0071
        L_0x002f:
            int r2 = r4.e
            r3 = 4
            if (r2 != r3) goto L_0x004f
            int r2 = r0.length
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r2)
            java.lang.String r2 = "elements"
            kotlin.jvm.internal.j.e(r0, r2)
            java.util.LinkedHashSet r2 = new java.util.LinkedHashSet
            int r3 = r0.length
            int r3 = ne.z.Z(r3)
            r2.<init>(r3)
            ne.C1192j.w0(r0, r2)
            r2.add(r5)
            goto L_0x005d
        L_0x004f:
            int r2 = r2 + r1
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r0, r2)
            java.lang.String r0 = "copyOf(...)"
            kotlin.jvm.internal.j.d(r2, r0)
            int r0 = r2.length
            int r0 = r0 - r1
            r2[r0] = r5
        L_0x005d:
            r4.d = r2
            goto L_0x0073
        L_0x0060:
            java.lang.Object r0 = r4.d
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.collections.MutableSet<T of org.jetbrains.kotlin.utils.SmartSet>"
            kotlin.jvm.internal.j.c(r0, r2)
            java.util.Set r0 = kotlin.jvm.internal.y.b(r0)
            boolean r5 = r0.add(r5)
            if (r5 != 0) goto L_0x0073
        L_0x0071:
            r4 = 0
            return r4
        L_0x0073:
            int r5 = r4.e
            int r5 = r5 + r1
            r4.e = r5
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: Qf.h.add(java.lang.Object):boolean");
    }

    public final void clear() {
        this.d = null;
        this.e = 0;
    }

    public final boolean contains(Object obj) {
        if (p() == 0) {
            return false;
        }
        if (p() == 1) {
            return j.a(this.d, obj);
        }
        if (p() < 5) {
            Object obj2 = this.d;
            j.c(obj2, "null cannot be cast to non-null type kotlin.Array<T of org.jetbrains.kotlin.utils.SmartSet>");
            return C1192j.d0((Object[]) obj2, obj);
        }
        Object obj3 = this.d;
        j.c(obj3, "null cannot be cast to non-null type kotlin.collections.Set<T of org.jetbrains.kotlin.utils.SmartSet>");
        return ((Set) obj3).contains(obj);
    }

    public final Iterator iterator() {
        int i2 = this.e;
        if (i2 == 0) {
            return Collections.EMPTY_SET.iterator();
        }
        if (i2 == 1) {
            return new o(1, this.d);
        }
        if (i2 < 5) {
            Object obj = this.d;
            j.c(obj, "null cannot be cast to non-null type kotlin.Array<T of org.jetbrains.kotlin.utils.SmartSet>");
            return new g((Object[]) obj);
        }
        Object obj2 = this.d;
        j.c(obj2, "null cannot be cast to non-null type kotlin.collections.MutableSet<T of org.jetbrains.kotlin.utils.SmartSet>");
        return y.b(obj2).iterator();
    }

    public final int p() {
        return this.e;
    }
}
