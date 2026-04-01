package com.samsung.android.imagetranslation.data;

import android.util.Size;
import com.samsung.android.imagetranslation.jni.SceneText;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BlockData {
    int blockHeight;
    int blockWidth;
    ArrayList<SceneText> linesInBlock;
    SceneText paragraph;

    public BlockData(ArrayList<SceneText> arrayList, SceneText sceneText) {
        this.linesInBlock = arrayList;
        this.paragraph = sceneText;
        Size calculateBlockDimensions = sceneText.calculateBlockDimensions();
        this.blockWidth = calculateBlockDimensions.getWidth();
        this.blockHeight = calculateBlockDimensions.getHeight();
    }

    public int getBlockHeight() {
        return this.blockHeight;
    }

    public int getBlockWidth() {
        return this.blockWidth;
    }

    public ArrayList<SceneText> getLinesInBlock() {
        return this.linesInBlock;
    }

    public SceneText getParagraph() {
        return this.paragraph;
    }

    public void setBlockHeight(int i2) {
        this.blockHeight = i2;
    }

    public void setBlockWidth(int i2) {
        this.blockWidth = i2;
    }

    public void setLinesInBlock(ArrayList<SceneText> arrayList) {
        this.linesInBlock = arrayList;
    }

    public void setParagraph(SceneText sceneText) {
        this.paragraph = sceneText;
    }
}
