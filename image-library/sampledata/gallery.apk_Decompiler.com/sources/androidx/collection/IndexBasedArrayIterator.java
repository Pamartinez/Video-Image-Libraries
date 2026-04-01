package androidx.collection;

import Be.a;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010)\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\b\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u0003H$¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0003H$¢\u0006\u0004\b\u000b\u0010\u0006J\u0010\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u00020\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0007\u001a\u00020\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010\u0014R\u0016\u0010\u0015\u001a\u00020\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/collection/IndexBasedArrayIterator;", "T", "", "", "startingSize", "<init>", "(I)V", "index", "elementAt", "(I)Ljava/lang/Object;", "Lme/x;", "removeAt", "", "hasNext", "()Z", "next", "()Ljava/lang/Object;", "remove", "()V", "size", "I", "canRemove", "Z", "collection"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class IndexBasedArrayIterator<T> implements Iterator<T>, a {
    private boolean canRemove;
    private int index;
    private int size;

    public IndexBasedArrayIterator(int i2) {
        this.size = i2;
    }

    public abstract T elementAt(int i2);

    public boolean hasNext() {
        if (this.index < this.size) {
            return true;
        }
        return false;
    }

    public T next() {
        if (hasNext()) {
            T elementAt = elementAt(this.index);
            this.index++;
            this.canRemove = true;
            return elementAt;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (this.canRemove) {
            int i2 = this.index - 1;
            this.index = i2;
            removeAt(i2);
            this.size--;
            this.canRemove = false;
            return;
        }
        throw new IllegalStateException("Call next() before removing an element.");
    }

    public abstract void removeAt(int i2);
}
