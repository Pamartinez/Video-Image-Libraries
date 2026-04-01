package com.samsung.android.motionphoto.utils.ex;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/XMPInformation;", "", "primaryPadding", "", "motionphotoLength", "", "<init>", "(IJ)V", "getPrimaryPadding", "()I", "setPrimaryPadding", "(I)V", "getMotionphotoLength", "()J", "setMotionphotoLength", "(J)V", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class XMPInformation {
    private long motionphotoLength;
    private int primaryPadding;

    public XMPInformation(int i2, long j2) {
        this.primaryPadding = i2;
        this.motionphotoLength = j2;
    }

    public final long getMotionphotoLength() {
        return this.motionphotoLength;
    }

    public final int getPrimaryPadding() {
        return this.primaryPadding;
    }

    public final void setMotionphotoLength(long j2) {
        this.motionphotoLength = j2;
    }

    public final void setPrimaryPadding(int i2) {
        this.primaryPadding = i2;
    }
}
