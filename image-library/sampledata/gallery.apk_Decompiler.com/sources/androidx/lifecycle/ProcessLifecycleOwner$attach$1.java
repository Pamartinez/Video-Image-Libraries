package androidx.lifecycle;

import android.app.Activity;
import android.os.Bundle;
import androidx.lifecycle.ProcessLifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J!\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0017¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\t\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\t\u0010\bJ\u0017\u0010\n\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"androidx/lifecycle/ProcessLifecycleOwner$attach$1", "Landroidx/lifecycle/EmptyActivityLifecycleCallbacks;", "Landroid/app/Activity;", "activity", "Landroid/os/Bundle;", "savedInstanceState", "Lme/x;", "onActivityPreCreated", "(Landroid/app/Activity;Landroid/os/Bundle;)V", "onActivityCreated", "onActivityPaused", "(Landroid/app/Activity;)V", "onActivityStopped", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ProcessLifecycleOwner$attach$1 extends EmptyActivityLifecycleCallbacks {
    final /* synthetic */ ProcessLifecycleOwner this$0;

    public ProcessLifecycleOwner$attach$1(ProcessLifecycleOwner processLifecycleOwner) {
        this.this$0 = processLifecycleOwner;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        j.e(activity, "activity");
    }

    public void onActivityPaused(Activity activity) {
        j.e(activity, "activity");
        this.this$0.activityPaused$lifecycle_process_release();
    }

    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        j.e(activity, "activity");
        ProcessLifecycleOwner.Api29Impl.registerActivityLifecycleCallbacks(activity, new ProcessLifecycleOwner$attach$1$onActivityPreCreated$1(this.this$0));
    }

    public void onActivityStopped(Activity activity) {
        j.e(activity, "activity");
        this.this$0.activityStopped$lifecycle_process_release();
    }
}
