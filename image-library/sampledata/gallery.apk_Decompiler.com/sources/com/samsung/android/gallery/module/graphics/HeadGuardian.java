package com.samsung.android.gallery.module.graphics;

import android.graphics.Rect;
import android.graphics.RectF;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class HeadGuardian {
    private static final CharSequence TAG = "RecapHeadGuardian";

    public static boolean isFaceIn(String str, String str2, int i2, int i7, RectF rectF, int i8, int i10, int i11, int i12, int i13, int i14) {
        RectF rectF2 = rectF;
        int i15 = i8;
        int i16 = i10;
        int i17 = i11;
        int i18 = i12;
        int i19 = i13;
        int i20 = i14;
        boolean z = false;
        if (i15 > 0 && i16 > 0 && i17 > 0 && i18 > 0 && i19 > 0 && i20 > 0) {
            float f = (float) i17;
            float f5 = (float) i15;
            float f8 = (float) i18;
            float f10 = (float) i16;
            float min = Math.min(f / f5, f8 / f10);
            float f11 = (f - (f5 * min)) / 2.0f;
            float f12 = (f8 - (f10 * min)) / 2.0f;
            Rect rect = new Rect((int) ((rectF2.left * f5 * min) + f11), (int) ((rectF2.top * f10 * min) + f12), (int) ((rectF2.right * f5 * min) + f11), (int) ((rectF2.bottom * f10 * min) + f12));
            if (i19 <= i17 && i20 <= i18) {
                float f13 = ((float) (i17 - i19)) / 2.0f;
                float f14 = ((float) (i18 - i20)) / 2.0f;
                float f15 = ((float) i19) + f13;
                float f16 = ((float) i20) + f14;
                if (((float) rect.left) >= f13 && ((float) rect.top) >= f14 && ((float) rect.right) <= f15 && ((float) rect.bottom) <= f16) {
                    z = true;
                }
                CharSequence charSequence = TAG;
                Log.d(charSequence, str + " isFaceIn=" + z);
            }
        }
        return z;
    }

    public static boolean isImageCropSafe(RectF rectF, int i2, int i7, int i8, int i10) {
        float f = (float) i2;
        float f5 = (float) i7;
        float f8 = ((float) i8) / ((float) i10);
        String str = "Fail";
        boolean z = false;
        if (f / f5 > f8) {
            float f10 = f5 * f8;
            float f11 = ((f - f10) / 2.0f) / f;
            float f12 = (f10 / f) + f11;
            if (((double) rectF.left) * 1.2d >= ((double) f11) && ((double) rectF.right) * 0.8d <= ((double) f12)) {
                z = true;
            }
            CharSequence charSequence = TAG;
            StringBuilder sb2 = new StringBuilder();
            if (z) {
                str = "Safe";
            }
            sb2.append(str);
            sb2.append(", faceLeft(");
            sb2.append(((double) rectF.left) * 1.2d);
            sb2.append(") >= cropLeft(");
            sb2.append(f11);
            sb2.append("), faceRight(");
            sb2.append(((double) rectF.right) * 1.2d);
            sb2.append(") >= cropLeft(");
            sb2.append(f12);
            sb2.append(")");
            Log.i(charSequence, sb2.toString());
            return z;
        }
        float f13 = ((f5 - (f / f8)) / 2.0f) / f5;
        if (((double) rectF.top) * 1.1d >= ((double) f13)) {
            z = true;
        }
        CharSequence charSequence2 = TAG;
        StringBuilder sb3 = new StringBuilder();
        if (z) {
            str = "Safe";
        }
        sb3.append(str);
        sb3.append(", faceTop(");
        sb3.append(((double) rectF.top) * 1.1d);
        sb3.append(") >= cropTop(");
        sb3.append(f13);
        sb3.append(")");
        Log.i(charSequence2, sb3.toString());
        return z;
    }
}
