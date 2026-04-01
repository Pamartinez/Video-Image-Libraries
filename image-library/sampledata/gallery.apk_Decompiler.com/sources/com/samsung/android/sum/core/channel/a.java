package com.samsung.android.sum.core.channel;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import java.util.Map;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4063a;

    public /* synthetic */ a(int i2) {
        this.f4063a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f4063a) {
            case 0:
                return ChannelRouterBase.lambda$new$0((Map.Entry) obj);
            case 1:
                return SendChannelRouter.lambda$isBufferSupplier$6((BufferChannel) obj);
            case 2:
                return SendChannelRouter.lambda$evaluate$2((MediaBuffer) obj);
            case 3:
                return SendChannelRouter.lambda$getBuffer$7((BufferChannel) obj);
            case 4:
                return SendChannelRouter.lambda$getBuffer$8((BufferChannel) obj);
            default:
                return SendChannelRouter.lambda$isBufferSupplier$5((BufferChannel) obj);
        }
    }
}
