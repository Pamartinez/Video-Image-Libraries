package com.samsung.android.sum.core.buffer;

import com.samsung.android.sum.core.buffer.BufferExtension;
import java.util.Objects;
import java.util.function.Predicate;

/* renamed from: com.samsung.android.sum.core.buffer.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0927e implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4046a;

    public /* synthetic */ C0927e(int i2) {
        this.f4046a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f4046a) {
            case 0:
                return Objects.nonNull((BufferExtension.TransformFunction) obj);
            case 1:
                return MediaBuffer.lambda$mutableOf$0(obj);
            case 2:
                return Objects.nonNull((MediaBuffer) obj);
            case 3:
                return MediaBufferGroup.lambda$getExifBuffer$1((MediaBuffer) obj);
            case 4:
                return MediaBufferGroup.lambda$getIccBuffer$2((MediaBuffer) obj);
            case 5:
                return MediaBufferGroup.lambda$getMetaDataBuffers$0((MediaBuffer) obj);
            case 6:
                return MutableMediaBuffer.lambda$setExifBuffer$11((MediaBuffer) obj);
            case 7:
                return MutableMediaBuffer.lambda$getIccBuffer$10((MediaBuffer) obj);
            case 8:
                return MutableMediaBuffer.lambda$getExifBuffer$9((MediaBuffer) obj);
            case 9:
                return MutableMediaBuffer.lambda$getMetaDataBuffers$8((MediaBuffer) obj);
            default:
                return MutableMediaBuffer.lambda$setIccBuffer$12((MediaBuffer) obj);
        }
    }
}
