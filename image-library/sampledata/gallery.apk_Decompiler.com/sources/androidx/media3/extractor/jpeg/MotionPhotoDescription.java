package androidx.media3.extractor.jpeg;

import androidx.media3.extractor.metadata.mp4.MotionPhotoMetadata;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class MotionPhotoDescription {
    public final List<ContainerItem> items;
    public final long photoPresentationTimestampUs;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ContainerItem {
        public final long length;
        public final String mime;
        public final long padding;
        public final String semantic;

        public ContainerItem(String str, String str2, long j2, long j3) {
            this.mime = str;
            this.semantic = str2;
            this.length = j2;
            this.padding = j3;
        }
    }

    public MotionPhotoDescription(long j2, List<ContainerItem> list) {
        this.photoPresentationTimestampUs = j2;
        this.items = list;
    }

    public MotionPhotoMetadata getMotionPhotoMetadata(long j2) {
        long j3;
        if (this.items.size() < 2) {
            return null;
        }
        long j8 = j2;
        long j10 = -1;
        long j11 = -1;
        long j12 = -1;
        long j13 = -1;
        boolean z = false;
        for (int size = this.items.size() - 1; size >= 0; size--) {
            ContainerItem containerItem = this.items.get(size);
            boolean equals = Encode.ContentType.VIDEO_MP4.equals(containerItem.mime) | z;
            if (size == 0) {
                j8 -= containerItem.padding;
                j3 = 0;
            } else {
                j3 = j8 - containerItem.length;
            }
            long j14 = j3;
            long j15 = j8;
            j8 = j14;
            if (!equals || j8 == j15) {
                z = equals;
            } else {
                j13 = j15 - j8;
                j12 = j8;
                z = false;
            }
            if (size == 0) {
                j10 = j8;
                j11 = j15;
            }
        }
        if (j12 == -1 || j13 == -1 || j10 == -1 || j11 == -1) {
            return null;
        }
        return new MotionPhotoMetadata(j10, j11, this.photoPresentationTimestampUs, j12, j13);
    }
}
