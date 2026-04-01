package com.samsung.android.sum.core.channel;

import android.hardware.HardwareBuffer;
import java.util.function.IntPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements IntPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4066a;
    public final /* synthetic */ HardwareBuffer b;

    public /* synthetic */ d(HardwareBuffer hardwareBuffer, int i2) {
        this.f4066a = i2;
        this.b = hardwareBuffer;
    }

    public final boolean test(int i2) {
        int i7 = this.f4066a;
        HardwareBuffer hardwareBuffer = this.b;
        switch (i7) {
            case 0:
                return DetachableSurfaceReadChannel.lambda$onImageAvailable$1(hardwareBuffer, i2);
            default:
                return SurfaceChannelImpl.lambda$onImageReceive$5(hardwareBuffer, i2);
        }
    }
}
