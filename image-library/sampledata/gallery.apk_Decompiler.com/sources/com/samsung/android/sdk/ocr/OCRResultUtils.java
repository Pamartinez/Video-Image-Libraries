package com.samsung.android.sdk.ocr;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OCRResultUtils {
    private static final String TAG = "OCRResultUtils";

    public static Rect cornerToRect(Point[] pointArr) {
        if (pointArr == null || pointArr.length <= 0) {
            return new Rect();
        }
        return new Rect(Math.min(pointArr[0].x, pointArr[3].x), Math.min(pointArr[0].y, pointArr[1].y), Math.max(pointArr[1].x, pointArr[2].x), Math.max(pointArr[2].y, pointArr[3].y));
    }

    public static Point[] createInitialRect() {
        return new Point[]{new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), new Point(Integer.MIN_VALUE, Integer.MAX_VALUE), new Point(Integer.MIN_VALUE, Integer.MIN_VALUE), new Point(Integer.MAX_VALUE, Integer.MIN_VALUE)};
    }

    public static Point getCenterPoint(Point[] pointArr) {
        ArrayList arrayList = new ArrayList(Arrays.asList(pointArr));
        return new Point((int) arrayList.stream().mapToDouble(new a(0)).average().getAsDouble(), (int) arrayList.stream().mapToDouble(new a(1)).average().getAsDouble());
    }

    public static Point getImageCenter(Bitmap bitmap) {
        return new Point(bitmap.getWidth() / 2, bitmap.getHeight() / 2);
    }

    public static Bitmap getROI(Bitmap bitmap, Point[] pointArr) {
        return getROI(bitmap, cornerToRect(pointArr));
    }

    public static Bitmap getRotatedROI(Bitmap bitmap, Point[] pointArr, float f, Point point) {
        Rect cornerToRect = cornerToRect(pointArr);
        PointF pointF = new PointF((float) cornerToRect.centerX(), (float) cornerToRect.centerY());
        Point[] rotatedCorner = OCRResult.getRotatedCorner(pointArr, -f, pointF);
        if (rotatedCorner == null) {
            Log.e(TAG, "Error on calculating deskewed points from rotated corners");
            return bitmap;
        }
        Rect cornerToRect2 = cornerToRect(rotatedCorner);
        Matrix matrix = new Matrix();
        matrix.setRotate(f, pointF.x, pointF.y);
        matrix.postTranslate((float) (-cornerToRect2.left), (float) (-cornerToRect2.top));
        Bitmap createBitmap = Bitmap.createBitmap(cornerToRect2.width(), cornerToRect2.height(), Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(bitmap, matrix, new Paint());
        point.x = cornerToRect2.left;
        point.y = cornerToRect2.top;
        return createBitmap;
    }

    public static void getTextRect(Point[] pointArr, Point[] pointArr2) {
        Log.w(TAG, "Depreacted API. Use unionRect() instead.");
        unionRect(pointArr, pointArr2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ double lambda$getCenterPoint$0(Point point) {
        return (double) point.x;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ double lambda$getCenterPoint$1(Point point) {
        return (double) point.y;
    }

    public static Point[] rectToCorner(Rect rect) {
        return new Point[]{new Point(rect.left, rect.top), new Point(rect.right, rect.top), new Point(rect.right, rect.bottom), new Point(rect.left, rect.bottom)};
    }

    public static void unionRect(Point[] pointArr, Point[] pointArr2) {
        Point point = pointArr[0];
        point.x = Math.min(point.x, pointArr2[0].x);
        Point point2 = pointArr[0];
        point2.y = Math.min(point2.y, pointArr2[0].y);
        Point point3 = pointArr[1];
        point3.x = Math.max(point3.x, pointArr2[1].x);
        Point point4 = pointArr[1];
        point4.y = Math.min(point4.y, pointArr2[1].y);
        Point point5 = pointArr[2];
        point5.x = Math.max(point5.x, pointArr2[2].x);
        Point point6 = pointArr[2];
        point6.y = Math.max(point6.y, pointArr2[2].y);
        Point point7 = pointArr[3];
        point7.x = Math.min(point7.x, pointArr2[3].x);
        Point point8 = pointArr[3];
        point8.y = Math.max(point8.y, pointArr2[3].y);
    }

    public static Bitmap getROI(Bitmap bitmap, Rect rect) {
        int i2;
        int i7;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (rect.width() + rect.left >= width) {
            i2 = width - rect.left;
        } else {
            i2 = rect.width();
        }
        if (rect.height() + rect.top >= height) {
            i7 = height - rect.top;
        } else {
            i7 = rect.height();
        }
        return Bitmap.createBitmap(bitmap, rect.left, rect.top, i2, i7);
    }
}
