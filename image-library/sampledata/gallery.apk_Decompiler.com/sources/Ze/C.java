package Ze;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C {
    IGNORE("ignore"),
    WARN("warn"),
    STRICT("strict");
    
    public static final B Companion = null;
    private final String description;

    /* JADX WARNING: type inference failed for: r0v3, types: [Ze.B, java.lang.Object] */
    static {
        C[] cArr;
        $ENTRIES = c.t(cArr);
        Companion = new Object();
    }

    /* access modifiers changed from: public */
    C(String str) {
        this.description = str;
    }

    public final String a() {
        return this.description;
    }
}
