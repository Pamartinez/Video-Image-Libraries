package com.samsung.scsp.common;

import com.samsung.scsp.error.FaultBarrier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class JournalFactory {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class JournalBase implements Journal {
        private final List<JournalItem> journalItems;

        private JournalBase() {
            this.journalItems = new ArrayList();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$apply$0(Consumer consumer) {
            consumer.accept(new ArrayList(this.journalItems));
            this.journalItems.clear();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$apply$1(Consumer consumer) {
            FaultBarrier.run(new e(0, this, consumer));
        }

        public void apply(Consumer<List<JournalItem>> consumer) {
            new Thread(new d(this, consumer)).start();
        }

        public void begin(String str, String str2) {
            this.journalItems.add(JournalItem.begin(str, str2));
        }

        public void end(String str, String str2) {
            this.journalItems.add(JournalItem.end(str, str2));
        }

        public void error(String str, String str2, int i2) {
            this.journalItems.add(JournalItem.error(str, str2, i2));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        /* access modifiers changed from: private */
        public static final Map<String, Journal> MAP = new HashMap();

        private LazyHolder() {
        }
    }

    public static Journal get(String str) {
        Journal journal = (Journal) LazyHolder.MAP.get(str);
        if (journal != null) {
            return journal;
        }
        JournalBase journalBase = new JournalBase();
        LazyHolder.MAP.put(str, journalBase);
        return journalBase;
    }
}
