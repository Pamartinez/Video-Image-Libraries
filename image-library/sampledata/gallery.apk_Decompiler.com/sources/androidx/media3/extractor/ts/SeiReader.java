package androidx.media3.extractor.ts;

import U3.a;
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
public final class SeiReader {
    private final List<Format> closedCaptionFormats;
    private final String containerMimeType;
    private final TrackOutput[] outputs;
    private final ReorderingBufferQueue reorderingBufferQueue = new ReorderingBufferQueue(new a(28, this));

    public SeiReader(List<Format> list, String str) {
        this.closedCaptionFormats = list;
        this.containerMimeType = str;
        this.outputs = new TrackOutput[list.size()];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(long j2, ParsableByteArray parsableByteArray) {
        CeaUtil.consume(j2, parsableByteArray, this.outputs);
    }

    public void clear() {
        this.reorderingBufferQueue.flush();
    }

    public void consume(long j2, ParsableByteArray parsableByteArray) {
        this.reorderingBufferQueue.add(j2, parsableByteArray);
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
            String str2 = format.id;
            if (str2 == null) {
                str2 = trackIdGenerator.getFormatId();
            }
            track.format(new Format.Builder().setId(str2).setContainerMimeType(this.containerMimeType).setSampleMimeType(str).setSelectionFlags(format.selectionFlags).setLanguage(format.language).setAccessibilityChannel(format.accessibilityChannel).setInitializationData(format.initializationData).build());
            this.outputs[i2] = track;
        }
    }

    public void flush() {
        this.reorderingBufferQueue.flush();
    }

    public void setReorderingQueueSize(int i2) {
        this.reorderingBufferQueue.setMaxSize(i2);
    }
}
