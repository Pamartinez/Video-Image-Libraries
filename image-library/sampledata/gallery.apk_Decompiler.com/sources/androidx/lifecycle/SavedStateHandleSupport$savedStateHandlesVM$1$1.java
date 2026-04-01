package androidx.lifecycle;

import Ae.b;
import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/lifecycle/SavedStateHandlesVM;", "Landroidx/lifecycle/viewmodel/CreationExtras;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SavedStateHandleSupport$savedStateHandlesVM$1$1 extends k implements b {
    public static final SavedStateHandleSupport$savedStateHandlesVM$1$1 INSTANCE = new SavedStateHandleSupport$savedStateHandlesVM$1$1();

    public SavedStateHandleSupport$savedStateHandlesVM$1$1() {
        super(1);
    }

    public final SavedStateHandlesVM invoke(CreationExtras creationExtras) {
        j.e(creationExtras, "$this$initializer");
        return new SavedStateHandlesVM();
    }
}
