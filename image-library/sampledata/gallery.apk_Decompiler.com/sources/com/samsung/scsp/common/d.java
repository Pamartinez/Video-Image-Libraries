package com.samsung.scsp.common;

import com.samsung.scsp.common.JournalFactory;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ JournalFactory.JournalBase d;
    public final /* synthetic */ Consumer e;

    public /* synthetic */ d(JournalFactory.JournalBase journalBase, Consumer consumer) {
        this.d = journalBase;
        this.e = consumer;
    }

    public final void run() {
        this.d.lambda$apply$1(this.e);
    }
}
