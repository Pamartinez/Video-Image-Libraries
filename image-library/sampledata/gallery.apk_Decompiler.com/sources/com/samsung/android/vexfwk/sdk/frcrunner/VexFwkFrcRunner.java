package com.samsung.android.vexfwk.sdk.frcrunner;

import L1.d;
import android.util.Size;
import android.view.Surface;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkFrcStatus;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkStream;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import md.b;
import me.f;
import me.x;
import pd.C1221a;
import pd.C1222b;

@Metadata(d1 = {"\u0000;\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\b*\u0001\u0016\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0007¢\u0006\u0004\b\u0002\u0010\u0003JA\u0010\r\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\r\u0010\u000eJ%\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u000b¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0011\u0010\u001b\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/frcrunner/VexFwkFrcRunner;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "", "format", "width", "height", "Landroid/view/Surface;", "outputSurface", "rotationDegree", "", "inputFrameRate", "configure", "(IIILandroid/view/Surface;IF)Lcom/samsung/android/vexfwk/sdk/frcrunner/VexFwkFrcRunner;", "Lcom/samsung/android/vexfwk/param/VexFwkFrcStatus;", "status", "upsampleFactor", "outputFrameRate", "Lme/x;", "run", "(Lcom/samsung/android/vexfwk/param/VexFwkFrcStatus;IF)V", "com/samsung/android/vexfwk/sdk/frcrunner/VexFwkFrcRunner$sessionCallback$1", "sessionCallback", "Lcom/samsung/android/vexfwk/sdk/frcrunner/VexFwkFrcRunner$sessionCallback$1;", "getInputSurface", "()Landroid/view/Surface;", "inputSurface", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkFrcRunner extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_SURFACE = 0;
    private static final int STREAM_ID_OUTPUT_SURFACE = 1;
    private static final String TAG = "VexFwkFrcRunner";
    /* access modifiers changed from: private */
    public static final f availableUpsampleFactors$delegate = d.q(new b(5));
    /* access modifiers changed from: private */
    public static final f isAvailable$delegate = d.q(new b(4));
    private final VexFwkFrcRunner$sessionCallback$1 sessionCallback = new VexFwkFrcRunner$sessionCallback$1();

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R!\u0010\u0007\u001a\u00020\u00048FX\u0002¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u0012\u0004\b\t\u0010\u0003\u001a\u0004\b\u0007\u0010\bR!\u0010\u000f\u001a\u00020\n8FX\u0002¢\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00108\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002XT¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00148\u0002XT¢\u0006\u0006\n\u0004\b\u0017\u0010\u0016¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/frcrunner/VexFwkFrcRunner$Companion;", "", "<init>", "()V", "", "isAvailable$delegate", "Lme/f;", "isAvailable", "()Z", "isAvailable$annotations", "", "availableUpsampleFactors$delegate", "getAvailableUpsampleFactors", "()[I", "getAvailableUpsampleFactors$annotations", "availableUpsampleFactors", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "", "STREAM_ID_INPUT_SURFACE", "I", "STREAM_ID_OUTPUT_SURFACE", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final int[] getAvailableUpsampleFactors() {
            return (int[]) VexFwkFrcRunner.availableUpsampleFactors$delegate.getValue();
        }

        public final boolean isAvailable() {
            return ((Boolean) VexFwkFrcRunner.isAvailable$delegate.getValue()).booleanValue();
        }

        private Companion() {
        }

        public static /* synthetic */ void getAvailableUpsampleFactors$annotations() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    /* access modifiers changed from: private */
    public static final int[] availableUpsampleFactors_delegate$lambda$10() {
        return (int[]) VexFwkSdkBase.Companion.getProperties(VexFwkUsecase.FRAME_RATE_CONVERSION, VexFwkMetadataKey.PROPERTY_FRC_AVAILABLE_UPSAMPLE_FACTORS.INSTANCE, new e5.d(9));
    }

    /* access modifiers changed from: private */
    public static final int[] availableUpsampleFactors_delegate$lambda$10$lambda$9() {
        return new int[0];
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$3(int i2, int i7, Surface surface, int i8, int i10, float f, VexFwkFrcRunner vexFwkFrcRunner) {
        j.e(vexFwkFrcRunner, "$this$configureWith");
        vexFwkFrcRunner.createSession(VexFwkUsecase.FRAME_RATE_CONVERSION, new C1221a(i2, i7, surface, i8, i10, f, 1));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$3$lambda$2(int i2, int i7, Surface surface, int i8, int i10, float f, VexFwkSessionConfigRequest.Builder builder) {
        VexFwkSessionConfigRequest.Builder builder2 = builder;
        j.e(builder2, "$this$createSession");
        VexFwkStreamType vexFwkStreamType = VexFwkStreamType.SURFACE;
        VexFwkStreamUsage vexFwkStreamUsage = VexFwkStreamUsage.IMAGE;
        int i11 = i2;
        int i12 = i7;
        VexFwkSessionConfigRequest.Builder.addInputStream$default(builder2, 0, vexFwkStreamType, vexFwkStreamUsage, i11, i12, 35, (Surface) null, 64, (Object) null);
        builder2.addOutputStream(1, vexFwkStreamType, vexFwkStreamUsage, i11, i12, 35, surface);
        float f5 = f;
        builder2.setConfigMetadata((Ae.b) new C1222b(f5, i8, i2, i7, i10));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$3$lambda$2$lambda$1(int i2, int i7, int i8, int i10, float f, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setConfigMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.IMAGE_FORMAT.INSTANCE, Integer.valueOf(i2));
        vexFwkMetadataNative.set(VexFwkMetadataKey.IMAGE_SIZE.INSTANCE, new Size(i7, i8));
        vexFwkMetadataNative.set(VexFwkMetadataKey.ROTATION_DEGREE.INSTANCE, Integer.valueOf(i10));
        vexFwkMetadataNative.set(VexFwkMetadataKey.FRAME_RATE.INSTANCE, Float.valueOf(f));
        return x.f4917a;
    }

    public static final int[] getAvailableUpsampleFactors() {
        return Companion.getAvailableUpsampleFactors();
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    /* access modifiers changed from: private */
    public static final boolean isAvailable_delegate$lambda$8() {
        return VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.FRAME_RATE_CONVERSION);
    }

    /* access modifiers changed from: private */
    public static final x run$lambda$5(VexFwkFrcStatus vexFwkFrcStatus, int i2, float f, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.FRC_STATUS.INSTANCE, vexFwkFrcStatus);
        vexFwkMetadataNative.set(VexFwkMetadataKey.UPSAMPLE_FACTOR.INSTANCE, Integer.valueOf(i2));
        vexFwkMetadataNative.set(VexFwkMetadataKey.FRAME_RATE.INSTANCE, Float.valueOf(f));
        return x.f4917a;
    }

    public final VexFwkFrcRunner configure(int i2, int i7, int i8, Surface surface, int i10, float f) {
        return (VexFwkFrcRunner) configureWith(this, new C1221a(i7, i8, surface, i2, i10, f, 0));
    }

    public final Surface getInputSurface() {
        Object obj;
        Iterator it = getSession(VexFwkUsecase.FRAME_RATE_CONVERSION).getStreams().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((VexFwkStream) obj).getId() == 0) {
                break;
            }
        }
        j.b(obj);
        Surface surface = ((VexFwkStream) obj).getSurface();
        j.b(surface);
        return surface;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0045, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0046, code lost:
        He.F.u(r3, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0049, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run(com.samsung.android.vexfwk.param.VexFwkFrcStatus r3, int r4, float r5) {
        /*
            r2 = this;
            java.lang.String r0 = "status"
            kotlin.jvm.internal.j.e(r3, r0)
            com.samsung.android.vexfwk.sdk.frcrunner.VexFwkFrcRunner$Companion r0 = Companion
            int[] r1 = r0.getAvailableUpsampleFactors()
            boolean r1 = ne.C1192j.c0(r4, r1)
            if (r1 == 0) goto L_0x004a
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Async$Builder r0 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Async$Builder
            r0.<init>()
            r1 = 0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r0 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addInputBuffer(r0, r1)
            r1 = 1
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r0 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addOutputBuffer(r0, r1)
            pd.c r1 = new pd.c
            r1.<init>(r3, r4, r5)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r3 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.setSettingMetadata(r0, r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Async$Builder r3 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Async.Builder) r3
            com.samsung.android.vexfwk.sdk.frcrunner.VexFwkFrcRunner$sessionCallback$1 r4 = r2.sessionCallback
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Async$Builder r3 = r3.setCallback(r4)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Async r3 = r3.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r4 = com.samsung.android.vexfwk.session.VexFwkUsecase.FRAME_RATE_CONVERSION     // Catch:{ all -> 0x0043 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r2 = r2.getSession(r4)     // Catch:{ all -> 0x0043 }
            r2.processRequest((com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Async) r3)     // Catch:{ all -> 0x0043 }
            r2 = 0
            He.F.u(r3, r2)
            return
        L_0x0043:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r4 = move-exception
            He.F.u(r3, r2)
            throw r4
        L_0x004a:
            int[] r2 = r0.getAvailableUpsampleFactors()
            java.lang.String r2 = java.util.Arrays.toString(r2)
            java.lang.String r3 = "toString(...)"
            kotlin.jvm.internal.j.d(r2, r3)
            java.lang.String r3 = "Unsupported upsample factor: "
            java.lang.String r5 = ". Available factors are "
            java.lang.String r2 = i.C0212a.k(r4, r3, r5, r2)
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r2 = r2.toString()
            r3.<init>(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.frcrunner.VexFwkFrcRunner.run(com.samsung.android.vexfwk.param.VexFwkFrcStatus, int, float):void");
    }
}
