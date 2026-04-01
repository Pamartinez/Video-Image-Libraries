package com.samsung.o3dp.lib3dphotography.graphics;

import A4.I;
import android.graphics.Rect;
import com.samsung.android.gallery.module.dynamicview.a;
import com.samsung.o3dp.lib3dphotography.O3DPObjType;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class O3DPRectUtil {
    private static final float DELETE_CONDITION_PERCENT_50 = 0.5f;
    private static final String TAG = "O3DPRectUtil";

    public static <T extends O3DPRect> List<T> copyO3DPRectInstances(List<T> list) {
        ArrayList arrayList = new ArrayList();
        for (T copyInstance : list) {
            arrayList.add(copyInstance.copyInstance());
        }
        return arrayList;
    }

    public static <T extends O3DPRect> List<Integer> extractRectPoint(List<T> list) {
        ArrayList arrayList = new ArrayList();
        for (T bound : list) {
            Rect bound2 = bound.getBound();
            arrayList.add(Integer.valueOf(bound2.left));
            arrayList.add(Integer.valueOf(bound2.top));
            arrayList.add(Integer.valueOf(bound2.right));
            arrayList.add(Integer.valueOf(bound2.bottom));
        }
        return arrayList;
    }

    public static <T extends O3DPRect> List<T> extractTargetsFromList(List<T> list, O3DPObjType o3DPObjType) {
        return (List) list.stream().filter(new I(24, o3DPObjType)).collect(Collectors.toList());
    }

    public static <T extends O3DPRect> T getLargestRect(List<T> list) {
        int i2 = 0;
        T t = null;
        for (T t3 : list) {
            Rect bound = t3.getBound();
            int abs = Math.abs(bound.bottom - bound.top) * Math.abs(bound.right - bound.left);
            if (abs > i2) {
                t = t3;
                i2 = abs;
            }
        }
        return t;
    }

    public static int getOverlapDirection(Rect rect, Rect rect2) {
        int i2;
        if (rect.top >= rect2.top) {
            LogUtil.i(TAG, "getOverlapDirection() : top is overlapping.");
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (rect.bottom <= rect2.bottom) {
            i2 |= 2;
            LogUtil.i(TAG, "getOverlapDirection() : bottom is overlapping.");
        }
        if (rect.left >= rect2.left) {
            i2 |= 4;
            LogUtil.i(TAG, "getOverlapDirection() : left is overlapping.");
        }
        if (rect.right > rect2.right) {
            return i2;
        }
        int i7 = i2 | 8;
        LogUtil.i(TAG, "getOverlapDirection() : right is overlapping.");
        return i7;
    }

    public static boolean isRectOverlap(Rect rect, Rect rect2) {
        int max = Math.max(rect.left, rect2.left);
        int max2 = Math.max(rect.top, rect2.top);
        int min = Math.min(rect.right, rect2.right);
        int min2 = Math.min(rect.bottom, rect2.bottom);
        if (max < min && max2 < min2) {
            if (((double) ((min2 - max2) * (min - max))) / ((double) (rect2.height() * rect2.width())) >= 0.5d) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$extractTargetsFromList$0(O3DPObjType o3DPObjType, O3DPRect o3DPRect) {
        if (o3DPRect.getObjectType() == o3DPObjType) {
            return true;
        }
        return false;
    }

    public static Rect parseObjectRoi(JSONObject jSONObject, int i2, int i7) {
        JSONObject jSONObject2 = jSONObject.getJSONObject("roiBounds");
        double d = (double) i2;
        double d2 = (double) i7;
        return new Rect((int) Math.round(jSONObject2.getDouble("left") * d), (int) Math.round(jSONObject2.getDouble("top") * d2), (int) Math.round(jSONObject2.getDouble("right") * d), (int) Math.round(jSONObject2.getDouble("bottom") * d2));
    }

    public static <T extends O3DPRect> List<T> removeRectIfNotOverlap(List<T> list, Rect rect) {
        ArrayList arrayList = new ArrayList();
        for (T t : list) {
            if (isRectOverlap(rect, t.getBound())) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static void scaleRect(Rect rect, float f) {
        rect.left = Math.round(((float) rect.left) * f);
        rect.top = Math.round(((float) rect.top) * f);
        rect.right = Math.round(((float) rect.right) * f);
        rect.bottom = Math.round(((float) rect.bottom) * f);
    }

    public static <T extends O3DPRect> void scaleRects(List<T> list, float f) {
        for (T scaleBound : list) {
            scaleBound.scaleBound(f);
        }
    }

    public static <T extends O3DPRect> void shiftRectToCropBound(List<T> list, Rect rect) {
        for (T t : list) {
            int size = t.size();
            boolean z = true;
            if (!t.intersect(rect)) {
                t.setToBeDeleted(true);
            } else {
                t.offset(-rect.left, -rect.top);
                if (((float) t.size()) >= ((float) size) * 0.5f) {
                    z = false;
                }
                t.setToBeDeleted(z);
            }
        }
        list.removeIf(new a(16));
    }
}
