package com.samsung.android.vexfwk.sdk.docscan;

import Bd.C0725a;
import He.F;
import W.a;
import Wf.c;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import c0.C0086a;
import com.samsung.android.sum.core.service.d;
import com.samsung.android.vexfwk.bitmap.VexFwkBitmapHardware;
import com.samsung.android.vexfwk.docscan.VexFwkDocDewarpModes;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkDocumentCorners;
import com.samsung.android.vexfwk.param.VexFwkDocumentScanRect;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.k;
import me.x;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0000J1\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u0002H\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002¢\u0006\u0002\u0010\u000eJ&\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rJ&\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocDewarper;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "configure", "dewarpDocumentImpl", "Ljava/util/concurrent/CompletableFuture;", "Landroid/graphics/Bitmap;", "T", "buffer", "corners", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentCorners;", "dewarpMode", "Lcom/samsung/android/vexfwk/docscan/VexFwkDocDewarpModes$Companion$DewarpMode;", "(Ljava/lang/Object;Lcom/samsung/android/vexfwk/param/VexFwkDocumentCorners;Lcom/samsung/android/vexfwk/docscan/VexFwkDocDewarpModes$Companion$DewarpMode;)Ljava/util/concurrent/CompletableFuture;", "dewarpDocument", "image", "Landroid/media/Image;", "bitmap", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkDocDewarper extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_IMAGE = 0;
    private static final int STREAM_ID_OUTPUT_IMAGE = 1;
    private static final String TAG = "VexFwkDocDewarper";
    /* access modifiers changed from: private */
    public static final boolean isAvailable = VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.DOCUMENT_DEWARPING);

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000b\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocDewarper$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "STREAM_ID_INPUT_IMAGE", "", "STREAM_ID_OUTPUT_IMAGE", "isAvailable", "", "isAvailable$annotations", "()Z", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isAvailable() {
            return VexFwkDocDewarper.isAvailable;
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1(VexFwkDocDewarper vexFwkDocDewarper) {
        j.e(vexFwkDocDewarper, "$this$configureWith");
        vexFwkDocDewarper.createSession(VexFwkUsecase.DOCUMENT_DEWARPING, new b(0));
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

    public static /* synthetic */ CompletableFuture dewarpDocument$default(VexFwkDocDewarper vexFwkDocDewarper, Image image, VexFwkDocumentCorners vexFwkDocumentCorners, VexFwkDocDewarpModes.Companion.DewarpMode dewarpMode, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            dewarpMode = VexFwkDocDewarpModes.Companion.DewarpMode.DEWARP_MODE_CV;
        }
        return vexFwkDocDewarper.dewarpDocument(image, vexFwkDocumentCorners, dewarpMode);
    }

    private final <T> CompletableFuture<Bitmap> dewarpDocumentImpl(T t, VexFwkDocumentCorners vexFwkDocumentCorners, VexFwkDocDewarpModes.Companion.DewarpMode dewarpMode) {
        VexFwkDocumentCorners vexFwkDocumentCorners2 = vexFwkDocumentCorners;
        CompletableFuture<Bitmap> supplyAsync = CompletableFuture.supplyAsync(new d(1, vexFwkDocumentCorners2, t, dewarpMode, this));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public static final Bitmap dewarpDocumentImpl$lambda$12(Object obj, VexFwkDocumentCorners vexFwkDocumentCorners, VexFwkDocDewarpModes.Companion.DewarpMode dewarpMode, VexFwkDocDewarper vexFwkDocDewarper) {
        Size size;
        Throwable th;
        Throwable th2;
        Throwable th3;
        String str = TAG;
        Log.d(str, "dewarpDocumentImpl E");
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
        VexFwkSessionRequest.Sync build = ((VexFwkSessionRequest.Sync.Builder) VexFwkSessionRequestKt.setSettingMetadata(VexFwkSessionRequestKt.addOutputBuffer(VexFwkSessionRequestKt.addInputBuffer(new VexFwkSessionRequest.Sync.Builder(), 0, obj), 1, vexFwkBitmapHardware.getBitmap()), new c(6, vexFwkDocumentCorners, dewarpMode))).build();
        try {
            Object r10 = vexFwkDocDewarper.getSession(VexFwkUsecase.DOCUMENT_DEWARPING).m60processRequestIoAF18A(build);
            F.u(build, (Throwable) null);
            Throwable a7 = k.a(r10);
            if (a7 == null) {
                VexFwkSessionTotalResult vexFwkSessionTotalResult = (VexFwkSessionTotalResult) r10;
                try {
                    VexFwkDocumentScanRect vexFwkDocumentScanRect = (VexFwkDocumentScanRect) vexFwkSessionTotalResult.getResultMetadata().getOrElse(VexFwkMetadataKey.DOCUMENT_SCAN_RECT.INSTANCE, new a(25));
                    F.u(vexFwkSessionTotalResult, (Throwable) null);
                    Log.d(str, "documentRectificationRect(" + vexFwkDocumentScanRect + ")");
                    try {
                        Bitmap bitmapCropped = vexFwkBitmapHardware.toBitmapCropped(vexFwkDocumentScanRect.getLeft(), vexFwkDocumentScanRect.getTop(), vexFwkDocumentScanRect.getRight(), vexFwkDocumentScanRect.getBottom(), Bitmap.Config.ARGB_8888);
                        F.u(vexFwkBitmapHardware, (Throwable) null);
                        Log.d(str, "dewarpDocumentImpl X");
                        return bitmapCropped;
                    } catch (Throwable th4) {
                        Throwable th5 = th4;
                        F.u(vexFwkBitmapHardware, th3);
                        throw th5;
                    }
                } catch (Throwable th6) {
                    Throwable th7 = th6;
                    F.u(vexFwkSessionTotalResult, th2);
                    throw th7;
                }
            } else {
                C0086a.x("Failed to process request : ", str, a7);
                throw a7;
            }
        } catch (Throwable th8) {
            Throwable th9 = th8;
            F.u(build, th);
            throw th9;
        }
    }

    /* access modifiers changed from: private */
    public static final x dewarpDocumentImpl$lambda$12$lambda$3(VexFwkDocumentCorners vexFwkDocumentCorners, VexFwkDocDewarpModes.Companion.DewarpMode dewarpMode, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.DOCUMENT_RECT.INSTANCE, vexFwkDocumentCorners);
        vexFwkMetadataNative.set(VexFwkMetadataKey.DOCUMENT_DEWARP_MODE.INSTANCE, Integer.valueOf(dewarpMode.getValue()));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final VexFwkDocumentScanRect dewarpDocumentImpl$lambda$12$lambda$7$lambda$6$lambda$5() {
        throw new Exception("Request Failed due to no ROI metadata found in result metadata.");
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    public final VexFwkDocDewarper configure() {
        return (VexFwkDocDewarper) configureWith(this, new C0725a(29));
    }

    public final CompletableFuture<Bitmap> dewarpDocument(Image image, VexFwkDocumentCorners vexFwkDocumentCorners, VexFwkDocDewarpModes.Companion.DewarpMode dewarpMode) {
        j.e(image, "image");
        j.e(vexFwkDocumentCorners, "corners");
        j.e(dewarpMode, "dewarpMode");
        return dewarpDocumentImpl(image, vexFwkDocumentCorners, dewarpMode);
    }

    public final CompletableFuture<Bitmap> dewarpDocument(Bitmap bitmap, VexFwkDocumentCorners vexFwkDocumentCorners, VexFwkDocDewarpModes.Companion.DewarpMode dewarpMode) {
        j.e(bitmap, "bitmap");
        j.e(vexFwkDocumentCorners, "corners");
        j.e(dewarpMode, "dewarpMode");
        return dewarpDocumentImpl(bitmap, vexFwkDocumentCorners, dewarpMode);
    }

    public static /* synthetic */ CompletableFuture dewarpDocument$default(VexFwkDocDewarper vexFwkDocDewarper, Bitmap bitmap, VexFwkDocumentCorners vexFwkDocumentCorners, VexFwkDocDewarpModes.Companion.DewarpMode dewarpMode, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            dewarpMode = VexFwkDocDewarpModes.Companion.DewarpMode.DEWARP_MODE_CV;
        }
        return vexFwkDocDewarper.dewarpDocument(bitmap, vexFwkDocumentCorners, dewarpMode);
    }
}
