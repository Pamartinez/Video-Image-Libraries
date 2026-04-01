package com.samsung.android.vexfwk.sdk.bokeheffect;

import Ad.C0721b;
import L1.d;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Surface;
import com.samsung.android.sdk.spage.card.event.Event;
import com.samsung.android.vexfwk.Bokeheffect.BokehEffectType;
import com.samsung.android.vexfwk.hardware.VexFwkHardwareBufferNative;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkBokehEffectFeature;
import com.samsung.android.vexfwk.param.VexFwkBokehEffectParamsV1;
import com.samsung.android.vexfwk.param.VexFwkNodeResultCode;
import com.samsung.android.vexfwk.param.VexFwkOrientation;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkBuffer;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionPartialResult;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import md.a;
import md.b;
import me.f;
import me.i;
import me.x;
import ne.C1200r;
import ne.C1202t;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0003\u001d\u001e\u001fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ%\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ5\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u000b2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0000¢\u0006\u0004\b\u0016\u0010\u0017J#\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u00182\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0004¢\u0006\u0004\b\u0019\u0010\u001aJ#\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00182\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0004¢\u0006\u0004\b\u001c\u0010\u001a¨\u0006 "}, d2 = {"Lcom/samsung/android/vexfwk/sdk/bokeheffect/VexFwkPortraitBokehEffect;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "Lcom/samsung/android/vexfwk/sdk/bokeheffect/VexFwkPortraitBokehEffect$BokehEffect;", "bokehEffect", "Lcom/samsung/android/vexfwk/param/VexFwkBokehEffectParamsV1;", "convertToBokehEffectParams", "(Lcom/samsung/android/vexfwk/sdk/bokeheffect/VexFwkPortraitBokehEffect$BokehEffect;)Lcom/samsung/android/vexfwk/param/VexFwkBokehEffectParamsV1;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "totalResult", "Lme/i;", "Landroid/graphics/Bitmap;", "", "handleProcessResult", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;)Lme/i;", "bitmap", "effect", "", "pipelineName", "applyBokehEffectInternal", "(Landroid/graphics/Bitmap;Lcom/samsung/android/vexfwk/sdk/bokeheffect/VexFwkPortraitBokehEffect$BokehEffect;Ljava/lang/String;)Lme/i;", "configure", "()Lcom/samsung/android/vexfwk/sdk/bokeheffect/VexFwkPortraitBokehEffect;", "Ljava/util/concurrent/CompletableFuture;", "applyEffect", "(Landroid/graphics/Bitmap;Lcom/samsung/android/vexfwk/sdk/bokeheffect/VexFwkPortraitBokehEffect$BokehEffect;)Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/sdk/bokeheffect/VexFwkPortraitBokehEffect$Result;", "applyEffectWithSegMapOutput", "Companion", "BokehEffect", "Result", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkPortraitBokehEffect extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final String MIN_AVAILABLE_VERSION = "1";
    private static final int STREAM_ID_INPUT_IMAGE = 0;
    private static final int STREAM_ID_OUTPUT_IMAGE = 1;
    private static final int STREAM_ID_OUTPUT_SEGMENTATION_MAP = 2;
    private static final String TAG = "VexFwkPortraitBokehEffect";
    /* access modifiers changed from: private */
    public static final f bokehEffectVersion$delegate = d.q(new C0721b(29));
    /* access modifiers changed from: private */
    public static final f isAvailable$delegate = d.q(new b(0));
    /* access modifiers changed from: private */
    public static final f isSegmentationMapSupported$delegate = d.q(new b(1));

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0001\u0005¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/bokeheffect/VexFwkPortraitBokehEffect$BokehEffect;", "", "<init>", "()V", "BokehLens", "Lcom/samsung/android/vexfwk/sdk/bokeheffect/VexFwkPortraitBokehEffect$BokehEffect$BokehLens;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class BokehEffect {

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001c\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\t\u0010#\u001a\u00020\nHÆ\u0003J;\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010%\u001a\u00020\n2\b\u0010&\u001a\u0004\u0018\u00010'HÖ\u0003J\t\u0010(\u001a\u00020\u0005HÖ\u0001J\t\u0010)\u001a\u00020*HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006+"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/bokeheffect/VexFwkPortraitBokehEffect$BokehEffect$BokehLens;", "Lcom/samsung/android/vexfwk/sdk/bokeheffect/VexFwkPortraitBokehEffect$BokehEffect;", "effectTypeId", "Lcom/samsung/android/vexfwk/Bokeheffect/BokehEffectType;", "effectLevel", "", "reLightLevel", "imageOrientation", "Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "saveAsFlip", "", "<init>", "(Lcom/samsung/android/vexfwk/Bokeheffect/BokehEffectType;IILcom/samsung/android/vexfwk/param/VexFwkOrientation;Z)V", "getEffectTypeId", "()Lcom/samsung/android/vexfwk/Bokeheffect/BokehEffectType;", "setEffectTypeId", "(Lcom/samsung/android/vexfwk/Bokeheffect/BokehEffectType;)V", "getEffectLevel", "()I", "setEffectLevel", "(I)V", "getReLightLevel", "setReLightLevel", "getImageOrientation", "()Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "setImageOrientation", "(Lcom/samsung/android/vexfwk/param/VexFwkOrientation;)V", "getSaveAsFlip", "()Z", "setSaveAsFlip", "(Z)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class BokehLens extends BokehEffect {
            private int effectLevel;
            private BokehEffectType effectTypeId;
            private VexFwkOrientation imageOrientation;
            private int reLightLevel;
            private boolean saveAsFlip;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public BokehLens(BokehEffectType bokehEffectType, int i2, int i7, VexFwkOrientation vexFwkOrientation, boolean z) {
                super((e) null);
                j.e(bokehEffectType, "effectTypeId");
                j.e(vexFwkOrientation, "imageOrientation");
                this.effectTypeId = bokehEffectType;
                this.effectLevel = i2;
                this.reLightLevel = i7;
                this.imageOrientation = vexFwkOrientation;
                this.saveAsFlip = z;
            }

            public static /* synthetic */ BokehLens copy$default(BokehLens bokehLens, BokehEffectType bokehEffectType, int i2, int i7, VexFwkOrientation vexFwkOrientation, boolean z, int i8, Object obj) {
                if ((i8 & 1) != 0) {
                    bokehEffectType = bokehLens.effectTypeId;
                }
                if ((i8 & 2) != 0) {
                    i2 = bokehLens.effectLevel;
                }
                if ((i8 & 4) != 0) {
                    i7 = bokehLens.reLightLevel;
                }
                if ((i8 & 8) != 0) {
                    vexFwkOrientation = bokehLens.imageOrientation;
                }
                if ((i8 & 16) != 0) {
                    z = bokehLens.saveAsFlip;
                }
                VexFwkOrientation vexFwkOrientation2 = vexFwkOrientation;
                boolean z3 = z;
                return bokehLens.copy(bokehEffectType, i2, i7, vexFwkOrientation2, z3);
            }

            public final BokehEffectType component1() {
                return this.effectTypeId;
            }

            public final int component2() {
                return this.effectLevel;
            }

            public final int component3() {
                return this.reLightLevel;
            }

            public final VexFwkOrientation component4() {
                return this.imageOrientation;
            }

            public final boolean component5() {
                return this.saveAsFlip;
            }

            public final BokehLens copy(BokehEffectType bokehEffectType, int i2, int i7, VexFwkOrientation vexFwkOrientation, boolean z) {
                j.e(bokehEffectType, "effectTypeId");
                j.e(vexFwkOrientation, "imageOrientation");
                return new BokehLens(bokehEffectType, i2, i7, vexFwkOrientation, z);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof BokehLens)) {
                    return false;
                }
                BokehLens bokehLens = (BokehLens) obj;
                if (this.effectTypeId == bokehLens.effectTypeId && this.effectLevel == bokehLens.effectLevel && this.reLightLevel == bokehLens.reLightLevel && this.imageOrientation == bokehLens.imageOrientation && this.saveAsFlip == bokehLens.saveAsFlip) {
                    return true;
                }
                return false;
            }

            public final int getEffectLevel() {
                return this.effectLevel;
            }

            public final BokehEffectType getEffectTypeId() {
                return this.effectTypeId;
            }

            public final VexFwkOrientation getImageOrientation() {
                return this.imageOrientation;
            }

            public final int getReLightLevel() {
                return this.reLightLevel;
            }

            public final boolean getSaveAsFlip() {
                return this.saveAsFlip;
            }

            public int hashCode() {
                int b = C0212a.b(this.reLightLevel, C0212a.b(this.effectLevel, this.effectTypeId.hashCode() * 31, 31), 31);
                return Boolean.hashCode(this.saveAsFlip) + ((this.imageOrientation.hashCode() + b) * 31);
            }

            public final void setEffectLevel(int i2) {
                this.effectLevel = i2;
            }

            public final void setEffectTypeId(BokehEffectType bokehEffectType) {
                j.e(bokehEffectType, "<set-?>");
                this.effectTypeId = bokehEffectType;
            }

            public final void setImageOrientation(VexFwkOrientation vexFwkOrientation) {
                j.e(vexFwkOrientation, "<set-?>");
                this.imageOrientation = vexFwkOrientation;
            }

            public final void setReLightLevel(int i2) {
                this.reLightLevel = i2;
            }

            public final void setSaveAsFlip(boolean z) {
                this.saveAsFlip = z;
            }

            public String toString() {
                BokehEffectType bokehEffectType = this.effectTypeId;
                int i2 = this.effectLevel;
                int i7 = this.reLightLevel;
                VexFwkOrientation vexFwkOrientation = this.imageOrientation;
                boolean z = this.saveAsFlip;
                StringBuilder sb2 = new StringBuilder("BokehLens(effectTypeId=");
                sb2.append(bokehEffectType);
                sb2.append(", effectLevel=");
                sb2.append(i2);
                sb2.append(", reLightLevel=");
                sb2.append(i7);
                sb2.append(", imageOrientation=");
                sb2.append(vexFwkOrientation);
                sb2.append(", saveAsFlip=");
                return N2.j.h(sb2, z, ")");
            }
        }

        public /* synthetic */ BokehEffect(e eVar) {
            this();
        }

        private BokehEffect() {
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bR\u001b\u0010\u000e\u001a\u00020\t8BX\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR!\u0010\u0011\u001a\u00020\u000f8FX\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\u000b\u0012\u0004\b\u0013\u0010\u0003\u001a\u0004\b\u0011\u0010\u0012R!\u0010\u0015\u001a\u00020\u000f8FX\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\u000b\u0012\u0004\b\u0016\u0010\u0003\u001a\u0004\b\u0015\u0010\u0012R\u001c\u0010\u0018\u001a\n \u0017*\u0004\u0018\u00010\t0\t8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002XT¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001a8\u0002XT¢\u0006\u0006\n\u0004\b\u001d\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001a8\u0002XT¢\u0006\u0006\n\u0004\b\u001e\u0010\u001cR\u0014\u0010\u001f\u001a\u00020\t8\u0002XT¢\u0006\u0006\n\u0004\b\u001f\u0010\u0019¨\u0006 "}, d2 = {"Lcom/samsung/android/vexfwk/sdk/bokeheffect/VexFwkPortraitBokehEffect$Companion;", "", "<init>", "()V", "Lcom/samsung/android/vexfwk/Bokeheffect/BokehEffectType;", "effectType", "Lcom/samsung/android/vexfwk/sdk/bokeheffect/VexFwkPortraitBokehEffect$BokehEffect;", "getEffect", "(Lcom/samsung/android/vexfwk/Bokeheffect/BokehEffectType;)Lcom/samsung/android/vexfwk/sdk/bokeheffect/VexFwkPortraitBokehEffect$BokehEffect;", "", "bokehEffectVersion$delegate", "Lme/f;", "getBokehEffectVersion", "()Ljava/lang/String;", "bokehEffectVersion", "", "isAvailable$delegate", "isAvailable", "()Z", "isAvailable$annotations", "isSegmentationMapSupported$delegate", "isSegmentationMapSupported", "isSegmentationMapSupported$annotations", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "", "STREAM_ID_INPUT_IMAGE", "I", "STREAM_ID_OUTPUT_IMAGE", "STREAM_ID_OUTPUT_SEGMENTATION_MAP", "MIN_AVAILABLE_VERSION", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[BokehEffectType.values().length];
                try {
                    iArr[BokehEffectType.EFFECT_TYPE_BOKEH_LENS.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final String getBokehEffectVersion() {
            return (String) VexFwkPortraitBokehEffect.bokehEffectVersion$delegate.getValue();
        }

        public final BokehEffect getEffect(BokehEffectType bokehEffectType) {
            j.e(bokehEffectType, "effectType");
            if (WhenMappings.$EnumSwitchMapping$0[bokehEffectType.ordinal()] == 1) {
                return new BokehEffect.BokehLens(BokehEffectType.EFFECT_TYPE_BOKEH_LENS, 5, 5, VexFwkOrientation.ORIENTATION_ROTATE_0, false);
            }
            throw new RuntimeException();
        }

        public final boolean isAvailable() {
            return ((Boolean) VexFwkPortraitBokehEffect.isAvailable$delegate.getValue()).booleanValue();
        }

        public final boolean isSegmentationMapSupported() {
            return ((Boolean) VexFwkPortraitBokehEffect.isSegmentationMapSupported$delegate.getValue()).booleanValue();
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }

        public static /* synthetic */ void isSegmentationMapSupported$annotations() {
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/bokeheffect/VexFwkPortraitBokehEffect$Result;", "", "image", "Landroid/graphics/Bitmap;", "segmentationMap", "", "<init>", "(Landroid/graphics/Bitmap;[I)V", "getImage", "()Landroid/graphics/Bitmap;", "getSegmentationMap", "()[I", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Result {
        private final Bitmap image;
        private final int[] segmentationMap;

        public Result(Bitmap bitmap, int[] iArr) {
            j.e(bitmap, "image");
            j.e(iArr, "segmentationMap");
            this.image = bitmap;
            this.segmentationMap = iArr;
        }

        public final Bitmap getImage() {
            return this.image;
        }

        public final int[] getSegmentationMap() {
            return this.segmentationMap;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004c, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004d, code lost:
        He.F.u(r6, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0050, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0053, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0054, code lost:
        He.F.u(r5, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0057, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final me.i applyBokehEffectInternal(android.graphics.Bitmap r4, com.samsung.android.vexfwk.sdk.bokeheffect.VexFwkPortraitBokehEffect.BokehEffect r5, java.lang.String r6) {
        /*
            r3 = this;
            com.samsung.android.vexfwk.param.VexFwkBokehEffectParamsV1 r5 = r3.convertToBokehEffectParams(r5)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r0 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r0.<init>()
            r1 = 0
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r0 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.addInputBuffer(r0, (int) r1, (android.graphics.Bitmap) r4)
            Wf.c r1 = new Wf.c
            r2 = 11
            r1.<init>(r2, r5, r6)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r5 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.setSettingMetadata(r0, r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r5 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r5
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r5 = r5.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r6 = com.samsung.android.vexfwk.session.VexFwkUsecase.PORTRAIT_BOKEH_EFFECT     // Catch:{ all -> 0x0051 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r6 = r3.getSession(r6)     // Catch:{ all -> 0x0051 }
            java.lang.Object r6 = r6.m60processRequestIoAF18A(r5)     // Catch:{ all -> 0x0051 }
            r0 = 0
            He.F.u(r5, r0)
            L2.a.A(r6)
            java.lang.AutoCloseable r6 = (java.lang.AutoCloseable) r6
            r5 = r6
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r5 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r5     // Catch:{ all -> 0x004a }
            me.i r3 = r3.handleProcessResult(r5)     // Catch:{ all -> 0x004a }
            He.F.u(r6, r0)
            android.graphics.ColorSpace r4 = r4.getColorSpace()
            if (r4 == 0) goto L_0x0049
            java.lang.Object r5 = r3.d
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
            r5.setColorSpace(r4)
        L_0x0049:
            return r3
        L_0x004a:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x004c }
        L_0x004c:
            r4 = move-exception
            He.F.u(r6, r3)
            throw r4
        L_0x0051:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0053 }
        L_0x0053:
            r4 = move-exception
            He.F.u(r5, r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.bokeheffect.VexFwkPortraitBokehEffect.applyBokehEffectInternal(android.graphics.Bitmap, com.samsung.android.vexfwk.sdk.bokeheffect.VexFwkPortraitBokehEffect$BokehEffect, java.lang.String):me.i");
    }

    /* access modifiers changed from: private */
    public static final x applyBokehEffectInternal$lambda$13(VexFwkBokehEffectParamsV1 vexFwkBokehEffectParamsV1, String str, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.PORTRAIT_BOKEH_EFFECT_V1.INSTANCE, vexFwkBokehEffectParamsV1);
        vexFwkMetadataNative.set(VexFwkMetadataKey.PORTRAIT_BOKEH_EFFECT.INSTANCE, vexFwkBokehEffectParamsV1.convertToVexFwkBokehEffectParams());
        vexFwkMetadataNative.set(VexFwkMetadataKey.PIPELINE_NAME.INSTANCE, str);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final Bitmap applyEffect$lambda$2(VexFwkPortraitBokehEffect vexFwkPortraitBokehEffect, Bitmap bitmap, BokehEffect bokehEffect) {
        String str = TAG;
        Log.d(str, "applyEffect : E");
        Bitmap bitmap2 = (Bitmap) vexFwkPortraitBokehEffect.applyBokehEffectInternal(bitmap, bokehEffect, Event.DEFAULT_EVENT_TYPE).d;
        Log.d(str, "applyEffect : X");
        return bitmap2;
    }

    /* access modifiers changed from: private */
    public static final Result applyEffectWithSegMapOutput$lambda$5(VexFwkPortraitBokehEffect vexFwkPortraitBokehEffect, Bitmap bitmap, BokehEffect bokehEffect) {
        String str = TAG;
        Log.d(str, "applyEffectWithMap : E");
        if (Companion.isSegmentationMapSupported()) {
            i applyBokehEffectInternal = vexFwkPortraitBokehEffect.applyBokehEffectInternal(bitmap, bokehEffect, "segmentation_map_output");
            Bitmap bitmap2 = (Bitmap) applyBokehEffectInternal.d;
            int[] iArr = (int[]) applyBokehEffectInternal.e;
            if (iArr != null) {
                Log.d(str, "applyEffectWithMap : X");
                return new Result(bitmap2, iArr);
            }
            throw new IllegalStateException("Segmentation map is null. This should not happen. Please report this issue.");
        }
        throw new IllegalStateException("Bokeh effect with segmentation map is not supported on this device.");
    }

    /* access modifiers changed from: private */
    public static final String bokehEffectVersion_delegate$lambda$18() {
        return (String) VexFwkSdkBase.Companion.getProperties(VexFwkUsecase.PORTRAIT_BOKEH_EFFECT, VexFwkMetadataKey.PROPERTY_BOKEH_EFFECT_VERSION.INSTANCE, new e5.d(6));
    }

    /* access modifiers changed from: private */
    public static final String bokehEffectVersion_delegate$lambda$18$lambda$17() {
        return "0";
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1(VexFwkPortraitBokehEffect vexFwkPortraitBokehEffect) {
        j.e(vexFwkPortraitBokehEffect, "$this$configureWith");
        vexFwkPortraitBokehEffect.createSession(VexFwkUsecase.PORTRAIT_BOKEH_EFFECT, new com.samsung.android.vexfwk.sdk.docscan.b(5));
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
        if (Companion.isSegmentationMapSupported()) {
            VexFwkSessionConfigRequest.Builder.addOutputStream$default(builder2, 2, vexFwkStreamType, VexFwkStreamUsage.MASK, 0, 0, 0, (Surface) null, 120, (Object) null);
        }
        return x.f4917a;
    }

    private final VexFwkBokehEffectParamsV1 convertToBokehEffectParams(BokehEffect bokehEffect) {
        if (bokehEffect instanceof BokehEffect.BokehLens) {
            BokehEffect.BokehLens bokehLens = (BokehEffect.BokehLens) bokehEffect;
            return new VexFwkBokehEffectParamsV1(bokehLens.getEffectTypeId(), bokehLens.getEffectLevel(), bokehLens.getReLightLevel(), bokehLens.getImageOrientation(), bokehLens.getSaveAsFlip());
        }
        throw new RuntimeException();
    }

    public static final BokehEffect getEffect(BokehEffectType bokehEffectType) {
        return Companion.getEffect(bokehEffectType);
    }

    private final i handleProcessResult(VexFwkSessionTotalResult vexFwkSessionTotalResult) {
        int[] iArr;
        Object obj;
        Bitmap bitmap;
        Object obj2;
        if (vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.RESULT_CODE.INSTANCE) == VexFwkNodeResultCode.OK) {
            ArrayList arrayList = new ArrayList();
            for (VexFwkSessionPartialResult outputBuffers : vexFwkSessionTotalResult.getPartialResults()) {
                C1200r.A0(outputBuffers.getOutputBuffers(), arrayList);
            }
            Iterator it = arrayList.iterator();
            while (true) {
                iArr = null;
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
            if (vexFwkBuffer != null) {
                bitmap = VexFwkHardwareBufferNative.Companion.convertToBitmap$default(VexFwkHardwareBufferNative.Companion, vexFwkBuffer.getHardwareBuffer(), false, 2, (Object) null);
            } else {
                bitmap = null;
            }
            if (bitmap != null) {
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        obj2 = null;
                        break;
                    }
                    obj2 = it2.next();
                    if (((VexFwkBuffer) obj2).getStreamId() == 2) {
                        break;
                    }
                }
                VexFwkBuffer vexFwkBuffer2 = (VexFwkBuffer) obj2;
                if (vexFwkBuffer2 != null) {
                    iArr = VexFwkHardwareBufferNative.Companion.convertToArgbIntArray(vexFwkBuffer2.getHardwareBuffer());
                }
                return new i(bitmap, iArr);
            }
            throw new IllegalStateException("Output buffer for image not found");
        }
        throw new IllegalStateException("Failed to apply bokeh effect");
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    /* access modifiers changed from: private */
    public static final boolean isAvailable_delegate$lambda$19() {
        boolean z = false;
        if (!VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.PORTRAIT_BOKEH_EFFECT)) {
            return false;
        }
        Companion companion = Companion;
        if (companion.getBokehEffectVersion().compareTo("1") >= 0) {
            z = true;
        }
        String str = TAG;
        String access$getBokehEffectVersion = companion.getBokehEffectVersion();
        Log.d(str, "Current version " + access$getBokehEffectVersion + ", minimum available version 1 -> isAvailable " + z);
        return z;
    }

    public static final boolean isSegmentationMapSupported() {
        return Companion.isSegmentationMapSupported();
    }

    /* access modifiers changed from: private */
    public static final boolean isSegmentationMapSupported_delegate$lambda$21() {
        if (!Companion.isAvailable()) {
            return false;
        }
        boolean contains = ((List) VexFwkSdkBase.Companion.getProperties(VexFwkUsecase.PORTRAIT_BOKEH_EFFECT, VexFwkMetadataKey.PROPERTY_BOKEH_EFFECT_SUPPORTED_FEATURES.INSTANCE, new e5.d(5))).contains(VexFwkBokehEffectFeature.SEGMENTATION_MAP_OUTPUT);
        String str = TAG;
        Log.d(str, "Is segmentation map supported? " + contains);
        return contains;
    }

    /* access modifiers changed from: private */
    public static final List isSegmentationMapSupported_delegate$lambda$21$lambda$20() {
        return C1202t.d;
    }

    public final CompletableFuture<Bitmap> applyEffect(Bitmap bitmap, BokehEffect bokehEffect) {
        j.e(bitmap, "bitmap");
        j.e(bokehEffect, "effect");
        CompletableFuture<Bitmap> supplyAsync = CompletableFuture.supplyAsync(new a(this, bitmap, bokehEffect, 0));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    public final CompletableFuture<Result> applyEffectWithSegMapOutput(Bitmap bitmap, BokehEffect bokehEffect) {
        j.e(bitmap, "bitmap");
        j.e(bokehEffect, "effect");
        CompletableFuture<Result> supplyAsync = CompletableFuture.supplyAsync(new a(this, bitmap, bokehEffect, 1));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    public final VexFwkPortraitBokehEffect configure() {
        return (VexFwkPortraitBokehEffect) configureWith(this, new com.samsung.android.vexfwk.sdk.docscan.b(4));
    }
}
