package og;

import A.a;
import java.io.EOFException;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i implements b {
    public final a d = new Object();
    public final m e;
    public boolean f;

    /* JADX WARNING: type inference failed for: r0v0, types: [og.a, java.lang.Object] */
    public i(m mVar) {
        this.e = mVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00c0 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long a(og.c r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            boolean r2 = r0.f
            if (r2 != 0) goto L_0x00e1
            r2 = 0
        L_0x000a:
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            og.a r7 = r0.d
            if (r6 < 0) goto L_0x00d6
            og.j r6 = r7.d
            if (r6 != 0) goto L_0x0018
            goto L_0x00b8
        L_0x0018:
            long r8 = r7.e
            long r10 = r8 - r2
            int r10 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r10 >= 0) goto L_0x002e
        L_0x0020:
            int r4 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x003e
            og.j r6 = r6.g
            int r4 = r6.f5017c
            int r5 = r6.b
            int r4 = r4 - r5
            long r4 = (long) r4
            long r8 = r8 - r4
            goto L_0x0020
        L_0x002e:
            int r8 = r6.f5017c
            int r9 = r6.b
            int r8 = r8 - r9
            long r8 = (long) r8
            long r8 = r8 + r4
            int r10 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r10 >= 0) goto L_0x003d
            og.j r6 = r6.f
            r4 = r8
            goto L_0x002e
        L_0x003d:
            r8 = r4
        L_0x003e:
            int r4 = r1.g()
            r5 = 2
            r10 = 0
            if (r4 != r5) goto L_0x007d
            byte r4 = r1.b(r10)
            r5 = 1
            byte r5 = r1.b(r5)
            r10 = r2
        L_0x0050:
            long r12 = r7.e
            int r12 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r12 >= 0) goto L_0x00b8
            byte[] r12 = r6.f5016a
            int r13 = r6.b
            long r13 = (long) r13
            long r13 = r13 + r10
            long r13 = r13 - r8
            int r10 = (int) r13
            int r11 = r6.f5017c
        L_0x0060:
            if (r10 >= r11) goto L_0x0072
            byte r13 = r12[r10]
            if (r13 == r4) goto L_0x006c
            if (r13 != r5) goto L_0x0069
            goto L_0x006c
        L_0x0069:
            int r10 = r10 + 1
            goto L_0x0060
        L_0x006c:
            int r4 = r6.b
            int r10 = r10 - r4
            long r4 = (long) r10
        L_0x0070:
            long r4 = r4 + r8
            goto L_0x00ba
        L_0x0072:
            int r10 = r6.f5017c
            int r11 = r6.b
            int r10 = r10 - r11
            long r10 = (long) r10
            long r10 = r10 + r8
            og.j r6 = r6.f
            r8 = r10
            goto L_0x0050
        L_0x007d:
            byte[] r4 = r1.d()
            r11 = r2
        L_0x0082:
            long r13 = r7.e
            int r5 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r5 >= 0) goto L_0x00b8
            byte[] r5 = r6.f5016a
            int r13 = r6.b
            long r13 = (long) r13
            long r13 = r13 + r11
            long r13 = r13 - r8
            int r11 = (int) r13
            int r12 = r6.f5017c
        L_0x0092:
            if (r11 >= r12) goto L_0x00ab
            byte r13 = r5[r11]
            int r14 = r4.length
            r15 = r10
        L_0x0098:
            if (r15 >= r14) goto L_0x00a7
            byte r10 = r4[r15]
            if (r13 != r10) goto L_0x00a3
            int r4 = r6.b
            int r11 = r11 - r4
            long r4 = (long) r11
            goto L_0x0070
        L_0x00a3:
            int r15 = r15 + 1
            r10 = 0
            goto L_0x0098
        L_0x00a7:
            int r11 = r11 + 1
            r10 = 0
            goto L_0x0092
        L_0x00ab:
            int r5 = r6.f5017c
            int r10 = r6.b
            int r5 = r5 - r10
            long r10 = (long) r5
            long r11 = r8 + r10
            og.j r6 = r6.f
            r8 = r11
            r10 = 0
            goto L_0x0082
        L_0x00b8:
            r4 = -1
        L_0x00ba:
            r8 = -1
            int r6 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x00c1
            return r4
        L_0x00c1:
            long r4 = r7.e
            og.m r6 = r0.e
            r10 = 8192(0x2000, double:4.0474E-320)
            long r6 = r6.e(r7, r10)
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 != 0) goto L_0x00d0
            return r8
        L_0x00d0:
            long r2 = java.lang.Math.max(r2, r4)
            goto L_0x000a
        L_0x00d6:
            r7.getClass()
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "fromIndex < 0"
            r0.<init>(r1)
            throw r0
        L_0x00e1:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "closed"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: og.i.a(og.c):long");
    }

    public final void close() {
        if (!this.f) {
            this.f = true;
            this.e.close();
            a aVar = this.d;
            try {
                aVar.h(aVar.e);
            } catch (EOFException e7) {
                throw new AssertionError(e7);
            }
        }
    }

    public final a d() {
        return this.d;
    }

    public final long e(a aVar, long j2) {
        if (!this.f) {
            a aVar2 = this.d;
            if (aVar2.e == 0 && this.e.e(aVar2, 8192) == -1) {
                return -1;
            }
            return aVar2.e(aVar, Math.min(8192, aVar2.e));
        }
        throw new IllegalStateException("closed");
    }

    public final boolean isOpen() {
        return !this.f;
    }

    public final boolean k(long j2) {
        a aVar;
        if (j2 < 0) {
            throw new IllegalArgumentException(a.f("byteCount < 0: ", j2));
        } else if (!this.f) {
            do {
                aVar = this.d;
                if (aVar.e >= j2) {
                    return true;
                }
            } while (this.e.e(aVar, 8192) != -1);
            return false;
        } else {
            throw new IllegalStateException("closed");
        }
    }

    public final int read(ByteBuffer byteBuffer) {
        a aVar = this.d;
        if (aVar.e == 0 && this.e.e(aVar, 8192) == -1) {
            return -1;
        }
        return aVar.read(byteBuffer);
    }

    public final String toString() {
        return "buffer(" + this.e + ")";
    }
}
