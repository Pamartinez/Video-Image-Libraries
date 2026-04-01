package androidx.media3.container;

import androidx.media3.common.util.ParsableByteArray;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DolbyVisionConfig {
    public final String codecs;
    public final int level;
    public final int profile;

    private DolbyVisionConfig(int i2, int i7, String str) {
        this.profile = i2;
        this.level = i7;
        this.codecs = str;
    }

    public static DolbyVisionConfig parse(ParsableByteArray parsableByteArray) {
        String str;
        String str2;
        parsableByteArray.skipBytes(2);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = readUnsignedByte >> 1;
        int readUnsignedByte2 = ((parsableByteArray.readUnsignedByte() >> 3) & 31) | ((readUnsignedByte & 1) << 5);
        if (i2 == 4 || i2 == 5 || i2 == 7 || i2 == 8) {
            str = "dvhe";
        } else if (i2 == 9) {
            str = "dvav";
        } else if (i2 != 10) {
            return null;
        } else {
            str = "dav1";
        }
        StringBuilder s = C0212a.s(str);
        String str3 = ".";
        if (i2 < 10) {
            str2 = ".0";
        } else {
            str2 = str3;
        }
        s.append(str2);
        s.append(i2);
        if (readUnsignedByte2 < 10) {
            str3 = ".0";
        }
        s.append(str3);
        s.append(readUnsignedByte2);
        return new DolbyVisionConfig(i2, readUnsignedByte2, s.toString());
    }
}
