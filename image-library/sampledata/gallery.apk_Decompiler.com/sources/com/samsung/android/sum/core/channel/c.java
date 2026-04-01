package com.samsung.android.sum.core.channel;

import java.util.function.BinaryOperator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements BinaryOperator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4065a;

    public /* synthetic */ c(int i2) {
        this.f4065a = i2;
    }

    public final Object apply(Object obj, Object obj2) {
        BufferChannel bufferChannel = (BufferChannel) obj;
        BufferChannel bufferChannel2 = (BufferChannel) obj2;
        switch (this.f4065a) {
            case 0:
                return ChannelRouterBase.lambda$new$2(bufferChannel, bufferChannel2);
            default:
                return SendChannelRouter.lambda$new$0(bufferChannel, bufferChannel2);
        }
    }
}
