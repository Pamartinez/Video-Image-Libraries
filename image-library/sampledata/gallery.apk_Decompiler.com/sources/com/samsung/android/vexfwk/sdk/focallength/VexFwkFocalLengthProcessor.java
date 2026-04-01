package com.samsung.android.vexfwk.sdk.focallength;

import A8.C0544a;
import L1.d;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Surface;
import com.samsung.android.sdk.spage.card.event.Event;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.FocalLengthModeType;
import com.samsung.android.vexfwk.param.VexFwkFocalLengthCapabilities;
import com.samsung.android.vexfwk.param.VexFwkFocalLengthParams;
import com.samsung.android.vexfwk.param.VexFwkNodeResultCode;
import com.samsung.android.vexfwk.param.VexFwkOrientation;
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
import ne.C1202t;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0003\u0016\u0017\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0000J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J \u0010\u0013\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/focallength/VexFwkFocalLengthProcessor;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "configure", "convertToVexFwkFocalLengthParams", "Lcom/samsung/android/vexfwk/param/VexFwkFocalLengthParams;", "focalParam", "Lcom/samsung/android/vexfwk/sdk/focallength/VexFwkFocalLengthProcessor$FocalLengthParam;", "getFocalLength", "Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/sdk/focallength/VexFwkFocalLengthProcessor$Result;", "bitmap", "Landroid/graphics/Bitmap;", "focal", "handleProcessResult", "", "totalResult", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "getFocalLengthInternal", "pipelineName", "", "Companion", "FocalLengthParam", "Result", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkFocalLengthProcessor extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_IMAGE = 0;
    /* access modifiers changed from: private */
    public static final String TAG = "VexFwkFocalLengthProcessor";
    /* access modifiers changed from: private */
    public static final f isAvailable$delegate = d.q(new b(3));

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\t\u0010\nR!\u0010\u000e\u001a\u00020\u000b8FX\u0002¢\u0006\u0012\n\u0004\b\f\u0010\r\u0012\u0004\b\u0010\u0010\u0003\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0013\u001a\n \u0012*\u0004\u0018\u00010\u00110\u00118\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002XT¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/focallength/VexFwkFocalLengthProcessor$Companion;", "", "<init>", "()V", "", "Lcom/samsung/android/vexfwk/param/FocalLengthModeType;", "getSupportedFeatures", "()Ljava/util/List;", "Lcom/samsung/android/vexfwk/sdk/focallength/VexFwkFocalLengthProcessor$FocalLengthParam;", "getFocalLengthParam", "()Lcom/samsung/android/vexfwk/sdk/focallength/VexFwkFocalLengthProcessor$FocalLengthParam;", "", "isAvailable$delegate", "Lme/f;", "isAvailable", "()Z", "isAvailable$annotations", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "", "STREAM_ID_INPUT_IMAGE", "I", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final VexFwkFocalLengthCapabilities getSupportedFeatures$lambda$1$lambda$0() {
            return new VexFwkFocalLengthCapabilities();
        }

        public final FocalLengthParam getFocalLengthParam() {
            return new FocalLengthParam(VexFwkOrientation.ORIENTATION_ROTATE_0);
        }

        public final List<FocalLengthModeType> getSupportedFeatures() {
            try {
                VexFwkFocalLengthCapabilities vexFwkFocalLengthCapabilities = (VexFwkFocalLengthCapabilities) VexFwkProvider.fetchProperties(VexFwkUsecase.FOCALLENGTH_PROCESSOR).getOrElse(VexFwkMetadataKey.PROPERTY_FOCAL_LENGTH_CAPABILITIES.INSTANCE, new e5.d(8));
                String access$getTAG$cp = VexFwkFocalLengthProcessor.TAG;
                String R02 = C1194l.R0(vexFwkFocalLengthCapabilities, (String) null, (String) null, (String) null, (Ae.b) null, 63);
                Log.d(access$getTAG$cp, "getSupportedFeatures capabilities : " + R02);
                String access$getTAG$cp2 = VexFwkFocalLengthProcessor.TAG;
                Log.d(access$getTAG$cp2, "getSupportedFeatures result: " + vexFwkFocalLengthCapabilities);
                String access$getTAG$cp3 = VexFwkFocalLengthProcessor.TAG;
                String R03 = C1194l.R0(vexFwkFocalLengthCapabilities, (String) null, (String) null, (String) null, (Ae.b) null, 63);
                Log.d(access$getTAG$cp3, "getSupportedFeatures returning list: " + R03);
                return vexFwkFocalLengthCapabilities;
            } catch (Exception e) {
                Log.e(VexFwkFocalLengthProcessor.TAG, "Failed to get supported features", e);
                return C1202t.d;
            }
        }

        public final boolean isAvailable() {
            return ((Boolean) VexFwkFocalLengthProcessor.isAvailable$delegate.getValue()).booleanValue();
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/focallength/VexFwkFocalLengthProcessor$FocalLengthParam;", "", "imageOrientation", "Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "<init>", "(Lcom/samsung/android/vexfwk/param/VexFwkOrientation;)V", "getImageOrientation", "()Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "setImageOrientation", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FocalLengthParam {
        private VexFwkOrientation imageOrientation;

        public FocalLengthParam(VexFwkOrientation vexFwkOrientation) {
            j.e(vexFwkOrientation, "imageOrientation");
            this.imageOrientation = vexFwkOrientation;
        }

        public static /* synthetic */ FocalLengthParam copy$default(FocalLengthParam focalLengthParam, VexFwkOrientation vexFwkOrientation, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                vexFwkOrientation = focalLengthParam.imageOrientation;
            }
            return focalLengthParam.copy(vexFwkOrientation);
        }

        public final VexFwkOrientation component1() {
            return this.imageOrientation;
        }

        public final FocalLengthParam copy(VexFwkOrientation vexFwkOrientation) {
            j.e(vexFwkOrientation, "imageOrientation");
            return new FocalLengthParam(vexFwkOrientation);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof FocalLengthParam) && this.imageOrientation == ((FocalLengthParam) obj).imageOrientation) {
                return true;
            }
            return false;
        }

        public final VexFwkOrientation getImageOrientation() {
            return this.imageOrientation;
        }

        public int hashCode() {
            return this.imageOrientation.hashCode();
        }

        public final void setImageOrientation(VexFwkOrientation vexFwkOrientation) {
            j.e(vexFwkOrientation, "<set-?>");
            this.imageOrientation = vexFwkOrientation;
        }

        public String toString() {
            VexFwkOrientation vexFwkOrientation = this.imageOrientation;
            return "FocalLengthParam(imageOrientation=" + vexFwkOrientation + ")";
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/focallength/VexFwkFocalLengthProcessor$Result;", "", "<init>", "()V", "Success", "Error", "Lcom/samsung/android/vexfwk/sdk/focallength/VexFwkFocalLengthProcessor$Result$Error;", "Lcom/samsung/android/vexfwk/sdk/focallength/VexFwkFocalLengthProcessor$Result$Success;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Result {

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/focallength/VexFwkFocalLengthProcessor$Result$Error;", "Lcom/samsung/android/vexfwk/sdk/focallength/VexFwkFocalLengthProcessor$Result;", "returnCode", "Lcom/samsung/android/vexfwk/param/VexFwkNodeResultCode;", "<init>", "(Lcom/samsung/android/vexfwk/param/VexFwkNodeResultCode;)V", "getReturnCode", "()Lcom/samsung/android/vexfwk/param/VexFwkNodeResultCode;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Error extends Result {
            private final VexFwkNodeResultCode returnCode;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Error(VexFwkNodeResultCode vexFwkNodeResultCode) {
                super((e) null);
                j.e(vexFwkNodeResultCode, "returnCode");
                this.returnCode = vexFwkNodeResultCode;
            }

            public static /* synthetic */ Error copy$default(Error error, VexFwkNodeResultCode vexFwkNodeResultCode, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    vexFwkNodeResultCode = error.returnCode;
                }
                return error.copy(vexFwkNodeResultCode);
            }

            public final VexFwkNodeResultCode component1() {
                return this.returnCode;
            }

            public final Error copy(VexFwkNodeResultCode vexFwkNodeResultCode) {
                j.e(vexFwkNodeResultCode, "returnCode");
                return new Error(vexFwkNodeResultCode);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if ((obj instanceof Error) && this.returnCode == ((Error) obj).returnCode) {
                    return true;
                }
                return false;
            }

            public final VexFwkNodeResultCode getReturnCode() {
                return this.returnCode;
            }

            public int hashCode() {
                return this.returnCode.hashCode();
            }

            public String toString() {
                VexFwkNodeResultCode vexFwkNodeResultCode = this.returnCode;
                return "Error(returnCode=" + vexFwkNodeResultCode + ")";
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/focallength/VexFwkFocalLengthProcessor$Result$Success;", "Lcom/samsung/android/vexfwk/sdk/focallength/VexFwkFocalLengthProcessor$Result;", "focalLength", "", "<init>", "(F)V", "getFocalLength", "()F", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Success extends Result {
            private final float focalLength;

            public Success(float f) {
                super((e) null);
                this.focalLength = f;
            }

            public static /* synthetic */ Success copy$default(Success success, float f, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    f = success.focalLength;
                }
                return success.copy(f);
            }

            public final float component1() {
                return this.focalLength;
            }

            public final Success copy(float f) {
                return new Success(f);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if ((obj instanceof Success) && Float.compare(this.focalLength, ((Success) obj).focalLength) == 0) {
                    return true;
                }
                return false;
            }

            public final float getFocalLength() {
                return this.focalLength;
            }

            public int hashCode() {
                return Float.hashCode(this.focalLength);
            }

            public String toString() {
                float f = this.focalLength;
                return "Success(focalLength=" + f + ")";
            }
        }

        public /* synthetic */ Result(e eVar) {
            this();
        }

        private Result() {
        }
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1(VexFwkFocalLengthProcessor vexFwkFocalLengthProcessor) {
        j.e(vexFwkFocalLengthProcessor, "$this$configureWith");
        String str = TAG;
        Log.d(str, "E");
        vexFwkFocalLengthProcessor.createSession(VexFwkUsecase.FOCALLENGTH_PROCESSOR, new com.samsung.android.vexfwk.sdk.docscan.b(9));
        Log.d(str, "X");
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1$lambda$0(VexFwkSessionConfigRequest.Builder builder) {
        j.e(builder, "$this$createSession");
        VexFwkSessionConfigRequest.Builder.addInputStream$default(builder, 0, VexFwkStreamType.BUFFER, VexFwkStreamUsage.IMAGE, 0, 0, 0, (Surface) null, 120, (Object) null);
        return x.f4917a;
    }

    private final VexFwkFocalLengthParams convertToVexFwkFocalLengthParams(FocalLengthParam focalLengthParam) {
        return new VexFwkFocalLengthParams(focalLengthParam.getImageOrientation());
    }

    /* access modifiers changed from: private */
    public static final Result getFocalLength$lambda$2(VexFwkFocalLengthProcessor vexFwkFocalLengthProcessor, Bitmap bitmap, FocalLengthParam focalLengthParam) {
        String str = TAG;
        Log.d(str, "E");
        float focalLengthInternal = vexFwkFocalLengthProcessor.getFocalLengthInternal(bitmap, focalLengthParam, Event.DEFAULT_EVENT_TYPE);
        Log.d(str, "focalLength : " + focalLengthInternal);
        Log.d(str, "X");
        return new Result.Success(focalLengthInternal);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0040, code lost:
        He.F.u(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0043, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0046, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0047, code lost:
        He.F.u(r3, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004a, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final float getFocalLengthInternal(android.graphics.Bitmap r3, com.samsung.android.vexfwk.sdk.focallength.VexFwkFocalLengthProcessor.FocalLengthParam r4, java.lang.String r5) {
        /*
            r2 = this;
            com.samsung.android.vexfwk.param.VexFwkFocalLengthParams r4 = r2.convertToVexFwkFocalLengthParams(r4)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r0 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r0.<init>()
            r1 = 0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r3 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addInputBuffer(r0, (int) r1, (android.graphics.Bitmap) r3)
            Wf.c r0 = new Wf.c
            r1 = 13
            r0.<init>(r1, r4, r5)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r3 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.setSettingMetadata(r3, r0)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r3 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r3
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r3 = r3.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r4 = com.samsung.android.vexfwk.session.VexFwkUsecase.FOCALLENGTH_PROCESSOR     // Catch:{ all -> 0x0044 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r4 = r2.getSession(r4)     // Catch:{ all -> 0x0044 }
            java.lang.Object r4 = r4.m60processRequestIoAF18A(r3)     // Catch:{ all -> 0x0044 }
            r5 = 0
            He.F.u(r3, r5)
            L2.a.A(r4)
            java.lang.AutoCloseable r4 = (java.lang.AutoCloseable) r4
            r3 = r4
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r3 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r3     // Catch:{ all -> 0x003d }
            float r2 = r2.handleProcessResult(r3)     // Catch:{ all -> 0x003d }
            He.F.u(r4, r5)
            return r2
        L_0x003d:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x003f }
        L_0x003f:
            r3 = move-exception
            He.F.u(r4, r2)
            throw r3
        L_0x0044:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0046 }
        L_0x0046:
            r4 = move-exception
            He.F.u(r3, r2)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.focallength.VexFwkFocalLengthProcessor.getFocalLengthInternal(android.graphics.Bitmap, com.samsung.android.vexfwk.sdk.focallength.VexFwkFocalLengthProcessor$FocalLengthParam, java.lang.String):float");
    }

    /* access modifiers changed from: private */
    public static final x getFocalLengthInternal$lambda$5(VexFwkFocalLengthParams vexFwkFocalLengthParams, String str, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.FOCALLENGTH_PARAMS.INSTANCE, vexFwkFocalLengthParams);
        vexFwkMetadataNative.set(VexFwkMetadataKey.PIPELINE_NAME.INSTANCE, str);
        return x.f4917a;
    }

    public static final FocalLengthParam getFocalLengthParam() {
        return Companion.getFocalLengthParam();
    }

    public static final List<FocalLengthModeType> getSupportedFeatures() {
        return Companion.getSupportedFeatures();
    }

    private final float handleProcessResult(VexFwkSessionTotalResult vexFwkSessionTotalResult) {
        if (vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.RESULT_CODE.INSTANCE) == VexFwkNodeResultCode.OK) {
            Float f = (Float) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.FOCALLENGTH_RESULT.INSTANCE);
            if (f != null) {
                return f.floatValue();
            }
            throw new IllegalStateException("Focal length result not found in metadata");
        }
        throw new IllegalStateException("Failed to get focal length");
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    /* access modifiers changed from: private */
    public static final boolean isAvailable_delegate$lambda$8() {
        boolean isAvailable = VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.FOCALLENGTH_PROCESSOR);
        String str = TAG;
        Log.d(str, "isAvailable result: " + isAvailable);
        return isAvailable;
    }

    public final VexFwkFocalLengthProcessor configure() {
        return (VexFwkFocalLengthProcessor) configureWith(this, new com.samsung.android.vexfwk.sdk.docscan.b(10));
    }

    public final CompletableFuture<Result> getFocalLength(Bitmap bitmap, FocalLengthParam focalLengthParam) {
        j.e(bitmap, "bitmap");
        j.e(focalLengthParam, "focal");
        CompletableFuture<Result> supplyAsync = CompletableFuture.supplyAsync(new C0544a(this, bitmap, focalLengthParam, 6));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }
}
