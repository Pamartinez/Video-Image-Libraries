package com.samsung.srcb.unihal;

import N2.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FrameInfo {
    public int frameId;
    public float score;
    public long timeStamp;

    public void setValue(float f, long j2, int i2) {
        this.score = f;
        this.timeStamp = j2;
        this.frameId = i2;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("FrameInfo{score=");
        sb2.append(this.score);
        sb2.append(", timeStamp=");
        sb2.append(this.timeStamp);
        sb2.append(", frameId=");
        return j.e(sb2, this.frameId, '}');
    }
}
