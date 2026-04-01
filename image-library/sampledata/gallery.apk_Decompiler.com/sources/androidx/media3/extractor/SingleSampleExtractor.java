package androidx.media3.extractor;

import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.TrackOutput;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SingleSampleExtractor implements Extractor {
    private ExtractorOutput extractorOutput;
    private final int fileSignature;
    private final int fileSignatureLength;
    private final String sampleMimeType;
    private int size;
    private int state;
    private TrackOutput trackOutput;

    public SingleSampleExtractor(int i2, int i7, String str) {
        this.fileSignature = i2;
        this.fileSignatureLength = i7;
        this.sampleMimeType = str;
    }

    private void outputImageTrackAndSeekMap(String str) {
        TrackOutput track = this.extractorOutput.track(1024, 4);
        this.trackOutput = track;
        track.format(new Format.Builder().setContainerMimeType(str).setSampleMimeType(str).build());
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(new SingleSampleSeekMap(-9223372036854775807L));
        this.state = 1;
    }

    private void readSegment(ExtractorInput extractorInput) {
        int sampleData = ((TrackOutput) Assertions.checkNotNull(this.trackOutput)).sampleData((DataReader) extractorInput, 1024, true);
        if (sampleData == -1) {
            this.state = 2;
            this.trackOutput.sampleMetadata(0, 1, this.size, 0, (TrackOutput.CryptoData) null);
            this.size = 0;
            return;
        }
        this.size += sampleData;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
        outputImageTrackAndSeekMap(this.sampleMimeType);
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        int i2 = this.state;
        if (i2 == 1) {
            readSegment(extractorInput);
            return 0;
        } else if (i2 == 2) {
            return -1;
        } else {
            throw new IllegalStateException();
        }
    }

    public void seek(long j2, long j3) {
        if (j2 == 0 || this.state == 1) {
            this.state = 1;
            this.size = 0;
        }
    }

    public boolean sniff(ExtractorInput extractorInput) {
        boolean z;
        if (this.fileSignature == -1 || this.fileSignatureLength == -1) {
            z = false;
        } else {
            z = true;
        }
        Assertions.checkState(z);
        ParsableByteArray parsableByteArray = new ParsableByteArray(this.fileSignatureLength);
        extractorInput.peekFully(parsableByteArray.getData(), 0, this.fileSignatureLength);
        if (parsableByteArray.readUnsignedShort() == this.fileSignature) {
            return true;
        }
        return false;
    }

    public void release() {
    }
}
