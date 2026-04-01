package androidx.core.view;

import Sf.k;
import Sf.o;
import android.view.View;
import kotlin.Metadata;
import qe.C1227c;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00000\u0001*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroid/view/View;", "LSf/k;", "getAllViews", "(Landroid/view/View;)LSf/k;", "allViews", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewKt {
    public static final k getAllViews(View view) {
        return new o(new ViewKt$allViews$1(view, (C1227c) null));
    }
}
