package com.samsung.android.vexfwk.sdk.imgstyletransfer;

import A8.C0544a;
import Ad.f;
import Ae.b;
import He.F;
import N2.j;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import c0.C0086a;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.vexfwk.bitmap.VexFwkBitmapHardware;
import com.samsung.android.vexfwk.imagestyletransfer.VexFwkImageStyleTransferCapabilities;
import com.samsung.android.vexfwk.imagestyletransfer.VexFwkImageStyleTransferResultCode;
import com.samsung.android.vexfwk.imagestyletransfer.VexFwkImageStyleTransferStyles;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkNodeResultCode;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkProvider;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import e5.d;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import me.k;
import me.x;
import ne.C1194l;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00112\u00020\u0001:\u0002\u0011\u0012B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0000J)\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u0002H\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000e\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000b¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imgstyletransfer/VexFwkImageStyleTransfer;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "configure", "applyStyleTransferImpl", "Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/sdk/imgstyletransfer/VexFwkImageStyleTransfer$ImageStyleTransferResult;", "T", "buffer", "style", "Lcom/samsung/android/vexfwk/imagestyletransfer/VexFwkImageStyleTransferStyles$Companion$StyleTransferType;", "(Ljava/lang/Object;Lcom/samsung/android/vexfwk/imagestyletransfer/VexFwkImageStyleTransferStyles$Companion$StyleTransferType;)Ljava/util/concurrent/CompletableFuture;", "applyStyleTransfer", "image", "Landroid/media/Image;", "Landroid/graphics/Bitmap;", "Companion", "ImageStyleTransferResult", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageStyleTransfer extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_IMAGE = 0;
    private static final int STREAM_ID_OUTPUT_IMAGE = 1;
    /* access modifiers changed from: private */
    public static final String TAG = "VexFwkImageStyleTransfer";
    /* access modifiers changed from: private */
    public static final boolean isAvailable = VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.IMAGE_STYLE_TRANSFER);

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0007R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000b\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imgstyletransfer/VexFwkImageStyleTransfer$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "STREAM_ID_IMAGE", "", "STREAM_ID_OUTPUT_IMAGE", "isAvailable", "", "isAvailable$annotations", "()Z", "capabilities", "Lcom/samsung/android/vexfwk/imagestyletransfer/VexFwkImageStyleTransferCapabilities;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final VexFwkImageStyleTransferCapabilities capabilities$lambda$1$lambda$0() {
            return new VexFwkImageStyleTransferCapabilities(new int[0]);
        }

        public final VexFwkImageStyleTransferCapabilities capabilities() {
            VexFwkImageStyleTransferCapabilities vexFwkImageStyleTransferCapabilities = (VexFwkImageStyleTransferCapabilities) VexFwkProvider.fetchProperties(VexFwkUsecase.IMAGE_STYLE_TRANSFER).getOrElse(VexFwkMetadataKey.PROPERTY_IMAGE_STYLE_TRANSFER_CAPABILITIES.INSTANCE, new d(18));
            j.w("capabilities : ", C1194l.R0(vexFwkImageStyleTransferCapabilities, (String) null, (String) null, (String) null, (b) null, 63), VexFwkImageStyleTransfer.TAG);
            return vexFwkImageStyleTransferCapabilities;
        }

        public final boolean isAvailable() {
            return VexFwkImageStyleTransfer.isAvailable;
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imgstyletransfer/VexFwkImageStyleTransfer$ImageStyleTransferResult;", "", "<init>", "()V", "Success", "Error", "Lcom/samsung/android/vexfwk/sdk/imgstyletransfer/VexFwkImageStyleTransfer$ImageStyleTransferResult$Error;", "Lcom/samsung/android/vexfwk/sdk/imgstyletransfer/VexFwkImageStyleTransfer$ImageStyleTransferResult$Success;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class ImageStyleTransferResult {

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imgstyletransfer/VexFwkImageStyleTransfer$ImageStyleTransferResult$Error;", "Lcom/samsung/android/vexfwk/sdk/imgstyletransfer/VexFwkImageStyleTransfer$ImageStyleTransferResult;", "resultCode", "Lcom/samsung/android/vexfwk/imagestyletransfer/VexFwkImageStyleTransferResultCode;", "<init>", "(Lcom/samsung/android/vexfwk/imagestyletransfer/VexFwkImageStyleTransferResultCode;)V", "getResultCode", "()Lcom/samsung/android/vexfwk/imagestyletransfer/VexFwkImageStyleTransferResultCode;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Error extends ImageStyleTransferResult {
            private final VexFwkImageStyleTransferResultCode resultCode;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Error(VexFwkImageStyleTransferResultCode vexFwkImageStyleTransferResultCode) {
                super((e) null);
                kotlin.jvm.internal.j.e(vexFwkImageStyleTransferResultCode, OCRServiceConstant.KEY_RESULT_CODE);
                this.resultCode = vexFwkImageStyleTransferResultCode;
            }

            public static /* synthetic */ Error copy$default(Error error, VexFwkImageStyleTransferResultCode vexFwkImageStyleTransferResultCode, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    vexFwkImageStyleTransferResultCode = error.resultCode;
                }
                return error.copy(vexFwkImageStyleTransferResultCode);
            }

            public final VexFwkImageStyleTransferResultCode component1() {
                return this.resultCode;
            }

            public final Error copy(VexFwkImageStyleTransferResultCode vexFwkImageStyleTransferResultCode) {
                kotlin.jvm.internal.j.e(vexFwkImageStyleTransferResultCode, OCRServiceConstant.KEY_RESULT_CODE);
                return new Error(vexFwkImageStyleTransferResultCode);
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

            public final VexFwkImageStyleTransferResultCode getResultCode() {
                return this.resultCode;
            }

            public int hashCode() {
                return this.resultCode.hashCode();
            }

            public String toString() {
                VexFwkImageStyleTransferResultCode vexFwkImageStyleTransferResultCode = this.resultCode;
                return "Error(resultCode=" + vexFwkImageStyleTransferResultCode + ")";
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imgstyletransfer/VexFwkImageStyleTransfer$ImageStyleTransferResult$Success;", "Lcom/samsung/android/vexfwk/sdk/imgstyletransfer/VexFwkImageStyleTransfer$ImageStyleTransferResult;", "resBitmap", "Landroid/graphics/Bitmap;", "<init>", "(Landroid/graphics/Bitmap;)V", "getResBitmap", "()Landroid/graphics/Bitmap;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Success extends ImageStyleTransferResult {
            private final Bitmap resBitmap;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Success(Bitmap bitmap) {
                super((e) null);
                kotlin.jvm.internal.j.e(bitmap, "resBitmap");
                this.resBitmap = bitmap;
            }

            public static /* synthetic */ Success copy$default(Success success, Bitmap bitmap, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    bitmap = success.resBitmap;
                }
                return success.copy(bitmap);
            }

            public final Bitmap component1() {
                return this.resBitmap;
            }

            public final Success copy(Bitmap bitmap) {
                kotlin.jvm.internal.j.e(bitmap, "resBitmap");
                return new Success(bitmap);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if ((obj instanceof Success) && kotlin.jvm.internal.j.a(this.resBitmap, ((Success) obj).resBitmap)) {
                    return true;
                }
                return false;
            }

            public final Bitmap getResBitmap() {
                return this.resBitmap;
            }

            public int hashCode() {
                return this.resBitmap.hashCode();
            }

            public String toString() {
                Bitmap bitmap = this.resBitmap;
                return "Success(resBitmap=" + bitmap + ")";
            }
        }

        public /* synthetic */ ImageStyleTransferResult(e eVar) {
            this();
        }

        private ImageStyleTransferResult() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.samsung.android.vexfwk.param.VexFwkNodeResultCode[] r0 = com.samsung.android.vexfwk.param.VexFwkNodeResultCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.samsung.android.vexfwk.param.VexFwkNodeResultCode r1 = com.samsung.android.vexfwk.param.VexFwkNodeResultCode.OK     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.samsung.android.vexfwk.param.VexFwkNodeResultCode r1 = com.samsung.android.vexfwk.param.VexFwkNodeResultCode.PROCESS_FAIL     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.imgstyletransfer.VexFwkImageStyleTransfer.WhenMappings.<clinit>():void");
        }
    }

    private final <T> CompletableFuture<ImageStyleTransferResult> applyStyleTransferImpl(T t, VexFwkImageStyleTransferStyles.Companion.StyleTransferType styleTransferType) {
        CompletableFuture<ImageStyleTransferResult> supplyAsync = CompletableFuture.supplyAsync(new C0544a(t, styleTransferType, this, 8));
        kotlin.jvm.internal.j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    /* access modifiers changed from: private */
    public static final ImageStyleTransferResult applyStyleTransferImpl$lambda$9(Object obj, VexFwkImageStyleTransferStyles.Companion.StyleTransferType styleTransferType, VexFwkImageStyleTransfer vexFwkImageStyleTransfer) {
        Size size;
        Throwable th;
        Throwable th2;
        ImageStyleTransferResult imageStyleTransferResult;
        String str = TAG;
        Log.d(str, "applyStyleTransferImpl E");
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
        VexFwkSessionRequest.Sync build = ((VexFwkSessionRequest.Sync.Builder) VexFwkSessionRequestKt.setSettingMetadata(VexFwkSessionRequestKt.addOutputBuffer(VexFwkSessionRequestKt.addInputBuffer(new VexFwkSessionRequest.Sync.Builder(), 0, obj), 1, vexFwkBitmapHardware.getBitmap()), new f(22, (Object) styleTransferType))).build();
        try {
            Object r10 = vexFwkImageStyleTransfer.getSession(VexFwkUsecase.IMAGE_STYLE_TRANSFER).m60processRequestIoAF18A(build);
            F.u(build, (Throwable) null);
            Throwable a7 = k.a(r10);
            if (a7 == null) {
                VexFwkSessionTotalResult vexFwkSessionTotalResult = (VexFwkSessionTotalResult) r10;
                Log.d(str, "applyStyleTransferImpl success");
                try {
                    Object obj2 = vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.RESULT_CODE.INSTANCE);
                    kotlin.jvm.internal.j.b(obj2);
                    int i2 = WhenMappings.$EnumSwitchMapping$0[((VexFwkNodeResultCode) obj2).ordinal()];
                    if (i2 == 1) {
                        imageStyleTransferResult = new ImageStyleTransferResult.Success(vexFwkBitmapHardware.toBitmapCropped(0, 0, vexFwkBitmapHardware.getWidth(), vexFwkBitmapHardware.getHeight(), Bitmap.Config.ARGB_8888));
                    } else if (i2 != 2) {
                        imageStyleTransferResult = new ImageStyleTransferResult.Error(VexFwkImageStyleTransferResultCode.ERR_UNKNOWN);
                    } else {
                        imageStyleTransferResult = new ImageStyleTransferResult.Error(VexFwkImageStyleTransferResultCode.ERR_UNKNOWN);
                    }
                    F.u(vexFwkSessionTotalResult, (Throwable) null);
                    vexFwkBitmapHardware.close();
                    Log.i(str, "applyStyleTransferImpl X");
                    return imageStyleTransferResult;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    F.u(vexFwkSessionTotalResult, th2);
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
    public static final x applyStyleTransferImpl$lambda$9$lambda$3(VexFwkImageStyleTransferStyles.Companion.StyleTransferType styleTransferType, VexFwkMetadataNative vexFwkMetadataNative) {
        kotlin.jvm.internal.j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.STYLE_TRANSFER_TYPE.INSTANCE, Integer.valueOf(styleTransferType.getValue()));
        return x.f4917a;
    }

    public static final VexFwkImageStyleTransferCapabilities capabilities() {
        return Companion.capabilities();
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1(VexFwkImageStyleTransfer vexFwkImageStyleTransfer) {
        kotlin.jvm.internal.j.e(vexFwkImageStyleTransfer, "$this$configureWith");
        vexFwkImageStyleTransfer.createSession(VexFwkUsecase.IMAGE_STYLE_TRANSFER, new vd.d(7));
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

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    public final CompletableFuture<ImageStyleTransferResult> applyStyleTransfer(Image image, VexFwkImageStyleTransferStyles.Companion.StyleTransferType styleTransferType) {
        kotlin.jvm.internal.j.e(image, "image");
        kotlin.jvm.internal.j.e(styleTransferType, "style");
        return applyStyleTransferImpl(image, styleTransferType);
    }

    public final VexFwkImageStyleTransfer configure() {
        return (VexFwkImageStyleTransfer) configureWith(this, new vd.d(8));
    }

    public final CompletableFuture<ImageStyleTransferResult> applyStyleTransfer(Bitmap bitmap, VexFwkImageStyleTransferStyles.Companion.StyleTransferType styleTransferType) {
        kotlin.jvm.internal.j.e(bitmap, "image");
        kotlin.jvm.internal.j.e(styleTransferType, "style");
        return applyStyleTransferImpl(bitmap, styleTransferType);
    }
}
