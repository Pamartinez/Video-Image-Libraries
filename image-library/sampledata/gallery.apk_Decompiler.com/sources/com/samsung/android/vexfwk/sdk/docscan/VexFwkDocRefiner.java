package com.samsung.android.vexfwk.sdk.docscan;

import Ad.C0721b;
import Ae.b;
import He.F;
import L1.d;
import N2.j;
import W.a;
import Wf.c;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import c0.C0086a;
import com.samsung.android.vexfwk.bitmap.VexFwkBitmapHardware;
import com.samsung.android.vexfwk.docscan.VexFwkDocRefineModes;
import com.samsung.android.vexfwk.docscan.VexFwkDocRefinerCapabilities;
import com.samsung.android.vexfwk.hardware.VexFwkHardwareBufferNative;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkDocumentCorners;
import com.samsung.android.vexfwk.param.VexFwkDocumentRefinerModes;
import com.samsung.android.vexfwk.param.VexFwkDocumentRefinerParams;
import com.samsung.android.vexfwk.param.VexFwkDocumentRefinerResult;
import com.samsung.android.vexfwk.param.VexFwkNodeResultCode;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkProvider;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionPartialResult;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import me.f;
import me.i;
import me.k;
import me.x;
import ne.C1194l;
import ne.C1200r;
import ne.C1202t;
import o1.C0246a;

@Metadata(d1 = {"\u0000s\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\b\u0007*\u00019\u0018\u0000 <2\u00020\u0001:\u0003<=>B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0000¢\u0006\u0004\b\u0004\u0010\u0005J+\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000e\u0010\u000fJ+\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000e\u0010\u0011J7\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0018H\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001dH\u0007¢\u0006\u0004\b\u001e\u0010\u001fJ3\u0010\"\u001a\b\u0012\u0004\u0012\u00020\r0\f\"\u0004\b\u0000\u0010 2\u0006\u0010!\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\"\u0010#J%\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010'0&2\u0006\u0010%\u001a\u00020$H\u0003¢\u0006\u0004\b(\u0010)J%\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010'0&2\u0006\u0010+\u001a\u00020*H\u0003¢\u0006\u0004\b(\u0010,R$\u0010-\u001a\u0004\u0018\u00010\u00168\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u00102R$\u00103\u001a\u0004\u0018\u00010\u00188\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0004¢\u0006\u0006\n\u0004\b:\u0010;¨\u0006?"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocRefiner;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "configure", "()Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocRefiner;", "Landroid/media/Image;", "image", "Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$EnhancementMode;", "enhance", "Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$ColorFilterType;", "colorFilter", "Ljava/util/concurrent/CompletableFuture;", "Landroid/graphics/Bitmap;", "refineDocument", "(Landroid/media/Image;Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$EnhancementMode;Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$ColorFilterType;)Ljava/util/concurrent/CompletableFuture;", "bitmap", "(Landroid/graphics/Bitmap;Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$EnhancementMode;Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$ColorFilterType;)Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerParams;", "docRefineParam", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentCorners;", "docCornerList", "Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocRefiner$VexFwkBatchRefineCallback;", "callback", "Ljava/util/concurrent/Executor;", "executor", "Lme/x;", "batchRefineDocument", "(Landroid/graphics/Bitmap;Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerParams;Lcom/samsung/android/vexfwk/param/VexFwkDocumentCorners;Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocRefiner$VexFwkBatchRefineCallback;Ljava/util/concurrent/Executor;)V", "", "isBatchRefineDocumentSupported", "()Z", "T", "buffer", "refineDocumentImpl", "(Ljava/lang/Object;Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$EnhancementMode;Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$ColorFilterType;)Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "totalResult", "Lme/i;", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerResult;", "handleProcessResult", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;)Lme/i;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionPartialResult;", "partialResult", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionPartialResult;)Lme/i;", "mCallback", "Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocRefiner$VexFwkBatchRefineCallback;", "getMCallback", "()Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocRefiner$VexFwkBatchRefineCallback;", "setMCallback", "(Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocRefiner$VexFwkBatchRefineCallback;)V", "mExecutor", "Ljava/util/concurrent/Executor;", "getMExecutor", "()Ljava/util/concurrent/Executor;", "setMExecutor", "(Ljava/util/concurrent/Executor;)V", "com/samsung/android/vexfwk/sdk/docscan/VexFwkDocRefiner$sessionCallback$1", "sessionCallback", "Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocRefiner$sessionCallback$1;", "Companion", "RequiresBatchRefineSupport", "VexFwkBatchRefineCallback", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkDocRefiner extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_IMAGE = 0;
    private static final int STREAM_ID_OUTPUT_IMAGE = 1;
    /* access modifiers changed from: private */
    public static final String TAG = "VexFwkDocRefiner";
    /* access modifiers changed from: private */
    public static final boolean isAvailable = VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.DOCUMENT_REFINEMENT);
    /* access modifiers changed from: private */
    public static final f supportedModes$delegate = d.q(new C0721b(13));
    private VexFwkBatchRefineCallback mCallback;
    private Executor mExecutor;
    private final VexFwkDocRefiner$sessionCallback$1 sessionCallback = new VexFwkDocRefiner$sessionCallback$1(this);

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0005\u0010\u0006R \u0010\b\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u000b\u0010\u0003\u001a\u0004\b\b\u0010\nR'\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\f8FX\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u0012\u0004\b\u0012\u0010\u0003\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0016\u001a\n \u0015*\u0004\u0018\u00010\u00140\u00148\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b\u001b\u0010\u001a¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocRefiner$Companion;", "", "<init>", "()V", "Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefinerCapabilities;", "capabilities", "()Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefinerCapabilities;", "", "isAvailable", "Z", "()Z", "isAvailable$annotations", "", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerModes;", "supportedModes$delegate", "Lme/f;", "getSupportedModes", "()Ljava/util/List;", "getSupportedModes$annotations", "supportedModes", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "", "STREAM_ID_INPUT_IMAGE", "I", "STREAM_ID_OUTPUT_IMAGE", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final VexFwkDocRefinerCapabilities capabilities$lambda$1$lambda$0() {
            return new VexFwkDocRefinerCapabilities(new int[0]);
        }

        public final VexFwkDocRefinerCapabilities capabilities() {
            VexFwkDocRefinerCapabilities vexFwkDocRefinerCapabilities = (VexFwkDocRefinerCapabilities) VexFwkProvider.fetchProperties(VexFwkUsecase.DOCUMENT_REFINEMENT).getOrElse(VexFwkMetadataKey.PROPERTY_DOC_REFINER_CAPABILITIES.INSTANCE, new a(27));
            j.w("capabilities : ", C1194l.R0(vexFwkDocRefinerCapabilities, (String) null, (String) null, (String) null, (b) null, 63), VexFwkDocRefiner.TAG);
            return vexFwkDocRefinerCapabilities;
        }

        public final List<VexFwkDocumentRefinerModes> getSupportedModes() {
            return (List) VexFwkDocRefiner.supportedModes$delegate.getValue();
        }

        public final boolean isAvailable() {
            return VexFwkDocRefiner.isAvailable;
        }

        private Companion() {
        }

        public static /* synthetic */ void getSupportedModes$annotations() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    @Target({ElementType.METHOD})
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocRefiner$RequiresBatchRefineSupport;", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @Retention(RetentionPolicy.RUNTIME)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface RequiresBatchRefineSupport {
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J%\u0010\u0007\u001a\u00020\u00062\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0002H&¢\u0006\u0004\b\u0007\u0010\bJ%\u0010\t\u001a\u00020\u00062\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0002H&¢\u0006\u0004\b\t\u0010\bJ\u0017\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nH&¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocRefiner$VexFwkBatchRefineCallback;", "", "Lme/i;", "Landroid/graphics/Bitmap;", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerResult;", "result", "Lme/x;", "onTaskCompleted", "(Lme/i;)V", "onAllTasksCompleted", "", "throwable", "onFailed", "(Ljava/lang/Throwable;)V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface VexFwkBatchRefineCallback {
        void onAllTasksCompleted(i iVar);

        void onFailed(Throwable th);

        void onTaskCompleted(i iVar);
    }

    /* access modifiers changed from: private */
    public static final x batchRefineDocument$lambda$10(VexFwkDocumentRefinerParams vexFwkDocumentRefinerParams, VexFwkDocumentCorners vexFwkDocumentCorners, VexFwkMetadataNative vexFwkMetadataNative) {
        kotlin.jvm.internal.j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.DOCUMENT_REFINE_PARAM.INSTANCE, vexFwkDocumentRefinerParams);
        vexFwkMetadataNative.set(VexFwkMetadataKey.DOCUMENT_RECT.INSTANCE, vexFwkDocumentCorners);
        return x.f4917a;
    }

    public static final VexFwkDocRefinerCapabilities capabilities() {
        return Companion.capabilities();
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1(VexFwkDocRefiner vexFwkDocRefiner) {
        kotlin.jvm.internal.j.e(vexFwkDocRefiner, "$this$configureWith");
        vexFwkDocRefiner.createSession(VexFwkUsecase.DOCUMENT_REFINEMENT, new b(1));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1$lambda$0(VexFwkSessionConfigRequest.Builder builder) {
        kotlin.jvm.internal.j.e(builder, "$this$createSession");
        VexFwkStreamType vexFwkStreamType = VexFwkStreamType.BUFFER;
        VexFwkStreamUsage vexFwkStreamUsage = VexFwkStreamUsage.IMAGE;
        VexFwkSessionConfigRequest.Builder builder2 = builder;
        VexFwkSessionConfigRequest.Builder.addInputStream$default(builder2, 0, vexFwkStreamType, vexFwkStreamUsage, 0, 0, 0, (Surface) null, 120, (Object) null);
        VexFwkSessionConfigRequest.Builder.addOutputStream$default(builder2, 1, vexFwkStreamType, vexFwkStreamUsage, 0, 0, 0, (Surface) null, 120, (Object) null);
        return x.f4917a;
    }

    public static final List<VexFwkDocumentRefinerModes> getSupportedModes() {
        return Companion.getSupportedModes();
    }

    /* access modifiers changed from: private */
    public final i handleProcessResult(VexFwkSessionTotalResult vexFwkSessionTotalResult) {
        Bitmap bitmap;
        Object obj;
        Log.i(TAG, "handleProcessResult E");
        if (vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.RESULT_CODE.INSTANCE) == VexFwkNodeResultCode.OK) {
            ArrayList arrayList = new ArrayList();
            for (VexFwkSessionPartialResult outputBuffers : vexFwkSessionTotalResult.getPartialResults()) {
                C1200r.A0(outputBuffers.getOutputBuffers(), arrayList);
            }
            Iterator it = arrayList.iterator();
            while (true) {
                bitmap = null;
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((VexFwkBuffer) obj).getStreamId() == 1) {
                    break;
                }
            }
            VexFwkBuffer vexFwkBuffer = (VexFwkBuffer) obj;
            if (vexFwkBuffer != null) {
                bitmap = VexFwkHardwareBufferNative.Companion.convertToBitmap$default(VexFwkHardwareBufferNative.Companion, vexFwkBuffer.getHardwareBuffer(), false, 2, (Object) null);
            }
            if (bitmap != null) {
                Log.i(TAG, "handleProcessResult X");
                return new i(bitmap, (VexFwkDocumentRefinerResult) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.DOCUMENT_REFINE_RESULT.INSTANCE));
            }
            throw new IllegalStateException("Output buffer for image not found");
        }
        Executor executor = this.mExecutor;
        if (executor != null) {
            executor.execute(new c(this, 0));
        }
        throw new IllegalStateException("Failed to process refine");
    }

    /* access modifiers changed from: private */
    public static final void handleProcessResult$lambda$13$lambda$12(VexFwkDocRefiner vexFwkDocRefiner) {
        VexFwkBatchRefineCallback vexFwkBatchRefineCallback = vexFwkDocRefiner.mCallback;
        if (vexFwkBatchRefineCallback != null) {
            vexFwkBatchRefineCallback.onFailed(new IllegalStateException("Failed to process refine"));
        }
    }

    /* access modifiers changed from: private */
    public static final void handleProcessResult$lambda$19$lambda$18(VexFwkDocRefiner vexFwkDocRefiner) {
        VexFwkBatchRefineCallback vexFwkBatchRefineCallback = vexFwkDocRefiner.mCallback;
        if (vexFwkBatchRefineCallback != null) {
            vexFwkBatchRefineCallback.onFailed(new IllegalStateException("Failed to process refine"));
        }
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    private final <T> CompletableFuture<Bitmap> refineDocumentImpl(T t, VexFwkDocRefineModes.Companion.EnhancementMode enhancementMode, VexFwkDocRefineModes.Companion.ColorFilterType colorFilterType) {
        VexFwkDocRefineModes.Companion.EnhancementMode enhancementMode2 = enhancementMode;
        VexFwkDocRefineModes.Companion.ColorFilterType colorFilterType2 = colorFilterType;
        CompletableFuture<Bitmap> supplyAsync = CompletableFuture.supplyAsync(new com.samsung.android.sum.core.service.d(2, colorFilterType2, enhancementMode2, t, this));
        kotlin.jvm.internal.j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public static final Bitmap refineDocumentImpl$lambda$9(VexFwkDocRefineModes.Companion.EnhancementMode enhancementMode, VexFwkDocRefineModes.Companion.ColorFilterType colorFilterType, Object obj, VexFwkDocRefiner vexFwkDocRefiner) {
        Size size;
        Throwable th;
        Throwable th2;
        String str = TAG;
        Log.i(str, "refineDocumentImpl E");
        if (enhancementMode == VexFwkDocRefineModes.Companion.EnhancementMode.OFF && colorFilterType == VexFwkDocRefineModes.Companion.ColorFilterType.COLOR_FILTER_NONE && !(obj instanceof Image) && (obj instanceof Bitmap)) {
            return (Bitmap) obj;
        }
        if (obj instanceof Image) {
            Image image = (Image) obj;
            size = new Size(image.getWidth(), image.getHeight());
        } else if (obj instanceof Bitmap) {
            Bitmap bitmap = (Bitmap) obj;
            size = new Size(bitmap.getWidth(), bitmap.getHeight());
        } else {
            throw new IllegalArgumentException("Unsupported image type");
        }
        VexFwkBitmapHardware vexFwkBitmapHardware = new VexFwkBitmapHardware(size.getWidth(), size.getHeight(), 1, (int[]) null);
        Log.d(str, "refineDocumentImpl enhance: " + enhancementMode);
        Log.d(str, "refineDocumentImpl colorfilter: " + colorFilterType);
        VexFwkSessionRequest.Sync build = ((VexFwkSessionRequest.Sync.Builder) VexFwkSessionRequestKt.setSettingMetadata(VexFwkSessionRequestKt.addOutputBuffer(VexFwkSessionRequestKt.addInputBuffer(new VexFwkSessionRequest.Sync.Builder(), 0, obj), 1, vexFwkBitmapHardware.getBitmap()), new c(8, colorFilterType, enhancementMode))).build();
        try {
            Object r10 = vexFwkDocRefiner.getSession(VexFwkUsecase.DOCUMENT_REFINEMENT).m60processRequestIoAF18A(build);
            F.u(build, (Throwable) null);
            Throwable a7 = k.a(r10);
            if (a7 == null) {
                Log.d(str, "refineDocumentImpl success");
                ((VexFwkSessionTotalResult) r10).close();
                try {
                    Bitmap bitmapCropped = vexFwkBitmapHardware.toBitmapCropped(0, 0, vexFwkBitmapHardware.getWidth(), vexFwkBitmapHardware.getHeight(), Bitmap.Config.ARGB_8888);
                    F.u(vexFwkBitmapHardware, (Throwable) null);
                    Log.i(str, "refineDocumentImpl X");
                    return bitmapCropped;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    F.u(vexFwkBitmapHardware, th2);
                    throw th4;
                }
            } else {
                C0086a.x("Failed to process request : ", str, a7);
                throw a7;
            }
        } catch (Throwable th5) {
            Throwable th6 = th5;
            F.u(build, th);
            throw th6;
        }
    }

    /* access modifiers changed from: private */
    public static final x refineDocumentImpl$lambda$9$lambda$3(VexFwkDocRefineModes.Companion.ColorFilterType colorFilterType, VexFwkDocRefineModes.Companion.EnhancementMode enhancementMode, VexFwkMetadataNative vexFwkMetadataNative) {
        kotlin.jvm.internal.j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.DOCUMENT_REFINE_COLOR_FILTER_TYPE.INSTANCE, Integer.valueOf(colorFilterType.getValue()));
        vexFwkMetadataNative.set(VexFwkMetadataKey.DOCUMENT_REFINE_ENHANCE.INSTANCE, Integer.valueOf(enhancementMode.getValue()));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final List supportedModes_delegate$lambda$24() {
        return (List) VexFwkSdkBase.Companion.getProperties(VexFwkUsecase.DOCUMENT_REFINEMENT, VexFwkMetadataKey.PROPERTY_DOCUMENT_REFINER_SUPPORTED_MODES.INSTANCE, new a(26));
    }

    /* access modifiers changed from: private */
    public static final List supportedModes_delegate$lambda$24$lambda$23() {
        if (isAvailable) {
            return C0246a.e0(VexFwkDocumentRefinerModes.SINGLE);
        }
        return C1202t.d;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x005f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0060, code lost:
        He.F.u(r3, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0063, code lost:
        throw r4;
     */
    @com.samsung.android.vexfwk.sdk.docscan.VexFwkDocRefiner.RequiresBatchRefineSupport
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void batchRefineDocument(android.graphics.Bitmap r3, com.samsung.android.vexfwk.param.VexFwkDocumentRefinerParams r4, com.samsung.android.vexfwk.param.VexFwkDocumentCorners r5, com.samsung.android.vexfwk.sdk.docscan.VexFwkDocRefiner.VexFwkBatchRefineCallback r6, java.util.concurrent.Executor r7) {
        /*
            r2 = this;
            java.lang.String r0 = "bitmap"
            kotlin.jvm.internal.j.e(r3, r0)
            java.lang.String r0 = "docRefineParam"
            kotlin.jvm.internal.j.e(r4, r0)
            java.lang.String r0 = "docCornerList"
            kotlin.jvm.internal.j.e(r5, r0)
            java.lang.String r0 = "callback"
            kotlin.jvm.internal.j.e(r6, r0)
            java.lang.String r0 = "executor"
            kotlin.jvm.internal.j.e(r7, r0)
            java.lang.String r0 = TAG
            java.lang.String r1 = "batchRefineDocument E"
            android.util.Log.i(r0, r1)
            boolean r1 = r2.isBatchRefineDocumentSupported()
            if (r1 == 0) goto L_0x0064
            r2.mCallback = r6
            r2.mExecutor = r7
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Async$Builder r6 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Async$Builder
            r6.<init>()
            r7 = 0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r3 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addInputBuffer(r6, (int) r7, (android.graphics.Bitmap) r3)
            Wf.c r6 = new Wf.c
            r7 = 7
            r6.<init>(r7, r4, r5)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r3 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.setSettingMetadata(r3, r6)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Async$Builder r3 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Async.Builder) r3
            com.samsung.android.vexfwk.sdk.docscan.VexFwkDocRefiner$sessionCallback$1 r4 = r2.sessionCallback
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Async$Builder r3 = r3.setCallback(r4)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Async r3 = r3.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r4 = com.samsung.android.vexfwk.session.VexFwkUsecase.DOCUMENT_REFINEMENT     // Catch:{ all -> 0x005d }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r2 = r2.getSession(r4)     // Catch:{ all -> 0x005d }
            r2.processRequest((com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Async) r3)     // Catch:{ all -> 0x005d }
            r2 = 0
            He.F.u(r3, r2)
            java.lang.String r2 = "batchRefineDocument X"
            android.util.Log.i(r0, r2)
            return
        L_0x005d:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x005f }
        L_0x005f:
            r4 = move-exception
            He.F.u(r3, r2)
            throw r4
        L_0x0064:
            java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
            java.lang.String r3 = "batchRefineDocument is not supported on this device"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.docscan.VexFwkDocRefiner.batchRefineDocument(android.graphics.Bitmap, com.samsung.android.vexfwk.param.VexFwkDocumentRefinerParams, com.samsung.android.vexfwk.param.VexFwkDocumentCorners, com.samsung.android.vexfwk.sdk.docscan.VexFwkDocRefiner$VexFwkBatchRefineCallback, java.util.concurrent.Executor):void");
    }

    public final VexFwkDocRefiner configure() {
        return (VexFwkDocRefiner) configureWith(this, new b(2));
    }

    public final VexFwkBatchRefineCallback getMCallback() {
        return this.mCallback;
    }

    public final Executor getMExecutor() {
        return this.mExecutor;
    }

    public final boolean isBatchRefineDocumentSupported() {
        if (!isAvailable) {
            Log.w(TAG, "Document refinement feature is not available");
            return false;
        }
        String str = TAG;
        Companion companion = Companion;
        List<VexFwkDocumentRefinerModes> supportedModes = companion.getSupportedModes();
        Log.d(str, "Supported Modes : " + supportedModes);
        return companion.getSupportedModes().contains(VexFwkDocumentRefinerModes.BATCH);
    }

    public final CompletableFuture<Bitmap> refineDocument(Image image, VexFwkDocRefineModes.Companion.EnhancementMode enhancementMode, VexFwkDocRefineModes.Companion.ColorFilterType colorFilterType) {
        kotlin.jvm.internal.j.e(image, "image");
        kotlin.jvm.internal.j.e(enhancementMode, "enhance");
        kotlin.jvm.internal.j.e(colorFilterType, "colorFilter");
        return refineDocumentImpl(image, enhancementMode, colorFilterType);
    }

    public final void setMCallback(VexFwkBatchRefineCallback vexFwkBatchRefineCallback) {
        this.mCallback = vexFwkBatchRefineCallback;
    }

    public final void setMExecutor(Executor executor) {
        this.mExecutor = executor;
    }

    public final CompletableFuture<Bitmap> refineDocument(Bitmap bitmap, VexFwkDocRefineModes.Companion.EnhancementMode enhancementMode, VexFwkDocRefineModes.Companion.ColorFilterType colorFilterType) {
        kotlin.jvm.internal.j.e(bitmap, "bitmap");
        kotlin.jvm.internal.j.e(enhancementMode, "enhance");
        kotlin.jvm.internal.j.e(colorFilterType, "colorFilter");
        return refineDocumentImpl(bitmap, enhancementMode, colorFilterType);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: com.samsung.android.vexfwk.param.VexFwkDocumentRefinerResult} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final me.i handleProcessResult(com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionPartialResult r5) {
        /*
            r4 = this;
            java.lang.String r0 = TAG
            java.lang.String r1 = "handleProcessResult E"
            android.util.Log.i(r0, r1)
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r0 = r5.getResultMetadata()
            r1 = 0
            if (r0 == 0) goto L_0x0017
            com.samsung.android.vexfwk.metadata.VexFwkMetadataKey$RESULT_CODE r2 = com.samsung.android.vexfwk.metadata.VexFwkMetadataKey.RESULT_CODE.INSTANCE
            java.lang.Object r0 = r0.get(r2)
            com.samsung.android.vexfwk.param.VexFwkNodeResultCode r0 = (com.samsung.android.vexfwk.param.VexFwkNodeResultCode) r0
            goto L_0x0018
        L_0x0017:
            r0 = r1
        L_0x0018:
            com.samsung.android.vexfwk.param.VexFwkNodeResultCode r2 = com.samsung.android.vexfwk.param.VexFwkNodeResultCode.OK
            if (r0 != r2) goto L_0x0074
            java.util.List r4 = r5.getOutputBuffers()
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.Iterator r4 = r4.iterator()
        L_0x0026:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x003b
            java.lang.Object r0 = r4.next()
            r2 = r0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer r2 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer) r2
            int r2 = r2.getStreamId()
            r3 = 1
            if (r2 != r3) goto L_0x0026
            goto L_0x003c
        L_0x003b:
            r0 = r1
        L_0x003c:
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer r0 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer) r0
            if (r0 == 0) goto L_0x004d
            com.samsung.android.vexfwk.hardware.VexFwkHardwareBufferNative$Companion r4 = com.samsung.android.vexfwk.hardware.VexFwkHardwareBufferNative.Companion
            android.hardware.HardwareBuffer r0 = r0.getHardwareBuffer()
            r2 = 0
            r3 = 2
            android.graphics.Bitmap r4 = com.samsung.android.vexfwk.hardware.VexFwkHardwareBufferNative.Companion.convertToBitmap$default(r4, r0, r2, r3, r1)
            goto L_0x004e
        L_0x004d:
            r4 = r1
        L_0x004e:
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r5 = r5.getResultMetadata()
            if (r5 == 0) goto L_0x005d
            com.samsung.android.vexfwk.metadata.VexFwkMetadataKey$DOCUMENT_REFINE_RESULT r0 = com.samsung.android.vexfwk.metadata.VexFwkMetadataKey.DOCUMENT_REFINE_RESULT.INSTANCE
            java.lang.Object r5 = r5.get(r0)
            r1 = r5
            com.samsung.android.vexfwk.param.VexFwkDocumentRefinerResult r1 = (com.samsung.android.vexfwk.param.VexFwkDocumentRefinerResult) r1
        L_0x005d:
            if (r4 == 0) goto L_0x006c
            java.lang.String r5 = TAG
            java.lang.String r0 = "handleProcessResult X"
            android.util.Log.i(r5, r0)
            me.i r5 = new me.i
            r5.<init>(r4, r1)
            return r5
        L_0x006c:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "Output buffer for image not found"
            r4.<init>(r5)
            throw r4
        L_0x0074:
            java.util.concurrent.Executor r5 = r4.mExecutor
            if (r5 == 0) goto L_0x0081
            com.samsung.android.vexfwk.sdk.docscan.c r0 = new com.samsung.android.vexfwk.sdk.docscan.c
            r1 = 1
            r0.<init>(r4, r1)
            r5.execute(r0)
        L_0x0081:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "Failed to process refine"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.docscan.VexFwkDocRefiner.handleProcessResult(com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionPartialResult):me.i");
    }
}
