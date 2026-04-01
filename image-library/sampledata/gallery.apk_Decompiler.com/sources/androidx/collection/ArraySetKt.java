package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.ConcurrentModificationException;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a'\u0010\u0004\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a1\u0010\b\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001f\u0010\n\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a'\u0010\u000e\u001a\u00020\r\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\f\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"E", "Landroidx/collection/ArraySet;", "", "hash", "binarySearchInternal", "(Landroidx/collection/ArraySet;I)I", "", "key", "indexOf", "(Landroidx/collection/ArraySet;Ljava/lang/Object;I)I", "indexOfNull", "(Landroidx/collection/ArraySet;)I", "size", "Lme/x;", "allocArrays", "(Landroidx/collection/ArraySet;I)V", "collection"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ArraySetKt {
    public static final <E> void allocArrays(ArraySet<E> arraySet, int i2) {
        j.e(arraySet, "<this>");
        arraySet.setHashes$collection(new int[i2]);
        arraySet.setArray$collection(new Object[i2]);
    }

    public static final <E> int binarySearchInternal(ArraySet<E> arraySet, int i2) {
        j.e(arraySet, "<this>");
        try {
            return ContainerHelpersKt.binarySearch(arraySet.getHashes$collection(), arraySet.get_size$collection(), i2);
        } catch (IndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public static final <E> int indexOf(ArraySet<E> arraySet, Object obj, int i2) {
        j.e(arraySet, "<this>");
        int i7 = arraySet.get_size$collection();
        if (i7 == 0) {
            return -1;
        }
        int binarySearchInternal = binarySearchInternal(arraySet, i2);
        if (binarySearchInternal < 0 || j.a(obj, arraySet.getArray$collection()[binarySearchInternal])) {
            return binarySearchInternal;
        }
        int i8 = binarySearchInternal + 1;
        while (i8 < i7 && arraySet.getHashes$collection()[i8] == i2) {
            if (j.a(obj, arraySet.getArray$collection()[i8])) {
                return i8;
            }
            i8++;
        }
        int i10 = binarySearchInternal - 1;
        while (i10 >= 0 && arraySet.getHashes$collection()[i10] == i2) {
            if (j.a(obj, arraySet.getArray$collection()[i10])) {
                return i10;
            }
            i10--;
        }
        return ~i8;
    }

    public static final <E> int indexOfNull(ArraySet<E> arraySet) {
        j.e(arraySet, "<this>");
        return indexOf(arraySet, (Object) null, 0);
    }
}
