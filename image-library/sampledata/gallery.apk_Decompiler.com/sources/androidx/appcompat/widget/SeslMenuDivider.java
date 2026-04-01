package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;
import androidx.appcompat.R$color;
import androidx.appcompat.util.SeslMisc;
import androidx.core.content.res.ResourcesCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslMenuDivider extends ImageView {
    private final int mDiameter;
    private final int mInterval;
    private Paint mPaint;

    public SeslMenuDivider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void onDraw(Canvas canvas) {
        int i2;
        int i7;
        super.onDraw(canvas);
        int width = (getWidth() - getPaddingStart()) - getPaddingEnd();
        int height = getHeight();
        int i8 = this.mDiameter;
        int i10 = (width - i8) / (this.mInterval + i8);
        int i11 = i10 + 1;
        int paddingStart = getPaddingStart() + ((int) ((((float) i8) / 2.0f) + 0.5f));
        int i12 = this.mDiameter;
        int i13 = (width - i12) - ((this.mInterval + i12) * i10);
        if (i12 % 2 != 0) {
            i13--;
        }
        if (i10 > 0) {
            i2 = i13 / i10;
            i7 = i13 % i10;
        } else {
            i7 = 0;
            i2 = 0;
        }
        int i14 = 0;
        for (int i15 = 0; i15 < i11; i15++) {
            canvas.drawCircle((float) (paddingStart + i14), ((float) height) / 2.0f, ((float) this.mDiameter) / 2.0f, this.mPaint);
            int i16 = this.mDiameter + this.mInterval + i2 + i14;
            if (i15 < i7) {
                i16++;
            }
            i14 = i16;
        }
    }

    public SeslMenuDivider(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        int i7;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.mDiameter = (int) TypedValue.applyDimension(1, 1.5f, displayMetrics);
        this.mInterval = (int) TypedValue.applyDimension(1, 3.0f, displayMetrics);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setFlags(1);
        Resources resources = context.getResources();
        if (SeslMisc.isLightTheme(context)) {
            i7 = R$color.sesl_popup_menu_divider_color_light;
        } else {
            i7 = R$color.sesl_popup_menu_divider_color_dark;
        }
        this.mPaint.setColor(ResourcesCompat.getColor(resources, i7, (Resources.Theme) null));
    }
}
