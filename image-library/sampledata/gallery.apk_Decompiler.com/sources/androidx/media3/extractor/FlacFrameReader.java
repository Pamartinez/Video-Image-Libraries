package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FlacFrameReader {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SampleNumberHolder {
        public long sampleNumber;
    }

    private static boolean checkAndReadBlockSizeSamples(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, int i2) {
        int readFrameBlockSizeSamplesFromKey = readFrameBlockSizeSamplesFromKey(parsableByteArray, i2);
        if (readFrameBlockSizeSamplesFromKey == -1 || readFrameBlockSizeSamplesFromKey > flacStreamMetadata.maxBlockSizeSamples) {
            return false;
        }
        return true;
    }

    private static boolean checkAndReadCrc(ParsableByteArray parsableByteArray, int i2) {
        if (parsableByteArray.readUnsignedByte() == Util.crc8(parsableByteArray.getData(), i2, parsableByteArray.getPosition() - 1, 0)) {
            return true;
        }
        return false;
    }

    private static boolean checkAndReadFirstSampleNumber(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, boolean z, SampleNumberHolder sampleNumberHolder) {
        try {
            long readUtf8EncodedLong = parsableByteArray.readUtf8EncodedLong();
            if (!z) {
                readUtf8EncodedLong *= (long) flacStreamMetadata.maxBlockSizeSamples;
            }
            sampleNumberHolder.sampleNumber = readUtf8EncodedLong;
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static boolean checkAndReadFrameHeader(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, int i2, SampleNumberHolder sampleNumberHolder) {
        boolean z;
        boolean z3;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        FlacStreamMetadata flacStreamMetadata2 = flacStreamMetadata;
        int position = parsableByteArray2.getPosition();
        long readUnsignedInt = parsableByteArray2.readUnsignedInt();
        long j2 = readUnsignedInt >>> 16;
        if (j2 != ((long) i2)) {
            return false;
        }
        if ((j2 & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        int i7 = (int) ((readUnsignedInt >> 12) & 15);
        int i8 = (int) ((readUnsignedInt >> 8) & 15);
        int i10 = (int) ((readUnsignedInt >> 4) & 15);
        int i11 = (int) ((readUnsignedInt >> 1) & 7);
        if ((readUnsignedInt & 1) == 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!checkChannelAssignment(i10, flacStreamMetadata2) || !checkBitsPerSample(i11, flacStreamMetadata2) || z3 || !checkAndReadFirstSampleNumber(parsableByteArray2, flacStreamMetadata2, z, sampleNumberHolder) || !checkAndReadBlockSizeSamples(parsableByteArray2, flacStreamMetadata2, i7) || !checkAndReadSampleRate(parsableByteArray2, flacStreamMetadata2, i8) || !checkAndReadCrc(parsableByteArray2, position)) {
            return false;
        }
        return true;
    }

    private static boolean checkAndReadSampleRate(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, int i2) {
        int i7 = flacStreamMetadata.sampleRate;
        if (i2 == 0) {
            return true;
        }
        if (i2 <= 11) {
            if (i2 == flacStreamMetadata.sampleRateLookupKey) {
                return true;
            }
            return false;
        } else if (i2 != 12) {
            if (i2 <= 14) {
                int readUnsignedShort = parsableByteArray.readUnsignedShort();
                if (i2 == 14) {
                    readUnsignedShort *= 10;
                }
                if (readUnsignedShort == i7) {
                    return true;
                }
            }
            return false;
        } else if (parsableByteArray.readUnsignedByte() * 1000 == i7) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean checkBitsPerSample(int i2, FlacStreamMetadata flacStreamMetadata) {
        if (i2 == 0 || i2 == flacStreamMetadata.bitsPerSampleLookupKey) {
            return true;
        }
        return false;
    }

    private static boolean checkChannelAssignment(int i2, FlacStreamMetadata flacStreamMetadata) {
        if (i2 <= 7) {
            if (i2 == flacStreamMetadata.channels - 1) {
                return true;
            }
            return false;
        } else if (i2 > 10 || flacStreamMetadata.channels != 2) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkFrameHeaderFromPeek(ExtractorInput extractorInput, FlacStreamMetadata flacStreamMetadata, int i2, SampleNumberHolder sampleNumberHolder) {
        long peekPosition = extractorInput.getPeekPosition();
        byte[] bArr = new byte[2];
        extractorInput.peekFully(bArr, 0, 2);
        if ((((bArr[0] & 255) << 8) | (bArr[1] & 255)) != i2) {
            extractorInput.resetPeekPosition();
            extractorInput.advancePeekPosition((int) (peekPosition - extractorInput.getPosition()));
            return false;
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(16);
        System.arraycopy(bArr, 0, parsableByteArray.getData(), 0, 2);
        parsableByteArray.setLimit(ExtractorUtil.peekToLength(extractorInput, parsableByteArray.getData(), 2, 14));
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition((int) (peekPosition - extractorInput.getPosition()));
        return checkAndReadFrameHeader(parsableByteArray, flacStreamMetadata, i2, sampleNumberHolder);
    }

    public static long getFirstSampleNumber(ExtractorInput extractorInput, FlacStreamMetadata flacStreamMetadata) {
        int i2;
        extractorInput.resetPeekPosition();
        boolean z = true;
        extractorInput.advancePeekPosition(1);
        byte[] bArr = new byte[1];
        extractorInput.peekFully(bArr, 0, 1);
        if ((bArr[0] & 1) != 1) {
            z = false;
        }
        extractorInput.advancePeekPosition(2);
        if (z) {
            i2 = 7;
        } else {
            i2 = 6;
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(i2);
        parsableByteArray.setLimit(ExtractorUtil.peekToLength(extractorInput, parsableByteArray.getData(), 0, i2));
        extractorInput.resetPeekPosition();
        SampleNumberHolder sampleNumberHolder = new SampleNumberHolder();
        if (checkAndReadFirstSampleNumber(parsableByteArray, flacStreamMetadata, z, sampleNumberHolder)) {
            return sampleNumberHolder.sampleNumber;
        }
        throw ParserException.createForMalformedContainer((String) null, (Throwable) null);
    }

    public static int readFrameBlockSizeSamplesFromKey(ParsableByteArray parsableByteArray, int i2) {
        switch (i2) {
            case 1:
                return 192;
            case 2:
            case 3:
            case 4:
            case 5:
                return 576 << (i2 - 2);
            case 6:
                return parsableByteArray.readUnsignedByte() + 1;
            case 7:
                return parsableByteArray.readUnsignedShort() + 1;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return 256 << (i2 - 8);
            default:
                return -1;
        }
    }
}
