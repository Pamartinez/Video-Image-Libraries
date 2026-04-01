package com.samsung.o3dp.lib3dphotography;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.o3dp.lib3dphotography.nativelib.JNI;
import com.samsung.o3dp.lib3dphotography.utils.ImageUtil;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.o3dp.lib3dphotography.utils.StringUtil;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FaceDetector {
    private static final String TAG = "FaceDetector";

    private static List<Face> convertStrToFace(List<String> list, List<String> list2, int i2, int i7) {
        ArrayList arrayList = new ArrayList();
        int round = Math.round(((float) Math.min(i2, i7)) * 0.083333336f);
        char c5 = 0;
        int i8 = 0;
        while (i8 < list.size()) {
            String[] split = list.get(i8).split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            if (split.length != 4) {
                LogUtil.w(TAG, "Failed to parse strRects, size : " + split.length);
                return arrayList;
            }
            String[] split2 = list2.get(i8).split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            if (split2.length != 3) {
                LogUtil.w(TAG, "Failed to parse strDirections, size : " + split2.length);
                return arrayList;
            }
            Face face = new Face(O3DPObjType.HUMAN);
            try {
                char c6 = c5;
                Rect rect = new Rect(Integer.parseInt(split[c5]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
                int min = Math.min(rect.width(), rect.height());
                if (min <= round) {
                    LogUtil.i(TAG, StringUtil.format("Small human face BB with a side length %d is removed (<=%d)", Integer.valueOf(min), Integer.valueOf(round)));
                } else {
                    face.setBound(rect);
                    try {
                        float parseFloat = Float.parseFloat(split2[c6]);
                        float parseFloat2 = Float.parseFloat(split2[1]);
                        float parseFloat3 = Float.parseFloat(split2[2]);
                        face.setRoll(parseFloat);
                        face.setYaw(parseFloat2);
                        face.setPitch(parseFloat3);
                        arrayList.add(face);
                    } catch (NumberFormatException e) {
                        LogUtil.e(TAG, "Failed to parse direction : " + e);
                    }
                }
                i8++;
                c5 = c6;
            } catch (NumberFormatException e7) {
                LogUtil.e(TAG, "Failed to parse bound : " + e7);
            }
        }
        return arrayList;
    }

    public static List<Face> predict(Bitmap bitmap) {
        LogUtil.d(TAG, "FaceDetector - E");
        byte[] convertBitmapToBgr = ImageUtil.convertBitmapToBgr(bitmap);
        if (convertBitmapToBgr == null) {
            LogUtil.e(TAG, "The byte array converted from an input bitmap is null.");
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        JNI.runFaceDetector(convertBitmapToBgr, bitmap.getWidth(), bitmap.getHeight(), arrayList, arrayList2);
        List<Face> convertStrToFace = convertStrToFace(arrayList, arrayList2, bitmap.getWidth(), bitmap.getHeight());
        LogUtil.d(TAG, "FaceDetector - X");
        return convertStrToFace;
    }
}
