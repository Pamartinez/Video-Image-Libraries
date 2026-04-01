package com.samsung.android.vexfwk.sdk.imageenhancer;

import A8.C0544a;
import android.graphics.Bitmap;
import android.view.Surface;
import com.samsung.android.vexfwk.imageenhancer.EnhanceType;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkImageEnhancerParams;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.docscan.b;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0000J\u001e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imageenhancer/VexFwkImageEnhancer;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "configure", "applyImageEnhancer", "Ljava/util/concurrent/CompletableFuture;", "Landroid/graphics/Bitmap;", "inputBitmap", "enhanceType", "Lcom/samsung/android/vexfwk/imageenhancer/EnhanceType;", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageEnhancer extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_IMAGE = 0;
    private static final int STREAM_ID_OUTPUT_IMAGE = 1;
    private static final String TAG = "VexFwkImageEnhancer";
    /* access modifiers changed from: private */
    public static final boolean isAvailable = VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.IMAGE_ENHANCER);

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000b\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imageenhancer/VexFwkImageEnhancer$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "STREAM_ID_INPUT_IMAGE", "", "STREAM_ID_OUTPUT_IMAGE", "isAvailable", "", "isAvailable$annotations", "()Z", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isAvailable() {
            return VexFwkImageEnhancer.isAvailable;
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ee, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ef, code lost:
        He.F.u(r5, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00f2, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final android.graphics.Bitmap applyImageEnhancer$lambda$11(android.graphics.Bitmap r5, com.samsung.android.vexfwk.imageenhancer.EnhanceType r6, com.samsung.android.vexfwk.sdk.imageenhancer.VexFwkImageEnhancer r7) {
        /*
            java.lang.String r0 = "Failed to Enhance Image ResultCode : "
            java.lang.String r1 = TAG
            java.lang.String r2 = "applyImageEnhancer E"
            android.util.Log.d(r1, r2)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r2 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r2.<init>()
            r3 = 0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r5 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addInputBuffer(r2, (int) r3, (android.graphics.Bitmap) r5)
            Ad.f r2 = new Ad.f
            r4 = 20
            r2.<init>((int) r4, (java.lang.Object) r6)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r5 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.setSettingMetadata(r5, r2)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r5 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r5
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r5 = r5.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r6 = com.samsung.android.vexfwk.session.VexFwkUsecase.IMAGE_ENHANCER     // Catch:{ all -> 0x00ec }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r6 = r7.getSession(r6)     // Catch:{ all -> 0x00ec }
            java.lang.Object r6 = r6.m60processRequestIoAF18A(r5)     // Catch:{ all -> 0x00ec }
            r7 = 0
            He.F.u(r5, r7)
            java.lang.Throwable r5 = me.k.a(r6)
            if (r5 != 0) goto L_0x00e6
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r6 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r6
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r5 = r6.getResultMetadata()     // Catch:{ all -> 0x0071 }
            com.samsung.android.vexfwk.metadata.VexFwkMetadataKey$RESULT_CODE r1 = com.samsung.android.vexfwk.metadata.VexFwkMetadataKey.RESULT_CODE.INSTANCE     // Catch:{ all -> 0x0071 }
            java.lang.Object r5 = r5.get(r1)     // Catch:{ all -> 0x0071 }
            if (r5 == 0) goto L_0x00d8
            com.samsung.android.vexfwk.param.VexFwkNodeResultCode r5 = (com.samsung.android.vexfwk.param.VexFwkNodeResultCode) r5     // Catch:{ all -> 0x0071 }
            com.samsung.android.vexfwk.param.VexFwkNodeResultCode r1 = com.samsung.android.vexfwk.param.VexFwkNodeResultCode.OK     // Catch:{ all -> 0x0071 }
            if (r5 != r1) goto L_0x00c2
            java.util.List r0 = r6.getPartialResults()     // Catch:{ all -> 0x0071 }
            java.lang.Iterable r0 = (java.lang.Iterable) r0     // Catch:{ all -> 0x0071 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0071 }
            r1.<init>()     // Catch:{ all -> 0x0071 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0071 }
        L_0x005b:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x0071 }
            if (r2 == 0) goto L_0x0073
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x0071 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionPartialResult r2 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionPartialResult) r2     // Catch:{ all -> 0x0071 }
            java.util.List r2 = r2.getOutputBuffers()     // Catch:{ all -> 0x0071 }
            java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch:{ all -> 0x0071 }
            ne.C1200r.A0(r2, r1)     // Catch:{ all -> 0x0071 }
            goto L_0x005b
        L_0x0071:
            r5 = move-exception
            goto L_0x00e0
        L_0x0073:
            java.util.Iterator r0 = r1.iterator()     // Catch:{ all -> 0x0071 }
        L_0x0077:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0071 }
            if (r1 == 0) goto L_0x00ba
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0071 }
            r2 = r1
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer r2 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer) r2     // Catch:{ all -> 0x0071 }
            int r2 = r2.getStreamId()     // Catch:{ all -> 0x0071 }
            r4 = 1
            if (r2 != r4) goto L_0x0077
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer r1 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer) r1     // Catch:{ all -> 0x0071 }
            com.samsung.android.vexfwk.hardware.VexFwkHardwareBufferNative$Companion r0 = com.samsung.android.vexfwk.hardware.VexFwkHardwareBufferNative.Companion     // Catch:{ all -> 0x0071 }
            android.hardware.HardwareBuffer r1 = r1.getHardwareBuffer()     // Catch:{ all -> 0x0071 }
            r2 = 2
            android.graphics.Bitmap r0 = com.samsung.android.vexfwk.hardware.VexFwkHardwareBufferNative.Companion.convertToBitmap$default(r0, r1, r3, r2, r7)     // Catch:{ all -> 0x0071 }
            java.lang.String r1 = TAG     // Catch:{ all -> 0x0071 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0071 }
            r2.<init>()     // Catch:{ all -> 0x0071 }
            java.lang.String r3 = "Image Enhance SUCCESS, ResultCode : "
            r2.append(r3)     // Catch:{ all -> 0x0071 }
            r2.append(r5)     // Catch:{ all -> 0x0071 }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x0071 }
            android.util.Log.d(r1, r5)     // Catch:{ all -> 0x0071 }
            He.F.u(r6, r7)
            java.lang.String r5 = "applyImageEnhancer X"
            android.util.Log.d(r1, r5)
            kotlin.jvm.internal.j.b(r0)
            return r0
        L_0x00ba:
            java.util.NoSuchElementException r5 = new java.util.NoSuchElementException     // Catch:{ all -> 0x0071 }
            java.lang.String r7 = "Collection contains no element matching the predicate."
            r5.<init>(r7)     // Catch:{ all -> 0x0071 }
            throw r5     // Catch:{ all -> 0x0071 }
        L_0x00c2:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0071 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0071 }
            r1.<init>(r0)     // Catch:{ all -> 0x0071 }
            r1.append(r5)     // Catch:{ all -> 0x0071 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x0071 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0071 }
            r7.<init>(r5)     // Catch:{ all -> 0x0071 }
            throw r7     // Catch:{ all -> 0x0071 }
        L_0x00d8:
            java.lang.String r5 = "Result code is null for the request"
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0071 }
            r7.<init>(r5)     // Catch:{ all -> 0x0071 }
            throw r7     // Catch:{ all -> 0x0071 }
        L_0x00e0:
            throw r5     // Catch:{ all -> 0x00e1 }
        L_0x00e1:
            r7 = move-exception
            He.F.u(r6, r5)
            throw r7
        L_0x00e6:
            java.lang.String r6 = "Failed to process request : "
            c0.C0086a.x(r6, r1, r5)
            throw r5
        L_0x00ec:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x00ee }
        L_0x00ee:
            r7 = move-exception
            He.F.u(r5, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.imageenhancer.VexFwkImageEnhancer.applyImageEnhancer$lambda$11(android.graphics.Bitmap, com.samsung.android.vexfwk.imageenhancer.EnhanceType, com.samsung.android.vexfwk.sdk.imageenhancer.VexFwkImageEnhancer):android.graphics.Bitmap");
    }

    /* access modifiers changed from: private */
    public static final x applyImageEnhancer$lambda$11$lambda$2(EnhanceType enhanceType, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.IMAGE_ENHANCER_PARAMS.INSTANCE, new VexFwkImageEnhancerParams(enhanceType));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1(VexFwkImageEnhancer vexFwkImageEnhancer) {
        j.e(vexFwkImageEnhancer, "$this$configureWith");
        vexFwkImageEnhancer.createSession(VexFwkUsecase.IMAGE_ENHANCER, new b(18));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1$lambda$0(VexFwkSessionConfigRequest.Builder builder) {
        j.e(builder, "$this$createSession");
        VexFwkStreamType vexFwkStreamType = VexFwkStreamType.BUFFER;
        VexFwkStreamUsage vexFwkStreamUsage = VexFwkStreamUsage.IMAGE;
        VexFwkSessionConfigRequest.Builder builder2 = builder;
        VexFwkSessionConfigRequest.Builder.addInputStream$default(builder2, 0, vexFwkStreamType, vexFwkStreamUsage, 0, 0, 0, (Surface) null, 120, (Object) null);
        VexFwkSessionConfigRequest.Builder.addOutputStream$default(builder2, 1, vexFwkStreamType, vexFwkStreamUsage, 0, 0, 0, (Surface) null, 120, (Object) null);
        return x.f4917a;
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    public final CompletableFuture<Bitmap> applyImageEnhancer(Bitmap bitmap, EnhanceType enhanceType) {
        j.e(bitmap, "inputBitmap");
        j.e(enhanceType, "enhanceType");
        CompletableFuture<Bitmap> supplyAsync = CompletableFuture.supplyAsync(new C0544a(bitmap, enhanceType, this, 7));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    public final VexFwkImageEnhancer configure() {
        return (VexFwkImageEnhancer) configureWith(this, new b(19));
    }
}
