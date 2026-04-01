package androidx.lifecycle;

import android.view.View;
import androidx.lifecycle.viewmodel.R$id;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001d\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Landroid/view/View;", "Landroidx/lifecycle/ViewModelStoreOwner;", "viewModelStoreOwner", "Lme/x;", "set", "(Landroid/view/View;Landroidx/lifecycle/ViewModelStoreOwner;)V", "setViewTreeViewModelStoreOwner", "lifecycle-viewmodel_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewTreeViewModelStoreOwner {
    public static final void set(View view, ViewModelStoreOwner viewModelStoreOwner) {
        j.e(view, "<this>");
        view.setTag(R$id.view_tree_view_model_store_owner, viewModelStoreOwner);
    }
}
