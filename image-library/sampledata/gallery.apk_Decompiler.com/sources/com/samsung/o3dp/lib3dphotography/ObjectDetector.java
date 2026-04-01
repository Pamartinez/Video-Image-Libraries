package com.samsung.o3dp.lib3dphotography;

import A0.l;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.RemoteException;
import com.samsung.o3dp.lib3dphotography.graphics.O3DPRectUtil;
import com.samsung.o3dp.lib3dphotography.utils.DebugUtil;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.o3dp.lib3dphotography.utils.RemasterUtil;
import com.samsung.o3dp.lib3dphotography.utils.StringUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ObjectDetector {
    private static final String TAG = "ObjectDetector";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Category {
        Body("body"),
        Face("face");
        
        /* access modifiers changed from: private */
        public final String categoryName;

        private Category(String str) {
            this.categoryName = str;
        }

        public String getCategoryName() {
            return this.categoryName;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Label {
        Person("person"),
        Cat("cat"),
        Dog("dog"),
        PersonFace("person face"),
        CatFace("cat face"),
        DogFace("dog face");
        
        private final String labelName;

        private Label(String str) {
            this.labelName = str;
        }

        public String getLabelName() {
            return this.labelName;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ObjectInfo {
        /* access modifiers changed from: private */
        public final String categoryName;
        /* access modifiers changed from: private */
        public final String label;
        private final Rect roiRect;

        public ObjectInfo(String str, String str2, Rect rect) {
            this.categoryName = str;
            this.label = str2;
            this.roiRect = rect;
        }

        public String getLabel() {
            return this.label;
        }

        public Rect getRoiRect() {
            return this.roiRect;
        }
    }

    private static JSONArray getJsonArray(String str) {
        return new JSONArray(new JSONObject(str).getJSONObject("EstimatorValue").getJSONObject("OBJECTDET").getString("dataJson"));
    }

    private static float getMinFaceSizeRatio(String str) {
        if (Label.PersonFace.getLabelName().equals(str)) {
            return 0.083333336f;
        }
        return 0.11111111f;
    }

    private static boolean isTooSmallBodyRect(Rect rect, int i2) {
        int round = Math.round(((float) i2) * 0.16666667f);
        int min = Math.min(rect.width(), rect.height());
        if (min > round) {
            return false;
        }
        LogUtil.i(TAG, StringUtil.format("Small body BB with a side length %d is removed (<=%d)", Integer.valueOf(min), Integer.valueOf(round)));
        return true;
    }

    private static boolean isTooSmallFaceRect(Rect rect, int i2, float f) {
        int round = Math.round(((float) i2) * f);
        int min = Math.min(rect.width(), rect.height());
        if (min > round) {
            return false;
        }
        LogUtil.i(TAG, StringUtil.format("Small face BB with a side length %d is removed (<=%d)", Integer.valueOf(min), Integer.valueOf(round)));
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$searchWithLabels$1(ArrayList arrayList, ObjectInfo objectInfo) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (objectInfo.label.equals(((Label) it.next()).getLabelName())) {
                return true;
            }
        }
        return false;
    }

    private static List<ObjectInfo> makeObjectInfoList(String str, int i2, int i7) {
        int min = Math.min(i2, i7);
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jsonArray = getJsonArray(str);
            for (int i8 = 0; i8 < jsonArray.length(); i8++) {
                JSONObject jSONObject = jsonArray.getJSONObject(i8);
                String string = jSONObject.getString("categoryName");
                String string2 = jSONObject.getString("className");
                Rect parseObjectRoi = O3DPRectUtil.parseObjectRoi(jSONObject, i2, i7);
                if (!Category.Body.getCategoryName().equals(string) || !isTooSmallBodyRect(parseObjectRoi, min)) {
                    if (!Category.Face.getCategoryName().equals(string) || !isTooSmallFaceRect(parseObjectRoi, min, getMinFaceSizeRatio(string2))) {
                        arrayList.add(new ObjectInfo(string, string2, parseObjectRoi));
                    }
                }
            }
            return arrayList;
        } catch (JSONException e) {
            LogUtil.e(TAG, "Failed to run Object Detector : " + e);
            return arrayList;
        }
    }

    public static List<ObjectInfo> run(Context context, Bitmap bitmap) {
        if (RemasterUtil.isAtLeastOneUi8_0()) {
            try {
                String b = new l().b(context, bitmap, 2);
                if (b != null) {
                    return makeObjectInfoList(b, bitmap.getWidth(), bitmap.getHeight());
                }
                if (!DebugUtil.DEBUG_BINARY) {
                    LogUtil.e(TAG, "ObjectDetector.run() : analyzeImageWithDetectionEngine() returns null");
                    return new ArrayList();
                }
                throw new IllegalStateException("ObjectDetector.run() : analyzeImageWithDetectionEngine() returns null");
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalStateException("ObjectDetector is not supported for this OneUi version!");
        }
    }

    public static List<ObjectInfo> searchWithCategory(Category category, List<ObjectInfo> list) {
        return (List) list.stream().filter(new l(category, 1)).collect(Collectors.toList());
    }

    public static List<ObjectInfo> searchWithLabels(ArrayList<Label> arrayList, List<ObjectInfo> list) {
        return (List) list.stream().filter(new l(arrayList, 0)).collect(Collectors.toList());
    }
}
