package Uf;

import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum c {
    NANOSECONDS(TimeUnit.NANOSECONDS),
    MICROSECONDS(TimeUnit.MICROSECONDS),
    MILLISECONDS(TimeUnit.MILLISECONDS),
    SECONDS(TimeUnit.SECONDS),
    MINUTES(TimeUnit.MINUTES),
    HOURS(TimeUnit.HOURS),
    DAYS(TimeUnit.DAYS);
    
    private final TimeUnit timeUnit;

    static {
        c[] cVarArr;
        $ENTRIES = com.samsung.context.sdk.samsunganalytics.internal.sender.c.t(cVarArr);
    }

    /* access modifiers changed from: public */
    c(TimeUnit timeUnit2) {
        this.timeUnit = timeUnit2;
    }

    public final TimeUnit a() {
        return this.timeUnit;
    }
}
