package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.message.Message;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Message e;

    public /* synthetic */ c(Message message, int i2) {
        this.d = i2;
        this.e = message;
    }

    public final Object get() {
        int i2 = this.d;
        Message message = this.e;
        switch (i2) {
            case 0:
                return EncoderFilter.lambda$configCodec$1(message);
            case 1:
                return EncoderFilter.lambda$configCodec$3(message);
            default:
                return MediaMuxerFilter.lambda$onMessageReceived$0(message);
        }
    }
}
