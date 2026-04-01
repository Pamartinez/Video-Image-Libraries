package com.samsung.android.imagetranslation.util;

import C4.i;
import android.content.Context;
import com.samsung.android.gallery.support.utils.H;
import com.samsung.android.imagetranslation.common.Dump;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.imagetranslation.jni.KeyFrame;
import com.samsung.android.imagetranslation.jni.SceneText;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OcrDataToSceneTextConverter {
    private static final String TAG = "OcrDataToSceneTextConverter";
    private int googleBlockIdx;
    private boolean isValid;
    private LttOcrResult mSrc;
    private CopyOnWriteArrayList<SceneText> mTar;

    private List<SceneText> convertStChars(LttOcrResult.WordInfo wordInfo) {
        return (List) wordInfo.getCharInfo().stream().filter(new h(2, this)).map(new p(this, 0)).collect(Collectors.toList());
    }

    private List<SceneText> convertStLines(LttOcrResult.BlockInfo blockInfo, int i2, ArrayList<String> arrayList) {
        return (List) blockInfo.getLineInfo().stream().filter(new j(4)).map(new H(this, arrayList, i2, 1)).collect(Collectors.toList());
    }

    private List<SceneText> convertStWords(LttOcrResult.LineInfo lineInfo) {
        return (List) lineInfo.getWordInfo().stream().filter(new j(5)).map(new p(this, 1)).collect(Collectors.toList());
    }

    private boolean isInvalidCharInfo(LttOcrResult.CharInfo charInfo) {
        if (charInfo.getPoly() == null || charInfo.getChar().equals(" ") || charInfo.getChar().equals("\n")) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$convert$0(LttOcrResult.BlockInfo blockInfo) {
        if (blockInfo.getPoly() != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$convert$1(int i2, Context context, LttOcrResult.BlockInfo blockInfo) {
        this.isValid = false;
        ArrayList arrayList = new ArrayList();
        arrayList.add(blockInfo.getLangCode());
        SceneText sceneText = new SceneText();
        sceneText.setDeviceOrientation(i2);
        sceneText.setType(SceneText.SceneTextType.PARAGRAPH);
        sceneText.setPoly(blockInfo.getPoly());
        sceneText.setValue(blockInfo.getString());
        if (Discriminator.isParagraph(context, blockInfo)) {
            LTTLogger.d(TAG, "Rendering type is Paragraph");
            sceneText.setTrlUnit(KeyFrame.TRL_UNIT.PARAGRAPH.toString());
            sceneText.setParagraphRendering(true);
        } else {
            LTTLogger.d(TAG, "Rendering type is Line");
            sceneText.setTrlUnit(KeyFrame.TRL_UNIT.LINE.toString());
        }
        if (blockInfo.getBlockType() == LttOcrResult.BlockInfo.BLOCK_TYPE.TABULAR.ordinal()) {
            sceneText.setBlockType(blockInfo.getBlockType());
        } else {
            sceneText.setBlockType(0);
        }
        if (Dump.IS_INIT_DUMP_SUCCESS && Dump.RENDERING_TYPE.equalsIgnoreCase("P")) {
            LTTLogger.d(TAG, "Forced paragraph rendering");
            sceneText.setTrlUnit(KeyFrame.TRL_UNIT.PARAGRAPH.toString());
            sceneText.setParagraphRendering(true);
        } else if (Dump.IS_INIT_DUMP_SUCCESS && Dump.RENDERING_TYPE.equalsIgnoreCase("L")) {
            LTTLogger.d(TAG, "Forced line rendering");
            sceneText.setTrlUnit(KeyFrame.TRL_UNIT.LINE.toString());
            sceneText.setParagraphRendering(false);
        }
        sceneText.setLanguages(arrayList);
        sceneText.setComponents(convertStLines(blockInfo, this.googleBlockIdx, sceneText.getLanguages()));
        if (this.isValid) {
            this.mTar.add(sceneText);
            this.googleBlockIdx++;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$convertStChars$6(LttOcrResult.CharInfo charInfo) {
        return !isInvalidCharInfo(charInfo);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ SceneText lambda$convertStChars$7(LttOcrResult.CharInfo charInfo) {
        SceneText sceneText = new SceneText();
        sceneText.setType(SceneText.SceneTextType.CHARACTER);
        sceneText.setValue(charInfo.getChar());
        sceneText.setPoly(charInfo.getPoly());
        this.isValid = true;
        return sceneText;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$convertStLines$2(LttOcrResult.LineInfo lineInfo) {
        if (lineInfo.getPoly() != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ SceneText lambda$convertStLines$3(ArrayList arrayList, int i2, LttOcrResult.LineInfo lineInfo) {
        SceneText sceneText = new SceneText();
        sceneText.setType(SceneText.SceneTextType.LINE);
        sceneText.setPoly(lineInfo.getPoly());
        sceneText.setValue(lineInfo.getString().replace("\n", ""));
        sceneText.setLanguages(arrayList);
        sceneText.setGoogleBlockIdx(i2);
        if (SceneTextUtils.containsOnlyNumbersAndSymbols(sceneText.getValue())) {
            sceneText.setOnlyNumber(true);
        }
        sceneText.setComponents(convertStWords(lineInfo));
        return sceneText;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$convertStWords$4(LttOcrResult.WordInfo wordInfo) {
        if (wordInfo.getPoly() != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ SceneText lambda$convertStWords$5(LttOcrResult.WordInfo wordInfo) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(wordInfo.getLangCode());
        SceneText sceneText = new SceneText();
        sceneText.setType(SceneText.SceneTextType.WORD);
        sceneText.setPoly(wordInfo.getPoly());
        sceneText.setValue(wordInfo.getString().replace("\n", ""));
        sceneText.setLanguages(arrayList);
        sceneText.setComponents(convertStChars(wordInfo));
        return sceneText;
    }

    public boolean convert(LttOcrResult lttOcrResult, CopyOnWriteArrayList<SceneText> copyOnWriteArrayList, int i2, Context context) {
        String str = TAG;
        LTTLogger.d(str, "convert : E");
        if (lttOcrResult == null || copyOnWriteArrayList == null) {
            LTTLogger.d(str, "convert : X with false 1");
            return false;
        }
        this.mSrc = lttOcrResult;
        this.mTar = copyOnWriteArrayList;
        this.googleBlockIdx = 0;
        LTTLogger.d(str, "convert: paragraph number: " + this.mSrc.getBlockInfoList().size());
        this.mSrc.getBlockInfoList().stream().filter(new j(3)).forEach(new i((Object) this, i2, (Object) context, 5));
        if (this.mTar.size() < 1) {
            LTTLogger.d(str, "convert : X with false 2");
            return false;
        }
        LTTLogger.d(str, "convert : X");
        return true;
    }
}
