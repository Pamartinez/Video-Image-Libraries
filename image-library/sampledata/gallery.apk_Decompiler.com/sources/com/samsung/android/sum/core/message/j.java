package com.samsung.android.sum.core.message;

import android.util.Pair;
import com.samsung.android.sum.core.message.MessagePublisher;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4131a;
    public final /* synthetic */ MessagePublisher.MessageProducerImpl b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f4132c;
    public final /* synthetic */ Object d;

    public /* synthetic */ j(MessagePublisher.MessageProducerImpl messageProducerImpl, int i2, Object obj, int i7) {
        this.f4131a = i7;
        this.b = messageProducerImpl;
        this.f4132c = i2;
        this.d = obj;
    }

    public final Object apply(Object obj) {
        switch (this.f4131a) {
            case 0:
                return this.b.lambda$newMessage$4(this.f4132c, (Pair[]) this.d, (MessagePublisher) obj);
            default:
                return this.b.lambda$newMessage$2(this.f4132c, this.d, (MessagePublisher) obj);
        }
    }
}
