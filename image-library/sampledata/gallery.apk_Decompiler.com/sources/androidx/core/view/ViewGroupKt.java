package androidx.core.view;

import Sf.k;
import android.view.View;
import android.view.ViewGroup;
import c0.C0086a;
import java.util.Iterator;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001c\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006*\u00020\u0000H\u0002¢\u0006\u0004\b\u0007\u0010\b\"\u001b\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\t*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\"\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\t*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u000f"}, d2 = {"Landroid/view/ViewGroup;", "", "index", "Landroid/view/View;", "get", "(Landroid/view/ViewGroup;I)Landroid/view/View;", "", "iterator", "(Landroid/view/ViewGroup;)Ljava/util/Iterator;", "LSf/k;", "getChildren", "(Landroid/view/ViewGroup;)LSf/k;", "children", "getDescendants", "descendants", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewGroupKt {
    public static final View get(ViewGroup viewGroup, int i2) {
        View childAt = viewGroup.getChildAt(i2);
        if (childAt != null) {
            return childAt;
        }
        StringBuilder o2 = C0086a.o(i2, "Index: ", ", Size: ");
        o2.append(viewGroup.getChildCount());
        throw new IndexOutOfBoundsException(o2.toString());
    }

    public static final k getChildren(ViewGroup viewGroup) {
        return new ViewGroupKt$children$1(viewGroup);
    }

    public static final k getDescendants(ViewGroup viewGroup) {
        return new ViewGroupKt$special$$inlined$Sequence$1(viewGroup);
    }

    public static final Iterator<View> iterator(ViewGroup viewGroup) {
        return new ViewGroupKt$iterator$1(viewGroup);
    }
}
