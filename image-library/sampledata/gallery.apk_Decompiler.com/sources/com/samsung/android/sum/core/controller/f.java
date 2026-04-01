package com.samsung.android.sum.core.controller;

import com.samsung.android.sum.core.message.Message;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Message f4071a;

    public /* synthetic */ f(Message message) {
        this.f4071a = message;
    }

    public final boolean test(Object obj) {
        return MediaFilterController.lambda$onMessageReceived$10(this.f4071a, (Integer) obj);
    }
}
