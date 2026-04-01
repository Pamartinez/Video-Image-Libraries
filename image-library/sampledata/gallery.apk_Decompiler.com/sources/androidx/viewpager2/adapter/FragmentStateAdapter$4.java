package androidx.viewpager2.adapter;

import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FragmentStateAdapter$4 implements LifecycleEventObserver {
    final /* synthetic */ Handler val$handler;
    final /* synthetic */ Runnable val$runnable;

    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.val$handler.removeCallbacks(this.val$runnable);
            lifecycleOwner.getLifecycle().removeObserver(this);
        }
    }
}
