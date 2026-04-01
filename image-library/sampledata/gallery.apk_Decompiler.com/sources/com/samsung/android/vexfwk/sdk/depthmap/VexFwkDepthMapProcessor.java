package com.samsung.android.vexfwk.sdk.depthmap;

import A8.C0544a;
import L1.d;
import android.graphics.Bitmap;
import android.hardware.HardwareBuffer;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import com.samsung.android.sdk.spage.card.event.Event;
import com.samsung.android.vexfwk.depthmap.DepthDataType;
import com.samsung.android.vexfwk.depthmap.DepthModeType;
import com.samsung.android.vexfwk.hardware.VexFwkHardwareBufferNative;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkDepthMapCapabilities;
import com.samsung.android.vexfwk.param.VexFwkDepthMapParams;
import com.samsung.android.vexfwk.param.VexFwkNodeResultCode;
import com.samsung.android.vexfwk.param.VexFwkOrientation;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkProvider;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionPartialResult;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import md.b;
import me.f;
import me.x;
import ne.C1200r;
import ne.C1202t;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0003\u0015\u0016\u0017B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0000J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bJ\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J \u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "configure", "convertToVexFwkDepthMapParams", "Lcom/samsung/android/vexfwk/param/VexFwkDepthMapParams;", "depthParam", "Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$DepthMapParam;", "getDepthMap", "Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$Result;", "bitmap", "Landroid/graphics/Bitmap;", "depth", "handleProcessResult", "totalResult", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "getDepthInternal", "pipelineName", "", "Companion", "DepthMapParam", "Result", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkDepthMapProcessor extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_IMAGE = 0;
    private static final int STREAM_ID_OUTPUT_DEPTH_MAP = 1;
    /* access modifiers changed from: private */
    public static final String TAG = "VexFwkDepthMapProcessor";
    /* access modifiers changed from: private */
    public static final f isAvailable$delegate = d.q(new b(2));

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\t\u0010\nR!\u0010\u000e\u001a\u00020\u000b8FX\u0002¢\u0006\u0012\n\u0004\b\f\u0010\r\u0012\u0004\b\u0010\u0010\u0003\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0013\u001a\n \u0012*\u0004\u0018\u00010\u00110\u00118\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002XT¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00158\u0002XT¢\u0006\u0006\n\u0004\b\u0018\u0010\u0017¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$Companion;", "", "<init>", "()V", "", "Lcom/samsung/android/vexfwk/depthmap/DepthModeType;", "getSupportedFeatures", "()Ljava/util/List;", "Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$DepthMapParam;", "getDepthParam", "()Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$DepthMapParam;", "", "isAvailable$delegate", "Lme/f;", "isAvailable", "()Z", "isAvailable$annotations", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "", "STREAM_ID_INPUT_IMAGE", "I", "STREAM_ID_OUTPUT_DEPTH_MAP", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final VexFwkDepthMapCapabilities getSupportedFeatures$lambda$1$lambda$0() {
            return new VexFwkDepthMapCapabilities();
        }

        public final DepthMapParam getDepthParam() {
            return new DepthMapParam(DepthModeType.DEPTH_QUALITY_MODE_HIGH, DepthDataType.DEPTH_DATA_TYPE_FLOAT, -1, VexFwkOrientation.ORIENTATION_ROTATE_0);
        }

        public final List<DepthModeType> getSupportedFeatures() {
            try {
                VexFwkDepthMapCapabilities vexFwkDepthMapCapabilities = (VexFwkDepthMapCapabilities) VexFwkProvider.fetchProperties(VexFwkUsecase.DEPTHMAP_PROCESSOR).getOrElse(VexFwkMetadataKey.PROPERTY_DEPTHMAP_CAPABILITIES.INSTANCE, new e5.d(7));
                String access$getTAG$cp = VexFwkDepthMapProcessor.TAG;
                Log.d(access$getTAG$cp, "getSupportedFeatures result: " + vexFwkDepthMapCapabilities);
                return vexFwkDepthMapCapabilities;
            } catch (Exception e) {
                Log.e(VexFwkDepthMapProcessor.TAG, "Failed to get supported features", e);
                return C1202t.d;
            }
        }

        public final boolean isAvailable() {
            return ((Boolean) VexFwkDepthMapProcessor.isAvailable$delegate.getValue()).booleanValue();
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003J1\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0007HÖ\u0001J\t\u0010%\u001a\u00020&HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006'"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$DepthMapParam;", "", "depthMode", "Lcom/samsung/android/vexfwk/depthmap/DepthModeType;", "depthData", "Lcom/samsung/android/vexfwk/depthmap/DepthDataType;", "focalLength", "", "imageOrientation", "Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "<init>", "(Lcom/samsung/android/vexfwk/depthmap/DepthModeType;Lcom/samsung/android/vexfwk/depthmap/DepthDataType;ILcom/samsung/android/vexfwk/param/VexFwkOrientation;)V", "getDepthMode", "()Lcom/samsung/android/vexfwk/depthmap/DepthModeType;", "setDepthMode", "(Lcom/samsung/android/vexfwk/depthmap/DepthModeType;)V", "getDepthData", "()Lcom/samsung/android/vexfwk/depthmap/DepthDataType;", "setDepthData", "(Lcom/samsung/android/vexfwk/depthmap/DepthDataType;)V", "getFocalLength", "()I", "setFocalLength", "(I)V", "getImageOrientation", "()Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "setImageOrientation", "(Lcom/samsung/android/vexfwk/param/VexFwkOrientation;)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DepthMapParam {
        private DepthDataType depthData;
        private DepthModeType depthMode;
        private int focalLength;
        private VexFwkOrientation imageOrientation;

        public DepthMapParam(DepthModeType depthModeType, DepthDataType depthDataType, int i2, VexFwkOrientation vexFwkOrientation) {
            j.e(depthModeType, "depthMode");
            j.e(depthDataType, "depthData");
            j.e(vexFwkOrientation, "imageOrientation");
            this.depthMode = depthModeType;
            this.depthData = depthDataType;
            this.focalLength = i2;
            this.imageOrientation = vexFwkOrientation;
        }

        public static /* synthetic */ DepthMapParam copy$default(DepthMapParam depthMapParam, DepthModeType depthModeType, DepthDataType depthDataType, int i2, VexFwkOrientation vexFwkOrientation, int i7, Object obj) {
            if ((i7 & 1) != 0) {
                depthModeType = depthMapParam.depthMode;
            }
            if ((i7 & 2) != 0) {
                depthDataType = depthMapParam.depthData;
            }
            if ((i7 & 4) != 0) {
                i2 = depthMapParam.focalLength;
            }
            if ((i7 & 8) != 0) {
                vexFwkOrientation = depthMapParam.imageOrientation;
            }
            return depthMapParam.copy(depthModeType, depthDataType, i2, vexFwkOrientation);
        }

        public final DepthModeType component1() {
            return this.depthMode;
        }

        public final DepthDataType component2() {
            return this.depthData;
        }

        public final int component3() {
            return this.focalLength;
        }

        public final VexFwkOrientation component4() {
            return this.imageOrientation;
        }

        public final DepthMapParam copy(DepthModeType depthModeType, DepthDataType depthDataType, int i2, VexFwkOrientation vexFwkOrientation) {
            j.e(depthModeType, "depthMode");
            j.e(depthDataType, "depthData");
            j.e(vexFwkOrientation, "imageOrientation");
            return new DepthMapParam(depthModeType, depthDataType, i2, vexFwkOrientation);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DepthMapParam)) {
                return false;
            }
            DepthMapParam depthMapParam = (DepthMapParam) obj;
            if (this.depthMode == depthMapParam.depthMode && this.depthData == depthMapParam.depthData && this.focalLength == depthMapParam.focalLength && this.imageOrientation == depthMapParam.imageOrientation) {
                return true;
            }
            return false;
        }

        public final DepthDataType getDepthData() {
            return this.depthData;
        }

        public final DepthModeType getDepthMode() {
            return this.depthMode;
        }

        public final int getFocalLength() {
            return this.focalLength;
        }

        public final VexFwkOrientation getImageOrientation() {
            return this.imageOrientation;
        }

        public int hashCode() {
            int hashCode = this.depthData.hashCode();
            return this.imageOrientation.hashCode() + C0212a.b(this.focalLength, (hashCode + (this.depthMode.hashCode() * 31)) * 31, 31);
        }

        public final void setDepthData(DepthDataType depthDataType) {
            j.e(depthDataType, "<set-?>");
            this.depthData = depthDataType;
        }

        public final void setDepthMode(DepthModeType depthModeType) {
            j.e(depthModeType, "<set-?>");
            this.depthMode = depthModeType;
        }

        public final void setFocalLength(int i2) {
            this.focalLength = i2;
        }

        public final void setImageOrientation(VexFwkOrientation vexFwkOrientation) {
            j.e(vexFwkOrientation, "<set-?>");
            this.imageOrientation = vexFwkOrientation;
        }

        public String toString() {
            DepthModeType depthModeType = this.depthMode;
            DepthDataType depthDataType = this.depthData;
            int i2 = this.focalLength;
            VexFwkOrientation vexFwkOrientation = this.imageOrientation;
            return "DepthMapParam(depthMode=" + depthModeType + ", depthData=" + depthDataType + ", focalLength=" + i2 + ", imageOrientation=" + vexFwkOrientation + ")";
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$Result;", "", "<init>", "()V", "IntResult", "ByteResult", "FloatResult", "Error", "Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$Result$ByteResult;", "Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$Result$Error;", "Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$Result$FloatResult;", "Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$Result$IntResult;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Result {

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$Result$ByteResult;", "Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$Result;", "depthMap", "", "size", "Landroid/util/Size;", "<init>", "([BLandroid/util/Size;)V", "getDepthMap", "()[B", "getSize", "()Landroid/util/Size;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class ByteResult extends Result {
            private final byte[] depthMap;
            private final Size size;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ByteResult(byte[] bArr, Size size2) {
                super((e) null);
                j.e(bArr, "depthMap");
                j.e(size2, "size");
                this.depthMap = bArr;
                this.size = size2;
            }

            public static /* synthetic */ ByteResult copy$default(ByteResult byteResult, byte[] bArr, Size size2, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    bArr = byteResult.depthMap;
                }
                if ((i2 & 2) != 0) {
                    size2 = byteResult.size;
                }
                return byteResult.copy(bArr, size2);
            }

            public final byte[] component1() {
                return this.depthMap;
            }

            public final Size component2() {
                return this.size;
            }

            public final ByteResult copy(byte[] bArr, Size size2) {
                j.e(bArr, "depthMap");
                j.e(size2, "size");
                return new ByteResult(bArr, size2);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof ByteResult)) {
                    return false;
                }
                ByteResult byteResult = (ByteResult) obj;
                if (j.a(this.depthMap, byteResult.depthMap) && j.a(this.size, byteResult.size)) {
                    return true;
                }
                return false;
            }

            public final byte[] getDepthMap() {
                return this.depthMap;
            }

            public final Size getSize() {
                return this.size;
            }

            public int hashCode() {
                return this.size.hashCode() + (Arrays.hashCode(this.depthMap) * 31);
            }

            public String toString() {
                String arrays = Arrays.toString(this.depthMap);
                Size size2 = this.size;
                return "ByteResult(depthMap=" + arrays + ", size=" + size2 + ")";
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$Result$Error;", "Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$Result;", "returnCode", "Lcom/samsung/android/vexfwk/param/VexFwkNodeResultCode;", "<init>", "(Lcom/samsung/android/vexfwk/param/VexFwkNodeResultCode;)V", "getReturnCode", "()Lcom/samsung/android/vexfwk/param/VexFwkNodeResultCode;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
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

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$Result$FloatResult;", "Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$Result;", "depthMap", "", "size", "Landroid/util/Size;", "<init>", "([FLandroid/util/Size;)V", "getDepthMap", "()[F", "getSize", "()Landroid/util/Size;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class FloatResult extends Result {
            private final float[] depthMap;
            private final Size size;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public FloatResult(float[] fArr, Size size2) {
                super((e) null);
                j.e(fArr, "depthMap");
                j.e(size2, "size");
                this.depthMap = fArr;
                this.size = size2;
            }

            public static /* synthetic */ FloatResult copy$default(FloatResult floatResult, float[] fArr, Size size2, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    fArr = floatResult.depthMap;
                }
                if ((i2 & 2) != 0) {
                    size2 = floatResult.size;
                }
                return floatResult.copy(fArr, size2);
            }

            public final float[] component1() {
                return this.depthMap;
            }

            public final Size component2() {
                return this.size;
            }

            public final FloatResult copy(float[] fArr, Size size2) {
                j.e(fArr, "depthMap");
                j.e(size2, "size");
                return new FloatResult(fArr, size2);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof FloatResult)) {
                    return false;
                }
                FloatResult floatResult = (FloatResult) obj;
                if (j.a(this.depthMap, floatResult.depthMap) && j.a(this.size, floatResult.size)) {
                    return true;
                }
                return false;
            }

            public final float[] getDepthMap() {
                return this.depthMap;
            }

            public final Size getSize() {
                return this.size;
            }

            public int hashCode() {
                return this.size.hashCode() + (Arrays.hashCode(this.depthMap) * 31);
            }

            public String toString() {
                String arrays = Arrays.toString(this.depthMap);
                Size size2 = this.size;
                return "FloatResult(depthMap=" + arrays + ", size=" + size2 + ")";
            }
        }

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$Result$IntResult;", "Lcom/samsung/android/vexfwk/sdk/depthmap/VexFwkDepthMapProcessor$Result;", "depthMap", "", "size", "Landroid/util/Size;", "<init>", "([ILandroid/util/Size;)V", "getDepthMap", "()[I", "getSize", "()Landroid/util/Size;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class IntResult extends Result {
            private final int[] depthMap;
            private final Size size;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public IntResult(int[] iArr, Size size2) {
                super((e) null);
                j.e(iArr, "depthMap");
                j.e(size2, "size");
                this.depthMap = iArr;
                this.size = size2;
            }

            public static /* synthetic */ IntResult copy$default(IntResult intResult, int[] iArr, Size size2, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    iArr = intResult.depthMap;
                }
                if ((i2 & 2) != 0) {
                    size2 = intResult.size;
                }
                return intResult.copy(iArr, size2);
            }

            public final int[] component1() {
                return this.depthMap;
            }

            public final Size component2() {
                return this.size;
            }

            public final IntResult copy(int[] iArr, Size size2) {
                j.e(iArr, "depthMap");
                j.e(size2, "size");
                return new IntResult(iArr, size2);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof IntResult)) {
                    return false;
                }
                IntResult intResult = (IntResult) obj;
                if (j.a(this.depthMap, intResult.depthMap) && j.a(this.size, intResult.size)) {
                    return true;
                }
                return false;
            }

            public final int[] getDepthMap() {
                return this.depthMap;
            }

            public final Size getSize() {
                return this.size;
            }

            public int hashCode() {
                return this.size.hashCode() + (Arrays.hashCode(this.depthMap) * 31);
            }

            public String toString() {
                String arrays = Arrays.toString(this.depthMap);
                Size size2 = this.size;
                return "IntResult(depthMap=" + arrays + ", size=" + size2 + ")";
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

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.samsung.android.vexfwk.depthmap.DepthDataType[] r0 = com.samsung.android.vexfwk.depthmap.DepthDataType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.samsung.android.vexfwk.depthmap.DepthDataType r1 = com.samsung.android.vexfwk.depthmap.DepthDataType.DEPTH_DATA_TYPE_BYTE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.samsung.android.vexfwk.depthmap.DepthDataType r1 = com.samsung.android.vexfwk.depthmap.DepthDataType.DEPTH_DATA_TYPE_FLOAT     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.depthmap.VexFwkDepthMapProcessor.WhenMappings.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1(VexFwkDepthMapProcessor vexFwkDepthMapProcessor) {
        j.e(vexFwkDepthMapProcessor, "$this$configureWith");
        vexFwkDepthMapProcessor.createSession(VexFwkUsecase.DEPTHMAP_PROCESSOR, new com.samsung.android.vexfwk.sdk.docscan.b(6));
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

    private final VexFwkDepthMapParams convertToVexFwkDepthMapParams(DepthMapParam depthMapParam) {
        return new VexFwkDepthMapParams(depthMapParam.getDepthMode(), depthMapParam.getDepthData(), depthMapParam.getFocalLength(), depthMapParam.getImageOrientation());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0048, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0049, code lost:
        He.F.u(r5, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004c, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
        He.F.u(r3, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0053, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.samsung.android.vexfwk.sdk.depthmap.VexFwkDepthMapProcessor.Result getDepthInternal(android.graphics.Bitmap r3, com.samsung.android.vexfwk.sdk.depthmap.VexFwkDepthMapProcessor.DepthMapParam r4, java.lang.String r5) {
        /*
            r2 = this;
            com.samsung.android.vexfwk.param.VexFwkDepthMapParams r4 = r2.convertToVexFwkDepthMapParams(r4)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r0 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r0.<init>()
            r1 = 0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r3 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addInputBuffer(r0, (int) r1, (android.graphics.Bitmap) r3)
            Wf.c r0 = new Wf.c
            r1 = 12
            r0.<init>(r1, r4, r5)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r3 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.setSettingMetadata(r3, r0)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r3 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r3
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r3 = r3.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r5 = com.samsung.android.vexfwk.session.VexFwkUsecase.DEPTHMAP_PROCESSOR     // Catch:{ all -> 0x004d }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r5 = r2.getSession(r5)     // Catch:{ all -> 0x004d }
            java.lang.Object r5 = r5.m60processRequestIoAF18A(r3)     // Catch:{ all -> 0x004d }
            r0 = 0
            He.F.u(r3, r0)
            L2.a.A(r5)
            java.lang.AutoCloseable r5 = (java.lang.AutoCloseable) r5
            r3 = r5
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r3 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r3     // Catch:{ all -> 0x0046 }
            com.samsung.android.vexfwk.sdk.depthmap.VexFwkDepthMapProcessor$Result r2 = r2.handleProcessResult(r3, r4)     // Catch:{ all -> 0x0046 }
            He.F.u(r5, r0)
            if (r2 != 0) goto L_0x0045
            com.samsung.android.vexfwk.sdk.depthmap.VexFwkDepthMapProcessor$Result$Error r2 = new com.samsung.android.vexfwk.sdk.depthmap.VexFwkDepthMapProcessor$Result$Error
            com.samsung.android.vexfwk.param.VexFwkNodeResultCode r3 = com.samsung.android.vexfwk.param.VexFwkNodeResultCode.PROCESS_FAIL
            r2.<init>(r3)
        L_0x0045:
            return r2
        L_0x0046:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r3 = move-exception
            He.F.u(r5, r2)
            throw r3
        L_0x004d:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x004f }
        L_0x004f:
            r4 = move-exception
            He.F.u(r3, r2)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.depthmap.VexFwkDepthMapProcessor.getDepthInternal(android.graphics.Bitmap, com.samsung.android.vexfwk.sdk.depthmap.VexFwkDepthMapProcessor$DepthMapParam, java.lang.String):com.samsung.android.vexfwk.sdk.depthmap.VexFwkDepthMapProcessor$Result");
    }

    /* access modifiers changed from: private */
    public static final x getDepthInternal$lambda$5(VexFwkDepthMapParams vexFwkDepthMapParams, String str, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.DEPTHMAP_PARAMS.INSTANCE, vexFwkDepthMapParams);
        vexFwkMetadataNative.set(VexFwkMetadataKey.PIPELINE_NAME.INSTANCE, str);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final Result getDepthMap$lambda$2(VexFwkDepthMapProcessor vexFwkDepthMapProcessor, Bitmap bitmap, DepthMapParam depthMapParam) {
        String str = TAG;
        Log.d(str, "E");
        try {
            Result depthInternal = vexFwkDepthMapProcessor.getDepthInternal(bitmap, depthMapParam, Event.DEFAULT_EVENT_TYPE);
            if (depthInternal == null) {
                Log.e(str, "Failed to get depth map");
                return new Result.Error(VexFwkNodeResultCode.PROCESS_FAIL);
            }
            Log.d(str, "X");
            return depthInternal;
        } catch (Exception e) {
            Log.e(TAG, "Failed to get depth map", e);
            return new Result.Error(VexFwkNodeResultCode.PROCESS_FAIL);
        }
    }

    public static final DepthMapParam getDepthParam() {
        return Companion.getDepthParam();
    }

    public static final List<DepthModeType> getSupportedFeatures() {
        return Companion.getSupportedFeatures();
    }

    private final Result handleProcessResult(VexFwkSessionTotalResult vexFwkSessionTotalResult, VexFwkDepthMapParams vexFwkDepthMapParams) {
        Object obj;
        VexFwkNodeResultCode vexFwkNodeResultCode = (VexFwkNodeResultCode) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.RESULT_CODE.INSTANCE);
        if (vexFwkNodeResultCode == null) {
            vexFwkNodeResultCode = VexFwkNodeResultCode.PROCESS_FAIL;
        }
        if (vexFwkNodeResultCode != VexFwkNodeResultCode.OK) {
            Log.e(TAG, "Failed to get depth map, result code: " + vexFwkNodeResultCode);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (VexFwkSessionPartialResult outputBuffers : vexFwkSessionTotalResult.getPartialResults()) {
            C1200r.A0(outputBuffers.getOutputBuffers(), arrayList);
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((VexFwkBuffer) obj).getStreamId() == 1) {
                break;
            }
        }
        VexFwkBuffer vexFwkBuffer = (VexFwkBuffer) obj;
        if (vexFwkBuffer == null) {
            Log.e(TAG, "Output buffer for depth map not found");
            return null;
        }
        HardwareBuffer hardwareBuffer = vexFwkBuffer.getHardwareBuffer();
        try {
            Size size = new Size(hardwareBuffer.getWidth(), hardwareBuffer.getHeight());
            int i2 = WhenMappings.$EnumSwitchMapping$0[vexFwkDepthMapParams.getDepthData().ordinal()];
            if (i2 == 1) {
                return new Result.IntResult(VexFwkHardwareBufferNative.Companion.convertToArgbIntArray(hardwareBuffer), new Size(hardwareBuffer.getWidth(), hardwareBuffer.getHeight()));
            }
            if (i2 == 2) {
                return new Result.FloatResult(VexFwkHardwareBufferNative.Companion.convertToFloatArray(hardwareBuffer), size);
            }
            Log.w(TAG, "Unsupported depth data type: " + vexFwkDepthMapParams.getDepthData() + ", returning null.");
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Error converting hardware buffer to desired type", e);
            return null;
        }
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    /* access modifiers changed from: private */
    public static final boolean isAvailable_delegate$lambda$8() {
        boolean isAvailable = VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.DEPTHMAP_PROCESSOR);
        String str = TAG;
        Log.d(str, "isAvailable result: " + isAvailable);
        return isAvailable;
    }

    public final VexFwkDepthMapProcessor configure() {
        return (VexFwkDepthMapProcessor) configureWith(this, new com.samsung.android.vexfwk.sdk.docscan.b(7));
    }

    public final CompletableFuture<Result> getDepthMap(Bitmap bitmap, DepthMapParam depthMapParam) {
        j.e(bitmap, "bitmap");
        j.e(depthMapParam, "depth");
        CompletableFuture<Result> supplyAsync = CompletableFuture.supplyAsync(new C0544a(this, bitmap, depthMapParam, 5));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }
}
