package com.samsung.android.gallery.support.utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VerboseLogger {
    static final SimpleRingBuffer<String> log = new SimpleRingBuffer<>(600);

    public static String dump() {
        StringBuilder sb2 = new StringBuilder(1024);
        sb2.append("========== VerboseLogger ============\n");
        try {
            log.dump().forEach(new Q(sb2, 1));
        } catch (Exception unused) {
        }
        return sb2.toString();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$dump$0(StringBuilder sb2, String str) {
        sb2.append(str);
        sb2.append("\n");
    }
}
