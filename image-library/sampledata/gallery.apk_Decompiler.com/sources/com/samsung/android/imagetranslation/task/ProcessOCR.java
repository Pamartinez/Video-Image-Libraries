package com.samsung.android.imagetranslation.task;

import android.graphics.Point;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.imagetranslation.common.Dump;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.imagetranslation.jni.KeyFrameParam;
import com.samsung.android.imagetranslation.jni.SceneText;
import com.samsung.android.imagetranslation.util.OcrDataToSceneTextConverter;
import com.samsung.android.imagetranslation.util.SceneTextUtil;
import com.samsung.android.imagetranslation.util.SceneTextUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProcessOCR {
    private static final String TAG = "ProcessOCR";
    private KeyFrameParam keyFrameParam;

    public ProcessOCR(KeyFrameParam keyFrameParam2) {
        this.keyFrameParam = keyFrameParam2;
    }

    private void changeCoordinateToPreview(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList) {
        String str = TAG;
        LTTLogger.d(str, "changeCoordinateToPreview: E");
        SceneTextUtils.convertCoordiate(copyOnWriteArrayList, SceneTextUtil.generateRotationMatrix(this.keyFrameParam.getInputImage().getWidth(), this.keyFrameParam.getInputImage().getHeight(), this.keyFrameParam.getResizeRatio(), this.keyFrameParam.getDeviceRotation()), this.keyFrameParam.getDeviceRotation());
        LTTLogger.d(str, "changeCoordinateToPreview: X");
    }

    private void updateBlockIdx(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList) {
        LTTLogger.d(TAG, "updateBlockIdx: E");
        int googleBlockIdx = copyOnWriteArrayList.get(0).getComponents().get(0).getGoogleBlockIdx();
        Iterator<SceneText> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            googleBlockIdx = setBlockIdx(googleBlockIdx, it.next());
            String str = TAG;
            LTTLogger.d(str, "SetBlockIdx = " + googleBlockIdx);
        }
        splitBlockIdxWithLang(copyOnWriteArrayList);
        LTTLogger.d(TAG, "updateBlockIdx: X");
    }

    public boolean checkDigitFrequencyLastWordForEachLine(SceneText sceneText) {
        Pattern compile = Pattern.compile("\\d+");
        Iterator<SceneText> it = sceneText.getComponents().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            SceneText next = it.next();
            if (next.getComponents() != null && compile.matcher(next.getComponents().get(next.getComponentNum() - 1).getValue()).find()) {
                i2++;
            }
        }
        if (i2 >= 3) {
            return false;
        }
        return true;
    }

    public boolean checkWithAreaRatio(SceneText sceneText) {
        if (getLineAreaInParagraph(sceneText) / getPolygonArea(sceneText.getPoly()) > 0.65d) {
            return true;
        }
        return false;
    }

    public boolean checkWithLineLength(SceneText sceneText) {
        Iterator<SceneText> it = sceneText.getComponents().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (it.next().getComponentNum() <= 2) {
                i2++;
            }
        }
        if (((double) i2) > ((double) sceneText.getComponentNum()) * 0.5d) {
            return false;
        }
        return true;
    }

    public boolean checkWithOrderedLines(SceneText sceneText) {
        Pattern compile = Pattern.compile("^[\\dA-I]+\\.");
        Iterator<SceneText> it = sceneText.getComponents().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (compile.matcher(it.next().getValue()).find()) {
                i2++;
            }
        }
        if (i2 > 2) {
            return true;
        }
        return false;
    }

    public boolean checkWithSentencePunctuation(SceneText sceneText) {
        Matcher matcher = Pattern.compile("[^\\d\\.]\\.($|\\n| |^\\.)").matcher(sceneText.getValue());
        Iterator<SceneText> it = sceneText.getComponents().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (it.next().getComponentNum() <= 4) {
                i2++;
            }
        }
        if (!matcher.find() || ((double) i2) >= ((double) sceneText.getComponentNum()) * 0.5d) {
            return false;
        }
        return true;
    }

    public boolean enoughForSingleBlockIdx(SceneText sceneText) {
        boolean checkWithSentencePunctuation = checkWithSentencePunctuation(sceneText);
        boolean checkWithAreaRatio = checkWithAreaRatio(sceneText);
        boolean checkWithLineLength = checkWithLineLength(sceneText);
        boolean checkWithOrderedLines = checkWithOrderedLines(sceneText);
        boolean checkDigitFrequencyLastWordForEachLine = checkDigitFrequencyLastWordForEachLine(sceneText);
        String str = TAG;
        LTTLogger.i(str, "Check paragraph conditions : List(" + checkWithOrderedLines + "), Punctuation(" + checkWithSentencePunctuation + "), Area(" + checkWithAreaRatio + "), Words(" + checkWithLineLength + "), Digits(" + checkDigitFrequencyLastWordForEachLine + "), ");
        if (checkWithOrderedLines) {
            return false;
        }
        return checkDigitFrequencyLastWordForEachLine & ((checkWithAreaRatio & checkWithLineLength) | checkWithSentencePunctuation);
    }

    public double getLineAreaInParagraph(SceneText sceneText) {
        Iterator<SceneText> it = sceneText.getComponents().iterator();
        double d = MapUtil.INVALID_LOCATION;
        while (it.hasNext()) {
            d += getPolygonArea(it.next().getPoly());
        }
        return d;
    }

    public double getPolygonArea(Point[] pointArr) {
        int i2;
        int i7;
        int i8;
        double d = MapUtil.INVALID_LOCATION;
        for (int i10 = 0; i10 < pointArr.length; i10++) {
            if (i10 == 0) {
                i2 = pointArr[i10].x;
                i7 = pointArr[i10 + 1].y;
                i8 = pointArr[pointArr.length - 1].y;
            } else if (i10 == pointArr.length - 1) {
                i2 = pointArr[i10].x;
                i7 = pointArr[0].y;
                i8 = pointArr[i10 - 1].y;
            } else {
                i2 = pointArr[i10].x;
                i7 = pointArr[i10 + 1].y;
                i8 = pointArr[i10 - 1].y;
            }
            d += (double) ((i7 - i8) * i2);
        }
        return Math.abs(d) * 0.5d;
    }

    public CopyOnWriteArrayList<SceneText> getSceneTextFromOCR(LttOcrResult lttOcrResult, int i2, int i7, int i8) {
        CopyOnWriteArrayList<SceneText> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        if (!new OcrDataToSceneTextConverter().convert(lttOcrResult, copyOnWriteArrayList, i8, this.keyFrameParam.getContext())) {
            return null;
        }
        Dump.drawOCR(this.keyFrameParam.getInputImage(), copyOnWriteArrayList, false);
        for (LttOcrResult.BlockInfo poly : lttOcrResult.getBlockInfoList()) {
            String str = TAG;
            LTTLogger.d(str, "Original block poly: " + Arrays.toString(poly.getPoly()));
        }
        SceneTextUtils.updateSceneTextRotation(copyOnWriteArrayList, i8);
        updateBlockIdx(copyOnWriteArrayList);
        SceneTextUtils.detectVerticalLine(copyOnWriteArrayList);
        changeCoordinateToPreview(copyOnWriteArrayList);
        SceneTextUtils.updatePadding(copyOnWriteArrayList, i7, i2);
        if (Dump.IS_PADDED_POLY_ENABLED) {
            Dump.drawOCR(this.keyFrameParam.getInputImage(), copyOnWriteArrayList, true);
        }
        return copyOnWriteArrayList;
    }

    public int setBlockIdx(int i2, SceneText sceneText) {
        Iterator<SceneText> it = sceneText.getComponents().iterator();
        while (it.hasNext()) {
            it.next().setGoogleBlockIdx(i2);
        }
        return i2 + 1;
    }

    public boolean splitBlockIdxWithLang(CopyOnWriteArrayList<SceneText> copyOnWriteArrayList) {
        String str;
        String str2;
        Iterator<SceneText> it = copyOnWriteArrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Iterator<SceneText> it2 = it.next().getComponents().iterator();
            int i7 = -1;
            String str3 = "";
            while (it2.hasNext()) {
                SceneText next = it2.next();
                HashMap hashMap = new HashMap();
                Iterator<SceneText> it3 = next.getComponents().iterator();
                while (it3.hasNext()) {
                    SceneText next2 = it3.next();
                    if (next2.getLanguages() != null) {
                        str2 = next2.getLanguages().get(0);
                    } else {
                        str2 = "";
                    }
                    Integer num = (Integer) hashMap.get(str2);
                    if (num != null) {
                        hashMap.put(str2, Integer.valueOf(num.intValue() + 1));
                    } else {
                        hashMap.put(str2, 1);
                    }
                }
                Map.Entry entry = null;
                int i8 = 0;
                for (Map.Entry entry2 : hashMap.entrySet()) {
                    if (entry == null || ((Integer) entry2.getValue()).compareTo((Integer) entry.getValue()) > 0) {
                        entry = entry2;
                    }
                    i8 += ((Integer) entry2.getValue()).intValue();
                }
                if (entry == null || ((double) (((float) ((Integer) entry.getValue()).intValue()) / ((float) i8))) <= 0.5d) {
                    str = "";
                } else {
                    str = (String) entry.getKey();
                }
                if ("".equals(str3) && !"".equals(str)) {
                    i7 = next.getGoogleBlockIdx();
                    str3 = str;
                }
                if ("".equals(str3) || "".equals(str) || str3.equals(str) || i7 != next.getGoogleBlockIdx()) {
                    next.setGoogleBlockIdx(next.getGoogleBlockIdx() + i2);
                } else {
                    i2++;
                    next.setGoogleBlockIdx(next.getGoogleBlockIdx() + i2);
                }
            }
        }
        return true;
    }
}
