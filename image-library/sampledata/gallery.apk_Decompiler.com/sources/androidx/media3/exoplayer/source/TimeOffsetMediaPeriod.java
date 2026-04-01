package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TimeOffsetMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    private final MediaPeriod mediaPeriod;
    private final long timeOffsetUs;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TimeOffsetSampleStream implements SampleStream {
        private final SampleStream sampleStream;
        private final long timeOffsetUs;

        public TimeOffsetSampleStream(SampleStream sampleStream2, long j2) {
            this.sampleStream = sampleStream2;
            this.timeOffsetUs = j2;
        }

        public SampleStream getChildStream() {
            return this.sampleStream;
        }

        public void maybeThrowError() {
            this.sampleStream.maybeThrowError();
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i2) {
            int readData = this.sampleStream.readData(formatHolder, decoderInputBuffer, i2);
            if (readData == -4) {
                decoderInputBuffer.timeUs += this.timeOffsetUs;
            }
            return readData;
        }
    }

    public TimeOffsetMediaPeriod(MediaPeriod mediaPeriod2, long j2) {
        this.mediaPeriod = mediaPeriod2;
        this.timeOffsetUs = j2;
    }

    public boolean continueLoading(LoadingInfo loadingInfo) {
        return this.mediaPeriod.continueLoading(loadingInfo.buildUpon().setPlaybackPositionUs(loadingInfo.playbackPositionUs - this.timeOffsetUs).build());
    }

    public void discardBuffer(long j2, boolean z) {
        this.mediaPeriod.discardBuffer(j2 - this.timeOffsetUs, z);
    }

    public long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters) {
        return this.mediaPeriod.getAdjustedSeekPositionUs(j2 - this.timeOffsetUs, seekParameters) + this.timeOffsetUs;
    }

    public long getBufferedPositionUs() {
        long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
        if (bufferedPositionUs == Long.MIN_VALUE) {
            return Long.MIN_VALUE;
        }
        return bufferedPositionUs + this.timeOffsetUs;
    }

    public long getNextLoadPositionUs() {
        long nextLoadPositionUs = this.mediaPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs == Long.MIN_VALUE) {
            return Long.MIN_VALUE;
        }
        return nextLoadPositionUs + this.timeOffsetUs;
    }

    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    public MediaPeriod getWrappedMediaPeriod() {
        return this.mediaPeriod;
    }

    public boolean isLoading() {
        return this.mediaPeriod.isLoading();
    }

    public void maybeThrowPrepareError() {
        this.mediaPeriod.maybeThrowPrepareError();
    }

    public void onPrepared(MediaPeriod mediaPeriod2) {
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
    }

    public void prepare(MediaPeriod.Callback callback2, long j2) {
        this.callback = callback2;
        this.mediaPeriod.prepare(this, j2 - this.timeOffsetUs);
    }

    public long readDiscontinuity() {
        long readDiscontinuity = this.mediaPeriod.readDiscontinuity();
        if (readDiscontinuity == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return readDiscontinuity + this.timeOffsetUs;
    }

    public void reevaluateBuffer(long j2) {
        this.mediaPeriod.reevaluateBuffer(j2 - this.timeOffsetUs);
    }

    public long seekToUs(long j2) {
        return this.mediaPeriod.seekToUs(j2 - this.timeOffsetUs) + this.timeOffsetUs;
    }

    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j2) {
        SampleStream[] sampleStreamArr2 = new SampleStream[sampleStreamArr.length];
        int i2 = 0;
        while (true) {
            SampleStream sampleStream = null;
            if (i2 >= sampleStreamArr.length) {
                break;
            }
            TimeOffsetSampleStream timeOffsetSampleStream = sampleStreamArr[i2];
            if (timeOffsetSampleStream != null) {
                sampleStream = timeOffsetSampleStream.getChildStream();
            }
            sampleStreamArr2[i2] = sampleStream;
            i2++;
        }
        long selectTracks = this.mediaPeriod.selectTracks(exoTrackSelectionArr, zArr, sampleStreamArr2, zArr2, j2 - this.timeOffsetUs);
        for (int i7 = 0; i7 < sampleStreamArr.length; i7++) {
            SampleStream sampleStream2 = sampleStreamArr2[i7];
            if (sampleStream2 == null) {
                sampleStreamArr[i7] = null;
            } else {
                TimeOffsetSampleStream timeOffsetSampleStream2 = sampleStreamArr[i7];
                if (timeOffsetSampleStream2 == null || timeOffsetSampleStream2.getChildStream() != sampleStream2) {
                    sampleStreamArr[i7] = new TimeOffsetSampleStream(sampleStream2, this.timeOffsetUs);
                }
            }
        }
        return selectTracks + this.timeOffsetUs;
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod2) {
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
    }
}
