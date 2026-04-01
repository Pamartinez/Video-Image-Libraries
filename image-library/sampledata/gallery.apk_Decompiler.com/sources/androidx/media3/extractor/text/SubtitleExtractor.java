package androidx.media3.extractor.text;

import U3.a;
import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.IndexSeekMap;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SubtitleExtractor implements Extractor {
    private int bytesRead;
    private final CueEncoder cueEncoder = new CueEncoder();
    private final Format format;
    private final List<Sample> samples;
    private final ParsableByteArray scratchSampleArray = new ParsableByteArray();
    private long seekTimeUs;
    private int state;
    private byte[] subtitleData = Util.EMPTY_BYTE_ARRAY;
    private final SubtitleParser subtitleParser;
    private long[] timestamps;
    private TrackOutput trackOutput;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Sample implements Comparable<Sample> {
        /* access modifiers changed from: private */
        public final byte[] data;
        /* access modifiers changed from: private */
        public final long timeUs;

        private Sample(long j2, byte[] bArr) {
            this.timeUs = j2;
            this.data = bArr;
        }

        public int compareTo(Sample sample) {
            return Long.compare(this.timeUs, sample.timeUs);
        }
    }

    public SubtitleExtractor(SubtitleParser subtitleParser2, Format format2) {
        Format format3;
        this.subtitleParser = subtitleParser2;
        if (format2 != null) {
            format3 = format2.buildUpon().setSampleMimeType("application/x-media3-cues").setCodecs(format2.sampleMimeType).setCueReplacementBehavior(subtitleParser2.getCueReplacementBehavior()).build();
        } else {
            format3 = null;
        }
        this.format = format3;
        this.samples = new ArrayList();
        this.state = 0;
        this.timestamps = Util.EMPTY_LONG_ARRAY;
        this.seekTimeUs = -9223372036854775807L;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$parseAndWriteToOutput$0(CuesWithTiming cuesWithTiming) {
        Sample sample = new Sample(cuesWithTiming.startTimeUs, this.cueEncoder.encode(cuesWithTiming.cues, cuesWithTiming.durationUs));
        this.samples.add(sample);
        long j2 = this.seekTimeUs;
        if (j2 == -9223372036854775807L || cuesWithTiming.endTimeUs >= j2) {
            writeToOutput(sample);
        }
    }

    private void parseAndWriteToOutput() {
        SubtitleParser.OutputOptions allCues;
        try {
            long j2 = this.seekTimeUs;
            if (j2 != -9223372036854775807L) {
                allCues = SubtitleParser.OutputOptions.cuesAfterThenRemainingCuesBefore(j2);
            } else {
                allCues = SubtitleParser.OutputOptions.allCues();
            }
            this.subtitleParser.parse(this.subtitleData, 0, this.bytesRead, allCues, new a(25, this));
            Collections.sort(this.samples);
            this.timestamps = new long[this.samples.size()];
            for (int i2 = 0; i2 < this.samples.size(); i2++) {
                this.timestamps[i2] = this.samples.get(i2).timeUs;
            }
            this.subtitleData = Util.EMPTY_BYTE_ARRAY;
        } catch (RuntimeException e) {
            throw ParserException.createForMalformedContainer("SubtitleParser failed.", e);
        }
    }

    private boolean readFromInput(ExtractorInput extractorInput) {
        byte[] bArr = this.subtitleData;
        if (bArr.length == this.bytesRead) {
            this.subtitleData = Arrays.copyOf(bArr, bArr.length + 1024);
        }
        byte[] bArr2 = this.subtitleData;
        int i2 = this.bytesRead;
        int read = extractorInput.read(bArr2, i2, bArr2.length - i2);
        if (read != -1) {
            this.bytesRead += read;
        }
        long length = extractorInput.getLength();
        if ((length == -1 || ((long) this.bytesRead) != length) && read != -1) {
            return false;
        }
        return true;
    }

    private boolean skipInput(ExtractorInput extractorInput) {
        int i2;
        if (extractorInput.getLength() != -1) {
            i2 = C0246a.N(extractorInput.getLength());
        } else {
            i2 = 1024;
        }
        if (extractorInput.skip(i2) == -1) {
            return true;
        }
        return false;
    }

    private void writeToOutput() {
        int i2;
        long j2 = this.seekTimeUs;
        if (j2 == -9223372036854775807L) {
            i2 = 0;
        } else {
            i2 = Util.binarySearchFloor(this.timestamps, j2, true, true);
        }
        while (i2 < this.samples.size()) {
            writeToOutput(this.samples.get(i2));
            i2++;
        }
    }

    public void init(ExtractorOutput extractorOutput) {
        boolean z;
        if (this.state == 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        TrackOutput track = extractorOutput.track(0, 3);
        this.trackOutput = track;
        Format format2 = this.format;
        if (format2 != null) {
            track.format(format2);
            extractorOutput.endTracks();
            extractorOutput.seekMap(new IndexSeekMap(new long[]{0}, new long[]{0}, -9223372036854775807L));
        }
        this.state = 1;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        boolean z;
        int i2;
        int i7 = this.state;
        if (i7 == 0 || i7 == 5) {
            z = false;
        } else {
            z = true;
        }
        Assertions.checkState(z);
        if (this.state == 1) {
            if (extractorInput.getLength() != -1) {
                i2 = C0246a.N(extractorInput.getLength());
            } else {
                i2 = 1024;
            }
            if (i2 > this.subtitleData.length) {
                this.subtitleData = new byte[i2];
            }
            this.bytesRead = 0;
            this.state = 2;
        }
        if (this.state == 2 && readFromInput(extractorInput)) {
            parseAndWriteToOutput();
            this.state = 4;
        }
        if (this.state == 3 && skipInput(extractorInput)) {
            writeToOutput();
            this.state = 4;
        }
        if (this.state == 4) {
            return -1;
        }
        return 0;
    }

    public void release() {
        if (this.state != 5) {
            this.subtitleParser.reset();
            this.state = 5;
        }
    }

    public void seek(long j2, long j3) {
        boolean z;
        int i2 = this.state;
        if (i2 == 0 || i2 == 5) {
            z = false;
        } else {
            z = true;
        }
        Assertions.checkState(z);
        this.seekTimeUs = j3;
        if (this.state == 2) {
            this.state = 1;
        }
        if (this.state == 4) {
            this.state = 3;
        }
    }

    public boolean sniff(ExtractorInput extractorInput) {
        return true;
    }

    private void writeToOutput(Sample sample) {
        Assertions.checkStateNotNull(this.trackOutput);
        int length = sample.data.length;
        this.scratchSampleArray.reset(sample.data);
        this.trackOutput.sampleData(this.scratchSampleArray, length);
        this.trackOutput.sampleMetadata(sample.timeUs, 1, length, 0, (TrackOutput.CryptoData) null);
    }
}
