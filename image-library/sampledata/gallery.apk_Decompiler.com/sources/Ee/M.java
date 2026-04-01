package ee;

import E2.g;
import H2.a;
import H2.c;
import H2.d;
import He.F;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class M {

    /* renamed from: c  reason: collision with root package name */
    public static final Logger f4274c = Logger.getLogger(M.class.getName());
    public static final Z d = new Z(4);
    public static final c e;

    /* renamed from: a  reason: collision with root package name */
    public Object[] f4275a;
    public int b;

    static {
        c cVar = d.f338c;
        if (cVar.b != null) {
            cVar = new c(cVar.f339a, (Character) null);
        }
        e = cVar;
    }

    public final void a(I i2) {
        if (this.b != 0) {
            int i7 = 0;
            int i8 = 0;
            while (true) {
                int i10 = this.b;
                if (i7 < i10) {
                    int i11 = i7 * 2;
                    if (!Arrays.equals(i2.b, (byte[]) this.f4275a[i11])) {
                        Object[] objArr = this.f4275a;
                        int i12 = i8 * 2;
                        objArr[i12] = (byte[]) objArr[i11];
                        Object obj = objArr[i11 + 1];
                        if (objArr instanceof byte[][]) {
                            b(objArr.length);
                        }
                        this.f4275a[i12 + 1] = obj;
                        i8++;
                    }
                    i7++;
                } else {
                    Arrays.fill(this.f4275a, i8 * 2, i10 * 2, (Object) null);
                    this.b = i8;
                    return;
                }
            }
        }
    }

    public final void b(int i2) {
        Object[] objArr = new Object[i2];
        int i7 = this.b;
        if (i7 != 0) {
            System.arraycopy(this.f4275a, 0, objArr, 0, i7 * 2);
        }
        this.f4275a = objArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0017, code lost:
        if (r1 == r2) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c(ee.I r5, java.lang.Object r6) {
        /*
            r4 = this;
            java.lang.String r0 = "key"
            He.F.n(r5, r0)
            java.lang.String r0 = "value"
            He.F.n(r6, r0)
            int r0 = r4.b
            int r1 = r0 * 2
            if (r1 == 0) goto L_0x0019
            java.lang.Object[] r2 = r4.f4275a
            if (r2 == 0) goto L_0x0016
            int r2 = r2.length
            goto L_0x0017
        L_0x0016:
            r2 = 0
        L_0x0017:
            if (r1 != r2) goto L_0x0024
        L_0x0019:
            int r0 = r0 * 4
            r1 = 8
            int r0 = java.lang.Math.max(r0, r1)
            r4.b(r0)
        L_0x0024:
            int r0 = r4.b
            byte[] r1 = r5.b
            java.lang.Object[] r2 = r4.f4275a
            int r3 = r0 * 2
            r2[r3] = r1
            byte[] r5 = r5.b(r6)
            java.lang.Object[] r6 = r4.f4275a
            int r0 = r0 * 2
            int r0 = r0 + 1
            r6[r0] = r5
            int r5 = r4.b
            int r5 = r5 + 1
            r4.b = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ee.M.c(ee.I, java.lang.Object):void");
    }

    public final String toString() {
        byte[] bArr;
        byte[] bArr2;
        StringBuilder sb2 = new StringBuilder("Metadata(");
        for (int i2 = 0; i2 < this.b; i2++) {
            if (i2 != 0) {
                sb2.append(',');
            }
            int i7 = i2 * 2;
            Charset charset = g.f166a;
            String str = new String((byte[]) this.f4275a[i7], charset);
            sb2.append(str);
            sb2.append('=');
            if (str.endsWith("-bin")) {
                Object obj = this.f4275a[i7 + 1];
                if (obj instanceof byte[]) {
                    bArr2 = (byte[]) obj;
                } else {
                    bArr2 = ((J) obj).a();
                }
                c cVar = e;
                cVar.getClass();
                int length = bArr2.length;
                F.p(0, length, bArr2.length);
                a aVar = cVar.f339a;
                int i8 = aVar.e;
                int i10 = aVar.f;
                RoundingMode roundingMode = RoundingMode.CEILING;
                StringBuilder sb3 = new StringBuilder(L1.d.g(length, i10) * i8);
                try {
                    cVar.a(sb3, bArr2, length);
                    sb2.append(sb3.toString());
                } catch (IOException e7) {
                    throw new AssertionError(e7);
                }
            } else {
                Object obj2 = this.f4275a[i7 + 1];
                if (obj2 instanceof byte[]) {
                    bArr = (byte[]) obj2;
                } else {
                    bArr = ((J) obj2).a();
                }
                sb2.append(new String(bArr, charset));
            }
        }
        sb2.append(')');
        return sb2.toString();
    }
}
