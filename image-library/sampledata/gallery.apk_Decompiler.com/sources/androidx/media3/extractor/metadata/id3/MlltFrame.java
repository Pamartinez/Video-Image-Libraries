package androidx.media3.extractor.metadata.id3;

import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MlltFrame extends Id3Frame {
    public final int bytesBetweenReference;
    public final int[] bytesDeviations;
    public final int millisecondsBetweenReference;
    public final int[] millisecondsDeviations;
    public final int mpegFramesBetweenReference;

    public MlltFrame(int i2, int i7, int i8, int[] iArr, int[] iArr2) {
        super("MLLT");
        this.mpegFramesBetweenReference = i2;
        this.bytesBetweenReference = i7;
        this.millisecondsBetweenReference = i8;
        this.bytesDeviations = iArr;
        this.millisecondsDeviations = iArr2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && MlltFrame.class == obj.getClass()) {
            MlltFrame mlltFrame = (MlltFrame) obj;
            if (this.mpegFramesBetweenReference == mlltFrame.mpegFramesBetweenReference && this.bytesBetweenReference == mlltFrame.bytesBetweenReference && this.millisecondsBetweenReference == mlltFrame.millisecondsBetweenReference && Arrays.equals(this.bytesDeviations, mlltFrame.bytesDeviations) && Arrays.equals(this.millisecondsDeviations, mlltFrame.millisecondsDeviations)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = Arrays.hashCode(this.bytesDeviations);
        return Arrays.hashCode(this.millisecondsDeviations) + ((hashCode + ((((((527 + this.mpegFramesBetweenReference) * 31) + this.bytesBetweenReference) * 31) + this.millisecondsBetweenReference) * 31)) * 31);
    }
}
