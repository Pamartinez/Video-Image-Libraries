package androidx.core.view;

import Sf.k;
import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"androidx/core/view/ViewGroupKt$special$$inlined$Sequence$1", "LSf/k;", "", "iterator", "()Ljava/util/Iterator;", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ViewGroupKt$special$$inlined$Sequence$1 implements k {
    final /* synthetic */ ViewGroup $this_descendants$inlined;

    public ViewGroupKt$special$$inlined$Sequence$1(ViewGroup viewGroup) {
        this.$this_descendants$inlined = viewGroup;
    }

    public Iterator<View> iterator() {
        return new TreeIterator(ViewGroupKt.getChildren(this.$this_descendants$inlined).iterator(), ViewGroupKt$descendants$1$1.INSTANCE);
    }
}
