package F2;

import He.F;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class X {

    /* renamed from: a  reason: collision with root package name */
    public Object[] f254a;
    public int b = 0;

    /* renamed from: c  reason: collision with root package name */
    public W f255c;

    public X(int i2) {
        this.f254a = new Object[(i2 * 2)];
    }

    public Y a() {
        D0 d0;
        W w = this.f255c;
        if (w == null) {
            int i2 = this.b;
            Object[] objArr = this.f254a;
            if (i2 == 0) {
                d0 = D0.k;
            } else if (i2 == 1) {
                Objects.requireNonNull(objArr[0]);
                Objects.requireNonNull(objArr[1]);
                d0 = new D0(objArr, 1, (Object) null);
            } else {
                F.o(i2, objArr.length >> 1);
                Object j2 = D0.j(objArr, i2, C0010c0.w(i2), 0);
                if (j2 instanceof Object[]) {
                    Object[] objArr2 = (Object[]) j2;
                    this.f255c = (W) objArr2[2];
                    Object obj = objArr2[0];
                    int intValue = ((Integer) objArr2[1]).intValue();
                    objArr = Arrays.copyOf(objArr, intValue * 2);
                    j2 = obj;
                    i2 = intValue;
                }
                d0 = new D0(objArr, i2, j2);
            }
            W w6 = this.f255c;
            if (w6 == null) {
                return d0;
            }
            throw w6.a();
        }
        throw w.a();
    }

    public X b(Object obj, Object obj2) {
        int i2 = (this.b + 1) * 2;
        Object[] objArr = this.f254a;
        if (i2 > objArr.length) {
            this.f254a = Arrays.copyOf(objArr, N.e(objArr.length, i2));
        }
        C0040v.b(obj, obj2);
        Object[] objArr2 = this.f254a;
        int i7 = this.b;
        int i8 = i7 * 2;
        objArr2[i8] = obj;
        objArr2[i8 + 1] = obj2;
        this.b = i7 + 1;
        return this;
    }
}
