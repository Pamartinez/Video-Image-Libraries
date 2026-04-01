package com.samsung.o3dp.lib3dphotography;

import A0.l;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.o3dp.lib3dphotography.utils.RemasterUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ObjectDetectorLegacy implements ObjectDetectable {
    private static final String TAG = "ObjectDetectorLegacy";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DetectorSupplier implements Supplier<String> {
        private final Context mContext;
        private final Bitmap mInBitmap;

        public DetectorSupplier(Context context, Bitmap bitmap) {
            this.mContext = context;
            this.mInBitmap = bitmap;
        }

        public String get() {
            try {
                if (RemasterUtil.isAtLeastOneUi7_0()) {
                    return new l().b(this.mContext, this.mInBitmap, 1);
                }
                return RemasterUtil.requestRemasterServiceObjDet(this.mContext, this.mInBitmap, 24);
            } catch (RemoteException e) {
                LogUtil.e(ObjectDetectorLegacy.TAG, "RemoteException at analyzeImageWithDetectionEngine() - " + e);
                return null;
            }
        }
    }

    public void detect(Context context, Bitmap bitmap, List<O3DPObject> list, List<Face> list2) {
        list2.addAll(FaceDetector.predict(bitmap));
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        PetDetector.run(bitmap, arrayList2, arrayList, new DetectorSupplier(context, bitmap));
        list2.addAll(arrayList2);
        list.addAll(arrayList);
        list.addAll(HumanDetector.run(context, bitmap));
    }
}
