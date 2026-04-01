package com.samsung.android.motionphoto.utils.v2;

import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u0006J\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u000fR\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/SimpleProcessMeasureStrategy;", "Lcom/samsung/android/motionphoto/utils/v2/ProgressMeasureStrategy;", "", "totalFrames", "updateInterval", "<init>", "(II)V", "count", "transcodingFrames", "Lme/x;", "setTotalFrames", "processedFrame", "", "onFrameProcessed", "(I)F", "I", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SimpleProcessMeasureStrategy implements ProgressMeasureStrategy {
    private int totalFrames;
    private final int updateInterval;

    public SimpleProcessMeasureStrategy() {
        this(0, 0, 3, (e) null);
    }

    public float onFrameProcessed(int i2) {
        int i7;
        if (i2 % this.updateInterval != 0 || (i7 = this.totalFrames) <= 0) {
            return -1.0f;
        }
        return ((float) i2) / ((float) i7);
    }

    public void setTotalFrames(int i2, int i7) {
        this.totalFrames = i2 + i7;
    }

    public SimpleProcessMeasureStrategy(int i2, int i7) {
        this.totalFrames = i2;
        this.updateInterval = i7;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SimpleProcessMeasureStrategy(int i2, int i7, int i8, e eVar) {
        this((i8 & 1) != 0 ? 0 : i2, (i8 & 2) != 0 ? 10 : i7);
    }
}
