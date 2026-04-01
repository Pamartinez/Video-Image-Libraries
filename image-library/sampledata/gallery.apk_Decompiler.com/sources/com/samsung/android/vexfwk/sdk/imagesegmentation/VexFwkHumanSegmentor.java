package com.samsung.android.vexfwk.sdk.imagesegmentation;

import Bd.C0726b;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Size;
import android.view.Surface;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.vexfwk.param.VexFwkNodeResultCode;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.docscan.b;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000f\u0010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0000J!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u0002H\bH\u0002¢\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\f\u001a\u00020\rJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\f\u001a\u00020\u000e¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagesegmentation/VexFwkHumanSegmentor;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "configure", "doSegmentationImpl", "Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/sdk/imagesegmentation/VexFwkHumanSegmentor$Result;", "T", "buffer", "(Ljava/lang/Object;)Ljava/util/concurrent/CompletableFuture;", "doSegmentation", "image", "Landroid/media/Image;", "Landroid/graphics/Bitmap;", "Companion", "Result", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkHumanSegmentor extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_IMAGE = 0;
    private static final int STREAM_ID_OUTPUT_MASK = 1;
    private static final String TAG = "VexFwkHumanSegmentor";
    /* access modifiers changed from: private */
    public static final boolean isAvailable = VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.HUMAN_SEGMENTATION);

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000b\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagesegmentation/VexFwkHumanSegmentor$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "STREAM_ID_INPUT_IMAGE", "", "STREAM_ID_OUTPUT_MASK", "isAvailable", "", "isAvailable$annotations", "()Z", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isAvailable() {
            return VexFwkHumanSegmentor.isAvailable;
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagesegmentation/VexFwkHumanSegmentor$Result;", "", "<init>", "()V", "Success", "Error", "Lcom/samsung/android/vexfwk/sdk/imagesegmentation/VexFwkHumanSegmentor$Result$Error;", "Lcom/samsung/android/vexfwk/sdk/imagesegmentation/VexFwkHumanSegmentor$Result$Success;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Result {

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagesegmentation/VexFwkHumanSegmentor$Result$Error;", "Lcom/samsung/android/vexfwk/sdk/imagesegmentation/VexFwkHumanSegmentor$Result;", "resultCode", "Lcom/samsung/android/vexfwk/param/VexFwkNodeResultCode;", "<init>", "(Lcom/samsung/android/vexfwk/param/VexFwkNodeResultCode;)V", "getResultCode", "()Lcom/samsung/android/vexfwk/param/VexFwkNodeResultCode;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Error extends Result {
            private final VexFwkNodeResultCode resultCode;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Error(VexFwkNodeResultCode vexFwkNodeResultCode) {
                super((e) null);
                j.e(vexFwkNodeResultCode, OCRServiceConstant.KEY_RESULT_CODE);
                this.resultCode = vexFwkNodeResultCode;
            }

            public static /* synthetic */ Error copy$default(Error error, VexFwkNodeResultCode vexFwkNodeResultCode, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    vexFwkNodeResultCode = error.resultCode;
                }
                return error.copy(vexFwkNodeResultCode);
            }

            public final VexFwkNodeResultCode component1() {
                return this.resultCode;
            }

            public final Error copy(VexFwkNodeResultCode vexFwkNodeResultCode) {
                j.e(vexFwkNodeResultCode, OCRServiceConstant.KEY_RESULT_CODE);
                return new Error(vexFwkNodeResultCode);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if ((obj instanceof Error) && this.resultCode == ((Error) obj).resultCode) {
                    return true;
                }
                return false;
            }

            public final VexFwkNodeResultCode getResultCode() {
                return this.resultCode;
            }

            public int hashCode() {
                return this.resultCode.hashCode();
            }

            public String toString() {
                VexFwkNodeResultCode vexFwkNodeResultCode = this.resultCode;
                return "Error(resultCode=" + vexFwkNodeResultCode + ")";
            }
        }

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagesegmentation/VexFwkHumanSegmentor$Result$Success;", "Lcom/samsung/android/vexfwk/sdk/imagesegmentation/VexFwkHumanSegmentor$Result;", "segmentationMask", "", "size", "Landroid/util/Size;", "<init>", "([ILandroid/util/Size;)V", "getSegmentationMask", "()[I", "setSegmentationMask", "([I)V", "getSize", "()Landroid/util/Size;", "setSize", "(Landroid/util/Size;)V", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Success extends Result {
            private int[] segmentationMask;
            private Size size;

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Success(int[] iArr, Size size2, int i2, e eVar) {
                this((i2 & 1) != 0 ? null : iArr, size2);
            }

            public static /* synthetic */ Success copy$default(Success success, int[] iArr, Size size2, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    iArr = success.segmentationMask;
                }
                if ((i2 & 2) != 0) {
                    size2 = success.size;
                }
                return success.copy(iArr, size2);
            }

            public final int[] component1() {
                return this.segmentationMask;
            }

            public final Size component2() {
                return this.size;
            }

            public final Success copy(int[] iArr, Size size2) {
                j.e(size2, "size");
                return new Success(iArr, size2);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Success)) {
                    return false;
                }
                Success success = (Success) obj;
                if (j.a(this.segmentationMask, success.segmentationMask) && j.a(this.size, success.size)) {
                    return true;
                }
                return false;
            }

            public final int[] getSegmentationMask() {
                return this.segmentationMask;
            }

            public final Size getSize() {
                return this.size;
            }

            public int hashCode() {
                int i2;
                int[] iArr = this.segmentationMask;
                if (iArr == null) {
                    i2 = 0;
                } else {
                    i2 = Arrays.hashCode(iArr);
                }
                return this.size.hashCode() + (i2 * 31);
            }

            public final void setSegmentationMask(int[] iArr) {
                this.segmentationMask = iArr;
            }

            public final void setSize(Size size2) {
                j.e(size2, "<set-?>");
                this.size = size2;
            }

            public String toString() {
                String arrays = Arrays.toString(this.segmentationMask);
                Size size2 = this.size;
                return "Success(segmentationMask=" + arrays + ", size=" + size2 + ")";
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Success(int[] iArr, Size size2) {
                super((e) null);
                j.e(size2, "size");
                this.segmentationMask = iArr;
                this.size = size2;
            }
        }

        public /* synthetic */ Result(e eVar) {
            this();
        }

        private Result() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VexFwkNodeResultCode.values().length];
            try {
                iArr[VexFwkNodeResultCode.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1(VexFwkHumanSegmentor vexFwkHumanSegmentor) {
        j.e(vexFwkHumanSegmentor, "$this$configureWith");
        vexFwkHumanSegmentor.createSession(VexFwkUsecase.HUMAN_SEGMENTATION, new b(22));
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

    private final <T> CompletableFuture<Result> doSegmentationImpl(T t) {
        CompletableFuture<Result> supplyAsync = CompletableFuture.supplyAsync(new C0726b(14, t, this));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00ee, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ef, code lost:
        He.F.u(r5, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00f2, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.samsung.android.vexfwk.sdk.imagesegmentation.VexFwkHumanSegmentor.Result doSegmentationImpl$lambda$9(java.lang.Object r5, com.samsung.android.vexfwk.sdk.imagesegmentation.VexFwkHumanSegmentor r6) {
        /*
            com.samsung.android.vexfwk.log.VexFwkLog$Companion r0 = com.samsung.android.vexfwk.log.VexFwkLog.Companion
            java.lang.String r1 = TAG
            java.lang.String r2 = "doSegmentationImpl E"
            r0.d(r1, r2)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r2 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r2.<init>()
            r3 = 0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r5 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addInputBuffer(r2, (int) r3, r5)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r5 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r5
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r5 = r5.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r2 = com.samsung.android.vexfwk.session.VexFwkUsecase.HUMAN_SEGMENTATION     // Catch:{ all -> 0x00ec }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r6 = r6.getSession(r2)     // Catch:{ all -> 0x00ec }
            java.lang.Object r6 = r6.m60processRequestIoAF18A(r5)     // Catch:{ all -> 0x00ec }
            r2 = 0
            He.F.u(r5, r2)
            java.lang.Throwable r5 = me.k.a(r6)
            if (r5 != 0) goto L_0x00d5
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r6 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r6
            java.lang.String r5 = "doSegmentation success"
            android.util.Log.d(r1, r5)
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r5 = r6.getResultMetadata()     // Catch:{ all -> 0x0073 }
            com.samsung.android.vexfwk.metadata.VexFwkMetadataKey$RESULT_CODE r0 = com.samsung.android.vexfwk.metadata.VexFwkMetadataKey.RESULT_CODE.INSTANCE     // Catch:{ all -> 0x0073 }
            java.lang.Object r5 = r5.get(r0)     // Catch:{ all -> 0x0073 }
            kotlin.jvm.internal.j.b(r5)     // Catch:{ all -> 0x0073 }
            com.samsung.android.vexfwk.param.VexFwkNodeResultCode r5 = (com.samsung.android.vexfwk.param.VexFwkNodeResultCode) r5     // Catch:{ all -> 0x0073 }
            int[] r0 = com.samsung.android.vexfwk.sdk.imagesegmentation.VexFwkHumanSegmentor.WhenMappings.$EnumSwitchMapping$0     // Catch:{ all -> 0x0073 }
            int r5 = r5.ordinal()     // Catch:{ all -> 0x0073 }
            r5 = r0[r5]     // Catch:{ all -> 0x0073 }
            r0 = 1
            if (r5 != r0) goto L_0x00bb
            java.util.List r5 = r6.getPartialResults()     // Catch:{ all -> 0x0073 }
            java.lang.Iterable r5 = (java.lang.Iterable) r5     // Catch:{ all -> 0x0073 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0073 }
            r1.<init>()     // Catch:{ all -> 0x0073 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0073 }
        L_0x005d:
            boolean r3 = r5.hasNext()     // Catch:{ all -> 0x0073 }
            if (r3 == 0) goto L_0x0075
            java.lang.Object r3 = r5.next()     // Catch:{ all -> 0x0073 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionPartialResult r3 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionPartialResult) r3     // Catch:{ all -> 0x0073 }
            java.util.List r3 = r3.getOutputBuffers()     // Catch:{ all -> 0x0073 }
            java.lang.Iterable r3 = (java.lang.Iterable) r3     // Catch:{ all -> 0x0073 }
            ne.C1200r.A0(r3, r1)     // Catch:{ all -> 0x0073 }
            goto L_0x005d
        L_0x0073:
            r5 = move-exception
            goto L_0x00cf
        L_0x0075:
            java.util.Iterator r5 = r1.iterator()     // Catch:{ all -> 0x0073 }
        L_0x0079:
            boolean r1 = r5.hasNext()     // Catch:{ all -> 0x0073 }
            if (r1 == 0) goto L_0x00b3
            java.lang.Object r1 = r5.next()     // Catch:{ all -> 0x0073 }
            r3 = r1
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer r3 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer) r3     // Catch:{ all -> 0x0073 }
            int r3 = r3.getStreamId()     // Catch:{ all -> 0x0073 }
            if (r3 != r0) goto L_0x0079
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer r1 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer) r1     // Catch:{ all -> 0x0073 }
            com.samsung.android.vexfwk.hardware.VexFwkHardwareBufferNative$Companion r5 = com.samsung.android.vexfwk.hardware.VexFwkHardwareBufferNative.Companion     // Catch:{ all -> 0x0073 }
            android.hardware.HardwareBuffer r0 = r1.getHardwareBuffer()     // Catch:{ all -> 0x0073 }
            int[] r5 = r5.convertToArgbIntArray(r0)     // Catch:{ all -> 0x0073 }
            com.samsung.android.vexfwk.sdk.imagesegmentation.VexFwkHumanSegmentor$Result$Success r0 = new com.samsung.android.vexfwk.sdk.imagesegmentation.VexFwkHumanSegmentor$Result$Success     // Catch:{ all -> 0x0073 }
            android.util.Size r3 = new android.util.Size     // Catch:{ all -> 0x0073 }
            android.hardware.HardwareBuffer r4 = r1.getHardwareBuffer()     // Catch:{ all -> 0x0073 }
            int r4 = r4.getWidth()     // Catch:{ all -> 0x0073 }
            android.hardware.HardwareBuffer r1 = r1.getHardwareBuffer()     // Catch:{ all -> 0x0073 }
            int r1 = r1.getHeight()     // Catch:{ all -> 0x0073 }
            r3.<init>(r4, r1)     // Catch:{ all -> 0x0073 }
            r0.<init>(r5, r3)     // Catch:{ all -> 0x0073 }
            goto L_0x00c2
        L_0x00b3:
            java.util.NoSuchElementException r5 = new java.util.NoSuchElementException     // Catch:{ all -> 0x0073 }
            java.lang.String r0 = "Collection contains no element matching the predicate."
            r5.<init>(r0)     // Catch:{ all -> 0x0073 }
            throw r5     // Catch:{ all -> 0x0073 }
        L_0x00bb:
            com.samsung.android.vexfwk.sdk.imagesegmentation.VexFwkHumanSegmentor$Result$Error r0 = new com.samsung.android.vexfwk.sdk.imagesegmentation.VexFwkHumanSegmentor$Result$Error     // Catch:{ all -> 0x0073 }
            com.samsung.android.vexfwk.param.VexFwkNodeResultCode r5 = com.samsung.android.vexfwk.param.VexFwkNodeResultCode.PROCESS_FAIL     // Catch:{ all -> 0x0073 }
            r0.<init>(r5)     // Catch:{ all -> 0x0073 }
        L_0x00c2:
            He.F.u(r6, r2)
            com.samsung.android.vexfwk.log.VexFwkLog$Companion r5 = com.samsung.android.vexfwk.log.VexFwkLog.Companion
            java.lang.String r6 = TAG
            java.lang.String r1 = "doSegmentationImpl X"
            r5.d(r6, r1)
            return r0
        L_0x00cf:
            throw r5     // Catch:{ all -> 0x00d0 }
        L_0x00d0:
            r0 = move-exception
            He.F.u(r6, r5)
            throw r0
        L_0x00d5:
            java.lang.String r6 = "TAG"
            kotlin.jvm.internal.j.d(r1, r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r2 = "Failed to process request : "
            r6.<init>(r2)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            r0.e(r1, r6)
            throw r5
        L_0x00ec:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x00ee }
        L_0x00ee:
            r0 = move-exception
            He.F.u(r5, r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.imagesegmentation.VexFwkHumanSegmentor.doSegmentationImpl$lambda$9(java.lang.Object, com.samsung.android.vexfwk.sdk.imagesegmentation.VexFwkHumanSegmentor):com.samsung.android.vexfwk.sdk.imagesegmentation.VexFwkHumanSegmentor$Result");
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    public final VexFwkHumanSegmentor configure() {
        return (VexFwkHumanSegmentor) configureWith(this, new b(23));
    }

    public final CompletableFuture<Result> doSegmentation(Image image) {
        j.e(image, "image");
        return doSegmentationImpl(image);
    }

    public final CompletableFuture<Result> doSegmentation(Bitmap bitmap) {
        j.e(bitmap, "image");
        return doSegmentationImpl(bitmap);
    }
}
