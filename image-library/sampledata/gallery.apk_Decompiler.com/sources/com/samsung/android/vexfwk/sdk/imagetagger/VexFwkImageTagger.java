package com.samsung.android.vexfwk.sdk.imagetagger;

import android.graphics.Bitmap;
import android.view.Surface;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkImageTaggerResult;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.docscan.b;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0000J+\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u0002H\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000e\u001a\u00020\u000f¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetagger/VexFwkImageTagger;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "configure", "detectImageTagsImpl", "Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/param/VexFwkImageTaggerResult;", "T", "buffer", "angularRotation", "", "(Ljava/lang/Object;I)Ljava/util/concurrent/CompletableFuture;", "detectImageTags", "image", "Landroid/graphics/Bitmap;", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageTagger extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_IMAGE = 0;
    private static final String TAG = "VexFwkImageTagger";
    /* access modifiers changed from: private */
    public static final boolean isAvailable = VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.IMAGE_TAGGER);

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u00020\u000b8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\u0003\u001a\u0004\b\n\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetagger/VexFwkImageTagger$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "STREAM_ID_IMAGE", "", "isAvailable", "", "isAvailable$annotations", "()Z", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isAvailable() {
            return VexFwkImageTagger.isAvailable;
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1(VexFwkImageTagger vexFwkImageTagger) {
        j.e(vexFwkImageTagger, "$this$configureWith");
        vexFwkImageTagger.createSession(VexFwkUsecase.IMAGE_TAGGER, new b(24));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1$lambda$0(VexFwkSessionConfigRequest.Builder builder) {
        j.e(builder, "$this$createSession");
        VexFwkSessionConfigRequest.Builder.addInputStream$default(builder, 0, VexFwkStreamType.BUFFER, VexFwkStreamUsage.IMAGE, 0, 0, 0, (Surface) null, 120, (Object) null);
        return x.f4917a;
    }

    private final <T> CompletableFuture<VexFwkImageTaggerResult> detectImageTagsImpl(T t, int i2) {
        CompletableFuture<VexFwkImageTaggerResult> supplyAsync = CompletableFuture.supplyAsync(new com.samsung.android.sum.core.controller.e(t, i2, this, 1));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    public static /* synthetic */ CompletableFuture detectImageTagsImpl$default(VexFwkImageTagger vexFwkImageTagger, Object obj, int i2, int i7, Object obj2) {
        if ((i7 & 2) != 0) {
            i2 = 0;
        }
        return vexFwkImageTagger.detectImageTagsImpl(obj, i2);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006d, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006e, code lost:
        He.F.u(r5, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0071, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007a, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007b, code lost:
        He.F.u(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007e, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.samsung.android.vexfwk.param.VexFwkImageTaggerResult detectImageTagsImpl$lambda$10(java.lang.Object r4, int r5, com.samsung.android.vexfwk.sdk.imagetagger.VexFwkImageTagger r6) {
        /*
            java.lang.String r0 = TAG
            java.lang.String r1 = "detectImageTagsImpl E"
            android.util.Log.d(r0, r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r1 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r1.<init>()
            r2 = 0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r4 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addInputBuffer(r1, (int) r2, r4)
            ud.a r1 = new ud.a
            r1.<init>(r5)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r4 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.setSettingMetadata(r4, r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r4 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r4
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r4 = r4.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r5 = com.samsung.android.vexfwk.session.VexFwkUsecase.IMAGE_TAGGER     // Catch:{ all -> 0x0078 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r5 = r6.getSession(r5)     // Catch:{ all -> 0x0078 }
            java.lang.Object r5 = r5.m60processRequestIoAF18A(r4)     // Catch:{ all -> 0x0078 }
            r6 = 0
            He.F.u(r4, r6)
            java.lang.Throwable r4 = me.k.a(r5)
            if (r4 != 0) goto L_0x0072
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r5 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r5
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r4 = r5.getResultMetadata()     // Catch:{ all -> 0x0062 }
            com.samsung.android.vexfwk.metadata.VexFwkMetadataKey$RESULT_CODE r1 = com.samsung.android.vexfwk.metadata.VexFwkMetadataKey.RESULT_CODE.INSTANCE     // Catch:{ all -> 0x0062 }
            java.lang.Object r4 = r4.get(r1)     // Catch:{ all -> 0x0062 }
            com.samsung.android.vexfwk.param.VexFwkNodeResultCode r4 = (com.samsung.android.vexfwk.param.VexFwkNodeResultCode) r4     // Catch:{ all -> 0x0062 }
            com.samsung.android.vexfwk.param.VexFwkNodeResultCode r1 = com.samsung.android.vexfwk.param.VexFwkNodeResultCode.OK     // Catch:{ all -> 0x0062 }
            if (r4 != r1) goto L_0x0064
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r4 = r5.getResultMetadata()     // Catch:{ all -> 0x0062 }
            com.samsung.android.vexfwk.metadata.VexFwkMetadataKey$IMAGE_TAGGER_RESULT r1 = com.samsung.android.vexfwk.metadata.VexFwkMetadataKey.IMAGE_TAGGER_RESULT.INSTANCE     // Catch:{ all -> 0x0062 }
            e5.d r2 = new e5.d     // Catch:{ all -> 0x0062 }
            r3 = 14
            r2.<init>(r3)     // Catch:{ all -> 0x0062 }
            java.lang.Object r4 = r4.getOrElse(r1, r2)     // Catch:{ all -> 0x0062 }
            com.samsung.android.vexfwk.param.VexFwkImageTaggerResult r4 = (com.samsung.android.vexfwk.param.VexFwkImageTaggerResult) r4     // Catch:{ all -> 0x0062 }
            He.F.u(r5, r6)
            java.lang.String r5 = "detectImageTagsImpl X"
            android.util.Log.d(r0, r5)
            return r4
        L_0x0062:
            r4 = move-exception
            goto L_0x006c
        L_0x0064:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0062 }
            java.lang.String r6 = "Failed to get Image Tagger result"
            r4.<init>(r6)     // Catch:{ all -> 0x0062 }
            throw r4     // Catch:{ all -> 0x0062 }
        L_0x006c:
            throw r4     // Catch:{ all -> 0x006d }
        L_0x006d:
            r6 = move-exception
            He.F.u(r5, r4)
            throw r6
        L_0x0072:
            java.lang.String r5 = "Failed to process request : "
            c0.C0086a.x(r5, r0, r4)
            throw r4
        L_0x0078:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x007a }
        L_0x007a:
            r6 = move-exception
            He.F.u(r4, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.imagetagger.VexFwkImageTagger.detectImageTagsImpl$lambda$10(java.lang.Object, int, com.samsung.android.vexfwk.sdk.imagetagger.VexFwkImageTagger):com.samsung.android.vexfwk.param.VexFwkImageTaggerResult");
    }

    /* access modifiers changed from: private */
    public static final x detectImageTagsImpl$lambda$10$lambda$2(int i2, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.ROTATION_DEGREE.INSTANCE, Integer.valueOf(i2));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final VexFwkImageTaggerResult detectImageTagsImpl$lambda$10$lambda$7$lambda$6$lambda$5$lambda$4() {
        return new VexFwkImageTaggerResult(new ArrayList());
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    public final VexFwkImageTagger configure() {
        return (VexFwkImageTagger) configureWith(this, new b(25));
    }

    public final CompletableFuture<VexFwkImageTaggerResult> detectImageTags(Bitmap bitmap) {
        j.e(bitmap, "image");
        return detectImageTagsImpl$default(this, bitmap, 0, 2, (Object) null);
    }
}
