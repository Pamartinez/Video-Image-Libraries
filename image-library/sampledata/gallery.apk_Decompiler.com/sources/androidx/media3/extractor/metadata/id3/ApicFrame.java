package androidx.media3.extractor.metadata.id3;

import androidx.media3.common.MediaMetadata;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ApicFrame extends Id3Frame {
    public final String description;
    public final String mimeType;
    public final byte[] pictureData;
    public final int pictureType;

    public ApicFrame(String str, String str2, int i2, byte[] bArr) {
        super("APIC");
        this.mimeType = str;
        this.description = str2;
        this.pictureType = i2;
        this.pictureData = bArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && ApicFrame.class == obj.getClass()) {
            ApicFrame apicFrame = (ApicFrame) obj;
            if (this.pictureType != apicFrame.pictureType || !Objects.equals(this.mimeType, apicFrame.mimeType) || !Objects.equals(this.description, apicFrame.description) || !Arrays.equals(this.pictureData, apicFrame.pictureData)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        int i7 = (527 + this.pictureType) * 31;
        String str = this.mimeType;
        int i8 = 0;
        if (str != null) {
            i2 = str.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i7 + i2) * 31;
        String str2 = this.description;
        if (str2 != null) {
            i8 = str2.hashCode();
        }
        return Arrays.hashCode(this.pictureData) + ((i10 + i8) * 31);
    }

    public void populateMediaMetadata(MediaMetadata.Builder builder) {
        builder.maybeSetArtworkData(this.pictureData, this.pictureType);
    }

    public String toString() {
        return this.id + ": mimeType=" + this.mimeType + ", description=" + this.description;
    }
}
