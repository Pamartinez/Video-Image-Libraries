package com.samsung.o3dp.lib3dphotography.animation;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.o3dp.lib3dphotography.DepthMap;
import com.samsung.o3dp.lib3dphotography.Face;
import com.samsung.o3dp.lib3dphotography.O3DPContext;
import com.samsung.o3dp.lib3dphotography.O3DPObjType;
import com.samsung.o3dp.lib3dphotography.O3DPObject;
import com.samsung.o3dp.lib3dphotography.graphics.O3DPRect;
import com.samsung.o3dp.lib3dphotography.graphics.O3DPRectUtil;
import com.samsung.o3dp.lib3dphotography.nativelib.JNI;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.o3dp.lib3dphotography.utils.StringUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AnimationPolicy {
    private static final String ANIM_CENTER_L2R = "Center-L2R";
    private static final String ANIM_CENTER_R2L = "Center-R2L";
    private static final String ANIM_DOLLY_L = "Dolly-L";
    private static final String ANIM_DOLLY_R = "Dolly-R";
    private static final String ANIM_GLIDE_L2R = "Glide-L2R";
    private static final String ANIM_GLIDE_R2L = "Glide-R2L";
    private static final String ANIM_LANDSCAPE = "Landscape";
    public static final String ANIM_PANORAMA = "Panorama";
    private static final String ANIM_SLIDE = "Slide";
    private static final String ANIM_STATIC = "Static";
    private static final float PERCENT_BOTTOM_ROW_TO_CHECK = 0.01f;
    private static final float SIDE_RATIO = 0.3333333f;
    private static final String TAG = "AnimationPolicy";
    private static final float THRESHOLD_FACE_AREA_RATIO = 0.02f;
    private static final float THRESHOLD_FLOATING_OBJECT_TO_BOTTOM_RATIO = 0.09f;
    private static final float THRESHOLD_UPPER_BODY_WIDTH_RATIO = 0.95f;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum FacePosition {
        CENTERED,
        LEFT_ALIGNED,
        RIGHT_ALIGNED,
        TOP_ALIGNED,
        BOTTOM_ALIGNED
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum PositionX {
        NONE,
        LEFT,
        CENTER,
        RIGHT
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum PositionY {
        NONE,
        TOP,
        CENTER,
        BOTTOM
    }

    public static boolean areObjectsScattered(Bitmap bitmap, Rect rect, List<O3DPObject> list) {
        int width = rect.width();
        int height = rect.height();
        int width2 = bitmap.getWidth();
        int height2 = bitmap.getHeight();
        float f = ((float) height) / ((float) height2);
        if (((float) width) / ((float) width2) < THRESHOLD_UPPER_BODY_WIDTH_RATIO || f < THRESHOLD_UPPER_BODY_WIDTH_RATIO || list.size() < 2) {
            return false;
        }
        int i2 = height2;
        int i7 = i2;
        int i8 = width2;
        int i10 = i8;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        for (O3DPObject bound : list) {
            Rect bound2 = bound.getBound();
            i11 = Math.max(i11, bound2.top);
            i2 = Math.min(i2, bound2.top);
            i12 = Math.max(i12, bound2.bottom);
            i7 = Math.min(i7, bound2.bottom);
            i13 = Math.min(i13, bound2.left);
            i8 = Math.max(i8, bound2.left);
            i14 = Math.max(i14, bound2.right);
            i10 = Math.min(i10, bound2.right);
        }
        int abs = Math.abs(i11 - i2);
        int abs2 = Math.abs(i12 - i7);
        int abs3 = Math.abs(i13 - i8);
        int abs4 = Math.abs(i14 - i10);
        int i15 = width2 / 2;
        int i16 = height2 / 2;
        if (abs >= i16 || abs2 >= i16 || abs3 >= i15 || abs4 >= i15) {
            return true;
        }
        return false;
    }

    public static String chooseAnimation(Bitmap bitmap, Bitmap bitmap2, Rect rect, List<Face> list, List<O3DPObject> list2, Point point, O3DPContext o3DPContext, boolean z, boolean z3, JSONObject jSONObject) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (z) {
            return ANIM_PANORAMA;
        }
        if (o3DPContext.getSalientObjCnt() == 0) {
            return ANIM_LANDSCAPE;
        }
        LogUtil.i(TAG, StringUtil.format("# faces %d, # bodies %d, # salientObject %d", Integer.valueOf(list.size()), Integer.valueOf(list2.size()), Integer.valueOf(o3DPContext.getSalientObjCnt())));
        LogUtil.i(TAG, "focus : " + point);
        boolean isFaceDominant = isFaceDominant(bitmap, list);
        LogUtil.i(TAG, "faceDominant? " + isFaceDominant);
        FacePosition facePosition = getFacePosition(bitmap, list);
        LogUtil.i(TAG, "facePosition " + facePosition.name());
        boolean z7 = rect != null && isSegBoundCoveringImage(bitmap, rect);
        LogUtil.i(TAG, "isSegBoundCoveringImage? " + z7);
        PositionX positionX = getPositionX(width, point.x);
        PositionY positionY = getPositionY(height, point.y);
        LogUtil.i(TAG, StringUtil.format("posX : %s, poxY : %s", positionX, positionY));
        try {
            jSONObject.put("FACE_DOMINANT", isFaceDominant);
            jSONObject.put("FACE_POSITION", facePosition.name());
            jSONObject.put("IS_SEG_BOUND_COVERING_IMAGE", z7);
            jSONObject.put("IS_NO_SEG_IN_CENTER", o3DPContext.isNoSegInCenter());
            jSONObject.put("ARE_OBJECTS_SCATTERED", o3DPContext.areObjectsScattered());
            jSONObject.put("IS_SEG_OBJECT_BEHIND", o3DPContext.isSegObjectBehind());
            jSONObject.put("IS_FLOATING", o3DPContext.isFloating());
            jSONObject.put("IS_SOLO_UPPER_BODY_SHOT", isSoloUpperBodyShot(bitmap2, list));
            jSONObject.put("POS_X", positionX);
            jSONObject.put("POS_Y", positionY);
        } catch (JSONException e) {
            LogUtil.w(TAG, "Failed to put information into mInfo : " + e);
        }
        if (FacePosition.TOP_ALIGNED.equals(facePosition)) {
            return ANIM_SLIDE;
        }
        LogUtil.i(TAG, "isNoSegInCenter " + o3DPContext.isNoSegInCenter());
        if (o3DPContext.isNoSegInCenter()) {
            return ANIM_SLIDE;
        }
        LogUtil.i(TAG, "areObjectsScattered " + o3DPContext.areObjectsScattered());
        if (o3DPContext.areObjectsScattered()) {
            return ANIM_SLIDE;
        }
        if (z3) {
            return ANIM_STATIC;
        }
        if (isFaceDominant) {
            if (point.x >= width / 2) {
                return ANIM_DOLLY_R;
            }
            return ANIM_DOLLY_L;
        } else if (positionY == PositionY.TOP && (o3DPContext.isFloating() || o3DPContext.isSegObjectBehind())) {
            return ANIM_SLIDE;
        } else {
            if (positionX != PositionX.CENTER) {
                return point.x >= width / 2 ? ANIM_GLIDE_R2L : ANIM_GLIDE_L2R;
            }
            if (!o3DPContext.isSegObjectBehind()) {
                boolean isSoloUpperBodyShot = isSoloUpperBodyShot(bitmap2, list);
                LogUtil.i(TAG, "soloUpperBody ? " + isSoloUpperBodyShot);
                if (isSoloUpperBodyShot) {
                    if (point.x >= width / 2) {
                        return ANIM_CENTER_L2R;
                    }
                    return ANIM_CENTER_R2L;
                }
            }
            return point.x >= width / 2 ? ANIM_GLIDE_R2L : ANIM_GLIDE_L2R;
        }
    }

    private static FacePosition getFacePosition(Bitmap bitmap, List<Face> list) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        for (Face bound : list) {
            Rect bound2 = bound.getBound();
            float f = (float) width;
            if (((float) bound2.left) < 0.1f * f) {
                return FacePosition.LEFT_ALIGNED;
            }
            if (((float) bound2.right) >= f * 0.9f) {
                return FacePosition.RIGHT_ALIGNED;
            }
            float f5 = (float) height;
            if (((float) bound2.top) < 0.2f * f5) {
                return FacePosition.TOP_ALIGNED;
            }
            if (((float) bound2.bottom) >= f5 * 0.9f) {
                return FacePosition.BOTTOM_ALIGNED;
            }
        }
        return FacePosition.CENTERED;
    }

    public static Point getFocusPointOfBodyObjects(List<O3DPObject> list) {
        Point point = new Point();
        O3DPRect largestRect = O3DPRectUtil.getLargestRect(list);
        if (largestRect != null) {
            Rect bound = largestRect.getBound();
            point.x = bound.centerX();
            point.y = (bound.centerY() + bound.top) / 2;
        }
        return point;
    }

    public static Point getFocusPointOfFace(List<Face> list) {
        Point point = new Point();
        O3DPRect largestRect = O3DPRectUtil.getLargestRect(list);
        if (largestRect != null) {
            Rect bound = largestRect.getBound();
            point.x = bound.centerX();
            point.y = bound.centerY();
        }
        return point;
    }

    public static <T extends O3DPRect> Point getFocusPointOfMultiObjects(List<T> list) {
        boolean z;
        int i2;
        Point point = new Point();
        int i7 = 0;
        int i8 = 0;
        int i10 = 0;
        for (T t : list) {
            Rect bound = t.getBound();
            if (t.getObjectType() == O3DPObjType.HUMAN) {
                z = true;
            } else {
                z = false;
            }
            i7 += bound.centerX();
            if (z) {
                i2 = (bound.centerY() + bound.top) / 2;
            } else {
                i2 = bound.centerY();
            }
            i8 += i2;
            i10++;
        }
        if (i7 == 0 && i8 == 0) {
            return point;
        }
        point.x = i7 / i10;
        point.y = i8 / i10;
        return point;
    }

    public static Point getFocusPointOfObjects(int i2, int i7, List<Face> list, List<O3DPObject> list2, int i8) {
        return getFocusPointOfObjects(i2, i7, (DepthMap) null, (Bitmap) null, list, list2, i8);
    }

    private static <T extends O3DPRect> Rect getFrontObjectFromDepthMap(DepthMap depthMap, Bitmap bitmap, List<T> list) {
        String findFrontObjectUsingDepth = JNI.findFrontObjectUsingDepth(depthMap.data, depthMap.width, depthMap.height, bitmap, O3DPRectUtil.extractRectPoint(list));
        if (findFrontObjectUsingDepth == null) {
            LogUtil.e(TAG, "Cannot find front object");
            return null;
        }
        String[] split = findFrontObjectUsingDepth.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return new Rect(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
    }

    public static Rect getMostHeadOnFace(List<Face> list) {
        float f = Float.MAX_VALUE;
        Rect rect = null;
        for (Face next : list) {
            float abs = Math.abs(next.getYaw());
            if (abs < f && abs < 25.0f) {
                rect = next.getBound();
                f = abs;
            }
        }
        return rect;
    }

    private static PositionX getPositionX(int i2, int i7) {
        int round = Math.round(((float) i2) * SIDE_RATIO);
        if (i7 < round) {
            return PositionX.LEFT;
        }
        if (i7 >= i2 - round) {
            return PositionX.RIGHT;
        }
        return PositionX.CENTER;
    }

    private static PositionY getPositionY(int i2, int i7) {
        int round = Math.round(((float) i2) * SIDE_RATIO);
        if (i7 < round) {
            return PositionY.TOP;
        }
        if (i7 >= i2 - round) {
            return PositionY.BOTTOM;
        }
        return PositionY.CENTER;
    }

    private static boolean isFaceDominant(Bitmap bitmap, List<Face> list) {
        float height = (float) (bitmap.getHeight() * bitmap.getWidth());
        for (Face next : list) {
            if (next.getObjectType() == O3DPObjType.HUMAN) {
                Rect bound = next.getBound();
                float height2 = ((float) (bound.height() * bound.width())) / height;
                if (height2 > THRESHOLD_FACE_AREA_RATIO) {
                    LogUtil.i(TAG, "faceAreaRatio " + height2);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isFloating(Bitmap bitmap, List<Face> list, List<O3DPObject> list2) {
        boolean z = false;
        if (bitmap == null) {
            LogUtil.d(TAG, "No salient objects");
            return false;
        }
        int i2 = 0;
        for (Face bound : list) {
            Rect bound2 = bound.getBound();
            i2 = Math.max(i2, JNI.calcBottomDistance(bitmap, bound2.left, bound2.right));
        }
        for (O3DPObject bound3 : list2) {
            Rect bound4 = bound3.getBound();
            i2 = Math.max(i2, JNI.calcBottomDistance(bitmap, bound4.left, bound4.right));
        }
        float height = ((float) i2) / ((float) bitmap.getHeight());
        LogUtil.i(TAG, StringUtil.format("maxBottomDistance %d, bottomDistanceRatio %f", Integer.valueOf(i2), Float.valueOf(height)));
        if (height >= THRESHOLD_FLOATING_OBJECT_TO_BOTTOM_RATIO) {
            z = true;
        }
        LogUtil.i(TAG, "has floating object(s)? " + z);
        return z;
    }

    public static boolean isNoSegInCenter(List<Rect> list, int i2, int i7) {
        for (Rect next : list) {
            if (getPositionX(i2, next.centerX()) == PositionX.CENTER && getPositionY(i7, next.centerY()) == PositionY.CENTER) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSegBoundCoveringImage(Bitmap bitmap, Rect rect) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = rect.width();
        float height2 = ((float) rect.height()) / ((float) height);
        if (((float) width2) / ((float) width) > THRESHOLD_UPPER_BODY_WIDTH_RATIO || height2 > THRESHOLD_UPPER_BODY_WIDTH_RATIO) {
            return true;
        }
        return false;
    }

    private static boolean isSoloUpperBodyShot(Bitmap bitmap, List<Face> list) {
        if (bitmap == null) {
            LogUtil.d(TAG, "No salient objects");
            return false;
        } else if (list.size() != 1) {
            LogUtil.d(TAG, "face # " + list.size());
            return false;
        } else {
            int height = bitmap.getHeight();
            Rect bound = list.get(0).getBound();
            for (int i2 = height - ((int) (((float) height) * 0.01f)); i2 < height; i2++) {
                int i7 = 0;
                for (int i8 = bound.left; i8 < bound.right; i8++) {
                    if ((bitmap.getPixel(i8, i2) & -16777216) != 0) {
                        i7++;
                    }
                }
                float width = ((float) i7) / ((float) bound.width());
                if (width > THRESHOLD_UPPER_BODY_WIDTH_RATIO) {
                    LogUtil.d(TAG, "width ratio : " + width);
                    return true;
                }
            }
            return false;
        }
    }

    public static Point getFocusPointOfObjects(int i2, int i7, DepthMap depthMap, Bitmap bitmap, List<Face> list, List<O3DPObject> list2, int i8) {
        Rect frontObjectFromDepthMap;
        Rect mostHeadOnFace;
        Point point = new Point(i2 / 2, i7 / 2);
        O3DPObjType o3DPObjType = O3DPObjType.HUMAN;
        List<T> extractTargetsFromList = O3DPRectUtil.extractTargetsFromList(list, o3DPObjType);
        if (i8 == 1) {
            if (extractTargetsFromList.size() > 1 && (mostHeadOnFace = getMostHeadOnFace(extractTargetsFromList)) != null) {
                return new Point(mostHeadOnFace.centerX(), mostHeadOnFace.centerY());
            }
            if (list.size() > 1) {
                return getFocusPointOfMultiObjects(list);
            }
            if (list2.size() > 1) {
                return getFocusPointOfMultiObjects(list2);
            }
        }
        List<T> extractTargetsFromList2 = O3DPRectUtil.extractTargetsFromList(list2, o3DPObjType);
        if (!(depthMap == null || bitmap == null || i8 <= 1)) {
            if (extractTargetsFromList2.size() + extractTargetsFromList.size() > 1) {
                Rect mostHeadOnFace2 = getMostHeadOnFace(extractTargetsFromList);
                if (mostHeadOnFace2 != null) {
                    return new Point(mostHeadOnFace2.centerX(), mostHeadOnFace2.centerY());
                }
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(extractTargetsFromList);
                arrayList.addAll(extractTargetsFromList2);
                Rect frontObjectFromDepthMap2 = getFrontObjectFromDepthMap(depthMap, bitmap, arrayList);
                if (frontObjectFromDepthMap2 != null) {
                    return new Point(frontObjectFromDepthMap2.centerX(), frontObjectFromDepthMap2.centerY());
                }
                return point;
            }
        }
        if (!extractTargetsFromList.isEmpty()) {
            return getFocusPointOfFace(extractTargetsFromList);
        }
        O3DPObjType o3DPObjType2 = O3DPObjType.PET;
        List<T> extractTargetsFromList3 = O3DPRectUtil.extractTargetsFromList(list, o3DPObjType2);
        List<T> extractTargetsFromList4 = O3DPRectUtil.extractTargetsFromList(list2, o3DPObjType2);
        if (!(depthMap == null || bitmap == null || i8 <= 1)) {
            if (extractTargetsFromList4.size() + extractTargetsFromList3.size() > 1) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(extractTargetsFromList3);
                arrayList2.addAll(extractTargetsFromList4);
                Rect frontObjectFromDepthMap3 = getFrontObjectFromDepthMap(depthMap, bitmap, arrayList2);
                if (frontObjectFromDepthMap3 != null) {
                    return new Point(frontObjectFromDepthMap3.centerX(), frontObjectFromDepthMap3.centerY());
                }
                return point;
            }
        }
        if (!extractTargetsFromList3.isEmpty()) {
            return getFocusPointOfFace(extractTargetsFromList3);
        }
        if (!extractTargetsFromList2.isEmpty()) {
            return getFocusPointOfBodyObjects(extractTargetsFromList2);
        }
        if (!extractTargetsFromList4.isEmpty()) {
            return getFocusPointOfBodyObjects(extractTargetsFromList4);
        }
        if (!(depthMap == null || bitmap == null || list2.isEmpty() || (frontObjectFromDepthMap = getFrontObjectFromDepthMap(depthMap, bitmap, list2)) == null)) {
            return new Point(frontObjectFromDepthMap.centerX(), frontObjectFromDepthMap.centerY());
        }
        return point;
    }
}
