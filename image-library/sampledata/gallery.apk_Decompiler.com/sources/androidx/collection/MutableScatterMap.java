package androidx.collection;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003B\u0011\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\t\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000b\u0010\u0007J\u000f\u0010\f\u001a\u00020\bH\u0002¢\u0006\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/collection/MutableScatterMap;", "K", "V", "Landroidx/collection/ScatterMap;", "", "initialCapacity", "<init>", "(I)V", "Lme/x;", "initializeStorage", "capacity", "initializeMetadata", "initializeGrowth", "()V", "growthLimit", "I", "collection"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MutableScatterMap<K, V> extends ScatterMap<K, V> {
    private int growthLimit;

    public MutableScatterMap(int i2) {
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
        this.keys = new Object[i7];
        this.values = new Object[i7];
    }
}
