package com.samsung.android.vexfwk.metadata;

import java.util.Iterator;
import kotlin.Metadata;
import ne.w;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0001J\u0015\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0006\u001a\u00028\u00012\u0006\u0010\u0005\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"com/samsung/android/vexfwk/metadata/VexFwkMetadataValidator$getDuplicates$$inlined$groupingBy$1", "Lne/w;", "", "sourceIterator", "()Ljava/util/Iterator;", "element", "keyOf", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkMetadataValidator$getDuplicates$$inlined$groupingBy$1 implements w {
    final /* synthetic */ Iterable $this_groupingBy;

    public VexFwkMetadataValidator$getDuplicates$$inlined$groupingBy$1(Iterable iterable) {
        this.$this_groupingBy = iterable;
    }

    public Iterator<T> sourceIterator() {
        return this.$this_groupingBy.iterator();
    }

    public T keyOf(T t) {
        return t;
    }
}
