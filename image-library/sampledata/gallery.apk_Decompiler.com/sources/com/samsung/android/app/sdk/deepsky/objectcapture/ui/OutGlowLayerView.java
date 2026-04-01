package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 +2\u00020\u0001:\u0001+B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u001b\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0004\u0010\bB#\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0004\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J%\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\r\u0010\u001c\u001a\u00020\u000e¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\u000e¢\u0006\u0004\b\u001e\u0010\u001dJ\r\u0010\u001f\u001a\u00020\u000e¢\u0006\u0004\b\u001f\u0010\u001dJ\u0017\u0010\"\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020 H\u0015¢\u0006\u0004\b\"\u0010#R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010$R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010$R\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010%R\u0016\u0010\u0015\u001a\u00020\u00148\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010&R\u0018\u0010(\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\u0018\u0010*\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b*\u0010)¨\u0006,"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/OutGlowLayerView;", "Landroid/view/View;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "", "delay", "Lme/x;", "startOutGlowAnimation", "(J)V", "Landroid/graphics/Bitmap;", "mask", "objectBitmap", "", "offset", "updateMask", "(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;F)V", "", "isSelectAll", "updateSelectAll", "(Z)V", "startAnimation", "()V", "endTimeAnimation", "recycle", "Landroid/graphics/Canvas;", "canvas", "onDraw", "(Landroid/graphics/Canvas;)V", "Landroid/graphics/Bitmap;", "Z", "F", "Landroid/animation/ObjectAnimator;", "animator", "Landroid/animation/ObjectAnimator;", "outGlowAnimator", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class OutGlowLayerView extends View {
    public static final Companion Companion = new Companion((e) null);
    public static final long FADE_IN_OUT_ANIMATION_DELAY = 1000;
    public static final long FADE_IN_OUT_ANIMATION_DURATION = 1000;
    public static final float OUT_GLOW_ALPHA = 0.0f;
    public static final float OUT_GLOW_FADE_IN_OUT_END_ALPHA = 0.35f;
    public static final float OUT_GLOW_FADE_IN_OUT_START_ALPHA = 0.8f;
    public static final long OUT_GLOW_SHOW_DELAY = 450;
    public static final long OUT_GLOW_SHOW_DURATION = 400;
    private ObjectAnimator animator;
    private boolean isSelectAll;
    private Bitmap mask;
    private Bitmap objectBitmap;
    private float offset;
    private ObjectAnimator outGlowAnimator;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tXT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/OutGlowLayerView$Companion;", "", "<init>", "()V", "OUT_GLOW_ALPHA", "", "OUT_GLOW_FADE_IN_OUT_START_ALPHA", "OUT_GLOW_FADE_IN_OUT_END_ALPHA", "FADE_IN_OUT_ANIMATION_DELAY", "", "FADE_IN_OUT_ANIMATION_DURATION", "OUT_GLOW_SHOW_DELAY", "OUT_GLOW_SHOW_DURATION", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OutGlowLayerView(Context context) {
        super(context);
        j.e(context, "context");
    }

    /* access modifiers changed from: private */
    public final void startOutGlowAnimation(long j2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, View.ALPHA, new float[]{0.8f, 0.35f});
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.setStartDelay(j2);
        ofFloat.setDuration(1000);
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(2);
        this.outGlowAnimator = ofFloat;
        ofFloat.start();
    }

    public final void endTimeAnimation() {
        ObjectAnimator objectAnimator = this.animator;
        if (objectAnimator != null) {
            objectAnimator.setCurrentPlayTime(400);
        }
        ObjectAnimator objectAnimator2 = this.outGlowAnimator;
        if (objectAnimator2 != null) {
            objectAnimator2.setCurrentPlayTime(1000);
        }
    }

    public void onDraw(Canvas canvas) {
        j.e(canvas, "canvas");
        Bitmap bitmap = this.mask;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
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
        ObjectAnimator objectAnimator = this.animator;
        if (objectAnimator != null) {
            objectAnimator.removeAllListeners();
        }
        ObjectAnimator objectAnimator2 = this.outGlowAnimator;
        if (objectAnimator2 != null) {
            objectAnimator2.removeAllListeners();
        }
        ObjectAnimator objectAnimator3 = this.animator;
        if (objectAnimator3 != null) {
            objectAnimator3.end();
        }
        ObjectAnimator objectAnimator4 = this.outGlowAnimator;
        if (objectAnimator4 != null) {
            objectAnimator4.end();
        }
        this.animator = null;
        this.outGlowAnimator = null;
    }

    public final void startAnimation() {
        if (!this.isSelectAll) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, View.ALPHA, new float[]{0.0f, 0.8f});
            ofFloat.setInterpolator(new LinearInterpolator());
            ofFloat.setStartDelay(450);
            ofFloat.setDuration(400);
            ofFloat.addListener(new OutGlowLayerView$startAnimation$1$1(this));
            this.animator = ofFloat;
            ofFloat.start();
            return;
        }
        startOutGlowAnimation(0);
    }

    public final void updateMask(Bitmap bitmap, Bitmap bitmap2, float f) {
        j.e(bitmap, "mask");
        j.e(bitmap2, "objectBitmap");
        setAlpha(0.0f);
        this.mask = bitmap;
        this.objectBitmap = bitmap2;
        this.offset = f;
        invalidate();
    }

    public final void updateSelectAll(boolean z) {
        this.isSelectAll = z;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OutGlowLayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.e(context, "context");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OutGlowLayerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        j.e(context, "context");
    }
}
