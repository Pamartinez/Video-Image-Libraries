package com.samsung.android.livetranslation.task;

import android.util.Size;
import com.samsung.android.livetranslation.text.SceneText;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ParaBlockData {
    int blockHeight;
    int blockWidth;
    ArrayList<SceneText> linesInBlock;
    SceneText paragraph;

    public ParaBlockData(ArrayList<SceneText> arrayList, SceneText sceneText) {
        this.linesInBlock = arrayList;
        this.paragraph = sceneText;
        Size calculateBlockDimensions = sceneText.calculateBlockDimensions();
        this.blockWidth = calculateBlockDimensions.getWidth();
        this.blockHeight = calculateBlockDimensions.getHeight();
    }
}
