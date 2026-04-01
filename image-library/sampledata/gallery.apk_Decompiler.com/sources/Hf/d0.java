package Hf;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum d0 {
    INVARIANT("", true, true, 0),
    IN_VARIANCE("in", true, false, -1),
    OUT_VARIANCE("out", false, true, 1);
    
    private final boolean allowsInPosition;
    private final boolean allowsOutPosition;
    private final String label;
    private final int superpositionFactor;

    static {
        d0[] d0VarArr;
        $ENTRIES = c.t(d0VarArr);
    }

    /* access modifiers changed from: public */
    d0(String str, boolean z, boolean z3, int i2) {
        this.label = str;
        this.allowsInPosition = z;
        this.allowsOutPosition = z3;
        this.superpositionFactor = i2;
    }

    public final boolean a() {
        return this.allowsOutPosition;
    }

    public final String b() {
        return this.label;
    }

    public final String toString() {
        return this.label;
    }
}
