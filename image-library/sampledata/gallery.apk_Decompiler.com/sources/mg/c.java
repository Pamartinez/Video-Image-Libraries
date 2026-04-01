package mg;

import B0.a;
import Ed.b;
import He.F;
import Tf.u;
import c0.C0086a;
import ig.f;
import kotlin.jvm.internal.j;
import lg.C1174b;
import me.q;
import me.s;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends C0246a {

    /* renamed from: h  reason: collision with root package name */
    public final b f4924h;

    /* renamed from: i  reason: collision with root package name */
    public final a f4925i;

    public c(b bVar, C1174b bVar2) {
        j.e(bVar2, "json");
        this.f4924h = bVar;
        this.f4925i = bVar2.b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0027 A[Catch:{ IllegalArgumentException -> 0x002e }] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002a A[Catch:{ IllegalArgumentException -> 0x002e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte G() {
        /*
            r4 = this;
            Ed.b r4 = r4.f4924h
            java.lang.String r0 = r4.l()
            r1 = 0
            java.lang.String r2 = "<this>"
            kotlin.jvm.internal.j.e(r0, r2)     // Catch:{ IllegalArgumentException -> 0x002e }
            me.q r2 = He.F.Q(r0)     // Catch:{ IllegalArgumentException -> 0x002e }
            if (r2 == 0) goto L_0x0024
            int r2 = r2.d     // Catch:{ IllegalArgumentException -> 0x002e }
            r3 = 255(0xff, float:3.57E-43)
            int r3 = java.lang.Integer.compareUnsigned(r2, r3)     // Catch:{ IllegalArgumentException -> 0x002e }
            if (r3 <= 0) goto L_0x001d
            goto L_0x0024
        L_0x001d:
            byte r2 = (byte) r2     // Catch:{ IllegalArgumentException -> 0x002e }
            me.o r3 = new me.o     // Catch:{ IllegalArgumentException -> 0x002e }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x002e }
            goto L_0x0025
        L_0x0024:
            r3 = r1
        L_0x0025:
            if (r3 == 0) goto L_0x002a
            byte r4 = r3.d     // Catch:{ IllegalArgumentException -> 0x002e }
            return r4
        L_0x002a:
            Tf.u.m0(r0)     // Catch:{ IllegalArgumentException -> 0x002e }
            throw r1     // Catch:{ IllegalArgumentException -> 0x002e }
        L_0x002e:
            java.lang.String r2 = "Failed to parse type 'UByte' for input '"
            r3 = 39
            java.lang.String r0 = c0.C0086a.h(r3, r2, r0)
            r2 = 0
            r3 = 6
            Ed.b.p(r4, r0, r2, r1, r3)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: mg.c.G():byte");
    }

    public final int d(f fVar) {
        j.e(fVar, "descriptor");
        throw new IllegalStateException("unsupported");
    }

    public final int f() {
        b bVar = this.f4924h;
        String l = bVar.l();
        try {
            j.e(l, "<this>");
            q Q = F.Q(l);
            if (Q != null) {
                return Q.d;
            }
            u.m0(l);
            throw null;
        } catch (IllegalArgumentException unused) {
            b.p(bVar, C0086a.h('\'', "Failed to parse type 'UInt' for input '", l), 0, (String) null, 6);
            throw null;
        }
    }

    public final long j() {
        s sVar;
        b bVar = this.f4924h;
        String l = bVar.l();
        try {
            j.e(l, "<this>");
            j.e(l, "<this>");
            int length = l.length();
            if (length != 0) {
                int i2 = 0;
                char charAt = l.charAt(0);
                if (j.f(charAt, 48) < 0) {
                    i2 = 1;
                    if (length != 1) {
                        if (charAt != '+') {
                        }
                    }
                }
                long j2 = (long) 10;
                long j3 = 0;
                long j8 = 512409557603043100L;
                while (true) {
                    if (i2 >= length) {
                        sVar = new s(j3);
                        break;
                    }
                    int digit = Character.digit(l.charAt(i2), 10);
                    if (digit < 0) {
                        break;
                    }
                    if (Long.compareUnsigned(j3, j8) > 0) {
                        if (j8 != 512409557603043100L) {
                            break;
                        }
                        j8 = Long.divideUnsigned(-1, j2);
                        if (Long.compareUnsigned(j3, j8) > 0) {
                            break;
                        }
                    }
                    long j10 = j3 * j2;
                    long j11 = (((long) digit) & 4294967295L) + j10;
                    if (Long.compareUnsigned(j11, j10) < 0) {
                        break;
                    }
                    i2++;
                    j3 = j11;
                }
            }
            sVar = null;
            if (sVar != null) {
                return sVar.d;
            }
            u.m0(l);
            throw null;
        } catch (IllegalArgumentException unused) {
            b.p(bVar, C0086a.h('\'', "Failed to parse type 'ULong' for input '", l), 0, (String) null, 6);
            throw null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0028 A[Catch:{ IllegalArgumentException -> 0x002f }] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b A[Catch:{ IllegalArgumentException -> 0x002f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final short o() {
        /*
            r4 = this;
            Ed.b r4 = r4.f4924h
            java.lang.String r0 = r4.l()
            r1 = 0
            java.lang.String r2 = "<this>"
            kotlin.jvm.internal.j.e(r0, r2)     // Catch:{ IllegalArgumentException -> 0x002f }
            me.q r2 = He.F.Q(r0)     // Catch:{ IllegalArgumentException -> 0x002f }
            if (r2 == 0) goto L_0x0025
            int r2 = r2.d     // Catch:{ IllegalArgumentException -> 0x002f }
            r3 = 65535(0xffff, float:9.1834E-41)
            int r3 = java.lang.Integer.compareUnsigned(r2, r3)     // Catch:{ IllegalArgumentException -> 0x002f }
            if (r3 <= 0) goto L_0x001e
            goto L_0x0025
        L_0x001e:
            short r2 = (short) r2     // Catch:{ IllegalArgumentException -> 0x002f }
            me.v r3 = new me.v     // Catch:{ IllegalArgumentException -> 0x002f }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x002f }
            goto L_0x0026
        L_0x0025:
            r3 = r1
        L_0x0026:
            if (r3 == 0) goto L_0x002b
            short r4 = r3.d     // Catch:{ IllegalArgumentException -> 0x002f }
            return r4
        L_0x002b:
            Tf.u.m0(r0)     // Catch:{ IllegalArgumentException -> 0x002f }
            throw r1     // Catch:{ IllegalArgumentException -> 0x002f }
        L_0x002f:
            java.lang.String r2 = "Failed to parse type 'UShort' for input '"
            r3 = 39
            java.lang.String r0 = c0.C0086a.h(r3, r2, r0)
            r2 = 0
            r3 = 6
            Ed.b.p(r4, r0, r2, r1, r3)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: mg.c.o():short");
    }

    public final a y() {
        return this.f4925i;
    }
}
