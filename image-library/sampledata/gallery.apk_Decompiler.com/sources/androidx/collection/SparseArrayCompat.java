package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.photoremaster.sdk.common.Constants;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1192j;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001a\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0013\b\u0007\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\n\u001a\u0004\u0018\u00018\u00002\u0006\u0010\t\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0015\u0010\u000bJ\u0017\u0010\u0016\u001a\u00020\u00032\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001a\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u001a\u0010\u000fJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u001e8\u0000@\u0000X\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010\"\u001a\u00020!8\u0000@\u0000X\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R\u001e\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010%0$8\u0000@\u0000X\u000e¢\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010\u0010\u001a\u00020\u00038\u0000@\u0000X\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010(¨\u0006)"}, d2 = {"Landroidx/collection/SparseArrayCompat;", "E", "", "", "initialCapacity", "<init>", "(I)V", "clone", "()Landroidx/collection/SparseArrayCompat;", "key", "get", "(I)Ljava/lang/Object;", "value", "Lme/x;", "put", "(ILjava/lang/Object;)V", "size", "()I", "index", "keyAt", "(I)I", "valueAt", "indexOfValue", "(Ljava/lang/Object;)I", "clear", "()V", "append", "", "toString", "()Ljava/lang/String;", "", "garbage", "Z", "", "keys", "[I", "", "", "values", "[Ljava/lang/Object;", "I", "collection"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SparseArrayCompat<E> implements Cloneable {
    public /* synthetic */ boolean garbage;
    public /* synthetic */ int[] keys;
    public /* synthetic */ int size;
    public /* synthetic */ Object[] values;

    public SparseArrayCompat() {
        this(0, 1, (e) null);
    }

    public void append(int i2, E e) {
        int i7 = this.size;
        if (i7 == 0 || i2 > this.keys[i7 - 1]) {
            if (this.garbage && i7 >= this.keys.length) {
                SparseArrayCompatKt.gc(this);
            }
            int i8 = this.size;
            if (i8 >= this.keys.length) {
                int idealIntArraySize = ContainerHelpersKt.idealIntArraySize(i8 + 1);
                int[] copyOf = Arrays.copyOf(this.keys, idealIntArraySize);
                j.d(copyOf, "copyOf(this, newSize)");
                this.keys = copyOf;
                Object[] copyOf2 = Arrays.copyOf(this.values, idealIntArraySize);
                j.d(copyOf2, "copyOf(this, newSize)");
                this.values = copyOf2;
            }
            this.keys[i8] = i2;
            this.values[i8] = e;
            this.size = i8 + 1;
            return;
        }
        put(i2, e);
    }

    public void clear() {
        int i2 = this.size;
        Object[] objArr = this.values;
        for (int i7 = 0; i7 < i2; i7++) {
            objArr[i7] = null;
        }
        this.size = 0;
        this.garbage = false;
    }

    public E get(int i2) {
        return SparseArrayCompatKt.commonGet(this, i2);
    }

    public int indexOfValue(E e) {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        int i2 = this.size;
        for (int i7 = 0; i7 < i2; i7++) {
            if (this.values[i7] == e) {
                return i7;
            }
        }
        return -1;
    }

    public int keyAt(int i2) {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        return this.keys[i2];
    }

    public void put(int i2, E e) {
        int binarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, i2);
        if (binarySearch >= 0) {
            this.values[binarySearch] = e;
            return;
        }
        int i7 = ~binarySearch;
        if (i7 >= this.size || this.values[i7] != SparseArrayCompatKt.DELETED) {
            if (this.garbage && this.size >= this.keys.length) {
                SparseArrayCompatKt.gc(this);
                i7 = ~ContainerHelpersKt.binarySearch(this.keys, this.size, i2);
            }
            int i8 = this.size;
            if (i8 >= this.keys.length) {
                int idealIntArraySize = ContainerHelpersKt.idealIntArraySize(i8 + 1);
                int[] copyOf = Arrays.copyOf(this.keys, idealIntArraySize);
                j.d(copyOf, "copyOf(this, newSize)");
                this.keys = copyOf;
                Object[] copyOf2 = Arrays.copyOf(this.values, idealIntArraySize);
                j.d(copyOf2, "copyOf(this, newSize)");
                this.values = copyOf2;
            }
            int i10 = this.size;
            if (i10 - i7 != 0) {
                int[] iArr = this.keys;
                int i11 = i7 + 1;
                C1192j.f0(i11, i7, i10, iArr, iArr);
                Object[] objArr = this.values;
                C1192j.g0(i11, i7, this.size, objArr, objArr);
            }
            this.keys[i7] = i2;
            this.values[i7] = e;
            this.size++;
            return;
        }
        this.keys[i7] = i2;
        this.values[i7] = e;
    }

    public int size() {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        return this.size;
    }

    public String toString() {
        if (size() <= 0) {
            return Constants.EMPTY_JSON_STRING;
        }
        StringBuilder sb2 = new StringBuilder(this.size * 28);
        sb2.append('{');
        int i2 = this.size;
        for (int i7 = 0; i7 < i2; i7++) {
            if (i7 > 0) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
            sb2.append(keyAt(i7));
            sb2.append('=');
            Object valueAt = valueAt(i7);
            if (valueAt != this) {
                sb2.append(valueAt);
            } else {
                sb2.append("(this Map)");
            }
        }
        sb2.append('}');
        String sb3 = sb2.toString();
        j.d(sb3, "buffer.toString()");
        return sb3;
    }

    public E valueAt(int i2) {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        return this.values[i2];
    }

    public SparseArrayCompat(int i2) {
        if (i2 == 0) {
            this.keys = ContainerHelpersKt.EMPTY_INTS;
            this.values = ContainerHelpersKt.EMPTY_OBJECTS;
            return;
        }
        int idealIntArraySize = ContainerHelpersKt.idealIntArraySize(i2);
        this.keys = new int[idealIntArraySize];
        this.values = new Object[idealIntArraySize];
    }

    public SparseArrayCompat<E> clone() {
        Object clone = super.clone();
        j.c(clone, "null cannot be cast to non-null type androidx.collection.SparseArrayCompat<E of androidx.collection.SparseArrayCompat>");
        SparseArrayCompat<E> sparseArrayCompat = (SparseArrayCompat) clone;
        sparseArrayCompat.keys = (int[]) this.keys.clone();
        sparseArrayCompat.values = (Object[]) this.values.clone();
        return sparseArrayCompat;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SparseArrayCompat(int i2, int i7, e eVar) {
        this((i7 & 1) != 0 ? 10 : i2);
    }
}
