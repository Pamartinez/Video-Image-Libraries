package com.samsung.android.imagetranslation.task;

import A.a;
import android.graphics.Paint;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.data.BlockData;
import com.samsung.android.imagetranslation.jni.KeyFrameParam;
import com.samsung.android.imagetranslation.jni.SceneText;
import com.samsung.android.imagetranslation.util.SceneTextUtil;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextDistribution {
    private static final String TAG = "TextDistribution";
    private static final List<String> noSpaceLangCode = new ArrayList(Arrays.asList(new String[]{"ja", "zh", "lo", "th"}));
    KeyFrameParam keyFrameParam;
    List<String> mTRLResultStr;

    public TextDistribution(KeyFrameParam keyFrameParam2, List<String> list) {
        this.keyFrameParam = keyFrameParam2;
        this.mTRLResultStr = list;
    }

    private ArrayList<Integer> calcTrsCharIdxBasedOnStrLength(ArrayList<SceneText> arrayList, int i2, String str) {
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        Iterator<SceneText> it = arrayList.iterator();
        int i7 = 0;
        int i8 = 0;
        while (it.hasNext()) {
            i8 += it.next().getValue().length();
        }
        Iterator<SceneText> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            i7 += it2.next().getValue().length();
            int ceil = (int) Math.ceil((((double) i7) / ((double) i8)) * ((double) i2));
            arrayList2.add(Integer.valueOf(checkWordBreakUsingIndex(ceil, str) + ceil));
        }
        String str2 = TAG;
        StringBuilder o2 = C0086a.o(i2, "calcTrsCharIdxBasedOnStrLength : tarTRLLength(", ") : ");
        o2.append(arrayList2.toString());
        LTTLogger.d(str2, o2.toString());
        return arrayList2;
    }

    private void charUnitRelativeDistribution(ArrayList<SceneText> arrayList, String str) {
        ArrayList<Integer> calcTrsCharIdxBasedOnStrLength = calcTrsCharIdxBasedOnStrLength(arrayList, str.length(), str);
        boolean z = true;
        if (arrayList.size() <= 1) {
            z = false;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (i2 == 0) {
                arrayList.get(i2).setTrsValue(getCharsFromString(str, 0, calcTrsCharIdxBasedOnStrLength.get(i2).intValue(), z));
            } else {
                arrayList.get(i2).setTrsValue(getCharsFromString(str, calcTrsCharIdxBasedOnStrLength.get(i2 - 1).intValue(), calcTrsCharIdxBasedOnStrLength.get(i2).intValue(), z));
            }
        }
    }

    private void distributeTrsStrToLines(ArrayList<SceneText> arrayList, String str) {
        charUnitRelativeDistribution(arrayList, str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00f2, code lost:
        r2 = r12;
        r3 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x012c, code lost:
        if (r13.equals(r3[r3.length - 1]) == false) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0141, code lost:
        r3 = r19;
        r2 = r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean distributeTrsStrToLinesWithSpace(java.util.ArrayList<com.samsung.android.imagetranslation.jni.SceneText> r20, java.lang.String r21) {
        /*
            r19 = this;
            java.lang.String r0 = TAG
            java.lang.String r1 = "distributeTrsStrToLinesWithSpace"
            com.samsung.android.imagetranslation.common.LTTLogger.d(r0, r1)
            java.lang.String r0 = "\n"
            java.lang.String r1 = ""
            r2 = r21
            java.lang.String r0 = r2.replace(r0, r1)
            java.lang.String r2 = " "
            java.lang.String[] r3 = r0.split(r2)
            int r4 = r3.length
            int r5 = r20.size()
            java.util.Iterator r6 = r20.iterator()
            java.lang.Object r7 = r6.next()
            com.samsung.android.imagetranslation.jni.SceneText r7 = (com.samsung.android.imagetranslation.jni.SceneText) r7
            java.util.Iterator r8 = r20.iterator()
            r10 = 0
        L_0x002b:
            boolean r11 = r8.hasNext()
            if (r11 == 0) goto L_0x0044
            java.lang.Object r11 = r8.next()
            com.samsung.android.imagetranslation.jni.SceneText r11 = (com.samsung.android.imagetranslation.jni.SceneText) r11
            r11.setTrsValue(r1)
            java.lang.String r11 = r11.getValue()
            int r11 = r11.length()
            int r10 = r10 + r11
            goto L_0x002b
        L_0x0044:
            int r8 = r3.length
            r11 = 0
        L_0x0046:
            if (r11 >= r8) goto L_0x013e
            r13 = r3[r11]
            int r14 = r13.length()
            float r14 = (float) r14
            int r15 = r0.length()
            float r15 = (float) r15
            float r14 = r14 / r15
            java.lang.String r15 = r7.getTrsValue()
            int r15 = r15.length()
            float r15 = (float) r15
            r19 = 1
            int r12 = r0.length()
            float r12 = (float) r12
            float r15 = r15 / r12
            java.lang.String r12 = r7.getValue()
            int r12 = r12.length()
            float r12 = (float) r12
            float r9 = (float) r10
            float r12 = r12 / r9
            r16 = r8
            double r8 = (double) r14
            r17 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r8 = (r8 > r17 ? 1 : (r8 == r17 ? 0 : -1))
            if (r8 != 0) goto L_0x0099
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r7.getTrsValue()
            r3.append(r4)
            r3.append(r2)
            r3.append(r13)
            java.lang.String r2 = r3.toString()
            r7.setTrsValue(r2)
            r2 = r19
            r3 = r2
            r12 = 0
            goto L_0x0144
        L_0x0099:
            float r14 = r14 + r15
            int r8 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r8 > 0) goto L_0x00ba
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = r7.getTrsValue()
            r8.append(r9)
            r8.append(r2)
            r8.append(r13)
            java.lang.String r8 = r8.toString()
            r7.setTrsValue(r8)
            r12 = 0
            goto L_0x0138
        L_0x00ba:
            r8 = 1056964608(0x3f000000, float:0.5)
            float r12 = r12 * r8
            int r8 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r8 >= 0) goto L_0x0103
            if (r5 > r4) goto L_0x0103
            float r12 = r12 - r15
            int r8 = r13.length()
            float r8 = (float) r8
            float r12 = r12 * r8
            int r8 = java.lang.Math.round(r12)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r12 = r7.getTrsValue()
            r9.append(r12)
            r9.append(r2)
            r12 = 0
            java.lang.String r14 = r13.substring(r12, r8)
            r9.append(r14)
            java.lang.String r9 = r9.toString()
            r7.setTrsValue(r9)
            boolean r7 = r6.hasNext()
            if (r7 != 0) goto L_0x00f5
        L_0x00f2:
            r2 = r12
            r3 = r2
            goto L_0x0144
        L_0x00f5:
            java.lang.Object r7 = r6.next()
            com.samsung.android.imagetranslation.jni.SceneText r7 = (com.samsung.android.imagetranslation.jni.SceneText) r7
            java.lang.String r8 = r13.substring(r8)
            r7.setTrsValue(r8)
            goto L_0x0138
        L_0x0103:
            r12 = 0
            boolean r8 = r6.hasNext()
            if (r8 != 0) goto L_0x012f
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r7.getTrsValue()
            r4.append(r5)
            r4.append(r2)
            r4.append(r13)
            java.lang.String r2 = r4.toString()
            r7.setTrsValue(r2)
            int r2 = r3.length
            int r2 = r2 + -1
            r2 = r3[r2]
            boolean r2 = r13.equals(r2)
            if (r2 != 0) goto L_0x0141
            goto L_0x00f2
        L_0x012f:
            java.lang.Object r7 = r6.next()
            com.samsung.android.imagetranslation.jni.SceneText r7 = (com.samsung.android.imagetranslation.jni.SceneText) r7
            r7.setTrsValue(r13)
        L_0x0138:
            int r11 = r11 + 1
            r8 = r16
            goto L_0x0046
        L_0x013e:
            r19 = 1
            r12 = 0
        L_0x0141:
            r3 = r19
            r2 = r12
        L_0x0144:
            java.util.Iterator r4 = r20.iterator()
        L_0x0148:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x018c
            java.lang.Object r5 = r4.next()
            com.samsung.android.imagetranslation.jni.SceneText r5 = (com.samsung.android.imagetranslation.jni.SceneText) r5
            java.lang.String r6 = r5.getTrsValue()
            int r6 = r6.length()
            float r6 = (float) r6
            int r7 = r0.length()
            float r7 = (float) r7
            float r6 = r6 / r7
            java.lang.String r7 = r5.getValue()
            int r7 = r7.length()
            float r7 = (float) r7
            float r8 = (float) r10
            float r7 = r7 / r8
            if (r2 != 0) goto L_0x017b
            double r8 = (double) r6
            double r6 = (double) r7
            r13 = 4612811918334230528(0x4004000000000000, double:2.5)
            double r6 = r6 * r13
            int r6 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x017b
            r9 = r12
            goto L_0x018d
        L_0x017b:
            java.lang.String r6 = r5.getTrsValue()
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0148
            java.lang.String r6 = "　"
            r5.setTrsValue(r6)
            goto L_0x0148
        L_0x018c:
            r9 = r3
        L_0x018d:
            if (r9 != 0) goto L_0x01a4
            java.util.Iterator r0 = r20.iterator()
        L_0x0193:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x01a4
            java.lang.Object r1 = r0.next()
            com.samsung.android.imagetranslation.jni.SceneText r1 = (com.samsung.android.imagetranslation.jni.SceneText) r1
            r2 = 0
            r1.setTrsValue(r2)
            goto L_0x0193
        L_0x01a4:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.imagetranslation.task.TextDistribution.distributeTrsStrToLinesWithSpace(java.util.ArrayList, java.lang.String):boolean");
    }

    private boolean fitTokensForGivenFontSize(SceneText sceneText, String[] strArr, int i2, int i7, Paint paint, ArrayList<String> arrayList) {
        String str;
        String str2;
        String str3;
        String[] strArr2 = strArr;
        int i8 = i2;
        Paint paint2 = paint;
        ArrayList<String> arrayList2 = arrayList;
        if (isLangWithoutSpaceDelimiter(this.keyFrameParam.getDestLanguage())) {
            str = "";
        } else {
            str = " ";
        }
        int min = Math.min((int) (((double) i8) * 0.075d * 2.0d), 30);
        String str4 = TAG;
        LTTLogger.d(str4, "Left padding: " + min);
        double d = (double) i7;
        int i10 = (int) (0.1d * d);
        arrayList2.clear();
        double d2 = MapUtil.INVALID_LOCATION;
        String str5 = "";
        int i11 = min;
        double d3 = d;
        int i12 = 0;
        int i13 = 1;
        int i14 = 0;
        while (true) {
            String str6 = str5;
            if (i14 < strArr2.length) {
                String str7 = strArr2[i14];
                if (str7.equals("\n")) {
                    arrayList2.add(str5);
                    i13++;
                    i12 = SceneTextUtil.getTextHeight(str5, paint2);
                    d2 += (double) i12;
                    str2 = str;
                    str5 = "";
                } else {
                    if (str5.equals("")) {
                        str3 = str7;
                    } else {
                        str3 = C0212a.B(str5, str, str7);
                    }
                    str2 = str;
                    if (SceneTextUtil.getTextWidth(str3, paint2) + i11 < i8) {
                        i12 = SceneTextUtil.getTextHeight(str3, paint2);
                        str5 = str3;
                        if (i14 == strArr2.length - 1) {
                            d2 += (double) i12;
                            String str8 = TAG;
                            LTTLogger.d(str8, "Total Height: " + d2);
                        }
                    } else {
                        if (!str6.equals("")) {
                            arrayList2.add(str6);
                        }
                        d2 += (double) i12;
                        if (SceneTextUtil.getTextWidth(str7, paint2) + i11 > i8) {
                            return false;
                        }
                        i12 = SceneTextUtil.getTextHeight(str7, paint2);
                        if (i14 == strArr2.length - 1) {
                            d2 += (double) i12;
                            String str9 = TAG;
                            LTTLogger.d(str9, "Total Height: " + d2);
                        }
                        i13++;
                        str5 = str7;
                    }
                }
                i14++;
                strArr2 = strArr;
                i8 = i2;
                str = str2;
            } else {
                double d5 = d2;
                if (!str6.equals("")) {
                    String str10 = TAG;
                    LTTLogger.d(str10, "Current Line width: " + SceneTextUtil.getTextWidth(str6, paint2));
                    arrayList2.add(str6);
                }
                String str11 = TAG;
                LTTLogger.d(str11, "TOTAL line generated: " + i13);
                int maxTextSize = SceneTextUtil.getMaxTextSize(arrayList2, paint2);
                int min2 = Math.min(maxTextSize / 2, i10);
                LTTLogger.d(str11, "Updated Margin: " + min2);
                double d6 = d3 - d5;
                double d7 = d6 / ((double) (i13 + 1));
                LTTLogger.d(str11, "Updated Margin: totalHeightLeft - " + d6);
                LTTLogger.d(str11, "Updated Margin: spacingBetweenLine - " + d7);
                if (i13 > 1 && d7 < ((double) maxTextSize) / 3.0d) {
                    return false;
                }
                if (i13 == 1 && d6 < ((double) maxTextSize) / 3.0d) {
                    return false;
                }
                sceneText.setMarginY(d7);
                return true;
            }
        }
    }

    private String getCharsFromString(String str, int i2, int i7, boolean z) {
        if (!z && i7 - i2 < 2) {
            return " ";
        }
        String substring = str.substring(i2, i7);
        if (substring.length() != 1 || !substring.equals(" ")) {
            return substring;
        }
        return "　";
    }

    private boolean isLangWithoutSpaceDelimiter(String str) {
        return noSpaceLangCode.contains(str);
    }

    private boolean isSingleLine(ArrayList<SceneText> arrayList) {
        if (arrayList.size() == 1) {
            return true;
        }
        return false;
    }

    public boolean LineBreakDistribution(String str, SceneText sceneText) {
        String str2 = TAG;
        LTTLogger.d(str2, "LineBreakDistribution" + str);
        List asList = Arrays.asList(str.split("\n"));
        LTTLogger.d(str2, "After split trl lines count = " + asList.size());
        int i2 = 0;
        if (asList.size() != sceneText.getComponents().size()) {
            LTTLogger.d(str2, "LineBreakDistribution" + asList.size() + " != " + sceneText.getComponents().size());
            return false;
        }
        Iterator<SceneText> it = sceneText.getComponents().iterator();
        while (it.hasNext()) {
            it.next().setTrsValue((String) asList.get(i2));
            i2++;
        }
        return true;
    }

    public int checkWordBreakUsingIndex(int i2, String str) {
        StringBuilder sb2 = null;
        for (String str2 : str.split(" ")) {
            if (sb2 == null) {
                sb2 = new StringBuilder(str2);
            } else {
                sb2.append(' ');
                sb2.append(str2);
            }
            if (sb2.length() > i2) {
                int length = sb2.length() - i2;
                if (str2.length() - length > length) {
                    return length;
                }
                return -(str2.length() - length);
            }
        }
        return 0;
    }

    public int countTRLReqElements() {
        return this.keyFrameParam.getTrlResult().size();
    }

    public int countTRLResElements() {
        return this.mTRLResultStr.size();
    }

    public boolean distribute() {
        if (isInvalidTRLResult()) {
            return false;
        }
        processTRLUnitParagraph();
        distributeTRLResultsToLines();
        return true;
    }

    public void distributeTRLResultsBasedOnBlockSpace(String str, int i2, int i7, SceneText sceneText) {
        String[] strArr;
        int i8 = i7;
        LTTLogger.d(TAG, "distributeTRLResultsBasedOnBlockSpace");
        String replaceAll = str.replaceAll("\n", " \n ");
        ArrayList arrayList = new ArrayList();
        for (float f = 0.5f; f <= ((float) i8); f += 0.5f) {
            arrayList.add(Float.valueOf(f));
        }
        if (!isLangWithoutSpaceDelimiter(this.keyFrameParam.getDestLanguage())) {
            strArr = replaceAll.trim().split(" ");
        } else {
            replaceAll = replaceAll.replaceAll(" ", "");
            int length = replaceAll.length();
            String[] strArr2 = new String[length];
            for (int i10 = 0; i10 < length; i10++) {
                strArr2[i10] = replaceAll.charAt(i10) + "";
            }
            strArr = strArr2;
        }
        LTTLogger.d(TAG, "trsStr = [" + replaceAll + "]");
        int length2 = strArr.length;
        for (int i11 = 0; i11 < length2; i11++) {
            String str2 = strArr[i11];
            LTTLogger.d(TAG, "token = [" + str2 + "]");
        }
        int length3 = strArr.length;
        String str3 = TAG;
        int i12 = i2;
        StringBuilder h5 = a.h(length3, i12, "Token Num: ", " blockWidth: ", " blockHeight: ");
        h5.append(i8);
        LTTLogger.d(str3, h5.toString());
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size() - 1;
        Paint paint = new Paint();
        int i13 = size;
        float floatValue = ((Float) arrayList.get(size)).floatValue();
        int i14 = 0;
        while (i13 > i14) {
            int D = C0086a.D(i13, i14, 2, i14);
            float floatValue2 = ((Float) arrayList.get(D)).floatValue();
            String str4 = TAG;
            LTTLogger.d(str4, "Checking for font Size of: " + floatValue2);
            paint.setTextSize(floatValue2);
            if (fitTokensForGivenFontSize(sceneText, strArr, i12, i8, paint, arrayList2)) {
                LTTLogger.d(str4, "Current best Fit size: " + floatValue2);
                i14 = D + 1;
                floatValue = floatValue2;
            } else {
                i13 = D - 1;
            }
            i12 = i2;
            i8 = i7;
        }
        SceneText sceneText2 = sceneText;
        String str5 = TAG;
        LTTLogger.d(str5, "Best fit found for size: " + floatValue);
        sceneText2.setFontSize(floatValue);
        paint.setTextSize(floatValue);
        fitTokensForGivenFontSize(sceneText2, strArr, i2, i7, paint, arrayList2);
        LTTLogger.d(str5, "Generated following lines:");
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            LTTLogger.d(TAG, (String) it.next());
        }
        sceneText2.setSplitTRSLines(arrayList2);
    }

    public void distributeTRLResultsToLines() {
        if (this.keyFrameParam.getBlockCount() != this.mTRLResultStr.size()) {
            String str = TAG;
            LTTLogger.e(str, "distributeTRLResultsToLineBasedOnSpace : Error! Block(" + this.keyFrameParam.getBlockCount() + ") != Trs elements" + this.mTRLResultStr.size() + ")");
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<SceneText> it = this.keyFrameParam.getSceneTexts().iterator();
        while (it.hasNext()) {
            SceneText next = it.next();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(next.getComponents());
            arrayList.add(new BlockData(arrayList2, next));
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            BlockData blockData = (BlockData) arrayList.get(i2);
            String str2 = this.mTRLResultStr.get(i2);
            if (blockData.getParagraph().isParagraphRendering()) {
                distributeTRLResultsBasedOnBlockSpace(str2, blockData.getBlockWidth(), blockData.getBlockHeight(), blockData.getParagraph());
            } else if (!LineBreakDistribution(str2, blockData.getParagraph())) {
                distributeTrsResultsBasedOnLinesSpace(str2, blockData);
            }
        }
    }

    public void distributeTrsResultsBasedOnLinesSpace(String str, BlockData blockData) {
        if (blockData.getLinesInBlock() == null) {
            LTTLogger.e(TAG, "Got a null block");
        } else if (isSingleLine(blockData.getLinesInBlock())) {
            blockData.getLinesInBlock().get(0).setTrsValue(str);
        } else if (!distributeTrsStrToLinesWithSpace(blockData.getLinesInBlock(), str)) {
            distributeTrsStrToLines(blockData.getLinesInBlock(), str);
        }
    }

    public boolean isInvalidTRLResult() {
        boolean z;
        int countTRLReqElements = countTRLReqElements();
        int countTRLResElements = countTRLResElements();
        if (countTRLResElements != countTRLReqElements) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            String str = TAG;
            LTTLogger.e(str, "ERROR : TRL Req Element Count=" + countTRLReqElements + " TRL Res Element Count=" + countTRLResElements);
        }
        return z;
    }

    public void processTRLUnitLine() {
        Iterator<SceneText> it = this.keyFrameParam.getSceneTexts().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Iterator<SceneText> it2 = it.next().getComponents().iterator();
            while (it2.hasNext()) {
                it2.next().setTrsValue(this.mTRLResultStr.get(i2));
                i2++;
            }
        }
    }

    public void processTRLUnitParagraph() {
        Iterator<SceneText> it = this.keyFrameParam.getSceneTexts().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            it.next().setTrsValue(this.mTRLResultStr.get(i2));
            i2++;
        }
    }
}
