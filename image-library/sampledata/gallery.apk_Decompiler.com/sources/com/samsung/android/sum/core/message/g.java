package com.samsung.android.sum.core.message;

import android.util.Pair;
import com.samsung.android.sum.core.message.MessagePublisher;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ g(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((Message) obj2).lambda$post$7((MessageChannel) obj);
                return;
            case 1:
                ((MessageChannel) obj).send((Message) obj2);
                return;
            case 2:
                ((MessageConsumer) obj).onMessageReceived((Message) obj2);
                return;
            case 3:
                ((MessageConsumer) obj).onMessageReceived((Message) obj2);
                return;
            default:
                ((MessagePublisher.MessageProducerImpl.AnonymousClass3) obj2).lambda$new$0((Pair) obj);
                return;
        }
    }
}
