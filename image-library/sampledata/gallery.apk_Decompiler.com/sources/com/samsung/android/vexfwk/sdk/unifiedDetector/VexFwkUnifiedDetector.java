package com.samsung.android.vexfwk.sdk.unifiedDetector;

import Ad.C0720a;
import Ad.C0721b;
import Ad.l;
import L1.d;
import android.content.Context;
import android.graphics.Bitmap;
import com.google.common.util.concurrent.ListenableFuture;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.param.VexFwkOrientation;
import com.samsung.android.vexfwk.param.VexFwkUnifiedDetectorMode;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperBase;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;
import ne.C1202t;
import o1.C0246a;
import qe.C1227c;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0016B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00000\t2\u0006\u0010\n\u001a\u00020\u000bJ)\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\t\"\u0004\b\u0000\u0010\u000e2\u0006\u0010\u000f\u001a\u0002H\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¢\u0006\u0002\u0010\u0012J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\t2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0010\u001a\u00020\u0011R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/unifiedDetector/VexFwkUnifiedDetector;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBase;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "configure", "Lcom/google/common/util/concurrent/ListenableFuture;", "mode", "Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorMode;", "detectImpl", "Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorResult;", "T", "buffer", "imageOrientation", "Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "(Ljava/lang/Object;Lcom/samsung/android/vexfwk/param/VexFwkOrientation;)Lcom/google/common/util/concurrent/ListenableFuture;", "detect", "image", "Landroid/graphics/Bitmap;", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkUnifiedDetector extends VexFwkHelperBase<VexFwkUnifiedDetector> {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_IMAGE = 0;
    /* access modifiers changed from: private */
    public static final String TAG = "VexFwkUnifiedDetector";
    /* access modifiers changed from: private */
    public static final f isAvailable$delegate = d.q(new C0721b(0));
    /* access modifiers changed from: private */
    public static final f supportedModes$delegate = d.q(new C0721b(1));
    private final Context context;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R!\u0010\u0007\u001a\u00020\u00048FX\u0002¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u0012\u0004\b\t\u0010\u0003\u001a\u0004\b\u0007\u0010\bR'\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8FX\u0002¢\u0006\u0012\n\u0004\b\f\u0010\u0006\u0012\u0004\b\u000f\u0010\u0003\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0013\u001a\n \u0012*\u0004\u0018\u00010\u00110\u00118\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002XT¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/unifiedDetector/VexFwkUnifiedDetector$Companion;", "", "<init>", "()V", "", "isAvailable$delegate", "Lme/f;", "isAvailable", "()Z", "isAvailable$annotations", "", "Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorMode;", "supportedModes$delegate", "getSupportedModes", "()Ljava/util/List;", "getSupportedModes$annotations", "supportedModes", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "", "STREAM_ID_IMAGE", "I", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final List<VexFwkUnifiedDetectorMode> getSupportedModes() {
            return (List) VexFwkUnifiedDetector.supportedModes$delegate.getValue();
        }

        public final boolean isAvailable() {
            return ((Boolean) VexFwkUnifiedDetector.isAvailable$delegate.getValue()).booleanValue();
        }

        private Companion() {
        }

        public static /* synthetic */ void getSupportedModes$annotations() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    public VexFwkUnifiedDetector(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    public static /* synthetic */ ListenableFuture detect$default(VexFwkUnifiedDetector vexFwkUnifiedDetector, Bitmap bitmap, VexFwkOrientation vexFwkOrientation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            vexFwkOrientation = VexFwkOrientation.ORIENTATION_ROTATE_0;
        }
        return vexFwkUnifiedDetector.detect(bitmap, vexFwkOrientation);
    }

    private final <T> ListenableFuture detectImpl(T t, VexFwkOrientation vexFwkOrientation) {
        return supplyAsyncProcess(new l(vexFwkOrientation, (Object) t, (C1227c) null));
    }

    public static final List<VexFwkUnifiedDetectorMode> getSupportedModes() {
        return Companion.getSupportedModes();
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    /* access modifiers changed from: private */
    public static final boolean isAvailable_delegate$lambda$0() {
        return VexFwkHelperBase.Companion.isAvailable(VexFwkUsecase.UNIFIED_DETECTOR);
    }

    /* access modifiers changed from: private */
    public static final List supportedModes_delegate$lambda$2() {
        return (List) VexFwkHelperBase.Companion.getProperties(VexFwkUsecase.UNIFIED_DETECTOR, VexFwkMetadataKey.PROPERTY_UNIFIED_DETECTOR_SUPPORTED_MODES.INSTANCE, new C0720a(0));
    }

    /* access modifiers changed from: private */
    public static final List supportedModes_delegate$lambda$2$lambda$1() {
        if (Companion.isAvailable()) {
            return C0246a.e0(VexFwkUnifiedDetectorMode.OBJECT_DETECTION);
        }
        return C1202t.d;
    }

    public final ListenableFuture configure(VexFwkUnifiedDetectorMode vexFwkUnifiedDetectorMode) {
        j.e(vexFwkUnifiedDetectorMode, "mode");
        return supplyAsyncConfiguration(new a(vexFwkUnifiedDetectorMode, (C1227c) null));
    }

    public final ListenableFuture detect(Bitmap bitmap, VexFwkOrientation vexFwkOrientation) {
        j.e(bitmap, "image");
        j.e(vexFwkOrientation, "imageOrientation");
        return detectImpl(bitmap, vexFwkOrientation);
    }

    public Context getContext() {
        return this.context;
    }
}
