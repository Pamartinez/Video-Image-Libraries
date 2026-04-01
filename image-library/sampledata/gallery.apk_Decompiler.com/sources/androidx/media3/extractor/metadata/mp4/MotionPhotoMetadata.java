package androidx.media3.extractor.metadata.mp4;

import androidx.media3.common.Metadata;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MotionPhotoMetadata implements Metadata.Entry {
    public final long photoPresentationTimestampUs;
    public final long photoSize;
    public final long photoStartPosition;
    public final long videoSize;
    public final long videoStartPosition;

    public MotionPhotoMetadata(long j2, long j3, long j8, long j10, long j11) {
        this.photoStartPosition = j2;
        this.photoSize = j3;
        this.photoPresentationTimestampUs = j8;
        this.videoStartPosition = j10;
        this.videoSize = j11;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && MotionPhotoMetadata.class == obj.getClass()) {
            MotionPhotoMetadata motionPhotoMetadata = (MotionPhotoMetadata) obj;
            if (this.photoStartPosition == motionPhotoMetadata.photoStartPosition && this.photoSize == motionPhotoMetadata.photoSize && this.photoPresentationTimestampUs == motionPhotoMetadata.photoPresentationTimestampUs && this.videoStartPosition == motionPhotoMetadata.videoStartPosition && this.videoSize == motionPhotoMetadata.videoSize) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int D = k.D(this.photoSize);
        int D4 = k.D(this.photoPresentationTimestampUs);
        int D10 = k.D(this.videoStartPosition);
        return k.D(this.videoSize) + ((D10 + ((D4 + ((D + ((k.D(this.photoStartPosition) + 527) * 31)) * 31)) * 31)) * 31);
    }

    public String toString() {
        return "Motion photo metadata: photoStartPosition=" + this.photoStartPosition + ", photoSize=" + this.photoSize + ", photoPresentationTimestampUs=" + this.photoPresentationTimestampUs + ", videoStartPosition=" + this.videoStartPosition + ", videoSize=" + this.videoSize;
    }
}
