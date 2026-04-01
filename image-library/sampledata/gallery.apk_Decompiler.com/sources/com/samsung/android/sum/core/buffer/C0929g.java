package com.samsung.android.sum.core.buffer;

import android.graphics.Bitmap;
import android.hardware.HardwareBuffer;
import android.media.Image;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.format.MediaFormat;
import java.nio.ByteBuffer;
import java.util.function.BiFunction;

/* renamed from: com.samsung.android.sum.core.buffer.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0929g implements BiFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4048a;

    public /* synthetic */ C0929g(int i2) {
        this.f4048a = i2;
    }

    public final Object apply(Object obj, Object obj2) {
        switch (this.f4048a) {
            case 0:
                return BufferExtension.lambda$new$9((MediaFormat) obj, (HardwareBuffer) obj2);
            case 1:
                return BufferExtension.lambda$new$10((MediaFormat) obj, (Bitmap) obj2);
            case 2:
                return BufferExtension.lambda$new$11((MediaFormat) obj, (ByteBuffer) obj2);
            case 3:
                return Def.check(((MediaFormat) obj).contains("exif"));
            case 4:
                return ((Image) obj2).getHardwareBuffer();
            case 5:
                return BufferExtension.lambda$new$5((MediaFormat) obj, (Number) obj2);
            case 6:
                return BufferExtension.lambda$new$6((MediaFormat) obj, (ByteBuffer) obj2);
            case 7:
                return BufferExtension.lambda$new$8((MediaFormat) obj, (ByteBuffer) obj2);
            default:
                return SLog.w(MediaBufferFileWriter.TAG, "not implement internal compress image writer yet, plz should set explicitly");
        }
    }
}
