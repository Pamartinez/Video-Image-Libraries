package com.samsung.android.livetranslation.task;

import A.a;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Size;
import c0.C0086a;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.livetranslation.common.Dump;
import com.samsung.android.livetranslation.text.KeyFrame;
import com.samsung.android.livetranslation.text.SceneText;
import com.samsung.android.livetranslation.util.LTTLogger;
import com.samsung.android.livetranslation.util.SceneTextUtil;
import com.samsung.android.livetranslation.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SceneTextFormatter {
    private static int ALIGN_MODE = 0;
    private static final float BASE_RATIO_Y = 0.2f;
    private static final float MARGIN_RATIO_X = 0.02f;
    private static final float MARGIN_RATIO_Y = 0.02f;
    private static final int MIPMAP_NUM = 16;
    private static final boolean SCALE_TEXT_ON = true;
    private static boolean SCALE_X_ON = false;
    static final String TAG = "SceneTextFormatter";
    private static String destinationLangCode;
    private final int[] MIPMAP_SIZE = {2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, SerializeOptions.SORT, 16384, 32768, 65536};
    private boolean[] mIsTextValid = null;
    private int[] mLineHeight = null;
    private int[] mLineHeight_m = null;
    private int[] mLineWidth = null;
    private int[] mLineWidth_m = null;
    private int[] mMarginX = null;
    private int mMaskHeight;
    private int mMaskWidth;

    private void drawTrsTextMask(Bitmap bitmap, String[] strArr, int i2, SceneText sceneText) {
        int i7;
        float f;
        float f5;
        int i8;
        int i10;
        Bitmap bitmap2 = bitmap;
        Canvas canvas = new Canvas(bitmap2);
        int i11 = 0;
        canvas.drawColor(0);
        Paint textAlignedPaint = SceneTextUtil.getTextAlignedPaint(ALIGN_MODE);
        CopyOnWriteArrayList<SceneText> components = sceneText.getComponents();
        int i12 = 0;
        int i13 = 0;
        while (i12 < i2) {
            if (this.mIsTextValid[i12]) {
                String str = strArr[i12];
                int i14 = this.mLineHeight[i12];
                float f8 = 0.5f;
                int i15 = (int) ((((float) i14) * 0.2f) + 0.5f);
                textAlignedPaint.setTextSize((float) i14);
                float max = ((float) this.mLineWidth[i12]) / ((float) Math.max(getTextWidth(str, textAlignedPaint), 1));
                if (!SCALE_X_ON && max > 1.0f) {
                    max = 1.0f;
                }
                textAlignedPaint.setTextScaleX(f);
                while (true) {
                    f5 = f8;
                    if (SceneTextUtil.getTextHeight(str, textAlignedPaint) <= this.mLineHeight[i12] - i15) {
                        break;
                    }
                    i14--;
                    textAlignedPaint.setTextSize((float) i14);
                    f8 = f5;
                }
                if (SceneTextUtil.getTextWidth(str, textAlignedPaint) != this.mLineWidth[i12]) {
                    int i16 = i11;
                    boolean z = true;
                    while (z) {
                        i16++;
                        Paint paint = new Paint(textAlignedPaint);
                        float max2 = (((float) this.mLineWidth[i12]) / ((float) Math.max(getTextWidth(str, paint), 1))) * f;
                        if (SCALE_X_ON || max2 <= 1.0f) {
                            f = max2;
                        } else {
                            f = 1.0f;
                        }
                        paint.setTextScaleX(f);
                        int textWidth = SceneTextUtil.getTextWidth(str, paint);
                        int i17 = this.mLineWidth[i12];
                        if ((textWidth > i17 || (i17 - SceneTextUtil.getTextWidth(str, paint) >= 10 && 10 > i16)) && 100 > i16) {
                            textAlignedPaint = paint;
                        } else {
                            textAlignedPaint = paint;
                            z = false;
                        }
                    }
                }
                int i18 = ALIGN_MODE;
                if (i18 == 0) {
                    i8 = this.mMarginX[i12];
                } else if (i18 == 1) {
                    i8 = (int) ((((float) this.mLineWidth_m[i12]) / 2.0f) + f5);
                } else if (i18 != 2) {
                    i8 = 0;
                } else {
                    i8 = (this.mLineWidth_m[i12] - 1) - this.mMarginX[i12];
                }
                if (Util.isRTLLanguage(destinationLangCode)) {
                    textAlignedPaint.setTextAlign(Paint.Align.RIGHT);
                    i10 = this.mLineWidth[i12] - i8;
                } else {
                    i10 = 0;
                }
                canvas.drawText(strArr[i12], (float) i10, (float) (((this.mLineHeight[i12] - 1) + i13) - i15), textAlignedPaint);
                Rect rect = new Rect();
                String str2 = strArr[i12];
                i7 = 0;
                textAlignedPaint.getTextBounds(str2, 0, str2.length(), rect);
                setUpdatedPolyforTranslatedLines(components.get(i12), components.get(i12).getPoly(), rect.width());
                i13 += this.mLineHeight_m[i12];
            } else {
                i7 = i11;
            }
            i12++;
            i11 = i7;
        }
        if (Dump.IS_INIT_DUMP_SUCCESS && Dump.IS_MASK_DUMP_ENABLE) {
            Dump.dumpBitmap(bitmap2.copy(Bitmap.Config.ARGB_8888, true), a.e(Util.getTimeInMilliSecond(), "mask_java_dump_", ".png"), "Mask");
        }
    }

    private void drawTrsTextOnParagraphMask(Bitmap bitmap, SceneText sceneText) {
        int i2;
        ArrayList<String> arrayList;
        boolean z;
        Bitmap bitmap2 = bitmap;
        ArrayList<String> splitTRSLines = sceneText.getSplitTRSLines();
        int size = splitTRSLines.size();
        Canvas canvas = new Canvas(bitmap2);
        boolean z3 = false;
        canvas.drawColor(0);
        LTTLogger.d(TAG, "drawTrsTextOnParagraphMask: orientation - " + sceneText.getOrient());
        for (Point point : sceneText.getPoly()) {
            LTTLogger.d(TAG, "drawTrsTextOnParagraphMask: Original - " + point.x + " " + point.y);
        }
        Paint textAlignedPaint = SceneTextUtil.getTextAlignedPaint(ALIGN_MODE);
        float fontSize = sceneText.getFontSize();
        String str = TAG;
        LTTLogger.d(str, "Opti size for current para is: " + fontSize);
        textAlignedPaint.setTextSize(fontSize);
        int width = sceneText.getWidth();
        int min = Math.min((int) (((double) width) * 0.075d), 15);
        double marginY = sceneText.getMarginY();
        LTTLogger.d(str, "alignPtY : " + marginY);
        if (Util.isRTLLanguage(destinationLangCode)) {
            textAlignedPaint.setTextAlign(Paint.Align.RIGHT);
            min = width - min;
        }
        int i7 = 0;
        double d = marginY;
        while (i7 < size) {
            String str2 = splitTRSLines.get(i7);
            if (TextUtils.isEmpty(str2) || str2.equals(" ")) {
                arrayList = splitTRSLines;
                i2 = size;
                z = z3;
                LTTLogger.w(TAG, "No String present in current trans line");
            } else {
                int textHeight = SceneTextUtil.getTextHeight(str2, textAlignedPaint);
                int textWidth = SceneTextUtil.getTextWidth(str2, textAlignedPaint);
                String str3 = TAG;
                arrayList = splitTRSLines;
                i2 = size;
                LTTLogger.d(str3, "Text width: " + textWidth + " Height: " + textHeight);
                if (textWidth == 0 || textHeight == 0) {
                    z = false;
                    LTTLogger.w(str3, "Can't paint current line: ".concat(str2));
                } else {
                    double d2 = d + ((double) textHeight);
                    canvas.drawText(str2, (float) min, ((float) d2) - ((float) SceneTextUtil.getTextBottom(str2, textAlignedPaint)), textAlignedPaint);
                    LTTLogger.d(str3, "Drawing " + str2 + " at current height of " + d2 + " with width of " + SceneTextUtil.getTextWidth(str2, textAlignedPaint));
                    z = false;
                    textAlignedPaint.getTextBounds(str2, 0, str2.length(), new Rect());
                    d = d2 + marginY;
                }
            }
            i7++;
            z3 = z;
            splitTRSLines = arrayList;
            size = i2;
        }
        LTTLogger.d(TAG, "drawTrsTextMask:  maskHeight - " + bitmap2.getHeight() + " maskWidth - " + bitmap2.getWidth());
        if (Dump.IS_INIT_DUMP_SUCCESS && Dump.IS_MASK_DUMP_ENABLE) {
            Dump.dumpBitmap(bitmap2.copy(Bitmap.Config.ARGB_8888, true), a.e(Util.getTimeInMilliSecond(), "mask_java_dump_", ".png"), "Mask");
        }
    }

    private int getTextWidth(String str, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.width();
    }

    private void makeParagraphTrsTextMask(SceneText sceneText, String[] strArr, int i2, KeyFrame.TRL_UNIT trl_unit) {
        this.mIsTextValid = new boolean[i2];
        this.mMarginX = new int[i2];
        this.mLineWidth = new int[i2];
        this.mLineWidth_m = new int[i2];
        this.mLineHeight = new int[i2];
        this.mLineHeight_m = new int[i2];
        this.mMaskWidth = 0;
        this.mMaskHeight = 0;
        updateTrsTextMaskPoly(sceneText, strArr, i2);
        if (sceneText.isParagraphRendering() || !(this.mMaskWidth == 0 || this.mMaskHeight == 0)) {
            updateTrsTextMask(sceneText, strArr, i2, trl_unit);
            return;
        }
        sceneText.setTrsTextMask((Bitmap) null);
        for (int i7 = 0; i7 < i2; i7++) {
            sceneText.getComponents().get(i7).setTrsTextMaskPoly((Point[]) null);
        }
    }

    public static void setTextScaleX(boolean z) {
        if (!z) {
            SCALE_X_ON = false;
            ALIGN_MODE = 0;
            return;
        }
        SCALE_X_ON = true;
        ALIGN_MODE = 1;
    }

    private void setUpdatedPolyforTranslatedLines(SceneText sceneText, Point[] pointArr, int i2) {
        Point point = new Point();
        Point point2 = pointArr[0];
        point.x = point2.x;
        point.y = point2.y;
        Point point3 = new Point();
        point3.x = pointArr[0].x + i2;
        point3.y = pointArr[1].y;
        Point point4 = new Point();
        point4.x = pointArr[0].x + i2;
        point4.y = pointArr[2].y;
        Point point5 = new Point();
        Point point6 = pointArr[3];
        point5.x = point6.x;
        point5.y = point6.y;
        sceneText.setUpdatedTrsPoly(new Point[]{point, point3, point4, point5});
    }

    private void updateTrsTextMask(SceneText sceneText, String[] strArr, int i2, KeyFrame.TRL_UNIT trl_unit) {
        int i7;
        int i8;
        int i10;
        if (sceneText.isParagraphRendering()) {
            int width = sceneText.getWidth();
            int height = sceneText.getHeight();
            LTTLogger.i(TAG, "Block W: " + width + " H: " + height);
            int i11 = 0;
            while (true) {
                if (i11 >= 16) {
                    i7 = 0;
                    break;
                }
                i7 = this.MIPMAP_SIZE[i11];
                if (width <= i7) {
                    break;
                }
                i11++;
            }
            int i12 = 0;
            while (true) {
                if (i12 >= 16) {
                    break;
                }
                i8 = this.MIPMAP_SIZE[i12];
                if (height <= i8) {
                    break;
                }
                i12++;
            }
        } else {
            int i13 = 0;
            while (true) {
                if (i13 >= 16) {
                    i10 = 0;
                    break;
                }
                int i14 = this.mMaskWidth;
                int i15 = this.MIPMAP_SIZE[i13];
                if (i14 <= i15) {
                    i10 = i15;
                    break;
                }
                i13++;
            }
            int i16 = 0;
            while (true) {
                if (i16 >= 16) {
                    break;
                }
                int i17 = this.mMaskHeight;
                i8 = this.MIPMAP_SIZE[i16];
                if (i17 <= i8) {
                    break;
                }
                i16++;
            }
        }
        i8 = 0;
        LTTLogger.i(TAG, "maskWidth_mipmap : " + i7 + ", maskHeight_mipmap : " + i8);
        if (i7 == 0 || i8 == 0) {
            sceneText.setTrsTextMask((Bitmap) null);
            for (int i18 = 0; i18 < i2; i18++) {
                sceneText.getComponents().get(i18).setTrsTextMaskPoly((Point[]) null);
            }
            return;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i7, i8, Bitmap.Config.ALPHA_8);
        if (!sceneText.isParagraphRendering()) {
            drawTrsTextMask(createBitmap, strArr, i2, sceneText);
        } else if (sceneText.getFontSize() != 0.0f) {
            drawTrsTextOnParagraphMask(createBitmap, sceneText);
        }
        sceneText.setTrsTextMask(createBitmap);
    }

    private void updateTrsTextMaskPoly(SceneText sceneText, String[] strArr, int i2) {
        int i7;
        int i8;
        if (sceneText.getDeviceOrientation() != 90) {
        }
        CopyOnWriteArrayList<SceneText> components = sceneText.getComponents();
        this.mMaskWidth = -1;
        this.mMaskHeight = 0;
        int i10 = i2;
        boolean z = false;
        boolean z3 = false;
        for (int i11 = 0; i11 < i10; i11++) {
            components.get(i11).getVerticalType();
            String str = strArr[i11];
            Point[] poly = components.get(i11).getPoly();
            boolean z7 = true;
            if (poly.length == 4) {
                Size optimalLineMaskSize = SceneTextUtil.getOptimalLineMaskSize(poly, str, 0);
                i7 = optimalLineMaskSize.getWidth();
                i8 = optimalLineMaskSize.getHeight();
                if (i7 == -1 || i8 == -1) {
                    i8 = 0;
                    i7 = 0;
                    z = true;
                }
            } else {
                i8 = 0;
                i7 = 0;
                z3 = true;
            }
            boolean[] zArr = this.mIsTextValid;
            if (TextUtils.isEmpty(str) || i7 <= 0 || i8 <= 0) {
                z7 = false;
            }
            zArr[i11] = z7;
            if (this.mIsTextValid[i11]) {
                int i12 = (int) ((((float) i7) * 0.02f) + 0.5f);
                int i13 = (i12 * 2) + i7;
                int i14 = (((int) ((((float) i8) * 0.02f) + 0.5f)) * 2) + i8;
                this.mLineWidth[i11] = i7;
                this.mMarginX[i11] = i12;
                this.mLineWidth_m[i11] = i13;
                this.mLineHeight[i11] = i8;
                this.mLineHeight_m[i11] = i14;
                Point[] pointArr = new Point[4];
                for (int i15 = 0; i15 < 4; i15++) {
                    pointArr[i15] = new Point();
                }
                Point[] trsTextMaskPoly = SceneTextUtil.getTrsTextMaskPoly(0, this.mMaskHeight, i13, i14);
                sceneText.getComponents().get(i11).setTrsTextMaskPoly(trsTextMaskPoly);
                if (i13 > this.mMaskWidth) {
                    this.mMaskWidth = i13;
                }
                this.mMaskHeight += i14;
                String str2 = TAG;
                StringBuilder o2 = C0086a.o(i11, "Mask ", " => TrsTextMaskPoly: ");
                o2.append(Arrays.toString(trsTextMaskPoly));
                LTTLogger.d(str2, o2.toString());
            } else {
                sceneText.getComponents().get(i11).setTrsTextMaskPoly((Point[]) null);
            }
        }
        if (z) {
            LTTLogger.e(TAG, "Device orientation is not correct");
        }
        if (z3) {
            LTTLogger.e(TAG, "Size of poly is not four!!");
        }
    }

    public void doSceneTextFormatting(KeyFrame keyFrame) {
        String str = TAG;
        LTTLogger.d(str, "--> doSceneTextFormatting");
        KeyFrame.TRL_UNIT tRLUnit = keyFrame.getTRLUnit();
        int size = keyFrame.getSceneTexts().size();
        destinationLangCode = keyFrame.getTarLang();
        if (size == 0) {
            LTTLogger.d(str, "Number of paragraph is zero");
            return;
        }
        for (int i2 = 0; i2 < size; i2++) {
            SceneText sceneText = keyFrame.getSceneTexts().get(i2);
            int size2 = sceneText.getComponents().size();
            if ((!sceneText.isParagraphRendering() || !sceneText.getSplitTRSLines().isEmpty()) && (sceneText.isParagraphRendering() || size2 != 0)) {
                CopyOnWriteArrayList<SceneText> components = sceneText.getComponents();
                String[] strArr = new String[size2];
                for (int i7 = 0; i7 < size2; i7++) {
                    strArr[i7] = components.get(i7).getTrsValue();
                }
                makeParagraphTrsTextMask(sceneText, strArr, size2, tRLUnit);
                keyFrame.getSceneTexts().set(i2, sceneText);
            }
        }
        LTTLogger.d(TAG, "<-- doSceneTextFormatting");
    }
}
