package androidx.media3.common.util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConditionVariable {
    private final Clock clock;
    private boolean isOpen;

    public ConditionVariable() {
        this(Clock.DEFAULT);
    }

    public synchronized void block() {
        while (!this.isOpen) {
            this.clock.onThreadBlocked();
            wait();
        }
    }

    public synchronized void blockUninterruptible() {
        boolean z = false;
        while (!this.isOpen) {
            try {
                this.clock.onThreadBlocked();
                wait();
            } catch (InterruptedException unused) {
                z = true;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized boolean close() {
        boolean z;
        z = this.isOpen;
        this.isOpen = false;
        return z;
    }

    public synchronized boolean isOpen() {
        return this.isOpen;
    }

    public synchronized boolean open() {
        if (this.isOpen) {
            return false;
        }
        this.isOpen = true;
        notifyAll();
        return true;
    }

    public ConditionVariable(Clock clock2) {
        this.clock = clock2;
    }

    public synchronized boolean blockUninterruptible(long j2) {
        if (j2 <= 0) {
            return this.isOpen;
        }
        long elapsedRealtime = this.clock.elapsedRealtime();
        long j3 = j2 + elapsedRealtime;
        if (j3 < elapsedRealtime) {
            blockUninterruptible();
        } else {
            boolean z = false;
            while (!this.isOpen && elapsedRealtime < j3) {
                try {
                    this.clock.onThreadBlocked();
                    wait(j3 - elapsedRealtime);
                } catch (InterruptedException unused) {
                    z = true;
                }
                elapsedRealtime = this.clock.elapsedRealtime();
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
        return this.isOpen;
    }
}
