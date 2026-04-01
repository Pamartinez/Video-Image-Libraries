package androidx.media3.extractor;

import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.TrackOutput;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TrueHdSampleRechunker {
    private int chunkFlags;
    private int chunkOffset;
    private int chunkSampleCount;
    private int chunkSize;
    private long chunkTimeUs;
    private boolean foundSyncframe;
    private final byte[] syncframePrefix = new byte[10];

    public void outputPendingSampleMetadata(TrackOutput trackOutput, TrackOutput.CryptoData cryptoData) {
        if (this.chunkSampleCount > 0) {
            trackOutput.sampleMetadata(this.chunkTimeUs, this.chunkFlags, this.chunkSize, this.chunkOffset, cryptoData);
            this.chunkSampleCount = 0;
        }
    }

    public void reset() {
        this.foundSyncframe = false;
        this.chunkSampleCount = 0;
    }

    public void sampleMetadata(TrackOutput trackOutput, long j2, int i2, int i7, int i8, TrackOutput.CryptoData cryptoData) {
        boolean z;
        if (this.chunkOffset <= i7 + i8) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z, "TrueHD chunk samples must be contiguous in the sample queue.");
        if (this.foundSyncframe) {
            int i10 = this.chunkSampleCount;
            int i11 = i10 + 1;
            this.chunkSampleCount = i11;
            if (i10 == 0) {
                this.chunkTimeUs = j2;
                this.chunkFlags = i2;
                this.chunkSize = 0;
            }
            this.chunkSize += i7;
            this.chunkOffset = i8;
            if (i11 >= 16) {
                outputPendingSampleMetadata(trackOutput, cryptoData);
            }
        }
    }

    public void startSample(ExtractorInput extractorInput) {
        if (!this.foundSyncframe) {
            extractorInput.peekFully(this.syncframePrefix, 0, 10);
            extractorInput.resetPeekPosition();
            if (Ac3Util.parseTrueHdSyncframeAudioSampleCount(this.syncframePrefix) != 0) {
                this.foundSyncframe = true;
            }
        }
    }
}
