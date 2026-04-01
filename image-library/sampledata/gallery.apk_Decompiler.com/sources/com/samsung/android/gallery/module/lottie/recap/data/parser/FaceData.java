package com.samsung.android.gallery.module.lottie.recap.data.parser;

import android.graphics.RectF;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.RectUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FaceData {
    double[] facePos = {MapUtil.INVALID_LOCATION, MapUtil.INVALID_LOCATION, MapUtil.INVALID_LOCATION, MapUtil.INVALID_LOCATION};
    public String name;
    int orientation;
    long personId;
    long recommendedId;

    public RectF getFaceRectF() {
        double[] dArr = this.facePos;
        if (dArr[0] == MapUtil.INVALID_LOCATION && dArr[1] == MapUtil.INVALID_LOCATION && dArr[3] == MapUtil.INVALID_LOCATION && dArr[4] == MapUtil.INVALID_LOCATION) {
            return null;
        }
        double[] dArr2 = this.facePos;
        return RectUtils.getRotatedRectFRatio(new RectF((float) dArr2[0], (float) dArr2[1], (float) dArr2[2], (float) dArr2[3]), this.orientation);
    }
}
