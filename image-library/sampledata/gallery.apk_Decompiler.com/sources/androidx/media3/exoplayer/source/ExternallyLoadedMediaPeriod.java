package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.ExternalLoader;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ExternallyLoadedMediaPeriod implements MediaPeriod {
    /* access modifiers changed from: private */
    public final AtomicBoolean loadingFinished = new AtomicBoolean();
    private ListenableFuture loadingFuture;
    /* access modifiers changed from: private */
    public final AtomicReference<Throwable> loadingThrowable = new AtomicReference<>();
    /* access modifiers changed from: private */
    public final byte[] sampleData;
    private final ArrayList<SampleStreamImpl> sampleStreams = new ArrayList<>();
    /* access modifiers changed from: private */
    public final TrackGroupArray tracks;
    private final Uri uri;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class SampleStreamImpl implements SampleStream {
        private int streamState = 0;

        public SampleStreamImpl() {
        }

        public void maybeThrowError() {
            Throwable th = (Throwable) ExternallyLoadedMediaPeriod.this.loadingThrowable.get();
            if (th != null) {
                throw new IOException(th);
            }
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i2) {
            int i7 = this.streamState;
            if (i7 == 2) {
                decoderInputBuffer.addFlag(4);
                return -4;
            } else if ((i2 & 2) != 0 || i7 == 0) {
                formatHolder.format = ExternallyLoadedMediaPeriod.this.tracks.get(0).getFormat(0);
                this.streamState = 1;
                return -5;
            } else if (!ExternallyLoadedMediaPeriod.this.loadingFinished.get()) {
                return -3;
            } else {
                int length = ExternallyLoadedMediaPeriod.this.sampleData.length;
                decoderInputBuffer.addFlag(1);
                decoderInputBuffer.timeUs = 0;
                if ((i2 & 4) == 0) {
                    decoderInputBuffer.ensureSpaceForWrite(length);
                    decoderInputBuffer.data.put(ExternallyLoadedMediaPeriod.this.sampleData, 0, length);
                }
                if ((i2 & 1) == 0) {
                    this.streamState = 2;
                }
                return -4;
            }
        }

        public void reset() {
            if (this.streamState == 2) {
                this.streamState = 1;
            }
        }
    }

    public ExternallyLoadedMediaPeriod(Uri uri2, String str, ExternalLoader externalLoader) {
        this.uri = uri2;
        this.tracks = new TrackGroupArray(new TrackGroup(new Format.Builder().setSampleMimeType(str).build()));
        this.sampleData = uri2.toString().getBytes(StandardCharsets.UTF_8);
    }

    public boolean continueLoading(LoadingInfo loadingInfo) {
        return !this.loadingFinished.get();
    }

    public long getBufferedPositionUs() {
        if (this.loadingFinished.get()) {
            return Long.MIN_VALUE;
        }
        return 0;
    }

    public long getNextLoadPositionUs() {
        if (this.loadingFinished.get()) {
            return Long.MIN_VALUE;
        }
        return 0;
    }

    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    public boolean isLoading() {
        return !this.loadingFinished.get();
    }

    public void prepare(MediaPeriod.Callback callback, long j2) {
        callback.onPrepared(this);
        new ExternalLoader.LoadRequest(this.uri);
        throw null;
    }

    public long readDiscontinuity() {
        return -9223372036854775807L;
    }

    public void releasePeriod() {
        ListenableFuture listenableFuture = this.loadingFuture;
        if (listenableFuture != null) {
            listenableFuture.cancel(false);
        }
    }

    public long seekToUs(long j2) {
        for (int i2 = 0; i2 < this.sampleStreams.size(); i2++) {
            this.sampleStreams.get(i2).reset();
        }
        return j2;
    }

    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j2) {
        for (int i2 = 0; i2 < exoTrackSelectionArr.length; i2++) {
            SampleStream sampleStream = sampleStreamArr[i2];
            if (sampleStream != null && (exoTrackSelectionArr[i2] == null || !zArr[i2])) {
                this.sampleStreams.remove(sampleStream);
                sampleStreamArr[i2] = null;
            }
            if (sampleStreamArr[i2] == null && exoTrackSelectionArr[i2] != null) {
                SampleStreamImpl sampleStreamImpl = new SampleStreamImpl();
                this.sampleStreams.add(sampleStreamImpl);
                sampleStreamArr[i2] = sampleStreamImpl;
                zArr2[i2] = true;
            }
        }
        return j2;
    }

    public void maybeThrowPrepareError() {
    }

    public void reevaluateBuffer(long j2) {
    }

    public void discardBuffer(long j2, boolean z) {
    }

    public long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters) {
        return j2;
    }
}
