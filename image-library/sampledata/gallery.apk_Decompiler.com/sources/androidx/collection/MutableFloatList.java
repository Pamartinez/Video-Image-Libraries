package androidx.collection;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u0005¨\u0006\u000e"}, d2 = {"Landroidx/collection/MutableFloatList;", "Landroidx/collection/FloatList;", "", "initialCapacity", "<init>", "(I)V", "", "element", "", "add", "(F)Z", "capacity", "Lme/x;", "ensureCapacity", "collection"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MutableFloatList extends FloatList {
    public MutableFloatList(int i2) {
        super(i2, (e) null);
    }

    public final boolean add(float f) {
        ensureCapacity(this._size + 1);
        float[] fArr = this.content;
        int i2 = this._size;
        fArr[i2] = f;
        this._size = i2 + 1;
        return true;
    }

    public final void ensureCapacity(int i2) {
        float[] fArr = this.content;
        if (fArr.length < i2) {
            float[] copyOf = Arrays.copyOf(fArr, Math.max(i2, (fArr.length * 3) / 2));
            j.d(copyOf, "copyOf(this, newSize)");
            this.content = copyOf;
        }
    }
}
