package com.samsung.android.motionphoto.utils.v2.io;

import A.a;
import Ae.b;
import Ae.c;
import Bd.C0725a;
import Ke.v0;
import Vf.C0884v;
import android.os.Build;
import android.os.SemSystemProperties;
import bd.h;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.motionphoto.utils.ex.HeicXMPComposer;
import com.samsung.android.motionphoto.utils.v2.CommonsKt;
import com.samsung.android.motionphoto.utils.v2.ExifInfo;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.motionphoto.utils.v2.MediaFile;
import com.samsung.android.motionphoto.utils.v2.XMPInfo;
import com.samsung.android.motionphoto.utils.v2.XMPInfoImpl;
import com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader;
import com.samsung.android.sum.core.SLog;
import java.io.FileDescriptor;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.t;
import me.x;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 32\u00020\u00012\u00020\u0002:\u00013B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H ¢\u0006\u0004\b\n\u0010\u000bJ \u0010\u000f\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\fH ¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\t¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\fH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\tH\u0016¢\u0006\u0004\b\u001d\u0010\u0018J\u0017\u0010 \u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b \u0010!J\u0017\u0010$\u001a\u00020\t2\u0006\u0010#\u001a\u00020\"H\u0016¢\u0006\u0004\b$\u0010%J\u0017\u0010&\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020\tH\u0016¢\u0006\u0004\b(\u0010\u0018R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010)R&\u0010+\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000e0*8\u0002X\u0004¢\u0006\u0006\n\u0004\b+\u0010,R \u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t0-8\u0002X\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u0016\u00101\u001a\u0002008\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b1\u00102¨\u00064"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/HEIFMetaWriter;", "Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaBase;", "Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaWriter;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "mediaFile", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "Ljava/io/FileDescriptor;", "fd", "Lme/x;", "nRemoveXMPOnHEIF", "(Ljava/io/FileDescriptor;)V", "", "xmpSize", "", "nReserveXMPOnHEIF", "(Ljava/io/FileDescriptor;I)J", "Ljava/nio/channels/FileChannel;", "fc", "", "data", "appendFreeCdif", "(Ljava/nio/channels/FileChannel;[B)V", "load", "()V", "size", "Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader$Box;", "reserveXMP", "(I)Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader$Box;", "removeXMP", "Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;", "xmpInfo", "writeXMP", "(Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;)V", "Lcom/samsung/android/motionphoto/utils/v2/ExifInfo;", "exifInfo", "writeExif", "(Lcom/samsung/android/motionphoto/utils/v2/ExifInfo;)V", "writeCameraDebugInfo", "([B)V", "removeCameraDebugInfo", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "Lkotlin/Function2;", "reserveXMPOnHEIF", "LAe/c;", "Lkotlin/Function1;", "removeXMPOnHEIF", "LAe/b;", "Lcom/samsung/android/motionphoto/utils/v2/io/HEIFMetaReader;", "reader", "Lcom/samsung/android/motionphoto/utils/v2/io/HEIFMetaReader;", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class HEIFMetaWriter extends ImageMetaBase implements ImageMetaWriter {
    public static final Companion Companion;
    private static final int ONEUI_8_0_SEP = 170000;
    private static final String TAG;
    private final MediaFile mediaFile;
    private HEIFMetaReader reader;
    private final b removeXMPOnHEIF;
    private final c reserveXMPOnHEIF;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/HEIFMetaWriter$Companion;", "", "<init>", "()V", "TAG", "", "ONEUI_8_0_SEP", "", "useNewApi", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final boolean useNewApi() {
            boolean z;
            if (CommonsKt.getSEPVersion() > HEIFMetaWriter.ONEUI_8_0_SEP) {
                z = true;
            } else {
                z = false;
            }
            return SemSystemProperties.getBoolean("secmm.mputils.use-new-api", z);
        }

        private Companion() {
        }
    }

    static {
        Companion companion = new Companion((e) null);
        Companion = companion;
        String tagOf = SLog.tagOf(HEIFMetaWriter.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
        SLog.i("SUM", a.d(CommonsKt.getSEPVersion(), Build.VERSION.SEM_PLATFORM_INT, "sep version: ", "[SEM_PLATFORM_INT=", "]"));
        if (companion.useNewApi()) {
            SLog.i(tagOf, "load new api");
            CommonsKt.loadALibrary("motionphoto_utils_jni.media.samsung");
            return;
        }
        SLog.i(tagOf, "load legacy api");
        CommonsKt.loadALibrary("apex_motionphoto_utils_jni.media.samsung");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HEIFMetaWriter(MediaFile mediaFile2) {
        super(mediaFile2);
        j.e(mediaFile2, "mediaFile");
        this.mediaFile = mediaFile2;
        this.reader = new HEIFMetaReader(mediaFile2);
        if (Companion.useNewApi()) {
            this.reserveXMPOnHEIF = new c(this) {
                public final Long invoke(FileDescriptor fileDescriptor, int i2) {
                    j.e(fileDescriptor, "p0");
                    return Long.valueOf(((HEIFMetaWriter) this.receiver).nReserveXMPOnHEIF(fileDescriptor, i2));
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    return invoke((FileDescriptor) obj, ((Number) obj2).intValue());
                }
            };
            this.removeXMPOnHEIF = new b(this) {
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((FileDescriptor) obj);
                    return x.f4917a;
                }

                public final void invoke(FileDescriptor fileDescriptor) {
                    j.e(fileDescriptor, "p0");
                    ((HEIFMetaWriter) this.receiver).nRemoveXMPOnHEIF(fileDescriptor);
                }
            };
        } else {
            this.reserveXMPOnHEIF = new C0884v(7);
            this.removeXMPOnHEIF = new C0725a(25);
        }
        load();
    }

    /* access modifiers changed from: private */
    public static final long _init_$lambda$0(FileDescriptor fileDescriptor, int i2) {
        j.e(fileDescriptor, "fd");
        SLog.i(TAG, "reserve xmp: V1");
        return new HeicXMPComposer(fileDescriptor).reserveXMP(i2);
    }

    /* access modifiers changed from: private */
    public static final x _init_$lambda$1(FileDescriptor fileDescriptor) {
        j.e(fileDescriptor, "fd");
        SLog.i(TAG, "remove xmp: V1");
        new HeicXMPComposer(fileDescriptor).removeXmp();
        return x.f4917a;
    }

    private final void appendFreeCdif(FileChannel fileChannel, byte[] bArr) {
        int length = bArr.length + 12;
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.putInt(length);
        Charset charset = Tf.a.f3815a;
        byte[] bytes = "free".getBytes(charset);
        j.d(bytes, "getBytes(...)");
        allocate.put(bytes);
        byte[] bytes2 = MediaDefs.Image.HEIF.HEIF_CDIF_BOX.getBytes(charset);
        j.d(bytes2, "getBytes(...)");
        allocate.put(bytes2);
        allocate.put(bArr);
        allocate.flip();
        fileChannel.position(fileChannel.size());
        fileChannel.write(allocate);
    }

    /* access modifiers changed from: private */
    public final native void nRemoveXMPOnHEIF(FileDescriptor fileDescriptor);

    /* access modifiers changed from: private */
    public final native long nReserveXMPOnHEIF(FileDescriptor fileDescriptor, int i2);

    /* access modifiers changed from: private */
    public static final Object removeCameraDebugInfo$lambda$7(t tVar, t tVar2, FileChannel fileChannel) {
        j.e(fileChannel, "it");
        long size = fileChannel.size();
        long j2 = tVar.d;
        if (tVar2.d + j2 == size) {
            return fileChannel.truncate(j2);
        }
        fileChannel.position(j2);
        ByteBuffer allocate = ByteBuffer.allocate((int) ((size - tVar.d) - tVar2.d));
        allocate.putInt(8);
        byte[] bytes = "free".getBytes(Tf.a.f3815a);
        j.d(bytes, "getBytes(...)");
        allocate.put(bytes);
        allocate.flip();
        return Integer.valueOf(fileChannel.write(allocate));
    }

    /* access modifiers changed from: private */
    public static final x writeCameraDebugInfo$lambda$5(ImageMetaReader.Box box, HEIFMetaWriter hEIFMetaWriter, byte[] bArr, FileChannel fileChannel) {
        j.e(fileChannel, "it");
        if (box != null) {
            long component1 = box.component1();
            long j2 = (long) 12;
            long component2 = box.component2() + j2;
            fileChannel.position((component1 - j2) + component2);
            FileChannel fileChannel2 = fileChannel;
            CommonsKt.shiftLeftSafely$default(fileChannel2, (int) component2, 0, false, 6, (Object) null);
            hEIFMetaWriter.appendFreeCdif(fileChannel2, bArr);
        }
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final int writeXMP$lambda$4(ImageMetaReader.Box box, ByteBuffer byteBuffer, FileChannel fileChannel) {
        j.e(fileChannel, "it");
        fileChannel.position(box.getOffset());
        fileChannel.write(byteBuffer);
        return CommonsKt.write(fileChannel, (byte) 32, ((int) box.getLength()) - byteBuffer.limit());
    }

    public final void load() {
        this.reader.reload(this.mediaFile);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [kotlin.jvm.internal.t, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v1, types: [kotlin.jvm.internal.t, java.lang.Object] */
    public void removeCameraDebugInfo() {
        ImageMetaReader.Box cameraDebugInfo = this.reader.getCameraDebugInfo();
        if (cameraDebugInfo != null) {
            ? obj = new Object();
            obj.d = cameraDebugInfo.component1();
            ? obj2 = new Object();
            obj2.d = cameraDebugInfo.component2();
            long j2 = (long) 12;
            obj.d -= j2;
            obj2.d += j2;
            this.mediaFile.useFileChannel(new StandardOpenOption[]{StandardOpenOption.READ, StandardOpenOption.WRITE}, new Wf.c(3, obj, obj2));
            load();
        }
    }

    public void removeXMP() {
        b bVar = this.removeXMPOnHEIF;
        FileDescriptor fd2 = this.mediaFile.getFd();
        j.d(fd2, "<get-fd>(...)");
        bVar.invoke(fd2);
    }

    public ImageMetaReader.Box reserveXMP(int i2) {
        c cVar = this.reserveXMPOnHEIF;
        FileDescriptor fd2 = this.mediaFile.getFd();
        j.d(fd2, "<get-fd>(...)");
        long longValue = ((Number) cVar.invoke(fd2, Integer.valueOf(i2))).longValue();
        if (longValue > 0) {
            return new ImageMetaReader.Box(longValue, (long) i2, getByteReader());
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public void writeCameraDebugInfo(byte[] bArr) {
        j.e(bArr, "data");
        if (bArr.length != 0) {
            this.mediaFile.useFileChannel(new StandardOpenOption[]{StandardOpenOption.READ, StandardOpenOption.WRITE}, new h(this.reader.getCameraDebugInfo(), this, bArr, 1));
            load();
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public void writeExif(ExifInfo exifInfo) {
        j.e(exifInfo, "exifInfo");
        throw new v0("An operation is not implemented: Not yet implemented", 2);
    }

    public void writeXMP(XMPInfo xMPInfo) {
        j.e(xMPInfo, "xmpInfo");
        if (xMPInfo.edit().isDirty()) {
            XMPMeta xmp = ((XMPInfoImpl) xMPInfo).getXmp();
            SerializeOptions serializeOptions = new SerializeOptions();
            serializeOptions.setOmitPacketWrapper(true);
            serializeOptions.setUseCompactFormat(true);
            byte[] serializeToBuffer = XMPMetaFactory.serializeToBuffer(xmp, serializeOptions);
            byte[] bytes = MediaDefs.Meta.XMP.XMP_SIGNATURE.getBytes(Tf.a.f3815a);
            j.d(bytes, "getBytes(...)");
            ByteBuffer allocate = ByteBuffer.allocate(serializeToBuffer.length + bytes.length);
            allocate.put(serializeToBuffer);
            allocate.flip();
            ImageMetaReader.Box xmp2 = ImageMetaReader.Companion.of(this.mediaFile).getXMP();
            if (xmp2 == null) {
                xmp2 = reserveXMP(MediaDefs.Meta.XMP.XMP_MIX_RESERVED_SIZE);
            }
            if (((long) allocate.limit()) < xmp2.getLength()) {
                this.mediaFile.useOutputFileChannel(new Wf.c(4, xmp2, allocate));
                return;
            }
            throw new IllegalArgumentException("Failed requirement.");
        }
    }
}
