package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.os.SystemClock;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\f\u001a\u00020\u0003J\u0006\u0010\r\u001a\u00020\u0003R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/FrameRateStepper;", "", "baseStartTime", "", "<init>", "(F)V", "lastTime", "", "value", "elapsedTime", "getElapsedTime", "()F", "stepDeltaTime", "step", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FrameRateStepper {
    private float elapsedTime;
    private long lastTime;

    public FrameRateStepper() {
        this(0.0f, 1, (e) null);
    }

    public final float getElapsedTime() {
        return this.elapsedTime;
    }

    public final float step() {
        long uptimeMillis = SystemClock.uptimeMillis();
        long j2 = this.lastTime;
        long j3 = uptimeMillis - j2;
        float f = 0.0f;
        if (((float) j2) > 0.0f) {
            f = ((float) j3) / 1000.0f;
        }
        this.lastTime = uptimeMillis;
        float f5 = this.elapsedTime;
        if (f > 0.021f) {
            f = 0.021f;
        }
        float f8 = f5 + f;
        this.elapsedTime = f8;
        return f8;
    }

    public final float stepDeltaTime() {
        long uptimeMillis = SystemClock.uptimeMillis();
        long j2 = this.lastTime;
        long j3 = uptimeMillis - j2;
        float f = 0.0f;
        if (((float) j2) > 0.0f) {
            f = ((float) j3) / 1000.0f;
        }
        this.lastTime = uptimeMillis;
        if (f > 0.021f) {
            return 0.021f;
        }
        return f;
    }

    public FrameRateStepper(float f) {
        this.elapsedTime = f;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FrameRateStepper(float f, int i2, e eVar) {
        this((i2 & 1) != 0 ? 0.0f : f);
    }
}
