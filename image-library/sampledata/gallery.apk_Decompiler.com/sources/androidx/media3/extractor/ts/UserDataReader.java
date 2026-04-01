package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.ReorderingBufferQueue;
import androidx.media3.extractor.CeaUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class UserDataReader {
    private final List<Format> closedCaptionFormats;
    private final String containerMimeType;
    private final TrackOutput[] outputs;
    private final ReorderingBufferQueue reorderingBufferQueue;

    public UserDataReader(List<Format> list, String str) {
        this.closedCaptionFormats = list;
        this.containerMimeType = str;
        this.outputs = new TrackOutput[list.size()];
        ReorderingBufferQueue reorderingBufferQueue2 = new ReorderingBufferQueue(new a(this));
        this.reorderingBufferQueue = reorderingBufferQueue2;
        reorderingBufferQueue2.setMaxSize(3);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(long j2, ParsableByteArray parsableByteArray) {
        CeaUtil.consumeCcData(j2, parsableByteArray, this.outputs);
    }

    public void consume(long j2, ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() >= 9) {
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            int readUnsignedByte = parsableByteArray.readUnsignedByte();
            if (readInt == 434 && readInt2 == 1195456820 && readUnsignedByte == 3) {
                this.reorderingBufferQueue.add(j2, parsableByteArray);
            }
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        boolean z;
        for (int i2 = 0; i2 < this.outputs.length; i2++) {
            trackIdGenerator.generateNewId();
            TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 3);
            Format format = this.closedCaptionFormats.get(i2);
            String str = format.sampleMimeType;
            if ("application/cea-608".equals(str) || "application/cea-708".equals(str)) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z, "Invalid closed caption MIME type provided: " + str);
            track.format(new Format.Builder().setId(trackIdGenerator.getFormatId()).setContainerMimeType(this.containerMimeType).setSampleMimeType(str).setSelectionFlags(format.selectionFlags).setLanguage(format.language).setAccessibilityChannel(format.accessibilityChannel).setInitializationData(format.initializationData).build());
            this.outputs[i2] = track;
        }
    }
}
