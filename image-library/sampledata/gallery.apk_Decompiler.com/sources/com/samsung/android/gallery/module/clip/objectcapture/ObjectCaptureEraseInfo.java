package com.samsung.android.gallery.module.clip.objectcapture;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ObjectCaptureEraseInfo {
    private EraseType mEraseType = null;
    private final int mFrameIndex;
    private final String mInputPath;
    private final String mOutputImagePath;
    private final String mOutputVideoPath;
    private final boolean mShowEraseDialog;
    private final boolean mVideoMode;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum EraseType {
        IMAGE_FROM_FILE,
        VIDEO_FROM_FILE
    }

    public ObjectCaptureEraseInfo(boolean z, boolean z3, int i2, String str, String str2, String str3) {
        this.mShowEraseDialog = z;
        this.mVideoMode = z3;
        this.mFrameIndex = i2;
        this.mInputPath = str;
        this.mOutputImagePath = str2;
        this.mOutputVideoPath = str3;
    }

    public EraseType getEraseType() {
        return this.mEraseType;
    }

    public int getFrameIndex() {
        return this.mFrameIndex;
    }

    public String getInputPath() {
        return this.mInputPath;
    }

    public String getOutputImagePath() {
        return this.mOutputImagePath;
    }

    public String getOutputVideoPath() {
        return this.mOutputVideoPath;
    }

    public boolean isVideoMode() {
        return this.mVideoMode;
    }

    public void setEraseType(EraseType eraseType) {
        this.mEraseType = eraseType;
    }

    public boolean showEraseDialog() {
        return this.mShowEraseDialog;
    }
}
