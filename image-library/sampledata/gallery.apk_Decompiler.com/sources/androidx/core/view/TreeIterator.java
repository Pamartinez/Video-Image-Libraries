package androidx.core.view;

import Ae.b;
import Be.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import ne.C1194l;
import ne.C1200r;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B1\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u001a\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u000f\u0010\u0010R(\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0011R \u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u00128\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/core/view/TreeIterator;", "T", "", "rootIterator", "Lkotlin/Function1;", "getChildIterator", "<init>", "(Ljava/util/Iterator;LAe/b;)V", "item", "Lme/x;", "prepareNextIterator", "(Ljava/lang/Object;)V", "", "hasNext", "()Z", "next", "()Ljava/lang/Object;", "LAe/b;", "", "stack", "Ljava/util/List;", "iterator", "Ljava/util/Iterator;", "core-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TreeIterator<T> implements Iterator<T>, a {
    private final b getChildIterator;
    private Iterator<? extends T> iterator;
    private final List<Iterator<T>> stack = new ArrayList();

    public TreeIterator(Iterator<? extends T> it, b bVar) {
        this.getChildIterator = bVar;
        this.iterator = it;
    }

    private final void prepareNextIterator(T t) {
        Iterator<? extends T> it = (Iterator) this.getChildIterator.invoke(t);
        if (it == null || !it.hasNext()) {
            while (!this.iterator.hasNext() && !this.stack.isEmpty()) {
                this.iterator = (Iterator) C1194l.T0(this.stack);
                C1200r.C0(this.stack);
            }
            return;
        }
        this.stack.add(this.iterator);
        this.iterator = it;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public T next() {
        T next = this.iterator.next();
        prepareNextIterator(next);
        return next;
    }

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
