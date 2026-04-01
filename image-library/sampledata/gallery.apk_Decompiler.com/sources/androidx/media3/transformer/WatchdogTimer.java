package androidx.media3.transformer;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import bc.C0584a;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class WatchdogTimer {
    private final Listener listener;
    private final long timeoutDurationMs;
    private ScheduledFuture<?> timeoutScheduledFuture;
    private final ScheduledExecutorService watchdogScheduledExecutorService = Util.newSingleThreadScheduledExecutor("WatchdogTimer");

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
    }

    public WatchdogTimer(long j2, Listener listener2) {
        this.timeoutDurationMs = j2;
        this.listener = listener2;
    }

    private void cancelExistingTimer() {
        ((ScheduledFuture) Assertions.checkNotNull(this.timeoutScheduledFuture)).cancel(false);
    }

    private void scheduleNewTimer() {
        ScheduledExecutorService scheduledExecutorService = this.watchdogScheduledExecutorService;
        Listener listener2 = this.listener;
        Objects.requireNonNull(listener2);
        this.timeoutScheduledFuture = scheduledExecutorService.schedule(new C0584a(5, listener2), this.timeoutDurationMs, TimeUnit.MILLISECONDS);
    }

    public void reset() {
        cancelExistingTimer();
        scheduleNewTimer();
    }

    public void start() {
        scheduleNewTimer();
    }

    public void stop() {
        cancelExistingTimer();
        this.watchdogScheduledExecutorService.shutdownNow();
    }
}
