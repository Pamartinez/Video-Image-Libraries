package com.samsung.android.sum.core.message;

import com.samsung.android.sum.core.message.MessagePublisher;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MessagePublisher.MessageProducerImpl f4133a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f4134c;
    public final /* synthetic */ Object d;

    public /* synthetic */ k(MessagePublisher.MessageProducerImpl messageProducerImpl, int i2, String str, Object obj) {
        this.f4133a = messageProducerImpl;
        this.b = i2;
        this.f4134c = str;
        this.d = obj;
    }

    public final Object apply(Object obj) {
        return this.f4133a.lambda$newMessage$3(this.b, this.f4134c, this.d, (MessagePublisher) obj);
    }
}
