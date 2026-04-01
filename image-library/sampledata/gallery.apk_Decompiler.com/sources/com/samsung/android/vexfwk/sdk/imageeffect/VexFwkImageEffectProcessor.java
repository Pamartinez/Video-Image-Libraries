package com.samsung.android.vexfwk.sdk.imageeffect;

import Ad.f;
import Ae.b;
import He.F;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import com.samsung.android.vexfwk.bitmap.VexFwkBitmapHardware;
import com.samsung.android.vexfwk.imageeffect.ImageEffectType;
import com.samsung.android.vexfwk.imageeffect.VexFwkImageEffectCapabilities;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkImageEffectParams;
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
import i.C0212a;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.k;
import me.x;
import ne.C1194l;
import qd.C1224a;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001c2\u00020\u0001:\u0003\u001a\u001b\u001cB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0000J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J;\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\"\u0004\b\u0000\u0010\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u0002H\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\nH\u0002¢\u0006\u0002\u0010\u0014J,\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\nJ,\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\nJ\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0017J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\r¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imageeffect/VexFwkImageEffectProcessor;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "configure", "convertToImageEffectParams", "Lcom/samsung/android/vexfwk/param/VexFwkImageEffectParams;", "imageEffect", "Lcom/samsung/android/vexfwk/sdk/imageeffect/VexFwkImageEffectProcessor$ImageEffect;", "applyNegativeSegMask", "", "applyEffectImpl", "Ljava/util/concurrent/CompletableFuture;", "Landroid/graphics/Bitmap;", "T", "effect", "buffer", "inputMask", "Lcom/samsung/android/vexfwk/sdk/imageeffect/VexFwkImageEffectProcessor$SegMask;", "isNegativeMask", "(Lcom/samsung/android/vexfwk/sdk/imageeffect/VexFwkImageEffectProcessor$ImageEffect;Ljava/lang/Object;Lcom/samsung/android/vexfwk/sdk/imageeffect/VexFwkImageEffectProcessor$SegMask;Z)Ljava/util/concurrent/CompletableFuture;", "applyEffect", "image", "Landroid/media/Image;", "isApplyBG", "bitmap", "ImageEffect", "SegMask", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageEffectProcessor extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_IMAGE = 0;
    private static final int STREAM_ID_INPUT_MASK = 1;
    private static final int STREAM_ID_OUTPUT_IMAGE = 2;
    /* access modifiers changed from: private */
    public static final String TAG = "VexFwkImageEffectProcessor";
    /* access modifiers changed from: private */
    public static final boolean isAvailable = VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.IMAGE_EFFECT);

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0007J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u00020\r8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\f\u0010\u000f¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imageeffect/VexFwkImageEffectProcessor$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "STREAM_ID_INPUT_IMAGE", "", "STREAM_ID_INPUT_MASK", "STREAM_ID_OUTPUT_IMAGE", "isAvailable", "", "isAvailable$annotations", "()Z", "getSupportedFilters", "Lcom/samsung/android/vexfwk/imageeffect/VexFwkImageEffectCapabilities;", "getEffect", "Lcom/samsung/android/vexfwk/sdk/imageeffect/VexFwkImageEffectProcessor$ImageEffect;", "effectType", "Lcom/samsung/android/vexfwk/imageeffect/ImageEffectType;", "getEffectImpl", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            static {
                /*
                    com.samsung.android.vexfwk.imageeffect.ImageEffectType[] r0 = com.samsung.android.vexfwk.imageeffect.ImageEffectType.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.samsung.android.vexfwk.imageeffect.ImageEffectType r1 = com.samsung.android.vexfwk.imageeffect.ImageEffectType.EFFECT_TYPE_GRAYSCALE     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.samsung.android.vexfwk.imageeffect.ImageEffectType r1 = com.samsung.android.vexfwk.imageeffect.ImageEffectType.EFFECT_TYPE_BLUR     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.imageeffect.VexFwkImageEffectProcessor.Companion.WhenMappings.<clinit>():void");
            }
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private final ImageEffect getEffectImpl(ImageEffectType imageEffectType) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[imageEffectType.ordinal()];
            if (i2 == 1) {
                return new ImageEffect.GrayScale(ImageEffectType.EFFECT_TYPE_GRAYSCALE, 100, 0, 0);
            }
            if (i2 == 2) {
                return new ImageEffect.Blur(ImageEffectType.EFFECT_TYPE_BLUR, 25.0f);
            }
            throw new RuntimeException();
        }

        /* access modifiers changed from: private */
        public static final VexFwkImageEffectCapabilities getSupportedFilters$lambda$1$lambda$0() {
            return new VexFwkImageEffectCapabilities(new int[0]);
        }

        public final ImageEffect getEffect(ImageEffectType imageEffectType) {
            j.e(imageEffectType, "effectType");
            return getEffectImpl(imageEffectType);
        }

        public final VexFwkImageEffectCapabilities getSupportedFilters() {
            VexFwkImageEffectCapabilities vexFwkImageEffectCapabilities = (VexFwkImageEffectCapabilities) VexFwkProvider.fetchProperties(VexFwkUsecase.IMAGE_EFFECT).getOrElse(VexFwkMetadataKey.PROPERTY_IMAGE_EFFECT_CAPABILITIES.INSTANCE, new d(11));
            N2.j.w("capabilities : ", C1194l.R0(vexFwkImageEffectCapabilities, (String) null, (String) null, (String) null, (b) null, 63), VexFwkImageEffectProcessor.TAG);
            return vexFwkImageEffectCapabilities;
        }

        public final boolean isAvailable() {
            return VexFwkImageEffectProcessor.isAvailable;
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imageeffect/VexFwkImageEffectProcessor$ImageEffect;", "", "<init>", "()V", "Blur", "GrayScale", "Lcom/samsung/android/vexfwk/sdk/imageeffect/VexFwkImageEffectProcessor$ImageEffect$Blur;", "Lcom/samsung/android/vexfwk/sdk/imageeffect/VexFwkImageEffectProcessor$ImageEffect$GrayScale;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class ImageEffect {

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imageeffect/VexFwkImageEffectProcessor$ImageEffect$Blur;", "Lcom/samsung/android/vexfwk/sdk/imageeffect/VexFwkImageEffectProcessor$ImageEffect;", "effectType", "Lcom/samsung/android/vexfwk/imageeffect/ImageEffectType;", "radius", "", "<init>", "(Lcom/samsung/android/vexfwk/imageeffect/ImageEffectType;F)V", "getEffectType", "()Lcom/samsung/android/vexfwk/imageeffect/ImageEffectType;", "getRadius", "()F", "setRadius", "(F)V", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Blur extends ImageEffect {
            private final ImageEffectType effectType;
            private float radius;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Blur(ImageEffectType imageEffectType, float f) {
                super((e) null);
                j.e(imageEffectType, "effectType");
                this.effectType = imageEffectType;
                this.radius = f;
            }

            public static /* synthetic */ Blur copy$default(Blur blur, ImageEffectType imageEffectType, float f, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    imageEffectType = blur.effectType;
                }
                if ((i2 & 2) != 0) {
                    f = blur.radius;
                }
                return blur.copy(imageEffectType, f);
            }

            public final ImageEffectType component1() {
                return this.effectType;
            }

            public final float component2() {
                return this.radius;
            }

            public final Blur copy(ImageEffectType imageEffectType, float f) {
                j.e(imageEffectType, "effectType");
                return new Blur(imageEffectType, f);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Blur)) {
                    return false;
                }
                Blur blur = (Blur) obj;
                if (this.effectType == blur.effectType && Float.compare(this.radius, blur.radius) == 0) {
                    return true;
                }
                return false;
            }

            public final ImageEffectType getEffectType() {
                return this.effectType;
            }

            public final float getRadius() {
                return this.radius;
            }

            public int hashCode() {
                return Float.hashCode(this.radius) + (this.effectType.hashCode() * 31);
            }

            public final void setRadius(float f) {
                this.radius = f;
            }

            public String toString() {
                ImageEffectType imageEffectType = this.effectType;
                float f = this.radius;
                return "Blur(effectType=" + imageEffectType + ", radius=" + f + ")";
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000f¨\u0006 "}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imageeffect/VexFwkImageEffectProcessor$ImageEffect$GrayScale;", "Lcom/samsung/android/vexfwk/sdk/imageeffect/VexFwkImageEffectProcessor$ImageEffect;", "effectType", "Lcom/samsung/android/vexfwk/imageeffect/ImageEffectType;", "intensity", "", "contrast", "grain", "<init>", "(Lcom/samsung/android/vexfwk/imageeffect/ImageEffectType;III)V", "getEffectType", "()Lcom/samsung/android/vexfwk/imageeffect/ImageEffectType;", "getIntensity", "()I", "setIntensity", "(I)V", "getContrast", "setContrast", "getGrain", "setGrain", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class GrayScale extends ImageEffect {
            private int contrast;
            private final ImageEffectType effectType;
            private int grain;
            private int intensity;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public GrayScale(ImageEffectType imageEffectType, int i2, int i7, int i8) {
                super((e) null);
                j.e(imageEffectType, "effectType");
                this.effectType = imageEffectType;
                this.intensity = i2;
                this.contrast = i7;
                this.grain = i8;
            }

            public static /* synthetic */ GrayScale copy$default(GrayScale grayScale, ImageEffectType imageEffectType, int i2, int i7, int i8, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    imageEffectType = grayScale.effectType;
                }
                if ((i10 & 2) != 0) {
                    i2 = grayScale.intensity;
                }
                if ((i10 & 4) != 0) {
                    i7 = grayScale.contrast;
                }
                if ((i10 & 8) != 0) {
                    i8 = grayScale.grain;
                }
                return grayScale.copy(imageEffectType, i2, i7, i8);
            }

            public final ImageEffectType component1() {
                return this.effectType;
            }

            public final int component2() {
                return this.intensity;
            }

            public final int component3() {
                return this.contrast;
            }

            public final int component4() {
                return this.grain;
            }

            public final GrayScale copy(ImageEffectType imageEffectType, int i2, int i7, int i8) {
                j.e(imageEffectType, "effectType");
                return new GrayScale(imageEffectType, i2, i7, i8);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof GrayScale)) {
                    return false;
                }
                GrayScale grayScale = (GrayScale) obj;
                if (this.effectType == grayScale.effectType && this.intensity == grayScale.intensity && this.contrast == grayScale.contrast && this.grain == grayScale.grain) {
                    return true;
                }
                return false;
            }

            public final int getContrast() {
                return this.contrast;
            }

            public final ImageEffectType getEffectType() {
                return this.effectType;
            }

            public final int getGrain() {
                return this.grain;
            }

            public final int getIntensity() {
                return this.intensity;
            }

            public int hashCode() {
                return Integer.hashCode(this.grain) + C0212a.b(this.contrast, C0212a.b(this.intensity, this.effectType.hashCode() * 31, 31), 31);
            }

            public final void setContrast(int i2) {
                this.contrast = i2;
            }

            public final void setGrain(int i2) {
                this.grain = i2;
            }

            public final void setIntensity(int i2) {
                this.intensity = i2;
            }

            public String toString() {
                ImageEffectType imageEffectType = this.effectType;
                int i2 = this.intensity;
                int i7 = this.contrast;
                int i8 = this.grain;
                return "GrayScale(effectType=" + imageEffectType + ", intensity=" + i2 + ", contrast=" + i7 + ", grain=" + i8 + ")";
            }
        }

        public /* synthetic */ ImageEffect(e eVar) {
            this();
        }

        private ImageEffect() {
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imageeffect/VexFwkImageEffectProcessor$SegMask;", "", "mask", "", "size", "Landroid/util/Size;", "<init>", "([ILandroid/util/Size;)V", "getMask", "()[I", "setMask", "([I)V", "getSize", "()Landroid/util/Size;", "setSize", "(Landroid/util/Size;)V", "equals", "", "other", "hashCode", "", "component1", "component2", "copy", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SegMask {
        private int[] mask;
        private Size size;

        public SegMask(int[] iArr, Size size2) {
            j.e(iArr, "mask");
            j.e(size2, "size");
            this.mask = iArr;
            this.size = size2;
        }

        public static /* synthetic */ SegMask copy$default(SegMask segMask, int[] iArr, Size size2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                iArr = segMask.mask;
            }
            if ((i2 & 2) != 0) {
                size2 = segMask.size;
            }
            return segMask.copy(iArr, size2);
        }

        public final int[] component1() {
            return this.mask;
        }

        public final Size component2() {
            return this.size;
        }

        public final SegMask copy(int[] iArr, Size size2) {
            j.e(iArr, "mask");
            j.e(size2, "size");
            return new SegMask(iArr, size2);
        }

        public boolean equals(Object obj) {
            Class<?> cls;
            if (this == obj) {
                return true;
            }
            if (obj != null) {
                cls = obj.getClass();
            } else {
                cls = null;
            }
            if (!SegMask.class.equals(cls)) {
                return false;
            }
            j.c(obj, "null cannot be cast to non-null type com.samsung.android.vexfwk.sdk.imageeffect.VexFwkImageEffectProcessor.SegMask");
            SegMask segMask = (SegMask) obj;
            if (Arrays.equals(this.mask, segMask.mask) && j.a(this.size, segMask.size)) {
                return true;
            }
            return false;
        }

        public final int[] getMask() {
            return this.mask;
        }

        public final Size getSize() {
            return this.size;
        }

        public int hashCode() {
            return this.size.hashCode() + (Arrays.hashCode(this.mask) * 31);
        }

        public final void setMask(int[] iArr) {
            j.e(iArr, "<set-?>");
            this.mask = iArr;
        }

        public final void setSize(Size size2) {
            j.e(size2, "<set-?>");
            this.size = size2;
        }

        public String toString() {
            String arrays = Arrays.toString(this.mask);
            Size size2 = this.size;
            return "SegMask(mask=" + arrays + ", size=" + size2 + ")";
        }
    }

    private final <T> CompletableFuture<Bitmap> applyEffectImpl(ImageEffect imageEffect, T t, SegMask segMask, boolean z) {
        CompletableFuture<Bitmap> supplyAsync = CompletableFuture.supplyAsync(new C1224a(t, segMask, this, imageEffect, z));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public static final Bitmap applyEffectImpl$lambda$9(Object obj, SegMask segMask, VexFwkImageEffectProcessor vexFwkImageEffectProcessor, ImageEffect imageEffect, boolean z) {
        Size size;
        VexFwkBitmapHardware vexFwkBitmapHardware;
        Throwable th;
        Throwable th2;
        String str = TAG;
        Log.i(str, "imageEffectImpl E");
        if (obj instanceof Image) {
            Image image = (Image) obj;
            size = new Size(image.getWidth(), image.getHeight());
        } else if (obj instanceof Bitmap) {
            Bitmap bitmap = (Bitmap) obj;
            size = new Size(bitmap.getWidth(), bitmap.getHeight());
        } else {
            throw new IllegalArgumentException("Unsupported image type");
        }
        VexFwkBitmapHardware vexFwkBitmapHardware2 = new VexFwkBitmapHardware(size.getWidth(), size.getHeight(), 1, (int[]) null);
        if (segMask != null) {
            vexFwkBitmapHardware = new VexFwkBitmapHardware(segMask.getSize().getWidth(), segMask.getSize().getHeight(), 1, segMask.getMask());
        } else {
            vexFwkBitmapHardware = new VexFwkBitmapHardware(1, 1, 1, (int[]) null);
        }
        VexFwkSessionRequest.Sync build = ((VexFwkSessionRequest.Sync.Builder) VexFwkSessionRequestKt.addOutputBuffer(VexFwkSessionRequestKt.setSettingMetadata(VexFwkSessionRequestKt.addInputBuffer(VexFwkSessionRequestKt.addInputBuffer(new VexFwkSessionRequest.Sync.Builder(), 0, obj), 1, vexFwkBitmapHardware.getBitmap()), new f(19, (Object) vexFwkImageEffectProcessor.convertToImageEffectParams(imageEffect, z))), 2, vexFwkBitmapHardware2.getBitmap())).build();
        try {
            Object r10 = vexFwkImageEffectProcessor.getSession(VexFwkUsecase.IMAGE_EFFECT).m60processRequestIoAF18A(build);
            F.u(build, (Throwable) null);
            Throwable a7 = k.a(r10);
            if (a7 == null) {
                Log.d(str, "ImageEffectImpl success");
                ((VexFwkSessionTotalResult) r10).close();
                vexFwkBitmapHardware.close();
                try {
                    Bitmap bitmapCropped = vexFwkBitmapHardware2.toBitmapCropped(0, 0, vexFwkBitmapHardware2.getWidth(), vexFwkBitmapHardware2.getHeight(), Bitmap.Config.ARGB_8888);
                    F.u(vexFwkBitmapHardware2, (Throwable) null);
                    Log.i(str, "imageEffectImpl X");
                    return bitmapCropped;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    F.u(vexFwkBitmapHardware2, th2);
                    throw th4;
                }
            } else {
                Log.e(str, "Failed to process request : " + a7);
                vexFwkBitmapHardware.close();
                vexFwkBitmapHardware2.close();
                throw a7;
            }
        } catch (Throwable th5) {
            Throwable th6 = th5;
            F.u(build, th);
            throw th6;
        }
    }

    /* access modifiers changed from: private */
    public static final x applyEffectImpl$lambda$9$lambda$3(VexFwkImageEffectParams vexFwkImageEffectParams, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.IMAGE_EFFECT_PARAMS.INSTANCE, vexFwkImageEffectParams);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1(VexFwkImageEffectProcessor vexFwkImageEffectProcessor) {
        j.e(vexFwkImageEffectProcessor, "$this$configureWith");
        vexFwkImageEffectProcessor.createSession(VexFwkUsecase.IMAGE_EFFECT, new com.samsung.android.vexfwk.sdk.docscan.b(17));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$1$lambda$0(VexFwkSessionConfigRequest.Builder builder) {
        j.e(builder, "$this$createSession");
        VexFwkStreamType vexFwkStreamType = VexFwkStreamType.BUFFER;
        VexFwkStreamUsage vexFwkStreamUsage = VexFwkStreamUsage.IMAGE;
        VexFwkSessionConfigRequest.Builder builder2 = builder;
        VexFwkSessionConfigRequest.Builder.addInputStream$default(builder2, 0, vexFwkStreamType, vexFwkStreamUsage, 0, 0, 0, (Surface) null, 120, (Object) null);
        VexFwkSessionConfigRequest.Builder.addInputStream$default(builder2, 1, vexFwkStreamType, VexFwkStreamUsage.MASK, 0, 0, 0, (Surface) null, 120, (Object) null);
        VexFwkSessionConfigRequest.Builder.addOutputStream$default(builder2, 2, vexFwkStreamType, vexFwkStreamUsage, 0, 0, 0, (Surface) null, 120, (Object) null);
        return x.f4917a;
    }

    private final VexFwkImageEffectParams convertToImageEffectParams(ImageEffect imageEffect, boolean z) {
        if (imageEffect instanceof ImageEffect.GrayScale) {
            ImageEffect.GrayScale grayScale = (ImageEffect.GrayScale) imageEffect;
            return new VexFwkImageEffectParams(ImageEffectType.EFFECT_TYPE_GRAYSCALE, grayScale.getIntensity(), 0, 0, grayScale.getContrast(), grayScale.getGrain(), 0.0f, z);
        }
        boolean z3 = z;
        if (imageEffect instanceof ImageEffect.Blur) {
            return new VexFwkImageEffectParams(ImageEffectType.EFFECT_TYPE_BLUR, 0, 0, 0, 0, 0, ((ImageEffect.Blur) imageEffect).getRadius(), z3);
        }
        throw new RuntimeException();
    }

    public static final VexFwkImageEffectCapabilities getSupportedFilters() {
        return Companion.getSupportedFilters();
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    public final CompletableFuture<Bitmap> applyEffect(ImageEffect imageEffect, Image image, SegMask segMask, boolean z) {
        j.e(imageEffect, "effect");
        j.e(image, "image");
        j.e(segMask, "inputMask");
        return applyEffectImpl(imageEffect, image, segMask, z);
    }

    public final VexFwkImageEffectProcessor configure() {
        return (VexFwkImageEffectProcessor) configureWith(this, new com.samsung.android.vexfwk.sdk.docscan.b(16));
    }

    public final CompletableFuture<Bitmap> applyEffect(ImageEffect imageEffect, Bitmap bitmap, SegMask segMask, boolean z) {
        j.e(imageEffect, "effect");
        j.e(bitmap, "bitmap");
        j.e(segMask, "inputMask");
        return applyEffectImpl(imageEffect, bitmap, segMask, z);
    }

    public final CompletableFuture<Bitmap> applyEffect(ImageEffect imageEffect, Image image) {
        j.e(imageEffect, "effect");
        j.e(image, "image");
        return applyEffectImpl(imageEffect, image, (SegMask) null, false);
    }

    public final CompletableFuture<Bitmap> applyEffect(ImageEffect imageEffect, Bitmap bitmap) {
        j.e(imageEffect, "effect");
        j.e(bitmap, "bitmap");
        return applyEffectImpl(imageEffect, bitmap, (SegMask) null, false);
    }
}
