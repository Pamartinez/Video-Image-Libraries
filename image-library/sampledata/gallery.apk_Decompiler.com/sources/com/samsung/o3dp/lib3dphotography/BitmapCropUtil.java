package com.samsung.o3dp.lib3dphotography;

import H7.x;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Size;
import com.samsung.o3dp.lib3dphotography.animation.AnimationPolicy;
import com.samsung.o3dp.lib3dphotography.graphics.O3DPRectUtil;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BitmapCropUtil {
    private static final String TAG = "BitmapCropUtil";

    private static Size adjustCropSizeForTargetAspectRatio(int i2, int i7) {
        if (exceedsMaxRatio(i2, i7)) {
            if (i2 == 1080) {
                i7 = Math.min(i7, Math.round(((float) i2) * 2.4444444f));
            } else {
                i2 = Math.min(i2, Math.round(((float) i7) * 2.4444444f));
            }
        }
        return new Size(i2, i7);
    }

    private static Rect calculateCropBound(int i2, int i7, Point point) {
        Size adjustCropSizeForTargetAspectRatio = adjustCropSizeForTargetAspectRatio(i2, i7);
        int width = adjustCropSizeForTargetAspectRatio.getWidth();
        int height = adjustCropSizeForTargetAspectRatio.getHeight();
        Point point2 = new Point(i2 / 2, i7 / 2);
        int i8 = i2 - width;
        int i10 = (i8 / 2) + (point.x - point2.x);
        int i11 = point.y - point2.y;
        int i12 = i7 - height;
        int i13 = (i12 / 2) + i11;
        if (i10 + width <= i2) {
            if (i10 < 0) {
                i8 = 0;
            } else {
                i8 = i10;
            }
        }
        if (i13 + height <= i7) {
            if (i13 < 0) {
                i12 = 0;
            } else {
                i12 = i13;
            }
        }
        return new Rect(i8, i12, width + i8, height + i12);
    }

    private static float calculateScaleFactor(int i2, int i7, int i8) {
        return ((float) i8) / ((float) Math.min(i2, i7));
    }

    private static boolean exceedsMaxRatio(int i2, int i7) {
        if (((float) Math.max(i2, i7)) / ((float) Math.min(i2, i7)) > 2.4444444f) {
            return true;
        }
        return false;
    }

    public static Rect standardizeInputsObj(int i2, int i7, List<Face> list, List<O3DPObject> list2, Rect rect, List<Rect> list3, float f, int i8) {
        O3DPRectUtil.scaleRects(list, f);
        O3DPRectUtil.scaleRects(list2, f);
        if (list3 != null) {
            list3.forEach(new x(f, 1));
        }
        int round = Math.round(((float) i2) * f);
        int round2 = Math.round(((float) i7) * f);
        Point focusPointOfObjects = AnimationPolicy.getFocusPointOfObjects(round, round2, list, list2, i8);
        LogUtil.i(TAG, "standardizeInputsObj: Focus " + focusPointOfObjects);
        Rect calculateCropBound = calculateCropBound(round, round2, focusPointOfObjects);
        LogUtil.i(TAG, "standardizeInputsObj: cropBound " + calculateCropBound);
        O3DPRectUtil.shiftRectToCropBound(list, calculateCropBound);
        O3DPRectUtil.shiftRectToCropBound(list2, calculateCropBound);
        if (rect != null) {
            O3DPRectUtil.scaleRect(rect, f);
            rect.intersect(calculateCropBound);
            rect.offset(-calculateCropBound.left, -calculateCropBound.top);
        }
        return calculateCropBound;
    }

    public static void updateCropSize(O3DPContext o3DPContext, int i2, int i7) {
        float calculateScaleFactor = calculateScaleFactor(i2, i7, 1080);
        Size adjustCropSizeForTargetAspectRatio = adjustCropSizeForTargetAspectRatio(Math.round(((float) i2) * calculateScaleFactor), Math.round(((float) i7) * calculateScaleFactor));
        o3DPContext.setCropSize(adjustCropSizeForTargetAspectRatio.getWidth() & -2, adjustCropSizeForTargetAspectRatio.getHeight() & -2);
    }
}
