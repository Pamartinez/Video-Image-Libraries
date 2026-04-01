package com.samsung.o3dp.lib3dphotography;

import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.o3dp.lib3dphotography.ObjectDetector;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class EfficientDetector implements ObjectDetectable {
    private static final String TAG = "EfficientDetector";

    /* access modifiers changed from: private */
    public static O3DPObject createBodyObject(ObjectDetector.ObjectInfo objectInfo) {
        O3DPObject o3DPObject;
        if (objectInfo.getLabel().contains("person")) {
            o3DPObject = new O3DPObject(O3DPObjType.HUMAN);
        } else {
            o3DPObject = new O3DPObject(O3DPObjType.PET);
        }
        o3DPObject.setBound(objectInfo.getRoiRect());
        return o3DPObject;
    }

    /* access modifiers changed from: private */
    public static Face createFaceObject(ObjectDetector.ObjectInfo objectInfo) {
        Face face;
        if (objectInfo.getLabel().contains("person")) {
            face = new Face(O3DPObjType.HUMAN);
        } else {
            face = new Face(O3DPObjType.PET);
        }
        face.setBound(objectInfo.getRoiRect());
        return face;
    }

    private static List<Face> toFaceList(List<ObjectDetector.ObjectInfo> list) {
        return (List) list.stream().map(new a(1)).collect(Collectors.toList());
    }

    private static List<O3DPObject> toO3DPObjectList(List<ObjectDetector.ObjectInfo> list) {
        return (List) list.stream().map(new a(0)).collect(Collectors.toList());
    }

    public void detect(Context context, Bitmap bitmap, List<O3DPObject> list, List<Face> list2) {
        List<ObjectDetector.ObjectInfo> run = ObjectDetector.run(context, bitmap);
        if (run.isEmpty()) {
            LogUtil.i(TAG, "ObjectDetector returned empty list, no objects detected");
            return;
        }
        List<ObjectDetector.ObjectInfo> searchWithCategory = ObjectDetector.searchWithCategory(ObjectDetector.Category.Body, run);
        list2.addAll(toFaceList(ObjectDetector.searchWithCategory(ObjectDetector.Category.Face, run)));
        list.addAll(toO3DPObjectList(searchWithCategory));
    }
}
