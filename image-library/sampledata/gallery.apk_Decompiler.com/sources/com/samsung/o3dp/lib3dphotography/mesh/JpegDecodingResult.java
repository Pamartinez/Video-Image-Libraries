package com.samsung.o3dp.lib3dphotography.mesh;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class JpegDecodingResult extends C0212a {
    private final byte[] cameraAnimation;
    private final byte[] image;
    private final byte[] mesh;

    public JpegDecodingResult(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.image = bArr;
        this.mesh = bArr2;
        this.cameraAnimation = bArr3;
    }

    public byte[] cameraAnimation() {
        return this.cameraAnimation;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof JpegDecodingResult)) {
            return false;
        }
        JpegDecodingResult jpegDecodingResult = (JpegDecodingResult) obj;
        if (!Objects.equals(this.image, jpegDecodingResult.image) || !Objects.equals(this.mesh, jpegDecodingResult.mesh) || !Objects.equals(this.cameraAnimation, jpegDecodingResult.cameraAnimation)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        byte[] bArr = this.image;
        byte[] bArr2 = this.mesh;
        byte[] bArr3 = this.cameraAnimation;
        int hashCode = Objects.hashCode(bArr2);
        return Objects.hashCode(bArr3) + ((hashCode + (Objects.hashCode(bArr) * 31)) * 31);
    }

    public byte[] image() {
        return this.image;
    }

    public byte[] mesh() {
        return this.mesh;
    }

    public final String toString() {
        String[] strArr;
        Object[] objArr = {this.image, this.mesh, this.cameraAnimation};
        if ("image;mesh;cameraAnimation".length() == 0) {
            strArr = new String[0];
        } else {
            strArr = "image;mesh;cameraAnimation".split(";");
        }
        StringBuilder sb2 = new StringBuilder("JpegDecodingResult[");
        for (int i2 = 0; i2 < strArr.length; i2++) {
            sb2.append(strArr[i2]);
            sb2.append("=");
            sb2.append(objArr[i2]);
            if (i2 != strArr.length - 1) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
        }
        sb2.append("]");
        return sb2.toString();
    }
}
