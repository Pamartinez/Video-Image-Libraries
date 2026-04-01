package androidx.collection;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0007\u0010\u0005J\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\t\u0010\u0005J\u000f\u0010\n\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u00020\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/collection/MutableFloatSet;", "Landroidx/collection/FloatSet;", "", "initialCapacity", "<init>", "(I)V", "Lme/x;", "initializeStorage", "capacity", "initializeMetadata", "initializeGrowth", "()V", "growthLimit", "I", "collection"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MutableFloatSet extends FloatSet {
    private int growthLimit;

    public MutableFloatSet(int i2) {
        super((e) null);
        if (i2 >= 0) {
            initializeStorage(ScatterMapKt.unloadedCapacity(i2));
            return;
        }
        throw new IllegalArgumentException("Capacity must be a positive value.");
    }

    private final void initializeGrowth() {
        this.growthLimit = ScatterMapKt.loadedCapacity(getCapacity()) - this._size;
    }

    private final void initializeMetadata(int i2) {
        long[] jArr;
        if (i2 == 0) {
            jArr = ScatterMapKt.EmptyGroup;
        } else {
            int i7 = ((i2 + 15) & -8) >> 3;
            long[] jArr2 = new long[i7];
            Arrays.fill(jArr2, 0, i7, -9187201950435737472L);
            jArr = jArr2;
        }
        this.metadata = jArr;
        int i8 = i2 >> 3;
        long j2 = 255 << ((i2 & 7) << 3);
        jArr[i8] = (jArr[i8] & (~j2)) | j2;
        initializeGrowth();
    }

    private final void initializeStorage(int i2) {
        int i7;
        if (i2 > 0) {
            i7 = Math.max(7, ScatterMapKt.normalizeCapacity(i2));
        } else {
            i7 = 0;
        }
        this._capacity = i7;
        initializeMetadata(i7);
        this.elements = new float[i7];
    }
}
