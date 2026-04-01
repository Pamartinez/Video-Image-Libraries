package cg;

import eg.b;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q {
    private volatile AtomicReferenceArray<Object> array;

    public q(int i2) {
        this.array = new AtomicReferenceArray<>(i2);
    }

    public final int a() {
        return this.array.length();
    }

    public final Object b(int i2) {
        AtomicReferenceArray<Object> atomicReferenceArray = this.array;
        if (i2 < atomicReferenceArray.length()) {
            return atomicReferenceArray.get(i2);
        }
        return null;
    }

    public final void c(int i2, b bVar) {
        AtomicReferenceArray<Object> atomicReferenceArray = this.array;
        int length = atomicReferenceArray.length();
        if (i2 < length) {
            atomicReferenceArray.set(i2, bVar);
            return;
        }
        int i7 = i2 + 1;
        int i8 = length * 2;
        if (i7 < i8) {
            i7 = i8;
        }
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(i7);
        for (int i10 = 0; i10 < length; i10++) {
            atomicReferenceArray2.set(i10, atomicReferenceArray.get(i10));
        }
        atomicReferenceArray2.set(i2, bVar);
        this.array = atomicReferenceArray2;
    }
}
