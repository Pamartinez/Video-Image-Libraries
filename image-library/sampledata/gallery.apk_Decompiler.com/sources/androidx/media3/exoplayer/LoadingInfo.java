package androidx.media3.exoplayer;

import androidx.media3.common.util.Assertions;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LoadingInfo {
    public final long lastRebufferRealtimeMs;
    public final long playbackPositionUs;
    public final float playbackSpeed;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public long lastRebufferRealtimeMs;
        /* access modifiers changed from: private */
        public long playbackPositionUs;
        /* access modifiers changed from: private */
        public float playbackSpeed;

        public LoadingInfo build() {
            return new LoadingInfo(this);
        }

        public Builder setLastRebufferRealtimeMs(long j2) {
            boolean z;
            if (j2 >= 0 || j2 == -9223372036854775807L) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            this.lastRebufferRealtimeMs = j2;
            return this;
        }

        public Builder setPlaybackPositionUs(long j2) {
            this.playbackPositionUs = j2;
            return this;
        }

        public Builder setPlaybackSpeed(float f) {
            boolean z;
            if (f > 0.0f || f == -3.4028235E38f) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            this.playbackSpeed = f;
            return this;
        }

        public Builder() {
            this.playbackPositionUs = -9223372036854775807L;
            this.playbackSpeed = -3.4028235E38f;
            this.lastRebufferRealtimeMs = -9223372036854775807L;
        }

        private Builder(LoadingInfo loadingInfo) {
            this.playbackPositionUs = loadingInfo.playbackPositionUs;
            this.playbackSpeed = loadingInfo.playbackSpeed;
            this.lastRebufferRealtimeMs = loadingInfo.lastRebufferRealtimeMs;
        }
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LoadingInfo)) {
            return false;
        }
        LoadingInfo loadingInfo = (LoadingInfo) obj;
        if (this.playbackPositionUs == loadingInfo.playbackPositionUs && this.playbackSpeed == loadingInfo.playbackSpeed && this.lastRebufferRealtimeMs == loadingInfo.lastRebufferRealtimeMs) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Long.valueOf(this.playbackPositionUs), Float.valueOf(this.playbackSpeed), Long.valueOf(this.lastRebufferRealtimeMs)});
    }

    private LoadingInfo(Builder builder) {
        this.playbackPositionUs = builder.playbackPositionUs;
        this.playbackSpeed = builder.playbackSpeed;
        this.lastRebufferRealtimeMs = builder.lastRebufferRealtimeMs;
    }
}
