package ge;

import com.samsung.android.sdk.scs.base.StatusCodes;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class w1 {

    /* renamed from: a  reason: collision with root package name */
    public final int f4569a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public final int f4570c;
    public final AtomicInteger d;

    public w1(float f, float f5) {
        AtomicInteger atomicInteger = new AtomicInteger();
        this.d = atomicInteger;
        this.f4570c = (int) (f5 * 1000.0f);
        int i2 = (int) (f * 1000.0f);
        this.f4569a = i2;
        this.b = i2 / 2;
        atomicInteger.set(i2);
    }

    public final boolean a() {
        AtomicInteger atomicInteger;
        int i2;
        int i7;
        do {
            atomicInteger = this.d;
            i2 = atomicInteger.get();
            if (i2 == 0) {
                return false;
            }
            i7 = i2 + StatusCodes.UNDEFINED;
        } while (!atomicInteger.compareAndSet(i2, Math.max(i7, 0)));
        if (i7 > this.b) {
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof w1)) {
            return false;
        }
        w1 w1Var = (w1) obj;
        if (this.f4569a == w1Var.f4569a && this.f4570c == w1Var.f4570c) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f4569a), Integer.valueOf(this.f4570c)});
    }
}
