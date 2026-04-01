package com.samsung.android.motionphoto.utils.v2.io;

import com.samsung.android.motionphoto.utils.v2.MediaFile;
import com.samsung.android.motionphoto.utils.v2.MimeType;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoInfoImpl;
import i.C0212a;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\bf\u0018\u0000 \u00052\u00020\u0001:\u0002\u0004\u0005J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/VideoMetaReader;", "", "readSampleCount", "", "Box", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface VideoMetaReader {
    public static final Companion Companion = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\bHÆ\u0003J3\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/VideoMetaReader$Box;", "", "type", "", "offset", "", "length", "data", "", "<init>", "(Ljava/lang/String;JJ[B)V", "getType", "()Ljava/lang/String;", "getOffset", "()J", "getLength", "getData", "()[B", "setData", "([B)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Box {
        private byte[] data;
        private final long length;
        private final long offset;
        private final String type;

        public Box(String str, long j2, long j3, byte[] bArr) {
            j.e(str, "type");
            this.type = str;
            this.offset = j2;
            this.length = j3;
            this.data = bArr;
        }

        public static /* synthetic */ Box copy$default(Box box, String str, long j2, long j3, byte[] bArr, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = box.type;
            }
            if ((i2 & 2) != 0) {
                j2 = box.offset;
            }
            if ((i2 & 4) != 0) {
                j3 = box.length;
            }
            if ((i2 & 8) != 0) {
                bArr = box.data;
            }
            byte[] bArr2 = bArr;
            long j8 = j3;
            return box.copy(str, j2, j8, bArr2);
        }

        public final String component1() {
            return this.type;
        }

        public final long component2() {
            return this.offset;
        }

        public final long component3() {
            return this.length;
        }

        public final byte[] component4() {
            return this.data;
        }

        public final Box copy(String str, long j2, long j3, byte[] bArr) {
            j.e(str, "type");
            return new Box(str, j2, j3, bArr);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Box)) {
                return false;
            }
            Box box = (Box) obj;
            if (j.a(this.type, box.type) && this.offset == box.offset && this.length == box.length && j.a(this.data, box.data)) {
                return true;
            }
            return false;
        }

        public final byte[] getData() {
            return this.data;
        }

        public final long getLength() {
            return this.length;
        }

        public final long getOffset() {
            return this.offset;
        }

        public final String getType() {
            return this.type;
        }

        public int hashCode() {
            int i2;
            int c5 = C0212a.c(C0212a.c(this.type.hashCode() * 31, 31, this.offset), 31, this.length);
            byte[] bArr = this.data;
            if (bArr == null) {
                i2 = 0;
            } else {
                i2 = Arrays.hashCode(bArr);
            }
            return c5 + i2;
        }

        public final void setData(byte[] bArr) {
            this.data = bArr;
        }

        public String toString() {
            String str = this.type;
            long j2 = this.offset;
            long j3 = this.length;
            String arrays = Arrays.toString(this.data);
            StringBuilder sb2 = new StringBuilder("Box(type=");
            sb2.append(str);
            sb2.append(", offset=");
            sb2.append(j2);
            sb2.append(", length=");
            sb2.append(j3);
            sb2.append(", data=");
            return C0212a.p(sb2, arrays, ")");
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Box(String str, long j2, long j3, byte[] bArr, int i2, e eVar) {
            this(str, j2, j3, (i2 & 8) != 0 ? null : bArr);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/VideoMetaReader$Companion;", "", "<init>", "()V", "of", "Lcom/samsung/android/motionphoto/utils/v2/io/VideoMetaReader;", "mediaFile", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final VideoMetaReader of(MediaFile mediaFile) {
            Object obj;
            j.e(mediaFile, "mediaFile");
            if (mediaFile.getMimeType().isVideo()) {
                obj = mediaFile.getMimeType();
            } else if (mediaFile.getMimeType().isImage()) {
                MotionPhotoInfoImpl motionPhotoInfoImpl = new MotionPhotoInfoImpl(mediaFile);
                if (motionPhotoInfoImpl.isValid()) {
                    mediaFile.getMimeTypeOfMediaAt(motionPhotoInfoImpl.getVideoPosition());
                    obj = x.f4917a;
                } else {
                    obj = MimeType.NONE;
                }
            } else {
                throw new UnsupportedOperationException();
            }
            if (obj == MimeType.VIDEO_MP4) {
                return new MP4MetaReader(mediaFile);
            }
            if (obj == MimeType.VIDEO_MOV) {
                throw new UnsupportedOperationException("not implemented yet");
            }
            String path = mediaFile.path();
            throw new UnsupportedOperationException("mime: " + obj + ", path=" + path);
        }
    }

    long readSampleCount();
}
