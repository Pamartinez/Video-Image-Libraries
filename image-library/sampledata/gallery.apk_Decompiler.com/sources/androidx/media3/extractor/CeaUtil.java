package androidx.media3.extractor;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.TrackOutput;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CeaUtil {
    public static void consume(long j2, ParsableByteArray parsableByteArray, TrackOutput[] trackOutputArr) {
        int i2;
        boolean z;
        while (true) {
            boolean z3 = true;
            if (parsableByteArray.bytesLeft() > 1) {
                int readNon255TerminatedValue = readNon255TerminatedValue(parsableByteArray);
                int readNon255TerminatedValue2 = readNon255TerminatedValue(parsableByteArray);
                int position = parsableByteArray.getPosition() + readNon255TerminatedValue2;
                if (readNon255TerminatedValue2 == -1 || readNon255TerminatedValue2 > parsableByteArray.bytesLeft()) {
                    Log.w("CeaUtil", "Skipping remainder of malformed SEI NAL unit.");
                    position = parsableByteArray.limit();
                } else if (readNon255TerminatedValue == 4 && readNon255TerminatedValue2 >= 8) {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    int readUnsignedShort = parsableByteArray.readUnsignedShort();
                    if (readUnsignedShort == 49) {
                        i2 = parsableByteArray.readInt();
                    } else {
                        i2 = 0;
                    }
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    if (readUnsignedShort == 47) {
                        parsableByteArray.skipBytes(1);
                    }
                    if (readUnsignedByte == 181 && ((readUnsignedShort == 49 || readUnsignedShort == 47) && readUnsignedByte2 == 3)) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (readUnsignedShort == 49) {
                        if (i2 != 1195456820) {
                            z3 = false;
                        }
                        z &= z3;
                    }
                    if (z) {
                        consumeCcData(j2, parsableByteArray, trackOutputArr);
                    }
                }
                parsableByteArray.setPosition(position);
            } else {
                return;
            }
        }
    }

    public static void consumeCcData(long j2, ParsableByteArray parsableByteArray, TrackOutput[] trackOutputArr) {
        boolean z;
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        if ((readUnsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(1);
            int i2 = (readUnsignedByte & 31) * 3;
            int position = parsableByteArray.getPosition();
            for (TrackOutput trackOutput : trackOutputArr) {
                parsableByteArray.setPosition(position);
                trackOutput.sampleData(parsableByteArray, i2);
                if (j2 != -9223372036854775807L) {
                    z = true;
                } else {
                    z = false;
                }
                Assertions.checkState(z);
                trackOutput.sampleMetadata(j2, 1, i2, 0, (TrackOutput.CryptoData) null);
            }
        }
    }

    private static int readNon255TerminatedValue(ParsableByteArray parsableByteArray) {
        int i2 = 0;
        while (parsableByteArray.bytesLeft() != 0) {
            int readUnsignedByte = parsableByteArray.readUnsignedByte();
            i2 += readUnsignedByte;
            if (readUnsignedByte != 255) {
                return i2;
            }
        }
        return -1;
    }
}
