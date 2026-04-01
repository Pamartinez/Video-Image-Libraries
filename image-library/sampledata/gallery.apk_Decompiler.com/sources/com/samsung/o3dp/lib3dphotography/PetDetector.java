package com.samsung.o3dp.lib3dphotography;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.o3dp.lib3dphotography.graphics.O3DPRectUtil;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.o3dp.lib3dphotography.utils.StringUtil;
import java.util.List;
import java.util.function.Supplier;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PetDetector {
    private static final int LABEL_PETS = 5;
    private static final int LABEL_PET_FACE = 3;
    private static final String TAG = "PetDetector";

    private static int calculateMinPetDetectionSide(Bitmap bitmap, float f) {
        return Math.round(((float) Math.min(bitmap.getWidth(), bitmap.getHeight())) * f);
    }

    private static O3DPObject createBodyObject(Rect rect) {
        O3DPObject o3DPObject = new O3DPObject(O3DPObjType.PET);
        o3DPObject.setBound(rect);
        return o3DPObject;
    }

    private static Face createFaceObject(Rect rect) {
        Face face = new Face(O3DPObjType.PET);
        face.setBound(rect);
        return face;
    }

    private static JSONArray parseDetectionResult(String str) {
        try {
            return new JSONArray(new JSONObject(str).getJSONObject("EstimatorValue").getJSONObject("PETDET").getString("dataJson"));
        } catch (JSONException e) {
            LogUtil.e(TAG, "Failed to parse detection result: " + e);
            return null;
        }
    }

    private static void processObject(JSONObject jSONObject, Bitmap bitmap, List<Face> list, List<O3DPObject> list2) {
        int i2 = jSONObject.getInt("classValue");
        Rect parseObjectRoi = O3DPRectUtil.parseObjectRoi(jSONObject, bitmap.getWidth(), bitmap.getHeight());
        int min = Math.min(parseObjectRoi.width(), parseObjectRoi.height());
        if (i2 == 3) {
            LogUtil.d(TAG, "Pet face ROI : " + parseObjectRoi);
            int calculateMinPetDetectionSide = calculateMinPetDetectionSide(bitmap, 0.11111111f);
            if (min <= calculateMinPetDetectionSide) {
                LogUtil.i(TAG, StringUtil.format("Small pet face BB with a side length %d is removed (<=%d)", Integer.valueOf(min), Integer.valueOf(calculateMinPetDetectionSide)));
            } else {
                list.add(createFaceObject(parseObjectRoi));
            }
        } else if (i2 == 5) {
            LogUtil.d(TAG, "Pet object ROI : " + parseObjectRoi);
            int calculateMinPetDetectionSide2 = calculateMinPetDetectionSide(bitmap, 0.16666667f);
            if (min <= calculateMinPetDetectionSide2) {
                LogUtil.i(TAG, StringUtil.format("Small pet body BB with a side length %d is removed (<=%d)", Integer.valueOf(min), Integer.valueOf(calculateMinPetDetectionSide2)));
            } else {
                list2.add(createBodyObject(parseObjectRoi));
            }
        }
    }

    public static void run(Bitmap bitmap, List<Face> list, List<O3DPObject> list2, Supplier<String> supplier) {
        String str = supplier.get();
        if (str == null) {
            LogUtil.e(TAG, "Failed to run requestServiceObjDet");
            return;
        }
        JSONArray parseDetectionResult = parseDetectionResult(str);
        if (parseDetectionResult == null) {
            LogUtil.e(TAG, "Failed to parse detection result");
            return;
        }
        for (int i2 = 0; i2 < parseDetectionResult.length(); i2++) {
            try {
                processObject(parseDetectionResult.getJSONObject(i2), bitmap, list, list2);
            } catch (JSONException e) {
                LogUtil.e(TAG, "JSONException at processing object index " + i2 + ": " + e);
            }
        }
    }
}
