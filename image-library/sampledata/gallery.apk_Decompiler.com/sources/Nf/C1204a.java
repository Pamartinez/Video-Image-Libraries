package nf;

import Ae.b;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1193k;
import ne.C1194l;
import ne.C1202t;

/* renamed from: nf.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1204a {

    /* renamed from: a  reason: collision with root package name */
    public final int[] f4951a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public final int f4952c;
    public final int d;
    public final List e;

    public C1204a(int... iArr) {
        int i2;
        int i7;
        List list;
        j.e(iArr, "numbers");
        this.f4951a = iArr;
        Integer o0 = C1192j.o0(0, iArr);
        int i8 = -1;
        if (o0 != null) {
            i2 = o0.intValue();
        } else {
            i2 = -1;
        }
        this.b = i2;
        Integer o02 = C1192j.o0(1, iArr);
        if (o02 != null) {
            i7 = o02.intValue();
        } else {
            i7 = -1;
        }
        this.f4952c = i7;
        Integer o03 = C1192j.o0(2, iArr);
        this.d = o03 != null ? o03.intValue() : i8;
        if (iArr.length <= 3) {
            list = C1202t.d;
        } else if (iArr.length <= 1024) {
            list = C1194l.k1(new C1193k(0, iArr).subList(3, iArr.length));
        } else {
            throw new IllegalArgumentException(N2.j.e(new StringBuilder("BinaryVersion with length more than 1024 are not supported. Provided length "), iArr.length, '.'));
        }
        this.e = list;
    }

    public final boolean a(int i2, int i7, int i8) {
        int i10 = this.b;
        if (i10 > i2) {
            return true;
        }
        if (i10 < i2) {
            return false;
        }
        int i11 = this.f4952c;
        if (i11 > i7) {
            return true;
        }
        if (i11 >= i7 && this.d >= i8) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        r3 = (nf.C1204a) r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0030
            java.lang.Class r0 = r2.getClass()
            java.lang.Class r1 = r3.getClass()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0030
            nf.a r3 = (nf.C1204a) r3
            int r0 = r3.b
            int r1 = r2.b
            if (r1 != r0) goto L_0x0030
            int r0 = r2.f4952c
            int r1 = r3.f4952c
            if (r0 != r1) goto L_0x0030
            int r0 = r2.d
            int r1 = r3.d
            if (r0 != r1) goto L_0x0030
            java.util.List r2 = r2.e
            java.util.List r3 = r3.e
            boolean r2 = kotlin.jvm.internal.j.a(r2, r3)
            if (r2 == 0) goto L_0x0030
            r2 = 1
            return r2
        L_0x0030:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: nf.C1204a.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i2 = this.b;
        int i7 = (i2 * 31) + this.f4952c + i2;
        int i8 = (i7 * 31) + this.d + i7;
        return this.e.hashCode() + (i8 * 31) + i8;
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        for (int i2 : this.f4951a) {
            if (i2 == -1) {
                break;
            }
            arrayList.add(Integer.valueOf(i2));
        }
        if (arrayList.isEmpty()) {
            return "unknown";
        }
        return C1194l.R0(arrayList, ".", (String) null, (String) null, (b) null, 62);
    }
}
