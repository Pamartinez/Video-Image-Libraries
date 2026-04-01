package com.samsung.android.sum.core.utils.dump;

import Gb.b;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DumpConfig {
    public static final int DUMP_FLAG_DIRECT_STORE = 4;
    public static final int DUMP_FLAG_FLUSH_BEFORE_STORE = 2;
    public static final int DUMP_FLAG_NONE = 1;
    private final int bitDepth;
    private final int channelCount;
    private final long durationMs;
    private final int flags;
    private final int height;
    private final byte paddingValue;
    private final int sampleRate;
    private final int width;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public int bitDepth = 16;
        /* access modifiers changed from: private */
        public int channelCount = 1;
        /* access modifiers changed from: private */
        public long durationMs = -1;
        /* access modifiers changed from: private */
        public int flags = 1;
        /* access modifiers changed from: private */
        public int height = -1;
        /* access modifiers changed from: private */
        public byte paddingValue = 0;
        /* access modifiers changed from: private */
        public int sampleRate = 48000;
        /* access modifiers changed from: private */
        public int width = -1;

        public DumpConfig build() {
            return new DumpConfig(this);
        }

        public Builder setBitDepth(int i2) {
            if (i2 > 0) {
                this.bitDepth = i2;
                return this;
            }
            throw new IllegalArgumentException("Bit depth must be positive");
        }

        public Builder setChannelCount(int i2) {
            if (i2 > 0) {
                this.channelCount = i2;
                return this;
            }
            throw new IllegalArgumentException("Channel count must be positive");
        }

        public Builder setDurationMs(long j2) {
            if (j2 > 0) {
                this.durationMs = j2;
                return this;
            }
            throw new IllegalArgumentException("Duration must be positive");
        }

        public Builder setFlags(int... iArr) {
            for (int i2 : iArr) {
                this.flags = i2 | this.flags;
            }
            return this;
        }

        public Builder setHeight(int i2) {
            this.height = i2;
            return this;
        }

        public Builder setPaddingValue(byte b) {
            this.paddingValue = b;
            return this;
        }

        public Builder setSampleRate(int i2) {
            if (i2 > 0) {
                this.sampleRate = i2;
                return this;
            }
            throw new IllegalArgumentException("Sample rate must be positive");
        }

        public Builder setWidth(int i2) {
            this.width = i2;
            return this;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$containFlags$0(int i2) {
        if ((this.flags & i2) != 0) {
            return true;
        }
        return false;
    }

    public boolean containFlags(int... iArr) {
        return Arrays.stream(iArr).allMatch(new b(3, this));
    }

    public int getBitDepth() {
        return this.bitDepth;
    }

    public int getChannelCount() {
        return this.channelCount;
    }

    public long getDurationMs() {
        return this.durationMs;
    }

    public int getHeight() {
        return this.height;
    }

    public byte getPaddingValue() {
        return this.paddingValue;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public int getWidth() {
        return this.width;
    }

    private DumpConfig(Builder builder) {
        this.flags = builder.flags;
        this.durationMs = builder.durationMs;
        this.sampleRate = builder.sampleRate;
        this.channelCount = builder.channelCount;
        this.bitDepth = builder.bitDepth;
        this.width = builder.width;
        this.height = builder.height;
        this.paddingValue = builder.paddingValue;
    }
}
