package com.samsung.android.motionphoto.utils.v2.io;

import Tf.v;
import com.samsung.android.motionphoto.utils.v2.CommonsKt;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.motionphoto.utils.v2.MediaFile;
import com.samsung.android.motionphoto.utils.v2.MetaType;
import com.samsung.android.sum.core.SLog;
import java.io.FileInputStream;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;
import me.n;
import ne.z;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000 \u00182\u00020\u0001:\u0006\u0019\u001a\u001b\u001c\u001d\u0018B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J1\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0004¢\u0006\u0004\b\r\u0010\u000eJ3\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\n2\u0006\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\bH\u0004¢\u0006\u0004\b\r\u0010\u0011J/\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130\u00152\u0006\u0010\u0010\u001a\u00020\u000f2\n\u0010\u0014\u001a\u00020\u0012\"\u00020\u0013H\u0004¢\u0006\u0004\b\u0016\u0010\u0017¨\u0006\u001e"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaBase;", "Lcom/samsung/android/motionphoto/utils/v2/io/ImageMetaBase;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "mediaFile", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "Ljava/io/FileInputStream;", "fis", "", "marker", "Lme/n;", "Lcom/samsung/android/motionphoto/utils/v2/MetaType;", "", "getMeta", "(Ljava/io/FileInputStream;S)Lme/n;", "Ljava/nio/channels/FileChannel;", "fc", "(Ljava/nio/channels/FileChannel;S)Lme/n;", "", "", "whiches", "Lme/i;", "findNextAppNMarker", "(Ljava/nio/channels/FileChannel;[I)Lme/i;", "Companion", "MetaTypeDetectorRegistry", "MetaTypeDetector", "App1MetaTypeDetector", "App4MetaTypeDetector", "DefaultMetaTypeDetector", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class JPEGMetaBase extends ImageMetaBase {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaBase$App1MetaTypeDetector;", "Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaBase$MetaTypeDetector;", "<init>", "()V", "detect", "Lcom/samsung/android/motionphoto/utils/v2/MetaType;", "header", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class App1MetaTypeDetector implements MetaTypeDetector {
        public MetaType detect(String str) {
            j.e(str, "header");
            if (v.t0(str, MediaDefs.Meta.XMP.XMP_SIGNATURE)) {
                return MetaType.META_XMP;
            }
            if (v.t0(str, MediaDefs.Image.HEIF.HEIF_EXIF_BOX)) {
                return MetaType.META_EXIF;
            }
            return MetaType.META_UNKNOWN;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaBase$App4MetaTypeDetector;", "Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaBase$MetaTypeDetector;", "<init>", "()V", "detect", "Lcom/samsung/android/motionphoto/utils/v2/MetaType;", "header", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class App4MetaTypeDetector implements MetaTypeDetector {
        public MetaType detect(String str) {
            j.e(str, "header");
            return MetaType.META_CAMERA_DEBUG;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaBase$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaBase$DefaultMetaTypeDetector;", "Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaBase$MetaTypeDetector;", "<init>", "()V", "detect", "Lcom/samsung/android/motionphoto/utils/v2/MetaType;", "header", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DefaultMetaTypeDetector implements MetaTypeDetector {
        public MetaType detect(String str) {
            j.e(str, "header");
            return MetaType.META_UNKNOWN;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaBase$MetaTypeDetector;", "", "detect", "Lcom/samsung/android/motionphoto/utils/v2/MetaType;", "header", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface MetaTypeDetector {
        MetaType detect(String str);
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\n\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0006R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaBase$MetaTypeDetectorRegistry;", "", "<init>", "()V", "detectors", "", "", "Lcom/samsung/android/motionphoto/utils/v2/io/JPEGMetaBase$MetaTypeDetector;", "getMetaTypeDetector", "marker", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MetaTypeDetectorRegistry {
        public static final MetaTypeDetectorRegistry INSTANCE = new MetaTypeDetectorRegistry();
        private static final Map<Short, MetaTypeDetector> detectors;

        static {
            i[] iVarArr = {new i((short) -31, new App1MetaTypeDetector()), new i((short) -28, new App4MetaTypeDetector())};
            LinkedHashMap linkedHashMap = new LinkedHashMap(z.Z(2));
            z.d0(linkedHashMap, iVarArr);
            detectors = linkedHashMap;
        }

        private MetaTypeDetectorRegistry() {
        }

        public final MetaTypeDetector getMetaTypeDetector(short s) {
            MetaTypeDetector metaTypeDetector = detectors.get(Short.valueOf(s));
            if (metaTypeDetector == null) {
                return new DefaultMetaTypeDetector();
            }
            return metaTypeDetector;
        }
    }

    static {
        String tagOf = SLog.tagOf(JPEGMetaBase.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JPEGMetaBase(MediaFile mediaFile) {
        super(mediaFile);
        j.e(mediaFile, "mediaFile");
    }

    public static /* synthetic */ n getMeta$default(JPEGMetaBase jPEGMetaBase, FileChannel fileChannel, short s, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 2) != 0) {
                s = -31;
            }
            return jPEGMetaBase.getMeta(fileChannel, s);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getMeta");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e0, code lost:
        return new me.i(java.lang.Long.valueOf(r10.position(r10.position() - ((long) 4)).position()), java.lang.Integer.valueOf(r9));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final me.i findNextAppNMarker(java.nio.channels.FileChannel r10, int... r11) {
        /*
            r9 = this;
            java.lang.String r9 = "fc"
            kotlin.jvm.internal.j.e(r10, r9)
            java.lang.String r9 = "whiches"
            kotlin.jvm.internal.j.e(r11, r9)
        L_0x000b:
            r9 = 1
            r0 = 0
            r1 = 2
            java.nio.ShortBuffer r2 = com.samsung.android.motionphoto.utils.v2.CommonsKt.readAsShortBufferOrNull$default((java.nio.channels.FileChannel) r10, (int) r9, (java.nio.ByteOrder) r0, (int) r1, (java.lang.Object) r0)
            if (r2 == 0) goto L_0x00f1
            r3 = 0
            short r2 = r2.get(r3)
            int r4 = r2 >> 8
            r5 = r4 & 255(0xff, float:3.57E-43)
            kotlin.jvm.internal.w r6 = kotlin.jvm.internal.v.f4727a
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            He.d r7 = r6.b(r7)
            java.lang.Class r8 = java.lang.Byte.TYPE
            He.d r6 = r6.b(r8)
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x0037
            byte r5 = (byte) r5
            java.lang.Byte r5 = java.lang.Byte.valueOf(r5)
            goto L_0x003b
        L_0x0037:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
        L_0x003b:
            boolean r6 = r5 instanceof java.lang.Integer
            r7 = 4
            if (r6 != 0) goto L_0x0041
            goto L_0x0049
        L_0x0041:
            int r5 = r5.intValue()
            r6 = 255(0xff, float:3.57E-43)
            if (r6 == r5) goto L_0x00a2
        L_0x0049:
            java.lang.String r10 = TAG
            int r11 = Tf.c.f3817a
            Tf.f r11 = Tf.f.d
            java.lang.String r0 = "format"
            kotlin.jvm.internal.j.e(r11, r0)
            boolean r0 = r11.f3820a
            if (r0 == 0) goto L_0x005b
            java.lang.String r0 = "0123456789ABCDEF"
            goto L_0x005d
        L_0x005b:
            java.lang.String r0 = "0123456789abcdef"
        L_0x005d:
            Tf.e r11 = r11.f3821c
            boolean r5 = r11.f3819a
            if (r5 == 0) goto L_0x0090
            int r11 = r2 >> 12
            r11 = r11 & 15
            char r11 = r0.charAt(r11)
            r4 = r4 & 15
            char r4 = r0.charAt(r4)
            int r5 = r2 >> 4
            r5 = r5 & 15
            char r5 = r0.charAt(r5)
            r2 = r2 & 15
            char r0 = r0.charAt(r2)
            char[] r2 = new char[r7]
            r2[r3] = r11
            r2[r9] = r4
            r2[r1] = r5
            r9 = 3
            r2[r9] = r0
            java.lang.String r9 = new java.lang.String
            r9.<init>(r2)
            goto L_0x0097
        L_0x0090:
            long r1 = (long) r2
            r9 = 16
            java.lang.String r9 = Tf.c.b(r1, r11, r0, r9)
        L_0x0097:
            java.lang.String r11 = "this is not valid markers: "
            java.lang.String r9 = r11.concat(r9)
            com.samsung.android.sum.core.SLog.w((java.lang.String) r10, (java.lang.String) r9)
            goto L_0x00f1
        L_0x00a2:
            r3 = -38
            if (r2 != r3) goto L_0x00a7
            goto L_0x00f1
        L_0x00a7:
            java.nio.ShortBuffer r9 = com.samsung.android.motionphoto.utils.v2.CommonsKt.readAsShortBufferOrNull$default((java.nio.channels.FileChannel) r10, (int) r9, (java.nio.ByteOrder) r0, (int) r1, (java.lang.Object) r0)
            if (r9 == 0) goto L_0x00f1
            long r3 = com.samsung.android.motionphoto.utils.v2.CommonsKt.getLong(r9)
            r9 = -31
            if (r9 > r2) goto L_0x00e9
            r9 = -27
            if (r2 >= r9) goto L_0x00e9
            r9 = r2 & 15
            int r0 = r11.length
            if (r0 != 0) goto L_0x00bf
            goto L_0x00c5
        L_0x00bf:
            boolean r0 = ne.C1192j.c0(r9, r11)
            if (r0 == 0) goto L_0x00e1
        L_0x00c5:
            long r0 = r10.position()
            long r2 = (long) r7
            long r0 = r0 - r2
            java.nio.channels.FileChannel r10 = r10.position(r0)
            long r10 = r10.position()
            java.lang.Long r10 = java.lang.Long.valueOf(r10)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            me.i r11 = new me.i
            r11.<init>(r10, r9)
            return r11
        L_0x00e1:
            long r0 = (long) r1
            long r3 = r3 - r0
            int r9 = (int) r3
            com.samsung.android.motionphoto.utils.v2.CommonsKt.skipNBytes(r10, r9)
            goto L_0x000b
        L_0x00e9:
            long r0 = (long) r1
            long r3 = r3 - r0
            int r9 = (int) r3
            com.samsung.android.motionphoto.utils.v2.CommonsKt.skipNBytes(r10, r9)
            goto L_0x000b
        L_0x00f1:
            r9 = -1
            java.lang.Long r9 = java.lang.Long.valueOf(r9)
            r10 = -1
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            me.i r11 = new me.i
            r11.<init>(r9, r10)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.io.JPEGMetaBase.findNextAppNMarker(java.nio.channels.FileChannel, int[]):me.i");
    }

    public final n getMeta(FileInputStream fileInputStream, short s) {
        j.e(fileInputStream, "fis");
        FileChannel channel = fileInputStream.getChannel();
        j.d(channel, "getChannel(...)");
        return getMeta(channel, s);
    }

    public final n getMeta(FileChannel fileChannel, short s) {
        j.e(fileChannel, "fc");
        CommonsKt.skipNBytes(fileChannel, 2);
        long j2 = CommonsKt.getLong(CommonsKt.readAsShortBuffer$default(fileChannel, 1, (ByteOrder) null, 2, (Object) null)) - ((long) 2);
        long position = fileChannel.position();
        String readAsString = CommonsKt.readAsString(fileChannel, 32);
        fileChannel.position(position);
        return new n(MetaTypeDetectorRegistry.INSTANCE.getMetaTypeDetector(s).detect(readAsString), Long.valueOf(position), Long.valueOf(j2));
    }
}
