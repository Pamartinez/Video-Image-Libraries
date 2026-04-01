package androidx.lifecycle;

import Ae.a;
import Vf.C0873j;
import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"androidx/lifecycle/WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1", "Landroidx/lifecycle/LifecycleEventObserver;", "Landroidx/lifecycle/LifecycleOwner;", "source", "Landroidx/lifecycle/Lifecycle$Event;", "event", "Lme/x;", "onStateChanged", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/lifecycle/Lifecycle$Event;)V", "lifecycle-runtime-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1 implements LifecycleEventObserver {
    final /* synthetic */ a $block;
    final /* synthetic */ C0873j $co;
    final /* synthetic */ Lifecycle.State $state;
    final /* synthetic */ Lifecycle $this_suspendWithStateAtLeastUnchecked;

    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Object obj;
        j.e(lifecycleOwner, "source");
        j.e(event, "event");
        if (event == Lifecycle.Event.Companion.upTo(this.$state)) {
            this.$this_suspendWithStateAtLeastUnchecked.removeObserver(this);
            C0873j jVar = this.$co;
            try {
                obj = this.$block.invoke();
            } catch (Throwable th) {
                obj = L2.a.l(th);
            }
            jVar.resumeWith(obj);
        } else if (event == Lifecycle.Event.ON_DESTROY) {
            this.$this_suspendWithStateAtLeastUnchecked.removeObserver(this);
            this.$co.resumeWith(L2.a.l(new LifecycleDestroyedException()));
        }
    }
}
