package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DvbSubtitleReader implements ElementaryStreamReader {
    private int bytesToCheck;
    private final String containerMimeType;
    private final TrackOutput[] outputs;
    private int sampleBytesWritten;
    private long sampleTimeUs = -9223372036854775807L;
    private final List<TsPayloadReader.DvbSubtitleInfo> subtitleInfos;
    private boolean writingSample;

    public DvbSubtitleReader(List<TsPayloadReader.DvbSubtitleInfo> list, String str) {
        this.subtitleInfos = list;
        this.containerMimeType = str;
        this.outputs = new TrackOutput[list.size()];
    }

    private boolean checkNextByte(ParsableByteArray parsableByteArray, int i2) {
        if (parsableByteArray.bytesLeft() == 0) {
            return false;
        }
        if (parsableByteArray.readUnsignedByte() != i2) {
            this.writingSample = false;
        }
        this.bytesToCheck--;
        return this.writingSample;
    }

    public void consume(ParsableByteArray parsableByteArray) {
        if (!this.writingSample) {
            return;
        }
        if (this.bytesToCheck != 2 || checkNextByte(parsableByteArray, 32)) {
            if (this.bytesToCheck != 1 || checkNextByte(parsableByteArray, 0)) {
                int position = parsableByteArray.getPosition();
                int bytesLeft = parsableByteArray.bytesLeft();
                for (TrackOutput sampleData : this.outputs) {
                    parsableByteArray.setPosition(position);
                    sampleData.sampleData(parsableByteArray, bytesLeft);
                }
                this.sampleBytesWritten += bytesLeft;
            }
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        for (int i2 = 0; i2 < this.outputs.length; i2++) {
            TsPayloadReader.DvbSubtitleInfo dvbSubtitleInfo = this.subtitleInfos.get(i2);
            trackIdGenerator.generateNewId();
            TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 3);
            track.format(new Format.Builder().setId(trackIdGenerator.getFormatId()).setContainerMimeType(this.containerMimeType).setSampleMimeType("application/dvbsubs").setInitializationData(Collections.singletonList(dvbSubtitleInfo.initializationData)).setLanguage(dvbSubtitleInfo.language).build());
            this.outputs[i2] = track;
        }
    }

    public void packetFinished(boolean z) {
        boolean z3;
        if (this.writingSample) {
            if (this.sampleTimeUs != -9223372036854775807L) {
                z3 = true;
            } else {
                z3 = false;
            }
            Assertions.checkState(z3);
            for (TrackOutput sampleMetadata : this.outputs) {
                sampleMetadata.sampleMetadata(this.sampleTimeUs, 1, this.sampleBytesWritten, 0, (TrackOutput.CryptoData) null);
            }
            this.writingSample = false;
        }
    }

    public void packetStarted(long j2, int i2) {
        if ((i2 & 4) != 0) {
            this.writingSample = true;
            this.sampleTimeUs = j2;
            this.sampleBytesWritten = 0;
            this.bytesToCheck = 2;
        }
    }

    public void seek() {
        this.writingSample = false;
        this.sampleTimeUs = -9223372036854775807L;
    }
}
