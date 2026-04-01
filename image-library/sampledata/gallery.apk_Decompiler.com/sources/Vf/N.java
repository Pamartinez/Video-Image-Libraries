package Vf;

import java.util.concurrent.ScheduledFuture;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class N implements O {
    public final ScheduledFuture d;

    public N(ScheduledFuture scheduledFuture) {
        this.d = scheduledFuture;
    }

    public final void a() {
        this.d.cancel(false);
    }

    public final String toString() {
        return "DisposableFutureHandle[" + this.d + ']';
    }
}
