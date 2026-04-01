package com.samsung.android.sum.core.channel;

import android.view.Surface;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.format.MediaFormat;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SurfaceChannelConfig {
    /* access modifiers changed from: private */
    public int format;
    /* access modifiers changed from: private */
    public int height;
    /* access modifiers changed from: private */
    public Consumer<MediaBuffer> metaDataHandler;
    /* access modifiers changed from: private */
    public Surface surface;
    /* access modifiers changed from: private */
    public MediaFormat surfaceFormat;
    /* access modifiers changed from: private */
    public long timeoutInMillis;
    /* access modifiers changed from: private */
    public long usage;
    /* access modifiers changed from: private */
    public int width;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public int format;
        /* access modifiers changed from: private */
        public int height;
        /* access modifiers changed from: private */
        public Consumer<MediaBuffer> metaDataHandler;
        /* access modifiers changed from: private */
        public Surface surface;
        /* access modifiers changed from: private */
        public MediaFormat surfaceFormat;
        /* access modifiers changed from: private */
        public long timeoutInMillis;
        /* access modifiers changed from: private */
        public long usage;
        /* access modifiers changed from: private */
        public int width;

        public Builder() {
        }

        public SurfaceChannelConfig build() {
            return new SurfaceChannelConfig(this);
        }

        public Builder setFormat(int i2) {
            this.format = i2;
            return this;
        }

        public Builder setHeight(int i2) {
            this.height = i2;
            return this;
        }

        public Builder setMetaDataHandler(Consumer<MediaBuffer> consumer) {
            this.metaDataHandler = consumer;
            return this;
        }

        public Builder setSurface(Surface surface2) {
            this.surface = surface2;
            return this;
        }

        public Builder setSurfaceFormat(MediaFormat mediaFormat) {
            this.surfaceFormat = mediaFormat;
            return this;
        }

        public Builder setTimeoutInMillis(long j2) {
            this.timeoutInMillis = j2;
            return this;
        }

        public Builder setUsage(long j2) {
            this.usage = j2;
            return this;
        }

        public Builder setWidth(int i2) {
            this.width = i2;
            return this;
        }

        public Builder(SurfaceChannelConfig surfaceChannelConfig) {
            this.width = surfaceChannelConfig.width;
            this.height = surfaceChannelConfig.height;
            this.format = surfaceChannelConfig.format;
            this.usage = surfaceChannelConfig.usage;
            this.timeoutInMillis = surfaceChannelConfig.timeoutInMillis;
            this.surface = surfaceChannelConfig.surface;
            this.surfaceFormat = surfaceChannelConfig.surfaceFormat;
            this.metaDataHandler = surfaceChannelConfig.metaDataHandler;
        }
    }

    public int getFormat() {
        return this.format;
    }

    public int getHeight() {
        return this.height;
    }

    public Consumer<MediaBuffer> getMetaDataHandler() {
        return this.metaDataHandler;
    }

    public Surface getSurface() {
        return this.surface;
    }

    public MediaFormat getSurfaceFormat() {
        return this.surfaceFormat;
    }

    public long getTimeoutInMillis() {
        return this.timeoutInMillis;
    }

    public long getUsage() {
        return this.usage;
    }

    public int getWidth() {
        return this.width;
    }

    public String toString() {
        return String.join(ArcCommonLog.TAG_COMMA, new CharSequence[]{Def.fmtstr("w/h/fmt/usg(%d/%d/%d/0x%x), timeout=%d ms", Integer.valueOf(this.width), Integer.valueOf(this.height), Integer.valueOf(this.format), Long.valueOf(this.usage), Long.valueOf(this.timeoutInMillis)), "surface=" + this.surface, "surface-format=" + this.surfaceFormat, "metadata-handler=" + this.metaDataHandler});
    }

    private SurfaceChannelConfig(Builder builder) {
        this.width = builder.width;
        this.height = builder.height;
        this.format = builder.format;
        this.usage = builder.usage;
        this.timeoutInMillis = builder.timeoutInMillis;
        this.surface = builder.surface;
        this.surfaceFormat = builder.surfaceFormat;
        this.metaDataHandler = builder.metaDataHandler;
    }
}
