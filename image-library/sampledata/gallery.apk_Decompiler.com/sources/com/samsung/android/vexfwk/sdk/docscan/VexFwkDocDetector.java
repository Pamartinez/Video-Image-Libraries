package com.samsung.android.vexfwk.sdk.docscan;

import Ad.C0721b;
import Ae.b;
import Bd.C0726b;
import He.F;
import L1.d;
import L2.a;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.view.Surface;
import com.samsung.android.sdk.mobileservice.profile.Profile;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkDocumentCorners;
import com.samsung.android.vexfwk.param.VexFwkDocumentDetectionType;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkResultCode;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;
import me.i;
import me.x;
import ne.C1202t;
import o1.C0246a;

@Metadata(d1 = {"\u0000O\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\b*\u0001\u001d\u0018\u0000 !2\u00020\u0001:\u0003!\"#B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ#\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\f\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\t\u0010\u000fJ\u0015\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0012\u001a\u00020\b¢\u0006\u0004\b\u0012\u0010\u0003J\u001d\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0014\u0010\nJ\u001b\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0005\u001a\u00020\u0016¢\u0006\u0004\b\u0014\u0010\u0017R,\u0010\u001b\u001a\u001a\u0012\u0004\u0012\u00020\u0019\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\u001a0\u00188\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010\u0011\u001a\u00020\u00108\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010 ¨\u0006$"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocDetector;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "Landroid/media/Image;", "image", "Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocDetector$Callback;", "callback", "Lme/x;", "detectDocumentImpl", "(Landroid/media/Image;Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocDetector$Callback;)V", "T", "buffer", "Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocDetector$DetectResult;", "(Ljava/lang/Object;)Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentDetectionType;", "opType", "configure", "(Lcom/samsung/android/vexfwk/param/VexFwkDocumentDetectionType;)V", "detectDocument", "(Landroid/media/Image;)Ljava/util/concurrent/CompletableFuture;", "Landroid/graphics/Bitmap;", "(Landroid/graphics/Bitmap;)Ljava/util/concurrent/CompletableFuture;", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lme/i;", "requestMap", "Ljava/util/concurrent/ConcurrentHashMap;", "com/samsung/android/vexfwk/sdk/docscan/VexFwkDocDetector$sessionCallback$1", "sessionCallback", "Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocDetector$sessionCallback$1;", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentDetectionType;", "Companion", "DetectResult", "Callback", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkDocDetector extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_IMAGE = 0;
    /* access modifiers changed from: private */
    public static final String TAG = "VexFwkDocDetector";
    /* access modifiers changed from: private */
    public static final f isAvailable$delegate = d.q(new C0721b(11));
    /* access modifiers changed from: private */
    public static final f supportedOperationTypes$delegate = d.q(new C0721b(12));
    private VexFwkDocumentDetectionType opType = VexFwkDocumentDetectionType.CAMERA_PREVIEW;
    /* access modifiers changed from: private */
    public final ConcurrentHashMap<Integer, i> requestMap = new ConcurrentHashMap<>();
    private final VexFwkDocDetector$sessionCallback$1 sessionCallback = new VexFwkDocDetector$sessionCallback$1(this);

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J%\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H&¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocDetector$Callback;", "", "Landroid/media/Image;", "image", "Lme/k;", "Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocDetector$DetectResult;", "result", "Lme/x;", "onDetected", "(Landroid/media/Image;Ljava/lang/Object;)V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Callback {
        void onDetected(Image image, Object obj);
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0003¢\u0006\u0004\b\u0007\u0010\bR!\u0010\f\u001a\u00020\t8FX\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\f\u0010\rR'\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8FX\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\u000b\u0012\u0004\b\u0014\u0010\u0003\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0018\u001a\n \u0017*\u0004\u0018\u00010\u00160\u00168\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002XT¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocDetector$Companion;", "", "<init>", "()V", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "metadata", "Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocDetector$DetectResult;", "createDetectResult", "(Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;)Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocDetector$DetectResult;", "", "isAvailable$delegate", "Lme/f;", "isAvailable", "()Z", "isAvailable$annotations", "", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentDetectionType;", "supportedOperationTypes$delegate", "getSupportedOperationTypes", "()Ljava/util/List;", "getSupportedOperationTypes$annotations", "supportedOperationTypes", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "", "STREAM_ID_IMAGE", "I", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final DetectResult createDetectResult(VexFwkMetadataNative vexFwkMetadataNative) {
            int i2;
            if (vexFwkMetadataNative == null) {
                return new DetectResult((VexFwkDocumentCorners) null, 0, 3, (e) null);
            }
            VexFwkDocumentCorners vexFwkDocumentCorners = (VexFwkDocumentCorners) vexFwkMetadataNative.get(VexFwkMetadataKey.DOCUMENT_RECT.INSTANCE);
            Integer num = (Integer) vexFwkMetadataNative.get(VexFwkMetadataKey.DOCUMENT_SCAN_STABILITY.INSTANCE);
            if (num != null) {
                i2 = num.intValue();
            } else {
                i2 = -1;
            }
            return new DetectResult(vexFwkDocumentCorners, i2);
        }

        public final List<VexFwkDocumentDetectionType> getSupportedOperationTypes() {
            return (List) VexFwkDocDetector.supportedOperationTypes$delegate.getValue();
        }

        public final boolean isAvailable() {
            return ((Boolean) VexFwkDocDetector.isAvailable$delegate.getValue()).booleanValue();
        }

        private Companion() {
        }

        public static /* synthetic */ void getSupportedOperationTypes$annotations() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/docscan/VexFwkDocDetector$DetectResult;", "", "corners", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentCorners;", "stability", "", "<init>", "(Lcom/samsung/android/vexfwk/param/VexFwkDocumentCorners;I)V", "getCorners", "()Lcom/samsung/android/vexfwk/param/VexFwkDocumentCorners;", "setCorners", "(Lcom/samsung/android/vexfwk/param/VexFwkDocumentCorners;)V", "getStability", "()I", "setStability", "(I)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DetectResult {
        private VexFwkDocumentCorners corners;
        private int stability;

        public DetectResult() {
            this((VexFwkDocumentCorners) null, 0, 3, (e) null);
        }

        public static /* synthetic */ DetectResult copy$default(DetectResult detectResult, VexFwkDocumentCorners vexFwkDocumentCorners, int i2, int i7, Object obj) {
            if ((i7 & 1) != 0) {
                vexFwkDocumentCorners = detectResult.corners;
            }
            if ((i7 & 2) != 0) {
                i2 = detectResult.stability;
            }
            return detectResult.copy(vexFwkDocumentCorners, i2);
        }

        public final VexFwkDocumentCorners component1() {
            return this.corners;
        }

        public final int component2() {
            return this.stability;
        }

        public final DetectResult copy(VexFwkDocumentCorners vexFwkDocumentCorners, int i2) {
            return new DetectResult(vexFwkDocumentCorners, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DetectResult)) {
                return false;
            }
            DetectResult detectResult = (DetectResult) obj;
            if (j.a(this.corners, detectResult.corners) && this.stability == detectResult.stability) {
                return true;
            }
            return false;
        }

        public final VexFwkDocumentCorners getCorners() {
            return this.corners;
        }

        public final int getStability() {
            return this.stability;
        }

        public int hashCode() {
            int i2;
            VexFwkDocumentCorners vexFwkDocumentCorners = this.corners;
            if (vexFwkDocumentCorners == null) {
                i2 = 0;
            } else {
                i2 = vexFwkDocumentCorners.hashCode();
            }
            return Integer.hashCode(this.stability) + (i2 * 31);
        }

        public final void setCorners(VexFwkDocumentCorners vexFwkDocumentCorners) {
            this.corners = vexFwkDocumentCorners;
        }

        public final void setStability(int i2) {
            this.stability = i2;
        }

        public String toString() {
            VexFwkDocumentCorners vexFwkDocumentCorners = this.corners;
            int i2 = this.stability;
            return "DetectResult(corners=" + vexFwkDocumentCorners + ", stability=" + i2 + ")";
        }

        public DetectResult(VexFwkDocumentCorners vexFwkDocumentCorners, int i2) {
            this.corners = vexFwkDocumentCorners;
            this.stability = i2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ DetectResult(VexFwkDocumentCorners vexFwkDocumentCorners, int i2, int i7, e eVar) {
            this((i7 & 1) != 0 ? null : vexFwkDocumentCorners, (i7 & 2) != 0 ? -1 : i2);
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VexFwkResultCode.values().length];
            try {
                iArr[VexFwkResultCode.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$3(VexFwkDocumentDetectionType vexFwkDocumentDetectionType, VexFwkDocDetector vexFwkDocDetector) {
        j.e(vexFwkDocDetector, "$this$configureWith");
        vexFwkDocDetector.createSession(VexFwkUsecase.DOCUMENT_DETECTION, new a(vexFwkDocumentDetectionType, 1));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$3$lambda$2(VexFwkDocumentDetectionType vexFwkDocumentDetectionType, VexFwkSessionConfigRequest.Builder builder) {
        j.e(builder, "$this$createSession");
        builder.setConfigMetadata((b) new a(vexFwkDocumentDetectionType, 0));
        VexFwkSessionConfigRequest.Builder.addInputStream$default(builder, 0, VexFwkStreamType.BUFFER, VexFwkStreamUsage.IMAGE, 0, 0, 0, (Surface) null, 120, (Object) null);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$3$lambda$2$lambda$1(VexFwkDocumentDetectionType vexFwkDocumentDetectionType, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setConfigMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.SESSION_DOCUMENT_DETECTION_TYPE.INSTANCE, vexFwkDocumentDetectionType);
        return x.f4917a;
    }

    private static final DetectResult createDetectResult(VexFwkMetadataNative vexFwkMetadataNative) {
        return Companion.createDetectResult(vexFwkMetadataNative);
    }

    private final void detectDocumentImpl(Image image, Callback callback) {
        String str = TAG;
        Log.d(str, "detectDocumentImpl E");
        VexFwkSessionRequest.Async build = ((VexFwkSessionRequest.Async.Builder) VexFwkSessionRequestKt.addInputBuffer(new VexFwkSessionRequest.Async.Builder(), 0, image)).setCallback(this.sessionCallback).build();
        try {
            if (WhenMappings.$EnumSwitchMapping$0[getSession(VexFwkUsecase.DOCUMENT_DETECTION).processRequest(build).ordinal()] == 1) {
                this.requestMap.put(Integer.valueOf(build.getRequestFrameNumber()), new i(callback, image));
            } else {
                callback.onDetected(image, a.l(new RuntimeException("Failed to process request")));
            }
            F.u(build, (Throwable) null);
            Log.d(str, "detectDocumentImpl X");
        } catch (Throwable th) {
            F.u(build, th);
            throw th;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0042, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0043, code lost:
        He.F.u(r4, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0046, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0050, code lost:
        He.F.u(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0053, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.samsung.android.vexfwk.sdk.docscan.VexFwkDocDetector.DetectResult detectDocumentImpl$lambda$11(java.lang.Object r3, com.samsung.android.vexfwk.sdk.docscan.VexFwkDocDetector r4) {
        /*
            java.lang.String r0 = TAG
            java.lang.String r1 = "detectDocumentImpl E"
            android.util.Log.d(r0, r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r1 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r1.<init>()
            r2 = 0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r3 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addInputBuffer(r1, (int) r2, r3)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r3 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r3
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r3 = r3.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r1 = com.samsung.android.vexfwk.session.VexFwkUsecase.DOCUMENT_DETECTION     // Catch:{ all -> 0x004d }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r4 = r4.getSession(r1)     // Catch:{ all -> 0x004d }
            java.lang.Object r4 = r4.m60processRequestIoAF18A(r3)     // Catch:{ all -> 0x004d }
            r1 = 0
            He.F.u(r3, r1)
            java.lang.Throwable r3 = me.k.a(r4)
            if (r3 != 0) goto L_0x0047
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r4 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r4
            com.samsung.android.vexfwk.sdk.docscan.VexFwkDocDetector$Companion r3 = Companion     // Catch:{ all -> 0x0040 }
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r2 = r4.getResultMetadata()     // Catch:{ all -> 0x0040 }
            com.samsung.android.vexfwk.sdk.docscan.VexFwkDocDetector$DetectResult r3 = r3.createDetectResult(r2)     // Catch:{ all -> 0x0040 }
            He.F.u(r4, r1)
            java.lang.String r4 = "detectDocumentImpl X"
            android.util.Log.d(r0, r4)
            return r3
        L_0x0040:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0042 }
        L_0x0042:
            r0 = move-exception
            He.F.u(r4, r3)
            throw r0
        L_0x0047:
            java.lang.String r4 = "Failed to process request : "
            c0.C0086a.x(r4, r0, r3)
            throw r3
        L_0x004d:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x004f }
        L_0x004f:
            r0 = move-exception
            He.F.u(r3, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.docscan.VexFwkDocDetector.detectDocumentImpl$lambda$11(java.lang.Object, com.samsung.android.vexfwk.sdk.docscan.VexFwkDocDetector):com.samsung.android.vexfwk.sdk.docscan.VexFwkDocDetector$DetectResult");
    }

    public static final List<VexFwkDocumentDetectionType> getSupportedOperationTypes() {
        return Companion.getSupportedOperationTypes();
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    /* access modifiers changed from: private */
    public static final boolean isAvailable_delegate$lambda$12() {
        return VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.DOCUMENT_DETECTION);
    }

    /* access modifiers changed from: private */
    public static final List supportedOperationTypes_delegate$lambda$14() {
        return (List) VexFwkSdkBase.Companion.getProperties(VexFwkUsecase.DOCUMENT_DETECTION, VexFwkMetadataKey.PROPERTY_DOCUMENT_DETECTION_SUPPORTED_TYPES.INSTANCE, new W.a(24));
    }

    /* access modifiers changed from: private */
    public static final List supportedOperationTypes_delegate$lambda$14$lambda$13() {
        if (Companion.isAvailable()) {
            return C0246a.e0(VexFwkDocumentDetectionType.CAMERA_PREVIEW);
        }
        return C1202t.d;
    }

    public final void configure(VexFwkDocumentDetectionType vexFwkDocumentDetectionType) {
        j.e(vexFwkDocumentDetectionType, "opType");
        Companion companion = Companion;
        if (companion.getSupportedOperationTypes().contains(vexFwkDocumentDetectionType)) {
            this.opType = vexFwkDocumentDetectionType;
            configureWith(this, new a(vexFwkDocumentDetectionType, 2));
            return;
        }
        List<VexFwkDocumentDetectionType> supportedOperationTypes = companion.getSupportedOperationTypes();
        throw new IllegalStateException(("Unsupported operation type " + vexFwkDocumentDetectionType + ", supported types are " + supportedOperationTypes).toString());
    }

    public final void detectDocument(Image image, Callback callback) {
        j.e(image, "image");
        j.e(callback, Profile.PhoneNumberData.TYPE_CALLBACK);
        if (this.opType != VexFwkDocumentDetectionType.CAMERA_PREVIEW) {
            callback.onDetected(image, a.l(new IllegalStateException("Invalid operation type is set in configure")));
        } else {
            detectDocumentImpl(image, callback);
        }
    }

    public final void configure() {
        configure(VexFwkDocumentDetectionType.CAMERA_PREVIEW);
    }

    public final CompletableFuture<DetectResult> detectDocument(Image image) {
        j.e(image, "image");
        if (this.opType == VexFwkDocumentDetectionType.CAMERA_PREVIEW) {
            return detectDocumentImpl(image);
        }
        throw new IllegalStateException("Invalid operation type is set in configure");
    }

    public final CompletableFuture<DetectResult> detectDocument(Bitmap bitmap) {
        j.e(bitmap, "image");
        if (this.opType == VexFwkDocumentDetectionType.STILL_CAPTURE) {
            return detectDocumentImpl(bitmap);
        }
        throw new IllegalStateException("Invalid operation type is set in configure");
    }

    private final <T> CompletableFuture<DetectResult> detectDocumentImpl(T t) {
        CompletableFuture<DetectResult> supplyAsync = CompletableFuture.supplyAsync(new C0726b(9, t, this));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }
}
