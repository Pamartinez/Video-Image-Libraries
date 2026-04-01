package com.samsung.android.gallery.module.abstraction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoReqInfo {
    public boolean isMotionSefPlay;
    public boolean requestAudioFocus;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        VideoReqInfo info = new VideoReqInfo(0);

        public VideoReqInfo build() {
            return this.info;
        }

        public Builder isMotionSefPlay(boolean z) {
            this.info.isMotionSefPlay = z;
            return this;
        }

        public Builder requestAudioFocus(boolean z) {
            this.info.requestAudioFocus = z;
            return this;
        }
    }

    public /* synthetic */ VideoReqInfo(int i2) {
        this();
    }

    private VideoReqInfo() {
        this.isMotionSefPlay = false;
        this.requestAudioFocus = true;
    }
}
