package rf;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C extends C1255e {
    public static final int[] k;
    public final int e;
    public final C1255e f;
    public final C1255e g;

    /* renamed from: h  reason: collision with root package name */
    public final int f5050h;

    /* renamed from: i  reason: collision with root package name */
    public final int f5051i;

    /* renamed from: j  reason: collision with root package name */
    public int f5052j = 0;

    static {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        int i7 = 1;
        while (i2 > 0) {
            arrayList.add(Integer.valueOf(i2));
            int i8 = i7 + i2;
            i7 = i2;
            i2 = i8;
        }
        arrayList.add(Integer.MAX_VALUE);
        k = new int[arrayList.size()];
        int i10 = 0;
        while (true) {
            int[] iArr = k;
            if (i10 < iArr.length) {
                iArr[i10] = ((Integer) arrayList.get(i10)).intValue();
                i10++;
            } else {
                return;
            }
        }
    }

    public C(C1255e eVar, C1255e eVar2) {
        this.f = eVar;
        this.g = eVar2;
        int size = eVar.size();
        this.f5050h = size;
        this.e = eVar2.size() + size;
        this.f5051i = Math.max(eVar.s(), eVar2.s()) + 1;
    }

    public final void B(OutputStream outputStream, int i2, int i7) {
        int i8 = i2 + i7;
        C1255e eVar = this.f;
        int i10 = this.f5050h;
        if (i8 <= i10) {
            eVar.B(outputStream, i2, i7);
            return;
        }
        C1255e eVar2 = this.g;
        if (i2 >= i10) {
            eVar2.B(outputStream, i2 - i10, i7);
            return;
        }
        int i11 = i10 - i2;
        eVar.B(outputStream, i2, i11);
        eVar2.B(outputStream, 0, i7 - i11);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        r12 = (rf.C1255e) r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r12) {
        /*
            r11 = this;
            if (r12 != r11) goto L_0x0003
            goto L_0x005a
        L_0x0003:
            boolean r0 = r12 instanceof rf.C1255e
            r1 = 0
            if (r0 != 0) goto L_0x0009
            goto L_0x0054
        L_0x0009:
            rf.e r12 = (rf.C1255e) r12
            int r0 = r12.size()
            int r2 = r11.e
            if (r2 == r0) goto L_0x0014
            goto L_0x0054
        L_0x0014:
            if (r2 != 0) goto L_0x0017
            goto L_0x005a
        L_0x0017:
            int r0 = r11.f5052j
            if (r0 == 0) goto L_0x0026
            int r0 = r12.y()
            if (r0 == 0) goto L_0x0026
            int r3 = r11.f5052j
            if (r3 == r0) goto L_0x0026
            goto L_0x0054
        L_0x0026:
            rf.A r0 = new rf.A
            r0.<init>(r11)
            rf.x r11 = r0.next()
            rf.A r3 = new rf.A
            r3.<init>(r12)
            rf.x r12 = r3.next()
            r4 = r1
            r5 = r4
            r6 = r5
        L_0x003b:
            byte[] r7 = r11.e
            int r7 = r7.length
            int r7 = r7 - r4
            byte[] r8 = r12.e
            int r8 = r8.length
            int r8 = r8 - r5
            int r9 = java.lang.Math.min(r7, r8)
            if (r4 != 0) goto L_0x004e
            boolean r10 = r11.C(r12, r5, r9)
            goto L_0x0052
        L_0x004e:
            boolean r10 = r12.C(r11, r4, r9)
        L_0x0052:
            if (r10 != 0) goto L_0x0055
        L_0x0054:
            return r1
        L_0x0055:
            int r6 = r6 + r9
            if (r6 < r2) goto L_0x0062
            if (r6 != r2) goto L_0x005c
        L_0x005a:
            r11 = 1
            return r11
        L_0x005c:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            r11.<init>()
            throw r11
        L_0x0062:
            if (r9 != r7) goto L_0x006a
            rf.x r11 = r0.next()
            r4 = r1
            goto L_0x006b
        L_0x006a:
            int r4 = r4 + r9
        L_0x006b:
            if (r9 != r8) goto L_0x0073
            rf.x r12 = r3.next()
            r5 = r1
            goto L_0x003b
        L_0x0073:
            int r5 = r5 + r9
            goto L_0x003b
        */
        throw new UnsupportedOperationException("Method not decompiled: rf.C.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i2 = this.f5052j;
        if (i2 == 0) {
            int i7 = this.e;
            i2 = w(i7, 0, i7);
            if (i2 == 0) {
                i2 = 1;
            }
            this.f5052j = i2;
        }
        return i2;
    }

    public final Iterator iterator() {
        return new B(this);
    }

    public final void r(int i2, int i7, int i8, byte[] bArr) {
        int i10 = i2 + i8;
        C1255e eVar = this.f;
        int i11 = this.f5050h;
        if (i10 <= i11) {
            eVar.r(i2, i7, i8, bArr);
            return;
        }
        C1255e eVar2 = this.g;
        if (i2 >= i11) {
            eVar2.r(i2 - i11, i7, i8, bArr);
            return;
        }
        int i12 = i11 - i2;
        eVar.r(i2, i7, i12, bArr);
        eVar2.r(0, i7 + i12, i8 - i12, bArr);
    }

    public final int s() {
        return this.f5051i;
    }

    public final int size() {
        return this.e;
    }

    public final boolean t() {
        if (this.e >= k[this.f5051i]) {
            return true;
        }
        return false;
    }

    public final boolean u() {
        int x9 = this.f.x(0, 0, this.f5050h);
        C1255e eVar = this.g;
        if (eVar.x(x9, 0, eVar.size()) == 0) {
            return true;
        }
        return false;
    }

    public final int w(int i2, int i7, int i8) {
        int i10 = i7 + i8;
        C1255e eVar = this.f;
        int i11 = this.f5050h;
        if (i10 <= i11) {
            return eVar.w(i2, i7, i8);
        }
        C1255e eVar2 = this.g;
        if (i7 >= i11) {
            return eVar2.w(i2, i7 - i11, i8);
        }
        int i12 = i11 - i7;
        return eVar2.w(eVar.w(i2, i7, i12), 0, i8 - i12);
    }

    public final int x(int i2, int i7, int i8) {
        int i10 = i7 + i8;
        C1255e eVar = this.f;
        int i11 = this.f5050h;
        if (i10 <= i11) {
            return eVar.x(i2, i7, i8);
        }
        C1255e eVar2 = this.g;
        if (i7 >= i11) {
            return eVar2.x(i2, i7 - i11, i8);
        }
        int i12 = i11 - i7;
        return eVar2.x(eVar.x(i2, i7, i12), 0, i8 - i12);
    }

    public final int y() {
        return this.f5052j;
    }

    public final String z() {
        byte[] bArr;
        int i2 = this.e;
        if (i2 == 0) {
            bArr = t.f5070a;
        } else {
            byte[] bArr2 = new byte[i2];
            r(0, 0, i2, bArr2);
            bArr = bArr2;
        }
        return new String(bArr, "UTF-8");
    }
}
