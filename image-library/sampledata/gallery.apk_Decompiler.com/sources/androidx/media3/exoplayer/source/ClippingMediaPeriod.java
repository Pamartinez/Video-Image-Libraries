package androidx.media3.exoplayer.source;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.ClippingMediaSource;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ClippingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    private ClippingMediaSource.IllegalClippingException clippingError;
    long endUs;
    public final MediaPeriod mediaPeriod;
    private long pendingInitialDiscontinuityPositionUs;
    private ClippingSampleStream[] sampleStreams = new ClippingSampleStream[0];
    long startUs;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class ClippingSampleStream implements SampleStream {
        public final SampleStream childStream;
        private boolean sentEos;

        public ClippingSampleStream(SampleStream sampleStream) {
            this.childStream = sampleStream;
        }

        public void clearSentEos() {
            this.sentEos = false;
        }

        public void maybeThrowError() {
            this.childStream.maybeThrowError();
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i2) {
            if (ClippingMediaPeriod.this.isPendingInitialDiscontinuity()) {
                return -3;
            }
            if (this.sentEos) {
                decoderInputBuffer.setFlags(4);
                return -4;
            }
            long bufferedPositionUs = ClippingMediaPeriod.this.getBufferedPositionUs();
            int readData = this.childStream.readData(formatHolder, decoderInputBuffer, i2);
            if (readData == -5) {
                Format format = (Format) Assertions.checkNotNull(formatHolder.format);
                int i7 = format.encoderDelay;
                if (!(i7 == 0 && format.encoderPadding == 0)) {
                    ClippingMediaPeriod clippingMediaPeriod = ClippingMediaPeriod.this;
                    int i8 = 0;
                    if (clippingMediaPeriod.startUs != 0) {
                        i7 = 0;
                    }
                    if (clippingMediaPeriod.endUs == Long.MIN_VALUE) {
                        i8 = format.encoderPadding;
                    }
                    formatHolder.format = format.buildUpon().setEncoderDelay(i7).setEncoderPadding(i8).build();
                }
                return -5;
            }
            long j2 = ClippingMediaPeriod.this.endUs;
            if (j2 == Long.MIN_VALUE || ((readData != -4 || decoderInputBuffer.timeUs < j2) && (readData != -3 || bufferedPositionUs != Long.MIN_VALUE || decoderInputBuffer.waitingForKeys))) {
                return readData;
            }
            decoderInputBuffer.clear();
            decoderInputBuffer.setFlags(4);
            this.sentEos = true;
            return -4;
        }
    }

    public ClippingMediaPeriod(MediaPeriod mediaPeriod2, boolean z, long j2, long j3) {
        long j8;
        this.mediaPeriod = mediaPeriod2;
        if (z) {
            j8 = j2;
        } else {
            j8 = -9223372036854775807L;
        }
        this.pendingInitialDiscontinuityPositionUs = j8;
        this.startUs = j2;
        this.endUs = j3;
    }

    private SeekParameters clipSeekParameters(long j2, SeekParameters seekParameters) {
        long j3;
        long constrainValue = Util.constrainValue(seekParameters.toleranceBeforeUs, 0, j2 - this.startUs);
        long j8 = seekParameters.toleranceAfterUs;
        long j10 = this.endUs;
        if (j10 == Long.MIN_VALUE) {
            j3 = Long.MAX_VALUE;
        } else {
            j3 = j10 - j2;
        }
        long constrainValue2 = Util.constrainValue(j8, 0, j3);
        if (constrainValue == seekParameters.toleranceBeforeUs && constrainValue2 == seekParameters.toleranceAfterUs) {
            return seekParameters;
        }
        return new SeekParameters(constrainValue, constrainValue2);
    }

    private static long enforceClippingRange(long j2, long j3, long j8) {
        long max = Math.max(j2, j3);
        if (j8 != Long.MIN_VALUE) {
            return Math.min(max, j8);
        }
        return max;
    }

    private static boolean shouldKeepInitialDiscontinuity(long j2, long j3, ExoTrackSelection[] exoTrackSelectionArr) {
        if (j2 < j3) {
            return true;
        }
        if (j2 != 0) {
            for (ExoTrackSelection exoTrackSelection : exoTrackSelectionArr) {
                if (exoTrackSelection != null) {
                    Format selectedFormat = exoTrackSelection.getSelectedFormat();
                    if (!MimeTypes.allSamplesAreSyncSamples(selectedFormat.sampleMimeType, selectedFormat.codecs)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean continueLoading(LoadingInfo loadingInfo) {
        return this.mediaPeriod.continueLoading(loadingInfo);
    }

    public void discardBuffer(long j2, boolean z) {
        this.mediaPeriod.discardBuffer(j2, z);
    }

    public long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters) {
        long j3 = this.startUs;
        if (j2 == j3) {
            return j3;
        }
        return this.mediaPeriod.getAdjustedSeekPositionUs(j2, clipSeekParameters(j2, seekParameters));
    }

    public long getBufferedPositionUs() {
        long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
        if (bufferedPositionUs != Long.MIN_VALUE) {
            long j2 = this.endUs;
            if (j2 == Long.MIN_VALUE || bufferedPositionUs < j2) {
                return bufferedPositionUs;
            }
        }
        return Long.MIN_VALUE;
    }

    public long getNextLoadPositionUs() {
        long nextLoadPositionUs = this.mediaPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs != Long.MIN_VALUE) {
            long j2 = this.endUs;
            if (j2 == Long.MIN_VALUE || nextLoadPositionUs < j2) {
                return nextLoadPositionUs;
            }
        }
        return Long.MIN_VALUE;
    }

    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    public boolean isLoading() {
        return this.mediaPeriod.isLoading();
    }

    public boolean isPendingInitialDiscontinuity() {
        if (this.pendingInitialDiscontinuityPositionUs != -9223372036854775807L) {
            return true;
        }
        return false;
    }

    public void maybeThrowPrepareError() {
        ClippingMediaSource.IllegalClippingException illegalClippingException = this.clippingError;
        if (illegalClippingException == null) {
            this.mediaPeriod.maybeThrowPrepareError();
            return;
        }
        throw illegalClippingException;
    }

    public void onPrepared(MediaPeriod mediaPeriod2) {
        if (this.clippingError == null) {
            ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
        }
    }

    public void prepare(MediaPeriod.Callback callback2, long j2) {
        this.callback = callback2;
        this.mediaPeriod.prepare(this, j2);
    }

    public long readDiscontinuity() {
        if (isPendingInitialDiscontinuity()) {
            long j2 = this.pendingInitialDiscontinuityPositionUs;
            this.pendingInitialDiscontinuityPositionUs = -9223372036854775807L;
            long readDiscontinuity = readDiscontinuity();
            if (readDiscontinuity != -9223372036854775807L) {
                return readDiscontinuity;
            }
            return j2;
        }
        long readDiscontinuity2 = this.mediaPeriod.readDiscontinuity();
        if (readDiscontinuity2 == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return enforceClippingRange(readDiscontinuity2, this.startUs, this.endUs);
    }

    public void reevaluateBuffer(long j2) {
        this.mediaPeriod.reevaluateBuffer(j2);
    }

    public long seekToUs(long j2) {
        this.pendingInitialDiscontinuityPositionUs = -9223372036854775807L;
        for (ClippingSampleStream clippingSampleStream : this.sampleStreams) {
            if (clippingSampleStream != null) {
                clippingSampleStream.clearSentEos();
            }
        }
        return enforceClippingRange(this.mediaPeriod.seekToUs(j2), this.startUs, this.endUs);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long selectTracks(androidx.media3.exoplayer.trackselection.ExoTrackSelection[] r18, boolean[] r19, androidx.media3.exoplayer.source.SampleStream[] r20, boolean[] r21, long r22) {
        /*
            r17 = this;
            r0 = r17
            r1 = r20
            int r2 = r1.length
            androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream[] r2 = new androidx.media3.exoplayer.source.ClippingMediaPeriod.ClippingSampleStream[r2]
            r0.sampleStreams = r2
            int r2 = r1.length
            androidx.media3.exoplayer.source.SampleStream[] r6 = new androidx.media3.exoplayer.source.SampleStream[r2]
            r2 = 0
            r3 = r2
        L_0x000e:
            int r4 = r1.length
            r10 = 0
            if (r3 >= r4) goto L_0x0023
            androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream[] r4 = r0.sampleStreams
            r5 = r1[r3]
            androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream r5 = (androidx.media3.exoplayer.source.ClippingMediaPeriod.ClippingSampleStream) r5
            r4[r3] = r5
            if (r5 == 0) goto L_0x001e
            androidx.media3.exoplayer.source.SampleStream r10 = r5.childStream
        L_0x001e:
            r6[r3] = r10
            int r3 = r3 + 1
            goto L_0x000e
        L_0x0023:
            androidx.media3.exoplayer.source.MediaPeriod r3 = r0.mediaPeriod
            r4 = r18
            r5 = r19
            r7 = r21
            r8 = r22
            long r11 = r3.selectTracks(r4, r5, r6, r7, r8)
            long r3 = r0.endUs
            r13 = r22
            r15 = r3
            long r3 = enforceClippingRange(r11, r13, r15)
            boolean r5 = r0.isPendingInitialDiscontinuity()
            if (r5 == 0) goto L_0x004c
            r5 = r18
            r13 = r22
            boolean r5 = shouldKeepInitialDiscontinuity(r11, r13, r5)
            if (r5 == 0) goto L_0x004c
            r7 = r3
            goto L_0x0051
        L_0x004c:
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x0051:
            r0.pendingInitialDiscontinuityPositionUs = r7
        L_0x0053:
            int r5 = r1.length
            if (r2 >= r5) goto L_0x0079
            r5 = r6[r2]
            if (r5 != 0) goto L_0x005f
            androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream[] r5 = r0.sampleStreams
            r5[r2] = r10
            goto L_0x0070
        L_0x005f:
            androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream[] r7 = r0.sampleStreams
            r8 = r7[r2]
            if (r8 == 0) goto L_0x0069
            androidx.media3.exoplayer.source.SampleStream r8 = r8.childStream
            if (r8 == r5) goto L_0x0070
        L_0x0069:
            androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream r8 = new androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream
            r8.<init>(r5)
            r7[r2] = r8
        L_0x0070:
            androidx.media3.exoplayer.source.ClippingMediaPeriod$ClippingSampleStream[] r5 = r0.sampleStreams
            r5 = r5[r2]
            r1[r2] = r5
            int r2 = r2 + 1
            goto L_0x0053
        L_0x0079:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.ClippingMediaPeriod.selectTracks(androidx.media3.exoplayer.trackselection.ExoTrackSelection[], boolean[], androidx.media3.exoplayer.source.SampleStream[], boolean[], long):long");
    }

    public void setClippingError(ClippingMediaSource.IllegalClippingException illegalClippingException) {
        this.clippingError = illegalClippingException;
    }

    public void updateClipping(long j2, long j3) {
        this.startUs = j2;
        this.endUs = j3;
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod2) {
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
    }
}
