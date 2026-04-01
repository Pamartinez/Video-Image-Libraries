package com.samsung.android.sesl.visualeffect.lighteffects.processinglight;

import Vf.C0874k;
import android.graphics.Color;
import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import c0.C0086a;
import com.samsung.android.sesl.visualeffect.lighteffects.common.config.AnimatableAttribute;
import com.samsung.android.sesl.visualeffect.lighteffects.common.config.BaseVibeEffectConfig;
import com.samsung.android.sesl.visualeffect.lighteffects.common.control.VibeEffectControl;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\bK\b\b\u0018\u0000 m2\u00020\u0001:\u0002nmB½\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\b\b\u0003\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\b\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\b\u0012\b\b\u0002\u0010\u0012\u001a\u00020\b\u0012\b\b\u0002\u0010\u0013\u001a\u00020\b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\b\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0018\u001a\u00020\b\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0015¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001e\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010 \u001a\u00020\u001fHÖ\u0001¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\"\u0010#J\u001a\u0010&\u001a\u00020\u00022\b\u0010%\u001a\u0004\u0018\u00010$HÖ\u0003¢\u0006\u0004\b&\u0010'J\u0013\u0010(\u001a\u00020\b*\u00020\bH\u0002¢\u0006\u0004\b(\u0010)J'\u0010-\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\bH\u0002¢\u0006\u0004\b-\u0010.J\u001b\u00100\u001a\u00020\u0006*\u00020\u00062\u0006\u0010/\u001a\u00020\u0006H\u0002¢\u0006\u0004\b0\u00101J\u001b\u00102\u001a\u00020\u0006*\u00020\u00062\u0006\u0010/\u001a\u00020\u0006H\u0002¢\u0006\u0004\b2\u00101J\u001b\u00103\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0014\u001a\u00020\bH\u0002¢\u0006\u0004\b3\u00104R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0003\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010:\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010?\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\"\u0010\t\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\t\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\"\u0010\n\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\n\u0010D\u001a\u0004\bI\u0010F\"\u0004\bJ\u0010HR\"\u0010\u000b\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010D\u001a\u0004\bK\u0010F\"\u0004\bL\u0010HR\"\u0010\r\u001a\u00020\f8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\r\u0010M\u001a\u0004\bN\u0010#\"\u0004\bO\u0010PR\"\u0010\u000e\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010D\u001a\u0004\bQ\u0010F\"\u0004\bR\u0010HR\"\u0010\u000f\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010D\u001a\u0004\bS\u0010F\"\u0004\bT\u0010HR$\u0010\u0010\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010U\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\"\u0010\u0011\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010D\u001a\u0004\bZ\u0010F\"\u0004\b[\u0010HR\"\u0010\u0012\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010D\u001a\u0004\b\\\u0010F\"\u0004\b]\u0010HR\"\u0010\u0013\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010D\u001a\u0004\b^\u0010F\"\u0004\b_\u0010HR\"\u0010\u0014\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010D\u001a\u0004\b`\u0010F\"\u0004\ba\u0010HR\"\u0010\u0016\u001a\u00020\u00158\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010b\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\"\u0010\u0017\u001a\u00020\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0017\u00105\u001a\u0004\bg\u00107\"\u0004\bh\u00109R\"\u0010\u0018\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010D\u001a\u0004\bi\u0010F\"\u0004\bj\u0010HR\"\u0010\u0019\u001a\u00020\u00158\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010b\u001a\u0004\bk\u0010d\"\u0004\bl\u0010f¨\u0006o"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/config/BaseVibeEffectConfig;", "", "useLightnessCalibration", "Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig$BlendMode;", "blendMode", "Landroid/graphics/PointF;", "lightPos", "", "lightAngle", "lightScale", "lightStretch", "", "lightColor", "lightIntensity", "lightSaturation", "domainColor", "domainStrength", "domainDeltaRatio", "ditherVariation", "angle", "", "durationInMillis", "useDynamicDomainColor", "frameRate", "repeatDelay", "<init>", "(ZLcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig$BlendMode;Landroid/graphics/PointF;FFFIFFLjava/lang/Integer;FFFFJZFJ)V", "makeConfigWithAnimator$sesl_visualeffect_INTERNAL_2_1_6_release", "()Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig;", "makeConfigWithAnimator", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "toRadian", "(F)F", "p1", "p2", "ratio", "mix", "(Landroid/graphics/PointF;Landroid/graphics/PointF;F)Landroid/graphics/PointF;", "p", "minus", "(Landroid/graphics/PointF;Landroid/graphics/PointF;)Landroid/graphics/PointF;", "plus", "getRotated", "(Landroid/graphics/PointF;F)Landroid/graphics/PointF;", "Z", "getUseLightnessCalibration", "()Z", "setUseLightnessCalibration", "(Z)V", "Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig$BlendMode;", "getBlendMode", "()Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig$BlendMode;", "setBlendMode", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig$BlendMode;)V", "Landroid/graphics/PointF;", "getLightPos", "()Landroid/graphics/PointF;", "setLightPos", "(Landroid/graphics/PointF;)V", "F", "getLightAngle", "()F", "setLightAngle", "(F)V", "getLightScale", "setLightScale", "getLightStretch", "setLightStretch", "I", "getLightColor", "setLightColor", "(I)V", "getLightIntensity", "setLightIntensity", "getLightSaturation", "setLightSaturation", "Ljava/lang/Integer;", "getDomainColor", "()Ljava/lang/Integer;", "setDomainColor", "(Ljava/lang/Integer;)V", "getDomainStrength", "setDomainStrength", "getDomainDeltaRatio", "setDomainDeltaRatio", "getDitherVariation", "setDitherVariation", "getAngle", "setAngle", "J", "getDurationInMillis", "()J", "setDurationInMillis", "(J)V", "getUseDynamicDomainColor", "setUseDynamicDomainColor", "getFrameRate", "setFrameRate", "getRepeatDelay", "setRepeatDelay", "Companion", "BlendMode", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ProcessingLightConfig extends BaseVibeEffectConfig {
    public static final Companion Companion = new Companion((e) null);
    private static final ProcessingLightConfig VI_CONFIG_CAPTURE;
    private static final ProcessingLightConfig VI_CONFIG_DEFAULT;
    /* access modifiers changed from: private */
    public static final ProcessingLightConfig VI_CONFIG_OVERLAY;
    private static final PathInterpolator positionInterpolator = new PathInterpolator(0.33f, 0.22f, 0.42f, 1.0f);
    private float angle;
    private BlendMode blendMode;
    private float ditherVariation;
    private Integer domainColor;
    private float domainDeltaRatio;
    private float domainStrength;
    private long durationInMillis;
    private float frameRate;
    private float lightAngle;
    private int lightColor;
    private float lightIntensity;
    private PointF lightPos;
    private float lightSaturation;
    private float lightScale;
    private float lightStretch;
    private long repeatDelay;
    private boolean useDynamicDomainColor;
    private boolean useLightnessCalibration;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig$BlendMode;", "", "<init>", "(Ljava/lang/String;I)V", "PREMULT", "ADD", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum BlendMode {
        PREMULT,
        ADD;

        static {
            BlendMode[] $values;
            $ENTRIES = c.t($values);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\t8\u0000XT¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0000XT¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u000f8\u0000XT¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig$Companion;", "", "<init>", "()V", "Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig;", "VI_CONFIG_OVERLAY", "Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig;", "getVI_CONFIG_OVERLAY", "()Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig;", "", "TAG", "Ljava/lang/String;", "", "DURATION", "J", "", "ANGLE", "F", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final ProcessingLightConfig getVI_CONFIG_OVERLAY() {
            return ProcessingLightConfig.VI_CONFIG_OVERLAY;
        }

        private Companion() {
        }
    }

    static {
        ProcessingLightConfig processingLightConfig = new ProcessingLightConfig(false, (BlendMode) null, (PointF) null, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0.0f, (Integer) null, 0.0f, 0.0f, 0.0f, 0.0f, 0, false, 0.0f, 0, 262143, (e) null);
        BlendMode blendMode2 = BlendMode.PREMULT;
        processingLightConfig.blendMode = blendMode2;
        processingLightConfig.domainDeltaRatio = 0.23f;
        processingLightConfig.useDynamicDomainColor = true;
        processingLightConfig.useLightnessCalibration = true;
        VI_CONFIG_DEFAULT = processingLightConfig;
        ProcessingLightConfig processingLightConfig2 = new ProcessingLightConfig(false, (BlendMode) null, (PointF) null, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0.0f, (Integer) null, 0.0f, 0.0f, 0.0f, 0.0f, 0, false, 0.0f, 0, 262143, (e) null);
        processingLightConfig2.blendMode = BlendMode.ADD;
        processingLightConfig2.domainDeltaRatio = 0.52f;
        processingLightConfig2.domainColor = Integer.valueOf(Color.parseColor("#010101"));
        processingLightConfig2.useDynamicDomainColor = false;
        processingLightConfig2.useLightnessCalibration = true;
        VI_CONFIG_OVERLAY = processingLightConfig2;
        ProcessingLightConfig processingLightConfig3 = new ProcessingLightConfig(false, (BlendMode) null, (PointF) null, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0.0f, (Integer) null, 0.0f, 0.0f, 0.0f, 0.0f, 0, false, 0.0f, 0, 262143, (e) null);
        processingLightConfig3.blendMode = blendMode2;
        processingLightConfig3.domainDeltaRatio = 0.0f;
        processingLightConfig3.useDynamicDomainColor = false;
        processingLightConfig3.domainColor = -1;
        processingLightConfig3.useLightnessCalibration = false;
        VI_CONFIG_CAPTURE = processingLightConfig3;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ProcessingLightConfig(boolean r22, com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightConfig.BlendMode r23, android.graphics.PointF r24, float r25, float r26, float r27, int r28, float r29, float r30, java.lang.Integer r31, float r32, float r33, float r34, float r35, long r36, boolean r38, float r39, long r40, int r42, kotlin.jvm.internal.e r43) {
        /*
            r21 = this;
            r0 = r42
            r1 = r0 & 1
            if (r1 == 0) goto L_0x0008
            r1 = 1
            goto L_0x000a
        L_0x0008:
            r1 = r22
        L_0x000a:
            r2 = r0 & 2
            if (r2 == 0) goto L_0x0011
            com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightConfig$BlendMode r2 = com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightConfig.BlendMode.ADD
            goto L_0x0013
        L_0x0011:
            r2 = r23
        L_0x0013:
            r3 = r0 & 4
            if (r3 == 0) goto L_0x001e
            com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightRenderEffect$Companion$Default r3 = com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightRenderEffect.Companion.Default.INSTANCE
            android.graphics.PointF r3 = r3.getLightPos()
            goto L_0x0020
        L_0x001e:
            r3 = r24
        L_0x0020:
            r4 = r0 & 8
            if (r4 == 0) goto L_0x0026
            r4 = 0
            goto L_0x0028
        L_0x0026:
            r4 = r25
        L_0x0028:
            r5 = r0 & 16
            if (r5 == 0) goto L_0x0030
            r5 = 1073951539(0x40033333, float:2.05)
            goto L_0x0032
        L_0x0030:
            r5 = r26
        L_0x0032:
            r6 = r0 & 32
            r7 = 1065353216(0x3f800000, float:1.0)
            if (r6 == 0) goto L_0x003a
            r6 = r7
            goto L_0x003c
        L_0x003a:
            r6 = r27
        L_0x003c:
            r8 = r0 & 64
            if (r8 == 0) goto L_0x0042
            r8 = -1
            goto L_0x0044
        L_0x0042:
            r8 = r28
        L_0x0044:
            r9 = r0 & 128(0x80, float:1.794E-43)
            if (r9 == 0) goto L_0x0049
            goto L_0x004b
        L_0x0049:
            r7 = r29
        L_0x004b:
            r9 = r0 & 256(0x100, float:3.59E-43)
            if (r9 == 0) goto L_0x0053
            r9 = 1066611507(0x3f933333, float:1.15)
            goto L_0x0055
        L_0x0053:
            r9 = r30
        L_0x0055:
            r10 = r0 & 512(0x200, float:7.175E-43)
            if (r10 == 0) goto L_0x005b
            r10 = 0
            goto L_0x005d
        L_0x005b:
            r10 = r31
        L_0x005d:
            r11 = r0 & 1024(0x400, float:1.435E-42)
            if (r11 == 0) goto L_0x0065
            r11 = 1066695393(0x3f947ae1, float:1.16)
            goto L_0x0067
        L_0x0065:
            r11 = r32
        L_0x0067:
            r12 = r0 & 2048(0x800, float:2.87E-42)
            if (r12 == 0) goto L_0x006f
            r12 = 1045220557(0x3e4ccccd, float:0.2)
            goto L_0x0071
        L_0x006f:
            r12 = r33
        L_0x0071:
            r13 = r0 & 4096(0x1000, float:5.74E-42)
            if (r13 == 0) goto L_0x0079
            r13 = 1028443341(0x3d4ccccd, float:0.05)
            goto L_0x007b
        L_0x0079:
            r13 = r34
        L_0x007b:
            r14 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r14 == 0) goto L_0x0082
            r14 = 1118306304(0x42a80000, float:84.0)
            goto L_0x0084
        L_0x0082:
            r14 = r35
        L_0x0084:
            r15 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r15 == 0) goto L_0x008b
            r15 = 2000(0x7d0, double:9.88E-321)
            goto L_0x008d
        L_0x008b:
            r15 = r36
        L_0x008d:
            r17 = 32768(0x8000, float:4.5918E-41)
            r17 = r0 & r17
            if (r17 == 0) goto L_0x0097
            r17 = 0
            goto L_0x0099
        L_0x0097:
            r17 = r38
        L_0x0099:
            r18 = 65536(0x10000, float:9.18355E-41)
            r18 = r0 & r18
            if (r18 == 0) goto L_0x00a2
            r18 = 1114636288(0x42700000, float:60.0)
            goto L_0x00a4
        L_0x00a2:
            r18 = r39
        L_0x00a4:
            r19 = 131072(0x20000, float:1.83671E-40)
            r0 = r0 & r19
            if (r0 == 0) goto L_0x00d3
            r19 = 0
            r41 = r19
        L_0x00ae:
            r22 = r21
            r23 = r1
            r24 = r2
            r25 = r3
            r26 = r4
            r27 = r5
            r28 = r6
            r30 = r7
            r29 = r8
            r31 = r9
            r32 = r10
            r33 = r11
            r34 = r12
            r35 = r13
            r36 = r14
            r37 = r15
            r39 = r17
            r40 = r18
            goto L_0x00d6
        L_0x00d3:
            r41 = r40
            goto L_0x00ae
        L_0x00d6:
            r22.<init>(r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r39, r40, r41)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightConfig.<init>(boolean, com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightConfig$BlendMode, android.graphics.PointF, float, float, float, int, float, float, java.lang.Integer, float, float, float, float, long, boolean, float, long, int, kotlin.jvm.internal.e):void");
    }

    private final PointF getRotated(PointF pointF, float f) {
        double radians = (double) ((float) Math.toRadians((double) f));
        float cos = (float) Math.cos(radians);
        float sin = (float) Math.sin(radians);
        float f5 = pointF.x;
        float f8 = pointF.y;
        return new PointF((f5 * cos) - (f8 * sin), (f8 * cos) + (f5 * sin));
    }

    private static final float makeConfigWithAnimator$lambda$1$getHypotenuse(ProcessingLightConfig processingLightConfig, float f, float f5) {
        return f / ((float) Math.cos((double) processingLightConfig.toRadian(f5)));
    }

    /* access modifiers changed from: private */
    public static final x makeConfigWithAnimator$lambda$1$lambda$0(ProcessingLightConfig processingLightConfig, VibeEffectControl vibeEffectControl, float f, float f5) {
        ProcessingLightControl processingLightControl;
        float f8 = processingLightConfig.angle % ((float) 90);
        if (f8 > 45.0f) {
            f8 = 90.0f - f8;
        }
        PointF rotated = processingLightConfig.getRotated(new PointF(makeConfigWithAnimator$lambda$1$getHypotenuse(processingLightConfig, 0.5f, f8) + makeConfigWithAnimator$lambda$1$getHypotenuse(processingLightConfig, processingLightConfig.lightScale * 0.5f, f8), 0.0f), processingLightConfig.angle);
        PointF pointF = new PointF(0.5f, 0.5f);
        PointF mix = processingLightConfig.mix(processingLightConfig.minus(pointF, rotated), processingLightConfig.plus(pointF, rotated), positionInterpolator.getInterpolation(f));
        boolean z = vibeEffectControl instanceof ProcessingLightControl;
        ProcessingLightControl processingLightControl2 = null;
        if (z) {
            processingLightControl = (ProcessingLightControl) vibeEffectControl;
        } else {
            processingLightControl = null;
        }
        if (processingLightControl != null) {
            processingLightControl.setLightPosition(mix);
        }
        if (z) {
            processingLightControl2 = (ProcessingLightControl) vibeEffectControl;
        }
        if (processingLightControl2 != null) {
            processingLightControl2.setLightScale(processingLightConfig.lightScale);
        }
        return x.f4917a;
    }

    private final PointF minus(PointF pointF, PointF pointF2) {
        return new PointF(pointF.x - pointF2.x, pointF.y - pointF2.y);
    }

    private final PointF mix(PointF pointF, PointF pointF2, float f) {
        float f5 = 1.0f - f;
        return new PointF((pointF2.x * f) + (pointF.x * f5), (pointF2.y * f) + (pointF.y * f5));
    }

    private final PointF plus(PointF pointF, PointF pointF2) {
        return new PointF(pointF.x + pointF2.x, pointF.y + pointF2.y);
    }

    private final float toRadian(float f) {
        return (f * 3.1415927f) / 180.0f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProcessingLightConfig)) {
            return false;
        }
        ProcessingLightConfig processingLightConfig = (ProcessingLightConfig) obj;
        if (this.useLightnessCalibration == processingLightConfig.useLightnessCalibration && this.blendMode == processingLightConfig.blendMode && j.a(this.lightPos, processingLightConfig.lightPos) && Float.compare(this.lightAngle, processingLightConfig.lightAngle) == 0 && Float.compare(this.lightScale, processingLightConfig.lightScale) == 0 && Float.compare(this.lightStretch, processingLightConfig.lightStretch) == 0 && this.lightColor == processingLightConfig.lightColor && Float.compare(this.lightIntensity, processingLightConfig.lightIntensity) == 0 && Float.compare(this.lightSaturation, processingLightConfig.lightSaturation) == 0 && j.a(this.domainColor, processingLightConfig.domainColor) && Float.compare(this.domainStrength, processingLightConfig.domainStrength) == 0 && Float.compare(this.domainDeltaRatio, processingLightConfig.domainDeltaRatio) == 0 && Float.compare(this.ditherVariation, processingLightConfig.ditherVariation) == 0 && Float.compare(this.angle, processingLightConfig.angle) == 0 && this.durationInMillis == processingLightConfig.durationInMillis && this.useDynamicDomainColor == processingLightConfig.useDynamicDomainColor && Float.compare(this.frameRate, processingLightConfig.frameRate) == 0 && this.repeatDelay == processingLightConfig.repeatDelay) {
            return true;
        }
        return false;
    }

    public final BlendMode getBlendMode() {
        return this.blendMode;
    }

    public final Integer getDomainColor() {
        return this.domainColor;
    }

    public final float getDomainDeltaRatio() {
        return this.domainDeltaRatio;
    }

    public final float getDomainStrength() {
        return this.domainStrength;
    }

    public final long getDurationInMillis() {
        return this.durationInMillis;
    }

    public final float getFrameRate() {
        return this.frameRate;
    }

    public final float getLightAngle() {
        return this.lightAngle;
    }

    public final int getLightColor() {
        return this.lightColor;
    }

    public final float getLightIntensity() {
        return this.lightIntensity;
    }

    public final PointF getLightPos() {
        return this.lightPos;
    }

    public final float getLightScale() {
        return this.lightScale;
    }

    public final float getLightStretch() {
        return this.lightStretch;
    }

    public final long getRepeatDelay() {
        return this.repeatDelay;
    }

    public final boolean getUseDynamicDomainColor() {
        return this.useDynamicDomainColor;
    }

    public final boolean getUseLightnessCalibration() {
        return this.useLightnessCalibration;
    }

    public int hashCode() {
        int i2;
        int hashCode = this.blendMode.hashCode();
        int hashCode2 = this.lightPos.hashCode();
        int a7 = N2.j.a(this.lightSaturation, N2.j.a(this.lightIntensity, C0212a.b(this.lightColor, N2.j.a(this.lightStretch, N2.j.a(this.lightScale, N2.j.a(this.lightAngle, (hashCode2 + ((hashCode + (Boolean.hashCode(this.useLightnessCalibration) * 31)) * 31)) * 31, 31), 31), 31), 31), 31), 31);
        Integer num = this.domainColor;
        if (num == null) {
            i2 = 0;
        } else {
            i2 = num.hashCode();
        }
        return Long.hashCode(this.repeatDelay) + N2.j.a(this.frameRate, C0212a.e(C0212a.c(N2.j.a(this.angle, N2.j.a(this.ditherVariation, N2.j.a(this.domainDeltaRatio, N2.j.a(this.domainStrength, (a7 + i2) * 31, 31), 31), 31), 31), 31, this.durationInMillis), 31, this.useDynamicDomainColor), 31);
    }

    public final ProcessingLightConfig makeConfigWithAnimator$sesl_visualeffect_INTERNAL_2_1_6_release() {
        boolean z = this.useLightnessCalibration;
        BlendMode blendMode2 = this.blendMode;
        int i2 = this.lightColor;
        float f = this.lightIntensity;
        float f5 = this.lightAngle;
        float f8 = this.lightScale;
        float f10 = this.lightStretch;
        Integer num = this.domainColor;
        float f11 = this.domainStrength;
        float f12 = this.domainDeltaRatio;
        float f13 = this.ditherVariation;
        long j2 = this.durationInMillis;
        boolean z3 = z;
        ProcessingLightConfig processingLightConfig = new ProcessingLightConfig(z3, blendMode2, (PointF) null, f5, f8, f10, i2, f, 0.0f, num, f11, f12, f13, this.angle, j2, false, this.frameRate, this.repeatDelay, 33028, (e) null);
        processingLightConfig.getAnimatableAttribute$sesl_visualeffect_INTERNAL_2_1_6_release().clear();
        processingLightConfig.add(new AnimatableAttribute(processingLightConfig.durationInMillis, 0, Float.valueOf(0.0f), Float.valueOf(1.0f), (Interpolator) null, -1, 0, new ProcessingLightConfig$makeConfigWithAnimator$1$1(processingLightConfig), new C0874k(1, (Object) processingLightConfig), 82, (e) null));
        return processingLightConfig;
    }

    public final void setDomainColor(Integer num) {
        this.domainColor = num;
    }

    public final void setFrameRate(float f) {
        this.frameRate = f;
    }

    public String toString() {
        boolean z = this.useLightnessCalibration;
        BlendMode blendMode2 = this.blendMode;
        PointF pointF = this.lightPos;
        float f = this.lightAngle;
        float f5 = this.lightScale;
        float f8 = this.lightStretch;
        int i2 = this.lightColor;
        float f10 = this.lightIntensity;
        float f11 = this.lightSaturation;
        Integer num = this.domainColor;
        float f12 = this.domainStrength;
        float f13 = this.domainDeltaRatio;
        float f14 = this.ditherVariation;
        long j2 = this.durationInMillis;
        float f15 = f14;
        boolean z3 = this.useDynamicDomainColor;
        float f16 = this.frameRate;
        StringBuilder sb2 = new StringBuilder("ProcessingLightConfig(useLightnessCalibration=");
        sb2.append(z);
        sb2.append(", blendMode=");
        sb2.append(blendMode2);
        sb2.append(", lightPos=");
        sb2.append(pointF);
        sb2.append(", lightAngle=");
        sb2.append(f);
        sb2.append(", lightScale=");
        C0086a.y(sb2, f5, ", lightStretch=", f8, ", lightColor=");
        sb2.append(i2);
        sb2.append(", lightIntensity=");
        sb2.append(f10);
        sb2.append(", lightSaturation=");
        sb2.append(f11);
        sb2.append(", domainColor=");
        sb2.append(num);
        sb2.append(", domainStrength=");
        C0086a.y(sb2, f12, ", domainDeltaRatio=", f13, ", ditherVariation=");
        C0086a.y(sb2, f15, ", angle=", this.angle, ", durationInMillis=");
        sb2.append(j2);
        sb2.append(", useDynamicDomainColor=");
        sb2.append(z3);
        sb2.append(", frameRate=");
        sb2.append(f16);
        sb2.append(", repeatDelay=");
        return C0212a.o(sb2, this.repeatDelay, ")");
    }

    public ProcessingLightConfig(boolean z, BlendMode blendMode2, PointF pointF, float f, float f5, float f8, int i2, float f10, float f11, Integer num, float f12, float f13, float f14, float f15, long j2, boolean z3, float f16, long j3) {
        j.e(blendMode2, "blendMode");
        j.e(pointF, "lightPos");
        this.useLightnessCalibration = z;
        this.blendMode = blendMode2;
        this.lightPos = pointF;
        this.lightAngle = f;
        this.lightScale = f5;
        this.lightStretch = f8;
        this.lightColor = i2;
        this.lightIntensity = f10;
        this.lightSaturation = f11;
        this.domainColor = num;
        this.domainStrength = f12;
        this.domainDeltaRatio = f13;
        this.ditherVariation = f14;
        this.angle = f15;
        this.durationInMillis = j2;
        this.useDynamicDomainColor = z3;
        this.frameRate = f16;
        this.repeatDelay = j3;
    }
}
