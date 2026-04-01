package androidx.media3.container;

import E2.i;
import L1.d;
import N2.j;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MdtaMetadataEntry implements Metadata.Entry {
    public final String key;
    public final int localeIndicator;
    public final int typeIndicator;
    public final byte[] value;

    public MdtaMetadataEntry(String str, byte[] bArr, int i2, int i7) {
        validateData(str, bArr, i7);
        this.key = str;
        this.value = bArr;
        this.localeIndicator = i2;
        this.typeIndicator = i7;
    }

    private static String getFormattedValueForAuxiliaryTracksMap(List<Integer> list) {
        StringBuilder s = C0212a.s("track types = ");
        new i(String.valueOf(',')).a(s, list.iterator());
        return s.toString();
    }

    private static void validateData(String str, byte[] bArr, int i2) {
        byte b;
        str.getClass();
        boolean z = false;
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1949883051:
                if (str.equals("com.android.capture.fps")) {
                    c5 = 0;
                    break;
                }
                break;
            case -269399509:
                if (str.equals("auxiliary.tracks.interleaved")) {
                    c5 = 1;
                    break;
                }
                break;
            case 1011693540:
                if (str.equals("auxiliary.tracks.length")) {
                    c5 = 2;
                    break;
                }
                break;
            case 1098277265:
                if (str.equals("auxiliary.tracks.offset")) {
                    c5 = 3;
                    break;
                }
                break;
            case 2002123038:
                if (str.equals("auxiliary.tracks.map")) {
                    c5 = 4;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                if (i2 == 23 && bArr.length == 4) {
                    z = true;
                }
                Assertions.checkArgument(z);
                return;
            case 1:
                if (i2 == 75 && bArr.length == 1 && ((b = bArr[0]) == 0 || b == 1)) {
                    z = true;
                }
                Assertions.checkArgument(z);
                return;
            case 2:
            case 3:
                if (i2 == 78 && bArr.length == 8) {
                    z = true;
                }
                Assertions.checkArgument(z);
                return;
            case 4:
                if (i2 == 0) {
                    z = true;
                }
                Assertions.checkArgument(z);
                return;
            default:
                return;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && MdtaMetadataEntry.class == obj.getClass()) {
            MdtaMetadataEntry mdtaMetadataEntry = (MdtaMetadataEntry) obj;
            if (!this.key.equals(mdtaMetadataEntry.key) || !Arrays.equals(this.value, mdtaMetadataEntry.value) || this.localeIndicator != mdtaMetadataEntry.localeIndicator || this.typeIndicator != mdtaMetadataEntry.typeIndicator) {
                return false;
            }
            return true;
        }
        return false;
    }

    public List<Integer> getAuxiliaryTrackTypesFromMap() {
        Assertions.checkState(this.key.equals("auxiliary.tracks.map"), "Metadata is not an auxiliary tracks map");
        byte b = this.value[1];
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < b; i2++) {
            arrayList.add(Integer.valueOf(this.value[i2 + 2]));
        }
        return arrayList;
    }

    public int hashCode() {
        return ((((Arrays.hashCode(this.value) + C0212a.d(527, 31, this.key)) * 31) + this.localeIndicator) * 31) + this.typeIndicator;
    }

    public String toString() {
        String str;
        boolean z;
        boolean z3;
        int i2 = this.typeIndicator;
        if (i2 != 0) {
            if (i2 == 1) {
                str = Util.fromUtf8Bytes(this.value);
            } else if (i2 == 23) {
                byte[] bArr = this.value;
                if (bArr.length >= 4) {
                    z = true;
                } else {
                    z = false;
                }
                int length = bArr.length;
                if (z) {
                    str = String.valueOf(Float.intBitsToFloat(C0246a.V(bArr[0], bArr[1], bArr[2], bArr[3])));
                } else {
                    throw new IllegalArgumentException(d.r("array too small: %s < %s", Integer.valueOf(length), 4));
                }
            } else if (i2 == 67) {
                byte[] bArr2 = this.value;
                if (bArr2.length >= 4) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                int length2 = bArr2.length;
                if (z3) {
                    str = String.valueOf(C0246a.V(bArr2[0], bArr2[1], bArr2[2], bArr2[3]));
                } else {
                    throw new IllegalArgumentException(d.r("array too small: %s < %s", Integer.valueOf(length2), 4));
                }
            } else if (i2 == 75) {
                str = String.valueOf(Byte.toUnsignedInt(this.value[0]));
            } else if (i2 == 78) {
                str = String.valueOf(new ParsableByteArray(this.value).readUnsignedLongToLong());
            }
            return j.f(new StringBuilder("mdta: key="), this.key, ", value=", str);
        } else if (this.key.equals("auxiliary.tracks.map")) {
            str = getFormattedValueForAuxiliaryTracksMap(getAuxiliaryTrackTypesFromMap());
            return j.f(new StringBuilder("mdta: key="), this.key, ", value=", str);
        }
        str = Util.toHexString(this.value);
        return j.f(new StringBuilder("mdta: key="), this.key, ", value=", str);
    }
}
