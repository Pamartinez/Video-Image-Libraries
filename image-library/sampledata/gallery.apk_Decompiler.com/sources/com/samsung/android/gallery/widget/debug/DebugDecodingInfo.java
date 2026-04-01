package com.samsung.android.gallery.widget.debug;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DebugDecodingInfo {
    private final float DENSITY;
    private final int LINE_HEIGHT = px(15);
    private Paint mDebugLinePaint;
    private Paint mDebugTextPaint;
    private int mLine;

    public DebugDecodingInfo(Context context) {
        this.DENSITY = context.getResources().getDisplayMetrics().density;
    }

    private int getNextLine() {
        int i2 = this.LINE_HEIGHT;
        int i7 = this.mLine;
        this.mLine = i7 + 1;
        return i2 * i7;
    }

    private int px(int i2) {
        return (int) (this.DENSITY * ((float) i2));
    }

    public void createPaints() {
        if (this.mDebugTextPaint == null || this.mDebugLinePaint == null) {
            Paint paint = new Paint();
            this.mDebugTextPaint = paint;
            paint.setTextSize((float) px(12));
            this.mDebugTextPaint.setColor(-65281);
            this.mDebugTextPaint.setStyle(Paint.Style.FILL);
            Paint paint2 = new Paint();
            this.mDebugLinePaint = paint2;
            paint2.setColor(-65281);
            this.mDebugLinePaint.setStyle(Paint.Style.STROKE);
            this.mDebugLinePaint.setStrokeWidth((float) px(2));
        }
    }

    public void drawAnimCenterCircle(Canvas canvas, PointF pointF, PointF pointF2, PointF pointF3, float f, float f5) {
        if (pointF != null && pointF2 != null && pointF3 != null) {
            canvas.drawCircle(pointF.x, pointF.y, (float) px(10), this.mDebugLinePaint);
            this.mDebugLinePaint.setColor(-65536);
            canvas.drawCircle(pointF2.x, pointF2.y, (float) px(20), this.mDebugLinePaint);
            this.mDebugLinePaint.setColor(-16776961);
            canvas.drawCircle(pointF3.x, pointF3.y, (float) px(25), this.mDebugLinePaint);
            this.mDebugLinePaint.setColor(-16711681);
            canvas.drawCircle(f / 2.0f, f5 / 2.0f, (float) px(30), this.mDebugLinePaint);
        }
    }

    public void drawDebug(Canvas canvas, String str) {
        if (str != null) {
            for (String str2 : str.split(System.lineSeparator())) {
                if (str2 != null) {
                    canvas.drawText(str2, (float) px(5), (float) getNextLine(), this.mDebugTextPaint);
                }
            }
        }
    }

    public void drawDebugLine(Canvas canvas, Rect rect) {
        canvas.drawRect(rect, this.mDebugLinePaint);
    }

    public void drawDebugText(Canvas canvas, float f, float f5, float f8, PointF pointF, PointF pointF2, int i2, String str) {
        PointF pointF3 = pointF;
        PointF pointF4 = pointF2;
        canvas.drawText(String.format(Locale.ENGLISH, C0212a.A(str, " zoom[%.2f~%.2f]=%.2f R=%d Cen[%.2f,%.2f], P[%.2f,%.2f]"), new Object[]{Float.valueOf(f5), Float.valueOf(f8), Float.valueOf(f), Integer.valueOf(i2), Float.valueOf(pointF4.x), Float.valueOf(pointF4.y), Float.valueOf(pointF3.x), Float.valueOf(pointF3.y)}), (float) px(5), (float) getNextLine(), this.mDebugTextPaint);
    }

    public void drawISS(Canvas canvas, int i2, Rect rect, Rect rect2, Bitmap bitmap) {
        canvas.drawText(i2 + NumericEnum.SEP + rect.left + GlobalPostProcInternalPPInterface.SPLIT_REGEX + rect.top + " " + rect.width() + "x" + rect.height(), (float) (rect2.left + px(20)), (float) (rect2.top + px(30)), this.mDebugTextPaint);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(rect2.left);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(rect2.top);
        sb2.append(" ");
        sb2.append(rect2.width());
        sb2.append("x");
        sb2.append(rect2.height());
        canvas.drawText(sb2.toString(), (float) (rect2.left + px(20)), (float) (rect2.top + px(45)), this.mDebugTextPaint);
        if (bitmap != null) {
            canvas.drawText(bitmap.getHeight() + "x" + bitmap.getWidth(), (float) (rect2.left + px(20)), (float) (rect2.top + px(60)), this.mDebugTextPaint);
            StringBuilder sb3 = new StringBuilder("bs = ");
            sb3.append(((float) rect2.width()) / ((float) bitmap.getHeight()));
            canvas.drawText(sb3.toString(), (float) (rect2.left + px(20)), (float) (rect2.top + px(75)), this.mDebugTextPaint);
        }
    }

    public void drawLoading(Canvas canvas, float f, float f5) {
        canvas.drawText("LOADING", f + ((float) px(5)), f5 + ((float) px(35)), this.mDebugTextPaint);
    }

    public void drawVCenterCircle(Canvas canvas, PointF pointF) {
        this.mDebugLinePaint.setColor(-65536);
        canvas.drawCircle(pointF.x, pointF.y, (float) px(20), this.mDebugLinePaint);
    }

    public void init() {
        this.mLine = 2;
    }

    public void resetDebugLinePaint() {
        this.mDebugLinePaint.setColor(-65281);
    }

    public void drawDebugQuickCropText(Canvas canvas, Rect rect, Rect rect2, int i2, int i7, int i8) {
    }
}
