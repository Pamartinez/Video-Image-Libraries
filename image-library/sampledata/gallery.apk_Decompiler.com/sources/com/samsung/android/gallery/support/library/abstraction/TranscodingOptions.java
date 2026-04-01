package com.samsung.android.gallery.support.library.abstraction;

import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TranscodingOptions {
    public boolean apv;
    public int audioCodec;
    public int bitrate;
    public int colorDepth;
    public int frameRate;
    public boolean hdr;
    public int height;
    public String input;
    public int maxSize;
    public String output;
    public int videoCodec;
    public int width;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        private final TranscodingOptions options = new TranscodingOptions();

        public TranscodingOptions build() {
            return this.options;
        }

        public Builder setApv(boolean z) {
            this.options.apv = z;
            return this;
        }

        public Builder setFrameRate(int i2) {
            this.options.frameRate = i2;
            return this;
        }

        public Builder setHdr(boolean z) {
            this.options.hdr = z;
            return this;
        }

        public Builder setHeight(int i2) {
            this.options.height = i2;
            return this;
        }

        public Builder setInput(String str) {
            this.options.input = str;
            return this;
        }

        public Builder setOutput(String str) {
            this.options.output = str;
            return this;
        }

        public Builder setWidth(int i2) {
            this.options.width = i2;
            return this;
        }
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("TranscodingOptions{");
        sb2.append(this.width);
        sb2.append("x");
        sb2.append(this.height);
        sb2.append(",s=");
        sb2.append(this.maxSize);
        sb2.append(",b=");
        sb2.append(this.bitrate);
        sb2.append(",d=");
        sb2.append(this.colorDepth);
        if (this.hdr) {
            str = ",hdr";
        } else {
            str = ",normal";
        }
        sb2.append(str);
        sb2.append(",vc=");
        sb2.append(this.videoCodec);
        sb2.append(",ac=");
        sb2.append(this.audioCodec);
        sb2.append(",fps=");
        return C0086a.l(sb2, this.frameRate, "}");
    }
}
