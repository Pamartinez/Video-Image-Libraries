package androidx.lifecycle;

import Ae.b;
import android.view.View;
import android.view.ViewParent;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroid/view/View;", "currentView", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1 extends k implements b {
    public static final ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1 INSTANCE = new ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1();

    public ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1() {
        super(1);
    }

    public final View invoke(View view) {
        j.e(view, "currentView");
        ViewParent parent = view.getParent();
        if (parent instanceof View) {
            return (View) parent;
        }
        return null;
    }
}
