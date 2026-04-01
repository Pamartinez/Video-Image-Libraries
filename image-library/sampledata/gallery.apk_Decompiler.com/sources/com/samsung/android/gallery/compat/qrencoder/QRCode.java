package com.samsung.android.gallery.compat.qrencoder;

import com.samsung.android.gallery.compat.qrencoder.common.Mode;
import com.samsung.android.gallery.compat.qrencoder.common.Version;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class QRCode {
    private ErrorCorrectionLevel ecLevel;
    private int maskPattern = -1;
    private ByteMatrix matrix;
    private Mode mode;
    private Version version;

    public static boolean isValidMaskPattern(int i2) {
        if (i2 < 0 || i2 >= 8) {
            return false;
        }
        return true;
    }

    public ByteMatrix getMatrix() {
        return this.matrix;
    }

    public void setECLevel(ErrorCorrectionLevel errorCorrectionLevel) {
        this.ecLevel = errorCorrectionLevel;
    }

    public void setMaskPattern(int i2) {
        this.maskPattern = i2;
    }

    public void setMatrix(ByteMatrix byteMatrix) {
        this.matrix = byteMatrix;
    }

    public void setMode(Mode mode2) {
        this.mode = mode2;
    }

    public void setVersion(Version version2) {
        this.version = version2;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder(200);
        sb2.append("<<\n mode: ");
        sb2.append(this.mode);
        sb2.append("\n ecLevel: ");
        sb2.append(this.ecLevel);
        sb2.append("\n version: ");
        sb2.append(this.version);
        sb2.append("\n maskPattern: ");
        sb2.append(this.maskPattern);
        if (this.matrix == null) {
            sb2.append("\n matrix: null\n");
        } else {
            sb2.append("\n matrix:\n");
            sb2.append(this.matrix);
        }
        sb2.append(">>\n");
        return sb2.toString();
    }
}
