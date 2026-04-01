package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\u0006J\u0017\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u0006J\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000b\u0010\u0006ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/lifecycle/DefaultLifecycleObserver;", "Landroidx/lifecycle/LifecycleObserver;", "Landroidx/lifecycle/LifecycleOwner;", "owner", "Lme/x;", "onCreate", "(Landroidx/lifecycle/LifecycleOwner;)V", "onStart", "onResume", "onPause", "onStop", "onDestroy", "lifecycle-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface DefaultLifecycleObserver extends LifecycleObserver {
    void onCreate(LifecycleOwner lifecycleOwner) {
        j.e(lifecycleOwner, "owner");
    }

    void onDestroy(LifecycleOwner lifecycleOwner) {
        j.e(lifecycleOwner, "owner");
    }

    void onPause(LifecycleOwner lifecycleOwner) {
        j.e(lifecycleOwner, "owner");
    }

    void onResume(LifecycleOwner lifecycleOwner) {
        j.e(lifecycleOwner, "owner");
    }

    void onStart(LifecycleOwner lifecycleOwner) {
        j.e(lifecycleOwner, "owner");
    }

    void onStop(LifecycleOwner lifecycleOwner) {
        j.e(lifecycleOwner, "owner");
    }
}
