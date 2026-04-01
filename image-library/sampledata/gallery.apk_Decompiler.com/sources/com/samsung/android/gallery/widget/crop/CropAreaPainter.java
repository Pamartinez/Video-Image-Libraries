package com.samsung.android.gallery.widget.crop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.crop.config.CropViewConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CropAreaPainter {
    private Paint mBackgroundPaint;
    private float mBorderCornerLength;
    private float mBorderCornerThickness;
    private Paint mBorderPaint;
    private Drawable mBottomLeftArrow;
    private Drawable mBottomRightArrow;
    private RectF mRect;
    private CropViewConfig.ShapeType mShapeType;
    private Drawable mTopLeftArrow;
    private Drawable mTopRightArrow;

    /* renamed from: com.samsung.android.gallery.widget.crop.CropAreaPainter$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$widget$crop$config$CropViewConfig$ShapeType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.gallery.widget.crop.config.CropViewConfig$ShapeType[] r0 = com.samsung.android.gallery.widget.crop.config.CropViewConfig.ShapeType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$widget$crop$config$CropViewConfig$ShapeType = r0
                com.samsung.android.gallery.widget.crop.config.CropViewConfig$ShapeType r1 = com.samsung.android.gallery.widget.crop.config.CropViewConfig.ShapeType.OVAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$crop$config$CropViewConfig$ShapeType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.widget.crop.config.CropViewConfig$ShapeType r1 = com.samsung.android.gallery.widget.crop.config.CropViewConfig.ShapeType.RECTANGLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$crop$config$CropViewConfig$ShapeType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.widget.crop.config.CropViewConfig$ShapeType r1 = com.samsung.android.gallery.widget.crop.config.CropViewConfig.ShapeType.RECT_1X1     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$crop$config$CropViewConfig$ShapeType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.widget.crop.config.CropViewConfig$ShapeType r1 = com.samsung.android.gallery.widget.crop.config.CropViewConfig.ShapeType.RECT_2X1     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$crop$config$CropViewConfig$ShapeType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.gallery.widget.crop.config.CropViewConfig$ShapeType r1 = com.samsung.android.gallery.widget.crop.config.CropViewConfig.ShapeType.RECT_2X2     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$crop$config$CropViewConfig$ShapeType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.samsung.android.gallery.widget.crop.config.CropViewConfig$ShapeType r1 = com.samsung.android.gallery.widget.crop.config.CropViewConfig.ShapeType.RECT_4X1     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$crop$config$CropViewConfig$ShapeType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.samsung.android.gallery.widget.crop.config.CropViewConfig$ShapeType r1 = com.samsung.android.gallery.widget.crop.config.CropViewConfig.ShapeType.RECT_4X2     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.crop.CropAreaPainter.AnonymousClass1.<clinit>():void");
        }
    }

    private void drawBorders(Canvas canvas) {
        Paint paint = this.mBorderPaint;
        if (paint != null) {
            float strokeWidth = paint.getStrokeWidth() / 2.0f;
            RectF rectF = this.mRect;
            Canvas canvas2 = canvas;
            canvas2.drawRect(rectF.left + strokeWidth, rectF.top + strokeWidth, rectF.right - strokeWidth, rectF.bottom - strokeWidth, this.mBorderPaint);
            canvas2.drawRect(this.mRect, this.mBorderPaint);
            if (this.mShapeType == CropViewConfig.ShapeType.OVAL) {
                canvas2.drawOval(this.mRect, this.mBorderPaint);
            }
        }
    }

    private void drawCorner(Canvas canvas) {
        this.mTopLeftArrow.setBounds(Math.round(this.mRect.left - this.mBorderCornerThickness), Math.round(this.mRect.top - this.mBorderCornerThickness), Math.round(this.mRect.left + this.mBorderCornerLength), Math.round(this.mRect.top + this.mBorderCornerLength));
        this.mTopRightArrow.setBounds(Math.round(this.mRect.right - this.mBorderCornerLength), Math.round(this.mRect.top - this.mBorderCornerThickness), Math.round(this.mRect.right + this.mBorderCornerThickness), Math.round(this.mRect.top + this.mBorderCornerLength));
        this.mBottomLeftArrow.setBounds(Math.round(this.mRect.left - this.mBorderCornerThickness), Math.round(this.mRect.bottom - this.mBorderCornerLength), Math.round(this.mRect.left + this.mBorderCornerLength), Math.round(this.mRect.bottom + this.mBorderCornerThickness));
        this.mBottomRightArrow.setBounds(Math.round(this.mRect.right - this.mBorderCornerLength), Math.round(this.mRect.bottom - this.mBorderCornerLength), Math.round(this.mRect.right + this.mBorderCornerThickness), Math.round(this.mRect.bottom + this.mBorderCornerThickness));
        this.mTopLeftArrow.draw(canvas);
        this.mTopRightArrow.draw(canvas);
        this.mBottomLeftArrow.draw(canvas);
        this.mBottomRightArrow.draw(canvas);
    }

    private void drawCropShape(Canvas canvas) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$widget$crop$config$CropViewConfig$ShapeType[this.mShapeType.ordinal()];
        if (i2 == 1) {
            canvas.drawOval(this.mRect, this.mBackgroundPaint);
        } else if (i2 != 2) {
            drawInnerShape(canvas);
        } else {
            canvas.drawRect(this.mRect, this.mBackgroundPaint);
        }
    }

    private void drawInnerShape(Canvas canvas) {
        Path path = new Path();
        float height = this.mRect.height() / 4.0f;
        float width = this.mRect.width() / 8.0f;
        float centerX = this.mRect.centerX();
        float centerY = this.mRect.centerY();
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$widget$crop$config$CropViewConfig$ShapeType[this.mShapeType.ordinal()];
        if (i2 == 3) {
            path.addRoundRect(new RectF(centerX - width, centerY - height, centerX + width, centerY + height), height, height, Path.Direction.CW);
        } else if (i2 == 4) {
            float height2 = this.mRect.height() / 6.0f;
            float f = width * 2.0f;
            path.addRoundRect(new RectF(centerX - f, centerY - height, centerX + f, centerY + height), height2, height2, Path.Direction.CCW);
        } else if (i2 == 5) {
            float height3 = (this.mRect.height() * 24.0f) / 154.0f;
            float f5 = width * 2.0f;
            float f8 = height * 2.0f;
            path.addRoundRect(new RectF(centerX - f5, centerY - f8, centerX + f5, centerY + f8), height3, height3, Path.Direction.CW);
        } else if (i2 == 6) {
            float height4 = (this.mRect.height() * 24.0f) / 154.0f;
            float f10 = width * 4.0f;
            path.addRoundRect(new RectF(centerX - f10, centerY - height, centerX + f10, centerY + height), height4, height4, Path.Direction.CCW);
        } else if (i2 == 7) {
            float height5 = (this.mRect.height() * 24.0f) / 154.0f;
            path.addRoundRect(this.mRect, height5, height5, Path.Direction.CW);
        }
        canvas.drawPath(path, this.mBackgroundPaint);
        canvas.drawPath(path, this.mBorderPaint);
    }

    private Paint getBackgroundPaint() {
        Paint paint = new Paint();
        paint.setColor(0);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        return paint;
    }

    private Paint getBorderPaint(float f, int i2) {
        Paint paint = new Paint();
        paint.setColor(i2);
        paint.setStrokeWidth(f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        return paint;
    }

    public void draw(Canvas canvas) {
        drawCropShape(canvas);
        drawBorders(canvas);
        drawCorner(canvas);
    }

    public void onViewCreated(Context context, RectF rectF, CropViewConfig.ShapeType shapeType) {
        this.mRect = rectF;
        this.mShapeType = shapeType;
        this.mBorderCornerThickness = context.getResources().getDimension(R$dimen.crop_view_corner);
        this.mBorderCornerLength = context.getResources().getDimension(R$dimen.crop_view_corner_length);
        this.mBorderPaint = getBorderPaint(context.getResources().getDimension(R$dimen.crop_view_border), context.getColor(R$color.crop_view_border_color));
        this.mBackgroundPaint = getBackgroundPaint();
        this.mTopLeftArrow = context.getDrawable(R$drawable.crop_handler_01);
        this.mTopRightArrow = context.getDrawable(R$drawable.crop_handler_02);
        this.mBottomLeftArrow = context.getDrawable(R$drawable.crop_handler_04);
        this.mBottomRightArrow = context.getDrawable(R$drawable.crop_handler_03);
    }
}
