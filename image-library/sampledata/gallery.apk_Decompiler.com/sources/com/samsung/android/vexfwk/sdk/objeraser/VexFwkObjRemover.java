package com.samsung.android.vexfwk.sdk.objeraser;

import He.F;
import L1.d;
import L2.a;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;
import android.view.Surface;
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
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import md.b;
import me.f;
import me.x;
import ne.C1202t;
import o1.C0246a;
import yd.C1354a;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011J+\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u00152\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkObjRemover;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "Landroid/graphics/Bitmap;", "inputBitmap", "maskBitmap", "Landroid/graphics/Rect;", "roi", "Lme/k;", "removeObjectInternal-0E7RQCE", "(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;Landroid/graphics/Rect;)Ljava/lang/Object;", "removeObjectInternal", "Lcom/samsung/android/vexfwk/param/VexFwkObjectRemoverMode;", "mode", "Lme/x;", "configure", "(Lcom/samsung/android/vexfwk/param/VexFwkObjectRemoverMode;)V", "bitmap", "", "mask", "Ljava/util/concurrent/CompletableFuture;", "removeObject", "(Landroid/graphics/Bitmap;[ILandroid/graphics/Rect;)Ljava/util/concurrent/CompletableFuture;", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkObjRemover extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_IMAGE = 0;
    private static final int STREAM_ID_INPUT_MASK = 1;
    private static final int STREAM_ID_OUTPUT_IMAGE = 2;
    private static final String TAG = "VexFwkObjRemover";
    /* access modifiers changed from: private */
    public static final f isAvailable$delegate = d.q(new b(9));
    /* access modifiers changed from: private */
    public static final f supportedModes$delegate = d.q(new b(10));

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R!\u0010\u0007\u001a\u00020\u00048FX\u0002¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u0012\u0004\b\t\u0010\u0003\u001a\u0004\b\u0007\u0010\bR'\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8FX\u0002¢\u0006\u0012\n\u0004\b\f\u0010\u0006\u0012\u0004\b\u000f\u0010\u0003\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0013\u001a\n \u0012*\u0004\u0018\u00010\u00110\u00118\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002XT¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00158\u0002XT¢\u0006\u0006\n\u0004\b\u0018\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00158\u0002XT¢\u0006\u0006\n\u0004\b\u0019\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkObjRemover$Companion;", "", "<init>", "()V", "", "isAvailable$delegate", "Lme/f;", "isAvailable", "()Z", "isAvailable$annotations", "", "Lcom/samsung/android/vexfwk/param/VexFwkObjectRemoverMode;", "supportedModes$delegate", "getSupportedModes", "()Ljava/util/List;", "getSupportedModes$annotations", "supportedModes", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "", "STREAM_ID_INPUT_IMAGE", "I", "STREAM_ID_INPUT_MASK", "STREAM_ID_OUTPUT_IMAGE", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final List<VexFwkObjectRemoverMode> getSupportedModes() {
            return (List) VexFwkObjRemover.supportedModes$delegate.getValue();
        }

        public final boolean isAvailable() {
            return ((Boolean) VexFwkObjRemover.isAvailable$delegate.getValue()).booleanValue();
        }

        private Companion() {
        }

        public static /* synthetic */ void getSupportedModes$annotations() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    public static /* synthetic */ void configure$default(VexFwkObjRemover vexFwkObjRemover, VexFwkObjectRemoverMode vexFwkObjectRemoverMode, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            vexFwkObjectRemoverMode = VexFwkObjectRemoverMode.LOW_QUALITY;
        }
        vexFwkObjRemover.configure(vexFwkObjectRemoverMode);
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$2(VexFwkObjectRemoverMode vexFwkObjectRemoverMode, VexFwkObjRemover vexFwkObjRemover) {
        j.e(vexFwkObjRemover, "$this$configureWith");
        vexFwkObjRemover.createSession(VexFwkUsecase.OBJECT_REMOVAL, new C1354a(vexFwkObjectRemoverMode, 5));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$2$lambda$1(VexFwkObjectRemoverMode vexFwkObjectRemoverMode, VexFwkSessionConfigRequest.Builder builder) {
        j.e(builder, "$this$createSession");
        VexFwkStreamType vexFwkStreamType = VexFwkStreamType.BUFFER;
        VexFwkStreamUsage vexFwkStreamUsage = VexFwkStreamUsage.IMAGE;
        VexFwkSessionConfigRequest.Builder builder2 = builder;
        VexFwkSessionConfigRequest.Builder.addInputStream$default(builder2, 0, vexFwkStreamType, vexFwkStreamUsage, 0, 0, 0, (Surface) null, 120, (Object) null);
        VexFwkSessionConfigRequest.Builder.addInputStream$default(builder2, 1, vexFwkStreamType, VexFwkStreamUsage.MASK, 0, 0, 0, (Surface) null, 120, (Object) null);
        VexFwkSessionConfigRequest.Builder.addOutputStream$default(builder2, 2, vexFwkStreamType, vexFwkStreamUsage, 0, 0, 0, (Surface) null, 120, (Object) null);
        builder2.setConfigMetadata((Ae.b) new C1354a(vexFwkObjectRemoverMode, 3));
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
    public static final boolean isAvailable_delegate$lambda$9() {
        return VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.OBJECT_REMOVAL);
    }

    /* access modifiers changed from: private */
    public static final Bitmap removeObject$lambda$8(Rect rect, int[] iArr, VexFwkObjRemover vexFwkObjRemover, Bitmap bitmap) {
        String str = TAG;
        Log.d(str, "removeObject E");
        VexFwkBitmapHardware vexFwkBitmapHardware = new VexFwkBitmapHardware(rect.width(), rect.height(), 1, iArr);
        Object r62 = vexFwkObjRemover.m61removeObjectInternal0E7RQCE(bitmap, vexFwkBitmapHardware.getBitmap(), rect);
        vexFwkBitmapHardware.close();
        a.A(r62);
        Bitmap bitmap2 = (Bitmap) r62;
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        int width2 = rect.width();
        int height2 = rect.height();
        StringBuilder h5 = A.a.h(width, height, "removeObject : ", "x", " -> ");
        h5.append(width2);
        h5.append("x");
        h5.append(height2);
        Log.d(str, h5.toString());
        Log.d(str, "removeObject X");
        return bitmap2;
    }

    /* renamed from: removeObjectInternal-0E7RQCE  reason: not valid java name */
    private final Object m61removeObjectInternal0E7RQCE(Bitmap bitmap, Bitmap bitmap2, Rect rect) {
        Object obj;
        Throwable th;
        VexFwkBitmapHardware vexFwkBitmapHardware = new VexFwkBitmapHardware(rect.width(), rect.height(), 1, (int[]) null);
        try {
            VexFwkSessionRequest.Sync build = ((VexFwkSessionRequest.Sync.Builder) VexFwkSessionRequestKt.addOutputBuffer(VexFwkSessionRequestKt.addInputBuffer(VexFwkSessionRequestKt.addInputBuffer(new VexFwkSessionRequest.Sync.Builder(), 0, bitmap), 1, bitmap2), 2, vexFwkBitmapHardware.getBitmap())).build();
            try {
                Object r72 = getSession(VexFwkUsecase.OBJECT_REMOVAL).m60processRequestIoAF18A(build);
                F.u(build, (Throwable) null);
                if (!(r72 instanceof me.j)) {
                    VexFwkSessionTotalResult vexFwkSessionTotalResult = (VexFwkSessionTotalResult) r72;
                    try {
                        VexFwkNodeResultCode vexFwkNodeResultCode = (VexFwkNodeResultCode) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.RESULT_CODE.INSTANCE);
                        F.u(vexFwkSessionTotalResult, (Throwable) null);
                        r72 = vexFwkNodeResultCode;
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        F.u(vexFwkSessionTotalResult, th);
                        throw th3;
                    }
                }
                a.A(r72);
                if (((VexFwkNodeResultCode) r72) == VexFwkNodeResultCode.OK) {
                    int i2 = rect.left;
                    int i7 = rect.top;
                    int i8 = rect.right;
                    int i10 = rect.bottom;
                    Bitmap.Config config = bitmap.getConfig();
                    j.b(config);
                    obj = vexFwkBitmapHardware.toBitmapCropped(i2, i7, i8, i10, config);
                    vexFwkBitmapHardware.close();
                    return obj;
                }
                throw new IllegalStateException("Failed to remove object");
            } catch (Throwable th4) {
                F.u(build, th);
                throw th;
            } finally {
                th = th4;
            }
        } catch (Throwable th5) {
            obj = a.l(th5);
        }
    }

    /* access modifiers changed from: private */
    public static final List supportedModes_delegate$lambda$11() {
        return (List) VexFwkSdkBase.Companion.getProperties(VexFwkUsecase.OBJECT_REMOVAL, VexFwkMetadataKey.PROPERTY_OBJECT_REMOVER_SUPPORTED_MODES.INSTANCE, new e5.d(20));
    }

    /* access modifiers changed from: private */
    public static final List supportedModes_delegate$lambda$11$lambda$10() {
        if (Companion.isAvailable()) {
            return C0246a.e0(VexFwkObjectRemoverMode.LOW_QUALITY);
        }
        return C1202t.d;
    }

    public final void configure(VexFwkObjectRemoverMode vexFwkObjectRemoverMode) {
        j.e(vexFwkObjectRemoverMode, "mode");
        if (Companion.getSupportedModes().contains(vexFwkObjectRemoverMode)) {
            configureWith(this, new C1354a(vexFwkObjectRemoverMode, 4));
            return;
        }
        throw new IllegalStateException("Check failed.");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [java.io.Serializable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.concurrent.CompletableFuture<android.graphics.Bitmap> removeObject(android.graphics.Bitmap r8, int[] r9, android.graphics.Rect r10) {
        /*
            r7 = this;
            java.lang.String r0 = "bitmap"
            kotlin.jvm.internal.j.e(r8, r0)
            java.lang.String r0 = "mask"
            kotlin.jvm.internal.j.e(r9, r0)
            java.lang.String r0 = "roi"
            kotlin.jvm.internal.j.e(r10, r0)
            com.samsung.android.sum.core.service.d r1 = new com.samsung.android.sum.core.service.d
            r2 = 3
            r5 = r7
            r6 = r8
            r3 = r9
            r4 = r10
            r1.<init>(r2, r3, r4, r5, r6)
            java.util.concurrent.CompletableFuture r7 = java.util.concurrent.CompletableFuture.supplyAsync(r1)
            java.lang.String r8 = "supplyAsync(...)"
            kotlin.jvm.internal.j.d(r7, r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjRemover.removeObject(android.graphics.Bitmap, int[], android.graphics.Rect):java.util.concurrent.CompletableFuture");
    }
}
