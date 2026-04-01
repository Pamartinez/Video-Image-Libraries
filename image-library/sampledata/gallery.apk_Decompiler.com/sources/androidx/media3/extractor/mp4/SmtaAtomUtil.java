package androidx.media3.extractor.mp4;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.metadata.mp4.SmtaMetadataEntry;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SmtaAtomUtil {
    private static int getCaptureFrameRate(int i2, ParsableByteArray parsableByteArray, int i7) {
        if (i2 == 12) {
            return 240;
        }
        if (i2 == 13) {
            return 120;
        }
        if (i2 == 21 && parsableByteArray.bytesLeft() >= 8 && parsableByteArray.getPosition() + 8 <= i7) {
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt >= 12 && readInt2 == 1936877170) {
                return parsableByteArray.readUnsignedFixedPoint1616();
            }
        }
        return -2147483647;
    }

    public static Metadata parseSmta(ParsableByteArray parsableByteArray, int i2) {
        parsableByteArray.skipBytes(12);
        while (parsableByteArray.getPosition() < i2) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() != 1935766900) {
                parsableByteArray.setPosition(position + readInt);
            } else if (readInt < 16) {
                return null;
            } else {
                parsableByteArray.skipBytes(4);
                int i7 = -1;
                int i8 = 0;
                for (int i10 = 0; i10 < 2; i10++) {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    if (readUnsignedByte == 0) {
                        i7 = readUnsignedByte2;
                    } else if (readUnsignedByte == 1) {
                        i8 = readUnsignedByte2;
                    }
                }
                int captureFrameRate = getCaptureFrameRate(i7, parsableByteArray, i2);
                if (captureFrameRate == -2147483647) {
                    return null;
                }
                return new Metadata(new SmtaMetadataEntry((float) captureFrameRate, i8));
            }
        }
        return null;
    }
}
