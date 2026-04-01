package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a)\u0010\u0004\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001f\u0010\u0007\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0002¢\u0006\u0004\b\u0007\u0010\b\"\u0014\u0010\n\u001a\u00020\t8\u0002X\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"E", "Landroidx/collection/SparseArrayCompat;", "", "key", "commonGet", "(Landroidx/collection/SparseArrayCompat;I)Ljava/lang/Object;", "Lme/x;", "gc", "(Landroidx/collection/SparseArrayCompat;)V", "", "DELETED", "Ljava/lang/Object;", "collection"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SparseArrayCompatKt {
    /* access modifiers changed from: private */
    public static final Object DELETED = new Object();

    public static final <E> E commonGet(SparseArrayCompat<E> sparseArrayCompat, int i2) {
        E e;
        j.e(sparseArrayCompat, "<this>");
        int binarySearch = ContainerHelpersKt.binarySearch(sparseArrayCompat.keys, sparseArrayCompat.size, i2);
        if (binarySearch < 0 || (e = sparseArrayCompat.values[binarySearch]) == DELETED) {
            return null;
        }
        return e;
    }

    /* access modifiers changed from: private */
    public static final <E> void gc(SparseArrayCompat<E> sparseArrayCompat) {
        int i2 = sparseArrayCompat.size;
        int[] iArr = sparseArrayCompat.keys;
        Object[] objArr = sparseArrayCompat.values;
        int i7 = 0;
        for (int i8 = 0; i8 < i2; i8++) {
            Object obj = objArr[i8];
            if (obj != DELETED) {
                if (i8 != i7) {
                    iArr[i7] = iArr[i8];
                    objArr[i7] = obj;
                    objArr[i8] = null;
                }
                i7++;
            }
        }
        sparseArrayCompat.garbage = false;
        sparseArrayCompat.size = i7;
    }
}
