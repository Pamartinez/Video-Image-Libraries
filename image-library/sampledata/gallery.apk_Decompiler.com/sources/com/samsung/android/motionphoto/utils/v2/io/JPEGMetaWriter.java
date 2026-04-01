package com.samsung.android.motionphoto.utils.v2.io;

import Ad.f;
import B1.a;
import Ke.v0;
import Wf.c;
import bd.h;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.motionphoto.utils.v2.CommonsKt;
import com.samsung.android.motionphoto.utils.v2.ExifInfo;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.motionphoto.utils.v2.MediaFile;
import com.samsung.android.motionphoto.utils.v2.MetaType;
import com.samsung.android.motionphoto.utils.v2.XMPInfo;
import com.samsung.android.motionphoto.utils.v2.XMPInfoImpl;
import com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader;
import com.samsung.android.sum.core.SLog;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.t;
import me.i;
import me.n;
import me.x;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 ,2\u00020\u00012\u00020\u0002:\u0001,B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\f\u0010\u000bJ\u001b\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J-\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J'\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u001d\u0010\u000bJ\u000f\u0010\u001e\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010\"\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020 H\u0016¢\u0006\u0004\b\"\u0010#J\u0017\u0010$\u001a\u00020\u001a2\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020\u001aH\u0016¢\u0006\u0004\b&\u0010\u001fJ\u0017\u0010)\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020'H\u0016¢\u0006\u0004\b)\u0010*R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010+¨\u0006-"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaWriter;", "Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaBase;", "Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaWriter;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "mediaFile", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "", "size", "Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader$Box;", "reserveXMPByMove", "(I)Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaReader$Box;", "reserveXMPByCopy", "Lme/i;", "", "findXMPApp1Marker", "()Lme/i;", "Ljava/nio/channels/FileChannel;", "fc", "", "want", "findAppSegment", "(Ljava/nio/channels/FileChannel;S)Lme/i;", "", "data", "position", "Lme/x;", "writeApp4", "(Ljava/nio/channels/FileChannel;[BJ)V", "reserveXMP", "removeXMP", "()V", "Lcom/samsung/android/motionphoto/utils/v2/ExifInfo;", "exifInfo", "writeExif", "(Lcom/samsung/android/motionphoto/utils/v2/ExifInfo;)V", "writeCameraDebugInfo", "([B)V", "removeCameraDebugInfo", "Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;", "xmpInfo", "writeXMP", "(Lcom/samsung/android/motionphoto/utils/v2/XMPInfo;)V", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class JPEGMetaWriter extends JPEGMetaBase implements ImageMetaWriter {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private final MediaFile mediaFile;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaWriter$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.samsung.android.motionphoto.utils.v2.MetaType[] r0 = com.samsung.android.motionphoto.utils.v2.MetaType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.samsung.android.motionphoto.utils.v2.MetaType r1 = com.samsung.android.motionphoto.utils.v2.MetaType.META_EXIF     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.samsung.android.motionphoto.utils.v2.MetaType r1 = com.samsung.android.motionphoto.utils.v2.MetaType.META_XMP     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.io.JPEGMetaWriter.WhenMappings.<clinit>():void");
        }
    }

    static {
        String tagOf = SLog.tagOf(JPEGMetaWriter.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JPEGMetaWriter(MediaFile mediaFile2) {
        super(mediaFile2);
        j.e(mediaFile2, "mediaFile");
        this.mediaFile = mediaFile2;
    }

    private final i findAppSegment(FileChannel fileChannel, short s) {
        fileChannel.position(2);
        while (true) {
            long longValue = ((Number) findNextAppNMarker(fileChannel, new int[0]).d).longValue();
            if (longValue <= 0) {
                return null;
            }
            short s5 = CommonsKt.readAsShortBuffer$default(fileChannel, 1, (ByteOrder) null, 2, (Object) null).get(0);
            long j2 = CommonsKt.getLong(CommonsKt.readAsShortBuffer$default(fileChannel, 1, (ByteOrder) null, 2, (Object) null)) - ((long) 2);
            if (s5 == s) {
                return new i(Long.valueOf(longValue), Long.valueOf(j2));
            }
            CommonsKt.skipNBytes(fileChannel, (int) j2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0098, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0099, code lost:
        B1.a.g(r4, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x009c, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final me.i findXMPApp1Marker() {
        /*
            r11 = this;
            java.lang.String r0 = "getChannel(...)"
            r1 = 0
            java.lang.Long r3 = java.lang.Long.valueOf(r1)
            com.samsung.android.motionphoto.utils.v2.MediaFile r4 = r11.mediaFile
            java.io.FileInputStream r4 = r4.newInputFileStream()
            java.nio.channels.FileChannel r5 = r4.getChannel()     // Catch:{ all -> 0x0088 }
            r6 = 2
            r5.position(r6)     // Catch:{ all -> 0x0088 }
        L_0x0017:
            java.nio.channels.FileChannel r5 = r4.getChannel()     // Catch:{ all -> 0x0088 }
            kotlin.jvm.internal.j.d(r5, r0)     // Catch:{ all -> 0x0088 }
            r6 = 1
            int[] r6 = new int[]{r6}     // Catch:{ all -> 0x0088 }
            me.i r5 = r11.findNextAppNMarker(r5, r6)     // Catch:{ all -> 0x0088 }
            java.lang.Object r5 = r5.d     // Catch:{ all -> 0x0088 }
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ all -> 0x0088 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x0088 }
            int r5 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r5 <= 0) goto L_0x008e
            java.nio.channels.FileChannel r5 = r4.getChannel()     // Catch:{ all -> 0x0088 }
            kotlin.jvm.internal.j.d(r5, r0)     // Catch:{ all -> 0x0088 }
            r6 = 0
            r7 = 2
            r8 = 0
            me.n r5 = com.samsung.android.motionphoto.utils.v2.io.JPEGMetaBase.getMeta$default(r11, r5, r6, r7, r8)     // Catch:{ all -> 0x0088 }
            java.lang.Object r6 = r5.d     // Catch:{ all -> 0x0088 }
            com.samsung.android.motionphoto.utils.v2.MetaType r6 = (com.samsung.android.motionphoto.utils.v2.MetaType) r6     // Catch:{ all -> 0x0088 }
            java.lang.Object r7 = r5.e     // Catch:{ all -> 0x0088 }
            java.lang.Number r7 = (java.lang.Number) r7     // Catch:{ all -> 0x0088 }
            long r7 = r7.longValue()     // Catch:{ all -> 0x0088 }
            java.lang.Object r5 = r5.f     // Catch:{ all -> 0x0088 }
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ all -> 0x0088 }
            long r9 = r5.longValue()     // Catch:{ all -> 0x0088 }
            com.samsung.android.motionphoto.utils.v2.MetaType r5 = com.samsung.android.motionphoto.utils.v2.MetaType.META_XMP     // Catch:{ all -> 0x0088 }
            if (r6 != r5) goto L_0x008a
            java.lang.String r11 = TAG     // Catch:{ all -> 0x0088 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0088 }
            r0.<init>()     // Catch:{ all -> 0x0088 }
            java.lang.String r1 = "found xmp on position="
            r0.append(r1)     // Catch:{ all -> 0x0088 }
            r0.append(r7)     // Catch:{ all -> 0x0088 }
            java.lang.String r1 = ", payload="
            r0.append(r1)     // Catch:{ all -> 0x0088 }
            r0.append(r9)     // Catch:{ all -> 0x0088 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0088 }
            com.samsung.android.sum.core.SLog.d((java.lang.String) r11, (java.lang.String) r0)     // Catch:{ all -> 0x0088 }
            java.lang.Long r11 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x0088 }
            java.lang.Long r0 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x0088 }
            me.i r1 = new me.i     // Catch:{ all -> 0x0088 }
            r1.<init>(r11, r0)     // Catch:{ all -> 0x0088 }
            r4.close()
            return r1
        L_0x0088:
            r11 = move-exception
            goto L_0x0097
        L_0x008a:
            r4.skipNBytes(r9)     // Catch:{ all -> 0x0088 }
            goto L_0x0017
        L_0x008e:
            me.i r11 = new me.i     // Catch:{ all -> 0x0088 }
            r11.<init>(r3, r3)     // Catch:{ all -> 0x0088 }
            r4.close()
            return r11
        L_0x0097:
            throw r11     // Catch:{ all -> 0x0098 }
        L_0x0098:
            r0 = move-exception
            B1.a.g(r4, r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.io.JPEGMetaWriter.findXMPApp1Marker():me.i");
    }

    /* access modifiers changed from: private */
    public static final x removeCameraDebugInfo$lambda$5(JPEGMetaWriter jPEGMetaWriter, FileChannel fileChannel) {
        j.e(fileChannel, "fc");
        i findAppSegment = jPEGMetaWriter.findAppSegment(fileChannel, -28);
        x xVar = x.f4917a;
        if (findAppSegment == null) {
            return xVar;
        }
        fileChannel.position(((Number) findAppSegment.d).longValue());
        FileChannel fileChannel2 = fileChannel;
        CommonsKt.shiftLeftSafely$default(fileChannel2, (int) (((long) 4) + ((Number) findAppSegment.e).longValue()), 0, false, 6, (Object) null);
        return xVar;
    }

    private final ImageMetaReader.Box reserveXMPByCopy(int i2) {
        Throwable th;
        SLog.d(TAG, "reserveXMPByCopy: " + i2);
        FileInputStream newInputFileStream = this.mediaFile.newInputFileStream();
        try {
            long j2 = 2;
            newInputFileStream.getChannel().position(2);
            ByteBuffer byteBuffer = null;
            while (true) {
                FileChannel channel = newInputFileStream.getChannel();
                j.d(channel, "getChannel(...)");
                if (((Number) findNextAppNMarker(channel, 1).d).longValue() > 0) {
                    FileChannel channel2 = newInputFileStream.getChannel();
                    j.d(channel2, "getChannel(...)");
                    n meta$default = JPEGMetaBase.getMeta$default(this, channel2, 0, 2, (Object) null);
                    long longValue = ((Number) meta$default.e).longValue();
                    long longValue2 = ((Number) meta$default.f).longValue();
                    int i7 = WhenMappings.$EnumSwitchMapping$0[((MetaType) meta$default.d).ordinal()];
                    if (i7 == 1) {
                        SLog.d(TAG, "found exif: pos=" + longValue + ", payload=" + longValue2);
                        long j3 = longValue + longValue2;
                        newInputFileStream.getChannel().position(j3);
                        long size = newInputFileStream.getChannel().size() - newInputFileStream.getChannel().position();
                        long j8 = (long) 2;
                        ByteBuffer allocate = ByteBuffer.allocate((int) (size + j8 + j8 + ((long) i2)));
                        allocate.putShort(-31);
                        allocate.putShort((short) (i2 + 2));
                        newInputFileStream.read(allocate.array(), allocate.position() + i2, (int) size);
                        allocate.rewind();
                        j2 = j3 + j8 + j8;
                        byteBuffer = allocate;
                    } else if (i7 != 2) {
                        newInputFileStream.skipNBytes(longValue2);
                    } else {
                        throw new v0("An operation is not implemented: " + "do handle this", 2);
                    }
                } else {
                    j.b(byteBuffer);
                    Long valueOf = Long.valueOf(j2);
                    newInputFileStream.close();
                    long longValue3 = valueOf.longValue();
                    this.mediaFile.useOutputFileChannel(new b(longValue3, byteBuffer, 1));
                    return new ImageMetaReader.Box(longValue3, (long) i2, getByteReader());
                }
            }
        } catch (Throwable th2) {
            Throwable th3 = th2;
            a.g(newInputFileStream, th);
            throw th3;
        }
    }

    /* access modifiers changed from: private */
    public static final FileChannel reserveXMPByCopy$lambda$3(long j2, ByteBuffer byteBuffer, FileChannel fileChannel) {
        j.e(fileChannel, "it");
        long j3 = j2 - ((long) 4);
        fileChannel.position(j3);
        fileChannel.write(byteBuffer);
        return fileChannel.truncate(j3 + ((long) byteBuffer.limit()));
    }

    private final ImageMetaReader.Box reserveXMPByMove(int i2) {
        String str = TAG;
        SLog.d(str, "reserveXMPByMove: " + i2);
        return (ImageMetaReader.Box) this.mediaFile.useFileChannel(new StandardOpenOption[]{StandardOpenOption.READ, StandardOpenOption.WRITE}, new dd.a(this, i2, 3));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0197  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x01ac  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0319  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x032e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader.Box reserveXMPByMove$lambda$0(com.samsung.android.motionphoto.utils.v2.io.JPEGMetaWriter r35, int r36, java.nio.channels.FileChannel r37) {
        /*
            r0 = r35
            r2 = r37
            r3 = -31
            java.lang.Short r9 = java.lang.Short.valueOf(r3)
            java.lang.String r3 = "it"
            kotlin.jvm.internal.j.e(r2, r3)
            r10 = 2
            r2.position(r10)
            r2.position()
            r12 = 0
            r13 = r12
        L_0x0019:
            r3 = 1
            int[] r4 = new int[]{r3}
            me.i r4 = r0.findNextAppNMarker(r2, r4)
            java.lang.Object r4 = r4.d
            java.lang.Number r4 = (java.lang.Number) r4
            long r4 = r4.longValue()
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            java.lang.Class r8 = java.lang.Short.TYPE
            java.lang.String r14 = "getBytes(...)"
            java.lang.Class<java.lang.String> r15 = java.lang.String.class
            java.lang.String r5 = "null cannot be cast to non-null type kotlin.Long"
            r16 = 8
            java.lang.Class r6 = java.lang.Long.TYPE
            java.lang.String r7 = "null cannot be cast to non-null type kotlin.Int"
            java.lang.Class r10 = java.lang.Integer.TYPE
            java.lang.String r11 = "null cannot be cast to non-null type kotlin.Short"
            r19 = r4
            java.lang.Class<java.lang.Short> r4 = java.lang.Short.class
            r20 = r4
            r4 = 2
            r21 = r5
            r22 = r6
            r6 = 0
            if (r19 <= 0) goto L_0x023f
            me.n r5 = com.samsung.android.motionphoto.utils.v2.io.JPEGMetaBase.getMeta$default(r0, r2, r6, r4, r12)
            java.lang.Object r6 = r5.d
            com.samsung.android.motionphoto.utils.v2.MetaType r6 = (com.samsung.android.motionphoto.utils.v2.MetaType) r6
            java.lang.Object r12 = r5.e
            java.lang.Number r12 = (java.lang.Number) r12
            long r3 = r12.longValue()
            java.lang.Object r5 = r5.f
            java.lang.Number r5 = (java.lang.Number) r5
            r12 = r6
            long r5 = r5.longValue()
            int[] r25 = com.samsung.android.motionphoto.utils.v2.io.JPEGMetaWriter.WhenMappings.$EnumSwitchMapping$0
            int r12 = r12.ordinal()
            r12 = r25[r12]
            java.lang.String r0 = ", payload="
            r25 = r7
            r7 = 1
            if (r12 == r7) goto L_0x00ac
            r7 = 2
            if (r12 == r7) goto L_0x007e
            int r0 = (int) r5
            com.samsung.android.motionphoto.utils.v2.CommonsKt.skipNBytes(r2, r0)
            goto L_0x00a6
        L_0x007e:
            java.lang.String r7 = TAG
            java.lang.String r8 = "found xmp: pos="
            java.lang.StringBuilder r0 = N2.j.j(r3, r8, r0)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            com.samsung.android.sum.core.SLog.d((java.lang.String) r7, (java.lang.String) r0)
            r0 = 4
            long r7 = (long) r0
            long r10 = r3 - r7
            long r5 = r5 + r7
            long r3 = r10 + r5
            r2.position(r3)
            int r3 = (int) r5
            r7 = 6
            r8 = 0
            r4 = 0
            r6 = 0
            com.samsung.android.motionphoto.utils.v2.CommonsKt.shiftLeftSafely$default(r2, r3, r4, r6, r7, r8)
            r2.position(r10)
        L_0x00a6:
            r3 = r36
            r19 = r9
            goto L_0x022a
        L_0x00ac:
            r7 = 2
            java.lang.String r12 = TAG
            java.lang.String r13 = "found exif: pos="
            java.lang.StringBuilder r0 = N2.j.j(r3, r13, r0)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            com.samsung.android.sum.core.SLog.d((java.lang.String) r12, (java.lang.String) r0)
            long r12 = r3 + r5
            r0 = 4
            long r3 = (long) r0
            long r27 = r12 + r3
            r2.position(r12)
            int r3 = r36 + 4
            r6 = 2
            r24 = r7
            r7 = 0
            r4 = 0
            r1 = r21
            r21 = r0
            r0 = r1
            r1 = r20
            r20 = r14
            r14 = r1
            r19 = r9
            r9 = r22
            r1 = r25
            r23 = 0
            com.samsung.android.motionphoto.utils.v2.CommonsKt.shiftRightSafely$default(r2, r3, r4, r6, r7)
            r2.position(r12)
            java.lang.Short[] r29 = new java.lang.Short[]{r19}
            kotlin.jvm.internal.w r3 = kotlin.jvm.internal.v.f4727a
            He.d r4 = r3.b(r14)
            He.d r5 = r3.b(r8)
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0113
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r24)
            r5 = r29[r23]
            kotlin.jvm.internal.j.c(r5, r11)
            short r5 = r5.shortValue()
            r4.putShort(r5)
            byte[] r4 = r4.array()
        L_0x0110:
            r12 = r20
            goto L_0x0178
        L_0x0113:
            He.d r5 = r3.b(r10)
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0134
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r21)
            r5 = r29[r23]
            kotlin.jvm.internal.j.c(r5, r1)
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            r4.putInt(r5)
            byte[] r4 = r4.array()
            goto L_0x0110
        L_0x0134:
            He.d r5 = r3.b(r9)
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0155
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r16)
            r5 = r29[r23]
            kotlin.jvm.internal.j.c(r5, r0)
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            r4.putLong(r5)
            byte[] r4 = r4.array()
            goto L_0x0110
        L_0x0155:
            He.d r5 = r3.b(r15)
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0239
            r33 = 0
            r34 = 63
            r30 = 0
            r31 = 0
            r32 = 0
            java.lang.String r4 = ne.C1192j.s0(r29, r30, r31, r32, r33, r34)
            java.nio.charset.Charset r5 = Tf.a.f3815a
            byte[] r4 = r4.getBytes(r5)
            r12 = r20
            kotlin.jvm.internal.j.d(r4, r12)
        L_0x0178:
            kotlin.jvm.internal.j.b(r4)
            com.samsung.android.motionphoto.utils.v2.CommonsKt.write((java.nio.channels.FileChannel) r2, (byte[]) r4)
            int r4 = r36 + 2
            short r4 = (short) r4
            java.lang.Short r4 = java.lang.Short.valueOf(r4)
            java.lang.Short[] r29 = new java.lang.Short[]{r4}
            He.d r4 = r3.b(r14)
            He.d r5 = r3.b(r8)
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x01ac
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r24)
            r1 = r29[r23]
            kotlin.jvm.internal.j.c(r1, r11)
            short r1 = r1.shortValue()
            r0.putShort(r1)
            byte[] r0 = r0.array()
            goto L_0x020f
        L_0x01ac:
            He.d r5 = r3.b(r10)
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x01cd
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r21)
            r3 = r29[r23]
            kotlin.jvm.internal.j.c(r3, r1)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r1 = r3.intValue()
            r0.putInt(r1)
            byte[] r0 = r0.array()
            goto L_0x020f
        L_0x01cd:
            He.d r1 = r3.b(r9)
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L_0x01ee
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r16)
            r3 = r29[r23]
            kotlin.jvm.internal.j.c(r3, r0)
            java.lang.Long r3 = (java.lang.Long) r3
            long r3 = r3.longValue()
            r1.putLong(r3)
            byte[] r0 = r1.array()
            goto L_0x020f
        L_0x01ee:
            He.d r0 = r3.b(r15)
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0233
            r33 = 0
            r34 = 63
            r30 = 0
            r31 = 0
            r32 = 0
            java.lang.String r0 = ne.C1192j.s0(r29, r30, r31, r32, r33, r34)
            java.nio.charset.Charset r1 = Tf.a.f3815a
            byte[] r0 = r0.getBytes(r1)
            kotlin.jvm.internal.j.d(r0, r12)
        L_0x020f:
            kotlin.jvm.internal.j.b(r0)
            com.samsung.android.motionphoto.utils.v2.CommonsKt.write((java.nio.channels.FileChannel) r2, (byte[]) r0)
            r3 = r36
            long r0 = (long) r3
            long r4 = r27 + r0
            r2.position(r4)
            com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader$Box r26 = new com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader$Box
            com.samsung.android.motionphoto.utils.v2.io.MediaByteReader r31 = r35.getByteReader()
            r29 = r0
            r26.<init>(r27, r29, r31)
            r13 = r26
        L_0x022a:
            r10 = 2
            r12 = 0
            r0 = r35
            r9 = r19
            goto L_0x0019
        L_0x0233:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            throw r0
        L_0x0239:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            throw r0
        L_0x023f:
            r3 = r36
            r24 = r4
            r23 = r6
            r1 = r7
            r19 = r9
            r12 = r14
            r14 = r20
            r0 = r21
            r9 = r22
            r21 = 4
            if (r13 != 0) goto L_0x03bc
            r4 = 2
            r2.position(r4)
            int r3 = r36 + 4
            r6 = 2
            r7 = 0
            r17 = r4
            r4 = 0
            r20 = r12
            r12 = r17
            com.samsung.android.motionphoto.utils.v2.CommonsKt.shiftRightSafely$default(r2, r3, r4, r6, r7)
            r2.position(r12)
            java.lang.Short[] r25 = new java.lang.Short[]{r19}
            kotlin.jvm.internal.w r3 = kotlin.jvm.internal.v.f4727a
            He.d r4 = r3.b(r14)
            He.d r5 = r3.b(r8)
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0295
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r24)
            r5 = r25[r23]
            kotlin.jvm.internal.j.c(r5, r11)
            short r5 = r5.shortValue()
            r4.putShort(r5)
            byte[] r4 = r4.array()
        L_0x0292:
            r12 = r20
            goto L_0x02fa
        L_0x0295:
            He.d r5 = r3.b(r10)
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x02b6
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r21)
            r5 = r25[r23]
            kotlin.jvm.internal.j.c(r5, r1)
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            r4.putInt(r5)
            byte[] r4 = r4.array()
            goto L_0x0292
        L_0x02b6:
            He.d r5 = r3.b(r9)
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x02d7
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r16)
            r5 = r25[r23]
            kotlin.jvm.internal.j.c(r5, r0)
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            r4.putLong(r5)
            byte[] r4 = r4.array()
            goto L_0x0292
        L_0x02d7:
            He.d r5 = r3.b(r15)
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x03b6
            r29 = 0
            r30 = 63
            r26 = 0
            r27 = 0
            r28 = 0
            java.lang.String r4 = ne.C1192j.s0(r25, r26, r27, r28, r29, r30)
            java.nio.charset.Charset r5 = Tf.a.f3815a
            byte[] r4 = r4.getBytes(r5)
            r12 = r20
            kotlin.jvm.internal.j.d(r4, r12)
        L_0x02fa:
            kotlin.jvm.internal.j.b(r4)
            com.samsung.android.motionphoto.utils.v2.CommonsKt.write((java.nio.channels.FileChannel) r2, (byte[]) r4)
            int r4 = r36 + 2
            short r4 = (short) r4
            java.lang.Short r4 = java.lang.Short.valueOf(r4)
            java.lang.Short[] r25 = new java.lang.Short[]{r4}
            He.d r4 = r3.b(r14)
            He.d r5 = r3.b(r8)
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x032e
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r24)
            r1 = r25[r23]
            kotlin.jvm.internal.j.c(r1, r11)
            short r1 = r1.shortValue()
            r0.putShort(r1)
            byte[] r0 = r0.array()
            goto L_0x0391
        L_0x032e:
            He.d r5 = r3.b(r10)
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x034f
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r21)
            r3 = r25[r23]
            kotlin.jvm.internal.j.c(r3, r1)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r1 = r3.intValue()
            r0.putInt(r1)
            byte[] r0 = r0.array()
            goto L_0x0391
        L_0x034f:
            He.d r1 = r3.b(r9)
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L_0x0370
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r16)
            r3 = r25[r23]
            kotlin.jvm.internal.j.c(r3, r0)
            java.lang.Long r3 = (java.lang.Long) r3
            long r3 = r3.longValue()
            r1.putLong(r3)
            byte[] r0 = r1.array()
            goto L_0x0391
        L_0x0370:
            He.d r0 = r3.b(r15)
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x03b0
            r29 = 0
            r30 = 63
            r26 = 0
            r27 = 0
            r28 = 0
            java.lang.String r0 = ne.C1192j.s0(r25, r26, r27, r28, r29, r30)
            java.nio.charset.Charset r1 = Tf.a.f3815a
            byte[] r0 = r0.getBytes(r1)
            kotlin.jvm.internal.j.d(r0, r12)
        L_0x0391:
            kotlin.jvm.internal.j.b(r0)
            com.samsung.android.motionphoto.utils.v2.CommonsKt.write((java.nio.channels.FileChannel) r2, (byte[]) r0)
            r0 = r21
            long r0 = (long) r0
            r17 = 2
            long r3 = r17 + r0
            r1 = r36
            long r5 = (long) r1
            long r0 = r3 + r5
            r2.position(r0)
            com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader$Box r2 = new com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader$Box
            com.samsung.android.motionphoto.utils.v2.io.MediaByteReader r7 = r35.getByteReader()
            r2.<init>(r3, r5, r7)
            return r2
        L_0x03b0:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            throw r0
        L_0x03b6:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            throw r0
        L_0x03bc:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.io.JPEGMetaWriter.reserveXMPByMove$lambda$0(com.samsung.android.motionphoto.utils.v2.io.JPEGMetaWriter, int, java.nio.channels.FileChannel):com.samsung.android.motionphoto.utils.v2.io.ImageMetaReader$Box");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x016b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void writeApp4(java.nio.channels.FileChannel r28, byte[] r29, long r30) {
        /*
            r27 = this;
            r0 = r27
            r1 = r28
            r6 = r29
            r2 = 2
            int r2 = (r30 > r2 ? 1 : (r30 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x0055
            long r2 = r1.position()
        L_0x0010:
            r4 = 1
            int[] r4 = new int[]{r4}
            me.i r4 = r0.findNextAppNMarker(r1, r4)
            java.lang.Object r5 = r4.d
            java.lang.Number r5 = (java.lang.Number) r5
            long r7 = r5.longValue()
            java.lang.Object r4 = r4.e
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            r9 = 0
            int r5 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r5 <= 0) goto L_0x0053
            r5 = 65504(0xffe0, float:9.179E-41)
            int r4 = r4 + r5
            short r4 = (short) r4
            me.n r5 = r0.getMeta((java.nio.channels.FileChannel) r1, (short) r4)
            java.lang.Object r7 = r5.e
            java.lang.Number r7 = (java.lang.Number) r7
            long r7 = r7.longValue()
            java.lang.Object r5 = r5.f
            java.lang.Number r5 = (java.lang.Number) r5
            long r9 = r5.longValue()
            r5 = -31
            if (r4 != r5) goto L_0x004e
            long r2 = r7 + r9
        L_0x004e:
            int r4 = (int) r9
            com.samsung.android.motionphoto.utils.v2.CommonsKt.skipNBytes(r1, r4)
            goto L_0x0010
        L_0x0053:
            r7 = r2
            goto L_0x0057
        L_0x0055:
            r7 = r30
        L_0x0057:
            int r0 = r6.length
            r9 = 4
            int r0 = r0 + r9
            r1.position(r7)
            r4 = 2
            r5 = 0
            r2 = 0
            r26 = r1
            r1 = r0
            r0 = r26
            com.samsung.android.motionphoto.utils.v2.CommonsKt.shiftRightSafely$default(r0, r1, r2, r4, r5)
            r0.position(r7)
            r1 = -28
            java.lang.Short r1 = java.lang.Short.valueOf(r1)
            java.lang.Short[] r10 = new java.lang.Short[]{r1}
            kotlin.jvm.internal.w r1 = kotlin.jvm.internal.v.f4727a
            java.lang.Class<java.lang.Short> r2 = java.lang.Short.class
            He.d r3 = r1.b(r2)
            java.lang.Class r4 = java.lang.Short.TYPE
            He.d r5 = r1.b(r4)
            boolean r5 = r3.equals(r5)
            java.lang.String r7 = "getBytes(...)"
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            java.lang.String r11 = "null cannot be cast to non-null type kotlin.Long"
            r16 = 8
            java.lang.Class r12 = java.lang.Long.TYPE
            java.lang.String r13 = "null cannot be cast to non-null type kotlin.Int"
            java.lang.Class r14 = java.lang.Integer.TYPE
            java.lang.String r15 = "null cannot be cast to non-null type kotlin.Short"
            r17 = 2
            r18 = 0
            if (r5 == 0) goto L_0x00bf
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r17)
            r5 = r10[r18]
            kotlin.jvm.internal.j.c(r5, r15)
            short r5 = r5.shortValue()
            r3.putShort(r5)
            byte[] r3 = r3.array()
        L_0x00b2:
            r10 = r3
            r30 = r8
            r27 = r9
        L_0x00b7:
            r19 = r11
            r3 = r12
            r5 = r13
            r8 = r14
            r9 = r15
            goto L_0x0136
        L_0x00bf:
            He.d r5 = r1.b(r14)
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x00e0
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r9)
            r5 = r10[r18]
            kotlin.jvm.internal.j.c(r5, r13)
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            r3.putInt(r5)
            byte[] r3 = r3.array()
            goto L_0x00b2
        L_0x00e0:
            He.d r5 = r1.b(r12)
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0106
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r16)
            r5 = r10[r18]
            kotlin.jvm.internal.j.c(r5, r11)
            java.lang.Long r5 = (java.lang.Long) r5
            r27 = r9
            long r9 = r5.longValue()
            r3.putLong(r9)
            byte[] r3 = r3.array()
            r10 = r3
            r30 = r8
            goto L_0x00b7
        L_0x0106:
            r27 = r9
            He.d r5 = r1.b(r8)
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x01e6
            r3 = r14
            r14 = 0
            r5 = r15
            r15 = 63
            r9 = r11
            r11 = 0
            r19 = r12
            r12 = 0
            r20 = r13
            r13 = 0
            r30 = r8
            r8 = r3
            r3 = r19
            r19 = r9
            r9 = r5
            r5 = r20
            java.lang.String r10 = ne.C1192j.s0(r10, r11, r12, r13, r14, r15)
            java.nio.charset.Charset r11 = Tf.a.f3815a
            byte[] r10 = r10.getBytes(r11)
            kotlin.jvm.internal.j.d(r10, r7)
        L_0x0136:
            kotlin.jvm.internal.j.b(r10)
            com.samsung.android.motionphoto.utils.v2.CommonsKt.write((java.nio.channels.FileChannel) r0, (byte[]) r10)
            int r10 = r6.length
            int r10 = r10 + 2
            short r10 = (short) r10
            java.lang.Short r10 = java.lang.Short.valueOf(r10)
            java.lang.Short[] r20 = new java.lang.Short[]{r10}
            He.d r2 = r1.b(r2)
            He.d r4 = r1.b(r4)
            boolean r4 = r2.equals(r4)
            if (r4 == 0) goto L_0x016b
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r17)
            r2 = r20[r18]
            kotlin.jvm.internal.j.c(r2, r9)
            short r2 = r2.shortValue()
            r1.putShort(r2)
            byte[] r1 = r1.array()
            goto L_0x01d2
        L_0x016b:
            He.d r4 = r1.b(r8)
            boolean r4 = r2.equals(r4)
            if (r4 == 0) goto L_0x018c
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r27)
            r2 = r20[r18]
            kotlin.jvm.internal.j.c(r2, r5)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r1.putInt(r2)
            byte[] r1 = r1.array()
            goto L_0x01d2
        L_0x018c:
            He.d r3 = r1.b(r3)
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x01af
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r16)
            r2 = r20[r18]
            r9 = r19
            kotlin.jvm.internal.j.c(r2, r9)
            java.lang.Long r2 = (java.lang.Long) r2
            long r2 = r2.longValue()
            r1.putLong(r2)
            byte[] r1 = r1.array()
            goto L_0x01d2
        L_0x01af:
            r3 = r30
            He.d r1 = r1.b(r3)
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x01e0
            r24 = 0
            r25 = 63
            r21 = 0
            r22 = 0
            r23 = 0
            java.lang.String r1 = ne.C1192j.s0(r20, r21, r22, r23, r24, r25)
            java.nio.charset.Charset r2 = Tf.a.f3815a
            byte[] r1 = r1.getBytes(r2)
            kotlin.jvm.internal.j.d(r1, r7)
        L_0x01d2:
            kotlin.jvm.internal.j.b(r1)
            com.samsung.android.motionphoto.utils.v2.CommonsKt.write((java.nio.channels.FileChannel) r0, (byte[]) r1)
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.wrap(r6)
            r0.write(r1)
            return
        L_0x01e0:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            throw r0
        L_0x01e6:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.io.JPEGMetaWriter.writeApp4(java.nio.channels.FileChannel, byte[], long):void");
    }

    /* access modifiers changed from: private */
    public static final x writeCameraDebugInfo$lambda$4(JPEGMetaWriter jPEGMetaWriter, byte[] bArr, FileChannel fileChannel) {
        FileChannel fileChannel2 = fileChannel;
        j.e(fileChannel, "fc");
        i findAppSegment = jPEGMetaWriter.findAppSegment(fileChannel, -28);
        fileChannel.position(2);
        x xVar = x.f4917a;
        if (findAppSegment != null) {
            long longValue = ((Number) findAppSegment.d).longValue();
            long longValue2 = ((Number) findAppSegment.e).longValue();
            int i2 = (int) (((long) 4) + longValue2);
            long j2 = (long) 2;
            long j3 = longValue + j2 + j2;
            if (((int) longValue2) == bArr.length) {
                fileChannel.position(j3);
                fileChannel.write(ByteBuffer.wrap(bArr));
            } else {
                fileChannel.position(longValue);
                CommonsKt.shiftLeftSafely$default(fileChannel2, i2, 0, false, 6, (Object) null);
                jPEGMetaWriter.writeApp4(fileChannel, bArr, longValue);
            }
            return xVar;
        }
        jPEGMetaWriter.writeApp4(fileChannel, bArr, fileChannel.position());
        return xVar;
    }

    /* access modifiers changed from: private */
    public static final int writeXMP$lambda$9(t tVar, t tVar2, ByteBuffer byteBuffer, FileChannel fileChannel) {
        j.e(fileChannel, "it");
        String str = TAG;
        long j2 = tVar.d;
        long j3 = tVar2.d;
        int limit = byteBuffer.limit();
        StringBuilder j8 = N2.j.j(j2, "write xmp at ", "[cap=");
        j8.append(j3);
        j8.append("] w/ size=");
        j8.append(limit);
        SLog.i(str, j8.toString());
        fileChannel.position(tVar.d);
        fileChannel.write(byteBuffer);
        int limit2 = ((int) tVar2.d) - byteBuffer.limit();
        if (limit2 < 0) {
            limit2 = 0;
        }
        return CommonsKt.write(fileChannel, (byte) 32, limit2);
    }

    public void removeCameraDebugInfo() {
        this.mediaFile.useFileChannel(new StandardOpenOption[]{StandardOpenOption.READ, StandardOpenOption.WRITE}, new f(11, (Object) this));
    }

    public void removeXMP() {
        throw new v0("An operation is not implemented: Not yet implemented", 2);
    }

    public ImageMetaReader.Box reserveXMP(int i2) {
        String str = TAG;
        SLog.d(str, "reserveXMP: size=" + i2);
        try {
            return reserveXMPByMove(i2);
        } catch (Exception unused) {
            return reserveXMPByCopy(i2);
        }
    }

    public void writeCameraDebugInfo(byte[] bArr) {
        j.e(bArr, "data");
        if (bArr.length != 0) {
            this.mediaFile.useFileChannel(new StandardOpenOption[]{StandardOpenOption.READ, StandardOpenOption.WRITE}, new c(5, this, bArr));
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public void writeExif(ExifInfo exifInfo) {
        j.e(exifInfo, "exifInfo");
        throw new v0("An operation is not implemented: Not yet implemented", 2);
    }

    /* JADX WARNING: type inference failed for: r0v6, types: [kotlin.jvm.internal.t, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v4, types: [kotlin.jvm.internal.t, java.lang.Object] */
    public void writeXMP(XMPInfo xMPInfo) {
        j.e(xMPInfo, "xmpInfo");
        if (!xMPInfo.edit().isDirty()) {
            SLog.i(TAG, "xmpInfo is clean, skip write");
            return;
        }
        XMPMeta xmp = ((XMPInfoImpl) xMPInfo).getXmp();
        SerializeOptions serializeOptions = new SerializeOptions();
        serializeOptions.setOmitPacketWrapper(true);
        serializeOptions.setUseCompactFormat(true);
        byte[] serializeToBuffer = XMPMetaFactory.serializeToBuffer(xmp, serializeOptions);
        byte[] bytes = MediaDefs.Meta.XMP.XMP_SIGNATURE.getBytes(Tf.a.f3815a);
        j.d(bytes, "getBytes(...)");
        ByteBuffer allocate = ByteBuffer.allocate(serializeToBuffer.length + bytes.length);
        allocate.put(bytes);
        allocate.put(serializeToBuffer);
        allocate.flip();
        i findXMPApp1Marker = findXMPApp1Marker();
        ? obj = new Object();
        obj.d = ((Number) findXMPApp1Marker.d).longValue();
        ? obj2 = new Object();
        long longValue = ((Number) findXMPApp1Marker.e).longValue();
        obj2.d = longValue;
        String str = TAG;
        long j2 = obj.d;
        int limit = allocate.limit();
        StringBuilder j3 = N2.j.j(j2, "found xmp[pos=", ", size=");
        j3.append(longValue);
        j3.append("], xmpBoxBuffer[");
        j3.append(limit);
        j3.append("]");
        SLog.i(str, j3.toString());
        if (obj2.d < ((long) allocate.limit())) {
            ImageMetaReader.Box reserveXMP = reserveXMP(Math.max(MediaDefs.Meta.XMP.XMP_MIX_RESERVED_SIZE, CommonsKt.align(allocate.limit(), 64)));
            obj.d = reserveXMP.getOffset();
            obj2.d = reserveXMP.getLength();
        }
        this.mediaFile.useOutputFileChannel(new h(obj, obj2, allocate, 2));
    }
}
