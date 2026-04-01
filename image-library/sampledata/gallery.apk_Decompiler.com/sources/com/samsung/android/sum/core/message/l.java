package com.samsung.android.sum.core.message;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Message e;

    public /* synthetic */ l(Message message, int i2) {
        this.d = i2;
        this.e = message;
    }

    public final Object get() {
        int i2 = this.d;
        Message message = this.e;
        switch (i2) {
            case 0:
                return ((Request) message).lambda$contentToString$5();
            default:
                return ((Response) message).lambda$contentToString$2();
        }
    }
}
