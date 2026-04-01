package androidx.media3.common;

import androidx.media3.common.util.Util;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ColorInfo {
    private static final String FIELD_CHROMA_BITDEPTH = Util.intToStringMaxRadix(5);
    private static final String FIELD_COLOR_RANGE = Util.intToStringMaxRadix(1);
    private static final String FIELD_COLOR_SPACE = Util.intToStringMaxRadix(0);
    private static final String FIELD_COLOR_TRANSFER = Util.intToStringMaxRadix(2);
    private static final String FIELD_HDR_STATIC_INFO = Util.intToStringMaxRadix(3);
    private static final String FIELD_LUMA_BITDEPTH = Util.intToStringMaxRadix(4);
    public static final ColorInfo SDR_BT709_LIMITED = new Builder().setColorSpace(1).setColorRange(2).setColorTransfer(3).build();
    public static final ColorInfo SRGB_BT709_FULL = new Builder().setColorSpace(1).setColorRange(1).setColorTransfer(2).build();
    public final int chromaBitdepth;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    private int hashCode;
    public final byte[] hdrStaticInfo;
    public final int lumaBitdepth;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private int chromaBitdepth;
        private int colorRange;
        private int colorSpace;
        private int colorTransfer;
        private byte[] hdrStaticInfo;
        private int lumaBitdepth;

        public ColorInfo build() {
            return new ColorInfo(this.colorSpace, this.colorRange, this.colorTransfer, this.hdrStaticInfo, this.lumaBitdepth, this.chromaBitdepth);
        }

        public Builder setChromaBitdepth(int i2) {
            this.chromaBitdepth = i2;
            return this;
        }

        public Builder setColorRange(int i2) {
            this.colorRange = i2;
            return this;
        }

        public Builder setColorSpace(int i2) {
            this.colorSpace = i2;
            return this;
        }

        public Builder setColorTransfer(int i2) {
            this.colorTransfer = i2;
            return this;
        }

        public Builder setHdrStaticInfo(byte[] bArr) {
            this.hdrStaticInfo = bArr;
            return this;
        }

        public Builder setLumaBitdepth(int i2) {
            this.lumaBitdepth = i2;
            return this;
        }

        public Builder() {
            this.colorSpace = -1;
            this.colorRange = -1;
            this.colorTransfer = -1;
            this.lumaBitdepth = -1;
            this.chromaBitdepth = -1;
        }

        private Builder(ColorInfo colorInfo) {
            this.colorSpace = colorInfo.colorSpace;
            this.colorRange = colorInfo.colorRange;
            this.colorTransfer = colorInfo.colorTransfer;
            this.hdrStaticInfo = colorInfo.hdrStaticInfo;
            this.lumaBitdepth = colorInfo.lumaBitdepth;
            this.chromaBitdepth = colorInfo.chromaBitdepth;
        }
    }

    private static String chromaBitdepthToString(int i2) {
        if (i2 == -1) {
            return "NA";
        }
        return i2 + "bit Chroma";
    }

    private static String colorRangeToString(int i2) {
        if (i2 == -1) {
            return "Unset color range";
        }
        if (i2 == 1) {
            return "Full range";
        }
        if (i2 != 2) {
            return C0086a.i(i2, "Undefined color range ");
        }
        return "Limited range";
    }

    private static String colorSpaceToString(int i2) {
        if (i2 == -1) {
            return "Unset color space";
        }
        if (i2 == 6) {
            return "BT2020";
        }
        if (i2 == 1) {
            return "BT709";
        }
        if (i2 != 2) {
            return C0086a.i(i2, "Undefined color space ");
        }
        return "BT601";
    }

    private static String colorTransferToString(int i2) {
        if (i2 == -1) {
            return "Unset color transfer";
        }
        if (i2 == 10) {
            return "Gamma 2.2";
        }
        if (i2 == 1) {
            return "Linear";
        }
        if (i2 == 2) {
            return "sRGB";
        }
        if (i2 == 3) {
            return "SDR SMPTE 170M";
        }
        if (i2 == 6) {
            return "ST2084 PQ";
        }
        if (i2 != 7) {
            return C0086a.i(i2, "Undefined color transfer ");
        }
        return "HLG";
    }

    public static boolean isTransferHdr(ColorInfo colorInfo) {
        if (colorInfo == null) {
            return false;
        }
        int i2 = colorInfo.colorTransfer;
        if (i2 == 7 || i2 == 6) {
            return true;
        }
        return false;
    }

    public static int isoColorPrimariesToColorSpace(int i2) {
        if (i2 == 1) {
            return 1;
        }
        if (i2 == 9) {
            return 6;
        }
        if (i2 == 4 || i2 == 5 || i2 == 6 || i2 == 7) {
            return 2;
        }
        return -1;
    }

    public static int isoTransferCharacteristicsToColorTransfer(int i2) {
        if (i2 == 1) {
            return 3;
        }
        if (i2 == 4) {
            return 10;
        }
        if (i2 == 13) {
            return 2;
        }
        if (i2 == 16) {
            return 6;
        }
        if (i2 == 18) {
            return 7;
        }
        if (i2 == 6 || i2 == 7) {
            return 3;
        }
        return -1;
    }

    private static String lumaBitdepthToString(int i2) {
        if (i2 == -1) {
            return "NA";
        }
        return i2 + "bit Luma";
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && ColorInfo.class == obj.getClass()) {
            ColorInfo colorInfo = (ColorInfo) obj;
            if (this.colorSpace == colorInfo.colorSpace && this.colorRange == colorInfo.colorRange && this.colorTransfer == colorInfo.colorTransfer && Arrays.equals(this.hdrStaticInfo, colorInfo.hdrStaticInfo) && this.lumaBitdepth == colorInfo.lumaBitdepth && this.chromaBitdepth == colorInfo.chromaBitdepth) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = ((((Arrays.hashCode(this.hdrStaticInfo) + ((((((527 + this.colorSpace) * 31) + this.colorRange) * 31) + this.colorTransfer) * 31)) * 31) + this.lumaBitdepth) * 31) + this.chromaBitdepth;
        }
        return this.hashCode;
    }

    public boolean isBitdepthValid() {
        if (this.lumaBitdepth == -1 || this.chromaBitdepth == -1) {
            return false;
        }
        return true;
    }

    public boolean isDataSpaceValid() {
        if (this.colorSpace == -1 || this.colorRange == -1 || this.colorTransfer == -1) {
            return false;
        }
        return true;
    }

    public boolean isValid() {
        if (isBitdepthValid() || isDataSpaceValid()) {
            return true;
        }
        return false;
    }

    public String toLogString() {
        String str;
        String str2;
        if (isDataSpaceValid()) {
            str = Util.formatInvariant("%s/%s/%s", colorSpaceToString(this.colorSpace), colorRangeToString(this.colorRange), colorTransferToString(this.colorTransfer));
        } else {
            str = "NA/NA/NA";
        }
        if (isBitdepthValid()) {
            str2 = this.lumaBitdepth + "/" + this.chromaBitdepth;
        } else {
            str2 = "NA/NA";
        }
        return C0212a.B(str, "/", str2);
    }

    public String toString() {
        boolean z;
        StringBuilder sb2 = new StringBuilder("ColorInfo(");
        sb2.append(colorSpaceToString(this.colorSpace));
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(colorRangeToString(this.colorRange));
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(colorTransferToString(this.colorTransfer));
        sb2.append(ArcCommonLog.TAG_COMMA);
        if (this.hdrStaticInfo != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(lumaBitdepthToString(this.lumaBitdepth));
        sb2.append(ArcCommonLog.TAG_COMMA);
        return C0212a.p(sb2, chromaBitdepthToString(this.chromaBitdepth), ")");
    }

    private ColorInfo(int i2, int i7, int i8, byte[] bArr, int i10, int i11) {
        this.colorSpace = i2;
        this.colorRange = i7;
        this.colorTransfer = i8;
        this.hdrStaticInfo = bArr;
        this.lumaBitdepth = i10;
        this.chromaBitdepth = i11;
    }
}
