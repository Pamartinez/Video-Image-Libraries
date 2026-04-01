package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1192j;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0013\b\u0007\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\u000b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u000e\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00158\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u00020\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u00020\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u0019R\u0016\u0010\u001b\u001a\u00020\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u0019¨\u0006\u001c"}, d2 = {"Landroidx/collection/CircularArray;", "E", "", "", "minCapacity", "<init>", "(I)V", "Lme/x;", "doubleCapacity", "()V", "element", "addLast", "(Ljava/lang/Object;)V", "index", "get", "(I)Ljava/lang/Object;", "size", "()I", "", "isEmpty", "()Z", "", "elements", "[Ljava/lang/Object;", "head", "I", "tail", "capacityBitmask", "collection"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CircularArray<E> {
    private int capacityBitmask;
    private E[] elements;
    private int head;
    private int tail;

    public CircularArray(int i2) {
        if (i2 < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        } else if (i2 <= 1073741824) {
            i2 = Integer.bitCount(i2) != 1 ? Integer.highestOneBit(i2 - 1) << 1 : i2;
            this.capacityBitmask = i2 - 1;
            this.elements = new Object[i2];
        } else {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
    }

    private final void doubleCapacity() {
        E[] eArr = this.elements;
        int length = eArr.length;
        int i2 = this.head;
        int i7 = length - i2;
        int i8 = length << 1;
        if (i8 >= 0) {
            E[] eArr2 = new Object[i8];
            C1192j.g0(0, i2, length, eArr, eArr2);
            C1192j.g0(i7, 0, this.head, this.elements, eArr2);
            this.elements = eArr2;
            this.head = 0;
            this.tail = length;
            this.capacityBitmask = i8 - 1;
            return;
        }
        throw new RuntimeException("Max array capacity exceeded");
    }

    public final void addLast(E e) {
        E[] eArr = this.elements;
        int i2 = this.tail;
        eArr[i2] = e;
        int i7 = this.capacityBitmask & (i2 + 1);
        this.tail = i7;
        if (i7 == this.head) {
            doubleCapacity();
        }
    }

    public final E get(int i2) {
        if (i2 < 0 || i2 >= size()) {
            CollectionPlatformUtils collectionPlatformUtils = CollectionPlatformUtils.INSTANCE;
            throw new ArrayIndexOutOfBoundsException();
        }
        E e = this.elements[this.capacityBitmask & (this.head + i2)];
        j.b(e);
        return e;
    }

    public final boolean isEmpty() {
        if (this.head == this.tail) {
            return true;
        }
        return false;
    }

    public final int size() {
        return this.capacityBitmask & (this.tail - this.head);
    }
}
