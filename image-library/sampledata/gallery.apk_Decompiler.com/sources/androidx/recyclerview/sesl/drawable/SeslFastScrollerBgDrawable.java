package androidx.recyclerview.sesl.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.ColorUtils;
import com.samsung.android.sdk.cover.ScoverState;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u0011\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u000bH\u0017¢\u0006\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\u00020\u00038\u0016@\u0016X\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Landroidx/recyclerview/sesl/drawable/SeslFastScrollerBgDrawable;", "Landroid/graphics/drawable/Drawable;", "", "", "<init>", "()V", "Landroid/graphics/Canvas;", "canvas", "Lme/x;", "draw", "(Landroid/graphics/Canvas;)V", "", "alpha", "setAlpha", "(I)V", "Landroid/graphics/ColorFilter;", "colorFilter", "setColorFilter", "(Landroid/graphics/ColorFilter;)V", "getOpacity", "()I", "value", "F", "getValue", "()Ljava/lang/Float;", "setValue", "(F)V", "Landroid/graphics/Paint;", "paint", "Landroid/graphics/Paint;", "recyclerview_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SeslFastScrollerBgDrawable extends Drawable {
    private final Paint paint;
    private float value;

    public SeslFastScrollerBgDrawable() {
        Paint paint2 = new Paint(1);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAlpha(8);
        paint2.setColor(ColorUtils.setAlphaComponent(-16777216, ScoverState.TYPE_NFC_SMART_COVER));
        this.paint = paint2;
    }

    public void draw(Canvas canvas) {
        j.e(canvas, "canvas");
        this.paint.setStrokeWidth(getValue().floatValue());
        float f = (float) 2;
        canvas.drawLine(((float) canvas.getWidth()) / f, this.paint.getStrokeWidth() / f, ((float) canvas.getWidth()) / f, ((float) canvas.getHeight()) - (this.paint.getStrokeWidth() / f), this.paint);
    }

    public int getOpacity() {
        return -2;
    }

    public Float getValue() {
        return Float.valueOf(this.value);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }

    public void setAlpha(int i2) {
    }
}
