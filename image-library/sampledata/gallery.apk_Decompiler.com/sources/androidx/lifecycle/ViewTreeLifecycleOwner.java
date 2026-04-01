package androidx.lifecycle;

import Sf.n;
import android.view.View;
import androidx.lifecycle.runtime.R$id;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u001d\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0015\u0010\t\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u0007¢\u0006\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Landroid/view/View;", "Landroidx/lifecycle/LifecycleOwner;", "lifecycleOwner", "Lme/x;", "set", "(Landroid/view/View;Landroidx/lifecycle/LifecycleOwner;)V", "setViewTreeLifecycleOwner", "get", "(Landroid/view/View;)Landroidx/lifecycle/LifecycleOwner;", "findViewTreeLifecycleOwner", "lifecycle-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewTreeLifecycleOwner {
    public static final LifecycleOwner get(View view) {
        j.e(view, "<this>");
        return (LifecycleOwner) n.q0(n.u0(n.s0(ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1.INSTANCE, view), ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$2.INSTANCE));
    }

    public static final void set(View view, LifecycleOwner lifecycleOwner) {
        j.e(view, "<this>");
        view.setTag(R$id.view_tree_lifecycle_owner, lifecycleOwner);
    }
}
