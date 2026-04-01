package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import B2.h;
import Ee.a;
import Ee.d;
import Ge.c;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.samsung.android.app.sdk.deepsky.objectcapture.R;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 22\u00020\u0001:\u00012B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u001b\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0004\u0010\bB#\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0004\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J%\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u0015¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\u0015¢\u0006\u0004\b\u001d\u0010\u001cJ\u0017\u0010 \u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u001eH\u0015¢\u0006\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010&\u001a\u00020%8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020%8\u0002X\u0004¢\u0006\u0006\n\u0004\b(\u0010'R\u0014\u0010*\u001a\u00020)8\u0002X\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u0018\u0010-\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010/R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010/R\u0018\u00100\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b0\u0010/R\u0016\u0010\u0014\u001a\u00020\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0014\u00101¨\u00063"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ParticleLayerView;", "Landroid/view/View;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Landroid/graphics/Bitmap;", "source", "size", "getShadowBitmap", "(Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;", "mask", "objectBitmap", "", "offset", "Lme/x;", "updateMask", "(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;F)V", "bitmap", "addShadow", "(Landroid/graphics/Bitmap;I)V", "startAnimation", "()V", "recycle", "Landroid/graphics/Canvas;", "canvas", "onDraw", "(Landroid/graphics/Canvas;)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/GalaxyShader;", "runtimeShader", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/GalaxyShader;", "Landroid/graphics/Paint;", "runtimeShaderPaint", "Landroid/graphics/Paint;", "particleOutlinePaint", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/FrameRateStepper;", "frameRateStepper", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/FrameRateStepper;", "Landroid/animation/ValueAnimator;", "animator", "Landroid/animation/ValueAnimator;", "Landroid/graphics/Bitmap;", "shadow", "F", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ParticleLayerView extends View {
    private static final long ALPHA_ANIMATION_DURATION = 300;
    public static final Companion Companion = new Companion((e) null);
    private static final float DEFAULT_ALPHA = 0.99f;
    private static final float SHADOW_RADIUS = 4.0f;
    private ValueAnimator animator;
    private final FrameRateStepper frameRateStepper;
    private Bitmap mask;
    private Bitmap objectBitmap;
    private float offset;
    private final Paint particleOutlinePaint;
    private final GalaxyShader runtimeShader;
    private Paint runtimeShaderPaint;
    private Bitmap shadow;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ParticleLayerView$Companion;", "", "<init>", "()V", "DEFAULT_ALPHA", "", "SHADOW_RADIUS", "ALPHA_ANIMATION_DURATION", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [Ge.c, Ge.e] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ParticleLayerView(Context context) {
        super(context);
        j.e(context, "context");
        Context context2 = getContext();
        j.d(context2, "getContext(...)");
        GalaxyShader galaxyShader = new GalaxyShader(context2);
        this.runtimeShader = galaxyShader;
        Paint paint = new Paint(1);
        paint.setShader(galaxyShader);
        this.runtimeShaderPaint = paint;
        Paint paint2 = new Paint(1);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        this.particleOutlinePaint = paint2;
        ? cVar = new c(0, 50, 1);
        a aVar = d.d;
        this.frameRateStepper = new FrameRateStepper((float) B1.a.P(cVar));
    }

    private final Bitmap getShadowBitmap(Bitmap bitmap, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth() + i2, bitmap.getHeight() + i2, Bitmap.Config.ARGB_8888);
        j.d(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        int color = getContext().getColor(R.color.glow_color);
        PorterDuff.Mode mode = PorterDuff.Mode.CLEAR;
        canvas.drawColor(color, mode);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setMaskFilter(new BlurMaskFilter(25.0f, BlurMaskFilter.Blur.NORMAL));
        Bitmap extractAlpha = bitmap.extractAlpha();
        j.d(extractAlpha, "extractAlpha(...)");
        paint.setColor(color);
        float f = (float) (i2 / 2);
        canvas.drawBitmap(extractAlpha, f, f, paint);
        paint.setColor(0);
        paint.setXfermode(new PorterDuffXfermode(mode));
        canvas.drawBitmap(extractAlpha, f, f, paint);
        extractAlpha.recycle();
        return createBitmap;
    }

    /* access modifiers changed from: private */
    public static final void startAnimation$lambda$4$lambda$3(ParticleLayerView particleLayerView, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "animation");
        Object animatedValue = valueAnimator.getAnimatedValue();
        j.c(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        float floatValue = ((Float) animatedValue).floatValue();
        AnimationUtils animationUtils = AnimationUtils.INSTANCE;
        int convertPaintAlpha = animationUtils.convertPaintAlpha(floatValue);
        int applyAlpha = animationUtils.applyAlpha(-1, convertPaintAlpha);
        particleLayerView.particleOutlinePaint.setShadowLayer(SHADOW_RADIUS, 0.0f, 0.0f, applyAlpha);
        particleLayerView.particleOutlinePaint.setAlpha(convertPaintAlpha);
        particleLayerView.runtimeShaderPaint.setShadowLayer(SHADOW_RADIUS, 0.0f, 0.0f, applyAlpha);
        particleLayerView.runtimeShaderPaint.setAlpha(convertPaintAlpha);
        particleLayerView.invalidate();
    }

    public final void addShadow(Bitmap bitmap, int i2) {
        j.e(bitmap, "bitmap");
        Bitmap createBitmap = Bitmap.createBitmap((int) (((float) bitmap.getWidth()) + this.offset), (int) (((float) bitmap.getHeight()) + this.offset), Bitmap.Config.ARGB_8888);
        this.shadow = createBitmap;
        if (createBitmap != null) {
            Canvas canvas = new Canvas(createBitmap);
            Bitmap shadowBitmap = getShadowBitmap(bitmap, i2);
            canvas.drawBitmap(shadowBitmap, 0.0f, 0.0f, (Paint) null);
            shadowBitmap.recycle();
            setBackground(new BitmapDrawable(getResources(), createBitmap));
        }
    }

    public void onDraw(Canvas canvas) {
        j.e(canvas, "canvas");
        canvas.setMatrix(new Matrix());
        setAlpha(DEFAULT_ALPHA);
        Bitmap bitmap = this.mask;
        if (bitmap != null) {
            Paint paint = new Paint(1);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            float width = ((float) bitmap.getWidth()) + this.offset;
            float height = ((float) bitmap.getHeight()) + this.offset;
            this.runtimeShader.updateResolution(width, height);
            this.runtimeShader.updateTime(this.frameRateStepper.step());
            Canvas canvas2 = canvas;
            canvas2.drawRect(0.0f, 0.0f, width, height, this.runtimeShaderPaint);
            Bitmap createBitmap = Bitmap.createBitmap((int) width, (int) height, Bitmap.Config.ARGB_8888);
            j.d(createBitmap, "createBitmap(...)");
            Canvas canvas3 = new Canvas(createBitmap);
            canvas3.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            Bitmap bitmap2 = this.objectBitmap;
            j.b(bitmap2);
            canvas3.drawBitmap(bitmap2, 0.0f, 0.0f, paint);
            canvas2.drawBitmap(createBitmap, 0.0f, 0.0f, this.particleOutlinePaint);
            createBitmap.recycle();
            invalidate();
        }
    }

    public final void recycle() {
        Bitmap bitmap = this.mask;
        if (bitmap != null) {
            AnimationUtils.INSTANCE.checkAndRecycle(bitmap);
        }
        this.mask = null;
        Bitmap bitmap2 = this.objectBitmap;
        if (bitmap2 != null) {
            AnimationUtils.INSTANCE.checkAndRecycle(bitmap2);
        }
        this.objectBitmap = null;
        Bitmap bitmap3 = this.shadow;
        if (bitmap3 != null) {
            AnimationUtils.INSTANCE.checkAndRecycle(bitmap3);
        }
        this.shadow = null;
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.animator;
        if (valueAnimator2 != null) {
            valueAnimator2.removeAllListeners();
        }
        this.animator = null;
    }

    public final void startAnimation() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, DEFAULT_ALPHA});
        ofFloat.setDuration(ALPHA_ANIMATION_DURATION);
        ofFloat.setInterpolator(new DecelerateInterpolator());
        ofFloat.addUpdateListener(new h(11, this));
        this.animator = ofFloat;
        ofFloat.start();
    }

    public final void updateMask(Bitmap bitmap, Bitmap bitmap2, float f) {
        j.e(bitmap, "mask");
        j.e(bitmap2, "objectBitmap");
        this.mask = bitmap;
        this.objectBitmap = bitmap2;
        this.offset = f;
        invalidate();
    }

    /* JADX WARNING: type inference failed for: r5v4, types: [Ge.c, Ge.e] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ParticleLayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.e(context, "context");
        Context context2 = getContext();
        j.d(context2, "getContext(...)");
        GalaxyShader galaxyShader = new GalaxyShader(context2);
        this.runtimeShader = galaxyShader;
        Paint paint = new Paint(1);
        paint.setShader(galaxyShader);
        this.runtimeShaderPaint = paint;
        Paint paint2 = new Paint(1);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        this.particleOutlinePaint = paint2;
        ? cVar = new c(0, 50, 1);
        a aVar = d.d;
        this.frameRateStepper = new FrameRateStepper((float) B1.a.P(cVar));
    }

    /* JADX WARNING: type inference failed for: r4v4, types: [Ge.c, Ge.e] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ParticleLayerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        j.e(context, "context");
        Context context2 = getContext();
        j.d(context2, "getContext(...)");
        GalaxyShader galaxyShader = new GalaxyShader(context2);
        this.runtimeShader = galaxyShader;
        Paint paint = new Paint(1);
        paint.setShader(galaxyShader);
        this.runtimeShaderPaint = paint;
        Paint paint2 = new Paint(1);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        this.particleOutlinePaint = paint2;
        ? cVar = new c(0, 50, 1);
        a aVar = d.d;
        this.frameRateStepper = new FrameRateStepper((float) B1.a.P(cVar));
    }
}
