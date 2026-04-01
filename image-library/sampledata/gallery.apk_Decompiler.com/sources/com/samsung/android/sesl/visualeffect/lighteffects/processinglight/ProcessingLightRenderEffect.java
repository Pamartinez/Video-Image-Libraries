package com.samsung.android.sesl.visualeffect.lighteffects.processinglight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RenderEffect;
import android.graphics.RuntimeShader;
import android.graphics.Shader;
import bd.m;
import c0.C0086a;
import cd.a;
import cd.b;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import com.samsung.android.sesl.visualeffect.R$drawable;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.IColorizable;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffectBase;
import com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightConfig;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import o.c;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\b\u0001\u0018\u0000 <2\u00020\u00012\u00020\u0002:\u0001<B\u0019\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0014¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u0010\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\u000b2\b\b\u0001\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0015\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u0016¢\u0006\u0004\b\u001f\u0010\u0019J\u0015\u0010!\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u0016¢\u0006\u0004\b!\u0010\u0019J\u0015\u0010#\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u0016¢\u0006\u0004\b#\u0010\u0019J\u0015\u0010%\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u0016¢\u0006\u0004\b%\u0010\u0019J\u0017\u0010&\u001a\u00020\u000b2\b\b\u0001\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b&\u0010\u0015J\u0015\u0010(\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020\u0016¢\u0006\u0004\b(\u0010\u0019J\u0015\u0010*\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u0016¢\u0006\u0004\b*\u0010\u0019J\u000f\u0010+\u001a\u00020\u000bH\u0016¢\u0006\u0004\b+\u0010,J\u0019\u0010-\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0004\b-\u0010\u0011R$\u0010/\u001a\u0004\u0018\u00010.8\u0016@\u0016X\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R$\u00106\u001a\u0004\u0018\u0001058V@\u0016X\u000e¢\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;¨\u0006="}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightRenderEffect;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/VibeRenderEffectBase;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/IColorizable;", "", "useLightnessCalibration", "Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig$BlendMode;", "blendMode", "<init>", "(ZLcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig$BlendMode;)V", "Landroid/content/Context;", "appContext", "Lme/x;", "onInitialize", "(Landroid/content/Context;)V", "Landroid/graphics/Bitmap;", "bitmap", "setTintTexture", "(Landroid/graphics/Bitmap;)V", "", "colorInt", "setLightColor", "(I)V", "", "angle", "setLightRotation", "(F)V", "Landroid/graphics/PointF;", "pos", "setLightPosition", "(Landroid/graphics/PointF;)V", "scale", "setLightScale", "stretch", "setStretch", "intensity", "setLightIntensity", "saturation", "setLightSaturation", "setDomainColor", "deltaRatio", "setDomainDeltaRatio", "strength", "setDomainStrength", "destroy", "()V", "setLightMap", "Landroid/graphics/RuntimeShader;", "shader", "Landroid/graphics/RuntimeShader;", "getShader", "()Landroid/graphics/RuntimeShader;", "setShader", "(Landroid/graphics/RuntimeShader;)V", "Landroid/graphics/RenderEffect;", "renderEffect", "Landroid/graphics/RenderEffect;", "getRenderEffect", "()Landroid/graphics/RenderEffect;", "setRenderEffect", "(Landroid/graphics/RenderEffect;)V", "Companion", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ProcessingLightRenderEffect extends VibeRenderEffectBase implements IColorizable {
    public static final Companion Companion = new Companion((e) null);
    private RenderEffect renderEffect;
    private RuntimeShader shader;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\nB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0003¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightRenderEffect$Companion;", "", "<init>", "()V", "assembleShader", "", "useLightnessCalibration", "", "blendMode", "Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig$BlendMode;", "Default", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR$\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0006\u001a\u0004\b\u0011\u0010\bR\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightRenderEffect$Companion$Default;", "", "<init>", "()V", "", "lightmapRes", "I", "getLightmapRes", "()I", "Landroid/graphics/Bitmap;", "bmpLightmap", "Landroid/graphics/Bitmap;", "getBmpLightmap", "()Landroid/graphics/Bitmap;", "setBmpLightmap", "(Landroid/graphics/Bitmap;)V", "colorRes", "getColorRes", "Landroid/graphics/PointF;", "lightPos", "Landroid/graphics/PointF;", "getLightPos", "()Landroid/graphics/PointF;", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Default {
            public static final Default INSTANCE = new Default();
            private static Bitmap bmpLightmap;
            private static final int colorRes = R$drawable.grad_processing_640_oneui85;
            private static final PointF lightPos = new PointF(0.5f, 0.5f);
            private static final int lightmapRes = R$drawable.lightmap;

            private Default() {
            }

            public final Bitmap getBmpLightmap() {
                return bmpLightmap;
            }

            public final int getColorRes() {
                return colorRes;
            }

            public final PointF getLightPos() {
                return lightPos;
            }

            public final int getLightmapRes() {
                return lightmapRes;
            }

            public final void setBmpLightmap(Bitmap bitmap) {
                bmpLightmap = bitmap;
            }
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final String assembleShader(boolean z, ProcessingLightConfig.BlendMode blendMode) {
            String str;
            String str2;
            String str3 = "";
            if (z) {
                str = " lightColor *= 1.15 - 0.5 * abs(tintLightness - viewLightness); ";
            } else {
                str = str3;
            }
            ProcessingLightConfig.BlendMode blendMode2 = ProcessingLightConfig.BlendMode.PREMULT;
            if (blendMode == blendMode2) {
                str3 = " if (viewColor.a < epsilon) return half4(0, 0, 0, 0); ";
            }
            if (blendMode == blendMode2) {
                str2 = "\ncolor *= viewColor.a;\ncolor += viewColor.rgb * (1 - lightColor.a);\ncolor = clamp(color, half3(0), half3(1));\nreturn half4(color, viewColor.a);\n                    ";
            } else {
                str2 = "\nfloat alpha = max(length(color) / sqrt(3), viewColor.a);\ncolor += viewColor.rgb * (1 - lightColor.a);\ncolor = clamp(color, half3(0), half3(1));\nreturn half4(color, alpha);\n                    ";
            }
            return C0212a.p(C0086a.q("\nuniform shader inputShader;\nuniform shader lightMapShader;\n\nuniform shader tintShader;\nuniform half2 uTintShaderSize;\n// TODO possible for any transforming with mat3 for trs, but currently just for flipping since there's no requirements at least now.\nuniform half2 uTintFlipDirection; \n\nhalf useTint() {\n    return step(0.01, abs(uTintShaderSize.x * uTintShaderSize.y)); \n}\n    \nhalf4 texTint(half2 uv) {\n    uv = mix(uv, half2(1 - uv.x, uv.y), step(0.5, uTintFlipDirection.x));\n    return tintShader.eval(uv * uTintShaderSize);\n}\n\n// get tint color aligned center\nhalf3 getTintColor(half2 uv, half2 resolution) {\n    half2 guv = uv;\n    half asp = resolution.x / resolution.y;\n    if (asp > 1) {\n        guv.y /= asp;\n        guv.y += 0.5 * (1 - 1 / asp);\n    } else {\n        guv.x *= asp;\n        guv.x += 0.5 * (1 - asp);\n    }\n    return clamp(texTint(guv).rgb, half3(0), half3(1));\n}\n\n// get tint color aligned center\nhalf4 getTintColorAlpha(half2 uv, half2 resolution) {\n    half2 guv = uv;\n    half asp = resolution.x / resolution.y;\n    if (asp > 1) {\n        guv.y /= asp;\n        guv.y += 0.5 * (1 - 1 / asp);\n    } else {\n        guv.x *= asp;\n        guv.x += 0.5 * (1 - asp);\n    }\n    half4 tint = texTint(guv);\n    return clamp(tint, half4(0), half4(1));\n}\n        \n\nuniform half2 uLightMapSize;\nuniform half uStretch;\nuniform int uLightPositionStretch;\nuniform half2 uSize;\n\nuniform half2 uLightPosition;\nuniform half uLightRotation;\nuniform half uLightScale;\nuniform half4 uLightColor; // TODO for dev phase. use tint shader\nuniform half uLightIntensity;\nuniform half uLightSaturation;\n\nuniform half4 uDomainColor;\nuniform half uDomainStrength;\nuniform half uDomainDeltaRatio;\n\nuniform half uDitherVariation;\n\nhalf rand(half2 uv) {\n    return fract(sin(dot(uv, half2(12.9898, 78.233))) * 43758.5453);\n}\n\nhalf dither(half2 uv, half variation) {\n    return 1 + variation * 2 * (rand(uv * 10.0) - 0.5);\n}\n\nhalf2 rotate(half2 p, half angle) {\n    half r = radians(angle);\n    half s = sin(r);\n    half c = cos(r);\n    return mat2(c, -s, s, c) * p;\n}\n\nhalf4 texView(half2 uv) {\n    return inputShader.eval(uv * uSize);\n}\n\n// get relative uv based on longer length among width and height of the view. \nhalf2 relativeUv(half2 uv, half2 pos, half scale, half stretch) {\n    half asp = uSize.x / uSize.y;\n    asp = mix(asp, stretch, step(0.01, stretch));\n    if (asp > 1) {\n        pos.y /= asp;\n        uv.y /= asp;\n    } else {\n        pos.x *= asp;\n        uv.x *= asp;\n    }\n    pos /= scale;\n    uv /= scale;\n    uv -= pos - half2(0.5); // translate\n    return uv;\n}\n\nhalf4 texLight(half2 uv, half2 pos, half rotation, half scale, half stretch) {\n    half2 ruv = relativeUv(rotate(uv, rotation), rotate(pos, rotation), scale, stretch);\n    half4 map = lightMapShader.eval(ruv * uLightMapSize);\n    // TODO alpha should be the actual alpha of the map in future.\n    return half4(map.rgb, length(map.rgb) / sqrt(3));\n}\n\nhalf2 stretchedPos(half2 pos, half scale) {\n    half aspectRatio = uSize.x / uSize.y;\n    half2 dPos = 0.5 * half2(pos - half2(0.5, 0.5));\n    if (scale > 1) {\n        dPos *= scale;\n    }\n    half asp = aspectRatio;\n    dPos.x *= step(aspectRatio, 1);\n    dPos.y *= step(1, aspectRatio);\n    asp = mix(asp, 1 / aspectRatio, step(aspectRatio, 1));\n    return half2(pos + (asp - 1) * dPos);\n}\n\nconst half epsilon = 0.0001;\nhalf3 rgb2hsl(half3 rgb) {\n    half minColor = min(rgb.r, min(rgb.g, rgb.b));\n    half maxColor = max(rgb.r, max(rgb.g, rgb.b));\n    half3 mask = step(rgb.grr, rgb.rgb) * step(rgb.bbg, rgb.rgb);\n    half3 hue = mask * (half3(0, 2, 4) + (rgb.gbr - rgb.brg) / (maxColor - minColor + epsilon)) / 6;\n    return half3(\n            fract(1 + hue.x + hue.y + hue.z), // h \n            (maxColor - minColor) / (1 - abs(minColor + maxColor - 1) + epsilon), // s\n            (minColor + maxColor) * 0.5 // l\n    );\n}\n\nhalf triangular(half x) {\n    half dbx = 2 * x;\n    return mix(dbx, 2 - dbx, step(1, dbx));\n}\n\n// a simple way to compute color delta using HSL Color Space. Imagine a HSL cylinder for deep understanding.\n// TODO do a test with CIELAB color model to mimic human eyes, not just using this model. \nhalf computeColorDelta(half4 c1, half4 c2) {\n    half3 hsl1 = rgb2hsl(c1.rgb * c1.a);\n    half3 hsl2 = rgb2hsl(c2.rgb * c2.a);\n    half dl = abs(hsl1.z - hsl2.z);\n    half ds = abs(hsl1.y - hsl2.y);\n    half dh = abs(hsl1.x - hsl2.x);\n    dh = mix(dh, 1 - dh, step(0.5, dh)) * 2; // for closer angle with normalization\n\n    // apply weights for each channel. this would be a tune point of the color delta model.\n    half w = abs(1 - dl) * smoothstep(0.6, 1, triangular(hsl1.z) * triangular(hsl2.z)); // weight based on delta lightness.\n    ds *= 0.1 * smoothstep(0.1, 0, dh * dl); // apply delta saturation only if other delta is almost zero.                \n    dh *= w; // apply delta lightness as a weight of the hue.\n    return max(dh, max(ds, dl));\n}\n\nhalf4 main(in vec2 fragCoord) {\n    vec2 uv = fragCoord / uSize;\n    half4 viewColor = texView(uv);\n    ", str3, "\n    half2 pos = mix(uLightPosition, stretchedPos(uLightPosition, uLightScale), step(1, half(uLightPositionStretch)));\n\n    // light color\n    half4 lightColor = texLight(uv, pos, uLightRotation, uLightScale, uStretch);\n    lightColor *= uLightIntensity;\n    lightColor = clamp(lightColor, half4(0), half4(1));\n    half3 tintColor = mix(uLightColor.rgb, getTintColor(uv, uSize), useTint());\n    lightColor.rgb *= tintColor;\n    lightColor.rgb *= dither(uv, uDitherVariation);\n    \n    half useDomain = step(0.1, uDomainColor.a);\n    half domainLuminance = clamp(max(computeColorDelta(viewColor, uDomainColor), uDomainDeltaRatio) * uDomainStrength, 0.0, 1.0);\n    lightColor.a = mix(lightColor.a, lightColor.a * domainLuminance, useDomain);\n    lightColor.a = clamp(lightColor.a, 0, 1);\n\n    // reduce tinting light by delta lightness btw tint and view pixels.\n    half tintLightness = length(tintColor.rgb) / sqrt(3);\n    half viewLightness = length(viewColor.rgb * viewColor.a) / sqrt(3);\n    ", str, "\n\n    half3 color = mix(lightColor.rgb, lightColor.rgb * domainLuminance, useDomain);\n    "), str2, "\n}\n                ");
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ProcessingLightRenderEffect(boolean z, ProcessingLightConfig.BlendMode blendMode) {
        super(false, 1, (e) null);
        j.e(blendMode, "blendMode");
        c.f();
        this.shader = c.d(Companion.assembleShader(z, blendMode));
        setLightColor(-1);
        setLightPosition(Companion.Default.INSTANCE.getLightPos());
        setLightScale(2.05f);
        setLightIntensity(1.0f);
        setLightSaturation(1.15f);
        setStretch(1.0f);
        setDomainColor(0);
        setDomainStrength(1.16f);
        setDomainDeltaRatio(0.2f);
    }

    /* access modifiers changed from: private */
    public static final void setDomainColor$lambda$17(int i2, ProcessingLightRenderEffect processingLightRenderEffect, RuntimeShader runtimeShader) {
        Color valueOf = Color.valueOf(i2);
        j.d(valueOf, "valueOf(...)");
        RuntimeShader shader2 = processingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uDomainColor", valueOf.red(), valueOf.green(), valueOf.blue(), valueOf.alpha());
        }
    }

    /* access modifiers changed from: private */
    public static final void setDomainDeltaRatio$lambda$18(ProcessingLightRenderEffect processingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = processingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uDomainDeltaRatio", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setDomainStrength$lambda$19(ProcessingLightRenderEffect processingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = processingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uDomainStrength", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setLightColor$lambda$9(int i2, ProcessingLightRenderEffect processingLightRenderEffect, RuntimeShader runtimeShader) {
        Color valueOf = Color.valueOf(i2);
        j.d(valueOf, "valueOf(...)");
        RuntimeShader shader2 = processingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uLightColor", valueOf.red(), valueOf.green(), valueOf.blue(), valueOf.alpha());
        }
    }

    /* access modifiers changed from: private */
    public static final void setLightIntensity$lambda$15(ProcessingLightRenderEffect processingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = processingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uLightIntensity", f);
        }
    }

    private final void setLightMap(Bitmap bitmap) {
        if (bitmap == null) {
            updateShader(new bd.j(this, 1));
        } else {
            updateShader(new cd.c(this, bitmap, 0));
        }
    }

    /* access modifiers changed from: private */
    public static final void setLightMap$lambda$7(ProcessingLightRenderEffect processingLightRenderEffect, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = processingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uLightMapSize", 0.0f, 0.0f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setLightMap$lambda$8(ProcessingLightRenderEffect processingLightRenderEffect, Bitmap bitmap, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = processingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setInputBuffer("lightMapShader", new BitmapShader(bitmap, Shader.TileMode.DECAL, Shader.TileMode.DECAL));
        }
        RuntimeShader shader3 = processingLightRenderEffect.getShader();
        if (shader3 != null) {
            shader3.setFloatUniform("uLightMapSize", (float) bitmap.getWidth(), (float) bitmap.getHeight());
        }
    }

    /* access modifiers changed from: private */
    public static final void setLightPosition$lambda$11(ProcessingLightRenderEffect processingLightRenderEffect, PointF pointF, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = processingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uLightPosition", pointF.x, pointF.y);
        }
    }

    /* access modifiers changed from: private */
    public static final void setLightRotation$lambda$10(ProcessingLightRenderEffect processingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = processingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uLightRotation", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setLightSaturation$lambda$16(ProcessingLightRenderEffect processingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = processingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uLightSaturation", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setLightScale$lambda$13(ProcessingLightRenderEffect processingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = processingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uLightScale", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setStretch$lambda$14(ProcessingLightRenderEffect processingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = processingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uStretch", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setTintTexture$lambda$4(ProcessingLightRenderEffect processingLightRenderEffect, Bitmap bitmap, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = processingLightRenderEffect.getShader();
        if (shader2 != null) {
            processingLightRenderEffect.setTintTexture(shader2, bitmap);
        }
    }

    public void destroy() {
        super.destroy();
        Companion.Default defaultR = Companion.Default.INSTANCE;
        Bitmap bmpLightmap = defaultR.getBmpLightmap();
        if (bmpLightmap != null) {
            bmpLightmap.recycle();
        }
        defaultR.setBmpLightmap((Bitmap) null);
        setRenderEffect((RenderEffect) null);
        setShader((RuntimeShader) null);
    }

    public RenderEffect getRenderEffect() {
        RuntimeShader shader2 = getShader();
        if (shader2 != null) {
            return RenderEffect.createRuntimeShaderEffect(shader2, "inputShader");
        }
        return null;
    }

    public RuntimeShader getShader() {
        return this.shader;
    }

    public void onInitialize(Context context) {
        j.e(context, StateHandler.KEY_APP_STATE);
        Companion.Default defaultR = Companion.Default.INSTANCE;
        if (defaultR.getBmpLightmap() == null) {
            defaultR.setBmpLightmap(BitmapFactory.decodeResource(context.getResources(), defaultR.getLightmapRes()));
        }
        Bitmap bmpLightmap = defaultR.getBmpLightmap();
        if (bmpLightmap != null) {
            setLightMap(bmpLightmap);
        }
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), defaultR.getColorRes());
        if (decodeResource != null) {
            setTintTexture(decodeResource);
        }
    }

    public final void setDomainColor(int i2) {
        updateShader(new b(i2, this, 1));
    }

    public final void setDomainDeltaRatio(float f) {
        updateShader(new a(this, f, 4));
    }

    public final void setDomainStrength(float f) {
        updateShader(new a(this, f, 0));
    }

    public final void setLightColor(int i2) {
        updateShader(new b(i2, this, 0));
    }

    public final void setLightIntensity(float f) {
        updateShader(new a(this, f, 5));
    }

    public final void setLightPosition(PointF pointF) {
        j.e(pointF, "pos");
        updateShader(new m(this, pointF, 2));
    }

    public final void setLightRotation(float f) {
        updateShader(new a(this, f, 6));
    }

    public final void setLightSaturation(float f) {
        updateShader(new a(this, f, 2));
    }

    public final void setLightScale(float f) {
        updateShader(new a(this, f, 1));
    }

    public void setRenderEffect(RenderEffect renderEffect2) {
        this.renderEffect = renderEffect2;
    }

    public void setShader(RuntimeShader runtimeShader) {
        this.shader = runtimeShader;
    }

    public final void setStretch(float f) {
        updateShader(new a(this, f, 3));
    }

    public void setTintTexture(RuntimeShader runtimeShader, Bitmap bitmap) {
        IColorizable.DefaultImpls.setTintTexture(this, runtimeShader, bitmap);
    }

    public void setTintTexture(Bitmap bitmap) {
        updateShader(new cd.c(this, bitmap, 1));
    }
}
