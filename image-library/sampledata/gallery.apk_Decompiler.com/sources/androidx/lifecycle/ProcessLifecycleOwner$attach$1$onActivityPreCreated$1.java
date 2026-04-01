package androidx.lifecycle;

import android.app.Activity;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\u0006¨\u0006\b"}, d2 = {"androidx/lifecycle/ProcessLifecycleOwner$attach$1$onActivityPreCreated$1", "Landroidx/lifecycle/EmptyActivityLifecycleCallbacks;", "Landroid/app/Activity;", "activity", "Lme/x;", "onActivityPostStarted", "(Landroid/app/Activity;)V", "onActivityPostResumed", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ProcessLifecycleOwner$attach$1$onActivityPreCreated$1 extends EmptyActivityLifecycleCallbacks {
    final /* synthetic */ ProcessLifecycleOwner this$0;

    public ProcessLifecycleOwner$attach$1$onActivityPreCreated$1(ProcessLifecycleOwner processLifecycleOwner) {
        this.this$0 = processLifecycleOwner;
    }

    public void onActivityPostResumed(Activity activity) {
        j.e(activity, "activity");
        this.this$0.activityResumed$lifecycle_process_release();
    }

    public void onActivityPostStarted(Activity activity) {
        j.e(activity, "activity");
        this.this$0.activityStarted$lifecycle_process_release();
    }
}
