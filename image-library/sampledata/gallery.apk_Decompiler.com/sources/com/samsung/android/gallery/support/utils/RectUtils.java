package com.samsung.android.gallery.support.utils;

import A.a;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RectUtils {
    private static final float[] FACE_EXPAND_RATIO = {0.6f, 0.4f, 0.5f};
    private static final boolean IS_RTL = Features.isEnabled(Features.IS_RTL);

    private static int calcFaceExpand(int i2, int i7) {
        return (int) (((float) i2) * FACE_EXPAND_RATIO[i7]);
    }

    public static Rect createCenterCropRect(int i2, int i7, int i8, int i10) {
        float f = (float) i8;
        float f5 = ((float) i2) / f;
        float f8 = (float) i10;
        float f10 = ((float) i7) / f8;
        int i11 = (f5 > f10 ? 1 : (f5 == f10 ? 0 : -1));
        if (i11 == 0) {
            return new Rect(0, 0, i2, i7);
        }
        if (i11 > 0) {
            int ceil = (int) Math.ceil((double) (((float) (i2 - ((int) Math.floor((double) (f * f10))))) / 2.0f));
            return new Rect(ceil, 0, i2 - ceil, i7);
        }
        int ceil2 = (int) Math.ceil((double) (((float) (i7 - ((int) Math.floor((double) (f8 * f5))))) / 2.0f));
        return new Rect(0, ceil2, i2, i7 - ceil2);
    }

    public static boolean equalsRectF(RectF rectF, RectF rectF2) {
        return toString(rectF).equals(toString(rectF2));
    }

    public static Rect expandRectWithLimit(int i2, int i7, Rect rect) {
        Rect rect2 = new Rect();
        int i8 = rect.left;
        int i10 = rect.right;
        if (i8 < i2 - i10) {
            int max = Math.max(0, i8 - rect.width());
            rect2.left = max;
            rect2.right = Math.min(i2, (rect.right + rect.left) - max);
        } else {
            int min = Math.min(i2, rect.width() + i10);
            rect2.right = min;
            rect2.left = Math.max(0, rect.left - (min - rect.right));
        }
        int i11 = rect.top;
        int i12 = rect.bottom;
        if (i11 < i7 - i12) {
            int max2 = Math.max(0, i11 - rect.height());
            rect2.top = max2;
            rect2.bottom = Math.min(i7, (rect.bottom + rect.top) - max2);
            return rect2;
        }
        int height = (i7 - i12) - rect.height();
        if (height >= 0) {
            i2 = rect.bottom + height;
        }
        rect2.bottom = i2;
        int min2 = Math.min(i7, rect.height() + rect.bottom);
        rect2.bottom = min2;
        rect2.top = Math.max(0, rect.top - (min2 - rect.bottom));
        return rect2;
    }

    public static Rect expandRectWithLimitForFace(int i2, int i7, Rect rect, int i8) {
        float f;
        Rect rect2 = new Rect();
        int i10 = rect.left;
        int i11 = rect.right;
        if (i10 < i2 - i11) {
            int max = Math.max(0, i10 - calcFaceExpand(rect.width(), 2));
            rect2.left = max;
            rect2.right = Math.min(i2, (rect.right + rect.left) - max);
        } else {
            int min = Math.min(i2, i11 + calcFaceExpand(rect.width(), 2));
            rect2.right = min;
            rect2.left = Math.max(0, rect.left - (min - rect.right));
        }
        int i12 = rect.top;
        int i13 = rect.bottom;
        if (i12 < i7 - i13) {
            int max2 = Math.max(0, i12 - calcFaceExpand(rect.height(), 0));
            rect2.top = max2;
            rect2.bottom = Math.min(i7, rect.bottom + getExtraBottom(rect.top - max2, rect.height(), i8));
            return rect2;
        }
        int extraBottom = (i7 - i13) - getExtraBottom(rect.height(), rect.height(), i8);
        if (extraBottom >= 0) {
            i2 = rect.bottom + extraBottom;
        }
        rect2.bottom = i2;
        int min2 = Math.min(i7, rect.bottom + calcFaceExpand(rect.height(), 1));
        rect2.bottom = min2;
        float[] fArr = FACE_EXPAND_RATIO;
        if (i8 == 2) {
            f = fArr[1] + 0.4f;
        } else {
            f = fArr[1];
        }
        rect2.top = Math.max(0, rect.top - ((int) ((FACE_EXPAND_RATIO[0] / f) * ((float) (min2 - rect.bottom)))));
        return rect2;
    }

    public static RectF getCenterCropRect(RectF rectF) {
        float f;
        RectF rectF2 = new RectF(rectF);
        float height = (rectF.height() - rectF.width()) / 2.0f;
        if (height < 0.0f) {
            f = -height;
        } else {
            f = 0.0f;
        }
        if (height <= 0.0f) {
            height = 0.0f;
        }
        rectF2.inset(f, height);
        return rectF2;
    }

    public static int getEnd(Rect rect) {
        if (rect == null) {
            return 0;
        }
        if (IS_RTL) {
            return rect.left;
        }
        return rect.right;
    }

    private static int getExtraBottom(int i2, int i7, int i8) {
        int calcFaceExpand = calcFaceExpand(i2, 1);
        if (i8 == 2) {
            return calcFaceExpand + ((int) (((float) i7) * 0.4f));
        }
        return calcFaceExpand;
    }

    public static RectF getMirrorHorizontalRectF(RectF rectF, int i2) {
        if (rectF == null) {
            return null;
        }
        if (i2 == 0 || i2 == 180) {
            return new RectF(1.0f - rectF.right, rectF.top, 1.0f - rectF.left, rectF.bottom);
        }
        return new RectF(rectF.left, 1.0f - rectF.bottom, rectF.right, 1.0f - rectF.top);
    }

    public static Rect getRotatedRect(Rect rect, int i2, int i7, int i8) {
        if (rect == null || i8 == 0) {
            return rect;
        }
        if (i8 == 90) {
            return new Rect(i7 - rect.bottom, rect.left, i7 - rect.top, rect.right);
        }
        if (i8 == 180) {
            return new Rect(i2 - rect.right, i7 - rect.bottom, i2 - rect.left, i7 - rect.top);
        }
        if (i8 != 270) {
            return rect;
        }
        return new Rect(rect.top, i2 - rect.right, rect.bottom, i2 - rect.left);
    }

    public static RectF getRotatedRectFRatio(RectF rectF, int i2) {
        if (isValidRect(rectF) && i2 != 0) {
            if (i2 == 90) {
                return new RectF(1.0f - rectF.bottom, rectF.left, 1.0f - rectF.top, rectF.right);
            }
            if (i2 == 180) {
                return new RectF(1.0f - rectF.right, 1.0f - rectF.bottom, 1.0f - rectF.left, 1.0f - rectF.top);
            }
            if (i2 == 270) {
                return new RectF(rectF.top, 1.0f - rectF.right, rectF.bottom, 1.0f - rectF.left);
            }
        }
        return rectF;
    }

    public static ArrayList<RectF> getRotatedRectList(ArrayList<RectF> arrayList, int i2) {
        if (arrayList == null || arrayList.isEmpty()) {
            return arrayList;
        }
        return new ArrayList<>((Collection) arrayList.stream().filter(new C0680s(8)).map(new F(i2, 1)).collect(Collectors.toList()));
    }

    public static Rect getSmartCropRect(Bitmap bitmap, RectF rectF) {
        return getSmartCropRect(bitmap, rectF, 0);
    }

    public static Rect getSmartCropRectWithLimit(RectF rectF, int i2, int i7) {
        return expandRectWithLimit(i2, i7, getSmartCropRect(rectF, i2, i7));
    }

    public static int getStart(Rect rect) {
        if (rect == null) {
            return 0;
        }
        if (IS_RTL) {
            return rect.right;
        }
        return rect.left;
    }

    public static boolean hasValue(RectF rectF) {
        if (rectF == null || rectF.left == 0.0f || rectF.top == 0.0f || rectF.right == 0.0f || rectF.bottom == 0.0f) {
            return false;
        }
        return true;
    }

    public static boolean isStretchable(RectF rectF, int i2, int i7, int i8) {
        if (rectF == null || rectF.width() <= 0.0f) {
            return true;
        }
        if (!(i8 == 90 || i8 == 270)) {
            int i10 = i7;
            i7 = i2;
            i2 = i10;
        }
        if ((rectF.height() * ((float) i2)) / (rectF.width() * ((float) i7)) < 0.99f) {
            return true;
        }
        return false;
    }

    public static boolean isValidRect(Rect rect) {
        return rect != null && rect.width() > 0 && rect.height() > 0;
    }

    private static boolean isValidRectRatio(RectF rectF) {
        if (rectF == null || rectF.left * rectF.right * rectF.top * rectF.bottom >= 1.0f) {
            return false;
        }
        return true;
    }

    public static ArrayList<RectF> listOf(String str) {
        ArrayList<RectF> arrayList = new ArrayList<>();
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(str, ",|");
            for (int i2 = 0; i2 < 6 && stringTokenizer.hasMoreElements(); i2++) {
                arrayList.add(new RectF(Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken()), Float.parseFloat(stringTokenizer.nextToken())));
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("listOf failed e="), "RectUtils");
        }
        if (arrayList.size() > 0) {
            return arrayList;
        }
        return null;
    }

    public static RectF parseOf(String str) {
        String[] strArr;
        if (str == null) {
            strArr = null;
        } else {
            strArr = str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        }
        if (strArr == null || strArr.length <= 3) {
            return new RectF();
        }
        return new RectF(Float.parseFloat(strArr[0]), Float.parseFloat(strArr[1]), Float.parseFloat(strArr[2]), Float.parseFloat(strArr[3]));
    }

    public static void rotate(Rect rect, int i2, int i7, int i8) {
        if (i8 % 360 != 0) {
            int i10 = rect.left;
            int i11 = rect.top;
            int i12 = rect.right;
            int i13 = rect.bottom;
            if (i8 == 90) {
                rect.left = i11;
                rect.top = i7 - i12;
                rect.right = i13;
                rect.bottom = i7 - i10;
            } else if (i8 == 180) {
                rect.left = i2 - i12;
                rect.top = i7 - i13;
                rect.right = i2 - i10;
                rect.bottom = i7 - i11;
            } else if (i8 == 270) {
                rect.left = i2 - i13;
                rect.top = i10;
                rect.right = i2 - i11;
                rect.bottom = i12;
            } else {
                throw new AssertionError();
            }
        }
    }

    public static RectF stringToRectF(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(str, ",|");
            if (stringTokenizer.hasMoreElements()) {
                RectF rectF = new RectF();
                rectF.left = Float.parseFloat(stringTokenizer.nextToken());
                rectF.top = Float.parseFloat(stringTokenizer.nextToken());
                rectF.right = Float.parseFloat(stringTokenizer.nextToken());
                rectF.bottom = Float.parseFloat(stringTokenizer.nextToken());
                return rectF;
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("stringToRectF failed e="), "RectUtils");
        }
        return null;
    }

    public static String toString(ArrayList<RectF> arrayList) {
        StringBuilder sb2 = new StringBuilder();
        Iterator<RectF> it = arrayList.iterator();
        while (it.hasNext()) {
            RectF next = it.next();
            if (sb2.length() > 0) {
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            }
            sb2.append(next.left);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(next.top);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(next.right);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(next.bottom);
        }
        return sb2.toString();
    }

    public static Rect getSmartCropRect(Bitmap bitmap, RectF rectF, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect smartCropRect = getSmartCropRect(rectF, width, height);
        if (i2 != 0) {
            return expandRectWithLimitForFace(width, height, smartCropRect, i2);
        }
        return expandRectWithLimit(width, height, smartCropRect);
    }

    public static boolean isValidRect(RectF rectF) {
        return rectF != null && rectF.width() > 0.0f && rectF.height() > 0.0f;
    }

    public static Rect getSmartCropRect(Drawable drawable, RectF rectF, int i2) {
        return getSmartCropRect(drawable, rectF, i2, true);
    }

    public static Rect getSmartCropRect(Drawable drawable, RectF rectF, int i2, boolean z) {
        int i7 = i2 % MOCRLang.KHMER;
        int intrinsicWidth = i7 == 0 ? drawable.getIntrinsicWidth() : drawable.getIntrinsicHeight();
        int intrinsicHeight = i7 == 0 ? drawable.getIntrinsicHeight() : drawable.getIntrinsicWidth();
        Rect smartCropRect = getSmartCropRect(rectF, intrinsicWidth, intrinsicHeight);
        return z ? expandRectWithLimit(intrinsicWidth, intrinsicHeight, smartCropRect) : smartCropRect;
    }

    public static String toString(RectF rectF) {
        if (!isValidRect(rectF)) {
            return "0,0,0,0";
        }
        try {
            return "" + rectF.left + GlobalPostProcInternalPPInterface.SPLIT_REGEX + rectF.top + GlobalPostProcInternalPPInterface.SPLIT_REGEX + rectF.right + GlobalPostProcInternalPPInterface.SPLIT_REGEX + rectF.bottom + GlobalPostProcInternalPPInterface.SPLIT_REGEX;
        } catch (Exception e) {
            a.s(e, new StringBuilder("toString(RectF) failed e="), "RectUtils");
            return "0,0,0,0";
        }
    }

    public static Rect getSmartCropRect(Bitmap bitmap, RectF rectF, int i2, boolean z) {
        return getSmartCropRect(bitmap, rectF, i2, z, 0);
    }

    public static Rect getSmartCropRect(Bitmap bitmap, RectF rectF, int i2, boolean z, int i7) {
        int i8 = i2 % MOCRLang.KHMER;
        int width = i8 == 0 ? bitmap.getWidth() : bitmap.getHeight();
        int height = i8 == 0 ? bitmap.getHeight() : bitmap.getWidth();
        Rect smartCropRect = getSmartCropRect(rectF, width, height);
        if (!z) {
            return smartCropRect;
        }
        if (i7 != 0) {
            return expandRectWithLimitForFace(width, height, smartCropRect, i7);
        }
        return expandRectWithLimit(width, height, smartCropRect);
    }

    public static Rect getSmartCropRect(RectF rectF, int i2, int i7) {
        if (!isValidRectRatio(rectF)) {
            return new Rect(0, 0, i2, i7);
        }
        Rect rect = new Rect();
        float f = (float) i2;
        rect.left = (int) (rectF.left * f);
        rect.right = (int) (f * rectF.right);
        float f5 = (float) i7;
        rect.top = (int) (rectF.top * f5);
        rect.bottom = (int) (f5 * rectF.bottom);
        return rect;
    }
}
