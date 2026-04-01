package com.samsung.android.imagetranslation.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.gson.Gson;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.imagetranslation.geometry.SmallestSurroundingRectangle;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Util {
    private static final String TAG = "Util";
    private static final List<String> rtlLanguages = new ArrayList(List.of("ar"));

    public static byte[] convertBitmapToNV21(Bitmap bitmap) {
        byte[] bArr;
        String str = TAG;
        LTTLogger.d(str, "convertBitmapToNV21: E" + bitmap.getConfig());
        if (!bitmap.isRecycled()) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            LTTLogger.d(str, "[convertBitmapToNV21] size: " + width + " x " + height);
            int i2 = width * height;
            int[] iArr = new int[i2];
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            bArr = new byte[((i2 * 3) / 2)];
            encodeYUV420SP(bArr, iArr, width, height);
        } else {
            bArr = null;
        }
        LTTLogger.d(str, "convertBitmapToNV21: X");
        return bArr;
    }

    public static String convertToJson(LttOcrResult lttOcrResult) {
        return new Gson().toJson((Object) lttOcrResult);
    }

    private static void encodeYUV420SP(byte[] bArr, int[] iArr, int i2, int i7) {
        int i8 = i2;
        int i10 = i7;
        int i11 = i8 * i10;
        int i12 = 0;
        int i13 = 0;
        for (int i14 = 0; i14 < i10; i14++) {
            int i15 = 0;
            while (i15 < i8) {
                int i16 = iArr[i13];
                int i17 = (16711680 & i16) >> 16;
                int i18 = (65280 & i16) >> 8;
                int i19 = ScoverState.TYPE_NFC_SMART_COVER;
                int i20 = i16 & ScoverState.TYPE_NFC_SMART_COVER;
                int i21 = ((((i20 * 25) + ((i18 * 129) + (i17 * 66))) + 128) >> 8) + 16;
                int i22 = ((((i20 * 112) + ((i17 * -38) - (i18 * 74))) + 128) >> 8) + 128;
                int i23 = (((((i17 * 112) - (i18 * 94)) - (i20 * 18)) + 128) >> 8) + 128;
                int i24 = i12 + 1;
                if (i21 < 0) {
                    i21 = 0;
                } else if (i21 > 255) {
                    i21 = 255;
                }
                bArr[i12] = (byte) i21;
                if (i14 % 2 == 0 && i13 % 2 == 0) {
                    int i25 = i11 + 1;
                    if (i23 < 0) {
                        i23 = 0;
                    } else if (i23 > 255) {
                        i23 = 255;
                    }
                    bArr[i11] = (byte) i23;
                    i11 += 2;
                    if (i22 < 0) {
                        i19 = 0;
                    } else if (i22 <= 255) {
                        i19 = i22;
                    }
                    bArr[i25] = (byte) i19;
                }
                i13++;
                i15++;
                i12 = i24;
            }
        }
    }

    public static int[] getDeviceResolution(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        int[] iArr = {displayMetrics.widthPixels, displayMetrics.heightPixels};
        String str = TAG;
        LTTLogger.d(str, "Device Width - " + iArr[0] + " Height - " + iArr[1]);
        return iArr;
    }

    public static float getDpToPxFactor(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static Point[] getExtendedPolyValues(Point[] pointArr, int i2, int i7, int i8, boolean z) {
        int i10 = i2;
        int i11 = i7;
        int i12 = i8;
        Point[] pointArr2 = new Point[4];
        if (z) {
            if (i11 == 0) {
                i11 = 2;
            } else if (i11 == 2) {
                i11 = 0;
            }
        }
        int i13 = -1;
        if (i11 == 0) {
            double sqrt = Math.sqrt(Math.pow((double) (pointArr[0].y - pointArr[1].y), 2.0d) + Math.pow((double) (pointArr[0].x - pointArr[1].x), 2.0d));
            double sqrt2 = Math.sqrt(Math.pow((double) (pointArr[2].y - pointArr[3].y), 2.0d) + Math.pow((double) (pointArr[2].x - pointArr[3].x), 2.0d));
            if (i12 == 0 || i12 == 90) {
                i13 = 1;
            } else if (!(i12 == 180 || i12 == 270)) {
                i13 = 0;
            }
            Point point = pointArr[0];
            pointArr2[0] = new Point(point.x, point.y);
            Point point2 = new Point();
            pointArr2[1] = point2;
            Point point3 = pointArr[1];
            int i14 = point3.x;
            Point point4 = pointArr[0];
            Point[] pointArr3 = pointArr2;
            double d = sqrt2;
            double d2 = (double) i10;
            point2.x = (int) (((((double) ((i14 - point4.x) * i13)) / sqrt) * d2) + ((double) i14));
            int i15 = point3.y;
            point2.y = (int) (((((double) ((i15 - point4.y) * i13)) / sqrt) * d2) + ((double) i15));
            Point point5 = new Point();
            pointArr3[2] = point5;
            Point point6 = pointArr[2];
            int i16 = point6.x;
            Point point7 = pointArr[3];
            point5.x = (int) (((((double) ((i16 - point7.x) * i13)) / d) * d2) + ((double) i16));
            int i17 = point6.y;
            point5.y = (int) (((((double) ((i17 - point7.y) * i13)) / d) * d2) + ((double) i17));
            Point point8 = pointArr[3];
            pointArr3[3] = new Point(point8.x, point8.y);
            return pointArr3;
        }
        Point[] pointArr4 = pointArr2;
        if (i11 == 2) {
            double sqrt3 = Math.sqrt(Math.pow((double) (pointArr[0].y - pointArr[1].y), 2.0d) + Math.pow((double) (pointArr[0].x - pointArr[1].x), 2.0d));
            double sqrt4 = Math.sqrt(Math.pow((double) (pointArr[2].y - pointArr[3].y), 2.0d) + Math.pow((double) (pointArr[2].x - pointArr[3].x), 2.0d));
            if (!(i12 == 0 || i12 == 90)) {
                i13 = (i12 == 180 || i12 == 270) ? 1 : 0;
            }
            Point point9 = pointArr[1];
            pointArr4[1] = new Point(point9.x, point9.y);
            Point point10 = pointArr[2];
            pointArr4[2] = new Point(point10.x, point10.y);
            Point point11 = new Point();
            pointArr4[0] = point11;
            Point point12 = pointArr[0];
            int i18 = point12.x;
            Point point13 = pointArr[1];
            double d3 = (double) i10;
            point11.x = (int) (((((double) ((point13.x - i18) * i13)) / sqrt3) * d3) + ((double) i18));
            int i19 = point12.y;
            point11.y = (int) (((((double) ((point13.y - i19) * i13)) / sqrt3) * d3) + ((double) i19));
            Point point14 = new Point();
            pointArr4[3] = point14;
            Point point15 = pointArr[3];
            int i20 = point15.x;
            Point point16 = pointArr[2];
            point14.x = (int) (((((double) ((point16.x - i20) * i13)) / sqrt4) * d3) + ((double) i20));
            int i21 = point15.y;
            point14.y = (int) (((((double) ((point16.y - i21) * i13)) / sqrt4) * d3) + ((double) i21));
            return pointArr4;
        }
        int i22 = i10 / 2;
        double sqrt5 = Math.sqrt(Math.pow((double) (pointArr[0].y - pointArr[1].y), 2.0d) + Math.pow((double) (pointArr[0].x - pointArr[1].x), 2.0d));
        double sqrt6 = Math.sqrt(Math.pow((double) (pointArr[2].y - pointArr[3].y), 2.0d) + Math.pow((double) (pointArr[2].x - pointArr[3].x), 2.0d));
        int i23 = (i12 == 0 || i12 == 90) ? 1 : (i12 == 180 || i12 == 270) ? -1 : 0;
        if (!(i12 == 0 || i12 == 90)) {
            i13 = (i12 == 180 || i12 == 270) ? 1 : 0;
        }
        Point point17 = new Point();
        pointArr4[0] = point17;
        Point point18 = pointArr[0];
        int i24 = point18.x;
        Point point19 = pointArr[1];
        int i25 = i23;
        double d5 = (double) i22;
        point17.x = (int) (((((double) ((point19.x - i24) * i13)) / sqrt5) * d5) + ((double) i24));
        int i26 = point18.y;
        point17.y = (int) (((((double) ((point19.y - i26) * i13)) / sqrt5) * d5) + ((double) i26));
        Point point20 = new Point();
        pointArr4[1] = point20;
        Point point21 = pointArr[1];
        int i27 = point21.x;
        Point point22 = pointArr[0];
        point20.x = (int) (((((double) ((i27 - point22.x) * i25)) / sqrt5) * d5) + ((double) i27));
        int i28 = point21.y;
        point20.y = (int) (((((double) ((i28 - point22.y) * i25)) / sqrt5) * d5) + ((double) i28));
        Point point23 = new Point();
        pointArr4[2] = point23;
        Point point24 = pointArr[2];
        int i29 = point24.x;
        Point point25 = pointArr[3];
        point23.x = (int) (((((double) ((i29 - point25.x) * i25)) / sqrt6) * d5) + ((double) i29));
        int i30 = point24.y;
        point23.y = (int) (((((double) ((i30 - point25.y) * i25)) / sqrt6) * d5) + ((double) i30));
        Point point26 = new Point();
        pointArr4[3] = point26;
        Point point27 = pointArr[3];
        int i31 = point27.x;
        Point point28 = pointArr[2];
        point26.x = (int) (((((double) ((point28.x - i31) * i13)) / sqrt6) * d5) + ((double) i31));
        int i32 = point27.y;
        point26.y = (int) (((((double) ((point28.y - i32) * i13)) / sqrt6) * d5) + ((double) i32));
        return pointArr4;
    }

    public static int getOrientation(Point[] pointArr) {
        ArrayList arrayList = new ArrayList();
        for (Point point : pointArr) {
            arrayList.add(new PointF((float) point.x, (float) point.y));
        }
        Point[] sortPoint = SmallestSurroundingRectangle.sortPoint(arrayList);
        int i2 = 0;
        while (true) {
            if (i2 >= 4) {
                i2 = 0;
                break;
            }
            Point point2 = sortPoint[0];
            int i7 = point2.x;
            Point point3 = pointArr[i2];
            if (i7 == point3.x && point2.y == point3.y) {
                break;
            }
            i2++;
        }
        if (i2 == 0) {
            return 0;
        }
        if (i2 == 1) {
            return 270;
        }
        if (i2 != 2) {
            return 90;
        }
        return MOCRLang.KHMER;
    }

    public static boolean isJarAndNativeVersionCompatible(String str, String str2) {
        if (str == null || str.isEmpty() || str2 == null) {
            return false;
        }
        return str.split("\\.")[0].equals(str2.split("\\.")[0]);
    }

    public static boolean isLineOnTopOrBottom(Point[] pointArr, Point[] pointArr2, Point[] pointArr3, Point[] pointArr4) {
        for (int i2 = 0; i2 < 4; i2++) {
            Point point = pointArr3[i2];
            int i7 = point.x;
            Point point2 = pointArr4[i2];
            if (i7 != point2.x || point.y != point2.y) {
                return false;
            }
        }
        if (isPolyValueIntersecting(pointArr, pointArr2)) {
            return true;
        }
        return false;
    }

    public static boolean isPointInsidePolygon(Point point, Point[] pointArr) {
        int length = pointArr.length;
        double d = (double) point.x;
        double d2 = (double) point.y;
        boolean z = false;
        Point point2 = pointArr[0];
        int i2 = 1;
        while (i2 <= length) {
            Point point3 = pointArr[i2 % length];
            if (d2 > ((double) Math.min(point2.y, point3.y)) && d2 <= ((double) Math.max(point2.y, point3.y)) && d <= ((double) Math.max(point2.x, point3.x))) {
                int i7 = point2.y;
                int i8 = point3.x;
                int i10 = point2.x;
                double d3 = (((d2 - ((double) i7)) * ((double) (i8 - i10))) / ((double) (point3.y - i7))) + ((double) i10);
                if (i10 == i8 || d <= d3) {
                    z = !z;
                }
            }
            i2++;
            point2 = point3;
        }
        return z;
    }

    private static boolean isPolyAntiClockwise(Point[] pointArr) {
        int length = pointArr.length;
        long j2 = 0;
        int i2 = 0;
        while (i2 < length) {
            int i7 = i2 + 1;
            Point point = pointArr[i7 % length];
            int i8 = point.x;
            Point point2 = pointArr[i2];
            j2 += ((long) (i8 - point2.x)) * ((long) (point.y + point2.y));
            i2 = i7;
        }
        if (j2 > 0) {
            return true;
        }
        return false;
    }

    public static boolean isPolyOutOfImageBound(Point[] pointArr, Point[] pointArr2, int i2, int i7, int i8) {
        int i10 = (i7 * i8) / 100;
        int i11 = 100 - i8;
        int i12 = (i7 * i11) / 100;
        int i13 = (i8 * i2) / 100;
        int i14 = (i2 * i11) / 100;
        for (int i15 = 0; i15 < 4; i15++) {
            Point point = pointArr[i15];
            Point point2 = pointArr2[i15];
            int i16 = point.x;
            int i17 = point2.x;
            if (i16 != i17 && (i17 < i13 || i17 > i14)) {
                return true;
            }
            int i18 = point.y;
            int i19 = point2.y;
            if (i18 != i19 && (i19 < i10 || i19 > i12)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPolyValueIntersecting(Point[] pointArr, Point[] pointArr2) {
        for (Point isPointInsidePolygon : pointArr) {
            if (isPointInsidePolygon(isPointInsidePolygon, pointArr2)) {
                return true;
            }
        }
        for (Point isPointInsidePolygon2 : pointArr2) {
            if (isPointInsidePolygon(isPointInsidePolygon2, pointArr)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRTLLanguage(Point[] pointArr) {
        return isPolyAntiClockwise(pointArr);
    }

    public static String removeMoreThanOneSpaceBetweenWords(String str) {
        return Pattern.compile("\\s+").matcher(str).replaceAll(" ");
    }

    public static boolean isRTLLanguage(String str) {
        return rtlLanguages.contains(str);
    }
}
