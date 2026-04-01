package com.samsung.android.vexfwk.sdk.objeraser;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.view.Surface;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import vd.d;
import yd.C1356c;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0000J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkObjectExtractor;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "configure", "extractObject", "Ljava/util/concurrent/CompletableFuture;", "", "bitmap", "Landroid/graphics/Bitmap;", "touchCoordinateX", "", "touchCoordinateY", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkObjectExtractor extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_IMAGE = 0;
    private static final int STREAM_ID_OUTPUT_MASK = 1;
    private static final String TAG = "VexFwkObjectExtractor";
    /* access modifiers changed from: private */
    public static final boolean isAvailable = VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.OBJECT_EXTRACTION);

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000b\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkObjectExtractor$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "STREAM_ID_INPUT_IMAGE", "", "STREAM_ID_OUTPUT_MASK", "isAvailable", "", "isAvailable$annotations", "()Z", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isAvailable() {
            return VexFwkObjectExtractor.isAvailable;
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1(VexFwkObjectExtractor vexFwkObjectExtractor) {
        j.e(vexFwkObjectExtractor, "$this$configureWith");
        vexFwkObjectExtractor.createSession(VexFwkUsecase.OBJECT_EXTRACTION, new d(13));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1$lambda$0(VexFwkSessionConfigRequest.Builder builder) {
        j.e(builder, "$this$createSession");
        VexFwkStreamType vexFwkStreamType = VexFwkStreamType.BUFFER;
        VexFwkSessionConfigRequest.Builder builder2 = builder;
        VexFwkSessionConfigRequest.Builder.addInputStream$default(builder2, 0, vexFwkStreamType, VexFwkStreamUsage.IMAGE, 0, 0, 0, (Surface) null, 120, (Object) null);
        VexFwkSessionConfigRequest.Builder.addOutputStream$default(builder2, 1, vexFwkStreamType, VexFwkStreamUsage.MASK, 0, 0, 0, (Surface) null, 120, (Object) null);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0063, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0064, code lost:
        He.F.u(r5, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0067, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006a, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006b, code lost:
        He.F.u(r6, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006e, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int[] extractObject$lambda$7(float r5, float r6, android.graphics.Bitmap r7, com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjectExtractor r8) {
        /*
            java.lang.String r0 = TAG
            java.lang.String r1 = "extractObject E"
            android.util.Log.d(r0, r1)
            android.graphics.PointF r1 = new android.graphics.PointF
            r1.<init>(r5, r6)
            com.samsung.android.vexfwk.bitmap.VexFwkBitmapHardware r5 = new com.samsung.android.vexfwk.bitmap.VexFwkBitmapHardware
            int r6 = r7.getWidth()
            int r2 = r7.getHeight()
            r3 = 1
            r4 = 0
            r5.<init>(r6, r2, r3, r4)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r6 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r6.<init>()
            r2 = 0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r6 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addInputBuffer(r6, (int) r2, (android.graphics.Bitmap) r7)
            android.graphics.Bitmap r7 = r5.getBitmap()
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r6 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addOutputBuffer(r6, (int) r3, (android.graphics.Bitmap) r7)
            Ad.f r7 = new Ad.f
            r2 = 23
            r7.<init>((int) r2, (java.lang.Object) r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r6 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.setSettingMetadata(r6, r7)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r6 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r6
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r6 = r6.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r7 = com.samsung.android.vexfwk.session.VexFwkUsecase.OBJECT_EXTRACTION     // Catch:{ all -> 0x0068 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r7 = r8.getSession(r7)     // Catch:{ all -> 0x0068 }
            java.lang.Object r7 = r7.m60processRequestIoAF18A(r6)     // Catch:{ all -> 0x0068 }
            He.F.u(r6, r4)
            boolean r6 = r7 instanceof me.j
            if (r6 != 0) goto L_0x0054
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r7 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r7
            r7.close()
        L_0x0054:
            int[] r6 = r5.toIntArray()     // Catch:{ all -> 0x0061 }
            He.F.u(r5, r4)
            java.lang.String r5 = "extractObject X"
            android.util.Log.d(r0, r5)
            return r6
        L_0x0061:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0063 }
        L_0x0063:
            r7 = move-exception
            He.F.u(r5, r6)
            throw r7
        L_0x0068:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x006a }
        L_0x006a:
            r7 = move-exception
            He.F.u(r6, r5)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjectExtractor.extractObject$lambda$7(float, float, android.graphics.Bitmap, com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjectExtractor):int[]");
    }

    /* access modifiers changed from: private */
    public static final x extractObject$lambda$7$lambda$2(PointF pointF, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.AILASSO_TOUCH_COORDINATE.INSTANCE, pointF);
        return x.f4917a;
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    public final VexFwkObjectExtractor configure() {
        return (VexFwkObjectExtractor) configureWith(this, new d(14));
    }

    public final CompletableFuture<int[]> extractObject(Bitmap bitmap, float f, float f5) {
        j.e(bitmap, "bitmap");
        CompletableFuture<int[]> supplyAsync = CompletableFuture.supplyAsync(new C1356c(f, f5, bitmap, this));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }
}
