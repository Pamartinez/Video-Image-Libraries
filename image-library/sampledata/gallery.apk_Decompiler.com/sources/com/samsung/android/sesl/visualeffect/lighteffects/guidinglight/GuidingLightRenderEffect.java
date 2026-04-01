package com.samsung.android.sesl.visualeffect.lighteffects.guidinglight;

import Ee.a;
import Ee.d;
import Tf.o;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RenderEffect;
import android.graphics.RuntimeShader;
import android.graphics.Shader;
import android.util.Log;
import android.view.View;
import bd.k;
import bd.l;
import bd.m;
import bd.n;
import c0.C0086a;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import com.samsung.android.sesl.visualeffect.R$drawable;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.IColorizable;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffectBase;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import o.c;

@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\n\b\u0001\u0018\u0000 g2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001gB\u0019\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0014¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u000f¢\u0006\u0004\b\u0019\u0010\u0012J\u0015\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u000f¢\u0006\u0004\b\u001a\u0010\u0012J\u0015\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000f¢\u0006\u0004\b\u001c\u0010\u0012J\u0015\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u000f¢\u0006\u0004\b\u001e\u0010\u0012J\u0015\u0010 \u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u000f¢\u0006\u0004\b \u0010\u0012J\u0015\u0010#\u001a\u00020\f2\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b#\u0010$J\u0015\u0010&\u001a\u00020\f2\u0006\u0010%\u001a\u00020\u000f¢\u0006\u0004\b&\u0010\u0012J\u0017\u0010)\u001a\u00020\f2\b\u0010(\u001a\u0004\u0018\u00010'¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00020\f2\b\u0010(\u001a\u0004\u0018\u00010'¢\u0006\u0004\b+\u0010*J\u0015\u0010.\u001a\u00020\f2\u0006\u0010-\u001a\u00020,¢\u0006\u0004\b.\u0010/J\u0015\u00100\u001a\u00020\f2\u0006\u0010%\u001a\u00020\u000f¢\u0006\u0004\b0\u0010\u0012J\u0015\u00102\u001a\u00020\f2\u0006\u00101\u001a\u00020\u000f¢\u0006\u0004\b2\u0010\u0012J\u0017\u00105\u001a\u00020\f2\b\b\u0001\u00104\u001a\u000203¢\u0006\u0004\b5\u00106J\u0015\u00107\u001a\u00020\f2\u0006\u00101\u001a\u00020\u000f¢\u0006\u0004\b7\u0010\u0012J\u0015\u00108\u001a\u00020\f2\u0006\u0010%\u001a\u00020\u000f¢\u0006\u0004\b8\u0010\u0012J\u0015\u0010:\u001a\u00020\f2\u0006\u00109\u001a\u00020\u000f¢\u0006\u0004\b:\u0010\u0012J\u0015\u0010;\u001a\u00020\f2\u0006\u0010%\u001a\u00020\u000f¢\u0006\u0004\b;\u0010\u0012J\u0015\u0010<\u001a\u00020\f2\u0006\u00101\u001a\u00020\u000f¢\u0006\u0004\b<\u0010\u0012J\u0015\u0010>\u001a\u00020\f2\u0006\u0010=\u001a\u00020\u000f¢\u0006\u0004\b>\u0010\u0012J\u0015\u0010@\u001a\u00020\f2\u0006\u0010?\u001a\u00020\u000f¢\u0006\u0004\b@\u0010\u0012J#\u0010D\u001a\u00020\f2\b\u0010B\u001a\u0004\u0018\u00010A2\b\u0010C\u001a\u0004\u0018\u00010,H\u0016¢\u0006\u0004\bD\u0010EJ\u0015\u0010G\u001a\u00020\f2\u0006\u0010F\u001a\u00020\u000f¢\u0006\u0004\bG\u0010\u0012J\u0015\u0010I\u001a\u00020\f2\u0006\u0010H\u001a\u00020\u000f¢\u0006\u0004\bI\u0010\u0012J\u000f\u0010J\u001a\u00020\fH\u0016¢\u0006\u0004\bJ\u0010KJ\u0017\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0016\u0010\u0012J\u0017\u0010M\u001a\u00020\f2\u0006\u0010L\u001a\u00020,H\u0002¢\u0006\u0004\bM\u0010/R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010NR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010OR$\u0010Q\u001a\u0004\u0018\u00010P8\u0016@\u0016X\u000e¢\u0006\u0012\n\u0004\bQ\u0010R\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR$\u0010X\u001a\u0004\u0018\u00010W8V@\u0016X\u000e¢\u0006\u0012\n\u0004\bX\u0010Y\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\"\u0010_\u001a\u00020^8\u0016@\u0016X\u000e¢\u0006\u0012\n\u0004\b_\u0010`\u001a\u0004\ba\u0010b\"\u0004\bc\u0010dR\u0018\u0010e\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\be\u0010f¨\u0006h"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightRenderEffect;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/VibeRenderEffectBase;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/IColorizable;", "", "", "roundRectType", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$ShaderPrecision;", "precision", "<init>", "(ZLcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$ShaderPrecision;)V", "Landroid/content/Context;", "appContext", "Lme/x;", "onInitialize", "(Landroid/content/Context;)V", "", "progress", "setProgress", "(F)V", "Landroid/view/View;", "view", "alpha", "setViewAlpha", "(Landroid/view/View;F)V", "stretch", "setStretch", "setStretchDirLit", "saturation", "setSaturation", "luminance", "setGlobalLuminance", "outerSaturation", "setOuterSaturation", "Landroid/graphics/Point;", "borderInPixel", "setBorderWidth", "(Landroid/graphics/Point;)V", "radius", "setViewCornerRadius", "Landroid/graphics/Bitmap;", "bitmap", "setLightmap", "(Landroid/graphics/Bitmap;)V", "setLightmapGlow", "Landroid/graphics/PointF;", "pos", "setLightPos", "(Landroid/graphics/PointF;)V", "setLightRadius", "intensity", "setLightIntensity", "", "color", "setLightColor", "(I)V", "setGlowIntensity", "setGlowRadius", "sharpness", "setGlowSharpness", "setReflLightRadius", "setReflLightIntensity", "albedo", "setReflAlbedo", "ditherVariation", "setDitherVariation", "Landroid/graphics/Shader;", "tintShader", "tintFragSize", "setTintShader", "(Landroid/graphics/Shader;Landroid/graphics/PointF;)V", "size", "setOutlineThickness", "width", "setBoundarySmoothWidth", "destroy", "()V", "center", "setViewCenter", "Z", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$ShaderPrecision;", "Landroid/graphics/RuntimeShader;", "shader", "Landroid/graphics/RuntimeShader;", "getShader", "()Landroid/graphics/RuntimeShader;", "setShader", "(Landroid/graphics/RuntimeShader;)V", "Landroid/graphics/RenderEffect;", "renderEffect", "Landroid/graphics/RenderEffect;", "getRenderEffect", "()Landroid/graphics/RenderEffect;", "setRenderEffect", "(Landroid/graphics/RenderEffect;)V", "", "startProgress", "J", "getStartProgress", "()J", "setStartProgress", "(J)V", "prevViewAlpha", "Ljava/lang/Float;", "Companion", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GuidingLightRenderEffect extends VibeRenderEffectBase implements IColorizable {
    public static final Companion Companion = new Companion((e) null);
    private final GuidingLightConfig.ShaderPrecision precision;
    private Float prevViewAlpha;
    private RenderEffect renderEffect;
    private final boolean roundRectType;
    private RuntimeShader shader;
    private long startProgress;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\t"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightRenderEffect$Companion;", "", "<init>", "()V", "generateShader", "", "precision", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$ShaderPrecision;", "Default", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u0006\u001a\u0004\b\u000f\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0018\u001a\u00020\u00178\u0000XD¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightRenderEffect$Companion$Default;", "", "<init>", "()V", "", "lightmapRes", "I", "getLightmapRes", "()I", "Landroid/graphics/PointF;", "lightPos", "Landroid/graphics/PointF;", "getLightPos", "()Landroid/graphics/PointF;", "lightColor", "getLightColor", "Landroid/graphics/Bitmap;", "bmpLightmap", "Landroid/graphics/Bitmap;", "getBmpLightmap", "()Landroid/graphics/Bitmap;", "setBmpLightmap", "(Landroid/graphics/Bitmap;)V", "", "initialViewAlpha", "F", "getInitialViewAlpha$sesl_visualeffect_INTERNAL_2_1_6_release", "()F", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Default {
            public static final Default INSTANCE = new Default();
            private static Bitmap bmpLightmap;
            private static final float initialViewAlpha = 1.0f;
            private static final int lightColor = Color.parseColor("#FFFFFF");
            private static final PointF lightPos = new PointF(0.5f, 0.5f);
            private static final int lightmapRes = R$drawable.lightmap;

            private Default() {
            }

            public final Bitmap getBmpLightmap() {
                return bmpLightmap;
            }

            public final float getInitialViewAlpha$sesl_visualeffect_INTERNAL_2_1_6_release() {
                return initialViewAlpha;
            }

            public final int getLightColor() {
                return lightColor;
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

        public final String generateShader(GuidingLightConfig.ShaderPrecision shaderPrecision) {
            String str;
            String str2;
            j.e(shaderPrecision, "precision");
            String str3 = "float";
            if (!j.a(shaderPrecision.getColorPrecision(), "lowp") && !j.a(shaderPrecision.getColorPrecision(), "mediump")) {
                str = str3;
            } else {
                str = "half";
            }
            if (!j.a(shaderPrecision.getGeometryPrecision(), "lowp") && !j.a(shaderPrecision.getGeometryPrecision(), "mediump")) {
                str2 = str3;
            } else {
                str2 = "half";
            }
            if (j.a(shaderPrecision.getCoordinatePrecision(), "lowp") || j.a(shaderPrecision.getCoordinatePrecision(), "mediump")) {
                str3 = "half";
            }
            StringBuilder q = C0086a.q("\n\nuniform shader tintShader;\nuniform half2 uTintShaderSize;\n// TODO possible for any transforming with mat3 for trs, but currently just for flipping since there's no requirements at least now.\nuniform half2 uTintFlipDirection; \n\nhalf useTint() {\n    return step(0.01, abs(uTintShaderSize.x * uTintShaderSize.y)); \n}\n    \nhalf4 texTint(half2 uv) {\n    uv = mix(uv, half2(1 - uv.x, uv.y), step(0.5, uTintFlipDirection.x));\n    return tintShader.eval(uv * uTintShaderSize);\n}\n\n// get tint color aligned center\nhalf3 getTintColor(half2 uv, half2 resolution) {\n    half2 guv = uv;\n    half asp = resolution.x / resolution.y;\n    if (asp > 1) {\n        guv.y /= asp;\n        guv.y += 0.5 * (1 - 1 / asp);\n    } else {\n        guv.x *= asp;\n        guv.x += 0.5 * (1 - asp);\n    }\n    return clamp(texTint(guv).rgb, half3(0), half3(1));\n}\n\n// get tint color aligned center\nhalf4 getTintColorAlpha(half2 uv, half2 resolution) {\n    half2 guv = uv;\n    half asp = resolution.x / resolution.y;\n    if (asp > 1) {\n        guv.y /= asp;\n        guv.y += 0.5 * (1 - 1 / asp);\n    } else {\n        guv.x *= asp;\n        guv.x += 0.5 * (1 - asp);\n    }\n    half4 tint = texTint(guv);\n    return clamp(tint, half4(0), half4(1));\n}\n        \nuniform shader inputShader;\nuniform shader lightMapShader;\nuniform shader lightMapGlowShader;\n\nuniform ", str3, "2 uSize;\nuniform ", str, " uProgress;\n\nuniform ");
            C0086a.z(q, str3, "2 uLightMapSize;\nuniform ", str3, "2 uLightMapGlowSize;\n\nuniform ");
            C0086a.z(q, str, " uDitherVariation;\n\n// for view shape\nuniform ", str3, "2 uViewCenter; // normalized value\nuniform ");
            C0086a.z(q, str, " uViewAlpha;\nuniform int uRoundRectShape;\nuniform ", str2, " uCornerRadius;\nuniform ");
            C0086a.z(q, str2, " uOutlineThickness;\nuniform ", str3, "2 uRoundRectDirection;\nuniform ");
            C0086a.z(q, str2, " uCircleRadius;\nuniform ", str3, "2 uBorderWidth; // inset\n\n// directional light \nuniform ");
            C0086a.z(q, str3, "2 uLightPos;\nuniform ", str, " uLightRadius;\nuniform ");
            C0086a.z(q, str, "4 uLightColor;\nuniform ", str, " uLightIntensity;\n\n// glow light\nuniform ");
            C0086a.z(q, str, " uGlowIntensity;\nuniform ", str, " uGlowRadius;\nuniform ");
            C0086a.z(q, str, " uGlowSharpness;\n\n// reflection light\nuniform ", str, " uReflRadius;\nuniform ");
            C0086a.z(q, str, " uReflLightIntensity;\nuniform ", str, " uReflAlbedo;\n\nuniform ");
            C0086a.z(q, str, " uGlobalLuminance;\nuniform ", str, " uOuterSaturation;\nuniform ");
            C0086a.z(q, str, " uSaturation;\n\nuniform ", str, " uStretch;\nuniform ");
            C0086a.z(q, str, " uStretchDirLit;\n\nuniform ", str, " uBoundarySmoothWidth;\n\n// get relative uv based on longer length among width and height of the view.\n");
            C0086a.z(q, str3, "2 relativeUv(", str3, "2 uv, ");
            C0086a.z(q, str3, "2 pos, ", str, " scale, ");
            C0086a.z(q, str, " stretch) {\n    ", str3, " asp = uSize.x / uSize.y;\n    asp = stretch >= 0.01 ? stretch : asp;\n\n    if (asp > 1) {\n    pos.y /= asp;\n    uv.y /= asp;\n    } else {\n    pos.x *= asp;\n    uv.x *= asp;\n    }\n    pos /= scale;\n    uv /= scale;\n    uv -= pos - ");
            C0086a.z(q, str3, "2(0.5);// translate\n    return uv;\n}\n\n", str, "4 texView(");
            C0086a.z(q, str3, "2 uv) {\n    ", str, "4 c = inputShader.eval(uv * uSize);\n    c.rgb *= c.a;\n    return c;\n}\n\n");
            C0086a.z(q, str, " rand(", str3, "2 uv) {\n    return fract(sin(dot(uv, ");
            C0086a.z(q, str3, "2(12.9898, 78.233))) * 43758.5453);\n}\n\n", str, " dither(");
            C0086a.z(q, str3, "2 uv, ", str, " variation) {\n    return 1 + variation * 2 * (rand(uv * 10.0) - 0.5);\n}\n\n");
            C0086a.z(q, str2, " sdRoundRect(vec2 fragCoord, ", str3, "2 center, ");
            C0086a.z(q, str3, "2 size, ", str2, " radius) {\n    return length(max(abs(fragCoord - center) - size + radius, 0.0)) - radius;\n}\n\n");
            C0086a.z(q, str, " lightmap(", str3, "2 uv, ");
            C0086a.z(q, str3, "2 pos, ", str, " scale, ");
            C0086a.z(q, str, " intensity, ", str, " stretch) {\n    uv = relativeUv(uv, pos, scale, stretch);\n    return length(lightMapShader.eval(uv * uLightMapSize).rgb) / sqrt(3) * intensity;\n}\n\n");
            C0086a.z(q, str, " lightmapGlow(", str3, "2 uv, ");
            C0086a.z(q, str3, "2 pos, ", str, " scale, ");
            C0086a.z(q, str, " intensity, ", str, " stretch) {\n    uv = relativeUv(uv, pos, scale, stretch);\n    return length(lightMapGlowShader.eval(uv * uLightMapGlowSize).rgb) / sqrt(3) * intensity;\n}\n\nfloat getRadius(");
            C0086a.z(q, str3, "2 halfViewSize, bool useDirection, bool isCornerDirection) {\n    if (uRoundRectShape == 0) { // circle\n        return uCircleRadius;\n    }\n    \n    if (!useDirection || isCornerDirection) {\n        return min(min(halfViewSize.x, halfViewSize.y), uCornerRadius);\n    }\n\n    return 0.01;\n}\n\nfloat sdf(vec2 fragCoord, ", str3, "2 halfViewSize) {\n    // detect rounded direction\n    vec2 signedQuadrant = fragCoord - uViewCenter * uSize;\n    vec2 dv = uRoundRectDirection * signedQuadrant;\n    bool useDirection = length(uRoundRectDirection) >= 0.1;\n    bool isCornerDirection = dv.x + dv.y >= 1.;\n    float radius = getRadius(halfViewSize, useDirection, isCornerDirection);\n\n    ");
            C0086a.z(q, str2, " dist = sdRoundRect(fragCoord, uViewCenter * uSize, halfViewSize, radius);\n    ", str2, " attenuation = uOutlineThickness;\n\n    return dist / attenuation;\n}\n\n");
            C0086a.z(q, str, "4 main(vec2 fragCoord) {\n    vec2 uv = fragCoord / uSize;\n\n    ", str, "4 view = texView(uv);\n    ");
            C0086a.z(q, str3, "2 halfViewSize = 0.5 * uSize - uBorderWidth;\n    ", str3, " ratioByY = uSize.x / uSize.y;\n    ");
            C0086a.z(q, str3, " minSizeLength = ratioByY >= 1. ? uSize.y : uSize.x;\n\n    // use proper sdf by primitive type of the view.\n    float dist = sdf(fragCoord, halfViewSize);\n\n    // smooth transition factor for inner/outer boundary (0.0 = inner, 1.0 = outer)\n    float outFactor = smoothstep(-uBoundarySmoothWidth, uBoundarySmoothWidth, dist);\n\n    // compute light\n    ", str, " lit = lightmap(uv, uLightPos, uLightRadius, uLightIntensity, uStretchDirLit);\n\n    // compute glow\n    // Note that RoundedRect using direction should have disabled the glow light because of limitation of the sdf.\n    ");
            C0086a.z(q, str, " useDirection = step(0.1, length(uRoundRectDirection));\n    ", str, " glowLit = lightmapGlow(uv, uLightPos, uGlowRadius, uGlowIntensity, uStretch);\n    ");
            C0086a.z(q, str, " glow = (1 - useDirection) * smoothstep(glowLit, 0, abs(dist));\n    glow = pow(glow, uGlowSharpness);\n\n    // compute fakey light reflection by sdf\n    ", str, " reflLit = lightmapGlow(uv, uLightPos, uReflRadius, uReflLightIntensity, uStretch);\n    ");
            C0086a.z(q, str, " distForOut = clamp(dist, 0.0, 0.99);\n    ", str, " outerReflLit = reflLit * clamp(pow(1 - distForOut, 4.5) + 0.1 * (1 - distForOut), 0, 1);\n    ");
            C0086a.z(q, str, " innerReflLit = uReflAlbedo * reflLit;\n    reflLit = mix(innerReflLit, outerReflLit, outFactor);\n    ", str, " refl = smoothstep(uReflRadius, 0, dist);\n\n    // build lights\n    ");
            C0086a.z(q, str, " luminance = max(glow * glowLit, refl * reflLit);\n    // add directional light on the view (smoothly blended at boundary)\n    luminance += mix(lit, 0.0, outFactor);\n    ", str, " alpha = mix(luminance * uGlobalLuminance, view.a, view.a);\n    const ");
            C0086a.z(q, str, " epsilon = 0.0001;\n    if (alpha < epsilon) {\n    return ", str, "4(0, 0, 0, 0);\n    }\n    luminance = clamp(luminance, 0, 1);\n\n    ");
            C0086a.z(q, str, "4 litColor = ", str, "4(0.0);\n    litColor.rgb += luminance * uLightColor.rgb;\n    litColor.rgb *= dither(fract(uv * uProgress), uDitherVariation);\n    litColor.rgb = clamp(litColor.rgb, ");
            C0086a.z(q, str, "3(0), ", str, "3(1));\n\n    // apply color tint\n    ");
            C0086a.z(q, str, "4 tintColor = getTintColorAlpha(uv, uSize);\n    litColor.rgb = mix(litColor.rgb, litColor.rgb * tintColor.rgb, useTint()) * uSaturation;\n    // smooth transition for outer saturation at boundary\n    litColor.rgb = mix(litColor.rgb, litColor.rgb * uOuterSaturation, outFactor);\n    luminance *= tintColor.a;\n    litColor.a = luminance * uGlobalLuminance;\n\n    ", str, "3 color = litColor.rgb * uGlobalLuminance + view.rgb * view.a * (1 - litColor.a);\n    // use premult as default btw lighting and view\n    return ");
            q.append(str);
            q.append("4(color, alpha);\n}\n            ");
            return o.l0(q.toString());
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GuidingLightRenderEffect(boolean z, GuidingLightConfig.ShaderPrecision shaderPrecision) {
        super(false, 1, (e) null);
        j.e(shaderPrecision, "precision");
        this.roundRectType = z;
        this.precision = shaderPrecision;
        c.f();
        this.shader = c.d(Companion.generateShader(shaderPrecision));
        a aVar = d.d;
        float nextFloat = d.d.d().nextFloat() * ((float) 10000);
        this.startProgress = (long) (nextFloat < 1.0f ? 1.0f : nextFloat);
        updateShader(new bd.j(this, 0));
        setViewCenter(new PointF(0.5f, 0.5f));
        Companion.Default defaultR = Companion.Default.INSTANCE;
        setViewAlpha(defaultR.getInitialViewAlpha$sesl_visualeffect_INTERNAL_2_1_6_release());
        setSaturation(1.15f);
        setGlobalLuminance(1.0f);
        setOuterSaturation(0.9f);
        setLightPos(defaultR.getLightPos());
        setLightRadius(1.92f);
        setLightColor(defaultR.getLightColor());
        setLightIntensity(0.28f);
        setGlowIntensity(0.28f);
        setGlowRadius(1.25f);
        setGlowSharpness(36.0f);
        setReflLightIntensity(0.48f);
        setReflLightRadius(1.82f);
        setReflAlbedo(0.0f);
        setProgress((float) getStartProgress());
        setDitherVariation(0.07f);
        setOutlineThickness(48.0f);
        setBoundarySmoothWidth(0.01f);
        setTintShader((Shader) null, (PointF) null);
    }

    /* access modifiers changed from: private */
    public static final void _init_$lambda$1(GuidingLightRenderEffect guidingLightRenderEffect, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setIntUniform("uRoundRectShape", guidingLightRenderEffect.roundRectType ? 1 : 0);
        }
    }

    /* access modifiers changed from: private */
    public static final void setBorderWidth$lambda$11(GuidingLightRenderEffect guidingLightRenderEffect, Point point, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uBorderWidth", (float) point.x, (float) point.y);
        }
    }

    /* access modifiers changed from: private */
    public static final void setBoundarySmoothWidth$lambda$37(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uBoundarySmoothWidth", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setDitherVariation$lambda$28(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uDitherVariation", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setGlobalLuminance$lambda$8(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uGlobalLuminance", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setGlowIntensity$lambda$22(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uGlowIntensity", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setGlowRadius$lambda$23(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uGlowRadius", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setGlowSharpness$lambda$24(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uGlowSharpness", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setLightColor$lambda$21(GuidingLightRenderEffect guidingLightRenderEffect, Color color, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uLightColor", color.red(), color.green(), color.blue(), color.alpha());
        }
    }

    /* access modifiers changed from: private */
    public static final void setLightIntensity$lambda$20(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uLightIntensity", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setLightPos$lambda$18(GuidingLightRenderEffect guidingLightRenderEffect, PointF pointF, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uLightPos", pointF.x, pointF.y);
        }
    }

    /* access modifiers changed from: private */
    public static final void setLightRadius$lambda$19(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uLightRadius", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setLightmap$lambda$15(Bitmap bitmap, GuidingLightRenderEffect guidingLightRenderEffect, RuntimeShader runtimeShader) {
        if (bitmap != null) {
            RuntimeShader shader2 = guidingLightRenderEffect.getShader();
            if (shader2 != null) {
                shader2.setInputBuffer("lightMapShader", new BitmapShader(bitmap, Shader.TileMode.DECAL, Shader.TileMode.DECAL));
            }
            RuntimeShader shader3 = guidingLightRenderEffect.getShader();
            if (shader3 != null) {
                shader3.setFloatUniform("uLightMapSize", (float) bitmap.getWidth(), (float) bitmap.getHeight());
                return;
            }
            return;
        }
        Bitmap bmpLightmap = Companion.Default.INSTANCE.getBmpLightmap();
        if (bmpLightmap != null) {
            RuntimeShader shader4 = guidingLightRenderEffect.getShader();
            if (shader4 != null) {
                shader4.setInputBuffer("lightMapShader", new BitmapShader(bmpLightmap, Shader.TileMode.DECAL, Shader.TileMode.DECAL));
            }
            RuntimeShader shader5 = guidingLightRenderEffect.getShader();
            if (shader5 != null) {
                shader5.setFloatUniform("uLightMapSize", (float) bmpLightmap.getWidth(), (float) bmpLightmap.getHeight());
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void setLightmapGlow$lambda$17(Bitmap bitmap, GuidingLightRenderEffect guidingLightRenderEffect, RuntimeShader runtimeShader) {
        if (bitmap != null) {
            RuntimeShader shader2 = guidingLightRenderEffect.getShader();
            if (shader2 != null) {
                shader2.setInputBuffer("lightMapGlowShader", new BitmapShader(bitmap, Shader.TileMode.DECAL, Shader.TileMode.DECAL));
            }
            RuntimeShader shader3 = guidingLightRenderEffect.getShader();
            if (shader3 != null) {
                shader3.setFloatUniform("uLightMapGlowSize", (float) bitmap.getWidth(), (float) bitmap.getHeight());
                return;
            }
            return;
        }
        Bitmap bmpLightmap = Companion.Default.INSTANCE.getBmpLightmap();
        if (bmpLightmap != null) {
            RuntimeShader shader4 = guidingLightRenderEffect.getShader();
            if (shader4 != null) {
                shader4.setInputBuffer("lightMapGlowShader", new BitmapShader(bmpLightmap, Shader.TileMode.DECAL, Shader.TileMode.DECAL));
            }
            RuntimeShader shader5 = guidingLightRenderEffect.getShader();
            if (shader5 != null) {
                shader5.setFloatUniform("uLightMapGlowSize", (float) bmpLightmap.getWidth(), (float) bmpLightmap.getHeight());
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void setOuterSaturation$lambda$9(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uOuterSaturation", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setOutlineThickness$lambda$36(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uOutlineThickness", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setProgress$lambda$3(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uProgress", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setReflAlbedo$lambda$27(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uReflAlbedo", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setReflLightIntensity$lambda$26(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uReflLightIntensity", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setReflLightRadius$lambda$25(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uReflRadius", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setSaturation$lambda$7(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uSaturation", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setStretch$lambda$5(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uStretch", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setStretchDirLit$lambda$6(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uStretchDirLit", f);
        }
    }

    /* access modifiers changed from: private */
    public static final void setTintShader$lambda$33(GuidingLightRenderEffect guidingLightRenderEffect, Shader shader2, PointF pointF, RuntimeShader runtimeShader) {
        RuntimeShader shader3 = guidingLightRenderEffect.getShader();
        if (shader3 != null) {
            guidingLightRenderEffect.setTintShaderUniforms(shader3, shader2, pointF);
        }
    }

    /* access modifiers changed from: private */
    public static final void setViewAlpha$lambda$4(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uViewAlpha", f);
        }
        guidingLightRenderEffect.prevViewAlpha = Float.valueOf(f);
    }

    private final void setViewCenter(PointF pointF) {
        updateShader(new n(this, pointF, 1));
    }

    /* access modifiers changed from: private */
    public static final void setViewCenter$lambda$10(GuidingLightRenderEffect guidingLightRenderEffect, PointF pointF, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uViewCenter", pointF.x, pointF.y);
        }
    }

    /* access modifiers changed from: private */
    public static final void setViewCornerRadius$lambda$13(GuidingLightRenderEffect guidingLightRenderEffect, float f, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = guidingLightRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uCornerRadius", f);
        }
    }

    public void destroy() {
        super.destroy();
        Log.i("VibeRenderEffectBase", "destroy");
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

    public long getStartProgress() {
        return this.startProgress;
    }

    public void onInitialize(Context context) {
        j.e(context, StateHandler.KEY_APP_STATE);
        Companion.Default defaultR = Companion.Default.INSTANCE;
        if (defaultR.getBmpLightmap() == null) {
            defaultR.setBmpLightmap(BitmapFactory.decodeResource(context.getResources(), defaultR.getLightmapRes()));
        }
        Bitmap bmpLightmap = defaultR.getBmpLightmap();
        if (bmpLightmap != null) {
            setLightmap(bmpLightmap);
            setLightmapGlow(bmpLightmap);
        }
    }

    public final void setBorderWidth(Point point) {
        j.e(point, "borderInPixel");
        updateShader(new m(this, point, 1));
    }

    public final void setBoundarySmoothWidth(float f) {
        updateShader(new k(this, f, 7));
    }

    public final void setDitherVariation(float f) {
        updateShader(new k(this, f, 4));
    }

    public final void setGlobalLuminance(float f) {
        updateShader(new k(this, f, 10));
    }

    public final void setGlowIntensity(float f) {
        updateShader(new k(this, f, 14));
    }

    public final void setGlowRadius(float f) {
        updateShader(new k(this, f, 15));
    }

    public final void setGlowSharpness(float f) {
        updateShader(new k(this, f, 6));
    }

    public final void setLightColor(int i2) {
        Color valueOf = Color.valueOf(i2);
        j.d(valueOf, "valueOf(...)");
        updateShader(new m(this, valueOf, 0));
    }

    public final void setLightIntensity(float f) {
        updateShader(new k(this, f, 2));
    }

    public final void setLightPos(PointF pointF) {
        j.e(pointF, "pos");
        updateShader(new n(this, pointF, 0));
    }

    public final void setLightRadius(float f) {
        updateShader(new k(this, f, 16));
    }

    public final void setLightmap(Bitmap bitmap) {
        updateShader(new l(bitmap, this, 0));
    }

    public final void setLightmapGlow(Bitmap bitmap) {
        updateShader(new l(bitmap, this, 1));
    }

    public final void setOuterSaturation(float f) {
        updateShader(new k(this, f, 8));
    }

    public final void setOutlineThickness(float f) {
        updateShader(new k(this, f, 1));
    }

    public void setProgress(float f) {
        updateShader(new k(this, f, 12));
    }

    public final void setReflAlbedo(float f) {
        updateShader(new k(this, f, 9));
    }

    public final void setReflLightIntensity(float f) {
        updateShader(new k(this, f, 3));
    }

    public final void setReflLightRadius(float f) {
        updateShader(new k(this, f, 13));
    }

    public void setRenderEffect(RenderEffect renderEffect2) {
        this.renderEffect = renderEffect2;
    }

    public final void setSaturation(float f) {
        updateShader(new k(this, f, 18));
    }

    public void setShader(RuntimeShader runtimeShader) {
        this.shader = runtimeShader;
    }

    public final void setStretch(float f) {
        updateShader(new k(this, f, 11));
    }

    public final void setStretchDirLit(float f) {
        updateShader(new k(this, f, 17));
    }

    public void setTintShader(Shader shader2, PointF pointF) {
        updateShader(new bd.o(this, shader2, pointF));
    }

    public void setTintShaderUniforms(RuntimeShader runtimeShader, Shader shader2, PointF pointF) {
        IColorizable.DefaultImpls.setTintShaderUniforms(this, runtimeShader, shader2, pointF);
    }

    public void setViewAlpha(View view, float f) {
        j.e(view, "view");
        setViewAlpha(f);
    }

    public final void setViewCornerRadius(float f) {
        updateShader(new k(this, f, 0));
    }

    private final void setViewAlpha(float f) {
        Float f5 = this.prevViewAlpha;
        if (f5 == null || Float.compare(f5.floatValue(), f) != 0) {
            updateShader(new k(this, f, 5));
        }
    }
}
