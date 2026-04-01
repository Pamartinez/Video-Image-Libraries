package androidx.collection;

import Be.a;
import Be.d;
import androidx.collection.internal.ContainerHelpersKt;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.photoremaster.sdk.common.Constants;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1194l;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010)\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u0012\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0001GB\u0013\b\u0007\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\b\u0016\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\b¢\u0006\u0004\b\u0006\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0007J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0016\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u0004¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u001d\u0010\u0013J\u0017\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u001e\u0010\u0013J\u0015\u0010\u001f\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u0004¢\u0006\u0004\b\u001f\u0010\u001aJ\u0015\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140 ¢\u0006\u0004\b!\u0010\"J'\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00010 \"\u0004\b\u0001\u0010#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00010 ¢\u0006\u0004\b!\u0010%J\u001a\u0010'\u001a\u00020\u00112\b\u0010&\u001a\u0004\u0018\u00010\u0014H\u0002¢\u0006\u0004\b'\u0010\u0013J\u000f\u0010(\u001a\u00020\u0004H\u0016¢\u0006\u0004\b(\u0010)J\u000f\u0010+\u001a\u00020*H\u0016¢\u0006\u0004\b+\u0010,J\u0016\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000-H\u0002¢\u0006\u0004\b.\u0010/J\u001d\u00101\u001a\u00020\u00112\f\u00100\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016¢\u0006\u0004\b1\u00102J\u001d\u00103\u001a\u00020\u00112\f\u00100\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016¢\u0006\u0004\b3\u00102J\u001d\u00104\u001a\u00020\u00112\f\u00100\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016¢\u0006\u0004\b4\u00102J\u001d\u00105\u001a\u00020\u00112\f\u00100\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016¢\u0006\u0004\b5\u00102R\"\u00107\u001a\u0002068\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R*\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140 8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b$\u0010=\u001a\u0004\b>\u0010\"\"\u0004\b?\u0010@R\"\u0010A\u001a\u00020\u00048\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bA\u0010B\u001a\u0004\bC\u0010)\"\u0004\bD\u0010\u0007R\u0014\u0010F\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\bE\u0010)¨\u0006H"}, d2 = {"Landroidx/collection/ArraySet;", "E", "", "", "", "capacity", "<init>", "(I)V", "", "set", "(Ljava/util/Collection;)V", "Lme/x;", "clear", "()V", "minimumCapacity", "ensureCapacity", "element", "", "contains", "(Ljava/lang/Object;)Z", "", "key", "indexOf", "(Ljava/lang/Object;)I", "index", "valueAt", "(I)Ljava/lang/Object;", "isEmpty", "()Z", "add", "remove", "removeAt", "", "toArray", "()[Ljava/lang/Object;", "T", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "other", "equals", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "", "iterator", "()Ljava/util/Iterator;", "elements", "containsAll", "(Ljava/util/Collection;)Z", "addAll", "removeAll", "retainAll", "", "hashes", "[I", "getHashes$collection", "()[I", "setHashes$collection", "([I)V", "[Ljava/lang/Object;", "getArray$collection", "setArray$collection", "([Ljava/lang/Object;)V", "_size", "I", "get_size$collection", "set_size$collection", "getSize", "size", "ElementIterator", "collection"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ArraySet<E> implements Collection<E>, Set<E>, a, d {
    private int _size;
    private Object[] array;
    private int[] hashes;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0014¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0014¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/collection/ArraySet$ElementIterator;", "Landroidx/collection/IndexBasedArrayIterator;", "<init>", "(Landroidx/collection/ArraySet;)V", "", "index", "elementAt", "(I)Ljava/lang/Object;", "Lme/x;", "removeAt", "(I)V", "collection"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class ElementIterator extends IndexBasedArrayIterator<E> {
        public ElementIterator() {
            super(ArraySet.this.get_size$collection());
        }

        public E elementAt(int i2) {
            return ArraySet.this.valueAt(i2);
        }

        public void removeAt(int i2) {
            ArraySet.this.removeAt(i2);
        }
    }

    public ArraySet() {
        this(0, 1, (e) null);
    }

    public boolean add(E e) {
        int i2;
        int i7;
        int i8 = get_size$collection();
        if (e == null) {
            i7 = ArraySetKt.indexOfNull(this);
            i2 = 0;
        } else {
            int hashCode = e.hashCode();
            i2 = hashCode;
            i7 = ArraySetKt.indexOf(this, e, hashCode);
        }
        if (i7 >= 0) {
            return false;
        }
        int i10 = ~i7;
        if (i8 >= getHashes$collection().length) {
            int i11 = 8;
            if (i8 >= 8) {
                i11 = (i8 >> 1) + i8;
            } else if (i8 < 4) {
                i11 = 4;
            }
            int[] hashes$collection = getHashes$collection();
            Object[] array$collection = getArray$collection();
            ArraySetKt.allocArrays(this, i11);
            if (i8 != get_size$collection()) {
                throw new ConcurrentModificationException();
            } else if (getHashes$collection().length != 0) {
                C1192j.f0(0, 0, hashes$collection.length, hashes$collection, getHashes$collection());
                C1192j.h0(0, array$collection.length, 6, array$collection, getArray$collection());
            }
        }
        if (i10 < i8) {
            int i12 = i10 + 1;
            C1192j.f0(i12, i10, i8, getHashes$collection(), getHashes$collection());
            C1192j.g0(i12, i10, i8, getArray$collection(), getArray$collection());
        }
        if (i8 != get_size$collection() || i10 >= getHashes$collection().length) {
            throw new ConcurrentModificationException();
        }
        getHashes$collection()[i10] = i2;
        getArray$collection()[i10] = e;
        set_size$collection(get_size$collection() + 1);
        return true;
    }

    public boolean addAll(Collection<? extends E> collection) {
        j.e(collection, "elements");
        ensureCapacity(collection.size() + get_size$collection());
        boolean z = false;
        for (Object add : collection) {
            z |= add(add);
        }
        return z;
    }

    public void clear() {
        if (get_size$collection() != 0) {
            setHashes$collection(ContainerHelpersKt.EMPTY_INTS);
            setArray$collection(ContainerHelpersKt.EMPTY_OBJECTS);
            set_size$collection(0);
        }
        if (get_size$collection() != 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean contains(Object obj) {
        if (indexOf(obj) >= 0) {
            return true;
        }
        return false;
    }

    public boolean containsAll(Collection<? extends Object> collection) {
        j.e(collection, "elements");
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public final void ensureCapacity(int i2) {
        int i7 = get_size$collection();
        if (getHashes$collection().length < i2) {
            int[] hashes$collection = getHashes$collection();
            Object[] array$collection = getArray$collection();
            ArraySetKt.allocArrays(this, i2);
            if (get_size$collection() > 0) {
                C1192j.f0(0, 0, get_size$collection(), hashes$collection, getHashes$collection());
                C1192j.h0(0, get_size$collection(), 6, array$collection, getArray$collection());
            }
        }
        if (get_size$collection() != i7) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Set) || size() != ((Set) obj).size()) {
            return false;
        }
        try {
            int i2 = get_size$collection();
            for (int i7 = 0; i7 < i2; i7++) {
                if (!((Set) obj).contains(valueAt(i7))) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public final Object[] getArray$collection() {
        return this.array;
    }

    public final int[] getHashes$collection() {
        return this.hashes;
    }

    public int getSize() {
        return this._size;
    }

    public final int get_size$collection() {
        return this._size;
    }

    public int hashCode() {
        int[] hashes$collection = getHashes$collection();
        int i2 = get_size$collection();
        int i7 = 0;
        for (int i8 = 0; i8 < i2; i8++) {
            i7 += hashes$collection[i8];
        }
        return i7;
    }

    public final int indexOf(Object obj) {
        if (obj == null) {
            return ArraySetKt.indexOfNull(this);
        }
        return ArraySetKt.indexOf(this, obj, obj.hashCode());
    }

    public boolean isEmpty() {
        if (get_size$collection() <= 0) {
            return true;
        }
        return false;
    }

    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        removeAt(indexOf);
        return true;
    }

    public boolean removeAll(Collection<? extends Object> collection) {
        j.e(collection, "elements");
        boolean z = false;
        for (Object remove : collection) {
            z |= remove(remove);
        }
        return z;
    }

    public final E removeAt(int i2) {
        int i7 = get_size$collection();
        E e = getArray$collection()[i2];
        if (i7 <= 1) {
            clear();
            return e;
        }
        int i8 = i7 - 1;
        int i10 = 8;
        if (getHashes$collection().length <= 8 || get_size$collection() >= getHashes$collection().length / 3) {
            if (i2 < i8) {
                int i11 = i2 + 1;
                C1192j.f0(i2, i11, i7, getHashes$collection(), getHashes$collection());
                C1192j.g0(i2, i11, i7, getArray$collection(), getArray$collection());
            }
            getArray$collection()[i8] = null;
        } else {
            if (get_size$collection() > 8) {
                i10 = get_size$collection() + (get_size$collection() >> 1);
            }
            int[] hashes$collection = getHashes$collection();
            Object[] array$collection = getArray$collection();
            ArraySetKt.allocArrays(this, i10);
            if (i2 > 0) {
                C1192j.f0(0, 0, i2, hashes$collection, getHashes$collection());
                C1192j.h0(0, i2, 6, array$collection, getArray$collection());
            }
            if (i2 < i8) {
                int i12 = i2 + 1;
                C1192j.f0(i2, i12, i7, hashes$collection, getHashes$collection());
                C1192j.g0(i2, i12, i7, array$collection, getArray$collection());
            }
        }
        if (i7 == get_size$collection()) {
            set_size$collection(i8);
            return e;
        }
        throw new ConcurrentModificationException();
    }

    public boolean retainAll(Collection<? extends Object> collection) {
        j.e(collection, "elements");
        boolean z = false;
        for (int i2 = get_size$collection() - 1; -1 < i2; i2--) {
            if (!C1194l.G0(collection, getArray$collection()[i2])) {
                removeAt(i2);
                z = true;
            }
        }
        return z;
    }

    public final void setArray$collection(Object[] objArr) {
        j.e(objArr, "<set-?>");
        this.array = objArr;
    }

    public final void setHashes$collection(int[] iArr) {
        j.e(iArr, "<set-?>");
        this.hashes = iArr;
    }

    public final void set_size$collection(int i2) {
        this._size = i2;
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public final Object[] toArray() {
        return C1192j.i0(this.array, 0, this._size);
    }

    public String toString() {
        if (isEmpty()) {
            return Constants.EMPTY_JSON_STRING;
        }
        StringBuilder sb2 = new StringBuilder(get_size$collection() * 14);
        sb2.append('{');
        int i2 = get_size$collection();
        for (int i7 = 0; i7 < i2; i7++) {
            if (i7 > 0) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
            Object valueAt = valueAt(i7);
            if (valueAt != this) {
                sb2.append(valueAt);
            } else {
                sb2.append("(this Set)");
            }
        }
        sb2.append('}');
        String sb3 = sb2.toString();
        j.d(sb3, "StringBuilder(capacity).…builderAction).toString()");
        return sb3;
    }

    public final E valueAt(int i2) {
        return getArray$collection()[i2];
    }

    public ArraySet(int i2) {
        this.hashes = ContainerHelpersKt.EMPTY_INTS;
        this.array = ContainerHelpersKt.EMPTY_OBJECTS;
        if (i2 > 0) {
            ArraySetKt.allocArrays(this, i2);
        }
    }

    public final <T> T[] toArray(T[] tArr) {
        j.e(tArr, "array");
        T[] resizeForToArray = ArraySetJvmUtil.resizeForToArray(tArr, this._size);
        C1192j.g0(0, 0, this._size, this.array, resizeForToArray);
        return resizeForToArray;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ArraySet(int i2, int i7, e eVar) {
        this((i7 & 1) != 0 ? 0 : i2);
    }

    public ArraySet(Collection<? extends E> collection) {
        this(0);
        if (collection != null) {
            addAll(collection);
        }
    }
}
