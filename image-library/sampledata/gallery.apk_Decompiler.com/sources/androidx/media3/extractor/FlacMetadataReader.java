package androidx.media3.extractor;

import F2.U;
import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.FlacStreamMetadata;
import androidx.media3.extractor.metadata.flac.PictureFrame;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FlacMetadataReader {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FlacStreamMetadataHolder {
        public FlacStreamMetadata flacStreamMetadata;

        public FlacStreamMetadataHolder(FlacStreamMetadata flacStreamMetadata2) {
            this.flacStreamMetadata = flacStreamMetadata2;
        }
    }

    public static boolean checkAndPeekStreamMarker(ExtractorInput extractorInput) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(4);
        extractorInput.peekFully(parsableByteArray.getData(), 0, 4);
        if (parsableByteArray.readUnsignedInt() == 1716281667) {
            return true;
        }
        return false;
    }

    public static int getFrameStartMarker(ExtractorInput extractorInput) {
        extractorInput.resetPeekPosition();
        ParsableByteArray parsableByteArray = new ParsableByteArray(2);
        extractorInput.peekFully(parsableByteArray.getData(), 0, 2);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        if ((readUnsignedShort >> 2) == 16382) {
            extractorInput.resetPeekPosition();
            return readUnsignedShort;
        }
        extractorInput.resetPeekPosition();
        throw ParserException.createForMalformedContainer("First frame does not start with sync code.", (Throwable) null);
    }

    public static Metadata peekId3Metadata(ExtractorInput extractorInput, boolean z) {
        Id3Decoder.FramePredicate framePredicate;
        if (z) {
            framePredicate = null;
        } else {
            framePredicate = Id3Decoder.NO_FRAMES_PREDICATE;
        }
        Metadata peekId3Data = new Id3Peeker().peekId3Data(extractorInput, framePredicate);
        if (peekId3Data == null || peekId3Data.length() == 0) {
            return null;
        }
        return peekId3Data;
    }

    public static Metadata readId3Metadata(ExtractorInput extractorInput, boolean z) {
        extractorInput.resetPeekPosition();
        long peekPosition = extractorInput.getPeekPosition();
        Metadata peekId3Metadata = peekId3Metadata(extractorInput, z);
        extractorInput.skipFully((int) (extractorInput.getPeekPosition() - peekPosition));
        return peekId3Metadata;
    }

    public static boolean readMetadataBlock(ExtractorInput extractorInput, FlacStreamMetadataHolder flacStreamMetadataHolder) {
        extractorInput.resetPeekPosition();
        ParsableBitArray parsableBitArray = new ParsableBitArray(new byte[4]);
        extractorInput.peekFully(parsableBitArray.data, 0, 4);
        boolean readBit = parsableBitArray.readBit();
        int readBits = parsableBitArray.readBits(7);
        int readBits2 = parsableBitArray.readBits(24) + 4;
        if (readBits == 0) {
            flacStreamMetadataHolder.flacStreamMetadata = readStreamInfoBlock(extractorInput);
            return readBit;
        }
        FlacStreamMetadata flacStreamMetadata = flacStreamMetadataHolder.flacStreamMetadata;
        if (flacStreamMetadata == null) {
            throw new IllegalArgumentException();
        } else if (readBits == 3) {
            flacStreamMetadataHolder.flacStreamMetadata = flacStreamMetadata.copyWithSeekTable(readSeekTableMetadataBlock(extractorInput, readBits2));
            return readBit;
        } else if (readBits == 4) {
            flacStreamMetadataHolder.flacStreamMetadata = flacStreamMetadata.copyWithVorbisComments(readVorbisCommentMetadataBlock(extractorInput, readBits2));
            return readBit;
        } else if (readBits == 6) {
            ParsableByteArray parsableByteArray = new ParsableByteArray(readBits2);
            extractorInput.readFully(parsableByteArray.getData(), 0, readBits2);
            parsableByteArray.skipBytes(4);
            flacStreamMetadataHolder.flacStreamMetadata = flacStreamMetadata.copyWithPictureFrames(U.B(PictureFrame.fromPictureBlock(parsableByteArray)));
            return readBit;
        } else {
            extractorInput.skipFully(readBits2);
            return readBit;
        }
    }

    public static FlacStreamMetadata.SeekTable readSeekTableMetadataBlock(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(1);
        int readUnsignedInt24 = parsableByteArray.readUnsignedInt24();
        long position = ((long) parsableByteArray.getPosition()) + ((long) readUnsignedInt24);
        int i2 = readUnsignedInt24 / 18;
        long[] jArr = new long[i2];
        long[] jArr2 = new long[i2];
        int i7 = 0;
        while (true) {
            if (i7 >= i2) {
                break;
            }
            long readLong = parsableByteArray.readLong();
            if (readLong == -1) {
                jArr = Arrays.copyOf(jArr, i7);
                jArr2 = Arrays.copyOf(jArr2, i7);
                break;
            }
            jArr[i7] = readLong;
            jArr2[i7] = parsableByteArray.readLong();
            parsableByteArray.skipBytes(2);
            i7++;
        }
        parsableByteArray.skipBytes((int) (position - ((long) parsableByteArray.getPosition())));
        return new FlacStreamMetadata.SeekTable(jArr, jArr2);
    }

    private static FlacStreamMetadata readStreamInfoBlock(ExtractorInput extractorInput) {
        byte[] bArr = new byte[38];
        extractorInput.readFully(bArr, 0, 38);
        return new FlacStreamMetadata(bArr, 4);
    }

    public static void readStreamMarker(ExtractorInput extractorInput) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(4);
        extractorInput.readFully(parsableByteArray.getData(), 0, 4);
        if (parsableByteArray.readUnsignedInt() != 1716281667) {
            throw ParserException.createForMalformedContainer("Failed to read FLAC stream marker.", (Throwable) null);
        }
    }

    private static List<String> readVorbisCommentMetadataBlock(ExtractorInput extractorInput, int i2) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(i2);
        extractorInput.readFully(parsableByteArray.getData(), 0, i2);
        parsableByteArray.skipBytes(4);
        return Arrays.asList(VorbisUtil.readVorbisCommentHeader(parsableByteArray, false, false).comments);
    }

    private static FlacStreamMetadata.SeekTable readSeekTableMetadataBlock(ExtractorInput extractorInput, int i2) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(i2);
        extractorInput.readFully(parsableByteArray.getData(), 0, i2);
        return readSeekTableMetadataBlock(parsableByteArray);
    }
}
