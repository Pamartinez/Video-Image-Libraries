package androidx.work.impl.constraints.trackers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"androidx/work/impl/constraints/trackers/BroadcastReceiverConstraintTracker$broadcastReceiver$1", "Landroid/content/BroadcastReceiver;", "Landroid/content/Context;", "context", "Landroid/content/Intent;", "intent", "Lme/x;", "onReceive", "(Landroid/content/Context;Landroid/content/Intent;)V", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BroadcastReceiverConstraintTracker$broadcastReceiver$1 extends BroadcastReceiver {
    final /* synthetic */ BroadcastReceiverConstraintTracker<T> this$0;

    public BroadcastReceiverConstraintTracker$broadcastReceiver$1(BroadcastReceiverConstraintTracker<T> broadcastReceiverConstraintTracker) {
        this.this$0 = broadcastReceiverConstraintTracker;
    }

    public void onReceive(Context context, Intent intent) {
        j.e(context, "context");
        j.e(intent, "intent");
        this.this$0.onBroadcastReceive(intent);
    }
}
