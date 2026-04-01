package androidx.media3.exoplayer;

import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.LoadControl;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DefaultLoadControl implements LoadControl {
    private final DefaultAllocator allocator;
    private final long backBufferDurationUs;
    private final long bufferForPlaybackAfterRebufferUs;
    private final long bufferForPlaybackUs;
    private final HashMap<PlayerId, PlayerLoadingState> loadingStates;
    private final long maxBufferUs;
    private final long minBufferUs;
    private final boolean prioritizeTimeOverSizeThresholds;
    private final boolean retainBackBufferFromKeyframe;
    private final int targetBufferBytesOverwrite;
    private long threadId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private DefaultAllocator allocator;
        private int backBufferDurationMs = 0;
        private int bufferForPlaybackAfterRebufferMs = 2000;
        private int bufferForPlaybackMs = 1000;
        private boolean buildCalled;
        private int maxBufferMs = 50000;
        private int minBufferMs = 50000;
        private boolean prioritizeTimeOverSizeThresholds = false;
        private boolean retainBackBufferFromKeyframe = false;
        private int targetBufferBytes = -1;

        public DefaultLoadControl build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            if (this.allocator == null) {
                this.allocator = new DefaultAllocator(true, 65536);
            }
            return new DefaultLoadControl(this.allocator, this.minBufferMs, this.maxBufferMs, this.bufferForPlaybackMs, this.bufferForPlaybackAfterRebufferMs, this.targetBufferBytes, this.prioritizeTimeOverSizeThresholds, this.backBufferDurationMs, this.retainBackBufferFromKeyframe);
        }

        public Builder setBufferDurationsMs(int i2, int i7, int i8, int i10) {
            Assertions.checkState(!this.buildCalled);
            DefaultLoadControl.assertGreaterOrEqual(i8, 0, "bufferForPlaybackMs", "0");
            DefaultLoadControl.assertGreaterOrEqual(i10, 0, "bufferForPlaybackAfterRebufferMs", "0");
            DefaultLoadControl.assertGreaterOrEqual(i2, i8, "minBufferMs", "bufferForPlaybackMs");
            DefaultLoadControl.assertGreaterOrEqual(i2, i10, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
            DefaultLoadControl.assertGreaterOrEqual(i7, i2, "maxBufferMs", "minBufferMs");
            this.minBufferMs = i2;
            this.maxBufferMs = i7;
            this.bufferForPlaybackMs = i8;
            this.bufferForPlaybackAfterRebufferMs = i10;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PlayerLoadingState {
        public boolean isLoading;
        public int targetBufferBytes;

        private PlayerLoadingState() {
        }
    }

    public DefaultLoadControl() {
        this(new DefaultAllocator(true, 65536), 50000, 50000, 1000, 2000, -1, false, 0, false);
    }

    /* access modifiers changed from: private */
    public static void assertGreaterOrEqual(int i2, int i7, String str, String str2) {
        boolean z;
        if (i2 >= i7) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z, str + " cannot be less than " + str2);
    }

    private static int getDefaultBufferSize(int i2) {
        switch (i2) {
            case -2:
                return 0;
            case -1:
                return 13107200;
            case 0:
                return 144310272;
            case 1:
                return 13107200;
            case 2:
                return 131072000;
            case 3:
                return 131072;
            case 4:
                return 26214400;
            case 5:
            case 6:
                return 131072;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void removePlayer(PlayerId playerId) {
        if (this.loadingStates.remove(playerId) != null) {
            updateAllocator();
        }
    }

    private void resetPlayerLoadingState(PlayerId playerId) {
        PlayerLoadingState playerLoadingState = (PlayerLoadingState) Assertions.checkNotNull(this.loadingStates.get(playerId));
        int i2 = this.targetBufferBytesOverwrite;
        if (i2 == -1) {
            i2 = 13107200;
        }
        playerLoadingState.targetBufferBytes = i2;
        playerLoadingState.isLoading = false;
    }

    private void updateAllocator() {
        if (this.loadingStates.isEmpty()) {
            this.allocator.reset();
        } else {
            this.allocator.setTargetBufferSize(calculateTotalTargetBufferBytes());
        }
    }

    public int calculateTargetBufferBytes(ExoTrackSelection[] exoTrackSelectionArr) {
        int i2 = 0;
        for (ExoTrackSelection exoTrackSelection : exoTrackSelectionArr) {
            if (exoTrackSelection != null) {
                i2 += getDefaultBufferSize(exoTrackSelection.getTrackGroup().type);
            }
        }
        return Math.max(13107200, i2);
    }

    public int calculateTotalTargetBufferBytes() {
        int i2 = 0;
        for (PlayerLoadingState playerLoadingState : this.loadingStates.values()) {
            i2 += playerLoadingState.targetBufferBytes;
        }
        return i2;
    }

    public Allocator getAllocator() {
        return this.allocator;
    }

    public long getBackBufferDurationUs(PlayerId playerId) {
        return this.backBufferDurationUs;
    }

    public void onPrepared(PlayerId playerId) {
        boolean z;
        long id = Thread.currentThread().getId();
        long j2 = this.threadId;
        if (j2 == -1 || j2 == id) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z, "Players that share the same LoadControl must share the same playback thread. See ExoPlayer.Builder.setPlaybackLooper(Looper).");
        this.threadId = id;
        if (!this.loadingStates.containsKey(playerId)) {
            this.loadingStates.put(playerId, new PlayerLoadingState());
        }
        resetPlayerLoadingState(playerId);
    }

    public void onReleased(PlayerId playerId) {
        removePlayer(playerId);
        if (this.loadingStates.isEmpty()) {
            this.threadId = -1;
        }
    }

    public void onStopped(PlayerId playerId) {
        removePlayer(playerId);
    }

    public void onTracksSelected(LoadControl.Parameters parameters, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr) {
        PlayerLoadingState playerLoadingState = (PlayerLoadingState) Assertions.checkNotNull(this.loadingStates.get(parameters.playerId));
        int i2 = this.targetBufferBytesOverwrite;
        if (i2 == -1) {
            i2 = calculateTargetBufferBytes(exoTrackSelectionArr);
        }
        playerLoadingState.targetBufferBytes = i2;
        updateAllocator();
    }

    public boolean retainBackBufferFromKeyframe(PlayerId playerId) {
        return this.retainBackBufferFromKeyframe;
    }

    public boolean shouldContinueLoading(LoadControl.Parameters parameters) {
        boolean z;
        PlayerLoadingState playerLoadingState = (PlayerLoadingState) Assertions.checkNotNull(this.loadingStates.get(parameters.playerId));
        boolean z3 = true;
        if (this.allocator.getTotalBytesAllocated() >= calculateTotalTargetBufferBytes()) {
            z = true;
        } else {
            z = false;
        }
        long j2 = this.minBufferUs;
        float f = parameters.playbackSpeed;
        if (f > 1.0f) {
            j2 = Math.min(Util.getMediaDurationForPlayoutDuration(j2, f), this.maxBufferUs);
        }
        long max = Math.max(j2, 500000);
        long j3 = parameters.bufferedDurationUs;
        if (j3 < max) {
            if (!this.prioritizeTimeOverSizeThresholds && z) {
                z3 = false;
            }
            playerLoadingState.isLoading = z3;
            if (!z3 && j3 < 500000) {
                Log.w("DefaultLoadControl", "Target buffer size reached with less than 500ms of buffered media data.");
            }
        } else if (j3 >= this.maxBufferUs || z) {
            playerLoadingState.isLoading = false;
        }
        return playerLoadingState.isLoading;
    }

    public boolean shouldContinuePreloading(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j2) {
        for (PlayerLoadingState playerLoadingState : this.loadingStates.values()) {
            if (playerLoadingState.isLoading) {
                return false;
            }
        }
        return true;
    }

    public boolean shouldStartPlayback(LoadControl.Parameters parameters) {
        long j2;
        long playoutDurationForMediaDuration = Util.getPlayoutDurationForMediaDuration(parameters.bufferedDurationUs, parameters.playbackSpeed);
        if (parameters.rebuffering) {
            j2 = this.bufferForPlaybackAfterRebufferUs;
        } else {
            j2 = this.bufferForPlaybackUs;
        }
        long j3 = parameters.targetLiveOffsetUs;
        if (j3 != -9223372036854775807L) {
            j2 = Math.min(j3 / 2, j2);
        }
        if (j2 <= 0 || playoutDurationForMediaDuration >= j2) {
            return true;
        }
        if (this.prioritizeTimeOverSizeThresholds || this.allocator.getTotalBytesAllocated() < calculateTotalTargetBufferBytes()) {
            return false;
        }
        return true;
    }

    public DefaultLoadControl(DefaultAllocator defaultAllocator, int i2, int i7, int i8, int i10, int i11, boolean z, int i12, boolean z3) {
        assertGreaterOrEqual(i8, 0, "bufferForPlaybackMs", "0");
        assertGreaterOrEqual(i10, 0, "bufferForPlaybackAfterRebufferMs", "0");
        assertGreaterOrEqual(i2, i8, "minBufferMs", "bufferForPlaybackMs");
        assertGreaterOrEqual(i2, i10, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
        assertGreaterOrEqual(i7, i2, "maxBufferMs", "minBufferMs");
        assertGreaterOrEqual(i12, 0, "backBufferDurationMs", "0");
        this.allocator = defaultAllocator;
        this.minBufferUs = Util.msToUs((long) i2);
        this.maxBufferUs = Util.msToUs((long) i7);
        this.bufferForPlaybackUs = Util.msToUs((long) i8);
        this.bufferForPlaybackAfterRebufferUs = Util.msToUs((long) i10);
        this.targetBufferBytesOverwrite = i11;
        this.prioritizeTimeOverSizeThresholds = z;
        this.backBufferDurationUs = Util.msToUs((long) i12);
        this.retainBackBufferFromKeyframe = z3;
        this.loadingStates = new HashMap<>();
        this.threadId = -1;
    }
}
