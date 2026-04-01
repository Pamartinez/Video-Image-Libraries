package androidx.media3.extractor.text;

import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import java.io.EOFException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class SubtitleTranscodingTrackOutput implements TrackOutput {
    private final CueEncoder cueEncoder = new CueEncoder();
    private Format currentFormat;
    private SubtitleParser currentSubtitleParser;
    private final TrackOutput delegate;
    private final ParsableByteArray parsableScratch = new ParsableByteArray();
    private byte[] sampleData = Util.EMPTY_BYTE_ARRAY;
    private int sampleDataEnd = 0;
    private int sampleDataStart = 0;
    private boolean shouldSuppressParsingErrors;
    private final SubtitleParser.Factory subtitleParserFactory;

    public SubtitleTranscodingTrackOutput(TrackOutput trackOutput, SubtitleParser.Factory factory) {
        this.delegate = trackOutput;
        this.subtitleParserFactory = factory;
    }

    private void ensureSampleDataCapacity(int i2) {
        byte[] bArr;
        int length = this.sampleData.length;
        int i7 = this.sampleDataEnd;
        if (length - i7 < i2) {
            int i8 = i7 - this.sampleDataStart;
            int max = Math.max(i8 * 2, i2 + i8);
            byte[] bArr2 = this.sampleData;
            if (max <= bArr2.length) {
                bArr = bArr2;
            } else {
                bArr = new byte[max];
            }
            System.arraycopy(bArr2, this.sampleDataStart, bArr, 0, i8);
            this.sampleDataStart = 0;
            this.sampleDataEnd = i8;
            this.sampleData = bArr;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: outputSample */
    public void lambda$sampleMetadata$0(CuesWithTiming cuesWithTiming, long j2, int i2) {
        boolean z;
        Assertions.checkStateNotNull(this.currentFormat);
        byte[] encode = this.cueEncoder.encode(cuesWithTiming.cues, cuesWithTiming.durationUs);
        this.parsableScratch.reset(encode);
        this.delegate.sampleData(this.parsableScratch, encode.length);
        long j3 = cuesWithTiming.startTimeUs;
        if (j3 == -9223372036854775807L) {
            if (this.currentFormat.subsampleOffsetUs == Long.MAX_VALUE) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z);
        } else {
            long j8 = this.currentFormat.subsampleOffsetUs;
            if (j8 == Long.MAX_VALUE) {
                j2 += j3;
            } else {
                j2 = j3 + j8;
            }
        }
        this.delegate.sampleMetadata(j2, i2 | 1, encode.length, 0, (TrackOutput.CryptoData) null);
    }

    public void format(Format format) {
        boolean z;
        SubtitleParser subtitleParser;
        Assertions.checkNotNull(format.sampleMimeType);
        if (MimeTypes.getTrackType(format.sampleMimeType) == 3) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        if (!format.equals(this.currentFormat)) {
            this.currentFormat = format;
            if (this.subtitleParserFactory.supportsFormat(format)) {
                subtitleParser = this.subtitleParserFactory.create(format);
            } else {
                subtitleParser = null;
            }
            this.currentSubtitleParser = subtitleParser;
        }
        if (this.currentSubtitleParser == null) {
            this.delegate.format(format);
        } else {
            this.delegate.format(format.buildUpon().setSampleMimeType("application/x-media3-cues").setCodecs(format.sampleMimeType).setSubsampleOffsetUs(Long.MAX_VALUE).setCueReplacementBehavior(this.subtitleParserFactory.getCueReplacementBehavior(format)).build());
        }
    }

    public int sampleData(DataReader dataReader, int i2, boolean z, int i7) {
        if (this.currentSubtitleParser == null) {
            return this.delegate.sampleData(dataReader, i2, z, i7);
        }
        ensureSampleDataCapacity(i2);
        int read = dataReader.read(this.sampleData, this.sampleDataEnd, i2);
        if (read != -1) {
            this.sampleDataEnd += read;
            return read;
        } else if (z) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sampleMetadata(long r5, int r7, int r8, int r9, androidx.media3.extractor.TrackOutput.CryptoData r10) {
        /*
            r4 = this;
            androidx.media3.extractor.text.SubtitleParser r0 = r4.currentSubtitleParser
            if (r0 != 0) goto L_0x000a
            androidx.media3.extractor.TrackOutput r4 = r4.delegate
            r4.sampleMetadata(r5, r7, r8, r9, r10)
            return
        L_0x000a:
            r1 = 0
            if (r10 != 0) goto L_0x000f
            r10 = 1
            goto L_0x0010
        L_0x000f:
            r10 = r1
        L_0x0010:
            java.lang.String r0 = "DRM on subtitles is not supported"
            androidx.media3.common.util.Assertions.checkArgument(r10, r0)
            int r10 = r4.sampleDataEnd
            int r10 = r10 - r9
            int r10 = r10 - r8
            r2 = r5
            androidx.media3.extractor.text.SubtitleParser r5 = r4.currentSubtitleParser     // Catch:{ RuntimeException -> 0x0030 }
            byte[] r6 = r4.sampleData     // Catch:{ RuntimeException -> 0x0030 }
            androidx.media3.extractor.text.SubtitleParser$OutputOptions r9 = androidx.media3.extractor.text.SubtitleParser.OutputOptions.allCues()     // Catch:{ RuntimeException -> 0x0030 }
            r0 = r7
            r7 = r10
            androidx.media3.extractor.text.a r10 = new androidx.media3.extractor.text.a     // Catch:{ RuntimeException -> 0x002d }
            r10.<init>(r4, r2, r0)     // Catch:{ RuntimeException -> 0x002d }
            r5.parse(r6, r7, r8, r9, r10)     // Catch:{ RuntimeException -> 0x002d }
            goto L_0x003e
        L_0x002d:
            r0 = move-exception
        L_0x002e:
            r5 = r0
            goto L_0x0033
        L_0x0030:
            r0 = move-exception
            r7 = r10
            goto L_0x002e
        L_0x0033:
            boolean r6 = r4.shouldSuppressParsingErrors
            if (r6 == 0) goto L_0x004b
            java.lang.String r6 = "SubtitleTranscodingTO"
            java.lang.String r9 = "Parsing subtitles failed, ignoring sample."
            androidx.media3.common.util.Log.w(r6, r9, r5)
        L_0x003e:
            int r10 = r7 + r8
            r4.sampleDataStart = r10
            int r5 = r4.sampleDataEnd
            if (r10 != r5) goto L_0x004a
            r4.sampleDataStart = r1
            r4.sampleDataEnd = r1
        L_0x004a:
            return
        L_0x004b:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.SubtitleTranscodingTrackOutput.sampleMetadata(long, int, int, int, androidx.media3.extractor.TrackOutput$CryptoData):void");
    }

    public void shouldSuppressParsingErrors(boolean z) {
        this.shouldSuppressParsingErrors = z;
    }

    public void sampleData(ParsableByteArray parsableByteArray, int i2, int i7) {
        if (this.currentSubtitleParser == null) {
            this.delegate.sampleData(parsableByteArray, i2, i7);
            return;
        }
        ensureSampleDataCapacity(i2);
        parsableByteArray.readBytes(this.sampleData, this.sampleDataEnd, i2);
        this.sampleDataEnd += i2;
    }
}
