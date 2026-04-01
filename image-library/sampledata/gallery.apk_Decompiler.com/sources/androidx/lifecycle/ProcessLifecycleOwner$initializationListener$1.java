package androidx.lifecycle;

import androidx.lifecycle.ReportFragment;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0004¨\u0006\u0007"}, d2 = {"androidx/lifecycle/ProcessLifecycleOwner$initializationListener$1", "Landroidx/lifecycle/ReportFragment$ActivityInitializationListener;", "Lme/x;", "onCreate", "()V", "onStart", "onResume", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ProcessLifecycleOwner$initializationListener$1 implements ReportFragment.ActivityInitializationListener {
    final /* synthetic */ ProcessLifecycleOwner this$0;

    public ProcessLifecycleOwner$initializationListener$1(ProcessLifecycleOwner processLifecycleOwner) {
        this.this$0 = processLifecycleOwner;
    }

    public void onResume() {
        this.this$0.activityResumed$lifecycle_process_release();
    }

    public void onStart() {
        this.this$0.activityStarted$lifecycle_process_release();
    }

    public void onCreate() {
    }
}
