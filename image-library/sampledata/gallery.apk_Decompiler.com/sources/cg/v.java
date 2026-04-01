package cg;

import Vf.U;
import Vf.V;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class v {
    public static final /* synthetic */ AtomicIntegerFieldUpdater b = AtomicIntegerFieldUpdater.newUpdater(v.class, "_size$volatile");
    private volatile /* synthetic */ int _size$volatile;

    /* renamed from: a  reason: collision with root package name */
    public U[] f4035a;

    public final void a(U u) {
        u.d((V) this);
        U[] uArr = this.f4035a;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = b;
        if (uArr == null) {
            uArr = new U[4];
            this.f4035a = uArr;
        } else if (atomicIntegerFieldUpdater.get(this) >= uArr.length) {
            Object[] copyOf = Arrays.copyOf(uArr, atomicIntegerFieldUpdater.get(this) * 2);
            j.d(copyOf, "copyOf(...)");
            uArr = (U[]) copyOf;
            this.f4035a = uArr;
        }
        int i2 = atomicIntegerFieldUpdater.get(this);
        atomicIntegerFieldUpdater.set(this, i2 + 1);
        uArr[i2] = u;
        u.e = i2;
        c(i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0060, code lost:
        if (r6.compareTo(r7) < 0) goto L_0x0064;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final Vf.U b(int r9) {
        /*
            r8 = this;
            Vf.U[] r0 = r8.f4035a
            kotlin.jvm.internal.j.b(r0)
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r1 = b
            int r2 = r1.get(r8)
            r3 = -1
            int r2 = r2 + r3
            r1.set(r8, r2)
            int r2 = r1.get(r8)
            if (r9 >= r2) goto L_0x007a
            int r2 = r1.get(r8)
            r8.d(r9, r2)
            int r2 = r9 + -1
            int r2 = r2 / 2
            if (r9 <= 0) goto L_0x003a
            r4 = r0[r9]
            kotlin.jvm.internal.j.b(r4)
            r5 = r0[r2]
            kotlin.jvm.internal.j.b(r5)
            int r4 = r4.compareTo(r5)
            if (r4 >= 0) goto L_0x003a
            r8.d(r9, r2)
            r8.c(r2)
            goto L_0x007a
        L_0x003a:
            int r2 = r9 * 2
            int r4 = r2 + 1
            int r5 = r1.get(r8)
            if (r4 < r5) goto L_0x0045
            goto L_0x007a
        L_0x0045:
            Vf.U[] r5 = r8.f4035a
            kotlin.jvm.internal.j.b(r5)
            int r2 = r2 + 2
            int r6 = r1.get(r8)
            if (r2 >= r6) goto L_0x0063
            r6 = r5[r2]
            kotlin.jvm.internal.j.b(r6)
            r7 = r5[r4]
            kotlin.jvm.internal.j.b(r7)
            int r6 = r6.compareTo(r7)
            if (r6 >= 0) goto L_0x0063
            goto L_0x0064
        L_0x0063:
            r2 = r4
        L_0x0064:
            r4 = r5[r9]
            kotlin.jvm.internal.j.b(r4)
            r5 = r5[r2]
            kotlin.jvm.internal.j.b(r5)
            int r4 = r4.compareTo(r5)
            if (r4 > 0) goto L_0x0075
            goto L_0x007a
        L_0x0075:
            r8.d(r9, r2)
            r9 = r2
            goto L_0x003a
        L_0x007a:
            int r9 = r1.get(r8)
            r9 = r0[r9]
            kotlin.jvm.internal.j.b(r9)
            r2 = 0
            r9.d(r2)
            r9.e = r3
            int r8 = r1.get(r8)
            r0[r8] = r2
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: cg.v.b(int):Vf.U");
    }

    public final void c(int i2) {
        while (i2 > 0) {
            U[] uArr = this.f4035a;
            j.b(uArr);
            int i7 = (i2 - 1) / 2;
            U u = uArr[i7];
            j.b(u);
            U u3 = uArr[i2];
            j.b(u3);
            if (u.compareTo(u3) > 0) {
                d(i2, i7);
                i2 = i7;
            } else {
                return;
            }
        }
    }

    public final void d(int i2, int i7) {
        U[] uArr = this.f4035a;
        j.b(uArr);
        U u = uArr[i7];
        j.b(u);
        U u3 = uArr[i2];
        j.b(u3);
        uArr[i2] = u;
        uArr[i7] = u3;
        u.e = i2;
        u3.e = i7;
    }
}
