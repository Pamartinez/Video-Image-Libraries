package androidx.media3.extractor.metadata.id3;

import java.util.Arrays;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GeobFrame extends Id3Frame {
    public final byte[] data;
    public final String description;
    public final String filename;
    public final String mimeType;

    public GeobFrame(String str, String str2, String str3, byte[] bArr) {
        super("GEOB");
        this.mimeType = str;
        this.filename = str2;
        this.description = str3;
        this.data = bArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && GeobFrame.class == obj.getClass()) {
            GeobFrame geobFrame = (GeobFrame) obj;
            if (!Objects.equals(this.mimeType, geobFrame.mimeType) || !Objects.equals(this.filename, geobFrame.filename) || !Objects.equals(this.description, geobFrame.description) || !Arrays.equals(this.data, geobFrame.data)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        int i7;
        String str = this.mimeType;
        int i8 = 0;
        if (str != null) {
            i2 = str.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (527 + i2) * 31;
        String str2 = this.filename;
        if (str2 != null) {
            i7 = str2.hashCode();
        } else {
            i7 = 0;
        }
        int i11 = (i10 + i7) * 31;
        String str3 = this.description;
        if (str3 != null) {
            i8 = str3.hashCode();
        }
        return Arrays.hashCode(this.data) + ((i11 + i8) * 31);
    }

    public String toString() {
        return this.id + ": mimeType=" + this.mimeType + ", filename=" + this.filename + ", description=" + this.description;
    }
}
