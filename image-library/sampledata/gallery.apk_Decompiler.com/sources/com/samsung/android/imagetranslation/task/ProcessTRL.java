package com.samsung.android.imagetranslation.task;

import A.a;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Size;
import c0.C0086a;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.imagetranslation.common.Dump;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.data.LttEngineException;
import com.samsung.android.imagetranslation.jni.KeyFrameParam;
import com.samsung.android.imagetranslation.jni.SceneText;
import com.samsung.android.imagetranslation.util.SceneTextUtil;
import com.samsung.android.imagetranslation.util.TextAlignment;
import com.samsung.android.imagetranslation.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProcessTRL {
    private static int ALIGN_MODE = 0;
    private static final float BASE_RATIO_Y = 0.2f;
    private static final int FONT_SIZE = 35;
    private static final float MARGIN_RATIO_X = 0.02f;
    private static final float MARGIN_RATIO_Y = 0.02f;
    private static double MAX_EXTENSION_RATIO = 0.2d;
    private static final int MAX_LINE_HEIGHT = 65536;
    private static final int MAX_MASK_SIZE = 16384;
    private static final int MIN_FONT_THRESHOLD = 3;
    private static final int MIPMAP_NUM = 16;
    private static final boolean SCALE_TEXT_ON = false;
    private static boolean SCALE_X_ON = false;
    private static final String TAG = "ProcessTRL";
    private static String destinationLangCode;
    private final int[] MIPMAP_SIZE = {2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, SerializeOptions.SORT, MAX_MASK_SIZE, 32768, MAX_LINE_HEIGHT};
    private KeyFrameParam keyFrameParam;
    private boolean[] mIsTextValid = null;
    private int[] mLineHeight = null;
    private int[] mLineHeight_m = null;
    private int[] mLineWidth = null;
    private int[] mLineWidth_m = null;
    private int[] mMarginX = null;
    private int mMaskHeight;
    private int mMaskWidth;
    private int mMinLineHeight = MAX_LINE_HEIGHT;
    private long totalTimeTakenForUniformFont = 0;

    public ProcessTRL(KeyFrameParam keyFrameParam2) {
        this.keyFrameParam = keyFrameParam2;
    }

    private void decideFontSizeAndWidthForLine(String[] strArr, int i2, SceneText sceneText, int i7, CopyOnWriteArrayList<SceneText> copyOnWriteArrayList) {
        boolean z;
        SceneText sceneText2;
        int i8;
        CopyOnWriteArrayList<SceneText> copyOnWriteArrayList2;
        boolean z3;
        double min;
        boolean z7 = true;
        int i10 = 0;
        if (sceneText.getComponents().isEmpty() || !Util.isRTLLanguage(sceneText.getComponents().get(0).getPoly())) {
            sceneText2 = sceneText;
            z = false;
        } else {
            sceneText2 = sceneText;
            sceneText2.setTextAlignment(2);
            z = true;
        }
        int textAlignment = sceneText2.getTextAlignment();
        if (sceneText2.getComponents().size() == 1) {
            i8 = 1;
        } else {
            i8 = textAlignment;
        }
        int orientation = Util.getOrientation(sceneText2.getPoly());
        CopyOnWriteArrayList<SceneText> components = sceneText2.getComponents();
        int i11 = i2;
        int i12 = 0;
        while (i12 < i11) {
            Paint paint = new Paint();
            if (this.mIsTextValid[i12]) {
                String str = strArr[i12];
                Rect rect = new Rect();
                int i13 = this.mLineHeight[i12];
                String str2 = TAG;
                LTTLogger.d(str2, "originalFontSize : " + i13 + " Text : " + str);
                float f = (float) i13;
                paint.setTextSize(f);
                components.get(i12).setFontSize(f);
                components.get(i12).setPaddedBoxPoly(components.get(i12).getPoly());
                paint.getTextBounds(str, i10, str.length(), rect);
                int width = rect.width();
                LTTLogger.d(str2, "mLineWidth[l] : " + this.mLineWidth[i12] + " requiredWidth : " + width);
                if (width > this.mLineWidth[i12]) {
                    double maxExtendedWidth = (double) getMaxExtendedWidth(sceneText2, components.get(i12), copyOnWriteArrayList, 0, width - this.mLineWidth[i12], i8, orientation, z);
                    double ceil = ((double) ((int) Math.ceil(maxExtendedWidth / ((double) this.mLineWidth[i12])))) * 0.5d;
                    MAX_EXTENSION_RATIO = ceil;
                    z3 = true;
                    if (i8 == 1) {
                        copyOnWriteArrayList2 = components;
                        min = Math.min(maxExtendedWidth, ceil * 2.0d * ((double) this.mLineWidth[i12]));
                    } else {
                        copyOnWriteArrayList2 = components;
                        min = Math.min(maxExtendedWidth, ceil * ((double) this.mLineWidth[i12]));
                    }
                    int i14 = (int) min;
                    LTTLogger.d(str2, "Text : " + strArr[i12] + " Extension Ratio : " + MAX_EXTENSION_RATIO + " ExtendedLength : " + i14 + " alignment : " + i8 + " orientation : " + orientation + " isRTLLanguage : " + z);
                    Point[] extendedPolyValues = Util.getExtendedPolyValues(copyOnWriteArrayList2.get(i12).getPoly(), i14, i8, orientation, z);
                    int i15 = this.mLineWidth[i12] + i14;
                    copyOnWriteArrayList2.get(i12).setPaddedBoxPoly(extendedPolyValues);
                    this.mLineWidth[i12] = i15;
                } else {
                    copyOnWriteArrayList2 = components;
                    z3 = true;
                }
            } else {
                z3 = z7;
                copyOnWriteArrayList2 = components;
            }
            i12++;
            sceneText2 = sceneText;
            z7 = z3;
            components = copyOnWriteArrayList2;
            i10 = 0;
        }
    }

    private Bitmap drawTrsTextMask(String[] strArr, int i2, SceneText sceneText) {
        float f;
        float f5;
        float f8;
        int i7;
        ProcessTRL processTRL = this;
        int i8 = i2;
        Paint textAlignedPaint = SceneTextUtil.getTextAlignedPaint(ALIGN_MODE);
        int[] iArr = new int[i8];
        float[] fArr = new float[i8];
        int[] iArr2 = new int[i8];
        float[] fArr2 = new float[i8];
        for (int i10 = 0; i10 < i8; i10++) {
            int i11 = processTRL.mMinLineHeight;
            if (i11 < 35) {
                fArr2[i10] = (35.0f / ((float) i11)) * ((float) processTRL.mLineHeight[i10]);
            } else {
                fArr2[i10] = (float) processTRL.mLineHeight[i10];
            }
        }
        int i12 = 1024;
        float f10 = 1.0f;
        int i13 = 1024;
        float f11 = 1.0f;
        int i14 = 0;
        int i15 = 0;
        int i16 = 3;
        while (true) {
            f = 0.5f;
            f5 = 2.0f;
            int i17 = 1;
            if (i14 != 0) {
                break;
            }
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            while (i18 < i8) {
                if (processTRL.mIsTextValid[i18]) {
                    textAlignedPaint.setTextScaleX(f10);
                    int i21 = ALIGN_MODE;
                    if (i21 == 0) {
                        i15 = processTRL.mMarginX[i18];
                    } else if (i21 == i17) {
                        i15 = (int) ((((float) processTRL.mLineWidth_m[i18]) / 2.0f) + 0.5f);
                    } else if (i21 == 2) {
                        i15 = (processTRL.mLineWidth_m[i18] - i17) - processTRL.mMarginX[i18];
                    }
                    textAlignedPaint.setTextSize((float) Math.max((int) (fArr2[i18] * f11), 3));
                    if (((int) (fArr2[i18] * f11)) <= 3) {
                        i14 = i17;
                    }
                    int max = Math.max(2, i15);
                    int max2 = Math.max(SceneTextUtil.getTextWidth(strArr[i18], textAlignedPaint), (int) textAlignedPaint.measureText(strArr[i18]));
                    iArr2[i18] = max2;
                    int max3 = Math.max(i20, max2);
                    int textHeight = SceneTextUtil.getTextHeight(strArr[i18], textAlignedPaint);
                    iArr[i18] = textHeight;
                    processTRL.mMaskHeight += textHeight;
                    fArr[i18] = textAlignedPaint.getTextSize();
                    int max4 = Math.max(3, SceneTextUtil.getTextHeight(strArr[i18], textAlignedPaint) / 15);
                    i16 = max4;
                    i19 = ((Math.max(5, (int) sceneText.getMarginY()) + max4) * 2) + iArr[i18] + i19;
                    i7 = max3;
                    i15 = max;
                } else {
                    i7 = i20;
                }
                i18++;
                i20 = i7;
                i17 = 1;
                f10 = 1.0f;
            }
            int[] iArr3 = iArr;
            float[] fArr3 = fArr;
            int pow = (int) Math.pow(2.0d, Math.ceil(Math.log((double) ((i15 * 2) + i20)) / Math.log(2.0d)));
            int pow2 = (int) Math.pow(2.0d, Math.ceil(Math.log((double) (((Math.max(5, (int) sceneText.getMarginY()) + i16) * 2) + i19)) / Math.log(2.0d)));
            if (i14 != 0) {
                i12 = Math.min(pow, MAX_MASK_SIZE);
                pow2 = Math.min(pow2, MAX_MASK_SIZE);
            } else {
                i12 = pow;
            }
            i13 = pow2;
            if (i12 > MAX_MASK_SIZE || i13 > MAX_MASK_SIZE) {
                f11 *= 0.9f;
                iArr = iArr3;
                fArr = fArr3;
                f10 = 1.0f;
            } else {
                iArr = iArr3;
                fArr = fArr3;
                f10 = 1.0f;
                i14 = 1;
            }
        }
        int[] iArr4 = iArr;
        float[] fArr4 = fArr;
        Bitmap createBitmap = Bitmap.createBitmap(i12, i13, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        int i22 = i15;
        int i23 = 0;
        int i24 = 0;
        while (i23 < i8) {
            if (processTRL.mIsTextValid[i23]) {
                Rect rect = new Rect();
                textAlignedPaint.setTextSize(fArr4[i23]);
                int max5 = Math.max(3, SceneTextUtil.getTextHeight(strArr[i23], textAlignedPaint) / 15);
                int max6 = ((int) Math.max(5.0d, sceneText.getMarginY())) + max5;
                if (Util.isRTLLanguage(destinationLangCode)) {
                    textAlignedPaint.setTextAlign(Paint.Align.RIGHT);
                    i22 = i15 + iArr2[i23];
                }
                String str = strArr[i23];
                textAlignedPaint.getTextBounds(str, 0, str.length(), rect);
                if (max5 > 0) {
                    textAlignedPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                    f8 = f;
                    textAlignedPaint.setColor(Color.argb(f8, 0.0f, 0.0f, 0.0f));
                    textAlignedPaint.setStrokeWidth((float) max5);
                    textAlignedPaint.setStrokeMiter(f5);
                    textAlignedPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
                    float f12 = (float) i22;
                    int i25 = i24 + max6;
                    canvas.drawText(strArr[i23], f12, (float) (i25 - rect.top), textAlignedPaint);
                    textAlignedPaint.setStyle(Paint.Style.FILL);
                    textAlignedPaint.setColor(Color.argb(1.0f, 0.0f, 0.0f, 0.0f));
                    textAlignedPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
                    canvas.drawText(strArr[i23], f12, (float) (i25 - rect.top), textAlignedPaint);
                } else {
                    f8 = f;
                    textAlignedPaint.setStyle(Paint.Style.FILL);
                    textAlignedPaint.setColor(Color.argb(1.0f, 0.0f, 0.0f, 0.0f));
                    canvas.drawText(strArr[i23], (float) i22, (float) ((i24 + max6) - rect.top), textAlignedPaint);
                }
                int i26 = max6 * 2;
                sceneText.getComponents().get(i23).setTrsTextMaskPoly(SceneTextUtil.getTrsTextMaskPoly(0, i24, (i15 * 2) + iArr2[i23], iArr4[i23] + i26));
                i24 = iArr4[i23] + i26 + i24;
            } else {
                f8 = f;
            }
            i23++;
            processTRL = this;
            i8 = i2;
            f = f8;
            f5 = 2.0f;
        }
        if (Dump.IS_INIT_DUMP_SUCCESS && Dump.IS_MASK_DUMP_ENABLE) {
            Dump.dumpBitmap(createBitmap.copy(Bitmap.Config.ARGB_8888, true), a.e(System.currentTimeMillis(), "mask_java_dump_", ".png"), "Mask");
        }
        return createBitmap;
    }

    private Bitmap drawTrsTextOnParagraphMask(SceneText sceneText) {
        String str;
        int[] iArr;
        float f;
        int i2;
        int i7;
        String str2;
        int i8;
        float f5;
        SceneText sceneText2 = sceneText;
        ArrayList<String> splitTRSLines = sceneText2.getSplitTRSLines();
        int size = splitTRSLines.size();
        LTTLogger.d(TAG, "drawTrsTextOnParagraphMask: orientation - " + sceneText2.getOrient());
        Point[] poly = sceneText2.getPoly();
        int length = poly.length;
        int i10 = 0;
        while (true) {
            str = " ";
            if (i10 >= length) {
                break;
            }
            Point point = poly[i10];
            LTTLogger.d(TAG, "drawTrsTextOnParagraphMask: Original - " + point.x + str + point.y);
            i10++;
        }
        Paint textAlignedPaint = SceneTextUtil.getTextAlignedPaint(ALIGN_MODE);
        float fontSize = sceneText2.getFontSize();
        String str3 = TAG;
        LTTLogger.d(str3, "Optimal size for current para is: " + fontSize);
        textAlignedPaint.setTextSize(fontSize);
        int min = Math.min((int) (((double) sceneText2.getWidth()) * 0.075d), 15);
        float max = (float) Math.max(5.0d, sceneText2.getMarginY());
        LTTLogger.d(str3, "alignPtY : " + max);
        int[] iArr2 = new int[size];
        float[] fArr = new float[size];
        int max2 = Math.max((int) fontSize, 35);
        float f8 = (float) max2;
        textAlignedPaint.setTextSize(f8);
        int longestLine = SceneTextUtil.getLongestLine(splitTRSLines, textAlignedPaint);
        if (longestLine < splitTRSLines.size()) {
            iArr = iArr2;
            double d = ((double) max) * 1.01d;
            double textHeight = ((double) SceneTextUtil.getTextHeight(splitTRSLines.get(longestLine), textAlignedPaint)) + d;
            double d2 = d;
            double d3 = (double) size;
            double d5 = d3;
            f = max;
            int min2 = Math.min(max2, (int) Math.min(Math.ceil((double) (16384.0f / (((float) (SceneTextUtil.getTextWidth(splitTRSLines.get(longestLine), textAlignedPaint) + min)) / f8))), Math.ceil((double) (16384.0f / (((float) ((int) ((textHeight * d3) + d2))) / f8)))));
            textAlignedPaint.setTextSize((float) min2);
            if (min2 < max2) {
                max2 = min2;
                while (true) {
                    if (SceneTextUtil.getTextWidth(splitTRSLines.get(longestLine), textAlignedPaint) + min <= MAX_MASK_SIZE && ((int) (((((double) SceneTextUtil.getTextHeight(splitTRSLines.get(longestLine), textAlignedPaint)) + d2) * d5) + d2)) <= MAX_MASK_SIZE) {
                        break;
                    }
                    max2--;
                    textAlignedPaint.setTextSize((float) max2);
                }
            } else {
                max2 = min2;
            }
        } else {
            iArr = iArr2;
            f = max;
        }
        textAlignedPaint.setTextSize((float) Math.max(max2, 3));
        float f10 = f;
        int i11 = 0;
        float f11 = 0.0f;
        int i12 = 0;
        while (i11 < size) {
            String str4 = splitTRSLines.get(i11);
            if (TextUtils.isEmpty(str4) || str4.equals(str)) {
                i8 = i11;
                str2 = str;
                i7 = min;
                f5 = f11;
                LTTLogger.w(TAG, "No String present in current trans line");
            } else {
                int textHeight2 = SceneTextUtil.getTextHeight(str4, textAlignedPaint);
                i8 = i11;
                int textWidth = SceneTextUtil.getTextWidth(str4, textAlignedPaint);
                str2 = str;
                String str5 = TAG;
                i7 = min;
                f5 = f11;
                LTTLogger.d(str5, "Text width: " + textWidth + " Height: " + textHeight2);
                if (textWidth == 0 || textHeight2 == 0) {
                    LTTLogger.w(str5, "Can't paint current line: ".concat(str4));
                } else {
                    int max3 = Math.max(i12, SceneTextUtil.getTextWidth(str4, textAlignedPaint) + i7);
                    if (textAlignedPaint.getTextSize() < f8) {
                        f10 = Math.min((f10 * f8) / textAlignedPaint.getTextSize(), 15.0f);
                        textAlignedPaint.setTextSize(f8);
                        max3 = Math.max(max3, SceneTextUtil.getTextWidth(str4, textAlignedPaint) + i7);
                    }
                    int textHeight3 = SceneTextUtil.getTextHeight(str4, textAlignedPaint);
                    iArr[i8] = textHeight3;
                    f11 = f5 + ((float) textHeight3);
                    this.mMaskHeight += textHeight3;
                    fArr[i8] = textAlignedPaint.getTextSize();
                    LTTLogger.d(str5, "Drawing " + str4 + " at current height of " + f11 + " with width of " + SceneTextUtil.getTextWidth(str4, textAlignedPaint));
                    i12 = max3;
                    i11 = i8 + 1;
                    str = str2;
                    min = i7;
                }
            }
            f11 = f5;
            i11 = i8 + 1;
            str = str2;
            min = i7;
        }
        int i13 = min;
        Bitmap createBitmap = Bitmap.createBitmap((int) Math.min(16384.0d, Math.pow(2.0d, Math.ceil(Math.log((double) i12) / Math.log(2.0d)))), (int) Math.min(16384.0d, Math.pow(2.0d, Math.ceil(Math.log((double) ((((float) (size + 1)) * f10) + f11)) / Math.log(2.0d)))), Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        int textAlignment = sceneText2.getTextAlignment();
        int max4 = Math.max(3, SceneTextUtil.getTextHeight(splitTRSLines.get(longestLine), textAlignedPaint) / 15);
        float f12 = f10;
        int i14 = 0;
        int i15 = 0;
        while (i14 < size) {
            String str6 = splitTRSLines.get(i14);
            textAlignedPaint.setTextSize(fArr[i14]);
            Rect rect = new Rect();
            ArrayList<String> arrayList = splitTRSLines;
            float f13 = f12 + ((float) iArr[i14]);
            if (textAlignment == 1) {
                i2 = (i12 - SceneTextUtil.getTextWidth(str6, textAlignedPaint)) / 2;
            } else {
                i2 = textAlignment == 2 ? (i12 - SceneTextUtil.getTextWidth(str6, textAlignedPaint)) - i13 : i13;
            }
            int i16 = i2;
            if (Util.isRTLLanguage(destinationLangCode)) {
                textAlignedPaint.setTextAlign(Paint.Align.RIGHT);
                i2 = SceneTextUtil.getTextWidth(str6, textAlignedPaint) + i16;
            }
            int i17 = size;
            int i18 = textAlignment;
            textAlignedPaint.getTextBounds(str6, 0, str6.length(), rect);
            if (max4 > 0) {
                textAlignedPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                textAlignedPaint.setColor(Color.argb(0.5f, 0.0f, 0.0f, 0.0f));
                textAlignedPaint.setStrokeWidth((float) max4);
                textAlignedPaint.setStrokeMiter(2.0f);
                textAlignedPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
                float f14 = (float) i2;
                canvas.drawText(str6, f14, f13 - ((float) rect.bottom), textAlignedPaint);
                textAlignedPaint.setStyle(Paint.Style.FILL);
                textAlignedPaint.setColor(Color.argb(1.0f, 0.0f, 0.0f, 0.0f));
                textAlignedPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
                canvas.drawText(str6, f14, f13 - ((float) rect.bottom), textAlignedPaint);
            } else {
                textAlignedPaint.setStyle(Paint.Style.FILL);
                textAlignedPaint.setColor(Color.argb(1.0f, 0.0f, 0.0f, 0.0f));
                canvas.drawText(str6, (float) i2, f13 - ((float) rect.bottom), textAlignedPaint);
            }
            i15 = Math.max(i15, SceneTextUtil.getTextWidth(str6, textAlignedPaint));
            f12 = f13 + f10;
            i14++;
            splitTRSLines = arrayList;
            size = i17;
            textAlignment = i18;
        }
        sceneText2.setTrsTextHeight((int) f12);
        sceneText2.setTrsTextwidth((i13 * 2) + i15);
        LTTLogger.d(TAG, "drawTrsTextMask:  maskHeight - " + createBitmap.getHeight() + " maskWidth - " + createBitmap.getWidth());
        if (Dump.IS_MASK_DUMP_ENABLE) {
            Dump.dumpBitmap(createBitmap.copy(Bitmap.Config.ARGB_8888, true), a.e(System.currentTimeMillis(), "mask_java_dump_", ".png"), "Mask");
        }
        return createBitmap;
    }

    private int getMaxExtendedWidth(SceneText sceneText, SceneText sceneText2, CopyOnWriteArrayList<SceneText> copyOnWriteArrayList, int i2, int i7, int i8, int i10, boolean z) {
        int i11 = i7;
        int i12 = 0;
        int i13 = i2;
        while (i13 <= i11) {
            int D = C0086a.D(i11, i13, 2, i13);
            if (isPossibleToExtend(sceneText, sceneText2, copyOnWriteArrayList, D, i8, i10, z)) {
                i13 = D + 1;
                i12 = D;
            } else {
                i11 = D - 1;
            }
        }
        return i12;
    }

    private int getTextWidth(String str, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.width();
    }

    private boolean isPossibleToExtend(SceneText sceneText, SceneText sceneText2, CopyOnWriteArrayList<SceneText> copyOnWriteArrayList, int i2, int i7, int i8, boolean z) {
        Point[] pointArr;
        int inputImageWidth = this.keyFrameParam.getInputImageWidth();
        int inputImageHeight = this.keyFrameParam.getInputImageHeight();
        Point[] poly = sceneText2.getPoly();
        Point[] extendedPolyValues = Util.getExtendedPolyValues(poly, i2, i7, i8, z);
        if (Util.isPolyOutOfImageBound(poly, extendedPolyValues, inputImageWidth, inputImageHeight, 10)) {
            return false;
        }
        Iterator<SceneText> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            SceneText next = it.next();
            if (!next.isParagraphRendering() || next.getValue().equals(sceneText.getValue())) {
                Iterator<SceneText> it2 = next.getComponents().iterator();
                while (it2.hasNext()) {
                    SceneText next2 = it2.next();
                    if (!next2.getValue().equals(sceneText2.getValue())) {
                        boolean isLineOnTopOrBottom = Util.isLineOnTopOrBottom(sceneText2.getPoly(), next2.getPoly(), sceneText.getPaddedBoxPoly(), next.getPaddedBoxPoly());
                        if (next2.getPaddedBoxPoly() == null) {
                            pointArr = next2.getPoly();
                        } else {
                            pointArr = next2.getPaddedBoxPoly();
                        }
                        boolean isPolyValueIntersecting = Util.isPolyValueIntersecting(extendedPolyValues, pointArr);
                        if (!isLineOnTopOrBottom && isPolyValueIntersecting) {
                            return false;
                        }
                    }
                }
                continue;
            } else if (Util.isPolyValueIntersecting(extendedPolyValues, next.getPaddedBoxPoly())) {
                return false;
            }
        }
        return true;
    }

    private void makeParagraphTrsTextMask(SceneText sceneText, String[] strArr, int i2) {
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
            updateTrsTextMask(sceneText, strArr, i2);
            return;
        }
        sceneText.setTrsTextMask((Bitmap) null);
        for (int i7 = 0; i7 < i2; i7++) {
            sceneText.getComponents().get(i7).setTrsTextMaskPoly((Point[]) null);
        }
    }

    private static void setDestinationLanguage(KeyFrameParam keyFrameParam2) {
        destinationLangCode = keyFrameParam2.getDestLanguage();
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

    private void updateTrsTextMask(SceneText sceneText, String[] strArr, int i2) {
        int i7;
        int i8;
        int i10;
        SceneText sceneText2;
        int i11;
        String[] strArr2;
        ProcessTRL processTRL;
        if (sceneText.isParagraphRendering()) {
            int width = sceneText.getWidth();
            int height = sceneText.getHeight();
            LTTLogger.i(TAG, "Block W: " + width + " H: " + height);
            int i12 = 0;
            while (true) {
                if (i12 >= 16) {
                    i7 = 0;
                    break;
                }
                i7 = this.MIPMAP_SIZE[i12];
                if (width <= i7) {
                    break;
                }
                i12++;
            }
            int i13 = 0;
            while (true) {
                if (i13 >= 16) {
                    break;
                }
                i8 = this.MIPMAP_SIZE[i13];
                if (height <= i8) {
                    break;
                }
                i13++;
            }
            i8 = 0;
        } else {
            int i14 = 0;
            while (true) {
                if (i14 >= 16) {
                    i10 = 0;
                    break;
                }
                int i15 = this.mMaskWidth;
                int i16 = this.MIPMAP_SIZE[i14];
                if (i15 <= i16) {
                    i10 = i16;
                    break;
                }
                i14++;
            }
            int i17 = 0;
            while (true) {
                if (i17 >= 16) {
                    break;
                }
                int i18 = this.mMaskHeight;
                i8 = this.MIPMAP_SIZE[i17];
                if (i18 <= i8) {
                    break;
                }
                i17++;
            }
            i8 = 0;
        }
        int i19 = i7;
        LTTLogger.i(TAG, "maskWidth_mipmap : " + i19 + ", maskHeight_mipmap : " + i8);
        Bitmap bitmap = null;
        if (i19 == 0 || i8 == 0) {
            SceneText sceneText3 = sceneText;
            int i20 = i2;
            sceneText3.setTrsTextMask((Bitmap) null);
            for (int i21 = 0; i21 < i20; i21++) {
                sceneText3.getComponents().get(i21).setTrsTextMaskPoly((Point[]) null);
            }
            return;
        }
        sceneText.setTextAlignment(TextAlignment.getAlignmentOfBlock(sceneText));
        if (sceneText.isParagraphRendering()) {
            if (sceneText.getFontSize() != 0.0f) {
                bitmap = drawTrsTextOnParagraphMask(sceneText);
            }
            sceneText2 = sceneText;
        } else {
            if (Dump.IS_UNIFORM_FONT_ENABLED) {
                long currentTimeMillis = System.currentTimeMillis();
                processTRL = this;
                sceneText2 = sceneText;
                strArr2 = strArr;
                i11 = i2;
                processTRL.decideFontSizeAndWidthForLine(strArr2, i11, sceneText2, i19, this.keyFrameParam.getSceneTexts());
                processTRL.totalTimeTakenForUniformFont = (System.currentTimeMillis() - currentTimeMillis) + processTRL.totalTimeTakenForUniformFont;
            } else {
                processTRL = this;
                sceneText2 = sceneText;
                strArr2 = strArr;
                i11 = i2;
            }
            bitmap = processTRL.drawTrsTextMask(strArr2, i11, sceneText2);
        }
        sceneText2.setTrsTextMask(bitmap);
    }

    private void updateTrsTextMaskPoly(SceneText sceneText, String[] strArr, int i2) {
        int i7;
        int i8;
        if (sceneText.getDeviceOrientation() != 90) {
        }
        CopyOnWriteArrayList<SceneText> components = sceneText.getComponents();
        this.mMaskWidth = -1;
        this.mMaskHeight = 0;
        this.mMinLineHeight = MAX_LINE_HEIGHT;
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
                this.mMinLineHeight = Math.min(this.mMinLineHeight, i8);
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

    public void detectAndClearSameTranslatedResult() {
        Iterator<SceneText> it = this.keyFrameParam.getSceneTexts().iterator();
        while (it.hasNext()) {
            SceneText next = it.next();
            String str = TAG;
            LTTLogger.d(str, "Paragraph type =" + next.getTrlUnit());
            if (!next.isParagraphRendering()) {
                Iterator<SceneText> it2 = next.getComponents().iterator();
                while (it2.hasNext()) {
                    SceneText next2 = it2.next();
                    String str2 = TAG;
                    LTTLogger.d(str2, "Line Original =" + next2.getValue());
                    LTTLogger.d(str2, "Line Translated =" + next2.getTrsValue());
                    if (!next2.hasOnlyNumber()) {
                        String trsValue = next2.getTrsValue();
                        int length = trsValue.length();
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length) {
                                LTTLogger.d(TAG, "Empty mask rendering");
                                next2.setTrsValue(" ");
                                break;
                            }
                            int codePointAt = trsValue.codePointAt(i2);
                            if (Character.isWhitespace(codePointAt)) {
                                i2 += Character.charCount(codePointAt);
                            } else if (Util.removeMoreThanOneSpaceBetweenWords(next2.getValue()).trim().equalsIgnoreCase(Util.removeMoreThanOneSpaceBetweenWords(next2.getTrsValue()).trim())) {
                                LTTLogger.d(TAG, "Rendering skipped");
                                next2.setTrsValue("");
                            }
                        }
                    } else {
                        LTTLogger.d(str2, "Rendering skipped");
                        next2.setTrsValue("");
                    }
                }
            } else {
                LTTLogger.d(str, "Para Original =" + next.getValue());
                LTTLogger.d(str, "Para Translated =" + next.getTrsValue());
                LTTLogger.d(str, "Para Distributed =" + next.getSplitTRSLines());
                if (Util.removeMoreThanOneSpaceBetweenWords(next.getValue()).trim().equalsIgnoreCase(Util.removeMoreThanOneSpaceBetweenWords(next.getTrsValue()).trim())) {
                    next.getSplitTRSLines().clear();
                }
            }
        }
    }

    public void generateTranslationMask() {
        long currentTimeMillis = System.currentTimeMillis();
        String str = TAG;
        LTTLogger.d(str, "--> doSceneTextFormatting");
        int size = this.keyFrameParam.getSceneTexts().size();
        setDestinationLanguage(this.keyFrameParam);
        if (size != 0) {
            for (int i2 = 0; i2 < size; i2++) {
                SceneText sceneText = this.keyFrameParam.getSceneTexts().get(i2);
                int size2 = sceneText.getComponents().size();
                if ((!sceneText.isParagraphRendering() || !sceneText.getSplitTRSLines().isEmpty()) && (sceneText.isParagraphRendering() || size2 != 0)) {
                    CopyOnWriteArrayList<SceneText> components = sceneText.getComponents();
                    String[] strArr = new String[size2];
                    for (int i7 = 0; i7 < size2; i7++) {
                        strArr[i7] = components.get(i7).getTrsValue();
                    }
                    makeParagraphTrsTextMask(sceneText, strArr, size2);
                    String str2 = TAG;
                    StringBuilder o2 = C0086a.o(i2, "Block : ", " Alignment : ");
                    o2.append(sceneText.getTextAlignment());
                    LTTLogger.d(str2, o2.toString());
                    this.keyFrameParam.getSceneTexts().set(i2, sceneText);
                }
            }
            String str3 = TAG;
            LTTLogger.p(str3, "Uniform Font : " + this.totalTimeTakenForUniformFont + " ms");
            LTTLogger.p(str3, "generateTranslationMask: Total time taken : " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            LTTLogger.d(str3, "<-- doSceneTextFormatting");
            return;
        }
        LTTLogger.d(str, "Number of paragraph is zero");
        throw new LttEngineException(-16, "Number of paragraph is zero");
    }

    public void getLinesToRestore(List<Path> list) {
        Iterator<SceneText> it = this.keyFrameParam.getSceneTexts().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            SceneText next = it.next();
            String str = TAG;
            StringBuilder o2 = C0086a.o(i2, "getLinesToRestore: Distribution --> Block - ", " type =");
            o2.append(next.getTrlUnit());
            LTTLogger.d(str, o2.toString());
            if (next.isParagraphRendering()) {
                LTTLogger.d(str, "getLinesToRestore: Para Original - " + next.getValue());
                LTTLogger.d(str, "getLinesToRestore: Para Translated - " + next.getTrsValue());
                LTTLogger.d(str, "getLinesToRestore: Para Distributed - " + next.getSplitTRSLines());
                if (Util.removeMoreThanOneSpaceBetweenWords(next.getValue()).trim().equalsIgnoreCase(Util.removeMoreThanOneSpaceBetweenWords(next.getTrsValue()).trim())) {
                    LTTLogger.d(str, "getLinesToRestore: Para Restored");
                    list.add(getPath(next.getPoly()));
                    next.getSplitTRSLines().clear();
                }
            } else {
                Iterator<SceneText> it2 = next.getComponents().iterator();
                while (it2.hasNext()) {
                    SceneText next2 = it2.next();
                    String str2 = TAG;
                    LTTLogger.d(str2, "getLinesToRestore: Line Original - " + next2.getValue() + " hasOnlyNumber - " + next2.hasOnlyNumber());
                    StringBuilder sb2 = new StringBuilder("getLinesToRestore: Line Translated - ");
                    sb2.append(next2.getTrsValue());
                    LTTLogger.d(str2, sb2.toString());
                    if (next2.hasOnlyNumber()) {
                        LTTLogger.d(str2, "getLinesToRestore: Rendering skipped");
                        next2.setTrsValue("");
                    } else if (Util.removeMoreThanOneSpaceBetweenWords(next2.getValue()).trim().equalsIgnoreCase(Util.removeMoreThanOneSpaceBetweenWords(next2.getTrsValue()).trim())) {
                        LTTLogger.d(str2, "getLinesToRestore: Line Restored");
                        list.add(getPath(next2.getPoly()));
                        next2.setTrsValue("");
                    }
                }
            }
            i2++;
        }
    }

    public Path getPath(Point[] pointArr) {
        Path path = new Path();
        Point point = pointArr[0];
        path.moveTo((float) point.x, (float) point.y);
        Point point2 = pointArr[1];
        path.lineTo((float) point2.x, (float) point2.y);
        Point point3 = pointArr[2];
        path.lineTo((float) point3.x, (float) point3.y);
        Point point4 = pointArr[3];
        path.lineTo((float) point4.x, (float) point4.y);
        path.close();
        return path;
    }
}
