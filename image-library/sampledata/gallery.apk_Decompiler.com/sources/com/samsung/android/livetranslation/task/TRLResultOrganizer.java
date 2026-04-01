package com.samsung.android.livetranslation.task;

import com.samsung.android.livetranslation.text.KeyFrame;
import com.samsung.android.livetranslation.text.SceneText;
import com.samsung.android.livetranslation.util.LTTLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TRLResultOrganizer {
    private static final String TAG = "TRLResultOrganizer";
    KeyFrame mKeyFrame;
    List<String> mTRLResultStr;

    public TRLResultOrganizer(KeyFrame keyFrame, List<String> list) {
        this.mKeyFrame = keyFrame;
        this.mTRLResultStr = list;
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

    public abstract int countTRLReqElements();

    public abstract int countTRLResElements();

    public abstract void distributeTRLResultsBasedOnBlockSpace(String str, int i2, int i7, SceneText sceneText);

    public void distributeTRLResultsToLines() {
        if (this.mKeyFrame.getGoogleBlockCount() != this.mTRLResultStr.size()) {
            String str = TAG;
            LTTLogger.e(str, "distributeTRLResultsToLineBasedOnSpace : Error! Block(" + this.mKeyFrame.getGoogleBlockCount() + ") != Trs elements" + this.mTRLResultStr.size() + ")");
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<SceneText> it = this.mKeyFrame.getSceneTexts().iterator();
        while (it.hasNext()) {
            SceneText next = it.next();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(next.getComponents());
            arrayList.add(new ParaBlockData(arrayList2, next));
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            ParaBlockData paraBlockData = (ParaBlockData) arrayList.get(i2);
            String str2 = this.mTRLResultStr.get(i2);
            if (paraBlockData.paragraph.isParagraphRendering()) {
                distributeTRLResultsBasedOnBlockSpace(str2, paraBlockData.blockWidth, paraBlockData.blockHeight, paraBlockData.paragraph);
            } else if (!LineBreakDistribution(str2, paraBlockData.paragraph)) {
                distributeTrsResultsBasedOnLinesSpace(str2, paraBlockData);
            }
        }
    }

    public abstract boolean distributeTrsResultsBasedOnLinesSpace(String str, ParaBlockData paraBlockData);

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
        Iterator<SceneText> it = this.mKeyFrame.getSceneTexts().iterator();
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
        Iterator<SceneText> it = this.mKeyFrame.getSceneTexts().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            it.next().setTrsValue(this.mTRLResultStr.get(i2));
            i2++;
        }
    }

    public abstract boolean setTRLResultToKeyFrame();
}
