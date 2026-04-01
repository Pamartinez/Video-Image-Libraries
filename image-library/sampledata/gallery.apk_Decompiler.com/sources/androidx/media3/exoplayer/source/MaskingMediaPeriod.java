package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MaskingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private final Allocator allocator;
    private MediaPeriod.Callback callback;
    public final MediaSource.MediaPeriodId id;
    private PrepareListener listener;
    private MediaPeriod mediaPeriod;
    private MediaSource mediaSource;
    private boolean notifiedPrepareError;
    private long preparePositionOverrideUs = -9223372036854775807L;
    private final long preparePositionUs;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface PrepareListener {
        void onPrepareComplete(MediaSource.MediaPeriodId mediaPeriodId);

        void onPrepareError(MediaSource.MediaPeriodId mediaPeriodId, IOException iOException);
    }

    public MaskingMediaPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator2, long j2) {
        this.id = mediaPeriodId;
        this.allocator = allocator2;
        this.preparePositionUs = j2;
    }

    private long getPreparePositionWithOverride(long j2) {
        long j3 = this.preparePositionOverrideUs;
        if (j3 != -9223372036854775807L) {
            return j3;
        }
        return j2;
    }

    public boolean continueLoading(LoadingInfo loadingInfo) {
        MediaPeriod mediaPeriod2 = this.mediaPeriod;
        if (mediaPeriod2 == null || !mediaPeriod2.continueLoading(loadingInfo)) {
            return false;
        }
        return true;
    }

    public void createPeriod(MediaSource.MediaPeriodId mediaPeriodId) {
        long preparePositionWithOverride = getPreparePositionWithOverride(this.preparePositionUs);
        MediaPeriod createPeriod = ((MediaSource) Assertions.checkNotNull(this.mediaSource)).createPeriod(mediaPeriodId, this.allocator, preparePositionWithOverride);
        this.mediaPeriod = createPeriod;
        if (this.callback != null) {
            createPeriod.prepare(this, preparePositionWithOverride);
        }
    }

    public void discardBuffer(long j2, boolean z) {
        ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).discardBuffer(j2, z);
    }

    public long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters) {
        return ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).getAdjustedSeekPositionUs(j2, seekParameters);
    }

    public long getBufferedPositionUs() {
        return ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).getBufferedPositionUs();
    }

    public long getNextLoadPositionUs() {
        return ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).getNextLoadPositionUs();
    }

    public long getPreparePositionOverrideUs() {
        return this.preparePositionOverrideUs;
    }

    public long getPreparePositionUs() {
        return this.preparePositionUs;
    }

    public TrackGroupArray getTrackGroups() {
        return ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).getTrackGroups();
    }

    public boolean isLoading() {
        MediaPeriod mediaPeriod2 = this.mediaPeriod;
        if (mediaPeriod2 == null || !mediaPeriod2.isLoading()) {
            return false;
        }
        return true;
    }

    public void maybeThrowPrepareError() {
        try {
            MediaPeriod mediaPeriod2 = this.mediaPeriod;
            if (mediaPeriod2 != null) {
                mediaPeriod2.maybeThrowPrepareError();
                return;
            }
            MediaSource mediaSource2 = this.mediaSource;
            if (mediaSource2 != null) {
                mediaSource2.maybeThrowSourceInfoRefreshError();
            }
        } catch (IOException e) {
            PrepareListener prepareListener = this.listener;
            if (prepareListener == null) {
                throw e;
            } else if (!this.notifiedPrepareError) {
                this.notifiedPrepareError = true;
                prepareListener.onPrepareError(this.id, e);
            }
        }
    }

    public void onPrepared(MediaPeriod mediaPeriod2) {
        ((MediaPeriod.Callback) Util.castNonNull(this.callback)).onPrepared(this);
        PrepareListener prepareListener = this.listener;
        if (prepareListener != null) {
            prepareListener.onPrepareComplete(this.id);
        }
    }

    public void overridePreparePositionUs(long j2) {
        this.preparePositionOverrideUs = j2;
    }

    public void prepare(MediaPeriod.Callback callback2, long j2) {
        this.callback = callback2;
        MediaPeriod mediaPeriod2 = this.mediaPeriod;
        if (mediaPeriod2 != null) {
            mediaPeriod2.prepare(this, getPreparePositionWithOverride(this.preparePositionUs));
        }
    }

    public long readDiscontinuity() {
        return ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).readDiscontinuity();
    }

    public void reevaluateBuffer(long j2) {
        ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).reevaluateBuffer(j2);
    }

    public void releasePeriod() {
        if (this.mediaPeriod != null) {
            ((MediaSource) Assertions.checkNotNull(this.mediaSource)).releasePeriod(this.mediaPeriod);
        }
    }

    public long seekToUs(long j2) {
        return ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).seekToUs(j2);
    }

    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j2) {
        long j3 = this.preparePositionOverrideUs;
        if (j3 != -9223372036854775807L && j2 == this.preparePositionUs) {
            j2 = j3;
        }
        this.preparePositionOverrideUs = -9223372036854775807L;
        return ((MediaPeriod) Util.castNonNull(this.mediaPeriod)).selectTracks(exoTrackSelectionArr, zArr, sampleStreamArr, zArr2, j2);
    }

    public void setMediaSource(MediaSource mediaSource2) {
        boolean z;
        if (this.mediaSource == null) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        this.mediaSource = mediaSource2;
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod2) {
        ((MediaPeriod.Callback) Util.castNonNull(this.callback)).onContinueLoadingRequested(this);
    }
}
