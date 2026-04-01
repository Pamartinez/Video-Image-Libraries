package com.samsung.android.gallery.module.story.transcode.decoder.video;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.story.FontTypefaceUtils;
import com.samsung.android.gallery.support.utils.AppResources;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TitleImage {
    private Align mAlign = Align.BOTTOM_MIDDLE;
    private boolean mIsLandscape;
    private int mOutHeight;
    private int mOutWidth;
    private final int mSubTitleFontColor = AppResources.getColor(R$color.stories_pinch_item_sub_title_color);
    private Typeface mTitleFont = FontTypefaceUtils.TextFont.ROBOTO_BOLD.getTypeface();
    private final int mTitleFontColor = AppResources.getColor(R$color.stories_pinch_item_title_color);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Align {
        TOP_START,
        TOP_MIDDLE,
        TOP_END,
        BOTTOM_START,
        BOTTOM_MIDDLE,
        BOTTOM_END;

        /* access modifiers changed from: private */
        public static Align get(int i2) {
            if (i2 < 0 || i2 >= values().length) {
                return BOTTOM_MIDDLE;
            }
            return values()[i2];
        }

        /* access modifiers changed from: private */
        public static int getSubTitleYPosition(int i2) {
            return i2 + 33;
        }

        /* access modifiers changed from: private */
        public static Layout.Alignment getTextAlignment(Align align) {
            if (align == TOP_START || align == BOTTOM_START) {
                return Layout.Alignment.ALIGN_NORMAL;
            }
            if (align == TOP_END || align == BOTTOM_END) {
                return Layout.Alignment.ALIGN_OPPOSITE;
            }
            return Layout.Alignment.ALIGN_CENTER;
        }

        /* access modifiers changed from: private */
        public static float getTitleYPositionRatio(Align align, boolean z, boolean z3) {
            if (align.ordinal() <= TOP_END.ordinal()) {
                if (z) {
                    return 0.187f;
                }
                if (z3) {
                    return 0.1693f;
                }
                return 0.1797f;
            } else if (z) {
                return 0.5426f;
            } else {
                if (z3) {
                    return 0.6667f;
                }
                return 0.7135f;
            }
        }

        /* access modifiers changed from: private */
        public static float getXPositionRatio(boolean z) {
            if (z) {
                return 0.085f;
            }
            return 0.095f;
        }
    }

    private Canvas createCanvas(Bitmap bitmap) {
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(64);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0);
        canvas.drawPaint(paint);
        return canvas;
    }

    private StaticLayout createTitleStaticLayout(String str, int i2) {
        int i7;
        StaticLayout.Builder includePad = StaticLayout.Builder.obtain(str, 0, str.length(), getTitlePaint(i2), getWidth()).setAlignment(Align.getTextAlignment(this.mAlign)).setIncludePad(false);
        if (this.mIsLandscape) {
            i7 = 1;
        } else {
            i7 = 2;
        }
        return includePad.setMaxLines(i7).setEllipsize(TextUtils.TruncateAt.END).build();
    }

    private void drawSubTitle(Canvas canvas, String str, int i2) {
        canvas.save();
        StaticLayout subTitleStaticLayout = getSubTitleStaticLayout(str);
        canvas.translate(((float) this.mOutWidth) * Align.getXPositionRatio(this.mIsLandscape), (float) Align.getSubTitleYPosition(i2));
        subTitleStaticLayout.draw(canvas);
        canvas.restore();
    }

    private int drawTitle(Canvas canvas, String str) {
        canvas.save();
        StaticLayout staticLayout = getStaticLayout(str);
        float f = (float) this.mOutHeight;
        Align align = this.mAlign;
        boolean z = this.mIsLandscape;
        boolean z3 = true;
        if (staticLayout.getLineCount() == 1) {
            z3 = false;
        }
        int d = (int) (f * Align.getTitleYPositionRatio(align, z, z3));
        canvas.translate(((float) this.mOutWidth) * Align.getXPositionRatio(this.mIsLandscape), (float) d);
        staticLayout.draw(canvas);
        canvas.restore();
        return staticLayout.getHeight() + d;
    }

    private StaticLayout getStaticLayout(String str) {
        StaticLayout createTitleStaticLayout;
        int i2 = 80;
        do {
            createTitleStaticLayout = createTitleStaticLayout(str, i2);
            i2 -= 2;
            if (i2 <= 37 || createTitleStaticLayout.getEllipsisCount(createTitleStaticLayout.getLineCount() - 1) <= 0) {
                return createTitleStaticLayout;
            }
            createTitleStaticLayout = createTitleStaticLayout(str, i2);
            i2 -= 2;
            break;
        } while (createTitleStaticLayout.getEllipsisCount(createTitleStaticLayout.getLineCount() - 1) <= 0);
        return createTitleStaticLayout;
    }

    private TextPaint getSubTitlePaint() {
        TextPaint textPaint = new TextPaint(64);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(37.0f);
        textPaint.setColor(this.mSubTitleFontColor);
        textPaint.setTypeface(FontTypefaceUtils.TextFont.ROBOTO_REGULAR.getTypeface());
        return textPaint;
    }

    private StaticLayout getSubTitleStaticLayout(String str) {
        return StaticLayout.Builder.obtain(str, 0, str.length(), getSubTitlePaint(), getWidth()).setAlignment(Align.getTextAlignment(this.mAlign)).setIncludePad(false).setMaxLines(1).setEllipsize(TextUtils.TruncateAt.END).build();
    }

    private TextPaint getTitlePaint(int i2) {
        TextPaint textPaint = new TextPaint(64);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize((float) i2);
        textPaint.setColor(this.mTitleFontColor);
        textPaint.setTypeface(this.mTitleFont);
        return textPaint;
    }

    private int getWidth() {
        float f;
        float f5 = (float) this.mOutWidth;
        if (this.mIsLandscape) {
            f = 0.83f;
        } else {
            f = 0.81f;
        }
        return (int) (f5 * f);
    }

    public Bitmap createTitleImage(String str, String str2, int i2, int i7, int i8, Typeface typeface) {
        boolean z;
        this.mOutWidth = i2;
        this.mOutHeight = i7;
        this.mAlign = Align.get(i8);
        if (i7 < i2) {
            z = true;
        } else {
            z = false;
        }
        this.mIsLandscape = z;
        this.mTitleFont = typeface;
        Bitmap createBitmap = Bitmap.createBitmap(i2, i7, Bitmap.Config.ARGB_4444);
        Canvas createCanvas = createCanvas(createBitmap);
        drawSubTitle(createCanvas, str2, drawTitle(createCanvas, str));
        return createBitmap;
    }
}
