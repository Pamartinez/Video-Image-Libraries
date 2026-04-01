package androidx.media3.exoplayer.source;

import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.SequenceableLoader;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaPeriod extends SequenceableLoader {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Callback extends SequenceableLoader.Callback<MediaPeriod> {
        void onPrepared(MediaPeriod mediaPeriod);
    }

    boolean continueLoading(LoadingInfo loadingInfo);

    void discardBuffer(long j2, boolean z);

    long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters);

    long getBufferedPositionUs();

    long getNextLoadPositionUs();

    TrackGroupArray getTrackGroups();

    boolean isLoading();

    void maybeThrowPrepareError();

    void prepare(Callback callback, long j2);

    long readDiscontinuity();

    void reevaluateBuffer(long j2);

    long seekToUs(long j2);

    long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j2);
}
