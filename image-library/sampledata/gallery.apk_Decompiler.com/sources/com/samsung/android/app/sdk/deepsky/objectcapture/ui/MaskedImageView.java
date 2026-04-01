package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.PathInterpolator;
import androidx.appcompat.widget.AppCompatImageView;
import com.samsung.android.app.sdk.deepsky.objectcapture.R;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0000\u0018\u0000 F2\u00020\u0001:\u0001FB'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u001e\u0010\u001dJ\u0017\u0010!\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u001fH\u0002¢\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u001fH\u0002¢\u0006\u0004\b#\u0010\"J7\u0010*\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$2\u0006\u0010'\u001a\u00020$2\u0006\u0010)\u001a\u00020(H\u0002¢\u0006\u0004\b*\u0010+J\u0017\u0010,\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u001fH\u0002¢\u0006\u0004\b,\u0010\"J%\u00100\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\n2\u0006\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0006¢\u0006\u0004\b0\u00101J\u0015\u00104\u001a\u00020\u000e2\u0006\u00103\u001a\u000202¢\u0006\u0004\b4\u00105J\r\u00106\u001a\u00020\u000e¢\u0006\u0004\b6\u0010\u0010J\r\u00107\u001a\u00020\u000e¢\u0006\u0004\b7\u0010\u0010J\u0017\u00108\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u001fH\u0014¢\u0006\u0004\b8\u0010\"J\u000f\u00109\u001a\u00020\u000eH\u0014¢\u0006\u0004\b9\u0010\u0010R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010:R\u0016\u0010;\u001a\u00020\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b;\u0010<R\u0016\u0010=\u001a\u00020\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b=\u0010<R\u0016\u0010.\u001a\u00020\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b.\u0010<R\u0016\u0010/\u001a\u00020\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b/\u0010<R\u0018\u0010>\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b>\u0010:R\u0018\u0010?\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b?\u0010:R\u0016\u0010@\u001a\u0002028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u0014\u0010B\u001a\u00020(8\u0002X\u0004¢\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020(8\u0002X\u0004¢\u0006\u0006\n\u0004\bD\u0010CR\u0014\u0010E\u001a\u00020(8\u0002X\u0004¢\u0006\u0006\n\u0004\bE\u0010C¨\u0006G"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/MaskedImageView;", "Landroidx/appcompat/widget/AppCompatImageView;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Landroid/graphics/Bitmap;", "mask", "createTextureBitmap", "(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;", "Lme/x;", "startAnimationInternal", "()V", "Landroid/animation/AnimatorSet;", "initViewUpdateAnimator", "()Landroid/animation/AnimatorSet;", "getAnimationCircleSize", "(Landroid/graphics/Bitmap;)I", "", "duration", "getAnimationDuration", "(JLandroid/graphics/Bitmap;)J", "size", "Landroid/animation/ValueAnimator;", "getOuterCircleAnimator", "(ILandroid/graphics/Bitmap;)Landroid/animation/ValueAnimator;", "getInnerCircleAnimator", "Landroid/graphics/Canvas;", "canvas", "drawMaskedImage", "(Landroid/graphics/Canvas;)V", "drawOuterCircle", "", "radius", "centerX", "centerY", "Landroid/graphics/Paint;", "paint", "drawRoundedPolygonPath", "(Landroid/graphics/Canvas;FFFLandroid/graphics/Paint;)V", "drawInnerCircle", "bitmap", "offset", "shadow", "setMask", "(Landroid/graphics/Bitmap;II)V", "Landroid/graphics/PointF;", "point", "setStartPoint", "(Landroid/graphics/PointF;)V", "recycleBitmap", "startAnimation", "onDraw", "onDetachedFromWindow", "Landroid/graphics/Bitmap;", "outCircle", "I", "innerCircle", "resultBitmap", "gradientBitmap", "startPoint", "Landroid/graphics/PointF;", "maskPaint", "Landroid/graphics/Paint;", "texturePaint", "objectPaint", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MaskedImageView extends AppCompatImageView {
    public static final float ALPHA_FROM = 0.5f;
    public static final float ALPHA_TO = 0.0f;
    public static final float ALPHA_VISIBLE = 1.0f;
    public static final int ANIMATION_END_TIME = 100;
    public static final int ANIMATION_START_TIME = 1;
    public static final float BLUR_RADIUS = 30.0f;
    public static final long CIRCLE_ANIMATION_DURATION = 1200;
    public static final Companion Companion = new Companion((e) null);
    public static final long INNER_CIRCLE_ANIMATION_DURATION = 1200;
    /* access modifiers changed from: private */
    public static final PathInterpolator INNER_CIRCLE_INTERPOLATOR = new PathInterpolator(0.33f, 0.0f, 0.4f, 1.0f);
    public static final long OUTER_CIRCLE_ANIMATION_DURATION = 900;
    /* access modifiers changed from: private */
    public static final PathInterpolator OUTER_CIRCLE_INTERPOLATOR = new PathInterpolator(0.33f, 0.0f, 0.4f, 1.0f);
    public static final String TAG = "MaskedImageView";
    private Bitmap gradientBitmap;
    private int innerCircle;
    private Bitmap mask;
    private final Paint maskPaint;
    private final Paint objectPaint;
    private int offset;
    private int outCircle;
    private Bitmap resultBitmap;
    private int shadow;
    private PointF startPoint;
    private final Paint texturePaint;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nXT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eXT¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/MaskedImageView$Companion;", "", "<init>", "()V", "TAG", "", "ANIMATION_START_TIME", "", "ANIMATION_END_TIME", "OUTER_CIRCLE_ANIMATION_DURATION", "", "INNER_CIRCLE_ANIMATION_DURATION", "CIRCLE_ANIMATION_DURATION", "ALPHA_FROM", "", "ALPHA_TO", "ALPHA_VISIBLE", "BLUR_RADIUS", "INNER_CIRCLE_INTERPOLATOR", "Landroid/view/animation/PathInterpolator;", "getINNER_CIRCLE_INTERPOLATOR", "()Landroid/view/animation/PathInterpolator;", "OUTER_CIRCLE_INTERPOLATOR", "getOUTER_CIRCLE_INTERPOLATOR", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final PathInterpolator getINNER_CIRCLE_INTERPOLATOR() {
            return MaskedImageView.INNER_CIRCLE_INTERPOLATOR;
        }

        public final PathInterpolator getOUTER_CIRCLE_INTERPOLATOR() {
            return MaskedImageView.OUTER_CIRCLE_INTERPOLATOR;
        }

        private Companion() {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MaskedImageView(Context context) {
        this(context, (AttributeSet) null, 0, 6, (e) null);
        j.e(context, "context");
    }

    private final Bitmap createTextureBitmap(Bitmap bitmap) {
        Drawable drawable = getResources().getDrawable(R.drawable.texture, (Resources.Theme) null);
        drawable.setBounds(0, 0, bitmap.getWidth() + this.offset, bitmap.getHeight() + this.offset);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth() + this.offset, bitmap.getHeight() + this.offset, Bitmap.Config.ARGB_8888);
        j.d(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        drawable.draw(canvas);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.texturePaint);
        return createBitmap;
    }

    private final void drawInnerCircle(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        paint.setMaskFilter(new BlurMaskFilter(30.0f, BlurMaskFilter.Blur.NORMAL));
        Bitmap bitmap = this.mask;
        if (bitmap != null) {
            AnimationUtils animationUtils = AnimationUtils.INSTANCE;
            canvas.drawCircle(animationUtils.halfWidth(bitmap) + this.startPoint.x, animationUtils.halfHeight(bitmap) + this.startPoint.y, (float) this.innerCircle, paint);
            float halfWidth = animationUtils.halfWidth(bitmap) + this.startPoint.x;
            float halfHeight = animationUtils.halfHeight(bitmap) + this.startPoint.y;
            drawRoundedPolygonPath(canvas, (float) this.innerCircle, halfWidth, halfHeight, paint);
        }
    }

    private final void drawMaskedImage(Canvas canvas) {
        Bitmap bitmap = this.mask;
        if (bitmap != null && !bitmap.isRecycled()) {
            if (this.gradientBitmap == null) {
                this.gradientBitmap = createTextureBitmap(bitmap);
            }
            if (this.resultBitmap == null) {
                this.resultBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            }
            Bitmap bitmap2 = this.resultBitmap;
            if (bitmap2 != null) {
                Canvas canvas2 = new Canvas(bitmap2);
                drawOuterCircle(canvas2);
                Bitmap bitmap3 = this.gradientBitmap;
                j.b(bitmap3);
                canvas2.drawBitmap(bitmap3, 0.0f, 0.0f, this.maskPaint);
                canvas2.drawBitmap(bitmap, 0.0f, 0.0f, this.objectPaint);
                drawInnerCircle(canvas2);
                AnimationUtils animationUtils = AnimationUtils.INSTANCE;
                canvas.drawBitmap(bitmap2, animationUtils.half(this.offset), animationUtils.half(this.offset), (Paint) null);
            }
        }
    }

    private final void drawOuterCircle(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setAntiAlias(true);
        paint.setMaskFilter(new BlurMaskFilter(30.0f, BlurMaskFilter.Blur.NORMAL));
        Bitmap bitmap = this.mask;
        if (bitmap != null) {
            AnimationUtils animationUtils = AnimationUtils.INSTANCE;
            canvas.drawCircle(animationUtils.halfWidth(bitmap) + this.startPoint.x, animationUtils.halfHeight(bitmap) + this.startPoint.y, (float) this.outCircle, paint);
            float halfWidth = animationUtils.halfWidth(bitmap) + this.startPoint.x;
            float halfHeight = animationUtils.halfHeight(bitmap) + this.startPoint.y;
            drawRoundedPolygonPath(canvas, (float) this.outCircle, halfWidth, halfHeight, paint);
        }
    }

    private final void drawRoundedPolygonPath(Canvas canvas, float f, float f5, float f8, Paint paint) {
        Path path = new Path();
        AnimationUtils.getRoundedPolygonPath(f, f5, f8, path);
        canvas.drawPath(path, paint);
    }

    private final int getAnimationCircleSize(Bitmap bitmap) {
        float abs = Math.abs(this.startPoint.x) + ((float) bitmap.getWidth());
        float abs2 = Math.abs(this.startPoint.y) + ((float) bitmap.getHeight());
        if (abs > abs2) {
            return (int) abs;
        }
        return (int) abs2;
    }

    private final long getAnimationDuration(long j2, Bitmap bitmap) {
        long abs;
        if (bitmap.getWidth() < bitmap.getHeight()) {
            abs = ((long) (Math.abs(this.startPoint.y) * ((float) j2))) / ((long) bitmap.getHeight());
        } else {
            abs = ((long) (Math.abs(this.startPoint.x) * ((float) j2))) / ((long) bitmap.getWidth());
        }
        return abs + j2;
    }

    private final ValueAnimator getInnerCircleAnimator(int i2, Bitmap bitmap) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{1, i2});
        ofInt.setInterpolator(INNER_CIRCLE_INTERPOLATOR);
        ofInt.setDuration(getAnimationDuration(1200, bitmap));
        ofInt.addUpdateListener(new a(this, 0));
        return ofInt;
    }

    /* access modifiers changed from: private */
    public static final void getInnerCircleAnimator$lambda$8$lambda$7(MaskedImageView maskedImageView, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "animation");
        Object animatedValue = valueAnimator.getAnimatedValue();
        j.c(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        maskedImageView.innerCircle = ((Integer) animatedValue).intValue();
    }

    private final ValueAnimator getOuterCircleAnimator(int i2, Bitmap bitmap) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{1, i2});
        ofInt.setInterpolator(OUTER_CIRCLE_INTERPOLATOR);
        ofInt.setDuration(getAnimationDuration(900, bitmap));
        ofInt.addUpdateListener(new a(this, 1));
        return ofInt;
    }

    /* access modifiers changed from: private */
    public static final void getOuterCircleAnimator$lambda$6$lambda$5(MaskedImageView maskedImageView, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "animation");
        Object animatedValue = valueAnimator.getAnimatedValue();
        j.c(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        maskedImageView.outCircle = ((Integer) animatedValue).intValue();
    }

    private final AnimatorSet initViewUpdateAnimator() {
        AnimatorSet animatorSet = new AnimatorSet();
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{1, 100});
        ofInt.setDuration(1200);
        ofInt.addUpdateListener(new a(this, 2));
        ofInt.addListener(new MaskedImageView$initViewUpdateAnimator$2(this, animatorSet));
        Bitmap bitmap = this.mask;
        if (bitmap != null) {
            int animationCircleSize = getAnimationCircleSize(bitmap);
            animatorSet.playTogether(new Animator[]{ofInt, getOuterCircleAnimator(animationCircleSize, bitmap), getInnerCircleAnimator(animationCircleSize, bitmap)});
        }
        return animatorSet;
    }

    /* access modifiers changed from: private */
    public static final void initViewUpdateAnimator$lambda$3(MaskedImageView maskedImageView, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "it");
        maskedImageView.invalidate();
    }

    private final void startAnimationInternal() {
        setAlpha(0.5f);
        initViewUpdateAnimator().start();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        recycleBitmap();
    }

    public void onDraw(Canvas canvas) {
        j.e(canvas, "canvas");
        drawMaskedImage(canvas);
    }

    public final void recycleBitmap() {
        Bitmap bitmap = this.mask;
        if (bitmap != null) {
            bitmap.recycle();
        }
        Bitmap bitmap2 = this.gradientBitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
        Bitmap bitmap3 = this.resultBitmap;
        if (bitmap3 != null) {
            bitmap3.recycle();
        }
        this.mask = null;
        this.gradientBitmap = null;
        this.resultBitmap = null;
    }

    public final void setMask(Bitmap bitmap, int i2, int i7) {
        j.e(bitmap, "bitmap");
        this.mask = bitmap;
        this.offset = i2;
        this.shadow = i7;
    }

    public final void setStartPoint(PointF pointF) {
        j.e(pointF, "point");
        this.startPoint = pointF;
    }

    public final void startAnimation() {
        startAnimationInternal();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MaskedImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, (e) null);
        j.e(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MaskedImageView(Context context, AttributeSet attributeSet, int i2, int i7, e eVar) {
        this(context, (i7 & 2) != 0 ? null : attributeSet, (i7 & 4) != 0 ? 0 : i2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MaskedImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        j.e(context, "context");
        this.outCircle = 1;
        this.innerCircle = 1;
        this.startPoint = new PointF(0.0f, 0.0f);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        this.maskPaint = paint;
        Paint paint2 = new Paint();
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.ADD));
        this.texturePaint = paint2;
        Paint paint3 = new Paint();
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        this.objectPaint = paint3;
        setAlpha(1.0f);
    }
}
