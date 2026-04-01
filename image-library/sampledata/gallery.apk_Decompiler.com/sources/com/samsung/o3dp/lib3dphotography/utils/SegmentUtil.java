package com.samsung.o3dp.lib3dphotography.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.samsung.android.app.sdk.deepsky.objectcapture.ObjectCaptureProvider;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectCapture;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectInfo;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectResult;
import com.samsung.o3dp.lib3dphotography.DepthMap;
import com.samsung.o3dp.lib3dphotography.nativelib.JNI;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SegmentUtil {
    private static final String TAG = "SegmentUtil";

    public static Bitmap getSegImage(ObjectInfo objectInfo, int i2, int i7) {
        Bitmap createBitmap = Bitmap.createBitmap(i2, i7, Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(objectInfo.getObjBitmap(), (Rect) null, objectInfo.getBoundRect(), (Paint) null);
        return createBitmap;
    }

    public static ObjectResult predict(Context context, Bitmap bitmap) {
        ObjectCapture instance = ObjectCaptureProvider.getInstance(context);
        if (!instance.isObjectCaptureSupported()) {
            LogUtil.e(TAG, "the engine does NOT support the current device.");
            return null;
        }
        instance.init();
        ObjectResult capture = instance.capture(bitmap);
        boolean isCaptured = capture.isCaptured();
        instance.release();
        if (!isCaptured) {
            LogUtil.w(TAG, "No objects were captured");
        }
        if (isCaptured) {
            return capture;
        }
        return null;
    }

    public static Bitmap refineFPInSegmentImg(DepthMap depthMap, Bitmap bitmap) {
        if (depthMap.width == 910 && depthMap.height == 910 && bitmap != null) {
            long currentTimeMillis = System.currentTimeMillis();
            Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            if (!JNI.refineFPInSegmentImg(depthMap.data, bitmap, copy, 910, 910)) {
                LogUtil.w(TAG, "Failed to refine segment image !!");
                return null;
            }
            LogUtil.d(TAG, "refine Segment Elapsed Time : " + (System.currentTimeMillis() - currentTimeMillis));
            return copy;
        }
        LogUtil.d(TAG, "Depth Map Quality is not enough for segment refinement : H(" + depthMap.height + "), W(" + depthMap.width + ")");
        return null;
    }
}
