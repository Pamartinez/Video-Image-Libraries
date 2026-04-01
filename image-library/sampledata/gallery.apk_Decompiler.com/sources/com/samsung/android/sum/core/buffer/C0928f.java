package com.samsung.android.sum.core.buffer;

import android.hardware.HardwareBuffer;
import android.media.Image;
import java.util.Map;
import java.util.function.Function;

/* renamed from: com.samsung.android.sum.core.buffer.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0928f implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4047a;
    public final /* synthetic */ BufferExtension b;

    public /* synthetic */ C0928f(BufferExtension bufferExtension, int i2) {
        this.f4047a = i2;
        this.b = bufferExtension;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4047a;
        BufferExtension bufferExtension = this.b;
        switch (i2) {
            case 0:
                return bufferExtension.lambda$registerTransform$35((Map.Entry) obj);
            case 1:
                return bufferExtension.lambda$new$14((HardwareBuffer) obj);
            case 2:
                return bufferExtension.lambda$new$20((Image) obj);
            case 3:
                return bufferExtension.getHwBufferId((HardwareBuffer) obj);
            case 4:
                return bufferExtension.lambda$registerDescribe$26((Map.Entry) obj);
            case 5:
                return bufferExtension.lambda$registerAlloc$29((Map.Entry) obj);
            case 6:
                return bufferExtension.lambda$registerDealloc$32((Map.Entry) obj);
            default:
                return bufferExtension.lambda$registerStringfy$39((Map.Entry) obj);
        }
    }
}
