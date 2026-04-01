package com.samsung.android.vexfwk.sdk.ocr;

import L1.d;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.view.Surface;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.ocr.VexFwkImageOcrCapabilities;
import com.samsung.android.vexfwk.ocr.VexFwkImageOcrCapabilityType;
import com.samsung.android.vexfwk.ocr.VexFwkImageOcrFuncType;
import com.samsung.android.vexfwk.ocr.VexFwkImageOcrResultCode;
import com.samsung.android.vexfwk.ocr.VexFwkImageOcrResultVersion;
import com.samsung.android.vexfwk.param.VexFwkNodeResultCode;
import com.samsung.android.vexfwk.param.VexFwkOcrAdditionalMeta;
import com.samsung.android.vexfwk.param.VexFwkOcrResultMeta;
import com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2;
import com.samsung.android.vexfwk.param.VexFwkOcrResultV2;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkProvider;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import md.b;
import me.f;
import me.x;
import ne.C1194l;
import zd.C1362a;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0002\u0016\u0017B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0000J!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u0002H\bH\u0002¢\u0006\u0002\u0010\nJ!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u0002H\bH\u0002¢\u0006\u0002\u0010\nJ\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0011\u001a\u00020\u0012J\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0013\u001a\u00020\u0014J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0006\u0010\u0011\u001a\u00020\u0012J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0006\u0010\u0013\u001a\u00020\u0014¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/ocr/VexFwkImageOcr;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "configure", "hasTextImpl", "Ljava/util/concurrent/CompletableFuture;", "", "T", "buffer", "(Ljava/lang/Object;)Ljava/util/concurrent/CompletableFuture;", "recognizeImpl", "Lcom/samsung/android/vexfwk/sdk/ocr/VexFwkImageOcr$Result;", "processResult", "totalResult", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "hasText", "bitmap", "Landroid/graphics/Bitmap;", "image", "Landroid/media/Image;", "recognize", "Companion", "Result", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageOcr extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_IMAGE = 0;
    private static final String TAG = "VexFwkImageOcr";
    /* access modifiers changed from: private */
    public static final f capabilities$delegate = d.q(new b(14));
    /* access modifiers changed from: private */
    public static final boolean isAvailable = VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.IMAGE_OCR);

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u0012\u0004\b\b\u0010\u0003\u001a\u0004\b\u0005\u0010\u0007R'\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\t8FX\u0002¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u0012\u0004\b\u000f\u0010\u0003\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0013\u001a\n \u0012*\u0004\u0018\u00010\u00110\u00118\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002XT¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/ocr/VexFwkImageOcr$Companion;", "", "<init>", "()V", "", "isAvailable", "Z", "()Z", "isAvailable$annotations", "", "Lcom/samsung/android/vexfwk/ocr/VexFwkImageOcrCapabilityType;", "capabilities$delegate", "Lme/f;", "getCapabilities", "()Ljava/util/List;", "getCapabilities$annotations", "capabilities", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "", "STREAM_ID_INPUT_IMAGE", "I", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final List<VexFwkImageOcrCapabilityType> getCapabilities() {
            return (List) VexFwkImageOcr.capabilities$delegate.getValue();
        }

        public final boolean isAvailable() {
            return VexFwkImageOcr.isAvailable;
        }

        private Companion() {
        }

        public static /* synthetic */ void getCapabilities$annotations() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/ocr/VexFwkImageOcr$Result;", "", "<init>", "()V", "SuccessWithData", "Error", "Lcom/samsung/android/vexfwk/sdk/ocr/VexFwkImageOcr$Result$Error;", "Lcom/samsung/android/vexfwk/sdk/ocr/VexFwkImageOcr$Result$SuccessWithData;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Result {

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/ocr/VexFwkImageOcr$Result$Error;", "Lcom/samsung/android/vexfwk/sdk/ocr/VexFwkImageOcr$Result;", "resultCode", "Lcom/samsung/android/vexfwk/ocr/VexFwkImageOcrResultCode;", "<init>", "(Lcom/samsung/android/vexfwk/ocr/VexFwkImageOcrResultCode;)V", "getResultCode", "()Lcom/samsung/android/vexfwk/ocr/VexFwkImageOcrResultCode;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Error extends Result {
            private final VexFwkImageOcrResultCode resultCode;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Error(VexFwkImageOcrResultCode vexFwkImageOcrResultCode) {
                super((e) null);
                j.e(vexFwkImageOcrResultCode, OCRServiceConstant.KEY_RESULT_CODE);
                this.resultCode = vexFwkImageOcrResultCode;
            }

            public static /* synthetic */ Error copy$default(Error error, VexFwkImageOcrResultCode vexFwkImageOcrResultCode, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    vexFwkImageOcrResultCode = error.resultCode;
                }
                return error.copy(vexFwkImageOcrResultCode);
            }

            public final VexFwkImageOcrResultCode component1() {
                return this.resultCode;
            }

            public final Error copy(VexFwkImageOcrResultCode vexFwkImageOcrResultCode) {
                j.e(vexFwkImageOcrResultCode, OCRServiceConstant.KEY_RESULT_CODE);
                return new Error(vexFwkImageOcrResultCode);
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

            public final VexFwkImageOcrResultCode getResultCode() {
                return this.resultCode;
            }

            public int hashCode() {
                return this.resultCode.hashCode();
            }

            public String toString() {
                VexFwkImageOcrResultCode vexFwkImageOcrResultCode = this.resultCode;
                return "Error(resultCode=" + vexFwkImageOcrResultCode + ")";
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/ocr/VexFwkImageOcr$Result$SuccessWithData;", "Lcom/samsung/android/vexfwk/sdk/ocr/VexFwkImageOcr$Result;", "ocrResult", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;", "<init>", "(Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;)V", "getOcrResult", "()Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class SuccessWithData extends Result {
            private final VexFwkOcrResultV2 ocrResult;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SuccessWithData(VexFwkOcrResultV2 vexFwkOcrResultV2) {
                super((e) null);
                j.e(vexFwkOcrResultV2, "ocrResult");
                this.ocrResult = vexFwkOcrResultV2;
            }

            public static /* synthetic */ SuccessWithData copy$default(SuccessWithData successWithData, VexFwkOcrResultV2 vexFwkOcrResultV2, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    vexFwkOcrResultV2 = successWithData.ocrResult;
                }
                return successWithData.copy(vexFwkOcrResultV2);
            }

            public final VexFwkOcrResultV2 component1() {
                return this.ocrResult;
            }

            public final SuccessWithData copy(VexFwkOcrResultV2 vexFwkOcrResultV2) {
                j.e(vexFwkOcrResultV2, "ocrResult");
                return new SuccessWithData(vexFwkOcrResultV2);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if ((obj instanceof SuccessWithData) && j.a(this.ocrResult, ((SuccessWithData) obj).ocrResult)) {
                    return true;
                }
                return false;
            }

            public final VexFwkOcrResultV2 getOcrResult() {
                return this.ocrResult;
            }

            public int hashCode() {
                return this.ocrResult.hashCode();
            }

            public String toString() {
                VexFwkOcrResultV2 vexFwkOcrResultV2 = this.ocrResult;
                return "SuccessWithData(ocrResult=" + vexFwkOcrResultV2 + ")";
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
    public static final VexFwkImageOcrCapabilities capabilities_delegate$lambda$18() {
        VexFwkImageOcrCapabilities vexFwkImageOcrCapabilities = (VexFwkImageOcrCapabilities) VexFwkProvider.fetchProperties(VexFwkUsecase.IMAGE_OCR).getOrElse(VexFwkMetadataKey.PROPERTY_IMAGE_OCR_CAPABILITIES.INSTANCE, new e5.d(21));
        N2.j.w("capabilities : ", C1194l.R0(vexFwkImageOcrCapabilities, (String) null, (String) null, (String) null, (Ae.b) null, 63), TAG);
        return vexFwkImageOcrCapabilities;
    }

    /* access modifiers changed from: private */
    public static final VexFwkImageOcrCapabilities capabilities_delegate$lambda$18$lambda$16$lambda$15() {
        return new VexFwkImageOcrCapabilities(new int[0]);
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1(VexFwkImageOcr vexFwkImageOcr) {
        j.e(vexFwkImageOcr, "$this$configureWith");
        vexFwkImageOcr.createSession(VexFwkUsecase.IMAGE_OCR, new vd.d(19));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1$lambda$0(VexFwkSessionConfigRequest.Builder builder) {
        j.e(builder, "$this$createSession");
        VexFwkSessionConfigRequest.Builder.addInputStream$default(builder, 0, VexFwkStreamType.BUFFER, VexFwkStreamUsage.IMAGE, 0, 0, 0, (Surface) null, 120, (Object) null);
        return x.f4917a;
    }

    public static final List<VexFwkImageOcrCapabilityType> getCapabilities() {
        return Companion.getCapabilities();
    }

    private final <T> CompletableFuture<Boolean> hasTextImpl(T t) {
        CompletableFuture<Boolean> supplyAsync = CompletableFuture.supplyAsync(new C1362a(t, this, 0));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0076, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0077, code lost:
        He.F.u(r5, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007a, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0083, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0084, code lost:
        He.F.u(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0087, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Boolean hasTextImpl$lambda$8(java.lang.Object r4, com.samsung.android.vexfwk.sdk.ocr.VexFwkImageOcr r5) {
        /*
            java.lang.String r0 = TAG
            java.lang.String r1 = "hasTextImpl E"
            android.util.Log.i(r0, r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r1 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r1.<init>()
            r2 = 0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r4 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addInputBuffer(r1, (int) r2, r4)
            vd.d r1 = new vd.d
            r3 = 17
            r1.<init>(r3)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r4 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.setSettingMetadata(r4, r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r4 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r4
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r4 = r4.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r1 = com.samsung.android.vexfwk.session.VexFwkUsecase.IMAGE_OCR     // Catch:{ all -> 0x0081 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r5 = r5.getSession(r1)     // Catch:{ all -> 0x0081 }
            java.lang.Object r5 = r5.m60processRequestIoAF18A(r4)     // Catch:{ all -> 0x0081 }
            r1 = 0
            He.F.u(r4, r1)
            java.lang.Throwable r4 = me.k.a(r5)
            if (r4 != 0) goto L_0x007b
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r5 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r5
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r4 = r5.getResultMetadata()     // Catch:{ all -> 0x0066 }
            com.samsung.android.vexfwk.metadata.VexFwkMetadataKey$RESULT_CODE r3 = com.samsung.android.vexfwk.metadata.VexFwkMetadataKey.RESULT_CODE.INSTANCE     // Catch:{ all -> 0x0066 }
            java.lang.Object r4 = r4.get(r3)     // Catch:{ all -> 0x0066 }
            kotlin.jvm.internal.j.b(r4)     // Catch:{ all -> 0x0066 }
            com.samsung.android.vexfwk.param.VexFwkNodeResultCode r4 = (com.samsung.android.vexfwk.param.VexFwkNodeResultCode) r4     // Catch:{ all -> 0x0066 }
            int[] r3 = com.samsung.android.vexfwk.sdk.ocr.VexFwkImageOcr.WhenMappings.$EnumSwitchMapping$0     // Catch:{ all -> 0x0066 }
            int r4 = r4.ordinal()     // Catch:{ all -> 0x0066 }
            r4 = r3[r4]     // Catch:{ all -> 0x0066 }
            r3 = 1
            if (r4 != r3) goto L_0x0068
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r4 = r5.getResultMetadata()     // Catch:{ all -> 0x0066 }
            com.samsung.android.vexfwk.metadata.VexFwkMetadataKey$OCR_HAS_TEXT r2 = com.samsung.android.vexfwk.metadata.VexFwkMetadataKey.OCR_HAS_TEXT.INSTANCE     // Catch:{ all -> 0x0066 }
            java.lang.Object r4 = r4.get(r2)     // Catch:{ all -> 0x0066 }
            kotlin.jvm.internal.j.b(r4)     // Catch:{ all -> 0x0066 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0066 }
            boolean r2 = r4.booleanValue()     // Catch:{ all -> 0x0066 }
            goto L_0x0068
        L_0x0066:
            r4 = move-exception
            goto L_0x0075
        L_0x0068:
            He.F.u(r5, r1)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r2)
            java.lang.String r5 = "hasTextImpl X"
            android.util.Log.i(r0, r5)
            return r4
        L_0x0075:
            throw r4     // Catch:{ all -> 0x0076 }
        L_0x0076:
            r0 = move-exception
            He.F.u(r5, r4)
            throw r0
        L_0x007b:
            java.lang.String r5 = "Failed to process request : "
            c0.C0086a.x(r5, r0, r4)
            throw r4
        L_0x0081:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0083 }
        L_0x0083:
            r0 = move-exception
            He.F.u(r4, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.ocr.VexFwkImageOcr.hasTextImpl$lambda$8(java.lang.Object, com.samsung.android.vexfwk.sdk.ocr.VexFwkImageOcr):java.lang.Boolean");
    }

    /* access modifiers changed from: private */
    public static final x hasTextImpl$lambda$8$lambda$2(VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.OCR_FUNCTION.INSTANCE, Integer.valueOf(VexFwkImageOcrFuncType.HAS_TEXT.getValue()));
        return x.f4917a;
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    private final Result processResult(VexFwkSessionTotalResult vexFwkSessionTotalResult) {
        Object obj = vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.RESULT_CODE.INSTANCE);
        j.b(obj);
        if (WhenMappings.$EnumSwitchMapping$0[((VexFwkNodeResultCode) obj).ordinal()] != 1) {
            return new Result.Error(VexFwkImageOcrResultCode.ERR_UNKNOWN);
        }
        VexFwkOcrResultMeta vexFwkOcrResultMeta = (VexFwkOcrResultMeta) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.OCR_RESULT.INSTANCE);
        VexFwkOcrAdditionalMeta vexFwkOcrAdditionalMeta = (VexFwkOcrAdditionalMeta) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.OCR_ADDITIONAL_RESULT.INSTANCE);
        VexFwkOcrResultMetaV2 vexFwkOcrResultMetaV2 = (VexFwkOcrResultMetaV2) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.OCR_RESULT_V2.INSTANCE);
        if (vexFwkOcrResultMeta == null && vexFwkOcrResultMetaV2 == null) {
            Log.e(TAG, "No result metadata found!");
            return new Result.Error(VexFwkImageOcrResultCode.ERR_UNKNOWN);
        }
        if (vexFwkOcrResultMetaV2 == null && vexFwkOcrResultMeta != null) {
            vexFwkOcrResultMetaV2 = new VexFwkOcrResultMetaV2(vexFwkOcrResultMeta, vexFwkOcrAdditionalMeta);
        }
        if (vexFwkOcrResultMetaV2 == null) {
            return new Result.Error(VexFwkImageOcrResultCode.ERR_UNKNOWN);
        }
        return new Result.SuccessWithData(vexFwkOcrResultMetaV2.toResult());
    }

    private final <T> CompletableFuture<Result> recognizeImpl(T t) {
        CompletableFuture<Result> supplyAsync = CompletableFuture.supplyAsync(new C1362a(t, this, 1));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0047, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0048, code lost:
        He.F.u(r1, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004b, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0054, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0055, code lost:
        He.F.u(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0058, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.samsung.android.vexfwk.sdk.ocr.VexFwkImageOcr.Result recognizeImpl$lambda$14(java.lang.Object r3, com.samsung.android.vexfwk.sdk.ocr.VexFwkImageOcr r4) {
        /*
            java.lang.String r0 = TAG
            java.lang.String r1 = "recognizeV2Impl E"
            android.util.Log.i(r0, r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r1 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r1.<init>()
            r2 = 0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r3 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addInputBuffer(r1, (int) r2, r3)
            vd.d r1 = new vd.d
            r2 = 18
            r1.<init>(r2)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r3 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.setSettingMetadata(r3, r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r3 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r3
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r3 = r3.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r1 = com.samsung.android.vexfwk.session.VexFwkUsecase.IMAGE_OCR     // Catch:{ all -> 0x0052 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r1 = r4.getSession(r1)     // Catch:{ all -> 0x0052 }
            java.lang.Object r1 = r1.m60processRequestIoAF18A(r3)     // Catch:{ all -> 0x0052 }
            r2 = 0
            He.F.u(r3, r2)
            java.lang.Throwable r3 = me.k.a(r1)
            if (r3 != 0) goto L_0x004c
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r1 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r1
            com.samsung.android.vexfwk.sdk.ocr.VexFwkImageOcr$Result r3 = r4.processResult(r1)     // Catch:{ all -> 0x0045 }
            He.F.u(r1, r2)
            java.lang.String r4 = "recognizeV2Impl X"
            android.util.Log.i(r0, r4)
            return r3
        L_0x0045:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r4 = move-exception
            He.F.u(r1, r3)
            throw r4
        L_0x004c:
            java.lang.String r4 = "Failed to process request: "
            c0.C0086a.x(r4, r0, r3)
            throw r3
        L_0x0052:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0054 }
        L_0x0054:
            r0 = move-exception
            He.F.u(r3, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.ocr.VexFwkImageOcr.recognizeImpl$lambda$14(java.lang.Object, com.samsung.android.vexfwk.sdk.ocr.VexFwkImageOcr):com.samsung.android.vexfwk.sdk.ocr.VexFwkImageOcr$Result");
    }

    /* access modifiers changed from: private */
    public static final x recognizeImpl$lambda$14$lambda$9(VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.OCR_FUNCTION.INSTANCE, Integer.valueOf(VexFwkImageOcrFuncType.RECOGNIZE.getValue()));
        vexFwkMetadataNative.set(VexFwkMetadataKey.OCR_RESULT_VERSION.INSTANCE, VexFwkImageOcrResultVersion.RESULT_V2);
        return x.f4917a;
    }

    public final VexFwkImageOcr configure() {
        return (VexFwkImageOcr) configureWith(this, new vd.d(20));
    }

    public final CompletableFuture<Boolean> hasText(Bitmap bitmap) {
        j.e(bitmap, "bitmap");
        return hasTextImpl(bitmap);
    }

    public final CompletableFuture<Result> recognize(Bitmap bitmap) {
        j.e(bitmap, "bitmap");
        return recognizeImpl(bitmap);
    }

    public final CompletableFuture<Boolean> hasText(Image image) {
        j.e(image, "image");
        return hasTextImpl(image);
    }

    public final CompletableFuture<Result> recognize(Image image) {
        j.e(image, "image");
        return recognizeImpl(image);
    }
}
