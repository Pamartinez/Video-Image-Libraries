package com.samsung.srcb.unihal;

import i.C0212a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActionClipInfo {
    public long endTime;
    public List<FrameInfo> frameInfoList;
    public long postTime;
    public long preTime;
    public int priority;
    public List<SlowFastInfo> slowFastInfoList;
    public long startTime;
    public List<SubClipInfo> subClipInfoList;
    public int typeId;
    public float typeScore;

    public void setValue(long j2, long j3, long j8, long j10, int i2, float f, int i7, Object[] objArr, Object[] objArr2, Object[] objArr3) {
        this.startTime = j2;
        this.endTime = j3;
        this.preTime = j8;
        this.postTime = j10;
        this.typeId = i2;
        this.typeScore = f;
        this.priority = i7;
        this.subClipInfoList = new ArrayList();
        this.frameInfoList = new ArrayList();
        this.slowFastInfoList = new ArrayList();
        if (objArr != null) {
            for (SubClipInfo add : objArr) {
                this.subClipInfoList.add(add);
            }
        }
        if (objArr2 != null) {
            for (FrameInfo add2 : objArr2) {
                this.frameInfoList.add(add2);
            }
        }
        if (objArr3 != null) {
            for (SlowFastInfo add3 : objArr3) {
                this.slowFastInfoList.add(add3);
            }
        }
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("ActionClipInfo{startTime=");
        sb2.append(this.startTime);
        sb2.append(", endTime=");
        sb2.append(this.endTime);
        sb2.append(", typeId=");
        sb2.append(this.typeId);
        sb2.append(", priority=");
        sb2.append(this.priority);
        sb2.append(", typeScore=");
        sb2.append(this.typeScore);
        sb2.append(", subClipInfoList='");
        sb2.append(this.subClipInfoList);
        sb2.append("', slowFastInfo=");
        sb2.append(this.slowFastInfoList);
        sb2.append(", frameInfoList=");
        return C0212a.r(sb2, this.frameInfoList, '}');
    }

    public void setValue(long j2, long j3, long j8, long j10, int i2, float f, Object[] objArr, Object[] objArr2) {
        this.startTime = j2;
        this.endTime = j3;
        this.preTime = j8;
        this.postTime = j10;
        this.typeId = i2;
        this.typeScore = f;
        this.priority = 0;
        this.subClipInfoList = new ArrayList();
        this.frameInfoList = new ArrayList();
        this.slowFastInfoList = new ArrayList();
        if (objArr != null) {
            for (FrameInfo add : objArr) {
                this.frameInfoList.add(add);
            }
        }
        if (objArr2 != null) {
            for (SlowFastInfo add2 : objArr2) {
                this.slowFastInfoList.add(add2);
            }
        }
    }
}
