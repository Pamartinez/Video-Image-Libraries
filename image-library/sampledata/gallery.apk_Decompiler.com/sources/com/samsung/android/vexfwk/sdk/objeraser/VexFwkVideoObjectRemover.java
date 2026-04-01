package com.samsung.android.vexfwk.sdk.objeraser;

import He.F;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import c0.C0086a;
import com.samsung.android.vexfwk.bitmap.VexFwkBitmapHardware;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkNodeResultCode;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import dd.b;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.k;
import me.x;
import qd.C1224a;
import vd.d;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000f\u0010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0000J.\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkVideoObjectRemover;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "configure", "removeObject", "Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/param/VexFwkNodeResultCode;", "inputPath", "", "outputPath", "inputMask", "Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkVideoObjectRemover$ObjectMask;", "mediaScan", "", "Companion", "ObjectMask", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkVideoObjectRemover extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_MASK = 0;
    private static final String TAG = "VexFwkVideoObjectRemover";
    /* access modifiers changed from: private */
    public static final boolean isAvailable = VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.VIDEO_OBJECT_REMOVAL);

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u00020\u000b8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\u0003\u001a\u0004\b\n\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkVideoObjectRemover$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "STREAM_ID_INPUT_MASK", "", "isAvailable", "", "isAvailable$annotations", "()Z", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isAvailable() {
            return VexFwkVideoObjectRemover.isAvailable;
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003J1\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020'HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006("}, d2 = {"Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkVideoObjectRemover$ObjectMask;", "", "mask", "", "size", "Landroid/util/Size;", "cropRect", "Landroid/graphics/Rect;", "maskFrameTimestampMs", "", "<init>", "([ILandroid/util/Size;Landroid/graphics/Rect;J)V", "getMask", "()[I", "setMask", "([I)V", "getSize", "()Landroid/util/Size;", "setSize", "(Landroid/util/Size;)V", "getCropRect", "()Landroid/graphics/Rect;", "setCropRect", "(Landroid/graphics/Rect;)V", "getMaskFrameTimestampMs", "()J", "setMaskFrameTimestampMs", "(J)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ObjectMask {
        private Rect cropRect;
        private int[] mask;
        private long maskFrameTimestampMs;
        private Size size;

        public ObjectMask(int[] iArr, Size size2, Rect rect, long j2) {
            j.e(iArr, "mask");
            j.e(size2, "size");
            j.e(rect, "cropRect");
            this.mask = iArr;
            this.size = size2;
            this.cropRect = rect;
            this.maskFrameTimestampMs = j2;
        }

        public static /* synthetic */ ObjectMask copy$default(ObjectMask objectMask, int[] iArr, Size size2, Rect rect, long j2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                iArr = objectMask.mask;
            }
            if ((i2 & 2) != 0) {
                size2 = objectMask.size;
            }
            if ((i2 & 4) != 0) {
                rect = objectMask.cropRect;
            }
            if ((i2 & 8) != 0) {
                j2 = objectMask.maskFrameTimestampMs;
            }
            long j3 = j2;
            return objectMask.copy(iArr, size2, rect, j3);
        }

        public final int[] component1() {
            return this.mask;
        }

        public final Size component2() {
            return this.size;
        }

        public final Rect component3() {
            return this.cropRect;
        }

        public final long component4() {
            return this.maskFrameTimestampMs;
        }

        public final ObjectMask copy(int[] iArr, Size size2, Rect rect, long j2) {
            j.e(iArr, "mask");
            j.e(size2, "size");
            j.e(rect, "cropRect");
            return new ObjectMask(iArr, size2, rect, j2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ObjectMask)) {
                return false;
            }
            ObjectMask objectMask = (ObjectMask) obj;
            if (j.a(this.mask, objectMask.mask) && j.a(this.size, objectMask.size) && j.a(this.cropRect, objectMask.cropRect) && this.maskFrameTimestampMs == objectMask.maskFrameTimestampMs) {
                return true;
            }
            return false;
        }

        public final Rect getCropRect() {
            return this.cropRect;
        }

        public final int[] getMask() {
            return this.mask;
        }

        public final long getMaskFrameTimestampMs() {
            return this.maskFrameTimestampMs;
        }

        public final Size getSize() {
            return this.size;
        }

        public int hashCode() {
            int hashCode = this.size.hashCode();
            int hashCode2 = this.cropRect.hashCode();
            return Long.hashCode(this.maskFrameTimestampMs) + ((hashCode2 + ((hashCode + (Arrays.hashCode(this.mask) * 31)) * 31)) * 31);
        }

        public final void setCropRect(Rect rect) {
            j.e(rect, "<set-?>");
            this.cropRect = rect;
        }

        public final void setMask(int[] iArr) {
            j.e(iArr, "<set-?>");
            this.mask = iArr;
        }

        public final void setMaskFrameTimestampMs(long j2) {
            this.maskFrameTimestampMs = j2;
        }

        public final void setSize(Size size2) {
            j.e(size2, "<set-?>");
            this.size = size2;
        }

        public String toString() {
            String arrays = Arrays.toString(this.mask);
            Size size2 = this.size;
            Rect rect = this.cropRect;
            long j2 = this.maskFrameTimestampMs;
            return "ObjectMask(mask=" + arrays + ", size=" + size2 + ", cropRect=" + rect + ", maskFrameTimestampMs=" + j2 + ")";
        }
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1(VexFwkVideoObjectRemover vexFwkVideoObjectRemover) {
        j.e(vexFwkVideoObjectRemover, "$this$configureWith");
        vexFwkVideoObjectRemover.createSession(VexFwkUsecase.VIDEO_OBJECT_REMOVAL, new d(15));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1$lambda$0(VexFwkSessionConfigRequest.Builder builder) {
        j.e(builder, "$this$createSession");
        VexFwkSessionConfigRequest.Builder.addInputStream$default(builder, 0, VexFwkStreamType.BUFFER, VexFwkStreamUsage.MASK, 0, 0, 0, (Surface) null, 120, (Object) null);
        return x.f4917a;
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    public static /* synthetic */ CompletableFuture removeObject$default(VexFwkVideoObjectRemover vexFwkVideoObjectRemover, String str, String str2, ObjectMask objectMask, boolean z, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z = false;
        }
        return vexFwkVideoObjectRemover.removeObject(str, str2, objectMask, z);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public static final VexFwkNodeResultCode removeObject$lambda$9(boolean z, ObjectMask objectMask, String str, String str2, VexFwkVideoObjectRemover vexFwkVideoObjectRemover) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        String str3 = TAG;
        Log.d(str3, "removeObject E");
        Size size = objectMask.getSize();
        Rect cropRect = objectMask.getCropRect();
        Log.d(str3, "inputMask size " + size + ", cropRect " + cropRect);
        VexFwkBitmapHardware vexFwkBitmapHardware = new VexFwkBitmapHardware(objectMask.getSize().getWidth(), objectMask.getSize().getHeight(), 1, objectMask.getMask());
        try {
            Bitmap bitmapCropped = vexFwkBitmapHardware.toBitmapCropped(objectMask.getCropRect().left, objectMask.getCropRect().top, objectMask.getCropRect().right, objectMask.getCropRect().bottom, Bitmap.Config.ARGB_8888);
            F.u(vexFwkBitmapHardware, (Throwable) null);
            VexFwkSessionRequest.Sync build = ((VexFwkSessionRequest.Sync.Builder) VexFwkSessionRequestKt.setSettingMetadata(VexFwkSessionRequestKt.addInputBuffer(new VexFwkSessionRequest.Sync.Builder(), 0, bitmapCropped), new b(objectMask, str, str2, z ? 1 : 0, 1))).build();
            try {
                Object r11 = vexFwkVideoObjectRemover.getSession(VexFwkUsecase.VIDEO_OBJECT_REMOVAL).m60processRequestIoAF18A(build);
                F.u(build, (Throwable) null);
                Throwable a7 = k.a(r11);
                if (a7 == null) {
                    VexFwkSessionTotalResult vexFwkSessionTotalResult = (VexFwkSessionTotalResult) r11;
                    try {
                        VexFwkNodeResultCode vexFwkNodeResultCode = (VexFwkNodeResultCode) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.RESULT_CODE.INSTANCE);
                        F.u(vexFwkSessionTotalResult, (Throwable) null);
                        bitmapCropped.recycle();
                        Log.d(str3, "removeObject X");
                        return vexFwkNodeResultCode;
                    } catch (Throwable th4) {
                        Throwable th5 = th4;
                        F.u(vexFwkSessionTotalResult, th3);
                        throw th5;
                    }
                } else {
                    C0086a.x("Failed to process request : ", str3, a7);
                    throw a7;
                }
            } catch (Throwable th6) {
                Throwable th7 = th6;
                F.u(build, th2);
                throw th7;
            }
        } catch (Throwable th8) {
            Throwable th9 = th8;
            F.u(vexFwkBitmapHardware, th);
            throw th9;
        }
    }

    /* access modifiers changed from: private */
    public static final x removeObject$lambda$9$lambda$3(ObjectMask objectMask, String str, String str2, int i2, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.VIDEO_OBJECT_REMOVER_MASK_FRAME_TIMESTAMP_MS.INSTANCE, Long.valueOf(objectMask.getMaskFrameTimestampMs()));
        vexFwkMetadataNative.set(VexFwkMetadataKey.INPUT_PATH.INSTANCE, str);
        vexFwkMetadataNative.set(VexFwkMetadataKey.OUTPUT_PATH.INSTANCE, str2);
        vexFwkMetadataNative.set(VexFwkMetadataKey.MEDIA_SCAN.INSTANCE, Integer.valueOf(i2));
        return x.f4917a;
    }

    public final VexFwkVideoObjectRemover configure() {
        return (VexFwkVideoObjectRemover) configureWith(this, new d(16));
    }

    public final CompletableFuture<VexFwkNodeResultCode> removeObject(String str, String str2, ObjectMask objectMask, boolean z) {
        j.e(str, "inputPath");
        j.e(str2, "outputPath");
        j.e(objectMask, "inputMask");
        ObjectMask objectMask2 = objectMask;
        boolean z3 = z;
        CompletableFuture<VexFwkNodeResultCode> supplyAsync = CompletableFuture.supplyAsync(new C1224a(z3, objectMask2, str, str2, this, 2));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }
}
