package androidx.media3.extractor.amr;

import P.a;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ConstantBitrateSeekMap;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.IndexSeekMap;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import com.samsung.android.imagetranslation.common.Config;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.io.EOFException;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AmrExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new a(1);
    private static final byte[] amrSignatureNb = Util.getUtf8Bytes("#!AMR\n");
    private static final byte[] amrSignatureWb = Util.getUtf8Bytes("#!AMR-WB\n");
    private static final int[] frameSizeBytesByTypeNb = {13, 14, 16, 18, 20, 21, 27, 32, 6, 7, 6, 6, 1, 1, 1, 1};
    private static final int[] frameSizeBytesByTypeWb = {18, 24, 33, 37, 41, 47, 51, 59, 61, 6, 1, 1, 1, 1, 1, 1};
    private int currentSampleBytesRemaining;
    private int currentSampleSize;
    private long currentSampleTimeUs;
    private TrackOutput currentTrackOutput;
    private ExtractorOutput extractorOutput;
    private long firstSamplePosition;
    private int firstSampleSize;
    private final int flags;
    private boolean hasOutputFormat;
    private boolean isSeekInProgress;
    private boolean isWideBand;
    private int numSamplesWithSameSize;
    private TrackOutput realTrackOutput;
    private final byte[] scratch;
    private SeekMap seekMap;
    private long seekTimeUs;
    private final TrackOutput skippingTrackOutput;
    private long timeOffsetUs;

    public AmrExtractor() {
        this(0);
    }

    private void assertInitialized() {
        Assertions.checkStateNotNull(this.realTrackOutput);
        Util.castNonNull(this.extractorOutput);
    }

    private static int getBitrateFromFrameSize(int i2, long j2) {
        return (int) ((((long) i2) * 8000000) / j2);
    }

    private SeekMap getConstantBitrateSeekMap(long j2, boolean z) {
        return new ConstantBitrateSeekMap(j2, this.firstSamplePosition, getBitrateFromFrameSize(this.firstSampleSize, 20000), this.firstSampleSize, z);
    }

    private int getFrameSizeInBytes(int i2) {
        String str;
        if (!isValidFrameType(i2)) {
            StringBuilder sb2 = new StringBuilder("Illegal AMR ");
            if (this.isWideBand) {
                str = "WB";
            } else {
                str = "NB";
            }
            sb2.append(str);
            sb2.append(" frame type ");
            sb2.append(i2);
            throw ParserException.createForMalformedContainer(sb2.toString(), (Throwable) null);
        } else if (this.isWideBand) {
            return frameSizeBytesByTypeWb[i2];
        } else {
            return frameSizeBytesByTypeNb[i2];
        }
    }

    private boolean isNarrowBandValidFrameType(int i2) {
        if (this.isWideBand) {
            return false;
        }
        if (i2 < 12 || i2 > 14) {
            return true;
        }
        return false;
    }

    private boolean isSeekTimeUsWithinRange(long j2, long j3) {
        if (Math.abs(j3 - j2) < 20000) {
            return true;
        }
        return false;
    }

    private boolean isValidFrameType(int i2) {
        if (i2 < 0 || i2 > 15) {
            return false;
        }
        if (isWideBandValidFrameType(i2) || isNarrowBandValidFrameType(i2)) {
            return true;
        }
        return false;
    }

    private boolean isWideBandValidFrameType(int i2) {
        if (!this.isWideBand) {
            return false;
        }
        if (i2 < 10 || i2 > 13) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new AmrExtractor()};
    }

    private void maybeOutputFormat() {
        String str;
        int i2;
        int i7;
        if (!this.hasOutputFormat) {
            this.hasOutputFormat = true;
            boolean z = this.isWideBand;
            String str2 = "audio/amr-wb";
            if (z) {
                str = str2;
            } else {
                str = "audio/amr";
            }
            if (!z) {
                str2 = Encode.CodecsMime.AUDIO_CODEC_AMR;
            }
            if (z) {
                i2 = Config.MAX_RESOLUTION_SUPPORTED;
            } else {
                i2 = Encode.BitRate.VIDEO_HD_BITRATE;
            }
            if (z) {
                i7 = frameSizeBytesByTypeWb[8];
            } else {
                i7 = frameSizeBytesByTypeNb[7];
            }
            this.realTrackOutput.format(new Format.Builder().setContainerMimeType(str).setSampleMimeType(str2).setMaxInputSize(i7).setChannelCount(1).setSampleRate(i2).build());
        }
    }

    private void maybeOutputSeekMap(long j2, int i2) {
        int i7;
        if (this.seekMap == null) {
            int i8 = this.flags;
            boolean z = false;
            if ((i8 & 4) != 0) {
                this.seekMap = new IndexSeekMap(new long[]{this.firstSamplePosition}, new long[]{0}, -9223372036854775807L);
            } else if ((i8 & 1) == 0 || !((i7 = this.firstSampleSize) == -1 || i7 == this.currentSampleSize)) {
                this.seekMap = new SeekMap.Unseekable(-9223372036854775807L);
            } else if (this.numSamplesWithSameSize >= 20 || i2 == -1) {
                if ((i8 & 2) != 0) {
                    z = true;
                }
                SeekMap constantBitrateSeekMap = getConstantBitrateSeekMap(j2, z);
                this.seekMap = constantBitrateSeekMap;
                this.realTrackOutput.durationUs(constantBitrateSeekMap.getDurationUs());
            }
            SeekMap seekMap2 = this.seekMap;
            if (seekMap2 != null) {
                this.extractorOutput.seekMap(seekMap2);
            }
        }
    }

    private static boolean peekAmrSignature(ExtractorInput extractorInput, byte[] bArr) {
        extractorInput.resetPeekPosition();
        byte[] bArr2 = new byte[bArr.length];
        extractorInput.peekFully(bArr2, 0, bArr.length);
        return Arrays.equals(bArr2, bArr);
    }

    private int peekNextSampleSize(ExtractorInput extractorInput) {
        extractorInput.resetPeekPosition();
        extractorInput.peekFully(this.scratch, 0, 1);
        byte b = this.scratch[0];
        if ((b & 131) <= 0) {
            return getFrameSizeInBytes((b >> 3) & 15);
        }
        throw ParserException.createForMalformedContainer("Invalid padding bits for frame header " + b, (Throwable) null);
    }

    private boolean readAmrHeader(ExtractorInput extractorInput) {
        byte[] bArr = amrSignatureNb;
        if (peekAmrSignature(extractorInput, bArr)) {
            this.isWideBand = false;
            extractorInput.skipFully(bArr.length);
            return true;
        }
        byte[] bArr2 = amrSignatureWb;
        if (!peekAmrSignature(extractorInput, bArr2)) {
            return false;
        }
        this.isWideBand = true;
        extractorInput.skipFully(bArr2.length);
        return true;
    }

    private int readSample(ExtractorInput extractorInput) {
        if (this.currentSampleBytesRemaining == 0) {
            try {
                int peekNextSampleSize = peekNextSampleSize(extractorInput);
                this.currentSampleSize = peekNextSampleSize;
                this.currentSampleBytesRemaining = peekNextSampleSize;
                if (this.firstSampleSize == -1) {
                    this.firstSamplePosition = extractorInput.getPosition();
                    this.firstSampleSize = this.currentSampleSize;
                }
                if (this.firstSampleSize == this.currentSampleSize) {
                    this.numSamplesWithSameSize++;
                }
                SeekMap seekMap2 = this.seekMap;
                if (seekMap2 instanceof IndexSeekMap) {
                    IndexSeekMap indexSeekMap = (IndexSeekMap) seekMap2;
                    long j2 = this.timeOffsetUs + this.currentSampleTimeUs + 20000;
                    long position = extractorInput.getPosition() + ((long) this.currentSampleSize);
                    if (!indexSeekMap.isTimeUsInIndex(j2, 100000)) {
                        indexSeekMap.addSeekPoint(j2, position);
                    }
                    if (this.isSeekInProgress && isSeekTimeUsWithinRange(j2, this.seekTimeUs)) {
                        this.isSeekInProgress = false;
                        this.currentTrackOutput = this.realTrackOutput;
                    }
                }
            } catch (EOFException unused) {
                return -1;
            }
        }
        int sampleData = this.currentTrackOutput.sampleData((DataReader) extractorInput, this.currentSampleBytesRemaining, true);
        if (sampleData == -1) {
            return -1;
        }
        int i2 = this.currentSampleBytesRemaining - sampleData;
        this.currentSampleBytesRemaining = i2;
        if (i2 > 0) {
            return 0;
        }
        this.currentTrackOutput.sampleMetadata(this.timeOffsetUs + this.currentSampleTimeUs, 1, this.currentSampleSize, 0, (TrackOutput.CryptoData) null);
        this.currentSampleTimeUs += 20000;
        return 0;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
        TrackOutput track = extractorOutput2.track(0, 1);
        this.realTrackOutput = track;
        this.currentTrackOutput = track;
        extractorOutput2.endTracks();
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        assertInitialized();
        if (extractorInput.getPosition() != 0 || readAmrHeader(extractorInput)) {
            maybeOutputFormat();
            int readSample = readSample(extractorInput);
            maybeOutputSeekMap(extractorInput.getLength(), readSample);
            if (readSample == -1) {
                SeekMap seekMap2 = this.seekMap;
                if (seekMap2 instanceof IndexSeekMap) {
                    long j2 = this.timeOffsetUs + this.currentSampleTimeUs;
                    ((IndexSeekMap) seekMap2).setDurationUs(j2);
                    this.extractorOutput.seekMap(this.seekMap);
                    this.realTrackOutput.durationUs(j2);
                }
            }
            return readSample;
        }
        throw ParserException.createForMalformedContainer("Could not find AMR header.", (Throwable) null);
    }

    public void seek(long j2, long j3) {
        this.currentSampleTimeUs = 0;
        this.currentSampleSize = 0;
        this.currentSampleBytesRemaining = 0;
        this.seekTimeUs = j3;
        SeekMap seekMap2 = this.seekMap;
        if (seekMap2 instanceof IndexSeekMap) {
            long timeUs = ((IndexSeekMap) seekMap2).getTimeUs(j2);
            this.timeOffsetUs = timeUs;
            if (!isSeekTimeUsWithinRange(timeUs, this.seekTimeUs)) {
                this.isSeekInProgress = true;
                this.currentTrackOutput = this.skippingTrackOutput;
            }
        } else if (j2 == 0 || !(seekMap2 instanceof ConstantBitrateSeekMap)) {
            this.timeOffsetUs = 0;
        } else {
            this.timeOffsetUs = ((ConstantBitrateSeekMap) seekMap2).getTimeUsAtPosition(j2);
        }
    }

    public boolean sniff(ExtractorInput extractorInput) {
        return readAmrHeader(extractorInput);
    }

    public AmrExtractor(int i2) {
        this.flags = (i2 & 2) != 0 ? i2 | 1 : i2;
        this.scratch = new byte[1];
        this.firstSampleSize = -1;
        DiscardingTrackOutput discardingTrackOutput = new DiscardingTrackOutput();
        this.skippingTrackOutput = discardingTrackOutput;
        this.currentTrackOutput = discardingTrackOutput;
    }

    public void release() {
    }
}
