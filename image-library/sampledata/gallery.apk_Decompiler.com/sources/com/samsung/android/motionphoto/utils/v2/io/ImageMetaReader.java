package com.samsung.android.motionphoto.utils.v2.io;

import L1.d;
import Sf.q;
import com.samsung.android.motionphoto.utils.v2.MediaFile;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.f;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u0000 \u00072\u00020\u0001:\u0002\u0006\u0007J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0003H&¨\u0006\b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader;", "", "getXMP", "Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader$Box;", "getExif", "getCameraDebugInfo", "Box", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ImageMetaReader {
    public static final Companion Companion = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0006\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\nJ\u0010\u0010\f\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ.\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\nR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u001c\u0010\nR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001d\u001a\u0004\b\u001e\u0010\rR\u001b\u0010$\u001a\u00020\u001f8FX\u0002¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#¨\u0006%"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader$Box;", "", "", "offset", "length", "Lcom/samsung/android/motionphoto/utils/v2/io/MediaByteReader;", "byteReader", "<init>", "(JJLcom/samsung/android/motionphoto/utils/v2/io/MediaByteReader;)V", "component1", "()J", "component2", "component3", "()Lcom/samsung/android/motionphoto/utils/v2/io/MediaByteReader;", "copy", "(JJLcom/samsung/android/motionphoto/utils/v2/io/MediaByteReader;)Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader$Box;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "J", "getOffset", "getLength", "Lcom/samsung/android/motionphoto/utils/v2/io/MediaByteReader;", "getByteReader", "", "data$delegate", "Lme/f;", "getData", "()[B", "data", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Box {
        private final MediaByteReader byteReader;
        private final f data$delegate = d.q(new q(8, this));
        private final long length;
        private final long offset;

        public Box(long j2, long j3, MediaByteReader mediaByteReader) {
            j.e(mediaByteReader, "byteReader");
            this.offset = j2;
            this.length = j3;
            this.byteReader = mediaByteReader;
        }

        public static /* synthetic */ Box copy$default(Box box, long j2, long j3, MediaByteReader mediaByteReader, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j2 = box.offset;
            }
            long j8 = j2;
            if ((i2 & 2) != 0) {
                j3 = box.length;
            }
            long j10 = j3;
            if ((i2 & 4) != 0) {
                mediaByteReader = box.byteReader;
            }
            return box.copy(j8, j10, mediaByteReader);
        }

        /* access modifiers changed from: private */
        public static final byte[] data_delegate$lambda$0(Box box) {
            return box.byteReader.read(box.offset, box.length);
        }

        public final long component1() {
            return this.offset;
        }

        public final long component2() {
            return this.length;
        }

        public final MediaByteReader component3() {
            return this.byteReader;
        }

        public final Box copy(long j2, long j3, MediaByteReader mediaByteReader) {
            j.e(mediaByteReader, "byteReader");
            return new Box(j2, j3, mediaByteReader);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Box)) {
                return false;
            }
            Box box = (Box) obj;
            if (this.offset == box.offset && this.length == box.length && j.a(this.byteReader, box.byteReader)) {
                return true;
            }
            return false;
        }

        public final MediaByteReader getByteReader() {
            return this.byteReader;
        }

        public final byte[] getData() {
            return (byte[]) this.data$delegate.getValue();
        }

        public final long getLength() {
            return this.length;
        }

        public final long getOffset() {
            return this.offset;
        }

        public int hashCode() {
            return this.byteReader.hashCode() + C0212a.c(Long.hashCode(this.offset) * 31, 31, this.length);
        }

        public String toString() {
            long j2 = this.offset;
            long j3 = this.length;
            MediaByteReader mediaByteReader = this.byteReader;
            StringBuilder j8 = N2.j.j(j2, "Box(offset=", ", length=");
            j8.append(j3);
            j8.append(", byteReader=");
            j8.append(mediaByteReader);
            j8.append(")");
            return j8.toString();
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader$Companion;", "", "<init>", "()V", "of", "Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader;", "mediaFile", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            static {
                /*
                    com.samsung.android.motionphoto.utils.v2.MimeType[] r0 = com.samsung.android.motionphoto.utils.v2.MimeType.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.samsung.android.motionphoto.utils.v2.MimeType r1 = com.samsung.android.motionphoto.utils.v2.MimeType.IMAGE_JPEG     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.samsung.android.motionphoto.utils.v2.MimeType r1 = com.samsung.android.motionphoto.utils.v2.MimeType.IMAGE_HEIC     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader.Companion.WhenMappings.<clinit>():void");
            }
        }

        private Companion() {
        }

        public final ImageMetaReader of(MediaFile mediaFile) {
            j.e(mediaFile, "mediaFile");
            int i2 = WhenMappings.$EnumSwitchMapping$0[mediaFile.getMimeType().ordinal()];
            if (i2 == 1) {
                return new JPEGMetaReader(mediaFile);
            }
            if (i2 == 2) {
                return new HEIFMetaReader(mediaFile);
            }
            throw new UnsupportedOperationException();
        }
    }

    Box getCameraDebugInfo();

    Box getExif();

    Box getXMP();
}
