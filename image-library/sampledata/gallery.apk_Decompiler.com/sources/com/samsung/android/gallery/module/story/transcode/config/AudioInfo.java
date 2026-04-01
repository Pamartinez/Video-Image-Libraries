package com.samsung.android.gallery.module.story.transcode.config;

import Gb.b;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AudioInfo {
    private static final int[] STANDARD_SAMPLE_RATE_HZ = {44100, 48000};
    private int mChannelCount;
    private int mMaxInputSize;
    private int mSampleRateHZ;

    public AudioInfo() {
        this(2, STANDARD_SAMPLE_RATE_HZ[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$hasStandardSampleRate$0(int i2) {
        if (i2 == this.mSampleRateHZ) {
            return true;
        }
        return false;
    }

    public int getChannelCount() {
        return this.mChannelCount;
    }

    public int getSampleRateHz() {
        return this.mSampleRateHZ;
    }

    public boolean hasStandardSampleRate() {
        return Arrays.stream(STANDARD_SAMPLE_RATE_HZ).anyMatch(new b(4, this));
    }

    public void setChannelCount(int i2) {
        this.mChannelCount = i2;
    }

    public void setMaxInputSize(int i2) {
        this.mMaxInputSize = i2;
    }

    public void updateValues(int i2, int i7) {
        this.mChannelCount = i2;
        this.mSampleRateHZ = i7;
    }

    public AudioInfo(int i2) {
        this(2, i2);
    }

    public AudioInfo(int i2, int i7) {
        updateValues(i2, i7);
        setMaxInputSize(0);
    }
}
