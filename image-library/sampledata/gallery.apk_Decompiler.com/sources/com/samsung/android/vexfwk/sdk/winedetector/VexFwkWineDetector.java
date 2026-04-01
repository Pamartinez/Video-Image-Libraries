package com.samsung.android.vexfwk.sdk.winedetector;

import Bd.C0725a;
import Bd.C0726b;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.Surface;
import com.samsung.android.vexfwk.param.VexFwkWineInfo;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0000J!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u0002H\bH\u0002¢\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\f\u001a\u00020\rJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\f\u001a\u00020\u000e¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/winedetector/VexFwkWineDetector;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "configure", "detectWineImpl", "Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/param/VexFwkWineInfo;", "T", "buffer", "(Ljava/lang/Object;)Ljava/util/concurrent/CompletableFuture;", "detectWine", "image", "Landroid/graphics/Bitmap;", "Landroid/media/Image;", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkWineDetector extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_IMAGE = 0;
    private static final String TAG = "VexFwkWineDetector";
    /* access modifiers changed from: private */
    public static final boolean isAvailable = VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.WINE_DETECTION);

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u00020\u000b8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\u0003\u001a\u0004\b\n\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/winedetector/VexFwkWineDetector$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "STREAM_ID_IMAGE", "", "isAvailable", "", "isAvailable$annotations", "()Z", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isAvailable() {
            return VexFwkWineDetector.isAvailable;
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1(VexFwkWineDetector vexFwkWineDetector) {
        j.e(vexFwkWineDetector, "$this$configureWith");
        vexFwkWineDetector.createSession(VexFwkUsecase.WINE_DETECTION, new C0725a(0));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1$lambda$0(VexFwkSessionConfigRequest.Builder builder) {
        j.e(builder, "$this$createSession");
        VexFwkSessionConfigRequest.Builder.addInputStream$default(builder, 0, VexFwkStreamType.BUFFER, VexFwkStreamUsage.IMAGE, 0, 0, 0, (Surface) null, 120, (Object) null);
        return x.f4917a;
    }

    private final <T> CompletableFuture<VexFwkWineInfo> detectWineImpl(T t) {
        CompletableFuture<VexFwkWineInfo> supplyAsync = CompletableFuture.supplyAsync(new C0726b(0, t, this));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004b, code lost:
        He.F.u(r6, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0057, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0058, code lost:
        He.F.u(r5, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005b, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.samsung.android.vexfwk.param.VexFwkWineInfo detectWineImpl$lambda$8(java.lang.Object r5, com.samsung.android.vexfwk.sdk.winedetector.VexFwkWineDetector r6) {
        /*
            java.lang.String r0 = TAG
            java.lang.String r1 = "detectWineImpl E"
            android.util.Log.d(r0, r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r1 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r1.<init>()
            r2 = 0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r5 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addInputBuffer(r1, (int) r2, r5)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r5 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r5
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r5 = r5.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r1 = com.samsung.android.vexfwk.session.VexFwkUsecase.WINE_DETECTION     // Catch:{ all -> 0x0055 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r6 = r6.getSession(r1)     // Catch:{ all -> 0x0055 }
            java.lang.Object r6 = r6.m60processRequestIoAF18A(r5)     // Catch:{ all -> 0x0055 }
            r1 = 0
            He.F.u(r5, r1)
            java.lang.Throwable r5 = me.k.a(r6)
            if (r5 != 0) goto L_0x004f
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r6 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r6
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r5 = r6.getResultMetadata()     // Catch:{ all -> 0x0048 }
            com.samsung.android.vexfwk.metadata.VexFwkMetadataKey$WINE_INFO r2 = com.samsung.android.vexfwk.metadata.VexFwkMetadataKey.WINE_INFO.INSTANCE     // Catch:{ all -> 0x0048 }
            Ad.j r3 = new Ad.j     // Catch:{ all -> 0x0048 }
            r4 = 2
            r3.<init>(r4)     // Catch:{ all -> 0x0048 }
            java.lang.Object r5 = r5.getOrElse(r2, r3)     // Catch:{ all -> 0x0048 }
            com.samsung.android.vexfwk.param.VexFwkWineInfo r5 = (com.samsung.android.vexfwk.param.VexFwkWineInfo) r5     // Catch:{ all -> 0x0048 }
            He.F.u(r6, r1)
            java.lang.String r6 = "detectWineImpl X"
            android.util.Log.d(r0, r6)
            return r5
        L_0x0048:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x004a }
        L_0x004a:
            r0 = move-exception
            He.F.u(r6, r5)
            throw r0
        L_0x004f:
            java.lang.String r6 = "Failed to process request : "
            c0.C0086a.x(r6, r0, r5)
            throw r5
        L_0x0055:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0057 }
        L_0x0057:
            r0 = move-exception
            He.F.u(r5, r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.winedetector.VexFwkWineDetector.detectWineImpl$lambda$8(java.lang.Object, com.samsung.android.vexfwk.sdk.winedetector.VexFwkWineDetector):com.samsung.android.vexfwk.param.VexFwkWineInfo");
    }

    /* access modifiers changed from: private */
    public static final VexFwkWineInfo detectWineImpl$lambda$8$lambda$5$lambda$4$lambda$3() {
        return new VexFwkWineInfo(new LinkedList());
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    public final VexFwkWineDetector configure() {
        return (VexFwkWineDetector) configureWith(this, new C0725a(1));
    }

    public final CompletableFuture<VexFwkWineInfo> detectWine(Bitmap bitmap) {
        j.e(bitmap, "image");
        return detectWineImpl(bitmap);
    }

    public final CompletableFuture<VexFwkWineInfo> detectWine(Image image) {
        j.e(image, "image");
        return detectWineImpl(image);
    }
}
