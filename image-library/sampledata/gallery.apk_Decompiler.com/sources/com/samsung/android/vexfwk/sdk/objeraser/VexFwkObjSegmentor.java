package com.samsung.android.vexfwk.sdk.objeraser;

import Bd.C0726b;
import android.graphics.Bitmap;
import android.view.Surface;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import vd.d;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \n2\u00020\u0001:\u0002\n\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0000J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkObjSegmentor;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "configure", "segmentObject", "Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkObjSegmentor$InstanceSegmentResult;", "bitmap", "Landroid/graphics/Bitmap;", "Companion", "InstanceSegmentResult", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkObjSegmentor extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_IMAGE = 0;
    private static final int STREAM_ID_OUTPUT_MASK = 1;
    private static final String TAG = "VexFwkObjSegmentor";
    /* access modifiers changed from: private */
    public static final boolean isAvailable = VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.OBJECT_SEGMENTATION);

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000b\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkObjSegmentor$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "STREAM_ID_INPUT_IMAGE", "", "STREAM_ID_OUTPUT_MASK", "isAvailable", "", "isAvailable$annotations", "()Z", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isAvailable() {
            return VexFwkObjSegmentor.isAvailable;
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005HÆ\u0003J-\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR(\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001a"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/objeraser/VexFwkObjSegmentor$InstanceSegmentResult;", "", "segmentationMask", "", "categoryData", "", "", "", "<init>", "([ILjava/util/Map;)V", "getSegmentationMask", "()[I", "setSegmentationMask", "([I)V", "getCategoryData", "()Ljava/util/Map;", "setCategoryData", "(Ljava/util/Map;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class InstanceSegmentResult {
        private Map<Integer, String> categoryData;
        private int[] segmentationMask;

        public InstanceSegmentResult() {
            this((int[]) null, (Map) null, 3, (e) null);
        }

        public static /* synthetic */ InstanceSegmentResult copy$default(InstanceSegmentResult instanceSegmentResult, int[] iArr, Map<Integer, String> map, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                iArr = instanceSegmentResult.segmentationMask;
            }
            if ((i2 & 2) != 0) {
                map = instanceSegmentResult.categoryData;
            }
            return instanceSegmentResult.copy(iArr, map);
        }

        public final int[] component1() {
            return this.segmentationMask;
        }

        public final Map<Integer, String> component2() {
            return this.categoryData;
        }

        public final InstanceSegmentResult copy(int[] iArr, Map<Integer, String> map) {
            return new InstanceSegmentResult(iArr, map);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof InstanceSegmentResult)) {
                return false;
            }
            InstanceSegmentResult instanceSegmentResult = (InstanceSegmentResult) obj;
            if (j.a(this.segmentationMask, instanceSegmentResult.segmentationMask) && j.a(this.categoryData, instanceSegmentResult.categoryData)) {
                return true;
            }
            return false;
        }

        public final Map<Integer, String> getCategoryData() {
            return this.categoryData;
        }

        public final int[] getSegmentationMask() {
            return this.segmentationMask;
        }

        public int hashCode() {
            int i2;
            int[] iArr = this.segmentationMask;
            int i7 = 0;
            if (iArr == null) {
                i2 = 0;
            } else {
                i2 = Arrays.hashCode(iArr);
            }
            int i8 = i2 * 31;
            Map<Integer, String> map = this.categoryData;
            if (map != null) {
                i7 = map.hashCode();
            }
            return i8 + i7;
        }

        public final void setCategoryData(Map<Integer, String> map) {
            this.categoryData = map;
        }

        public final void setSegmentationMask(int[] iArr) {
            this.segmentationMask = iArr;
        }

        public String toString() {
            String arrays = Arrays.toString(this.segmentationMask);
            Map<Integer, String> map = this.categoryData;
            return "InstanceSegmentResult(segmentationMask=" + arrays + ", categoryData=" + map + ")";
        }

        public InstanceSegmentResult(int[] iArr, Map<Integer, String> map) {
            this.segmentationMask = iArr;
            this.categoryData = map;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ InstanceSegmentResult(int[] iArr, Map map, int i2, e eVar) {
            this((i2 & 1) != 0 ? null : iArr, (i2 & 2) != 0 ? null : map);
        }
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1(VexFwkObjSegmentor vexFwkObjSegmentor) {
        j.e(vexFwkObjSegmentor, "$this$configureWith");
        vexFwkObjSegmentor.createSession(VexFwkUsecase.OBJECT_SEGMENTATION, new d(12));
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

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x006e, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006f, code lost:
        He.F.u(r2, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0072, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x008f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0090, code lost:
        He.F.u(r7, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0093, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjSegmentor.InstanceSegmentResult segmentObject$lambda$6(android.graphics.Bitmap r7, com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjSegmentor r8) {
        /*
            com.samsung.android.vexfwk.log.VexFwkLog$Companion r0 = com.samsung.android.vexfwk.log.VexFwkLog.Companion
            java.lang.String r1 = TAG
            java.lang.String r2 = "segmentObject E"
            r0.d(r1, r2)
            com.samsung.android.vexfwk.bitmap.VexFwkBitmapHardware r2 = new com.samsung.android.vexfwk.bitmap.VexFwkBitmapHardware
            int r3 = r7.getWidth()
            int r4 = r7.getHeight()
            r5 = 1
            r6 = 0
            r2.<init>(r3, r4, r5, r6)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r3 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r3.<init>()
            r4 = 0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r7 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addInputBuffer(r3, (int) r4, (android.graphics.Bitmap) r7)
            android.graphics.Bitmap r3 = r2.getBitmap()
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r7 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addOutputBuffer(r7, (int) r5, (android.graphics.Bitmap) r3)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r7 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r7
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r7 = r7.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r3 = com.samsung.android.vexfwk.session.VexFwkUsecase.OBJECT_SEGMENTATION     // Catch:{ all -> 0x008d }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r8 = r8.getSession(r3)     // Catch:{ all -> 0x008d }
            java.lang.Object r8 = r8.m60processRequestIoAF18A(r7)     // Catch:{ all -> 0x008d }
            He.F.u(r7, r6)
            java.lang.Throwable r7 = me.k.a(r8)
            if (r7 != 0) goto L_0x0073
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r8 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r8
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r7 = r8.getResultMetadata()
            com.samsung.android.vexfwk.metadata.VexFwkMetadataKey$SEGMENT_CATEGORY_MAP r3 = com.samsung.android.vexfwk.metadata.VexFwkMetadataKey.SEGMENT_CATEGORY_MAP.INSTANCE
            java.lang.Object r7 = r7.get(r3)
            kotlin.jvm.internal.j.b(r7)
            r8.close()
            java.lang.String r8 = "segmentObject X"
            r0.d(r1, r8)
            int[] r8 = r2.toIntArray()     // Catch:{ all -> 0x006c }
            He.F.u(r2, r6)
            r2.close()
            com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjSegmentor$InstanceSegmentResult r0 = new com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjSegmentor$InstanceSegmentResult
            java.util.Map r7 = (java.util.Map) r7
            r0.<init>(r8, r7)
            return r0
        L_0x006c:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x006e }
        L_0x006e:
            r8 = move-exception
            He.F.u(r2, r7)
            throw r8
        L_0x0073:
            r2.close()
            java.lang.String r8 = "TAG"
            kotlin.jvm.internal.j.d(r1, r8)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r2 = "Failed to process request : "
            r8.<init>(r2)
            r8.append(r7)
            java.lang.String r8 = r8.toString()
            r0.e(r1, r8)
            throw r7
        L_0x008d:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x008f }
        L_0x008f:
            r0 = move-exception
            He.F.u(r7, r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjSegmentor.segmentObject$lambda$6(android.graphics.Bitmap, com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjSegmentor):com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjSegmentor$InstanceSegmentResult");
    }

    public final VexFwkObjSegmentor configure() {
        return (VexFwkObjSegmentor) configureWith(this, new d(11));
    }

    public final CompletableFuture<InstanceSegmentResult> segmentObject(Bitmap bitmap) {
        j.e(bitmap, "bitmap");
        CompletableFuture<InstanceSegmentResult> supplyAsync = CompletableFuture.supplyAsync(new C0726b(16, bitmap, this));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }
}
