package com.samsung.android.vexfwk.sdk.imgclipper;

import Bd.C0726b;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.Surface;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.vexfwk.hardware.VexFwkHardwareBufferNative;
import com.samsung.android.vexfwk.imageclipper.VexFwkImageClipperResultCode;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.param.VexFwkImageClipperInfo;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionPartialResult;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import vd.d;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00112\u00020\u0001:\u0003\u0011\u0012\u0013B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0000J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0003J\u0018\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0003J\u0018\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0003¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imgclipper/VexFwkImageClipper;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "configure", "objectCapture", "Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/sdk/imgclipper/VexFwkImageClipper$ClipResult;", "bitmap", "Landroid/graphics/Bitmap;", "objectCaptureSuccess", "totalResult", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "objectCaptureClipResult", "clipperInfo", "Lcom/samsung/android/vexfwk/param/VexFwkImageClipperInfo;", "populateClipBitmap", "Companion", "ClipBitmap", "ClipResult", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageClipper extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_IMAGE = 0;
    private static final int STREAM_ID_OUTPUT_IMAGE = 1;
    private static final String TAG = "VexFwkImageClipper";
    /* access modifiers changed from: private */
    public static final boolean isAvailable = VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.IMAGE_CLIPPER);

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imgclipper/VexFwkImageClipper$ClipBitmap;", "", "bitmap", "Landroid/graphics/Bitmap;", "roi", "Landroid/graphics/Rect;", "<init>", "(Landroid/graphics/Bitmap;Landroid/graphics/Rect;)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getRoi", "()Landroid/graphics/Rect;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ClipBitmap {
        private final Bitmap bitmap;
        private final Rect roi;

        public ClipBitmap(Bitmap bitmap2, Rect rect) {
            j.e(bitmap2, "bitmap");
            j.e(rect, "roi");
            this.bitmap = bitmap2;
            this.roi = rect;
        }

        public static /* synthetic */ ClipBitmap copy$default(ClipBitmap clipBitmap, Bitmap bitmap2, Rect rect, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                bitmap2 = clipBitmap.bitmap;
            }
            if ((i2 & 2) != 0) {
                rect = clipBitmap.roi;
            }
            return clipBitmap.copy(bitmap2, rect);
        }

        public final Bitmap component1() {
            return this.bitmap;
        }

        public final Rect component2() {
            return this.roi;
        }

        public final ClipBitmap copy(Bitmap bitmap2, Rect rect) {
            j.e(bitmap2, "bitmap");
            j.e(rect, "roi");
            return new ClipBitmap(bitmap2, rect);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ClipBitmap)) {
                return false;
            }
            ClipBitmap clipBitmap = (ClipBitmap) obj;
            if (j.a(this.bitmap, clipBitmap.bitmap) && j.a(this.roi, clipBitmap.roi)) {
                return true;
            }
            return false;
        }

        public final Bitmap getBitmap() {
            return this.bitmap;
        }

        public final Rect getRoi() {
            return this.roi;
        }

        public int hashCode() {
            return this.roi.hashCode() + (this.bitmap.hashCode() * 31);
        }

        public String toString() {
            Bitmap bitmap2 = this.bitmap;
            Rect rect = this.roi;
            return "ClipBitmap(bitmap=" + bitmap2 + ", roi=" + rect + ")";
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imgclipper/VexFwkImageClipper$ClipResult;", "", "<init>", "()V", "Success", "Error", "Lcom/samsung/android/vexfwk/sdk/imgclipper/VexFwkImageClipper$ClipResult$Error;", "Lcom/samsung/android/vexfwk/sdk/imgclipper/VexFwkImageClipper$ClipResult$Success;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class ClipResult {

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imgclipper/VexFwkImageClipper$ClipResult$Error;", "Lcom/samsung/android/vexfwk/sdk/imgclipper/VexFwkImageClipper$ClipResult;", "resultCode", "Lcom/samsung/android/vexfwk/imageclipper/VexFwkImageClipperResultCode;", "<init>", "(Lcom/samsung/android/vexfwk/imageclipper/VexFwkImageClipperResultCode;)V", "getResultCode", "()Lcom/samsung/android/vexfwk/imageclipper/VexFwkImageClipperResultCode;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Error extends ClipResult {
            private final VexFwkImageClipperResultCode resultCode;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Error(VexFwkImageClipperResultCode vexFwkImageClipperResultCode) {
                super((e) null);
                j.e(vexFwkImageClipperResultCode, OCRServiceConstant.KEY_RESULT_CODE);
                this.resultCode = vexFwkImageClipperResultCode;
            }

            public static /* synthetic */ Error copy$default(Error error, VexFwkImageClipperResultCode vexFwkImageClipperResultCode, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    vexFwkImageClipperResultCode = error.resultCode;
                }
                return error.copy(vexFwkImageClipperResultCode);
            }

            public final VexFwkImageClipperResultCode component1() {
                return this.resultCode;
            }

            public final Error copy(VexFwkImageClipperResultCode vexFwkImageClipperResultCode) {
                j.e(vexFwkImageClipperResultCode, OCRServiceConstant.KEY_RESULT_CODE);
                return new Error(vexFwkImageClipperResultCode);
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

            public final VexFwkImageClipperResultCode getResultCode() {
                return this.resultCode;
            }

            public int hashCode() {
                return this.resultCode.hashCode();
            }

            public String toString() {
                VexFwkImageClipperResultCode vexFwkImageClipperResultCode = this.resultCode;
                return "Error(resultCode=" + vexFwkImageClipperResultCode + ")";
            }
        }

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imgclipper/VexFwkImageClipper$ClipResult$Success;", "Lcom/samsung/android/vexfwk/sdk/imgclipper/VexFwkImageClipper$ClipResult;", "singleBitmap", "Lcom/samsung/android/vexfwk/sdk/imgclipper/VexFwkImageClipper$ClipBitmap;", "objectsBitmap", "", "<init>", "(Lcom/samsung/android/vexfwk/sdk/imgclipper/VexFwkImageClipper$ClipBitmap;Ljava/util/List;)V", "getSingleBitmap", "()Lcom/samsung/android/vexfwk/sdk/imgclipper/VexFwkImageClipper$ClipBitmap;", "getObjectsBitmap", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Success extends ClipResult {
            private final List<ClipBitmap> objectsBitmap;
            private final ClipBitmap singleBitmap;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Success(ClipBitmap clipBitmap, List<ClipBitmap> list) {
                super((e) null);
                j.e(clipBitmap, "singleBitmap");
                j.e(list, "objectsBitmap");
                this.singleBitmap = clipBitmap;
                this.objectsBitmap = list;
            }

            public static /* synthetic */ Success copy$default(Success success, ClipBitmap clipBitmap, List<ClipBitmap> list, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    clipBitmap = success.singleBitmap;
                }
                if ((i2 & 2) != 0) {
                    list = success.objectsBitmap;
                }
                return success.copy(clipBitmap, list);
            }

            public final ClipBitmap component1() {
                return this.singleBitmap;
            }

            public final List<ClipBitmap> component2() {
                return this.objectsBitmap;
            }

            public final Success copy(ClipBitmap clipBitmap, List<ClipBitmap> list) {
                j.e(clipBitmap, "singleBitmap");
                j.e(list, "objectsBitmap");
                return new Success(clipBitmap, list);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Success)) {
                    return false;
                }
                Success success = (Success) obj;
                if (j.a(this.singleBitmap, success.singleBitmap) && j.a(this.objectsBitmap, success.objectsBitmap)) {
                    return true;
                }
                return false;
            }

            public final List<ClipBitmap> getObjectsBitmap() {
                return this.objectsBitmap;
            }

            public final ClipBitmap getSingleBitmap() {
                return this.singleBitmap;
            }

            public int hashCode() {
                return this.objectsBitmap.hashCode() + (this.singleBitmap.hashCode() * 31);
            }

            public String toString() {
                ClipBitmap clipBitmap = this.singleBitmap;
                List<ClipBitmap> list = this.objectsBitmap;
                return "Success(singleBitmap=" + clipBitmap + ", objectsBitmap=" + list + ")";
            }
        }

        public /* synthetic */ ClipResult(e eVar) {
            this();
        }

        private ClipResult() {
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000b\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imgclipper/VexFwkImageClipper$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "STREAM_ID_INPUT_IMAGE", "", "STREAM_ID_OUTPUT_IMAGE", "isAvailable", "", "isAvailable$annotations", "()Z", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isAvailable() {
            return VexFwkImageClipper.isAvailable;
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VexFwkImageClipperResultCode.values().length];
            try {
                iArr[VexFwkImageClipperResultCode.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1(VexFwkImageClipper vexFwkImageClipper) {
        j.e(vexFwkImageClipper, "$this$configureWith");
        vexFwkImageClipper.createSession(VexFwkUsecase.IMAGE_CLIPPER, new d(5));
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

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0055, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
        He.F.u(r1, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0059, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0062, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0063, code lost:
        He.F.u(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0066, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.samsung.android.vexfwk.sdk.imgclipper.VexFwkImageClipper.ClipResult objectCapture$lambda$8(android.graphics.Bitmap r4, com.samsung.android.vexfwk.sdk.imgclipper.VexFwkImageClipper r5) {
        /*
            java.lang.String r0 = TAG
            java.lang.String r1 = "objectCapture E"
            android.util.Log.d(r0, r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r1 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r1.<init>()
            r2 = 0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r4 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addInputBuffer(r1, (int) r2, (android.graphics.Bitmap) r4)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r4 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r4
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r4 = r4.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r1 = com.samsung.android.vexfwk.session.VexFwkUsecase.IMAGE_CLIPPER     // Catch:{ all -> 0x0060 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r1 = r5.getSession(r1)     // Catch:{ all -> 0x0060 }
            java.lang.Object r1 = r1.m60processRequestIoAF18A(r4)     // Catch:{ all -> 0x0060 }
            r2 = 0
            He.F.u(r4, r2)
            java.lang.Throwable r4 = me.k.a(r1)
            if (r4 != 0) goto L_0x005a
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r1 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r1
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r4 = r1.getResultMetadata()     // Catch:{ all -> 0x004a }
            com.samsung.android.vexfwk.metadata.VexFwkMetadataKey$RESULT_CODE r3 = com.samsung.android.vexfwk.metadata.VexFwkMetadataKey.RESULT_CODE.INSTANCE     // Catch:{ all -> 0x004a }
            java.lang.Object r4 = r4.get(r3)     // Catch:{ all -> 0x004a }
            com.samsung.android.vexfwk.param.VexFwkNodeResultCode r4 = (com.samsung.android.vexfwk.param.VexFwkNodeResultCode) r4     // Catch:{ all -> 0x004a }
            com.samsung.android.vexfwk.param.VexFwkNodeResultCode r3 = com.samsung.android.vexfwk.param.VexFwkNodeResultCode.OK     // Catch:{ all -> 0x004a }
            if (r4 != r3) goto L_0x004c
            com.samsung.android.vexfwk.sdk.imgclipper.VexFwkImageClipper$ClipResult r4 = r5.objectCaptureSuccess(r1)     // Catch:{ all -> 0x004a }
            He.F.u(r1, r2)
            java.lang.String r5 = "objectCapture X"
            android.util.Log.d(r0, r5)
            return r4
        L_0x004a:
            r4 = move-exception
            goto L_0x0054
        L_0x004c:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x004a }
            java.lang.String r5 = "Failed to capture object"
            r4.<init>(r5)     // Catch:{ all -> 0x004a }
            throw r4     // Catch:{ all -> 0x004a }
        L_0x0054:
            throw r4     // Catch:{ all -> 0x0055 }
        L_0x0055:
            r5 = move-exception
            He.F.u(r1, r4)
            throw r5
        L_0x005a:
            java.lang.String r5 = "Failed to process request : "
            c0.C0086a.x(r5, r0, r4)
            throw r4
        L_0x0060:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0062 }
        L_0x0062:
            r0 = move-exception
            He.F.u(r4, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.imgclipper.VexFwkImageClipper.objectCapture$lambda$8(android.graphics.Bitmap, com.samsung.android.vexfwk.sdk.imgclipper.VexFwkImageClipper):com.samsung.android.vexfwk.sdk.imgclipper.VexFwkImageClipper$ClipResult");
    }

    private final ClipResult objectCaptureClipResult(VexFwkImageClipperInfo vexFwkImageClipperInfo, VexFwkSessionTotalResult vexFwkSessionTotalResult) {
        int i2;
        Rect[] objectsRect = vexFwkImageClipperInfo.getObjectsRect();
        if (objectsRect != null) {
            i2 = objectsRect.length;
        } else {
            i2 = 0;
        }
        if (i2 > 0) {
            return populateClipBitmap(vexFwkImageClipperInfo, vexFwkSessionTotalResult);
        }
        return new ClipResult.Error(VexFwkImageClipperResultCode.ERR_NO_OBJECT);
    }

    private final ClipResult objectCaptureSuccess(VexFwkSessionTotalResult vexFwkSessionTotalResult) {
        VexFwkImageClipperInfo vexFwkImageClipperInfo = (VexFwkImageClipperInfo) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.IMAGE_CLIPPER_INFO.INSTANCE);
        if (vexFwkImageClipperInfo == null) {
            return new ClipResult.Error(VexFwkImageClipperResultCode.ERR_UNKNOWN);
        }
        if (WhenMappings.$EnumSwitchMapping$0[vexFwkImageClipperInfo.getResultCode().ordinal()] == 1) {
            return objectCaptureClipResult(vexFwkImageClipperInfo, vexFwkSessionTotalResult);
        }
        return new ClipResult.Error(vexFwkImageClipperInfo.getResultCode());
    }

    private final ClipResult populateClipBitmap(VexFwkImageClipperInfo vexFwkImageClipperInfo, VexFwkSessionTotalResult vexFwkSessionTotalResult) {
        LinkedList linkedList = new LinkedList();
        boolean z = true;
        int i2 = 0;
        ClipBitmap clipBitmap = null;
        for (VexFwkSessionPartialResult outputBuffers : vexFwkSessionTotalResult.getPartialResults()) {
            ArrayList<VexFwkBuffer> arrayList = new ArrayList<>();
            for (Object next : outputBuffers.getOutputBuffers()) {
                if (((VexFwkBuffer) next).getStreamId() == 1) {
                    arrayList.add(next);
                }
            }
            for (VexFwkBuffer vexFwkBuffer : arrayList) {
                if (z) {
                    Bitmap convertToBitmap$default = VexFwkHardwareBufferNative.Companion.convertToBitmap$default(VexFwkHardwareBufferNative.Companion, vexFwkBuffer.getHardwareBuffer(), false, 2, (Object) null);
                    Rect singleRect = vexFwkImageClipperInfo.getSingleRect();
                    j.b(singleRect);
                    clipBitmap = new ClipBitmap(convertToBitmap$default, singleRect);
                    z = false;
                } else {
                    Bitmap convertToBitmap$default2 = VexFwkHardwareBufferNative.Companion.convertToBitmap$default(VexFwkHardwareBufferNative.Companion, vexFwkBuffer.getHardwareBuffer(), false, 2, (Object) null);
                    Rect[] objectsRect = vexFwkImageClipperInfo.getObjectsRect();
                    j.b(objectsRect);
                    linkedList.add(new ClipBitmap(convertToBitmap$default2, objectsRect[i2]));
                    i2++;
                }
            }
        }
        j.b(clipBitmap);
        return new ClipResult.Success(clipBitmap, linkedList);
    }

    public final VexFwkImageClipper configure() {
        return (VexFwkImageClipper) configureWith(this, new d(6));
    }

    public final CompletableFuture<ClipResult> objectCapture(Bitmap bitmap) {
        j.e(bitmap, "bitmap");
        CompletableFuture<ClipResult> supplyAsync = CompletableFuture.supplyAsync(new C0726b(15, bitmap, this));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }
}
