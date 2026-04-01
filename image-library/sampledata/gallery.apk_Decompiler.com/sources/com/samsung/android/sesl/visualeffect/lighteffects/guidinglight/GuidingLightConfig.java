package com.samsung.android.sesl.visualeffect.lighteffects.guidinglight;

import android.graphics.PointF;
import c0.C0086a;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.OCDHelperConstant;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sesl.visualeffect.lighteffects.common.config.BaseVibeEffectConfig;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect;
import com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.PresetEffectColor;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\bQ\b\b\u0018\u0000 }2\u00020\u0001:\u0005~\u0001}B\u0002\b\u0007\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\n\u0012\b\b\u0002\u0010\u000e\u001a\u00020\n\u0012\b\b\u0002\u0010\u000f\u001a\u00020\n\u0012\b\b\u0002\u0010\u0010\u001a\u00020\n\u0012\b\b\u0002\u0010\u0011\u001a\u00020\n\u0012\b\b\u0002\u0010\u0012\u001a\u00020\n\u0012\b\b\u0002\u0010\u0013\u001a\u00020\n\u0012\b\b\u0002\u0010\u0014\u001a\u00020\n\u0012\b\b\u0002\u0010\u0015\u001a\u00020\n\u0012\b\b\u0002\u0010\u0016\u001a\u00020\n\u0012\b\b\u0002\u0010\u0017\u001a\u00020\n\u0012\b\b\u0002\u0010\u0018\u001a\u00020\n\u0012\b\b\u0002\u0010\u0019\u001a\u00020\n\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u001c\u001a\u00020\n\u0012\b\b\u0002\u0010\u001d\u001a\u00020\n\u0012\b\b\u0002\u0010\u001e\u001a\u00020\n\u0012\b\b\u0002\u0010 \u001a\u00020\u001f\u0012\b\b\u0002\u0010!\u001a\u00020\n\u0012\b\b\u0002\u0010#\u001a\u00020\"¢\u0006\u0004\b$\u0010%J\u0002\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\n2\b\b\u0002\u0010\u0011\u001a\u00020\n2\b\b\u0002\u0010\u0012\u001a\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\n2\b\b\u0002\u0010\u0015\u001a\u00020\n2\b\b\u0002\u0010\u0016\u001a\u00020\n2\b\b\u0002\u0010\u0017\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\n2\b\b\u0002\u0010\u0019\u001a\u00020\n2\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\n2\b\b\u0002\u0010\u001d\u001a\u00020\n2\b\b\u0002\u0010\u001e\u001a\u00020\n2\b\b\u0002\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010!\u001a\u00020\n2\b\b\u0002\u0010#\u001a\u00020\"HÆ\u0001¢\u0006\u0004\b&\u0010'J\u0010\u0010)\u001a\u00020(HÖ\u0001¢\u0006\u0004\b)\u0010*J\u0010\u0010,\u001a\u00020+HÖ\u0001¢\u0006\u0004\b,\u0010-J\u001a\u00101\u001a\u0002002\b\u0010/\u001a\u0004\u0018\u00010.HÖ\u0003¢\u0006\u0004\b1\u00102R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0003\u00103\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u00108\u001a\u0004\b9\u0010:R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\"\u0010\t\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\t\u0010@\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\"\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010E\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\"\u0010\f\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\f\u0010E\u001a\u0004\bJ\u0010G\"\u0004\bK\u0010IR\"\u0010\r\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\r\u0010E\u001a\u0004\bL\u0010G\"\u0004\bM\u0010IR\"\u0010\u000e\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010E\u001a\u0004\bN\u0010G\"\u0004\bO\u0010IR\"\u0010\u000f\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010E\u001a\u0004\bP\u0010G\"\u0004\bQ\u0010IR\"\u0010\u0010\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010E\u001a\u0004\bR\u0010G\"\u0004\bS\u0010IR\"\u0010\u0011\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010E\u001a\u0004\bT\u0010G\"\u0004\bU\u0010IR\"\u0010\u0012\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010E\u001a\u0004\bV\u0010G\"\u0004\bW\u0010IR\"\u0010\u0013\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010E\u001a\u0004\bX\u0010G\"\u0004\bY\u0010IR\"\u0010\u0014\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010E\u001a\u0004\bZ\u0010G\"\u0004\b[\u0010IR\"\u0010\u0015\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010E\u001a\u0004\b\\\u0010G\"\u0004\b]\u0010IR\"\u0010\u0016\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010E\u001a\u0004\b^\u0010G\"\u0004\b_\u0010IR\"\u0010\u0017\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010E\u001a\u0004\b`\u0010G\"\u0004\ba\u0010IR\"\u0010\u0018\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010E\u001a\u0004\bb\u0010G\"\u0004\bc\u0010IR\"\u0010\u0019\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010E\u001a\u0004\bd\u0010G\"\u0004\be\u0010IR\"\u0010\u001b\u001a\u00020\u001a8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010f\u001a\u0004\bg\u0010h\"\u0004\bi\u0010jR\"\u0010\u001c\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010E\u001a\u0004\bk\u0010G\"\u0004\bl\u0010IR\"\u0010\u001d\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010E\u001a\u0004\bm\u0010G\"\u0004\bn\u0010IR\"\u0010\u001e\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010E\u001a\u0004\bo\u0010G\"\u0004\bp\u0010IR\"\u0010 \u001a\u00020\u001f8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b \u0010q\u001a\u0004\br\u0010s\"\u0004\bt\u0010uR\"\u0010!\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b!\u0010E\u001a\u0004\bv\u0010G\"\u0004\bw\u0010IR\"\u0010#\u001a\u00020\"8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b#\u0010x\u001a\u0004\by\u0010z\"\u0004\b{\u0010|¨\u0006\u0001"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/config/BaseVibeEffectConfig;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$Shape;", "shape", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$RoundRectDirection;", "roundRectDirection", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor$State;", "colorState", "Landroid/graphics/PointF;", "lightPos", "", "lightRadius", "lightIntensity", "glowIntensity", "glowRadius", "glowSharpness", "reflLightIntensity", "reflLightRadius", "reflAlbedo", "globalLuminance", "ditherVariation", "saturation", "outerSaturation", "stretch", "stretchDirLit", "initialViewAlpha", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$Movement;", "lightMovement", "frameRate", "outlineThickness", "boundarySmoothWidth", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$ShaderPrecision;", "shaderPrecision", "touchIntensity", "", "lightMovementInterval", "<init>", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$Shape;Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$RoundRectDirection;Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor$State;Landroid/graphics/PointF;FFFFFFFFFFFFFFFLcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$Movement;FFFLcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$ShaderPrecision;FJ)V", "copy", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$Shape;Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$RoundRectDirection;Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor$State;Landroid/graphics/PointF;FFFFFFFFFFFFFFFLcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$Movement;FFFLcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$ShaderPrecision;FJ)Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$Shape;", "getShape", "()Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$Shape;", "setShape", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$Shape;)V", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$RoundRectDirection;", "getRoundRectDirection", "()Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$RoundRectDirection;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor$State;", "getColorState", "()Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor$State;", "setColorState", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor$State;)V", "Landroid/graphics/PointF;", "getLightPos", "()Landroid/graphics/PointF;", "setLightPos", "(Landroid/graphics/PointF;)V", "F", "getLightRadius", "()F", "setLightRadius", "(F)V", "getLightIntensity", "setLightIntensity", "getGlowIntensity", "setGlowIntensity", "getGlowRadius", "setGlowRadius", "getGlowSharpness", "setGlowSharpness", "getReflLightIntensity", "setReflLightIntensity", "getReflLightRadius", "setReflLightRadius", "getReflAlbedo", "setReflAlbedo", "getGlobalLuminance", "setGlobalLuminance", "getDitherVariation", "setDitherVariation", "getSaturation", "setSaturation", "getOuterSaturation", "setOuterSaturation", "getStretch", "setStretch", "getStretchDirLit", "setStretchDirLit", "getInitialViewAlpha", "setInitialViewAlpha", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$Movement;", "getLightMovement", "()Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$Movement;", "setLightMovement", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$Movement;)V", "getFrameRate", "setFrameRate", "getOutlineThickness", "setOutlineThickness", "getBoundarySmoothWidth", "setBoundarySmoothWidth", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$ShaderPrecision;", "getShaderPrecision", "()Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$ShaderPrecision;", "setShaderPrecision", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$ShaderPrecision;)V", "getTouchIntensity", "setTouchIntensity", "J", "getLightMovementInterval", "()J", "setLightMovementInterval", "(J)V", "Companion", "Shape", "RoundRectDirection", "ShaderPrecision", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GuidingLightConfig extends BaseVibeEffectConfig {
    public static final GuidingLightConfig ACTION_DARK;
    public static final GuidingLightConfig ACTION_LIGHT;
    public static final GuidingLightConfig BUTTON_DARK;
    public static final GuidingLightConfig BUTTON_LIGHT;
    public static final Companion Companion = new Companion((e) null);
    public static final GuidingLightConfig DEFAULT = new GuidingLightConfig((Shape) null, (RoundRectDirection) null, (PresetEffectColor.State) null, (PointF) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, (GuidingLightEffect.Movement) null, 0.0f, 0.0f, 0.0f, (ShaderPrecision) null, 0.0f, 0, 67108863, (e) null);
    public static final GuidingLightConfig DEFAULT_DARK;
    public static final GuidingLightConfig DEFAULT_LIGHT;
    public static final GuidingLightConfig NUDGE;
    public static final GuidingLightConfig PROCESSING_85;
    public static final GuidingLightConfig RESULT;
    public static final GuidingLightConfig RESULT_DARK;
    public static final GuidingLightConfig RESULT_LIGHT;
    /* access modifiers changed from: private */
    public static final GuidingLightConfig VI_CONFIG_DEFAULT = new GuidingLightConfig((Shape) null, (RoundRectDirection) null, (PresetEffectColor.State) null, (PointF) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, (GuidingLightEffect.Movement) null, 0.0f, 0.0f, 0.0f, (ShaderPrecision) null, 0.0f, 0, 67108863, (e) null);
    /* access modifiers changed from: private */
    public static final GuidingLightConfig VI_CONFIG_DEFAULT_DARK;
    /* access modifiers changed from: private */
    public static final GuidingLightConfig VI_CONFIG_DEFAULT_LIGHT;
    private float boundarySmoothWidth;
    private PresetEffectColor.State colorState;
    private float ditherVariation;
    private float frameRate;
    private float globalLuminance;
    private float glowIntensity;
    private float glowRadius;
    private float glowSharpness;
    private float initialViewAlpha;
    private float lightIntensity;
    private GuidingLightEffect.Movement lightMovement;
    private long lightMovementInterval;
    private PointF lightPos;
    private float lightRadius;
    private float outerSaturation;
    private float outlineThickness;
    private float reflAlbedo;
    private float reflLightIntensity;
    private float reflLightRadius;
    private final RoundRectDirection roundRectDirection;
    private float saturation;
    private ShaderPrecision shaderPrecision;
    private Shape shape;
    private float stretch;
    private float stretchDirLit;
    private float touchIntensity;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\f\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u0012\u0004\b\t\u0010\u0003\u001a\u0004\b\u0007\u0010\bR \u0010\n\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0012\n\u0004\b\n\u0010\u0006\u0012\u0004\b\f\u0010\u0003\u001a\u0004\b\u000b\u0010\bR \u0010\r\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0012\n\u0004\b\r\u0010\u0006\u0012\u0004\b\u000f\u0010\u0003\u001a\u0004\b\u000e\u0010\bR\u0014\u0010\u0011\u001a\u00020\u00108\u0006XT¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0006XT¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0006R\u0014\u0010\u0018\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0006R\u0014\u0010\u0019\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u0006R\u0014\u0010\u001a\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u0006R\u0014\u0010\u001b\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u0006R\u0014\u0010\u001c\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u0006R\u0014\u0010\u001d\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u0006R\u0014\u0010\u001e\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u0006¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$Companion;", "", "<init>", "()V", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;", "VI_CONFIG_DEFAULT", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;", "getVI_CONFIG_DEFAULT", "()Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;", "getVI_CONFIG_DEFAULT$annotations", "VI_CONFIG_DEFAULT_LIGHT", "getVI_CONFIG_DEFAULT_LIGHT", "getVI_CONFIG_DEFAULT_LIGHT$annotations", "VI_CONFIG_DEFAULT_DARK", "getVI_CONFIG_DEFAULT_DARK", "getVI_CONFIG_DEFAULT_DARK$annotations", "", "TAG", "Ljava/lang/String;", "", "TOUCH_INTENSITY", "F", "BUTTON_LIGHT", "BUTTON_DARK", "DEFAULT_LIGHT", "DEFAULT_DARK", "ACTION_LIGHT", "ACTION_DARK", "RESULT_LIGHT", "RESULT_DARK", "NUDGE", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final GuidingLightConfig getVI_CONFIG_DEFAULT() {
            return GuidingLightConfig.VI_CONFIG_DEFAULT;
        }

        public final GuidingLightConfig getVI_CONFIG_DEFAULT_DARK() {
            return GuidingLightConfig.VI_CONFIG_DEFAULT_DARK;
        }

        public final GuidingLightConfig getVI_CONFIG_DEFAULT_LIGHT() {
            return GuidingLightConfig.VI_CONFIG_DEFAULT_LIGHT;
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\f\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$RoundRectDirection;", "", "Landroid/graphics/PointF;", "vector", "<init>", "(Ljava/lang/String;ILandroid/graphics/PointF;)V", "Landroid/graphics/PointF;", "getVector", "()Landroid/graphics/PointF;", "ALL", "UP", "RIGHT", "DOWN", "LEFT", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum RoundRectDirection {
        ALL(new PointF(0.0f, 0.0f)),
        UP(new PointF(0.0f, -1.0f)),
        RIGHT(new PointF(1.0f, 0.0f)),
        DOWN(new PointF(0.0f, 1.0f)),
        LEFT(new PointF(-1.0f, 0.0f));
        
        private final PointF vector;

        static {
            RoundRectDirection[] $values;
            $ENTRIES = c.t($values);
        }

        private RoundRectDirection(PointF pointF) {
            this.vector = pointF;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B1\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u000e\u001a\u0004\b\u0011\u0010\u0010R\u0017\u0010\u0007\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\b\u0010\u000e\u001a\u0004\b\u0013\u0010\u0010j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$ShaderPrecision;", "", "", "level", "", "description", "colorPrecision", "geometryPrecision", "coordinatePrecision", "<init>", "(Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "I", "getLevel", "()I", "Ljava/lang/String;", "getDescription", "()Ljava/lang/String;", "getColorPrecision", "getGeometryPrecision", "getCoordinatePrecision", "LEVEL_0", "LEVEL_1", "LEVEL_2", "LEVEL_3", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ShaderPrecision {
        LEVEL_0(0, "Ultra Low Power", "lowp", "lowp", "mediump"),
        LEVEL_1(1, "Low Power", "lowp", "mediump", "mediump"),
        LEVEL_2(2, "Balanced", "mediump", "highp", "highp"),
        LEVEL_3(3, "High Quality", "highp", "highp", "highp");
        
        private final String colorPrecision;
        private final String coordinatePrecision;
        private final String description;
        private final String geometryPrecision;
        private final int level;

        static {
            ShaderPrecision[] $values;
            $ENTRIES = c.t($values);
        }

        private ShaderPrecision(int i2, String str, String str2, String str3, String str4) {
            this.level = i2;
            this.description = str;
            this.colorPrecision = str2;
            this.geometryPrecision = str3;
            this.coordinatePrecision = str4;
        }

        public final String getColorPrecision() {
            return this.colorPrecision;
        }

        public final String getCoordinatePrecision() {
            return this.coordinatePrecision;
        }

        public final String getGeometryPrecision() {
            return this.geometryPrecision;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig$Shape;", "", "<init>", "(Ljava/lang/String;I)V", "RoundRect", "Circle", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Shape {
        RoundRect,
        Circle;

        static {
            Shape[] $values;
            $ENTRIES = c.t($values);
        }
    }

    static {
        GuidingLightConfig guidingLightConfig = new GuidingLightConfig((Shape) null, (RoundRectDirection) null, (PresetEffectColor.State) null, (PointF) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, (GuidingLightEffect.Movement) null, 0.0f, 0.0f, 0.0f, (ShaderPrecision) null, 0.0f, 0, 67108863, (e) null);
        guidingLightConfig.saturation = 1.15f;
        VI_CONFIG_DEFAULT_LIGHT = guidingLightConfig;
        GuidingLightConfig guidingLightConfig2 = new GuidingLightConfig((Shape) null, (RoundRectDirection) null, (PresetEffectColor.State) null, (PointF) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, (GuidingLightEffect.Movement) null, 0.0f, 0.0f, 0.0f, (ShaderPrecision) null, 0.0f, 0, 67108863, (e) null);
        guidingLightConfig2.saturation = 1.25f;
        VI_CONFIG_DEFAULT_DARK = guidingLightConfig2;
        GuidingLightConfig guidingLightConfig3 = new GuidingLightConfig((Shape) null, (RoundRectDirection) null, (PresetEffectColor.State) null, (PointF) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, (GuidingLightEffect.Movement) null, 0.0f, 0.0f, 0.0f, (ShaderPrecision) null, 0.0f, 0, 67108863, (e) null);
        guidingLightConfig3.lightRadius = 3.0f;
        guidingLightConfig3.lightIntensity = 0.3f;
        guidingLightConfig3.glowIntensity = 0.2f;
        guidingLightConfig3.glowRadius = 2.0f;
        guidingLightConfig3.glowSharpness = 20.0f;
        guidingLightConfig3.reflLightIntensity = 0.85f;
        guidingLightConfig3.reflLightRadius = 2.0f;
        guidingLightConfig3.reflAlbedo = 0.15f;
        guidingLightConfig3.ditherVariation = 0.0f;
        guidingLightConfig3.globalLuminance = 1.1f;
        guidingLightConfig3.saturation = 1.16f;
        guidingLightConfig3.outerSaturation = 1.03f;
        guidingLightConfig3.stretch = 1.0f;
        guidingLightConfig3.stretchDirLit = 0.0f;
        guidingLightConfig3.outlineThickness = 75.0f;
        guidingLightConfig3.colorState = PresetEffectColor.State.Button;
        GuidingLightEffect.Movement movement = GuidingLightEffect.Movement.NONE;
        guidingLightConfig3.lightMovement = movement;
        BUTTON_LIGHT = guidingLightConfig3;
        GuidingLightConfig copy$default = copy$default(guidingLightConfig3, (Shape) null, (RoundRectDirection) null, (PresetEffectColor.State) null, (PointF) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, (GuidingLightEffect.Movement) null, 0.0f, 0.0f, 0.0f, (ShaderPrecision) null, 0.0f, 0, 67108863, (Object) null);
        copy$default.lightIntensity = 0.5f;
        copy$default.glowSharpness = 30.0f;
        copy$default.globalLuminance = 1.15f;
        copy$default.outerSaturation = 0.5f;
        copy$default.outlineThickness = 80.0f;
        BUTTON_DARK = copy$default;
        DEFAULT_LIGHT = guidingLightConfig3;
        DEFAULT_DARK = copy$default;
        GuidingLightConfig guidingLightConfig4 = new GuidingLightConfig((Shape) null, (RoundRectDirection) null, (PresetEffectColor.State) null, (PointF) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, (GuidingLightEffect.Movement) null, 0.0f, 0.0f, 0.0f, (ShaderPrecision) null, 0.0f, 0, 67108863, (e) null);
        guidingLightConfig4.lightRadius = 3.0f;
        guidingLightConfig4.lightIntensity = 0.3f;
        guidingLightConfig4.glowIntensity = 0.2f;
        guidingLightConfig4.glowRadius = 2.0f;
        guidingLightConfig4.glowSharpness = 20.0f;
        guidingLightConfig4.reflLightIntensity = 0.85f;
        guidingLightConfig4.reflLightRadius = 2.0f;
        guidingLightConfig4.reflAlbedo = 0.15f;
        guidingLightConfig4.ditherVariation = 0.0f;
        guidingLightConfig4.globalLuminance = 1.0f;
        guidingLightConfig4.saturation = 1.16f;
        guidingLightConfig4.outerSaturation = 1.03f;
        guidingLightConfig4.stretch = 1.0f;
        guidingLightConfig4.stretchDirLit = 0.0f;
        guidingLightConfig4.outlineThickness = 45.0f;
        guidingLightConfig4.colorState = PresetEffectColor.State.Action;
        ACTION_LIGHT = guidingLightConfig4;
        GuidingLightConfig copy$default2 = copy$default(guidingLightConfig4, (Shape) null, (RoundRectDirection) null, (PresetEffectColor.State) null, (PointF) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, (GuidingLightEffect.Movement) null, 0.0f, 0.0f, 0.0f, (ShaderPrecision) null, 0.0f, 0, 67108863, (Object) null);
        copy$default2.lightIntensity = 0.5f;
        copy$default2.glowIntensity = 0.3f;
        copy$default2.glowSharpness = 25.0f;
        copy$default2.globalLuminance = 1.0f;
        copy$default2.outerSaturation = 0.6f;
        ACTION_DARK = copy$default2;
        GuidingLightConfig guidingLightConfig5 = new GuidingLightConfig((Shape) null, (RoundRectDirection) null, (PresetEffectColor.State) null, (PointF) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, (GuidingLightEffect.Movement) null, 0.0f, 0.0f, 0.0f, (ShaderPrecision) null, 0.0f, 0, 67108863, (e) null);
        guidingLightConfig5.lightRadius = 3.0f;
        guidingLightConfig5.lightIntensity = 0.3f;
        guidingLightConfig5.glowIntensity = 0.2f;
        guidingLightConfig5.glowRadius = 2.0f;
        guidingLightConfig5.glowSharpness = 20.0f;
        guidingLightConfig5.reflLightIntensity = 0.85f;
        guidingLightConfig5.reflLightRadius = 2.0f;
        guidingLightConfig5.reflAlbedo = 0.15f;
        guidingLightConfig5.ditherVariation = 0.0f;
        guidingLightConfig5.globalLuminance = 1.0f;
        guidingLightConfig5.saturation = 1.16f;
        guidingLightConfig5.outerSaturation = 1.03f;
        guidingLightConfig5.stretch = 1.0f;
        guidingLightConfig5.stretchDirLit = 0.0f;
        guidingLightConfig5.outlineThickness = 0.0f;
        guidingLightConfig5.colorState = PresetEffectColor.State.Processing85;
        PROCESSING_85 = guidingLightConfig5;
        GuidingLightConfig guidingLightConfig6 = copy$default2;
        GuidingLightConfig copy$default3 = copy$default(guidingLightConfig4, (Shape) null, (RoundRectDirection) null, (PresetEffectColor.State) null, (PointF) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, (GuidingLightEffect.Movement) null, 0.0f, 0.0f, 0.0f, (ShaderPrecision) null, 0.0f, 0, 67108863, (Object) null);
        copy$default3.lightPos = new PointF(0.5f, -0.1f);
        copy$default3.lightRadius = 2.2f;
        copy$default3.lightIntensity = 0.5f;
        copy$default3.outlineThickness = 45.0f;
        PresetEffectColor.State state = PresetEffectColor.State.Result;
        copy$default3.colorState = state;
        GuidingLightEffect.Movement movement2 = movement;
        copy$default3.lightMovement = movement2;
        RESULT_LIGHT = copy$default3;
        GuidingLightConfig copy$default4 = copy$default(guidingLightConfig6, (Shape) null, (RoundRectDirection) null, (PresetEffectColor.State) null, (PointF) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, (GuidingLightEffect.Movement) null, 0.0f, 0.0f, 0.0f, (ShaderPrecision) null, 0.0f, 0, 67108863, (Object) null);
        copy$default4.lightPos = new PointF(0.5f, -0.1f);
        copy$default4.lightRadius = 2.2f;
        copy$default4.lightIntensity = 0.25f;
        copy$default4.colorState = state;
        copy$default4.lightMovement = movement2;
        RESULT_DARK = copy$default4;
        RESULT = copy$default(copy$default3, (Shape) null, (RoundRectDirection) null, (PresetEffectColor.State) null, (PointF) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, (GuidingLightEffect.Movement) null, 0.0f, 0.0f, 0.0f, (ShaderPrecision) null, 0.0f, 0, 67108863, (Object) null);
        GuidingLightConfig guidingLightConfig7 = new GuidingLightConfig((Shape) null, (RoundRectDirection) null, (PresetEffectColor.State) null, (PointF) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, (GuidingLightEffect.Movement) null, 0.0f, 0.0f, 0.0f, (ShaderPrecision) null, 0.0f, 0, 67108863, (e) null);
        guidingLightConfig7.lightRadius = 3.0f;
        guidingLightConfig7.lightIntensity = 0.15f;
        guidingLightConfig7.glowIntensity = 0.2f;
        guidingLightConfig7.glowRadius = 2.0f;
        guidingLightConfig7.glowSharpness = 20.0f;
        guidingLightConfig7.reflLightIntensity = 0.45f;
        guidingLightConfig7.reflLightRadius = 1.85f;
        guidingLightConfig7.reflAlbedo = 0.15f;
        guidingLightConfig7.ditherVariation = 0.0f;
        guidingLightConfig7.globalLuminance = 1.1f;
        guidingLightConfig7.saturation = 1.0f;
        guidingLightConfig7.outerSaturation = 1.0f;
        guidingLightConfig7.stretch = 1.0f;
        guidingLightConfig7.stretchDirLit = 0.0f;
        guidingLightConfig7.outlineThickness = 35.0f;
        guidingLightConfig7.colorState = PresetEffectColor.State.Nudge;
        NUDGE = guidingLightConfig7;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ GuidingLightConfig(com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig.Shape r29, com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig.RoundRectDirection r30, com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.PresetEffectColor.State r31, android.graphics.PointF r32, float r33, float r34, float r35, float r36, float r37, float r38, float r39, float r40, float r41, float r42, float r43, float r44, float r45, float r46, float r47, com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect.Movement r48, float r49, float r50, float r51, com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig.ShaderPrecision r52, float r53, long r54, int r56, kotlin.jvm.internal.e r57) {
        /*
            r28 = this;
            r0 = r56
            r1 = r0 & 1
            if (r1 == 0) goto L_0x0009
            com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig$Shape r1 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig.Shape.RoundRect
            goto L_0x000b
        L_0x0009:
            r1 = r29
        L_0x000b:
            r2 = r0 & 2
            if (r2 == 0) goto L_0x0012
            com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig$RoundRectDirection r2 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig.RoundRectDirection.ALL
            goto L_0x0014
        L_0x0012:
            r2 = r30
        L_0x0014:
            r3 = r0 & 4
            if (r3 == 0) goto L_0x001b
            com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.PresetEffectColor$State r3 = com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.PresetEffectColor.State.Common
            goto L_0x001d
        L_0x001b:
            r3 = r31
        L_0x001d:
            r4 = r0 & 8
            if (r4 == 0) goto L_0x0028
            com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightRenderEffect$Companion$Default r4 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightRenderEffect.Companion.Default.INSTANCE
            android.graphics.PointF r4 = r4.getLightPos()
            goto L_0x002a
        L_0x0028:
            r4 = r32
        L_0x002a:
            r5 = r0 & 16
            if (r5 == 0) goto L_0x0032
            r5 = 1073070735(0x3ff5c28f, float:1.92)
            goto L_0x0034
        L_0x0032:
            r5 = r33
        L_0x0034:
            r6 = r0 & 32
            r7 = 1049582633(0x3e8f5c29, float:0.28)
            if (r6 == 0) goto L_0x003d
            r6 = r7
            goto L_0x003f
        L_0x003d:
            r6 = r34
        L_0x003f:
            r8 = r0 & 64
            if (r8 == 0) goto L_0x0044
            goto L_0x0046
        L_0x0044:
            r7 = r35
        L_0x0046:
            r8 = r0 & 128(0x80, float:1.794E-43)
            if (r8 == 0) goto L_0x004d
            r8 = 1067450368(0x3fa00000, float:1.25)
            goto L_0x004f
        L_0x004d:
            r8 = r36
        L_0x004f:
            r9 = r0 & 256(0x100, float:3.59E-43)
            if (r9 == 0) goto L_0x0056
            r9 = 1108344832(0x42100000, float:36.0)
            goto L_0x0058
        L_0x0056:
            r9 = r37
        L_0x0058:
            r10 = r0 & 512(0x200, float:7.175E-43)
            if (r10 == 0) goto L_0x0060
            r10 = 1056293519(0x3ef5c28f, float:0.48)
            goto L_0x0062
        L_0x0060:
            r10 = r38
        L_0x0062:
            r11 = r0 & 1024(0x400, float:1.435E-42)
            if (r11 == 0) goto L_0x006a
            r11 = 1072231875(0x3fe8f5c3, float:1.82)
            goto L_0x006c
        L_0x006a:
            r11 = r39
        L_0x006c:
            r12 = r0 & 2048(0x800, float:2.87E-42)
            if (r12 == 0) goto L_0x0072
            r12 = 0
            goto L_0x0074
        L_0x0072:
            r12 = r40
        L_0x0074:
            r14 = r0 & 4096(0x1000, float:5.74E-42)
            if (r14 == 0) goto L_0x007b
            r14 = 1065353216(0x3f800000, float:1.0)
            goto L_0x007d
        L_0x007b:
            r14 = r41
        L_0x007d:
            r15 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r15 == 0) goto L_0x0085
            r15 = 1032805417(0x3d8f5c29, float:0.07)
            goto L_0x0087
        L_0x0085:
            r15 = r42
        L_0x0087:
            r13 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r13 == 0) goto L_0x008f
            r13 = 1066611507(0x3f933333, float:1.15)
            goto L_0x0091
        L_0x008f:
            r13 = r43
        L_0x0091:
            r16 = 32768(0x8000, float:4.5918E-41)
            r16 = r0 & r16
            if (r16 == 0) goto L_0x009c
            r16 = 1063675494(0x3f666666, float:0.9)
            goto L_0x009e
        L_0x009c:
            r16 = r44
        L_0x009e:
            r17 = 65536(0x10000, float:9.18355E-41)
            r17 = r0 & r17
            if (r17 == 0) goto L_0x00a8
            r17 = 1070805811(0x3fd33333, float:1.65)
            goto L_0x00aa
        L_0x00a8:
            r17 = r45
        L_0x00aa:
            r18 = 131072(0x20000, float:1.83671E-40)
            r18 = r0 & r18
            if (r18 == 0) goto L_0x00b3
            r18 = 0
            goto L_0x00b5
        L_0x00b3:
            r18 = r46
        L_0x00b5:
            r19 = 262144(0x40000, float:3.67342E-40)
            r19 = r0 & r19
            if (r19 == 0) goto L_0x00c2
            com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightRenderEffect$Companion$Default r19 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightRenderEffect.Companion.Default.INSTANCE
            float r19 = r19.getInitialViewAlpha$sesl_visualeffect_INTERNAL_2_1_6_release()
            goto L_0x00c4
        L_0x00c2:
            r19 = r47
        L_0x00c4:
            r20 = 524288(0x80000, float:7.34684E-40)
            r20 = r0 & r20
            if (r20 == 0) goto L_0x00cd
            com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect$Movement r20 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect.Movement.DEFAULT
            goto L_0x00cf
        L_0x00cd:
            r20 = r48
        L_0x00cf:
            r21 = 1048576(0x100000, float:1.469368E-39)
            r21 = r0 & r21
            if (r21 == 0) goto L_0x00d8
            r21 = 1114636288(0x42700000, float:60.0)
            goto L_0x00da
        L_0x00d8:
            r21 = r49
        L_0x00da:
            r22 = 2097152(0x200000, float:2.938736E-39)
            r22 = r0 & r22
            if (r22 == 0) goto L_0x00e3
            r22 = 1111490560(0x42400000, float:48.0)
            goto L_0x00e5
        L_0x00e3:
            r22 = r50
        L_0x00e5:
            r23 = 4194304(0x400000, float:5.877472E-39)
            r23 = r0 & r23
            if (r23 == 0) goto L_0x00ef
            r23 = 1008981770(0x3c23d70a, float:0.01)
            goto L_0x00f1
        L_0x00ef:
            r23 = r51
        L_0x00f1:
            r24 = 8388608(0x800000, float:1.17549435E-38)
            r24 = r0 & r24
            if (r24 == 0) goto L_0x00fa
            com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig$ShaderPrecision r24 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig.ShaderPrecision.LEVEL_1
            goto L_0x00fc
        L_0x00fa:
            r24 = r52
        L_0x00fc:
            r25 = 16777216(0x1000000, float:2.3509887E-38)
            r25 = r0 & r25
            if (r25 == 0) goto L_0x0106
            r25 = 1058642330(0x3f19999a, float:0.6)
            goto L_0x0108
        L_0x0106:
            r25 = r53
        L_0x0108:
            r26 = 33554432(0x2000000, float:9.403955E-38)
            r0 = r0 & r26
            if (r0 == 0) goto L_0x0147
            r26 = 1100(0x44c, double:5.435E-321)
            r55 = r26
        L_0x0112:
            r29 = r28
            r30 = r1
            r31 = r2
            r32 = r3
            r33 = r4
            r34 = r5
            r35 = r6
            r36 = r7
            r37 = r8
            r38 = r9
            r39 = r10
            r40 = r11
            r41 = r12
            r44 = r13
            r42 = r14
            r43 = r15
            r45 = r16
            r46 = r17
            r47 = r18
            r48 = r19
            r49 = r20
            r50 = r21
            r51 = r22
            r52 = r23
            r53 = r24
            r54 = r25
            goto L_0x014a
        L_0x0147:
            r55 = r54
            goto L_0x0112
        L_0x014a:
            r29.<init>(r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig.<init>(com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig$Shape, com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig$RoundRectDirection, com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.PresetEffectColor$State, android.graphics.PointF, float, float, float, float, float, float, float, float, float, float, float, float, float, float, float, com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect$Movement, float, float, float, com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig$ShaderPrecision, float, long, int, kotlin.jvm.internal.e):void");
    }

    public static /* synthetic */ GuidingLightConfig copy$default(GuidingLightConfig guidingLightConfig, Shape shape2, RoundRectDirection roundRectDirection2, PresetEffectColor.State state, PointF pointF, float f, float f5, float f8, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, float f20, float f21, GuidingLightEffect.Movement movement, float f22, float f23, float f24, ShaderPrecision shaderPrecision2, float f25, long j2, int i2, Object obj) {
        long j3;
        float f26;
        float f27;
        GuidingLightConfig guidingLightConfig2 = guidingLightConfig;
        int i7 = i2;
        Shape shape3 = (i7 & 1) != 0 ? guidingLightConfig2.shape : shape2;
        RoundRectDirection roundRectDirection3 = (i7 & 2) != 0 ? guidingLightConfig2.roundRectDirection : roundRectDirection2;
        PresetEffectColor.State state2 = (i7 & 4) != 0 ? guidingLightConfig2.colorState : state;
        PointF pointF2 = (i7 & 8) != 0 ? guidingLightConfig2.lightPos : pointF;
        float f28 = (i7 & 16) != 0 ? guidingLightConfig2.lightRadius : f;
        float f29 = (i7 & 32) != 0 ? guidingLightConfig2.lightIntensity : f5;
        float f30 = (i7 & 64) != 0 ? guidingLightConfig2.glowIntensity : f8;
        float f31 = (i7 & 128) != 0 ? guidingLightConfig2.glowRadius : f10;
        float f32 = (i7 & 256) != 0 ? guidingLightConfig2.glowSharpness : f11;
        float f33 = (i7 & 512) != 0 ? guidingLightConfig2.reflLightIntensity : f12;
        float f34 = (i7 & 1024) != 0 ? guidingLightConfig2.reflLightRadius : f13;
        float f35 = (i7 & 2048) != 0 ? guidingLightConfig2.reflAlbedo : f14;
        float f36 = (i7 & 4096) != 0 ? guidingLightConfig2.globalLuminance : f15;
        float f37 = (i7 & SerializeOptions.SORT) != 0 ? guidingLightConfig2.ditherVariation : f16;
        Shape shape4 = shape3;
        float f38 = (i7 & 16384) != 0 ? guidingLightConfig2.saturation : f17;
        float f39 = (i7 & 32768) != 0 ? guidingLightConfig2.outerSaturation : f18;
        float f40 = (i2 & 65536) != 0 ? guidingLightConfig2.stretch : f19;
        float f41 = (i2 & 131072) != 0 ? guidingLightConfig2.stretchDirLit : f20;
        float f42 = (i2 & 262144) != 0 ? guidingLightConfig2.initialViewAlpha : f21;
        GuidingLightEffect.Movement movement2 = (i2 & 524288) != 0 ? guidingLightConfig2.lightMovement : movement;
        float f43 = (i2 & MediaDefs.Meta.SEF.SEF_MIN_SIZE) != 0 ? guidingLightConfig2.frameRate : f22;
        float f44 = (i2 & 2097152) != 0 ? guidingLightConfig2.outlineThickness : f23;
        float f45 = (i2 & OCDHelperConstant.TEMP_TO_CHECK_OBJECT_CAPTURE) != 0 ? guidingLightConfig2.boundarySmoothWidth : f24;
        ShaderPrecision shaderPrecision3 = (i2 & 8388608) != 0 ? guidingLightConfig2.shaderPrecision : shaderPrecision2;
        float f46 = (i2 & 16777216) != 0 ? guidingLightConfig2.touchIntensity : f25;
        if ((i2 & 33554432) != 0) {
            f27 = f38;
            f26 = f46;
            j3 = guidingLightConfig2.lightMovementInterval;
        } else {
            j3 = j2;
            f26 = f46;
            f27 = f38;
        }
        return guidingLightConfig2.copy(shape4, roundRectDirection3, state2, pointF2, f28, f29, f30, f31, f32, f33, f34, f35, f36, f37, f27, f39, f40, f41, f42, movement2, f43, f44, f45, shaderPrecision3, f26, j3);
    }

    public final GuidingLightConfig copy(Shape shape2, RoundRectDirection roundRectDirection2, PresetEffectColor.State state, PointF pointF, float f, float f5, float f8, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, float f20, float f21, GuidingLightEffect.Movement movement, float f22, float f23, float f24, ShaderPrecision shaderPrecision2, float f25, long j2) {
        Shape shape3 = shape2;
        j.e(shape3, "shape");
        RoundRectDirection roundRectDirection3 = roundRectDirection2;
        j.e(roundRectDirection3, "roundRectDirection");
        PresetEffectColor.State state2 = state;
        j.e(state2, "colorState");
        PointF pointF2 = pointF;
        j.e(pointF2, "lightPos");
        j.e(movement, "lightMovement");
        ShaderPrecision shaderPrecision3 = shaderPrecision2;
        j.e(shaderPrecision3, "shaderPrecision");
        ShaderPrecision shaderPrecision4 = shaderPrecision3;
        return new GuidingLightConfig(shape3, roundRectDirection3, state2, pointF2, f, f5, f8, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, f20, f21, movement, f22, f23, f24, shaderPrecision4, f25, j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GuidingLightConfig)) {
            return false;
        }
        GuidingLightConfig guidingLightConfig = (GuidingLightConfig) obj;
        if (this.shape == guidingLightConfig.shape && this.roundRectDirection == guidingLightConfig.roundRectDirection && this.colorState == guidingLightConfig.colorState && j.a(this.lightPos, guidingLightConfig.lightPos) && Float.compare(this.lightRadius, guidingLightConfig.lightRadius) == 0 && Float.compare(this.lightIntensity, guidingLightConfig.lightIntensity) == 0 && Float.compare(this.glowIntensity, guidingLightConfig.glowIntensity) == 0 && Float.compare(this.glowRadius, guidingLightConfig.glowRadius) == 0 && Float.compare(this.glowSharpness, guidingLightConfig.glowSharpness) == 0 && Float.compare(this.reflLightIntensity, guidingLightConfig.reflLightIntensity) == 0 && Float.compare(this.reflLightRadius, guidingLightConfig.reflLightRadius) == 0 && Float.compare(this.reflAlbedo, guidingLightConfig.reflAlbedo) == 0 && Float.compare(this.globalLuminance, guidingLightConfig.globalLuminance) == 0 && Float.compare(this.ditherVariation, guidingLightConfig.ditherVariation) == 0 && Float.compare(this.saturation, guidingLightConfig.saturation) == 0 && Float.compare(this.outerSaturation, guidingLightConfig.outerSaturation) == 0 && Float.compare(this.stretch, guidingLightConfig.stretch) == 0 && Float.compare(this.stretchDirLit, guidingLightConfig.stretchDirLit) == 0 && Float.compare(this.initialViewAlpha, guidingLightConfig.initialViewAlpha) == 0 && this.lightMovement == guidingLightConfig.lightMovement && Float.compare(this.frameRate, guidingLightConfig.frameRate) == 0 && Float.compare(this.outlineThickness, guidingLightConfig.outlineThickness) == 0 && Float.compare(this.boundarySmoothWidth, guidingLightConfig.boundarySmoothWidth) == 0 && this.shaderPrecision == guidingLightConfig.shaderPrecision && Float.compare(this.touchIntensity, guidingLightConfig.touchIntensity) == 0 && this.lightMovementInterval == guidingLightConfig.lightMovementInterval) {
            return true;
        }
        return false;
    }

    public final float getBoundarySmoothWidth() {
        return this.boundarySmoothWidth;
    }

    public final PresetEffectColor.State getColorState() {
        return this.colorState;
    }

    public final float getDitherVariation() {
        return this.ditherVariation;
    }

    public final float getFrameRate() {
        return this.frameRate;
    }

    public final float getGlobalLuminance() {
        return this.globalLuminance;
    }

    public final float getGlowIntensity() {
        return this.glowIntensity;
    }

    public final float getGlowRadius() {
        return this.glowRadius;
    }

    public final float getGlowSharpness() {
        return this.glowSharpness;
    }

    public final float getLightIntensity() {
        return this.lightIntensity;
    }

    public final GuidingLightEffect.Movement getLightMovement() {
        return this.lightMovement;
    }

    public final long getLightMovementInterval() {
        return this.lightMovementInterval;
    }

    public final PointF getLightPos() {
        return this.lightPos;
    }

    public final float getLightRadius() {
        return this.lightRadius;
    }

    public final float getOuterSaturation() {
        return this.outerSaturation;
    }

    public final float getOutlineThickness() {
        return this.outlineThickness;
    }

    public final float getReflAlbedo() {
        return this.reflAlbedo;
    }

    public final float getReflLightIntensity() {
        return this.reflLightIntensity;
    }

    public final float getReflLightRadius() {
        return this.reflLightRadius;
    }

    public final float getSaturation() {
        return this.saturation;
    }

    public final ShaderPrecision getShaderPrecision() {
        return this.shaderPrecision;
    }

    public final Shape getShape() {
        return this.shape;
    }

    public final float getStretch() {
        return this.stretch;
    }

    public final float getStretchDirLit() {
        return this.stretchDirLit;
    }

    public final float getTouchIntensity() {
        return this.touchIntensity;
    }

    public int hashCode() {
        int hashCode = this.roundRectDirection.hashCode();
        int hashCode2 = this.colorState.hashCode();
        int hashCode3 = this.lightPos.hashCode();
        int a7 = N2.j.a(this.initialViewAlpha, N2.j.a(this.stretchDirLit, N2.j.a(this.stretch, N2.j.a(this.outerSaturation, N2.j.a(this.saturation, N2.j.a(this.ditherVariation, N2.j.a(this.globalLuminance, N2.j.a(this.reflAlbedo, N2.j.a(this.reflLightRadius, N2.j.a(this.reflLightIntensity, N2.j.a(this.glowSharpness, N2.j.a(this.glowRadius, N2.j.a(this.glowIntensity, N2.j.a(this.lightIntensity, N2.j.a(this.lightRadius, (hashCode3 + ((hashCode2 + ((hashCode + (this.shape.hashCode() * 31)) * 31)) * 31)) * 31, 31), 31), 31), 31), 31), 31), 31), 31), 31), 31), 31), 31), 31), 31), 31);
        int a10 = N2.j.a(this.boundarySmoothWidth, N2.j.a(this.outlineThickness, N2.j.a(this.frameRate, (this.lightMovement.hashCode() + a7) * 31, 31), 31), 31);
        return Long.hashCode(this.lightMovementInterval) + N2.j.a(this.touchIntensity, (this.shaderPrecision.hashCode() + a10) * 31, 31);
    }

    public final void setLightIntensity(float f) {
        this.lightIntensity = f;
    }

    public final void setLightMovement(GuidingLightEffect.Movement movement) {
        j.e(movement, "<set-?>");
        this.lightMovement = movement;
    }

    public final void setOuterSaturation(float f) {
        this.outerSaturation = f;
    }

    public final void setOutlineThickness(float f) {
        this.outlineThickness = f;
    }

    public final void setShaderPrecision(ShaderPrecision shaderPrecision2) {
        j.e(shaderPrecision2, "<set-?>");
        this.shaderPrecision = shaderPrecision2;
    }

    public final void setShape(Shape shape2) {
        j.e(shape2, "<set-?>");
        this.shape = shape2;
    }

    public String toString() {
        Shape shape2 = this.shape;
        RoundRectDirection roundRectDirection2 = this.roundRectDirection;
        PresetEffectColor.State state = this.colorState;
        PointF pointF = this.lightPos;
        float f = this.lightRadius;
        float f5 = this.lightIntensity;
        float f8 = this.glowIntensity;
        float f10 = this.glowRadius;
        float f11 = this.glowSharpness;
        float f12 = this.reflLightIntensity;
        float f13 = this.reflLightRadius;
        float f14 = this.reflAlbedo;
        float f15 = this.globalLuminance;
        float f16 = this.ditherVariation;
        float f17 = this.saturation;
        float f18 = this.outerSaturation;
        float f19 = this.stretch;
        float f20 = this.stretchDirLit;
        float f21 = this.initialViewAlpha;
        GuidingLightEffect.Movement movement = this.lightMovement;
        float f22 = this.frameRate;
        float f23 = this.outlineThickness;
        float f24 = this.boundarySmoothWidth;
        ShaderPrecision shaderPrecision2 = this.shaderPrecision;
        float f25 = this.touchIntensity;
        StringBuilder sb2 = new StringBuilder("GuidingLightConfig(shape=");
        sb2.append(shape2);
        sb2.append(", roundRectDirection=");
        sb2.append(roundRectDirection2);
        sb2.append(", colorState=");
        sb2.append(state);
        sb2.append(", lightPos=");
        sb2.append(pointF);
        sb2.append(", lightRadius=");
        C0086a.y(sb2, f, ", lightIntensity=", f5, ", glowIntensity=");
        C0086a.y(sb2, f8, ", glowRadius=", f10, ", glowSharpness=");
        C0086a.y(sb2, f11, ", reflLightIntensity=", f12, ", reflLightRadius=");
        C0086a.y(sb2, f13, ", reflAlbedo=", f14, ", globalLuminance=");
        C0086a.y(sb2, f15, ", ditherVariation=", f16, ", saturation=");
        C0086a.y(sb2, f17, ", outerSaturation=", f18, ", stretch=");
        C0086a.y(sb2, f19, ", stretchDirLit=", f20, ", initialViewAlpha=");
        sb2.append(f21);
        sb2.append(", lightMovement=");
        sb2.append(movement);
        sb2.append(", frameRate=");
        C0086a.y(sb2, f22, ", outlineThickness=", f23, ", boundarySmoothWidth=");
        sb2.append(f24);
        sb2.append(", shaderPrecision=");
        sb2.append(shaderPrecision2);
        sb2.append(", touchIntensity=");
        sb2.append(f25);
        sb2.append(", lightMovementInterval=");
        sb2.append(this.lightMovementInterval);
        sb2.append(")");
        return sb2.toString();
    }

    public GuidingLightConfig(Shape shape2, RoundRectDirection roundRectDirection2, PresetEffectColor.State state, PointF pointF, float f, float f5, float f8, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, float f20, float f21, GuidingLightEffect.Movement movement, float f22, float f23, float f24, ShaderPrecision shaderPrecision2, float f25, long j2) {
        GuidingLightEffect.Movement movement2 = movement;
        ShaderPrecision shaderPrecision3 = shaderPrecision2;
        j.e(shape2, "shape");
        j.e(roundRectDirection2, "roundRectDirection");
        j.e(state, "colorState");
        j.e(pointF, "lightPos");
        j.e(movement2, "lightMovement");
        j.e(shaderPrecision3, "shaderPrecision");
        this.shape = shape2;
        this.roundRectDirection = roundRectDirection2;
        this.colorState = state;
        this.lightPos = pointF;
        this.lightRadius = f;
        this.lightIntensity = f5;
        this.glowIntensity = f8;
        this.glowRadius = f10;
        this.glowSharpness = f11;
        this.reflLightIntensity = f12;
        this.reflLightRadius = f13;
        this.reflAlbedo = f14;
        this.globalLuminance = f15;
        this.ditherVariation = f16;
        this.saturation = f17;
        this.outerSaturation = f18;
        this.stretch = f19;
        this.stretchDirLit = f20;
        this.initialViewAlpha = f21;
        this.lightMovement = movement2;
        this.frameRate = f22;
        this.outlineThickness = f23;
        this.boundarySmoothWidth = f24;
        this.shaderPrecision = shaderPrecision3;
        this.touchIntensity = f25;
        this.lightMovementInterval = j2;
    }
}
