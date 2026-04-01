package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0006¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0014¢\u0006\u0004\b\u0016\u0010\u000eR\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u0018R\u0016\u0010\u0013\u001a\u00020\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u001aR\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u0018¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/DummyImageView;", "Landroidx/appcompat/widget/AppCompatImageView;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Landroid/graphics/Canvas;", "canvas", "Lme/x;", "drawMaskedImage", "(Landroid/graphics/Canvas;)V", "recycleBitmap", "()V", "Landroid/graphics/Bitmap;", "bitmap", "offset", "setMask", "(Landroid/graphics/Bitmap;I)V", "onDraw", "mask", "Landroid/graphics/Bitmap;", "gradientBitmap", "I", "resultBitmap", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DummyImageView extends AppCompatImageView {
    private Bitmap gradientBitmap;
    private Bitmap mask;
    private int offset;
    private Bitmap resultBitmap;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DummyImageView(Context context) {
        this(context, (AttributeSet) null, 0, 6, (e) null);
        j.e(context, "context");
    }

    private final void drawMaskedImage(Canvas canvas) {
        Bitmap bitmap = this.mask;
        if (bitmap != null && !bitmap.isRecycled()) {
            AnimationUtils animationUtils = AnimationUtils.INSTANCE;
            canvas.drawBitmap(bitmap, animationUtils.half(this.offset), animationUtils.half(this.offset), (Paint) null);
        }
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

    public final void setMask(Bitmap bitmap, int i2) {
        j.e(bitmap, "bitmap");
        this.mask = bitmap;
        this.offset = i2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DummyImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, (e) null);
        j.e(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DummyImageView(Context context, AttributeSet attributeSet, int i2, int i7, e eVar) {
        this(context, (i7 & 2) != 0 ? null : attributeSet, (i7 & 4) != 0 ? 0 : i2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DummyImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        j.e(context, "context");
    }
}
