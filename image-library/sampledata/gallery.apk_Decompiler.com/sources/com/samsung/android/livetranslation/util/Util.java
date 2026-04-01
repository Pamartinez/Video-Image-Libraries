package com.samsung.android.livetranslation.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.gson.Gson;
import com.samsung.android.imagetranslation.data.LttOcrResult;
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
        LTTLogger.i(str, "convertBitmapToNV21: X");
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

    public static long getTimeInMilliSecond() {
        return System.currentTimeMillis();
    }

    public static boolean isJarAndNativeVersionCompatible(String str, String str2) {
        if (str == null || str.isEmpty() || str2 == null) {
            return false;
        }
        return str.split("\\.")[0].equals(str2.split("\\.")[0]);
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

    public static boolean isRTLLanguage(String str) {
        return rtlLanguages.contains(str);
    }

    public static String removeMoreThanOneSpaceBetweenWords(String str) {
        return Pattern.compile("\\s+").matcher(str).replaceAll(" ");
    }
}
