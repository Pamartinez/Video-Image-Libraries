package com.samsung.o3dp.lib3dphotography;

import A0.l;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.RemoteException;
import com.samsung.o3dp.lib3dphotography.graphics.O3DPRectUtil;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.o3dp.lib3dphotography.utils.RemasterUtil;
import com.samsung.o3dp.lib3dphotography.utils.StringUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class HumanDetector {
    private static final String TAG = "HumanDetector";
    public static final int TARGET_ID_PERSON = 2;

    public static List<O3DPObject> run(Context context, Bitmap bitmap) {
        String str;
        ArrayList arrayList = new ArrayList();
        try {
            if (RemasterUtil.isAtLeastOneUi7_0()) {
                str = new l().b(context, bitmap, 0);
            } else {
                str = RemasterUtil.requestRemasterServiceObjDet(context, bitmap, 26);
            }
            if (str == null) {
                LogUtil.e(TAG, "Failed to run requestServiceObjDet");
                return arrayList;
            }
            int round = Math.round(((float) Math.min(bitmap.getWidth(), bitmap.getHeight())) * 0.16666667f);
            try {
                JSONArray jSONArray = new JSONArray(new JSONObject(str).getJSONObject("EstimatorValue").getJSONObject("HUMANDET").getString("dataJson"));
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    if (jSONObject.getInt("classValue") == 2) {
                        Rect parseObjectRoi = O3DPRectUtil.parseObjectRoi(jSONObject, bitmap.getWidth(), bitmap.getHeight());
                        LogUtil.d(TAG, "ObjectROI : " + parseObjectRoi);
                        int min = Math.min(parseObjectRoi.width(), parseObjectRoi.height());
                        if (min <= round) {
                            LogUtil.i(TAG, StringUtil.format("Small human body BB with a side length %d is removed (<=%d)", Integer.valueOf(min), Integer.valueOf(round)));
                        } else {
                            O3DPObject o3DPObject = new O3DPObject(O3DPObjType.HUMAN);
                            o3DPObject.setBound(parseObjectRoi);
                            arrayList.add(o3DPObject);
                        }
                    }
                }
                return arrayList;
            } catch (JSONException e) {
                LogUtil.e(TAG, "Failed to run HumanDetector : " + e);
                return arrayList;
            }
        } catch (RemoteException e7) {
            LogUtil.e(TAG, "RemoteException at analyzeImageWithDetectionEngine() - " + e7);
            return arrayList;
        }
    }
}
