package com.samsung.android.motionphoto.utils.v2;

import A.a;
import A4.I;
import Ae.b;
import Bd.C0725a;
import L1.d;
import Sf.q;
import androidx.core.util.Pair;
import c0.C0086a;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sum.core.SLog;
import i.C0212a;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;
import me.x;
import ne.C1192j;
import ne.C1194l;
import ne.C1195m;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 d2\u00020\u00012\u00020\u0002:\u0001dB\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0011\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0011\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0011\u0010\u0015J\u001f\u0010\u0011\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0016J\u001f\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0017J\u001f\u0010\u0011\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0011\u0010\u0018J\u001f\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0011\u0010\u0019J#\u0010\u001c\u001a\u00020\u00012\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u001a\"\u00020\rH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001c\u001a\u00020\u00012\n\u0010\u001f\u001a\u00020\u001e\"\u00020\tH\u0016¢\u0006\u0004\b\u001c\u0010 J\u000f\u0010\"\u001a\u00020!H\u0016¢\u0006\u0004\b\"\u0010#J\u0017\u0010\"\u001a\u00020!2\u0006\u0010$\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\"\u0010%J\r\u0010&\u001a\u00020!¢\u0006\u0004\b&\u0010#J\u0010\u0010'\u001a\u00020\tH\u0001¢\u0006\u0004\b'\u0010(J\u0010\u0010)\u001a\u00020\tH\u0001¢\u0006\u0004\b)\u0010(J4\u0010-\u001a&\u0012\f\u0012\n +*\u0004\u0018\u00010\r0\r +*\u0012\u0012\f\u0012\n +*\u0004\u0018\u00010\r0\r\u0018\u00010,0*H\u0001¢\u0006\u0004\b-\u0010.J\u001f\u00101\u001a\u00020\u00062\r\b\u0001\u00100\u001a\u00070\r¢\u0006\u0002\b/H\u0001¢\u0006\u0004\b1\u00102J\u0018\u00103\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0001¢\u0006\u0004\b3\u00104J \u00105\u001a\n +*\u0004\u0018\u00010\u000f0\u000f2\u0006\u0010\n\u001a\u00020\tH\u0001¢\u0006\u0004\b5\u00106J'\u00105\u001a\n +*\u0004\u0018\u00010\u000f0\u000f2\r\b\u0001\u0010\u000e\u001a\u00070\r¢\u0006\u0002\b/H\u0001¢\u0006\u0004\b5\u00107J/\u00105\u001a\n +*\u0004\u0018\u00010\u000f0\u000f2\u0006\u0010\n\u001a\u00020\t2\r\b\u0001\u0010\u000e\u001a\u00070\r¢\u0006\u0002\b/H\u0001¢\u0006\u0004\b5\u00108JX\u0010;\u001aB\u0012\f\u0012\n +*\u0004\u0018\u00010:0:\u0012\f\u0012\n +*\u0004\u0018\u00010\t0\t +* \u0012\f\u0012\n +*\u0004\u0018\u00010:0:\u0012\f\u0012\n +*\u0004\u0018\u00010\t0\t\u0018\u000109092\u0006\u0010\n\u001a\u00020\tH\u0001¢\u0006\u0004\b;\u0010<J_\u0010;\u001aB\u0012\f\u0012\n +*\u0004\u0018\u00010:0:\u0012\f\u0012\n +*\u0004\u0018\u00010\t0\t +* \u0012\f\u0012\n +*\u0004\u0018\u00010:0:\u0012\f\u0012\n +*\u0004\u0018\u00010\t0\t\u0018\u000109092\r\b\u0001\u0010\u000e\u001a\u00070\r¢\u0006\u0002\b/H\u0001¢\u0006\u0004\b;\u0010=J\u0010\u0010>\u001a\u00020:H\u0001¢\u0006\u0004\b>\u0010?J\u000f\u0010@\u001a\u00020!H\u0002¢\u0006\u0004\b@\u0010#J\u000f\u0010A\u001a\u00020!H\u0002¢\u0006\u0004\bA\u0010#J#\u0010D\u001a\b\u0012\u0004\u0012\u00020B0*2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020B0*H\u0002¢\u0006\u0004\bD\u0010EJ\u001d\u0010G\u001a\u00020F2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020B0,H\u0002¢\u0006\u0004\bG\u0010HJ\u0017\u0010J\u001a\u00020!2\u0006\u0010I\u001a\u00020\tH\u0002¢\u0006\u0004\bJ\u0010KJ\u000f\u0010L\u001a\u00020\u0006H\u0002¢\u0006\u0004\bL\u0010\bJ\u0017\u0010N\u001a\u00020\t2\u0006\u0010M\u001a\u00020\tH\u0002¢\u0006\u0004\bN\u0010OR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010P\u001a\u0004\bQ\u0010RR\u0018\u0010S\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bS\u0010TR!\u0010C\u001a\b\u0012\u0004\u0012\u00020B0*8BX\u0002¢\u0006\f\n\u0004\bU\u0010V\u001a\u0004\bW\u0010.R\u0016\u0010X\u001a\u00020\t8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bX\u0010YR\u0014\u0010\u0007\u001a\u00020\u00068@X\u0004¢\u0006\u0006\u001a\u0004\bZ\u0010\bR\u0014\u0010]\u001a\u00020\u00138BX\u0004¢\u0006\u0006\u001a\u0004\b[\u0010\\R\u0014\u0010_\u001a\u00020\u00138BX\u0004¢\u0006\u0006\u001a\u0004\b^\u0010\\R\u001c\u0010c\u001a\n +*\u0004\u0018\u00010`0`8BX\u0004¢\u0006\u0006\u001a\u0004\ba\u0010b¨\u0006e"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/SEFEditImpl;", "Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "Lcom/samsung/android/motionphoto/utils/v2/SEFInfo;", "sefInfo", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/SEFInfo;)V", "", "isDirty", "()Z", "", "type", "setPrimaryType", "(I)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "", "name", "", "data", "putData", "(ILjava/lang/String;[B)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "dataFile", "(ILjava/lang/String;Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "(I[B)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "(Ljava/lang/String;[B)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "(ILcom/samsung/android/motionphoto/utils/v2/MediaFile;)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "(Ljava/lang/String;Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "", "names", "removeData", "([Ljava/lang/String;)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "", "types", "([I)Lcom/samsung/android/motionphoto/utils/v2/SEFEdit;", "Lme/x;", "commit", "()V", "file", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;)V", "loadAllData", "getVersion", "()I", "getDataCount", "", "kotlin.jvm.PlatformType", "", "getKeys", "()Ljava/util/List;", "Lkotlin/jvm/internal/EnhancedNullability;", "key", "containsKey", "(Ljava/lang/String;)Z", "containsType", "(I)Z", "getData", "(I)[B", "(Ljava/lang/String;)[B", "(ILjava/lang/String;)[B", "Landroidx/core/util/Pair;", "", "getDataPositionLength", "(I)Landroidx/core/util/Pair;", "(Ljava/lang/String;)Landroidx/core/util/Pair;", "getSize", "()J", "makeDirty", "cleanDirty", "Lcom/samsung/android/motionphoto/utils/v2/SEFDataInfo;", "dataInfos", "updateAllSEFData", "(Ljava/util/List;)Ljava/util/List;", "Ljava/nio/ByteBuffer;", "getSEFDataBuffer", "(Ljava/util/List;)Ljava/nio/ByteBuffer;", "sefTailSize", "updateEachDataPosition", "(I)V", "isOneIOFiles", "numData", "getSEFTailSize", "(I)I", "Lcom/samsung/android/motionphoto/utils/v2/SEFInfo;", "getSefInfo", "()Lcom/samsung/android/motionphoto/utils/v2/SEFInfo;", "_outputFile", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "dataInfos$delegate", "Lme/f;", "getDataInfos", "primaryType", "I", "isDirty$motionphoto_utils_v2_0_17_release", "getInputFile", "()Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "inputFile", "getOutputFile", "outputFile", "Ljava/nio/ByteOrder;", "getByteOrder", "()Ljava/nio/ByteOrder;", "byteOrder", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SEFEditImpl implements SEFEdit, SEFInfo {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    private MediaFile _outputFile;
    private final f dataInfos$delegate = d.q(new q(6, this));
    private int primaryType;
    private final SEFInfo sefInfo;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/SEFEditImpl$Companion;", "", "<init>", "()V", "TAG", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
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

        static {
            int[] iArr = new int[MimeType.values().length];
            try {
                iArr[MimeType.IMAGE_HEIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        String tagOf = SLog.tagOf(SEFEditImpl.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public SEFEditImpl(SEFInfo sEFInfo) {
        j.e(sEFInfo, "sefInfo");
        this.sefInfo = sEFInfo;
    }

    private final void cleanDirty() {
        SEFInfo sEFInfo = this.sefInfo;
        j.c(sEFInfo, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.SEFInfoImpl");
        ((SEFInfoImpl) sEFInfo).setDirty$motionphoto_utils_v2_0_17_release(false);
    }

    /* access modifiers changed from: private */
    public static final CharSequence commit$lambda$26(SEFDataInfo sEFDataInfo) {
        j.e(sEFDataInfo, "it");
        String name = sEFDataInfo.getName();
        int type = sEFDataInfo.getType();
        int type2 = sEFDataInfo.getType() % 16;
        return name + "[" + type + "(rank " + (type2 + ((((type2 ^ 16) & ((-type2) | type2)) >> 31) & 16)) + ")] ";
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x011d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.nio.channels.FileChannel commit$lambda$35(com.samsung.android.motionphoto.utils.v2.SEFEditImpl r26, com.samsung.android.motionphoto.utils.v2.SEFInfoImpl r27, java.util.List r28, java.nio.ByteBuffer r29, java.nio.channels.FileChannel r30) {
        /*
            r0 = r26
            r1 = r30
            java.lang.String r2 = "ofc"
            kotlin.jvm.internal.j.e(r1, r2)
            com.samsung.android.motionphoto.utils.v2.SEFInfo r2 = r0.sefInfo
            com.samsung.android.motionphoto.utils.v2.SEFInfoImpl r2 = (com.samsung.android.motionphoto.utils.v2.SEFInfoImpl) r2
            long r2 = r2.getImageSize$motionphoto_utils_v2_0_17_release()
            r1.position(r2)
            int r2 = r27.getDataOffsetFromEnd$motionphoto_utils_v2_0_17_release()
            com.samsung.android.motionphoto.utils.v2.MediaFile r3 = r0.getOutputFile()
            com.samsung.android.motionphoto.utils.v2.MimeType r3 = r3.getMimeType()
            com.samsung.android.motionphoto.utils.v2.MimeType r4 = com.samsung.android.motionphoto.utils.v2.MimeType.IMAGE_HEIC
            if (r3 != r4) goto L_0x0195
            int r3 = r27.getDataOffsetFromEnd$motionphoto_utils_v2_0_17_release()
            r4 = 8
            int r3 = r3 + r4
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Integer[] r5 = new java.lang.Integer[]{r3}
            kotlin.jvm.internal.w r3 = kotlin.jvm.internal.v.f4727a
            java.lang.Class<java.lang.Integer> r6 = java.lang.Integer.class
            He.d r6 = r3.b(r6)
            java.lang.Class r11 = java.lang.Short.TYPE
            He.d r7 = r3.b(r11)
            boolean r7 = r6.equals(r7)
            java.lang.String r12 = "getBytes(...)"
            java.lang.String r13 = "null cannot be cast to non-null type kotlin.Long"
            java.lang.Class r14 = java.lang.Long.TYPE
            java.lang.String r15 = "null cannot be cast to non-null type kotlin.Int"
            r16 = 4
            java.lang.Class r8 = java.lang.Integer.TYPE
            java.lang.String r9 = "null cannot be cast to non-null type kotlin.Short"
            r17 = 2
            java.lang.Class<java.lang.String> r10 = java.lang.String.class
            r18 = 0
            if (r7 == 0) goto L_0x007a
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.allocate(r17)
            r5 = r5[r18]
            kotlin.jvm.internal.j.c(r5, r9)
            java.lang.Short r5 = (java.lang.Short) r5
            short r5 = r5.shortValue()
            r6.putShort(r5)
            byte[] r5 = r6.array()
        L_0x0071:
            r19 = r2
            r27 = r4
        L_0x0075:
            r4 = r8
            r2 = r9
            r0 = r10
            goto L_0x00eb
        L_0x007a:
            He.d r7 = r3.b(r8)
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L_0x0099
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.allocate(r16)
            r5 = r5[r18]
            kotlin.jvm.internal.j.c(r5, r15)
            int r5 = r5.intValue()
            r6.putInt(r5)
            byte[] r5 = r6.array()
            goto L_0x0071
        L_0x0099:
            He.d r7 = r3.b(r14)
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L_0x00be
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.allocate(r4)
            r5 = r5[r18]
            kotlin.jvm.internal.j.c(r5, r13)
            java.lang.Long r5 = (java.lang.Long) r5
            r27 = r4
            long r4 = r5.longValue()
            r6.putLong(r4)
            byte[] r5 = r6.array()
            r19 = r2
            goto L_0x0075
        L_0x00be:
            r27 = r4
            He.d r4 = r3.b(r10)
            boolean r4 = r6.equals(r4)
            if (r4 == 0) goto L_0x018f
            r4 = r9
            r9 = 0
            r6 = r10
            r10 = 63
            r7 = r6
            r6 = 0
            r19 = r7
            r7 = 0
            r20 = r8
            r8 = 0
            r0 = r19
            r19 = r2
            r2 = r4
            r4 = r20
            java.lang.String r5 = ne.C1192j.s0(r5, r6, r7, r8, r9, r10)
            java.nio.charset.Charset r6 = Tf.a.f3815a
            byte[] r5 = r5.getBytes(r6)
            kotlin.jvm.internal.j.d(r5, r12)
        L_0x00eb:
            kotlin.jvm.internal.j.b(r5)
            com.samsung.android.motionphoto.utils.v2.CommonsKt.write((java.nio.channels.FileChannel) r1, (byte[]) r5)
            java.lang.String r5 = "sefd"
            java.lang.String[] r20 = new java.lang.String[]{r5}
            He.d r5 = r3.b(r0)
            He.d r6 = r3.b(r11)
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x011d
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r17)
            r3 = r20[r18]
            kotlin.jvm.internal.j.c(r3, r2)
            java.lang.Short r3 = (java.lang.Short) r3
            short r2 = r3.shortValue()
            r0.putShort(r2)
            byte[] r0 = r0.array()
            goto L_0x0180
        L_0x011d:
            He.d r2 = r3.b(r4)
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x013e
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r16)
            r2 = r20[r18]
            kotlin.jvm.internal.j.c(r2, r15)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r0.putInt(r2)
            byte[] r0 = r0.array()
            goto L_0x0180
        L_0x013e:
            He.d r2 = r3.b(r14)
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x015f
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r27)
            r2 = r20[r18]
            kotlin.jvm.internal.j.c(r2, r13)
            java.lang.Long r2 = (java.lang.Long) r2
            long r2 = r2.longValue()
            r0.putLong(r2)
            byte[] r0 = r0.array()
            goto L_0x0180
        L_0x015f:
            He.d r0 = r3.b(r0)
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0189
            r24 = 0
            r25 = 63
            r21 = 0
            r22 = 0
            r23 = 0
            java.lang.String r0 = ne.C1192j.s0(r20, r21, r22, r23, r24, r25)
            java.nio.charset.Charset r2 = Tf.a.f3815a
            byte[] r0 = r0.getBytes(r2)
            kotlin.jvm.internal.j.d(r0, r12)
        L_0x0180:
            kotlin.jvm.internal.j.b(r0)
            com.samsung.android.motionphoto.utils.v2.CommonsKt.write((java.nio.channels.FileChannel) r1, (byte[]) r0)
            int r2 = r19 + 8
            goto L_0x0197
        L_0x0189:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            throw r0
        L_0x018f:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            throw r0
        L_0x0195:
            r19 = r2
        L_0x0197:
            r0 = r28
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x019f:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x01df
            java.lang.Object r3 = r0.next()
            com.samsung.android.motionphoto.utils.v2.SEFDataInfo r3 = (com.samsung.android.motionphoto.utils.v2.SEFDataInfo) r3
            java.nio.ByteBuffer r4 = r3.toByteBuffer()
            java.lang.String r5 = TAG
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "write file: "
            r6.<init>(r7)
            r6.append(r3)
            java.lang.String r7 = ", buf="
            r6.append(r7)
            r6.append(r4)
            java.lang.String r6 = r6.toString()
            com.samsung.android.sum.core.SLog.i((java.lang.String) r5, (java.lang.String) r6)
            r1.write(r4)
            com.samsung.android.motionphoto.utils.v2.MediaFile r3 = r3.getDataFile()
            kotlin.jvm.internal.j.b(r3)
            com.samsung.android.motionphoto.utils.v2.e r4 = new com.samsung.android.motionphoto.utils.v2.e
            r5 = 2
            r4.<init>(r1, r5)
            r3.useInputFileChannel(r4)
            goto L_0x019f
        L_0x01df:
            r3 = r29
            r1.write(r3)
            r0 = r26
            com.samsung.android.motionphoto.utils.v2.SEFInfo r0 = r0.sefInfo
            com.samsung.android.motionphoto.utils.v2.SEFInfoImpl r0 = (com.samsung.android.motionphoto.utils.v2.SEFInfoImpl) r0
            long r3 = r0.getImageSize$motionphoto_utils_v2_0_17_release()
            long r5 = (long) r2
            long r3 = r3 + r5
            java.nio.channels.FileChannel r0 = r1.truncate(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.SEFEditImpl.commit$lambda$35(com.samsung.android.motionphoto.utils.v2.SEFEditImpl, com.samsung.android.motionphoto.utils.v2.SEFInfoImpl, java.util.List, java.nio.ByteBuffer, java.nio.channels.FileChannel):java.nio.channels.FileChannel");
    }

    /* access modifiers changed from: private */
    public static final x commit$lambda$35$lambda$34$lambda$33(FileChannel fileChannel, FileChannel fileChannel2) {
        j.e(fileChannel2, "ifc");
        CommonsKt.transferAllTo(fileChannel2, 0, fileChannel2.size(), fileChannel);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final long commit$lambda$39(SEFEditImpl sEFEditImpl, FileChannel fileChannel) {
        j.e(fileChannel, "ifc");
        return ((Number) sEFEditImpl.getOutputFile().useOutputFileChannel(new e(fileChannel, 1))).longValue();
    }

    /* access modifiers changed from: private */
    public static final long commit$lambda$39$lambda$38(FileChannel fileChannel, FileChannel fileChannel2) {
        j.e(fileChannel2, "ofc");
        return fileChannel.transferTo(0, fileChannel.size(), fileChannel2);
    }

    /* access modifiers changed from: private */
    public static final List dataInfos_delegate$lambda$2(SEFEditImpl sEFEditImpl) {
        SEFInfo sEFInfo = sEFEditImpl.sefInfo;
        j.c(sEFInfo, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.SEFInfoImpl");
        List<SEFDataInfo> dataInfos$motionphoto_utils_v2_0_17_release = ((SEFInfoImpl) sEFInfo).getDataInfos$motionphoto_utils_v2_0_17_release();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(dataInfos$motionphoto_utils_v2_0_17_release);
        return arrayList;
    }

    private final ByteOrder getByteOrder() {
        return SEFInfoImpl.Companion.getByteOrder$motionphoto_utils_v2_0_17_release();
    }

    private final List<SEFDataInfo> getDataInfos() {
        return (List) this.dataInfos$delegate.getValue();
    }

    private final MediaFile getInputFile() {
        SEFInfo sEFInfo = this.sefInfo;
        j.c(sEFInfo, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.SEFInfoImpl");
        return ((SEFInfoImpl) sEFInfo).getFile();
    }

    private final MediaFile getOutputFile() {
        MediaFile mediaFile = this._outputFile;
        if (mediaFile == null) {
            return getInputFile();
        }
        return mediaFile;
    }

    private final ByteBuffer getSEFDataBuffer(List<SEFDataInfo> list) {
        Iterable<SEFDataInfo> iterable = list;
        int i2 = 0;
        int i7 = 0;
        for (SEFDataInfo size : iterable) {
            i7 += size.size();
        }
        ByteBuffer order = ByteBuffer.allocate(i7).order(getByteOrder());
        for (Object next : iterable) {
            int i8 = i2 + 1;
            if (i2 >= 0) {
                SEFDataInfo sEFDataInfo = (SEFDataInfo) next;
                if (sEFDataInfo.getData() == null) {
                    sEFDataInfo.setData(getData(sEFDataInfo.getType(), sEFDataInfo.getName()));
                }
                order = order.put(sEFDataInfo.toByteBuffer());
                i2 = i8;
            } else {
                C1195m.v0();
                throw null;
            }
        }
        order.rewind();
        return order;
    }

    private final int getSEFTailSize(int i2) {
        Integer[] numArr = {4, 8, Integer.valueOf(i2 * 12), 4, 4};
        int i7 = 0;
        for (int i8 = 0; i8 < 5; i8++) {
            i7 += numArr[i8].intValue();
        }
        return i7;
    }

    private final boolean isOneIOFiles() {
        return j.a(getInputFile(), getOutputFile());
    }

    private final void makeDirty() {
        SEFInfo sEFInfo = this.sefInfo;
        j.c(sEFInfo, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.SEFInfoImpl");
        ((SEFInfoImpl) sEFInfo).setDirty$motionphoto_utils_v2_0_17_release(true);
    }

    /* access modifiers changed from: private */
    public static final boolean removeData$lambda$15(String[] strArr, SEFDataInfo sEFDataInfo) {
        j.e(sEFDataInfo, "it");
        return C1192j.d0(strArr, sEFDataInfo.getName());
    }

    /* access modifiers changed from: private */
    public static final boolean removeData$lambda$16(b bVar, Object obj) {
        return ((Boolean) bVar.invoke(obj)).booleanValue();
    }

    /* access modifiers changed from: private */
    public static final boolean removeData$lambda$17(int[] iArr, SEFDataInfo sEFDataInfo) {
        j.e(sEFDataInfo, "it");
        return C1192j.c0(sEFDataInfo.getType(), iArr);
    }

    /* access modifiers changed from: private */
    public static final boolean removeData$lambda$18(b bVar, Object obj) {
        return ((Boolean) bVar.invoke(obj)).booleanValue();
    }

    private final List<SEFDataInfo> updateAllSEFData(List<SEFDataInfo> list) {
        Iterable<SEFDataInfo> iterable = list;
        int i2 = 0;
        for (SEFDataInfo size : iterable) {
            i2 += size.size();
        }
        for (SEFDataInfo sEFDataInfo : iterable) {
            sEFDataInfo.setOffsetFromTail(i2);
            sEFDataInfo.setPayload(sEFDataInfo.size());
            i2 -= sEFDataInfo.getPayload();
        }
        return (List) iterable;
    }

    private final void updateEachDataPosition(int i2) {
        long size = getOutputFile().size() - ((long) i2);
        for (SEFDataInfo sEFDataInfo : getDataInfos()) {
            sEFDataInfo.setDataPosition((size - ((long) sEFDataInfo.getOffsetFromTail())) + ((long) 8) + ((long) sEFDataInfo.getName().length()));
            if (sEFDataInfo.getHasSubInfo()) {
                sEFDataInfo.setSubInfoPosition(sEFDataInfo.getDataPosition() + ((long) 4));
                sEFDataInfo.setDataPosition(sEFDataInfo.getSubInfoPosition() + ((long) sEFDataInfo.getSubInfoPayload()));
            }
        }
    }

    public void commit() {
        SEFInfo sEFInfo = this.sefInfo;
        j.c(sEFInfo, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.SEFInfoImpl");
        SEFInfoImpl sEFInfoImpl = (SEFInfoImpl) sEFInfo;
        sEFInfoImpl.adjustPosition$motionphoto_utils_v2_0_17_release(getOutputFile().size());
        ArrayList<SEFDataInfo> arrayList = new ArrayList<>();
        ArrayList<SEFDataInfo> arrayList2 = new ArrayList<>();
        for (Object next : getDataInfos()) {
            if (((SEFDataInfo) next).getDataFile() != null) {
                arrayList.add(next);
            } else {
                arrayList2.add(next);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(arrayList);
        arrayList3.addAll(arrayList2);
        updateAllSEFData(arrayList3);
        SLog.d(TAG, "dataInfos: " + C1194l.R0(getDataInfos(), (String) null, (String) null, (String) null, new C0725a(24), 31));
        int i2 = 0;
        int i7 = 0;
        for (SEFDataInfo size : arrayList) {
            i7 += size.size();
        }
        int i8 = 0;
        for (SEFDataInfo size2 : arrayList2) {
            i8 += size2.size();
        }
        String str = TAG;
        int size3 = arrayList.size();
        int size4 = arrayList2.size();
        StringBuilder h5 = a.h(i7, size3, "\n            | fileWriteSize=", "[", "], \n            | memoryWriteSize=");
        h5.append(i8);
        h5.append("[");
        h5.append(size4);
        h5.append("]\n        ");
        SLog.d(str, CommonsKt.trimToOneLine(h5.toString()));
        int sEFTailSize = getSEFTailSize(getDataInfos().size());
        sEFInfoImpl.setDataOffsetFromEnd$motionphoto_utils_v2_0_17_release(i7 + i8 + sEFTailSize);
        ByteBuffer order = ByteBuffer.allocate((WhenMappings.$EnumSwitchMapping$0[getOutputFile().getMimeType().ordinal()] == 1 ? 8 : 0) + i8 + sEFTailSize).order(getByteOrder());
        order.put(getSEFDataBuffer(arrayList2));
        byte[] bytes = MediaDefs.Meta.SEF.SEF_TAIL_START_SIGNATURE.getBytes(Tf.a.f3815a);
        j.d(bytes, "getBytes(...)");
        order.put(bytes);
        order.putInt(getVersion());
        order.putInt(getDataInfos().size());
        int i10 = this.primaryType;
        if (i10 > 0 && i10 != ((SEFDataInfo) C1194l.T0(getDataInfos())).getType()) {
            List<SEFDataInfo> dataInfos = getDataInfos();
            Iterator<SEFDataInfo> it = dataInfos.iterator();
            while (true) {
                if (!it.hasNext()) {
                    i2 = -1;
                    break;
                } else if (it.next().getType() == this.primaryType) {
                    break;
                } else {
                    i2++;
                }
            }
            dataInfos.add(dataInfos.remove(i2));
        }
        for (SEFDataInfo sEFDataInfo : getDataInfos()) {
            order.putInt(sEFDataInfo.getDataHeader());
            order.putInt(sEFDataInfo.getOffsetFromTail());
            order.putInt(sEFDataInfo.getPayload());
        }
        order.putInt((getDataInfos().size() * 12) + 12);
        byte[] bytes2 = MediaDefs.Meta.SEF.SEF_TAIL_SIGNATURE.getBytes(Tf.a.f3815a);
        j.d(bytes2, "getBytes(...)");
        order.put(bytes2);
        order.flip();
        getOutputFile().useOutputFileChannel(new n(this, sEFInfoImpl, arrayList, order, 0));
        if (isOneIOFiles()) {
            updateEachDataPosition(sEFTailSize);
            List<SEFDataInfo> dataInfos$motionphoto_utils_v2_0_17_release = ((SEFInfoImpl) this.sefInfo).getDataInfos$motionphoto_utils_v2_0_17_release();
            dataInfos$motionphoto_utils_v2_0_17_release.clear();
            dataInfos$motionphoto_utils_v2_0_17_release.addAll(getDataInfos());
        }
        cleanDirty();
    }

    public boolean containsKey(String str) {
        j.e(str, "key");
        return this.sefInfo.containsKey(str);
    }

    public boolean containsType(int i2) {
        return this.sefInfo.containsType(i2);
    }

    public byte[] getData(int i2) {
        return this.sefInfo.getData(i2);
    }

    public int getDataCount() {
        return this.sefInfo.getDataCount();
    }

    public Pair<Long, Integer> getDataPositionLength(int i2) {
        return this.sefInfo.getDataPositionLength(i2);
    }

    public List<String> getKeys() {
        return this.sefInfo.getKeys();
    }

    public final SEFInfo getSefInfo() {
        return this.sefInfo;
    }

    public long getSize() {
        return this.sefInfo.getSize();
    }

    public int getVersion() {
        return this.sefInfo.getVersion();
    }

    public boolean isDirty() {
        return isDirty$motionphoto_utils_v2_0_17_release();
    }

    public final boolean isDirty$motionphoto_utils_v2_0_17_release() {
        SEFInfo sEFInfo = this.sefInfo;
        j.c(sEFInfo, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.SEFInfoImpl");
        return ((SEFInfoImpl) sEFInfo).isDirty$motionphoto_utils_v2_0_17_release();
    }

    public final void loadAllData() {
        SEFInfo sEFInfo = this.sefInfo;
        j.c(sEFInfo, "null cannot be cast to non-null type com.samsung.android.motionphoto.utils.v2.SEFInfoImpl");
        ((SEFInfoImpl) sEFInfo).loadAllData$motionphoto_utils_v2_0_17_release();
    }

    public SEFEdit putData(int i2, String str, byte[] bArr) {
        String str2 = str;
        byte[] bArr2 = bArr;
        j.e(str2, "name");
        j.e(bArr2, "data");
        String str3 = TAG;
        StringBuilder u = C0212a.u("sef: add data name=", str2, ", type=", i2, ", data=");
        u.append(bArr2);
        SLog.i(str3, u.toString());
        try {
            for (Object next : getDataInfos()) {
                if (j.a(((SEFDataInfo) next).getName(), str2)) {
                    SEFDataInfo sEFDataInfo = (SEFDataInfo) next;
                    sEFDataInfo.setData(bArr2);
                    sEFDataInfo.setDataPayload(bArr2.length);
                    makeDirty();
                    return this;
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        } catch (Exception unused) {
            getDataInfos().add(new SEFDataInfo(i2, str2, false, 0, 0, bArr2, (MediaFile) null, 0, 0, (byte[]) null, 0, 0, 4060, (e) null));
        }
    }

    public SEFEdit removeData(String... strArr) {
        j.e(strArr, "names");
        String str = TAG;
        SLog.i(str, "sef: remove data name=" + strArr);
        getDataInfos().removeIf(new I(18, new Ad.f(7, (Object) strArr)));
        makeDirty();
        return this;
    }

    public SEFEdit setPrimaryType(int i2) {
        this.primaryType = i2;
        return this;
    }

    public byte[] getData(int i2, String str) {
        j.e(str, "name");
        return this.sefInfo.getData(i2, str);
    }

    public Pair<Long, Integer> getDataPositionLength(String str) {
        j.e(str, "name");
        return this.sefInfo.getDataPositionLength(str);
    }

    public byte[] getData(String str) {
        j.e(str, "name");
        return this.sefInfo.getData(str);
    }

    public SEFEdit removeData(int... iArr) {
        j.e(iArr, "types");
        String str = TAG;
        SLog.i(str, "sef: remove data types=" + iArr);
        getDataInfos().removeIf(new I(17, new Ad.f(6, (Object) iArr)));
        makeDirty();
        return this;
    }

    public SEFEdit putData(int i2, String str, MediaFile mediaFile) {
        String str2 = str;
        MediaFile mediaFile2 = mediaFile;
        j.e(str2, "name");
        j.e(mediaFile2, "dataFile");
        String str3 = TAG;
        StringBuilder u = C0212a.u("sef: add data name=", str2, ", type=", i2, ", dataFile=");
        u.append(mediaFile2);
        SLog.i(str3, u.toString());
        try {
            for (Object next : getDataInfos()) {
                if (j.a(((SEFDataInfo) next).getName(), str2)) {
                    SEFDataInfo sEFDataInfo = (SEFDataInfo) next;
                    sEFDataInfo.setDataFile(mediaFile2);
                    sEFDataInfo.setDataPayload((int) mediaFile2.size());
                    makeDirty();
                    return this;
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        } catch (Exception unused) {
            getDataInfos().add(new SEFDataInfo(i2, str2, false, 0, 0, (byte[]) null, mediaFile2, (int) mediaFile2.size(), 0, (byte[]) null, 0, 0, 3900, (e) null));
        }
    }

    public SEFEdit putData(int i2, byte[] bArr) {
        j.e(bArr, "data");
        try {
            for (Object next : getDataInfos()) {
                if (((SEFDataInfo) next).getType() == i2) {
                    SEFDataInfo sEFDataInfo = (SEFDataInfo) next;
                    sEFDataInfo.setData(bArr);
                    sEFDataInfo.setDataPayload(bArr.length);
                    SEFDataInfo sEFDataInfo2 = (SEFDataInfo) next;
                    makeDirty();
                    return this;
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        } catch (Exception unused) {
            throw new IllegalStateException(C0086a.i(i2, "No SEF data exist w/ type: "));
        }
    }

    public SEFEdit putData(String str, byte[] bArr) {
        j.e(str, "name");
        j.e(bArr, "data");
        try {
            for (Object next : getDataInfos()) {
                if (j.a(((SEFDataInfo) next).getName(), str)) {
                    SEFDataInfo sEFDataInfo = (SEFDataInfo) next;
                    sEFDataInfo.setData(bArr);
                    sEFDataInfo.setDataPayload(bArr.length);
                    SEFDataInfo sEFDataInfo2 = (SEFDataInfo) next;
                    makeDirty();
                    return this;
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        } catch (Exception unused) {
            throw new IllegalStateException("No SEF data exist w/ name: ".concat(str));
        }
    }

    public SEFEdit putData(int i2, MediaFile mediaFile) {
        j.e(mediaFile, "dataFile");
        try {
            for (Object next : getDataInfos()) {
                if (((SEFDataInfo) next).getType() == i2) {
                    SEFDataInfo sEFDataInfo = (SEFDataInfo) next;
                    sEFDataInfo.setDataFile(mediaFile);
                    sEFDataInfo.setDataPayload((int) mediaFile.size());
                    SEFDataInfo sEFDataInfo2 = (SEFDataInfo) next;
                    makeDirty();
                    return this;
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        } catch (Exception unused) {
            throw new IllegalStateException(C0086a.i(i2, "No SEF data exist w/ type: "));
        }
    }

    public void commit(MediaFile mediaFile) {
        j.e(mediaFile, "file");
        this._outputFile = mediaFile;
        if (!j.a(getInputFile(), getOutputFile())) {
            getInputFile().useInputFileChannel(new Ad.f(8, (Object) this));
        }
        commit();
    }

    public SEFEdit putData(String str, MediaFile mediaFile) {
        j.e(str, "name");
        j.e(mediaFile, "dataFile");
        try {
            for (Object next : getDataInfos()) {
                if (j.a(((SEFDataInfo) next).getName(), str)) {
                    SEFDataInfo sEFDataInfo = (SEFDataInfo) next;
                    sEFDataInfo.setData((byte[]) null);
                    sEFDataInfo.setDataFile(mediaFile);
                    sEFDataInfo.setDataPayload((int) mediaFile.size());
                    SEFDataInfo sEFDataInfo2 = (SEFDataInfo) next;
                    makeDirty();
                    return this;
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        } catch (Exception unused) {
            throw new IllegalStateException("No SEF data exist w/ name: ".concat(str));
        }
    }
}
