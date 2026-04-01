package com.samsung.srcb.unihal;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlowFastInfo {
    public long endTime;
    public long startTime;
    public int typeId;

    public void setValue(int i2, long j2, long j3) {
        this.typeId = i2;
        this.startTime = j2;
        this.endTime = j3;
    }

    public String toString() {
        return "SlowFastInfo{typeId=" + this.typeId + ", typeStr=" + SlowFast.getTypeNameById(this.typeId) + ", startTime=" + this.startTime + ", endTime=" + this.endTime + '}';
    }
}
