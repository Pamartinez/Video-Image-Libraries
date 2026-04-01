package com.samsung.android.vexfwk.sdk.objeraser;

import He.F;
import L1.d;
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
import com.samsung.android.vexfwk.param.VexFwkObjectRemoverMode;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import md.b;
import me.f;
import me.k;
import me.x;
import ne.C1202t;
import o1.C0246a;
import qd.C1224a;
import yd.C1354a;
import yd.C1355b;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00142\u00020\u0001:\u0002\u0014\u0015B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ5\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkImageObjectRemover;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "Lcom/samsung/android/vexfwk/param/VexFwkObjectRemoverMode;", "mode", "Lme/x;", "configure", "(Lcom/samsung/android/vexfwk/param/VexFwkObjectRemoverMode;)V", "", "inputPath", "outputPath", "Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkImageObjectRemover$ObjectMask;", "inputMask", "", "mediaScan", "Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/param/VexFwkNodeResultCode;", "removeObject", "(Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkImageObjectRemover$ObjectMask;Z)Ljava/util/concurrent/CompletableFuture;", "Companion", "ObjectMask", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageObjectRemover extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_MASK = 0;
    private static final String TAG = "VexFwkImageObjectRemover";
    /* access modifiers changed from: private */
    public static final f isAvailable$delegate = d.q(new b(7));
    /* access modifiers changed from: private */
    public static final f supportedModes$delegate = d.q(new b(8));

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R!\u0010\u0007\u001a\u00020\u00048FX\u0002¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u0012\u0004\b\t\u0010\u0003\u001a\u0004\b\u0007\u0010\bR'\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8FX\u0002¢\u0006\u0012\n\u0004\b\f\u0010\u0006\u0012\u0004\b\u000f\u0010\u0003\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0013\u001a\n \u0012*\u0004\u0018\u00010\u00110\u00118\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002XT¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkImageObjectRemover$Companion;", "", "<init>", "()V", "", "isAvailable$delegate", "Lme/f;", "isAvailable", "()Z", "isAvailable$annotations", "", "Lcom/samsung/android/vexfwk/param/VexFwkObjectRemoverMode;", "supportedModes$delegate", "getSupportedModes", "()Ljava/util/List;", "getSupportedModes$annotations", "supportedModes", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "", "STREAM_ID_INPUT_MASK", "I", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final List<VexFwkObjectRemoverMode> getSupportedModes() {
            return (List) VexFwkImageObjectRemover.supportedModes$delegate.getValue();
        }

        public final boolean isAvailable() {
            return ((Boolean) VexFwkImageObjectRemover.isAvailable$delegate.getValue()).booleanValue();
        }

        private Companion() {
        }

        public static /* synthetic */ void getSupportedModes$annotations() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J'\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkImageObjectRemover$ObjectMask;", "", "mask", "", "size", "Landroid/util/Size;", "cropRect", "Landroid/graphics/Rect;", "<init>", "([ILandroid/util/Size;Landroid/graphics/Rect;)V", "getMask", "()[I", "setMask", "([I)V", "getSize", "()Landroid/util/Size;", "setSize", "(Landroid/util/Size;)V", "getCropRect", "()Landroid/graphics/Rect;", "setCropRect", "(Landroid/graphics/Rect;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ObjectMask {
        private Rect cropRect;
        private int[] mask;
        private Size size;

        public ObjectMask(int[] iArr, Size size2, Rect rect) {
            j.e(iArr, "mask");
            j.e(size2, "size");
            j.e(rect, "cropRect");
            this.mask = iArr;
            this.size = size2;
            this.cropRect = rect;
        }

        public static /* synthetic */ ObjectMask copy$default(ObjectMask objectMask, int[] iArr, Size size2, Rect rect, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                iArr = objectMask.mask;
            }
            if ((i2 & 2) != 0) {
                size2 = objectMask.size;
            }
            if ((i2 & 4) != 0) {
                rect = objectMask.cropRect;
            }
            return objectMask.copy(iArr, size2, rect);
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

        public final ObjectMask copy(int[] iArr, Size size2, Rect rect) {
            j.e(iArr, "mask");
            j.e(size2, "size");
            j.e(rect, "cropRect");
            return new ObjectMask(iArr, size2, rect);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ObjectMask)) {
                return false;
            }
            ObjectMask objectMask = (ObjectMask) obj;
            if (j.a(this.mask, objectMask.mask) && j.a(this.size, objectMask.size) && j.a(this.cropRect, objectMask.cropRect)) {
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

        public final Size getSize() {
            return this.size;
        }

        public int hashCode() {
            int hashCode = this.size.hashCode();
            return this.cropRect.hashCode() + ((hashCode + (Arrays.hashCode(this.mask) * 31)) * 31);
        }

        public final void setCropRect(Rect rect) {
            j.e(rect, "<set-?>");
            this.cropRect = rect;
        }

        public final void setMask(int[] iArr) {
            j.e(iArr, "<set-?>");
            this.mask = iArr;
        }

        public final void setSize(Size size2) {
            j.e(size2, "<set-?>");
            this.size = size2;
        }

        public String toString() {
            String arrays = Arrays.toString(this.mask);
            Size size2 = this.size;
            Rect rect = this.cropRect;
            return "ObjectMask(mask=" + arrays + ", size=" + size2 + ", cropRect=" + rect + ")";
        }
    }

    public static /* synthetic */ void configure$default(VexFwkImageObjectRemover vexFwkImageObjectRemover, VexFwkObjectRemoverMode vexFwkObjectRemoverMode, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            vexFwkObjectRemoverMode = VexFwkObjectRemoverMode.LOW_QUALITY;
        }
        vexFwkImageObjectRemover.configure(vexFwkObjectRemoverMode);
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$2(VexFwkObjectRemoverMode vexFwkObjectRemoverMode, VexFwkImageObjectRemover vexFwkImageObjectRemover) {
        j.e(vexFwkImageObjectRemover, "$this$configureWith");
        vexFwkImageObjectRemover.createSession(VexFwkUsecase.IMAGE_OBJECT_REMOVAL, new C1354a(vexFwkObjectRemoverMode, 1));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$2$lambda$1(VexFwkObjectRemoverMode vexFwkObjectRemoverMode, VexFwkSessionConfigRequest.Builder builder) {
        j.e(builder, "$this$createSession");
        VexFwkSessionConfigRequest.Builder builder2 = builder;
        VexFwkSessionConfigRequest.Builder.addInputStream$default(builder2, 0, VexFwkStreamType.BUFFER, VexFwkStreamUsage.MASK, 0, 0, 0, (Surface) null, 120, (Object) null);
        builder2.setConfigMetadata((Ae.b) new C1354a(vexFwkObjectRemoverMode, 2));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$2$lambda$1$lambda$0(VexFwkObjectRemoverMode vexFwkObjectRemoverMode, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setConfigMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.OBJECT_REMOVER_MODE.INSTANCE, vexFwkObjectRemoverMode);
        return x.f4917a;
    }

    public static final List<VexFwkObjectRemoverMode> getSupportedModes() {
        return Companion.getSupportedModes();
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    /* access modifiers changed from: private */
    public static final boolean isAvailable_delegate$lambda$11() {
        return VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.IMAGE_OBJECT_REMOVAL);
    }

    public static /* synthetic */ CompletableFuture removeObject$default(VexFwkImageObjectRemover vexFwkImageObjectRemover, String str, String str2, ObjectMask objectMask, boolean z, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z = false;
        }
        return vexFwkImageObjectRemover.removeObject(str, str2, objectMask, z);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public static final VexFwkNodeResultCode removeObject$lambda$10(boolean z, ObjectMask objectMask, String str, String str2, VexFwkImageObjectRemover vexFwkImageObjectRemover) {
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
            VexFwkSessionRequest.Sync build = ((VexFwkSessionRequest.Sync.Builder) VexFwkSessionRequestKt.setSettingMetadata(VexFwkSessionRequestKt.addInputBuffer(new VexFwkSessionRequest.Sync.Builder(), 0, bitmapCropped), new C1355b(str, str2, z ? 1 : 0))).build();
            try {
                Object r10 = vexFwkImageObjectRemover.getSession(VexFwkUsecase.IMAGE_OBJECT_REMOVAL).m60processRequestIoAF18A(build);
                F.u(build, (Throwable) null);
                Throwable a7 = k.a(r10);
                if (a7 == null) {
                    VexFwkSessionTotalResult vexFwkSessionTotalResult = (VexFwkSessionTotalResult) r10;
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
    public static final x removeObject$lambda$10$lambda$4(String str, String str2, int i2, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.INPUT_PATH.INSTANCE, str);
        vexFwkMetadataNative.set(VexFwkMetadataKey.OUTPUT_PATH.INSTANCE, str2);
        vexFwkMetadataNative.set(VexFwkMetadataKey.MEDIA_SCAN.INSTANCE, Integer.valueOf(i2));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final List supportedModes_delegate$lambda$13() {
        return (List) VexFwkSdkBase.Companion.getProperties(VexFwkUsecase.IMAGE_OBJECT_REMOVAL, VexFwkMetadataKey.PROPERTY_OBJECT_REMOVER_SUPPORTED_MODES.INSTANCE, new e5.d(19));
    }

    /* access modifiers changed from: private */
    public static final List supportedModes_delegate$lambda$13$lambda$12() {
        if (Companion.isAvailable()) {
            return C0246a.e0(VexFwkObjectRemoverMode.LOW_QUALITY);
        }
        return C1202t.d;
    }

    public final void configure(VexFwkObjectRemoverMode vexFwkObjectRemoverMode) {
        j.e(vexFwkObjectRemoverMode, "mode");
        if (Companion.getSupportedModes().contains(vexFwkObjectRemoverMode)) {
            configureWith(this, new C1354a(vexFwkObjectRemoverMode, 0));
            return;
        }
        throw new IllegalStateException("Check failed.");
    }

    public final CompletableFuture<VexFwkNodeResultCode> removeObject(String str, String str2, ObjectMask objectMask, boolean z) {
        j.e(str, "inputPath");
        j.e(str2, "outputPath");
        j.e(objectMask, "inputMask");
        ObjectMask objectMask2 = objectMask;
        boolean z3 = z;
        CompletableFuture<VexFwkNodeResultCode> supplyAsync = CompletableFuture.supplyAsync(new C1224a(z3, objectMask2, str, str2, this, 1));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }
}
