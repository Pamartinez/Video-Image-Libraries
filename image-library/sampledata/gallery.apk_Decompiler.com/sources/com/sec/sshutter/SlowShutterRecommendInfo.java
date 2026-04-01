package com.sec.sshutter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlowShutterRecommendInfo {
    long duration = 0;
    int mode = 0;
    long startTimeUs = 0;

    public int getDetectedMode() {
        return this.mode;
    }

    public long getRecommendDuration() {
        return this.duration;
    }

    public long getRecommendStartTime() {
        return this.startTimeUs;
    }
}
