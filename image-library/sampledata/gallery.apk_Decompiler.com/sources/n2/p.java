package N2;

import R2.k;
import R2.m;
import T2.c;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f546a;
    public int b = 0;

    /* renamed from: c  reason: collision with root package name */
    public m f547c = null;
    public int[] d = null;

    public p(int i2) {
        this.f546a = new ArrayList(i2);
    }

    public static void b(int i2, n nVar, k kVar) {
        int i7 = kVar.d;
        if (i2 < 0) {
            throw new IllegalArgumentException("address < 0");
        } else if (nVar != null) {
            try {
                throw new NullPointerException("spec.getLocalItem() == null");
            } catch (NullPointerException unused) {
                throw new NullPointerException("spec == null");
            }
        } else {
            throw new NullPointerException("disposition == null");
        }
    }

    public static k d(k kVar) {
        if (kVar == null || kVar.e.getType() != c.s) {
            return kVar;
        }
        return k.d(kVar.d, c.v);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0043, code lost:
        r9 = r6 - 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(int r12, int r13) {
        /*
            r11 = this;
            int[] r0 = r11.d
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0008
            r3 = r1
            goto L_0x0009
        L_0x0008:
            r3 = r2
        L_0x0009:
            if (r12 != 0) goto L_0x000e
            if (r3 != 0) goto L_0x000e
            goto L_0x0016
        L_0x000e:
            if (r12 < 0) goto L_0x0073
            if (r3 != 0) goto L_0x0017
            int r12 = r0.length
            if (r13 < r12) goto L_0x0016
            goto L_0x0017
        L_0x0016:
            return
        L_0x0017:
            int r13 = r13 + r1
            R2.m r12 = new R2.m
            r12.<init>(r13)
            int[] r13 = new int[r13]
            r0 = -1
            java.util.Arrays.fill(r13, r0)
            if (r3 != 0) goto L_0x006e
            R2.m r0 = r11.f547c
            R2.k[] r1 = r0.e
            int r1 = r1.length
            r3 = r2
        L_0x002b:
            if (r3 >= r1) goto L_0x0068
            R2.k r4 = r0.d(r3)
            if (r4 == 0) goto L_0x0065
            R2.k[] r5 = r12.e
            r12.c()
            r6 = -1
            r12.f = r6
            int r6 = r4.d     // Catch:{ ArrayIndexOutOfBoundsException -> 0x005c }
            r5[r6] = r4     // Catch:{ ArrayIndexOutOfBoundsException -> 0x005c }
            r7 = 0
            r8 = 2
            if (r6 <= 0) goto L_0x0051
            int r9 = r6 + -1
            r10 = r5[r9]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x005c }
            if (r10 == 0) goto L_0x0051
            int r10 = r10.c()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x005c }
            if (r10 != r8) goto L_0x0051
            r5[r9] = r7     // Catch:{ ArrayIndexOutOfBoundsException -> 0x005c }
        L_0x0051:
            int r4 = r4.c()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x005c }
            if (r4 != r8) goto L_0x0065
            int r6 = r6 + 1
            r5[r6] = r7     // Catch:{ ArrayIndexOutOfBoundsException -> 0x005c }
            goto L_0x0065
        L_0x005c:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.String r12 = "spec.getReg() out of range"
            r11.<init>(r12)
            throw r11
        L_0x0065:
            int r3 = r3 + 1
            goto L_0x002b
        L_0x0068:
            int[] r0 = r11.d
            int r1 = r0.length
            java.lang.System.arraycopy(r0, r2, r13, r2, r1)
        L_0x006e:
            r11.f547c = r12
            r11.d = r13
            return
        L_0x0073:
            java.lang.RuntimeException r11 = new java.lang.RuntimeException
            java.lang.String r12 = "shouldn't happen"
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: N2.p.a(int, int):void");
    }

    public final void c(int i2, n nVar, k kVar) {
        if (nVar != n.START) {
            int i7 = this.d[kVar.d];
            if (i7 >= 0) {
                ((o) this.f546a.get(i7)).getClass();
                if (i2 == 0) {
                    throw null;
                }
            }
            int i8 = kVar.d;
            k d2 = d(kVar);
            a(i2, i8);
            if (this.d[i8] < 0) {
                ArrayList arrayList = this.f546a;
                int size = arrayList.size();
                while (true) {
                    size--;
                    if (size < 0) {
                        break;
                    }
                    o oVar = (o) arrayList.get(size);
                }
                m mVar = this.f547c;
                mVar.getClass();
                try {
                    mVar.e[d2.d] = null;
                    mVar.f = -1;
                    arrayList.set(size, (Object) null);
                    this.b++;
                    for (int i10 = size - 1; i10 >= 0; i10--) {
                        o oVar2 = (o) arrayList.get(i10);
                    }
                } catch (ArrayIndexOutOfBoundsException unused) {
                    throw new IllegalArgumentException("bogus reg");
                }
            }
        } else {
            throw new RuntimeException("shouldn't happen");
        }
    }
}
