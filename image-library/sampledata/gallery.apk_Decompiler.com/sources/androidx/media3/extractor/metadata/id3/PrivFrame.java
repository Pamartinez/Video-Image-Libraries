package androidx.media3.extractor.metadata.id3;

import java.util.Arrays;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PrivFrame extends Id3Frame {
    public final String owner;
    public final byte[] privateData;

    public PrivFrame(String str, byte[] bArr) {
        super("PRIV");
        this.owner = str;
        this.privateData = bArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && PrivFrame.class == obj.getClass()) {
            PrivFrame privFrame = (PrivFrame) obj;
            if (!Objects.equals(this.owner, privFrame.owner) || !Arrays.equals(this.privateData, privFrame.privateData)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        String str = this.owner;
        if (str != null) {
            i2 = str.hashCode();
        } else {
            i2 = 0;
        }
        return Arrays.hashCode(this.privateData) + ((527 + i2) * 31);
    }

    public String toString() {
        return this.id + ": owner=" + this.owner;
    }
}
