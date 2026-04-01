package com.samsung.android.sdk.ocr;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecognizerAPIChecker {
    private static final String TAG = "RecognizerAPIChecker";

    private static boolean isInputBitmapNull(Bitmap bitmap) {
        if (bitmap != null) {
            return false;
        }
        Log.e(TAG, "Input bitmap is null");
        return true;
    }

    private static boolean isPointContainerNull(Point[] pointArr) {
        if (pointArr == null) {
            Log.e(TAG, "Result space for 4 corner points is null");
            return true;
        } else if (pointArr.length == 4) {
            return false;
        } else {
            Log.e(TAG, "Result space for 4 corner points has not been prepared");
            return true;
        }
    }

    private static boolean isResultContainerNull(OCRResult oCRResult) {
        if (oCRResult != null) {
            return false;
        }
        Log.e(TAG, "Input parameter for result container is null");
        return true;
    }

    public static boolean isValidParameter(Bitmap bitmap) {
        return !isInputBitmapNull(bitmap);
    }

    private static void preparePointContainer(Point[] pointArr) {
        for (int i2 = 0; i2 < pointArr.length; i2++) {
            if (pointArr[i2] == null) {
                pointArr[i2] = new Point();
            }
        }
    }

    public static boolean isValidParameter(Bitmap bitmap, OCRResult oCRResult) {
        if (!isInputBitmapNull(bitmap) && !isResultContainerNull(oCRResult)) {
            return true;
        }
        return false;
    }

    public static boolean isValidParameter(Bitmap bitmap, Point[] pointArr) {
        if (isInputBitmapNull(bitmap) || isPointContainerNull(pointArr)) {
            return false;
        }
        preparePointContainer(pointArr);
        return true;
    }
}
