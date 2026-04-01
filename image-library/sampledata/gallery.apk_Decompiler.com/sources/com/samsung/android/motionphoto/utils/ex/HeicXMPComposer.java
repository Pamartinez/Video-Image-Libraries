package com.samsung.android.motionphoto.utils.ex;

import Tf.a;
import android.media.ExifInterface;
import android.util.Log;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b&\u0018\u0000 <2\u00020\u0001:\u0001<B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H ¢\u0006\u0004\b\u0007\u0010\u0005J \u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH ¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0001\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u0011\u001a\u00020\u00062\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0010\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J!\u0010\u0018\u001a\u00020\u00172\b\b\u0001\u0010\u0014\u001a\u00020\u00132\b\b\u0001\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0018\u0010\u0019JA\u0010\u001f\u001a\u00020\u00062\b\b\u0001\u0010\u001a\u001a\u00020\n2\b\b\u0001\u0010\u001b\u001a\u00020\b2\b\b\u0001\u0010\u001c\u001a\u00020\n2\b\b\u0001\u0010\u001d\u001a\u00020\b2\b\b\u0001\u0010\u001e\u001a\u00020\nH\u0016¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u0006H\u0016¢\u0006\u0004\b!\u0010\"J!\u0010#\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\b\b\u0001\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b#\u0010$J\u0015\u0010%\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b%\u0010&R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010'\u001a\u0004\b(\u0010)R\"\u0010*\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\"\u00100\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\"\u00106\u001a\u00020\u00158\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;¨\u0006="}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/HeicXMPComposer;", "Lcom/samsung/android/motionphoto/utils/ex/XmpComposer;", "Ljava/io/FileDescriptor;", "fd", "<init>", "(Ljava/io/FileDescriptor;)V", "Lme/x;", "native_remove_xmp_on_heic", "", "xmpSize", "", "native_reserve_xmp_on_heic", "(Ljava/io/FileDescriptor;I)J", "Lcom/samsung/android/motionphoto/utils/ex/XMPInformation;", "calculateXmp", "(Ljava/io/FileDescriptor;)Lcom/samsung/android/motionphoto/utils/ex/XMPInformation;", "timestamp", "completeXmp", "(Ljava/io/FileDescriptor;J)V", "Ljava/io/FileInputStream;", "iStream", "", "hasHdr", "Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoVideoUtils$XMPInformation;", "getXmpStartPosition", "(Ljava/io/FileInputStream;Z)Lcom/samsung/android/motionphoto/utils/ex/MotionPhotoVideoUtils$XMPInformation;", "primaryItemLength", "primaryItemPadding", "secondItemLength", "secondItemPadding", "presentationTimestamp", "writeXmp", "(JIJIJ)V", "removeXmp", "()V", "reserveXmp", "(IZ)V", "reserveXMP", "(I)J", "Ljava/io/FileDescriptor;", "getFd", "()Ljava/io/FileDescriptor;", "xmpPosition", "J", "getXmpPosition", "()J", "setXmpPosition", "(J)V", "xmpLength", "I", "getXmpLength", "()I", "setXmpLength", "(I)V", "hasHDR", "Z", "getHasHDR", "()Z", "setHasHDR", "(Z)V", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class HeicXMPComposer implements XmpComposer {
    public static final Companion Companion = new Companion((e) null);
    public static final int HEIC_SEF_XMP_PADDING = 67;
    private static final String TAG = "HeicXMPComposer";

    /* renamed from: fd  reason: collision with root package name */
    private final FileDescriptor f3241fd;
    private boolean hasHDR;
    private int xmpLength;
    private long xmpPosition;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/HeicXMPComposer$Companion;", "", "<init>", "()V", "TAG", "", "HEIC_SEF_XMP_PADDING", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        System.loadLibrary("apex_motionphoto_utils_jni.media.samsung");
    }

    public HeicXMPComposer(FileDescriptor fileDescriptor) {
        j.e(fileDescriptor, "fd");
        this.f3241fd = fileDescriptor;
    }

    private final native void native_remove_xmp_on_heic(FileDescriptor fileDescriptor);

    private final native long native_reserve_xmp_on_heic(FileDescriptor fileDescriptor, int i2);

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0046, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0047, code lost:
        B1.a.g(r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.samsung.android.motionphoto.utils.ex.XMPInformation calculateXmp(java.io.FileDescriptor r9) {
        /*
            r8 = this;
            java.lang.String r8 = "fd"
            kotlin.jvm.internal.j.e(r9, r8)
            java.io.FileInputStream r8 = new java.io.FileInputStream
            r8.<init>(r9)
            java.nio.channels.FileChannel r9 = r8.getChannel()     // Catch:{ all -> 0x0037 }
            r0 = 0
            r9.position(r0)     // Catch:{ all -> 0x0037 }
            long r0 = com.samsung.android.motionphoto.utils.ex.HEIFParser.getVideoOffset(r8)     // Catch:{ all -> 0x0037 }
            java.nio.channels.FileChannel r9 = r8.getChannel()     // Catch:{ all -> 0x0037 }
            long r2 = r9.size()     // Catch:{ all -> 0x0037 }
            r9 = 8
            long r4 = (long) r9     // Catch:{ all -> 0x0037 }
            long r6 = r0 + r4
            int r9 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r9 <= 0) goto L_0x0039
            long r4 = r0 - r4
            long r4 = r0 - r4
            long r2 = r2 - r0
            com.samsung.android.motionphoto.utils.ex.XMPInformation r9 = new com.samsung.android.motionphoto.utils.ex.XMPInformation     // Catch:{ all -> 0x0037 }
            int r0 = (int) r4     // Catch:{ all -> 0x0037 }
            r9.<init>(r0, r2)     // Catch:{ all -> 0x0037 }
            r8.close()
            return r9
        L_0x0037:
            r9 = move-exception
            goto L_0x0045
        L_0x0039:
            r8.close()
            java.lang.String r8 = TAG
            java.lang.String r9 = "Fail to calculate xmp!"
            android.util.Log.w(r8, r9)
            r8 = 0
            return r8
        L_0x0045:
            throw r9     // Catch:{ all -> 0x0046 }
        L_0x0046:
            r0 = move-exception
            B1.a.g(r8, r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.ex.HeicXMPComposer.calculateXmp(java.io.FileDescriptor):com.samsung.android.motionphoto.utils.ex.XMPInformation");
    }

    public void completeXmp(FileDescriptor fileDescriptor, long j2) {
        j.e(fileDescriptor, "fd");
        XMPInformation calculateXmp = calculateXmp(fileDescriptor);
        if (calculateXmp != null) {
            int primaryPadding = calculateXmp.getPrimaryPadding();
            long motionphotoLength = calculateXmp.getMotionphotoLength();
            String str = TAG;
            Log.d(str, "primary padding:" + primaryPadding + " mpLen:" + motionphotoLength + "ts:" + j2);
            writeXmp(0, primaryPadding, motionphotoLength, 0, j2);
            return;
        }
        Log.w(TAG, "Fail to write xmp");
    }

    public final FileDescriptor getFd() {
        return this.f3241fd;
    }

    public final boolean getHasHDR() {
        return this.hasHDR;
    }

    public final int getXmpLength() {
        return this.xmpLength;
    }

    public final long getXmpPosition() {
        return this.xmpPosition;
    }

    public final MotionPhotoVideoUtils.XMPInformation getXmpStartPosition(FileInputStream fileInputStream, boolean z) {
        j.e(fileInputStream, "iStream");
        HEIFParser hEIFParser = new HEIFParser();
        fileInputStream.getChannel().position(0);
        MotionPhotoVideoUtils.XMPInformation coverImageXMPOffsetAndSize = hEIFParser.getCoverImageXMPOffsetAndSize(fileInputStream);
        if (coverImageXMPOffsetAndSize != null) {
            String str = TAG;
            long j2 = coverImageXMPOffsetAndSize.offset;
            long j3 = coverImageXMPOffsetAndSize.size;
            StringBuilder j8 = N2.j.j(j2, "[HEIF]xmp ", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            j8.append(j3);
            Log.d(str, j8.toString());
        }
        j.b(coverImageXMPOffsetAndSize);
        return coverImageXMPOffsetAndSize;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0083, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        B1.a.g(r1, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0087, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeXmp() {
        /*
            r8 = this;
            java.io.FileInputStream r0 = new java.io.FileInputStream
            java.io.FileDescriptor r1 = r8.f3241fd
            r0.<init>(r1)
            com.adobe.internal.xmp.XMPMeta r1 = com.samsung.android.motionphoto.utils.ex.XMPComposerKt.hasHdrmap(r0)     // Catch:{ all -> 0x0017 }
            r2 = 1
            if (r1 == 0) goto L_0x001a
            java.lang.String r3 = TAG     // Catch:{ all -> 0x0017 }
            java.lang.String r4 = "Found hdr"
            android.util.Log.d(r3, r4)     // Catch:{ all -> 0x0017 }
            r3 = r2
            goto L_0x001b
        L_0x0017:
            r8 = move-exception
            goto L_0x0099
        L_0x001a:
            r3 = 0
        L_0x001b:
            com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils$XMPInformation r4 = r8.getXmpStartPosition(r0, r3)     // Catch:{ all -> 0x0017 }
            if (r4 == 0) goto L_0x008e
            long r5 = r4.offset     // Catch:{ all -> 0x0017 }
            r8.xmpPosition = r5     // Catch:{ all -> 0x0017 }
            java.nio.channels.FileChannel r5 = r0.getChannel()     // Catch:{ all -> 0x0017 }
            long r6 = r8.xmpPosition     // Catch:{ all -> 0x0017 }
            r5.position(r6)     // Catch:{ all -> 0x0017 }
            long r4 = r4.size     // Catch:{ all -> 0x0017 }
            int r4 = (int) r4     // Catch:{ all -> 0x0017 }
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r4)     // Catch:{ all -> 0x0017 }
            if (r3 == 0) goto L_0x0088
            r3 = 0
            if (r1 == 0) goto L_0x0041
            com.samsung.android.motionphoto.utils.ex.MimeType r5 = com.samsung.android.motionphoto.utils.ex.MimeType.HEIC     // Catch:{ all -> 0x0017 }
            com.adobe.internal.xmp.XMPMeta r1 = com.samsung.android.motionphoto.utils.ex.XMPComposerKt.recomposeXMP(r1, r5)     // Catch:{ all -> 0x0017 }
            goto L_0x0042
        L_0x0041:
            r1 = r3
        L_0x0042:
            com.adobe.internal.xmp.options.SerializeOptions r5 = new com.adobe.internal.xmp.options.SerializeOptions     // Catch:{ all -> 0x0017 }
            r5.<init>()     // Catch:{ all -> 0x0017 }
            r5.setOmitPacketWrapper(r2)     // Catch:{ all -> 0x0017 }
            r5.setUseCompactFormat(r2)     // Catch:{ all -> 0x0017 }
            byte[] r1 = com.adobe.internal.xmp.XMPMetaFactory.serializeToBuffer(r1, r5)     // Catch:{ all -> 0x0017 }
            r4.put(r1)     // Catch:{ all -> 0x0017 }
        L_0x0054:
            boolean r1 = r4.hasRemaining()     // Catch:{ all -> 0x0017 }
            if (r1 == 0) goto L_0x0060
            r1 = 32
            r4.put(r1)     // Catch:{ all -> 0x0017 }
            goto L_0x0054
        L_0x0060:
            r4.rewind()     // Catch:{ all -> 0x0017 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0017 }
            java.io.FileDescriptor r2 = r8.f3241fd     // Catch:{ all -> 0x0017 }
            r1.<init>(r2)     // Catch:{ all -> 0x0017 }
            java.nio.channels.FileChannel r1 = r1.getChannel()     // Catch:{ all -> 0x0017 }
            long r5 = r8.xmpPosition     // Catch:{ all -> 0x0081 }
            r1.position(r5)     // Catch:{ all -> 0x0081 }
            r1.write(r4)     // Catch:{ all -> 0x0081 }
            long r4 = r1.size()     // Catch:{ all -> 0x0081 }
            r1.truncate(r4)     // Catch:{ all -> 0x0081 }
            B1.a.g(r1, r3)     // Catch:{ all -> 0x0017 }
            goto L_0x0095
        L_0x0081:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0083 }
        L_0x0083:
            r2 = move-exception
            B1.a.g(r1, r8)     // Catch:{ all -> 0x0017 }
            throw r2     // Catch:{ all -> 0x0017 }
        L_0x0088:
            java.io.FileDescriptor r1 = r8.f3241fd     // Catch:{ all -> 0x0017 }
            r8.native_remove_xmp_on_heic(r1)     // Catch:{ all -> 0x0017 }
            goto L_0x0095
        L_0x008e:
            java.lang.String r8 = TAG     // Catch:{ all -> 0x0017 }
            java.lang.String r1 = "Fail to get remove xmp"
            android.util.Log.w(r8, r1)     // Catch:{ all -> 0x0017 }
        L_0x0095:
            r0.close()
            return
        L_0x0099:
            throw r8     // Catch:{ all -> 0x009a }
        L_0x009a:
            r1 = move-exception
            B1.a.g(r0, r8)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.ex.HeicXMPComposer.removeXmp():void");
    }

    public final long reserveXMP(int i2) {
        return native_reserve_xmp_on_heic(this.f3241fd, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0036, code lost:
        B1.a.g(r5, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0039, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void reserveXmp(int r5, boolean r6) {
        /*
            r4 = this;
            r4.xmpLength = r5
            r4.hasHDR = r6
            if (r6 != 0) goto L_0x000f
            java.io.FileDescriptor r6 = r4.f3241fd
            long r5 = r4.native_reserve_xmp_on_heic(r6, r5)
            r4.xmpPosition = r5
            return
        L_0x000f:
            java.io.FileInputStream r5 = new java.io.FileInputStream
            java.io.FileDescriptor r6 = r4.f3241fd
            r5.<init>(r6)
            boolean r6 = r4.hasHDR     // Catch:{ all -> 0x002e }
            com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils$XMPInformation r6 = r4.getXmpStartPosition(r5, r6)     // Catch:{ all -> 0x002e }
            long r0 = r6.offset     // Catch:{ all -> 0x002e }
            r4.xmpPosition = r0     // Catch:{ all -> 0x002e }
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x0030
            java.lang.String r4 = TAG     // Catch:{ all -> 0x002e }
            java.lang.String r6 = "[HEIC] reserveXmp, Fail to get xmp position"
            android.util.Log.e(r4, r6)     // Catch:{ all -> 0x002e }
            goto L_0x0030
        L_0x002e:
            r4 = move-exception
            goto L_0x0034
        L_0x0030:
            r5.close()
            return
        L_0x0034:
            throw r4     // Catch:{ all -> 0x0035 }
        L_0x0035:
            r6 = move-exception
            B1.a.g(r5, r4)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.ex.HeicXMPComposer.reserveXmp(int, boolean):void");
    }

    public final void setHasHDR(boolean z) {
        this.hasHDR = z;
    }

    public final void setXmpLength(int i2) {
        this.xmpLength = i2;
    }

    public final void setXmpPosition(long j2) {
        this.xmpPosition = j2;
    }

    public void writeXmp(long j2, int i2, long j3, int i7, long j8) {
        Throwable th;
        Throwable th2;
        XMPMeta xMPMeta;
        Throwable th3;
        if (this.xmpPosition == 0) {
            Log.w(TAG, "xmpPosition is 0, do nothing");
        } else if (this.hasHDR) {
            ByteBuffer allocate = ByteBuffer.allocate(this.xmpLength);
            FileInputStream fileInputStream = new FileInputStream(this.f3241fd);
            try {
                fileInputStream.getChannel().position(0);
                byte[] attributeBytes = new ExifInterface(fileInputStream).getAttributeBytes("Xmp");
                if (attributeBytes == null) {
                    Log.e(TAG, "Fail to get xmp from heic");
                    fileInputStream.close();
                    return;
                }
                int length = attributeBytes.length;
                int i8 = this.xmpLength;
                if (length > i8) {
                    String str = TAG;
                    int length2 = attributeBytes.length;
                    Log.e(str, "xmp size:" + length2 + " xmplen:" + i8);
                    fileInputStream.close();
                    return;
                }
                String str2 = TAG;
                int length3 = attributeBytes.length;
                Log.d(str2, "xmp size >" + length3 + " xmplen >" + i8);
                byte[] bArr = new byte[29];
                System.arraycopy(attributeBytes, 0, bArr, 0, 29);
                byte[] bytes = MediaDefs.Meta.XMP.XMP_SIGNATURE.getBytes(a.f3815a);
                j.d(bytes, "getBytes(...)");
                if (Arrays.equals(bArr, bytes)) {
                    Log.d(str2, "contain xmp header, remove it");
                    int length4 = attributeBytes.length - 29;
                    byte[] bArr2 = new byte[length4];
                    System.arraycopy(attributeBytes, 29, bArr2, 0, length4);
                    xMPMeta = XMPComposerKt.composeXMP(XMPMetaFactory.parseFromBuffer(bArr2), MimeType.HEIC, j2, i2, j3, i7, j8, true);
                } else {
                    xMPMeta = XMPComposerKt.composeXMP(XMPMetaFactory.parseFromBuffer(attributeBytes), MimeType.HEIC, j2, i2, j3, i7, j8, true);
                }
                fileInputStream.close();
                SerializeOptions serializeOptions = new SerializeOptions();
                serializeOptions.setOmitPacketWrapper(true);
                serializeOptions.setUseCompactFormat(true);
                allocate.put(XMPMetaFactory.serializeToBuffer(xMPMeta, serializeOptions));
                while (allocate.hasRemaining()) {
                    allocate.put((byte) 32);
                }
                allocate.rewind();
                FileChannel channel = new FileOutputStream(this.f3241fd).getChannel();
                try {
                    channel.position(this.xmpPosition);
                    channel.write(allocate);
                    B1.a.g(channel, (Throwable) null);
                } catch (Throwable th4) {
                    B1.a.g(channel, th3);
                    throw th4;
                }
            } catch (Throwable th5) {
                B1.a.g(fileInputStream, th2);
                throw th5;
            }
        } else {
            ByteBuffer allocate2 = ByteBuffer.allocate(this.xmpLength);
            XMPMeta access$composeXMP = XMPComposerKt.composeXMP((XMPMeta) null, MimeType.HEIC, j2, i2, j3, i7, j8, false);
            SerializeOptions serializeOptions2 = new SerializeOptions();
            serializeOptions2.setOmitPacketWrapper(true);
            serializeOptions2.setUseCompactFormat(true);
            allocate2.put(XMPMetaFactory.serializeToBuffer(access$composeXMP, serializeOptions2));
            while (allocate2.hasRemaining()) {
                allocate2.put((byte) 32);
            }
            allocate2.rewind();
            FileChannel channel2 = new FileOutputStream(this.f3241fd).getChannel();
            try {
                channel2.position(this.xmpPosition);
                channel2.write(allocate2);
                B1.a.g(channel2, (Throwable) null);
            } catch (Throwable th6) {
                B1.a.g(channel2, th);
                throw th6;
            }
        }
    }
}
