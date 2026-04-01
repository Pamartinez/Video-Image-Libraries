package F2;

import java.util.Arrays;
import java.util.Collection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class N {

    /* renamed from: a  reason: collision with root package name */
    public Object[] f247a;
    public int b = 0;

    /* renamed from: c  reason: collision with root package name */
    public boolean f248c;

    public N(int i2) {
        C0040v.c(i2, "initialCapacity");
        this.f247a = new Object[i2];
    }

    public static int e(int i2, int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("cannot store more than MAX_VALUE elements");
        } else if (i7 <= i2) {
            return i2;
        } else {
            int i8 = i2 + (i2 >> 1) + 1;
            if (i8 < i7) {
                i8 = Integer.highestOneBit(i7 - 1) << 1;
            }
            if (i8 < 0) {
                return Integer.MAX_VALUE;
            }
            return i8;
        }
    }

    public final void a(Object obj) {
        obj.getClass();
        d(1);
        Object[] objArr = this.f247a;
        int i2 = this.b;
        this.b = i2 + 1;
        objArr[i2] = obj;
    }

    public abstract N b(Object obj);

    public final void c(Iterable iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            d(collection.size());
            if (collection instanceof O) {
                this.b = ((O) collection).q(this.b, this.f247a);
                return;
            }
        }
        for (Object b5 : iterable) {
            b(b5);
        }
    }

    public final void d(int i2) {
        Object[] objArr = this.f247a;
        int e = e(objArr.length, this.b + i2);
        if (e > objArr.length || this.f248c) {
            this.f247a = Arrays.copyOf(this.f247a, e);
            this.f248c = false;
        }
    }
}
