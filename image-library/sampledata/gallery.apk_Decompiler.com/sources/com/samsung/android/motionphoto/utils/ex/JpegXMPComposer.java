package com.samsung.android.motionphoto.utils.ex;

import Tf.a;
import android.media.ExifInterface;
import android.util.Log;
import c0.C0086a;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.motionphoto.utils.ex.MotionPhotoParser;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.cover.ScoverState;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b#\b\u0002\u0018\u0000 82\u00020\u0001:\u00018B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\t\u001a\u00020\b2\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\b\u0001\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\f\u0010\rJ#\u0010\u0010\u001a\u00020\u000f2\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u000e\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J!\u0010\u0014\u001a\u00020\b2\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015J#\u0010\u0018\u001a\u00020\u000f2\b\b\u0001\u0010\u0017\u001a\u00020\u00162\b\b\u0001\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0018\u0010\u0019JA\u0010\u001f\u001a\u00020\u000f2\b\b\u0001\u0010\u001a\u001a\u00020\b2\b\b\u0001\u0010\u001b\u001a\u00020\u00162\b\b\u0001\u0010\u001c\u001a\u00020\b2\b\b\u0001\u0010\u001d\u001a\u00020\u00162\b\b\u0001\u0010\u001e\u001a\u00020\bH\u0016¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u000fH\u0016¢\u0006\u0004\b!\u0010\"R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010#\u001a\u0004\b$\u0010%R\"\u0010&\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\"\u0010,\u001a\u00020\u00168\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\"\u00102\u001a\u00020\u00128\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b2\u00103\u001a\u0004\b4\u00105\"\u0004\b6\u00107¨\u00069"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/JpegXMPComposer;", "Lcom/samsung/android/motionphoto/utils/ex/XmpComposer;", "Ljava/io/FileDescriptor;", "fd", "<init>", "(Ljava/io/FileDescriptor;)V", "Ljava/io/FileInputStream;", "iStream", "", "seekToXmpStartPosition", "(Ljava/io/FileInputStream;)J", "Lcom/samsung/android/motionphoto/utils/ex/XMPInformation;", "calculateXmp", "(Ljava/io/FileDescriptor;)Lcom/samsung/android/motionphoto/utils/ex/XMPInformation;", "timestamp", "Lme/x;", "completeXmp", "(Ljava/io/FileDescriptor;J)V", "", "hasHdr", "getXmpStartPosition", "(Ljava/io/FileInputStream;Z)J", "", "xmpSize", "reserveXmp", "(IZ)V", "primaryItemLength", "primaryItemPadding", "secondItemLength", "secondItemPadding", "presentationTimestamp", "writeXmp", "(JIJIJ)V", "removeXmp", "()V", "Ljava/io/FileDescriptor;", "getFd", "()Ljava/io/FileDescriptor;", "xmpPosition", "J", "getXmpPosition", "()J", "setXmpPosition", "(J)V", "xmpLength", "I", "getXmpLength", "()I", "setXmpLength", "(I)V", "hasHDR", "Z", "getHasHDR", "()Z", "setHasHDR", "(Z)V", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class JpegXMPComposer implements XmpComposer {
    public static final Companion Companion = new Companion((e) null);
    public static final int JPEG_LENGTH_SIZE = 2;
    public static final int JPEG_MARKER_SIZE = 2;
    private static final String TAG = "JpegXMPComposer";

    /* renamed from: fd  reason: collision with root package name */
    private final FileDescriptor f3242fd;
    private boolean hasHDR;
    private int xmpLength;
    private long xmpPosition;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/JpegXMPComposer$Companion;", "", "<init>", "()V", "TAG", "", "JPEG_MARKER_SIZE", "", "JPEG_LENGTH_SIZE", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public JpegXMPComposer(FileDescriptor fileDescriptor) {
        j.e(fileDescriptor, "fd");
        this.f3242fd = fileDescriptor;
    }

    private final long seekToXmpStartPosition(FileInputStream fileInputStream) {
        byte b;
        long size;
        this.xmpPosition = 0;
        byte[] bArr = new byte[2];
        fileInputStream.getChannel().position(0);
        fileInputStream.read(bArr, 0, 2);
        while (true) {
            if (fileInputStream.read(bArr, 0, 2) <= 0) {
                break;
            }
            Integer valueOf = Integer.valueOf(bArr[0] & 255);
            Integer valueOf2 = Integer.valueOf(bArr[1] & 255);
            if (valueOf.intValue() == 255) {
                int intValue = valueOf2.intValue();
                if (208 > intValue || intValue >= 216) {
                    if (valueOf2.intValue() != 225) {
                        int intValue2 = valueOf2.intValue();
                        if (226 <= intValue2 && intValue2 < 240) {
                            fileInputStream.read(bArr, 0, 2);
                            b = ((bArr[0] & 255) << 8) | (255 & bArr[1]);
                            size = fileInputStream.getChannel().size();
                            long position = fileInputStream.getChannel().position();
                            long j2 = (long) b;
                            if (j2 > size) {
                                break;
                            }
                            long j3 = (position - ((long) 2)) + j2;
                            if (j3 > size) {
                                break;
                            }
                            fileInputStream.getChannel().position(j3);
                        }
                    } else {
                        long j8 = (long) 2;
                        long position2 = fileInputStream.getChannel().position() - j8;
                        fileInputStream.read(bArr, 0, 2);
                        byte b5 = ((bArr[0] & 255) << 8) | (255 & bArr[1]);
                        String str = TAG;
                        C0086a.C(b5, "xmp length = ", str);
                        byte[] bArr2 = new byte[29];
                        fileInputStream.read(bArr2, 0, 29);
                        byte[] bytes = MediaDefs.Meta.XMP.XMP_SIGNATURE.getBytes(a.f3815a);
                        j.d(bytes, "getBytes(...)");
                        if (Arrays.equals(bArr2, bytes)) {
                            Log.d(str, "found XMP");
                            this.xmpPosition = position2;
                            return position2;
                        }
                        byte[] bArr3 = new byte[XMPComposerKt.EXIF_HEADER.length];
                        System.arraycopy(bArr2, 0, bArr3, 0, XMPComposerKt.EXIF_HEADER.length);
                        if (Arrays.equals(bArr3, XMPComposerKt.EXIF_HEADER)) {
                            Log.d(str, "EXIF, Not XMP");
                            fileInputStream.getChannel().position(position2 + j8 + ((long) b5));
                        } else {
                            Log.d(str, "Not EXIF, Not XMP");
                            fileInputStream.getChannel().position(position2 + 1);
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("this is not valid markers");
            }
        }
        String str2 = TAG;
        Log.w(str2, "marker len: " + b + "is larger than fsize: " + size);
        Log.e(TAG, "Fail to seek xmp position");
        throw new IllegalStateException("malformed 'JPEG' image");
    }

    public XMPInformation calculateXmp(FileDescriptor fileDescriptor) {
        XMPInformation xMPInformation;
        j.e(fileDescriptor, "fd");
        MotionPhotoParser motionPhotoParser = new MotionPhotoParser(fileDescriptor);
        MotionPhotoParser.DataPosition64 parseJpegSefTail = motionPhotoParser.parseJpegSefTail();
        if (parseJpegSefTail != null) {
            long offset = parseJpegSefTail.getOffset();
            long sEFStartOffset = motionPhotoParser.getSEFStartOffset();
            xMPInformation = new XMPInformation((int) (offset - sEFStartOffset), motionPhotoParser.getFileSize() - offset);
        } else {
            xMPInformation = null;
        }
        motionPhotoParser.close();
        return xMPInformation;
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
        Log.w(TAG, "Fail to write xmp!");
    }

    public final FileDescriptor getFd() {
        return this.f3242fd;
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

    public final long getXmpStartPosition(FileInputStream fileInputStream, boolean z) {
        j.e(fileInputStream, "iStream");
        if (z) {
            return seekToXmpStartPosition(fileInputStream);
        }
        byte[] bArr = new byte[1024];
        fileInputStream.getChannel().position(0);
        fileInputStream.read(bArr, 0, 2);
        while (fileInputStream.read(bArr, 0, 2) > 0) {
            Integer valueOf = Integer.valueOf(bArr[0] & 255);
            Integer valueOf2 = Integer.valueOf(bArr[1] & 255);
            if (valueOf.intValue() == 255) {
                int intValue = valueOf2.intValue();
                if (208 > intValue || intValue >= 216) {
                    fileInputStream.read(bArr, 0, 2);
                    if (valueOf2.intValue() != 221) {
                        fileInputStream.skip(((long) (((bArr[0] & 255) << 8) | (255 & bArr[1]))) - 2);
                        if (valueOf2.intValue() == 225) {
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            } else {
                throw new IllegalArgumentException("this is not valid markers");
            }
        }
        return fileInputStream.getChannel().position();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00e5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        B1.a.g(r3, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e9, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0138, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        B1.a.g(r0, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x013c, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeXmp() {
        /*
            r11 = this;
            java.lang.String r0 = "read bytes("
            java.lang.String r1 = "xmp size : "
            java.io.FileInputStream r2 = new java.io.FileInputStream
            java.io.FileDescriptor r3 = r11.f3242fd
            r2.<init>(r3)
            com.adobe.internal.xmp.XMPMeta r3 = com.samsung.android.motionphoto.utils.ex.XMPComposerKt.hasHdrmap(r2)     // Catch:{ all -> 0x001d }
            r4 = 1
            r5 = 0
            if (r3 == 0) goto L_0x0020
            java.lang.String r6 = TAG     // Catch:{ all -> 0x001d }
            java.lang.String r7 = "Found hdr"
            android.util.Log.d(r6, r7)     // Catch:{ all -> 0x001d }
            r6 = r4
            goto L_0x0021
        L_0x001d:
            r11 = move-exception
            goto L_0x0168
        L_0x0020:
            r6 = r5
        L_0x0021:
            long r7 = r11.getXmpStartPosition(r2, r6)     // Catch:{ all -> 0x001d }
            r11.xmpPosition = r7     // Catch:{ all -> 0x001d }
            java.nio.channels.FileChannel r7 = r2.getChannel()     // Catch:{ all -> 0x001d }
            long r8 = r11.xmpPosition     // Catch:{ all -> 0x001d }
            r7.position(r8)     // Catch:{ all -> 0x001d }
            r7 = 2
            byte[] r8 = new byte[r7]     // Catch:{ all -> 0x001d }
            r2.read(r8)     // Catch:{ all -> 0x001d }
            byte r9 = r8[r5]     // Catch:{ all -> 0x001d }
            r10 = 255(0xff, float:3.57E-43)
            r9 = r9 & r10
            if (r9 != r10) goto L_0x0164
            byte r8 = r8[r4]     // Catch:{ all -> 0x001d }
            r8 = r8 & r10
            r9 = 225(0xe1, float:3.15E-43)
            if (r8 != r9) goto L_0x0164
            byte[] r7 = new byte[r7]     // Catch:{ all -> 0x001d }
            java.nio.channels.FileChannel r8 = r2.getChannel()     // Catch:{ all -> 0x001d }
            java.nio.ByteBuffer r9 = java.nio.ByteBuffer.wrap(r7)     // Catch:{ all -> 0x001d }
            r8.read(r9)     // Catch:{ all -> 0x001d }
            byte r5 = r7[r5]     // Catch:{ all -> 0x001d }
            r5 = r5 & r10
            int r5 = r5 << 8
            byte r7 = r7[r4]     // Catch:{ all -> 0x001d }
            r7 = r7 & r10
            r5 = r5 | r7
            int r7 = r5 + 2
            java.lang.String r8 = TAG     // Catch:{ all -> 0x001d }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x001d }
            r9.<init>(r1)     // Catch:{ all -> 0x001d }
            r9.append(r7)     // Catch:{ all -> 0x001d }
            java.lang.String r1 = r9.toString()     // Catch:{ all -> 0x001d }
            android.util.Log.d(r8, r1)     // Catch:{ all -> 0x001d }
            r1 = 0
            if (r6 == 0) goto L_0x00ea
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r7)     // Catch:{ all -> 0x001d }
            r6 = -1
            r0.put(r6)     // Catch:{ all -> 0x001d }
            r6 = -31
            r0.put(r6)     // Catch:{ all -> 0x001d }
            int r6 = r5 >> 8
            r6 = r6 & r10
            byte r6 = (byte) r6     // Catch:{ all -> 0x001d }
            r0.put(r6)     // Catch:{ all -> 0x001d }
            r5 = r5 & r10
            byte r5 = (byte) r5     // Catch:{ all -> 0x001d }
            r0.put(r5)     // Catch:{ all -> 0x001d }
            if (r3 == 0) goto L_0x0092
            com.samsung.android.motionphoto.utils.ex.MimeType r5 = com.samsung.android.motionphoto.utils.ex.MimeType.JPEG     // Catch:{ all -> 0x001d }
            com.adobe.internal.xmp.XMPMeta r3 = com.samsung.android.motionphoto.utils.ex.XMPComposerKt.recomposeXMP(r3, r5)     // Catch:{ all -> 0x001d }
            goto L_0x0093
        L_0x0092:
            r3 = r1
        L_0x0093:
            com.adobe.internal.xmp.options.SerializeOptions r5 = new com.adobe.internal.xmp.options.SerializeOptions     // Catch:{ all -> 0x001d }
            r5.<init>()     // Catch:{ all -> 0x001d }
            r5.setOmitPacketWrapper(r4)     // Catch:{ all -> 0x001d }
            r5.setUseCompactFormat(r4)     // Catch:{ all -> 0x001d }
            java.lang.String r4 = "http://ns.adobe.com/xap/1.0/\u0000"
            java.nio.charset.Charset r6 = Tf.a.f3815a     // Catch:{ all -> 0x001d }
            byte[] r4 = r4.getBytes(r6)     // Catch:{ all -> 0x001d }
            java.lang.String r6 = "getBytes(...)"
            kotlin.jvm.internal.j.d(r4, r6)     // Catch:{ all -> 0x001d }
            r0.put(r4)     // Catch:{ all -> 0x001d }
            byte[] r3 = com.adobe.internal.xmp.XMPMetaFactory.serializeToBuffer(r3, r5)     // Catch:{ all -> 0x001d }
            r0.put(r3)     // Catch:{ all -> 0x001d }
        L_0x00b5:
            boolean r3 = r0.hasRemaining()     // Catch:{ all -> 0x001d }
            if (r3 == 0) goto L_0x00c1
            r3 = 32
            r0.put(r3)     // Catch:{ all -> 0x001d }
            goto L_0x00b5
        L_0x00c1:
            r0.rewind()     // Catch:{ all -> 0x001d }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x001d }
            java.io.FileDescriptor r4 = r11.f3242fd     // Catch:{ all -> 0x001d }
            r3.<init>(r4)     // Catch:{ all -> 0x001d }
            java.nio.channels.FileChannel r3 = r3.getChannel()     // Catch:{ all -> 0x001d }
            long r4 = r11.xmpPosition     // Catch:{ all -> 0x00e3 }
            r3.position(r4)     // Catch:{ all -> 0x00e3 }
            r3.write(r0)     // Catch:{ all -> 0x00e3 }
            long r4 = r3.size()     // Catch:{ all -> 0x00e3 }
            r3.truncate(r4)     // Catch:{ all -> 0x00e3 }
            B1.a.g(r3, r1)     // Catch:{ all -> 0x001d }
            goto L_0x0164
        L_0x00e3:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x00e5 }
        L_0x00e5:
            r0 = move-exception
            B1.a.g(r3, r11)     // Catch:{ all -> 0x001d }
            throw r0     // Catch:{ all -> 0x001d }
        L_0x00ea:
            java.nio.channels.FileChannel r3 = r2.getChannel()     // Catch:{ all -> 0x001d }
            long r3 = r3.size()     // Catch:{ all -> 0x001d }
            long r5 = r11.xmpPosition     // Catch:{ all -> 0x001d }
            long r3 = r3 - r5
            long r5 = (long) r7     // Catch:{ all -> 0x001d }
            long r3 = r3 - r5
            int r3 = (int) r3     // Catch:{ all -> 0x001d }
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocateDirect(r3)     // Catch:{ all -> 0x001d }
            java.nio.channels.FileChannel r4 = r2.getChannel()     // Catch:{ all -> 0x001d }
            long r7 = r11.xmpPosition     // Catch:{ all -> 0x001d }
            long r7 = r7 + r5
            r4.position(r7)     // Catch:{ all -> 0x001d }
            java.nio.channels.FileChannel r4 = r2.getChannel()     // Catch:{ all -> 0x001d }
            int r4 = r4.read(r3)     // Catch:{ all -> 0x001d }
            int r7 = r3.capacity()     // Catch:{ all -> 0x001d }
            if (r4 != r7) goto L_0x013d
            r3.rewind()     // Catch:{ all -> 0x001d }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ all -> 0x001d }
            java.io.FileDescriptor r4 = r11.f3242fd     // Catch:{ all -> 0x001d }
            r0.<init>(r4)     // Catch:{ all -> 0x001d }
            java.nio.channels.FileChannel r0 = r0.getChannel()     // Catch:{ all -> 0x001d }
            long r7 = r11.xmpPosition     // Catch:{ all -> 0x0136 }
            r0.position(r7)     // Catch:{ all -> 0x0136 }
            r0.write(r3)     // Catch:{ all -> 0x0136 }
            long r3 = r0.size()     // Catch:{ all -> 0x0136 }
            long r3 = r3 - r5
            r0.truncate(r3)     // Catch:{ all -> 0x0136 }
            B1.a.g(r0, r1)     // Catch:{ all -> 0x001d }
            goto L_0x0164
        L_0x0136:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x0138 }
        L_0x0138:
            r1 = move-exception
            B1.a.g(r0, r11)     // Catch:{ all -> 0x001d }
            throw r1     // Catch:{ all -> 0x001d }
        L_0x013d:
            int r11 = r3.capacity()     // Catch:{ all -> 0x001d }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x001d }
            r1.<init>(r0)     // Catch:{ all -> 0x001d }
            r1.append(r4)     // Catch:{ all -> 0x001d }
            java.lang.String r0 = ") differ from buffer size("
            r1.append(r0)     // Catch:{ all -> 0x001d }
            r1.append(r11)     // Catch:{ all -> 0x001d }
            java.lang.String r11 = ")"
            r1.append(r11)     // Catch:{ all -> 0x001d }
            java.lang.String r11 = r1.toString()     // Catch:{ all -> 0x001d }
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x001d }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x001d }
            r0.<init>(r11)     // Catch:{ all -> 0x001d }
            throw r0     // Catch:{ all -> 0x001d }
        L_0x0164:
            r2.close()
            return
        L_0x0168:
            throw r11     // Catch:{ all -> 0x0169 }
        L_0x0169:
            r0 = move-exception
            B1.a.g(r2, r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.ex.JpegXMPComposer.removeXmp():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0090, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0091, code lost:
        B1.a.g(r6, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0094, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0097, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0098, code lost:
        B1.a.g(r7, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009b, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x009f, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a0, code lost:
        B1.a.g(r7, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a3, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void reserveXmp(int r6, boolean r7) {
        /*
            r5 = this;
            java.lang.String r0 = "reserveXmp: read bytes: "
            r5.xmpLength = r6
            r5.hasHDR = r7
            java.io.FileInputStream r7 = new java.io.FileInputStream
            java.io.FileDescriptor r1 = r5.f3242fd
            r7.<init>(r1)
            boolean r1 = r5.hasHDR     // Catch:{ all -> 0x009d }
            long r1 = r5.getXmpStartPosition(r7, r1)     // Catch:{ all -> 0x009d }
            r5.xmpPosition = r1     // Catch:{ all -> 0x009d }
            r7.close()
            boolean r7 = r5.hasHDR
            if (r7 != 0) goto L_0x009c
            java.io.FileInputStream r7 = new java.io.FileInputStream
            java.io.FileDescriptor r1 = r5.f3242fd
            r7.<init>(r1)
            java.nio.channels.FileChannel r1 = r7.getChannel()     // Catch:{ all -> 0x0095 }
            long r1 = r1.size()     // Catch:{ all -> 0x0095 }
            long r3 = r5.xmpPosition     // Catch:{ all -> 0x0095 }
            long r1 = r1 - r3
            long r3 = (long) r6     // Catch:{ all -> 0x0095 }
            long r1 = r1 + r3
            int r1 = (int) r1     // Catch:{ all -> 0x0095 }
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocateDirect(r1)     // Catch:{ all -> 0x0095 }
            r2 = -1
            r1.put(r2)     // Catch:{ all -> 0x0095 }
            r2 = -31
            r1.put(r2)     // Catch:{ all -> 0x0095 }
            int r2 = r6 + -2
            int r3 = r2 >> 8
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3     // Catch:{ all -> 0x0095 }
            r1.put(r3)     // Catch:{ all -> 0x0095 }
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r2 = (byte) r2     // Catch:{ all -> 0x0095 }
            r1.put(r2)     // Catch:{ all -> 0x0095 }
            r1.position(r6)     // Catch:{ all -> 0x0095 }
            java.nio.channels.FileChannel r6 = r7.getChannel()     // Catch:{ all -> 0x0095 }
            long r2 = r5.xmpPosition     // Catch:{ all -> 0x0095 }
            r6.position(r2)     // Catch:{ all -> 0x0095 }
            java.nio.channels.FileChannel r6 = r7.getChannel()     // Catch:{ all -> 0x0095 }
            int r6 = r6.read(r1)     // Catch:{ all -> 0x0095 }
            java.lang.String r2 = TAG     // Catch:{ all -> 0x0095 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0095 }
            r3.<init>(r0)     // Catch:{ all -> 0x0095 }
            r3.append(r6)     // Catch:{ all -> 0x0095 }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x0095 }
            android.util.Log.v(r2, r6)     // Catch:{ all -> 0x0095 }
            r1.rewind()     // Catch:{ all -> 0x0095 }
            r7.close()
            java.io.FileOutputStream r6 = new java.io.FileOutputStream
            java.io.FileDescriptor r7 = r5.f3242fd
            r6.<init>(r7)
            java.nio.channels.FileChannel r6 = r6.getChannel()
            long r2 = r5.xmpPosition     // Catch:{ all -> 0x008e }
            r6.write(r1, r2)     // Catch:{ all -> 0x008e }
            r5 = 0
            B1.a.g(r6, r5)
            return
        L_0x008e:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0090 }
        L_0x0090:
            r7 = move-exception
            B1.a.g(r6, r5)
            throw r7
        L_0x0095:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0097 }
        L_0x0097:
            r6 = move-exception
            B1.a.g(r7, r5)
            throw r6
        L_0x009c:
            return
        L_0x009d:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x009f }
        L_0x009f:
            r6 = move-exception
            B1.a.g(r7, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.ex.JpegXMPComposer.reserveXmp(int, boolean):void");
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
        Throwable th3;
        if (this.hasHDR) {
            ByteBuffer allocate = ByteBuffer.allocate(this.xmpLength);
            allocate.put((byte) -1);
            allocate.put((byte) -31);
            int i8 = this.xmpLength - 2;
            allocate.put((byte) ((i8 >> 8) & ScoverState.TYPE_NFC_SMART_COVER));
            allocate.put((byte) (i8 & ScoverState.TYPE_NFC_SMART_COVER));
            FileInputStream fileInputStream = new FileInputStream(this.f3242fd);
            try {
                fileInputStream.getChannel().position(0);
                XMPMeta access$composeXMP = XMPComposerKt.composeXMP(XMPMetaFactory.parseFromBuffer(new ExifInterface(fileInputStream).getAttributeBytes("Xmp")), MimeType.JPEG, j2, i2, j3, i7, j8, true);
                fileInputStream.close();
                SerializeOptions serializeOptions = new SerializeOptions();
                serializeOptions.setOmitPacketWrapper(true);
                serializeOptions.setUseCompactFormat(true);
                byte[] bytes = MediaDefs.Meta.XMP.XMP_SIGNATURE.getBytes(a.f3815a);
                j.d(bytes, "getBytes(...)");
                allocate.put(bytes);
                allocate.put(XMPMetaFactory.serializeToBuffer(access$composeXMP, serializeOptions));
                while (allocate.hasRemaining()) {
                    allocate.put((byte) 32);
                }
                allocate.rewind();
                FileChannel channel = new FileOutputStream(this.f3242fd).getChannel();
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
            allocate2.put((byte) -1);
            allocate2.put((byte) -31);
            int i10 = this.xmpLength - 2;
            allocate2.put((byte) ((i10 >> 8) & ScoverState.TYPE_NFC_SMART_COVER));
            allocate2.put((byte) (i10 & ScoverState.TYPE_NFC_SMART_COVER));
            XMPMeta access$composeXMP2 = XMPComposerKt.composeXMP((XMPMeta) null, MimeType.JPEG, j2, i2, j3, i7, j8, false);
            SerializeOptions serializeOptions2 = new SerializeOptions();
            serializeOptions2.setOmitPacketWrapper(true);
            serializeOptions2.setUseCompactFormat(true);
            byte[] bytes2 = MediaDefs.Meta.XMP.XMP_SIGNATURE.getBytes(a.f3815a);
            j.d(bytes2, "getBytes(...)");
            allocate2.put(bytes2);
            allocate2.put(XMPMetaFactory.serializeToBuffer(access$composeXMP2, serializeOptions2));
            while (allocate2.hasRemaining()) {
                allocate2.put((byte) 32);
            }
            allocate2.rewind();
            FileChannel channel2 = new FileOutputStream(this.f3242fd).getChannel();
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
