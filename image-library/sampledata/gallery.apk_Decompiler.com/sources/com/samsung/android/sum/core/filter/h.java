package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.message.Message;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Message e;

    public /* synthetic */ h(Message message, int i2) {
        this.d = i2;
        this.e = message;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Message message = this.e;
        Consumer consumer = (Consumer) obj;
        switch (i2) {
            case 0:
                consumer.accept(message);
                return;
            default:
                consumer.accept(message);
                return;
        }
    }
}
