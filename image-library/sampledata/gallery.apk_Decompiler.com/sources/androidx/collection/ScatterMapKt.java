package androidx.collection;

import c0.C0086a;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000(\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\u001a\u0017\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0017\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0005\u0010\u0003\u001a\u0017\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0006\u0010\u0003\"\u0014\u0010\b\u001a\u00020\u00078\u0000X\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t\"\"\u0010\r\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0004\u0012\u00020\f0\n8\u0002X\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e*\f\b\u0000\u0010\u0010\"\u00020\u000f2\u00020\u000f*\f\b\u0000\u0010\u0011\"\u00020\u000f2\u00020\u000f*\f\b\u0000\u0010\u0012\"\u00020\u000f2\u00020\u000f¨\u0006\u0013"}, d2 = {"", "n", "normalizeCapacity", "(I)I", "capacity", "loadedCapacity", "unloadedCapacity", "", "EmptyGroup", "[J", "Landroidx/collection/MutableScatterMap;", "", "", "EmptyScatterMap", "Landroidx/collection/MutableScatterMap;", "", "Bitmask", "Group", "StaticBitmask", "collection"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ScatterMapKt {
    public static final long[] EmptyGroup = {-9187201950435737345L, -1};
    private static final MutableScatterMap EmptyScatterMap = new MutableScatterMap(0);

    public static final int loadedCapacity(int i2) {
        if (i2 == 7) {
            return 6;
        }
        return i2 - (i2 / 8);
    }

    public static final int normalizeCapacity(int i2) {
        if (i2 > 0) {
            return -1 >>> Integer.numberOfLeadingZeros(i2);
        }
        return 0;
    }

    public static final int unloadedCapacity(int i2) {
        if (i2 == 7) {
            return 8;
        }
        return C0086a.D(i2, 1, 7, i2);
    }
}
