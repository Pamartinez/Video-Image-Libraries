package com.samsung.android.motionphoto.utils.v2;

import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader;
import com.samsung.android.motionphoto.utils.v2.io.MediaByteReader;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sum.core.SLog;
import java.io.File;
import java.io.FileDescriptor;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0004\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0010\u0010\u000eJ\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001cR\u001a\u0010\u001e\u001a\u00020\u001d8\u0000X\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006#"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/ExifInfoImpl;", "Lcom/samsung/android/motionphoto/utils/v2/ExifInfo;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "mediaFile", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "Ljava/io/FileDescriptor;", "fd", "(Ljava/io/FileDescriptor;)V", "Ljava/io/File;", "file", "(Ljava/io/File;)V", "", "getWidth", "()I", "getHeight", "getRotation", "", "getDateTimeTaken", "()Ljava/lang/String;", "", "getXMP", "()[B", "", "isDirectBuffer", "Ljava/nio/ByteBuffer;", "toByteBuffer", "(Z)Ljava/nio/ByteBuffer;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "Landroidx/exifinterface/media/ExifInterface;", "exif", "Landroidx/exifinterface/media/ExifInterface;", "getExif$motionphoto_utils_v2_0_17_release", "()Landroidx/exifinterface/media/ExifInterface;", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExifInfoImpl implements ExifInfo {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private final ExifInterface exif;
    private final MediaFile mediaFile;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/ExifInfoImpl$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String tagOf = SLog.tagOf(ExifInfoImpl.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0025, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0026, code lost:
        B1.a.g(r4, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ExifInfoImpl(com.samsung.android.motionphoto.utils.v2.MediaFile r4) {
        /*
            r3 = this;
            java.lang.String r0 = "mediaFile"
            kotlin.jvm.internal.j.e(r4, r0)
            r3.<init>()
            r3.mediaFile = r4
            java.io.FileInputStream r4 = r4.newInputFileStream()
            java.nio.channels.FileChannel r0 = r4.getChannel()     // Catch:{ all -> 0x0023 }
            r1 = 0
            r0.position(r1)     // Catch:{ all -> 0x0023 }
            androidx.exifinterface.media.ExifInterface r0 = new androidx.exifinterface.media.ExifInterface     // Catch:{ all -> 0x0023 }
            r0.<init>((java.io.InputStream) r4)     // Catch:{ all -> 0x0023 }
            r1 = 0
            B1.a.g(r4, r1)
            r3.exif = r0
            return
        L_0x0023:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0025 }
        L_0x0025:
            r0 = move-exception
            B1.a.g(r4, r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.ExifInfoImpl.<init>(com.samsung.android.motionphoto.utils.v2.MediaFile):void");
    }

    public String getDateTimeTaken() {
        String attribute = this.exif.getAttribute("DateTimeOriginal");
        if (attribute == null) {
            return "";
        }
        return attribute;
    }

    public final ExifInterface getExif$motionphoto_utils_v2_0_17_release() {
        return this.exif;
    }

    public int getHeight() {
        String attribute = this.exif.getAttribute("ImageLength");
        if (attribute != null) {
            return Integer.parseInt(attribute);
        }
        return 0;
    }

    public int getRotation() {
        String attribute = this.exif.getAttribute("Orientation");
        if (attribute == null) {
            return 0;
        }
        int parseInt = Integer.parseInt(attribute);
        if (parseInt == 3) {
            return MOCRLang.KHMER;
        }
        if (parseInt == 6) {
            return 90;
        }
        if (parseInt != 8) {
            return 0;
        }
        return 270;
    }

    public int getWidth() {
        String attribute = this.exif.getAttribute("ImageWidth");
        if (attribute != null) {
            return Integer.parseInt(attribute);
        }
        return 0;
    }

    public byte[] getXMP() {
        return this.exif.getAttributeBytes("Xmp");
    }

    public ByteBuffer toByteBuffer(boolean z) {
        byte[] data;
        if (z) {
            ImageMetaReader.Box exif2 = ImageMetaReader.Companion.of(this.mediaFile).getExif();
            if (exif2 == null) {
                return null;
            }
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect((int) exif2.getLength());
            MediaByteReader byteReader = exif2.getByteReader();
            long offset = exif2.getOffset();
            long length = exif2.getLength();
            j.b(allocateDirect);
            byteReader.read(offset, length, allocateDirect);
            return allocateDirect;
        }
        ImageMetaReader.Box exif3 = ImageMetaReader.Companion.of(this.mediaFile).getExif();
        if (exif3 == null || (data = exif3.getData()) == null) {
            return null;
        }
        return ByteBuffer.wrap(data);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ExifInfoImpl(FileDescriptor fileDescriptor) {
        this(new MediaFile(fileDescriptor));
        j.e(fileDescriptor, "fd");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ExifInfoImpl(File file) {
        this(new MediaFile(file));
        j.e(file, "file");
    }
}
