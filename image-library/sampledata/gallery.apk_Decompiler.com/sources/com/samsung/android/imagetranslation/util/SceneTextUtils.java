package com.samsung.android.imagetranslation.util;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.geometry.SmallestSurroundingRectangle;
import com.samsung.android.imagetranslation.jni.SceneText;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SceneTextUtils {
    private static final int MAX_PADDING = 15;
    private static final float PADDING_RATIO = 0.1f;
    private static final String TAG = "SceneTextUtils";
    static int[] adjustmentX = {-1, 1, 1, -1};
    static int[] adjustmentY = {-1, -1, 1, 1};
    private static boolean isCurrencyConverted = false;
    public static boolean use_text_rotation = true;

    private static double calcSlopeWithSimple(SceneText sceneText) {
        if (!(sceneText.getComponents() == null || sceneText.getComponents().size() == 0)) {
            SceneText sceneText2 = sceneText.getComponents().get(0);
            SceneText sceneText3 = sceneText.getComponents().get(sceneText.getComponents().size() - 1);
            if (!(sceneText2.getComponents() == null || sceneText2.getComponents().size() == 0 || sceneText3.getComponents() == null || sceneText3.getComponents().size() == 0 || sceneText2.getComponents().get(0).getPoly() == null || sceneText3.getComponents().get(sceneText3.getComponents().size() - 1).getPoly() == null)) {
                Point ceneterPoint = SceneTextUtil.getCeneterPoint(sceneText2.getComponents().get(0).getPoly());
                Point ceneterPoint2 = SceneTextUtil.getCeneterPoint(sceneText3.getComponents().get(sceneText3.getComponents().size() - 1).getPoly());
                if (ceneterPoint.equals(ceneterPoint2.x, ceneterPoint2.y)) {
                    return MapUtil.INVALID_LOCATION;
                }
                int i2 = ceneterPoint.x;
                int i7 = ceneterPoint2.x;
                if (i2 == i7) {
                    return Double.MAX_VALUE;
                }
                return (((double) (ceneterPoint2.y - ceneterPoint.y)) * 1.0d) / ((double) (i7 - i2));
            }
        }
        return MapUtil.INVALID_LOCATION;
    }

    public static boolean containsOnlyNumbersAndSymbols(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.compile("^(?=.*[\\d\\p{S}\\p{Punct}])[\\d\\p{S}\\p{Punct}\\s]*[a-zA-Z]?[\\d\\p{S}\\p{Punct}\\s]*$").matcher(str).matches();
    }

    public static void convertCoordiate(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList, Matrix matrix, int i2) {
        Iterator<SceneText> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            SceneText next = it.next();
            if (next.getComponents() != null) {
                convertCoordiate(next.getComponents(), matrix, i2);
            }
            next.setPoly(SceneTextUtil.rotatePoly(next.getPoly(), next.getValue(), matrix, i2));
        }
    }

    public static void detectVerticalLine(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList) {
        if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() != 0) {
            Iterator<SceneText> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                SceneText next = it.next();
                if (next.getComponents() != null) {
                    Iterator<SceneText> it2 = next.getComponents().iterator();
                    while (it2.hasNext()) {
                        SceneText next2 = it2.next();
                        if (next2 != null) {
                            next2.setVerticalType(isVerticalTypeLine(next2));
                        }
                    }
                }
            }
        }
    }

    public static boolean getCurrencyState() {
        return isCurrencyConverted;
    }

    private static int getDynamicPadding(Point[] pointArr) {
        double pow = Math.pow((double) (pointArr[1].x - pointArr[0].x), 2.0d);
        return Math.min((int) (((float) Math.min((int) Math.sqrt(Math.pow((double) (pointArr[3].y - pointArr[0].y), 2.0d) + Math.pow((double) (pointArr[3].x - pointArr[0].x), 2.0d)), (int) Math.sqrt(Math.pow((double) (pointArr[1].y - pointArr[0].y), 2.0d) + pow))) * 0.1f), 15);
    }

    private static double getOrientationWithPoly(Point[] pointArr) {
        if (pointArr.length != 4) {
            return MapUtil.INVALID_LOCATION;
        }
        Point point = pointArr[0];
        Point point2 = pointArr[1];
        Point point3 = new Point();
        int i2 = point2.x - point.x;
        point3.x = i2;
        point3.y = point2.y - point.y;
        double degrees = Math.toDegrees(Math.acos(((double) point3.x) / Math.sqrt(Math.pow((double) point3.y, 2.0d) + Math.pow((double) i2, 2.0d))));
        if (point3.y < 0) {
            degrees = 360.0d - degrees;
        }
        return 360.0d - degrees;
    }

    private static boolean isPolyOutOfBound(Point[] pointArr, int i2, int i7) {
        int i8;
        for (Point point : pointArr) {
            int i10 = point.x;
            if (i10 < 0 || (i8 = point.y) < 0 || i10 > i7 || i8 > i2) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRectIntersecting(Point[] pointArr, Point[] pointArr2) {
        return Util.isPolyValueIntersecting(pointArr, pointArr2);
    }

    public static boolean isVerticalTypeLine(SceneText sceneText) {
        int i2;
        if (sceneText.getType() != SceneText.SceneTextType.LINE) {
            LTTLogger.d(TAG, "NOT LINE");
            return false;
        }
        if (sceneText.getComponents() != null) {
            Iterator<SceneText> it = sceneText.getComponents().iterator();
            i2 = 0;
            while (it.hasNext()) {
                SceneText next = it.next();
                if (next.getComponents() != null) {
                    i2 += next.getComponents().size();
                }
            }
        } else {
            i2 = 0;
        }
        if (i2 < 2 || Math.abs(calcSlopeWithSimple(sceneText)) <= 3.0d) {
            return false;
        }
        sceneText.getValue();
        return true;
    }

    public static CopyOnWriteArrayList<SceneText> makeOneParagraph(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList) {
        CopyOnWriteArrayList<SceneText> copyOnWriteArrayList2 = copyOnWriteArrayList;
        CopyOnWriteArrayList<SceneText> copyOnWriteArrayList3 = new CopyOnWriteArrayList<>();
        if (copyOnWriteArrayList2 == null) {
            return null;
        }
        int i2 = 0;
        if (copyOnWriteArrayList2.size() == 1) {
            copyOnWriteArrayList3.add(copyOnWriteArrayList2.get(0).clone());
            return copyOnWriteArrayList3;
        }
        SceneText sceneText = new SceneText();
        sceneText.setType(SceneText.SceneTextType.PARAGRAPH);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.setLength(0);
        ArrayList arrayList = new ArrayList();
        Iterator<SceneText> it = copyOnWriteArrayList2.iterator();
        int i7 = Integer.MIN_VALUE;
        int i8 = Integer.MAX_VALUE;
        int i10 = Integer.MAX_VALUE;
        int i11 = Integer.MIN_VALUE;
        while (it.hasNext()) {
            SceneText next = it.next();
            Iterator<SceneText> it2 = next.getComponents().iterator();
            while (it2.hasNext()) {
                SceneText next2 = it2.next();
                sceneText.setComponent(next2.clone());
                stringBuffer.append(10);
                stringBuffer.append(next2.getValue());
            }
            Point[] poly = next.getPoly();
            int length = poly.length;
            for (int i12 = i2; i12 < length; i12++) {
                Point point = poly[i12];
                i8 = Math.min(i8, point.x);
                i7 = Math.max(i7, point.x);
                i10 = Math.min(i10, point.y);
                i11 = Math.max(i11, point.y);
            }
            Iterator<String> it3 = next.getLanguages().iterator();
            while (it3.hasNext()) {
                arrayList.add(it3.next());
            }
            i2 = 0;
        }
        sceneText.setPoly(new Point[]{new Point(i8, i10), new Point(i7, i10), new Point(i7, i11), new Point(i8, i11)});
        sceneText.setDeviceOrientation(copyOnWriteArrayList2.get(0).getDeviceOrientation());
        sceneText.setLanguages(arrayList);
        if (stringBuffer.length() > 1) {
            sceneText.setValue(stringBuffer.toString().substring(1));
        } else {
            LTTLogger.e(TAG, "Unexpected value length : " + stringBuffer.toString());
            sceneText.setValue("");
        }
        copyOnWriteArrayList3.add(sceneText);
        return copyOnWriteArrayList3;
    }

    public static boolean repairSceneTexts(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList) {
        Iterator<SceneText> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            if (!it.next().repair()) {
                return false;
            }
        }
        return true;
    }

    public static void updatePadding(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList, int i2, int i7) {
        Iterator<SceneText> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            SceneText next = it.next();
            updatePoly(next, getDynamicPadding(next.getPoly()), next.getPoly());
        }
        validatePadding(copyOnWriteArrayList, i2, i7);
    }

    private static void updatePoly(SceneText sceneText, int i2, Point[] pointArr) {
        ArrayList arrayList = new ArrayList();
        for (Point point : pointArr) {
            arrayList.add(new PointF((float) point.x, (float) point.y));
        }
        Point[] sortPoint = SmallestSurroundingRectangle.sortPoint(arrayList);
        Point[] pointArr2 = new Point[4];
        for (int i7 = 0; i7 < pointArr.length; i7++) {
            Point point2 = pointArr[i7];
            int i8 = point2.x;
            int i10 = point2.y;
            for (int i11 = 0; i11 < sortPoint.length; i11++) {
                Point point3 = sortPoint[i11];
                if (i8 == point3.x && i10 == point3.y) {
                    pointArr2[i7] = new Point((adjustmentX[i11] * i2) + i8, (adjustmentY[i11] * i2) + i10);
                }
            }
        }
        sceneText.setPaddedBoxPoly(pointArr2);
    }

    public static void updateSceneTextRect(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList) {
        LTTLogger.d(TAG, "updateSceneTextRect: E");
        for (int i2 = 0; i2 < copyOnWriteArrayList.size(); i2++) {
            SceneText sceneText = copyOnWriteArrayList.get(i2);
            if (sceneText.getComponents() != null) {
                for (int i7 = 0; i7 < sceneText.getComponents().size(); i7++) {
                    SceneText sceneText2 = sceneText.getComponents().get(i7);
                    if (sceneText2.getComponents() != null) {
                        for (int i8 = 0; i8 < sceneText2.getComponents().size(); i8++) {
                            SceneText sceneText3 = sceneText2.getComponents().get(i8);
                            if (sceneText3.getComponents() != null && sceneText3.getComponents().size() > 0) {
                                sceneText3.setPoly(sceneText3.getComponents().get(0).getPoly());
                                for (int i10 = 0; i10 < sceneText3.getComponents().size(); i10++) {
                                    sceneText3.setPoly(SmallestSurroundingRectangle.computeCorners(sceneText3.getPoly(), sceneText3.getComponents().get(i10).getPoly()));
                                }
                            }
                            if (i8 == 0) {
                                sceneText2.setPoly(sceneText3.getPoly());
                            } else {
                                sceneText2.setPoly(SmallestSurroundingRectangle.computeCorners(sceneText2.getPoly(), sceneText3.getPoly()));
                            }
                        }
                    }
                    if (i7 == 0) {
                        sceneText.setPoly(sceneText2.getPoly());
                    } else {
                        sceneText.setPoly(SmallestSurroundingRectangle.computeCorners(sceneText.getPoly(), sceneText2.getPoly()));
                    }
                }
            }
        }
    }

    public static void updateSceneTextRotation(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList, int i2) {
        double d;
        SceneText.TextOrientation textOrientation;
        if (copyOnWriteArrayList != null && use_text_rotation) {
            Iterator<SceneText> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                SceneText next = it.next();
                Point[] pointArr = (Point[]) next.getPoly().clone();
                StringBuffer stringBuffer = new StringBuffer();
                for (Point point : pointArr) {
                    stringBuffer.append(point.x + ArcCommonLog.TAG_COMMA + point.y + ArcCommonLog.TAG_COMMA);
                }
                double orientationWithPoly = getOrientationWithPoly(pointArr);
                ArrayList arrayList = new ArrayList(Arrays.asList(new String[]{"ar", "ur", "iw", "fa", "ps", "ckb"}));
                SceneText.SceneTextType type = next.getType();
                SceneText.SceneTextType sceneTextType = SceneText.SceneTextType.PARAGRAPH;
                if ((type.equals(sceneTextType) || next.getType().equals(SceneText.SceneTextType.WORD)) && !next.getLanguages().isEmpty() && arrayList.contains(next.getLanguages().get(0))) {
                    orientationWithPoly += 180.0d;
                }
                SceneText.SceneTextType type2 = next.getType();
                SceneText.SceneTextType sceneTextType2 = SceneText.SceneTextType.LINE;
                if (type2.equals(sceneTextType2) && next.getComponents() != null && !next.getComponents().isEmpty() && !next.getComponents().get(0).getLanguages().isEmpty() && arrayList.contains(next.getComponents().get(0).getLanguages().get(0)) && next.getComponents().size() > 1) {
                    orientationWithPoly += 180.0d;
                }
                double d2 = ((orientationWithPoly + 360.0d) + ((double) (i2 - 90))) % 360.0d;
                if (i2 == 90 || i2 == 270) {
                    d = d2 - 26.190476190476186d;
                } else {
                    d = d2 + 26.190476190476186d;
                }
                if (d < 45.0d || d >= 315.0d) {
                    textOrientation = SceneText.TextOrientation.CCW_0;
                } else if (d >= 45.0d && d < 135.0d) {
                    textOrientation = SceneText.TextOrientation.CCW_90;
                } else if (d < 135.0d || d >= 225.0d) {
                    textOrientation = SceneText.TextOrientation.CCW_270;
                } else {
                    textOrientation = SceneText.TextOrientation.CCW_180;
                }
                next.setOrient(textOrientation);
                if (next.getType().equals(sceneTextType) || next.getType().equals(sceneTextType2)) {
                    LTTLogger.d(TAG, "Type = [" + next.getType() + "], Rot = " + SceneText.TextOrientation.values()[next.getOrient()] + ", angle = " + d + ", Text = " + next.getValue() + ", Poly = " + stringBuffer);
                }
                updateSceneTextRotation(next.getComponents(), i2);
            }
        }
    }

    private static void validatePadding(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList, int i2, int i7) {
        int size = copyOnWriteArrayList.size();
        for (int i8 = 0; i8 < size; i8++) {
            int dynamicPadding = getDynamicPadding(copyOnWriteArrayList.get(i8).getPoly());
            LTTLogger.d(TAG, "Initial Padding : " + dynamicPadding);
            for (int i10 = 0; i10 < size; i10++) {
                if (i8 != i10 || size == 1) {
                    Point[] paddedBoxPoly = copyOnWriteArrayList.get(i8).getPaddedBoxPoly();
                    Point[] paddedBoxPoly2 = copyOnWriteArrayList.get(i10).getPaddedBoxPoly();
                    while (dynamicPadding > 0 && ((size != 1 && isRectIntersecting(paddedBoxPoly, paddedBoxPoly2)) || isPolyOutOfBound(paddedBoxPoly, i2, i7))) {
                        dynamicPadding--;
                        updatePoly(copyOnWriteArrayList.get(i8), dynamicPadding, copyOnWriteArrayList.get(i8).getPoly());
                        paddedBoxPoly = copyOnWriteArrayList.get(i8).getPaddedBoxPoly();
                    }
                }
            }
            String str = TAG;
            Log.d(str, "Current block : " + copyOnWriteArrayList.get(i8).getValue());
            Log.d(str, "validatePadding: Current Padding - " + dynamicPadding);
        }
    }
}
