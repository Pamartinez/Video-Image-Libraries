package androidx.databinding;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.viewbinding.ViewBinding;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewDataBinding extends BaseObservable implements ViewBinding {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OnStartListener implements LifecycleObserver {
        final WeakReference<ViewDataBinding> mBinding;

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            if (this.mBinding.get() != null) {
                throw new ClassCastException();
            }
        }
    }
}
