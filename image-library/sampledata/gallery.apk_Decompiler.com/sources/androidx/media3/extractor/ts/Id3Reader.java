package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Id3Reader implements ElementaryStreamReader {
    private final String containerMimeType;
    private final ParsableByteArray id3Header = new ParsableByteArray(10);
    private TrackOutput output;
    private int sampleBytesRead;
    private int sampleSize;
    private long sampleTimeUs = -9223372036854775807L;
    private boolean writingSample;

    public Id3Reader(String str) {
        this.containerMimeType = str;
    }

    public void consume(ParsableByteArray parsableByteArray) {
        Assertions.checkStateNotNull(this.output);
        if (this.writingSample) {
            int bytesLeft = parsableByteArray.bytesLeft();
            int i2 = this.sampleBytesRead;
            if (i2 < 10) {
                int min = Math.min(bytesLeft, 10 - i2);
                System.arraycopy(parsableByteArray.getData(), parsableByteArray.getPosition(), this.id3Header.getData(), this.sampleBytesRead, min);
                if (this.sampleBytesRead + min == 10) {
                    this.id3Header.setPosition(0);
                    if (73 == this.id3Header.readUnsignedByte() && 68 == this.id3Header.readUnsignedByte() && 51 == this.id3Header.readUnsignedByte()) {
                        this.id3Header.skipBytes(3);
                        this.sampleSize = this.id3Header.readSynchSafeInt() + 10;
                    } else {
                        Log.w("Id3Reader", "Discarding invalid ID3 tag");
                        this.writingSample = false;
                        return;
                    }
                }
            }
            int min2 = Math.min(bytesLeft, this.sampleSize - this.sampleBytesRead);
            this.output.sampleData(parsableByteArray, min2);
            this.sampleBytesRead += min2;
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 5);
        this.output = track;
        track.format(new Format.Builder().setId(trackIdGenerator.getFormatId()).setContainerMimeType(this.containerMimeType).setSampleMimeType("application/id3").build());
    }

    public void packetFinished(boolean z) {
        int i2;
        boolean z3;
        Assertions.checkStateNotNull(this.output);
        if (this.writingSample && (i2 = this.sampleSize) != 0 && this.sampleBytesRead == i2) {
            if (this.sampleTimeUs != -9223372036854775807L) {
                z3 = true;
            } else {
                z3 = false;
            }
            Assertions.checkState(z3);
            this.output.sampleMetadata(this.sampleTimeUs, 1, this.sampleSize, 0, (TrackOutput.CryptoData) null);
            this.writingSample = false;
        }
    }

    public void packetStarted(long j2, int i2) {
        if ((i2 & 4) != 0) {
            this.writingSample = true;
            this.sampleTimeUs = j2;
            this.sampleSize = 0;
            this.sampleBytesRead = 0;
        }
    }

    public void seek() {
        this.writingSample = false;
        this.sampleTimeUs = -9223372036854775807L;
    }
}
