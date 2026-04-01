package com.samsung.android.motionphoto.utils.v2;

import Ad.f;
import Ae.b;
import Tf.a;
import androidx.core.util.Pair;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sum.core.SLog;
import i.C0212a;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import ne.C1194l;
import ne.C1196n;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010!\n\u0002\b\r\u0018\u0000 X2\u00020\u0001:\u0001XB=\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012*\u0010\b\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00050\u0004\"\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0016\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001a\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u001b\u0010\u0015J\u0015\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0006H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020\u001cH\u0016¢\u0006\u0004\b!\u0010\"J\u0017\u0010$\u001a\u00020 2\u0006\u0010#\u001a\u00020\u0017H\u0016¢\u0006\u0004\b$\u0010%J\r\u0010&\u001a\u00020\u0002¢\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020\u001cH\u0016¢\u0006\u0004\b(\u0010)J\u0019\u0010+\u001a\u0004\u0018\u00010*2\u0006\u0010#\u001a\u00020\u0017H\u0016¢\u0006\u0004\b+\u0010,J\u0019\u0010+\u001a\u0004\u0018\u00010*2\u0006\u0010-\u001a\u00020\u001cH\u0016¢\u0006\u0004\b+\u0010.J!\u0010+\u001a\u0004\u0018\u00010*2\u0006\u0010#\u001a\u00020\u00172\u0006\u0010-\u001a\u00020\u001cH\u0016¢\u0006\u0004\b+\u0010/J\u0017\u00100\u001a\u0004\u0018\u00010\u00072\u0006\u0010-\u001a\u00020\u001c¢\u0006\u0004\b0\u00101J%\u00103\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u0001022\u0006\u0010#\u001a\u00020\u0017H\u0016¢\u0006\u0004\b3\u00104J%\u00103\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u0001022\u0006\u0010-\u001a\u00020\u001cH\u0016¢\u0006\u0004\b3\u00105J\u000f\u00107\u001a\u00020\u000bH\u0000¢\u0006\u0004\b6\u0010\rJ\u000f\u00108\u001a\u00020\u000bH\u0002¢\u0006\u0004\b8\u0010\rJ\u0017\u0010:\u001a\u00020\u00172\u0006\u00109\u001a\u00020\u0017H\u0002¢\u0006\u0004\b:\u0010;J\u0017\u0010<\u001a\u00020 2\u0006\u00109\u001a\u00020\u0017H\u0002¢\u0006\u0004\b<\u0010%J\u0017\u0010+\u001a\u00020*2\u0006\u0010=\u001a\u00020\u0007H\u0002¢\u0006\u0004\b+\u0010>R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010?R(\u0010\b\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00050\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\b\u0010@R\u0016\u0010A\u001a\u00020\u00178\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bA\u0010BR\"\u0010C\u001a\u00020\u000f8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010\u0015\"\u0004\bF\u0010\u0012R\"\u0010G\u001a\u00020\u00178\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bG\u0010B\u001a\u0004\bH\u0010\u0019\"\u0004\bI\u0010JR\u0016\u0010K\u001a\u00020\u00178\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bK\u0010BR(\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00070L8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bM\u0010N\u001a\u0004\bO\u0010\u001e\"\u0004\bP\u0010QR\"\u0010R\u001a\u00020 8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bR\u0010S\u001a\u0004\bT\u0010U\"\u0004\bV\u0010W¨\u0006Y"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/SEFInfoImpl;", "Lcom/samsung/android/motionphoto/utils/v2/SEFInfo;", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "mediaFile", "", "Ljava/util/function/Consumer;", "", "Lcom/samsung/android/motionphoto/utils/v2/SEFDataInfo;", "dataHandler", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/MediaFile;[Ljava/util/function/Consumer;)V", "Lme/x;", "reload$motionphoto_utils_v2_0_17_release", "()V", "reload", "", "newFileSize", "adjustPosition$motionphoto_utils_v2_0_17_release", "(J)V", "adjustPosition", "getImageSize$motionphoto_utils_v2_0_17_release", "()J", "getImageSize", "", "getVersion", "()I", "getDataCount", "getSize", "", "getKeys", "()Ljava/util/List;", "key", "", "containsKey", "(Ljava/lang/String;)Z", "type", "containsType", "(I)Z", "getFile", "()Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "toString", "()Ljava/lang/String;", "", "getData", "(I)[B", "name", "(Ljava/lang/String;)[B", "(ILjava/lang/String;)[B", "getDataInfo", "(Ljava/lang/String;)Lcom/samsung/android/motionphoto/utils/v2/SEFDataInfo;", "Landroidx/core/util/Pair;", "getDataPositionLength", "(I)Landroidx/core/util/Pair;", "(Ljava/lang/String;)Landroidx/core/util/Pair;", "loadAllData$motionphoto_utils_v2_0_17_release", "loadAllData", "load", "header", "getTypeFromHeader", "(I)I", "hasSubInfoOnHeader", "dataInfo", "(Lcom/samsung/android/motionphoto/utils/v2/SEFDataInfo;)[B", "Lcom/samsung/android/motionphoto/utils/v2/MediaFile;", "[Ljava/util/function/Consumer;", "version", "I", "lastImageSize", "J", "getLastImageSize$motionphoto_utils_v2_0_17_release", "setLastImageSize$motionphoto_utils_v2_0_17_release", "dataOffsetFromEnd", "getDataOffsetFromEnd$motionphoto_utils_v2_0_17_release", "setDataOffsetFromEnd$motionphoto_utils_v2_0_17_release", "(I)V", "tailSize", "", "dataInfos", "Ljava/util/List;", "getDataInfos$motionphoto_utils_v2_0_17_release", "setDataInfos$motionphoto_utils_v2_0_17_release", "(Ljava/util/List;)V", "isDirty", "Z", "isDirty$motionphoto_utils_v2_0_17_release", "()Z", "setDirty$motionphoto_utils_v2_0_17_release", "(Z)V", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SEFInfoImpl implements SEFInfo {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG;
    /* access modifiers changed from: private */
    public static final ByteOrder byteOrder = ByteOrder.nativeOrder();
    private final Consumer<List<SEFDataInfo>>[] dataHandler;
    private List<SEFDataInfo> dataInfos = new ArrayList();
    private int dataOffsetFromEnd;
    private boolean isDirty;
    private long lastImageSize;
    private final MediaFile mediaFile;
    private int tailSize;
    private int version = 107;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/SEFInfoImpl$Companion;", "", "<init>", "()V", "TAG", "", "byteOrder", "Ljava/nio/ByteOrder;", "kotlin.jvm.PlatformType", "getByteOrder$motionphoto_utils_v2_0_17_release", "()Ljava/nio/ByteOrder;", "Ljava/nio/ByteOrder;", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final ByteOrder getByteOrder$motionphoto_utils_v2_0_17_release() {
            return SEFInfoImpl.byteOrder;
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
        String tagOf = SLog.tagOf(SEFInfoImpl.class);
        j.d(tagOf, "tagOf(...)");
        TAG = tagOf;
    }

    public SEFInfoImpl(MediaFile mediaFile2, Consumer<List<SEFDataInfo>>... consumerArr) {
        j.e(mediaFile2, "mediaFile");
        j.e(consumerArr, "dataHandler");
        this.mediaFile = mediaFile2;
        this.dataHandler = consumerArr;
        String str = TAG;
        SLog.i(str, "load");
        load();
        SLog.d(str, "parse: " + this);
    }

    private final int getTypeFromHeader(int i2) {
        return i2 >> 16;
    }

    private final boolean hasSubInfoOnHeader(int i2) {
        if (((i2 >> 15) & 1) == 1) {
            return true;
        }
        return false;
    }

    private final void load() {
        this.mediaFile.useInputFileChannel(new f(9, (Object) this));
    }

    /* access modifiers changed from: private */
    public static final x load$lambda$8(SEFInfoImpl sEFInfoImpl, FileChannel fileChannel) {
        int i2;
        SEFInfoImpl sEFInfoImpl2 = sEFInfoImpl;
        FileChannel fileChannel2 = fileChannel;
        j.e(fileChannel2, "it");
        fileChannel2.position(0);
        sEFInfoImpl2.lastImageSize = fileChannel2.size();
        int i7 = (fileChannel2.size() > 4 ? 1 : (fileChannel2.size() == 4 ? 0 : -1));
        x xVar = x.f4917a;
        if (i7 < 0) {
            SLog.d(TAG, "no end of sef-tail marker: " + sEFInfoImpl2.mediaFile.path());
            return xVar;
        }
        fileChannel2.position(fileChannel2.size() - ((long) 4));
        Charset charset = a.f3815a;
        byte[] bytes = MediaDefs.Meta.SEF.SEF_TAIL_SIGNATURE.getBytes(charset);
        j.d(bytes, "getBytes(...)");
        if (!CommonsKt.matched(fileChannel2, Arrays.copyOf(bytes, bytes.length))) {
            SLog.d(TAG, "no end of sef-tail marker: " + sEFInfoImpl2.mediaFile.path());
            return xVar;
        }
        char c5 = 8;
        fileChannel2.position(fileChannel2.size() - ((long) 8));
        ByteOrder byteOrder2 = byteOrder;
        int i8 = CommonsKt.readAsIntBuffer(fileChannel2, 1, byteOrder2).get() + 8;
        String str = TAG;
        SLog.d(str, "sefTailStartSignatureOffset=" + i8);
        fileChannel2.position(fileChannel2.size() - ((long) i8));
        byte[] bytes2 = MediaDefs.Meta.SEF.SEF_TAIL_START_SIGNATURE.getBytes(charset);
        j.d(bytes2, "getBytes(...)");
        if (!CommonsKt.matched(fileChannel2, Arrays.copyOf(bytes2, bytes2.length))) {
            SLog.e(str, "no start of sef-tail marker");
            return xVar;
        }
        IntBuffer readAsIntBuffer = CommonsKt.readAsIntBuffer(fileChannel2, 2, byteOrder2);
        Integer valueOf = Integer.valueOf(readAsIntBuffer.get(0));
        Integer valueOf2 = Integer.valueOf(readAsIntBuffer.get(1));
        int intValue = valueOf.intValue();
        int intValue2 = valueOf2.intValue();
        SLog.d(str, "SEF version: " + intValue + ", # of data: " + intValue2);
        ArrayList<SEFDataInfo> arrayList = new ArrayList<>();
        for (int i10 = 0; i10 < intValue2; i10++) {
            IntBuffer readAsIntBuffer2 = CommonsKt.readAsIntBuffer(fileChannel2, 3, byteOrder);
            Integer valueOf3 = Integer.valueOf(readAsIntBuffer2.get(0));
            Integer valueOf4 = Integer.valueOf(readAsIntBuffer2.get(1));
            Integer valueOf5 = Integer.valueOf(readAsIntBuffer2.get(2));
            int intValue3 = valueOf3.intValue();
            arrayList.add(new SEFDataInfo(sEFInfoImpl2.getTypeFromHeader(intValue3), (String) null, sEFInfoImpl2.hasSubInfoOnHeader(intValue3), valueOf4.intValue(), valueOf5.intValue(), (byte[]) null, (MediaFile) null, 0, 0, (byte[]) null, 0, 0, 4066, (e) null));
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (!it.hasNext()) {
                sEFInfoImpl2.dataOffsetFromEnd = ((SEFDataInfo) next).getOffsetFromTail() + i8;
                long j2 = sEFInfoImpl2.lastImageSize;
            } else {
                int offsetFromTail = ((SEFDataInfo) next).getOffsetFromTail();
                do {
                    Object next2 = it.next();
                    int offsetFromTail2 = ((SEFDataInfo) next2).getOffsetFromTail();
                    if (offsetFromTail < offsetFromTail2) {
                        next = next2;
                        offsetFromTail = offsetFromTail2;
                    }
                } while (it.hasNext());
            }
            sEFInfoImpl2.dataOffsetFromEnd = ((SEFDataInfo) next).getOffsetFromTail() + i8;
            long j22 = sEFInfoImpl2.lastImageSize;
            if (WhenMappings.$EnumSwitchMapping$0[sEFInfoImpl2.mediaFile.getMimeType().ordinal()] == 1) {
                i2 = sEFInfoImpl2.dataOffsetFromEnd + 8;
            } else {
                i2 = sEFInfoImpl2.dataOffsetFromEnd;
            }
            long j3 = j22 - ((long) i2);
            sEFInfoImpl2.lastImageSize = j3;
            SLog.i(TAG, CommonsKt.trimToOneLine("lastImageSize=" + j3 + ", dataOffsetFromEnd=" + sEFInfoImpl2.dataOffsetFromEnd + ",\n                | sefTailStartSignatureOffset=" + i8 + "\n            "));
            sEFInfoImpl2.version = intValue;
            sEFInfoImpl2.tailSize = i8;
            for (SEFDataInfo sEFDataInfo : arrayList) {
                fileChannel2.position(fileChannel2.size() - ((long) (sEFDataInfo.getOffsetFromTail() + i8)));
                ByteOrder byteOrder3 = byteOrder;
                IntBuffer readAsIntBuffer3 = CommonsKt.readAsIntBuffer(fileChannel2, 2, byteOrder3);
                Integer valueOf6 = Integer.valueOf(readAsIntBuffer3.get(0));
                Integer valueOf7 = Integer.valueOf(readAsIntBuffer3.get(1));
                int intValue4 = valueOf6.intValue();
                int intValue5 = valueOf7.intValue();
                char c6 = c5;
                if (sEFDataInfo.getType() != sEFInfoImpl2.getTypeFromHeader(intValue4)) {
                    throw new IllegalStateException("Check failed.");
                } else if (sEFDataInfo.getHasSubInfo() == sEFInfoImpl2.hasSubInfoOnHeader(intValue4)) {
                    if (sEFDataInfo.getHasSubInfo()) {
                        sEFDataInfo.setSubInfoPayload(CommonsKt.readAsIntBuffer(fileChannel2, 1, byteOrder3).get(0));
                    } else {
                        sEFDataInfo.setSubInfoPayload(0);
                    }
                    byte[] bArr = new byte[intValue5];
                    fileChannel2.read(ByteBuffer.wrap(bArr));
                    sEFDataInfo.setName(new String(bArr, a.f3815a));
                    sEFDataInfo.setDataPosition(fileChannel2.position());
                    sEFDataInfo.setDataPayload((sEFDataInfo.getPayload() - 8) - sEFDataInfo.getName().length());
                    if (sEFDataInfo.getHasSubInfo()) {
                        sEFDataInfo.setSubInfoPosition(fileChannel2.position());
                        sEFDataInfo.setDataPayload(sEFDataInfo.getDataPayload() - (sEFDataInfo.getSubInfoPayload() + 4));
                        sEFDataInfo.setDataPosition(sEFDataInfo.getDataPosition() + ((long) sEFDataInfo.getSubInfoPayload()));
                    }
                    c5 = c6;
                } else {
                    throw new IllegalStateException("Check failed.");
                }
            }
            sEFInfoImpl2.dataInfos.clear();
            sEFInfoImpl2.dataInfos.addAll(arrayList);
            for (Consumer<List<SEFDataInfo>> accept : sEFInfoImpl2.dataHandler) {
                accept.accept(arrayList);
            }
            return xVar;
        }
        throw new NoSuchElementException();
    }

    public final void adjustPosition$motionphoto_utils_v2_0_17_release(long j2) {
        int i2;
        long j3 = this.lastImageSize;
        this.lastImageSize = j2;
        if (this.dataOffsetFromEnd > 0) {
            if (WhenMappings.$EnumSwitchMapping$0[this.mediaFile.getMimeType().ordinal()] == 1) {
                i2 = this.dataOffsetFromEnd + 8;
            } else {
                i2 = this.dataOffsetFromEnd;
            }
            this.lastImageSize = j2 - ((long) i2);
        }
        long j8 = this.lastImageSize;
        long j10 = j8 - j3;
        if (j10 > 0) {
            String str = TAG;
            StringBuilder j11 = N2.j.j(j3, "adjustPosition: image-size changed from ", " to ");
            j11.append(j8);
            j11.append(", update each data position w/ ");
            j11.append(j10);
            SLog.i(str, j11.toString());
            for (SEFDataInfo sEFDataInfo : this.dataInfos) {
                sEFDataInfo.setDataPosition(sEFDataInfo.getDataPosition() + j10);
            }
        }
    }

    public boolean containsKey(String str) {
        Object obj;
        j.e(str, "key");
        Iterator it = this.dataInfos.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (j.a(((SEFDataInfo) obj).getName(), str)) {
                break;
            }
        }
        if (obj != null) {
            return true;
        }
        return false;
    }

    public boolean containsType(int i2) {
        Object obj;
        Iterator it = this.dataInfos.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((SEFDataInfo) obj).getType() == i2) {
                break;
            }
        }
        if (obj != null) {
            return true;
        }
        return false;
    }

    public byte[] getData(int i2) {
        Object obj;
        if (this.dataOffsetFromEnd == 0) {
            return null;
        }
        Iterator it = this.dataInfos.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((SEFDataInfo) obj).getType() == i2) {
                break;
            }
        }
        SEFDataInfo sEFDataInfo = (SEFDataInfo) obj;
        if (sEFDataInfo != null) {
            return getData(sEFDataInfo);
        }
        return null;
    }

    public int getDataCount() {
        return this.dataInfos.size();
    }

    public final SEFDataInfo getDataInfo(String str) {
        Object obj;
        j.e(str, "name");
        Iterator it = this.dataInfos.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (j.a(((SEFDataInfo) obj).getName(), str)) {
                break;
            }
        }
        return (SEFDataInfo) obj;
    }

    public final List<SEFDataInfo> getDataInfos$motionphoto_utils_v2_0_17_release() {
        return this.dataInfos;
    }

    public final int getDataOffsetFromEnd$motionphoto_utils_v2_0_17_release() {
        return this.dataOffsetFromEnd;
    }

    public Pair<Long, Integer> getDataPositionLength(int i2) {
        Object obj;
        Iterator it = this.dataInfos.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((SEFDataInfo) obj).getType() == i2) {
                break;
            }
        }
        SEFDataInfo sEFDataInfo = (SEFDataInfo) obj;
        if (sEFDataInfo != null) {
            return new Pair<>(Long.valueOf(sEFDataInfo.getDataPosition()), Integer.valueOf(sEFDataInfo.getDataPayload()));
        }
        return null;
    }

    public final MediaFile getFile() {
        return this.mediaFile;
    }

    public final long getImageSize$motionphoto_utils_v2_0_17_release() {
        return this.lastImageSize;
    }

    public List<String> getKeys() {
        Iterable<SEFDataInfo> iterable = this.dataInfos;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (SEFDataInfo name : iterable) {
            arrayList.add(name.getName());
        }
        return C1194l.k1(arrayList);
    }

    public final long getLastImageSize$motionphoto_utils_v2_0_17_release() {
        return this.lastImageSize;
    }

    public long getSize() {
        return (long) this.dataOffsetFromEnd;
    }

    public int getVersion() {
        return this.version;
    }

    public final boolean isDirty$motionphoto_utils_v2_0_17_release() {
        return this.isDirty;
    }

    public final void loadAllData$motionphoto_utils_v2_0_17_release() {
        for (SEFDataInfo sEFDataInfo : this.dataInfos) {
            if (sEFDataInfo.getData() == null && sEFDataInfo.getDataFile() == null) {
                sEFDataInfo.setData(getData(sEFDataInfo.getType(), sEFDataInfo.getName()));
            }
        }
    }

    public final void reload$motionphoto_utils_v2_0_17_release() {
        SLog.i(TAG, "reload");
        load();
    }

    public final void setDataInfos$motionphoto_utils_v2_0_17_release(List<SEFDataInfo> list) {
        j.e(list, "<set-?>");
        this.dataInfos = list;
    }

    public final void setDataOffsetFromEnd$motionphoto_utils_v2_0_17_release(int i2) {
        this.dataOffsetFromEnd = i2;
    }

    public final void setDirty$motionphoto_utils_v2_0_17_release(boolean z) {
        this.isDirty = z;
    }

    public final void setLastImageSize$motionphoto_utils_v2_0_17_release(long j2) {
        this.lastImageSize = j2;
    }

    public String toString() {
        int i2 = this.version;
        int i7 = this.dataOffsetFromEnd;
        return C0212a.p(A.a.h(i2, i7, "version=", ", startOffsetFromEnd=", ", dataInfos=["), C1194l.R0(this.dataInfos, ArcCommonLog.TAG_COMMA, (String) null, (String) null, (b) null, 62), "]");
    }

    public byte[] getData(String str) {
        Object obj;
        j.e(str, "name");
        if (this.dataOffsetFromEnd == 0) {
            return null;
        }
        Iterator it = this.dataInfos.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (j.a(((SEFDataInfo) obj).getName(), str)) {
                break;
            }
        }
        SEFDataInfo sEFDataInfo = (SEFDataInfo) obj;
        if (sEFDataInfo != null) {
            return getData(sEFDataInfo);
        }
        return null;
    }

    public Pair<Long, Integer> getDataPositionLength(String str) {
        Object obj;
        j.e(str, "name");
        Iterator it = this.dataInfos.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (j.a(((SEFDataInfo) obj).getName(), str)) {
                break;
            }
        }
        SEFDataInfo sEFDataInfo = (SEFDataInfo) obj;
        if (sEFDataInfo != null) {
            return new Pair<>(Long.valueOf(sEFDataInfo.getDataPosition()), Integer.valueOf(sEFDataInfo.getDataPayload()));
        }
        return null;
    }

    public byte[] getData(int i2, String str) {
        Object obj;
        j.e(str, "name");
        if (this.dataOffsetFromEnd == 0) {
            SLog.i(TAG, "dataOffsetFromEnd is 0, return null");
            return null;
        }
        Iterator it = this.dataInfos.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            SEFDataInfo sEFDataInfo = (SEFDataInfo) obj;
            if (sEFDataInfo.getType() == i2 && j.a(sEFDataInfo.getName(), str)) {
                break;
            }
        }
        SEFDataInfo sEFDataInfo2 = (SEFDataInfo) obj;
        if (sEFDataInfo2 != null) {
            return getData(sEFDataInfo2);
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0034, code lost:
        B1.a.g(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0033, code lost:
        r0 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final byte[] getData(com.samsung.android.motionphoto.utils.v2.SEFDataInfo r4) {
        /*
            r3 = this;
            byte[] r0 = r4.getData()
            if (r0 != 0) goto L_0x0038
            com.samsung.android.motionphoto.utils.v2.MediaFile r3 = r3.mediaFile
            java.io.FileInputStream r3 = r3.newInputFileStream()
            java.nio.channels.FileChannel r0 = r3.getChannel()     // Catch:{ all -> 0x0031 }
            long r1 = r4.getDataPosition()     // Catch:{ all -> 0x0031 }
            r0.position(r1)     // Catch:{ all -> 0x0031 }
            int r0 = r4.getDataPayload()     // Catch:{ all -> 0x0031 }
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x0031 }
            r4.setData(r0)     // Catch:{ all -> 0x0031 }
            byte[] r0 = r4.getData()     // Catch:{ all -> 0x0031 }
            int r1 = r4.getDataPayload()     // Catch:{ all -> 0x0031 }
            r2 = 0
            r3.read(r0, r2, r1)     // Catch:{ all -> 0x0031 }
            r0 = 0
            B1.a.g(r3, r0)
            goto L_0x0038
        L_0x0031:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0033 }
        L_0x0033:
            r0 = move-exception
            B1.a.g(r3, r4)
            throw r0
        L_0x0038:
            byte[] r3 = r4.getData()
            kotlin.jvm.internal.j.b(r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.SEFInfoImpl.getData(com.samsung.android.motionphoto.utils.v2.SEFDataInfo):byte[]");
    }
}
