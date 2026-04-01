package com.samsung.android.sesl.visualeffect.lighteffects.radialgrad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RenderEffect;
import android.graphics.RuntimeShader;
import android.graphics.Shader;
import android.util.Log;
import bd.m;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import com.samsung.android.sdk.scs.ai.asr.tasks.d;
import com.samsung.android.sesl.visualeffect.R$drawable;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffectBase;
import com.samsung.android.sum.core.message.Message;
import dd.c;
import dd.f;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 G2\u00020\u0001:\u0001GB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\rH\u0014¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\n2\b\b\u0001\u0010\u0015\u001a\u00020\u0005¢\u0006\u0004\b\u0016\u0010\fJ\u0015\u0010\u0017\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\u0017\u0010\fJ\u001d\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00052\b\b\u0001\u0010\u0015\u001a\u00020\u0005¢\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005¢\u0006\u0004\b\u001f\u0010 J\u001d\u0010#\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b#\u0010$J\u0015\u0010%\u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u0005¢\u0006\u0004\b%\u0010&J\u001d\u0010)\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010(\u001a\u00020'¢\u0006\u0004\b)\u0010*J\u0015\u0010+\u001a\u00020'2\u0006\u0010\u0018\u001a\u00020\u0005¢\u0006\u0004\b+\u0010,J\u000f\u0010-\u001a\u00020\nH\u0016¢\u0006\u0004\b-\u0010\u0003R$\u0010/\u001a\u0004\u0018\u00010.8\u0016@\u0016X\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u0016\u00105\u001a\u00020\u00058\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b5\u00106R\u0016\u00107\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b7\u00108R\u0016\u00109\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b9\u00108R\u0016\u0010:\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b:\u00108R\u0016\u0010;\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b;\u00108R*\u0010=\u001a\u00020\u00052\u0006\u0010<\u001a\u00020\u00058\u0006@FX\u000e¢\u0006\u0012\n\u0004\b=\u00106\u001a\u0004\b>\u0010?\"\u0004\b@\u0010\fR\u0016\u0010A\u001a\u00020\u00198\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bA\u0010BR\u0014\u0010F\u001a\u00020C8VX\u0004¢\u0006\u0006\u001a\u0004\bD\u0010E¨\u0006H"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/RadialGradRenderEffect;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/VibeRenderEffectBase;", "<init>", "()V", "", "", "newSize", "resize", "([FI)[F", "count", "Lme/x;", "buildSpotArrays", "(I)V", "Landroid/content/Context;", "appContext", "onInitialize", "(Landroid/content/Context;)V", "Landroid/graphics/Bitmap;", "bitmap", "setLightMap", "(Landroid/graphics/Bitmap;)V", "color", "setBackgroundColor", "setSpotCount", "index", "", "enabled", "setSpotEnabled", "(IZ)V", "setSpotColor", "(II)V", "getSpotColor", "(I)I", "Landroid/graphics/PointF;", "position", "setSpotPosition", "(ILandroid/graphics/PointF;)V", "getSpotPosition", "(I)Landroid/graphics/PointF;", "", "scale", "setSpotScale", "(IF)V", "getSpotScale", "(I)F", "destroy", "Landroid/graphics/RuntimeShader;", "shader", "Landroid/graphics/RuntimeShader;", "getShader", "()Landroid/graphics/RuntimeShader;", "setShader", "(Landroid/graphics/RuntimeShader;)V", "spotCount", "I", "spotEnabled", "[F", "spotColors", "spotPositions", "spotScales", "value", "blurRadius", "getBlurRadius", "()I", "setBlurRadius", "hasLightMap", "Z", "Landroid/graphics/RenderEffect;", "getRenderEffect", "()Landroid/graphics/RenderEffect;", "renderEffect", "Companion", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RadialGradRenderEffect extends VibeRenderEffectBase {
    public static final Companion Companion = new Companion((e) null);
    private int blurRadius;
    private boolean hasLightMap;
    private RuntimeShader shader = d.h();
    private float[] spotColors = new float[0];
    private int spotCount;
    private float[] spotEnabled = new float[0];
    private float[] spotPositions = new float[0];
    private float[] spotScales = new float[0];

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001:\u0001\tB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00078\u0006XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/RadialGradRenderEffect$Companion;", "", "<init>", "()V", "MAX_SPOTS", "", "TAG", "", "SHADER", "Default", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {

        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/RadialGradRenderEffect$Companion$Default;", "", "<init>", "()V", "lightmapRes", "", "getLightmapRes", "()I", "bmpLightmap", "Landroid/graphics/Bitmap;", "getBmpLightmap", "()Landroid/graphics/Bitmap;", "setBmpLightmap", "(Landroid/graphics/Bitmap;)V", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Default {
            public static final Default INSTANCE = new Default();
            private static Bitmap bmpLightmap;
            private static final int lightmapRes = R$drawable.lightmap;

            private Default() {
            }

            public final Bitmap getBmpLightmap() {
                return bmpLightmap;
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

        private Companion() {
        }
    }

    public RadialGradRenderEffect() {
        super(false, 1, (e) null);
    }

    private final void buildSpotArrays(int i2) {
        this.spotEnabled = resize(this.spotEnabled, i2);
        this.spotColors = resize(this.spotColors, i2 * 4);
        this.spotPositions = resize(this.spotPositions, i2 * 2);
        this.spotScales = resize(this.spotScales, i2);
    }

    private final float[] resize(float[] fArr, int i2) {
        float[] fArr2 = new float[i2];
        int length = fArr.length;
        int i7 = 0;
        int i8 = 0;
        while (i7 < length) {
            fArr2[i8] = fArr[i7];
            i7++;
            i8++;
        }
        return fArr2;
    }

    /* access modifiers changed from: private */
    public static final void setBackgroundColor$lambda$6(int i2, RadialGradRenderEffect radialGradRenderEffect, RuntimeShader runtimeShader) {
        Color valueOf = Color.valueOf(i2);
        RuntimeShader shader2 = radialGradRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uBaseColor", valueOf.alpha() * valueOf.red(), valueOf.alpha() * valueOf.green(), valueOf.alpha() * valueOf.blue(), valueOf.alpha());
        }
    }

    /* access modifiers changed from: private */
    public static final void setLightMap$lambda$4(RadialGradRenderEffect radialGradRenderEffect, Bitmap bitmap, RuntimeShader runtimeShader) {
        RuntimeShader shader2 = radialGradRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setInputBuffer("spotLightMapShader", new BitmapShader(bitmap, Shader.TileMode.DECAL, Shader.TileMode.DECAL));
        }
        RuntimeShader shader3 = radialGradRenderEffect.getShader();
        if (shader3 != null) {
            shader3.setFloatUniform("uLightMapSize", (float) bitmap.getWidth(), (float) bitmap.getHeight());
        }
        radialGradRenderEffect.hasLightMap = true;
    }

    /* access modifiers changed from: private */
    public static final void setSpotColor$lambda$10(int i2, RadialGradRenderEffect radialGradRenderEffect, int i7, RuntimeShader runtimeShader) {
        Color valueOf = Color.valueOf(i2);
        int i8 = i7 * 4;
        radialGradRenderEffect.spotColors[i8] = valueOf.red();
        radialGradRenderEffect.spotColors[i8 + 1] = valueOf.green();
        radialGradRenderEffect.spotColors[i8 + 2] = valueOf.blue();
        radialGradRenderEffect.spotColors[i8 + 3] = valueOf.alpha();
        RuntimeShader shader2 = radialGradRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uSpotColors", radialGradRenderEffect.spotColors);
        }
    }

    /* access modifiers changed from: private */
    public static final void setSpotCount$lambda$7(RadialGradRenderEffect radialGradRenderEffect, int i2, RuntimeShader runtimeShader) {
        radialGradRenderEffect.buildSpotArrays(5);
        RuntimeShader shader2 = radialGradRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setIntUniform("uSpotCount", i2);
        }
    }

    /* access modifiers changed from: private */
    public static final void setSpotEnabled$lambda$8(RadialGradRenderEffect radialGradRenderEffect, int i2, boolean z, RuntimeShader runtimeShader) {
        float f;
        float[] fArr = radialGradRenderEffect.spotEnabled;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        fArr[i2] = f;
        RuntimeShader shader2 = radialGradRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uSpotEnabled", radialGradRenderEffect.spotEnabled);
        }
    }

    /* access modifiers changed from: private */
    public static final void setSpotPosition$lambda$11(RadialGradRenderEffect radialGradRenderEffect, int i2, PointF pointF, RuntimeShader runtimeShader) {
        float[] fArr = radialGradRenderEffect.spotPositions;
        int i7 = i2 * 2;
        fArr[i7] = pointF.x;
        fArr[i7 + 1] = pointF.y;
        RuntimeShader shader2 = radialGradRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uSpotPositions", radialGradRenderEffect.spotPositions);
        }
    }

    /* access modifiers changed from: private */
    public static final void setSpotScale$lambda$12(RadialGradRenderEffect radialGradRenderEffect, int i2, float f, RuntimeShader runtimeShader) {
        radialGradRenderEffect.spotScales[i2] = f;
        RuntimeShader shader2 = radialGradRenderEffect.getShader();
        if (shader2 != null) {
            shader2.setFloatUniform("uSpotScales", radialGradRenderEffect.spotScales);
        }
    }

    public void destroy() {
        super.destroy();
        Log.i("RadialGradRenderEffect", "destroy");
        Companion.Default defaultR = Companion.Default.INSTANCE;
        Bitmap bmpLightmap = defaultR.getBmpLightmap();
        if (bmpLightmap != null) {
            bmpLightmap.recycle();
        }
        defaultR.setBmpLightmap((Bitmap) null);
        setShader((RuntimeShader) null);
    }

    public RenderEffect getRenderEffect() {
        RenderEffect renderEffect;
        RuntimeShader shader2 = getShader();
        if (shader2 != null) {
            renderEffect = RenderEffect.createRuntimeShaderEffect(shader2, "inputShader");
        } else {
            renderEffect = null;
        }
        int i2 = this.blurRadius;
        if (i2 > 0) {
            float f = (float) i2;
            j.b(renderEffect);
            Shader.TileMode tileMode = Shader.TileMode.MIRROR;
            RenderEffect h5 = RenderEffect.createBlurEffect(f, f, renderEffect, Shader.TileMode.MIRROR);
            j.b(h5);
            return h5;
        }
        j.b(renderEffect);
        return renderEffect;
    }

    public RuntimeShader getShader() {
        return this.shader;
    }

    public final int getSpotColor(int i2) {
        float[] fArr = this.spotColors;
        int i7 = i2 * 4;
        return Color.argb(fArr[i7 + 3], fArr[i7], fArr[i7 + 1], fArr[i7 + 2]);
    }

    public final PointF getSpotPosition(int i2) {
        float[] fArr = this.spotPositions;
        int i7 = i2 * 2;
        return new PointF(fArr[i7], fArr[i7 + 1]);
    }

    public final float getSpotScale(int i2) {
        return this.spotScales[i2];
    }

    public void onInitialize(Context context) {
        Bitmap bmpLightmap;
        j.e(context, StateHandler.KEY_APP_STATE);
        Companion.Default defaultR = Companion.Default.INSTANCE;
        if (defaultR.getBmpLightmap() == null) {
            defaultR.setBmpLightmap(BitmapFactory.decodeResource(context.getResources(), defaultR.getLightmapRes()));
        }
        if (!this.hasLightMap && (bmpLightmap = defaultR.getBmpLightmap()) != null) {
            setLightMap(bmpLightmap);
        }
    }

    public final void setBackgroundColor(int i2) {
        updateShader(new c(i2, this));
    }

    public final void setBlurRadius(int i2) {
        this.blurRadius = i2;
        invalidate();
    }

    public final void setLightMap(Bitmap bitmap) {
        j.e(bitmap, "bitmap");
        updateShader(new m(this, bitmap, 3));
    }

    public void setShader(RuntimeShader runtimeShader) {
        this.shader = runtimeShader;
    }

    public final void setSpotColor(int i2, int i7) {
        updateShader(new ad.d(this, i7, i2));
    }

    public final void setSpotCount(int i2) {
        if (this.spotCount != i2) {
            this.spotCount = i2;
            updateShader(new c(this, i2));
        }
    }

    public final void setSpotEnabled(int i2, boolean z) {
        updateShader(new dd.d(this, i2, z));
    }

    public final void setSpotPosition(int i2, PointF pointF) {
        j.e(pointF, Message.KEY_POSITION);
        updateShader(new dd.e(this, i2, pointF));
    }

    public final void setSpotScale(int i2, float f) {
        updateShader(new f(this, i2, f));
    }
}
