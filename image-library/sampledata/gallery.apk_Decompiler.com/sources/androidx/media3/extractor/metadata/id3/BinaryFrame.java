package androidx.media3.extractor.metadata.id3;

import i.C0212a;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BinaryFrame extends Id3Frame {
    public final byte[] data;

    public BinaryFrame(String str, byte[] bArr) {
        super(str);
        this.data = bArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && BinaryFrame.class == obj.getClass()) {
            BinaryFrame binaryFrame = (BinaryFrame) obj;
            if (!this.id.equals(binaryFrame.id) || !Arrays.equals(this.data, binaryFrame.data)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.data) + C0212a.d(527, 31, this.id);
    }
}
