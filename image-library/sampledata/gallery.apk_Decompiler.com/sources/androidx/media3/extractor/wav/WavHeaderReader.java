package androidx.media3.extractor.wav;

import android.util.Pair;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class WavHeaderReader {
    private static final byte[] AMBISONIC_SUBFORMAT = {0, 0, 33, 7, -45, 17, -122, 68, -56, -63, -54, 0, 0, 0};
    private static final byte[] WAVEEXT_SUBFORMAT = {0, 0, 0, 0, 16, 0, Byte.MIN_VALUE, 0, 0, -86, 0, 56, -101, 113};

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ChunkHeader {
        public final int id;
        public final long size;

        private ChunkHeader(int i2, long j2) {
            this.id = i2;
            this.size = j2;
        }

        public static ChunkHeader peek(ExtractorInput extractorInput, ParsableByteArray parsableByteArray) {
            extractorInput.peekFully(parsableByteArray.getData(), 0, 8);
            parsableByteArray.setPosition(0);
            return new ChunkHeader(parsableByteArray.readInt(), parsableByteArray.readLittleEndianUnsignedInt());
        }
    }

    public static boolean checkFileType(ExtractorInput extractorInput) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(8);
        int i2 = ChunkHeader.peek(extractorInput, parsableByteArray).id;
        if (i2 != 1380533830 && i2 != 1380333108) {
            return false;
        }
        extractorInput.peekFully(parsableByteArray.getData(), 0, 4);
        parsableByteArray.setPosition(0);
        int readInt = parsableByteArray.readInt();
        if (readInt == 1463899717) {
            return true;
        }
        Log.e("WavHeaderReader", "Unsupported form type: " + readInt);
        return false;
    }

    public static WavFormat readFormat(ExtractorInput extractorInput) {
        boolean z;
        byte[] bArr;
        ParsableByteArray parsableByteArray = new ParsableByteArray(16);
        ChunkHeader skipToChunk = skipToChunk(1718449184, extractorInput, parsableByteArray);
        if (skipToChunk.size >= 16) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        extractorInput.peekFully(parsableByteArray.getData(), 0, 16);
        parsableByteArray.setPosition(0);
        int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
        int readLittleEndianUnsignedShort2 = parsableByteArray.readLittleEndianUnsignedShort();
        int readLittleEndianUnsignedIntToInt = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int readLittleEndianUnsignedIntToInt2 = parsableByteArray.readLittleEndianUnsignedIntToInt();
        int readLittleEndianUnsignedShort3 = parsableByteArray.readLittleEndianUnsignedShort();
        int readLittleEndianUnsignedShort4 = parsableByteArray.readLittleEndianUnsignedShort();
        int i2 = ((int) skipToChunk.size) - 16;
        if (i2 > 0) {
            bArr = new byte[i2];
            extractorInput.peekFully(bArr, 0, i2);
            if (readLittleEndianUnsignedShort == 65534 && i2 == 24) {
                ParsableByteArray parsableByteArray2 = new ParsableByteArray(bArr);
                parsableByteArray2.readLittleEndianUnsignedShort();
                int readLittleEndianUnsignedShort5 = parsableByteArray2.readLittleEndianUnsignedShort();
                if (readLittleEndianUnsignedShort5 == 0 || readLittleEndianUnsignedShort5 == readLittleEndianUnsignedShort4) {
                    int readLittleEndianUnsignedIntToInt3 = parsableByteArray2.readLittleEndianUnsignedIntToInt();
                    if ((readLittleEndianUnsignedIntToInt3 >> 18) != 0) {
                        throw ParserException.createForUnsupportedContainerFeature("invalid channel mask " + readLittleEndianUnsignedIntToInt3);
                    } else if (readLittleEndianUnsignedIntToInt3 == 0 || Integer.bitCount(readLittleEndianUnsignedIntToInt3) == readLittleEndianUnsignedShort2) {
                        readLittleEndianUnsignedShort = parsableByteArray2.readLittleEndianUnsignedShort();
                        byte[] bArr2 = new byte[14];
                        parsableByteArray2.readBytes(bArr2, 0, 14);
                        if (!Arrays.equals(bArr2, WAVEEXT_SUBFORMAT) && !Arrays.equals(bArr2, AMBISONIC_SUBFORMAT)) {
                            throw ParserException.createForUnsupportedContainerFeature("invalid wav format extension guid");
                        }
                    } else {
                        throw ParserException.createForUnsupportedContainerFeature("invalid number of channels (" + Integer.bitCount(readLittleEndianUnsignedIntToInt3) + ") in channel mask " + readLittleEndianUnsignedIntToInt3);
                    }
                } else {
                    throw ParserException.createForUnsupportedContainerFeature("validBits ( " + readLittleEndianUnsignedShort5 + ")  != bitsPerSample( " + readLittleEndianUnsignedShort4 + ") are not supported");
                }
            }
        } else {
            bArr = Util.EMPTY_BYTE_ARRAY;
        }
        byte[] bArr3 = bArr;
        int i7 = readLittleEndianUnsignedShort;
        extractorInput.skipFully((int) (extractorInput.getPeekPosition() - extractorInput.getPosition()));
        return new WavFormat(i7, readLittleEndianUnsignedShort2, readLittleEndianUnsignedIntToInt, readLittleEndianUnsignedIntToInt2, readLittleEndianUnsignedShort3, readLittleEndianUnsignedShort4, bArr3);
    }

    public static long readRf64SampleDataSize(ExtractorInput extractorInput) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(8);
        ChunkHeader peek = ChunkHeader.peek(extractorInput, parsableByteArray);
        if (peek.id != 1685272116) {
            extractorInput.resetPeekPosition();
            return -1;
        }
        extractorInput.advancePeekPosition(8);
        parsableByteArray.setPosition(0);
        extractorInput.peekFully(parsableByteArray.getData(), 0, 8);
        long readLittleEndianLong = parsableByteArray.readLittleEndianLong();
        extractorInput.skipFully(((int) peek.size) + 8);
        return readLittleEndianLong;
    }

    private static ChunkHeader skipToChunk(int i2, ExtractorInput extractorInput, ParsableByteArray parsableByteArray) {
        ChunkHeader peek = ChunkHeader.peek(extractorInput, parsableByteArray);
        while (peek.id != i2) {
            Log.w("WavHeaderReader", "Ignoring unknown WAV chunk: " + peek.id);
            long j2 = peek.size;
            long j3 = 8 + j2;
            if (j2 % 2 != 0) {
                j3 = 9 + j2;
            }
            if (j3 <= 2147483647L) {
                extractorInput.skipFully((int) j3);
                peek = ChunkHeader.peek(extractorInput, parsableByteArray);
            } else {
                throw ParserException.createForUnsupportedContainerFeature("Chunk is too large (~2GB+) to skip; id: " + peek.id);
            }
        }
        return peek;
    }

    public static Pair<Long, Long> skipToSampleData(ExtractorInput extractorInput) {
        extractorInput.resetPeekPosition();
        ChunkHeader skipToChunk = skipToChunk(1684108385, extractorInput, new ParsableByteArray(8));
        extractorInput.skipFully(8);
        return Pair.create(Long.valueOf(extractorInput.getPosition()), Long.valueOf(skipToChunk.size));
    }
}
