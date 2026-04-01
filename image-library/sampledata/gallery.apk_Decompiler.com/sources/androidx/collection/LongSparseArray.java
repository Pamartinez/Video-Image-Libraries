package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.photoremaster.sdk.common.Constants;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1192j;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001a\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0013\b\u0007\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\u000b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0011\u0010\u0006J\u001f\u0010\u0013\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\rH\u0016¢\u0006\u0004\b \u0010!J\u000f\u0010#\u001a\u00020\"H\u0016¢\u0006\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\u001d8\u0000@\u0000X\u000e¢\u0006\u0006\n\u0004\b%\u0010&R\u0016\u0010(\u001a\u00020'8\u0000@\u0000X\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\u001e\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0*8\u0000@\u0000X\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010\u0015\u001a\u00020\u00038\u0000@\u0000X\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010.¨\u0006/"}, d2 = {"Landroidx/collection/LongSparseArray;", "E", "", "", "initialCapacity", "<init>", "(I)V", "clone", "()Landroidx/collection/LongSparseArray;", "", "key", "get", "(J)Ljava/lang/Object;", "Lme/x;", "remove", "(J)V", "index", "removeAt", "value", "put", "(JLjava/lang/Object;)V", "size", "()I", "keyAt", "(I)J", "valueAt", "(I)Ljava/lang/Object;", "indexOfKey", "(J)I", "", "containsKey", "(J)Z", "clear", "()V", "", "toString", "()Ljava/lang/String;", "garbage", "Z", "", "keys", "[J", "", "", "values", "[Ljava/lang/Object;", "I", "collection"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LongSparseArray<E> implements Cloneable {
    public /* synthetic */ boolean garbage;
    public /* synthetic */ long[] keys;
    public /* synthetic */ int size;
    public /* synthetic */ Object[] values;

    public LongSparseArray() {
        this(0, 1, (e) null);
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

    public boolean containsKey(long j2) {
        if (indexOfKey(j2) >= 0) {
            return true;
        }
        return false;
    }

    public E get(long j2) {
        int binarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j2);
        if (binarySearch < 0 || this.values[binarySearch] == LongSparseArrayKt.DELETED) {
            return null;
        }
        return this.values[binarySearch];
    }

    public int indexOfKey(long j2) {
        if (this.garbage) {
            int i2 = this.size;
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i7 = 0;
            for (int i8 = 0; i8 < i2; i8++) {
                Object obj = objArr[i8];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i8 != i7) {
                        jArr[i7] = jArr[i8];
                        objArr[i7] = obj;
                        objArr[i8] = null;
                    }
                    i7++;
                }
            }
            this.garbage = false;
            this.size = i7;
        }
        return ContainerHelpersKt.binarySearch(this.keys, this.size, j2);
    }

    public long keyAt(int i2) {
        int i7;
        if (i2 < 0 || i2 >= (i7 = this.size)) {
            throw new IllegalArgumentException(C0086a.i(i2, "Expected index to be within 0..size()-1, but was ").toString());
        }
        if (this.garbage) {
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i8 = 0;
            for (int i10 = 0; i10 < i7; i10++) {
                Object obj = objArr[i10];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i10 != i8) {
                        jArr[i8] = jArr[i10];
                        objArr[i8] = obj;
                        objArr[i10] = null;
                    }
                    i8++;
                }
            }
            this.garbage = false;
            this.size = i8;
        }
        return this.keys[i2];
    }

    public void put(long j2, E e) {
        int binarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j2);
        if (binarySearch >= 0) {
            this.values[binarySearch] = e;
            return;
        }
        int i2 = ~binarySearch;
        if (i2 >= this.size || this.values[i2] != LongSparseArrayKt.DELETED) {
            if (this.garbage) {
                int i7 = this.size;
                long[] jArr = this.keys;
                if (i7 >= jArr.length) {
                    Object[] objArr = this.values;
                    int i8 = 0;
                    for (int i10 = 0; i10 < i7; i10++) {
                        Object obj = objArr[i10];
                        if (obj != LongSparseArrayKt.DELETED) {
                            if (i10 != i8) {
                                jArr[i8] = jArr[i10];
                                objArr[i8] = obj;
                                objArr[i10] = null;
                            }
                            i8++;
                        }
                    }
                    this.garbage = false;
                    this.size = i8;
                    i2 = ~ContainerHelpersKt.binarySearch(this.keys, i8, j2);
                }
            }
            int i11 = this.size;
            if (i11 >= this.keys.length) {
                int idealLongArraySize = ContainerHelpersKt.idealLongArraySize(i11 + 1);
                long[] copyOf = Arrays.copyOf(this.keys, idealLongArraySize);
                j.d(copyOf, "copyOf(this, newSize)");
                this.keys = copyOf;
                Object[] copyOf2 = Arrays.copyOf(this.values, idealLongArraySize);
                j.d(copyOf2, "copyOf(this, newSize)");
                this.values = copyOf2;
            }
            int i12 = this.size - i2;
            if (i12 != 0) {
                long[] jArr2 = this.keys;
                int i13 = i2 + 1;
                j.e(jArr2, "<this>");
                System.arraycopy(jArr2, i2, jArr2, i13, i12);
                Object[] objArr2 = this.values;
                C1192j.g0(i13, i2, this.size, objArr2, objArr2);
            }
            this.keys[i2] = j2;
            this.values[i2] = e;
            this.size++;
            return;
        }
        this.keys[i2] = j2;
        this.values[i2] = e;
    }

    public void remove(long j2) {
        int binarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j2);
        if (binarySearch >= 0 && this.values[binarySearch] != LongSparseArrayKt.DELETED) {
            this.values[binarySearch] = LongSparseArrayKt.DELETED;
            this.garbage = true;
        }
    }

    public void removeAt(int i2) {
        if (this.values[i2] != LongSparseArrayKt.DELETED) {
            this.values[i2] = LongSparseArrayKt.DELETED;
            this.garbage = true;
        }
    }

    public int size() {
        if (this.garbage) {
            int i2 = this.size;
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i7 = 0;
            for (int i8 = 0; i8 < i2; i8++) {
                Object obj = objArr[i8];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i8 != i7) {
                        jArr[i7] = jArr[i8];
                        objArr[i7] = obj;
                        objArr[i8] = null;
                    }
                    i7++;
                }
            }
            this.garbage = false;
            this.size = i7;
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
            if (valueAt != sb2) {
                sb2.append(valueAt);
            } else {
                sb2.append("(this Map)");
            }
        }
        sb2.append('}');
        String sb3 = sb2.toString();
        j.d(sb3, "StringBuilder(capacity).…builderAction).toString()");
        return sb3;
    }

    public E valueAt(int i2) {
        int i7;
        if (i2 < 0 || i2 >= (i7 = this.size)) {
            throw new IllegalArgumentException(C0086a.i(i2, "Expected index to be within 0..size()-1, but was ").toString());
        }
        if (this.garbage) {
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i8 = 0;
            for (int i10 = 0; i10 < i7; i10++) {
                Object obj = objArr[i10];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i10 != i8) {
                        jArr[i8] = jArr[i10];
                        objArr[i8] = obj;
                        objArr[i10] = null;
                    }
                    i8++;
                }
            }
            this.garbage = false;
            this.size = i8;
        }
        return this.values[i2];
    }

    public LongSparseArray(int i2) {
        if (i2 == 0) {
            this.keys = ContainerHelpersKt.EMPTY_LONGS;
            this.values = ContainerHelpersKt.EMPTY_OBJECTS;
            return;
        }
        int idealLongArraySize = ContainerHelpersKt.idealLongArraySize(i2);
        this.keys = new long[idealLongArraySize];
        this.values = new Object[idealLongArraySize];
    }

    public LongSparseArray<E> clone() {
        Object clone = super.clone();
        j.c(clone, "null cannot be cast to non-null type androidx.collection.LongSparseArray<E of androidx.collection.LongSparseArray>");
        LongSparseArray<E> longSparseArray = (LongSparseArray) clone;
        longSparseArray.keys = (long[]) this.keys.clone();
        longSparseArray.values = (Object[]) this.values.clone();
        return longSparseArray;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LongSparseArray(int i2, int i7, e eVar) {
        this((i7 & 1) != 0 ? 10 : i2);
    }
}
