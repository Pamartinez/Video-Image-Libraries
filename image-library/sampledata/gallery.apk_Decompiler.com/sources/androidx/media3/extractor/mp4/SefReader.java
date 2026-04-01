package androidx.media3.extractor.mp4;

import E2.o;
import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.metadata.mp4.SlowMotionData;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class SefReader {
    private static final o ASTERISK_SPLITTER = o.a('*');
    private static final o COLON_SPLITTER = o.a(':');
    private final List<DataReference> dataReferences = new ArrayList();
    private int readerState = 0;
    private int tailLength;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DataReference {
        public final int dataType;
        public final int size;
        public final long startOffset;

        public DataReference(int i2, long j2, int i7) {
            this.dataType = i2;
            this.startOffset = j2;
            this.size = i7;
        }
    }

    private void checkForSefData(ExtractorInput extractorInput, PositionHolder positionHolder) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(8);
        extractorInput.readFully(parsableByteArray.getData(), 0, 8);
        this.tailLength = parsableByteArray.readLittleEndianInt() + 8;
        if (parsableByteArray.readInt() != 1397048916) {
            positionHolder.position = 0;
            return;
        }
        positionHolder.position = extractorInput.getPosition() - ((long) (this.tailLength - 12));
        this.readerState = 2;
    }

    private static int nameToDataType(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1711564334:
                if (str.equals("SlowMotion_Data")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1332107749:
                if (str.equals("Super_SlowMotion_Edit_Data")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1251387154:
                if (str.equals("Super_SlowMotion_Data")) {
                    c5 = 2;
                    break;
                }
                break;
            case -830665521:
                if (str.equals("Super_SlowMotion_Deflickering_On")) {
                    c5 = 3;
                    break;
                }
                break;
            case 1760745220:
                if (str.equals("Super_SlowMotion_BGM")) {
                    c5 = 4;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return 2192;
            case 1:
                return 2819;
            case 2:
                return 2816;
            case 3:
                return 2820;
            case 4:
                return 2817;
            default:
                throw ParserException.createForMalformedContainer("Invalid SEF name", (Throwable) null);
        }
    }

    private void readSdrs(ExtractorInput extractorInput, PositionHolder positionHolder) {
        long length = extractorInput.getLength();
        int i2 = this.tailLength - 20;
        ParsableByteArray parsableByteArray = new ParsableByteArray(i2);
        extractorInput.readFully(parsableByteArray.getData(), 0, i2);
        for (int i7 = 0; i7 < i2 / 12; i7++) {
            parsableByteArray.skipBytes(2);
            short readLittleEndianShort = parsableByteArray.readLittleEndianShort();
            if (readLittleEndianShort == 2192 || readLittleEndianShort == 2816 || readLittleEndianShort == 2817 || readLittleEndianShort == 2819 || readLittleEndianShort == 2820) {
                this.dataReferences.add(new DataReference(readLittleEndianShort, (length - ((long) this.tailLength)) - ((long) parsableByteArray.readLittleEndianInt()), parsableByteArray.readLittleEndianInt()));
            } else {
                parsableByteArray.skipBytes(8);
            }
        }
        if (this.dataReferences.isEmpty()) {
            positionHolder.position = 0;
            return;
        }
        this.readerState = 3;
        positionHolder.position = this.dataReferences.get(0).startOffset;
    }

    private void readSefData(ExtractorInput extractorInput, List<Metadata.Entry> list) {
        long position = extractorInput.getPosition();
        int length = (int) ((extractorInput.getLength() - extractorInput.getPosition()) - ((long) this.tailLength));
        ParsableByteArray parsableByteArray = new ParsableByteArray(length);
        extractorInput.readFully(parsableByteArray.getData(), 0, length);
        for (int i2 = 0; i2 < this.dataReferences.size(); i2++) {
            DataReference dataReference = this.dataReferences.get(i2);
            parsableByteArray.setPosition((int) (dataReference.startOffset - position));
            parsableByteArray.skipBytes(4);
            int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
            int nameToDataType = nameToDataType(parsableByteArray.readString(readLittleEndianInt));
            int i7 = dataReference.size - (readLittleEndianInt + 8);
            if (nameToDataType == 2192) {
                list.add(readSlowMotionData(parsableByteArray, i7));
            } else if (!(nameToDataType == 2816 || nameToDataType == 2817 || nameToDataType == 2819 || nameToDataType == 2820)) {
                throw new IllegalStateException();
            }
        }
    }

    private static SlowMotionData readSlowMotionData(ParsableByteArray parsableByteArray, int i2) {
        ArrayList arrayList = new ArrayList();
        List b = ASTERISK_SPLITTER.b(parsableByteArray.readString(i2));
        int i7 = 0;
        while (i7 < b.size()) {
            List b5 = COLON_SPLITTER.b((CharSequence) b.get(i7));
            if (b5.size() == 3) {
                try {
                    arrayList.add(new SlowMotionData.Segment(Long.parseLong((String) b5.get(0)), Long.parseLong((String) b5.get(1)), 1 << (Integer.parseInt((String) b5.get(2)) - 1)));
                    i7++;
                } catch (NumberFormatException e) {
                    throw ParserException.createForMalformedContainer((String) null, e);
                }
            } else {
                throw ParserException.createForMalformedContainer((String) null, (Throwable) null);
            }
        }
        return new SlowMotionData(arrayList);
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder, List<Metadata.Entry> list) {
        int i2 = this.readerState;
        long j2 = 0;
        if (i2 == 0) {
            long length = extractorInput.getLength();
            if (length != -1 && length >= 8) {
                j2 = length - 8;
            }
            positionHolder.position = j2;
            this.readerState = 1;
        } else if (i2 == 1) {
            checkForSefData(extractorInput, positionHolder);
        } else if (i2 == 2) {
            readSdrs(extractorInput, positionHolder);
        } else if (i2 == 3) {
            readSefData(extractorInput, list);
            positionHolder.position = 0;
        } else {
            throw new IllegalStateException();
        }
        return 1;
    }

    public void reset() {
        this.dataReferences.clear();
        this.readerState = 0;
    }
}
