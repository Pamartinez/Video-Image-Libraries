package com.samsung.android.gallery.module.story.smartrect;

import A.a;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SmartRect {
    private static final DeviceRatio sDeviceRatio = new DeviceRatio();
    private static final SupportRatio sSupportedRatio = new SupportRatio();

    public static RectF getBestCropRect(FileItemInterface fileItemInterface, Rect rect) {
        DeviceRatio deviceRatio = sDeviceRatio;
        int preferredRatioIndex = preferredRatioIndex(rect, deviceRatio.getDeviceRatio(fileItemInterface));
        SupportRatio supportRatio = sSupportedRatio;
        int preferredRatioIndex2 = preferredRatioIndex(rect, supportRatio.get().subList(6, 17));
        if (preferredRatioIndex == -1) {
            return getSmartCropRect(fileItemInterface, preferredRatioIndex2);
        }
        float floatValue = deviceRatio.getDeviceRatio(fileItemInterface).get(preferredRatioIndex).floatValue();
        if (floatValue == nearestRatio(((float) rect.width()) / ((float) rect.height()), floatValue, supportRatio.get(preferredRatioIndex2))) {
            return getSmartCropDeviceRect(fileItemInterface, preferredRatioIndex);
        }
        return getSmartCropRect(fileItemInterface, preferredRatioIndex2);
    }

    public static ArrayList<RectF> getFaceRectList(FileItemInterface fileItemInterface) {
        String storyCoverFaceRectRatio = MediaItemStory.getStoryCoverFaceRectRatio(fileItemInterface);
        if (TextUtils.isEmpty(storyCoverFaceRectRatio)) {
            return new ArrayList<>();
        }
        return listOf(storyCoverFaceRectRatio, Integer.MAX_VALUE);
    }

    private static RectF getSmartCropDeviceRect(FileItemInterface fileItemInterface, int i2) {
        ArrayList<RectF> totalSmartCropDeviceRect = getTotalSmartCropDeviceRect(fileItemInterface);
        if (totalSmartCropDeviceRect.isEmpty() || i2 < 0 || i2 >= totalSmartCropDeviceRect.size()) {
            return new RectF();
        }
        return totalSmartCropDeviceRect.get(i2);
    }

    private static RectF getSmartCropRect(FileItemInterface fileItemInterface, int i2) {
        ArrayList<RectF> totalSmartCropRect = getTotalSmartCropRect(fileItemInterface);
        if (!totalSmartCropRect.isEmpty()) {
            return totalSmartCropRect.get(i2);
        }
        return new RectF();
    }

    private static ArrayList<RectF> getTotalSmartCropDeviceRect(FileItemInterface fileItemInterface) {
        String totalSmartCropDeviceRatio = MediaItemStory.getTotalSmartCropDeviceRatio(fileItemInterface);
        if (TextUtils.isEmpty(totalSmartCropDeviceRatio)) {
            return new ArrayList<>();
        }
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(totalSmartCropDeviceRatio, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            while (stringTokenizer.hasMoreElements()) {
                String nextToken = stringTokenizer.nextToken();
                if (Float.parseFloat(nextToken) <= 1.0f) {
                    stringJoiner.add(nextToken);
                }
            }
            return listOf(stringJoiner.toString(), 20);
        } catch (Exception unused) {
            return new ArrayList<>();
        }
    }

    public static ArrayList<RectF> getTotalSmartCropRect(FileItemInterface fileItemInterface) {
        try {
            String totalSmartCropRatio = MediaItemStory.getTotalSmartCropRatio(fileItemInterface);
            if (!TextUtils.isEmpty(totalSmartCropRatio)) {
                ArrayList<RectF> listOf = listOf(totalSmartCropRatio, 20);
                if (listOf.size() == sSupportedRatio.size()) {
                    return listOf;
                }
            }
        } catch (Exception unused) {
        }
        return new ArrayList<>();
    }

    private static ArrayList<RectF> listOf(String str, int i2) {
        ArrayList<RectF> arrayList = new ArrayList<>();
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(str, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            for (int i7 = 0; i7 < i2 && stringTokenizer.hasMoreElements(); i7++) {
                RectF rectF = new RectF(Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken()));
                if (validPositiveRect(rectF)) {
                    arrayList.add(rectF);
                }
            }
            return arrayList;
        } catch (Exception e) {
            a.s(e, new StringBuilder("listOf failed. e="), "SmartRect");
            return arrayList;
        }
    }

    private static float nearestRatio(float f, float f5, float f8) {
        int i2 = (f5 > 0.0f ? 1 : (f5 == 0.0f ? 0 : -1));
        if (i2 <= 0 || f8 <= 0.0f ? i2 > 0 : Math.abs(f - f5) < Math.abs(f - f8)) {
            return f5;
        }
        return f8;
    }

    private static int preferredRatioIndex(Rect rect, List<Float> list) {
        if (list.isEmpty()) {
            return -1;
        }
        float width = ((float) rect.width()) / ((float) rect.height());
        int i2 = 0;
        for (int i7 = 0; i7 < list.size(); i7++) {
            if (nearestRatio(width, list.get(i2).floatValue(), list.get(i7).floatValue()) != list.get(i2).floatValue()) {
                i2 = i7;
            }
        }
        return i2;
    }

    private static boolean validPositiveRect(RectF rectF) {
        if (rectF.left < 0.0f || rectF.right < 0.0f || rectF.top < 0.0f || rectF.bottom < 0.0f || !RectUtils.isValidRect(rectF)) {
            return false;
        }
        return true;
    }
}
