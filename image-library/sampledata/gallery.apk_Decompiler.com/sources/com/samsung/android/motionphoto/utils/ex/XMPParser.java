package com.samsung.android.motionphoto.utils.ex;

import B1.a;
import java.io.BufferedInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\u0007J$\u0010\r\u001a\u00020\f2\b\b\u0001\u0010\n\u001a\u00020\t2\b\b\u0001\u0010\u000b\u001a\u00020\u0005H\u0001¢\u0006\u0004\b\r\u0010\u000eJB\u0010\u0015\u001a\u00020\f2\b\b\u0001\u0010\u0010\u001a\u00020\u000f2\b\b\u0001\u0010\u0011\u001a\u00020\t2\b\b\u0001\u0010\u0012\u001a\u00020\u000f2\b\b\u0001\u0010\u0013\u001a\u00020\t2\b\b\u0001\u0010\u0014\u001a\u00020\u000fH\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\b\u0001\u0010\u0018\u001a\u00020\u0017H\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ$\u0010\u001d\u001a\u00020\f2\b\b\u0001\u0010\u0018\u001a\u00020\u00172\b\b\u0001\u0010\u001c\u001a\u00020\u000fH\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\fH\u0001¢\u0006\u0004\b\u001f\u0010 R\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010!¨\u0006#"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/XMPParser;", "Lcom/samsung/android/motionphoto/utils/ex/XmpComposer;", "handler", "<init>", "(Lcom/samsung/android/motionphoto/utils/ex/XmpComposer;)V", "", "hasHeic", "()Z", "hasJpeg", "", "xmpSize", "hasHdr", "Lme/x;", "reserveXmp", "(IZ)V", "", "primaryItemLength", "primaryItemPadding", "secondItemLength", "secondItemPadding", "presentationTimestamp", "writeXmp", "(JIJIJ)V", "Ljava/io/FileDescriptor;", "fd", "Lcom/samsung/android/motionphoto/utils/ex/XMPInformation;", "calculateXmp", "(Ljava/io/FileDescriptor;)Lcom/samsung/android/motionphoto/utils/ex/XMPInformation;", "timestamp", "completeXmp", "(Ljava/io/FileDescriptor;J)V", "removeXmp", "()V", "Lcom/samsung/android/motionphoto/utils/ex/XmpComposer;", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class XMPParser implements XmpComposer {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static final byte[] HEIF_BRAND_HEIC = {104, 101, 105, 99};
    /* access modifiers changed from: private */
    public static final byte[] HEIF_BRAND_MIF1 = {109, 105, 102, 49};
    /* access modifiers changed from: private */
    public static final byte[] HEIF_TYPE_FTYP = {102, 116, 121, 112};
    /* access modifiers changed from: private */
    public static final byte[] JPEG_SIGNATURE = {-1, -40, -1};
    private static final int SIGNATURE_CHECK_SIZE = 5000;
    /* access modifiers changed from: private */
    public static final String TAG = "XMPParser";
    private final XmpComposer handler;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000bH\u0002J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000bH\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/XMPParser$Companion;", "", "<init>", "()V", "TAG", "", "getTAG$motionphoto_utils_v2_0_17_release", "()Ljava/lang/String;", "SIGNATURE_CHECK_SIZE", "", "JPEG_SIGNATURE", "", "HEIF_TYPE_FTYP", "HEIF_BRAND_MIF1", "HEIF_BRAND_HEIC", "create", "Lcom/samsung/android/motionphoto/utils/ex/XMPParser;", "fd", "Ljava/io/FileDescriptor;", "isJpegFormat", "", "signatureCheckBytes", "isHeifFormat", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* JADX WARNING: Removed duplicated region for block: B:60:0x00ad  */
        /* JADX WARNING: Removed duplicated region for block: B:63:0x00b3  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final boolean isHeifFormat(byte[] r15) {
            /*
                r14 = this;
                r0 = 0
                r1 = 0
                com.samsung.android.motionphoto.utils.ex.ByteOrderedDataInputStream r2 = new com.samsung.android.motionphoto.utils.ex.ByteOrderedDataInputStream     // Catch:{ Exception -> 0x00a1 }
                r2.<init>((byte[]) r15)     // Catch:{ Exception -> 0x00a1 }
                java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                java.lang.String r3 = "BIG_ENDIAN"
                kotlin.jvm.internal.j.d(r1, r3)     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                r2.setByteOrder(r1)     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                int r1 = r2.readInt()     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                long r3 = (long) r1     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                r1 = 4
                byte[] r5 = new byte[r1]     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                r2.read(r5)     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                byte[] r6 = com.samsung.android.motionphoto.utils.ex.XMPParser.HEIF_TYPE_FTYP     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                boolean r5 = java.util.Arrays.equals(r5, r6)     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                if (r5 != 0) goto L_0x002a
                r2.close()
                return r0
            L_0x002a:
                r5 = 1
                int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                r8 = 8
                if (r7 != 0) goto L_0x004c
                long r3 = r2.readLong()     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                r10 = 16
                int r7 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
                if (r7 >= 0) goto L_0x0040
                r2.close()
                return r0
            L_0x0040:
                r7 = 8
                long r10 = (long) r7
                long r10 = r10 + r8
                goto L_0x004d
            L_0x0045:
                r14 = move-exception
                r1 = r2
                goto L_0x00b1
            L_0x0049:
                r15 = move-exception
                r1 = r2
                goto L_0x00a2
            L_0x004c:
                r10 = r8
            L_0x004d:
                int r7 = r15.length     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                long r12 = (long) r7     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                int r7 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
                if (r7 <= 0) goto L_0x0055
                int r15 = r15.length     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                long r3 = (long) r15
            L_0x0055:
                long r3 = r3 - r10
                int r15 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
                if (r15 >= 0) goto L_0x005e
                r2.close()
                return r0
            L_0x005e:
                byte[] r15 = new byte[r1]     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                long r7 = (long) r1     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                long r3 = r3 / r7
                r7 = 0
                r9 = r0
                r10 = r9
            L_0x0066:
                int r11 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
                if (r11 >= 0) goto L_0x009b
                int r11 = r2.read(r15)     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                if (r11 == r1) goto L_0x0074
                r2.close()
                return r0
            L_0x0074:
                int r11 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                if (r11 != 0) goto L_0x0079
                goto L_0x0099
            L_0x0079:
                byte[] r11 = com.samsung.android.motionphoto.utils.ex.XMPParser.HEIF_BRAND_MIF1     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                boolean r11 = java.util.Arrays.equals(r15, r11)     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                r12 = 1
                if (r11 == 0) goto L_0x0086
                r9 = r12
                goto L_0x0091
            L_0x0086:
                byte[] r11 = com.samsung.android.motionphoto.utils.ex.XMPParser.HEIF_BRAND_HEIC     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                boolean r11 = java.util.Arrays.equals(r15, r11)     // Catch:{ Exception -> 0x0049, all -> 0x0045 }
                if (r11 == 0) goto L_0x0091
                r10 = r12
            L_0x0091:
                if (r9 == 0) goto L_0x0099
                if (r10 == 0) goto L_0x0099
                r2.close()
                return r12
            L_0x0099:
                long r7 = r7 + r5
                goto L_0x0066
            L_0x009b:
                r2.close()
                return r0
            L_0x009f:
                r14 = move-exception
                goto L_0x00b1
            L_0x00a1:
                r15 = move-exception
            L_0x00a2:
                java.lang.String r14 = r14.getTAG$motionphoto_utils_v2_0_17_release()     // Catch:{ all -> 0x009f }
                java.lang.String r2 = "Exception parsing HEIF file type box."
                android.util.Log.d(r14, r2, r15)     // Catch:{ all -> 0x009f }
                if (r1 == 0) goto L_0x00b0
                r1.close()
            L_0x00b0:
                return r0
            L_0x00b1:
                if (r1 == 0) goto L_0x00b6
                r1.close()
            L_0x00b6:
                throw r14
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.ex.XMPParser.Companion.isHeifFormat(byte[]):boolean");
        }

        private final boolean isJpegFormat(byte[] bArr) {
            int length = XMPParser.JPEG_SIGNATURE.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (bArr[i2] != XMPParser.JPEG_SIGNATURE[i2]) {
                    return false;
                }
            }
            return true;
        }

        public final XMPParser create(FileDescriptor fileDescriptor) {
            XmpComposer xmpComposer;
            j.e(fileDescriptor, "fd");
            FileInputStream fileInputStream = new FileInputStream(fileDescriptor);
            try {
                fileInputStream.getChannel().position(0);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 5000);
                bufferedInputStream.mark(5000);
                byte[] bArr = new byte[5000];
                bufferedInputStream.read(bArr);
                bufferedInputStream.reset();
                Companion companion = XMPParser.Companion;
                if (companion.isJpegFormat(bArr)) {
                    xmpComposer = new JpegXMPComposer(fileDescriptor);
                } else if (companion.isHeifFormat(bArr)) {
                    xmpComposer = new HeicXMPComposer(fileDescriptor);
                } else {
                    throw new IllegalArgumentException("supports JPEG and HEIF image formats only");
                }
                fileInputStream.close();
                return new XMPParser(xmpComposer);
            } catch (Throwable th) {
                a.g(fileInputStream, th);
                throw th;
            }
        }

        public final String getTAG$motionphoto_utils_v2_0_17_release() {
            return XMPParser.TAG;
        }

        private Companion() {
        }
    }

    public XMPParser(XmpComposer xmpComposer) {
        j.e(xmpComposer, "handler");
        this.handler = xmpComposer;
    }

    public XMPInformation calculateXmp(FileDescriptor fileDescriptor) {
        j.e(fileDescriptor, "fd");
        return this.handler.calculateXmp(fileDescriptor);
    }

    public void completeXmp(FileDescriptor fileDescriptor, long j2) {
        j.e(fileDescriptor, "fd");
        this.handler.completeXmp(fileDescriptor, j2);
    }

    public final boolean hasHeic() {
        return this.handler instanceof HeicXMPComposer;
    }

    public final boolean hasJpeg() {
        return this.handler instanceof JpegXMPComposer;
    }

    public void removeXmp() {
        this.handler.removeXmp();
    }

    public void reserveXmp(int i2, boolean z) {
        this.handler.reserveXmp(i2, z);
    }

    public void writeXmp(long j2, int i2, long j3, int i7, long j8) {
        this.handler.writeXmp(j2, i2, j3, i7, j8);
    }
}
