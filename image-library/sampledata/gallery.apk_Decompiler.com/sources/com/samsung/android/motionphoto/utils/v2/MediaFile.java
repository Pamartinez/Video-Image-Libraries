package com.samsung.android.motionphoto.utils.v2;

import Ae.b;
import Bd.C0725a;
import L1.d;
import Sf.q;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sum.core.SLog;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\f\b\u0016\u0018\u0000 E2\u00020\u0001:\u0001EB!\b\u0004\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\tB\u0011\b\u0016\u0012\u0006\u0010\n\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u000bJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\f¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0016¢\u0006\u0004\b\u0019\u0010\u0018J\r\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001e\u0010\u001fJ;\u0010'\u001a\u00028\u0000\"\u0004\b\u0000\u0010 2\u0012\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\"0!\"\u00020\"2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00028\u00000$¢\u0006\u0004\b'\u0010(J'\u0010)\u001a\u00028\u0000\"\u0004\b\u0000\u0010 2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00028\u00000$¢\u0006\u0004\b)\u0010*J'\u0010+\u001a\u00028\u0000\"\u0004\b\u0000\u0010 2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00028\u00000$¢\u0006\u0004\b+\u0010*J\r\u0010-\u001a\u00020,¢\u0006\u0004\b-\u0010.J\u001d\u00101\u001a\u00020\f2\u0006\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u0011¢\u0006\u0004\b1\u00102J5\u00106\u001a\u00020\u00162\u0006\u00103\u001a\u00020%2\b\b\u0002\u00104\u001a\u00020\f2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00160$H\u0002¢\u0006\u0004\b6\u00107R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u00108R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u00109R\u001b\u0010?\u001a\u00020:8FX\u0002¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>R\u0019\u0010\b\u001a\n @*\u0004\u0018\u00010\u00020\u00028F¢\u0006\u0006\u001a\u0004\bA\u0010BR\u0014\u0010\n\u001a\u00020\u00048@X\u0004¢\u0006\u0006\u001a\u0004\bC\u0010D¨\u0006F"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "", "Ljava/io/FileDescriptor;", "_fd", "Ljava/io/File;", "_file", "<init>", "(Ljava/io/FileDescriptor;Ljava/io/File;)V", "fd", "(Ljava/io/FileDescriptor;)V", "file", "(Ljava/io/File;)V", "", "position", "Lme/x;", "getMimeTypeOfMediaAt", "(J)V", "", "path", "()Ljava/lang/String;", "size", "()J", "", "isEmpty", "()Z", "isNotEmpty", "Ljava/io/FileInputStream;", "newInputFileStream", "()Ljava/io/FileInputStream;", "Ljava/io/FileOutputStream;", "newOutputFileStream", "()Ljava/io/FileOutputStream;", "R", "", "Ljava/nio/file/StandardOpenOption;", "options", "Lkotlin/Function1;", "Ljava/nio/channels/FileChannel;", "action", "useFileChannel", "([Ljava/nio/file/StandardOpenOption;LAe/b;)Ljava/lang/Object;", "useInputFileChannel", "(LAe/b;)Ljava/lang/Object;", "useOutputFileChannel", "Ljava/io/RandomAccessFile;", "newRandomAccessFile", "()Ljava/io/RandomAccessFile;", "prefix", "suffix", "dump", "(Ljava/lang/String;Ljava/lang/String;)J", "fis", "offset", "block", "checkFileType", "(Ljava/nio/channels/FileChannel;JLAe/b;)Z", "Ljava/io/FileDescriptor;", "Ljava/io/File;", "Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "mimeType$delegate", "Lme/f;", "getMimeType", "()Lcom/samsung/android/motionphoto/utils/v2/MimeType;", "mimeType", "kotlin.jvm.PlatformType", "getFd", "()Ljava/io/FileDescriptor;", "getFile$motionphoto_utils_v2_0_17_release", "()Ljava/io/File;", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaFile {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private final FileDescriptor _fd;
    private final File _file;
    private final f mimeType$delegate;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/MediaFile$Companion;", "", "<init>", "()V", "TAG", "", "isJpeg", "", "fc", "Ljava/nio/channels/FileChannel;", "isHeic", "isMP4Video", "isMOVVideo", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isHeic(FileChannel fileChannel) {
            j.e(fileChannel, "fc");
            fileChannel.position(fileChannel.position() + ((long) 4));
            MediaDefs.Image.HEIF.Companion companion = MediaDefs.Image.HEIF.Companion;
            int[] heif_ftyp_marker = companion.getHEIF_FTYP_MARKER();
            if (!CommonsKt.matched(fileChannel, Arrays.copyOf(heif_ftyp_marker, heif_ftyp_marker.length))) {
                return false;
            }
            int[] heif_heic_marker = companion.getHEIF_HEIC_MARKER();
            if (CommonsKt.matched(fileChannel, Arrays.copyOf(heif_heic_marker, heif_heic_marker.length))) {
                return true;
            }
            return false;
        }

        public final boolean isJpeg(FileChannel fileChannel) {
            j.e(fileChannel, "fc");
            ShortBuffer readAsShortBufferOrNull$default = CommonsKt.readAsShortBufferOrNull$default(fileChannel, 1, (ByteOrder) null, 2, (Object) null);
            if (readAsShortBufferOrNull$default == null || readAsShortBufferOrNull$default.get() != -40) {
                return false;
            }
            return true;
        }

        public final boolean isMOVVideo(FileChannel fileChannel) {
            j.e(fileChannel, "fc");
            fileChannel.position(fileChannel.position() + ((long) 4));
            return j.a(CommonsKt.readAsStringOrNull(fileChannel, 6), MediaDefs.Video.MOV.MOV_FTYP_SIGNATURE);
        }

        public final boolean isMP4Video(FileChannel fileChannel) {
            j.e(fileChannel, "fc");
            fileChannel.position(fileChannel.position() + ((long) 4));
            return j.a(CommonsKt.readAsStringOrNull(fileChannel, 8), MediaDefs.Video.MP4.MP4_FTYP_SIGNATURE);
        }

        private Companion() {
        }
    }

    static {
        String tagOf = SLog.tagOf(MediaFile.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public MediaFile() {
        this((FileDescriptor) null, (File) null, 3, (e) null);
    }

    private final boolean checkFileType(FileChannel fileChannel, long j2, b bVar) {
        fileChannel.position(j2);
        return ((Boolean) bVar.invoke(fileChannel)).booleanValue();
    }

    public static /* synthetic */ boolean checkFileType$default(MediaFile mediaFile, FileChannel fileChannel, long j2, b bVar, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 2) != 0) {
                j2 = 0;
            }
            return mediaFile.checkFileType(fileChannel, j2, bVar);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: checkFileType");
    }

    /* access modifiers changed from: private */
    public static final MimeType getMimeTypeOfMediaAt$lambda$11(MediaFile mediaFile, long j2, FileChannel fileChannel) {
        j.e(fileChannel, "it");
        if (mediaFile.checkFileType(fileChannel, j2, new C0725a(18))) {
            return MimeType.IMAGE_JPEG;
        }
        if (mediaFile.checkFileType(fileChannel, j2, new C0725a(19))) {
            return MimeType.IMAGE_HEIC;
        }
        if (mediaFile.checkFileType(fileChannel, j2, new C0725a(20))) {
            return MimeType.VIDEO_MP4;
        }
        if (mediaFile.checkFileType(fileChannel, j2, new C0725a(21))) {
            return MimeType.VIDEO_MOV;
        }
        return MimeType.NONE;
    }

    /* access modifiers changed from: private */
    public static final boolean getMimeTypeOfMediaAt$lambda$11$lambda$10(FileChannel fileChannel) {
        j.e(fileChannel, "it");
        return Companion.isMOVVideo(fileChannel);
    }

    /* access modifiers changed from: private */
    public static final boolean getMimeTypeOfMediaAt$lambda$11$lambda$7(FileChannel fileChannel) {
        j.e(fileChannel, "it");
        return Companion.isJpeg(fileChannel);
    }

    /* access modifiers changed from: private */
    public static final boolean getMimeTypeOfMediaAt$lambda$11$lambda$8(FileChannel fileChannel) {
        j.e(fileChannel, "it");
        return Companion.isHeic(fileChannel);
    }

    /* access modifiers changed from: private */
    public static final boolean getMimeTypeOfMediaAt$lambda$11$lambda$9(FileChannel fileChannel) {
        j.e(fileChannel, "it");
        return Companion.isMP4Video(fileChannel);
    }

    /* access modifiers changed from: private */
    public static final MimeType mimeType_delegate$lambda$5(MediaFile mediaFile) {
        return (MimeType) mediaFile.useInputFileChannel(new b(0, mediaFile));
    }

    /* access modifiers changed from: private */
    public static final MimeType mimeType_delegate$lambda$5$lambda$4(MediaFile mediaFile, FileChannel fileChannel) {
        j.e(fileChannel, "it");
        MediaFile mediaFile2 = mediaFile;
        FileChannel fileChannel2 = fileChannel;
        boolean checkFileType$default = checkFileType$default(mediaFile2, fileChannel2, 0, new C0725a(14), 2, (Object) null);
        MediaFile mediaFile3 = mediaFile2;
        FileChannel fileChannel3 = fileChannel2;
        if (checkFileType$default) {
            return MimeType.IMAGE_JPEG;
        }
        if (checkFileType$default(mediaFile3, fileChannel3, 0, new C0725a(15), 2, (Object) null)) {
            return MimeType.IMAGE_HEIC;
        }
        if (checkFileType$default(mediaFile3, fileChannel3, 0, new C0725a(16), 2, (Object) null)) {
            return MimeType.VIDEO_MP4;
        }
        if (checkFileType$default(mediaFile3, fileChannel3, 0, new C0725a(17), 2, (Object) null)) {
            return MimeType.VIDEO_MOV;
        }
        return MimeType.NONE;
    }

    /* access modifiers changed from: private */
    public static final boolean mimeType_delegate$lambda$5$lambda$4$lambda$0(FileChannel fileChannel) {
        j.e(fileChannel, "it");
        return Companion.isJpeg(fileChannel);
    }

    /* access modifiers changed from: private */
    public static final boolean mimeType_delegate$lambda$5$lambda$4$lambda$1(FileChannel fileChannel) {
        j.e(fileChannel, "it");
        return Companion.isHeic(fileChannel);
    }

    /* access modifiers changed from: private */
    public static final boolean mimeType_delegate$lambda$5$lambda$4$lambda$2(FileChannel fileChannel) {
        j.e(fileChannel, "it");
        return Companion.isMP4Video(fileChannel);
    }

    /* access modifiers changed from: private */
    public static final boolean mimeType_delegate$lambda$5$lambda$4$lambda$3(FileChannel fileChannel) {
        j.e(fileChannel, "it");
        return Companion.isMOVVideo(fileChannel);
    }

    /* access modifiers changed from: private */
    public static final long size$lambda$12(FileChannel fileChannel) {
        j.e(fileChannel, "it");
        fileChannel.position(0);
        return fileChannel.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002a, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        B1.a.g(r2, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long dump(java.lang.String r3, java.lang.String r4) {
        /*
            r2 = this;
            java.lang.String r0 = "prefix"
            kotlin.jvm.internal.j.e(r3, r0)
            java.lang.String r0 = "suffix"
            kotlin.jvm.internal.j.e(r4, r0)
            java.io.File r3 = java.io.File.createTempFile(r3, r4)
            java.io.FileInputStream r2 = r2.newInputFileStream()
            java.nio.file.Path r3 = r3.toPath()     // Catch:{ all -> 0x0028 }
            r4 = 1
            java.nio.file.CopyOption[] r4 = new java.nio.file.CopyOption[r4]     // Catch:{ all -> 0x0028 }
            java.nio.file.StandardCopyOption r0 = java.nio.file.StandardCopyOption.REPLACE_EXISTING     // Catch:{ all -> 0x0028 }
            r1 = 0
            r4[r1] = r0     // Catch:{ all -> 0x0028 }
            long r3 = java.nio.file.Files.copy(r2, r3, r4)     // Catch:{ all -> 0x0028 }
            r0 = 0
            B1.a.g(r2, r0)
            return r3
        L_0x0028:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x002a }
        L_0x002a:
            r4 = move-exception
            B1.a.g(r2, r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MediaFile.dump(java.lang.String, java.lang.String):long");
    }

    public final FileDescriptor getFd() {
        FileDescriptor fileDescriptor = this._fd;
        if (fileDescriptor == null) {
            return new RandomAccessFile(getFile$motionphoto_utils_v2_0_17_release(), "rw").getFD();
        }
        return fileDescriptor;
    }

    public final File getFile$motionphoto_utils_v2_0_17_release() {
        File file = this._file;
        j.b(file);
        return file;
    }

    public final MimeType getMimeType() {
        return (MimeType) this.mimeType$delegate.getValue();
    }

    public final void getMimeTypeOfMediaAt(long j2) {
        useInputFileChannel(new com.samsung.android.motionphoto.utils.v2.io.b(this, j2));
    }

    public final boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public final boolean isNotEmpty() {
        if (size() != 0) {
            return true;
        }
        return false;
    }

    public final FileInputStream newInputFileStream() {
        if (this._fd != null) {
            return new FileInputStream(getFd());
        }
        return new FileInputStream(getFile$motionphoto_utils_v2_0_17_release());
    }

    public final FileOutputStream newOutputFileStream() {
        if (this._fd != null) {
            return new FileOutputStream(getFd());
        }
        return new FileOutputStream(getFile$motionphoto_utils_v2_0_17_release());
    }

    public final RandomAccessFile newRandomAccessFile() {
        return new RandomAccessFile(getFile$motionphoto_utils_v2_0_17_release(), "rw");
    }

    public final String path() {
        if (this._file == null) {
            return "";
        }
        String absolutePath = getFile$motionphoto_utils_v2_0_17_release().getAbsolutePath();
        j.d(absolutePath, "getAbsolutePath(...)");
        return absolutePath;
    }

    public final long size() {
        return ((Number) useInputFileChannel(new C0725a(13))).longValue();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        B1.a.g(r1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0030, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <R> R useFileChannel(java.nio.file.StandardOpenOption[] r2, Ae.b r3) {
        /*
            r1 = this;
            java.lang.String r0 = "options"
            kotlin.jvm.internal.j.e(r2, r0)
            java.lang.String r0 = "action"
            kotlin.jvm.internal.j.e(r3, r0)
            java.io.File r0 = r1._file
            if (r0 == 0) goto L_0x0031
            java.io.File r1 = r1.getFile$motionphoto_utils_v2_0_17_release()
            java.nio.file.Path r1 = r1.toPath()
            int r0 = r2.length
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r0)
            java.nio.file.OpenOption[] r2 = (java.nio.file.OpenOption[]) r2
            java.nio.channels.FileChannel r1 = java.nio.channels.FileChannel.open(r1, r2)
            java.lang.Object r2 = r3.invoke(r1)     // Catch:{ all -> 0x002a }
            r3 = 0
            B1.a.g(r1, r3)
            return r2
        L_0x002a:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x002c }
        L_0x002c:
            r3 = move-exception
            B1.a.g(r1, r2)
            throw r3
        L_0x0031:
            java.io.FileDescriptor r0 = r1._fd
            if (r0 == 0) goto L_0x0042
            java.nio.file.StandardOpenOption r0 = java.nio.file.StandardOpenOption.WRITE
            boolean r2 = ne.C1192j.d0(r2, r0)
            if (r2 == 0) goto L_0x0042
            java.lang.Object r1 = r1.useOutputFileChannel(r3)
            return r1
        L_0x0042:
            java.io.FileDescriptor r2 = r1._fd
            if (r2 == 0) goto L_0x004b
            java.lang.Object r1 = r1.useInputFileChannel(r3)
            return r1
        L_0x004b:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "no file or fd given"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MediaFile.useFileChannel(java.nio.file.StandardOpenOption[], Ae.b):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0026, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        B1.a.g(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002c, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002d, code lost:
        B1.a.g(r0, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0030, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0050, code lost:
        B1.a.g(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0053, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <R> R useInputFileChannel(Ae.b r5) {
        /*
            r4 = this;
            java.lang.String r0 = "action"
            kotlin.jvm.internal.j.e(r5, r0)
            java.io.FileDescriptor r0 = r4._fd
            r1 = 0
            if (r0 == 0) goto L_0x0031
            java.io.FileInputStream r0 = new java.io.FileInputStream
            java.io.FileDescriptor r4 = r4.getFd()
            r0.<init>(r4)
            java.nio.channels.FileChannel r4 = r0.getChannel()     // Catch:{ all -> 0x0022 }
            java.lang.Object r5 = r5.invoke(r4)     // Catch:{ all -> 0x0024 }
            B1.a.g(r4, r1)     // Catch:{ all -> 0x0022 }
            r0.close()
            return r5
        L_0x0022:
            r4 = move-exception
            goto L_0x002b
        L_0x0024:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0026 }
        L_0x0026:
            r1 = move-exception
            B1.a.g(r4, r5)     // Catch:{ all -> 0x0022 }
            throw r1     // Catch:{ all -> 0x0022 }
        L_0x002b:
            throw r4     // Catch:{ all -> 0x002c }
        L_0x002c:
            r5 = move-exception
            B1.a.g(r0, r4)
            throw r5
        L_0x0031:
            java.io.File r4 = r4.getFile$motionphoto_utils_v2_0_17_release()
            java.nio.file.Path r4 = r4.toPath()
            r0 = 1
            java.nio.file.OpenOption[] r0 = new java.nio.file.OpenOption[r0]
            java.nio.file.StandardOpenOption r2 = java.nio.file.StandardOpenOption.READ
            r3 = 0
            r0[r3] = r2
            java.nio.channels.FileChannel r4 = java.nio.channels.FileChannel.open(r4, r0)
            java.lang.Object r5 = r5.invoke(r4)     // Catch:{ all -> 0x004d }
            B1.a.g(r4, r1)
            return r5
        L_0x004d:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x004f }
        L_0x004f:
            r0 = move-exception
            B1.a.g(r4, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MediaFile.useInputFileChannel(Ae.b):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0026, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        B1.a.g(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002c, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002d, code lost:
        B1.a.g(r0, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0030, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0050, code lost:
        B1.a.g(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0053, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <R> R useOutputFileChannel(Ae.b r5) {
        /*
            r4 = this;
            java.lang.String r0 = "action"
            kotlin.jvm.internal.j.e(r5, r0)
            java.io.FileDescriptor r0 = r4._fd
            r1 = 0
            if (r0 == 0) goto L_0x0031
            java.io.FileOutputStream r0 = new java.io.FileOutputStream
            java.io.FileDescriptor r4 = r4.getFd()
            r0.<init>(r4)
            java.nio.channels.FileChannel r4 = r0.getChannel()     // Catch:{ all -> 0x0022 }
            java.lang.Object r5 = r5.invoke(r4)     // Catch:{ all -> 0x0024 }
            B1.a.g(r4, r1)     // Catch:{ all -> 0x0022 }
            r0.close()
            return r5
        L_0x0022:
            r4 = move-exception
            goto L_0x002b
        L_0x0024:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0026 }
        L_0x0026:
            r1 = move-exception
            B1.a.g(r4, r5)     // Catch:{ all -> 0x0022 }
            throw r1     // Catch:{ all -> 0x0022 }
        L_0x002b:
            throw r4     // Catch:{ all -> 0x002c }
        L_0x002c:
            r5 = move-exception
            B1.a.g(r0, r4)
            throw r5
        L_0x0031:
            java.io.File r4 = r4.getFile$motionphoto_utils_v2_0_17_release()
            java.nio.file.Path r4 = r4.toPath()
            r0 = 1
            java.nio.file.OpenOption[] r0 = new java.nio.file.OpenOption[r0]
            java.nio.file.StandardOpenOption r2 = java.nio.file.StandardOpenOption.WRITE
            r3 = 0
            r0[r3] = r2
            java.nio.channels.FileChannel r4 = java.nio.channels.FileChannel.open(r4, r0)
            java.lang.Object r5 = r5.invoke(r4)     // Catch:{ all -> 0x004d }
            B1.a.g(r4, r1)
            return r5
        L_0x004d:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x004f }
        L_0x004f:
            r0 = move-exception
            B1.a.g(r4, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.MediaFile.useOutputFileChannel(Ae.b):java.lang.Object");
    }

    public MediaFile(FileDescriptor fileDescriptor, File file) {
        this._fd = fileDescriptor;
        this._file = file;
        this.mimeType$delegate = d.q(new q(4, this));
        if (fileDescriptor == null && file == null) {
            throw new IllegalStateException("either fd or file should be given");
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MediaFile(FileDescriptor fileDescriptor, File file, int i2, e eVar) {
        this((i2 & 1) != 0 ? null : fileDescriptor, (i2 & 2) != 0 ? null : file);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MediaFile(FileDescriptor fileDescriptor) {
        this(fileDescriptor, (File) null, 2, (e) null);
        j.e(fileDescriptor, "fd");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MediaFile(File file) {
        this((FileDescriptor) null, file, 1, (e) null);
        j.e(file, "file");
    }
}
