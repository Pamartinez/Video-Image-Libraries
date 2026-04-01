package com.samsung.android.sum.core.controller;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.controller.MediaFilterController;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4072a;

    public /* synthetic */ g(int i2) {
        this.f4072a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f4072a) {
            case 0:
                return MediaFilterController.AnonymousClass1.lambda$new$0((MediaBuffer) obj);
            case 1:
                return ((MediaBuffer) obj).asList();
            default:
                return ((Exception) obj).getMessage();
        }
    }
}
