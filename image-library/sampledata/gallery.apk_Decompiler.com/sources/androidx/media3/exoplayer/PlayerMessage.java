package androidx.media3.exoplayer;

import android.os.Looper;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PlayerMessage {
    private final Clock clock;
    private boolean deleteAfterDelivery = true;
    private boolean isCanceled;
    private boolean isDelivered;
    private boolean isProcessed;
    private boolean isSent;
    private Looper looper;
    private int mediaItemIndex;
    private Object payload;
    private long positionMs = -9223372036854775807L;
    private final Sender sender;
    private final Target target;
    private final Timeline timeline;
    private int type;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Sender {
        void sendMessage(PlayerMessage playerMessage);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Target {
        void handleMessage(int i2, Object obj);
    }

    public PlayerMessage(Sender sender2, Target target2, Timeline timeline2, int i2, Clock clock2, Looper looper2) {
        this.sender = sender2;
        this.target = target2;
        this.timeline = timeline2;
        this.looper = looper2;
        this.clock = clock2;
        this.mediaItemIndex = i2;
    }

    public boolean getDeleteAfterDelivery() {
        return this.deleteAfterDelivery;
    }

    public Looper getLooper() {
        return this.looper;
    }

    public int getMediaItemIndex() {
        return this.mediaItemIndex;
    }

    public Object getPayload() {
        return this.payload;
    }

    public long getPositionMs() {
        return this.positionMs;
    }

    public Target getTarget() {
        return this.target;
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    public int getType() {
        return this.type;
    }

    public synchronized boolean isCanceled() {
        return this.isCanceled;
    }

    public synchronized void markAsProcessed(boolean z) {
        this.isDelivered = z | this.isDelivered;
        this.isProcessed = true;
        notifyAll();
    }

    public PlayerMessage send() {
        Assertions.checkState(!this.isSent);
        if (this.positionMs == -9223372036854775807L) {
            Assertions.checkArgument(this.deleteAfterDelivery);
        }
        this.isSent = true;
        this.sender.sendMessage(this);
        return this;
    }

    public PlayerMessage setPayload(Object obj) {
        Assertions.checkState(!this.isSent);
        this.payload = obj;
        return this;
    }

    public PlayerMessage setType(int i2) {
        Assertions.checkState(!this.isSent);
        this.type = i2;
        return this;
    }
}
