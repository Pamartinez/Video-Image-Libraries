package androidx.appcompat.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$dimen;
import androidx.core.graphics.Insets;
import androidx.core.graphics.PathParser;
import c0.C0086a;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslRoundedCorner {
    protected final SeslRoundedChunkingDrawable mBottomLeftRound;
    private int mBottomLeftRoundColor;
    protected final SeslRoundedChunkingDrawable mBottomRightRound;
    private int mBottomRightRoundColor;
    private Insets mInsets;
    final int mRoundRadius;
    final Rect mRoundedCornerBounds;
    int mRoundedCornerMode;
    protected final SeslRoundedChunkingDrawable mTopLeftRound;
    private int mTopLeftRoundColor;
    protected final SeslRoundedChunkingDrawable mTopRightRound;
    private int mTopRightRoundColor;

    public SeslRoundedCorner(Context context) {
        this(context, false);
    }

    private void drawRoundedCornerInternal(Canvas canvas) {
        int i2;
        int i7;
        int i8;
        Rect rect = this.mRoundedCornerBounds;
        int i10 = rect.left;
        Insets insets = this.mInsets;
        int i11 = 0;
        if (insets != null) {
            i2 = insets.left;
        } else {
            i2 = 0;
        }
        int i12 = i10 + i2;
        int i13 = rect.right;
        if (insets != null) {
            i7 = insets.right;
        } else {
            i7 = 0;
        }
        int i14 = i13 - i7;
        int i15 = rect.top;
        if (insets != null) {
            i8 = insets.top;
        } else {
            i8 = 0;
        }
        int i16 = i15 + i8;
        int i17 = rect.bottom;
        if (insets != null) {
            i11 = insets.bottom;
        }
        int i18 = i17 - i11;
        if ((this.mRoundedCornerMode & 1) != 0) {
            SeslRoundedChunkingDrawable seslRoundedChunkingDrawable = this.mTopLeftRound;
            int i19 = this.mRoundRadius;
            seslRoundedChunkingDrawable.setBounds(i12, i16, i12 + i19, i19 + i16);
            this.mTopLeftRound.draw(canvas);
        }
        if ((this.mRoundedCornerMode & 2) != 0) {
            SeslRoundedChunkingDrawable seslRoundedChunkingDrawable2 = this.mTopRightRound;
            int i20 = this.mRoundRadius;
            seslRoundedChunkingDrawable2.setBounds(i14 - i20, i16, i14, i20 + i16);
            this.mTopRightRound.draw(canvas);
        }
        if ((this.mRoundedCornerMode & 4) != 0) {
            SeslRoundedChunkingDrawable seslRoundedChunkingDrawable3 = this.mBottomLeftRound;
            int i21 = this.mRoundRadius;
            seslRoundedChunkingDrawable3.setBounds(i12, i18 - i21, i21 + i12, i18);
            this.mBottomLeftRound.draw(canvas);
        }
        if ((this.mRoundedCornerMode & 8) != 0) {
            SeslRoundedChunkingDrawable seslRoundedChunkingDrawable4 = this.mBottomRightRound;
            int i22 = this.mRoundRadius;
            seslRoundedChunkingDrawable4.setBounds(i14 - i22, i18 - i22, i14, i18);
            this.mBottomRightRound.draw(canvas);
        }
        int i23 = this.mTopLeftRoundColor;
        if (i23 == this.mTopRightRoundColor && i23 == this.mBottomLeftRoundColor && i23 == this.mBottomRightRoundColor) {
            Paint paint = new Paint();
            paint.setColor(i23);
            Insets insets2 = this.mInsets;
            if (insets2 != null && insets2.top > 0) {
                Insets insets3 = this.mInsets;
                canvas.drawRect(new Rect(i12 - insets3.left, i16 - insets3.top, insets3.right + i14, i16), paint);
            }
            Insets insets4 = this.mInsets;
            if (insets4 != null && insets4.bottom > 0) {
                Insets insets5 = this.mInsets;
                canvas.drawRect(new Rect(i12 - insets5.left, i18, insets5.right + i14, insets5.bottom + i18), paint);
            }
            Insets insets6 = this.mInsets;
            if (insets6 != null && insets6.left > 0) {
                Insets insets7 = this.mInsets;
                canvas.drawRect(new Rect(i12 - insets7.left, i16 - insets7.top, i12, insets7.bottom + i18), paint);
            }
            Insets insets8 = this.mInsets;
            if (insets8 != null && insets8.right > 0) {
                Insets insets9 = this.mInsets;
                canvas.drawRect(new Rect(i14, i16 - insets9.top, insets9.right + i14, i18 + insets9.bottom), paint);
            }
        }
    }

    private boolean isColorType(int i2) {
        if (i2 < 28 || i2 > 31) {
            return false;
        }
        return true;
    }

    public void drawRoundedCorner(Canvas canvas) {
        canvas.getClipBounds(this.mRoundedCornerBounds);
        drawRoundedCornerInternal(canvas);
    }

    public void setRoundedCornerColor(int i2, int i7) {
        if (i2 == 0) {
            throw new IllegalArgumentException("There is no rounded corner on = " + this);
        } else if ((i2 & -16) == 0) {
            PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(i7, PorterDuff.Mode.SRC_IN);
            if ((i2 & 1) != 0) {
                this.mTopLeftRoundColor = i7;
                this.mTopLeftRound.setColorFilter(porterDuffColorFilter);
            }
            if ((i2 & 2) != 0) {
                this.mTopRightRoundColor = i7;
                this.mTopRightRound.setColorFilter(porterDuffColorFilter);
            }
            if ((i2 & 4) != 0) {
                this.mBottomLeftRoundColor = i7;
                this.mBottomLeftRound.setColorFilter(porterDuffColorFilter);
            }
            if ((i2 & 8) != 0) {
                this.mBottomRightRoundColor = i7;
                this.mBottomRightRound.setColorFilter(porterDuffColorFilter);
            }
        } else {
            throw new IllegalArgumentException(C0086a.i(i2, "Use wrong rounded corners to the param, corners = "));
        }
    }

    public void setRoundedCorners(int i2) {
        if ((i2 & -16) == 0) {
            this.mRoundedCornerMode = i2;
            return;
        }
        throw new IllegalArgumentException(C0086a.i(i2, "Use wrong rounded corners to the param, corners = "));
    }

    public SeslRoundedCorner(Context context, boolean z) {
        int i2;
        this.mRoundedCornerBounds = new Rect();
        this.mInsets = null;
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.sesl_rounded_corner_radius);
        this.mRoundRadius = dimensionPixelSize;
        boolean isLightTheme = SeslMisc.isLightTheme(context);
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.roundedCornerColor, typedValue, true);
        if (typedValue.resourceId > 0 && isColorType(typedValue.type)) {
            i2 = resources.getColor(typedValue.resourceId);
        } else if (typedValue.data <= 0 || !isColorType(typedValue.type)) {
            i2 = resources.getColor(!isLightTheme ? R$color.sesl_round_and_bgcolor_dark : R$color.sesl_round_and_bgcolor_light);
        } else {
            i2 = typedValue.data;
        }
        this.mBottomRightRoundColor = i2;
        this.mBottomLeftRoundColor = i2;
        this.mTopRightRoundColor = i2;
        this.mTopLeftRoundColor = i2;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(-1);
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(i2, PorterDuff.Mode.SRC_IN);
        SeslRoundedChunkingDrawable seslRoundedChunkingDrawable = new SeslRoundedChunkingDrawable(dimensionPixelSize, paint, 0.0f);
        this.mTopLeftRound = seslRoundedChunkingDrawable;
        seslRoundedChunkingDrawable.setColorFilter(porterDuffColorFilter);
        SeslRoundedChunkingDrawable seslRoundedChunkingDrawable2 = new SeslRoundedChunkingDrawable(dimensionPixelSize, paint, 90.0f);
        this.mTopRightRound = seslRoundedChunkingDrawable2;
        seslRoundedChunkingDrawable2.setColorFilter(porterDuffColorFilter);
        SeslRoundedChunkingDrawable seslRoundedChunkingDrawable3 = new SeslRoundedChunkingDrawable(dimensionPixelSize, paint, 270.0f);
        this.mBottomLeftRound = seslRoundedChunkingDrawable3;
        seslRoundedChunkingDrawable3.setColorFilter(porterDuffColorFilter);
        SeslRoundedChunkingDrawable seslRoundedChunkingDrawable4 = new SeslRoundedChunkingDrawable(dimensionPixelSize, paint, 180.0f);
        this.mBottomRightRound = seslRoundedChunkingDrawable4;
        seslRoundedChunkingDrawable4.setColorFilter(porterDuffColorFilter);
    }

    public void drawRoundedCorner(View view, Canvas canvas) {
        int i2;
        int i7;
        if (view.getTranslationY() != 0.0f) {
            i7 = Math.round(view.getX());
            i2 = Math.round(view.getY());
            canvas.translate((view.getX() - ((float) i7)) + 0.5f, (view.getY() - ((float) i2)) + 0.5f);
        } else {
            i7 = view.getLeft();
            i2 = view.getTop();
        }
        this.mRoundedCornerBounds.set(i7, i2, view.getWidth() + i7, view.getHeight() + i2);
        drawRoundedCornerInternal(canvas);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SeslRoundedChunkingDrawable extends Drawable {
        private final float mAngle;
        private ColorFilter mColorFilter;
        private final Paint mPaint;
        private final Path mPath = new Path();
        private PathParser.PathDataNode[] mPathDataNodes = null;
        private final int mRoundRadius;

        public SeslRoundedChunkingDrawable(int i2, Paint paint, float f) {
            this.mRoundRadius = i2;
            this.mPaint = paint;
            this.mAngle = f;
        }

        private Path getSmoothCornerRectPath(float f, int i2, int i7) {
            if (i2 <= 0 || i7 <= 0) {
                return new Path();
            }
            float f5 = (float) i2;
            float f8 = (float) i7;
            float min = Math.min(f5 / 2.0f, f8 / 2.0f);
            float min2 = Math.min(Math.max(f, 0.0f), min);
            float f10 = min2 / min;
            float f11 = 1.0f;
            float min3 = f10 > 0.5f ? 1.0f - (Math.min(1.0f, (f10 - 0.5f) / 0.4f) * 0.13877845f) : 1.0f;
            if (((double) f10) > 0.6d) {
                f11 = 1.0f + (Math.min(1.0f, (f10 - 0.6f) / 0.3f) * 0.042454004f);
            }
            return getSmoothCornerRectPath(min2, f5, f8, min3, f11);
        }

        private Path getTopLeftSmoothCornerPath(float f, float f5, float f8, float f10, float f11) {
            SeslRoundedChunkingDrawable seslRoundedChunkingDrawable;
            Float valueOf = Float.valueOf(34.86f);
            Float valueOf2 = Float.valueOf(22.07f);
            Float valueOf3 = Float.valueOf(51.16f);
            Float valueOf4 = Float.valueOf(13.36f);
            Float valueOf5 = Float.valueOf(67.45f);
            Float valueOf6 = Float.valueOf(4.64f);
            Float valueOf7 = Float.valueOf(0.0f);
            if (this.mPathDataNodes == null) {
                float f12 = ((f5 / 2.0f) / f) * 100.0f;
                Locale locale = Locale.ENGLISH;
                float f13 = f10 * 128.19f;
                String format = String.format(locale, "L %f %f ", new Object[]{valueOf7, Float.valueOf(Math.min(((f8 / 2.0f) / f) * 100.0f, f13))});
                float f14 = f11 * 83.62f;
                Float f15 = valueOf6;
                Float f16 = valueOf3;
                Float f17 = f15;
                Float f18 = valueOf5;
                Float f19 = valueOf4;
                Float f20 = f18;
                Float f21 = valueOf7;
                Object[] objArr = {f21, Float.valueOf(f14), f17, f20, f19, f16};
                Float f22 = f16;
                Float f23 = f17;
                Float f24 = f22;
                Float f25 = f19;
                Float f26 = f20;
                Float f27 = f25;
                Float f28 = f21;
                String format2 = String.format(locale, "C %f %f %f %f %f %f ", objArr);
                String str = "C %f %f %f %f %f %f ";
                String format3 = String.format(locale, str, new Object[]{valueOf2, valueOf, valueOf, valueOf2, f24, f27});
                Float valueOf8 = Float.valueOf(f14);
                String str2 = format;
                String format4 = String.format(locale, str, new Object[]{f26, f23, valueOf8, f28, Float.valueOf(Math.min(f12, f13)), f28});
                String format5 = String.format(locale, "L %f %f ", new Object[]{Float.valueOf(Math.min(f12, f13)), f28});
                StringBuilder q = C0086a.q("M 0 0 ", str2, format2, format3, format4);
                q.append(format5);
                q.append("Z");
                seslRoundedChunkingDrawable = this;
                seslRoundedChunkingDrawable.mPathDataNodes = PathParser.createNodesFromPathData(q.toString());
            } else {
                seslRoundedChunkingDrawable = this;
            }
            seslRoundedChunkingDrawable.mPath.reset();
            PathParser.PathDataNode.nodesToPath(seslRoundedChunkingDrawable.mPathDataNodes, seslRoundedChunkingDrawable.mPath);
            return seslRoundedChunkingDrawable.mPath;
        }

        public void draw(Canvas canvas) {
            this.mPaint.setColorFilter(this.mColorFilter);
            canvas.drawPath(getSmoothCornerRectPath((float) this.mRoundRadius, canvas.getWidth(), canvas.getHeight()), this.mPaint);
        }

        public ColorFilter getColorFilter() {
            return this.mColorFilter;
        }

        public int getOpacity() {
            return -1;
        }

        public void setAlpha(int i2) {
            this.mPaint.setAlpha(i2);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.mColorFilter = colorFilter;
        }

        public Path getSmoothCornerRectPath(float f, float f5, float f8, float f10, float f11) {
            Path topLeftSmoothCornerPath = getTopLeftSmoothCornerPath(f, f5, f8, f10, f11);
            Matrix matrix = new Matrix();
            float f12 = f / 100.0f;
            matrix.setScale(f12, f12);
            topLeftSmoothCornerPath.transform(matrix);
            Rect bounds = getBounds();
            Matrix matrix2 = new Matrix();
            matrix2.setRotate(this.mAngle, ((float) bounds.width()) / 2.0f, ((float) bounds.height()) / 2.0f);
            topLeftSmoothCornerPath.transform(matrix2);
            Matrix matrix3 = new Matrix();
            matrix3.setTranslate((float) bounds.left, (float) bounds.top);
            topLeftSmoothCornerPath.transform(matrix3);
            return topLeftSmoothCornerPath;
        }
    }

    public void drawRoundedCorner(Canvas canvas, Insets insets) {
        this.mInsets = insets;
        drawRoundedCorner(canvas);
    }
}
