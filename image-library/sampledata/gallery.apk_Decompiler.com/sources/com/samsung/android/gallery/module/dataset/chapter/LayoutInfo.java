package com.samsung.android.gallery.module.dataset.chapter;

import c0.C0086a;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LayoutInfo {
    public Align align = Align.MIDDLE;
    public int chapterId;
    public boolean chunkDisplayable;
    public int chunkId;
    public int chunkType;
    public int groupType;
    public boolean hasExtraInfo;
    public int height;
    public boolean horizontal;
    public boolean isVideo;
    public Layout layoutType = Layout.REAL_RATIO;
    public int mod;
    public int position;
    public boolean similarChunkDisplayable;
    public int width;

    public boolean isSimilarChunk() {
        if (this.chunkType == 4) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("LayoutInfo{");
        sb2.append(this.position);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.groupType);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mod);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.layoutType);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.align);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.chapterId);
        sb2.append(" , ");
        return C0086a.l(sb2, this.chunkId, "}");
    }
}
